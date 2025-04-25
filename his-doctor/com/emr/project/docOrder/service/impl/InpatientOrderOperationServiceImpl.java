package com.emr.project.docOrder.service.impl;

import com.emr.common.constant.InpatientOrderConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TakeDrugBatch;
import com.emr.project.docOrder.domain.TdPaOrderOperationLog;
import com.emr.project.docOrder.domain.TdPaOrderPerform;
import com.emr.project.docOrder.domain.TdPaOrderPerformDetail;
import com.emr.project.docOrder.domain.TdPaOrderPerformFirst;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.docOrder.domain.TmPmOrderStatus;
import com.emr.project.docOrder.domain.vo.HisYfkcHz;
import com.emr.project.docOrder.domain.vo.InpatientOrderCheckVo;
import com.emr.project.docOrder.domain.vo.InpatientOrderDTO;
import com.emr.project.docOrder.domain.vo.OrderDoHandleDrugDoseVo;
import com.emr.project.docOrder.domain.vo.OrderDoHandlePrintVo;
import com.emr.project.docOrder.domain.vo.OrderDoHandleUpVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.mapper.InpatientOrderCheckMapper;
import com.emr.project.docOrder.service.IInpatientOrderOperationService;
import com.emr.project.docOrder.service.ITdMsgSendMainService;
import com.emr.project.docOrder.service.ITdPaApplyFormItemService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderOperationService;
import com.emr.project.docOrder.service.ITdPaOrderPerformDetailService;
import com.emr.project.docOrder.service.ITdPaOrderPerformFirstService;
import com.emr.project.docOrder.service.ITdPaOrderPerformService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.docOrder.service.ITdPaTakeDrugListService;
import com.emr.project.docOrder.service.ITmPmOrderStatusService;
import com.emr.project.docOrder.service.TakeDrugBatchService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.TmPaFreeze;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.dto.DepartAccountDTO;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.domain.vo.PatFeeVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.IPatFeeService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.tmpa.domain.OrderFreq;
import com.emr.project.tmpa.service.IOrderFreqService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
public class InpatientOrderOperationServiceImpl implements IInpatientOrderOperationService {
   private final Logger log = LoggerFactory.getLogger(InpatientOrderOperationServiceImpl.class);
   @Autowired
   private InpatientOrderCheckMapper inpatientOrderCheckMapper;
   @Autowired
   private IOrderFreqService orderFreqService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITmPmOrderStatusService tmPmOrderStatusService;
   @Autowired
   private IBabyInfoService babyInfoService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ITdPaOrderOperationLogService tdPaOrderOperationLogService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private TakeDrugBatchService takeDrugBatchService;
   @Autowired
   private ITdMsgSendMainService tdMsgSendMainService;
   @Autowired
   private HisProcService hisProcService;
   @Autowired
   private ITdPaOrderPerformFirstService tdPaOrderPerformFirstService;
   @Autowired
   private ICommonOperationService commonOperationService;
   @Autowired
   private ITdPaApplyFormService tdPaApplyFormService;
   @Autowired
   private ITdPaApplyFormItemService tdPaApplyFormItemService;
   @Autowired
   private IPatFeeService patFeeService;
   @Autowired
   private ITdPaOrderOperationService orderOperationService;
   @Autowired
   private ITdPaOrderPerformService tdPaOrderPerformService;
   @Autowired
   private ITdPaOrderPerformDetailService tdPaOrderPerformDetailService;
   @Autowired
   private ITdPaTakeDrugListService tdPaTakeDrugListService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private IDrugStockService drugStockService;

