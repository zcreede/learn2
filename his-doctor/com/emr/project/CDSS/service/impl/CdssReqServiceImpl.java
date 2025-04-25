package com.emr.project.CDSS.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.http.HttpUtils2;
import com.emr.project.CDSS.domain.vo.xyt.CdssDateResVo;
import com.emr.project.CDSS.domain.vo.xyt.CdssPageResVo;
import com.emr.project.CDSS.domain.vo.xyt.CdssResVo;
import com.emr.project.CDSS.domain.vo.xyt.ReqParamVo;
import com.emr.project.CDSS.domain.xyt.Anaphylaxis;
import com.emr.project.CDSS.domain.xyt.Diagnoses;
import com.emr.project.CDSS.domain.xyt.ExamApplication;
import com.emr.project.CDSS.domain.xyt.ExamResult;
import com.emr.project.CDSS.domain.xyt.MedRecordInfo;
import com.emr.project.CDSS.domain.xyt.MedicalOrderMain;
import com.emr.project.CDSS.domain.xyt.OperationApplication;
import com.emr.project.CDSS.domain.xyt.PatientInfo;
import com.emr.project.CDSS.domain.xyt.TestsApplication;
import com.emr.project.CDSS.domain.xyt.TestsResult;
import com.emr.project.CDSS.domain.xyt.TreatmentOrder;
import com.emr.project.CDSS.domain.xyt.VitalSigns;
import com.emr.project.CDSS.service.ICdssReqService;
import com.emr.project.CDSS.xyt.RequestUtil;
import com.emr.project.docOrder.domain.vo.TdCaOperationApplyVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CdssReqServiceImpl implements ICdssReqService {
   protected final Logger log = LoggerFactory.getLogger(CdssReqServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Autowired
   private ITdCaOperationApplyService tdCaOperationApplyService;
   @Autowired
   private IBabyInfoService babyInfoService;

   public CdssPageResVo getCommonPage_mk(String pageName) throws Exception {
      CdssPageResVo resVo = null;
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      String resStr = "";
      switch (this.sysEmrConfigService.selectSysEmrConfigByKey("0039")) {
         case "XYT":
            String cdssBaseUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("003901");
            String cdssBhtKey = this.sysEmrConfigService.selectSysEmrConfigByKey("003902");
            String apiUrl = cdssBaseUrl + RequestUtil.COMMON_PAGE_URL;
            JSONObject conObj = new JSONObject();
            conObj.put("pageName", pageName);
            String content = conObj.toJSONString();
            String type = "json";
            Map<String, String> headers = RequestUtil.getHeaderMap(cdssBhtKey, basEmployee.getEmplNumber(), basEmployee.getEmplName(), user.getDept().getDeptName());
            resStr = HttpUtils2.doPost(apiUrl, content, type, headers);
         default:
            if (StringUtils.isNotBlank(resStr)) {
               JSONObject resObj = JSONObject.parseObject(resStr);
               String resUrl = resObj.get("url").toString();
               resVo = new CdssPageResVo(resUrl, (String)null);
            }

            return resVo;
      }
   }

   public CdssResVo getCheckResult_mk(String patientId, TdPaApplyFormVo tdPaApplyForm) throws Exception {
      CdssResVo res = null;
      String apiUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("003901");
      List<DrugAndClin> allList = tdPaApplyForm.getItemList();
      List<CdssDateResVo> resList = new ArrayList();
      if (allList != null && !allList.isEmpty()) {
         List<DrugAndClin> testList = (List)allList.stream().filter((s) -> s.getOrderClassCode().equals("03")).collect(Collectors.toList());
         List<DrugAndClin> examList = (List)allList.stream().filter((s) -> s.getOrderClassCode().equals("02")).collect(Collectors.toList());
         if (testList != null && !testList.isEmpty()) {
            List<TestsApplication> TestsApplication = this.getTestsApplication(testList, tdPaApplyForm.getApplyTime(), tdPaApplyForm.getPhysicianDptName());
            List<TestsResult> TestsResult = new ArrayList(1);
            ReqParamVo reqParamVo = this.getReqParam(patientId, tdPaApplyForm);
            reqParamVo.setTestsApplication(TestsApplication);
            reqParamVo.setTestsResult(TestsResult);
            String url = apiUrl + RequestUtil.TEST_DATA_URL;
            String resStr = this.getHttpResStr(url, reqParamVo);
            if (StringUtils.isNotEmpty(resStr)) {
               List<CdssDateResVo> testRes = JSONObject.parseArray(resStr, CdssDateResVo.class);
               if (testRes != null && !testRes.isEmpty()) {
                  resList.addAll(testRes);
               }
            }
         }

         if (examList != null && !examList.isEmpty()) {
            List<ExamApplication> ExamApplication = this.getExamApplication(examList, tdPaApplyForm.getApplyTime(), tdPaApplyForm.getPhysicianDptName());
            List<ExamResult> ExamResult = new ArrayList(1);
            ReqParamVo reqParamVo = this.getReqParam(patientId, tdPaApplyForm);
            reqParamVo.setExamApplication(ExamApplication);
            reqParamVo.setExamResult(ExamResult);
            String url = apiUrl + RequestUtil.EXAM_DATA_URL;
            String resStr = this.getHttpResStr(url, reqParamVo);
            if (StringUtils.isNotEmpty(resStr)) {
               List<CdssDateResVo> examRes = JSONObject.parseArray(resStr, CdssDateResVo.class);
               if (examRes != null && !examRes.isEmpty()) {
                  resList.addAll(examRes);
               }
            }
         }

         if (resList != null && !resList.isEmpty()) {
            res = new CdssResVo();
            res.setCdssDateResVoList(resList);
            List<String> alarmLevelList = (List)resList.stream().map(CdssDateResVo::getAlarmLevel).collect(Collectors.toList());
            if (alarmLevelList.contains("0")) {
               res.setCdssConfirmFlag(CommonConstants.BOOL_TRUE);
               List<CdssDateResVo> cdssConfirmList = (List)resList.stream().filter((t) -> t.getAlarmLevel().equals("0")).collect(Collectors.toList());
               res.setCdssConfirmList(cdssConfirmList);
            }

            if (alarmLevelList.contains("9") || alarmLevelList.contains("4")) {
               res.setCdssMsgFlag(CommonConstants.BOOL_TRUE);
               List<CdssDateResVo> cdssMsgList = (List)resList.stream().filter((t) -> t.getAlarmLevel().equals("9") || t.getAlarmLevel().equals("4")).collect(Collectors.toList());
               res.setCdssMsgList(cdssMsgList);
            }
         }
      }

      return res;
   }

   public ReqParamVo getReqParam(String patientId, TdPaApplyFormVo tdPaApplyForm) throws Exception {
      PatientInfo patientInfo = null;
      List<Diagnoses> diagnoses = null;
      List<Anaphylaxis> Anaphylaxis = null;
      List<OperationApplication> OperationApplication = null;
      List<VitalSigns> VitalSigns = new ArrayList(1);
      List<MedicalOrderMain> MedicalOrder = null;
      List<TreatmentOrder> TreatmentOrder = null;
      if (tdPaApplyForm != null && StringUtils.isNotBlank(tdPaApplyForm.getBabyNo()) && !tdPaApplyForm.getBabyNo().equals(patientId)) {
         patientInfo = this.getBabyInfo(patientId, tdPaApplyForm.getBabyNo());
      } else {
         patientInfo = this.getPatientInfo(patientId);
         diagnoses = this.getDiagnoses(patientId);
         Anaphylaxis = this.getAnaphylaxis(patientId);
         OperationApplication = this.getOperationApplication(patientId);
      }

      List<MedRecordInfo> medRecordInfo = null;
      ReqParamVo reqParamVo = new ReqParamVo();
      reqParamVo.setPatientInfo(patientInfo);
      reqParamVo.setDiagnoses(diagnoses);
      reqParamVo.setMedRecordInfo(medRecordInfo);
      reqParamVo.setAnaphylaxis(Anaphylaxis);
      reqParamVo.setVitalSigns(VitalSigns);
      reqParamVo.setMedicalOrder(MedicalOrder);
      reqParamVo.setOperationApplication(OperationApplication);
      reqParamVo.setTreatmentOrder(TreatmentOrder);
      return reqParamVo;
   }

   public String getHttpResStr(String apiUrl, ReqParamVo reqParamVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      JSONObject conObj = (JSONObject)JSONObject.toJSON(reqParamVo);
      String content = conObj.toJSONString();
      this.log.debug("=== CDSS审查新申请检查检验请求内容: " + content);
      String type = "json";
      String cdssBhtKey = this.sysEmrConfigService.selectSysEmrConfigByKey("003902");
      Map<String, String> headers = RequestUtil.getHeaderMap(cdssBhtKey, basEmployee.getEmplNumber(), basEmployee.getEmplName(), user.getDept().getDeptName());
      String resStr = HttpUtils2.doPost(apiUrl, content, type, headers);
      this.log.debug("=== CDSS审查新申请检查检验请求结果: " + resStr);
      return resStr;
   }

   private PatientInfo getPatientInfo(String patientId) throws Exception {
      PatientInfo patientInfo = new PatientInfo();
      VisitinfoPersonalVo visitinfoPersonalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
      patientInfo.setPatientId(patientId);
      patientInfo.setVisitId(visitinfoPersonalVo.getVisitId() + "");
      patientInfo.setSexCode(visitinfoPersonalVo.getSexCd());
      patientInfo.setBirthday(visitinfoPersonalVo.getBirDate() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfoPersonalVo.getBirDate()) : null);
      patientInfo.setIsPregnancy("0");
      patientInfo.setIsLactation("0");
      patientInfo.setDept(visitinfoPersonalVo.getDeptName());
      return patientInfo;
   }

   private PatientInfo getBabyInfo(String patientId, String babyNo) throws Exception {
      PatientInfo patientInfo = new PatientInfo();
      VisitinfoPersonalVo visitinfoPersonalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
      List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(patientId);
      babyInfoList = StringUtils.isNotBlank(babyNo) && CollectionUtils.isNotEmpty(babyInfoList) ? (List)babyInfoList.stream().filter((t) -> t.getPatBabyId().equals(babyNo)).collect(Collectors.toList()) : null;
      BabyInfo babyInfo = CollectionUtils.isNotEmpty(babyInfoList) ? (BabyInfo)babyInfoList.get(0) : null;
      patientInfo.setPatientId(patientId);
      patientInfo.setVisitId(visitinfoPersonalVo.getVisitId() + "");
      patientInfo.setSexCode(babyInfo != null ? babyInfo.getBabySexCd() : null);
      patientInfo.setBirthday(babyInfo.getBirDate() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, babyInfo.getBirDate()) : null);
      patientInfo.setIsPregnancy("0");
      patientInfo.setIsLactation("0");
      patientInfo.setDept(visitinfoPersonalVo.getDeptName());
      return patientInfo;
   }

   private List getDiagnoses(String patientId) throws Exception {
      List<Diagnoses> diagnoses = null;
      List<DiagInfo> diagInfoList = this.diagInfoService.selectDiagInfoByPatientId(patientId);
      if (diagInfoList != null && !diagInfoList.isEmpty()) {
         diagnoses = new ArrayList(diagInfoList.size());

         for(DiagInfo diagInfo : diagInfoList) {
            Diagnoses diagnoses1 = new Diagnoses();
            diagnoses1.setDiagnosisName(diagInfo.getDiagName());
            diagnoses1.setDiagnosisCode(diagInfo.getDiagCd());
            diagnoses1.setDiagnosisType(diagInfo.getDiagTypeName());
            diagnoses1.setDiagnosisMark(CommonConstants.BOOL_TRUE);
            diagnoses1.setDiagnosisDate(diagInfo.getDiaDate() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, diagInfo.getDiaDate()) : null);
            diagnoses.add(diagnoses1);
         }
      }

      return diagnoses;
   }

   private List getAnaphylaxis(String patientId) throws Exception {
      List<Anaphylaxis> anaphylaxes = null;
      AlleInfo param = new AlleInfo();
      param.setPatientId(patientId);
      List<AlleInfo> alleInfoList = this.alleInfoService.selectAlleInfoList(param);
      if (alleInfoList != null && !alleInfoList.isEmpty()) {
         anaphylaxes = new ArrayList(alleInfoList.size());

         for(AlleInfo alleInfo : alleInfoList) {
            Anaphylaxis anaphylaxis = new Anaphylaxis();
            String allergyTypeName = null;
            if (StringUtils.isNotBlank(alleInfo.getAlleType())) {
               switch (alleInfo.getAlleType()) {
                  case "1":
                     allergyTypeName = "药物过敏";
                     break;
                  case "2":
                     allergyTypeName = "食物过敏";
                     break;
                  case "3":
                     allergyTypeName = "其他类型";
               }
            }

            anaphylaxis.setAllergyType(alleInfo.getAlleType());
            anaphylaxis.setAllergenCode(alleInfo.getAlleCode());
            anaphylaxis.setAllergenName(alleInfo.getAlleName());
            anaphylaxis.setAllergyDegree(alleInfo.getCgiSi());
            anaphylaxes.add(anaphylaxis);
         }
      }

      return anaphylaxes;
   }

   private List getExamApplication(List itemList, Date applyDate, String physicianDptName) throws Exception {
      List<ExamApplication> examApplicationList = null;
      if (itemList != null && !itemList.isEmpty()) {
         examApplicationList = new ArrayList(itemList.size());

         for(DrugAndClin drugAndClin : itemList) {
            ExamApplication examApplication = new ExamApplication();
            examApplication.setExamItemCode(drugAndClin.getCpNo());
            examApplication.setExamItemName(drugAndClin.getCpName());
            examApplication.setApplicationTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, applyDate));
            examApplication.setApplicationDept(physicianDptName);
            if (StringUtils.isNotBlank(drugAndClin.getExamPartName())) {
               List<String> bodyParts = Arrays.asList(drugAndClin.getExamPartName().split(","));
               examApplication.setBodyParts(bodyParts);
            }

            examApplicationList.add(examApplication);
         }
      }

      return examApplicationList;
   }

   private List getTestsApplication(List itemList, Date applyDate, String physicianDptName) throws Exception {
      List<TestsApplication> testsApplicationList = null;
      if (itemList != null && !itemList.isEmpty()) {
         testsApplicationList = new ArrayList(itemList.size());

         for(DrugAndClin drugAndClin : itemList) {
            TestsApplication testsApplication = new TestsApplication();
            testsApplication.setTestsItemCode(drugAndClin.getCpNo());
            testsApplication.setTestsItemName(drugAndClin.getCpName());
            testsApplication.setApplicationTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, applyDate));
            testsApplication.setApplicationDept(physicianDptName);
            testsApplication.setTestsSpecimens(drugAndClin.getSpecName());
            testsApplicationList.add(testsApplication);
         }
      }

      return testsApplicationList;
   }

   private List getOperationApplication(String patientId) throws Exception {
      List<OperationApplication> operationApplicationList = null;
      TdCaOperationApplyVo param = new TdCaOperationApplyVo();
      param.setPatientId(patientId);
      List<String> statusList = Arrays.asList("02", "05", "06");
      param.setStatusList(statusList);
      List<TdCaOperationApplyVo> operationApplyList = this.tdCaOperationApplyService.selectTdCaOperationApplyList(param);
      if (operationApplyList != null && !operationApplyList.isEmpty()) {
         operationApplicationList = new ArrayList(operationApplyList.size());

         for(TdCaOperationApplyVo operationApply : operationApplyList) {
            OperationApplication operationApplication = new OperationApplication();
            operationApplication.setOperationCode(operationApply.getPlanOper1Cd());
            String planOper1Prefix = StringUtils.isNotBlank(operationApply.getPlanOper1Prefix()) ? operationApply.getPlanOper1Prefix() : "";
            String planOper1Suffix = StringUtils.isNotBlank(operationApply.getPlanOper1Suffix()) ? operationApply.getPlanOper1Suffix() : "";
            operationApplication.setOperationName(planOper1Prefix + operationApply.getPlanOper1Name() + planOper1Suffix);
            operationApplication.setApplicationTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, operationApply.getApplyDate()));
            operationApplicationList.add(operationApplication);
         }
      }

      return operationApplicationList;
   }
}
