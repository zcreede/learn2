package com.emr.project.docOrder.service.impl;

import com.emr.common.exception.BaseException;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaApplyFormDetail;
import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.docOrder.domain.vo.DrugAndClinPatientVo;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.emr.project.docOrder.domain.vo.ItemIsOpenResVo;
import com.emr.project.docOrder.domain.vo.ItemListIsOpenResVo;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.OrderSignTextVo;
import com.emr.project.docOrder.domain.vo.OrderStopVo;
import com.emr.project.docOrder.domain.vo.TdCaOperationApplyVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.domain.vo.TdPaTakeDrugListVO;
import com.emr.project.docOrder.mapper.TdPaOrderItemMapper;
import com.emr.project.docOrder.service.ITdMsgSendMainService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.docOrder.util.OrderUtil;
import com.emr.project.dr.domain.DrAntiApply;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.dr.service.IDrAntiApplyService;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.TdNaAllergyRecord;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.DocumentType;
import com.emr.project.tmpm.service.IDocumentTypeService;
import com.emr.project.tmpm.service.ITmPmCipherDetailService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zxp.esclientrhl.repository.PageList;

@Service
public class TdPaOrderItemServiceImpl implements ITdPaOrderItemService {
   @Autowired
   private TdPaOrderItemMapper tdPaOrderItemMapper;
   @Autowired
   private ITdPaOrderDetailService tdPaOrderDetailService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IDocumentTypeService documentTypeService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;
   @Autowired
   private IDrAntiApplyService drAntiApplyService;
   @Autowired
   private ITdPaApplyFormService applyFormService;
   @Autowired
   private ITmPmCipherDetailService tmPmCipherDetailService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ITdPaOrderOperationLogService tdPaOrderOperationLogService;
   @Autowired
   private ITdPaOrderSignMainService tdPaOrderSignMainService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private ITdMsgSendMainService tdMsgSendMainService;

   public TdPaOrderItemVo selectTdPaOrderItemById(String orderNo) {
      List<TdPaOrderItemVo> list = this.tdPaOrderItemMapper.selectTdPaOrderItemById(orderNo);
      return CollectionUtils.isNotEmpty(list) ? (TdPaOrderItemVo)list.get(0) : null;
   }

   public List selectTdPaOrderItemList(TdPaOrderItemVo tdPaOrderItem) throws Exception {
      return this.tdPaOrderItemMapper.selectTdPaOrderItemList(tdPaOrderItem);
   }

   public List selectOrderHerbList(TdPaOrderItemVo tdPaOrderItem) throws Exception {
      List<TdPaOrderItemVo> list = this.tdPaOrderItemMapper.selectOrderHerbList(tdPaOrderItem);
      if (list != null && !list.isEmpty()) {
         VisitinfoVo param = new VisitinfoVo();
         param.setPatientId(tdPaOrderItem.getPatientId());
         VisitinfoVo vo = this.visitinfoService.selectPatientById(param);
         List<DiagInfo> diagInfo = this.visitinfoService.selectDiagInfo(tdPaOrderItem.getPatientId());
         if (diagInfo != null && diagInfo.size() > 0) {
            Map<String, List<DiagInfo>> diagClassListMap = (Map)diagInfo.stream().collect(Collectors.groupingBy(DiagInfo::getDiagClass));
            List<DiagInfo> zyDiag = (List)diagClassListMap.get("ZY");
            List<DiagInfo> xyDiag = (List)diagClassListMap.get("XY");
            if (zyDiag != null && zyDiag.size() > 0) {
               Map<String, List<DiagInfo>> map = (Map)zyDiag.stream().collect(Collectors.groupingBy(DiagInfo::getDiagTypeCd));
               List<DiagInfo> diagInfos = (List)map.get("02");
               if (diagInfos != null && diagInfos.size() > 0) {
                  Map<String, List<DiagInfo>> diagClassMap = (Map)diagInfos.stream().collect(Collectors.groupingBy(DiagInfo::getDiagClassCd));
                  List<DiagInfo> infoList = (List)diagClassMap.get("11");
                  if (infoList != null && infoList.size() > 0) {
                     DiagInfo info = (DiagInfo)infoList.get(0);
                     vo.setInhosDiag(info.getDiagName());
                  } else if (xyDiag != null && xyDiag.size() > 0) {
                     Map<String, List<DiagInfo>> xyTypeCode = (Map)xyDiag.stream().collect(Collectors.groupingBy(DiagInfo::getDiagTypeCd));
                     List<DiagInfo> infos = (List)xyTypeCode.get("02");
                     if (infos != null && infos.size() > 0) {
                        Map<String, List<DiagInfo>> diagClassXYMap = (Map)infos.stream().collect(Collectors.groupingBy(DiagInfo::getDiagClassCd));
                        List<DiagInfo> diagInfos1 = (List)diagClassXYMap.get("01");
                        if (diagInfos1 != null && diagInfos1.size() > 0) {
                           DiagInfo info = (DiagInfo)diagInfos1.get(0);
                           vo.setInhosDiag(info.getDiagName());
                        }
                     }
                  }

                  List<DiagInfo> diagInfoList = (List)diagClassMap.get("12");
                  if (diagInfoList != null && diagInfoList.size() > 0) {
                     DiagInfo diagInfo1 = (DiagInfo)diagInfoList.get(0);
                     vo.setInhosCmSymName(diagInfo1.getDiagName());
                  }
               } else if (xyDiag != null && xyDiag.size() > 0) {
                  Map<String, List<DiagInfo>> xyTypeCode = (Map)xyDiag.stream().collect(Collectors.groupingBy(DiagInfo::getDiagTypeCd));
                  List<DiagInfo> infos = (List)xyTypeCode.get("02");
                  if (infos != null && infos.size() > 0) {
                     Map<String, List<DiagInfo>> diagClassXYMap = (Map)infos.stream().collect(Collectors.groupingBy(DiagInfo::getDiagClassCd));
                     List<DiagInfo> diagInfos1 = (List)diagClassXYMap.get("01");
                     if (diagInfos1 != null && diagInfos1.size() > 0) {
                        DiagInfo info = (DiagInfo)diagInfos1.get(0);
                        vo.setInhosDiag(info.getDiagName());
                     }
                  }
               }
            } else if (xyDiag != null && xyDiag.size() > 0) {
               Map<String, List<DiagInfo>> xyTypeCode = (Map)xyDiag.stream().collect(Collectors.groupingBy(DiagInfo::getDiagTypeCd));
               List<DiagInfo> infos = (List)xyTypeCode.get("02");
               if (infos != null && infos.size() > 0) {
                  Map<String, List<DiagInfo>> diagClassXYMap = (Map)infos.stream().collect(Collectors.groupingBy(DiagInfo::getDiagClassCd));
                  List<DiagInfo> diagInfos1 = (List)diagClassXYMap.get("01");
                  if (diagInfos1 != null && diagInfos1.size() > 0) {
                     DiagInfo info = (DiagInfo)diagInfos1.get(0);
                     vo.setInhosDiag(info.getDiagName());
                  }
               }
            }
         }

         vo.setAge(AgeUtil.getAgeStr(vo.getAgeY(), vo.getAgeM(), vo.getAgeD(), vo.getAgeH(), vo.getAgeMi()));

         for(TdPaOrderItemVo tdPaOrderItemVo : list) {
            tdPaOrderItemVo.setVisitinfoVo(vo);
         }
      }

      return list;
   }

   public int insertTdPaOrderItem(TdPaOrderItem tdPaOrderItem) {
      return this.tdPaOrderItemMapper.insertTdPaOrderItem(tdPaOrderItem);
   }

   public int updateTdPaOrderItem(TdPaOrderItem tdPaOrderItem) {
      return this.tdPaOrderItemMapper.updateTdPaOrderItem(tdPaOrderItem);
   }

   public int deleteTdPaOrderItemByIds(String[] orderNos) {
      return this.tdPaOrderItemMapper.deleteTdPaOrderItemByIds(orderNos);
   }

   public int deleteTdPaOrderItemById(String orderNo) {
      return this.tdPaOrderItemMapper.deleteTdPaOrderItemById(orderNo);
   }

   public List selectByOrderNoList(List orderNoList) throws Exception {
      List<TdPaOrderItem> list = orderNoList != null && !orderNoList.isEmpty() ? this.tdPaOrderItemMapper.selectByOrderNoList(orderNoList) : null;
      return list;
   }

   public List selectFromStatusByOrderNoList(List orderNoList) {
      List<TdPaOrderItem> list = orderNoList != null && !orderNoList.isEmpty() ? this.tdPaOrderItemMapper.selectFromStatusByOrderNoList(orderNoList) : null;
      return list;
   }

   public void updateOrderStatus(TdPaOrderItemVo param) throws Exception {
      if (param.getOrderNoList() != null && !param.getOrderNoList().isEmpty()) {
         this.tdPaOrderItemMapper.updateOrderStatus(param);
      }

   }

   public void updateCancelCommit(TdPaOrderItemVo param) {
      if (param.getOrderNoList() != null && !param.getOrderNoList().isEmpty()) {
         this.tdPaOrderItemMapper.updateCancelCommit(param);
      }

   }

   public void updateOrderStatusList(List orderItemVoList) throws Exception {
      if (orderItemVoList != null && !orderItemVoList.isEmpty()) {
         this.tdPaOrderItemMapper.updateOrderStatusList(orderItemVoList);
      }

   }

