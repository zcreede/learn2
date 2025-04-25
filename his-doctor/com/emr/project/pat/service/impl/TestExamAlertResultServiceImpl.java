package com.emr.project.pat.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.pat.domain.ExamItem;
import com.emr.project.pat.domain.TestAlertReport;
import com.emr.project.pat.domain.TestExamAlertResult;
import com.emr.project.pat.domain.vo.ExamAlertResultVo;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestAlertReportVo;
import com.emr.project.pat.domain.vo.TestAlertResultVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgVo;
import com.emr.project.pat.mapper.TestExamAlertResultMapper;
import com.emr.project.pat.service.IExamItemService;
import com.emr.project.pat.service.ITestAlertReportService;
import com.emr.project.pat.service.ITestExamAlertResultService;
import com.emr.project.sys.domain.TmBsDefineConfigureP;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestExamAlertResultServiceImpl implements ITestExamAlertResultService {
   @Autowired
   private TestExamAlertResultMapper testExamAlertResultMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IExamItemService examItemService;
   @Autowired
   private ITestAlertReportService testAlertReportService;

   public TestExamAlertResult selectTestExamAlertResultByReportId(String id) {
      return this.testExamAlertResultMapper.selectTestExamAlertResultByReportId(id);
   }

   public List selectTestExamAlertResultList(TestExamAlertResult testExamAlertResult) {
      return this.testExamAlertResultMapper.selectTestExamAlertResultList(testExamAlertResult);
   }

   public TestExamAlertResult insertTestExamAlertResult(TestExamAlertMsgVo alertMsgVo, TestExamAlertResult result) throws Exception {
      TestExamAlertResult testExamAlertResult = new TestExamAlertResult();
      boolean addFlag = true;
      Date alertdt = null;
      Date currDate = this.commonService.getDbSysdate();
      if (result != null) {
         addFlag = false;
         testExamAlertResult = result;
         if (alertMsgVo.getClassCode().equals("02")) {
            ExamItem examItem = this.examItemService.selectExamItemById(alertMsgVo.getId());
            alertdt = examItem.getRecDate() != null ? examItem.getRecDate() : currDate;
         }

         if (alertMsgVo.getClassCode().equals("03")) {
            TestAlertReport testAlertReport = this.testAlertReportService.selectTestAlertReportById(alertMsgVo.getId());
            alertdt = testAlertReport.getAlertdt();
         }
      } else {
         testExamAlertResult.setId(SnowIdUtils.uniqueLong());
         if (alertMsgVo.getClassCode().equals("02")) {
            ExamItem examItem = this.examItemService.selectExamItemById(alertMsgVo.getId());
            testExamAlertResult.setAppCd(examItem.getAppCd());
            testExamAlertResult.setReportId(alertMsgVo.getId());
            testExamAlertResult.setReportItemId(examItem.getExamItemCd());
            alertdt = examItem.getRecDate() != null ? examItem.getRecDate() : currDate;
         }

         if (alertMsgVo.getClassCode().equals("03")) {
            TestAlertReport testAlertReport = this.testAlertReportService.selectTestAlertReportById(alertMsgVo.getId());
            testExamAlertResult.setReportId(alertMsgVo.getId());
            testExamAlertResult.setReportItemId(testAlertReport.getRptItemid());
            alertdt = testAlertReport.getAlertdt();
         }

         testExamAlertResult.setClassCode(alertMsgVo.getClassCode());
      }

      TmBsDefineConfigureP overtimeConfigure = this.commonService.selectDefineConfigureByCode("00010010");
      alertdt = DateUtils.addMinutes(alertdt, Integer.valueOf(overtimeConfigure.getDefaultValue()));
      int overtime = DateUtils.getDateMinutes(alertdt, currDate);
      testExamAlertResult.setOvertimeFlag(overtime >= 0 ? "0" : "1");
      SysUser user = SecurityUtils.getLoginUser().getUser();
      testExamAlertResult.setHandleDocNo(user.getUserName());
      testExamAlertResult.setHandleDocName(user.getNickName());
      testExamAlertResult.setHandleDocTime(currDate);
      testExamAlertResult.setHandleDocContent(alertMsgVo.getConfirmInfo());
      if (addFlag) {
         this.testExamAlertResultMapper.insertTestExamAlertResult(testExamAlertResult);
      } else {
         this.testExamAlertResultMapper.updateTestExamAlertResult(testExamAlertResult);
      }

      return testExamAlertResult;
   }

   public int updateTestExamAlertResult(TestExamAlertResult testExamAlertResult) {
      return this.testExamAlertResultMapper.updateTestExamAlertResult(testExamAlertResult);
   }

   public int deleteTestExamAlertResultByIds(Long[] ids) {
      return this.testExamAlertResultMapper.deleteTestExamAlertResultByIds(ids);
   }

   public int deleteTestExamAlertResultById(Long id) {
      return this.testExamAlertResultMapper.deleteTestExamAlertResultById(id);
   }

   public List testResultlist(TestAlertReportVo testAlertReportVo, String overtime) throws Exception {
      List<TestAlertResultVo> testList = this.testExamAlertResultMapper.selectTestAlertResultList(testAlertReportVo);

      for(TestAlertResultVo t : testList) {
         String contentStr = MessageUtils.message("pat.exam.alert.content", t.getRptItemname(), t.getResult(), t.getResultRef(), t.getAlertrules());
         t.setContentStr(contentStr);
         if (t.getHandleDocTime() != null && StringUtils.isNotBlank(overtime) && t.getAlertdt() != null) {
            int overtimeInt = Integer.valueOf(overtime);
            Date alertdt = t.getAlertdt();
            alertdt = DateUtils.addMinutes(alertdt, Integer.valueOf(overtimeInt));
            int overtimeFlag = DateUtils.getDateMinutes(alertdt, t.getHandleDocTime());
            t.setOvertimeFlag(overtimeFlag >= 0 ? "0" : "1");
         }

         t.setMrFileFlag(t.getMrFileId() != null ? "1" : "0");
      }

      return testList;
   }

   public List examResultlist(ExamItemVo examItemVo, String overtime) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<ExamAlertResultVo> examList = this.testExamAlertResultMapper.selectExamAlertResultList(examItemVo);

      for(ExamAlertResultVo t : examList) {
         String contentStr = MessageUtils.message("pat.exam.alert.content", t.getExamItemName(), t.getExamResSub());
         t.setContentStr(contentStr);
         if (t.getHandleDocTime() != null && StringUtils.isNotBlank(overtime) && t.getRecDate() != null) {
            int overtimeInt = Integer.valueOf(overtime);
            Date alertdt = t.getRecDate();
            alertdt = DateUtils.addMinutes(alertdt, Integer.valueOf(overtimeInt));
            int overtimeFlag = DateUtils.getDateMinutes(alertdt, currDate);
            t.setOvertimeFlag(overtimeFlag >= 0 ? "0" : "1");
         }

         t.setMrFileFlag(t.getMrFileId() != null ? "1" : "0");
      }

      return examList;
   }

   public void updateMrFileId(TestExamAlertResult testExamAlertResult) throws Exception {
      this.testExamAlertResultMapper.updateMrFileId(testExamAlertResult);
   }

   public List selectExamaLertList(ExamItemVo examItemVo) throws Exception {
      return this.testExamAlertResultMapper.selectExamaLertList(examItemVo);
   }

   public List selectExamAlertList(ExamItemVo examItemVo) throws Exception {
      return this.testExamAlertResultMapper.selectExamAlertList(examItemVo);
   }
}
