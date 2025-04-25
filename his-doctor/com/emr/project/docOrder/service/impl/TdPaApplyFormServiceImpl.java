package com.emr.project.docOrder.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.domain.vo.ReplaceUrlParamVo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaApplyForm;
import com.emr.project.docOrder.domain.TdPaApplyFormDetail;
import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormItemVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.mapper.TdPaApplyFormMapper;
import com.emr.project.docOrder.service.ITdPaApplyFormDetailService;
import com.emr.project.docOrder.service.ITdPaApplyFormItemService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.docOrder.util.OrderUtil;
import com.emr.project.emr.domain.EmrElemstoe;
import com.emr.project.emr.service.IEmrElemstoeService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.ExamItem;
import com.emr.project.pat.domain.TestReport;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.resp.ApplyFormPrintResp;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IExamItemService;
import com.emr.project.pat.service.ITestReportService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.SysDeptMapper;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.domain.DocumentType;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.mapper.ClinItemMainMapper;
import com.emr.project.tmpm.service.IClinItemDetailService;
import com.emr.project.tmpm.service.IDocumentTypeService;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TdPaApplyFormServiceImpl implements ITdPaApplyFormService {
   @Autowired
   private TdPaApplyFormMapper tdPaApplyFormMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IClinItemDetailService clinItemDetailService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private ITdPaApplyFormItemService tdPaApplyFormItemService;
   @Autowired
   private ITdPaApplyFormDetailService tdPaApplyFormDetailService;
   @Autowired
   private IEmrElemstoeService emrElemstoeService;
   @Autowired
   private IDocumentTypeService documentTypeService;
   @Autowired
   private ITestReportService testReportService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ITdPaOrderOperationLogService tdPaOrderOperationLogService;
   @Autowired
   private ITdPaOrderSignMainService tdPaOrderSignMainService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private SysDeptMapper sysDeptMapper;
   @Autowired
   private IExamItemService examItemService;
   @Autowired
   private ClinItemMainMapper clinItemMainMapper;
   @Autowired
   private ISysDictDataService dictDataService;
   @Autowired
   private IBabyInfoService babyInfoService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   public TdPaApplyFormVo selectTdPaApplyFormById(String applyFormNo) throws Exception {
      TdPaApplyFormVo tdPaApplyFormVo = this.tdPaApplyFormMapper.selectTdPaApplyFormById(applyFormNo);
      return tdPaApplyFormVo;
   }

   public List selectTdPaApplyFormList(TdPaApplyForm tdPaApplyForm) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TdPaApplyFormVo> list = this.tdPaApplyFormMapper.selectTdPaApplyFormList(tdPaApplyForm);
      List<TestReport> reportVoList = this.testReportService.selectTestReportByPatientId(tdPaApplyForm.getPatientId());
      Map<String, List<TestReport>> reportMap = new HashMap(1);
      if (reportVoList != null && !reportVoList.isEmpty()) {
         reportMap = (Map)reportVoList.stream().collect(Collectors.groupingBy((s) -> s.getAppCd()));
      }

      if (list != null && !list.isEmpty()) {
         List<SysDictData> dictDataList = this.dictDataService.selectDictDataByType("s103");
         Map<String, List<SysDictData>> dictDataMap = (Map<String, List<SysDictData>>)(CollectionUtils.isNotEmpty(dictDataList) ? (Map)dictDataList.stream().collect(Collectors.groupingBy((t) -> t.getDictValue())) : new HashMap(1));
         VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(tdPaApplyForm.getPatientId());

         for(TdPaApplyFormVo tdPaApplyFormVo : list) {
            tdPaApplyFormVo.setBedNo(personalVo.getBedNo());
            if (StringUtils.isNotBlank(tdPaApplyFormVo.getBabyNo())) {
               tdPaApplyFormVo.setPatientName(tdPaApplyFormVo.getBabyName());
               tdPaApplyFormVo.setSex(tdPaApplyFormVo.getBabySex());
               tdPaApplyFormVo.setAge("新生儿");
            } else {
               tdPaApplyFormVo.setSex(personalVo.getSex());
               tdPaApplyFormVo.setAge(AgeUtil.getAgeStr(personalVo.getAgeY(), personalVo.getAgeM(), personalVo.getAgeD(), personalVo.getAgeH(), personalVo.getAgeMi()));
            }

            tdPaApplyFormVo.setResDocName(personalVo.getResDocName());
            tdPaApplyFormVo.setPatType(personalVo.getPatTypeName());
            tdPaApplyFormVo.setProTypeName(personalVo.getProTypeName());
            tdPaApplyFormVo.setNowAddrTel(personalVo.getNowAddrTel());
            tdPaApplyFormVo.setWeight(personalVo.getWeight());
            List<TestReport> reports = (List)reportMap.get(tdPaApplyFormVo.getApplyFormNo());
            if (reports != null && !reports.isEmpty()) {
               tdPaApplyFormVo.setConDocName(((TestReport)reports.get(0)).getConDocName());
               if (((TestReport)reports.get(0)).getRecDate() == null) {
                  tdPaApplyFormVo.setRecFlag(CommonConstants.BOOL_FALSE);
               } else {
                  tdPaApplyFormVo.setRecFlag(CommonConstants.BOOL_TRUE);
               }
            } else {
               tdPaApplyFormVo.setRecFlag(CommonConstants.BOOL_TRUE);
            }

            if (tdPaApplyFormVo.getApplyFormStatus().equals("9")) {
               ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(personalVo.getInpNo(), personalVo.getInpNo(), personalVo.getRecordNo(), personalVo.getVisitId().toString(), user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), tdPaApplyFormVo.getApplyFormNo(), personalVo.getIdCard(), (String)null, (String)null);
               tdPaApplyFormVo.setReportUrl(this.commonService.replaceUrlParam(replaceUrlParamVo, tdPaApplyFormVo.getReportUrl()));
            }

            if (StringUtils.isNotBlank(tdPaApplyFormVo.getPhysiologicalCycle())) {
               List<SysDictData> physiologicalCycleList = (List)dictDataMap.get(tdPaApplyFormVo.getPhysiologicalCycle());
               String physiologicalCycle = CollectionUtils.isNotEmpty(physiologicalCycleList) ? ((SysDictData)physiologicalCycleList.get(0)).getDictLabel() : null;
               tdPaApplyFormVo.setPhysiologicalCycle(physiologicalCycle);
            }
         }
      }

      return list;
   }

   public void insertTdPaApplyForm(TdPaApplyForm tdPaApplyForm) throws Exception {
      this.tdPaApplyFormMapper.insertTdPaApplyForm(tdPaApplyForm);
   }

   public Map getCheckApplyFormList(List itemList) throws Exception {
      Map<String, List<DrugAndClin>> map = (Map)itemList.stream().collect(Collectors.groupingBy((t) -> t.getOrderClassCode() + t.getDocumentTypeNo() + t.getPerformDepCode() + t.getExamMachineCd()));
      Map<String, List<DrugAndClin>> resultMap = new HashMap();

      for(String key : map.keySet()) {
         String applyFormNo = DateUtils.getDateStr("");
         resultMap.put(applyFormNo, map.get(key));
      }

      return resultMap;
   }

   public Map getCheckOutApplyFormList(List itemList) throws Exception {
      String syncStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0069");
      new HashMap();
      Map map;
      if (StringUtils.isNotEmpty(syncStr) && syncStr.equals("0")) {
         map = (Map)itemList.stream().collect(Collectors.groupingBy((t) -> t.getOrderClassCode() + t.getDocumentTypeNo() + t.getPerformDepCode() + t.getExamMachineCd() + t.getSpecCd()));
      } else {
         map = (Map)itemList.stream().collect(Collectors.groupingBy((t) -> t.getOrderClassCode() + t.getDocumentTypeNo() + t.getPerformDepCode() + t.getExamMachineCd() + t.getSpecCd() + t.getBarcodeClassCd()));
      }

      Map<String, List<DrugAndClin>> resultMap = new HashMap();

      for(String key : map.keySet()) {
         String applyFormNo = DateUtils.getDateStr("");
         resultMap.put(applyFormNo, map.get(key));
      }

      return resultMap;
   }

   public int updateTdPaApplyForm(TdPaApplyForm tdPaApplyForm) {
      return this.tdPaApplyFormMapper.updateTdPaApplyForm(tdPaApplyForm);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void submitTdPaApplyForm(OrderCommitVo orderCommitVo, List orderItemList, Visitinfo visitinfo) throws Exception {
      List<String> applyFormNoList = orderCommitVo.getApplyFormNoList();
      this.tdPaApplyFormMapper.updateStatusByApplyFormNos(applyFormNoList, "1");
      Date currDate = orderCommitVo.getSubmitTime() != null ? orderCommitVo.getSubmitTime() : this.commonService.getDbSysdate();
      List<String> orderNoList = (List)orderItemList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      TdPaOrderItemVo param = new TdPaOrderItemVo();
      param.setOrderNoList(orderNoList);
      param.setOrderStatus("0");
      param.setSubmitDoctorCode(basEmployee.getEmplNumber());
      param.setSubmitDoctorNo(basEmployee.getEmplNumber());
      param.setSubmitDoctorName(basEmployee.getEmplName());
      param.setSubmitTime(currDate);
      this.tdPaOrderItemService.updateOrderStatus(param);
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, 0, "提交", currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemList, 0, "提交", currDate, (String)null);
      this.tdPaOrderSignMainService.addTdPaOrderOperationSign(orderItemList, orderCommitVo, visitinfo, 0, "提交");
   }

   public int deleteTdPaApplyFormByIds(String[] applyFormNos) {
      return this.tdPaApplyFormMapper.deleteTdPaApplyFormByIds(applyFormNos);
   }

   public int deleteTdPaApplyFormById(String applyFormNo) {
      return this.tdPaApplyFormMapper.deleteTdPaApplyFormById(applyFormNo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List saveApplyFormList(TdPaApplyFormVo tdPaApplyForm, OrderCommitVo orderCommitVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<TdPaApplyFormVo> insertList = new ArrayList();
      List<TdPaApplyFormVo> insertOrderList = new ArrayList();
      List<TdPaApplyFormItem> insertItemList = new ArrayList();
      List<TdPaApplyFormItem> insertOrderItemList = new ArrayList();
      List<TdPaApplyFormDetail> inserDetailtList = new ArrayList();
      List<TdPaApplyFormDetail> inserOrderDetailtList = new ArrayList();
      List<String> itemCdList = (List)tdPaApplyForm.getItemList().stream().map((s) -> s.getCpNo()).collect(Collectors.toList());
      List<ClinItemDetail> itemDetailList = this.clinItemDetailService.selectClinItemDetailByItemCds(itemCdList);
      Map<String, List<ClinItemDetail>> detailMapList = (Map)itemDetailList.stream().collect(Collectors.groupingBy((s) -> s.getItemCd()));
      new HashMap();
      Map allMapList;
      if (tdPaApplyForm.getApplyFormClassCode().equals("02")) {
         allMapList = this.getCheckApplyFormList(tdPaApplyForm.getItemList());
      } else {
         List<DrugAndClin> itemList = tdPaApplyForm.getItemList();
         List<DrugAndClin> clins = (List)itemList.stream().filter((t) -> StringUtils.isEmpty(t.getBarcodeClassCd())).collect(Collectors.toList());
         if (!clins.isEmpty()) {
            List<String> cpNoList = (List)clins.stream().map(DrugAndClin::getCpNo).collect(Collectors.toList());
            List<ClinItemMain> itemMainList = this.clinItemMainMapper.selectClinItemMainByItemCdList(cpNoList);
            if (!itemMainList.isEmpty()) {
               Map<String, List<ClinItemMain>> itemMap = (Map)itemMainList.stream().collect(Collectors.groupingBy(ClinItemMain::getItemCd));

               for(DrugAndClin clin : itemList) {
                  String cpNo = clin.getCpNo();
                  if (itemMap.containsKey(cpNo)) {
                     List<ClinItemMain> itemMains = (List)itemMap.get(cpNo);
                     if (!itemMains.isEmpty()) {
                        ClinItemMain main = (ClinItemMain)itemMains.get(0);
                        clin.setBarcodeClassCd(main.getBarcodeClassCd());
                        clin.setBarcodeClassName(main.getBarcodeClassName());
                     }
                  }
               }
            }
         }

         allMapList = this.getCheckOutApplyFormList(itemList);
      }

      Set<String> deptCodeSet = new HashSet();

      for(String key : allMapList.keySet()) {
         deptCodeSet.add(((DrugAndClin)((List)allMapList.get(key)).get(0)).getPerformDepCode());
      }

      List<SysDept> deptList = this.sysDeptMapper.selectSysDeptSiteByCodeList(deptCodeSet);
      Map<String, List<SysDept>> deptMap = new HashMap();
      if (deptList != null && deptList.size() > 0) {
         deptMap = (Map)deptList.stream().collect(Collectors.groupingBy(SysDept::getDeptCode));
      }

      VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(tdPaApplyForm.getPatientId());
      List<DocumentType> documentTypes = this.documentTypeService.selectDocumentTypeList(new DocumentType());
      Map<Long, List<ClinItemDetail>> applyFormDetailMap = new HashMap();
      Map<TdPaApplyFormItem, List<ClinItemDetail>> detailMap = new HashMap();

      for(String key : allMapList.keySet()) {
         List<TdPaApplyFormItem> formItemList = new ArrayList();
         List<DrugAndClin> list = (List)allMapList.get(key);
         List<DocumentType> documentTypeList = (List)documentTypes.stream().filter((s) -> s.getDocumentTypeCd().equals(((DrugAndClin)list.get(0)).getDocumentTypeNo())).collect(Collectors.toList());
         TdPaApplyFormVo tdPaApplyFormVo = new TdPaApplyFormVo();
         BeanUtils.copyProperties(tdPaApplyForm, tdPaApplyFormVo);
         tdPaApplyFormVo.setHospitalCode(sysUser.getHospital().getOrgCode());
         tdPaApplyFormVo.setApplyFormNo(key);
         tdPaApplyFormVo.setApplyFormClassCode(((DrugAndClin)list.get(0)).getOrderClassCode());
         tdPaApplyFormVo.setApplyFormTypeCode(((DrugAndClin)list.get(0)).getDocumentTypeNo());
         tdPaApplyFormVo.setOrderSortNumber("01");
         tdPaApplyFormVo.setCaseNo(personalVo.getRecordNo());
         tdPaApplyFormVo.setAdmissionNo(personalVo.getInpNo());
         tdPaApplyFormVo.setHospitalizedCount(personalVo.getVisitId());
         tdPaApplyFormVo.setPatientName(personalVo.getPatientName());
         tdPaApplyFormVo.setPatientDepCode(personalVo.getDeptCd());
         tdPaApplyFormVo.setPatientDepName(personalVo.getDeptName());
         String performDepCode = ((DrugAndClin)list.get(0)).getPerformDepCode();
         tdPaApplyFormVo.setPerformDepCode(performDepCode);
         tdPaApplyFormVo.setPerformDepName(((DrugAndClin)list.get(0)).getPerformDepName());
         if (deptMap.containsKey(performDepCode)) {
            List<SysDept> sysDepts = (List)deptMap.get(performDepCode);
            if (sysDepts != null && sysDepts.size() > 0) {
               SysDept sysDept = (SysDept)sysDepts.get(0);
               tdPaApplyFormVo.setPerformDepSite(sysDept.getSite());
            }
         }

         tdPaApplyFormVo.setPrintFlag(((DocumentType)documentTypeList.get(0)).getPrintFlag());
         tdPaApplyFormVo.setPrintFormat(((DocumentType)documentTypeList.get(0)).getPrintFormat());
         tdPaApplyFormVo.setInputFormat(((DocumentType)documentTypeList.get(0)).getInputFormat());
         tdPaApplyFormVo.setApplyFormName(((DocumentType)documentTypeList.get(0)).getDocumentTypeName());
         tdPaApplyFormVo.setNotice(((DrugAndClin)list.get(0)).getNotice());
         tdPaApplyFormVo.setAge(AgeUtil.getAgeStr(personalVo.getAgeY(), personalVo.getAgeM(), personalVo.getAgeD(), personalVo.getAgeH(), personalVo.getAgeMi()));
         tdPaApplyFormVo.setSex(personalVo.getSex());
         tdPaApplyFormVo.setBedNo(personalVo.getBedNo());
         tdPaApplyFormVo.setPatType(personalVo.getPatTypeName());
         tdPaApplyFormVo.setResDocName(personalVo.getResDocName());
         tdPaApplyFormVo.setProTypeName(personalVo.getProTypeName());
         tdPaApplyFormVo.setNowAddrTel(personalVo.getNowAddrTel());
         tdPaApplyFormVo.setWeight(personalVo.getWeight());
         String purposeExamination = this.getPurposeExamination(list);
         tdPaApplyFormVo.setPurposeExamination(purposeExamination);
         tdPaApplyFormVo.setExamCategory(((DrugAndClin)list.get(0)).getExamMachineName());
         tdPaApplyFormVo.setSampleNo(((DrugAndClin)list.get(0)).getSpecCd());
         tdPaApplyFormVo.setSampleName(((DrugAndClin)list.get(0)).getSpecName());
         if (StringUtils.isBlank(tdPaApplyFormVo.getExamPart())) {
            String examPart = this.getExamPartStr(list);
            tdPaApplyFormVo.setExamPart(examPart);
         }

         if (StringUtils.isNotBlank(tdPaApplyFormVo.getBabyNo()) && tdPaApplyFormVo.getBabyNo().equals(tdPaApplyFormVo.getPatientId())) {
            tdPaApplyFormVo.setBabyNo((String)null);
         }

         int sort = 0;

         for(DrugAndClin drugAndClin : list) {
            ++sort;
            TdPaApplyFormItem tdPaApplyFormItem = new TdPaApplyFormItem();
            tdPaApplyFormItem.setId(SnowIdUtils.uniqueLong());
            tdPaApplyFormItem.setApplyFormNo(key);
            tdPaApplyFormItem.setItemCode(drugAndClin.getCpNo());
            tdPaApplyFormItem.setItemName(drugAndClin.getCpName());
            tdPaApplyFormItem.setPrice(drugAndClin.getPrice());
            tdPaApplyFormItem.setDose(drugAndClin.getNum());
            tdPaApplyFormItem.setUnit(drugAndClin.getUnit());
            tdPaApplyFormItem.setExamPartCd(drugAndClin.getExamPartCd());
            tdPaApplyFormItem.setExamPartName(drugAndClin.getExamPartName());
            tdPaApplyFormItem.setSpecCd(drugAndClin.getSpecCd());
            tdPaApplyFormItem.setSpecName(drugAndClin.getSpecName());
            tdPaApplyFormItem.setStandard(drugAndClin.getStandard());
            tdPaApplyFormItem.setItemSortNumber(OrderUtil.getNumStr(sort));
            insertOrderItemList.add(tdPaApplyFormItem);
            insertItemList.add(tdPaApplyFormItem);
            formItemList.add(tdPaApplyFormItem);
            List<ClinItemDetail> detailList = (List)detailMapList.get(drugAndClin.getCpNo());
            detailMap.put(tdPaApplyFormItem, detailList);
            applyFormDetailMap.put(tdPaApplyFormItem.getId(), detailList);
         }

         tdPaApplyFormVo.setApplyItemList(formItemList);
         insertOrderList.add(tdPaApplyFormVo);
         insertList.add(tdPaApplyFormVo);
      }

      for(TdPaApplyFormItem tdPaApplyFormItem : detailMap.keySet()) {
         List<ClinItemDetail> detailList = (List)detailMap.get(tdPaApplyFormItem);
         List<DrugAndClin> list = (List)allMapList.get(tdPaApplyFormItem.getApplyFormNo());
         List<DocumentType> documentTypeList = (List)documentTypes.stream().filter((s) -> s.getDocumentTypeCd().equals(((DrugAndClin)list.get(0)).getDocumentTypeNo())).collect(Collectors.toList());
         if (detailList != null && !detailList.isEmpty()) {
            for(ClinItemDetail clinItemDetail : detailList) {
               TdPaApplyFormDetail tdPaApplyFormDetail = new TdPaApplyFormDetail();
               tdPaApplyFormDetail.setId(SnowIdUtils.uniqueLong());
               tdPaApplyFormDetail.setApplyFormNo(tdPaApplyFormItem.getApplyFormNo());
               tdPaApplyFormDetail.setOrderGroupSortNumber(clinItemDetail.getSortSn());
               tdPaApplyFormDetail.setChargeNo(clinItemDetail.getChargeCode());
               tdPaApplyFormDetail.setChargeName(clinItemDetail.getChargeName());
               tdPaApplyFormDetail.setStandardCode(clinItemDetail.getStandardCode());
               tdPaApplyFormDetail.setStandardName(clinItemDetail.getStandardName());
               tdPaApplyFormDetail.setStandard(tdPaApplyFormItem.getStandard());
               tdPaApplyFormDetail.setUnit(clinItemDetail.getUnit());
               tdPaApplyFormDetail.setPrice(clinItemDetail.getPrice());
               tdPaApplyFormDetail.setDose(clinItemDetail.getAmount());
               tdPaApplyFormDetail.setItemSortNumber(tdPaApplyFormItem.getItemSortNumber());
               tdPaApplyFormDetail.setSubFlag(clinItemDetail.getSubFlag());
               tdPaApplyFormDetail.setRemark(clinItemDetail.getRemark());
               inserOrderDetailtList.add(tdPaApplyFormDetail);
               inserDetailtList.add(tdPaApplyFormDetail);
            }
         }
      }

      if (insertOrderList != null && !insertOrderList.isEmpty() && insertOrderItemList != null && !insertOrderItemList.isEmpty() && inserOrderDetailtList != null && !inserOrderDetailtList.isEmpty()) {
         this.tdPaOrderItemService.insertOrderAndCheckList(personalVo, insertOrderList, insertOrderItemList, inserOrderDetailtList, tdPaApplyForm.getItemList(), applyFormDetailMap, orderCommitVo);
      }

      if (insertList != null && !insertList.isEmpty()) {
         this.tdPaApplyFormMapper.insertTdPaApplyFormList(insertList);
      }

      if (insertItemList != null && !insertItemList.isEmpty()) {
         this.tdPaApplyFormItemService.insertTdPaApplyFormItemList(insertItemList);
      }

      if (inserDetailtList != null && !inserDetailtList.isEmpty()) {
         this.tdPaApplyFormDetailService.insertTdPaApplyFormDetailList(inserDetailtList);
      }

      return insertList;
   }

   public String getPurposeExamination(List list) {
      String str = "";

      for(DrugAndClin drugAndClin : list) {
         str = str + "√" + drugAndClin.getCpName();
      }

      return str;
   }

   public String getExamPartStr(List list) {
      String str = "";

      for(DrugAndClin drugAndClin : list) {
         str = str + drugAndClin.getExamPartName() + ",";
      }

      str = StringUtils.isEmpty(str) ? str : str.substring(0, str.length() - 1);
      return str;
   }

   public TdPaApplyFormVo selectSelectPatDia(String patientId) throws Exception {
      TdPaApplyFormVo tdPaApplyFormVo = new TdPaApplyFormVo();
      List<TdPaApplyForm> applyFormList = this.tdPaApplyFormMapper.selectApplyFormListByPatientId(patientId);
      if (applyFormList != null && applyFormList.size() > 0) {
         TdPaApplyForm tdPaApplyForm = (TdPaApplyForm)applyFormList.get(0);
         tdPaApplyFormVo.setComplaint(tdPaApplyForm.getComplaint());
         tdPaApplyFormVo.setMedicalRecordDigest(tdPaApplyForm.getMedicalRecordDigest());
         tdPaApplyFormVo.setDiagnosisCode(tdPaApplyForm.getDiagnosisCode());
         tdPaApplyFormVo.setDiagnosisName(tdPaApplyForm.getDiagnosisName());
      } else {
         List<DiagInfo> diagInfos = this.diagInfoService.selectInHosDiagInFoByPatientId(patientId);
         if (diagInfos != null && diagInfos.size() > 0) {
            DiagInfo diagInfo = (DiagInfo)diagInfos.get(0);
            tdPaApplyFormVo.setDiagnosisCode(diagInfo.getDiagCd());
            tdPaApplyFormVo.setDiagnosisName(diagInfo.getDiagName());
         }

         String mainStr = "";
         String medicalRecordDigest = "";
         List<String> typeList = new ArrayList(2);
         typeList.add("1");
         typeList.add("12");
         List<EmrElemstoe> list = this.emrElemstoeService.selectEmrElemstoeListByPatientIdAndTypeList(patientId, typeList);
         if (list != null && !list.isEmpty()) {
            Map<String, List<EmrElemstoe>> listMap = (Map)list.stream().collect(Collectors.groupingBy(EmrElemstoe::getMrTypeCd));
            List<EmrElemstoe> firstList = (List)listMap.get("12");
            if (firstList != null && firstList.size() > 0) {
               List<EmrElemstoe> mainList = (List)firstList.stream().filter((s) -> s.getElemId().equals(CommonConstants.ELEM.MAIN_SUIT)).collect(Collectors.toList());
               List<EmrElemstoe> signList = (List)firstList.stream().filter((s) -> s.getElemId().equals(CommonConstants.ELEM.PHYSIGN_ALL)).collect(Collectors.toList());
               List<EmrElemstoe> hisList = (List)firstList.stream().filter((s) -> s.getElemId().equals(CommonConstants.ELEM.DIS_HIS)).collect(Collectors.toList());
               mainStr = mainStr + (mainList.isEmpty() ? "" : (StringUtils.isEmpty(((EmrElemstoe)mainList.get(0)).getElemCon()) ? "" : ((EmrElemstoe)mainList.get(0)).getElemCon()));
               if (CollectionUtils.isNotEmpty(hisList)) {
                  hisList.sort((m1, m2) -> m2.getCreDate().compareTo(m1.getCreDate()));
                  String elemCon = ((EmrElemstoe)hisList.get(0)).getElemCon();
                  if (StringUtils.isNotEmpty(elemCon)) {
                     medicalRecordDigest = medicalRecordDigest + elemCon;
                  }
               }

               if (CollectionUtils.isNotEmpty(signList)) {
                  signList.sort((m1, m2) -> m2.getCreDate().compareTo(m1.getCreDate()));
                  String elemCon = ((EmrElemstoe)signList.get(0)).getElemCon();
                  if (StringUtils.isNotEmpty(elemCon)) {
                     medicalRecordDigest = medicalRecordDigest + elemCon;
                  }
               }
            }

            List<EmrElemstoe> inHosList = (List)listMap.get("1");
            if (inHosList != null && inHosList.size() > 0) {
               if (mainStr.isEmpty()) {
                  List<EmrElemstoe> mainList = (List)inHosList.stream().filter((s) -> s.getElemId().equals(CommonConstants.ELEM.MAIN_SUIT)).collect(Collectors.toList());
                  mainStr = mainStr + (mainList.isEmpty() ? "" : (StringUtils.isEmpty(((EmrElemstoe)mainList.get(0)).getElemCon()) ? "" : ((EmrElemstoe)mainList.get(0)).getElemCon()));
               }

               if (medicalRecordDigest.isEmpty()) {
                  List<EmrElemstoe> signList = (List)inHosList.stream().filter((s) -> s.getElemId().equals(CommonConstants.ELEM.PHYSIGN_ALL)).collect(Collectors.toList());
                  List<EmrElemstoe> hisList = (List)inHosList.stream().filter((s) -> s.getElemId().equals(CommonConstants.ELEM.DIS_HIS)).collect(Collectors.toList());
                  if (CollectionUtils.isNotEmpty(hisList)) {
                     hisList.sort((m1, m2) -> m2.getCreDate().compareTo(m1.getCreDate()));
                     String elemCon = ((EmrElemstoe)hisList.get(0)).getElemCon();
                     if (StringUtils.isNotEmpty(elemCon)) {
                        medicalRecordDigest = medicalRecordDigest + elemCon;
                     }
                  }

                  if (CollectionUtils.isNotEmpty(signList)) {
                     signList.sort((m1, m2) -> m2.getCreDate().compareTo(m1.getCreDate()));
                     String elemCon = ((EmrElemstoe)signList.get(0)).getElemCon();
                     if (StringUtils.isNotEmpty(elemCon)) {
                        medicalRecordDigest = medicalRecordDigest + elemCon;
                     }
                  }
               }
            }
         }

         tdPaApplyFormVo.setComplaint(mainStr);
         tdPaApplyFormVo.setMedicalRecordDigest(medicalRecordDigest);
      }

      VisitinfoVo param = new VisitinfoVo();
      param.setPatientId(patientId);
      VisitinfoVo vo = this.visitinfoService.selectPatientById(param);
      tdPaApplyFormVo.setAge(vo.getAgeY() == null ? null : vo.getAgeY().toString());
      tdPaApplyFormVo.setSex(vo.getSexCd());
      return tdPaApplyFormVo;
   }

   public Map getPrintInfoList(List tdPaApplyFormList) throws Exception {
      Map<String, Object> map = new HashMap();
      if (tdPaApplyFormList != null && !tdPaApplyFormList.isEmpty()) {
         List<TdPaApplyFormVo> printList = new ArrayList();
         List<TdPaApplyFormVo> hintPrintList = new ArrayList();
         List<SysDictData> dictDataList = this.dictDataService.selectDictDataByType("s103");
         List<SysDictData> dictDataSexList = this.dictDataService.selectDictDataByType("s028");
         List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(((TdPaApplyFormVo)tdPaApplyFormList.get(0)).getAdmissionNo());
         Map<String, BabyInfo> babyInfoMap = (Map<String, BabyInfo>)(CollectionUtils.isNotEmpty(babyInfoList) ? (Map)babyInfoList.stream().collect(Collectors.toMap((t) -> t.getPatBabyId(), Function.identity())) : new HashMap(1));
         Map<String, List<SysDictData>> dictDataMap = (Map<String, List<SysDictData>>)(CollectionUtils.isNotEmpty(dictDataList) ? (Map)dictDataList.stream().collect(Collectors.groupingBy((t) -> t.getDictValue())) : new HashMap(1));

         for(TdPaApplyFormVo tdPaApplyForm : tdPaApplyFormList) {
            if (StringUtils.isNotBlank(tdPaApplyForm.getBabyNo())) {
               BabyInfo babyInfo = (BabyInfo)babyInfoMap.get(tdPaApplyForm.getBabyNo());
               tdPaApplyForm.setPatientName(babyInfo != null ? babyInfo.getBabyName() : tdPaApplyForm.getPatientDepName());
               tdPaApplyForm.setSex(babyInfo != null ? babyInfo.getBabySexName() : tdPaApplyForm.getSex());
               tdPaApplyForm.setAge("新生儿");
            }

            if (StringUtils.isNotBlank(tdPaApplyForm.getPhysiologicalCycle())) {
               List<SysDictData> physiologicalCycleList = (List)dictDataMap.get(tdPaApplyForm.getPhysiologicalCycle());
               String physiologicalCycle = CollectionUtils.isNotEmpty(physiologicalCycleList) ? ((SysDictData)physiologicalCycleList.get(0)).getDictLabel() : null;
               tdPaApplyForm.setPhysiologicalCycle(physiologicalCycle);
            }

            if (tdPaApplyForm.getPrintFlag().equals("1")) {
               printList.add(tdPaApplyForm);
            } else if (tdPaApplyForm.getPrintFlag().equals("2")) {
               hintPrintList.add(tdPaApplyForm);
            }
         }

         map.put("print", printList);
         map.put("hintPrint", hintPrintList);
      }

      return map;
   }

   public TdPaApplyFormVo selectNewestCheck(String patientId) throws Exception {
      return this.tdPaApplyFormMapper.selectNewestCheck(patientId);
   }

   public TdPaApplyForm genTdPaApplyForm(TdPaOrder order, List orderItemList, List orderDetailList, OrderSaveVo orderSaveVo, Visitinfo visitinfo, List tdPaApplyFormList, List tdPaApplyFormItemList, List tdPaApplyFormDetailList, Map applyFormItemSaveVoMap) {
      String applyFormNo = DateUtils.getDateStr("");
      TdPaApplyForm tdPaApplyForm = new TdPaApplyForm();
      TdPaOrderItem orderItemTemp = (TdPaOrderItem)orderItemList.get(0);
      tdPaApplyForm.setHospitalCode(order.getHospitalCode());
      tdPaApplyForm.setApplyFormNo(applyFormNo);
      tdPaApplyForm.setApplyFormClassCode(order.getOrderClassCode());
      tdPaApplyForm.setApplyFormTypeCode(orderItemTemp.getDocumentTypeNo());
      tdPaApplyForm.setOrderNo(order.getOrderNo());
      tdPaApplyForm.setOrderSortNumber(orderItemTemp.getOrderSortNumber());
      tdPaApplyForm.setPatientId(order.getPatientId());
      tdPaApplyForm.setCaseNo(order.getCaseNo());
      tdPaApplyForm.setAdmissionNo(order.getAdmissionNo());
      tdPaApplyForm.setHospitalizedCount(order.getHospitalizedCount());
      tdPaApplyForm.setPatientName(visitinfo.getPatientName());
      tdPaApplyForm.setBabyNo(order.getBabyAdmissionNo());
      tdPaApplyForm.setPatientDepCode(order.getPatientDepCode());
      tdPaApplyForm.setPatientDepName(order.getPatientDepName());
      tdPaApplyForm.setApplyTime(orderItemTemp.getOrderStartTime());
      tdPaApplyForm.setOrderDoctorCode(orderItemTemp.getOrderStartDoc());
      tdPaApplyForm.setOrderDoctorName(orderItemTemp.getOrderStartDocName());
      tdPaApplyForm.setPhysicianDptNo(order.getOrderDoctorDepCode());
      tdPaApplyForm.setPhysicianDptName(order.getOrderDoctorDepName());
      tdPaApplyForm.setPerformDepCode(orderSaveVo.getDetailPerformDepCode());
      tdPaApplyForm.setPerformDepName(orderSaveVo.getDetailPerformDepName());
      tdPaApplyForm.setDiagnosisCode(orderSaveVo.getDiagnosisCode());
      tdPaApplyForm.setDiagnosisName(orderSaveVo.getDiagnosisName());
      tdPaApplyForm.setMedicalRecordDigest(orderSaveVo.getMedicalRecordDigest());
      tdPaApplyForm.setPurposeExamination(orderSaveVo.getPurposeExamination());
      tdPaApplyForm.setExamPart(orderSaveVo.getExamPartName());
      tdPaApplyForm.setExamCategory(orderSaveVo.getExamMachineName());
      tdPaApplyForm.setSampleNo(orderSaveVo.getSpecCd());
      tdPaApplyForm.setSampleName(orderSaveVo.getSpecName());
      tdPaApplyForm.setApplyFormStatus("0");
      tdPaApplyForm.setOperatorCode(order.getOperatorCode());
      tdPaApplyForm.setOperatorName(order.getOperatorName());
      tdPaApplyForm.setRemark(orderItemTemp.getOederDesc());
      tdPaApplyForm.setUrgentFlag(StringUtils.isEmpty(orderItemTemp.getUrgentFlag()) ? "0" : orderItemTemp.getUrgentFlag());

      for(TdPaOrderItem orderItem : orderItemList) {
         OrderSaveVo orderItemSaveVo = (OrderSaveVo)applyFormItemSaveVoMap.get(orderItem.getOrderNo() + orderItem.getOrderSortNumber());
         TdPaApplyFormItem tdPaApplyFormItem = new TdPaApplyFormItem();
         tdPaApplyFormItem.setId(SnowIdUtils.uniqueLong());
         tdPaApplyFormItem.setApplyFormNo(applyFormNo);
         tdPaApplyFormItem.setOrderGroupNo(orderItem.getOrderGroupNo());
         tdPaApplyFormItem.setItemSortNumber(orderItem.getOrderSortNumber());
         tdPaApplyFormItem.setItemCode(orderItem.getCpNo());
         tdPaApplyFormItem.setItemName(orderItem.getCpName());
         tdPaApplyFormItem.setPrice(orderItem.getPriceItem());
         tdPaApplyFormItem.setDose(orderItem.getOrderDoseItem());
         tdPaApplyFormItem.setUnit(orderItem.getUnitItem());
         tdPaApplyFormItem.setExamPartCd(orderItemSaveVo.getExamPartCd());
         tdPaApplyFormItem.setExamPartName(orderItemSaveVo.getExamPartName());
         tdPaApplyFormItem.setSpecCd(orderItemSaveVo.getSpecCd());
         tdPaApplyFormItem.setSpecName(orderItemSaveVo.getSpecName());
         tdPaApplyFormItem.setApplyFormStatus(tdPaApplyForm.getApplyFormStatus());
         List<TdPaOrderDetail> orderDetailListTemp = (List)orderDetailList.stream().filter((t) -> t.getOrderNo().equals(orderItem.getOrderNo()) && t.getOrderSortNumber().equals(orderItem.getOrderSortNumber())).collect(Collectors.toList());

         for(TdPaOrderDetail orderDetail : orderDetailListTemp) {
            TdPaApplyFormDetail tdPaApplyFormDetail = new TdPaApplyFormDetail();
            tdPaApplyFormDetail.setId(SnowIdUtils.uniqueLong());
            tdPaApplyFormDetail.setApplyFormNo(tdPaApplyForm.getApplyFormNo());
            tdPaApplyFormDetail.setItemSortNumber(tdPaApplyFormItem.getItemSortNumber());
            tdPaApplyFormDetail.setOrderGroupSortNumber(orderDetail.getOrderGroupSortNumber());
            tdPaApplyFormDetail.setChargeNo(orderDetail.getChargeNo());
            tdPaApplyFormDetail.setChargeName(orderDetail.getChargeName());
            tdPaApplyFormDetail.setStandardCode(orderDetail.getStandardCode());
            tdPaApplyFormDetail.setStandardName(orderDetail.getStandardName());
            tdPaApplyFormDetail.setStandard(orderDetail.getStandard());
            tdPaApplyFormDetail.setUnit(orderDetail.getUnit());
            tdPaApplyFormDetail.setPrice(orderDetail.getPrice());
            tdPaApplyFormDetail.setDose(orderDetail.getOrderDose() != null ? orderDetail.getOrderDose().doubleValue() : null);
            tdPaApplyFormDetailList.add(tdPaApplyFormDetail);
         }

         if (CollectionUtils.isEmpty(orderDetailListTemp)) {
            TdPaApplyFormDetail tdPaApplyFormDetail = new TdPaApplyFormDetail();
            tdPaApplyFormDetail.setId(SnowIdUtils.uniqueLong());
            tdPaApplyFormDetail.setApplyFormNo(tdPaApplyForm.getApplyFormNo());
            tdPaApplyFormDetail.setItemSortNumber(tdPaApplyFormItem.getItemSortNumber());
            tdPaApplyFormDetail.setOrderGroupSortNumber("01");
            tdPaApplyFormDetail.setChargeNo("NNNNNN");
            tdPaApplyFormDetail.setChargeName(orderItem.getCpName());
            tdPaApplyFormDetailList.add(tdPaApplyFormDetail);
         }

         tdPaApplyFormItemList.add(tdPaApplyFormItem);
      }

      tdPaApplyFormList.add(tdPaApplyForm);
      return tdPaApplyForm;
   }

   public void insertTdPaApplyFormList(List tdPaApplyFormList) throws Exception {
      if (tdPaApplyFormList != null && !tdPaApplyFormList.isEmpty()) {
         this.tdPaApplyFormMapper.insertList(tdPaApplyFormList);
      }

   }

   public List selectCheckFlowChart(String applyFormNo) throws Exception {
      List<Map<String, Object>> listMap = new ArrayList();
      TdPaApplyForm tdPaApplyForm = this.tdPaApplyFormMapper.selectTdPaApplyFormById(applyFormNo);
      this.tdPaApplyFormItemService.selectTdPaApplyFormItemByFormNo(applyFormNo);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      if (tdPaApplyForm != null) {
         Map<String, Object> map = new HashMap();
         map.put("date", sdf.format(tdPaApplyForm.getApplyTime()));
         map.put("docName", tdPaApplyForm.getOrderDoctorName());
         map.put("status", "开单申请");
         listMap.add(map);
         if (tdPaApplyForm.getApplyFormClassCode().equals("03")) {
            List<TdPaApplyFormVo> tmInfoList = this.tdPaApplyFormMapper.testTmInfo(tdPaApplyForm.getApplyFormNo());
            TdPaApplyFormVo tmInfo = CollectionUtils.isNotEmpty(tmInfoList) ? (TdPaApplyFormVo)tmInfoList.get(0) : null;
            List<TestReport> testReportList = this.testReportService.selectByAppCd(tmInfo == null ? tdPaApplyForm.getApplyFormNo() : tmInfo.getApplyFormNo());
            Map<String, Object> map1 = new HashMap();
            map1.put("status", "生成条码");
            listMap.add(map1);
            Map<String, Object> map2 = new HashMap();
            map2.put("status", "打印条码");
            listMap.add(map2);
            Map<String, Object> map3 = new HashMap();
            map3.put("status", "采样确认");
            listMap.add(map3);
            Map<String, Object> map4 = new HashMap();
            map4.put("status", "标本送出");
            listMap.add(map4);
            Map<String, Object> map5 = new HashMap();
            map5.put("status", "标本接收");
            listMap.add(map5);
            Map<String, Object> map6 = new HashMap();
            map6.put("status", "条码上机");
            listMap.add(map6);
            Map<String, Object> map7 = new HashMap();
            map7.put("status", "报告发布");
            if (CollectionUtils.isNotEmpty(testReportList)) {
               String issTime = ((TestReport)testReportList.get(0)).getIssTime() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, ((TestReport)testReportList.get(0)).getIssTime()) : null;
               map7.put("date", issTime);
               map7.put("docName", ((TestReport)testReportList.get(0)).getIssName());
            }

            listMap.add(map7);
            Map<String, Object> map8 = new HashMap();
            map8.put("status", "报告打印");
            listMap.add(map8);
            if (tmInfo != null) {
               String sctmsj = tmInfo.getSctmsj() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, tmInfo.getSctmsj()) : null;
               String printSj = tmInfo.getPrintSj() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, tmInfo.getPrintSj()) : null;
               String smSj = tmInfo.getSmSj() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, tmInfo.getSmSj()) : null;
               String scSj = tmInfo.getScSj() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, tmInfo.getScSj()) : null;
               map1.put("date", sctmsj);
               map1.put("docName", tmInfo.getOper());
               map2.put("date", printSj);
               map2.put("docName", StringUtils.isNotBlank(printSj) ? tmInfo.getPrintOper() : null);
               map3.put("date", smSj);
               map3.put("docName", StringUtils.isNotBlank(smSj) ? tmInfo.getSmOper() : null);
               map4.put("date", scSj);
               map4.put("docName", StringUtils.isNotBlank(scSj) ? tmInfo.getScOper() : null);
               String recieveDt = tmInfo.getRecieveDt() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, tmInfo.getRecieveDt()) : null;
               String testDt = tmInfo.getTestDt() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, tmInfo.getTestDt()) : null;
               String reportPrintDt = tmInfo.getReportPrintDt() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, tmInfo.getReportPrintDt()) : null;
               map5.put("date", recieveDt);
               map5.put("docName", StringUtils.isNotBlank(recieveDt) ? tmInfo.getRecieveUser() : null);
               map6.put("date", testDt);
               map6.put("docName", StringUtils.isNotBlank(testDt) ? tmInfo.getTestUser() : null);
               map8.put("date", reportPrintDt);
               map8.put("docName", StringUtils.isNotBlank(reportPrintDt) ? tmInfo.getReportPrintUser() : null);
            }
         } else {
            List<ExamItem> examItemList = this.examItemService.selectByAppCd(tdPaApplyForm.getApplyFormNo());
            Map<String, Object> map6 = new HashMap();
            map6.put("status", "上机");
            listMap.add(map6);
            Map<String, Object> map7 = new HashMap();
            map7.put("status", "报告发布");
            listMap.add(map7);
            Map<String, Object> map8 = new HashMap();
            map8.put("status", "报告打印");
            listMap.add(map8);
            if (CollectionUtils.isNotEmpty(examItemList)) {
               String examTime = ((ExamItem)examItemList.get(0)).getExamTime() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, ((ExamItem)examItemList.get(0)).getExamTime()) : null;
               String examRepDate = ((ExamItem)examItemList.get(0)).getExamRepDate() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, ((ExamItem)examItemList.get(0)).getExamRepDate()) : null;
               map6.put("date", examTime);
               map6.put("docName", ((ExamItem)examItemList.get(0)).getExamDocName());
               map7.put("date", examRepDate);
               map7.put("docName", ((ExamItem)examItemList.get(0)).getRepDocName());
            }
         }
      }

      return listMap;
   }

   public List selectListByOrderNos(List orderNoList) throws Exception {
      List<TdPaApplyForm> list = new ArrayList(1);
      if (orderNoList != null && !orderNoList.isEmpty()) {
         list = this.tdPaApplyFormMapper.selectListByOrderNos(orderNoList);
      }

      return list;
   }

   public void updateStatusByApplyFormNos(List applyFormNoList, String applyFormStatus) throws Exception {
      if (applyFormNoList != null && !applyFormNoList.isEmpty() && StringUtils.isNotBlank(applyFormStatus)) {
         this.tdPaApplyFormMapper.updateStatusByApplyFormNos(applyFormNoList, applyFormStatus);
      }

   }

   public List selectTdPaApplyFormList(TdPaApplyFormVo tdPaApplyFormVo) throws Exception {
      return this.tdPaApplyFormMapper.selectList(tdPaApplyFormVo);
   }

   public String selectApplyForm(String orderNo, String orderClassCode) {
      return this.tdPaApplyFormMapper.selectApplyFormStatus(orderNo, orderClassCode);
   }

   public List testExamList(TdPaApplyFormVo tdPaApplyFormVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TdPaApplyFormVo> list = this.tdPaApplyFormMapper.testExamList(tdPaApplyFormVo);
      if (CollectionUtils.isNotEmpty(list) && tdPaApplyFormVo.getApplyFormClassCode().equals("03")) {
         List<String> applyFormNoList = (List)list.stream().filter((t) -> StringUtils.isNotBlank(t.getApplyFormStatus()) && t.getApplyFormStatus().equals("9")).map((t) -> t.getApplyFormNo()).collect(Collectors.toList());
         List<TestReport> testReportList = this.testReportService.selectByAppCds(applyFormNoList);
         Map<String, List<TestReport>> testReportMap = (Map)testReportList.stream().collect(Collectors.groupingBy((t) -> t.getAppCd()));

         for(TdPaApplyFormVo vo : list) {
            vo.setExamPart((String)null);
            List<TestReport> testReportListTemp = (List)testReportMap.get(vo.getApplyFormNo());
            TestReport testReport = CollectionUtils.isNotEmpty(testReportListTemp) ? (TestReport)testReportListTemp.get(0) : null;
            vo.setReportDate(testReport != null ? testReport.getIssTime() : null);
            vo.setReportDoctor(testReport != null ? testReport.getIssName() : null);
            if (testReport != null) {
               ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(vo.getAdmissionNo(), vo.getAdmissionNo(), vo.getCaseNo(), vo.getHospitalizedCount() + "", user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), vo.getApplyFormNo(), testReport.getId(), (String)null, (String)null);
               String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, vo.getReportUrl());
               vo.setReportUrl(reportUrl);
            }
         }
      } else {
         List<String> applyFormNoList = (List)list.stream().filter((t) -> StringUtils.isNotBlank(t.getApplyFormStatus()) && t.getApplyFormStatus().equals("9")).map((t) -> t.getApplyFormNo()).collect(Collectors.toList());
         if (!applyFormNoList.isEmpty()) {
            List<ExamItem> examItems = this.examItemService.selectExamItemByAppCdList(applyFormNoList);
            Map<String, List<ExamItem>> map = (Map)examItems.stream().collect(Collectors.groupingBy(ExamItem::getAppCd));

            for(TdPaApplyFormVo vo : list) {
               String applyFormStatus = vo.getApplyFormStatus();
               String applyFormNo = vo.getApplyFormNo();
               if (applyFormStatus.equals("9") && map.containsKey(applyFormNo)) {
                  ExamItem items = (ExamItem)((List)map.get(applyFormNo)).get(0);
                  ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(vo.getAdmissionNo(), vo.getAdmissionNo(), vo.getCaseNo(), vo.getHospitalizedCount() + "", user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), vo.getApplyFormNo(), items.getId(), (String)null, (String)null);
                  String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, vo.getReportUrl());
                  vo.setReportUrl(reportUrl);
               }
            }
         }
      }

      return list;
   }

   public List testTmInfo(String applyFormNo) throws Exception {
      return this.tdPaApplyFormMapper.testTmInfo(applyFormNo);
   }

   public ApplyFormPrintResp queryApplyFormPrintResp(String applyFormNo, SysUser user) throws Exception {
      List<TdPaApplyFormItemVo> applyFormItemlist = this.tdPaApplyFormItemService.selectTdPaApplyFormItemByFormNo(applyFormNo);
      String patientId = null;
      BigDecimal totalPrice = BigDecimal.ZERO;
      if (CollectionUtils.isNotEmpty(applyFormItemlist)) {
         for(TdPaApplyFormItemVo itemVo : applyFormItemlist) {
            totalPrice = totalPrice.add(itemVo.getMoney());
            itemVo.setMoney(itemVo.getMoney().setScale(2, 4));
            patientId = itemVo.getPatientId();
         }
      }

      TdPaApplyForm formQparam = new TdPaApplyForm();
      formQparam.setApplyFormNo(applyFormNo);
      formQparam.setPatientId(patientId);
      formQparam.setHospitalCode(user.getHospital().getOrgCode());
      List<TdPaApplyFormVo> applyFormList = this.selectTdPaApplyFormListAll(formQparam);
      TdPaApplyFormVo applyForm = CollectionUtils.isNotEmpty(applyFormList) ? (TdPaApplyFormVo)applyFormList.get(0) : null;
      if (applyForm != null) {
         PrintTmplInfo printTmplInfo = this.printTmplInfoService.selectTmPmPrintTmplInfoByCode(applyForm.getPrintFormat());
         String tmplFilePath = printTmplInfo != null ? printTmplInfo.getTmplFilePath() : null;
         applyForm.setTmplFilePath(tmplFilePath);
      }

      ApplyFormPrintResp applyFormPrint = new ApplyFormPrintResp();
      applyFormPrint.setApplyForm(applyForm);
      applyFormPrint.setApplyItemList(applyFormItemlist);
      applyFormPrint.setHospitalName(user.getHospital().getOrgName());
      applyFormPrint.setTotalPrice(totalPrice.setScale(2, 4));
      applyFormPrint.setUrgentName(applyForm != null ? (StringUtils.isNotBlank(applyForm.getUrgentFlag()) && applyForm.getUrgentFlag().equals("1") ? "急" : "") : null);
      applyFormPrint.setInfectiousName(applyForm != null ? (StringUtils.isNotBlank(applyForm.getInfectiousFlag()) && applyForm.getInfectiousFlag().equals("1") ? "是" : "否") : null);
      return applyFormPrint;
   }

   public List selectTdPaApplyFormListAll(TdPaApplyForm tdPaApplyForm) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TdPaApplyFormVo> list = this.tdPaApplyFormMapper.selectTdPaApplyFormListAll(tdPaApplyForm);
      List<TestReport> reportVoList = this.testReportService.selectTestReportByPatientId(tdPaApplyForm.getPatientId());
      Map<String, List<TestReport>> reportMap = new HashMap(1);
      if (reportVoList != null && !reportVoList.isEmpty()) {
         reportMap = (Map)reportVoList.stream().collect(Collectors.groupingBy((s) -> s.getAppCd()));
      }

      if (list != null && !list.isEmpty()) {
         List<SysDictData> dictDataList = this.dictDataService.selectDictDataByType("s103");
         Map<String, List<SysDictData>> dictDataMap = (Map<String, List<SysDictData>>)(CollectionUtils.isNotEmpty(dictDataList) ? (Map)dictDataList.stream().collect(Collectors.groupingBy((t) -> t.getDictValue())) : new HashMap(1));
         VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(tdPaApplyForm.getPatientId());

         for(TdPaApplyFormVo tdPaApplyFormVo : list) {
            tdPaApplyFormVo.setBedNo(personalVo.getBedNo());
            if (StringUtils.isNotBlank(tdPaApplyFormVo.getBabyNo())) {
               tdPaApplyFormVo.setPatientName(tdPaApplyFormVo.getBabyName());
               tdPaApplyFormVo.setSex(tdPaApplyFormVo.getBabySex());
               tdPaApplyFormVo.setAge("新生儿");
            } else {
               tdPaApplyFormVo.setSex(personalVo.getSex());
               tdPaApplyFormVo.setAge(AgeUtil.getAgeStr(personalVo.getAgeY(), personalVo.getAgeM(), personalVo.getAgeD(), personalVo.getAgeH(), personalVo.getAgeMi()));
            }

            tdPaApplyFormVo.setResDocName(personalVo.getResDocName());
            tdPaApplyFormVo.setPatType(personalVo.getPatTypeName());
            tdPaApplyFormVo.setProTypeName(personalVo.getProTypeName());
            tdPaApplyFormVo.setNowAddrTel(personalVo.getNowAddrTel());
            tdPaApplyFormVo.setWeight(personalVo.getWeight());
            List<TestReport> reports = (List)reportMap.get(tdPaApplyFormVo.getApplyFormNo());
            if (reports != null && !reports.isEmpty()) {
               tdPaApplyFormVo.setConDocName(((TestReport)reports.get(0)).getConDocName());
               if (((TestReport)reports.get(0)).getRecDate() == null) {
                  tdPaApplyFormVo.setRecFlag(CommonConstants.BOOL_FALSE);
               } else {
                  tdPaApplyFormVo.setRecFlag(CommonConstants.BOOL_TRUE);
               }
            } else {
               tdPaApplyFormVo.setRecFlag(CommonConstants.BOOL_TRUE);
            }

            if (tdPaApplyFormVo.getApplyFormStatus().equals("9")) {
               ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(personalVo.getInpNo(), personalVo.getInpNo(), personalVo.getRecordNo(), personalVo.getVisitId().toString(), user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), tdPaApplyFormVo.getApplyFormNo(), personalVo.getIdCard(), (String)null, (String)null);
               tdPaApplyFormVo.setReportUrl(this.commonService.replaceUrlParam(replaceUrlParamVo, tdPaApplyFormVo.getReportUrl()));
            }

            if (StringUtils.isNotBlank(tdPaApplyFormVo.getPhysiologicalCycle())) {
               List<SysDictData> physiologicalCycleList = (List)dictDataMap.get(tdPaApplyFormVo.getPhysiologicalCycle());
               String physiologicalCycle = CollectionUtils.isNotEmpty(physiologicalCycleList) ? ((SysDictData)physiologicalCycleList.get(0)).getDictLabel() : null;
               tdPaApplyFormVo.setPhysiologicalCycle(physiologicalCycle);
            }
         }
      }

      return list;
   }
}