   public ItemIsOpenResVo selectItemIsOpen(DrugAndClinPatientVo drugAndClinPatientVo) throws Exception {
      String resCode = "1";
      String msgFlag = "0";
      String windowFlag = "0";
      String windowCode = null;
      String drugClassCode = null;
      String tempOpenFlag = "0";
      String drugGradeCd = null;
      String drugGradeName = null;
      String drugLevelCd = null;
      StringBuffer resMsg = new StringBuffer();
      if (!resCode.equals("0")) {
         TdPaOrderItem outOrderParam = new TdPaOrderItem(drugAndClinPatientVo.getPatientId(), "01");
         List<TdPaOrderItem> outOrderList = this.tdPaOrderItemMapper.selectPatientOutOrder(outOrderParam, (List)null);
         if (outOrderList != null && !outOrderList.isEmpty()) {
            resCode = "0";
            msgFlag = "1";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.outHos"));
         }
      }

      if (!resCode.equals("0")) {
         VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(drugAndClinPatientVo.getPatientId());
         if (visitinfoVo != null && visitinfoVo.getOutTime() != null) {
            resCode = "0";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.outTime"));
         }
      }

      if (!resCode.equals("0")) {
         List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(drugAndClinPatientVo.getPatientId(), "02", "01");
         if (diagInfoList == null || diagInfoList != null && diagInfoList.isEmpty()) {
            resCode = "0";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.diag"));
         }
      }

      if (drugAndClinPatientVo.getOrderClassCode().equals("01") && !resCode.equals("0")) {
         ItemIsOpenResVo temp = this.drugStockService.antiUseAimIsOpen(SecurityUtils.getLoginUser().getUser(), drugAndClinPatientVo.getCpNo());
         if (temp != null) {
            resCode = temp.getResCode();
            msgFlag = temp.getMsgFlag();
            resMsg.append(StringUtils.isNotBlank(temp.getResMsg()) ? temp.getResMsg() : "");
            if (!temp.getResCode().equals("0")) {
               drugClassCode = temp.getDrugClassCode();
               drugLevelCd = temp.getDrugLevelCd();
               if (temp.getDrugClassCode().equals("01") && StringUtils.isNotBlank(temp.getWindowFlag()) && temp.getWindowFlag().equals("1")) {
                  List<DrAntiApply> drAntiApplyList = this.drAntiApplyService.selectByPatientAndDrugCode(new DrAntiApply(drugAndClinPatientVo.getPatientId(), drugAndClinPatientVo.getCpNo(), (String)null));
                  drAntiApplyList = drAntiApplyList != null ? (List)drAntiApplyList.stream().filter((t) -> !t.getState().equals("2")).collect(Collectors.toList()) : null;
                  if (drAntiApplyList != null && (drAntiApplyList == null || !drAntiApplyList.isEmpty())) {
                     List<DrAntiApply> drAntiApplyList2 = drAntiApplyList != null ? (List)drAntiApplyList.stream().filter((t) -> t.getState().equals("1")).collect(Collectors.toList()) : null;
                     List<DrAntiApply> drAntiApplyList1 = drAntiApplyList != null ? (List)drAntiApplyList.stream().filter((t) -> t.getState().equals("0")).collect(Collectors.toList()) : null;
                     if (CollectionUtils.isEmpty(drAntiApplyList2) && CollectionUtils.isNotEmpty(drAntiApplyList1)) {
                        drAntiApplyList1 = (List)drAntiApplyList1.stream().sorted(Comparator.comparing((t) -> t.getApplyDate())).collect(Collectors.toList());
                        String beginTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, ((DrAntiApply)drAntiApplyList1.get(0)).getApplyDate());
                        String endTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, DateUtils.addHours(((DrAntiApply)drAntiApplyList1.get(0)).getApplyDate(), 24));
                        List<TdPaOrderItem> orderItemList = this.selectPatOrderForDrugCodes(drugAndClinPatientVo.getPatientId(), beginTime, endTime, drugAndClinPatientVo.getCpNo());

                        for(DrAntiApply drAntiApply : drAntiApplyList1) {
                           Date applyDate = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, drAntiApply.getApplyDate()));
                           Date endDate = DateUtils.addHours(applyDate, 24);
                           Date orderStartTime = drugAndClinPatientVo.getOrderStartTime();
                           List<TdPaOrderItem> orderItemList1 = (List)orderItemList.stream().filter((t) -> t.getOrderStartTime().compareTo(applyDate) >= 0 && t.getOrderStartTime().compareTo(endDate) < 0).collect(Collectors.toList());
                           if (CollectionUtils.isEmpty(orderItemList1) && orderStartTime.compareTo(applyDate) >= 0 && orderStartTime.compareTo(endDate) < 0) {
                              tempOpenFlag = "1";
                              break;
                           }
                        }

                        if (StringUtils.isBlank(tempOpenFlag) || StringUtils.isNotBlank(tempOpenFlag) && tempOpenFlag.equals("0")) {
                           windowFlag = temp.getWindowFlag();
                           windowCode = temp.getWindowCode();
                           drugGradeName = temp.getDrugGradeName();
                           drugGradeCd = temp.getDrugGradeCd();
                        }
                     }
                  } else {
                     windowFlag = temp.getWindowFlag();
                     windowCode = temp.getWindowCode();
                     drugGradeName = temp.getDrugGradeName();
                     drugGradeCd = temp.getDrugGradeCd();
                  }
               }
            }
         }
      }

      if (!resCode.equals("0")) {
         String documentTypeNo = drugAndClinPatientVo.getDocumentTypeNo();
         if (StringUtils.isNotBlank(documentTypeNo)) {
            DocumentType documentType = this.documentTypeService.selectDocumentTypeById(documentTypeNo);
            if (documentType != null && StringUtils.isNotBlank(documentType.getOrderFlag()) && documentType.getOrderFlag().equals("0")) {
               resCode = "0";
               msgFlag = "1";
               resMsg.append(MessageUtils.message("doctor.order.item.canOpen.examtest"));
            }
         }
      }

      if (!resCode.equals("0") && StringUtils.isNotBlank(drugAndClinPatientVo.getPsfl())) {
         VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(drugAndClinPatientVo.getPatientId());
         List<TdNaAllergyRecord> allergyRecordList = this.alleInfoService.selectByInpNoAndDrugCode(visitinfo.getInpNo(), drugAndClinPatientVo.getCpNo());
         boolean skinTestFlag = true;
         if (allergyRecordList != null && !allergyRecordList.isEmpty()) {
            List<TdNaAllergyRecord> allergyRecordList2 = (List)allergyRecordList.stream().filter((t) -> !t.getAllergyType().equals("(-)")).collect(Collectors.toList());
            skinTestFlag = !CollectionUtils.isNotEmpty(allergyRecordList2);
         }

         if (skinTestFlag) {
            Date before72h = DateUtils.addHours(this.commonService.getDbSysdate(), -72);
            List<TdNaAllergyRecord> allergyRecordList2 = (List)allergyRecordList.stream().filter((t) -> t.getAllergyType().equals("(-)") && t.getOperatorDate().compareTo(before72h) >= 0).collect(Collectors.toList());
            List<TdPaOrderItem> tdPaOrderItem72hList = this.tdPaOrderItemMapper.selectByPatientIdAndDrugCode72hExe(drugAndClinPatientVo.getPatientId(), drugAndClinPatientVo.getCpNo());
            if (CollectionUtils.isEmpty(allergyRecordList2) && CollectionUtils.isEmpty(tdPaOrderItem72hList)) {
               msgFlag = "1";
               resMsg.append(MessageUtils.message("doctor.order.item.canOpen.skinTest"));
            }
         } else {
            resCode = "0";
            msgFlag = "1";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.skinTest.no"));
         }
      }

      if (!resCode.equals("0")) {
         if (StringUtils.isNotBlank(drugAndClinPatientVo.getHighRiskFlag()) && drugAndClinPatientVo.getHighRiskFlag().equals("1")) {
            msgFlag = "1";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.highRisk", drugAndClinPatientVo.getCpName()));
         }

         if (drugAndClinPatientVo.getOrderClassCode().equals("02") && StringUtils.isBlank(drugAndClinPatientVo.getExamPartCd())) {
            windowFlag = "1";
            windowCode = "2";
         }

         if (drugAndClinPatientVo.getOrderClassCode().equals("03") && StringUtils.isBlank(drugAndClinPatientVo.getSpecCd())) {
            windowFlag = "1";
            windowCode = "2";
         }

         if (StringUtils.isNotBlank(drugAndClinPatientVo.getItemFlagCd()) && drugAndClinPatientVo.getItemFlagCd().equals("02")) {
            windowFlag = "1";
            windowCode = "4";
         }

         if (StringUtils.isNotBlank(drugAndClinPatientVo.getItemFlagCd()) && drugAndClinPatientVo.getItemFlagCd().equals("01")) {
            windowFlag = "1";
            windowCode = "3";
         }
      }

      ItemIsOpenResVo itemIsOpenResVo = new ItemIsOpenResVo(resCode, msgFlag, resMsg.toString(), windowFlag, windowCode, drugClassCode, drugGradeCd, drugGradeName, drugLevelCd);
      itemIsOpenResVo.setTempOpenFlag(tempOpenFlag);
      if (StringUtils.isNotBlank(drugLevelCd)) {
         switch (drugLevelCd) {
            case "精神药品":
               itemIsOpenResVo.setRecipType("5");
               break;
            case "精神药品2":
               itemIsOpenResVo.setRecipType("6");
               break;
            case "麻醉药品":
               itemIsOpenResVo.setRecipType("4");
         }
      }

      if (drugAndClinPatientVo.getOrderClassCode().equals("02") || drugAndClinPatientVo.getOrderClassCode().equals("03")) {
         TdPaApplyFormVo tdPaApplyFormVo = this.applyFormService.selectSelectPatDia(drugAndClinPatientVo.getPatientId());
         List<DrugAndClin> cpNameList = Arrays.asList(drugAndClinPatientVo);
         String purposeExamination = this.applyFormService.getPurposeExamination(cpNameList);
         itemIsOpenResVo.setTdPaApplyFormVo(tdPaApplyFormVo);
         itemIsOpenResVo.setPurposeExamination(purposeExamination);
      }

      return itemIsOpenResVo;
   }

   public ItemIsOpenResVo selectHrefItemIsOpen(String patientId) throws Exception {
      String resCode = "1";
      StringBuffer resMsg = new StringBuffer();
      if (!resCode.equals("0")) {
         TdPaOrderItem outOrderParam = new TdPaOrderItem(patientId, "01");
         List<TdPaOrderItem> outOrderList = this.tdPaOrderItemMapper.selectPatientOutOrder(outOrderParam, (List)null);
         if (outOrderList != null && !outOrderList.isEmpty()) {
            resCode = "0";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.outHos"));
         }
      }

      if (!resCode.equals("0")) {
         VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(patientId);
         if (visitinfoVo != null && visitinfoVo.getOutTime() != null) {
            resCode = "0";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.outTime"));
         }
      }

      if (!resCode.equals("0")) {
         List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(patientId, "02", "01");
         if (diagInfoList == null || diagInfoList != null && diagInfoList.isEmpty()) {
            resCode = "0";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.diag"));
         }
      }

      ItemIsOpenResVo itemIsOpenResVo = new ItemIsOpenResVo();
      itemIsOpenResVo.setResCode(resCode);
      itemIsOpenResVo.setResMsg(resMsg.toString());
      return itemIsOpenResVo;
   }

   public ItemListIsOpenResVo selectItemIsOpenList(String patientId, List orderSearchVoList, Date orderStartTime) throws Exception {
      ItemListIsOpenResVo itemListIsOpenResVo = null;
      boolean flag = true;
      String resCode = "1";
      StringBuffer resMsg = new StringBuffer();
      List<ItemIsOpenResVo> resList = new ArrayList(orderSearchVoList.size());
      VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(patientId);
      List<String> drugCodeList = (List)orderSearchVoList.stream().filter((t) -> t.getOrderClassCode().equals("01")).map((t) -> t.getCpNo()).collect(Collectors.toList());
      DrugAndClinSearchVo drugAndClinParam = new DrugAndClinSearchVo();
      drugAndClinParam.setItemCdList(drugCodeList);
      PageList<DrugAndClin> outHosItemList = CollectionUtils.isNotEmpty(drugCodeList) ? this.drugAndClinService.selectQueryCheckList(drugAndClinParam, 1, drugCodeList.size()) : null;
      List<DrugAndClin> drugList = (List<DrugAndClin>)(CollectionUtils.isNotEmpty(drugCodeList) ? outHosItemList.getList() : new ArrayList(1));
      Map<String, List<DrugAndClin>> drugMap = (Map)drugList.stream().collect(Collectors.groupingBy((t) -> t.getCpNo()));
      TdPaOrderItem outOrderParam = new TdPaOrderItem(patientId, "01");
      List<TdPaOrderItem> outOrderList = this.tdPaOrderItemMapper.selectPatientOutOrder(outOrderParam, (List)null);
      if (flag && outOrderList != null && !outOrderList.isEmpty()) {
         flag = false;
         resCode = "0";
         resMsg.append(MessageUtils.message("doctor.order.item.canOpen.outHos"));
      }

      if (flag) {
         List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(patientId, "02", "01");
         if (diagInfoList == null || diagInfoList != null && diagInfoList.isEmpty()) {
            flag = false;
            resCode = "0";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.diag"));
         }
      }

      List<String> noOpenGroupNo = new ArrayList(1);
      if (flag) {
         Map<String, ItemIsOpenResVo> itemIsOpenResVoMap = null;
         List<String> cpNoList = (List)orderSearchVoList.stream().filter((t) -> t.getOrderClassCode().equals("01")).map((t) -> t.getCpNo()).distinct().collect(Collectors.toList());
         List<ItemIsOpenResVo> itemIsOpenResVoList = new ArrayList(1);
         if (CollectionUtils.isNotEmpty(cpNoList)) {
            for(String cpNo : cpNoList) {
               ItemIsOpenResVo itemIsOpenResVo = StringUtils.isNotBlank(cpNo) ? this.drugStockService.antiUseAimIsOpen(SecurityUtils.getLoginUser().getUser(), cpNo) : null;
               if (itemIsOpenResVo != null) {
                  itemIsOpenResVoList.add(itemIsOpenResVo);
               }
            }
         }

         Map var32 = itemIsOpenResVoList != null && !itemIsOpenResVoList.isEmpty() ? (Map)itemIsOpenResVoList.stream().collect(Collectors.toMap((t) -> t.getCpNo(), Function.identity())) : new HashMap(1);
         List<DrAntiApply> drAntiApplyListAll = (List<DrAntiApply>)(cpNoList != null && !cpNoList.isEmpty() ? this.drAntiApplyService.selectByPatientAndDrugCodes(patientId, cpNoList) : new ArrayList(1));

         for(OrderSearchVo orderSearchVo : orderSearchVoList) {
            ItemIsOpenResVo itemIsOpenResVo = new ItemIsOpenResVo();
            itemIsOpenResVo.setResCode("1");
            String orderGroupNoStr = "组号：" + orderSearchVo.getOrderGroupNo() + ":";
            String documentTypeNo = orderSearchVo.getDocumentTypeNo();
            String cpNo = orderSearchVo.getCpNo();
            if (!itemIsOpenResVo.getResCode().equals("0") && orderSearchVo.getOrderClassCode().equals("01")) {
               this.verifyDrAntiApply((Map)var32, drAntiApplyListAll, itemIsOpenResVo, cpNo, orderGroupNoStr, orderStartTime);
            }

            if (!itemIsOpenResVo.getResCode().equals("0")) {
               this.examAndTestIsOpen(itemIsOpenResVo, documentTypeNo, orderGroupNoStr);
            }

            if (!itemIsOpenResVo.getResCode().equals("0") && orderSearchVo.getOrderClassCode().equals("01")) {
               List<DrugAndClin> drugAndClinList = (List)drugMap.get(orderSearchVo.getCpNo());
               if (CollectionUtils.isNotEmpty(drugAndClinList)) {
                  this.verifySkinTest(orderSearchVo.getCpNo(), visitinfo, itemIsOpenResVo, ((DrugAndClin)drugAndClinList.get(0)).getPsfl());
               }
            }

            if (!itemIsOpenResVo.getResCode().equals("0")) {
               this.verifyHighRiskFlag(itemIsOpenResVo, orderSearchVo.getHighRiskFlag(), orderGroupNoStr, orderSearchVo.getCpName());
            }

            if (StringUtils.isNotBlank(itemIsOpenResVo.getDrugLevelCd())) {
               switch (itemIsOpenResVo.getDrugLevelCd()) {
                  case "精神药品":
                     itemIsOpenResVo.setRecipType("5");
                     break;
                  case "精神药品2":
                     itemIsOpenResVo.setRecipType("6");
                     break;
                  case "麻醉药品":
                     itemIsOpenResVo.setRecipType("4");
               }
            }

            itemIsOpenResVo.setCpNo(cpNo);
            itemIsOpenResVo.setOrderGroupNo(Integer.parseInt(orderSearchVo.getOrderGroupNo()));
            itemIsOpenResVo.setOrderGroupSortNumber(orderSearchVo.getOrderGroupSortNumber());
            resList.add(itemIsOpenResVo);
            if (itemIsOpenResVo.getResCode().equals("0")) {
               noOpenGroupNo.add(orderSearchVo.getOrderGroupNo());
            }
         }
      }

      itemListIsOpenResVo = new ItemListIsOpenResVo();
      if (flag) {
         List<String> groupNo = (List)orderSearchVoList.stream().map((t) -> t.getOrderGroupNo()).distinct().collect(Collectors.toList());
         itemListIsOpenResVo.setResCode("1");
         if (noOpenGroupNo.size() == groupNo.size()) {
            itemListIsOpenResVo.setResCode("0");
         }

         itemListIsOpenResVo.setNoOpenGroupNo(noOpenGroupNo);
         List<ItemIsOpenResVo> msgList = (List)resList.stream().filter((t) -> StringUtils.isNotBlank(t.getMsgFlag()) && t.getMsgFlag().equals("1")).collect(Collectors.toList());
         if (msgList != null && !msgList.isEmpty()) {
            List<String> msgStrList = (List)msgList.stream().map((t) -> t.getResMsg()).collect(Collectors.toList());
            String msgListStr = String.join(",", msgStrList);
            resMsg.append(msgListStr);
            itemListIsOpenResVo.setMsgFlag("1");
            itemListIsOpenResVo.setResMsg(resMsg.toString());
         }

         List<ItemIsOpenResVo> drugClassCodeList = (List)resList.stream().filter((t) -> StringUtils.isNotBlank(t.getDrugClassCode())).collect(Collectors.toList());
         itemListIsOpenResVo.setItemIsOpenResVoList(drugClassCodeList);
      } else {
         itemListIsOpenResVo.setResCode(resCode);
         itemListIsOpenResVo.setMsgFlag("1");
         itemListIsOpenResVo.setResMsg(resMsg.toString());
      }

      return itemListIsOpenResVo;
   }

   private void verifyDrAntiApply(Map itemIsOpenResVoMap, List drAntiApplyListAll, ItemIsOpenResVo itemIsOpenResVo, String cpNo, String msgPreStr, Date orderStartTime) throws Exception {
      String resCode = "1";
      String msgFlag = "0";
      String windowFlag = "0";
      String windowCode = null;
      String drugClassCode = null;
      String tempOpenFlag = null;
      String drugGradeCd = null;
      String drugGradeName = null;
      String drugLevelCd = null;
      StringBuffer resMsg = new StringBuffer();
      ItemIsOpenResVo temp = (ItemIsOpenResVo)itemIsOpenResVoMap.get(cpNo);
      if (temp != null) {
         resCode = temp.getResCode();
         msgFlag = temp.getMsgFlag();
         drugGradeCd = temp.getDrugGradeCd();
         resMsg.append(StringUtils.isNotBlank(temp.getResMsg()) ? msgPreStr + temp.getResMsg() : "");
         if (!temp.getResCode().equals("0")) {
            drugClassCode = temp.getDrugClassCode();
            drugLevelCd = temp.getDrugLevelCd();
            if (temp.getDrugClassCode().equals("01") && StringUtils.isNotBlank(temp.getWindowFlag()) && temp.getWindowFlag().equals("1")) {
               List<DrAntiApply> drAntiApplyList = (List)drAntiApplyListAll.stream().filter((t) -> t.getDrugCode().equals(cpNo)).collect(Collectors.toList());
               if (drAntiApplyList != null && (drAntiApplyList == null || !drAntiApplyList.isEmpty())) {
                  List<DrAntiApply> drAntiApplyList2 = drAntiApplyList != null ? (List)drAntiApplyList.stream().filter((t) -> t.getState().equals("1")).collect(Collectors.toList()) : null;
                  List<DrAntiApply> drAntiApplyList1 = (List)drAntiApplyList.stream().filter((t) -> t.getState().equals("0")).collect(Collectors.toList());
                  if (CollectionUtils.isEmpty(drAntiApplyList2) && CollectionUtils.isNotEmpty(drAntiApplyList1)) {
                     drAntiApplyList1 = (List)drAntiApplyList1.stream().sorted(Comparator.comparing((t) -> t.getApplyDate())).collect(Collectors.toList());
                     String beginTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, ((DrAntiApply)drAntiApplyList1.get(0)).getApplyDate());
                     String endTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, DateUtils.addHours(((DrAntiApply)drAntiApplyList1.get(0)).getApplyDate(), 24));
                     List<TdPaOrderItem> orderItemList = this.selectPatOrderForDrugCodes(((DrAntiApply)drAntiApplyList1.get(0)).getPatientId(), beginTime, endTime, cpNo);

                     for(DrAntiApply drAntiApply : drAntiApplyList1) {
                        Date applyDate = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, drAntiApply.getApplyDate()));
                        Date endDate = DateUtils.addHours(applyDate, 24);
                        List<TdPaOrderItem> orderItemList1 = (List)orderItemList.stream().filter((t) -> t.getOrderStartTime().compareTo(applyDate) >= 0 && t.getOrderStartTime().compareTo(endDate) < 0).collect(Collectors.toList());
                        if (CollectionUtils.isEmpty(orderItemList1) && orderStartTime.compareTo(applyDate) >= 0 && orderStartTime.compareTo(endDate) < 0) {
                           tempOpenFlag = "1";
                           break;
                        }
                     }

                     if (StringUtils.isBlank(tempOpenFlag) || StringUtils.isNotBlank(tempOpenFlag) && tempOpenFlag.equals("0")) {
                        windowFlag = temp.getWindowFlag();
                        windowCode = temp.getWindowCode();
                        drugGradeName = temp.getDrugGradeName();
                     }
                  }
               } else {
                  windowFlag = temp.getWindowFlag();
                  windowCode = temp.getWindowCode();
                  drugGradeName = temp.getDrugGradeName();
                  tempOpenFlag = "1";
               }
            }
         } else {
            drugClassCode = temp.getDrugClassCode();
            drugLevelCd = temp.getDrugLevelCd();
         }
      }

      itemIsOpenResVo.setResCode(resCode);
      itemIsOpenResVo.setMsgFlag(msgFlag);
      itemIsOpenResVo.setResMsg(resMsg.toString());
      itemIsOpenResVo.setWindowFlag(windowFlag);
      itemIsOpenResVo.setWindowCode(windowCode);
      itemIsOpenResVo.setDrugClassCode(drugClassCode);
      itemIsOpenResVo.setTempOpenFlag(tempOpenFlag);
      itemIsOpenResVo.setDrugGradeCd(drugGradeCd);
      itemIsOpenResVo.setDrugGradeName(drugGradeName);
      itemIsOpenResVo.setDrugLevelCd(drugLevelCd);
   }

   private void examAndTestIsOpen(ItemIsOpenResVo itemIsOpenResVo, String documentTypeNo, String msgPreStr) {
      String resCode = "1";
      String msgFlag = "0";
      StringBuffer resMsg = new StringBuffer();
      if (StringUtils.isNotBlank(documentTypeNo)) {
         DocumentType documentType = this.documentTypeService.selectDocumentTypeById(documentTypeNo);
         if (documentType != null && StringUtils.isNotBlank(documentType.getOrderFlag()) && documentType.getOrderFlag().equals("0")) {
            resCode = "0";
            msgFlag = "1";
            resMsg.append(msgPreStr + MessageUtils.message("doctor.order.item.canOpen.examtest"));
            itemIsOpenResVo.setResCode(resCode);
            itemIsOpenResVo.setMsgFlag(msgFlag);
            itemIsOpenResVo.setResMsg(resMsg.toString());
         }
      }

   }

   private void verifySkinTest(String cpNo, VisitinfoVo visitinfo, ItemIsOpenResVo itemIsOpenResVo, String psfl) throws Exception {
      String resCode = "1";
      String msgFlag = "0";
      StringBuffer resMsg = new StringBuffer();
      if (StringUtils.isNotBlank(psfl)) {
         List<TdNaAllergyRecord> allergyRecordList = this.alleInfoService.selectByInpNoAndDrugCode(visitinfo.getInpNo(), cpNo);
         boolean skinTestFlag = true;
         if (allergyRecordList != null && !allergyRecordList.isEmpty()) {
            List<TdNaAllergyRecord> allergyRecordList2 = (List)allergyRecordList.stream().filter((t) -> !t.getAllergyType().equals("(-)")).collect(Collectors.toList());
            skinTestFlag = allergyRecordList2 == null || allergyRecordList2.isEmpty();
         }

         if (skinTestFlag) {
            Date before72h = DateUtils.addHours(this.commonService.getDbSysdate(), -72);
            List<TdNaAllergyRecord> allergyRecordList2 = (List)allergyRecordList.stream().filter((t) -> t.getAllergyType().equals("(-)") && t.getOperatorDate().compareTo(before72h) >= 0).collect(Collectors.toList());
            List<TdPaOrderItem> tdPaOrderItem72hList = this.tdPaOrderItemMapper.selectByPatientIdAndDrugCode72hExe(visitinfo.getPatientId(), cpNo);
            if (CollectionUtils.isEmpty(allergyRecordList2) && CollectionUtils.isEmpty(tdPaOrderItem72hList)) {
               msgFlag = "1";
               resMsg.append(MessageUtils.message("doctor.order.item.canOpen.skinTest"));
               itemIsOpenResVo.setMsgFlag(msgFlag);
               itemIsOpenResVo.setResMsg(resMsg.toString());
            }
         } else {
            resCode = "0";
            msgFlag = "1";
            resMsg.append(MessageUtils.message("doctor.order.item.canOpen.skinTest.no"));
            itemIsOpenResVo.setResCode(resCode);
            itemIsOpenResVo.setMsgFlag(msgFlag);
            itemIsOpenResVo.setResMsg(resMsg.toString());
         }
      }

   }

   private void verifyHighRiskFlag(ItemIsOpenResVo itemIsOpenResVo, String highRiskFlag, String msgPreStr, String cpName) {
      if (StringUtils.isNotBlank(highRiskFlag) && highRiskFlag.equals("1")) {
         itemIsOpenResVo.setMsgFlag("1");
         itemIsOpenResVo.setResMsg(msgPreStr + MessageUtils.message("doctor.order.item.canOpen.highRisk", cpName));
      }

   }

   public List selectItemStatusFlow(String orderNo) throws Exception {
      List<TdPaOrderStatus> targetStatusList = new ArrayList(1);
      List<TdPaOrderStatus> statusList = this.tdPaOrderStatusService.selectTdPaOrderStatusById(orderNo);
      Map<String, List<TdPaOrderStatus>> statusListMap = (Map)statusList.stream().collect(Collectors.groupingBy((t) -> t.getOperationType()));
      this.getItemStatus(statusListMap, "-1", targetStatusList, "1");
      this.getItemStatus(statusListMap, "0", targetStatusList, "1");
      this.getItemStatus(statusListMap, "1", targetStatusList, "1");
      this.getItemStatus(statusListMap, "2", targetStatusList, "1");
      this.getItemStatus(statusListMap, "3", targetStatusList, "0");
      this.getItemStatus(statusListMap, "4", targetStatusList, "1");
      this.getItemStatus(statusListMap, "5", targetStatusList, "1");
      this.getItemStatus(statusListMap, "6", targetStatusList, "1");
      this.getItemStatus(statusListMap, "7", targetStatusList, "1");
      this.getItemStatus(statusListMap, "8", targetStatusList, "1");
      this.getItemStatus(statusListMap, "10", targetStatusList, "1");
      this.getItemStatus(statusListMap, "11", targetStatusList, "1");
      return targetStatusList;
   }

   private void getItemStatus(Map statusListMap, String orderStatus, List targetStatusList, String sortFlag) {
      List<TdPaOrderStatus> statusList = (List)statusListMap.get(orderStatus);
      if (statusList != null && !statusList.isEmpty()) {
         if (statusList.size() > 1) {
            if (sortFlag.equals("1")) {
               statusList.sort(Comparator.comparing(TdPaOrderStatus::getOperationTime).reversed());
            } else {
               statusList.sort(Comparator.comparing(TdPaOrderStatus::getOperationTime));
            }
         }

         TdPaOrderStatus status = (TdPaOrderStatus)statusList.get(0);
         targetStatusList.add(status);
      }

   }

   public void insertList(List list) throws Exception {
      this.tdPaOrderItemMapper.insertList(list);
   }

   public Integer selectPatientMaxGroupNo(String patientId) throws Exception {
      Integer orderGroupNoMax = this.tdPaOrderItemMapper.selectPatientMaxGroupNo(patientId);
      int orderGroupNo = orderGroupNoMax != null ? orderGroupNoMax : 0;
      return orderGroupNo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List insertOrderAndCheckList(VisitinfoPersonalVo personalVo, List applyList, List applyItemList, List applyDetailtList, List clinList, Map applyFormDetailMap, OrderCommitVo orderCommitVo) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      Integer orderGroupNoMax = this.selectPatientMaxGroupNo(personalVo.getPatientId());
      Integer reOrganizationNoMax = this.tdPaOrderDetailService.getMaxReOrganizationNo(personalVo.getPatientId());
      Map<String, List<TdPaApplyFormItem>> applyItemMap = (Map)applyItemList.stream().collect(Collectors.groupingBy((t) -> t.getApplyFormNo()));
      Map<String, List<TdPaApplyFormDetail>> applyDetailtMap = (Map)applyDetailtList.stream().collect(Collectors.groupingBy((t) -> t.getApplyFormNo() + t.getItemSortNumber()));
      Map<String, List<DrugAndClin>> clinListMap = (Map)clinList.stream().collect(Collectors.groupingBy((t) -> t.getCpNo()));
      List<TdPaOrder> orderList = new ArrayList(1);
      List<TdPaOrderItem> orderItemList = new ArrayList(1);
      List<TdPaOrderDetail> orderDetailList = new ArrayList(1);

      for(TdPaApplyFormVo applyFormVo : applyList) {
         String orderNo = DateUtils.getDateStr("");
         String applyFormNo = applyFormVo.getApplyFormNo();
         List<TdPaApplyFormItem> applyFormItemListTemp = (List)applyItemMap.get(applyFormNo);
         applyFormVo.setOrderNo(orderNo);
         TdPaOrder order = this.setApplyFormOrder(applyFormVo, personalVo, orderNo, currDate, orderCommitVo.getOperatingRoomFlag());
         orderList.add(order);
         int orderSortNumber = 0;

         for(TdPaApplyFormItem applyFormItem : applyFormItemListTemp) {
            ++orderSortNumber;
            orderGroupNoMax = orderGroupNoMax + 1;
            DrugAndClin clinItem = clinListMap.get(applyFormItem.getItemCode()) != null ? (DrugAndClin)((List)clinListMap.get(applyFormItem.getItemCode())).get(0) : null;
            applyFormItem.setOrderGroupNo(orderGroupNoMax);
            applyFormItem.setItemSortNumber(OrderUtil.getNumStr(orderSortNumber));
            TdPaOrderItem orderItem = this.setApplyFormOrderItem(applyFormVo, order, orderSortNumber, orderGroupNoMax, applyFormItem, clinItem, personalVo, reOrganizationNoMax);
            orderItemList.add(orderItem);
            List<TdPaApplyFormDetail> applyFormDetailListTemp = (List)applyDetailtMap.get(applyFormNo + applyFormItem.getItemSortNumber());
            int orderGroupSortNumber = 1;

            for(TdPaApplyFormDetail applyFormDetail : applyFormDetailListTemp) {
               TdPaOrderDetail orderDetail = this.setApplyFormOrderDetail(applyFormDetail, applyFormItem, order, orderItem, orderGroupSortNumber, reOrganizationNoMax);
               applyFormDetail.setItemSortNumber(OrderUtil.getNumStr(orderSortNumber));
               orderDetailList.add(orderDetail);
               ++orderGroupSortNumber;
            }
         }
      }

      if (orderList != null && !orderList.isEmpty()) {
         this.tdPaOrderService.insertList(orderList);
      }

      if (orderItemList != null && !orderItemList.isEmpty()) {
         this.insertList(orderItemList);
      }

      if (orderDetailList != null && !orderDetailList.isEmpty()) {
         this.tdPaOrderDetailService.insertList(orderDetailList);
      }

      currDate.setTime(currDate.getTime() - 1000L);
      this.tdPaOrderStatusService.addTdPaOrderStatus(personalVo, orderItemList, -1, "保存", currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(personalVo, orderItemList, -1, "保存", currDate, (String)null);
      this.tdMsgSendMainService.addMsgs(orderList, personalVo, 0, "提交");
      return applyList;
   }

   private TdPaOrder setApplyFormOrder(TdPaApplyFormVo applyFormVo, VisitinfoPersonalVo personalVo, String orderNo, Date currDate, String operatingRoomFlag) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      TdPaOrder order = new TdPaOrder();
      BeanUtils.copyProperties(applyFormVo, order);
      order.setOrderNo(orderNo);
      order.setOrderType("2");
      order.setOrderClassCode(applyFormVo.getApplyFormClassCode());
      order.setPatientWardNo(personalVo.getAreaCode());
      order.setOrderDoctorNo(applyFormVo.getOrderDoctorCode());
      order.setOrderDoctorCode(applyFormVo.getOrderDoctorCode());
      order.setOrderDoctorName(applyFormVo.getOrderDoctorName());
      order.setOrderDoctorDepCode(user.getDept().getDeptCode());
      order.setOrderDoctorDepName(user.getDept().getDeptName());
      order.setBabyAdmissionNo(applyFormVo.getBabyNo());
      order.setResidentDoctorNo(personalVo.getResDocCd());
      order.setResidentDoctorName(personalVo.getResDocName());
      order.setRespNurseNo(personalVo.getDutyNurCd());
      order.setRespNurseName(personalVo.getDutyNurName());
      order.setPerformWardNo(user.getDept().getDeptCode());
      order.setPerformDepCode(user.getDept().getDeptCode());
      order.setPerformDepName(user.getDept().getDeptName());
      order.setOrderPerformFlag("0");
      if (StringUtils.isNotBlank(operatingRoomFlag) && operatingRoomFlag.equals("1")) {
         order.setOrderPerformFlag("3");
      }

      order.setOperatorCode(basEmployee.getEmplNumber());
      order.setOperatorNo(basEmployee.getEmplNumber());
      order.setOperatorName(basEmployee.getEmplName());
      order.setOperationTime(currDate);
      order.setApplyFormNo(applyFormVo.getApplyFormNo());
      return order;
   }

   private TdPaOrderItem setApplyFormOrderItem(TdPaApplyFormVo applyFormVo, TdPaOrder order, int orderSortNumber, int orderGroupNoMax, TdPaApplyFormItem applyFormItem, DrugAndClin clinItem, VisitinfoPersonalVo personalVo, Integer reOrganizationNoMax) {
      TdPaOrderItem orderItem = new TdPaOrderItem();
      orderItem.setOrderNo(order.getOrderNo());
      orderItem.setOrderType(order.getOrderType());
      orderItem.setOrderClassCode(order.getOrderClassCode());
      orderItem.setOrderSortNumber(OrderUtil.getNumStr(orderSortNumber));
      orderItem.setOrderGroupNo(orderGroupNoMax);
      orderItem.setCpNo(applyFormItem.getItemCode());
      orderItem.setCpName(applyFormItem.getItemName());
      orderItem.setOrderStartTime(applyFormVo.getApplyTime());
      orderItem.setOrderStartDoc(applyFormVo.getOrderDoctorCode());
      orderItem.setOrderStartDocName(applyFormVo.getOrderDoctorName());
      orderItem.setOrderDoctorSignFlag("0");
      orderItem.setPriceFlag(clinItem.getPriceFlag());
      orderItem.setDocumentTypeNo(clinItem.getDocumentTypeNo());
      orderItem.setOrderItemType(clinItem.getOrderItemType());
      orderItem.setPerformStaffFlag("1");
      orderItem.setSubmitDoctorCode(applyFormVo.getOrderDoctorCode());
      orderItem.setSubmitDoctorNo(applyFormVo.getOrderDoctorCode());
      orderItem.setSubmitDoctorName(applyFormVo.getOrderDoctorName());
      orderItem.setSubmitTime(applyFormVo.getApplyTime());
      orderItem.setOrderDoseItem(applyFormItem.getDose());
      orderItem.setUnitItem(applyFormItem.getUnit());
      orderItem.setPriceItem(applyFormItem.getPrice());
      if (orderItem.getPriceItem() != null && orderItem.getOrderDoseItem() != null) {
         BigDecimal orderTotalItem = orderItem.getPriceItem().multiply(orderItem.getOrderDoseItem()).setScale(3, 1);
         orderItem.setOrderTotalItem(orderTotalItem);
      }

      orderItem.setExecutorDptNo(applyFormVo.getPerformDepCode());
      orderItem.setOrderStatus("-1");
      orderItem.setOrderFlagCd(StringUtils.isNotEmpty(clinItem.getItemFlagCd()) ? clinItem.getItemFlagCd() : "00");
      orderItem.setUrgentFlag(applyFormVo.getUrgentFlag());
      orderItem.setHerbalFlag("0");
      orderItem.setOederDesc(applyFormVo.getRemark());
      orderItem.setPatientId(personalVo.getPatientId());
      orderItem.setReOrganizationNo(reOrganizationNoMax);
      orderItem.setOrderItemType("1");
      return orderItem;
   }

   private TdPaOrderDetail setApplyFormOrderDetail(TdPaApplyFormDetail applyFormDetail, TdPaApplyFormItem applyFormItem, TdPaOrder order, TdPaOrderItem orderItem, int orderGroupSortNumber, int reOrganizationNoMax) {
      TdPaOrderDetail orderDetail = new TdPaOrderDetail();
      BeanUtils.copyProperties(orderItem, orderDetail);
      orderDetail.setId(SnowIdUtils.uniqueLong());
      orderDetail.setOrderGroupSortNumber(OrderUtil.getNumStr(orderGroupSortNumber));
      orderDetail.setChargeNo(applyFormDetail.getChargeNo());
      orderDetail.setChargeName(applyFormDetail.getChargeName());
      orderDetail.setStandardCode(applyFormDetail.getStandardCode());
      orderDetail.setStandardName(applyFormDetail.getStandardName());
      orderDetail.setStandard(applyFormDetail.getStandard());
      orderDetail.setUnit(applyFormDetail.getUnit());
      orderDetail.setOrderActualUsage(new BigDecimal(applyFormDetail.getDose()));
      orderDetail.setUsageUnit(applyFormDetail.getUnit());
      orderDetail.setOrderDose(new BigDecimal(applyFormDetail.getDose()));
      orderDetail.setOrderTotalDose(new BigDecimal(applyFormDetail.getDose()));
      orderDetail.setPrice(applyFormDetail.getPrice());
      BigDecimal orderTotal = (new BigDecimal(applyFormDetail.getDose())).multiply(applyFormDetail.getPrice()).setScale(3, 1);
      orderDetail.setOrderTotal(orderTotal);
      orderDetail.setHerbalFlag("0");
      orderDetail.setSubjectFlag(applyFormDetail.getSubFlag());
      orderDetail.setDetailPerformDepCode(orderItem.getExecutorDptNo());
      orderDetail.setDoctorInstructions(applyFormDetail.getRemark());
      orderDetail.setBabyAdmissionNo(order.getBabyAdmissionNo());
      orderDetail.setCpNo(orderItem.getCpNo());
      orderDetail.setCpName(orderItem.getCpName());
      orderDetail.setReOrganizationNo(reOrganizationNoMax);
      orderDetail.setOrderItemFlag("1");
      orderDetail.setMasterOrder(orderGroupSortNumber == 1 ? "1" : "0");
      return orderDetail;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertOrderFromTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      Visitinfo visitinfo = this.visitinfoService.selectVisitinfoById(tdCaConsApplyVo.getPatientId());
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      Integer orderGroupNoMax = this.selectPatientMaxGroupNo(tdCaConsApplyVo.getPatientId());
      Integer orderGroupNo = orderGroupNoMax + 1;
      Integer reOrganizationNoMax = this.tdPaOrderDetailService.getMaxReOrganizationNo(tdCaConsApplyVo.getPatientId());
      String orderNo = DateUtils.getDateStr("");
      TdPaOrder order = new TdPaOrder();
      order.setHospitalCode(user.getHospital().getOrgCode());
      order.setOrderNo(orderNo);
      order.setOrderType("2");
      order.setOrderClassCode("07");
      order.setPatientId(tdCaConsApplyVo.getPatientId());
      order.setCaseNo(visitinfo.getRecordNo());
      order.setAdmissionNo(visitinfo.getInpNo());
      order.setHospitalizedCount(visitinfo.getVisitId());
      order.setPatientDepCode(tdCaConsApplyVo.getApplyDeptCd());
      order.setPatientDepName(tdCaConsApplyVo.getApplyDeptName());
      order.setOrderDoctorCode(tdCaConsApplyVo.getApplyDocCd());
      order.setOrderDoctorNo(tdCaConsApplyVo.getApplyDocCd());
      order.setOrderDoctorName(tdCaConsApplyVo.getApplyDocName());
      order.setOrderDoctorDepCode(tdCaConsApplyVo.getApplyDeptCd());
      order.setOrderDoctorDepName(tdCaConsApplyVo.getApplyDeptName());
      order.setResidentDoctorNo(visitinfo.getResDocCd());
      order.setResidentDoctorName(visitinfo.getResDocName());
      order.setRespNurseNo(visitinfo.getDutyNurCd());
      order.setRespNurseName(visitinfo.getDutyNurName());
      order.setPerformWardNo(user.getDept().getDeptCode());
      order.setPerformDepCode(user.getDept().getDeptCode());
      order.setPerformDepName(user.getDept().getDeptName());
      order.setOrderPerformFlag("0");
      String moduleCode = null;
      if (user.getModule() != null) {
         moduleCode = user.getModule().getModuleCode();
      }

      if ("0403".equals(moduleCode)) {
         order.setOrderPerformFlag("3");
      }

      order.setOperatorNo(basEmployee.getEmplNumber());
      order.setOperatorName(basEmployee.getEmplName());
      order.setOperationTime(currDate);
      order.setApplyFormNo(tdCaConsApplyVo.getId().toString());
      TdPaOrderItem orderItem = new TdPaOrderItem();
      orderItem.setOrderNo(orderNo);
      orderItem.setOrderType(order.getOrderType());
      orderItem.setOrderClassCode(order.getOrderClassCode());
      orderItem.setOrderSortNumber("01");
      orderItem.setOrderGroupNo(orderGroupNo);
      orderItem.setCpNo("NNNNNN");
      orderItem.setCpName(MessageUtils.message("doctor.order.consApply.cpName", tdCaConsApplyVo.getConsDeptName()));
      orderItem.setOrderStartTime(currDate);
      orderItem.setOrderStartDoc(basEmployee.getEmplNumber());
      orderItem.setOrderStartDocName(basEmployee.getEmplName());
      orderItem.setOrderDoctorSignFlag("0");
      orderItem.setPriceFlag("0");
      orderItem.setPerformStaffFlag("1");
      orderItem.setOrderFlagCd("05");
      orderItem.setOrderFlagName("自由录入医嘱");
      orderItem.setPatientId(tdCaConsApplyVo.getPatientId());
      orderItem.setReOrganizationNo(reOrganizationNoMax);
      orderItem.setHerbalFlag("0");
      orderItem.setExecutorDptNo(tdCaConsApplyVo.getConsDeptCd());
      orderItem.setOrderStatus("0");
      orderItem.setOrderItemType("1");
      orderItem.setSubmitDoctorNo(basEmployee.getEmplNumber());
      orderItem.setSubmitDoctorCode(basEmployee.getEmplNumber());
      orderItem.setSubmitDoctorName(basEmployee.getEmplName());
      orderItem.setSubmitTime(currDate);
      TdPaOrderDetail orderDetail = new TdPaOrderDetail();
      orderDetail.setId(SnowIdUtils.uniqueLong());
      orderDetail.setOrderNo(orderNo);
      orderDetail.setOrderType(orderItem.getOrderType());
      orderDetail.setOrderSortNumber(orderItem.getOrderSortNumber());
      orderDetail.setOrderGroupNo(orderItem.getOrderGroupNo());
      orderDetail.setOrderGroupSortNumber("01");
      orderDetail.setOrderClassCode(orderItem.getOrderClassCode());
      orderDetail.setChargeNo(orderItem.getCpNo());
      orderDetail.setChargeName(orderItem.getCpName());
      orderDetail.setStandardCode(orderItem.getCpNo());
      orderDetail.setStandardName(orderItem.getCpName());
      orderDetail.setDetailPerformDepCode(tdCaConsApplyVo.getApplyDeptCd());
      orderDetail.setReOrganizationNo(reOrganizationNoMax);
      orderDetail.setOrderItemFlag("5");
      orderDetail.setMasterOrder("1");
      orderDetail.setHerbalFlag("0");
      orderDetail.setDetailPerformDepCode(tdCaConsApplyVo.getConsDeptCd());
      this.tdPaOrderService.insertTdPaOrder(order);
      this.tdPaOrderItemMapper.insertTdPaOrderItem(orderItem);
      this.tdPaOrderDetailService.insertTdPaOrderDetail(orderDetail);
      tdCaConsApplyVo.setOrderNo(orderNo);
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, Arrays.asList(orderItem), 0, "提交", currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, Arrays.asList(orderItem), 0, "提交", currDate, (String)null);
      this.tdMsgSendMainService.addMsgs(Arrays.asList(order), visitinfo, 0, "提交");
   }

   public void insertOrderFromTdCaOperationApply(TdCaOperationApplyVo tdCaOperationApply, OrderCommitVo orderCommitVo) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      Visitinfo visitinfo = this.visitinfoService.selectVisitinfoById(tdCaOperationApply.getPatientId());
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      Integer orderGroupNoMax = this.selectPatientMaxGroupNo(tdCaOperationApply.getPatientId());
      Integer orderGroupNo = orderGroupNoMax + 1;
      Integer reOrganizationNoMax = this.tdPaOrderDetailService.getMaxReOrganizationNo(tdCaOperationApply.getPatientId());
      String orderNo = DateUtils.getDateStr("");
      tdCaOperationApply.setOrderNo(orderNo);
      TdPaOrder order = new TdPaOrder();
      order.setHospitalCode(user.getHospital().getOrgCode());
      order.setOrderNo(orderNo);
      order.setOrderType("2");
      order.setOrderClassCode("04");
      order.setPatientId(tdCaOperationApply.getPatientId());
      order.setCaseNo(visitinfo.getRecordNo());
      order.setAdmissionNo(visitinfo.getInpNo());
      order.setHospitalizedCount(visitinfo.getVisitId());
      order.setPatientDepCode(tdCaOperationApply.getApplyDeptCd());
      order.setPatientDepName(tdCaOperationApply.getApplyDeptName());
      order.setOrderDoctorCode(tdCaOperationApply.getApplyDocCd());
      order.setOrderDoctorNo(tdCaOperationApply.getApplyDocCd());
      order.setOrderDoctorName(tdCaOperationApply.getApplyDocName());
      order.setOrderDoctorDepCode(tdCaOperationApply.getApplyDeptCd());
      order.setOrderDoctorDepName(tdCaOperationApply.getApplyDeptName());
      order.setResidentDoctorNo(visitinfo.getResDocCd());
      order.setResidentDoctorName(visitinfo.getResDocName());
      order.setRespNurseNo(visitinfo.getDutyNurCd());
      order.setRespNurseName(visitinfo.getDutyNurName());
      order.setPerformWardNo(user.getDept().getDeptCode());
      order.setPerformDepCode(user.getDept().getDeptCode());
      order.setPerformDepName(user.getDept().getDeptName());
      order.setOrderPerformFlag("0");
      String operatingRoomFlag = tdCaOperationApply.getOperatingRoomFlag();
      if (StringUtils.isNotBlank(operatingRoomFlag) && operatingRoomFlag.equals("1")) {
         order.setOrderPerformFlag("3");
      }

      order.setOperatorNo(basEmployee.getEmplNumber());
      order.setOperatorName(basEmployee.getEmplName());
      order.setOperationTime(currDate);
      order.setApplyFormNo(tdCaOperationApply.getApplyFormNo());
      TdPaOrderItem orderItem = new TdPaOrderItem();
      orderItem.setOrderNo(orderNo);
      orderItem.setOrderType(order.getOrderType());
      orderItem.setOrderClassCode(order.getOrderClassCode());
      orderItem.setOrderSortNumber("01");
      orderItem.setOrderGroupNo(orderGroupNo);
      orderItem.setCpNo("NNNNNN");
      String planOper1Name = (StringUtils.isNotBlank(tdCaOperationApply.getPlanOper1Prefix()) ? tdCaOperationApply.getPlanOper1Prefix() : "") + tdCaOperationApply.getPlanOper1Name() + (StringUtils.isNotBlank(tdCaOperationApply.getPlanOper1Suffix()) ? tdCaOperationApply.getPlanOper1Suffix() : "");
      String planOper2Name = (StringUtils.isNotBlank(tdCaOperationApply.getPlanOper2Prefix()) ? tdCaOperationApply.getPlanOper2Prefix() : "") + tdCaOperationApply.getPlanOper2Name() + (StringUtils.isNotBlank(tdCaOperationApply.getPlanOper2Suffix()) ? tdCaOperationApply.getPlanOper2Suffix() : "");
      String planOper3Name = (StringUtils.isNotBlank(tdCaOperationApply.getPlanOper3Prefix()) ? tdCaOperationApply.getPlanOper3Prefix() : "") + tdCaOperationApply.getPlanOper3Name() + (StringUtils.isNotBlank(tdCaOperationApply.getPlanOper3Suffix()) ? tdCaOperationApply.getPlanOper3Suffix() : "");
      String planOper4Name = (StringUtils.isNotBlank(tdCaOperationApply.getPlanOper4Prefix()) ? tdCaOperationApply.getPlanOper4Prefix() : "") + tdCaOperationApply.getPlanOper4Name() + (StringUtils.isNotBlank(tdCaOperationApply.getPlanOper4Suffix()) ? tdCaOperationApply.getPlanOper4Suffix() : "");
      orderItem.setCpName(MessageUtils.message("doctor.order.operationApply.cpName", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, tdCaOperationApply.getPlanOperTime()), tdCaOperationApply.getAnestMethName(), planOper1Name + "," + planOper2Name + "," + planOper3Name + "," + planOper4Name));
      orderItem.setOrderStartTime(currDate);
      orderItem.setOrderStartDoc(basEmployee.getEmplNumber());
      orderItem.setOrderStartDocName(basEmployee.getEmplName());
      orderItem.setOrderDoctorSignFlag("0");
      orderItem.setPriceFlag("0");
      orderItem.setPerformStaffFlag("1");
      orderItem.setPatientId(tdCaOperationApply.getPatientId());
      orderItem.setHerbalFlag("0");
      orderItem.setReOrganizationNo(reOrganizationNoMax);
      orderItem.setExecutorDptNo(tdCaOperationApply.getExecDeptCd());
      orderItem.setOrderFlagCd("05");
      orderItem.setOrderFlagName("自由录入医嘱");
      orderItem.setOrderStatus("-1");
      orderItem.setOrderItemType("1");
      TdPaOrderDetail orderDetail = new TdPaOrderDetail();
      orderDetail.setId(SnowIdUtils.uniqueLong());
      orderDetail.setOrderNo(orderNo);
      orderDetail.setOrderType(orderItem.getOrderType());
      orderDetail.setOrderSortNumber(orderItem.getOrderSortNumber());
      orderDetail.setOrderGroupNo(orderItem.getOrderGroupNo());
      orderDetail.setOrderGroupSortNumber("01");
      orderDetail.setOrderClassCode(orderItem.getOrderClassCode());
      orderDetail.setChargeNo(orderItem.getCpNo());
      orderDetail.setChargeName(orderItem.getCpName());
      orderDetail.setStandardCode(orderItem.getCpNo());
      orderDetail.setStandardName(orderItem.getCpName());
      orderDetail.setDetailPerformDepCode(tdCaOperationApply.getApplyDeptCd());
      orderDetail.setDoctorInstructions(tdCaOperationApply.getApplyDeptName());
      orderDetail.setReOrganizationNo(reOrganizationNoMax);
      orderDetail.setOrderItemFlag("5");
      orderDetail.setMasterOrder("1");
      orderDetail.setHerbalFlag("0");
      orderDetail.setDetailPerformDepCode(tdCaOperationApply.getExecDeptCd());
      this.tdPaOrderService.insertTdPaOrder(order);
      this.tdPaOrderItemMapper.insertTdPaOrderItem(orderItem);
      this.tdPaOrderDetailService.insertTdPaOrderDetail(orderDetail);
      int state = -1;
      String stateName = "保存";
      currDate.setTime(currDate.getTime() - 1000L);
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, Arrays.asList(orderItem), state, stateName, currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, Arrays.asList(orderItem), state, stateName, currDate, (String)null);
      EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
      emrTaskInfo.setBusSection(tdCaOperationApply.getApplyFormNo());
      List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
      if (emrTaskInfoList != null && !emrTaskInfoList.isEmpty()) {
         List<Long> mrFileIdList = (List)emrTaskInfoList.stream().map((s) -> s.getMrFileId()).collect(Collectors.toList());
         this.indexService.updateEmrOrderNoByIdList(tdCaOperationApply.getOrderNo(), mrFileIdList);
         this.subfileIndexService.updateEmrOrderNoByIdList(tdCaOperationApply.getOrderNo(), mrFileIdList);
      }

      this.tdMsgSendMainService.addMsgs(Arrays.asList(order), visitinfo, 0, "提交");
   }

   public void toDelTable(List list) throws Exception {
      if (list != null && !list.isEmpty()) {
         this.tdPaOrderItemMapper.toDelTable(list);
      }

   }

   public void deleteTdPaOrderItemByIds(List orderNoList) throws Exception {
      this.tdPaOrderItemMapper.deleteTdPaOrderItemByOrderNos(orderNoList);
   }

   public TdPaOrderItemVo selectOrderInfoByOrderNo(String orderNo) throws Exception {
      TdPaOrderItemVo result = this.selectTdPaOrderItemById(orderNo);
      result = result == null ? new TdPaOrderItemVo() : result;
      List<TdPaOrderDetailVo> detailList = this.tdPaOrderDetailService.selectByOrderNo(orderNo);
      if (detailList != null && !detailList.isEmpty()) {
         List<String> cpNoList = (List)detailList.stream().map((s) -> s.getChargeNo().trim()).collect(Collectors.toList());
         DrugAndClinSearchVo param = new DrugAndClinSearchVo();
         param.setOrderClassCode("01");
         param.setHerbalFlag("1");
         param.setPerformDepCode(((TdPaOrderDetailVo)detailList.get(0)).getDetailPerformDepCode());
         param.setItemCdList(cpNoList);
         List<DrugAndClin> list = this.tmPmCipherDetailService.selectCipAndGroupDetailList(param);
         if (list != null && !list.isEmpty()) {
            for(TdPaOrderDetailVo tdPaOrderDetailVo : detailList) {
               List<DrugAndClin> drugAndClins = (List)list.stream().filter((s) -> s.getCpNo().equals(tdPaOrderDetailVo.getChargeNo().trim())).collect(Collectors.toList());
               if (drugAndClins != null && !drugAndClins.isEmpty()) {
                  tdPaOrderDetailVo.setCpId(((DrugAndClin)drugAndClins.get(0)).getId());
                  int a = drugAndClins.stream().mapToInt((t) -> t.getXcsl().intValue()).sum();
                  int b = tdPaOrderDetailVo.getOrderTotalDose().intValue();
                  tdPaOrderDetailVo.setStockFakeNum(BigDecimal.valueOf((long)(a + b)));
                  tdPaOrderDetailVo.setStockNum(BigDecimal.valueOf((long)a));
               } else {
                  tdPaOrderDetailVo.setStockFakeNum(tdPaOrderDetailVo.getOrderTotalDose());
                  tdPaOrderDetailVo.setStockNum(BigDecimal.valueOf(0L));
               }
            }
         } else {
            for(TdPaOrderDetailVo tdPaOrderDetailVo : detailList) {
               tdPaOrderDetailVo.setStockFakeNum(tdPaOrderDetailVo.getOrderTotalDose());
               tdPaOrderDetailVo.setStockNum(BigDecimal.valueOf(0L));
            }
         }
      }

      result.setDetailList(detailList);
      return result;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List saveHerbOrder(HerbSaveVo herbSaveVo) throws Exception {
      String orderNo = DateUtils.getDateStr("");
      List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
      VisitinfoPersonalVo vo = this.visitinfoService.selectVisitinfoPersonalById(herbSaveVo.getPatientId());
      Integer orderGroupNoMax = this.tdPaOrderItemMapper.selectPatientMaxGroupNo(herbSaveVo.getPatientId());
      int orderGroupNo = orderGroupNoMax != null ? orderGroupNoMax + 1 : 1;
      herbSaveVo.setOrderNo(orderNo);
      herbSaveVo.setOrderGroupNo(orderGroupNo);
      herbSaveVo.setOrderNoList(Arrays.asList(orderNo));
      TdPaOrder tdPaOrder = this.getOrderInfo(herbSaveVo, vo);
      Integer reOrganizationNoMax = this.tdPaOrderDetailService.getMaxReOrganizationNo(herbSaveVo.getPatientId());
      herbSaveVo.setReOrganizationNo(reOrganizationNoMax);
      TdPaOrderItem tdPaOrderItem = this.getOrderItemInfo(herbSaveVo);
      List<TdPaOrderDetail> detailList = herbSaveVo.getOrderDetailList();
      this.getOrderDetailInfo(detailList, herbSaveVo, drugDoseVoList);
      List<TdPaOrderItem> tdPaOrderItemList = Arrays.asList(tdPaOrderItem);
      if (tdPaOrder != null) {
         this.tdPaOrderService.insertTdPaOrder(tdPaOrder);
      }

      if (tdPaOrderItem != null) {
         this.tdPaOrderItemMapper.insertTdPaOrderItem(tdPaOrderItem);
      }

      if (detailList != null && !detailList.isEmpty()) {
         this.tdPaOrderDetailService.insertList(detailList);
      }

      Date currDate = this.commonService.getDbSysdate();
      currDate.setTime(currDate.getTime() - 1000L);
      this.tdPaOrderStatusService.addTdPaOrderStatus(vo, tdPaOrderItemList, -1, "保存", currDate, "");
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(vo, tdPaOrderItemList, -1, "保存", currDate, "");
      return drugDoseVoList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List updateHerbOrder(HerbSaveVo herbSaveVo) throws Exception {
      List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
      VisitinfoPersonalVo vo = this.visitinfoService.selectVisitinfoPersonalById(herbSaveVo.getPatientId());
      TdPaOrder tdPaOrder = this.getOrderInfo(herbSaveVo, vo);
      TdPaOrderItemVo item = this.selectTdPaOrderItemById(herbSaveVo.getOrderNo());
      herbSaveVo.setOrderGroupNo(item.getOrderGroupNo());
      List<String> orderNoList = Arrays.asList(herbSaveVo.getOrderNo());
      herbSaveVo.setOrderNoList(orderNoList);
      TdPaOrderItem tdPaOrderItem = this.getOrderItemInfo(herbSaveVo);
      List<TdPaOrderDetail> detailList = herbSaveVo.getOrderDetailList();
      this.getOrderDetailInfo(detailList, herbSaveVo, drugDoseVoList);
      List<TdPaOrderItem> tdPaOrderItemList = Arrays.asList(tdPaOrderItem);
      if (tdPaOrder != null) {
         this.tdPaOrderService.updateTdPaOrder(tdPaOrder);
      }

      if (tdPaOrderItem != null) {
         this.tdPaOrderItemMapper.updateTdPaOrderItem(tdPaOrderItem);
      }

      if (detailList != null && !detailList.isEmpty()) {
         this.tdPaOrderDetailService.deleteTdPaOrderDetailByOrder(herbSaveVo.getOrderNo());
         this.tdPaOrderDetailService.insertList(detailList);
      }

      Date currDate = this.commonService.getDbSysdate();
      currDate.setTime(currDate.getTime() - 1000L);
      this.tdPaOrderStatusService.updateTdPaOrderStatus(orderNoList, -1, "保存", currDate, "");
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(vo, tdPaOrderItemList, -1, "保存", currDate, "");
      return drugDoseVoList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveSubmitHerbOrder(OrderCommitVo orderCommitVo, List orderItemList, Visitinfo visitinfo) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<String> orderNoList = (List)orderItemList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      TdPaOrderItemVo param = new TdPaOrderItemVo();
      param.setOrderNoList(orderNoList);
      param.setOrderStatus("0");
      param.setSubmitDoctorCode(basEmployee.getEmplNumber());
      param.setSubmitDoctorNo(basEmployee.getEmplNumber());
      param.setSubmitDoctorName(basEmployee.getEmplName());
      param.setSubmitTime(currDate);
      this.updateOrderStatus(param);
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, 0, "提交", currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemList, 0, "提交", currDate, (String)null);
      this.tdPaOrderSignMainService.addTdPaOrderOperationSign(orderItemList, orderCommitVo, visitinfo, 0, "提交");
      List<TdPaOrder> orderList = this.tdPaOrderService.selectByOrderNoList(orderNoList);
      this.tdMsgSendMainService.operMsgs(orderNoList, user);
      this.tdMsgSendMainService.addMsgs(orderList, visitinfo, 0, "提交");
   }

   public TdPaOrder getOrderInfo(HerbSaveVo herbSaveVo, VisitinfoPersonalVo vo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      TdPaOrder tdPaOrder = new TdPaOrder();
      tdPaOrder.setHospitalCode(sysUser.getHospital().getOrgCode());
      tdPaOrder.setOrderNo(herbSaveVo.getOrderNo());
      tdPaOrder.setOrderType(herbSaveVo.getOrderType());
      tdPaOrder.setOrderClassCode(herbSaveVo.getOrderClassCode());
      tdPaOrder.setPatientId(herbSaveVo.getPatientId());
      tdPaOrder.setCaseNo(vo.getRecordNo());
      tdPaOrder.setAdmissionNo(vo.getInpNo());
      tdPaOrder.setHospitalizedCount(vo.getVisitId());
      tdPaOrder.setPatientWardNo(vo.getAreaCode());
      tdPaOrder.setPatientDepCode(vo.getDeptCd());
      tdPaOrder.setPatientDepName(vo.getDeptName());
      tdPaOrder.setOrderDoctorCode(basEmployee.getEmplNumber());
      tdPaOrder.setOrderDoctorNo(basEmployee.getEmplNumber());
      tdPaOrder.setOrderDoctorName(basEmployee.getEmplName());
      tdPaOrder.setOrderDoctorDepCode(sysUser.getDept().getDeptCode());
      tdPaOrder.setOrderDoctorDepName(sysUser.getDept().getDeptName());
      tdPaOrder.setBabyAdmissionNo(herbSaveVo.getBabyAdmissionNo().equals(vo.getPatientId()) ? null : herbSaveVo.getBabyAdmissionNo());
      tdPaOrder.setResidentDoctorCode(vo.getResDocCd());
      tdPaOrder.setResidentDoctorNo(vo.getResDocCd());
      tdPaOrder.setResidentDoctorName(vo.getResDocName());
      tdPaOrder.setRespNurseCode(vo.getDutyNurCd());
      tdPaOrder.setRespNurseName(vo.getDutyNurName());
      tdPaOrder.setRespNurseNo(vo.getDutyNurCd());
      tdPaOrder.setPerformWardNo(sysUser.getDept().getDeptCode());
      tdPaOrder.setPerformDepCode(sysUser.getDept().getDeptCode());
      tdPaOrder.setPerformDepName(sysUser.getDept().getDeptName());
      tdPaOrder.setPerformComputerIp(herbSaveVo.getPerformComputerIp());
      tdPaOrder.setOrderPerformFlag("0");
      if (StringUtils.isNotBlank(herbSaveVo.getOperatingRoomFlag()) && herbSaveVo.getOperatingRoomFlag().equals("1")) {
         tdPaOrder.setOrderPerformFlag("3");
      }

      tdPaOrder.setBedNo(vo.getBedNo());
      return tdPaOrder;
   }

   public TdPaOrderItem getOrderItemInfo(HerbSaveVo herbSaveVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      TdPaOrderItem tdPaOrderItem = new TdPaOrderItem();
      tdPaOrderItem.setOrderNo(herbSaveVo.getOrderNo());
      tdPaOrderItem.setOrderGroupNo(herbSaveVo.getOrderGroupNo());
      tdPaOrderItem.setOrderSortNumber(OrderUtil.getNumStr(1));
      tdPaOrderItem.setOrderType(herbSaveVo.getOrderType());
      tdPaOrderItem.setOrderClassCode(herbSaveVo.getOrderClassCode());
      tdPaOrderItem.setCpNo("01");
      tdPaOrderItem.setCpName("药疗项目");
      tdPaOrderItem.setOrderStartTime(herbSaveVo.getOrderStartTime());
      tdPaOrderItem.setOrderStartDoc(herbSaveVo.getOrderStartDoc());
      tdPaOrderItem.setOrderStartDocName(herbSaveVo.getOrderStartDocName());
      tdPaOrderItem.setOutHavaDrugFlag(herbSaveVo.getOutHavaDrugFlag());
      tdPaOrderItem.setOrderItemType("1");
      if (herbSaveVo.getOrderStatus().equals("0")) {
         tdPaOrderItem.setSubmitDoctorCode(basEmployee.getEmplNumber());
         tdPaOrderItem.setSubmitDoctorName(basEmployee.getEmplName());
         tdPaOrderItem.setSubmitDoctorNo(basEmployee.getEmplNumber());
         tdPaOrderItem.setSubmitTime(this.commonService.getDbSysdate());
      }

      tdPaOrderItem.setItemOrderUsageWay(herbSaveVo.getItemOrderUsageWay());
      tdPaOrderItem.setItemOrderUsageWayName(herbSaveVo.getItemOrderUsageWayName());
      tdPaOrderItem.setItemOrderFreq(herbSaveVo.getItemOrderFreq());
      tdPaOrderItem.setItemOrderFreqName(herbSaveVo.getItemOrderFreqName());
      tdPaOrderItem.setExecutorDptNo(herbSaveVo.getPerformDepCode());
      tdPaOrderItem.setOrderStatus(herbSaveVo.getOrderStatus());
      tdPaOrderItem.setPrescribeNo(herbSaveVo.getPrescribeNo());
      tdPaOrderItem.setPrescribeName(herbSaveVo.getPrescribeName());
      tdPaOrderItem.setHerbalFlag("1");
      tdPaOrderItem.setHerbalDose(herbSaveVo.getHerbalDose());
      tdPaOrderItem.setHerbalIntr(herbSaveVo.getHerbalIntr());
      tdPaOrderItem.setDecoctMethod(herbSaveVo.getDecoctMethod());
      tdPaOrderItem.setOederDesc(herbSaveVo.getOederDesc());
      tdPaOrderItem.setPlasterFlag(herbSaveVo.getPlasterFlag());
      tdPaOrderItem.setPatientId(herbSaveVo.getPatientId());
      tdPaOrderItem.setReOrganizationNo(herbSaveVo.getReOrganizationNo());
      tdPaOrderItem.setOrderFlagCd("07");
      tdPaOrderItem.setOrderFlagName("中草药医嘱");
      return tdPaOrderItem;
   }

   public void getOrderDetailInfo(List detailList, HerbSaveVo herbSaveVo, List drugDoseVoList) throws Exception {
      for(TdPaOrderDetail tdPaOrderDetail : detailList) {
         tdPaOrderDetail.setOrderNo(herbSaveVo.getOrderNo());
         tdPaOrderDetail.setOrderGroupNo(herbSaveVo.getOrderGroupNo());
         tdPaOrderDetail.setOrderGroupSortNumber(OrderUtil.getNumStr(Integer.parseInt(tdPaOrderDetail.getOrderGroupSortNumber()) + 1));
         tdPaOrderDetail.setOrderSortNumber(OrderUtil.getNumStr(1));
         tdPaOrderDetail.setOrderType(herbSaveVo.getOrderType());
         tdPaOrderDetail.setOrderClassCode(herbSaveVo.getOrderClassCode());
         tdPaOrderDetail.setId(SnowIdUtils.uniqueLong());
         tdPaOrderDetail.setHerbalFlag("1");
         tdPaOrderDetail.setHerbalDose(herbSaveVo.getHerbalDose());
         tdPaOrderDetail.setDetailPerformDepCode(herbSaveVo.getPerformDepCode());
         tdPaOrderDetail.setPatientSelfDrugFlag("0");
         tdPaOrderDetail.setOrderUsageDays(BigDecimal.ONE);
         tdPaOrderDetail.setFirstDoubleFlag("0");
         tdPaOrderDetail.setFirstPerformNum(new BigDecimal(1));
         tdPaOrderDetail.setFirstPerformTotalDose(BigDecimal.valueOf(1L));
         tdPaOrderDetail.setCpNo("01");
         tdPaOrderDetail.setCpName("药疗项目");
         tdPaOrderDetail.setOutHavaDrugFlag(herbSaveVo.getOutHavaDrugFlag());
         tdPaOrderDetail.setOrderItemFlag("1");
         tdPaOrderDetail.setOrderFreq(herbSaveVo.getItemOrderFreq());
         tdPaOrderDetail.setOrderUsageWay(herbSaveVo.getItemOrderUsageWay());
         tdPaOrderDetail.setHerbalDose(herbSaveVo.getHerbalDose());
         tdPaOrderDetail.setReOrganizationNo(herbSaveVo.getReOrganizationNo());
         tdPaOrderDetail.setPharmacopoeiaNo(tdPaOrderDetail.getChargeNo());
         tdPaOrderDetail.setBabyAdmissionNo(herbSaveVo.getBabyAdmissionNo().equals(herbSaveVo.getPatientId()) ? null : herbSaveVo.getBabyAdmissionNo());
         DrugDoseVo drugDoseVo = new DrugDoseVo(tdPaOrderDetail.getCpId(), tdPaOrderDetail.getDetailPerformDepCode(), tdPaOrderDetail.getChargeNo(), tdPaOrderDetail.getStockNo(), tdPaOrderDetail.getOrderDose().negate(), tdPaOrderDetail.getPrice());
         drugDoseVo.setOrderNo(tdPaOrderDetail.getOrderNo());
         drugDoseVoList.add(drugDoseVo);
      }

   }

   public List selectByOrderFlagCdAndStatus(String patientId, String orderStatus, List orderFlagCdList) throws Exception {
      return this.tdPaOrderItemMapper.selectByOrderFlagCdAndStatus(patientId, orderStatus, orderFlagCdList);
   }

   public List selectByStatusList(TdPaOrderItemVo tdPaOrderItemVo) throws Exception {
      return this.tdPaOrderItemMapper.selectByStatusList(tdPaOrderItemVo);
   }

   public List selectPastOrderListByPatList(List patientIdList) throws Exception {
      return this.tdPaOrderItemMapper.selectPastOrderListByPatList(patientIdList);
   }

   public String herbSignText(String signType, String patientId, List orderNoList, Map orderStopMap, Date currDate) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      StringBuffer herbSignTextSb = new StringBuffer("");

      for(OrderSignTextVo orderSignTextVo : this.tdPaOrderSignMainService.selectOrderSignTextList(orderNoList)) {
         herbSignTextSb.append(orderSignTextVo.getChargeName()).append(",");
         switch (signType) {
            case "0":
               OrderStopVo orderStopVo = (OrderStopVo)orderStopMap.get(orderSignTextVo.getOrderNo());
               herbSignTextSb.append(orderSignTextVo.getSubmitDoctorNo()).append(",").append(orderSignTextVo.getSubmitTime()).append(",");
               herbSignTextSb.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, orderStopVo.getStopTime())).append(";");
               break;
            case "1":
               herbSignTextSb.append(orderSignTextVo.getSubmitDoctorNo()).append(",").append(orderSignTextVo.getSubmitTime()).append(",");
               herbSignTextSb.append(orderSignTextVo.getStopDoctorNo()).append(",").append(orderSignTextVo.getStopTime()).append(",");
               herbSignTextSb.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, currDate)).append(";");
               break;
            case "2":
               herbSignTextSb.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, currDate)).append(";");
               break;
            case "4":
               herbSignTextSb.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, currDate)).append(";");
         }
      }

      return herbSignTextSb.toString();
   }

   public String orderSignText(String signType, String patientId, List orderNoList, Map orderStopMap, Date currDate) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      StringBuffer herbSignTextSb = new StringBuffer("");

      for(OrderSignTextVo orderSignTextVo : this.tdPaOrderSignMainService.selectOrderSignTextList(orderNoList)) {
         herbSignTextSb.append(orderSignTextVo.getChargeName()).append(",");
         switch (signType) {
            case "0":
               OrderSearchVo orderVo = (OrderSearchVo)orderStopMap.get(orderSignTextVo.getOrderNo());
               herbSignTextSb.append(orderSignTextVo.getSubmitDoctorNo()).append(",").append(orderSignTextVo.getSubmitTime()).append(",");
               herbSignTextSb.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, orderVo.getOrderStopTime())).append(";");
               break;
            case "1":
               herbSignTextSb.append(orderSignTextVo.getSubmitDoctorNo()).append(",").append(orderSignTextVo.getSubmitTime()).append(",");
               herbSignTextSb.append(orderSignTextVo.getStopDoctorNo()).append(",").append(orderSignTextVo.getStopTime()).append(",");
               herbSignTextSb.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, currDate)).append(";");
               break;
            case "3":
               herbSignTextSb.append(orderSignTextVo.getSubmitDoctorNo()).append(",").append(orderSignTextVo.getSubmitTime()).append(",");
               herbSignTextSb.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, currDate)).append(";");
               break;
            case "4":
               herbSignTextSb.append(orderSignTextVo.getSubmitDoctorNo()).append(",").append(orderSignTextVo.getSubmitTime()).append(",");
               herbSignTextSb.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, currDate)).append(";");
         }
      }

      return herbSignTextSb.toString();
   }

   public void stopUnclaimedDrugList(List takeDrugList) {
      List<String> orderNoList = (List)takeDrugList.stream().map((t) -> t.getPerformListNo()).collect(Collectors.toList());
      this.tdPaOrderItemMapper.stopUnclaimedDrugList(orderNoList);
      this.tdPaOrderItemMapper.deleteUnclaimedDrugByPerFormNos(orderNoList);
   }

   public void stopUnExecPerformList(List orderPerformList) {
      this.tdPaOrderItemMapper.stopUnExecPerformList(orderPerformList);
   }

   public List selectByOrderTypeAndStatus(TdPaOrderItemVo param) {
      return this.tdPaOrderItemMapper.selectByOrderTypeAndStatus(param);
   }

   public List selectStopAllOrderList(String patientId) throws Exception {
      return this.tdPaOrderItemMapper.selectStopAllOrderList(patientId);
   }

   public List isStockNum(List detailList, String detailPerformDepCode) throws Exception {
      List<TdPaOrderDetail> resultList = new ArrayList();
      if (detailList != null && !detailList.isEmpty()) {
         List<String> cpNoList = (List)detailList.stream().map((s) -> s.getChargeNo().trim()).collect(Collectors.toList());
         List<DrugAndClin> list = this.drugStockService.selectHisDrugStockList(cpNoList, detailPerformDepCode);
         if (list != null && !list.isEmpty()) {
            for(TdPaOrderDetail tdPaOrderDetail : detailList) {
               List<DrugAndClin> drugAndClins = (List)list.stream().filter((s) -> s.getCpNo().trim().equals(tdPaOrderDetail.getChargeNo().trim())).collect(Collectors.toList());
               if (drugAndClins != null && !drugAndClins.isEmpty()) {
                  BigDecimal xcslTotal = (BigDecimal)drugAndClins.stream().map(DrugAndClin::getXcsl).reduce(BigDecimal.ZERO, BigDecimal::add);
                  int a = xcslTotal.intValue();
                  int b = tdPaOrderDetail.getOrderTotalDose().intValue();
                  if (a < b) {
                     resultList.add(tdPaOrderDetail);
                  }
               } else {
                  resultList.add(tdPaOrderDetail);
               }
            }
         } else {
            resultList.addAll(detailList);
         }
      }

      return resultList;
   }

   public List selectCpNoDayOrderList(TdPaOrderItemVo tdPaOrderItemVo) throws Exception {
      List<TdPaOrderItem> list = null;
      if (tdPaOrderItemVo != null && CollectionUtils.isNotEmpty(tdPaOrderItemVo.getCpNoList())) {
         list = this.tdPaOrderItemMapper.selectCpNoDayOrderList(tdPaOrderItemVo);
      }

      return list;
   }

   public List selectTdPaOrderItemListAll(TdPaOrderItem tdPaOrderItem) throws Exception {
      return this.tdPaOrderItemMapper.selectTdPaOrderItemListAll(tdPaOrderItem);
   }

   public void updateTdPaOrderItemByTableName(TdPaOrderItemVo tdPaOrderItemVo) throws Exception {
      if (StringUtils.isNotBlank(tdPaOrderItemVo.getOrderNo()) && StringUtils.isNotBlank(tdPaOrderItemVo.getTableName()) && (tdPaOrderItemVo.getOrderStopTime() != null || tdPaOrderItemVo.getOrderExecuteTime() != null)) {
         this.tdPaOrderItemMapper.updateTdPaOrderItemByTableName(tdPaOrderItemVo);
      }

   }

   public void stopPerformList(List orderPerformList, String userName, String nickName) throws Exception {
      this.tdPaOrderItemMapper.stopPerformList(orderPerformList, userName, nickName);
   }

   public List selectPatientDoingNursingLevel(String patientId) throws Exception {
      String orderFlagCd = "08";
      List<String> nlChargeNoList = this.tdPaOrderItemMapper.selectNursingLevelChargeNoList();
      List<TdPaOrderItemVo> list = new ArrayList(1);
      if (StringUtils.isNotBlank(patientId) && StringUtils.isNotBlank(orderFlagCd)) {
         list = this.tdPaOrderItemMapper.selectPatientDoingNursingLevel(patientId, orderFlagCd, nlChargeNoList);
      }

      return list;
   }

   public List selectNursingLevelChargeNoList() throws Exception {
      return this.tdPaOrderItemMapper.selectNursingLevelChargeNoList();
   }

   public List selectPatOrderForDrugCodes(String patientId, String beginTime, String endTime, String drugCode) throws Exception {
      List<TdPaOrderItem> list = null;
      if (StringUtils.isNotBlank(patientId) && StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime) && StringUtils.isNotBlank(drugCode)) {
         list = this.tdPaOrderItemMapper.selectPatOrderForDrugCodes(patientId, beginTime, endTime, drugCode);
         return list;
      } else {
         throw new BaseException("医嘱开立-抗菌药物是否可以开立-查询此药品的医嘱", "医嘱开立-抗菌药物是否可以开立-查询此药品的医嘱时参数为空，patientId：" + patientId + "，beginTime：" + beginTime + "，endTime：" + endTime + "，drugCodes：" + drugCode);
      }
   }

   public List selectPatientOutOrder(TdPaOrderItem param) throws Exception {
      List<TdPaOrderItem> list = null;
      if (StringUtils.isNotBlank(param.getPatientId()) && StringUtils.isNotBlank(param.getOrderFlagCd())) {
         list = this.tdPaOrderItemMapper.selectPatientOutOrder(param, (List)null);
      }

      return list;
   }

   public List selectPatientOutOrder(TdPaOrderItem param, List statusList) throws Exception {
      List<TdPaOrderItem> list = null;
      if (StringUtils.isNotBlank(param.getPatientId()) && StringUtils.isNotBlank(param.getOrderFlagCd()) && CollectionUtils.isNotEmpty(statusList)) {
         list = this.tdPaOrderItemMapper.selectPatientOutOrder(param, statusList);
      }

      return list;
   }

   public List selectOrderItemAll(TdPaOrderItemVo orderItemVo) throws Exception {
      List<TdPaOrderItem> list = null;
      if (orderItemVo != null) {
         list = this.tdPaOrderItemMapper.selectOrderItemAll(orderItemVo);
      }

      return list;
   }

   public Integer selectCultureCount(String patientId) {
      return this.tdPaOrderItemMapper.selectCultureCount(patientId);
   }

   public Integer selectCultureCountBySubmitDate(String patientId, Date orderStartTime) {
      return this.tdPaOrderItemMapper.selectCultureCountBySubmitDate(patientId, orderStartTime);
   }
}
