package com.emr.project.pat.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.pat.domain.TestAlertReport;
import com.emr.project.pat.domain.vo.TestAlertReportVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgResVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgVo;
import com.emr.project.pat.mapper.TestAlertReportMapper;
import com.emr.project.pat.service.ITestAlertReportService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrMessage;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.service.ISysDictDataService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestAlertReportServiceImpl implements ITestAlertReportService {
   @Autowired
   private TestAlertReportMapper testAlertReportMapper;
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISysDictDataService dictDataService;

   public TestAlertReport selectTestAlertReportById(String resultalertid) {
      return this.testAlertReportMapper.selectTestAlertReportById(resultalertid);
   }

   public List selectTestAlertReportList(TestAlertReport testAlertReport) {
      return this.testAlertReportMapper.selectTestAlertReportList(testAlertReport);
   }

   public int insertTestAlertReport(TestAlertReport testAlertReport) {
      return this.testAlertReportMapper.insertTestAlertReport(testAlertReport);
   }

   public int updateTestAlertReport(TestAlertReport testAlertReport) {
      return this.testAlertReportMapper.updateTestAlertReport(testAlertReport);
   }

   public int deleteTestAlertReportByIds(String[] resultalertids) {
      return this.testAlertReportMapper.deleteTestAlertReportByIds(resultalertids);
   }

   public int deleteTestAlertReportById(String resultalertid) {
      return this.testAlertReportMapper.deleteTestAlertReportById(resultalertid);
   }

   public TestExamAlertMsgResVo selectTestAlertMsg(TestAlertReportVo param) throws Exception {
      TestExamAlertMsgResVo result = new TestExamAlertMsgResVo();
      List<String> testAlertIdList = param.getResultalertidList();
      param.setResultalertidList((List)null);
      List<TestAlertReportVo> list = this.testAlertReportMapper.selectTestAlertMsg(param);
      Date curnDate = this.commonService.getDbSysdate();
      if (CollectionUtils.isNotEmpty(list)) {
         List<String> admissionNoList = (List)list.stream().filter((tx) -> StringUtils.isNotBlank(tx.getPatNo())).map((tx) -> tx.getPatNo()).collect(Collectors.toList());
         List<EmrMessage> msgList = new ArrayList(list.size());
         List<EmrMessageVo> msgVoList = new ArrayList(list.size());
         List<TestExamAlertMsgVo> alertMsgList = new ArrayList(list.size());

         for(TestAlertReportVo t : list) {
            if (CollectionUtils.isEmpty(testAlertIdList) || CollectionUtils.isNotEmpty(testAlertIdList) && !testAlertIdList.contains(t.getResultalertid())) {
               EmrMessage emrMessage = this.getTestMessage(t, curnDate);
               EmrMessageVo emrMessageVo = new EmrMessageVo();
               BeanUtils.copyProperties(emrMessage, emrMessageVo);
               if (t.getMsgId() == null) {
                  msgList.add(emrMessage);
               }

               msgVoList.add(emrMessageVo);
            }

            TestExamAlertMsgVo testAlertMsgVo = this.getTestAlertMsg(t);
            alertMsgList.add(testAlertMsgVo);
         }

         result.setMsgList(msgVoList);
         result.setAlertMsgVoList(alertMsgList);
         result.setTestAlertList(list);
      }

      return result;
   }

   private EmrMessage getTestMessage(TestAlertReportVo t, Date curnDate) {
      EmrMessage emrMessage = new EmrMessageVo();
      emrMessage.setId(SnowIdUtils.uniqueLong());
      emrMessage.setPatientId(t.getPatId());
      emrMessage.setPatientName(t.getPatName());
      emrMessage.setInpNo(t.getPatNo());
      emrMessage.setRoomNo((String)null);
      emrMessage.setBedNo(t.getReqBedno());
      emrMessage.setDeptCd(t.getReqDeptno());
      emrMessage.setDeptName(t.getReqDeptno());
      emrMessage.setMsgType(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_4);
      String msgContent = MessageUtils.message("pat.testexam.alert.msg", t.getReqBedno(), t.getPatName(), t.getCaseNo(), t.getRptItemname(), t.getResult());
      emrMessage.setMsgContent(msgContent);
      emrMessage.setMsgState(0);
      emrMessage.setBusType(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_5);
      emrMessage.setBusId(t.getResultalertid());
      emrMessage.setCrePerCode(t.getRepDocCd());
      emrMessage.setCrePerName(t.getRepDocName());
      emrMessage.setCreDate(t.getRecieveDt());
      emrMessage.setDoctCd(t.getVisitingStaffNo());
      emrMessage.setDoctName(t.getVisitingStaffName());
      emrMessage.setCreDate(curnDate);
      emrMessage.setMsgWarnType("2");
      return emrMessage;
   }

   private TestExamAlertMsgVo getTestAlertMsg(TestAlertReportVo t) throws Exception {
      TestExamAlertMsgVo testAlertMsgVo = new TestExamAlertMsgVo();
      testAlertMsgVo.setId(t.getResultalertid());
      testAlertMsgVo.setClassCode("03");
      testAlertMsgVo.setItemCd(t.getRptItemcode());
      testAlertMsgVo.setItemName(t.getRptItemname());
      testAlertMsgVo.setPublicDate(t.getRecieveDt());
      testAlertMsgVo.setPatientName(t.getPatientName());
      testAlertMsgVo.setInpNo(t.getInpNo());
      testAlertMsgVo.setCaseNo(t.getCaseNo());
      testAlertMsgVo.setBedNo(t.getBedNo());
      List<SysDictData> sexDictList = this.dictDataService.selectDictDataByType("s028");
      Map<String, List<SysDictData>> sexDictMap = (Map<String, List<SysDictData>>)(CollectionUtils.isNotEmpty(sexDictList) ? (Map)sexDictList.stream().collect(Collectors.groupingBy((s) -> s.getDictValue())) : new HashMap(1));
      List<SysDictData> sexStrList = StringUtils.isNotBlank(t.getSex()) ? (List)sexDictMap.get(t.getSex()) : null;
      String sexStr = CollectionUtils.isNotEmpty(sexStrList) ? ((SysDictData)sexStrList.get(0)).getDictLabel() : null;
      testAlertMsgVo.setSex(sexStr);
      String ageStr = StringUtils.isNotBlank(t.getAge()) ? t.getAge() : AgeUtil.getAgeStr(t.getAgeY(), t.getAgeM(), t.getAgeD(), t.getAgeH(), t.getAgeMi());
      testAlertMsgVo.setAge(ageStr);
      testAlertMsgVo.setResDocCd(t.getResidentNo());
      testAlertMsgVo.setResDocName(t.getResidentName());
      String contentStr = MessageUtils.message("pat.exam.alert.content", t.getRptItemname(), t.getResult(), t.getResultRef(), t.getAlertrules());
      testAlertMsgVo.setContentStr(contentStr);
      testAlertMsgVo.setRepDeptCd(t.getRepDeptCd());
      testAlertMsgVo.setRepDeptName(t.getRepDeptName());
      testAlertMsgVo.setRepDocCd(t.getRepDocCd());
      testAlertMsgVo.setRepDocName(t.getRepDocName());
      testAlertMsgVo.setAppDeptCd(t.getReqDeptno());
      testAlertMsgVo.setAppDeptName(t.getReqDeptname());
      testAlertMsgVo.setPatientId(t.getPatId());
      testAlertMsgVo.setPatientMainId(t.getPatientMainId());
      testAlertMsgVo.setMainFileId(t.getMainFileId());
      return testAlertMsgVo;
   }
}
