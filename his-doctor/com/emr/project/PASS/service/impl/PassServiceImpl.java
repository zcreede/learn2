package com.emr.project.PASS.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.PASS.domain.mk.Allergen;
import com.emr.project.PASS.domain.mk.Drugs;
import com.emr.project.PASS.domain.mk.Exam;
import com.emr.project.PASS.domain.mk.Lab;
import com.emr.project.PASS.domain.mk.MedCond;
import com.emr.project.PASS.domain.mk.Operation;
import com.emr.project.PASS.domain.mk.PassCheckDrug;
import com.emr.project.PASS.domain.mk.Patient;
import com.emr.project.PASS.domain.mk.Recipe;
import com.emr.project.PASS.domain.vo.mk.CheckInfoVo;
import com.emr.project.PASS.service.IPassService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.TdCaOperationApplyVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormItemVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.docOrder.service.ITdPaApplyFormItemService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.esSearch.mapper.DrugStockMapper;
import com.emr.project.operation.mapper.TmBsAccountThirdMapper;
import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpa.domain.OrderFreq;
import com.emr.project.tmpa.mapper.OrderFreqMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassServiceImpl implements IPassService {
   protected final Logger log = LoggerFactory.getLogger(PassServiceImpl.class);
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Autowired
   private ITdCaOperationApplyService tdCaOperationApplyService;
   @Autowired
   private ITdPaApplyFormItemService tdPaApplyFormItemService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private OrderFreqMapper orderFreqMapper;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private DrugStockMapper drugStockMapper;
   @Autowired
   private TmBsAccountThirdMapper tmBsAccountThirdMapper;

   public CheckInfoVo getCheckInfoVo_mk(String patientId, List orderSaveVoList, HerbSaveVo herbSaveVo) throws Exception {
      CheckInfoVo checkInfoVo = new CheckInfoVo();
      TdPaApplyFormVo param = new TdPaApplyFormVo();
      param.setPatientId(patientId);
      List<String> applyFormStatusList = Arrays.asList("1", "30", "31", "7", "8", "9");
      param.setApplyFormStatusList(applyFormStatusList);
      List<TdPaApplyFormItemVo> applyFormItemVoList = this.tdPaApplyFormItemService.selectTdPaApplyFormItemVoList(param);
      Patient patient = this.getPatient(patientId);
      String drugOrderTypeStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0044");
      List<String> drugOrderTypeList = StringUtils.isNotBlank(drugOrderTypeStr) ? Arrays.asList(drugOrderTypeStr.split(",")) : Arrays.asList("3");
      List<Drugs> drugsList = new ArrayList(1);
      List<Drugs> drugsListNew = null;
      List<Drugs> drugsListLong = null;
      List<Drugs> drugsListTemp = null;
      List<Drugs> drugsListLongStopOneDay = null;
      this.log.info("########查询0044配置信息###########");
      if (drugOrderTypeList.contains("3")) {
         if (orderSaveVoList != null && !orderSaveVoList.isEmpty()) {
            drugsListNew = this.getDrugsNew(orderSaveVoList);
         } else if (herbSaveVo != null && herbSaveVo.getOrderDetailList() != null && !herbSaveVo.getOrderDetailList().isEmpty()) {
            drugsListNew = this.getDrugsNew(herbSaveVo);
         }

         if (drugsListNew != null) {
            drugsList.addAll(drugsListNew);
         }
      }

      if (drugOrderTypeList.contains("1")) {
         drugsListLong = this.getDrugsLongUsing(patientId);
         if (drugsListLong != null) {
            drugsList.addAll(drugsListLong);
         }
      }

      if (drugOrderTypeList.contains("2")) {
         drugsListTemp = this.getDrugsTempUsing(patientId);
         if (drugsListTemp != null) {
            drugsList.addAll(drugsListTemp);
         }
      }

      if (drugOrderTypeList.contains("4")) {
         this.log.info("########一天内出院###########");
         drugsListLongStopOneDay = this.getDrugsLongStopOneDay(patientId);
         if (drugsListLongStopOneDay != null) {
            drugsList.addAll(drugsListLongStopOneDay);
         }
      }

      List<Recipe> recipeList = null;
      List<MedCond> medCondList = this.getMedCond(patientId);
      List<Allergen> allergenList = this.getAllergen(patientId);
      List<Operation> operationList = this.getOperation(patientId);
      List<TdPaApplyFormItemVo> examApplyFormList = applyFormItemVoList != null ? (List)applyFormItemVoList.stream().filter((t) -> t.getApplyFormClassCode().equals("02")).collect(Collectors.toList()) : null;
      List<Exam> examList = this.getExam(examApplyFormList);
      List<TdPaApplyFormItemVo> labApplyFormList = applyFormItemVoList != null ? (List)applyFormItemVoList.stream().filter((t) -> t.getApplyFormClassCode().equals("03")).collect(Collectors.toList()) : null;
      List<Lab> labList = this.getLab(labApplyFormList);
      checkInfoVo.setPatientO(patient);
      checkInfoVo.setDrugsListO(drugsList);
      checkInfoVo.setRecipeListO(recipeList);
      checkInfoVo.setMedCondListO(medCondList);
      checkInfoVo.setAllergenListO(allergenList);
      checkInfoVo.setOperationListO(operationList);
      checkInfoVo.setExamListO(examList);
      checkInfoVo.setLabListO(labList);
      checkInfoVo = checkInfoVo.toJsonInfo();
      return checkInfoVo;
   }

   public CheckInfoVo getCheckInfoForZcyVo_mk(String patientId, List orderSaveVoList, HerbSaveVo herbSaveVo) throws Exception {
      CheckInfoVo checkInfoVo = new CheckInfoVo();
      TdPaApplyFormVo param = new TdPaApplyFormVo();
      param.setPatientId(patientId);
      List<String> applyFormStatusList = Arrays.asList("1", "30", "31", "7", "8", "9");
      param.setApplyFormStatusList(applyFormStatusList);
      List<TdPaApplyFormItemVo> applyFormItemVoList = this.tdPaApplyFormItemService.selectTdPaApplyFormItemVoList(param);
      Patient patient = null;
      String drugOrderTypeStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0044");
      List<String> drugOrderTypeList = StringUtils.isNotBlank(drugOrderTypeStr) ? Arrays.asList(drugOrderTypeStr.split(",")) : Arrays.asList("3");
      List<Drugs> drugsList = new ArrayList(1);
      List<Drugs> drugsListNew = null;
      List<Drugs> drugsListLong = null;
      List<Drugs> drugsListTemp = null;
      List<Drugs> drugsListLongStopOneDay = null;
      this.log.info("########查询0044配置信息###########");
      if (drugOrderTypeList.contains("3")) {
         if (orderSaveVoList != null && !orderSaveVoList.isEmpty()) {
            drugsListNew = this.getDrugsNew(orderSaveVoList);
         } else if (herbSaveVo != null && herbSaveVo.getOrderDetailList() != null && !herbSaveVo.getOrderDetailList().isEmpty()) {
            drugsListNew = this.getDrugsNew(herbSaveVo);
         }

         if (drugsListNew != null) {
            drugsList.addAll(drugsListNew);
         }
      }

      if (drugOrderTypeList.contains("1")) {
         drugsListLong = this.getDrugsLongUsing(patientId);
         if (drugsListLong != null) {
            drugsList.addAll(drugsListLong);
         }
      }

      if (drugOrderTypeList.contains("2")) {
         drugsListTemp = this.getDrugsTempUsing(patientId);
         if (drugsListTemp != null) {
            drugsList.addAll(drugsListTemp);
         }
      }

      if (drugOrderTypeList.contains("4")) {
         this.log.info("########一天内出院###########");
         drugsListLongStopOneDay = this.getDrugsLongStopOneDay(patientId);
         if (drugsListLongStopOneDay != null) {
            drugsList.addAll(drugsListLongStopOneDay);
         }
      }

      List<Recipe> recipeList = null;
      List<MedCond> medCondList = null;
      List<Operation> operationList = null;
      if (applyFormItemVoList != null) {
         List var10000 = (List)applyFormItemVoList.stream().filter((t) -> t.getApplyFormClassCode().equals("02")).collect(Collectors.toList());
      } else {
         Object var28 = null;
      }

      List<Exam> examList = null;
      if (applyFormItemVoList != null) {
         List var29 = (List)applyFormItemVoList.stream().filter((t) -> t.getApplyFormClassCode().equals("03")).collect(Collectors.toList());
      } else {
         Object var30 = null;
      }

      List<Lab> labList = null;
      List<Allergen> allergenList = this.getAllergen(patientId);
      checkInfoVo.setPatientO(patient);
      checkInfoVo.setDrugsListO(drugsList);
      checkInfoVo.setRecipeListO(recipeList);
      checkInfoVo.setMedCondListO(medCondList);
      checkInfoVo.setAllergenListO(allergenList);
      checkInfoVo.setOperationListO(operationList);
      checkInfoVo.setExamListO(examList);
      checkInfoVo.setLabListO(labList);
      checkInfoVo = checkInfoVo.toJsonInfo();
      return checkInfoVo;
   }

   public CheckInfoVo getCheckInfoForDrug_mk(String patientId, List orderSaveVoList, HerbSaveVo herbSaveVo, String orgCd) throws Exception {
      CheckInfoVo checkInfoVo = new CheckInfoVo();
      TdPaApplyFormVo param = new TdPaApplyFormVo();
      param.setPatientId(patientId);
      List<String> applyFormStatusList = Arrays.asList("1", "30", "31", "7", "8", "9");
      param.setApplyFormStatusList(applyFormStatusList);
      this.tdPaApplyFormItemService.selectTdPaApplyFormItemVoList(param);
      String drugOrderTypeStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0044");
      List<String> drugOrderTypeList = StringUtils.isNotBlank(drugOrderTypeStr) ? Arrays.asList(drugOrderTypeStr.split(",")) : Arrays.asList("3");
      List<Drugs> drugsList = new ArrayList(1);
      List<Drugs> drugsListNew = null;
      List<Drugs> drugsListLong = null;
      List<Drugs> drugsListTemp = null;
      List<Drugs> drugsListLongStopOneDay = null;
      List<Drugs> drugsCheckPending = null;
      this.log.info("########查询0044配置信息###########");
      if (drugOrderTypeList.contains("3")) {
         if (orderSaveVoList != null && !orderSaveVoList.isEmpty()) {
            drugsListNew = this.getDrugsNew(orderSaveVoList);
         } else if (herbSaveVo != null && herbSaveVo.getOrderDetailList() != null && !herbSaveVo.getOrderDetailList().isEmpty()) {
            drugsListNew = this.getDrugsNew(herbSaveVo);
         }

         if (drugsListNew != null) {
            drugsList.addAll(drugsListNew);
         }

         drugsCheckPending = this.getDrugsCheckPending(patientId);
         if (drugsCheckPending != null) {
            drugsList.addAll(drugsCheckPending);
         }
      }

      if (drugOrderTypeList.contains("1")) {
         drugsListLong = this.getDrugsLongUsing(patientId);
         if (drugsListLong != null) {
            drugsList.addAll(drugsListLong);
         }
      }

      if (drugOrderTypeList.contains("2")) {
         drugsListTemp = this.getDrugsTempUsing(patientId);
         if (drugsListTemp != null) {
            drugsList.addAll(drugsListTemp);
         }
      }

      if (CollectionUtils.isNotEmpty(drugsList)) {
         this.passCheckDrug(drugsList, orgCd);
      }

      if (CollectionUtils.isNotEmpty(drugsList)) {
         VisitinfoPersonalVo visitinfoPersonalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
         String medicalcharge = visitinfoPersonalVo.getHospitalType();
         String payClass = "0";
         if (StringUtils.isNotBlank(medicalcharge)) {
            payClass = "1";
         }

         for(Drugs drugs : drugsList) {
            drugs.setPayClass(payClass);
         }
      }

      List<Recipe> recipeList = null;
      List<MedCond> medCondList = this.getMedCond(patientId);
      List<Operation> operationList = null;
      List<Allergen> allergenList = this.getAllergen(patientId);
      checkInfoVo.setDrugsListO(drugsList);
      checkInfoVo.setRecipeListO(recipeList);
      checkInfoVo.setMedCondListO(medCondList);
      checkInfoVo.setAllergenListO(allergenList);
      checkInfoVo.setOperationListO(operationList);
      checkInfoVo = checkInfoVo.toJsonInfo();
      return checkInfoVo;
   }

   public CheckInfoVo getCheckInfo_mk(String patientId, List orderSaveVoList, HerbSaveVo herbSaveVo) throws Exception {
      CheckInfoVo checkInfoVo = new CheckInfoVo();
      TdPaApplyFormVo param = new TdPaApplyFormVo();
      param.setPatientId(patientId);
      List<String> applyFormStatusList = Arrays.asList("1", "30", "31", "7", "8", "9");
      param.setApplyFormStatusList(applyFormStatusList);
      List<TdPaApplyFormItemVo> applyFormItemVoList = this.tdPaApplyFormItemService.selectTdPaApplyFormItemVoList(param);
      List<TdPaApplyFormItemVo> examApplyFormList = applyFormItemVoList != null ? (List)applyFormItemVoList.stream().filter((t) -> t.getApplyFormClassCode().equals("02")).collect(Collectors.toList()) : null;
      List<Exam> examList = this.getExam(examApplyFormList);
      List<TdPaApplyFormItemVo> labApplyFormList = applyFormItemVoList != null ? (List)applyFormItemVoList.stream().filter((t) -> t.getApplyFormClassCode().equals("03")).collect(Collectors.toList()) : null;
      List<Lab> labList = this.getLab(labApplyFormList);
      Patient patient = this.getPatient(patientId);
      List<Recipe> recipeList = null;
      List<MedCond> medCondList = this.getMedCond(patientId);
      List<Allergen> allergenList = this.getAllergen(patientId);
      List<Operation> operationList = this.getOperation(patientId);
      checkInfoVo.setPatientO(patient);
      checkInfoVo.setRecipeListO(recipeList);
      checkInfoVo.setMedCondListO(medCondList);
      checkInfoVo.setAllergenListO(allergenList);
      checkInfoVo.setOperationListO(operationList);
      checkInfoVo.setExamListO(examList);
      checkInfoVo.setLabListO(labList);
      checkInfoVo = checkInfoVo.toJsonInfo();
      return checkInfoVo;
   }

   private void passCheckDrug(List drugsList, String orgCd) throws Exception {
      List<String> cpNoList = (List)drugsList.stream().map(Drugs::getDrugUniqueCode).collect(Collectors.toList());
      List<PassCheckDrug> drugList = this.drugStockMapper.selectDrugsCostType(orgCd, cpNoList);
      if (!drugList.isEmpty()) {
         List<String> codeList = this.tmBsAccountThirdMapper.selectThirdCodeByFirst();
         if (!codeList.isEmpty()) {
            Iterator<Drugs> iterator = drugsList.iterator();

            while(iterator.hasNext()) {
               Drugs next = (Drugs)iterator.next();
               String cpNo = next.getDrugUniqueCode();
               PassCheckDrug passCheckDrug = (PassCheckDrug)drugList.stream().filter((t) -> t.getYpbm().equals(cpNo)).findFirst().orElse((Object)null);
               if (passCheckDrug != null) {
                  String hsxm = passCheckDrug.getHsxm();
                  if (!codeList.contains(hsxm)) {
                     iterator.remove();
                  }
               } else {
                  iterator.remove();
               }
            }
         }
      } else {
         new ArrayList();
      }

   }

   public List checkOrderFirst(List orderSaveVoList, String orgCd) throws Exception {
      if (CollectionUtils.isNotEmpty(orderSaveVoList)) {
         List<String> cpNoList = (List)orderSaveVoList.stream().map(OrderSaveVo::getCpNo).collect(Collectors.toList());
         List<PassCheckDrug> drugList = this.drugStockMapper.selectDrugsCostType(orgCd, cpNoList);
         if (!drugList.isEmpty()) {
            List<String> codeList = this.tmBsAccountThirdMapper.selectThirdCodeByFirst();
            if (!codeList.isEmpty()) {
               Iterator<OrderSaveVo> iterator = orderSaveVoList.iterator();

               while(iterator.hasNext()) {
                  OrderSaveVo next = (OrderSaveVo)iterator.next();
                  String cpNo = next.getCpNo();
                  PassCheckDrug passCheckDrug = (PassCheckDrug)drugList.stream().filter((t) -> t.getYpbm().equals(cpNo)).findFirst().orElse((Object)null);
                  if (passCheckDrug != null) {
                     String hsxm = passCheckDrug.getHsxm();
                     if (!codeList.contains(hsxm)) {
                        iterator.remove();
                     }
                  } else {
                     iterator.remove();
                  }
               }
            }
         } else {
            orderSaveVoList = new ArrayList();
         }
      }

      return orderSaveVoList;
   }

   private Patient getPatient(String patientId) throws Exception {
      Patient patient = new Patient();
      VisitinfoPersonalVo visitinfoPersonalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
      Personalinfo personalinfo = this.personalinfoService.selectPersonalinfoById(patientId);
      patient.setPatCode(visitinfoPersonalVo.getPatientId());
      patient.setInHospNo(visitinfoPersonalVo.getInpNo());
      patient.setVisitCode(visitinfoPersonalVo.getVisitId() + "");
      patient.setName(visitinfoPersonalVo.getPatientName());
      patient.setSex(visitinfoPersonalVo.getSex());
      Date birDate = personalinfo.getBirDate();
      String birDateStr = birDate != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, birDate) : DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
      patient.setBirthday(birDateStr);
      if ("0".equals(visitinfoPersonalVo.getHeight())) {
         patient.setHeightCM("");
      } else {
         patient.setHeightCM(visitinfoPersonalVo.getHeight());
      }

      if ("0".equals(visitinfoPersonalVo.getWeight())) {
         patient.setWeighKG("");
      } else {
         patient.setWeighKG(visitinfoPersonalVo.getWeight());
      }

      patient.setDeptCode(visitinfoPersonalVo.getDeptCd());
      patient.setDeptName(visitinfoPersonalVo.getDeptName());
      patient.setDoctorCode(visitinfoPersonalVo.getResDocCd());
      patient.setDoctorName(visitinfoPersonalVo.getResDocName());
      Integer patStatus = visitinfoPersonalVo.getOutTime() == null ? 1 : 0;
      patient.setPatStatus(patStatus);
      patient.setIsLactation(-1);
      patient.setIsPregnancy(-1);
      patient.setPregStartDate((String)null);
      patient.setHepDamageDegree(-1);
      patient.setCheckMode("zy");
      patient.setIsDoSave(1);
      String ageStr = AgeUtil.getAgeStr(visitinfoPersonalVo.getAgeY(), visitinfoPersonalVo.getAgeM(), visitinfoPersonalVo.getAgeD(), visitinfoPersonalVo.getAgeH(), visitinfoPersonalVo.getAgeMi());
      patient.setAge(ageStr);
      String inHospDate = visitinfoPersonalVo.getInhosTime() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfoPersonalVo.getInhosTime()) : null;
      patient.setInHospDate(inHospDate);
      String outHospDate = visitinfoPersonalVo.getOutTime() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfoPersonalVo.getOutTime()) : null;
      patient.setOutHospDate(outHospDate);
      patient.setIsTestEtiology(0);
      patient.setIDCard(personalinfo.getIdCard());
      patient.setTelephone(personalinfo.getPatientTel());
      patient.setUrgent(0);
      String payTypeCd = visitinfoPersonalVo.getPayTypeCd();
      String medicareType = null;
      String payTypeName = visitinfoPersonalVo.getPayTypeName();
      if (StringUtils.isNotBlank(payTypeName)) {
         medicareType = payTypeName;
      }

      patient.setMedicareType(medicareType);
      String medicalcharge = visitinfoPersonalVo.getHospitalType();
      if (StringUtils.isNotBlank(medicalcharge)) {
         if (medicalcharge.equals("21")) {
            medicalcharge = "1";
         } else if (medicalcharge.equals("52")) {
            medicalcharge = "2";
         } else if (medicalcharge.equals("990401")) {
            medicalcharge = "4";
         } else if (medicalcharge.equals("21")) {
            medicalcharge = "5";
         } else if (medicalcharge.equals("26")) {
            medicalcharge = "7";
         } else {
            medicalcharge = "1";
         }
      }

      patient.setMedicalcharge(medicalcharge);
      if (StringUtils.isNotBlank(medicalcharge)) {
         patient.setPayClass("1");
      } else {
         patient.setPayClass("0");
      }

      patient.setIsTestEtiology(0);
      patient.setHospitallevel("10");
      patient.setMultidaydosepriv("0");
      patient.setBedno(visitinfoPersonalVo.getBedNo());
      return patient;
   }

   private List getMedCond(String patientId) throws Exception {
      List<MedCond> medCondList = null;
      List<DiagInfo> diagInfoList = this.diagInfoService.selectDiagInfoByPatientId(patientId);
      if (diagInfoList != null && !diagInfoList.isEmpty()) {
         medCondList = new ArrayList(diagInfoList.size());

         for(int i = 0; i < diagInfoList.size(); ++i) {
            DiagInfo diagInfo = (DiagInfo)diagInfoList.get(i);
            MedCond medCond = new MedCond();
            medCond.setIndex(i + "");
            medCond.setDiseaseCode(diagInfo.getDiagCd());
            medCond.setDiseaseName(diagInfo.getDiagName());
            Date diaDate = diagInfo.getDiaDate();
            String starttime = diaDate != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, diaDate) : null;
            medCond.setStarttime(starttime);
            medCondList.add(medCond);
         }
      }

      return medCondList;
   }

   private List getAllergen(String patientId) throws Exception {
      List<Allergen> allergenList = null;
      AlleInfo param = new AlleInfo();
      param.setPatientId(patientId);
      List<AlleInfo> alleInfoList = this.alleInfoService.selectAlleInfoList(param);
      if (alleInfoList != null && !alleInfoList.isEmpty()) {
         allergenList = new ArrayList(alleInfoList.size());

         for(int i = 0; i < alleInfoList.size(); ++i) {
            AlleInfo alleInfo = (AlleInfo)alleInfoList.get(i);
            Allergen allergen = new Allergen();
            allergen.setIndex(i + "");
            allergen.setAllerCode(alleInfo.getAlleCode());
            allergen.setAllerName(alleInfo.getAlleName());
            allergen.setAllerSymptom(alleInfo.getSymDesc());
            allergenList.add(allergen);
         }
      }

      return allergenList;
   }

   private List getOperation(String patientId) throws Exception {
      List<Operation> operationList = null;
      TdCaOperationApplyVo param = new TdCaOperationApplyVo();
      param.setPatientId(patientId);
      List<String> statusList = Arrays.asList("02", "05", "06");
      param.setStatusList(statusList);
      List<TdCaOperationApplyVo> operationApplyList = this.tdCaOperationApplyService.selectTdCaOperationApplyList(param);
      if (operationApplyList != null && !operationApplyList.isEmpty()) {
         operationList = new ArrayList(operationApplyList.size());

         for(int i = 0; i < operationApplyList.size(); ++i) {
            TdCaOperationApplyVo operationApply = (TdCaOperationApplyVo)operationApplyList.get(i);
            Operation operation = new Operation();
            operation.setPcIndex(i + "");
            operation.setPcOprCode(operationApply.getPlanOper1Cd());
            String planOper1Prefix = StringUtils.isNotBlank(operationApply.getPlanOper1Prefix()) ? operationApply.getPlanOper1Prefix() : "";
            String planOper1Suffix = StringUtils.isNotBlank(operationApply.getPlanOper1Suffix()) ? operationApply.getPlanOper1Suffix() : "";
            operation.setPcOprName(planOper1Prefix + operationApply.getPlanOper1Name() + planOper1Suffix);
            operation.setPcIncisionType(operationApply.getOperInciCd());
            String pcOprStartDateTime = operationApply.getOperDate() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, operationApply.getOperDate()) : null;
            operation.setPcOprStartDateTime(pcOprStartDateTime);
            operationList.add(operation);
         }
      }

      return operationList;
   }

   private List getExam(List examApplyFormList) throws Exception {
      List<Exam> examList = null;
      if (examApplyFormList != null && !examApplyFormList.isEmpty()) {
         examList = new ArrayList(examApplyFormList.size());

         for(TdPaApplyFormItemVo applyForm : examApplyFormList) {
            Exam exam = new Exam();
            exam.setRequestNo(applyForm.getApplyFormNo());
            exam.setLabExamCode(applyForm.getItemCode());
            exam.setLabExamName(applyForm.getItemName());
            exam.setStartDateTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, applyForm.getApplyTime()));
            exam.setDeptCode(applyForm.getPhysicianDptNo());
            exam.setDeptName(applyForm.getPhysicianDptName());
            exam.setDoctorCode(applyForm.getOrderDoctorCode());
            exam.setDoctorName(applyForm.getOrderDoctorName());
            examList.add(exam);
         }
      }

      return examList;
   }

   private List getLab(List labApplyFormList) throws Exception {
      List<Lab> labList = null;
      if (labApplyFormList != null && !labApplyFormList.isEmpty()) {
         labList = new ArrayList(labApplyFormList.size());

         for(TdPaApplyFormItemVo applyForm : labApplyFormList) {
            Lab lab = new Lab();
            lab.setRequestNo(applyForm.getApplyFormNo());
            lab.setLabExamCode(applyForm.getItemCode());
            lab.setLabExamName(applyForm.getItemName());
            lab.setStartDateTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, applyForm.getApplyTime()));
            lab.setDeptCode(applyForm.getPhysicianDptNo());
            lab.setDeptName(applyForm.getPhysicianDptName());
            lab.setDoctorCode(applyForm.getOrderDoctorCode());
            lab.setDoctorName(applyForm.getOrderDoctorName());
            labList.add(lab);
         }
      }

      return labList;
   }

   private List getDrugsNew(List orderSaveVoList) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<Drugs> drugsList = null;
      if (orderSaveVoList != null && !orderSaveVoList.isEmpty()) {
         drugsList = new ArrayList(orderSaveVoList.size());
         OrderFreq orderFreq = new OrderFreq();
         orderFreq.setEnabled("1");
         List<OrderFreq> orderFreqList = this.orderFreqMapper.selectOrderFreqList(orderFreq);

         for(int i = 0; i < orderSaveVoList.size(); ++i) {
            OrderSaveVo orderSaveVo = (OrderSaveVo)orderSaveVoList.get(i);
            String freqName = null;
            if (StringUtils.isNotBlank(orderSaveVo.getOrderFreq())) {
               orderFreqList = (List)orderFreqList.stream().filter((s) -> s.getFreqCd().equals(orderSaveVo.getOrderFreq())).collect(Collectors.toList());
               freqName = CollectionUtils.isNotEmpty(orderFreqList) ? ((OrderFreq)orderFreqList.get(0)).getFreqName() : freqName;
            }

            Drugs drugs = new Drugs();
            drugs.setIndex(i + "");
            drugs.setOrderNo(i);
            orderSaveVo.setOrderFreqName(freqName);
            drugs.setDrugUniqueCode(orderSaveVo.getCpNo());
            drugs.setDrugName(orderSaveVo.getCpName());
            drugs.setDosePerTime(orderSaveVo.getOrderActualUsage().toString());
            drugs.setDoseUnit(orderSaveVo.getUsageUnit());
            drugs.setFrequency(orderSaveVo.getOrderFreqName());
            drugs.setRouteCode(orderSaveVo.getOrderUsageWayName());
            drugs.setRouteName(orderSaveVo.getOrderUsageWayName());
            drugs.setStartTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, orderSaveVo.getOrderStartTime()));
            drugs.setGroupTag(orderSaveVo.getOrderGroupNo() + "");
            Integer isTempDrug = orderSaveVo.getOrderType().equals("1") ? 0 : 1;
            drugs.setIsTempDrug(isTempDrug);
            drugs.setOrderType(9);
            drugs.setDeptCode(user.getDept().getDeptCode());
            drugs.setDeptName(user.getDept().getDeptName());
            drugs.setDoctorcode(user.getBasEmployee().getEmplNumber());
            drugs.setDoctorCode(user.getBasEmployee().getEmplNumber());
            drugs.setDoctorName(user.getBasEmployee().getEmplName());
            drugs.setRecipNo((String)null);
            drugs.setNum(orderSaveVo.getOrderDose().toString());
            drugs.setNumUnit(orderSaveVo.getUnit());
            String purposeAntimicrobialUse = orderSaveVo.getPurposeAntimicrobialUse();
            Integer purpose = StringUtils.isNotBlank(purposeAntimicrobialUse) ? (purposeAntimicrobialUse.equals("1") ? 4 : 3) : 0;
            drugs.setPurpose(purpose);
            drugs.setDriprate(StringUtils.isNotBlank(orderSaveVo.getDrippingSpeed()) ? orderSaveVo.getDrippingSpeed() : null);
            drugs.setIsOtherRecip(0);
            drugs.setSkintest((String)null);
            drugs.setPharmacycode(orderSaveVo.getDetailPerformDepCode());
            drugs.setPharmacyname(orderSaveVo.getDetailPerformDepName());
            drugs.setRemark(orderSaveVo.getDoctorInstructions());
            drugs.setFirstdayfreq(orderSaveVo.getFirstPerformNum() + "");
            drugsList.add(drugs);
         }
      }

      return drugsList;
   }

   private List getRecipeNew(HerbSaveVo herbSaveVo) throws Exception {
      List<Recipe> recipeList = null;
      if (herbSaveVo != null && herbSaveVo.getOrderDetailList() != null && !herbSaveVo.getOrderDetailList().isEmpty()) {
         recipeList = new ArrayList(herbSaveVo.getOrderDetailList().size());

         for(TdPaOrderDetail tdPaOrderDetail : herbSaveVo.getOrderDetailList()) {
            Recipe recipe = new Recipe();
            recipeList.add(recipe);
         }
      }

      return recipeList;
   }

   private List getDrugsNew(HerbSaveVo herbSaveVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<Drugs> drugsList = null;
      String orderNo = DateUtils.getDateStr("");
      if (herbSaveVo != null && herbSaveVo.getOrderDetailList() != null && !herbSaveVo.getOrderDetailList().isEmpty()) {
         drugsList = new ArrayList(herbSaveVo.getOrderDetailList().size());

         for(int i = 0; i < herbSaveVo.getOrderDetailList().size(); ++i) {
            TdPaOrderDetail tdPaOrderDetail = (TdPaOrderDetail)herbSaveVo.getOrderDetailList().get(i);
            Drugs drugs = new Drugs();
            drugs.setIndex(i + "");
            drugs.setOrderNo(i);
            drugs.setDrugUniqueCode(tdPaOrderDetail.getChargeNo());
            drugs.setDrugName(tdPaOrderDetail.getChargeName());
            drugs.setDosePerTime(tdPaOrderDetail.getOrderActualUsage().toString());
            drugs.setDoseUnit(tdPaOrderDetail.getUsageUnit());
            drugs.setFrequency(herbSaveVo.getItemOrderFreqName());
            drugs.setRouteCode(StringUtils.isNotBlank(tdPaOrderDetail.getOrderUsageWay()) ? tdPaOrderDetail.getOrderUsageWay() : herbSaveVo.getItemOrderUsageWayName());
            drugs.setRouteName(herbSaveVo.getItemOrderUsageWayName());
            drugs.setStartTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, herbSaveVo.getOrderStartTime()));
            drugs.setGroupTag(orderNo);
            Integer isTempDrug = herbSaveVo.getOrderType().equals("1") ? 0 : 1;
            drugs.setIsTempDrug(isTempDrug);
            drugs.setOrderType(9);
            drugs.setDeptCode(user.getDept().getDeptCode());
            drugs.setDeptName(user.getDept().getDeptName());
            drugs.setDoctorcode(user.getBasEmployee().getEmplNumber());
            drugs.setDoctorCode(user.getBasEmployee().getEmplNumber());
            drugs.setDoctorName(user.getBasEmployee().getEmplName());
            drugs.setRecipNo((String)null);
            drugs.setNum(tdPaOrderDetail.getOrderDose().toString());
            drugs.setNumUnit(tdPaOrderDetail.getUnit());
            drugs.setDriprate(StringUtils.isNotBlank(tdPaOrderDetail.getDrippingSpeed()) ? tdPaOrderDetail.getDrippingSpeed() : null);
            drugs.setIsOtherRecip(0);
            drugs.setSkintest((String)null);
            drugs.setPharmacycode(herbSaveVo.getPerformDepCode());
            drugs.setPharmacyname(herbSaveVo.getPerformDepName());
            drugs.setRemark(herbSaveVo.getOederDesc());
            drugs.setDecoction(tdPaOrderDetail.getDoctorInstructions());
            drugs.setFirstdayfreq(tdPaOrderDetail.getFirstPerformNum() + "");
            drugsList.add(drugs);
         }
      }

      return drugsList;
   }

   private List getDrugsLongUsing(String patientId) throws Exception {
      OrderSearchVo queryParam = new OrderSearchVo();
      queryParam.setPatientId(patientId);
      queryParam.setCommitFlag("1");
      queryParam.setOrderClassCode("01");
      queryParam.setOrderStatus("3");
      List<OrderSearchVo> mainList = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
      List<OrderSearchVo> allList = this.tdPaOrderService.selectSubOrderSearchVoList(mainList);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<Drugs> drugsList = null;
      if (allList != null && !allList.isEmpty()) {
         drugsList = new ArrayList(allList.size());

         for(int i = 0; i < allList.size(); ++i) {
            OrderSearchVo orderSearchVo = (OrderSearchVo)allList.get(i);
            Drugs drugs = this.getDrugs(orderSearchVo, 0);
            drugs.setIndex(orderSearchVo.getOrderNo() + orderSearchVo.getOrderGroupNo() + orderSearchVo.getOrderGroupSortNumber());
            drugs.setOrderNo(i);
            drugsList.add(drugs);
         }
      }

      return drugsList;
   }

   private List getDrugsCheckPending(String patientId) throws Exception {
      OrderSearchVo queryParam = new OrderSearchVo();
      queryParam.setPatientId(patientId);
      queryParam.setOrderClassCode("01");
      queryParam.setOrderStatusList(Arrays.asList("-1", "0"));
      List<OrderSearchVo> mainList = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
      List<OrderSearchVo> allList = this.tdPaOrderService.selectSubOrderSearchVoList(mainList);
      List<Drugs> drugsList = null;
      if (allList != null && !allList.isEmpty()) {
         drugsList = new ArrayList(allList.size());

         for(int i = 0; i < allList.size(); ++i) {
            OrderSearchVo orderSearchVo = (OrderSearchVo)allList.get(i);
            Drugs drugs = this.getDrugs(orderSearchVo, 0);
            drugs.setIndex(orderSearchVo.getOrderNo() + orderSearchVo.getOrderGroupNo() + orderSearchVo.getOrderGroupSortNumber());
            drugs.setOrderNo(i);
            drugsList.add(drugs);
         }
      }

      return drugsList;
   }

   private List getDrugsLongStopOneDay(String patientId) throws Exception {
      OrderSearchVo queryParam = new OrderSearchVo();
      queryParam.setPatientId(patientId);
      queryParam.setCommitFlag("1");
      queryParam.setOrderClassCode("01");
      queryParam.setOrderStatus("4");
      List<OrderSearchVo> allList = this.tdPaOrderService.selectOrderSearchVoListStopOneDay(queryParam);
      this.log.info("########查询一天内出院医嘱信息02###########" + allList);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<Drugs> drugsList = null;
      if (allList != null && !allList.isEmpty()) {
         drugsList = new ArrayList(allList.size());

         for(int i = 0; i < allList.size(); ++i) {
            OrderSearchVo orderSearchVo = (OrderSearchVo)allList.get(i);
            Drugs drugs = this.getDrugs(orderSearchVo, 0);
            drugs.setIndex(orderSearchVo.getOrderNo() + orderSearchVo.getOrderGroupNo() + orderSearchVo.getOrderGroupSortNumber());
            drugs.setOrderNo(i);
            drugsList.add(drugs);
         }
      }

      this.log.info("########查询一天内出院医嘱信息03###########" + drugsList);
      return drugsList;
   }

   private Drugs getDrugs(OrderSearchVo orderSearchVo, Integer orderType) {
      Drugs drugs = new Drugs();
      drugs.setDrugUniqueCode(orderSearchVo.getCpNo());
      drugs.setDrugName(orderSearchVo.getCpName());
      drugs.setDosePerTime(orderSearchVo.getOrderActualUsage() != null ? orderSearchVo.getOrderActualUsage().toString() : null);
      drugs.setDoseUnit(orderSearchVo.getUsageUnit());
      drugs.setFrequency(orderSearchVo.getOrderFreqName());
      drugs.setRouteCode(StringUtils.isNotBlank(orderSearchVo.getOrderUsageWay()) ? orderSearchVo.getOrderUsageWay() : orderSearchVo.getOrderUsageWayName());
      drugs.setRouteName(orderSearchVo.getOrderUsageWayName());
      drugs.setStartTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, orderSearchVo.getOrderStartTime()));
      drugs.setGroupTag(orderSearchVo.getOrderGroupNo() + "");
      Integer isTempDrug = orderSearchVo.getOrderType().equals("1") ? 0 : 1;
      drugs.setIsTempDrug(isTempDrug);
      drugs.setOrderType(orderType);
      drugs.setDeptCode(orderSearchVo.getOrderDoctorDepCode());
      drugs.setDeptName(orderSearchVo.getOrderDoctorDepName());
      drugs.setDoctorcode(orderSearchVo.getOrderDoctorNo());
      drugs.setDoctorCode(orderSearchVo.getOrderDoctorNo());
      drugs.setDoctorName(orderSearchVo.getOrderDoctorName());
      drugs.setRecipNo((String)null);
      drugs.setNum(orderSearchVo.getOrderDose().toString());
      drugs.setNumUnit(orderSearchVo.getUnit());
      String purposeAntimicrobialUse = orderSearchVo.getPurposeAntimicrobialUse();
      Integer purpose = StringUtils.isNotBlank(purposeAntimicrobialUse) ? (purposeAntimicrobialUse.equals("1") ? 4 : 3) : 0;
      drugs.setPurpose(purpose);
      drugs.setDriprate(StringUtils.isNotBlank(orderSearchVo.getDrippingSpeed()) ? orderSearchVo.getDrippingSpeed() : null);
      drugs.setIsOtherRecip(0);
      drugs.setSkintest((String)null);
      drugs.setPharmacycode(orderSearchVo.getDetailPerformDepCode());
      drugs.setPharmacyname(orderSearchVo.getDetailPerformDepName());
      drugs.setRemark(orderSearchVo.getDoctorInstructions());
      drugs.setFirstdayfreq(orderSearchVo.getFirstPerformNum() + "");
      return drugs;
   }

   private List getDrugsTempUsing(String patientId) throws Exception {
      OrderSearchVo queryParam = new OrderSearchVo();
      queryParam.setPatientId(patientId);
      queryParam.setCommitFlag("1");
      queryParam.setOrderClassCode("01");
      queryParam.setOrderStatus("3");
      queryParam.setOrderType("2");
      Date curDate = this.commonService.getDbSysdate();
      String curDateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, curDate);
      Date curDay = DateUtils.parseDate(curDateStr, new String[]{DateUtils.YYYY_MM_DD});
      Date nextDay = DateUtils.addDays(curDate, 1);
      queryParam.setOrderStartTimeBegin(curDay);
      queryParam.setOrderStartTimeEnd(nextDay);
      List<OrderSearchVo> mainList = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
      List<OrderSearchVo> allList = this.tdPaOrderService.selectSubOrderSearchVoList(mainList);
      List<Drugs> drugsList = null;
      if (allList != null && !allList.isEmpty()) {
         drugsList = new ArrayList(allList.size());

         for(int i = 0; i < allList.size(); ++i) {
            OrderSearchVo orderSearchVo = (OrderSearchVo)allList.get(i);
            Drugs drugs = this.getDrugs(orderSearchVo, 0);
            drugs.setIndex(orderSearchVo.getOrderNo() + orderSearchVo.getOrderGroupNo() + orderSearchVo.getOrderGroupSortNumber());
            drugs.setOrderNo(i);
            drugsList.add(drugs);
         }
      }

      return drugsList;
   }
}
