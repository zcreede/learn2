package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.common.service.IProcService;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnResultVo;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnVo;
import com.emr.project.docOrder.domain.vo.OrderStopDoReturnVo;
import com.emr.project.docOrder.domain.vo.OrderStopSignVo;
import com.emr.project.docOrder.domain.vo.OrderStopVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.domain.vo.TdPaTakeDrugListVO;
import com.emr.project.docOrder.mapper.TdPaOrderDetailMapper;
import com.emr.project.docOrder.service.ITdMsgSendMainService;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderMessageService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.esSearch.domain.TmPaFreeze;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
public class TdPaOrderDetailServiceImpl implements ITdPaOrderDetailService {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private TdPaOrderDetailMapper tdPaOrderDetailMapper;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ITdPaOrderOperationLogService tdPaOrderOperationLogService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;
   @Autowired
   private ITdPaOrderSignMainService tdPaOrderSignMainService;
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private IProcService procService;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private ITdMsgSendMainService tdMsgSendMainService;
   @Autowired
   private ITdPaOrderMessageService tdPaOrderMessageService;

   public TdPaOrderDetail selectTdPaOrderDetailById(Long id) {
      return this.tdPaOrderDetailMapper.selectTdPaOrderDetailById(id);
   }

   public List selectTdPaOrderDetailList(TdPaOrderDetailVo tdPaOrderDetail) {
      return this.tdPaOrderDetailMapper.selectTdPaOrderDetailList(tdPaOrderDetail);
   }

   public int insertTdPaOrderDetail(TdPaOrderDetail tdPaOrderDetail) {
      return this.tdPaOrderDetailMapper.insertTdPaOrderDetail(tdPaOrderDetail);
   }

   public int updateTdPaOrderDetail(TdPaOrderDetail tdPaOrderDetail) {
      return this.tdPaOrderDetailMapper.updateTdPaOrderDetail(tdPaOrderDetail);
   }

   public int deleteTdPaOrderDetailByIds(Long[] ids) {
      return this.tdPaOrderDetailMapper.deleteTdPaOrderDetailByIds(ids);
   }

   public int deleteTdPaOrderDetailById(Long id) {
      return this.tdPaOrderDetailMapper.deleteTdPaOrderDetailById(id);
   }

   public void deleteTdPaOrderDetailByOrder(String orderNo) throws Exception {
      this.tdPaOrderDetailMapper.deleteTdPaOrderDetailByOrder(orderNo);
   }

   public List selectOrderDetailListByPat(String patientId) throws Exception {
      return this.tdPaOrderDetailMapper.selectOrderDetailListByPat(patientId);
   }

   public void insertList(List list) throws Exception {
      this.tdPaOrderDetailMapper.insertList(list);
   }

   public List selectByOrderNoList(List orderNoList) throws Exception {
      List<TdPaOrderDetail> list = null;
      if (CollectionUtils.isNotEmpty(orderNoList)) {
         orderNoList = (List)orderNoList.stream().distinct().collect(Collectors.toList());
         if (orderNoList.size() > 1000) {
            list = new ArrayList(1);
            int operNum = 500;
            Integer subStoreNumberSize = orderNoList.size();
            int a = subStoreNumberSize / operNum;
            int b = subStoreNumberSize % operNum;
            if (b > 0) {
               ++a;
            }

            for(int i = 0; i < a; ++i) {
               int beginIndex = i * operNum;
               int endIndex = (i + 1) * operNum;
               if (i + 1 == a) {
                  endIndex = subStoreNumberSize;
               }

               List<String> orderNoListSub = orderNoList.subList(beginIndex, endIndex);
               List<TdPaOrderDetail> listSub = this.tdPaOrderDetailMapper.selectByOrderNoList(orderNoListSub);
               list.addAll(listSub);
            }
         } else {
            list = this.tdPaOrderDetailMapper.selectByOrderNoList(orderNoList);
         }
      }

      return list;
   }

   public List selectByOrderNo(String orderNo) throws Exception {
      return this.tdPaOrderDetailMapper.selectByOrderNo(orderNo);
   }

   public void toDelTable(List list) throws Exception {
      if (list != null && !list.isEmpty()) {
         this.tdPaOrderDetailMapper.toDelTable(list);
      }

   }

