package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.project.CDSS.xyt.RequestUtil;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.domain.TdPaApplyForm;
import com.emr.project.docOrder.domain.TdPaApplyFormDetail;
import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderAgent;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TmPmOrderStatus;
import com.emr.project.docOrder.domain.req.PrintOrderDataListDetail;
import com.emr.project.docOrder.domain.req.PrintOrderDataListReq;
import com.emr.project.docOrder.domain.vo.MedicalinformationVo;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSaveResVo;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.PrescriptionPrintVo;
import com.emr.project.docOrder.domain.vo.PrintAgentVo;
import com.emr.project.docOrder.domain.vo.PrintOrderDataListDetailVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderAgentVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.mapper.TdPaOrderMapper;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.docOrder.service.ITdMsgSendMainService;
import com.emr.project.docOrder.service.ITdPaApplyFormDetailService;
import com.emr.project.docOrder.service.ITdPaApplyFormItemService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.docOrder.service.ITdPaOrderAgentService;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.docOrder.service.ITmPmOrderStatusService;
import com.emr.project.docOrder.util.OrderUtil;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.req.DrugAndStockSearchReq;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.DrugCheck;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.his.domain.vo.PharmacyVo;
import com.emr.project.mrhp.service.IMrHpFeeService;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.vo.TakeDrugListSaveVO;
import com.emr.project.operation.mapper.ExpenseDetailMapper;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.PersonalinfoVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.QcAgiEven;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.qc.service.IQcAgiEvenService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpa.domain.OrderFreq;
import com.emr.project.tmpa.domain.OrderSig;
import com.emr.project.tmpa.service.IOrderFreqService;
import com.emr.project.tmpa.service.IOrderSigService;
import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IClinItemDetailService;
import com.emr.project.tmpm.service.IClinItemMainService;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TdPaOrderServiceImpl implements ITdPaOrderService {
   protected final Logger log = LoggerFactory.getLogger(TdPaOrderServiceImpl.class);
   @Autowired
   private TdPaOrderMapper tdPaOrderMapper;
   @Autowired
   private IOrderSigService orderSigService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ITdPaOrderOperationLogService tdPaOrderOperationLogService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private IBabyInfoService babyInfoService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITmPmOrderStatusService tmPmOrderStatusService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private IOrderFreqService orderFreqService;
   @Autowired
   private IClinItemDetailService clinItemDetailService;
   @Autowired
   private ITdPaOrderDetailService orderDetailService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private ITdPaOrderSignMainService tdPaOrderSignMainService;
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private IQcAgiEvenService qcAgiEvenService;
   @Autowired
   private ITdPaApplyFormService tdPaApplyFormService;
   @Autowired
   private ITdPaApplyFormItemService tdPaApplyFormItemService;
   @Autowired
   private ITdPaApplyFormDetailService tdPaApplyFormDetailService;
   @Autowired
   private ITdCaOperationApplyService tdCaOperationApplyService;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private IClinItemMainService clinItemMainService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private ITdPaOrderAgentService agentService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;
   @Autowired
   private ExpenseDetailMapper expenseDetailMapper;
   @Autowired
   private ITdMsgSendMainService tdMsgSendMainService;
   @Autowired
   IMrHpFeeService mrHpFeeService;

   public TdPaOrder selectTdPaOrderById(String orderNo) {
      return this.tdPaOrderMapper.selectTdPaOrderById(orderNo);
   }

   public TdPaOrder selectTdPaOrderByApplyFormNo(String applyFormNo) throws Exception {
      return this.tdPaOrderMapper.selectTdPaOrderByApplyFormNo(applyFormNo);
   }

   public List selectTdPaOrderList(TdPaOrder tdPaOrder) {
      return this.tdPaOrderMapper.selectTdPaOrderList(tdPaOrder);
   }

   public List selectOrderSearchVoList(OrderSearchVo queryParam) throws Exception {
      List<OrderSearchVo> listMain = new ArrayList(1);
      queryParam.setMasterOrder("1");
      queryParam.setOrderClassCode01("01");
      if (StringUtils.isBlank(queryParam.getHerbalFlag())) {
         queryParam.setHerbalFlag("0");
      } else if (StringUtils.isNotBlank(queryParam.getHerbalFlag()) && queryParam.getHerbalFlag().equals("1")) {
         queryParam.setHerbalFlag((String)null);
      }

      if (StringUtils.isNotBlank(queryParam.getCpName())) {
         List<String> distinctOrderNoList = this.tdPaOrderMapper.selectOrderNoList(queryParam);
         if (distinctOrderNoList != null && !distinctOrderNoList.isEmpty()) {
            OrderSearchVo queryParamTemp = new OrderSearchVo();
            queryParamTemp.setDistinctOrderNoList(distinctOrderNoList);
            queryParamTemp.setMasterOrder("1");
            queryParamTemp.setOrderClassCode01("01");
            listMain = this.tdPaOrderMapper.selectOrderSearchVoList(queryParamTemp);
         }
      } else {
         listMain = this.tdPaOrderMapper.selectOrderSearchVoList(queryParam);
      }

      if (listMain != null && !listMain.isEmpty()) {
         List<String> applyNoList = (List)((List)listMain.stream().filter((s) -> StringUtils.isNotBlank(s.getApplyFormNo())).collect(Collectors.toList())).stream().map((t) -> t.getApplyFormNo()).collect(Collectors.toList());
         List<TdPaApplyFormItem> formList = this.tdPaApplyFormItemService.selectItemByApplyNoList(applyNoList);
         if (formList != null && !formList.isEmpty()) {
            for(OrderSearchVo orderSearchVo : listMain) {
               List<TdPaApplyFormItem> itemList = (List)formList.stream().filter((s) -> s.getApplyFormNo().equals(orderSearchVo.getApplyFormNo()) && orderSearchVo.getCpNo().equals(s.getItemCode())).collect(Collectors.toList());
               if (itemList != null && !itemList.isEmpty()) {
                  orderSearchVo.setSpecCd(((TdPaApplyFormItem)itemList.get(0)).getSpecCd());
                  orderSearchVo.setSpecName(((TdPaApplyFormItem)itemList.get(0)).getSpecName());
                  orderSearchVo.setExamPartCd(((TdPaApplyFormItem)itemList.get(0)).getExamPartCd());
                  orderSearchVo.setExamPartName(((TdPaApplyFormItem)itemList.get(0)).getExamPartName());
               }
            }
         }
      }

      return listMain;
   }

   public List selectOrderSearchVoListStopOneDay(OrderSearchVo queryParam) throws Exception {
      new ArrayList(1);
      queryParam.setOrderClassCode01("01");
      Date currDate = this.commonService.getDbSysdate();
      Date currDate24 = DateUtils.addDay((Date)currDate, -1);
      queryParam.setOrderStopTime(currDate24);
      List listMain = this.tdPaOrderMapper.selectOrderSearchVoListStopOneDay(queryParam);
      this.log.info("########查询一天内出院医嘱信息01###########" + listMain);
      return listMain;
   }

   public List selectSubOrderSearchVoList(List listMain) throws Exception {
      List<OrderSearchVo> list = new ArrayList(1);
      if (listMain != null && !listMain.isEmpty()) {
         List<String> deptCodeList = (List)listMain.stream().filter((t) -> StringUtils.isNotBlank(t.getDetailPerformDepCode())).map((t) -> t.getDetailPerformDepCode()).collect(Collectors.toList());
         List<String> deptCodeList2 = (List)listMain.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderAuditDeptCode())).map((t) -> t.getOrderAuditDeptCode()).collect(Collectors.toList());
         String hospitalCode = SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode();
         if (deptCodeList2 != null && !deptCodeList2.isEmpty()) {
            deptCodeList.addAll(deptCodeList2);
         }

         List<OrderSearchVo> drugMainOrderList = (List)listMain.stream().filter((t) -> t.getOrderClassCode().equals("01")).collect(Collectors.toList());
         List<String> mainOrderNoList = drugMainOrderList != null && !drugMainOrderList.isEmpty() ? (List)drugMainOrderList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList()) : null;
         List<OrderSearchVo> listSub = mainOrderNoList != null && !mainOrderNoList.isEmpty() ? this.tdPaOrderMapper.selectOrderSearchVoSubList(mainOrderNoList, "1") : null;
         if (listSub != null) {
            List<String> deptSubCodeList = (List)listSub.stream().filter((t) -> StringUtils.isNotBlank(t.getDetailPerformDepCode())).map((t) -> t.getDetailPerformDepCode()).collect(Collectors.toList());
            List<String> deptSubCodeList1 = (List)listSub.stream().filter((t) -> StringUtils.isNotBlank(t.getPerformDepCode())).map((t) -> t.getPerformDepCode()).collect(Collectors.toList());
            if (!deptSubCodeList.isEmpty()) {
               deptCodeList.addAll(deptSubCodeList);
            }

            if (!deptSubCodeList1.isEmpty()) {
               deptCodeList.addAll(deptSubCodeList1);
            }
         }

         deptCodeList = (List)deptCodeList.stream().distinct().collect(Collectors.toList());
         List<SysDept> deptList = this.sysDeptService.selectListByDeptCodeList(hospitalCode, deptCodeList);
         Map<String, SysDept> deptMap = (Map)deptList.stream().collect(Collectors.toMap((t) -> t.getDeptCode(), Function.identity()));
         List<OrderSearchVo> orderList = (List)listMain.stream().filter((t) -> !t.getOrderClassCode().equals("01") && !t.getOrderClassCode().equals("02") && !t.getOrderClassCode().equals("03") && !t.getOrderClassCode().equals("04")).collect(Collectors.toList());
         List<String> orderNoList = (List)orderList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
         List<TdPaOrderDetail> listDetail = this.orderDetailService.selectByOrderNoList(orderNoList);
         Map<String, List<TdPaOrderDetail>> listDetailMap = (Map<String, List<TdPaOrderDetail>>)(CollectionUtils.isNotEmpty(listDetail) ? (Map)listDetail.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo())) : new HashMap(1));
         Map<String, Object> fieldsMap = this.getOrderSearchFieldsMap(listMain, listSub);
         if (listSub != null) {
            listSub.stream().forEach((s) -> {
               this.setOrderSearchFields(s, fieldsMap);
               String deptName = deptMap.get(s.getDetailPerformDepCode()) != null ? ((SysDept)deptMap.get(s.getDetailPerformDepCode())).getDeptName() : null;
               String performDepName = deptMap.get(s.getPerformDepCode()) != null ? ((SysDept)deptMap.get(s.getPerformDepCode())).getDeptName() : null;
               s.setDetailPerformDepName(deptName);
               s.setPerformDepName(performDepName);
            });
         }

         Map<String, List<OrderSearchVo>> listSubMap = (Map<String, List<OrderSearchVo>>)(listSub != null ? (Map)listSub.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo() + t.getOrderSortNumber())) : new HashMap(1));
         listMain.stream().forEach((t) -> {
            String deptName = deptMap.get(t.getDetailPerformDepCode()) != null ? ((SysDept)deptMap.get(t.getDetailPerformDepCode())).getDeptName() : null;
            t.setDetailPerformDepName(deptName);
            if (StringUtils.isBlank(t.getOrderAuditDeptName())) {
               String auditDeptName = deptMap.get(t.getOrderAuditDeptCode()) != null ? ((SysDept)deptMap.get(t.getOrderAuditDeptCode())).getDeptName() : null;
               t.setOrderAuditDeptName(auditDeptName);
            }

            this.setOrderSearchFields(t, fieldsMap);
            List<TdPaOrderDetail> listDetailTemp = (List)listDetailMap.get(t.getOrderNo());
            if (CollectionUtils.isNotEmpty(listDetailTemp)) {
               t.setOrderActualUsage(((TdPaOrderDetail)listDetailTemp.get(0)).getOrderActualUsage());
               String usageUnit = ((TdPaOrderDetail)listDetailTemp.get(0)).getUsageUnit();
               usageUnit = StringUtils.isNotBlank(usageUnit) ? usageUnit : "次";
               t.setUsageUnit(usageUnit);
               if (StringUtils.isBlank(t.getSkinTestResults()) && StringUtils.isNotBlank(((TdPaOrderDetail)listDetailTemp.get(0)).getSkinTestResults())) {
                  t.setSkinTestResults(((TdPaOrderDetail)listDetailTemp.get(0)).getSkinTestResults());
               }
            }

            String unit = StringUtils.isNotBlank(t.getUnit()) ? t.getUnit() : "次";
            t.setUnit(unit);
            list.add(t);
            List<OrderSearchVo> listSubTemp = (List)listSubMap.get(t.getOrderNo() + t.getOrderSortNumber());
            if (listSubTemp != null && !listSubTemp.isEmpty()) {
               t.setGroupStr("┓");

               for(int i = 0; i < listSubTemp.size(); ++i) {
                  OrderSearchVo temp = (OrderSearchVo)listSubTemp.get(i);
                  temp.setGroupStr("┃");
                  if (i == listSubTemp.size() - 1) {
                     temp.setGroupStr("┛");
                  }
               }

               list.addAll(listSubTemp);
            }

         });
      }

      return list;
   }

   private Map getOrderSearchFieldsMap(List listMain, List listSub) throws Exception {
      Map<String, Object> resMap = new HashMap(1);
      List<String> sigCdList = new ArrayList(1);
      List<String> sigCdListMain = (List)listMain.stream().map((t) -> t.getOrderUsageWay()).collect(Collectors.toList());
      List<String> sigCdListSub = (List<String>)(listSub != null ? (List)listSub.stream().map((t) -> t.getOrderUsageWay()).collect(Collectors.toList()) : new ArrayList(1));
      sigCdList.addAll(sigCdListMain);
      sigCdList.addAll(sigCdListSub);
      sigCdList = (List)sigCdList.stream().filter((t) -> StringUtils.isNotBlank(t)).distinct().collect(Collectors.toList());
      List<OrderSig> orderSigList = this.orderSigService.selectOrderSigListBySigCd(sigCdList);
      Map<String, OrderSig> orderSigMap = (Map)orderSigList.stream().collect(Collectors.toMap((t) -> t.getSigCd(), Function.identity()));
      resMap.put("orderSigMap", orderSigMap);
      List<String> freqCdList = new ArrayList(1);
      List<String> freqCdListMain = (List)listMain.stream().map((t) -> t.getOrderFreq()).collect(Collectors.toList());
      List<String> freqCdListSub = (List<String>)(listSub != null ? (List)listSub.stream().map((t) -> t.getOrderFreq()).collect(Collectors.toList()) : new ArrayList(1));
      freqCdList.addAll(freqCdListMain);
      freqCdList.addAll(freqCdListSub);
      freqCdList = (List)freqCdList.stream().filter((t) -> StringUtils.isNotBlank(t)).distinct().collect(Collectors.toList());
      List<OrderFreq> orderFreqList = this.orderFreqService.selectByFreqCdList(freqCdList);
      Map<String, OrderFreq> orderFreqMap = (Map)orderFreqList.stream().collect(Collectors.toMap((t) -> t.getFreqCd(), Function.identity()));
      resMap.put("orderFreqMap", orderFreqMap);
      List<SysDictData> purposeAntimicrobialUseList = this.sysDictDataService.selectDictDataByType("s063");
      Map<String, SysDictData> purposeAntimicrobialUseMap = (Map)purposeAntimicrobialUseList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      resMap.put("purposeAntimicrobialUseMap", purposeAntimicrobialUseMap);
      List<SysDictData> takeDrugModeList = this.sysDictDataService.selectDictDataByType("s069");
      Map<String, SysDictData> takeDrugModeMap = (Map)takeDrugModeList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      resMap.put("takeDrugModeMap", takeDrugModeMap);
      List<SysDictData> orderItemFlagList = this.sysDictDataService.selectDictDataByType("s068");
      Map<String, SysDictData> orderItemFlagMap = (Map)orderItemFlagList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      resMap.put("orderItemFlagMap", orderItemFlagMap);
      List<SysDictData> drippingSpeedList = this.sysDictDataService.selectDictDataByType("s071");
      Map<String, SysDictData> drippingSpeedMap = (Map)drippingSpeedList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      resMap.put("drippingSpeedMap", drippingSpeedMap);
      return resMap;
   }

   private void setOrderSearchFields(OrderSearchVo orderSearchVo, Map fieldsMap) {
      Map<String, OrderSig> orderSigMap = (Map)fieldsMap.get("orderSigMap");
      Map<String, OrderFreq> orderFreqMap = (Map)fieldsMap.get("orderFreqMap");
      Map<String, SysDictData> purposeAntimicrobialUseMap = (Map)fieldsMap.get("purposeAntimicrobialUseMap");
      Map<String, SysDictData> takeDrugModeMap = (Map)fieldsMap.get("takeDrugModeMap");
      Map<String, SysDictData> orderItemFlagMap = (Map)fieldsMap.get("orderItemFlagMap");
      Map<String, SysDictData> drippingSpeedMap = (Map)fieldsMap.get("drippingSpeedMap");
      OrderSig orderSigs = (OrderSig)orderSigMap.get(orderSearchVo.getOrderUsageWay());
      orderSearchVo.setOrderUsageWayName(orderSigs != null ? orderSigs.getSigName() : null);
      OrderFreq orderFreq = (OrderFreq)orderFreqMap.get(orderSearchVo.getOrderFreq());
      orderSearchVo.setOrderFreqName(orderFreq != null ? orderFreq.getFreqName() : null);
      SysDictData purposeAntimicrobialUse = (SysDictData)purposeAntimicrobialUseMap.get(orderSearchVo.getPurposeAntimicrobialUse());
      orderSearchVo.setPurposeAntimicrobialUseName(purposeAntimicrobialUse != null ? purposeAntimicrobialUse.getDictLabel() : null);
      SysDictData takeDrugMode = (SysDictData)takeDrugModeMap.get(orderSearchVo.getTakeDrugMode());
      orderSearchVo.setTakeDrugModeName(takeDrugMode != null ? takeDrugMode.getDictLabel() : null);
      SysDictData orderItemFlag = (SysDictData)orderItemFlagMap.get(orderSearchVo.getOrderItemFlag());
      orderSearchVo.setOrderItemFlagName(orderItemFlag != null ? orderItemFlag.getDictLabel() : null);
      SysDictData drippingSpeed = (SysDictData)drippingSpeedMap.get(orderSearchVo.getDrippingSpeed());
      orderSearchVo.setDrippingSpeedName(drippingSpeed != null ? drippingSpeed.getDictLabel() : null);
   }

   public int insertTdPaOrder(TdPaOrder tdPaOrder) {
      return this.tdPaOrderMapper.insertTdPaOrder(tdPaOrder);
   }

   public int updateTdPaOrder(TdPaOrder tdPaOrder) {
      return this.tdPaOrderMapper.updateTdPaOrder(tdPaOrder);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List deleteTdPaOrderByIds(List orderNoList, List applyFormList, List operationApplyList, List caConsApplyList) throws Exception {
      List<TdPaOrder> orderList = this.tdPaOrderMapper.selectByOrderNoList(orderNoList);
      List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
      if (orderList != null && orderList.size() > 0) {
         List<TdPaOrderItem> orderItemList = this.tdPaOrderItemService.selectByOrderNoList(orderNoList);
         List<TdPaOrderDetail> orderDetailList = this.orderDetailService.selectByOrderNoList(orderNoList);
         Map<String, List<TdPaOrderItem>> orderItemListMap = (Map)orderItemList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo() + t.getOrderSortNumber()));

         for(TdPaOrderDetail t : orderDetailList) {
            Boolean drugFlag = t.getOrderClassCode().equals("01");
            Boolean updateDrugDoseFlag = StringUtils.isNotBlank(t.getOrderItemFlag()) && !t.getOrderItemFlag().equals("2") && !t.getOrderItemFlag().equals("3");
            if (drugFlag && updateDrugDoseFlag) {
               TdPaOrderItem orderItem = (TdPaOrderItem)((List)orderItemListMap.get(t.getOrderNo() + t.getOrderSortNumber())).get(0);
               DrugDoseVo drugDoseVo = this.getDrugDoseToday(orderItem, t);
               if (drugDoseVo != null) {
                  drugDoseVoList.add(drugDoseVo);
               }
            }
         }

         this.tdPaOrderMapper.toDelTable(orderList);
         this.tdPaOrderMapper.deleteTdPaOrderByIds(orderNoList);
         this.tdPaOrderItemService.toDelTable(orderItemList);
         this.tdPaOrderItemService.deleteTdPaOrderItemByIds(orderNoList);
         this.orderDetailService.toDelTable(orderDetailList);
         this.orderDetailService.deleteTdPaOrderDetailByIds(orderNoList);
         String patientId = ((TdPaOrder)orderList.get(0)).getPatientId();
         Visitinfo visitinfo = this.visitinfoService.selectVisitinfoById(patientId);
         this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, -3, "删除", this.commonService.getDbSysdate(), (String)null);
         if (applyFormList != null && !applyFormList.isEmpty()) {
            List<String> applyFormNoList = (List)applyFormList.stream().map(TdPaApplyForm::getApplyFormNo).distinct().collect(Collectors.toList());
            this.tdPaApplyFormService.updateStatusByApplyFormNos(applyFormNoList, "99");
         }

         if (operationApplyList != null && !operationApplyList.isEmpty()) {
            List<String> applyFormNoList = (List)operationApplyList.stream().map(TdCaOperationApply::getApplyFormNo).distinct().collect(Collectors.toList());
            this.tdCaOperationApplyService.updateStatusByIds(applyFormNoList, "99");
         }

         if (caConsApplyList != null && !caConsApplyList.isEmpty()) {
            List<Long> caConsApplyIds = (List)caConsApplyList.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
            this.tdCaConsApplyService.updateStatusByIds(caConsApplyIds, "05");
         }

         this.tdMsgSendMainService.operMsgs(orderNoList, SecurityUtils.getLoginUser().getUser());
      }

      return drugDoseVoList;
   }

   public int deleteTdPaOrderById(String orderNo) {
      return this.tdPaOrderMapper.deleteTdPaOrderById(orderNo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List saveTdPaOrder(List orderSaveVoList, OrderSaveResVo orderSaveResVo, List drugCheckList, TdPaOrderAgentVo tdPaOrderAgentVo, Visitinfo visitinfo, String ip, String operatingRoomFlag) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long angentId = SnowIdUtils.uniqueLong();
      List<TdPaOrder> orderList = new ArrayList(1);
      List<TdPaOrderItem> orderItemList = new ArrayList(1);
      List<TdPaOrderDetail> orderDetailList = new ArrayList(1);
      List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
      List<OrderSaveVo> drugOrderSaveVoMainList = new ArrayList(1);
      Map<String, List<OrderSaveVo>> drugOrderSaveVoSubListMap = new HashMap(1);
      List<OrderSaveVo> clinOrderSaveVoList = new ArrayList(1);
      String orderNo = DateUtils.getDateStr("");
      List<OrderSaveVo> orderSaveVoListNew = new ArrayList(orderSaveVoList.size());
      int orderSortNumber = 1;
      int orderGroupSortNumber = 1;
      Integer orderGroupNoMax = this.tdPaOrderItemService.selectPatientMaxGroupNo(visitinfo.getPatientId());
      int orderGroupNo = orderGroupNoMax != null ? orderGroupNoMax + 1 : 1;
      Integer reOrganizationNoMax = this.orderDetailService.getMaxReOrganizationNo(visitinfo.getPatientId());
      int reOrganizationNo = reOrganizationNoMax != null ? reOrganizationNoMax : 0;
      List<Integer> pageOrderGroupNoList = new ArrayList(orderSaveVoList.size());
      List<String> freqCdList = (List)orderSaveVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderFreq())).map((t) -> t.getOrderFreq()).collect(Collectors.toList());
      List<OrderFreq> orderFreqList = this.orderFreqService.selectByFreqCdList(freqCdList);
      Map<String, OrderFreq> orderFreqMap = (Map)orderFreqList.stream().collect(Collectors.toMap((t) -> t.getFreqCd(), Function.identity()));
      List<TdPaOrderAgent> tdPaOrderAgentList = new ArrayList(1);

      for(int i = 0; i < orderSaveVoList.size(); ++i) {
         Boolean subFlag = false;
         OrderSaveVo orderSaveVo = (OrderSaveVo)orderSaveVoList.get(i);
         pageOrderGroupNoList.add(orderSaveVo.getOrderGroupNo());
         if (i > 0) {
            Integer prepageOrderGroupNo = (Integer)pageOrderGroupNoList.get(i - 1);
            if (prepageOrderGroupNo.compareTo(orderSaveVo.getOrderGroupNo()) != 0) {
               orderNo = DateUtils.getDateStr("");
               ++orderGroupNo;
               ++orderSortNumber;
               orderGroupSortNumber = 1;
            } else {
               ++orderGroupSortNumber;
               subFlag = true;
            }
         }

         orderSaveVo.setOrderNo(orderNo);
         orderSaveVo.setOrderGroupNo(orderGroupNo);
         orderSaveVo.setOrderSortNumber(OrderUtil.getNumStr(orderSortNumber));
         orderSaveVo.setOrderGroupSortNumber(OrderUtil.getNumStr(orderGroupSortNumber));
         orderSaveVo.setReOrganizationNo(reOrganizationNo);
         if (orderSaveVo.getFreqValue() == null) {
            OrderFreq orderFreq = (OrderFreq)orderFreqMap.get(orderSaveVo.getOrderFreq());
            orderSaveVo.setFreqValue(orderFreq != null ? orderFreq.getFreqValue() : null);
         }

         if (StringUtils.isNotBlank(orderSaveVo.getDrugClassCode()) && orderSaveVo.getDrugClassCode().equals("03")) {
            orderSaveVo.setAgentId(angentId);
         }

         orderSaveVoListNew.add(orderSaveVo);
         if (subFlag) {
            List<OrderSaveVo> drugSubList = (List<OrderSaveVo>)(drugOrderSaveVoSubListMap.get(orderNo) == null ? new ArrayList(1) : (List)drugOrderSaveVoSubListMap.get(orderNo));
            drugSubList.add(orderSaveVo);
            drugOrderSaveVoSubListMap.put(orderNo, drugSubList);
         } else if (orderSaveVo.getOrderClassCode().equals("01")) {
            drugOrderSaveVoMainList.add(orderSaveVo);
         } else {
            clinOrderSaveVoList.add(orderSaveVo);
         }

         if (StringUtils.isNotBlank(orderSaveVo.getDrugClassCode()) && orderSaveVo.getDrugClassCode().equals("03")) {
            TdPaOrderAgent orderAgentTemp = new TdPaOrderAgentVo();
            BeanUtils.copyProperties(tdPaOrderAgentVo, orderAgentTemp);
            orderAgentTemp.setAdmissionNo(orderSaveVo.getPatientId());
            orderAgentTemp.setOrderNo(orderSaveVo.getOrderNo());
            orderAgentTemp.setOrderGroupNo(orderSaveVo.getOrderGroupNo());
            orderAgentTemp.setOrderSortNumber(orderSaveVo.getOrderSortNumber());
            orderAgentTemp.setOrderGroupSortNumber(orderSaveVo.getOrderGroupSortNumber());
            tdPaOrderAgentList.add(orderAgentTemp);
         }
      }

      List<TdPaApplyForm> tdPaApplyFormList = new ArrayList(1);
      List<TdPaApplyFormItem> tdPaApplyFormItemList = new ArrayList(1);
      List<TdPaApplyFormDetail> tdPaApplyFormDetailList = new ArrayList(1);
      this.handelOrderGroup(drugOrderSaveVoMainList, drugOrderSaveVoSubListMap, clinOrderSaveVoList, orderList, orderItemList, orderDetailList, drugDoseVoList, visitinfo, tdPaApplyFormList, tdPaApplyFormItemList, tdPaApplyFormDetailList, operatingRoomFlag);
      if (!orderList.isEmpty()) {
         this.tdPaOrderMapper.insertList(orderList);
      }

      if (!orderItemList.isEmpty()) {
         this.tdPaOrderItemService.insertList(orderItemList);
      }

      if (!orderDetailList.isEmpty()) {
         this.orderDetailService.insertList(orderDetailList);
      }

      if (!tdPaApplyFormList.isEmpty()) {
         this.tdPaApplyFormService.insertTdPaApplyFormList(tdPaApplyFormList);
      }

      if (!tdPaApplyFormItemList.isEmpty()) {
         this.tdPaApplyFormItemService.insertTdPaApplyFormItemList(tdPaApplyFormItemList);
      }

      if (!tdPaApplyFormDetailList.isEmpty()) {
         this.tdPaApplyFormDetailService.insertTdPaApplyFormDetailList(tdPaApplyFormDetailList);
      }

      Date currDate = this.commonService.getDbSysdate();
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, -1, "保存", currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemList, -1, "保存", currDate, (String)null);
      List<OrderSaveVo> orderSaveVoList1 = (List)orderSaveVoList.stream().filter((t) -> t.getOrderFlagCd().equals("01")).collect(Collectors.toList());
      if (orderSaveVoList1 != null && !orderSaveVoList1.isEmpty()) {
         this.visitinfoService.updateOutInfoFromOrder((OrderSaveVo)orderSaveVoList1.get(0));
         this.mrHpService.updateOutInfoFromOrder((OrderSaveVo)orderSaveVoList1.get(0));
      }

      if (tdPaOrderAgentVo != null) {
         if (StringUtils.isNotBlank(tdPaOrderAgentVo.getWeight())) {
            this.visitinfoService.updateFromOrderAgent(tdPaOrderAgentVo, visitinfo.getPatientId());
         }

         Personalinfo personalinfo = new Personalinfo();
         personalinfo.setIdCard(tdPaOrderAgentVo.getIdCard());
         personalinfo.setSexCd(tdPaOrderAgentVo.getSexCd());
         personalinfo.setSex(tdPaOrderAgentVo.getSex());
         personalinfo.setAgeY(tdPaOrderAgentVo.getAge());
         personalinfo.setPatientId(visitinfo.getPatientId());
         this.personalinfoService.updatePersonalinfo(personalinfo);
         tdPaOrderAgentList.forEach((t) -> t.setId(SnowIdUtils.uniqueLong()));
         this.agentService.insertTdPaOrderAgentList(tdPaOrderAgentList);
      }

      if (CollectionUtils.isNotEmpty(drugDoseVoList)) {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<DrugDoseVo> drugDoseVos = this.drugStockService.updateDrugDoseByOrderDose(sysUser, drugDoseVoList, "1", ip);
         drugDoseVoList = drugDoseVos;
      }

      return drugDoseVoList;
   }

   private void getPrescriptionPrintData(List detailList, Visitinfo visitinfo, OrderSaveResVo orderSaveResVo, List drugCheckList) throws Exception {
      PersonalinfoVo personalinfo = this.personalinfoService.selectPersonalinfoVoById(visitinfo.getPatientId());
      List<PrescriptionPrintVo> psychotropicDrugs = new ArrayList(1);
      new ArrayList(1);
      if (drugCheckList != null && !drugCheckList.isEmpty()) {
         List<DrugCheck> sourceList = (List)drugCheckList.stream().filter((t) -> t.getDrugClassCode().equals("03")).collect(Collectors.toList());
         if (sourceList != null && !sourceList.isEmpty()) {
            List<String> drugCdList = (List)sourceList.stream().map((t) -> t.getDrugCode()).collect(Collectors.toList());
            List<TdPaOrderDetail> detailListsource = (List)detailList.stream().filter((t) -> drugCdList.contains(t.getChargeNo())).collect(Collectors.toList());
            List<List<TdPaOrderDetail>> pageList = new ArrayList(1);
            int a = detailListsource.size() % 5;
            int b = detailListsource.size() / 5;
            b = a > 0 ? b + 1 : b;

            for(int i = 0; i < b; ++i) {
               int begin = i * 5;
               int end = i * 5 + 5;
               end = end > drugCheckList.size() ? drugCheckList.size() : end;
               List<TdPaOrderDetail> tempList = detailListsource.subList(begin, end);
               pageList.add(tempList);
            }

            Date currDate = this.commonService.getDbSysdate();
            List<DiagInfo> diaginfoList = this.diagInfoService.selectDiagInfoByPatientId(visitinfo.getPatientId());

            for(List detailListTemp : pageList) {
               PrescriptionPrintVo prescriptionPrintVo = new PrescriptionPrintVo();
               prescriptionPrintVo.setPayTypeCd(visitinfo.getPayTypeCd());
               prescriptionPrintVo.setPayTypeName(visitinfo.getPayTypeName());
               prescriptionPrintVo.setHealCardNo(personalinfo.getHealCardNo());
               prescriptionPrintVo.setPatientId(visitinfo.getPatientId());
               prescriptionPrintVo.setPatientName(personalinfo.getPatientName());
               prescriptionPrintVo.setSexCd(personalinfo.getSexCd());
               String age = AgeUtil.getAgeStr(personalinfo.getAgeY(), personalinfo.getAgeM(), personalinfo.getAgeD(), personalinfo.getAgeH(), personalinfo.getAgeMi());
               prescriptionPrintVo.setAge(age);
               prescriptionPrintVo.setInpNo(visitinfo.getInpNo());
               prescriptionPrintVo.setDeptName(visitinfo.getDeptName());
               prescriptionPrintVo.setWardName(visitinfo.getWardName());
               prescriptionPrintVo.setBedNo(visitinfo.getBedNo());
               prescriptionPrintVo.setOrderStartDateY(DateUtils.parseDateToStr(DateUtils.YYYY, currDate));
               String addr = personalinfo.getNowAddrPro() + personalinfo.getNowAddrFlagty() + personalinfo.getNowAddrCou() + personalinfo.getNowAddrTown() + personalinfo.getNowAddrPost() + personalinfo.getNowAddrHouseNo();
               prescriptionPrintVo.setAddr(addr);
               prescriptionPrintVo.setTel(personalinfo.getPatientTel());
               psychotropicDrugs.add(prescriptionPrintVo);
            }
         }
      }

   }

   private void handelOrderGroup(List drugOrderSaveVoMainList, Map drugOrderSaveVoSubListMap, List clinOrderSaveVoList, List orderList, List orderItemList, List orderDetailList, List drugDoseVoList, Visitinfo visitinfo, List tdPaApplyFormList, List tdPaApplyFormItemList, List tdPaApplyFormDetailList, String operatingRoomFlag) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      Date operationTime = this.commonService.getDbSysdate();
      String hospitalCode = user.getHospital().getOrgCode();

      for(OrderSaveVo orderSaveVoMain : drugOrderSaveVoMainList) {
         TdPaOrder order = new TdPaOrder();
         TdPaOrderItem orderItem = new TdPaOrderItem();
         List<OrderSaveVo> drugTempList = (List)drugOrderSaveVoSubListMap.get(orderSaveVoMain.getOrderNo());
         BeanUtils.copyProperties(orderSaveVoMain, order);
         BeanUtils.copyProperties(orderSaveVoMain, orderItem);
         this.setOrderProperties(orderSaveVoMain, visitinfo, user, basEmployee, operationTime, hospitalCode, order, operatingRoomFlag);
         this.setDrugOrderItemProperties(orderSaveVoMain, basEmployee, orderItem);
         TdPaOrderDetail orderDetail01 = new TdPaOrderDetail();
         BeanUtils.copyProperties(orderSaveVoMain, orderDetail01);
         this.setDrugOrderDetailProperties(orderSaveVoMain, orderDetail01);
         orderList.add(order);
         orderItemList.add(orderItem);
         orderDetailList.add(orderDetail01);
         Boolean drugFlag01 = orderSaveVoMain.getOrderClassCode().equals("01");
         Boolean updateDrugDoseFlag01 = StringUtils.isNotBlank(orderSaveVoMain.getOrderItemFlag()) && !orderSaveVoMain.getOrderItemFlag().equals("2") && !orderSaveVoMain.getOrderItemFlag().equals("3");
         if (drugFlag01 && updateDrugDoseFlag01) {
            DrugDoseVo drugDoseVo = this.getDrugDoseToday(orderSaveVoMain, orderDetail01);
            if (drugDoseVo != null) {
               drugDoseVo.setOrderNo(order.getOrderNo());
               drugDoseVoList.add(drugDoseVo);
            }
         }

         if (drugTempList != null && !drugTempList.isEmpty()) {
            for(OrderSaveVo orderSaveVo : drugTempList) {
               TdPaOrderDetail orderDetail = new TdPaOrderDetail();
               BeanUtils.copyProperties(orderSaveVo, orderDetail);
               this.setDrugOrderDetailProperties(orderSaveVo, orderDetail);
               orderDetailList.add(orderDetail);
               Boolean drugFlag = orderSaveVo.getOrderClassCode().equals("01");
               Boolean updateDrugDoseFlag = StringUtils.isNotBlank(orderSaveVo.getOrderItemFlag()) && !orderSaveVo.getOrderItemFlag().equals("2") && !orderSaveVo.getOrderItemFlag().equals("3");
               if (drugFlag && updateDrugDoseFlag) {
                  DrugDoseVo drugDoseVo = this.getDrugDoseToday(orderSaveVo, orderDetail);
                  if (drugDoseVo != null) {
                     drugDoseVo.setOrderNo(order.getOrderNo());
                     drugDoseVoList.add(drugDoseVo);
                  }
               }
            }
         }
      }

      TdPaApplyFormVo diagnosisComplaintFormVo = null;
      if (clinOrderSaveVoList != null && !clinOrderSaveVoList.isEmpty()) {
         diagnosisComplaintFormVo = this.tdPaApplyFormService.selectSelectPatDia(((OrderSaveVo)clinOrderSaveVoList.get(0)).getPatientId());
         if (StringUtils.isNotBlank(diagnosisComplaintFormVo.getComplaint()) && StringUtils.getStringLengthByByte(diagnosisComplaintFormVo.getComplaint()) > 100) {
            diagnosisComplaintFormVo.setComplaint(diagnosisComplaintFormVo.getComplaint().substring(0, 24));
         }

         if (StringUtils.isNotBlank(diagnosisComplaintFormVo.getMedicalRecordDigest()) && StringUtils.getStringLengthByByte(diagnosisComplaintFormVo.getMedicalRecordDigest()) > 4000) {
            diagnosisComplaintFormVo.setMedicalRecordDigest(diagnosisComplaintFormVo.getMedicalRecordDigest().substring(0, 1998));
         }
      }

      List<String> itemCdList = (List)clinOrderSaveVoList.stream().map((t) -> t.getCpNo()).collect(Collectors.toList());
      List<ClinItemMain> itemMainList = this.clinItemMainService.selectClinItemMainByItemCds(itemCdList);
      Map<String, ClinItemMain> itemMainMap = itemMainList != null && !itemMainList.isEmpty() ? (Map)itemMainList.stream().collect(Collectors.toMap((t) -> t.getItemCd(), Function.identity())) : null;
      List<ClinItemDetail> itemDetailList = this.clinItemDetailService.selectClinItemDetailByItemCds(itemCdList);
      Map<String, List<ClinItemDetail>> itemDetailListMap = (Map)itemDetailList.stream().collect(Collectors.groupingBy((t) -> t.getItemCd()));
      List<OrderSaveVo> examOrderSaveVoList = (List)clinOrderSaveVoList.stream().filter((t) -> t.getOrderClassCode().equals("02")).collect(Collectors.toList());
      List<OrderSaveVo> testOrderSaveVoList = (List)clinOrderSaveVoList.stream().filter((t) -> t.getOrderClassCode().equals("03")).collect(Collectors.toList());
      List<OrderSaveVo> otherSaveVoList = (List)clinOrderSaveVoList.stream().filter((t) -> !t.getOrderClassCode().equals("02") && !t.getOrderClassCode().equals("03")).collect(Collectors.toList());
      Map<String, List<OrderSaveVo>> examGroupMap = new HashMap(1);
      if (CollectionUtils.isNotEmpty(examOrderSaveVoList)) {
         for(OrderSaveVo t : examOrderSaveVoList) {
            ClinItemMain clinItemMain = (ClinItemMain)itemMainMap.get(t.getCpNo());
            String orderStartTimeStr = DateUtils.dateFormat(t.getOrderStartTime(), DateUtils.YYYY_MM_DD_HH_MM);
            String mapKey = orderStartTimeStr + "_" + t.getDocumentTypeNo() + "_" + t.getDetailPerformDepCode() + "_" + (StringUtils.isNotBlank(clinItemMain.getExamMachineCd()) ? clinItemMain.getExamMachineCd() : "");
            List<OrderSaveVo> examTempList = (List<OrderSaveVo>)(examGroupMap.get(mapKey) == null ? new ArrayList(1) : (List)examGroupMap.get(mapKey));
            examTempList.add(t);
            examGroupMap.put(mapKey, examTempList);
         }
      }

      Map<String, List<OrderSaveVo>> testGroupMap = new HashMap(1);
      if (CollectionUtils.isNotEmpty(testOrderSaveVoList)) {
         String syncStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0069");

         for(OrderSaveVo t : testOrderSaveVoList) {
            ClinItemMain clinItemMain = (ClinItemMain)itemMainMap.get(t.getCpNo());
            String orderStartTimeStr = DateUtils.dateFormat(t.getOrderStartTime(), DateUtils.YYYY_MM_DD_HH_MM);
            new String();
            String detailList;
            if (StringUtils.isNotEmpty(syncStr) && syncStr.equals("0")) {
               detailList = orderStartTimeStr + "_" + t.getDocumentTypeNo() + "_" + t.getDetailPerformDepCode() + "_" + (StringUtils.isNotBlank(clinItemMain.getExamMachineCd()) ? clinItemMain.getExamMachineCd() : "") + "_" + t.getSpecCd();
            } else {
               detailList = orderStartTimeStr + "_" + t.getDocumentTypeNo() + "_" + t.getDetailPerformDepCode() + "_" + (StringUtils.isNotBlank(clinItemMain.getExamMachineCd()) ? clinItemMain.getExamMachineCd() : "") + "_" + t.getSpecCd() + (StringUtils.isNotBlank(clinItemMain.getBarcodeClassCd()) ? clinItemMain.getBarcodeClassCd() : "");
            }

            List<OrderSaveVo> examTempList = (List<OrderSaveVo>)(testGroupMap.get(detailList) == null ? new ArrayList(1) : (List)testGroupMap.get(detailList));
            examTempList.add(t);
            testGroupMap.put(detailList, examTempList);
         }
      }

      examGroupMap.putAll(testGroupMap);
      if (!examGroupMap.isEmpty() && examGroupMap.size() > 0) {
         for(String mapKey : examGroupMap.keySet()) {
            List<OrderSaveVo> examTestTempList = (List)examGroupMap.get(mapKey);
            OrderSaveVo orderSaveVo = (OrderSaveVo)examTestTempList.get(0);
            TdPaOrder order = new TdPaOrder();
            BeanUtils.copyProperties(orderSaveVo, order);
            this.setOrderProperties(orderSaveVo, visitinfo, user, basEmployee, operationTime, hospitalCode, order, operatingRoomFlag);
            List<TdPaOrderDetail> clinOrderDetailList = new ArrayList(1);
            List<TdPaOrderItem> clinOrderItemList = new ArrayList(1);
            List<DrugAndClin> drugAndClinNameList = new ArrayList(1);
            Map<String, OrderSaveVo> applyFormItemSaveVoMap = new HashMap(1);

            for(int j = 0; j < examTestTempList.size(); ++j) {
               OrderSaveVo t = (OrderSaveVo)examTestTempList.get(j);
               TdPaOrderItem orderItem = new TdPaOrderItem();
               BeanUtils.copyProperties(t, orderItem);
               this.setClinOrderItemProperties(t, basEmployee, orderItem);
               orderItem.setOrderNo(order.getOrderNo());
               orderItem.setOrderSortNumber(OrderUtil.getNumStr(j + 1));
               DrugAndClin applyFormItemClin = new DrugAndClin();
               applyFormItemClin.setCpName(t.getCpName());
               drugAndClinNameList.add(applyFormItemClin);
               applyFormItemSaveVoMap.put(orderItem.getOrderNo() + orderItem.getOrderSortNumber(), t);
               List<ClinItemDetail> detailList = (List)itemDetailListMap.get(t.getCpNo());
               if (detailList != null && !detailList.isEmpty()) {
                  for(int i = 0; i < detailList.size(); ++i) {
                     ClinItemDetail detail = (ClinItemDetail)detailList.get(i);
                     TdPaOrderDetail orderDetailTemp = new TdPaOrderDetail();
                     BeanUtils.copyProperties(t, orderDetailTemp);
                     this.setClinOrderDetailProperties(t, orderDetailTemp, detail);
                     orderDetailTemp.setOrderSortNumber(orderItem.getOrderSortNumber());
                     orderDetailTemp.setOrderGroupSortNumber(OrderUtil.getNumStr(i + 1));
                     orderDetailTemp.setOrderNo(orderItem.getOrderNo());
                     orderDetailList.add(orderDetailTemp);
                     clinOrderDetailList.add(orderDetailTemp);
                  }
               }

               if (detailList == null || detailList != null && detailList.isEmpty()) {
                  orderItem.setPriceFlag("0");
                  TdPaOrderDetail orderDetail = this.getOrderDetailNNNNNN(t);
                  orderDetailList.add(orderDetail);
               }

               clinOrderItemList.add(orderItem);
               orderItemList.add(orderItem);
            }

            TdPaApplyForm tdPaApplyForm = this.tdPaApplyFormService.genTdPaApplyForm(order, clinOrderItemList, clinOrderDetailList, orderSaveVo, visitinfo, tdPaApplyFormList, tdPaApplyFormItemList, tdPaApplyFormDetailList, applyFormItemSaveVoMap);
            tdPaApplyForm.setDiagnosisCode(StringUtils.isEmpty(orderSaveVo.getDiagnosisCode()) ? diagnosisComplaintFormVo.getDiagnosisCode() : orderSaveVo.getDiagnosisCode());
            tdPaApplyForm.setDiagnosisName(StringUtils.isEmpty(orderSaveVo.getDiagnosisName()) ? diagnosisComplaintFormVo.getDiagnosisName() : orderSaveVo.getDiagnosisName());
            tdPaApplyForm.setComplaint(diagnosisComplaintFormVo.getComplaint());
            tdPaApplyForm.setMedicalRecordDigest(StringUtils.isEmpty(orderSaveVo.getMedicalRecordDigest()) ? diagnosisComplaintFormVo.getMedicalRecordDigest() : orderSaveVo.getMedicalRecordDigest());
            String purposeExamination = this.tdPaApplyFormService.getPurposeExamination(drugAndClinNameList);
            tdPaApplyForm.setPurposeExamination(StringUtils.isEmpty(orderSaveVo.getPurposeExamination()) ? purposeExamination : orderSaveVo.getPurposeExamination());
            String applyFormTypeCode = itemMainMap.get(orderSaveVo.getCpNo()) != null ? ((ClinItemMain)itemMainMap.get(orderSaveVo.getCpNo())).getDocumentTypeNo() : null;
            tdPaApplyForm.setApplyFormTypeCode(StringUtils.isBlank(tdPaApplyForm.getApplyFormTypeCode()) ? applyFormTypeCode : tdPaApplyForm.getApplyFormTypeCode());
            order.setApplyFormNo(tdPaApplyForm.getApplyFormNo());
            orderList.add(order);
         }
      }

      for(OrderSaveVo orderSaveVo : otherSaveVoList) {
         List<TdPaOrderDetail> clinOrderDetailList = new ArrayList();
         TdPaOrder order = new TdPaOrder();
         TdPaOrderItem orderItem = new TdPaOrderItem();
         BeanUtils.copyProperties(orderSaveVo, order);
         BeanUtils.copyProperties(orderSaveVo, orderItem);
         this.setOrderProperties(orderSaveVo, visitinfo, user, basEmployee, operationTime, hospitalCode, order, operatingRoomFlag);
         this.setClinOrderItemProperties(orderSaveVo, basEmployee, orderItem);
         List<ClinItemDetail> detailList = (List)itemDetailListMap.get(orderSaveVo.getCpNo());
         if (detailList != null && !detailList.isEmpty()) {
            for(int i = 0; i < detailList.size(); ++i) {
               ClinItemDetail detail = (ClinItemDetail)detailList.get(i);
               TdPaOrderDetail orderDetailTemp = new TdPaOrderDetail();
               BeanUtils.copyProperties(orderSaveVo, orderDetailTemp);
               this.setClinOrderDetailProperties(orderSaveVo, orderDetailTemp, detail);
               orderDetailTemp.setOrderGroupSortNumber(OrderUtil.getNumStr(i + 1));
               orderDetailList.add(orderDetailTemp);
               clinOrderDetailList.add(orderDetailTemp);
            }
         }

         if (detailList == null || detailList != null && detailList.isEmpty()) {
            orderItem.setPriceFlag("0");
            TdPaOrderDetail orderDetail = this.getOrderDetailNNNNNN(orderSaveVo);
            orderDetailList.add(orderDetail);
         }

         orderList.add(order);
         orderItemList.add(orderItem);
      }

   }

   private DrugDoseVo getDrugDoseToday(OrderSaveVo orderSaveVo, TdPaOrderDetail orderDetail) throws Exception {
      BigDecimal orderDose = orderDetail.getOrderTotalDose().negate();
      BigDecimal firstPerformNum = orderSaveVo.getFirstPerformNum();
      if (orderSaveVo.getOrderType().equals("1") && firstPerformNum != null && StringUtils.isNotBlank(orderDetail.getOrderFreq())) {
         Integer freqValue = orderSaveVo.getFreqValue();
         Integer firstPerformNumInt = firstPerformNum.intValue();
         if (freqValue >= firstPerformNumInt) {
            try {
               orderDose = orderDetail.getOrderTotalDose().divide(new BigDecimal(freqValue + "")).multiply(firstPerformNum);
               if (freqValue > firstPerformNumInt && orderDose.scale() > 0) {
                  orderDose = orderDose.setScale(0, 0);
               }
            } catch (ArithmeticException var8) {
               this.log.debug("医嘱开立扣除虚存，提前领药，总量被医生修改过的，总量除以频次除不尽报错，首日次数大于0的，一日总量");
               orderDose = firstPerformNum.compareTo(BigDecimal.ZERO) > 0 ? orderDetail.getOrderTotalDose() : BigDecimal.ZERO;
            }

            orderDose = orderDose.negate();
         }
      }

      String takeDrugMode = orderSaveVo.getTakeDrugMode();
      if (orderSaveVo.getOrderType().equals("1") && StringUtils.isNotBlank(takeDrugMode) && takeDrugMode.equals("2")) {
         orderDose = orderDose.add(orderDetail.getOrderTotalDose().negate());
      }

      DrugDoseVo drugDoseVo = null;
      BigDecimal orderDoseAbs = orderDose.abs();
      if (orderDoseAbs.compareTo(BigDecimal.ZERO) > 0) {
         drugDoseVo = new DrugDoseVo(orderSaveVo.getCpId(), orderDetail.getDetailPerformDepCode(), orderDetail.getChargeNo(), orderDetail.getStockNo(), orderDose, orderDetail.getPrice());
      }

      return drugDoseVo;
   }

   public DrugDoseVo getDrugDoseToday(TdPaOrderItem orderItem, TdPaOrderDetail orderDetail) throws Exception {
      BigDecimal orderDose = orderDetail.getOrderTotalDose();
      BigDecimal firstPerformNum = orderDetail.getFirstPerformNum();
      if (orderDetail.getOrderType().equals("1") && firstPerformNum != null && StringUtils.isNotBlank(orderDetail.getOrderFreq())) {
         List<String> freqCdList = new ArrayList(1);
         freqCdList.add(orderDetail.getOrderFreq());
         List<OrderFreq> orderFreqList = this.orderFreqService.selectByFreqCdList(freqCdList);
         OrderFreq orderFreq = CollectionUtils.isNotEmpty(orderFreqList) ? (OrderFreq)orderFreqList.get(0) : null;
         Integer freqValue = orderFreq.getFreqValue();
         Integer firstPerformNumInt = firstPerformNum.intValue();
         if (freqValue >= firstPerformNumInt) {
            try {
               orderDose = orderDetail.getOrderTotalDose().divide(new BigDecimal(freqValue + "")).multiply(firstPerformNum);
               if (freqValue > firstPerformNumInt && orderDose.scale() > 0) {
                  orderDose = orderDose.setScale(0, 0);
               }
            } catch (ArithmeticException var11) {
               this.log.debug("医嘱删除，回退虚存，提前领药，总量被医生修改过的，总量除以频次除不尽报错，首日次数大于0的，回退一日总量*2");
               orderDose = firstPerformNum.compareTo(BigDecimal.ZERO) > 0 ? orderDose : BigDecimal.ZERO;
            }
         }
      }

      String takeDrugMode = orderItem.getTakeDrugMode();
      if (orderDetail.getOrderType().equals("1") && StringUtils.isNotBlank(takeDrugMode) && takeDrugMode.equals("2")) {
         orderDose = orderDose.add(orderDetail.getOrderTotalDose());
      }

      DrugDoseVo drugDoseVo = null;
      BigDecimal orderDoseAbs = orderDose.abs();
      if (orderDoseAbs.compareTo(BigDecimal.ZERO) > 0) {
         drugDoseVo = new DrugDoseVo(orderDetail.getDetailPerformDepCode() + orderDetail.getStockNo(), orderDetail.getDetailPerformDepCode(), orderDetail.getChargeNo(), orderDetail.getStockNo(), orderDose, orderDetail.getPrice());
         drugDoseVo.setOrderNo(orderDetail.getOrderNo());
      }

      return drugDoseVo;
   }

   private DrugDoseVo getDrugDoseToday(OrderSaveVo orderSaveVo) throws Exception {
      BigDecimal orderDose = orderSaveVo.getOrderDose();
      BigDecimal firstPerformNum = orderSaveVo.getFirstPerformNum();
      if (orderSaveVo.getOrderType().equals("1") && firstPerformNum != null && StringUtils.isNotBlank(orderSaveVo.getOrderFreq())) {
         Integer freqValue = orderSaveVo.getFreqValue();
         Integer firstPerformNumInt = firstPerformNum.intValue();
         if (freqValue >= firstPerformNumInt) {
            try {
               orderDose = orderSaveVo.getOrderDose().divide(new BigDecimal(freqValue + "")).multiply(firstPerformNum);
               if (freqValue > firstPerformNumInt && orderDose.scale() > 0) {
                  orderDose = orderDose.setScale(0, 0);
               }
            } catch (ArithmeticException var7) {
               this.log.debug("西药保存时判断虚存数量，提前领药，总量被医生修改过的，总量除以频次除不尽报错，首日次数大于0的，一日总量*2");
               orderDose = firstPerformNum.compareTo(BigDecimal.ZERO) > 0 ? orderSaveVo.getOrderDose() : BigDecimal.ZERO;
            }
         }
      }

      String takeDrugMode = orderSaveVo.getTakeDrugMode();
      if (orderSaveVo.getOrderType().equals("1") && StringUtils.isNotBlank(takeDrugMode) && takeDrugMode.equals("2")) {
         orderDose = orderDose.add(orderSaveVo.getOrderDose());
      }

      DrugDoseVo drugDoseVo = new DrugDoseVo(orderSaveVo.getCpId(), orderSaveVo.getDetailPerformDepCode(), orderSaveVo.getCpNo(), orderSaveVo.getStockNo(), orderDose, orderSaveVo.getPrice());
      return drugDoseVo;
   }

   private TdPaOrderDetail getOrderDetailNNNNNN(OrderSaveVo orderSaveVo) {
      TdPaOrderDetail tdPaOrderDetail = new TdPaOrderDetail();
      BeanUtils.copyProperties(orderSaveVo, tdPaOrderDetail);
      tdPaOrderDetail.setId(SnowIdUtils.uniqueLong());
      tdPaOrderDetail.setChargeName(orderSaveVo.getCpName());
      tdPaOrderDetail.setChargeNo("NNNNNN");
      return tdPaOrderDetail;
   }

   private void setOrderProperties(OrderSaveVo orderSaveVo, Visitinfo visitinfo, SysUser user, BasEmployee basEmployee, Date operationTime, String hospitalCode, TdPaOrder order, String operatingRoomFlag) {
      order.setHospitalCode(hospitalCode);
      order.setCaseNo(visitinfo.getRecordNo());
      order.setAdmissionNo(visitinfo.getInpNo());
      order.setHospitalizedCount(visitinfo.getVisitId());
      order.setPatientWardNo(visitinfo.getAreaCode());
      order.setPatientDepCode(visitinfo.getDeptCd());
      order.setPatientDepName(visitinfo.getDeptName());
      order.setOrderDoctorCode(basEmployee.getEmplNumber());
      order.setOrderDoctorNo(basEmployee.getEmplNumber());
      order.setOrderDoctorName(basEmployee.getEmplName());
      order.setOrderDoctorDepCode(user.getDept().getDeptCode());
      order.setOrderDoctorDepName(user.getDept().getDeptName());
      order.setResidentDoctorNo(visitinfo.getResDocCd());
      order.setResidentDoctorName(visitinfo.getResDocName());
      order.setRespNurseCode(visitinfo.getDutyNurCd());
      order.setRespNurseName(visitinfo.getDutyNurName());
      order.setPerformWardNo(user.getDept().getDeptCode());
      order.setPerformDepCode(user.getDept().getDeptCode());
      order.setPerformDepName(user.getDept().getDeptName());
      order.setOrderPerformFlag("0");
      if (StringUtils.isNotBlank(operatingRoomFlag) && operatingRoomFlag.equals("1")) {
         order.setOrderPerformFlag("3");
      }

      this.getOrderPerformFlag(order, orderSaveVo, visitinfo);
      order.setOperatorNo(basEmployee.getEmplNumber());
      order.setOperatorName(basEmployee.getEmplName());
      order.setOperationTime(operationTime);
      order.setBedNo(visitinfo.getBedNo());
   }

   private void setDrugOrderItemProperties(OrderSaveVo orderSaveVo, BasEmployee basEmployee, TdPaOrderItem orderItem) {
      orderItem.setCpNo("01");
      orderItem.setCpName("药疗");
      orderItem.setOrderStartDoc(basEmployee.getEmplNumber());
      orderItem.setOrderStartDocName(basEmployee.getEmplName());
      orderItem.setOrderDoctorSignFlag("0");
      orderItem.setOutHavaDrugFlag(orderSaveVo.getOrderItemFlag());
      orderItem.setPerformStaffFlag(orderSaveVo.getExecutorFlag());
      orderItem.setOrderDoseItem(orderSaveVo.getOrderDose());
      orderItem.setUnitItem(orderSaveVo.getUnit());
      orderItem.setPriceItem(orderSaveVo.getPrice());
      orderItem.setOrderTotalItem(orderSaveVo.getOrderTotal());
      orderItem.setItemOrderUsageWay(orderSaveVo.getOrderUsageWay());
      orderItem.setItemOrderUsageWayName(orderSaveVo.getOrderUsageWayName());
      orderItem.setItemOrderFreq(orderSaveVo.getOrderFreq());
      orderItem.setItemOrderFreqName(orderSaveVo.getOrderFreqName());
      orderItem.setExecutorDptNo(orderSaveVo.getDetailPerformDepCode());
      orderItem.setOrderStatus("-1");
      orderItem.setHerbalFlag("0");
      orderItem.setLcljCpNo(orderSaveVo.getLcljCpNo());
      orderItem.setLcljCpName(orderSaveVo.getLcljCpName());
      orderItem.setCpStageCode(orderSaveVo.getCpStageCode());
      orderItem.setCpItemCode(orderSaveVo.getCpItemCode());
   }

   private void setDrugOrderDetailProperties(OrderSaveVo orderSaveVo, TdPaOrderDetail orderDetail) {
      orderDetail.setId(SnowIdUtils.uniqueLong());
      orderDetail.setChargeNo(orderSaveVo.getCpNo());
      orderDetail.setChargeName(orderSaveVo.getCpName());
      orderDetail.setStandardCode(orderSaveVo.getCpNo());
      orderDetail.setStandardName(orderSaveVo.getCpName());
      orderDetail.setOrderTotalDose(orderSaveVo.getOrderDose());
      orderDetail.setHerbalFlag("0");
      orderDetail.setDrugForm(orderSaveVo.getDrugForm());
      orderDetail.setDrugClassCode(orderSaveVo.getDrugType());
      orderDetail.setDetailPerformDepCode(orderSaveVo.getDetailPerformDepCode());
      String patientSelfDrugFlag = orderSaveVo.getOrderFlagCd().equals("06") ? "1" : null;
      switch (orderSaveVo.getOrderItemFlag()) {
         case "2":
            patientSelfDrugFlag = "3";
            break;
         case "3":
            patientSelfDrugFlag = "4";
            break;
         case "4":
            patientSelfDrugFlag = "2";
      }

      orderDetail.setPatientSelfDrugFlag(patientSelfDrugFlag);
      orderDetail.setFirstDoubleFlag("0");
      if (StringUtils.isNotBlank(orderSaveVo.getTakeDrugMode()) && orderSaveVo.getTakeDrugMode().equals("2")) {
         orderDetail.setFirstDoubleFlag("1");
      }

      if (orderSaveVo.getFirstPerformNum() != null) {
         BigDecimal firstPerformTotalDose = orderSaveVo.getOrderActualUsage().multiply(orderSaveVo.getFirstPerformNum()).setScale(2, 1);
         orderDetail.setFirstPerformTotalDose(firstPerformTotalDose);
      }

      orderDetail.setPharmacopoeiaNo(orderDetail.getChargeNo());
      distributionFlag = StringUtils.isNotBlank(orderSaveVo.getOrderItemFlag()) && orderSaveVo.getOrderItemFlag().equals("6") ? "1" : "0";
      orderDetail.setDistributionFlag(distributionFlag);
      orderDetail.setHygienicMaterialFlag("0");
      orderDetail.setCpNo(orderSaveVo.getLcljCpNo());
      orderDetail.setCpName(orderSaveVo.getLcljCpName());
      orderDetail.setCpStageCode(orderSaveVo.getCpStageCode());
      orderDetail.setCpItemCode(orderSaveVo.getCpItemCode());
   }

   private void setClinOrderItemProperties(OrderSaveVo orderSaveVo, BasEmployee basEmployee, TdPaOrderItem orderItem) {
      orderItem.setOrderStartDoc(basEmployee.getEmplNumber());
      orderItem.setOrderStartDocName(basEmployee.getEmplName());
      orderItem.setOrderDoctorSignFlag("0");
      orderItem.setOutHavaDrugFlag(orderSaveVo.getOrderItemFlag());
      String transferOutFlag = !orderSaveVo.getOrderFlagCd().equals("01") && !orderSaveVo.getOrderFlagCd().equals("01") ? "0" : "1";
      orderItem.setTransferOutFlag(transferOutFlag);
      orderItem.setPerformStaffFlag(orderSaveVo.getExecutorFlag());
      orderItem.setOrderDoseItem(orderSaveVo.getOrderDose());
      orderItem.setUnitItem(orderSaveVo.getUnit());
      orderItem.setPriceItem(orderSaveVo.getPrice());
      orderItem.setOrderTotalItem(orderSaveVo.getOrderTotal());
      orderItem.setItemOrderUsageWay(orderSaveVo.getOrderUsageWay());
      orderItem.setItemOrderUsageWayName(orderSaveVo.getOrderUsageWayName());
      orderItem.setItemOrderFreq(orderSaveVo.getOrderFreq());
      orderItem.setItemOrderFreqName(orderSaveVo.getOrderFreqName());
      orderItem.setExecutorDptNo(orderSaveVo.getDetailPerformDepCode());
      orderItem.setOrderStatus("-1");
      orderItem.setHerbalFlag("0");
      orderItem.setOederDesc(orderSaveVo.getDoctorInstructions());
   }

   private void setClinOrderDetailProperties(OrderSaveVo orderSaveVo, TdPaOrderDetail orderDetail, ClinItemDetail itemDetail) {
      orderDetail.setId(SnowIdUtils.uniqueLong());
      orderDetail.setChargeNo(itemDetail.getChargeCode());
      orderDetail.setChargeName(itemDetail.getChargeName());
      orderDetail.setStandardCode(itemDetail.getStandardCode());
      orderDetail.setStandardName(itemDetail.getStandardName());
      orderDetail.setHerbalFlag("0");
      orderDetail.setSubjectFlag(itemDetail.getSubFlag());
      orderDetail.setDetailPerformDepCode(orderSaveVo.getDetailPerformDepCode());
      String patientSelfDrugFlag = orderSaveVo.getOrderFlagCd().equals("06") ? "1" : null;
      switch (orderSaveVo.getOrderItemFlag()) {
         case "2":
            patientSelfDrugFlag = "3";
            break;
         case "3":
            patientSelfDrugFlag = "4";
            break;
         case "4":
            patientSelfDrugFlag = "2";
      }

      orderDetail.setPatientSelfDrugFlag(patientSelfDrugFlag);
      orderDetail.setHygienicMaterialFlag(orderSaveVo.getOrderClassCode().equals("材料") ? "1" : "0");
      orderDetail.setMasterOrder(itemDetail.getSubFlag());
      orderDetail.setCpNo(orderSaveVo.getLcljCpNo());
      orderDetail.setCpName(orderSaveVo.getLcljCpName());
      orderDetail.setCpStageCode(orderSaveVo.getCpStageCode());
      orderDetail.setCpItemCode(orderSaveVo.getCpItemCode());
      orderDetail.setDoctorInstructions(itemDetail.getRemark());
   }

   private void getOrderPerformFlag(TdPaOrder order, OrderSaveVo orderSaveVo, Visitinfo visitinfo) {
      String auditDepFlag = orderSaveVo.getAuditDepFlag();
      String performDepFlag = orderSaveVo.getPerformDepFlag();
      if (StringUtils.isNotBlank(auditDepFlag) && auditDepFlag.equals("1")) {
         order.setPerformWardNo(orderSaveVo.getAuditDepCode());
         order.setPerformDepCode(orderSaveVo.getAuditDepCode());
         order.setPerformDepName(orderSaveVo.getAuditDepName());
         order.setOrderPerformFlag("3");
      }

      if (StringUtils.isNotBlank(performDepFlag) && performDepFlag.equals("1")) {
         order.setPerformWardNo(orderSaveVo.getPerformDepCode());
         order.setPerformDepCode(orderSaveVo.getPerformDepCode());
         order.setPerformDepName(orderSaveVo.getPerformDepName());
         order.setOrderPerformFlag("1");
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void commitTdPaOrders(OrderCommitVo orderCommitVo, List orderItemList, Visitinfo visitinfo) throws Exception {
      Date currDate = orderCommitVo.getSubmitTime() != null ? orderCommitVo.getSubmitTime() : this.commonService.getDbSysdate();
      List<String> orderNoList = (List)orderItemList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      TdPaOrderItemVo param = new TdPaOrderItemVo();
      param.setOrderNoList(orderNoList);
      param.setOrderStatus("0");
      param.setSubmitDoctorNo(basEmployee.getEmplNumber());
      param.setSubmitDoctorName(basEmployee.getEmplName());
      param.setSubmitTime(currDate);
      this.tdPaOrderItemService.updateOrderStatus(param);
      List<TdPaOrderDetailVo> detailList = this.orderDetailService.selectDetailEventCode(orderNoList);
      List<PatEvent> patEventList = new ArrayList(1);
      List<String> evenCodeList = (List)detailList.stream().filter((t) -> StringUtils.isNotBlank(t.getEmrEventCd())).map((t) -> t.getEmrEventCd()).distinct().collect(Collectors.toList());
      List<QcAgiEven> qcAgiEvenList = this.qcAgiEvenService.selectListByEvenCodes(evenCodeList);
      Map<String, QcAgiEven> qcAgiEvenMap = (Map)qcAgiEvenList.stream().collect(Collectors.toMap((t) -> t.getEvenCode(), Function.identity()));

      for(TdPaOrderDetailVo detailVo : detailList) {
         if (StringUtils.isNotBlank(detailVo.getEmrEventCd())) {
            PatEvent patEvent = new PatEvent();
            patEvent.setId(SnowIdUtils.uniqueLong());
            patEvent.setOrgCd(visitinfo.getOrgCd());
            patEvent.setInpNo(visitinfo.getInpNo());
            patEvent.setPatientId(visitinfo.getPatientId());
            patEvent.setPatientName(visitinfo.getPatientName());
            patEvent.setEventCode(detailVo.getEmrEventCd());
            String eventName = qcAgiEvenMap.get(detailVo.getEmrEventCd()) != null ? ((QcAgiEven)qcAgiEvenMap.get(detailVo.getEmrEventCd())).getEvenName() : null;
            patEvent.setEventName(eventName);
            patEvent.setBeginTime(currDate);
            patEvent.setEventNo(detailVo.getOrderNo());
            patEvent.setPreDocCd(basEmployee.getEmplNumber());
            patEvent.setEventSource("HIS");
            patEvent.setDelFlag("0");
            patEventList.add(patEvent);
         }
      }

      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, 0, "提交", currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemList, 0, "提交", currDate, (String)null);
      this.tdPaOrderSignMainService.addTdPaOrderOperationSign(orderItemList, orderCommitVo, visitinfo, 0, "提交");
      if (!patEventList.isEmpty()) {
         this.patEventService.insertList(patEventList);
      }

      List<TdPaApplyForm> applyFormList = this.tdPaApplyFormService.selectListByOrderNos(orderNoList);
      if (applyFormList != null && !applyFormList.isEmpty()) {
         List<String> applyFormNoList = (List)applyFormList.stream().map(TdPaApplyForm::getApplyFormNo).distinct().collect(Collectors.toList());
         this.tdPaApplyFormService.updateStatusByApplyFormNos(applyFormNoList, "1");
      }

      List<TdPaOrder> orderList = this.tdPaOrderMapper.selectByOrderNoList(orderNoList);
      this.tdMsgSendMainService.operMsgs(orderNoList, SecurityUtils.getLoginUser().getUser());
      this.tdMsgSendMainService.addMsgs(orderList, visitinfo, 0, "提交");
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void cancelCommitTdPaOrders(OrderCommitVo orderCommitVo, List orderItemList, Visitinfo visitinfo, String operatorDesc, List applyFormList, List operationApplyList, List caConsApplyList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<String> orderNoList = (List)orderItemList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
      TdPaOrderItemVo param = new TdPaOrderItemVo();
      param.setOrderNoList(orderNoList);
      this.tdPaOrderItemService.updateCancelCommit(param);
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, -1, "保存", currDate, operatorDesc);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemList, -2, "撤销提交", currDate, operatorDesc);
      this.patEventService.delPatientEventByOrderNos(orderNoList);
      this.emrTaskInfoService.updateTaskInfoByEventNo(orderNoList);
      if (applyFormList != null && !applyFormList.isEmpty()) {
         List<String> applyFormNoList = (List)applyFormList.stream().map(TdPaApplyForm::getApplyFormNo).distinct().collect(Collectors.toList());
         this.tdPaApplyFormService.updateStatusByApplyFormNos(applyFormNoList, "2");
      }

      if (operationApplyList != null && !operationApplyList.isEmpty()) {
         List<String> applyFormNoList = (List)operationApplyList.stream().map(TdCaOperationApply::getApplyFormNo).distinct().collect(Collectors.toList());
         this.tdCaOperationApplyService.updateStatusByIds(applyFormNoList, "03");
      }

      if (caConsApplyList != null && !caConsApplyList.isEmpty()) {
         List<Long> caConsApplyIds = (List)caConsApplyList.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
         this.tdCaConsApplyService.updateStatusByIds(caConsApplyIds, "05");
      }

      this.tdMsgSendMainService.revokeMsg(orderNoList, SecurityUtils.getLoginUser().getUser());
   }

   public Map selectOrderDictList(String patientId) throws Exception {
      this.log.info("selectOrderDictList======1==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      Map<String, Object> result = new HashMap();
      List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(user.getHospital().getOrgCode(), user.getDept().getDeptCode());
      result.put("pharmacyList", pharmacyList);
      this.log.info("selectOrderDictList======2=领药药房查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      VisitinfoPersonalVo visitinfoPersonalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
      if (pharmacyList != null && pharmacyList.size() > 0) {
         List<String> execDeptCdList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
         String expenseCategoryNo = visitinfoPersonalVo.getPayTypeCd();
         this.drugAndClinService.syncDrugAndClinAllToEs(execDeptCdList, "0", expenseCategoryNo, "1");
      }

      this.log.info("selectOrderDictList======3=创建登录用户的医嘱开立项目索引，并同步数据结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      String str = this.sysEmrConfigService.selectSysEmrConfigByKey("0036");
      String[] dictType = str.split(",");
      List<SysDictData> dictList = this.sysDictDataService.selectDictDataListByType(dictType);
      Map<String, List<SysDictData>> dictMap = (Map)dictList.stream().collect(Collectors.groupingBy((t) -> t.getDictType()));

      for(String key : dictMap.keySet()) {
         result.put(key, dictMap.get(key));
      }

      this.log.info("selectOrderDictList======4=字典表查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(patientId);
      if (babyInfoList != null && !babyInfoList.isEmpty()) {
         List<BabyInfo> babyList = new ArrayList();
         BabyInfo babyInfo = new BabyInfo();
         babyInfo.setPatBabyId(patientId);
         babyInfo.setBabyName(visitinfoPersonalVo.getPatientName());
         babyList.add(babyInfo);
         babyList.addAll(babyInfoList);
         result.put("babyList", babyList);
      }

      this.log.info("selectOrderDictList======5=医嘱对象查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<TmPmOrderStatus> statusList = this.tmPmOrderStatusService.selectTmPmOrderStatusList(new TmPmOrderStatus());
      result.put("statusList", statusList);
      this.log.info("selectOrderDictList======6=医嘱状态查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      TmPmOrderStatus statusAll = new TmPmOrderStatus("0,1,2,3,4,5,6,7,8,10,11,12", "全部", "全部");
      TmPmOrderStatus status1 = new TmPmOrderStatus("0,1,2,3", "当前在用", "当前在用");
      TmPmOrderStatus status2 = new TmPmOrderStatus("4,5,6,7,8,11", "历史在用", "历史在用");
      TmPmOrderStatus status3 = new TmPmOrderStatus("3", "在执行", "在执行");
      TmPmOrderStatus status4 = new TmPmOrderStatus("4,5,8", "已停止", "已停止");
      TmPmOrderStatus status5 = new TmPmOrderStatus("11,12", "已作废", "已作废");
      List<TmPmOrderStatus> statusList2 = Arrays.asList(statusAll, status1, status2, status3, status4, status5);
      result.put("statusList2", statusList2);
      this.log.info("selectOrderDictList======7=医嘱状态处理结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      OrderFreq orderFreq = new OrderFreq();
      orderFreq.setDocCd(basEmployee.getEmplNumber());
      orderFreq.setEnabled("1");
      List<OrderFreq> freqList = this.orderFreqService.selectUseTimeOrderFreqList(orderFreq);
      result.put("freqList", freqList);
      result.put("freqList_la", freqList.stream().filter((t) -> t.getOrderType().equals("0") || t.getOrderType().equals("1")).collect(Collectors.toList()));
      result.put("freqList_ta", freqList.stream().filter((t) -> t.getOrderType().equals("0") || t.getOrderType().equals("2")).collect(Collectors.toList()));
      result.put("freqList_lw", freqList.stream().filter((t) -> (t.getOrderType().equals("0") || t.getOrderType().equals("1")) && (t.getDrugType().equals("0") || t.getDrugType().equals("1"))).collect(Collectors.toList()));
      result.put("freqList_tw", freqList.stream().filter((t) -> (t.getOrderType().equals("0") || t.getOrderType().equals("2")) && (t.getDrugType().equals("0") || t.getDrugType().equals("1"))).collect(Collectors.toList()));
      result.put("freqList_lc", freqList.stream().filter((t) -> (t.getOrderType().equals("0") || t.getOrderType().equals("1")) && (t.getDrugType().equals("0") || t.getDrugType().equals("2"))).collect(Collectors.toList()));
      result.put("freqList_tc", freqList.stream().filter((t) -> (t.getOrderType().equals("0") || t.getOrderType().equals("2")) && (t.getDrugType().equals("0") || t.getDrugType().equals("2"))).collect(Collectors.toList()));
      this.log.info("selectOrderDictList======8=频率查询处理结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      OrderSig orderSig = new OrderSig();
      orderSig.setDocCd(basEmployee.getEmplNumber());
      orderSig.setEnabled("1");
      List<OrderSig> sigList = this.orderSigService.selectUseTimeOrderSigList(orderSig);
      result.put("sigList", sigList);
      result.put("sigList_la", sigList.stream().filter((t) -> t.getOrderType().equals("0") || t.getOrderType().equals("1")).collect(Collectors.toList()));
      result.put("sigList_ta", sigList.stream().filter((t) -> t.getOrderType().equals("0") || t.getOrderType().equals("2")).collect(Collectors.toList()));
      result.put("sigList_lw", sigList.stream().filter((t) -> (t.getOrderType().equals("0") || t.getOrderType().equals("1")) && (t.getDrugType().equals("0") || t.getDrugType().equals("1"))).collect(Collectors.toList()));
      result.put("sigList_tw", sigList.stream().filter((t) -> (t.getOrderType().equals("0") || t.getOrderType().equals("2")) && (t.getDrugType().equals("0") || t.getDrugType().equals("1"))).collect(Collectors.toList()));
      result.put("sigList_lc", sigList.stream().filter((t) -> (t.getOrderType().equals("0") || t.getOrderType().equals("1")) && (t.getDrugType().equals("0") || t.getDrugType().equals("2"))).collect(Collectors.toList()));
      result.put("sigList_tc", sigList.stream().filter((t) -> (t.getOrderType().equals("0") || t.getOrderType().equals("2")) && (t.getDrugType().equals("0") || t.getDrugType().equals("2"))).collect(Collectors.toList()));
      this.log.info("selectOrderDictList======9=用法查询处理结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      TdPaItemDocQuery tdPaItemDocQuery = new TdPaItemDocQuery();
      tdPaItemDocQuery.setDocCd(basEmployee.getEmplNumber());
      tdPaItemDocQuery.setOrderFlag("1");
      List<TdPaItemDocQuery> queryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
      result.put("order_query", queryList != null && !queryList.isEmpty() ? queryList.stream().filter((s) -> s.getOrderFlag().equals("1")).collect(Collectors.toList()) : new ArrayList());
      result.put("herb_query", queryList != null && !queryList.isEmpty() ? queryList.stream().filter((s) -> s.getOrderFlag().equals("2")).collect(Collectors.toList()) : new ArrayList());
      this.log.info("selectOrderDictList======10=查询当前登录医师的查询状态结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      tdPaItemDocQuery.setDocCd(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber());
      tdPaItemDocQuery.setOrderFlag("19");
      List<TdPaItemDocQuery> queryList19 = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
      if (queryList != null) {
         result.put("orderHeight", queryList19 != null && !queryList19.isEmpty() ? ((TdPaItemDocQuery)queryList19.get(0)).getQueryStatus() : "");
      } else {
         result.put("orderHeight", "");
      }

      TdPaOrder param = new TdPaOrder();
      param.setPatientId(patientId);
      List<TdPaOrder> orderList = this.selectTdPaOrderList(param);
      Map<String, List<TdPaOrder>> orderDoctorNoMap = (Map)orderList.stream().collect(Collectors.groupingBy((t) -> t.getOrderDoctorNo()));
      List<String> orderDoctorNoList = new ArrayList(orderDoctorNoMap.keySet());
      List<SysDictData> orderDoctorList = new ArrayList(orderDoctorNoList.size());
      orderDoctorNoList.stream().forEach((t) -> {
         SysDictData sysDictData = new SysDictData();
         String orderDoctorName = ((TdPaOrder)((List)orderDoctorNoMap.get(t)).get(0)).getOrderDoctorName();
         sysDictData.setDictLabel(orderDoctorName);
         sysDictData.setDictValue(t);
         orderDoctorList.add(sysDictData);
      });
      result.put("orderDoctorList", orderDoctorList);
      this.log.info("selectOrderDictList======11=开嘱医师查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      Map<String, List<TdPaOrder>> depCodeMap = (Map)orderList.stream().collect(Collectors.groupingBy((t) -> t.getOrderDoctorDepCode()));
      List<String> depCodeList = new ArrayList(depCodeMap.keySet());
      List<SysDictData> orderDepCdList = new ArrayList(depCodeList.size());
      depCodeList.stream().forEach((t) -> {
         SysDictData sysDictData = new SysDictData();
         String depName = ((TdPaOrder)((List)depCodeMap.get(t)).get(0)).getOrderDoctorDepName();
         sysDictData.setDictLabel(depName);
         sysDictData.setDictValue(t);
         orderDepCdList.add(sysDictData);
      });
      this.log.info("selectOrderDictList======12=开嘱科室查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      result.put("orderDepList", orderDepCdList);
      List<SysDept> deptList = this.sysDeptService.selectOrgDeptList(new SysDept());
      result.put("deptList", deptList);
      SysDept sysDept = new SysDept();
      sysDept.setDeptCode(user.getDept().getDeptCode());
      List<SysDept> zyDeptList = this.sysDeptService.selectDeptZyList(sysDept);
      result.put("zyDeptList", zyDeptList);
      sysDept = new SysDept();
      sysDept.setDeptCode(user.getDept().getDeptCode());
      List<SysDept> sysDeptList = this.sysDeptService.selectDeptList(sysDept);
      result.put("defaultDrugstore", ((SysDept)sysDeptList.get(0)).getDefaultDrugstore());
      result.put("currentDept", user.getDept());
      this.log.info("selectOrderDictList======13=查询当前登录账户所在院区的所有科室列表查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      String passFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0042");
      result.put("passFlag", passFlag);
      this.log.info("selectOrderDictList======14=passFlag查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      this.log.debug("从数据库中读取 outHosItemList");
      List<DrugAndClin> list = this.clinItemMainService.selectClinList(user.getHospital().getOrgCode(), user.getDept().getDeptCode(), user.getBasEmployee().getEmplNumber(), "01");
      result.put("outHosItemList", list);
      this.log.info("selectOrderDictList======15=查询出院医嘱的开立项目查询结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
      result.put("visitinfo", personalVo);
      this.log.info("selectOrderDictList======16=查询当前患者就诊信息结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      DiagInfo diagParam = new DiagInfo();
      diagParam.setPatientId(patientId);
      diagParam.setDiagTypeCd("02");
      List<DiagInfo> diagInfoList = this.diagInfoService.selectDiagInfoList(diagParam);
      String diagCd = diagInfoList != null && !diagInfoList.isEmpty() ? StringUtils.join((Iterable)diagInfoList.stream().map((s) -> s.getDiagCd()).collect(Collectors.toList()), ",") : "";
      String diagName = diagInfoList != null && !diagInfoList.isEmpty() ? StringUtils.join((Iterable)diagInfoList.stream().map((s) -> s.getDiagName()).collect(Collectors.toList()), ",") : "";
      result.put("inhosDiagCd", diagCd);
      result.put("inhosDiagName", diagName);
      this.log.info("selectOrderDictList======17=查询当前患者入院诊断信息结束=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      String skipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0046");
      result.put("skipFlag", skipFlag);
      String cdssFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0041");
      result.put("cdssFlag", cdssFlag);
      String cdssManufactor = this.sysEmrConfigService.selectSysEmrConfigByKey("0039");
      result.put("cdssManufactor", cdssManufactor);
      if (StringUtils.isNotBlank(cdssFlag) && cdssFlag.equals("1") && cdssManufactor.equals("XYT")) {
         String cdssBaseUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("003901");
         String cdssBhtKey = this.sysEmrConfigService.selectSysEmrConfigByKey("003902");
         Map<String, String> yxtHeaderMap = RequestUtil.getHeaderMap(cdssBhtKey, user.getUserName(), user.getNickName(), user.getDept().getDeptName());
         result.put("cdssBaseUrl", cdssBaseUrl);
         result.put("cdssBhtKey", cdssBhtKey);
         result.put("DOCTOR_ID", yxtHeaderMap.get("DOCTOR_ID"));
         result.put("DOCTOR_NAME", yxtHeaderMap.get("DOCTOR_NAME"));
         result.put("DOCTOR_DEPT", yxtHeaderMap.get("DOCTOR_DEPT"));
      }

      String drugFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0073");
      result.put("drugFlag", drugFlag);
      String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
      result.put("lcljFlag", lcljFlag);
      result.put("cpFlag", visitinfoPersonalVo.getCpFlag());
      String ybscFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("000501");
      result.put("ybscFlag", ybscFlag);
      String changePharmacyFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("00070101");
      result.put("changePharmacyFlag", changePharmacyFlag);
      this.log.info("selectOrderDictList======18=一些配置=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<String> typeCodeList = new ArrayList();
      typeCodeList.add("100001001");
      typeCodeList.add("100001002");
      typeCodeList.add("100001003");
      List<PrintTmplInfo> printTmplInfo = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
      result.put("printTmpl", printTmplInfo);
      List<PrintTmplInfo> testExamPrintTmplList = this.printTmplInfoService.selectListByParentTypeCode("100017");
      result.put("testExamPrintTmplList", testExamPrintTmplList);
      this.log.info("selectOrderDictList======19=打印模板=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      return result;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void cancelDoctorOrder(List orderItemList, Visitinfo visitinfo, OrderCommitVo orderCommitVo) throws Exception {
      Date currDate = orderCommitVo.getCancelTime() != null ? orderCommitVo.getCancelTime() : this.commonService.getDbSysdate();
      List<String> orderNoList = (List)orderItemList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      TdPaOrderItemVo param = new TdPaOrderItemVo();
      param.setOrderNoList(orderNoList);
      param.setOrderStatus("6");
      param.setOrderCancelTime(currDate);
      param.setOrderCancelDoc(basEmployee.getEmplNumber());
      param.setOrderCancelDocName(basEmployee.getEmplName());
      this.tdPaOrderItemService.updateOrderStatus(param);
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, 6, "取消", currDate, orderCommitVo.getOperatorDesc());
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemList, 6, "取消", currDate, orderCommitVo.getOperatorDesc());
      this.tdPaOrderSignMainService.addTdPaOrderOperationSign(orderItemList, orderCommitVo, visitinfo, 6, "取消");
      this.patEventService.delPatientEventByOrderNos(orderNoList);
      this.emrTaskInfoService.updateTaskInfoByEventNo(orderNoList);
      List<TdPaApplyForm> applyFormList = this.tdPaApplyFormService.selectListByOrderNos(orderNoList);
      if (applyFormList != null && !applyFormList.isEmpty()) {
         List<String> applyFormNoList = (List)applyFormList.stream().map(TdPaApplyForm::getApplyFormNo).distinct().collect(Collectors.toList());
         this.tdPaApplyFormService.updateStatusByApplyFormNos(applyFormNoList, "2");
      }

      List<TdCaOperationApply> operationApplyList = this.tdCaOperationApplyService.selectListByOrderNos(orderNoList);
      if (operationApplyList != null && !operationApplyList.isEmpty()) {
         List<String> applyFormNoList = (List)operationApplyList.stream().map(TdCaOperationApply::getApplyFormNo).distinct().collect(Collectors.toList());
         this.tdCaOperationApplyService.updateStatusByIds(applyFormNoList, "03");
      }

      List<TdPaOrder> orderList = this.tdPaOrderMapper.selectByOrderNoList(orderNoList);
      List<Long> caConsApplyIdList = orderList != null ? (List)orderList.stream().filter((t) -> t.getOrderClassCode().equals("04")).map((t) -> Long.valueOf(t.getApplyFormNo())).collect(Collectors.toList()) : null;
      List<TdCaConsApply> caConsApplyList = this.tdCaConsApplyService.selectListByIds(caConsApplyIdList);
      if (caConsApplyList != null && !caConsApplyList.isEmpty()) {
         List<Long> caConsApplyIds = (List)caConsApplyList.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
         this.tdCaConsApplyService.updateStatusByIds(caConsApplyIds, "05");
      }

      List<String> orderStatus4List = new ArrayList(1);

      for(TdPaOrderItem item : orderItemList) {
         String orderStatus = item.getOrderStatus();
         if (orderStatus.equals("4")) {
            orderStatus4List.add(item.getOrderNo());
         }
      }

      if (orderStatus4List.size() > 0) {
         this.tdMsgSendMainService.revokeMsg(orderStatus4List, SecurityUtils.getLoginUser().getUser());
      }

      this.tdMsgSendMainService.addMsgs(orderList, visitinfo, 6, "取消");
   }

   public List selectByOrderNoList(List orderNoList) throws Exception {
      return this.tdPaOrderMapper.selectByOrderNoList(orderNoList);
   }

   public List selectPastHerbTreeList(String patientId) throws Exception {
      List<TreeSelect> resultList = new ArrayList();
      List<Personalinfo> personList = this.personalinfoService.selectPersonListByPatientId(patientId);
      if (personList != null && !personList.isEmpty()) {
         List<String> patientIdList = (List)personList.stream().map((s) -> s.getPatientId()).collect(Collectors.toList());
         List<Visitinfo> visitinfoList = this.visitinfoService.selectVistinfoListByPatList(patientIdList);
         Map<String, List<Visitinfo>> visitinfoMapList = (Map)visitinfoList.stream().collect(Collectors.groupingBy((s) -> s.getPatientId()));
         List<TdPaOrderItem> orderList = this.tdPaOrderItemService.selectPastOrderListByPatList(patientIdList);
         Map<String, List<TdPaOrderItem>> orderMapList = new HashMap();
         if (orderList != null && !orderList.isEmpty()) {
            orderMapList = (Map)orderList.stream().collect(Collectors.groupingBy((s) -> s.getPatientId()));
         }

         for(String patId : visitinfoMapList.keySet()) {
            List<Visitinfo> visitinfos = (List)visitinfoMapList.get(patId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String label = ((Visitinfo)visitinfos.get(0)).getDeptName() + "(" + sdf.format(((Visitinfo)visitinfos.get(0)).getInhosTime()) + "~" + sdf.format(((Visitinfo)visitinfos.get(0)).getOutTime()) + ")";
            TreeSelect treeSelect = new TreeSelect(patId, label);
            List<TdPaOrderItem> orderItems = (List)orderMapList.get(patId);
            if (orderItems != null && !orderItems.isEmpty()) {
               List<TreeSelect> childList = new ArrayList();

               for(TdPaOrderItem tdPaOrderItem : orderItems) {
                  String lab = sdf.format(tdPaOrderItem.getOrderStartTime()) + " " + (StringUtils.isEmpty(tdPaOrderItem.getPrescribeName()) ? "" : tdPaOrderItem.getPrescribeName()) + " " + tdPaOrderItem.getOrderStartDocName();
                  TreeSelect tree = new TreeSelect(tdPaOrderItem.getOrderNo(), lab);
                  childList.add(tree);
               }

               treeSelect.setChildren(childList);
            }

            resultList.add(treeSelect);
         }
      }

      return resultList;
   }

   public void insertList(List list) throws Exception {
      this.tdPaOrderMapper.insertList(list);
   }

   public List selectUnclaimedDrugList(String patientId, List orderNoList) throws Exception {
      return this.tdPaOrderMapper.selectUnclaimedDrugList(patientId, orderNoList);
   }

   public List selectUnExecPerformList(String patientId, List orderNoList) throws Exception {
      return this.tdPaOrderMapper.selectUnExecPerformList(patientId, orderNoList);
   }

   public Map selectDrugBBList(String inpNo) throws Exception {
      Map<String, Object> result = new HashMap();
      Map<String, Object> param = new HashMap();
      param.put("inpNo", inpNo);
      param.put("mrHpFee", new HashMap());
      this.mrHpFeeService.selectMrHpFeeListByProc(param);
      result.put("table", param.get("mrHpFee"));
      return result;
   }

   public AjaxResult checkPrintOrder(List detailList, AjaxResult ajaxResult) throws Exception {
      Map<String, List<PrintOrderDataListDetail>> map = (Map)detailList.stream().collect(Collectors.groupingBy(PrintOrderDataListDetail::getOrderNo));
      Set<String> orderNoSet = map.keySet();
      int count = this.tdPaOrderMapper.checkCountPrintOrder(orderNoSet);
      if (count > 5) {
         ajaxResult = AjaxResult.error("处方打印一次不能超过5条");
         return ajaxResult;
      } else {
         int i = 0;
         StringBuffer stri = new StringBuffer();
         int j = 0;
         StringBuffer strj = new StringBuffer();
         int k = 0;
         StringBuffer strk = new StringBuffer();
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         Map<String, List<PrintOrderDataListDetail>> orderGroupMap = (Map)detailList.stream().collect(Collectors.groupingBy(PrintOrderDataListDetail::getOrderGroupNo));

         for(String orderNo : orderGroupMap.keySet()) {
            String drugClassCode = "00";
            List<PrintOrderDataListDetail> printOrderDataListDetails = (List)orderGroupMap.get(orderNo);
            List<String> orderCpNameList = (List)printOrderDataListDetails.stream().map(PrintOrderDataListDetail::getCpName).collect(Collectors.toList());
            String cpName = String.join(",", orderCpNameList);
            List<String> setCpSet = new ArrayList();

            for(PrintOrderDataListDetail detail : printOrderDataListDetails) {
               DrugCheck temp = this.drugStockService.antiUseAimIsOpenType(sysUser, detail.getCpNo());
               if (temp != null) {
                  setCpSet.add(temp.getDrugClassCode());
               }
            }

            if (!setCpSet.isEmpty()) {
               Collections.sort(setCpSet, Collections.reverseOrder());
               drugClassCode = (String)setCpSet.get(0);
            }

            if (drugClassCode.equals("03")) {
               strj.append(cpName);
               ++j;
            } else if (drugClassCode.equals("05")) {
               strk.append(cpName);
               ++k;
            } else {
               stri.append(cpName);
               ++i;
            }
         }

         if (i != 0 && j != 0) {
            String msg = "普通处方不能和精麻药品：" + strj.toString() + " 一起打印！";
            ajaxResult = AjaxResult.error(msg);
            return ajaxResult;
         } else if (i != 0 && k != 0) {
            String msg = "普通处方不能和精二药品：" + strk.toString() + " 一起打印！";
            ajaxResult = AjaxResult.error(msg);
            return ajaxResult;
         } else if (j != 0 && k != 0) {
            String msg = "精麻药品：" + strj.toString() + "和精二药品：" + strk.toString() + " 不能一起打印！";
            ajaxResult = AjaxResult.error(msg);
            return ajaxResult;
         } else {
            if (i > 0) {
               ajaxResult.put("data", 1);
               ajaxResult.put("typeCode", "100001001");
            } else if (j > 0) {
               ajaxResult.put("data", 2);
               ajaxResult.put("typeCode", "100001002");
            } else if (k > 0) {
               ajaxResult.put("data", 3);
               ajaxResult.put("typeCode", "100001003");
            }

            return ajaxResult;
         }
      }
   }

   public Map printOrderData(PrintOrderDataListReq req) throws Exception {
      Map<String, Object> dataMap = new HashMap();
      List<PrintOrderDataListDetailVo> list = new ArrayList();
      List<PrintOrderDataListDetail> detailList = req.getDetailList();
      String admissionNo = req.getAdmissionNo();
      if (detailList == null && detailList.size() == 0) {
         this.log.error("处方集合为空,跳过");
         return dataMap;
      } else {
         Map<String, List<PrintOrderDataListDetail>> map = (Map)detailList.stream().collect(Collectors.groupingBy(PrintOrderDataListDetail::getOrderNo));
         Set<String> orderNoSet = map.keySet();
         int count = this.tdPaOrderMapper.checkCountPrintOrder(orderNoSet);
         if (count > 5) {
            this.log.error("处方打印一次超过5条,跳过");
            return dataMap;
         } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleDateFormat.format(new Date());
            MedicalinformationVo medicalinformationVo = this.tdPaOrderMapper.selectMedicalInfor(admissionNo);
            String age = AgeUtil.getAgeStr(medicalinformationVo.getAgeY(), medicalinformationVo.getAgeM(), medicalinformationVo.getAgeD(), medicalinformationVo.getAgeH(), medicalinformationVo.getAgeMi());
            medicalinformationVo.setPersonAge(age);
            String hospitalName = "";
            BigDecimal totalAmount = BigDecimal.ZERO;
            String agentName = "";
            String agentIdCard = "";
            String orderDoctorName = "";
            if (StringUtils.isEmpty(medicalinformationVo.getAdmittingDiagnosis())) {
               List<DiagInfo> diagInfo = this.visitinfoService.selectDiagInfoByAdmissionNo(admissionNo);
               if (diagInfo != null && diagInfo.size() > 0) {
                  DiagInfo info = (DiagInfo)diagInfo.get(0);
                  medicalinformationVo.setAdmittingDiagnosis(info.getDiagName());
               }
            }

            for(PrintOrderDataListDetail detail : detailList) {
               String orderNo = detail.getOrderNo();
               String orderGroupNo = detail.getOrderGroupNo();
               String orderGroupSortNumber = detail.getOrderGroupSortNumber();
               String orderSortNumber = detail.getOrderSortNumber();
               PrintOrderDataListDetailVo detailVo = this.tdPaOrderMapper.selectOrderDetail(orderNo, orderGroupNo, orderGroupSortNumber, orderSortNumber);
               BeanUtils.copyProperties(medicalinformationVo, detailVo);
               detailVo.setDatetime(dateStr);
               detailVo.setOrderNo(orderNo);
               PrintAgentVo printAgentVo = this.tdPaOrderMapper.selectAgentData(orderNo, orderGroupNo, orderGroupSortNumber, orderSortNumber);
               if (printAgentVo != null) {
                  detailVo.setAgentName(printAgentVo.getAgentName());
                  detailVo.setAgentIdCard(printAgentVo.getAgentIdCard());
                  agentName = printAgentVo.getAgentName();
                  agentIdCard = printAgentVo.getAgentIdCard();
               }

               hospitalName = detailVo.getHospitalName();
               totalAmount = detailVo.getOrderTotal() == null ? totalAmount.add(BigDecimal.ZERO) : totalAmount.add(detailVo.getOrderTotal());
               orderDoctorName = detailVo.getDoctorName();
               list.add(detailVo);
            }

            medicalinformationVo.setHospitalName(hospitalName);
            medicalinformationVo.setTotalAmount(totalAmount);
            medicalinformationVo.setDatetime(dateStr);
            medicalinformationVo.setAgentName(agentName);
            medicalinformationVo.setAgentIdCard(agentIdCard);
            medicalinformationVo.setOrderDoctorName(orderDoctorName);
            medicalinformationVo.setSize(count);
            List<MedicalinformationVo> voList = new ArrayList();
            voList.add(medicalinformationVo);
            dataMap.put("medicalInfor", voList);
            List<Map<String, Object>> orderDetail = new ArrayList();
            List<Map<String, Object>> orderDetailFX = new ArrayList();
            Map<String, List<PrintOrderDataListDetailVo>> detailVoMap = (Map)list.stream().collect(Collectors.groupingBy(PrintOrderDataListDetailVo::getOrderNo));
            List<Map<String, String>> freqList = new ArrayList();

            for(String orderNo : detailVoMap.keySet()) {
               List<PrintOrderDataListDetailVo> dataListDetailVos = (List)detailVoMap.get(orderNo);
               if (dataListDetailVos.size() == 1) {
                  Map<String, Object> detailMap = new HashMap();
                  PrintOrderDataListDetailVo detailVo = (PrintOrderDataListDetailVo)dataListDetailVos.get(0);
                  detailMap.put("orderNo", detailVo.getOrderNo());
                  detailMap.put("cpName", detailVo.getCpName());
                  detailMap.put("standard", detailVo.getStandard());
                  detailMap.put("orderActualUsage", detailVo.getOrderActualUsage());
                  detailMap.put("unit", detailVo.getUnit());
                  detailMap.put("orderTotalDose", detailVo.getOrderTotalDose());
                  detailMap.put("orderUsageWayName", detailVo.getOrderUsageWayName());
                  detailMap.put("orderFreqName", detailVo.getOrderFreqName());
                  detailMap.put("doctorInstructions", detailVo.getDoctorInstructions());
                  detailMap.put("usageUnit", detailVo.getUsageUnit());
                  detailMap.put("bedid", detailVo.getBedid());
                  orderDetail.add(detailMap);
               } else {
                  Map<String, String> mapFreq = new HashMap();
                  List<PrintOrderDataListDetailVo> listDetailVos = (List)detailVoMap.get(orderNo);
                  mapFreq.put("orderNoFX", orderNo);
                  mapFreq.put("orderUsageWayNameFX", ((PrintOrderDataListDetailVo)listDetailVos.get(0)).getOrderUsageWayName());
                  mapFreq.put("orderFreqNameFX", ((PrintOrderDataListDetailVo)listDetailVos.get(0)).getOrderFreqName());
                  mapFreq.put("doctorInstructionsFX", ((PrintOrderDataListDetailVo)listDetailVos.get(0)).getDoctorInstructions());
                  freqList.add(mapFreq);

                  for(PrintOrderDataListDetailVo vo : dataListDetailVos) {
                     Map<String, Object> detailMap = new HashMap();
                     detailMap.put("orderNoFX", vo.getOrderNo());
                     detailMap.put("cpNameFX", vo.getCpName());
                     detailMap.put("standardFX", vo.getStandard());
                     detailMap.put("orderActualUsageFX", vo.getOrderActualUsage());
                     detailMap.put("unitFX", vo.getUnit());
                     detailMap.put("usageUnitFX", vo.getUsageUnit());
                     detailMap.put("orderTotalDoseFX", vo.getOrderTotalDose());
                     orderDetailFX.add(detailMap);
                  }
               }
            }

            dataMap.put("freqDetail", freqList);
            dataMap.put("orderDetail", orderDetail);
            dataMap.put("orderDetailFX", orderDetailFX);
            return dataMap;
         }
      }
   }

   public String isStockNum(List orderSaveVoList) throws Exception {
      StringBuffer resSb = new StringBuffer();
      List<OrderSaveVo> orderDrugList = (List)orderSaveVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderClassCode()) && t.getOrderClassCode().equals("01") && StringUtils.isNotBlank(t.getOrderItemFlag()) && !t.getOrderItemFlag().equals("2")).collect(Collectors.toList());
      if (CollectionUtils.isNotEmpty(orderDrugList)) {
         List<DrugAndClin> list = new ArrayList(1);
         Map<String, List<OrderSaveVo>> orderDrugListMap = (Map)orderDrugList.stream().collect(Collectors.groupingBy((t) -> t.getDetailPerformDepCode()));

         for(String performDepCode : orderDrugListMap.keySet()) {
            List<String> cpNoList = (List)orderDrugList.stream().map((s) -> s.getCpNo().trim()).collect(Collectors.toList());
            List<DrugAndClin> listTemp = this.drugStockService.selectHisDrugStockList(cpNoList, performDepCode);
            if (CollectionUtils.isNotEmpty(listTemp)) {
               listTemp.stream().forEach((t) -> t.setPerformDepCode(performDepCode));
               list.addAll(listTemp);
            }
         }

         if (CollectionUtils.isNotEmpty(list)) {
            Map<String, DrugAndClin> drugDoseInfoMap = (Map)list.stream().collect(Collectors.toMap((t) -> t.getPerformDepCode() + t.getCpNo(), Function.identity()));
            Map<String, List<OrderSaveVo>> orderDrugMap = (Map)orderDrugList.stream().collect(Collectors.groupingBy((t) -> t.getDetailPerformDepCode() + t.getCpNo()));

            for(String performDeptCodeCpNo : orderDrugMap.keySet()) {
               List<OrderSaveVo> orderDrugListTemp = (List)orderDrugMap.get(performDeptCodeCpNo);
               BigDecimal orderDose = new BigDecimal("0");

               for(OrderSaveVo orderSaveVo : orderDrugListTemp) {
                  DrugDoseVo drugDoseVo = this.getDrugDoseToday(orderSaveVo);
                  orderDose = orderDose.add(drugDoseVo.getOrderDose());
               }

               DrugAndClin drugDoseInfo = (DrugAndClin)drugDoseInfoMap.get(performDeptCodeCpNo);
               BigDecimal drugDose = drugDoseInfo != null ? drugDoseInfo.getXcsl() : new BigDecimal(0);
               if (orderDose.compareTo(drugDose) > 0) {
                  resSb.append("药房：" + ((OrderSaveVo)orderDrugListTemp.get(0)).getDetailPerformDepName() + "的药品：" + ((OrderSaveVo)orderDrugListTemp.get(0)).getCpName() + "；");
               }
            }
         }
      }

      String resStr = resSb.toString();
      resStr = StringUtils.isNotBlank(resStr) ? resStr + "库存不足，请重新选择！" : null;
      return resStr;
   }

   public String verifyExpensedetailByOrderNo(List orderNoList) throws Exception {
      String resStr = null;
      List<ExpenseDetail> list = this.expenseDetailMapper.selectExpensedetailByOrderNo(orderNoList);
      Map<String, List<ExpenseDetail>> listMap = (Map<String, List<ExpenseDetail>>)(CollectionUtils.isNotEmpty(list) ? (Map)list.stream().collect(Collectors.groupingBy((t) -> t.getCopeNo())) : new HashMap(1));
      List<String> orderGroupNoList = new ArrayList(1);

      for(String orderNo : orderNoList) {
         List<ExpenseDetail> listTemp = (List)listMap.get(orderNo);
         BigDecimal totalSum = CollectionUtils.isNotEmpty(listTemp) ? (BigDecimal)list.stream().map(ExpenseDetail::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add) : null;
         boolean flag = totalSum == null || totalSum.compareTo(BigDecimal.ZERO) <= 0;
         if (!flag) {
            List<String> orderGroupNoListTemp = (List)listTemp.stream().map((t) -> t.getCopeGroup() != null ? t.getCopeGroup() + "" : null).distinct().collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(orderGroupNoListTemp)) {
               orderGroupNoList.addAll(orderGroupNoListTemp);
            }
         }
      }

      if (CollectionUtils.isNotEmpty(orderGroupNoList)) {
         resStr = String.join(",", orderGroupNoList);
      }

      return resStr;
   }

   public String isDrugStockNum(List orderDrugList) throws Exception {
      StringBuffer resSb = new StringBuffer();
      if (CollectionUtils.isNotEmpty(orderDrugList)) {
         List<DrugAndClin> list = new ArrayList(1);
         Map<String, List<TakeDrugListSaveVO>> orderDrugListMap = (Map)orderDrugList.stream().collect(Collectors.groupingBy(TakeDrugList::getPharmacyNo));

         for(String performDepCode : orderDrugListMap.keySet()) {
            List<DrugAndStockSearchReq> searchReqs = new ArrayList(1);

            for(TakeDrugListSaveVO saveVO : (List)orderDrugListMap.get(performDepCode)) {
               DrugAndStockSearchReq req = new DrugAndStockSearchReq();
               req.setDrugCd(saveVO.getPharmacopoeiaNo());
               req.setStockNo(saveVO.getDrugStockNo());
               searchReqs.add(req);
            }

            List<DrugAndClin> listTemp = this.drugStockService.selectHisDrugStockByStockList(performDepCode, searchReqs);
            if (CollectionUtils.isNotEmpty(listTemp)) {
               listTemp.stream().forEach((t) -> t.setPerformDepCode(performDepCode));
               list.addAll(listTemp);
            }
         }

         if (CollectionUtils.isNotEmpty(list)) {
            Map<String, DrugAndClin> drugDoseInfoMap = (Map)list.stream().collect(Collectors.toMap((t) -> t.getPerformDepCode() + t.getCpNo(), Function.identity()));
            Map<String, List<TakeDrugListSaveVO>> orderDrugMap = (Map)orderDrugList.stream().collect(Collectors.groupingBy((t) -> t.getPharmacyNo() + t.getPharmacopoeiaNo()));

            for(String performDeptCodeCpNo : orderDrugMap.keySet()) {
               List<TakeDrugListSaveVO> orderDrugListTemp = (List)orderDrugMap.get(performDeptCodeCpNo);
               BigDecimal orderDose = (BigDecimal)orderDrugListTemp.stream().map(TakeDrugList::getOrderDose).reduce(BigDecimal.ZERO, BigDecimal::add);
               DrugAndClin drugDoseInfo = (DrugAndClin)drugDoseInfoMap.get(performDeptCodeCpNo);
               BigDecimal drugDose = drugDoseInfo != null ? drugDoseInfo.getXcsl() : new BigDecimal(0);
               if (orderDose.compareTo(drugDose) > 0) {
                  resSb.append("药品：" + ((TakeDrugListSaveVO)orderDrugListTemp.get(0)).getDrugName() + "；");
               }
            }
         }
      }

      String resStr = resSb.toString();
      resStr = StringUtils.isNotBlank(resStr) ? resStr + "库存不足，请重新选择！" : null;
      return resStr;
   }
}
