package com.emr.project.pat.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormItemVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.service.ITdPaApplyFormItemService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.pat.domain.TestReport;
import com.emr.project.pat.domain.TestResult;
import com.emr.project.pat.domain.vo.TestReportVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.mapper.TestReportMapper;
import com.emr.project.pat.service.IAppItemService;
import com.emr.project.pat.service.ITestReportService;
import com.emr.project.pat.service.ITestResultService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.service.IClinItemMainService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestReportServiceImpl implements ITestReportService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(TestReportServiceImpl.class);
   @Autowired
   private TestReportMapper testReportMapper;
   @Autowired
   private ITestResultService testResultService;
   @Autowired
   private IAppItemService appItemService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaApplyFormItemService tdPaApplyFormItemService;
   @Autowired
   private IClinItemMainService clinItemMainService;
   @Autowired
   private ITdPaApplyFormService applyFormService;

   public TestReport selectTestReportById(String id) {
      return this.testReportMapper.selectTestReportById(id);
   }

   public List selectTestReportList(TestReport testReport) {
      return this.testReportMapper.selectTestReportList(testReport);
   }

   public List selectTestReportByPatientId(String patientId) {
      return this.testReportMapper.selectTestReportByPatientId(patientId);
   }

   public List selectAppReportItemList(String[] patientIds) throws Exception {
      List<TestReportVo> list = this.testReportMapper.selectAppReportItemList(patientIds);
      List<TestReportVo> normalSignList = this.testReportMapper.selectNormalSignList(patientIds);
      Map<String, List<TdPaApplyFormItem>> formItemMap = new HashMap(1);
      Map<String, List<ClinItemMain>> clinItemMainMap = new HashMap(1);
      if (CollectionUtils.isNotEmpty(list)) {
         List<String> appCdList = (List)list.stream().map((tx) -> tx.getAppCd()).collect(Collectors.toList());
         List<TdPaApplyFormItem> formItemList = this.tdPaApplyFormItemService.selectItemByApplyNoList(appCdList);
         List<String> itemCdList = (List)formItemList.stream().map((tx) -> tx.getItemCode()).distinct().collect(Collectors.toList());
         List<ClinItemMain> clinItemMainList = this.clinItemMainService.selectClinItemMainByItemCds(itemCdList);
         formItemMap = (Map)formItemList.stream().collect(Collectors.groupingBy((tx) -> tx.getApplyFormNo()));
         clinItemMainMap = (Map)clinItemMainList.stream().collect(Collectors.groupingBy((tx) -> tx.getItemCd()));
      }

      for(TestReportVo testReportVo : list) {
         String normalSign2 = "M";

         for(int i = 0; i < normalSignList.size(); ++i) {
            String appCd = ((TestReportVo)normalSignList.get(i)).getAppCd();
            if (appCd.equals(testReportVo.getAppCd()) && ("L".equals(((TestReportVo)normalSignList.get(i)).getNormalSign2()) || "H".equals(((TestReportVo)normalSignList.get(i)).getNormalSign2()))) {
               normalSign2 = "H";
               break;
            }
         }

         testReportVo.setNormalSign2(normalSign2);
         if ("9".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("报告完成");
         } else if ("0".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("开立");
         } else if ("1".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("已提交");
         } else if ("2".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("已撤销");
         } else if ("30".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("未记账");
         } else if ("31".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("已记账");
         } else if ("4".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("已欠费");
         } else if ("5".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("已申请退费");
         } else if ("6".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("已退费");
         } else if ("7".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("医技科室确认");
         } else if ("8".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("上级确认");
         } else if ("99".equals(testReportVo.getItemState())) {
            testReportVo.setItemStateName("已删除");
         }

         List<TdPaApplyFormItemVo> formItemList = new ArrayList(1);
         List<TdPaApplyFormItem> formItemListTemp = (List)formItemMap.get(testReportVo.getAppCd());
         if (CollectionUtils.isNotEmpty(formItemListTemp)) {
            for(TdPaApplyFormItem tdPaApplyFormItem : formItemListTemp) {
               TdPaApplyFormItemVo t = new TdPaApplyFormItemVo();
               t.setItemCode(tdPaApplyFormItem.getItemCode());
               t.setItemName(tdPaApplyFormItem.getItemName());
               List<ClinItemMain> clinItemListTemp = (List)clinItemMainMap.get(t.getItemCode());
               ClinItemMain clinItemMain = CollectionUtils.isNotEmpty(clinItemListTemp) ? (ClinItemMain)clinItemListTemp.get(0) : null;
               if (clinItemMain != null) {
                  t.setIndication(clinItemMain.getIndication());
                  t.setExamNote(clinItemMain.getExamNote());
                  t.setSpecCollectionReq(clinItemMain.getSpecCollectionReq());
                  t.setExamSign(clinItemMain.getExamSign());
                  t.setSupportDiag(clinItemMain.getSupportDiag());
                  t.setExclusionDiag(clinItemMain.getExclusionDiag());
               }

               formItemList.add(t);
            }
         }

         testReportVo.setFormItemList(formItemList);
      }

      return list;
   }

   public List selectTestresultList(TestReportVo testReportVo) throws Exception {
      List<TdPaApplyFormVo> tmInfoList = this.applyFormService.testTmInfo(testReportVo.getAppCd());
      TdPaApplyFormVo tmInfo = CollectionUtils.isNotEmpty(tmInfoList) ? (TdPaApplyFormVo)tmInfoList.get(0) : null;
      testReportVo.setAppCd(tmInfo != null ? tmInfo.getApplyFormNo() : testReportVo.getAppCd());
      testReportVo.setTestRepNo(tmInfo != null ? tmInfo.getBarcodeNo() : null);
      List<TestReportVo> testReportVoList = this.testReportMapper.selectTestresultList(testReportVo);
      if (CollectionUtils.isNotEmpty(testReportVoList)) {
         for(TestReportVo vo : testReportVoList) {
            TestResult testResult = new TestResult();
            testResult.setReportId(vo.getId());
            List<TestResult> testResultVoList = this.testResultService.selectTestResultList(testResult);
            vo.setReasultList(testResultVoList);
            if (tmInfo != null && StringUtils.isNotBlank(tmInfo.getBabyNo())) {
               vo.setPatientName(tmInfo.getBabyName());
               vo.setSex(tmInfo.getBabySex());
               vo.setAge("新生儿");
            } else {
               String age = AgeUtil.getAgeStr(vo.getAgeY(), vo.getAgeM(), vo.getAgeD(), vo.getAgeH(), vo.getAgeMi());
               vo.setAge(age);
            }
         }
      }

      return testReportVoList;
   }

   public int insertTestReport(TestReport testReport) {
      return this.testReportMapper.insertTestReport(testReport);
   }

   public int updateTestReport(TestReport testReport) {
      return this.testReportMapper.updateTestReport(testReport);
   }

   public int deleteTestReportByIds(String[] ids) {
      return this.testReportMapper.deleteTestReportByIds(ids);
   }

   public int deleteTestReportById(String id) {
      return this.testReportMapper.deleteTestReportById(id);
   }

   public List selectAnalysisList(String[] patientIds) {
      List<TestReportVo> reportVoList = new ArrayList();
      List<TestReportVo> list = this.testReportMapper.selectGroupItemList(patientIds);
      List<TestReportVo> normalSignList = this.testReportMapper.selectNormalSignList(patientIds);
      Map<String, Long> counting = (Map)list.stream().collect(Collectors.groupingBy(TestReport::getClinItemCd, Collectors.counting()));
      Map<String, List<TestReportVo>> mapMaItemList = (Map)list.stream().collect(Collectors.groupingBy(TestReport::getClinItemCd));

      for(String clinCode : mapMaItemList.keySet()) {
         TestReportVo reportVo = new TestReportVo();
         reportVo.setClinItemCd(clinCode);
         reportVo.setClinItemName(((TestReportVo)((List)mapMaItemList.get(clinCode)).get(0)).getClinItemName());
         reportVo.setTestCount((Long)counting.get(clinCode));
         reportVo.setSpecName(((TestReportVo)((List)mapMaItemList.get(clinCode)).get(0)).getSpecName());
         String normalSign2 = "M";

         for(int i = 0; i < normalSignList.size(); ++i) {
            if (((TestReportVo)normalSignList.get(i)).getClinItemCd().equals(clinCode) && !"M".equals(((TestReportVo)normalSignList.get(i)).getNormalSign2())) {
               normalSign2 = ((TestReportVo)normalSignList.get(i)).getNormalSign2();
               break;
            }
         }

         reportVo.setNormalSign2(normalSign2);
         reportVoList.add(reportVo);
      }

      Collections.sort(reportVoList, new Comparator() {
         public int compare(TestReport o1, TestReport o2) {
            return o2.getSpecName().compareTo(o1.getSpecName());
         }
      });
      return reportVoList;
   }

   public List selectTestReportVoListByDate(TestReportVo testReportVo) throws Exception {
      return this.testReportMapper.selectTestReportVoListByDate(testReportVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      hisList.forEach((t) -> {
         String con_date_str = t.get("con_date") != null ? t.get("con_date").toString() : null;
         String iss_time_str = t.get("iss_time") != null ? t.get("iss_time").toString() : null;
         String last_alt_time_str = t.get("last_alt_time") != null ? t.get("last_alt_time").toString() : null;
         String cre_date_str = t.get("cre_date") != null ? t.get("cre_date").toString() : null;
         String rep_date_str = t.get("rep_date") != null ? t.get("rep_date").toString() : null;
         Date con_date = null;
         Date iss_time = null;
         Date last_alt_time = null;
         Date cre_date = null;
         Date rep_date = null;

         try {
            con_date = StringUtils.isNoneBlank(new CharSequence[]{con_date_str}) ? DateUtils.parseDate(con_date_str, new String[]{"yyyy.MM.dd"}) : null;
            iss_time = StringUtils.isNoneBlank(new CharSequence[]{iss_time_str}) ? DateUtils.parseDate(iss_time_str, new String[]{"yyyy.MM.dd"}) : null;
            last_alt_time = StringUtils.isNoneBlank(new CharSequence[]{last_alt_time_str}) ? DateUtils.parseDate(last_alt_time_str, new String[]{"yyyy.MM.dd"}) : null;
            cre_date = StringUtils.isNoneBlank(new CharSequence[]{cre_date_str}) ? DateUtils.parseDate(cre_date_str, new String[]{"yyyy.MM.dd"}) : null;
            rep_date = StringUtils.isNoneBlank(new CharSequence[]{rep_date_str}) ? DateUtils.parseDate(rep_date_str, new String[]{"yyyy.MM.dd"}) : null;
         } catch (ParseException e) {
            for(String key : t.keySet()) {
               this.log.info("对象数值===== key:{},value:{}", key, t.get(key) != null ? t.get(key).toString() : "");
            }

            this.log.error("同步检查检验申请单转换日期出现异常，", e.getMessage());
         }

         t.put("con_date", con_date);
         t.put("iss_time", iss_time);
         t.put("last_alt_time", last_alt_time);
         t.put("cre_date", cre_date);
         t.put("rep_date", rep_date);
      });
      int i = 0;

      for(Map temp : hisList) {
         this.log.info("i-> {}", i++);

         try {
            this.testReportMapper.insertMap(temp);
            String appCd = temp.get("app_cd") != null ? temp.get("app_cd").toString() : null;
            String clinItemCd = temp.get("clin_item_cd") != null ? temp.get("clin_item_cd").toString() : null;
            String itemState = "9";
            Date clinRepDate = temp.get("rep_date") != null ? (Date)temp.get("rep_date") : null;
            if (StringUtils.isNotBlank(appCd) && StringUtils.isNotBlank(clinItemCd)) {
               this.appItemService.updateStateDateByApp(appCd, clinItemCd, itemState, clinRepDate);
               this.log.info("更新检验申请单和临床项目的状态");
            }
         } catch (Exception e) {
            for(String key : temp.keySet()) {
               this.log.info("对象数值===== key:{},value:{}", key, temp.get(key) != null ? temp.get(key).toString() : "");
            }

            this.log.error("同步检查检验申请单出现异常，", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      this.syncData(list);
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }

   public void updateTestReportByAppCd(TestReport testReport) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      testReport.setRecDocCd(sysUser.getUserName());
      this.testReportMapper.updateTestReportByAppCd(testReport);
   }

   @DataSource(DataSourceType.hisTestReport)
   public List selectHisReportList(SqlVo sqlVo) throws Exception {
      List<TestReport> list = this.testReportMapper.selectHisReportList(sqlVo);
      if (null != list && list.size() > 0) {
         for(TestReport t : list) {
            t.setAppCd(t.getId());
         }
      }

      return list;
   }

   public List selectHisReport(String id, List list) throws Exception {
      if (null != list && list.size() > 0) {
         TestReportVo testReportVo = new TestReportVo();
         TestReport testReport = (TestReport)list.stream().filter((l) -> l.getId().equals(id)).findFirst().orElse((Object)null);
         if (null != testReport) {
            BeanUtils.copyProperties(testReport, testReportVo);
         }

         VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(testReport.getPatientId());
         List<TestReportVo> resultList = new ArrayList();

         for(TestReport report : list) {
            TestReportVo reportVo = new TestReportVo();
            BeanUtils.copyProperties(report, reportVo);
            if (null != visitinfoVo) {
               reportVo.setPatientName(visitinfoVo.getPatientName());
               reportVo.setInpNo(visitinfoVo.getInpNo());
               reportVo.setCaseNo(visitinfoVo.getRecordNo());
               reportVo.setSex(visitinfoVo.getSex());
               reportVo.setAge(visitinfoVo.getAge());
               reportVo.setBedNo(visitinfoVo.getBedNo());
            }

            resultList.add(reportVo);
         }

         return resultList;
      } else {
         return null;
      }
   }

   public List selectByAppCd(String appCd) throws Exception {
      return this.testReportMapper.selectByAppCd(appCd);
   }

   public List selectByAppCds(List list) throws Exception {
      List<TestReport> resList = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(list)) {
         resList = this.testReportMapper.selectByAppCds(list);
      }

      return resList;
   }

   public List selectMzTestReportList(TestReportVo testReportVo) throws Exception {
      return this.testReportMapper.selectMzTestReportList(testReportVo);
   }
}