   public void deleteTdPaOrderDetailByIds(List orderNoList) throws Exception {
      this.tdPaOrderDetailMapper.deleteTdPaOrderDetailByOrderNos(orderNoList);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public OrderStopDoReturnVo stopOrder(OrderStopSignVo orderStopSignVo, List orderItemList, String ip) throws Exception {
      return this.doStopOrder(orderStopSignVo, orderItemList, false, ip);
   }

   private OrderStopDoReturnVo doStopOrder(OrderStopSignVo orderStopSignVo, List orderItemList, boolean isStopAllFlag, String ip) throws Exception {
      OrderStopDoReturnVo orderStopDoReturnVo = new OrderStopDoReturnVo();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<OrderStopVo> orderStopVoList = orderStopSignVo.getOrderStopVoList();
      String patientId = ((TdPaOrderItem)orderItemList.get(0)).getPatientId();
      VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoById(patientId);
      List<String> eventList = new ArrayList(1);
      Map<String, List<TdPaOrderItem>> orderStatusItemMap = new HashMap(1);
      List<String> orderNoListNullify = new ArrayList(1);
      List<String> nlChargeNoList = this.tdPaOrderItemService.selectNursingLevelChargeNoList();
      List<String> nlOrderNoList = (List)orderItemList.stream().filter((t) -> t.getOrderClassCode().equals("05")).map((t) -> t.getOrderNo()).collect(Collectors.toList());
      List<TdPaOrderDetail> nlOrderDetailList = this.selectByOrderNoList(nlOrderNoList);
      Map<String, List<TdPaOrderDetail>> nlOrderDetailListMap = CollectionUtils.isNotEmpty(nlOrderDetailList) ? (Map)nlOrderDetailList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo())) : null;
      boolean nlFlag = false;
      List<TdPaOrderItem> autoFeeList = new ArrayList(1);

      for(TdPaOrderItem orderItem : orderItemList) {
         if (orderItem.getOrderType().equals("1") && orderItem.getOrderItemType().equals("3") && orderItem.getOrderStatus().equals("3")) {
            autoFeeList.add(orderItem);
         }

         List<String> orderFlagCdList = Arrays.asList("02");
         String orderStatus = null;
         switch (orderItem.getOrderStatus()) {
            case "-1":
               orderStatus = orderItem.getOrderType().equals("1") ? "4" : "8";
               if (isStopAllFlag && StringUtils.isNotBlank(orderItem.getOrderFlagCd()) && (orderItem.getOrderFlagCd().equals("01") || orderItem.getOrderFlagCd().equals("03"))) {
                  orderStatus = "0";
               }
               break;
            case "0":
               if (isStopAllFlag && orderFlagCdList.contains(orderItem.getOrderFlagCd()) && orderItem.getOrderType().equals("1")) {
                  orderStatus = "4";
               }
               break;
            case "2":
               if (isStopAllFlag) {
                  orderStatus = "4";
               } else {
                  orderNoListNullify.add(orderItem.getOrderNo());
                  orderStatus = "11";
               }
               break;
            case "1":
               orderStatus = "11";
               break;
            case "3":
               orderStatus = "4";
         }

         List<TdPaOrderItem> tempList = (List<TdPaOrderItem>)(orderStatusItemMap.get(orderStatus) == null ? new ArrayList(1) : (List)orderStatusItemMap.get(orderStatus));
         tempList.add(orderItem);
         orderStatusItemMap.put(orderStatus, tempList);
         if (StringUtils.isNotBlank(orderStatus) && orderStatus.equals("11")) {
            eventList.add(orderItem.getOrderNo());
         }

         if (nlOrderDetailListMap != null && nlOrderDetailListMap.get(orderItem.getOrderNo()) != null && orderStatus.equals("4")) {
            List<TdPaOrderDetail> nlDetailList = (List)nlOrderDetailListMap.get(orderItem.getOrderNo());
            List<String> nlDetailChargeNoList = (List)nlDetailList.stream().map((t) -> t.getChargeNo()).collect(Collectors.toList());
            boolean nlFlagSub = false;

            for(String chargeNo : nlDetailChargeNoList) {
               if (nlChargeNoList.contains(chargeNo)) {
                  nlFlagSub = true;
                  break;
               }
            }

            if (StringUtils.isNotBlank(orderItem.getOrderFlagCd()) && orderItem.getOrderFlagCd().equals("08") || nlFlagSub) {
               nlFlag = true;
            }
         }
      }

      Date currDate = this.commonService.getDbSysdate();
      List<TdPaOrderItemVo> orderItemVoList = new ArrayList(orderStopVoList.size());

      for(String orderStatus : orderStatusItemMap.keySet()) {
         int operationType = orderStatus.equals("11") ? 11 : 4;
         String operationTypeName = orderStatus.equals("11") ? "作废" : "停止";
         List<TdPaOrderItem> tempList = (List)orderStatusItemMap.get(orderStatus);
         List<String> orderNoList = (List)tempList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());

         for(TdPaOrderItem item : tempList) {
            TdPaOrderItemVo param = new TdPaOrderItemVo();
            param.setOrderNo(item.getOrderNo());
            param.setOrderStatus(orderStatus);
            if (!item.getOrderFlagCd().equals("03") && !item.getOrderFlagCd().equals("01") || !item.getOrderStatus().equals("-1")) {
               param.setOrderStopTime(item.getOrderStopTime());
               param.setOrderStopDoc(basEmployee.getEmplNumber());
               param.setOrderStopDocName(basEmployee.getEmplName());
            }

            if ((item.getOrderFlagCd().equals("01") || item.getOrderFlagCd().equals("02") || item.getOrderFlagCd().equals("03")) && StringUtils.isBlank(item.getSubmitDoctorNo()) && StringUtils.isBlank(item.getSubmitDoctorCode()) && StringUtils.isBlank(item.getSubmitDoctorName())) {
               param.setSubmitDoctorNo(item.getOrderStartDoc());
               param.setSubmitDoctorCode(item.getOrderStartDoc());
               param.setSubmitDoctorName(item.getOrderStartDocName());
               param.setSubmitTime(item.getOrderStartTime());
            }

            orderItemVoList.add(param);
         }