   public List inpatientOrderHandleSearch(InpatientOrderCheckVo inpatientOrderCheckVo) throws Exception {
      List<InpatientOrderDTO> list2 = this.inpatientOrderCheckMapper.getCheckInpatientOrderList2(inpatientOrderCheckVo);
      List<InpatientOrderDTO> resultList = new ArrayList();
      List<String> orderFreqCdList = list2 != null && !list2.isEmpty() ? (List)list2.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderFreq())).map((t) -> t.getOrderFreq()).distinct().collect(Collectors.toList()) : null;
      List<OrderFreq> orderFreqList = orderFreqCdList != null && !orderFreqCdList.isEmpty() ? this.orderFreqService.selectByFreqCdList(orderFreqCdList) : null;
      Map<String, OrderFreq> orderFreqMap = (Map<String, OrderFreq>)(orderFreqList != null && !orderFreqList.isEmpty() ? (Map)orderFreqList.stream().collect(Collectors.toMap((t) -> t.getFreqCd(), Function.identity())) : new HashMap(1));
      List<TmPmOrderStatus> orderStatusDicList = this.tmPmOrderStatusService.selectTmPmOrderStatusList((TmPmOrderStatus)null);
      Map<String, TmPmOrderStatus> orderStatusDicMap = (Map)orderStatusDicList.stream().collect(Collectors.toMap((t) -> t.getCodeNo(), Function.identity()));
      List<String> babyAdmissionNoList = (List)list2.stream().filter((t) -> StringUtils.isNotBlank(t.getBabyAdmissionNo())).map((t) -> t.getBabyAdmissionNo()).distinct().collect(Collectors.toList());
      List<BabyInfo> babyInfoList = this.babyInfoService.selectListByBabyAdmissionNoList(babyAdmissionNoList);
      Map<String, BabyInfo> babyInfoMap = CollectionUtils.isNotEmpty(babyInfoList) ? (Map)babyInfoList.stream().collect(Collectors.toMap((t) -> t.getPatBabyId(), Function.identity())) : null;

      for(InpatientOrderDTO inpatientOrderDTO : list2) {
         boolean isHandleFlag = true;
         if (!inpatientOrderDTO.getOrderStatus().toString().equals("4") && !inpatientOrderDTO.getOrderStatus().toString().equals("6")) {
            OrderFreq orderFreq = (OrderFreq)orderFreqMap.get(inpatientOrderDTO.getOrderFreq());
            isHandleFlag = this.isHandleDay(inpatientOrderDTO.getOrderNo(), inpatientOrderDTO.getOrderSortNumber(), orderFreq, inpatientOrderDTO.getOperationTime());
         } else {
            isHandleFlag = true;
         }

         Date orderStopTime = inpatientOrderDTO.getOrderStopTime();
         if (orderStopTime != null && (new Date()).before(orderStopTime)) {
            isHandleFlag = Boolean.FALSE;
         }

         if (isHandleFlag) {
            resultList.add(inpatientOrderDTO);
            if (StringUtils.isNotBlank(inpatientOrderDTO.getBabyAdmissionNo()) && babyInfoMap != null) {
               BabyInfo babyInfo = (BabyInfo)babyInfoMap.get(inpatientOrderDTO.getBabyAdmissionNo());
               inpatientOrderDTO.setPatientName(babyInfo.getBabyName());
            }

            TmPmOrderStatus tmPmOrderStatus = (TmPmOrderStatus)orderStatusDicMap.get(inpatientOrderDTO.getOrderStatus().toString());
            String orderStatusStr = tmPmOrderStatus != null ? tmPmOrderStatus.getCodeName() : "";
            inpatientOrderDTO.setOrderStatusStr(orderStatusStr);
         }
      }

      return resultList;
   }

   public List inpatientOrderHandlePageData(InpatientOrderCheckVo inpatientOrderCheckVo, List listAll, Integer pageSize, Integer pageNum) throws Exception {
      List<InpatientOrderDTO> listPage = new ArrayList(1);
      int begin = 0;
      int end = 0;
      if (pageSize != 0) {
         begin = (pageNum - 1) * pageSize;
         end = pageNum * pageSize;
         if (end > listAll.size()) {
            end = listAll.size();
         }
      }

      if (end == 0) {
         listPage.addAll(listAll);
      } else {
         listPage = listAll.subList(begin, end);
         if (begin != 0) {
            InpatientOrderDTO inpatientOrderDTO = (InpatientOrderDTO)listAll.get(begin);

            for(int i = begin - 1; i > 0; --i) {
               InpatientOrderDTO temp = (InpatientOrderDTO)listAll.get(i);
               if (!inpatientOrderDTO.getOrderNo().equals(temp.getOrderNo()) || !inpatientOrderDTO.getOrderGroupNo().equals(temp.getOrderGroupNo())) {
                  break;
               }

               listPage.add(0, temp);
            }
         }

         if (end != listAll.size()) {
            InpatientOrderDTO inpatientOrderDTO = (InpatientOrderDTO)listAll.get(end);

            for(int i = end - 1; i >= begin; --i) {
               InpatientOrderDTO temp = (InpatientOrderDTO)listAll.get(i);
               if (!inpatientOrderDTO.getOrderNo().equals(temp.getOrderNo()) || !inpatientOrderDTO.getOrderGroupNo().equals(temp.getOrderGroupNo())) {
                  break;
               }

               listPage.remove(listPage.size() - 1);
            }
         }
      }

      Map<String, List<InpatientOrderDTO>> inpatientOrderGroupMap = (Map)listPage.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo() + t.getOrderGroupNo()));

      for(int i = 0; i < listPage.size(); ++i) {
         InpatientOrderDTO temp = (InpatientOrderDTO)listPage.get(i);
         List<InpatientOrderDTO> tempList = (List)inpatientOrderGroupMap.get(temp.getOrderNo() + temp.getOrderGroupNo());
         Integer currIndex = tempList != null ? tempList.indexOf(temp) : null;
         if (tempList.size() > 1) {
            if (currIndex == 0 && temp.getOrderGroupSortNumber().equals("01")) {
               temp.setOrderGroupStr("┓");
            } else if (currIndex + 1 == tempList.size()) {
               temp.setOrderGroupStr("┛");
            } else {
               temp.setOrderGroupStr("┃");
            }
         }

         temp.setChecked(false);
      }

      return listPage;
   }

   public boolean isHandleDay(String orderNo, String orderSortNumber, OrderFreq orderFreq, Date handleTime) throws Exception {
      boolean flag = true;
      String freqType = orderFreq != null ? orderFreq.getFreqType() : null;
      Date dbDate = this.commonService.getDbSysdate();
      if (handleTime != null && StringUtils.isNotBlank(freqType) && freqType.equals("1")) {
         Integer freqInterDays = orderFreq.getFreqInterDays();
         if (freqType.equals("1") && freqInterDays != null) {
            Date nextHandleTime = DateUtils.addDays(handleTime, freqInterDays + 1);
            String dbDateDayStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, dbDate);
            String nextHandleTimeDayStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, nextHandleTime);
            Date dbDateDay = DateUtils.parseDate(dbDateDayStr);
            Date nextHandleTimeDay = DateUtils.parseDate(nextHandleTimeDayStr);
            if (dbDateDay.compareTo(nextHandleTimeDay) < 0) {
               flag = false;
            }
         }
      }

      String weekDays = orderFreq != null ? orderFreq.getWeekDay() : null;
      if (StringUtils.isNotBlank(freqType) && freqType.equals("3")) {
         if (StringUtils.isNotBlank(weekDays)) {
            int weekDay = DateUtils.dayForWeek(dbDate);
            flag = weekDays.contains(weekDay + "");
         } else {
            flag = false;
         }
      }

      return flag;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public OrderDoHandleDrugDoseVo inpatientOrderDoHandle(List inpatientOrderDTOList, PatFeeVo patFeeVo, BigDecimal patFeeAll, List drugAndClinList, List handleUplist, List printList, Map pharmacyNoApplyNoMap, String ip) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      InpatientOrderDTO inpatientOrderDTO = (InpatientOrderDTO)inpatientOrderDTOList.get(0);
      String resOrderStatus = inpatientOrderDTO.getOrderStatus().toString();
      String orderNo = inpatientOrderDTO.getOrderNo();
      String orderGroupNo = inpatientOrderDTO.getOrderGroupNo();
      String orderSortNumber = inpatientOrderDTO.getOrderSortNumber();
      String orderClassCode = inpatientOrderDTO.getOrderClassCode();
      Date currDate = this.commonService.getDbSysdate();
      VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoById(inpatientOrderDTO.getPatientId());
      List<TdPaOrderStatus> orderStatusList = new ArrayList(1);
      List<TdPaOrderOperationLog> orderOperationLogList = new ArrayList(1);
      boolean commitFlag = true;
      switch (inpatientOrderDTO.getOrderStatus().toString()) {
         case "0":
            resOrderStatus = "1";
            String operationType = String.valueOf(1);
            String operationName = "审核";
            TdPaOrderStatus commitStatus = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
            orderStatusList.add(commitStatus);
            TdPaOrderOperationLog commitOperationLog = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
            orderOperationLogList.add(commitOperationLog);
            break;
         case "4":
            commitFlag = false;
            resOrderStatus = "5";
            String operationType = String.valueOf(5);
            String operationName = "停止确认";
            TdPaOrderStatus stopStatus = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
            orderStatusList.add(stopStatus);
            TdPaOrderOperationLog stopOperationLog = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
            orderOperationLogList.add(stopOperationLog);
            this.updatePatientInfo(inpatientOrderDTO, visitinfo);
            break;
         case "6":
            commitFlag = false;
            resOrderStatus = "7";
            String operationType = String.valueOf(7);
            String operationName = "取消确认";
            TdPaOrderStatus cancelStatus = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
            orderStatusList.add(cancelStatus);
            TdPaOrderOperationLog cancelOperationLog = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
            orderOperationLogList.add(cancelOperationLog);
            this.updatePatientInfo(inpatientOrderDTO, visitinfo);
            break;
         case "11":
            commitFlag = false;
            resOrderStatus = "12";
            String operationType = String.valueOf(12);
            String operationName = "作废确认";
            TdPaOrderStatus invalidStatus = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
            orderStatusList.add(invalidStatus);
            TdPaOrderOperationLog invalidOperationLog = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
            orderOperationLogList.add(invalidOperationLog);
            this.updatePatientInfo(inpatientOrderDTO, visitinfo);
      }

      if (CollectionUtils.isNotEmpty(orderStatusList)) {
         this.tdPaOrderStatusService.insertTdPaOrderStatusList(orderStatusList);
      }

      if (CollectionUtils.isNotEmpty(orderOperationLogList)) {
         this.tdPaOrderOperationLogService.insertTdPaOrderOperationLogList(orderOperationLogList);
      }

      if (orderStatus.equals("4") || orderStatus.equals("6") || orderStatus.equals("11")) {
         this.tdPaOrderPerformService.updatePerformByOrderNoAndStauts(orderNo, resOrderStatus);
      }

      TdPaOrderItemVo param = new TdPaOrderItemVo();
      param.setOrderNoList(Arrays.asList(orderNo));
      param.setOrderStatus(resOrderStatus);
      this.tdPaOrderItemService.updateOrderStatus(param);
      this.tdMsgSendMainService.operMsgs(Arrays.asList(inpatientOrderDTO.getOrderNo()), sysUser);
      OrderDoHandleDrugDoseVo drugDoseVo = null;
      if (commitFlag) {
         inpatientOrderDTOList.stream().forEach((t) -> t.setOrderStatus(Integer.valueOf(resOrderStatus)));
         drugDoseVo = this.inpatientOrderDoHandleMore(inpatientOrderDTOList, sysUser, sysUser.getDept(), patFeeVo, patFeeAll, drugAndClinList, handleUplist, printList, pharmacyNoApplyNoMap);
      }

      if (drugDoseVo != null && StringUtils.isNotBlank(drugDoseVo.getNoStockFlag()) && drugDoseVo.getNoStockFlag().equals("1") && CollectionUtils.isNotEmpty(drugDoseVo.getDrugDoseVoList())) {
         List<DrugDoseVo> updateDoseList = drugDoseVo.getDrugDoseVoList();
         this.drugStockService.updateDrugDoseByOrderDose(sysUser, updateDoseList, "1", ip);
      }

      return drugDoseVo;
   }

   public OrderDoHandleDrugDoseVo inpatientOrderDoHandleMore(List inpatientOrderDTOList, SysUser sysUser, SysDept dept, PatFeeVo patFeeVo, BigDecimal patFeeAll, List drugAndClinList, List handleUplist, List printList, Map pharmacyNoApplyNoMap) throws Exception {
      InpatientOrderDTO inpatientOrderDTO = (InpatientOrderDTO)inpatientOrderDTOList.get(0);
      String resOrderStatus = inpatientOrderDTO.getOrderStatus().toString();
      String orderNo = inpatientOrderDTO.getOrderNo();
      String orderGroupNo = inpatientOrderDTO.getOrderGroupNo();
      String orderSortNumber = inpatientOrderDTO.getOrderSortNumber();
      String orderClassCode = inpatientOrderDTO.getOrderClassCode();
      String orderType = inpatientOrderDTO.getOrderType();
      String orderItemType = inpatientOrderDTO.getOrderItemType().toString();
      String herbalFlag = inpatientOrderDTO.getHerbalFlag();
      Date currDate = this.commonService.getDbSysdate();
      VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoById(inpatientOrderDTO.getPatientId());
      List<TdPaOrderStatus> orderStatusList = new ArrayList(1);
      List<TdPaOrderOperationLog> orderOperationLogList = new ArrayList(1);
      String orderStatus = inpatientOrderDTO.getOrderStatus().toString();
      TakeDrugBatch tdb = this.takeDrugBatchService.getDeptLastTakeDrugBatch(dept.getDeptCode(), currDate);
      boolean isFirst = orderStatus.equalsIgnoreCase("1");
      String takeDrugModel = inpatientOrderDTO.getTakeDrugMode();
      boolean isFirstDouble = StringUtils.isNotBlank(takeDrugModel) && takeDrugModel.equals("2");
      String performListNoToday = this.hisProcService.getDocumentOrBillNo(sysUser.getUserName(), 3);
      String performListNoTomorrow = null;
      if (isFirst && isFirstDouble && orderType.equals("1")) {
         performListNoTomorrow = this.hisProcService.getDocumentOrBillNo(sysUser.getUserName(), 3);
      }

      Map<Integer, TdPaOrderPerformFirst> orderPerformFirstMap = this.tdPaOrderPerformFirstService.selectByOrderInfo(inpatientOrderDTO.getOrderNo(), inpatientOrderDTO.getOrderSortNumber(), (Integer)null, inpatientOrderDTO.getAdmissionNo(), inpatientOrderDTO.getHospitalizedCount());
      List<TdPaOrderPerform> performList = new ArrayList(1);
      List<TdPaOrderPerformDetail> performDetailList = new ArrayList(1);
      List<TakeDrugList> takeDrugLists = new ArrayList(1);
      OrderFreq orderFreq = null;
      if (orderType.equals("1") && herbalFlag.equals("0") && !orderItemType.equals("3")) {
         orderFreq = StringUtils.isNotBlank(inpatientOrderDTO.getOrderFreq()) ? this.orderFreqService.selectOrderFreqById(inpatientOrderDTO.getOrderFreq()) : null;
      }

      if (orderType.equals("2") && orderClassCode.equals("01")) {
         orderFreq = StringUtils.isNotBlank(inpatientOrderDTO.getOrderFreq()) ? this.orderFreqService.selectOrderFreqById(inpatientOrderDTO.getOrderFreq()) : null;
      }

      TdPaOrderStatus handleStatusTemp = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, (String)null, (String)null, currDate);
      Boolean isNewOrderFlag = inpatientOrderDTO.getOrderStatus() == 1;
      BigDecimal performTotal = this.genPerformAndDetailList(performList, performDetailList, inpatientOrderDTOList, performListNoToday, performListNoTomorrow, isFirstDouble, handleStatusTemp, orderFreq, dept, isNewOrderFlag);
      patFeeAll = patFeeAll.add(performTotal);
      BigDecimal patFeeTemp = patFeeVo.getDepositRemaining().subtract(patFeeAll);
      BigDecimal total = BigDecimal.ZERO;

      for(InpatientOrderDTO dto : inpatientOrderDTOList) {
         if (dto.getOrderTotal() != null) {
            total = total.add(dto.getOrderTotal());
         }
      }

      boolean isArrears = this.commonOperationService.checkPatientArrears(visitinfo.getInpNo(), visitinfo.getVisitId().toString(), total);
      boolean isAccountFlag = false;
      OrderDoHandleDrugDoseVo drugDoseVo = null;
      switch (inpatientOrderDTO.getOrderClassCode()) {
         case "01":
            drugDoseVo = this.drugPatOrderHandle(inpatientOrderDTOList, performList, performDetailList, takeDrugLists, visitinfo, sysUser, 1, isArrears, drugAndClinList, currDate, pharmacyNoApplyNoMap, isNewOrderFlag);
            break;
         case "02":
            isAccountFlag = this.testExamOrderHandle(inpatientOrderDTOList, performList, sysUser, isArrears);
            break;
         case "03":
            isAccountFlag = this.testExamOrderHandle(inpatientOrderDTOList, performList, sysUser, isArrears);
            break;
         case "04":
            performList.stream().forEach((t) -> t.setPerformListStatus("0"));
            break;
         case "05":
            if (inpatientOrderDTO.getOrderFlagCd().equals("08") || inpatientOrderDTO.getOrderFlagCd().equals("09")) {
               this.updatePatientInfo(inpatientOrderDTO, visitinfo);
            }

            if (isArrears) {
               performList.stream().forEach((t) -> t.setPerformListStatus("0"));
            } else {
               performList.stream().forEach((t) -> t.setPerformListStatus("4"));
            }
         case "06":
            break;
         case "07":
            performList.stream().forEach((t) -> t.setPerformListStatus("1"));
            break;
         case "08":
            performList.stream().forEach((t) -> t.setPerformListStatus("0"));
            break;
         case "99":
            if (inpatientOrderDTO.getOrderFlagCd().equals("08") || inpatientOrderDTO.getOrderFlagCd().equals("09")) {
               this.updatePatientInfo(inpatientOrderDTO, visitinfo);
            }

            if (isArrears) {
               performList.stream().forEach((t) -> t.setPerformListStatus("0"));
            } else {
               performList.stream().forEach((t) -> t.setPerformListStatus("4"));
            }
            break;
         default:
            if (inpatientOrderDTO.getOrderFlagCd().equals("08") || inpatientOrderDTO.getOrderFlagCd().equals("09")) {
               this.updatePatientInfo(inpatientOrderDTO, visitinfo);
            }

            if (isArrears) {
               performList.stream().forEach((t) -> t.setPerformListStatus("0"));
            } else {
               performList.stream().forEach((t) -> t.setPerformListStatus("4"));
            }
      }

      if (CollectionUtils.isNotEmpty(performList) && CollectionUtils.isNotEmpty(performDetailList)) {
         this.tdPaOrderPerformService.addList(performList);
         this.tdPaOrderPerformDetailService.addList(performDetailList);
      }

      if (CollectionUtils.isNotEmpty(takeDrugLists)) {
         this.tdPaTakeDrugListService.insertList(takeDrugLists);
      }

      if (isAccountFlag) {
         List<Boolean> isAccountFlagSuccessList = new ArrayList();
         this.patFeeService.toFee(inpatientOrderDTOList, "0204", performListNoToday, isAccountFlagSuccessList, (DepartAccountDTO)null);
         if (!CollectionUtils.isNotEmpty(isAccountFlagSuccessList) || !(Boolean)isAccountFlagSuccessList.get(0)) {
            this.log.error("医嘱记账失败");
            throw new Exception("医嘱记账失败");
         }

         if (!sysUser.getDept().getDeptCode().equals(inpatientOrderDTO.getDetailPerformDepCode())) {
            String babyFlag = StringUtils.isNotBlank(inpatientOrderDTO.getBabyAdmissionNo()) ? "2" : "1";
            new OrderDoHandlePrintVo(visitinfo.getRecordNo(), visitinfo.getInpNo(), visitinfo.getVisitId(), babyFlag, "0", inpatientOrderDTO.getOrderNo(), inpatientOrderDTO.getOrderSortNumber(), currDate, sysUser.getDept().getDeptCode(), "");
            this.getPrintVo(inpatientOrderDTOList, currDate, sysUser, printList);
         }
      }

      if (drugDoseVo != null && drugDoseVo.getNoStockFlag().equals("0")) {
         OrderDoHandleUpVo handleUpVo = new OrderDoHandleUpVo("库存不足");
         handleUplist.add(handleUpVo);
      }

      if (!isArrears) {
         OrderDoHandleUpVo handleUpVo = new OrderDoHandleUpVo("欠费");
         handleUplist.add(handleUpVo);
      }

      this.orderOperationService.insert(orderNo, orderSortNumber, orderGroupNo);
      if (!inpatientOrderDTO.getOrderClassCode().equals("02") && !inpatientOrderDTO.getOrderClassCode().equals("03")) {
         if (inpatientOrderDTO.getOrderClassCode().equals("07")) {
            String operationType = String.valueOf(2);
            String operationName = "处理";
            TdPaOrderStatus handleStatus = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
            orderStatusList.add(handleStatus);
            TdPaOrderOperationLog handleOperationLog = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
            orderOperationLogList.add(handleOperationLog);
            TdPaOrderItemVo upParam = new TdPaOrderItemVo();
            upParam.setOrderNoList(Arrays.asList(orderNo));
            upParam.setOrderDealTime(currDate);
            upParam.setOrderDealDoc(sysUser.getUserName());
            upParam.setOrderDealDocName(sysUser.getNickName());
            if (inpatientOrderDTO.getOrderStatus() == Integer.valueOf("1")) {
               resOrderStatus = orderType.equals("1") ? "3" : "8";
               operationType = orderType.equals("1") ? String.valueOf(3) : String.valueOf(8);
               operationName = orderType.equals("1") ? "在执行" : "已执行";
               TdPaOrderStatus handleStatus2 = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
               orderStatusList.add(handleStatus2);
               TdPaOrderOperationLog handleOperationLog2 = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
               orderOperationLogList.add(handleOperationLog2);
               upParam.setOrderStatus(resOrderStatus);
               upParam.setOrderAuditTime(currDate);
               upParam.setOrderAuditDoc(sysUser.getUserName());
               upParam.setOrderAuditDocName(sysUser.getNickName());
               upParam.setOrderExecuteTime(currDate);
               upParam.setOrderExecuteDocName(sysUser.getNickName());
               upParam.setOrderExecuteDoc(sysUser.getUserName());
            }

            this.tdPaOrderItemService.updateOrderStatus(upParam);
         } else {
            TdPaOrderItemVo upParam = new TdPaOrderItemVo();
            upParam.setOrderNoList(Arrays.asList(orderNo));
            upParam.setOrderDealTime(currDate);
            upParam.setOrderDealDoc(sysUser.getUserName());
            upParam.setOrderDealDocName(sysUser.getNickName());
            if (inpatientOrderDTO.getOrderStatus() == Integer.valueOf("1")) {
               resOrderStatus = "2";
               String operationType = String.valueOf(2);
               String operationName = "处理";
               TdPaOrderStatus handleStatus = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
               orderStatusList.add(handleStatus);
               TdPaOrderOperationLog handleOperationLog = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
               orderOperationLogList.add(handleOperationLog);
               upParam.setOrderStatus(resOrderStatus);
               upParam.setOrderAuditTime(currDate);
               upParam.setOrderAuditDoc(sysUser.getUserName());
               upParam.setOrderAuditDocName(sysUser.getNickName());
            }

            this.tdPaOrderItemService.updateOrderStatus(upParam);
         }
      } else {
         String operationType = String.valueOf(2);
         String operationName = "处理";
         TdPaOrderStatus handleStatus = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
         orderStatusList.add(handleStatus);
         TdPaOrderOperationLog handleOperationLog = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
         orderOperationLogList.add(handleOperationLog);
         TdPaOrderItemVo upParam = new TdPaOrderItemVo();
         upParam.setOrderNoList(Arrays.asList(orderNo));
         upParam.setOrderDealTime(currDate);
         upParam.setOrderDealDoc(sysUser.getUserName());
         upParam.setOrderDealDocName(sysUser.getNickName());
         if (!isArrears) {
            operationType = String.valueOf(10);
            operationName = "挂起";
            TdPaOrderStatus handleStatus2 = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
            orderStatusList.add(handleStatus2);
            TdPaOrderOperationLog handleOperationLog2 = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
            orderOperationLogList.add(handleOperationLog2);
            resOrderStatus = "2";
         } else if (inpatientOrderDTO.getOrderStatus() == Integer.valueOf("1")) {
            resOrderStatus = orderType.equals("1") ? "3" : "8";
            operationType = orderType.equals("1") ? String.valueOf(3) : String.valueOf(8);
            operationName = orderType.equals("1") ? "在执行" : "已执行";
            TdPaOrderStatus handleStatus2 = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, orderNo, orderGroupNo, orderSortNumber, orderClassCode, operationType, operationName, currDate);
            orderStatusList.add(handleStatus2);
            TdPaOrderOperationLog handleOperationLog2 = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(orderNo, orderSortNumber, orderGroupNo, Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
            orderOperationLogList.add(handleOperationLog2);
            upParam.setOrderExecuteTime(currDate);
            upParam.setOrderExecuteDoc(sysUser.getUserName());
            upParam.setOrderExecuteDocName(sysUser.getNickName());
            upParam.setOrderAuditTime(currDate);
            upParam.setOrderAuditDoc(sysUser.getUserName());
            upParam.setOrderAuditDocName(sysUser.getNickName());
         }

         upParam.setOrderStatus(resOrderStatus);
         this.tdPaOrderItemService.updateOrderStatus(upParam);
      }

      if (CollectionUtils.isNotEmpty(orderStatusList)) {
         this.tdPaOrderStatusService.insertTdPaOrderStatusList(orderStatusList);
      }

      if (CollectionUtils.isNotEmpty(orderOperationLogList)) {
         this.tdPaOrderOperationLogService.insertTdPaOrderOperationLogList(orderOperationLogList);
      }

      return drugDoseVo;
   }

   private void updatePatientInfo(InpatientOrderDTO inpatientOrderDTO, VisitinfoVo visitinfo) throws Exception {
      if (inpatientOrderDTO.getOrderFlagCd().equals("08")) {
         switch (inpatientOrderDTO.getOrderStatus().toString()) {
            case "2":
               this.visitinfoService.updatePatientLevelCare(visitinfo, inpatientOrderDTO.getChargeNo(), inpatientOrderDTO.getChargeName());
               break;
            case "4":
               this.visitinfoService.updatePatientLevelCare(visitinfo, "99", "无");
               break;
            case "6":
               this.visitinfoService.updatePatientLevelCare(visitinfo, "99", "无");
               break;
            case "11":
               this.visitinfoService.updatePatientLevelCare(visitinfo, "99", "无");
         }
      }

      if (inpatientOrderDTO.getOrderFlagCd().equals("09")) {
         switch (inpatientOrderDTO.getOrderStatus().toString()) {
            case "2":
               this.visitinfoService.updatePatientIllness(visitinfo.getInpNo(), inpatientOrderDTO.getChargeName());
               break;
            case "4":
               this.visitinfoService.updatePatientIllness(visitinfo.getInpNo(), "");
               break;
            case "6":
               this.visitinfoService.updatePatientIllness(visitinfo.getInpNo(), "");
               break;
            case "11":
               this.visitinfoService.updatePatientIllness(visitinfo.getInpNo(), "");
         }
      }

   }

   private BigDecimal genPerformAndDetailList(List performListAll, List performDetailListAll, List inpatientOrderDTOList, String performListNoToday, String performListNoTomorrow, boolean isFirstDouble, TdPaOrderStatus handleStatus, OrderFreq orderFreq, SysDept dept, Boolean isNewOrderFlag) throws Exception {
      BigDecimal total = new BigDecimal("0");
      InpatientOrderDTO dto = (InpatientOrderDTO)inpatientOrderDTOList.get(0);
      TdPaOrderPerform perform = this.genPerform(inpatientOrderDTOList, performListNoToday, dept, handleStatus, orderFreq);
      perform.setPerformFirstGenFlag(isNewOrderFlag ? "1" : null);
      List<TdPaOrderPerformDetail> performDetailList = this.genPerformDetail(inpatientOrderDTOList, orderFreq);
      Date today = this.commonService.getDbSysdate();
      if (orderFreq != null) {
         Integer freqValue = orderFreq.getFreqValue();
         Date tomorrow = DateUtils.addDays(today, 1);
         if (dto.getOrderType().equals("1") && dto.getOrderStatus() == 1) {
            if (isFirstDouble) {
               int dayNum = dto.getFirstPerformNum() == null ? freqValue : dto.getFirstPerformNum();
               Integer subjectFlagToday = dto.getOrderItemType() != null && dto.getOrderItemType() == 2 ? 0 : null;
               BigDecimal todayTotal = this.genDayPerform(performListAll, performDetailListAll, performListNoToday, perform, performDetailList, dayNum, 1, today, subjectFlagToday);
               Integer subjectFlagTomorrow = dto.getOrderItemType() != null && dto.getOrderItemType() == 2 ? 1 : null;
               BigDecimal tomorrowTotal = this.genDayPerform(performListAll, performDetailListAll, performListNoTomorrow, perform, performDetailList, freqValue, 2, tomorrow, subjectFlagTomorrow);
               total = total.add(todayTotal);
               total = total.add(tomorrowTotal);
            } else {
               int dayNum = dto.getFirstPerformNum() == null ? freqValue : dto.getFirstPerformNum();
               Integer subjectFlagToday = dto.getOrderItemType() != null && dto.getOrderItemType() == 2 ? 0 : null;
               BigDecimal todayTotal = this.genDayPerform(performListAll, performDetailListAll, performListNoToday, perform, performDetailList, dayNum, 1, today, subjectFlagToday);
               total = total.add(todayTotal);
            }
         } else if (dto.getOrderType().equals("1") && dto.getOrderStatus() == 3) {
            if (isFirstDouble) {
               Integer subjectFlagTomorrow = dto.getOrderItemType() != null && dto.getOrderItemType() == 2 ? 1 : null;
               BigDecimal tomorrowTotal = this.genDayPerform(performListAll, performDetailListAll, performListNoToday, perform, performDetailList, freqValue, 2, tomorrow, subjectFlagTomorrow);
               total = total.add(tomorrowTotal);
            } else {
               Integer subjectFlagToday = dto.getOrderItemType() != null && dto.getOrderItemType() == 2 ? 0 : null;
               BigDecimal todayTotal = this.genDayPerform(performListAll, performDetailListAll, performListNoToday, perform, performDetailList, freqValue, 1, today, subjectFlagToday);
               total = total.add(todayTotal);
            }
         } else if (dto.getOrderType().equals("2") && dto.getOrderClassCode().equals("01")) {
            Integer subjectFlagToday = dto.getOrderItemType() != null && dto.getOrderItemType() == 2 ? 0 : null;
            BigDecimal todayTotal = this.genDayPerform(performListAll, performDetailListAll, performListNoToday, perform, performDetailList, freqValue, 1, today, subjectFlagToday);
            total = total.add(todayTotal);
         }
      } else {
         Integer subjectFlagToday = dto.getOrderItemType() != null && dto.getOrderItemType() == 2 ? 0 : null;
         BigDecimal todayTotal = this.genDayPerform(performListAll, performDetailListAll, performListNoToday, perform, performDetailList, 1, 1, today, subjectFlagToday);
         total = total.add(todayTotal);
      }

      total = total.setScale(2, 4);
      return total;
   }

   private TdPaOrderPerform genPerform(List inpatientOrderDTOList, String performListNoToday, SysDept dept, TdPaOrderStatus handleStatus, OrderFreq orderFreq) {
      TdPaOrderPerform perform = new TdPaOrderPerform();
      InpatientOrderDTO dto = (InpatientOrderDTO)inpatientOrderDTOList.get(0);
      perform.setHospitalCode(dto.getHospitalCode());
      Integer orderPerformFlag = dto.getOrderPerformFlag();
      if (orderPerformFlag == 0) {
         perform.setPerformDepCode(dept.getDeptCode());
         perform.setPerformWardNo(dept.getDeptCode());
      } else {
         perform.setPerformDepCode(dto.getPerformDepCode());
         perform.setPerformWardNo(dto.getPerformWardNo());
      }

      perform.setCaseNo(dto.getCaseNo());
      perform.setOrderType(dto.getOrderType());
      perform.setOrderNo(dto.getOrderNo());
      perform.setOrderSortNumber(dto.getOrderSortNumber());
      perform.setOrderClassCode(dto.getOrderClassCode());
      perform.setAdmissionNo(dto.getAdmissionNo());
      perform.setHospitalizedCount(dto.getHospitalizedCount());
      perform.setOrderDoctorCode(dto.getOrderDoctorCode());
      perform.setOrderDoctorNo(dto.getOrderDoctorNo());
      perform.setOrderDoctorWardNo(dto.getOrderDoctorWardNo());
      perform.setOrderDoctorDepCode(dto.getOrderDoctorDepCode());
      perform.setOrderStartTime(dto.getOrderStartTime());
      perform.setDocumentTypeNo(dto.getDocumentTypeNo());
      perform.setCpNo(dto.getCpNo());
      perform.setCpName(dto.getCpName());
      perform.setOutHavaDrugFlag(dto.getOutHavaDrugFlag());
      perform.setOrderItemType(dto.getOrderItemType());
      perform.setBabyAdmissionNo(dto.getBabyAdmissionNo());
      perform.setDetailPerformDepCode(dto.getDetailPerformDepCode());
      perform.setDetailPerformWardNo(dto.getDetailPerformDepCode());
      perform.setTakeDrugMode(dto.getTakeDrugMode());
      perform.setFirstDoubleFlag(dto.getFirstDoubleFlag() == null ? 2 : dto.getFirstDoubleFlag());
      perform.setHandleTime(handleStatus.getOperationTime());
      perform.setHandleNurseCode(handleStatus.getOperatorCode());
      perform.setHandleNurseNo(handleStatus.getOperatorNo());
      if (orderFreq != null && dto.getOrderDoseItem() != null && orderFreq.getFreqValue() != null) {
         int b = dto.getOrderDoseItem().intValue() % orderFreq.getFreqValue();
         if (b == 0) {
            BigDecimal performDoseItem = dto.getOrderDoseItem().divide(new BigDecimal((orderFreq.getFreqValue() == null ? 1 : orderFreq.getFreqValue()) + "")).setScale(2, 4);
            perform.setOrderDoseItem(performDoseItem);
         } else {
            perform.setOrderDoseItem(dto.getOrderDoseItem());
         }
      } else {
         perform.setOrderDoseItem(dto.getOrderDoseItem());
      }

      perform.setUnitItem(dto.getUnitItem());
      perform.setPriceItem(dto.getPriceItem());
      perform.setOrderTotalItem(dto.getOrderTotalItem());
      perform.setPerformListNo(performListNoToday);
      return perform;
   }

   private List genPerformDetail(List inpatientOrderDTOList, OrderFreq orderFreq) {
      List<TdPaOrderPerformDetail> performDetailList = new ArrayList(1);

      for(InpatientOrderDTO dto : inpatientOrderDTOList) {
         TdPaOrderPerformDetail detail = new TdPaOrderPerformDetail();
         detail.setOrderGroupNo(dto.getOrderGroupNo());
         detail.setOrderSortNumber(dto.getOrderSortNumber());
         detail.setOrderGroupSortNumber(dto.getOrderGroupSortNumber());
         detail.setChargeNo(dto.getChargeNo());
         detail.setChargeName(dto.getChargeName());
         detail.setStandardCode(dto.getStandardCode());
         detail.setStandardName(dto.getStandardName());
         detail.setStandard(dto.getStandard());
         detail.setUnit(dto.getUnit());
         detail.setOrderActualUsage(dto.getOrderActualUsage());
         detail.setUsageUnit(dto.getUsageUnit());
         detail.setOrderTotalDose(dto.getOrderTotalDose());
         detail.setPrice(dto.getPrice());
         if (orderFreq != null && dto.getOrderDose() != null && orderFreq.getFreqValue() != null && dto.getHerbalFlag().equals("0")) {
            int b = dto.getOrderDose().intValue() % orderFreq.getFreqValue();
            if (b == 0) {
               BigDecimal performDoseItem = dto.getOrderDose().divide(new BigDecimal(orderFreq.getFreqValue() + "")).setScale(2, 4);
               detail.setOrderDose(performDoseItem);
            } else {
               detail.setOrderDose(dto.getOrderDose());
            }

            if (dto.getPrice() != null) {
               BigDecimal perfromTotal = dto.getPrice().multiply(detail.getOrderDose()).setScale(2, 4);
               detail.setOrderTotal(perfromTotal);
            }
         } else {
            detail.setOrderDose(dto.getOrderDose());
            detail.setOrderTotal(dto.getOrderTotal());
         }

         detail.setOrderFreq(dto.getOrderFreq());
         detail.setOrderUsageWay(dto.getOrderUsageWay());
         detail.setHerbalFlag(StringUtils.isBlank(dto.getHerbalFlag()) ? 0 : Integer.parseInt(dto.getHerbalFlag()));
         detail.setHerbalDose(dto.getHerbalDose());
         detail.setDrugForm(dto.getDrugForm());
         detail.setDoctorInstructions(dto.getDoctorInstructions());
         detail.setPatientSelfDrugFlag(dto.getPatientSelfDrugFlag());
         detail.setPriceFlag(dto.getPriceFlag());
         detail.setPharmacopoeiaNo(dto.getPharmacopoeiaNo());
         detail.setCpNo(dto.getCpNo());
         detail.setStockNo(dto.getStockNo());
         detail.setHygienicMaterialFlag(dto.getHygienicMaterialFlag());
         detail.setDrugClassCode(dto.getDrugClassCode());
         detail.setSubjectFlag(dto.getSubjectFlag());
         performDetailList.add(detail);
      }

      return performDetailList;
   }

   private BigDecimal genDayPerform(List performListAll, List performDetailListAll, String performListNo, TdPaOrderPerform perform, List performDetailList, int dayNum, int firstDoubleFlag, Date planUsageTime, Integer subjectFlagParam) {
      BigDecimal total = new BigDecimal("0");

      for(int i = 1; i < dayNum + 1; ++i) {
         TdPaOrderPerform todayPerform = new TdPaOrderPerform();
         BeanUtils.copyProperties(perform, todayPerform);
         todayPerform.setPerformListNo(performListNo);
         todayPerform.setPerformListSortNumber(i);
         todayPerform.setFirstDoubleFlag(firstDoubleFlag);
         todayPerform.setPlanUsageTime(planUsageTime);
         Boolean subjectFlag = null;
         if (subjectFlagParam != null && subjectFlagParam == 0 && i == 0) {
            subjectFlag = true;
         }

         if (subjectFlagParam != null && subjectFlagParam == 0 && i != 0) {
            subjectFlag = false;
         }

         if (subjectFlagParam != null && subjectFlagParam == 1) {
            subjectFlag = false;
         }

         List<TdPaOrderPerformDetail> todayPerformDetailList = new ArrayList(performDetailList.size());

         for(int j = 0; j < performDetailList.size(); ++j) {
            TdPaOrderPerformDetail performDetail = (TdPaOrderPerformDetail)performDetailList.get(j);
            if ((subjectFlag == null || !subjectFlag || performDetail.getSubjectFlag() == 0) && (subjectFlag == null || subjectFlag || performDetail.getSubjectFlag() == 1)) {
               TdPaOrderPerformDetail todayPerformDetail = new TdPaOrderPerformDetail();
               BeanUtils.copyProperties(performDetail, todayPerformDetail);
               todayPerformDetail.setPerformListNo(todayPerform.getPerformListNo());
               todayPerformDetail.setPerformListSortNumber(todayPerform.getPerformListSortNumber().toString());
               if (perform.getOrderType().equals("1")) {
                  int b = todayPerformDetail.getOrderTotalDose() != null ? todayPerformDetail.getOrderTotalDose().intValue() % dayNum : 0;
                  if (b != 0) {
                     BigDecimal price = todayPerformDetail.getPrice() == null ? BigDecimal.ZERO : todayPerformDetail.getPrice();
                     BigDecimal orderDose = BigDecimal.ZERO;
                     if (i == 1) {
                        orderDose = todayPerformDetail.getOrderTotalDose();
                     }

                     todayPerformDetail.setOrderTotal(price.multiply(orderDose).setScale(2, 4));
                     total = total.add(todayPerformDetail.getOrderTotal());
                     todayPerformDetail.setOrderDose(orderDose);
                  } else {
                     BigDecimal price = todayPerformDetail.getPrice() == null ? BigDecimal.ZERO : todayPerformDetail.getPrice();
                     BigDecimal orderDose = todayPerformDetail.getOrderDose() == null ? BigDecimal.ZERO : todayPerformDetail.getOrderDose();
                     todayPerformDetail.setOrderTotal(price.multiply(orderDose).setScale(2, 4));
                     total = total.add(todayPerformDetail.getOrderTotal());
                  }
               } else if (perform.getOrderClassCode().equals("01")) {
                  int b = todayPerformDetail.getOrderTotalDose() != null ? todayPerformDetail.getOrderTotalDose().intValue() % dayNum : 0;
                  if (b != 0) {
                     BigDecimal price = todayPerformDetail.getPrice() == null ? BigDecimal.ZERO : todayPerformDetail.getPrice();
                     BigDecimal orderDose = BigDecimal.ZERO;
                     if (i == 1) {
                        orderDose = todayPerformDetail.getOrderTotalDose();
                     }

                     todayPerformDetail.setOrderTotal(price.multiply(orderDose).setScale(2, 4));
                     total = total.add(todayPerformDetail.getOrderTotal());
                     todayPerformDetail.setOrderDose(orderDose);
                  } else {
                     BigDecimal price = todayPerformDetail.getPrice() == null ? BigDecimal.ZERO : todayPerformDetail.getPrice();
                     BigDecimal orderDose = todayPerformDetail.getOrderDose() == null ? BigDecimal.ZERO : todayPerformDetail.getOrderDose();
                     todayPerformDetail.setOrderTotal(price.multiply(orderDose).setScale(2, 4));
                     total = total.add(todayPerformDetail.getOrderTotal());
                  }
               } else {
                  BigDecimal price = todayPerformDetail.getPrice() == null ? BigDecimal.ZERO : todayPerformDetail.getPrice();
                  BigDecimal orderDose = todayPerformDetail.getOrderDose() == null ? BigDecimal.ZERO : todayPerformDetail.getOrderDose();
                  todayPerformDetail.setOrderTotal(price.multiply(orderDose).setScale(2, 4));
                  total = total.add(todayPerformDetail.getOrderTotal());
               }

               todayPerformDetailList.add(todayPerformDetail);
               int patientSelfDrugFlag = performDetail.getPatientSelfDrugFlag() == null ? 0 : performDetail.getPatientSelfDrugFlag();
               if (patientSelfDrugFlag != InpatientOrderConstants.PATIENT_SELF_DRUG_FLAG_3) {
                  total.add(performDetail.getOrderTotal() == null ? BigDecimal.ZERO : performDetail.getOrderTotal());
               }
            }
         }

         performListAll.add(todayPerform);
         performDetailListAll.addAll(todayPerformDetailList);
      }

      return total;
   }

   private OrderDoHandleDrugDoseVo drugPatOrderHandle(List inpatientOrderDTOList, List performListAll, List performDetailListAll, List takeDrugListAll, VisitinfoVo visitinfo, SysUser sysUser, int todayAccountTimes, Boolean isArrears, List drugAndClinList, Date currDate, Map pharmacyNoApplyNoMap, Boolean isNewOrderFlag) throws Exception {
      OrderDoHandleDrugDoseVo res = new OrderDoHandleDrugDoseVo();
      List<TakeDrugList> takeDrugLists = this.genTakeDrugList(inpatientOrderDTOList, performListAll, performDetailListAll, visitinfo, sysUser, todayAccountTimes, currDate, pharmacyNoApplyNoMap, isNewOrderFlag);
      Map<String, BigDecimal> drugDoseMap = new HashMap(1);
      List<HisYfkcHz> yfkcHzList = new ArrayList(1);
      List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
      String noStockFlag = "1";
      InpatientOrderDTO dto = (InpatientOrderDTO)inpatientOrderDTOList.get(0);
      if (isArrears) {
         if (dto.getOrderClassCode().equals("01") && dto.getOrderStatus() == Integer.valueOf("3")) {
            List<String> drugDoseNoList = new ArrayList(1);

            for(TakeDrugList takeDrugList : takeDrugLists) {
               String drugCode = takeDrugList.getPharmacopoeiaNo();
               BigDecimal drugDose = takeDrugList.getOrderDose();
               BigDecimal drugDoseAll = (BigDecimal)drugDoseMap.get(drugCode);
               drugDoseAll = drugDoseAll == null ? drugDose : drugDoseAll.add(drugDose);
               drugDoseMap.put(drugCode, drugDoseAll);
            }

            Map<String, DrugAndClin> drugAndClinMap = (Map)drugAndClinList.stream().collect(Collectors.toMap((t) -> t.getCpNo(), Function.identity()));

            for(String drugCode : drugDoseMap.keySet()) {
               BigDecimal drugDose = (BigDecimal)drugDoseMap.get(drugCode);
               DrugAndClin drugAndClin = (DrugAndClin)drugAndClinMap.get(drugCode);
               BigDecimal drugDoseTemp = drugAndClin != null ? drugAndClin.getXcsl() : new BigDecimal("0");
               if (drugDose.compareTo(drugDoseTemp) > 0) {
                  drugDoseNoList.add(drugCode);
               }

               List<TakeDrugList> takeDrugListTemp = (List)takeDrugLists.stream().filter((t) -> t.getPharmacopoeiaNo().equals(drugCode)).collect(Collectors.toList());
               TakeDrugList takeDrugList = (TakeDrugList)takeDrugListTemp.get(0);
               HisYfkcHz yfkcHz = new HisYfkcHz(takeDrugList.getPharmacopoeiaNo(), takeDrugList.getPharmacyNo(), takeDrugList.getDrugStockNo(), (BigDecimal)null, drugDose);
               yfkcHzList.add(yfkcHz);
               DrugDoseVo drugDoseVo = new DrugDoseVo((String)null, takeDrugList.getPharmacyNo(), takeDrugList.getPharmacopoeiaNo(), takeDrugList.getDrugStockNo(), drugDose.negate(), takeDrugList.getOrderNo(), takeDrugList.getId(), takeDrugList.getOrderPrice());
               drugDoseVoList.add(drugDoseVo);
            }

            if (CollectionUtils.isNotEmpty(drugDoseNoList)) {
               noStockFlag = "0";
               yfkcHzList = null;
               drugDoseVoList = null;
               performListAll.stream().forEach((t) -> t.setPerformListStatus("3"));
               performDetailListAll.stream().forEach((t) -> {
                  if (drugDoseNoList.contains(t.getPharmacopoeiaNo())) {
                     t.setUpStatus("31");
                  }

               });
               takeDrugLists.stream().forEach((t) -> {
                  t.setTakeDrugStatus(3);
                  if (drugDoseNoList.contains(t.getPharmacopoeiaNo())) {
                     t.setUpStatus("10");
                  }

               });
            } else {
               performListAll.stream().forEach((t) -> t.setPerformListStatus("0"));
               performDetailListAll.stream().forEach((t) -> t.setUpStatus("11"));
               takeDrugLists.stream().forEach((t) -> t.setTakeDrugStatus(1));
            }
         } else {
            performListAll.stream().forEach((t) -> t.setPerformListStatus("0"));
            performDetailListAll.stream().forEach((t) -> t.setUpStatus("11"));
            takeDrugLists.stream().forEach((t) -> t.setTakeDrugStatus(1));
         }
      } else {
         performListAll.stream().forEach((t) -> t.setPerformListStatus("4"));
         performDetailListAll.stream().forEach((t) -> t.setUpStatus("41"));
         takeDrugLists.stream().forEach((t) -> t.setTakeDrugStatus(2));
      }

      takeDrugListAll.addAll(takeDrugLists);
      res.setNoStockFlag(noStockFlag);
      res.setYfkcHzList(yfkcHzList);
      return res;
   }

   private List genTakeDrugList(List inpatientOrderDTOList, List performListAll, List performDetailListAll, VisitinfoVo visitinfo, SysUser sysUser, int todayAccountTimes, Date currDate, Map pharmacyNoApplyNoMap, Boolean isNewOrderFlag) throws Exception {
      List<TakeDrugList> takeDrugLists = new ArrayList(1);
      Map<String, List<TdPaOrderPerform>> performMap = (Map)performListAll.stream().collect(Collectors.groupingBy((t) -> t.getPerformListNo()));
      Map<String, List<TdPaOrderPerformDetail>> performDetailMap = (Map)performDetailListAll.stream().collect(Collectors.groupingBy((t) -> t.getPerformListNo()));

      for(String performListNo : performMap.keySet()) {
         List<TdPaOrderPerform> performList = (List)performMap.get(performListNo);
         TdPaOrderPerform perform = (TdPaOrderPerform)performList.get(0);
         Map<String, BigDecimal> orderGroupSortNumberMap = new HashMap(1);

         for(TdPaOrderPerformDetail detail : (List)performDetailMap.get(performListNo)) {
            String orderGroupSortNumber = detail.getOrderGroupSortNumber();
            BigDecimal dose = (BigDecimal)orderGroupSortNumberMap.get(orderGroupSortNumber);
            if (dose == null) {
               dose = detail.getOrderDose();
            } else {
               dose = dose.add(detail.getOrderDose()).setScale(2, 4);
            }

            orderGroupSortNumberMap.put(orderGroupSortNumber, dose);
         }

         for(InpatientOrderDTO dto : inpatientOrderDTOList) {
            if ((dto.getPatientSelfDrugFlag() == null || dto.getPatientSelfDrugFlag() != InpatientOrderConstants.PATIENT_SELF_DRUG_FLAG_3) && !dto.getOrderItemFlag().equals("3")) {
               TakeDrugList takeDrugList = new TakeDrugList();
               takeDrugList.setId(SnowIdUtils.uniqueLong());
               takeDrugList.setHospitalCode(perform.getHospitalCode());
               takeDrugList.setPerformListNo(performListNo);
               takeDrugList.setPerformListSortNumber(Integer.parseInt(dto.getOrderGroupSortNumber() == null ? "0" : dto.getOrderGroupSortNumber()));
               takeDrugList.setOrderGroupSortNumber(dto.getOrderGroupSortNumber());
               takeDrugList.setDrugName(dto.getChargeName());
               takeDrugList.setDrugNamePym(PinYinUtil.getPinYinHeadChar(dto.getChargeName()));
               takeDrugList.setOrderStandard(dto.getStandard());
               takeDrugList.setDrugActualUsage(dto.getOrderActualUsage());
               takeDrugList.setUsageUnit(dto.getUsageUnit());
               takeDrugList.setOrderPrice(dto.getPrice());
               takeDrugList.setOrderUnit(dto.getUnit());
               takeDrugList.setDrugForm(dto.getDrugForm());
               takeDrugList.setDrugStockNo(dto.getStockNo());
               takeDrugList.setPharmacopoeiaNo(dto.getPharmacopoeiaNo());
               takeDrugList.setDrugUsageWay(dto.getOrderUsageWayName());
               takeDrugList.setDrugUsageWayCode(dto.getOrderUsageWay());
               takeDrugList.setDrugFreqCode(dto.getOrderFreq());
               takeDrugList.setDrugFreq(dto.getOrderFreqName());
               takeDrugList.setHygienicMaterialFlag(dto.getHygienicMaterialFlag());
               takeDrugList.setHerbalFlag(StringUtils.isBlank(dto.getHerbalFlag()) ? 0 : Integer.parseInt(dto.getHerbalFlag()));
               takeDrugList.setCpNo(dto.getCpNo());
               takeDrugList.setDoctorInstructions(dto.getDoctorInstructions());
               takeDrugList.setOrderGroupNo(dto.getOrderGroupNo());
               BigDecimal dose = (BigDecimal)orderGroupSortNumberMap.get(dto.getOrderGroupSortNumber());
               takeDrugList.setOrderDose(dose);
               takeDrugList.setAdmissionNo(dto.getAdmissionNo());
               takeDrugList.setCaseNo(dto.getCaseNo());
               takeDrugList.setHospitalizedCount(dto.getHospitalizedCount());
               takeDrugList.setTakeDrugWardNo(sysUser.getDept().getDeptCode());
               takeDrugList.setPatientName(visitinfo.getPatientName());
               takeDrugList.setBedid(visitinfo.getBedNo());
               takeDrugList.setOrderNo(dto.getOrderNo());
               takeDrugList.setOrderSortNumber(dto.getOrderSortNumber());
               takeDrugList.setOrderType(dto.getOrderType());
               takeDrugList.setDrugClassCode(dto.getDrugClassCode());
               takeDrugList.setTakeDrugTime(currDate);
               takeDrugList.setTakeDrugOperator(sysUser.getUserName());
               takeDrugList.setOrderDoctorCode(dto.getOrderDoctorCode());
               takeDrugList.setDrugEatTime(perform.getPlanUsageTime());
               takeDrugList.setOutDrugFlag(StringUtils.isBlank(dto.getOutHavaDrugFlag()) ? "0" : dto.getOutHavaDrugFlag());
               takeDrugList.setPatientDepCode(dto.getPatientDepCode());
               takeDrugList.setPatientDepName(sysUser.getDept().getDeptName());
               takeDrugList.setPharmacyNo(perform.getDetailPerformWardNo());
               takeDrugList.setTodayAccountTimes(todayAccountTimes);
               takeDrugList.setPerformedTimes(dto.getFirstDoubleFlag());
               takeDrugList.setPurposeAntimicrobialUse(dto.getPurposeAntimicrobialUse());
               takeDrugList.setPrescribeNo(dto.getPrescribeNo());
               takeDrugList.setPrescribeName(dto.getPrescribeName());
               takeDrugList.setBabyAdmissionNo(dto.getBabyAdmissionNo());
               takeDrugList.setSurgicalFormNo(dto.getSurgicalFormNo());
               takeDrugList.setValid(1);
               String applyNo = (String)pharmacyNoApplyNoMap.get(dto.getPharmacyNo());
               takeDrugList.setApplyNo(applyNo);
               takeDrugList.setPhysicianDptName(dto.getOrderDoctorDepName());
               takeDrugList.setPhysicianDptNo(dto.getOrderDoctorDepCode());
               takeDrugLists.add(takeDrugList);
            }
         }
      }

      if (isNewOrderFlag) {
         String orderNo = ((InpatientOrderDTO)inpatientOrderDTOList.get(0)).getOrderNo();
         List<TmPaFreeze> freezeList = this.drugStockService.selectNewOrderFreezeList(orderNo);
         if (CollectionUtils.isNotEmpty(freezeList)) {
            List<TmPaFreeze> freezeListNew = new ArrayList(1);
            Map<String, List<TmPaFreeze>> phDrugFreezeMap = (Map)freezeList.stream().collect(Collectors.groupingBy((t) -> t.getWareCode() + t.getDrugCd()));
            Map<String, List<TakeDrugList>> phDrugTakeListMap = (Map)takeDrugLists.stream().collect(Collectors.groupingBy((t) -> t.getPharmacyNo() + t.getPharmacopoeiaNo()));

            for(String phDrug : phDrugTakeListMap.keySet()) {
               List<TakeDrugList> takeDrugListsTemp = (List)phDrugTakeListMap.get(phDrug);
               List<TmPaFreeze> phDrugFreezesList = (List)phDrugFreezeMap.get(phDrug);
               List<TmPaFreeze> phDrugFreezesListSy = phDrugFreezesList;

               for(TakeDrugList drugList : takeDrugListsTemp) {
                  TmPaFreeze freezeTemp = new TmPaFreeze();
                  int orderDose = drugList.getOrderDose().multiply(new BigDecimal(((TmPaFreeze)phDrugFreezesList.get(0)).getMinUnitRatio().toString())).intValue();

                  for(int i = 0; i < phDrugFreezesListSy.size(); ++i) {
                     TmPaFreeze freezeDb = (TmPaFreeze)phDrugFreezesListSy.get(i);
                     BeanUtils.copyProperties(freezeDb, freezeTemp);
                     freezeTemp.setId(SnowIdUtils.uniqueLong());
                     int add = freezeDb.getFreezeNum() - orderDose;
                     if (add == 0) {
                        freezeDb.setFreezeNum(add);
                        if (i + 1 < phDrugFreezesListSy.size()) {
                           phDrugFreezesListSy = phDrugFreezesListSy.subList(i + 1, phDrugFreezesListSy.size());
                        }

                        freezeTemp.setFreezeNum(orderDose);
                        freezeListNew.add(freezeTemp);
                        freezeTemp.setFreezeId(drugList.getId());
                        break;
                     }

                     if (add > 0) {
                        freezeDb.setFreezeNum(add);
                        List<TmPaFreeze> phDrugFreezesListSy2 = null;
                        if (i + 1 < phDrugFreezesListSy.size()) {
                           phDrugFreezesListSy2 = phDrugFreezesListSy.subList(i + 1, phDrugFreezesListSy.size());
                        }

                        phDrugFreezesListSy = new ArrayList(1);
                        phDrugFreezesListSy.add(freezeDb);
                        if (CollectionUtils.isNotEmpty(phDrugFreezesListSy2)) {
                           phDrugFreezesListSy.addAll(phDrugFreezesListSy2);
                        }

                        freezeTemp.setFreezeNum(orderDose);
                        freezeListNew.add(freezeTemp);
                        freezeTemp.setFreezeId(drugList.getId());
                        break;
                     }

                     orderDose -= freezeDb.getFreezeNum();
                     TmPaFreeze freezeTemp2 = new TmPaFreeze();
                     BeanUtils.copyProperties(freezeTemp, freezeTemp2);
                     freezeTemp2.setFreezeId(drugList.getId());
                     freezeListNew.add(freezeTemp2);
                  }

                  freezeTemp.setFreezeId(drugList.getId());
               }
            }

            this.drugStockService.delFreezesByOrderNo(orderNo);
            this.drugStockService.batchInsertFreezes(freezeListNew);
         }
      }

      return takeDrugLists;
   }

   private Boolean testExamOrderHandle(List inpatientOrderDTOList, List performListAll, SysUser sysUser, Boolean isArrears) throws Exception {
      InpatientOrderDTO dto = (InpatientOrderDTO)inpatientOrderDTOList.get(0);
      Boolean isAccount = false;
      Boolean isArrearsTemp = false;
      SysDept dept = sysUser.getDept();
      String arrearsFlag = dept.getArrearsFlag();
      String performDepCode = ((TdPaOrderPerform)performListAll.get(0)).getDetailPerformDepCode();
      SysDept performDept = this.sysDeptService.getOneByCode(performDepCode);
      String accountsMode = performDept.getAccountsMode();
      if (StringUtils.isBlank(accountsMode) || StringUtils.isNotBlank(accountsMode) && accountsMode.equals("0")) {
         if (StringUtils.isBlank(arrearsFlag) || StringUtils.isNotBlank(arrearsFlag) && arrearsFlag.equals("0")) {
            isArrearsTemp = !isArrears;
            isAccount = isArrears;
         } else {
            isArrearsTemp = false;
            isAccount = true;
         }

         if (isArrearsTemp) {
            performListAll.stream().forEach((t) -> t.setPerformListStatus("4"));
            this.tdPaApplyFormService.updateStatusByApplyFormNos(Arrays.asList(dto.getApplyFormNo()), "4");
            this.tdPaApplyFormItemService.updateStatusByApplyFormNos(Arrays.asList(dto.getApplyFormNo()), "4");
         } else {
            for(TdPaOrderPerform perform : performListAll) {
               perform.setPerformListStatus("1");
               perform.setPerformTime(new Date());
               perform.setPerformNurseCode(sysUser.getUserName());
               perform.setPerformNurseNo(sysUser.getUserName());
            }

            this.tdPaApplyFormService.updateStatusByApplyFormNos(Arrays.asList(dto.getApplyFormNo()), "31");
            this.tdPaApplyFormItemService.updateStatusByApplyFormNos(Arrays.asList(dto.getApplyFormNo()), "31");
         }
      } else {
         isAccount = false;
         if (arrearsFlag.equals("0")) {
            isArrearsTemp = !isArrears;
         } else {
            isArrearsTemp = false;
         }

         if (isArrearsTemp) {
            performListAll.stream().forEach((t) -> t.setPerformListStatus("4"));
            this.tdPaApplyFormService.updateStatusByApplyFormNos(Arrays.asList(dto.getApplyFormNo()), "4");
            this.tdPaApplyFormItemService.updateStatusByApplyFormNos(Arrays.asList(dto.getApplyFormNo()), "4");
         } else {
            for(TdPaOrderPerform perform : performListAll) {
               perform.setPerformListStatus("1");
               perform.setPerformTime(new Date());
               perform.setPerformNurseCode(sysUser.getUserName());
               perform.setPerformNurseNo(sysUser.getUserName());
            }

            this.tdPaApplyFormService.updateStatusByApplyFormNos(Arrays.asList(dto.getApplyFormNo()), "30");
            this.tdPaApplyFormItemService.updateStatusByApplyFormNos(Arrays.asList(dto.getApplyFormNo()), "30");
         }
      }

      return isAccount;
   }

   private void getPrintVo(List inpatientOrderDTOList, Date currnDate, SysUser sysUser, List printList) {
      Set<String> set = new HashSet();

      for(InpatientOrderDTO inpatientOrderDTO : inpatientOrderDTOList) {
         if (!set.contains(inpatientOrderDTO.getOrderNo() + inpatientOrderDTO.getOrderSortNumber())) {
            set.add(inpatientOrderDTO.getOrderNo() + inpatientOrderDTO.getOrderSortNumber());
            Map<String, Object> param = new HashMap();
            param.put("case_no", inpatientOrderDTO.getCaseNo());
            param.put("admission_no", inpatientOrderDTO.getAdmissionNo());
            param.put("hospitalized_count", inpatientOrderDTO.getHospitalizedCount());
            if (StringUtils.isNotBlank(inpatientOrderDTO.getBabyAdmissionNo())) {
               param.put("yebz", "2");
            } else {
               param.put("yebz", "1");
            }

            param.put("type", "0");
            param.put("order_no", inpatientOrderDTO.getOrderNo());
            param.put("order_sort_number", inpatientOrderDTO.getOrderSortNumber());
            param.put("filing_date", currnDate);
            param.put("ward_no", sysUser.getDept().getDeptCode());
            param.put("operator", "");
            List<Map<String, Object>> list = this.patFeeService.getPrintDate(param);
            printList.addAll(list);
         }
      }

   }
}