         this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, tempList, operationType, operationTypeName, currDate, (String)null);
         this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, tempList, operationType, operationTypeName, currDate, (String)null);
         this.tdPaOrderSignMainService.addTdPaOrderOperationSign(tempList, (OrderStopSignVo)orderStopSignVo, visitinfo, operationType, operationTypeName);
      }

      if (CollectionUtils.isNotEmpty(autoFeeList)) {
         for(TdPaOrderItem orderItem : autoFeeList) {
            Map<String, Object> procParam = new HashMap(1);
            procParam.put("vs_zyh", visitinfo.getInpNo());
            procParam.put("vs_yzbh", orderItem.getOrderNo());
            procParam.put("vs_yzxh", orderItem.getOrderSortNumber());
            procParam.put("vd_jzrq", orderItem.getOrderStopTime());
            procParam.put("vs_oper", SecurityUtils.getUsername());
            procParam.put("vi_ret", 4);
            procParam.put("vs_errtext", "");
            this.procService.orderAutoFee(procParam);
         }
      }

      List<DrugListAndPerformReturnVo> returnVoList = new ArrayList(1);
      if (!isStopAllFlag) {
         List<TdPaOrderDetailVo> orderPerformList = orderStopSignVo.getOrderPerformList();
         if (null != orderPerformList && orderPerformList.size() > 0) {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            this.tdPaOrderItemService.stopPerformList(orderPerformList, sysUser.getUserName(), sysUser.getNickName());
         }

         if (CollectionUtils.isNotEmpty(orderPerformList)) {
            for(TdPaOrderDetailVo perfromVo : orderPerformList) {
               DrugListAndPerformReturnVo returnVo = new DrugListAndPerformReturnVo();
               returnVo.setPerformListNo(perfromVo.getPerformListNo());
               returnVo.setPerformListSortNumber(Integer.valueOf(perfromVo.getPerformListSortNumber()));
               returnVo.setDrugReturnDose(perfromVo.getOrderDose());
               returnVoList.add(returnVo);
            }
         }
      }

      if (isStopAllFlag && CollectionUtils.isNotEmpty(orderNoListNullify)) {
         List<TdPaTakeDrugListVO> takeDrugListNullify = this.tdPaOrderService.selectUnclaimedDrugList(patientId, orderNoListNullify);
         List<TdPaOrderDetailVo> orderPerformListNullify = this.tdPaOrderService.selectUnExecPerformList(patientId, orderNoListNullify);
         if (CollectionUtils.isNotEmpty(takeDrugListNullify)) {
            for(TdPaTakeDrugListVO takeDrugListVO : takeDrugListNullify) {
               DrugListAndPerformReturnVo returnVo = new DrugListAndPerformReturnVo();
               returnVo.setId(takeDrugListVO.getId());
               returnVoList.add(returnVo);
            }
         }

         if (null != orderPerformListNullify && orderPerformListNullify.size() > 0) {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            this.tdPaOrderItemService.stopPerformList(orderPerformListNullify, sysUser.getUserName(), sysUser.getNickName());
         }
      }

      if (CollectionUtils.isNotEmpty(returnVoList)) {
         DrugListAndPerformReturnResultVo returnResultVo = this.takeDrugReturnService.doReturnDrug(returnVoList, ip);
         orderStopDoReturnVo.setReturnResultVo(returnResultVo);
      }

      this.tdPaOrderItemService.updateOrderStatusList(orderItemVoList);
      if (eventList != null && !eventList.isEmpty()) {
         this.patEventService.delPatientEventByOrderNos(eventList);
         this.emrTaskInfoService.updateTaskInfoByEventNo(eventList);
      }

      List<TdPaOrderItem> tempList = (List)orderStatusItemMap.get("11");
      if (tempList != null && !tempList.isEmpty()) {
         List<String> orderNoList = (List)tempList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
         this.patEventService.delPatientEventByOrderNos(orderNoList);
      }

      for(String orderStatus : orderStatusItemMap.keySet()) {
         int operationType = orderStatus.equals("11") ? 11 : 4;
         String operationTypeName = orderStatus.equals("11") ? "作废" : "停止";
         List<TdPaOrderItem> itemListtemp = (List)orderStatusItemMap.get(orderStatus);
         List<String> orderNoList = (List)itemListtemp.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
         List<TdPaOrder> orderList = this.tdPaOrderService.selectByOrderNoList(orderNoList);
         this.tdMsgSendMainService.addMsgs(orderList, visitinfo, operationType, operationTypeName);
      }

      if (nlFlag) {
         this.visitinfoService.updatePatientLevelCare(visitinfo, "99", "无");
      }

      return orderStopDoReturnVo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public OrderStopDoReturnVo stopAllOrder(Visitinfo visitinfo, OrderStopSignVo orderStopSignVo, List orderItemList, String ip) throws Exception {
      OrderStopDoReturnVo returnVo = null;
      Date stopTime = this.commonService.getDbSysdate();
      List<OrderStopVo> orderStopVoList = new ArrayList(orderItemList.size());
      orderItemList.stream().forEach((t) -> {
         t.setOrderStopTime(stopTime);
         OrderStopVo orderStopVo = new OrderStopVo(t.getOrderNo(), stopTime);
         orderStopVoList.add(orderStopVo);
      });
      orderStopSignVo.setOrderStopVoList(orderStopVoList);
      if (orderItemList != null && !orderItemList.isEmpty()) {
         returnVo = this.doStopOrder(orderStopSignVo, orderItemList, true, ip);
      }

      return returnVo;
   }

   public List getStopAllOrderList(Visitinfo visitinfo) throws Exception {
      List<TdPaOrderItem> orderItemList = new ArrayList(1);
      List<String> orderFlagCdList = Arrays.asList("01", "02", "03");
      List<String> orderFlagCdList2 = Arrays.asList("02");
      List<TdPaOrderItem> orderItemList1 = this.tdPaOrderItemService.selectByOrderFlagCdAndStatus(visitinfo.getPatientId(), "-1", orderFlagCdList);
      List<TdPaOrderItem> orderItemList11 = this.tdPaOrderItemService.selectByOrderFlagCdAndStatus(visitinfo.getPatientId(), "0", orderFlagCdList2);
      TdPaOrderItemVo param1 = new TdPaOrderItemVo();
      param1.setOrderType("1");
      param1.setPatientId(visitinfo.getPatientId());
      List<String> statusList1 = Arrays.asList("1", "2", "3");
      param1.setStateList(statusList1);
      List<TdPaOrderItem> orderItemList2 = this.tdPaOrderItemService.selectByStatusList(param1);
      if (orderItemList1 != null) {
         orderItemList.addAll(orderItemList1);
      }

      if (orderItemList11 != null) {
         orderItemList.addAll(orderItemList11);
      }

      if (orderItemList2 != null) {
         orderItemList.addAll(orderItemList2);
      }

      return orderItemList;
   }

   public List getStopAllOrderList(String patientId) throws Exception {
      List<TdPaOrderItem> orderItemList = new ArrayList(1);
      List<String> orderFlagCdList = Arrays.asList("01", "02", "03");
      List<TdPaOrderItem> orderItemList1 = this.tdPaOrderItemService.selectByOrderFlagCdAndStatus(patientId, "-1", orderFlagCdList);
      List<TdPaOrderItem> orderItemList2 = this.tdPaOrderItemService.selectStopAllOrderList(patientId);
      orderItemList.addAll(orderItemList1);
      orderItemList.addAll(orderItemList2);
      return orderItemList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void rearrange(Visitinfo visitinfo, List orderItemList, String ip) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      Integer reOrganizationNoMax = this.getMaxReOrganizationNo(visitinfo.getPatientId());
      int reOrganizationNo = reOrganizationNoMax != null ? reOrganizationNoMax + 1 : 1;
      this.getRearrangeOrderData(visitinfo, currDate, ip, reOrganizationNo);
      this.doRearrangeAddOrder(visitinfo, orderItemList, reOrganizationNo, currDate);
      this.doRearrangeStopOrder(visitinfo, orderItemList, currDate);
   }

   private void doRearrangeAddOrder(Visitinfo visitinfo, List orderItemList, int reOrganizationNo, Date currDate) throws Exception {
      Integer orderGroupNoMax = this.tdPaOrderItemService.selectPatientMaxGroupNo(visitinfo.getPatientId());
      Integer orderGroupNo = orderGroupNoMax != null ? orderGroupNoMax + 1 : 1;
      List<TdPaOrder> orderListNew = new ArrayList(1);
      List<TdPaOrderItem> orderItemListNew = new ArrayList(1);
      List<TdPaOrderDetail> orderDetailListNew = new ArrayList(1);
      List<String> orderNoList = (List)orderItemList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
      List<TdPaOrderDetailVo> orderPerformList = this.tdPaOrderService.selectUnExecPerformList(visitinfo.getPatientId(), orderNoList);
      List<TdPaTakeDrugListVO> takeDrugList = this.tdPaOrderService.selectUnclaimedDrugList(visitinfo.getPatientId(), orderNoList);
      List<Long> takeDrugListIds = CollectionUtils.isNotEmpty(takeDrugList) ? (List)takeDrugList.stream().map((t) -> t.getId()).collect(Collectors.toList()) : null;
      List<TmPaFreeze> freezeListDb = this.drugStockService.selectFreezeListByTakeDrugListIds(takeDrugListIds);
      List<TdPaOrder> orderList = this.tdPaOrderService.selectByOrderNoList(orderNoList);
      List<TdPaOrderDetail> detailList = this.selectByOrderNoList(orderNoList);
      Map<String, TdPaOrderItem> orderItemMap = (Map)orderItemList.stream().collect(Collectors.toMap((t) -> t.getOrderNo(), Function.identity()));
      Map<String, List<TdPaOrderDetail>> detailListMap = (Map)detailList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo()));
      List<TdPaOrderDetailVo> performUpdateList = new ArrayList();
      List<TdPaTakeDrugListVO> drugUpdateList = new ArrayList();
      List<TmPaFreeze> freezeListUpdate = new ArrayList(1);

      for(TdPaOrder order : orderList) {
         String orderNoOld = order.getOrderNo();
         String orderNoNew = DateUtils.getDateStr("");
         order.setOrderNo(orderNoNew);
         orderListNew.add(order);
         TdPaOrderItem orderItem = (TdPaOrderItem)orderItemMap.get(orderNoOld);
         TdPaOrderItem orderItemNew = new TdPaOrderItem();
         BeanUtils.copyProperties(orderItem, orderItemNew);
         orderItemNew.setOrderNo(orderNoNew);
         orderItemNew.setReOrganizationNo(reOrganizationNo);
         orderItemNew.setOrderGroupNo(orderGroupNo);
         orderItemListNew.add(orderItemNew);
         List<TdPaOrderDetailVo> performList = orderPerformList != null && !orderPerformList.isEmpty() ? (List)orderPerformList.stream().filter((s) -> s.getOrderNo().equals(orderNoOld)).collect(Collectors.toList()) : null;
         if (performList != null) {
            performList.stream().forEach((t) -> {
               t.setOrderNo(orderNoNew);
               t.setOrderGroupNoRe(orderGroupNo);
               performUpdateList.add(t);
            });
         }

         List<TdPaTakeDrugListVO> drugListVOS = takeDrugList != null && !takeDrugList.isEmpty() ? (List)takeDrugList.stream().filter((s) -> s.getOrderNo().equals(orderNoOld)).collect(Collectors.toList()) : null;
         if (drugListVOS != null) {
            drugListVOS.stream().forEach((t) -> {
               t.setOrderNo(orderNoNew);
               t.setOrderGroupNo(orderGroupNo);
               drugUpdateList.add(t);
            });
            List<Long> takeDrugListIdsTemp = (List)drugListVOS.stream().map((t) -> t.getId()).collect(Collectors.toList());
            List<TmPaFreeze> freezeListDbTemp = CollectionUtils.isNotEmpty(freezeListDb) ? (List)freezeListDb.stream().filter((t) -> takeDrugListIdsTemp.contains(t.getFreezeId())).collect(Collectors.toList()) : null;
            if (CollectionUtils.isNotEmpty(freezeListDbTemp)) {
               freezeListDbTemp.forEach((t) -> t.setFreezeSerial(orderNoNew));
               freezeListUpdate.addAll(freezeListDbTemp);
            }
         }

         List<TdPaOrderDetail> temp = (List)detailListMap.get(orderNoOld);
         temp.stream().forEach((t) -> {
            t.setId(SnowIdUtils.uniqueLong());
            t.setOrderNo(orderNoNew);
            t.setReOrganizationNo(reOrganizationNo);
            t.setSourceOrderNo(orderNoOld);
            t.setOrderGroupNo(orderGroupNo);
            orderDetailListNew.add(t);
         });
         orderGroupNo = orderGroupNo + 1;
         if (orderItem.getOrderClassCode().equals("01") && orderItem.getOrderStatus().equals("0")) {
            List<TmPaFreeze> freezeList = this.drugStockService.selectNewOrderFreezeList(orderNoOld);
            this.drugStockService.delFreezesByOrderNo(orderNoOld);
            freezeList.stream().forEach((t) -> t.setFreezeSerial(orderNoNew));
            this.drugStockService.batchInsertFreezes(freezeList);
         }
      }

      if (!orderListNew.isEmpty()) {
         this.tdPaOrderService.insertList(orderListNew);
      }

      if (!orderItemListNew.isEmpty()) {
         this.tdPaOrderItemService.insertList(orderItemListNew);
      }

      if (!orderDetailListNew.isEmpty()) {
         this.tdPaOrderDetailMapper.insertList(orderDetailListNew);
      }

      if (!performUpdateList.isEmpty()) {
         this.tdPaOrderDetailMapper.updateTdPaOrderPerformList(performUpdateList);
         this.tdPaOrderDetailMapper.updateTdPaOrderPerformDetailList(performUpdateList);
      }

      if (!drugUpdateList.isEmpty()) {
         this.tdPaOrderDetailMapper.drugUpdateList(drugUpdateList);
      }

      if (CollectionUtils.isNotEmpty(freezeListUpdate)) {
         this.drugStockService.updateFreezeOrderNoByRearrange(freezeListUpdate);
      }

      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemListNew, 3, "在执行", currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemListNew, 3, "在执行", currDate, (String)null);
   }

   private void doRearrangeStopOrder(Visitinfo visitinfo, List orderItemList, Date currDate) throws Exception {
      String dutyNurCd = visitinfo.getDutyNurCd();
      String dutyNurName = visitinfo.getDutyNurName();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<String> orderNoList = (List)orderItemList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
      TdPaOrderItemVo paOrderItemVo = new TdPaOrderItemVo();
      paOrderItemVo.setOrderNoList(orderNoList);
      paOrderItemVo.setOrderStopTime(currDate);
      paOrderItemVo.setOrderStopDoc(basEmployee.getEmplNumber());
      paOrderItemVo.setOrderStopDocName(basEmployee.getEmplName());
      paOrderItemVo.setOrderStatus("5");
      paOrderItemVo.setOrderStopAuditTime(currDate);
      paOrderItemVo.setOrderStopAuditDoc(dutyNurCd);
      paOrderItemVo.setOrderStopAuditDocName(dutyNurName);
      this.tdPaOrderItemService.updateOrderStatus(paOrderItemVo);
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, 5, "停止确认", currDate, (String)null, dutyNurCd, dutyNurName);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemList, 5, "停止确认", currDate, (String)null, dutyNurCd, dutyNurName);
   }

   private void getRearrangeOrderData(Visitinfo visitinfo, Date currDate, String ip, int reOrganizationNo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      Integer orderGroupNoMax = this.tdPaOrderItemService.selectPatientMaxGroupNo(visitinfo.getPatientId());
      int orderGroupNo = orderGroupNoMax != null ? orderGroupNoMax + 1 : 1;
      String orderNo = DateUtils.getDateStr("");
      List<TdPaOrder> orderListNew = new ArrayList(1);
      List<TdPaOrderItem> orderItemListNew = new ArrayList(1);
      List<TdPaOrderDetail> orderDetailListNew = new ArrayList(1);
      TdPaOrder order = new TdPaOrder();
      order.setHospitalCode(sysUser.getHospital().getOrgCode());
      order.setOrderNo(orderNo);
      order.setOrderType("1");
      order.setOrderClassCode("07");
      order.setPatientId(visitinfo.getPatientId());
      order.setPatientDepName(visitinfo.getPatientId());
      order.setCaseNo(visitinfo.getRecordNo());
      order.setAdmissionNo(visitinfo.getInpNo());
      order.setHospitalizedCount(visitinfo.getVisitId());
      order.setPatientWardNo(visitinfo.getAreaCode());
      order.setPatientDepCode(visitinfo.getDeptCd());
      order.setPatientDepName(visitinfo.getDeptName());
      order.setOrderDoctorNo(basEmployee.getEmplNumber());
      order.setOrderDoctorName(basEmployee.getEmplName());
      order.setOrderDoctorDepCode(sysUser.getDept().getDeptCode());
      order.setOrderDoctorDepName(sysUser.getDept().getDeptName());
      order.setResidentDoctorNo(visitinfo.getResDocCd());
      order.setResidentDoctorName(visitinfo.getResDocName());
      order.setRespNurseNo(visitinfo.getDutyNurCd());
      order.setRespNurseName(visitinfo.getDutyNurName());
      order.setOperatorNo(basEmployee.getEmplNumber());
      order.setOperatorName(basEmployee.getEmplName());
      order.setOperationTime(currDate);
      order.setOrderPerformFlag("0");
      order.setBedNo(visitinfo.getBedNo());
      order.setPerformComputerIp(ip);
      TdPaOrderItem orderItem = new TdPaOrderItem();
      BeanUtils.copyProperties(order, orderItem);
      orderItem.setOrderSortNumber("01");
      orderItem.setOrderGroupNo(orderGroupNo);
      orderItem.setCpNo("NNNNNN");
      orderItem.setCpName("医嘱重整");
      orderItem.setOrderStartTime(currDate);
      orderItem.setOrderStartDoc(basEmployee.getEmplNumber());
      orderItem.setOrderStartDocName(basEmployee.getEmplName());
      orderItem.setPriceFlag("0");
      orderItem.setOrderFlagCd("04");
      orderItem.setOrderFlagName("重整医嘱");
      orderItem.setPatientId(visitinfo.getPatientId());
      orderItem.setHerbalFlag("0");
      orderItem.setReOrganizationNo(reOrganizationNo);
      orderItem.setOrderStatus("5");
      orderItem.setSubmitTime(currDate);
      orderItem.setSubmitDoctorCode(basEmployee.getEmplNumber());
      orderItem.setSubmitDoctorNo(basEmployee.getEmplNumber());
      orderItem.setSubmitDoctorName(basEmployee.getEmplName());
      orderItem.setOrderAuditTime(currDate);
      orderItem.setOrderAuditDoc(visitinfo.getDutyNurCd());
      orderItem.setOrderAuditDocName(visitinfo.getDutyNurName());
      orderItem.setOrderDealTime(currDate);
      orderItem.setOrderDealDoc(basEmployee.getEmplNumber());
      orderItem.setOrderDealDocName(basEmployee.getEmplName());
      orderItem.setOrderStopTime(currDate);
      orderItem.setOrderStopDoc(basEmployee.getEmplNumber());
      orderItem.setOrderStopDocName(basEmployee.getEmplName());
      orderItem.setOrderStopAuditTime(currDate);
      orderItem.setOrderStopAuditDoc(visitinfo.getDutyNurCd());
      orderItem.setOrderStopAuditDocName(visitinfo.getDutyNurName());
      TdPaOrderDetail orderDetail = new TdPaOrderDetail();
      BeanUtils.copyProperties(orderItem, orderDetail);
      orderDetail.setId(SnowIdUtils.uniqueLong());
      orderDetail.setOrderGroupSortNumber("01");
      orderDetail.setChargeNo("NNNNNN");
      orderDetail.setChargeName(orderItem.getCpName());
      orderDetail.setOrderItemFlag("5");
      orderDetail.setReOrganizationNo(reOrganizationNo);
      orderDetail.setMasterOrder("1");
      orderListNew.add(order);
      orderItemListNew.add(orderItem);
      orderDetailListNew.add(orderDetail);
      if (!orderListNew.isEmpty()) {
         this.tdPaOrderService.insertList(orderListNew);
      }

      if (!orderItemListNew.isEmpty()) {
         this.tdPaOrderItemService.insertList(orderItemListNew);
      }

      if (!orderDetailListNew.isEmpty()) {
         this.tdPaOrderDetailMapper.insertList(orderDetailListNew);
      }

      if (!orderItemListNew.isEmpty()) {
         this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemListNew, 4, "停止", currDate, (String)null);
         this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemListNew, 4, "停止", currDate, (String)null);
      }

   }

   public Integer getMaxReOrganizationNo(String patientId) throws Exception {
      Integer maxReOrganizationNo = this.tdPaOrderDetailMapper.selectMaxReOrganizationNo(patientId);
      int reOrganizationNo = maxReOrganizationNo != null ? maxReOrganizationNo : 0;
      return reOrganizationNo;
   }

   public List selectListByTime(Date orderStartTime, Date orderStartTimeEnd) throws Exception {
      return this.tdPaOrderDetailMapper.selectListByTime(orderStartTime, orderStartTimeEnd);
   }

   public List selectDetailEventCode(List orderNoList) throws Exception {
      return this.tdPaOrderDetailMapper.selectDetailEventCode(orderNoList);
   }

   public void updaTeskinTestResults(String orderNo, String orderSortNumber, Date operatorDate, String batchNumber, String allergyType) {
      this.tdPaOrderDetailMapper.updaTeskinTestResults(orderNo, orderSortNumber, operatorDate, batchNumber, allergyType);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public OrderStopDoReturnVo orderCancelDo(OrderStopSignVo orderStopSignVo, List orderItemList, String ip) throws Exception {
      OrderStopDoReturnVo orderStopDoReturnVo = new OrderStopDoReturnVo();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<OrderStopVo> orderStopVoList = orderStopSignVo.getOrderStopVoList();
      String patientId = ((TdPaOrderItem)orderItemList.get(0)).getPatientId();
      Visitinfo visitinfo = this.visitinfoService.selectVisitinfoById(patientId);
      List<String> eventList = new ArrayList(1);
      Map<String, List<TdPaOrderItem>> orderStatusItemMap = new HashMap(1);
      List<String> orderNoListNullify = new ArrayList(1);
      List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
      List<TdPaOrderItem> orderSubmitList = new ArrayList(1);

      for(TdPaOrderItem orderItem : orderItemList) {
         String orderStatus = "11";
         orderNoListNullify.add(orderItem.getOrderNo());
         List<TdPaOrderItem> tempList = (List<TdPaOrderItem>)(orderStatusItemMap.get(orderStatus) == null ? new ArrayList(1) : (List)orderStatusItemMap.get(orderStatus));
         tempList.add(orderItem);
         orderStatusItemMap.put(orderStatus, tempList);
         eventList.add(orderItem.getOrderNo());
         if (orderItem.getOrderClassCode().equals("01") && orderItem.getOrderStatus().equals("0")) {
            orderSubmitList.add(orderItem);
         }
      }

      List<DrugListAndPerformReturnVo> returnVoList = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(orderNoListNullify)) {
         List<TdPaTakeDrugListVO> takeDrugListNullify = this.tdPaOrderService.selectUnclaimedDrugList(patientId, orderNoListNullify);
         List<TdPaOrderDetailVo> orderPerformListNullify = this.tdPaOrderService.selectUnExecPerformList(patientId, orderNoListNullify);
         if (CollectionUtils.isNotEmpty(takeDrugListNullify)) {
            for(TdPaTakeDrugListVO takeDrugListVO : takeDrugListNullify) {
               DrugListAndPerformReturnVo returnVo = new DrugListAndPerformReturnVo();
               returnVo.setId(takeDrugListVO.getId());
               returnVoList.add(returnVo);
            }
         }

         if (null != orderPerformListNullify && orderPerformListNullify.size() > 0) {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            this.tdPaOrderItemService.stopPerformList(orderPerformListNullify, sysUser.getUserName(), sysUser.getNickName());
         }
      }

      Date currDate = this.commonService.getDbSysdate();
      List<TdPaOrderItemVo> orderItemVoList = new ArrayList(orderStopVoList.size());

      for(String orderStatus : orderStatusItemMap.keySet()) {
         int operationType = 11;
         String operationTypeName = "作废";
         List<TdPaOrderItem> tempList = (List)orderStatusItemMap.get(orderStatus);
         List<String> orderNoList = (List)tempList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());

         for(TdPaOrderItem item : tempList) {
            TdPaOrderItemVo param = new TdPaOrderItemVo();
            param.setOrderNo(item.getOrderNo());
            param.setOrderStatus(orderStatus);
            orderItemVoList.add(param);
         }

         this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, tempList, operationType, operationTypeName, currDate, orderStopSignVo.getOrderCancelDesc());
         this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, tempList, operationType, operationTypeName, currDate, orderStopSignVo.getOrderCancelDesc());
         this.tdPaOrderSignMainService.addTdPaOrderOperationSign(tempList, orderStopSignVo, visitinfo, operationType, operationTypeName);
      }

      this.tdPaOrderItemService.updateOrderStatusList(orderItemVoList);
      if (CollectionUtils.isNotEmpty(returnVoList)) {
         DrugListAndPerformReturnResultVo returnResultVo = this.takeDrugReturnService.doReturnDrug(returnVoList, ip);
         orderStopDoReturnVo.setReturnResultVo(returnResultVo);
      }

      if (CollectionUtils.isNotEmpty(orderSubmitList)) {
         List<String> orderNoSubmitList = (List)orderSubmitList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
         List<TdPaOrderDetail> orderDetailList = this.tdPaOrderDetailMapper.selectByOrderNoList(orderNoSubmitList);

         for(TdPaOrderItem orderItem : orderSubmitList) {
            for(TdPaOrderDetail orderDetail : (List)orderDetailList.stream().filter((t) -> t.getOrderNo().equals(orderItem.getOrderNo())).collect(Collectors.toList())) {
               DrugDoseVo drugDoseVo = this.tdPaOrderService.getDrugDoseToday(orderItem, orderDetail);
               if (drugDoseVo != null) {
                  drugDoseVoList.add(drugDoseVo);
               }
            }
         }

         if (!drugDoseVoList.isEmpty()) {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            List<DrugDoseVo> drugDoseVos = this.drugStockService.updateDrugDoseByOrderDose(sysUser, drugDoseVoList, "0", ip);

            try {
               this.drugAndClinService.updateDurgXcsl(drugDoseVos);
            } catch (Exception e) {
               this.log.error("医嘱作废更新es药品数量出现异常", e);
            }
         }
      }

      if (eventList != null && !eventList.isEmpty()) {
         this.patEventService.delPatientEventByOrderNos(eventList);
         this.emrTaskInfoService.updateTaskInfoByEventNo(eventList);
      }

      List<TdPaOrderItem> tempList = (List)orderStatusItemMap.get("11");
      if (tempList != null && !tempList.isEmpty()) {
         List<String> orderNoList = (List)tempList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
         this.patEventService.delPatientEventByOrderNos(orderNoList);
      }

      for(String orderStatus : orderStatusItemMap.keySet()) {
         int operationType = orderStatus.equals("11") ? 11 : 4;
         String operationTypeName = orderStatus.equals("11") ? "作废" : "停止";
         List<TdPaOrderItem> itemListtemp = (List)orderStatusItemMap.get(orderStatus);
         List<String> orderNoList = (List)itemListtemp.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
         List<TdPaOrder> orderList = this.tdPaOrderService.selectByOrderNoList(orderNoList);
         this.tdMsgSendMainService.addMsgs(orderList, visitinfo, operationType, operationTypeName);
      }

      return orderStopDoReturnVo;
   }

   public TdPaOrderDetail selectOrderDetail(String orderNo) throws Exception {
      return this.tdPaOrderDetailMapper.selectOrderDetail(orderNo);
   }

   public List selectTdPaOrderDetailListByPatientId(TdPaOrderDetailVo tdPaOrderDetail) throws Exception {
      return this.tdPaOrderDetailMapper.selectTdPaOrderDetailListByPatientId(tdPaOrderDetail);
   }
}
