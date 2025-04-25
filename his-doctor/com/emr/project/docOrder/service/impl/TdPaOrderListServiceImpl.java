package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.ExcelUtils;
import com.emr.common.utils.ObjectUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.security.LoginUser;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaOrderOperationLog;
import com.emr.project.docOrder.domain.TdPaOrderSignMain;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.docOrder.domain.req.OderPerformDetailReq;
import com.emr.project.docOrder.domain.resp.OrderExecutionExpenseDetail;
import com.emr.project.docOrder.domain.resp.OrderStatusProcessDetail;
import com.emr.project.docOrder.domain.vo.InpatientTodayOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.OderPerformDetailVo;
import com.emr.project.docOrder.domain.vo.OderPerformResultVo;
import com.emr.project.docOrder.domain.vo.OrderApplyFormDetailVo;
import com.emr.project.docOrder.domain.vo.OrderApplyFormTestVo;
import com.emr.project.docOrder.domain.vo.OrderListBaseInfoVo;
import com.emr.project.docOrder.domain.vo.OrderListInfoVo;
import com.emr.project.docOrder.domain.vo.OrderListPatientVo;
import com.emr.project.docOrder.domain.vo.OrderListPrintVo;
import com.emr.project.docOrder.domain.vo.OrderListSearchVo;
import com.emr.project.docOrder.domain.vo.OrderListVo;
import com.emr.project.docOrder.domain.vo.OrderSignPicVo;
import com.emr.project.docOrder.domain.vo.OrderTakeDrugDTO;
import com.emr.project.docOrder.mapper.InpatientSuspendOrderMapper;
import com.emr.project.docOrder.mapper.OrderExecutionRecordMapper;
import com.emr.project.docOrder.mapper.TdPaOrderItemMapper;
import com.emr.project.docOrder.mapper.TdPaOrderListMapper;
import com.emr.project.docOrder.mapper.TdPaOrderPerformMapper;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderListService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.operation.mapper.ExpenseDetailMapper;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.other.mapper.BasCertInfoMapper;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.BsStaffMapper;
import com.emr.project.system.mapper.SysOrgMapper;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpa.domain.OrderFreq;
import com.emr.project.tmpa.domain.OrderSig;
import com.emr.project.tmpa.service.IOrderFreqService;
import com.emr.project.tmpa.service.IOrderSigService;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderListServiceImpl implements ITdPaOrderListService {
   @Autowired
   private TdPaOrderListMapper tdPaOrderListMapper;
   @Autowired
   private IOrderSigService orderSigService;
   @Autowired
   private IOrderFreqService orderFreqService;
   @Autowired
   private ITdPaOrderSignMainService orderSignMainService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private ITdPaOrderDetailService orderDetailService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IBabyInfoService babyInfoService;
   @Autowired
   private TdPaOrderItemMapper tdPaOrderItemMapper;
   @Autowired
   private ITdPaOrderOperationLogService operationLogService;
   @Autowired
   private TdPaOrderPerformMapper tdPaOrderPerformMapper;
   @Autowired
   private OrderExecutionRecordMapper orderExecutionRecordMapper;
   @Autowired
   private InpatientSuspendOrderMapper inpatientSuspendOrderMapper;
   @Autowired
   private ExpenseDetailMapper expenseDetailMapper;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private BsStaffMapper bsStaffMapper;
   @Autowired
   private SysOrgMapper sysOrgMapper;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private BasCertInfoMapper basCertInfoMapper;

   public OrderListBaseInfoVo queryOrderListBaseInfo(String patientId) throws Exception {
      OrderListBaseInfoVo res = new OrderListBaseInfoVo();
      VisitinfoPersonalVo visitinfoPersonalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
      List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(patientId);
      if (babyInfoList != null && !babyInfoList.isEmpty()) {
         List<BabyInfo> babyList = new ArrayList();
         BabyInfo babyInfo = new BabyInfo();
         babyInfo.setPatBabyId(patientId);
         babyInfo.setBabyName(visitinfoPersonalVo.getPatientName());
         babyList.add(babyInfo);
         babyList.addAll(babyInfoList);
         res.setBabyList(babyList);
      }

      return res;
   }

   public OrderListInfoVo operationLongOrderList(OrderListSearchVo orderListSearchVo, Map groupOrderMap, List list, int pageSize, String blankStr) throws Exception {
      OrderListVo firstOrderListVo = null;
      OrderListInfoVo res = new OrderListInfoVo();
      if (list != null && !list.isEmpty()) {
         List<String> orderNoList = (List)list.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderNo())).map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
         List<TdPaOrderDetail> detailList = this.orderDetailService.selectByOrderNoList(orderNoList);
         Map<String, List<TdPaOrderDetail>> detailListMap = (Map<String, List<TdPaOrderDetail>>)(CollectionUtils.isNotEmpty(detailList) ? (Map)detailList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo())) : new HashMap(1));
         Map<String, List<OrderListVo>> groupOrderMapCurr = (Map)list.stream().filter((t) -> t.getOrderGroupNo() != null).collect(Collectors.groupingBy((t) -> t.getOrderGroupNo() + "" + t.getReOrganizationNo()));
         List<String> itemOrderUsageWayList = (List)list.stream().map((t) -> t.getItemOrderUsageWay()).filter((t) -> StringUtils.isNotBlank(t)).distinct().collect(Collectors.toList());
         List<String> itemOrderFreqList = (List)list.stream().map((t) -> t.getItemOrderFreq()).filter((t) -> StringUtils.isNotBlank(t)).distinct().collect(Collectors.toList());
         List<OrderSig> orderSigList = this.orderSigService.selectOrderSigListBySigCd(itemOrderUsageWayList);
         List<OrderFreq> orderFreqList = this.orderFreqService.selectByFreqCdList(itemOrderFreqList);
         Map<String, OrderSig> orderSigMap = (Map)orderSigList.stream().collect(Collectors.toMap((t) -> t.getSigCd(), Function.identity()));
         Map<String, OrderFreq> orderFreqMap = (Map)orderFreqList.stream().collect(Collectors.toMap((t) -> t.getFreqCd(), Function.identity()));
         List<Integer> operationTypeList = Arrays.asList(0, 1, 4, 5, 6, 7);
         List<OrderSignPicVo> orderSignPicList = this.orderSignMainService.selectSignPicByOrderNos(orderNoList, operationTypeList);
         Map<String, List<OrderSignPicVo>> orderSignPicMap = (Map)orderSignPicList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo()));
         List<OrderSignPicVo> staffSignPicList = this.orderSignMainService.selectStaffSignPicAll();
         List<SysDictData> drippingSpeedList = this.sysDictDataService.selectDictDataByType("s071");
         Map<String, SysDictData> drippingSpeedMap = (Map)drippingSpeedList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
         List<TdPaOrderStatus> orderStatusList = this.tdPaOrderStatusService.selectByOrderNoListAndStatusList(orderNoList, (List)null);
         Map<String, List<TdPaOrderStatus>> orderStatusMap = (Map<String, List<TdPaOrderStatus>>)(CollectionUtils.isNotEmpty(orderStatusList) ? (Map)orderStatusList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo())) : new HashMap(1));
         List<SysDictData> purposeAntimicrobialUseList = this.sysDictDataService.selectDictDataByType("s063");
         Map<String, SysDictData> purposeAntimicrobialUseMap = (Map)purposeAntimicrobialUseList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));

         for(OrderListVo orderListVo : list) {
            String orderGroupNo = orderListVo.getOrderGroupNo();
            List<OrderListVo> tempList = orderGroupNo != null ? (List)groupOrderMap.get(orderGroupNo + "" + orderListVo.getReOrganizationNo()) : null;
            Integer currIndex = tempList != null ? tempList.indexOf(orderListVo) : null;
            if (StringUtils.isNotBlank(orderListVo.getOrderClassCode()) && orderListVo.getOrderClassCode().equals("01") && tempList != null && tempList.size() > 1) {
               if (orderListVo.getMasterOrder().equals("1")) {
                  orderListVo.setGroupStr("┓");
               } else if (currIndex + 1 == tempList.size()) {
                  orderListVo.setGroupStr("┛");
               } else {
                  orderListVo.setGroupStr("┃");
               }
            }

            OrderSig orderSig = orderSigMap.get(orderListVo.getItemOrderUsageWay()) != null ? (OrderSig)orderSigMap.get(orderListVo.getItemOrderUsageWay()) : new OrderSig();
            OrderFreq orderFreq = orderFreqMap.get(orderListVo.getItemOrderFreq()) != null ? (OrderFreq)orderFreqMap.get(orderListVo.getItemOrderFreq()) : new OrderFreq();
            orderListVo.setItemOrderUsageWayName(orderSig.getSigShowName());
            orderListVo.setItemOrderFreqName(orderFreq.getFreqShowName());
            String drippingSpeed = orderListVo.getDrippingSpeed();
            SysDictData drippingSpeedData = (SysDictData)drippingSpeedMap.get(orderListVo.getDrippingSpeed());
            if (StringUtils.isNotBlank(drippingSpeed) && drippingSpeedData != null) {
               orderListVo.setDrippingSpeed(drippingSpeedData.getDictLabel());
            }

            if (StringUtils.isNotBlank(orderListVo.getPurposeAntimicrobialUse())) {
               SysDictData purposeTemp = (SysDictData)purposeAntimicrobialUseMap.get(orderListVo.getPurposeAntimicrobialUse());
               orderListVo.setPurposeAntimicrobialUseName(purposeTemp != null ? purposeTemp.getDictLabel() : null);
            }

            if (StringUtils.isNotBlank(orderListVo.getOrderNo())) {
               List<OrderSignPicVo> orderSignPicVoList = (List)orderSignPicMap.get(orderListVo.getOrderNo());
               this.setStatusDocNurseInfo((List)orderStatusMap.get(orderListVo.getOrderNo()), orderListVo, orderSignPicVoList);
               this.setSignImgByDocNurseInfo(orderListVo, staffSignPicList);
               String cpName = this.getCpName(orderListVo);
               orderListVo.setCpName(cpName);
               List<TdPaOrderDetail> detailListTemp = (List)detailListMap.get(orderListVo.getOrderNo());
               if (!orderListVo.getOrderClassCode().equals("01") && !orderListVo.getOrderClassCode().equals("02") && !orderListVo.getOrderClassCode().equals("03") && !orderListVo.getOrderClassCode().equals("07") && CollectionUtils.isNotEmpty(detailListTemp)) {
                  TdPaOrderDetail detail = (TdPaOrderDetail)detailListTemp.get(0);
                  BigDecimal orderActualUsage = detail.getOrderActualUsage() == null ? new BigDecimal("1") : detail.getOrderActualUsage();
                  String usageUnit = StringUtils.isBlank(detail.getUsageUnit()) ? "次" : detail.getUsageUnit();
                  orderListVo.setOrderActualUsage(orderActualUsage);
                  orderListVo.setUsageUnit(usageUnit);
                  String unit = StringUtils.isNotBlank(orderListVo.getUnit()) ? orderListVo.getUnit() : "次";
                  orderListVo.setUnit(unit);
               }

               if (!orderListVo.getOrderClassCode().equals("01") && CollectionUtils.isNotEmpty(detailListTemp)) {
                  TdPaOrderDetail detail = (TdPaOrderDetail)detailListTemp.get(0);
                  String skinTestResults = detail.getSkinTestResults();
                  if (StringUtils.isBlank(orderListVo.getSkinTestResults()) && StringUtils.isNotBlank(skinTestResults)) {
                     orderListVo.setSkinTestResults(skinTestResults);
                  }
               }

               if (orderListVo.getOrderClassCode().equals("07")) {
                  orderListVo.setOrderDose((BigDecimal)null);
               }
            }
         }

         OrderListVo orderListVo = (OrderListVo)list.get(list.size() - 1);
         if (list.size() == pageSize && StringUtils.isNotBlank(orderListVo.getOrderClassCode()) && orderListVo.getOrderClassCode().equals("01")) {
            List<OrderListVo> tempList1 = (List)groupOrderMapCurr.get(orderListVo.getOrderGroupNo() + "" + orderListVo.getReOrganizationNo());
            List<OrderListVo> tempList2 = (List)groupOrderMap.get(orderListVo.getOrderGroupNo() + "" + orderListVo.getReOrganizationNo());
            if (tempList1 != null && tempList2 != null && tempList2.size() > tempList1.size()) {
               list = list.subList(0, list.size() - tempList1.size());

               for(int i = 0; i < tempList1.size(); ++i) {
                  OrderListVo temp = new OrderListVo();
                  temp.setCpName(blankStr);
                  list.add(temp);
               }
            }
         } else {
            int listSize = list.size();

            for(int i = 0; i < pageSize - listSize; ++i) {
               OrderListVo temp = new OrderListVo();
               list.add(temp);
            }
         }

         firstOrderListVo = (OrderListVo)list.get(0);
      }

      OrderListPatientVo orderListPatientVo = this.getOrderListPatient(orderListSearchVo, firstOrderListVo);
      res.setPatientInfo(orderListPatientVo);
      if (CollectionUtils.isNotEmpty(list) && list.size() > 0) {
         LoginUser loginUser = SecurityUtils.getLoginUser();
         SysUser user = new SysUser();
         if (loginUser != null) {
            user = loginUser.getUser();
         } else {
            BsStaff record = new BsStaff();
            record.setStaffCode("sa");
            BsStaff bsStaff = this.bsStaffMapper.selectByConn(record);
            SysOrg sysOrg = this.sysOrgMapper.selectSysOrgByOrgNo(bsStaff.getHospitalNo());
            user.setHospital(sysOrg);
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");

         for(OrderListVo orderListVo : list) {
            orderListVo.setPatientName(orderListPatientVo.getPatientName());
            orderListVo.setSex(orderListPatientVo.getSex());
            orderListVo.setAge(orderListPatientVo.getAge());
            orderListVo.setBedNo(orderListPatientVo.getBedNo());
            orderListVo.setInpNo(orderListPatientVo.getInpNo());
            orderListVo.setRecordNo(orderListPatientVo.getRecordNo());
            orderListVo.setHospital(user.getHospital().getOrgName());
            orderListVo.setCaFlag(caFlag);
            orderListVo.setCaType(caType);
            if (orderListVo.getOrderDose() != null && orderListVo.getOrderDose().compareTo(BigDecimal.ONE) <= 0 || orderListVo.getOrderDose() == null) {
               orderListVo.setOrderDose((BigDecimal)null);
               orderListVo.setUnit((String)null);
            }
         }
      }

      res.setList(list);
      return res;
   }

   private OrderListPatientVo getOrderListPatient(OrderListSearchVo orderListSearchVo, OrderListVo firstOrderListVo) throws Exception {
      OrderListPatientVo orderListPatientVo = new OrderListPatientVo();
      orderListPatientVo.setPatientId(orderListSearchVo.getPatientId());
      VisitinfoPersonalVo visitinfo = this.visitinfoService.selectVisitinfoPersonalById(orderListSearchVo.getPatientId());
      if (StringUtils.isNotBlank(orderListSearchVo.getBabyAdmissionNo()) && !orderListSearchVo.getBabyAdmissionNo().equals(orderListSearchVo.getPatientId())) {
         List<BabyInfo> babyInfoList = this.babyInfoService.selectListByBabyAdmissionNoList(Arrays.asList(orderListSearchVo.getBabyAdmissionNo()));
         BabyInfo babyInfo = CollectionUtils.isNotEmpty(babyInfoList) ? (BabyInfo)babyInfoList.get(0) : null;
         if (babyInfo != null) {
            List<SysDictData> sexDataList = this.sysDictDataService.selectDictDataByType("s028");
            Map<String, SysDictData> sysDictDataMap = (Map<String, SysDictData>)(CollectionUtils.isNotEmpty(sexDataList) ? (Map)sexDataList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity())) : new HashMap());
            orderListPatientVo.setPatientName(babyInfo.getBabyName());
            orderListPatientVo.setDeptCd(visitinfo.getDeptCd());
            orderListPatientVo.setDeptName(visitinfo.getDeptName());
            orderListPatientVo.setSex(((SysDictData)sysDictDataMap.get(babyInfo.getBabySexCd())).getDictLabel());
            orderListPatientVo.setAge("新生儿");
            orderListPatientVo.setBedNo(visitinfo.getBedNo());
            orderListPatientVo.setInpNo(orderListSearchVo.getBabyAdmissionNo());
            orderListPatientVo.setRecordNo(orderListSearchVo.getBabyAdmissionNo());
            if (firstOrderListVo != null) {
               orderListPatientVo.setDeptCd(firstOrderListVo.getDeptCd());
               orderListPatientVo.setDeptName(firstOrderListVo.getDeptName());
               orderListPatientVo.setBedNo(firstOrderListVo.getBedNo());
            }
         }
      } else {
         orderListPatientVo.setPatientName(visitinfo.getPatientName());
         orderListPatientVo.setDeptCd(visitinfo.getDeptCd());
         orderListPatientVo.setDeptName(visitinfo.getDeptName());
         orderListPatientVo.setSex(visitinfo.getSex());
         String ageStr = AgeUtil.getAgeStr(visitinfo.getAgeY(), visitinfo.getAgeM(), visitinfo.getAgeD(), visitinfo.getAgeH(), visitinfo.getAgeMi());
         orderListPatientVo.setAge(ageStr);
         orderListPatientVo.setBedNo(visitinfo.getBedNo());
         orderListPatientVo.setInpNo(visitinfo.getInpNo());
         orderListPatientVo.setRecordNo(visitinfo.getRecordNo());
         if (firstOrderListVo != null) {
            orderListPatientVo.setDeptCd(firstOrderListVo.getDeptCd());
            orderListPatientVo.setDeptName(firstOrderListVo.getDeptName());
            orderListPatientVo.setBedNo(firstOrderListVo.getBedNo());
         }
      }

      return orderListPatientVo;
   }

   private void setOrderSignPic(OrderListVo orderListVo, List orderSignPicVoList) {
      if (orderSignPicVoList != null && !orderSignPicVoList.isEmpty()) {
         for(OrderSignPicVo orderSignPicVo : orderSignPicVoList) {
            String certPic = StringUtils.isNotEmpty(orderSignPicVo.getCertPic()) ? "data:image/gif;base64," + orderSignPicVo.getCertPic() : null;
            switch (Integer.valueOf(orderSignPicVo.getOperationType())) {
               case 0:
                  orderListVo.setSubmitDoctorSignImg(certPic);
                  break;
               case 1:
                  orderListVo.setOrderAuditDocSignImg(certPic);
               case 2:
               case 3:
               default:
                  break;
               case 4:
                  orderListVo.setOrderStopDocSignImg(certPic);
                  break;
               case 5:
                  orderListVo.setOrderStopNurseSignImg(certPic);
            }
         }
      }

   }

   private String getOrderSignPic(String operatorCode, String operationType, List orderSignPicVoList, boolean firstFlag) {
      String signImg = null;
      List<OrderSignPicVo> auditSignList = orderSignPicVoList != null && !orderSignPicVoList.isEmpty() ? (List)orderSignPicVoList.stream().filter((t) -> t.getOperatorCode().equals(operatorCode) && t.getOperationType().equals(operationType)).sorted(Comparator.comparing(TdPaOrderSignMain::getSignTime)).collect(Collectors.toList()) : null;
      if (auditSignList != null && !auditSignList.isEmpty()) {
         String certPic = firstFlag ? ((OrderSignPicVo)auditSignList.get(0)).getCertPic() : ((OrderSignPicVo)auditSignList.get(auditSignList.size() - 1)).getCertPic();
         signImg = StringUtils.isNotBlank(certPic) ? "data:image/gif;base64," + certPic : signImg;
      }

      return signImg;
   }

   private String getSignPicByStaffCode(String operatorCode, List staffSignPicList) {
      String signImg = null;
      List<OrderSignPicVo> staffs = (List)staffSignPicList.stream().filter((t) -> t.getEmployeenumber().equals(operatorCode)).collect(Collectors.toList());
      if (StringUtils.isNotBlank(operatorCode) && staffs.size() > 0) {
         String certPic = ((OrderSignPicVo)staffs.get(0)).getCertPic();
         signImg = StringUtils.isNotBlank(certPic) ? "data:image/gif;base64," + certPic : signImg;
      }

      return signImg;
   }

   private String getCpName(OrderListVo orderListVo) {
      String cpName = orderListVo.getCpName();
      if (orderListVo.getOrderClassCode().equals("01")) {
         cpName = cpName + (StringUtils.isNotBlank(orderListVo.getStandard()) ? "[" + orderListVo.getStandard() + "]" : "");
      }

      if (orderListVo.getOrderClassCode().equals("02")) {
         cpName = cpName + (StringUtils.isNotBlank(orderListVo.getExamPart()) ? "(" + orderListVo.getExamPart() + ")" : "");
      }

      if (orderListVo.getOrderCancelTime() != null) {
         String cancelStr = "(取消 {time})".replace("{time}", DateUtils.parseDateToStr(DateUtils.HH_MM, orderListVo.getOrderCancelTime()));
         cpName = cancelStr + cpName;
      }

      if (StringUtils.isNotBlank(orderListVo.getPatientSelfDrugFlag()) && orderListVo.getPatientSelfDrugFlag().equals("3")) {
         cpName = "(自)" + cpName;
      }

      if (StringUtils.isNotBlank(orderListVo.getDoctorInstructions())) {
         String doctorInstructions = StringUtils.isNotBlank(orderListVo.getDoctorInstructions()) ? orderListVo.getDoctorInstructions() : "";
         cpName = cpName + "({})".replace("{}", doctorInstructions);
      }

      if (StringUtils.isNotBlank(orderListVo.getOutHavaDrugFlag()) && orderListVo.getOutHavaDrugFlag().equals("1")) {
         cpName = "(带)" + cpName;
      }

      if (StringUtils.isNotBlank(orderListVo.getAdditionalFlag()) && orderListVo.getAdditionalFlag().equals("1")) {
         cpName = cpName + "(补)";
      }

      return cpName;
   }

   private void setStatusDocNurseInfo(List orderStatusList, OrderListVo orderListVo, List orderSignPicVoList) {
      Map<String, List<TdPaOrderStatus>> orderStatusMap = (Map<String, List<TdPaOrderStatus>>)(CollectionUtils.isNotEmpty(orderStatusList) ? (Map)orderStatusList.stream().collect(Collectors.groupingBy((t) -> t.getOperationType())) : new HashMap(1));
      if (StringUtils.isNotBlank(orderListVo.getSubmitDoctorNo()) && StringUtils.isNotBlank(orderListVo.getSubmitDoctorName())) {
         String submitDocSignImg = this.getOrderSignPic(orderListVo.getSubmitDoctorNo(), "0", orderSignPicVoList, false);
         orderListVo.setSubmitDoctorSignImg(submitDocSignImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderFlagCd()) && (orderListVo.getOrderFlagCd().equals("01") || orderListVo.getOrderFlagCd().equals("02") || orderListVo.getOrderFlagCd().equals("03"))) {
         String submitDocSignImg = this.getOrderSignPic(orderListVo.getOrderStopDoc(), "4", orderSignPicVoList, false);
         orderListVo.setSubmitDoctorSignImg(submitDocSignImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderFlagCd()) && (orderListVo.getOrderFlagCd().equals("01") || orderListVo.getOrderFlagCd().equals("02") || orderListVo.getOrderFlagCd().equals("03"))) {
         String auditDocSignImg = this.getOrderSignPic(orderListVo.getOrderStopAuditDoc(), "1", orderSignPicVoList, false);
         orderListVo.setOrderAuditDocSignImg(auditDocSignImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderAuditDoc()) && StringUtils.isNotBlank(orderListVo.getOrderAuditDocName())) {
         String auditDocSignImg = this.getOrderSignPic(orderListVo.getOrderAuditDoc(), "1", orderSignPicVoList, false);
         orderListVo.setOrderAuditDocSignImg(auditDocSignImg);
      } else {
         List<TdPaOrderStatus> auditStatusList = (List)orderStatusMap.get("1");
         if (CollectionUtils.isNotEmpty(auditStatusList)) {
            auditStatusList = (List)auditStatusList.stream().sorted(Comparator.comparing(TdPaOrderStatus::getOperationTime)).collect(Collectors.toList());
            TdPaOrderStatus auditStatus = (TdPaOrderStatus)auditStatusList.get(auditStatusList.size() - 1);
            orderListVo.setOrderAuditDoc(auditStatus.getOperatorNo());
            orderListVo.setOrderAuditDocName(auditStatus.getOperatorName());
            String auditDocSignImg = this.getOrderSignPic(orderListVo.getOrderAuditDoc(), "1", orderSignPicVoList, false);
            orderListVo.setOrderAuditDocSignImg(auditDocSignImg);
         }
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderStopDoc()) && StringUtils.isNotBlank(orderListVo.getOrderStopDocName())) {
         String stopDocSignImg = this.getOrderSignPic(orderListVo.getOrderStopDoc(), "4", orderSignPicVoList, false);
         orderListVo.setOrderStopDocSignImg(stopDocSignImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderStopAuditDoc())) {
         orderListVo.setOrderStopNurse(orderListVo.getOrderStopAuditDoc());
         orderListVo.setOrderStopNurseName(orderListVo.getOrderStopAuditDocName());
         String signImg = this.getOrderSignPic(orderListVo.getOrderStopAuditDoc(), "5", orderSignPicVoList, false);
         signImg = StringUtils.isNotBlank(signImg) ? signImg : this.getOrderSignPic(orderListVo.getOrderStopAuditDoc(), "4", orderSignPicVoList, false);
         signImg = StringUtils.isNotBlank(signImg) ? signImg : this.getOrderSignPic(orderListVo.getOrderStopAuditDoc(), "1", orderSignPicVoList, false);
         orderListVo.setOrderStopNurseSignImg(signImg);
      }

      List<TdPaOrderStatus> stopedStatusList = (List)orderStatusMap.get("5");
      if (orderListVo.getOrderStatus().equals("5") && StringUtils.isBlank(orderListVo.getOrderStopAuditDoc()) && CollectionUtils.isNotEmpty(stopedStatusList)) {
         stopedStatusList = (List)stopedStatusList.stream().sorted(Comparator.comparing(TdPaOrderStatus::getOperationTime)).collect(Collectors.toList());
         TdPaOrderStatus stopStatus = (TdPaOrderStatus)stopedStatusList.get(stopedStatusList.size() - 1);
         orderListVo.setOrderStopNurse(stopStatus.getOperatorNo());
         orderListVo.setOrderStopNurseName(stopStatus.getOperatorName());
         orderListVo.setOrderStopAuditDoc(stopStatus.getOperatorNo());
         String signImg = this.getOrderSignPic(orderListVo.getOrderStopNurse(), "5", orderSignPicVoList, false);
         signImg = StringUtils.isNotBlank(signImg) ? signImg : this.getOrderSignPic(orderListVo.getOrderStopAuditDoc(), "4", orderSignPicVoList, false);
         orderListVo.setOrderStopNurseSignImg(signImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderPerformNurse()) && StringUtils.isNotBlank(orderListVo.getOrderPerformNurseName())) {
         String signImg = this.getOrderSignPic(orderListVo.getOrderPerformNurse(), "8", orderSignPicVoList, true);
         signImg = StringUtils.isNotBlank(signImg) ? signImg : this.getOrderSignPic(orderListVo.getOrderPerformNurse(), "3", orderSignPicVoList, true);
         signImg = StringUtils.isNotBlank(signImg) ? signImg : this.getOrderSignPic(orderListVo.getOrderPerformNurse(), "1", orderSignPicVoList, true);
         orderListVo.setOrderPerformNurseSignImg(signImg);
      } else {
         List<TdPaOrderStatus> performedStatusList = (List)orderStatusMap.get("8");
         if (CollectionUtils.isNotEmpty(performedStatusList)) {
            performedStatusList = (List)performedStatusList.stream().sorted(Comparator.comparing(TdPaOrderStatus::getOperationTime)).collect(Collectors.toList());
            TdPaOrderStatus performedStatus = (TdPaOrderStatus)performedStatusList.get(0);
            orderListVo.setOrderPerformNurse(performedStatus.getOperatorNo());
            orderListVo.setOrderPerformNurseName(performedStatus.getOperatorName());
            orderListVo.setOrderPerformTime(orderListVo.getOrderPerformTime() == null ? performedStatus.getOperationTime() : orderListVo.getOrderPerformTime());
            String signImg = this.getOrderSignPic(orderListVo.getOrderStopNurse(), "8", orderSignPicVoList, true);
            orderListVo.setOrderPerformNurseSignImg(signImg);
         }

         List<TdPaOrderStatus> performStatusList = (List)orderStatusMap.get("3");
         if (CollectionUtils.isNotEmpty(performStatusList)) {
            performStatusList = (List)performStatusList.stream().filter((t) -> t.getOperationTime() != null).sorted(Comparator.comparing(TdPaOrderStatus::getOperationTime)).collect(Collectors.toList());
            if (performStatusList != null && !performStatusList.isEmpty()) {
               TdPaOrderStatus performedStatus = (TdPaOrderStatus)performStatusList.get(0);
               orderListVo.setOrderPerformNurse(performedStatus.getOperatorNo());
               orderListVo.setOrderPerformNurseName(performedStatus.getOperatorName());
               orderListVo.setOrderPerformTime(orderListVo.getOrderPerformTime() == null ? performedStatus.getOperationTime() : orderListVo.getOrderPerformTime());
               String signImg = this.getOrderSignPic(orderListVo.getOrderStopNurse(), "8", orderSignPicVoList, true);
               orderListVo.setOrderPerformNurseSignImg(signImg);
            }
         }
      }

   }

   private void setSignImgByDocNurseInfo(OrderListVo orderListVo, List staffSignPicList) {
      if (StringUtils.isNotBlank(orderListVo.getOrderFlagCd()) && orderListVo.getOrderFlagCd().equals("04") && StringUtils.isNotBlank(orderListVo.getOrderStopDoc())) {
         orderListVo.setSubmitDoctorNo(orderListVo.getOrderStartDoc());
         orderListVo.setOrderStopDoc(orderListVo.getOrderStartDoc());
         this.getSignPicByStaffCode(orderListVo.getOrderStopDoc(), staffSignPicList);
      }

      if (StringUtils.isNotBlank(orderListVo.getSubmitDoctorNo()) && StringUtils.isNotBlank(orderListVo.getSubmitDoctorName())) {
         String submitDocSignImg = this.getSignPicByStaffCode(orderListVo.getSubmitDoctorNo(), staffSignPicList);
         orderListVo.setSubmitDoctorSignImg(submitDocSignImg);
      }

      if (StringUtils.isBlank(orderListVo.getSubmitDoctorNo()) && StringUtils.isNotBlank(orderListVo.getOrderFlagCd()) && (orderListVo.getOrderFlagCd().equals("01") || orderListVo.getOrderFlagCd().equals("02") || orderListVo.getOrderFlagCd().equals("03"))) {
         String submitDocSignImg = this.getSignPicByStaffCode(orderListVo.getOrderStopDoc(), staffSignPicList);
         orderListVo.setSubmitDoctorSignImg(submitDocSignImg);
      }

      if (StringUtils.isBlank(orderListVo.getOrderAuditDoc()) && StringUtils.isNotBlank(orderListVo.getOrderFlagCd()) && (orderListVo.getOrderFlagCd().equals("01") || orderListVo.getOrderFlagCd().equals("02") || orderListVo.getOrderFlagCd().equals("03"))) {
         String auditDocSignImg = this.getSignPicByStaffCode(orderListVo.getOrderStopAuditDoc(), staffSignPicList);
         orderListVo.setOrderAuditDocSignImg(auditDocSignImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderAuditDoc()) && StringUtils.isNotBlank(orderListVo.getOrderAuditDocName())) {
         String auditDocSignImg = this.getSignPicByStaffCode(orderListVo.getOrderAuditDoc(), staffSignPicList);
         orderListVo.setOrderAuditDocSignImg(auditDocSignImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderStopDoc()) && StringUtils.isNotBlank(orderListVo.getOrderStopDocName())) {
         String stopDocSignImg = this.getSignPicByStaffCode(orderListVo.getOrderStopDoc(), staffSignPicList);
         orderListVo.setOrderStopDocSignImg(stopDocSignImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderStopAuditDoc())) {
         orderListVo.setOrderStopNurse(orderListVo.getOrderStopAuditDoc());
         String signImg = this.getSignPicByStaffCode(orderListVo.getOrderStopAuditDoc(), staffSignPicList);
         orderListVo.setOrderStopNurseSignImg(signImg);
      }

      if (StringUtils.isNotBlank(orderListVo.getOrderPerformNurse()) && StringUtils.isNotBlank(orderListVo.getOrderPerformNurseName())) {
         String signImg = this.getSignPicByStaffCode(orderListVo.getOrderPerformNurse(), staffSignPicList);
         orderListVo.setOrderPerformNurseSignImg(signImg);
      }

   }

   private List subListByOrderItemType(List list) {
      List<OrderListVo> subList = list;
      int index = -1;

      for(int i = 0; i < list.size(); ++i) {
         OrderListVo orderListVo = (OrderListVo)list.get(i);
         if (orderListVo.getOrderFlagCd().equals("02") || orderListVo.getOrderFlagCd().equals("03") || orderListVo.getOrderFlagCd().equals("04")) {
            index = i;
            break;
         }
      }

      if (index > 0) {
         subList = list.subList(0, index);
      }

      return subList;
   }

   public List queryOrderTempListPage(int pageNum, String patientId) throws Exception {
      return null;
   }

   public OrderListInfoVo queryDecoctionTotal(OrderListSearchVo orderListSearchVo, int pageSize, String blankStr) throws Exception {
      orderListSearchVo.setPplorderGroupNo((Integer)null);
      orderListSearchVo.setPageSize((Integer)null);
      List<OrderListVo> listAll = this.tdPaOrderListMapper.selectOrderDecoctionListPage(orderListSearchVo);
      Map<String, List<OrderListVo>> listMap = (Map)listAll.stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo()));
      List<List<OrderListVo>> pageDataList = new ArrayList(1);
      List<OrderListVo> pageList = new ArrayList(pageSize);
      List<OrderListVo> herbalListAll = new ArrayList(1);
      int orderNum = 3;
      List<String> orderGroupNoStrList = new ArrayList(listMap.keySet());
      List<Integer> orderGroupNoList = (List)orderGroupNoStrList.stream().map(Integer::valueOf).collect(Collectors.toList());

      for(Integer orderGroupNo : (List)orderGroupNoList.stream().sorted().collect(Collectors.toList())) {
         String orderGroupNoStr = orderGroupNo + "";
         List<OrderListVo> herbalListGroup = new ArrayList(1);
         List<OrderListVo> orderGroupList = (List)listMap.get(orderGroupNoStr);
         List<OrderListVo> herbalList = null;
         OrderListVo mainHerbal = null;

         for(int i = 0; i < orderGroupList.size(); ++i) {
            OrderListVo temp = (OrderListVo)orderGroupList.get(i);
            temp.setHerbalList((List)null);
            if (i % orderNum == 0) {
               if (mainHerbal != null) {
                  herbalListAll.add(mainHerbal);
                  herbalListGroup.add(mainHerbal);
               }

               mainHerbal = new OrderListVo();
               BeanUtils.copyProperties(temp, mainHerbal);
               if (temp.getOrderCancelTime() != null) {
                  String cancelStr = "(取消 {time})".replace("{time}", DateUtils.parseDateToStr(DateUtils.HH_MM, temp.getOrderCancelTime()));
                  String cpName = cancelStr + temp.getCpName();
                  temp.setCpName(cpName);
               }

               List var32 = new ArrayList(orderNum);
               mainHerbal.setHerbalList(var32);
            }

            mainHerbal.getHerbalList().add(temp);
         }

         int herbalListSize = herbalListGroup.size() * orderNum;
         int groupListSize = orderGroupList.size();
         if (herbalListSize != groupListSize) {
            int subHerbalListSize = mainHerbal.getHerbalList().size();

            for(int i = 0; i < orderNum - subHerbalListSize; ++i) {
               mainHerbal.getHerbalList().add(new OrderListVo());
            }

            herbalListAll.add(mainHerbal);
         }

         OrderListVo orderListVo = (OrderListVo)orderGroupList.get(0);
         orderListVo.setHerbalList((List)null);
         orderListVo.setMasterOrder("2");
         herbalListAll.add(orderListVo);
      }

      Map<String, List<OrderListVo>> herbalMap = (Map)herbalListAll.stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo() + "" + t.getReOrganizationNo()));

      for(int i = 0; i < herbalListAll.size(); ++i) {
         OrderListVo orderListVo = (OrderListVo)herbalListAll.get(i);
         if (StringUtils.isNotBlank(orderListVo.getOrderFlagCd()) && orderListVo.getOrderFlagCd().equals("02")) {
            List<OrderListVo> temp = (List)herbalMap.get(orderListVo.getOrderGroupNo() + "" + orderListVo.getReOrganizationNo());
            if (pageList.size() > 0) {
               if (herbalListAll.size() - (i + 1) > temp.size()) {
                  this.addBlankRow(pageList, pageSize, blankStr);
               }

               pageDataList.add(pageList);
               pageList = new ArrayList(pageSize);
            }
         } else if (StringUtils.isNotBlank(orderListVo.getOrderFlagCd()) && orderListVo.getOrderFlagCd().equals("04")) {
            List<OrderListVo> temp = (List)herbalMap.get(orderListVo.getOrderGroupNo() + "" + orderListVo.getReOrganizationNo());
            if (pageList.size() > 0) {
               if (herbalListAll.size() - (i + 1) > temp.size()) {
                  this.addBlankRow(pageList, pageSize, blankStr);
               }

               pageDataList.add(pageList);
               pageList = new ArrayList(pageSize);
            }
         } else {
            pageList.add(orderListVo);
            if (pageSize == pageList.size()) {
               List<OrderListVo> subList = (List)pageList.stream().filter((t) -> t.getOrderGroupNo().equals(orderListVo.getOrderGroupNo())).collect(Collectors.toList());
               pageDataList.add(pageList);
               pageList = new ArrayList(pageSize);
               int subSize = subList.size();
               List<OrderListVo> temp = (List)herbalMap.get(orderListVo.getOrderGroupNo() + "" + orderListVo.getReOrganizationNo());
               if (subSize < temp.size()) {
                  pageList.addAll(subList);
               }
            }
         }
      }

      if (pageList.size() > 0 && pageList.size() < pageSize) {
         pageDataList.add(pageList);
      }

      OrderListInfoVo res = new OrderListInfoVo();
      res.setListAll(listAll);
      res.setPageDataList(pageDataList);
      res.setHerbalMap(herbalMap);
      return res;
   }

   public OrderListInfoVo operationDecoctionOrderList(OrderListSearchVo orderListSearchVo, Map herbalMap, List list, int pageSize, String blankStr) throws Exception {
      OrderListVo firstOrderListVo = null;
      OrderListInfoVo res = new OrderListInfoVo();
      if (list != null && !list.isEmpty()) {
         List<String> orderNoList = (List)list.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderNo())).map((t) -> t.getOrderNo()).collect(Collectors.toList());
         List<Integer> operationTypeList = Arrays.asList(0, 1, 4, 5, 6, 7);
         List<OrderSignPicVo> orderSignPicList = this.orderSignMainService.selectSignPicByOrderNos(orderNoList, operationTypeList);
         Map<String, List<OrderSignPicVo>> orderSignPicMap = (Map)orderSignPicList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo()));
         List<OrderSignPicVo> staffSignPicList = this.orderSignMainService.selectStaffSignPicAll();
         List<TdPaOrderStatus> orderStatusList = this.tdPaOrderStatusService.selectByOrderNoListAndStatusList(orderNoList, (List)null);
         Map<String, List<TdPaOrderStatus>> orderStatusMap = (Map<String, List<TdPaOrderStatus>>)(CollectionUtils.isNotEmpty(orderStatusList) ? (Map)orderStatusList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo())) : new HashMap(1));
         Map<String, List<OrderListVo>> groupOrderMapCurr = (Map)list.stream().filter((t) -> t.getOrderGroupNo() != null).collect(Collectors.groupingBy((t) -> t.getOrderGroupNo() + "" + t.getReOrganizationNo()));

         for(OrderListVo orderListVo : list) {
            String orderGroupNo = orderListVo.getOrderGroupNo();
            List<OrderListVo> tempList = orderGroupNo != null ? (List)herbalMap.get(orderGroupNo + "" + orderListVo.getReOrganizationNo()) : null;
            Integer currIndex = tempList != null ? tempList.indexOf(orderListVo) : null;
            if (StringUtils.isNotBlank(orderListVo.getOrderClassCode()) && tempList != null && orderListVo.getOrderClassCode().equals("01") && tempList.size() > 1) {
               if (currIndex == 0 && orderListVo.getMasterOrder().equals("1")) {
                  orderListVo.setGroupStr("┓");
               } else if (currIndex + 1 == tempList.size()) {
                  orderListVo.setGroupStr("┛");
               } else {
                  orderListVo.setGroupStr("┃");
               }
            }

            if (StringUtils.isNotBlank(orderListVo.getOrderNo())) {
               List<OrderSignPicVo> orderSignPicVoList = (List)orderSignPicMap.get(orderListVo.getOrderNo());
               this.setStatusDocNurseInfo((List)orderStatusMap.get(orderListVo.getOrderNo()), orderListVo, orderSignPicVoList);
               this.setSignImgByDocNurseInfo(orderListVo, staffSignPicList);
            }
         }

         if (list.size() == pageSize) {
            OrderListVo orderListVo = (OrderListVo)list.get(list.size() - 1);
            List<OrderListVo> tempList1 = (List)groupOrderMapCurr.get(orderListVo.getOrderGroupNo() + "" + orderListVo.getReOrganizationNo());
            List<OrderListVo> tempList2 = (List)herbalMap.get(orderListVo.getOrderGroupNo() + "" + orderListVo.getReOrganizationNo());
            if (tempList1 != null && tempList2 != null && tempList2.size() > tempList1.size()) {
               list = list.subList(0, list.size() - tempList1.size());

               for(int i = 0; i < tempList1.size(); ++i) {
                  OrderListVo temp = new OrderListVo();
                  temp.setCpName(blankStr);
                  list.add(temp);
               }
            }
         } else {
            int listSize = list.size();

            for(int i = 0; i < pageSize - listSize; ++i) {
               OrderListVo temp = new OrderListVo();
               list.add(temp);
            }
         }

         firstOrderListVo = (OrderListVo)list.get(0);
      }

      OrderListPatientVo orderListPatientVo = this.getOrderListPatient(orderListSearchVo, firstOrderListVo);
      res.setPatientInfo(orderListPatientVo);
      if (CollectionUtils.isNotEmpty(list) && list.size() > 0) {
         LoginUser loginUser = SecurityUtils.getLoginUser();
         SysUser user = new SysUser();
         if (loginUser != null) {
            user = loginUser.getUser();
         } else {
            BsStaff record = new BsStaff();
            record.setStaffCode("sa");
            BsStaff bsStaff = this.bsStaffMapper.selectByConn(record);
            SysOrg sysOrg = this.sysOrgMapper.selectSysOrgByOrgNo(bsStaff.getHospitalNo());
            user.setHospital(sysOrg);
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");

         for(OrderListVo orderListVo : list) {
            orderListVo.setPatientName(orderListPatientVo.getPatientName());
            orderListVo.setSex(orderListPatientVo.getSex());
            orderListVo.setAge(orderListPatientVo.getAge());
            orderListVo.setBedNo(orderListPatientVo.getBedNo());
            orderListVo.setInpNo(orderListPatientVo.getInpNo());
            orderListVo.setRecordNo(orderListPatientVo.getRecordNo());
            orderListVo.setHospital(user.getHospital().getOrgName());
            orderListVo.setCaFlag(caFlag);
            orderListVo.setCaType(caType);
         }
      }

      res.setList(list);
      return res;
   }

   private List getReOrganizationListAll(List listAll, List reOrganizationList) {
      List<OrderListVo> reOrganizationListAll = listAll;
      if (reOrganizationList != null && !reOrganizationList.isEmpty()) {
         reOrganizationListAll = new ArrayList(listAll.size());
         Map<Integer, List<OrderListVo>> reOrganizationListMap = (Map)listAll.stream().collect(Collectors.groupingBy((t) -> t.getReOrganizationNo()));
         List<Integer> reOrganizations = (List)reOrganizationListMap.keySet().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

         for(int i = 0; i < reOrganizations.size(); ++i) {
            List<OrderListVo> reOrganizationListTemp = (List)reOrganizationListMap.get(reOrganizations.get(i));
            List<OrderListVo> reOrganizationListTemp2 = (List)reOrganizationListTemp.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderFlagCd()) && t.getOrderFlagCd().equals("04")).collect(Collectors.toList());
            if (reOrganizationListTemp2 != null && !reOrganizationListTemp2.isEmpty()) {
               OrderListVo reOrganizationOrder = (OrderListVo)reOrganizationListTemp2.get(0);
               reOrganizationListAll.add(reOrganizationOrder);
               reOrganizationListTemp = (List)reOrganizationListTemp.stream().filter((t) -> !t.getOrderNo().equals(reOrganizationOrder.getOrderNo())).collect(Collectors.toList());
            }

            reOrganizationListTemp = (List)reOrganizationListTemp.stream().sorted(Comparator.comparing(OrderListVo::getOrderStartTime)).collect(Collectors.toList());
            reOrganizationListAll.addAll(reOrganizationListTemp);
         }
      }

      return reOrganizationListAll;
   }

   public OrderListInfoVo getPageTotal(OrderListSearchVo orderListSearchVo, int pageSize, String blankStr, String allFlag) {
      orderListSearchVo.setPplorderGroupNo((Integer)null);
      orderListSearchVo.setPageSize((Integer)null);
      List<OrderListVo> listAll = null;
      if (StringUtils.isNotBlank(allFlag) && allFlag.equals("1")) {
         listAll = this.tdPaOrderListMapper.selectOrderLongAllListPage(orderListSearchVo);
      } else {
         listAll = this.tdPaOrderListMapper.selectOrderLongListPage(orderListSearchVo);
      }

      List<OrderListVo> reOrganizationList = (List)listAll.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderFlagCd()) && t.getOrderFlagCd().equals("04")).collect(Collectors.toList());
      listAll = this.getReOrganizationListAll(listAll, reOrganizationList);
      Map<String, List<OrderListVo>> listMap = (Map)listAll.stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo()));
      List<List<OrderListVo>> pageDataList = new ArrayList(1);
      List<OrderListVo> pageList = new ArrayList(pageSize);

      for(int i = 0; i < listAll.size(); ++i) {
         OrderListVo orderListVo = (OrderListVo)listAll.get(i);
         if (orderListVo.getOrderFlagCd().equals("02")) {
            if (pageList.size() >= 0) {
               if (orderListSearchVo.getOrderType().equals(orderListVo.getOrderType())) {
                  pageList.add(orderListVo);
               }

               if (pageList.size() >= 1) {
                  this.addBlankRow(pageList, pageSize, blankStr);
                  pageDataList.add(pageList);
                  pageList = new ArrayList(pageSize);
               }
               continue;
            }

            pageList.add(orderListVo);
         } else if (!orderListVo.getOrderFlagCd().equals("03") && !orderListVo.getOrderFlagCd().equals("04")) {
            pageList.add(orderListVo);
         } else if (pageList.size() > 0) {
            this.addBlankRow(pageList, pageSize, blankStr);
            pageDataList.add(pageList);
            pageList = new ArrayList(pageSize);
            pageList.add(orderListVo);
            continue;
         }

         if (pageSize == pageList.size()) {
            List<OrderListVo> subList = (List)pageList.stream().filter((t) -> t.getOrderGroupNo().equals(orderListVo.getOrderGroupNo())).collect(Collectors.toList());
            pageDataList.add(pageList);
            pageList = new ArrayList(pageSize);
            int orderGroupSortNumber = Integer.valueOf(orderListVo.getOrderGroupSortNumber());
            List<OrderListVo> temp = (List)listMap.get(orderListVo.getOrderGroupNo());
            if (orderListVo.getOrderClassCode().equals("01") && orderGroupSortNumber < temp.size()) {
               pageList.addAll(subList);
            }
         }
      }

      if (pageList.size() > 0 && pageList.size() < pageSize) {
         pageDataList.add(pageList);
      }

      OrderListInfoVo res = new OrderListInfoVo();
      res.setListAll(listAll);
      res.setPageDataList(pageDataList);
      return res;
   }

   private void addBlankRow(List list, int pageSize, String blankStr) {
      int listSize = list.size();

      for(int i = 0; i < pageSize - listSize; ++i) {
         OrderListVo temp = new OrderListVo();
         temp.setCpName(blankStr);
         list.add(temp);
      }

   }

   private OrderListInfoVo operationOrderList(String orderListType, OrderListSearchVo orderListSearchVo, Map groupOrderMap, List list, int pageSize, String blankStr) throws Exception {
      OrderListInfoVo res = null;
      switch (orderListType) {
         case "3":
            res = this.operationDecoctionOrderList(orderListSearchVo, groupOrderMap, list, pageSize, blankStr);
            break;
         default:
            res = this.operationLongOrderList(orderListSearchVo, groupOrderMap, list, pageSize, blankStr);
      }

      return res;
   }

   public List queryPrintData(OrderListPrintVo orderListPrintVo, int pageSize, String blankStr, String allFlag) throws Exception {
      OrderListInfoVo orderListInfoVoTotal = null;
      Map<String, List<OrderListVo>> groupOrderMap = null;
      switch (orderListPrintVo.getOrderListType()) {
         case "3":
            orderListInfoVoTotal = this.queryDecoctionTotal(orderListPrintVo.getOrderListSearchVo(), pageSize, blankStr);
            groupOrderMap = orderListInfoVoTotal.getHerbalMap();
            break;
         default:
            orderListInfoVoTotal = this.getPageTotal(orderListPrintVo.getOrderListSearchVo(), pageSize, blankStr, allFlag);
            groupOrderMap = (Map)orderListInfoVoTotal.getListAll().stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo() + "" + t.getReOrganizationNo()));
      }

      List<List<OrderListVo>> pageDataList = orderListInfoVoTotal.getPageDataList();
      List<OrderListInfoVo> orderListInfoVoList = new ArrayList(pageDataList.size());
      List<Integer> printTypes1 = Arrays.asList(4, 6);
      if (printTypes1.contains(orderListPrintVo.getPrintType())) {
         switch (orderListPrintVo.getPrintType()) {
            case 4:
               List<OrderListVo> tempList = (List)pageDataList.get(orderListPrintVo.getPrintPageNum() - 1);
               OrderListInfoVo orderListInfoVo = this.operationOrderList(orderListPrintVo.getOrderListType(), orderListPrintVo.getOrderListSearchVo(), groupOrderMap, tempList, pageSize, blankStr);
               orderListInfoVo.setPageNum(orderListPrintVo.getPrintPageNum());
               orderListInfoVoList.add(orderListInfoVo);
               break;
            case 6:
               List<OrderListVo> tempList6 = (List)pageDataList.get(orderListPrintVo.getPrintPageNum2() - 1);
               OrderListInfoVo orderListInfoVo6 = this.operationOrderList(orderListPrintVo.getOrderListType(), orderListPrintVo.getOrderListSearchVo(), groupOrderMap, tempList6, pageSize, blankStr);
               List<OrderListVo> newList = this.getPrintType6List(orderListInfoVo6.getList());
               orderListInfoVo6.setList(newList);
               orderListInfoVo6.setPageNum(orderListPrintVo.getPrintPageNum2());
               orderListInfoVoList.add(orderListInfoVo6);
         }
      }

      List<Integer> printTypes2 = Arrays.asList(1, 2, 3, 5);
      if (printTypes2.contains(orderListPrintVo.getPrintType())) {
         int endPageNum = orderListPrintVo.getPrintEndPageNum() == null ? pageDataList.size() : orderListPrintVo.getPrintEndPageNum();
         int endLineNum = orderListPrintVo.getPrintEndLineNum() == null ? pageSize : orderListPrintVo.getPrintEndLineNum();

         for(int i = 0; i < pageDataList.size(); ++i) {
            List<OrderListVo> tempList = (List)pageDataList.get(i);
            OrderListInfoVo orderListInfoVo = this.operationOrderList(orderListPrintVo.getOrderListType(), orderListPrintVo.getOrderListSearchVo(), groupOrderMap, tempList, pageSize, blankStr);
            tempList = orderListInfoVo.getList();
            orderListInfoVo.setPageNum(i + 1);
            switch (orderListPrintVo.getPrintType()) {
               case 1:
                  orderListInfoVoList.add(orderListInfoVo);
                  break;
               case 2:
                  if ((i + 1) % 2 != 0) {
                     orderListInfoVoList.add(orderListInfoVo);
                  }
                  break;
               case 3:
                  if ((i + 1) % 2 == 0) {
                     orderListInfoVoList.add(orderListInfoVo);
                  }
               case 4:
               default:
                  break;
               case 5:
                  if (orderListPrintVo.getPrintStartPageNum() == orderListPrintVo.getPrintPageNum()) {
                     if (i == orderListPrintVo.getPrintStartPageNum() - 1) {
                        List newList = this.getPrintType5List(tempList, orderListPrintVo.getPrintStartLineNum(), endLineNum);
                        orderListInfoVo.setList(newList);
                     }

                     orderListInfoVoList.add(orderListInfoVo);
                  } else if (i >= orderListPrintVo.getPrintStartPageNum() - 1 && i <= endPageNum - 1) {
                     if (i == orderListPrintVo.getPrintStartPageNum() - 1) {
                        List newList = this.getPrintType5List(tempList, orderListPrintVo.getPrintStartLineNum(), pageSize);
                        orderListInfoVo.setList(newList);
                     } else if (i == endPageNum - 1) {
                        List newList = this.getPrintType5List(tempList, 1, endLineNum);
                        orderListInfoVo.setList(newList);
                     }

                     orderListInfoVoList.add(orderListInfoVo);
                  }
            }
         }
      }

      if (CollectionUtils.isNotEmpty(orderListInfoVoList) && orderListInfoVoList.size() > 0) {
         if ("1".equals(orderListPrintVo.getOrderListType())) {
            int index = 0;

            for(OrderListInfoVo orderListInfoVo : orderListInfoVoList) {
               int pageNum = orderListInfoVo.getPageNum();
               ++index;
               List<OrderListVo> orderListVoList = orderListInfoVo.getList();
               if (CollectionUtils.isNotEmpty(orderListVoList) && orderListVoList.size() > 0) {
                  int index2 = 0;

                  for(OrderListVo orderListVo : orderListVoList) {
                     ++index2;
                     orderListVo.setPageNum(pageNum);
                     BigDecimal orderActualUsage = orderListVo.getOrderActualUsage() != null ? orderListVo.getOrderActualUsage() : new BigDecimal(BigInteger.ZERO);
                     String orderActualUsageS = orderActualUsage.compareTo(BigDecimal.ZERO) > 0 ? String.valueOf(orderActualUsage) : "";
                     String cpName1 = orderListVo.getCpName() != null ? orderListVo.getCpName() : (" " + orderActualUsageS + orderListVo.getUsageUnit() != null ? orderListVo.getUsageUnit() : "");
                     orderListVo.setCpName1(cpName1 != null ? cpName1 : "");
                     BigDecimal orderDose = orderListVo.getOrderDose() != null ? orderListVo.getOrderDose() : BigDecimal.ZERO;
                     String orderDoseS = orderDose.compareTo(BigDecimal.ZERO) > 0 ? String.valueOf(orderDose) : "";
                     String unit = StringUtils.isNotEmpty(orderListVo.getUnit()) ? orderListVo.getUnit() : "";
                     String cpName2 = orderDoseS + unit;
                     orderListVo.setCpName2(cpName2 != null ? cpName2 : "");
                     if (orderListVo.getOrderGroupNo() != null) {
                        orderListVo.setOrderGroupNo(index + "-" + index2);
                     } else {
                        if (StringUtils.isNotBlank(orderListVo.getSkinTestResults())) {
                           String skinTestResults = StringUtils.isNotBlank(orderListVo.getSkinTestResults()) ? orderListVo.getSkinTestResults() : "";
                           orderListVo.setCpName(orderListVo.getCpName() + skinTestResults);
                        }

                        String itemOrderFreqName = StringUtils.isNotEmpty(orderListVo.getItemOrderFreqName()) ? orderListVo.getItemOrderFreqName() : "";
                        if ("01".equals(orderListVo.getOrderClassCode())) {
                           orderListVo.setCpName(orderListVo.getCpName() + " " + orderListVo.getOrderActualUsage() + orderListVo.getUsageUnit());
                           String drippingSpeed = StringUtils.isNotEmpty(orderListVo.getDrippingSpeed()) ? orderListVo.getDrippingSpeed() : "";
                           String itemOrderUsageWayName = StringUtils.isNotEmpty(orderListVo.getItemOrderUsageWayName()) ? orderListVo.getItemOrderUsageWayName() : "";
                           orderListVo.setCpName2(itemOrderUsageWayName + " " + drippingSpeed + " " + itemOrderFreqName);
                        } else {
                           orderDose = orderListVo.getOrderDose() != null ? orderListVo.getOrderDose() : BigDecimal.ZERO;
                           orderDoseS = orderDose.compareTo(BigDecimal.ZERO) > 0 ? String.valueOf(orderDose) : "";
                           unit = StringUtils.isNotEmpty(orderListVo.getUnit()) ? orderListVo.getUnit() : "";
                           cpName2 = itemOrderFreqName + orderDoseS + unit;
                           orderListVo.setCpName2(cpName2 != null ? cpName2 : "");
                        }
                     }
                  }

                  orderListInfoVo.setList(orderListVoList);
               }
            }
         } else if ("2".equals(orderListPrintVo.getOrderListType())) {
            String tempDrugDoseViewFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("004703");

            for(OrderListInfoVo orderListInfoVo : orderListInfoVoList) {
               int pageNum = orderListInfoVo.getPageNum();
               List<OrderListVo> orderListVoList = orderListInfoVo.getList();
               if (CollectionUtils.isNotEmpty(orderListVoList) && orderListVoList.size() > 0) {
                  for(OrderListVo orderListVo : orderListVoList) {
                     orderListVo.setPageNum(pageNum);
                     BigDecimal orderActualUsage = orderListVo.getOrderActualUsage() != null ? orderListVo.getOrderActualUsage() : new BigDecimal(BigInteger.ZERO);
                     String orderActualUsageS = orderActualUsage.compareTo(BigDecimal.ZERO) > 0 ? String.valueOf(orderActualUsage) : "";
                     String cpName1 = orderListVo.getCpName() != null ? orderListVo.getCpName() : (" " + orderActualUsageS + orderListVo.getUsageUnit() != null ? orderListVo.getUsageUnit() : "");
                     orderListVo.setCpName1(cpName1 != null ? cpName1 : "");
                     BigDecimal orderDose = orderListVo.getOrderDose() != null ? orderListVo.getOrderDose() : BigDecimal.ZERO;
                     String orderDoseS = orderDose.compareTo(BigDecimal.ZERO) > 0 ? String.valueOf(orderDose) : "";
                     String unit = StringUtils.isNotEmpty(orderListVo.getUnit()) ? orderListVo.getUnit() : "";
                     String cpName2 = orderDoseS + unit;
                     orderListVo.setCpName2(cpName2 != null ? cpName2 : "");
                     if (orderListVo.getOrderGroupNo() != null) {
                        String skinTestResults = StringUtils.isNotBlank(orderListVo.getSkinTestResults()) ? orderListVo.getSkinTestResults() : "";
                        orderListVo.setCpName(orderListVo.getCpName() + skinTestResults);
                     }

                     if ("01".equals(orderListVo.getOrderClassCode())) {
                        orderListVo.setCpName2(orderListVo.getOrderActualUsage() + orderListVo.getUsageUnit());
                        if ("1".equals(orderListVo.getMasterOrder())) {
                           String itemOrderUsageWayName = StringUtils.isNotBlank(orderListVo.getItemOrderUsageWayName()) ? orderListVo.getItemOrderUsageWayName() : "";
                           String drippingSpeed = StringUtils.isNotBlank(orderListVo.getDrippingSpeed()) ? orderListVo.getDrippingSpeed() : "";
                           orderListVo.setCpName4(itemOrderUsageWayName + drippingSpeed);
                           orderListVo.setCpName5(StringUtils.isNotBlank(orderListVo.getItemOrderFreqName()) ? orderListVo.getItemOrderFreqName() : "");
                           if (!"1".equals(orderListVo.getOutHavaDrugFlag()) && "1".equals(tempDrugDoseViewFlag) || "1".equals(orderListVo.getOutHavaDrugFlag())) {
                              orderListVo.setCpName5(orderListVo.getCpName5() + orderDoseS + unit);
                           }
                        } else {
                           orderListVo.setCpName4("");
                           orderListVo.setCpName5(orderDoseS + unit);
                        }
                     } else {
                        orderListVo.setCpName2("");
                        orderListVo.setGroupStr("");
                        orderListVo.setCpName4(orderDoseS + unit);
                        orderListVo.setCpName5(StringUtils.isNotBlank(orderListVo.getItemOrderFreqName()) ? orderListVo.getItemOrderFreqName() : "");
                     }
                  }

                  orderListInfoVo.setList(orderListVoList);
               }
            }
         } else if ("3".equals(orderListPrintVo.getOrderListType())) {
            for(OrderListInfoVo orderListInfoVo : orderListInfoVoList) {
               int pageNum = orderListInfoVo.getPageNum();
               List<OrderListVo> orderListVoList = orderListInfoVo.getList();
               List<Map<String, Object>> orderListMapList = new ArrayList();
               if (CollectionUtils.isNotEmpty(orderListVoList) && orderListVoList.size() > 0) {
                  for(OrderListVo orderListVo : orderListVoList) {
                     orderListVo.setPageNum(pageNum);
                     Map<String, Object> propertiesMap = new HashMap();
                     if (CollectionUtils.isNotEmpty(orderListVo.getHerbalList()) && orderListVo.getHerbalList().size() > 0) {
                        List<OrderListVo> herbalList = orderListVo.getHerbalList();
                        String cpName0 = "";
                        String cpName1 = "";
                        String cpName2 = "";

                        for(int i = 0; i < 3; ++i) {
                           OrderListVo orderHerListVo = (OrderListVo)herbalList.get(i);
                           BigDecimal orderActualUsage = orderHerListVo.getOrderActualUsage() != null ? orderHerListVo.getOrderActualUsage() : new BigDecimal(BigInteger.ZERO);
                           String orderActualUsageS = orderActualUsage.compareTo(BigDecimal.ZERO) > 0 ? String.valueOf(orderActualUsage) : "";
                           String cpName = orderHerListVo.getCpName() != null ? orderHerListVo.getCpName() : "";
                           String usageUnit = orderHerListVo.getUsageUnit() != null ? orderHerListVo.getUsageUnit() : "";
                           String cpName9 = cpName + orderActualUsageS + usageUnit;
                           if (orderHerListVo != null && i == 0) {
                              cpName0 = cpName9;
                           } else if (orderHerListVo != null && i == 1) {
                              cpName1 = cpName9;
                           } else if (orderHerListVo != null && i == 2) {
                              cpName2 = cpName9;
                           }
                        }

                        orderListVo.setCpName0(cpName0);
                        orderListVo.setCpName1(cpName1);
                        orderListVo.setCpName2(cpName2);
                     } else if (StringUtils.isNotBlank(orderListVo.getCpNo()) && !CollectionUtils.isNotEmpty(orderListVo.getHerbalList())) {
                        orderListVo.setCpName0("共" + orderListVo.getHerbalDose() + "剂");
                        String itemOrderUsageWayName = StringUtils.isNotBlank(orderListVo.getItemOrderUsageWayName()) ? orderListVo.getItemOrderUsageWayName() : "";
                        String itemOrderFreqName = StringUtils.isNotBlank(orderListVo.getItemOrderFreqName()) ? orderListVo.getItemOrderFreqName() : "";
                        if (itemOrderFreqName == null && itemOrderUsageWayName == null) {
                           orderListVo.setCpName1("");
                        } else {
                           orderListVo.setCpName1(itemOrderUsageWayName + " " + itemOrderFreqName);
                        }

                        orderListVo.setCpName2(StringUtils.isNotBlank(orderListVo.getOederDesc()) ? orderListVo.getOederDesc() : "");
                     } else {
                        orderListVo.setCpName0(StringUtils.isNotBlank(orderListVo.getCpName()) ? orderListVo.getCpName() : "");
                        orderListVo.setCpName1("");
                        orderListVo.setCpName2("");
                     }

                     Map<String, Object> orderMap = ObjectUtils.objectToMap(orderListVo);
                     ObjectUtils.removeNullValue(orderMap);
                     propertiesMap.forEach((k, v) -> orderMap.merge(k, v, (v1, v2) -> v2));
                     orderListMapList.add(orderMap);
                  }
               }

               orderListInfoVo.setList(orderListVoList);
               orderListInfoVo.setListMap(orderListMapList);
            }
         }
      }

      return orderListInfoVoList;
   }

   public List getPrintType5List(List list, Integer startLineNum, Integer endLineNum) {
      List<OrderListVo> resList = new ArrayList(list.size());

      for(int i = 0; i < list.size(); ++i) {
         if (i >= startLineNum - 1 && i <= endLineNum - 1) {
            resList.add(list.get(i));
         } else {
            resList.add(new OrderListVo());
         }
      }

      return resList;
   }

   public List getPrintType6List(List list) {
      List<OrderListVo> resList = new ArrayList(list.size());

      for(int i = 0; i < list.size(); ++i) {
         OrderListVo orderListVo = (OrderListVo)list.get(i);
         if (orderListVo.getPrintDate() != null) {
            OrderListVo temp = new OrderListVo();
            temp.setOrderStopDate(orderListVo.getOrderStopDate());
            temp.setOrderStopTime(orderListVo.getOrderStopTime());
            temp.setOrderStopDoc(orderListVo.getOrderStopDoc());
            temp.setOrderStopDocName(orderListVo.getOrderStopDocName());
            temp.setOrderStopDocSignImg(orderListVo.getOrderStopDocSignImg());
            temp.setOrderStopNurse(orderListVo.getOrderStopNurse());
            temp.setOrderStopNurseName(orderListVo.getOrderStopNurseName());
            temp.setOrderStopNurseSignImg(orderListVo.getOrderStopNurseSignImg());
            resList.add(temp);
         } else {
            resList.add(orderListVo);
         }
      }

      return resList;
   }

   public AjaxResult getExcelOrderTotal(OrderListSearchVo orderListSearchVo, HttpServletResponse httpResponse, String allFlag) throws Exception {
      orderListSearchVo.setPplorderGroupNo((Integer)null);
      orderListSearchVo.setPageSize((Integer)null);
      List<OrderListVo> listAll = null;
      if (StringUtils.isNotBlank(allFlag) && allFlag.equals("1")) {
         listAll = this.tdPaOrderListMapper.selectOrderLongAllListPage(orderListSearchVo);
      } else {
         listAll = this.tdPaOrderListMapper.selectOrderLongListPage(orderListSearchVo);
      }

      List<OrderListVo> reOrganizationList = (List)listAll.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderFlagCd()) && t.getOrderFlagCd().equals("04")).collect(Collectors.toList());
      listAll = this.getReOrganizationListAll(listAll, reOrganizationList);
      Map<String, List<OrderListVo>> groupOrderMap = (Map)listAll.stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo() + "" + t.getReOrganizationNo()));
      OrderListInfoVo orderListInfoVo = this.operationLongOrderList(orderListSearchVo, groupOrderMap, listAll, 0, (String)null);
      String fileName = "";
      Workbook wb = new SXSSFWorkbook(500);
      if (orderListSearchVo.getOrderType().equals("1")) {
         ExcelUtils.setOrderLongExcel(wb, "长期医嘱单", orderListInfoVo, httpResponse);
         fileName = orderListInfoVo.getPatientInfo().getInpNo() + "/" + orderListInfoVo.getPatientInfo().getPatientName() + "-长期医嘱单.xlsx";
      } else {
         ExcelUtils.setOrderTempExcel(wb, "临时医嘱单", orderListInfoVo, httpResponse);
         fileName = orderListInfoVo.getPatientInfo().getInpNo() + "/" + orderListInfoVo.getPatientInfo().getPatientName() + "-临时医嘱单.xlsx";
      }

      httpResponse.setHeader("Content-disposition", "attachment;filename=" + fileName);
      OutputStream stream = new FileOutputStream(ExcelUtils.getAbsoluteFile(fileName));
      if (null != wb && null != stream) {
         wb.write(stream);
         stream.close();
      }

      return AjaxResult.success(fileName);
   }

   public AjaxResult getDecoctionExcelTotal(OrderListSearchVo orderListSearchVo, HttpServletResponse httpResponse) throws Exception {
      OrderListInfoVo allList = this.queryDecoctionAllTotal(orderListSearchVo);
      OrderListInfoVo result = this.operationDecoctionOrderList(orderListSearchVo, allList.getHerbalMap(), allList.getListAll(), 0, (String)null);
      Workbook wb = new SXSSFWorkbook(500);
      ExcelUtils.setDecoctionExcel(wb, "汤剂医嘱单", result, httpResponse);
      String fileName = result.getPatientInfo().getInpNo() + "/" + result.getPatientInfo().getPatientName() + "-汤剂医嘱单.xlsx";
      httpResponse.setHeader("Content-disposition", "attachment;filename=" + fileName);
      OutputStream stream = new FileOutputStream(ExcelUtils.getAbsoluteFile(fileName));
      if (null != wb && null != stream) {
         wb.write(stream);
         stream.close();
      }

      return AjaxResult.success(fileName);
   }

   public OderPerformResultVo selectOderPerform(OderPerformDetailReq req) throws Exception {
      OderPerformResultVo resultVo = new OderPerformResultVo();
      String herbalFlag = req.getHerbalFlag();
      String orderType = req.getOrderType();
      String orderNo = req.getOrderNo();
      List<TdPaOrderItem> orderItems = this.tdPaOrderItemMapper.selectByOrderNoList(Arrays.asList(orderNo));
      if (orderItems != null && orderItems.size() > 0) {
         TdPaOrderItem orderItem = (TdPaOrderItem)orderItems.get(0);
         String orderClassCode = orderItem.getOrderClassCode();
         String doctorInstructions = null;
         if (orderItem.getOrderClassCode().equals("01") && orderItem.getHerbalFlag().equals("0")) {
            TdPaOrderDetail orderDetail = this.orderDetailService.selectOrderDetail(orderNo);
            if (orderDetail != null) {
               doctorInstructions = orderDetail.getDoctorInstructions();
            }
         } else {
            doctorInstructions = orderItem.getOederDesc();
         }

         List<OrderStatusProcessDetail> processDetails = this.queryOrderProcess(orderItem);
         resultVo.setMainStatusList(processDetails);
         List<OderPerformDetailVo> performDetailVo = new ArrayList(1);
         List<InpatientTodayOrderPerformDTO> performDTOList = this.tdPaOrderPerformMapper.selectPerformListByOrderNo(orderNo);
         if (performDTOList != null && performDTOList.size() > 0) {
            Map<String, List<InpatientTodayOrderPerformDTO>> performListNoMap = (Map)performDTOList.stream().collect(Collectors.groupingBy(InpatientTodayOrderPerformDTO::getPerformListNo));
            Map<String, List<InpatientTodayOrderPerformDTO>> listMap = (Map)performDTOList.stream().collect(Collectors.groupingBy(InpatientTodayOrderPerformDTO::getOrderExecuteTimeStr));
            Set<String> performListNo = performListNoMap.keySet();
            List<OrderExecutionExpenseDetail> expenseDetailList = this.orderExecutionRecordMapper.selectExpenseDetailListByPerformListNo(performListNo, orderNo);
            Map<String, List<OrderExecutionExpenseDetail>> returnDetailMap = new HashMap();
            if (orderClassCode.equals("02") || orderClassCode.equals("03")) {
               Map<String, String> relationshipMap = new HashMap();

               for(OrderExecutionExpenseDetail detail : expenseDetailList) {
                  String performNo = detail.getPerformListNo();
                  String billsNo = detail.getBillsNo();
                  relationshipMap.put(billsNo, performNo);
               }

               List<String> billsNoList = (List)expenseDetailList.stream().map(OrderExecutionExpenseDetail::getBillsNo).collect(Collectors.toList());
               List<OrderExecutionExpenseDetail> returnDetailList = this.orderExecutionRecordMapper.selectReturnDetail(billsNoList, orderNo);
               if (returnDetailList != null && returnDetailList.size() > 0) {
                  Map<String, List<OrderExecutionExpenseDetail>> billsNoOldList = (Map)returnDetailList.stream().collect(Collectors.groupingBy(OrderExecutionExpenseDetail::getBillsNoOld));

                  for(String billNoOld : billsNoOldList.keySet()) {
                     String performList = (String)relationshipMap.get(billNoOld);
                     List<OrderExecutionExpenseDetail> detailList = (List)billsNoOldList.get(billNoOld);
                     if (StringUtils.isNotEmpty(performList)) {
                        if (returnDetailMap.containsKey(performList)) {
                           List<OrderExecutionExpenseDetail> details = (List)returnDetailMap.get(performList);
                           details.addAll(detailList);
                           returnDetailMap.put(performList, details);
                        } else {
                           returnDetailMap.put(performList, detailList);
                        }
                     }
                  }
               }
            }

            Map<String, List<OrderExecutionExpenseDetail>> expenseDetailListMap = expenseDetailList == null ? null : (Map)expenseDetailList.stream().collect(Collectors.groupingBy(OrderExecutionExpenseDetail::getPerformListNo));
            List<OrderTakeDrugDTO> tackDrugList = this.orderExecutionRecordMapper.selectTakeDrugListByPerformListNo(performListNo, orderNo);
            Map<String, List<ExpenseDetail>> returnFeeMap = null;
            if (tackDrugList != null && tackDrugList.size() > 0) {
               Map<String, List<OrderTakeDrugDTO>> typeMap = (Map)tackDrugList.stream().collect(Collectors.groupingBy(OrderTakeDrugDTO::getType));

               for(String type : typeMap.keySet()) {
                  if (type.equals("1")) {
                     List<String> billsNoList = this.inpatientSuspendOrderMapper.selectReturnDrugBillNoListByPerformListNo(performListNo);
                     List<ExpenseDetail> returnFeeList = billsNoList.isEmpty() ? null : this.expenseDetailMapper.selectReturnedFeeList(billsNoList);
                     returnFeeMap = returnFeeList == null ? null : (Map)returnFeeList.stream().collect(Collectors.groupingBy(ExpenseDetail::getPerformListNo));
                  }
               }
            }

            Map<String, List<OrderTakeDrugDTO>> takeDrugListMap = tackDrugList == null ? null : (Map)tackDrugList.stream().collect(Collectors.groupingBy(OrderTakeDrugDTO::getPerformListNo));

            for(String key : listMap.keySet()) {
               List<InpatientTodayOrderPerformDTO> orderPerformDTOS = (List)listMap.get(key);

               for(InpatientTodayOrderPerformDTO dto : orderPerformDTOS) {
                  dto.setDoctorInstructions(doctorInstructions);
                  String usageUnit = dto.getUsageUnit();
                  BigDecimal orderActualUsage = dto.getOrderActualUsage();
                  if (StringUtils.isNotEmpty(usageUnit) && orderActualUsage != null) {
                     dto.setOrderDose(orderActualUsage.toString() + usageUnit);
                  }
               }

               OderPerformDetailVo detailVo = new OderPerformDetailVo();
               List<OrderExecutionExpenseDetail> expenseDetailListDetail = new ArrayList(1);
               List<OrderTakeDrugDTO> tackDrugDetailList = new ArrayList(1);
               Map<String, List<InpatientTodayOrderPerformDTO>> performListMap = (Map)orderPerformDTOS.stream().collect(Collectors.groupingBy(InpatientTodayOrderPerformDTO::getPerformListNo));

               for(String performNo : performListMap.keySet()) {
                  if (expenseDetailListMap != null) {
                     List<OrderExecutionExpenseDetail> expenseDetails = (List)expenseDetailListMap.get(performNo);
                     if (expenseDetails != null && !expenseDetails.isEmpty()) {
                        expenseDetailListDetail.addAll(expenseDetails);
                     }
                  }

                  if (takeDrugListMap != null) {
                     List<OrderTakeDrugDTO> takeDrugDTOS = (List)takeDrugListMap.get(performNo);
                     takeDrugDTOS = this.genTakeDrugListDetail(takeDrugDTOS, performNo, returnFeeMap);
                     if (takeDrugDTOS != null && !takeDrugDTOS.isEmpty()) {
                        tackDrugDetailList.addAll(takeDrugDTOS);
                     }
                  }

                  if ((orderClassCode.equals("02") || orderClassCode.equals("03")) && !returnDetailMap.isEmpty()) {
                     List<OrderExecutionExpenseDetail> expenseDetailListReturn = (List)returnDetailMap.get(performNo);
                     if (expenseDetailListReturn != null && expenseDetailListReturn.size() > 0) {
                        expenseDetailListDetail.addAll(expenseDetailListReturn);
                     }
                  }
               }

               detailVo.setTackDrugDetailList(tackDrugDetailList);
               detailVo.setExpenseDetailList(expenseDetailListDetail);
               if (herbalFlag.equals("0") && orderType.equals("1") || herbalFlag.equals("1") && orderType.equals("1")) {
                  DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                  Date date = fmt.parse(key);
                  detailVo.setPerformDate(date);
                  List<TdPaOrderOperationLog> logPerformList = this.operationLogService.selectLogByOrderNo(orderNo, date);
                  List<OrderStatusProcessDetail> childProcess = this.assemblyChildProcessData(logPerformList, orderPerformDTOS);
                  detailVo.setChildProcess(childProcess);
                  Boolean flag = Boolean.FALSE;

                  for(InpatientTodayOrderPerformDTO dto : orderPerformDTOS) {
                     String status = dto.getPerformListStatus();
                     if (!status.equals(String.valueOf(1))) {
                        flag = Boolean.TRUE;
                     }
                  }

                  detailVo.setFlag(flag);
               }

               detailVo.setOrderPerformList(orderPerformDTOS);
               performDetailVo.add(detailVo);
            }
         }

         if (performDetailVo.size() > 0 && (herbalFlag.equals("0") && orderType.equals("1") || herbalFlag.equals("1") && orderType.equals("1"))) {
            performDetailVo = (List)performDetailVo.stream().sorted(Comparator.comparing(OderPerformDetailVo::getPerformDate)).collect(Collectors.toList());
         }

         resultVo.setPerformDetailVo(performDetailVo);
      }

      return resultVo;
   }

   public List queryOrderProcess(TdPaOrderItem orderItem) {
      List<OrderStatusProcessDetail> logList = new ArrayList(1);
      if (orderItem == null) {
         return logList;
      } else {
         String orderType = orderItem.getOrderType();
         String orderClassCode = orderItem.getOrderClassCode();
         if (orderType.equals("1")) {
            logList = this.assemblyProcessData(orderItem);
         } else if (orderType.equals("2")) {
            switch (orderClassCode) {
               case "02":
               case "03":
                  logList = this.getInspectionProcessData(orderItem);
                  break;
               default:
                  logList = this.getOtherProcessData(orderItem);
            }
         }

         return logList;
      }
   }

   public List selectPatOrderDeptList(String patientId) throws Exception {
      return StringUtils.isNotBlank(patientId) ? this.tdPaOrderListMapper.selectPatOrderDeptList(patientId) : null;
   }

   private List getOtherProcessData(TdPaOrderItem orderItem) {
      List<OrderStatusProcessDetail> list = new ArrayList();
      OrderStatusProcessDetail detail1 = new OrderStatusProcessDetail();
      detail1.setOperationName("开嘱");
      if (orderItem.getOrderStartTime() != null) {
         detail1.setFlg(Boolean.TRUE);
         detail1.setOperationTime(orderItem.getOrderStartTime());
         detail1.setOperatorName(orderItem.getOrderStartDocName());
         detail1.setOperatorNo(orderItem.getOrderStartDoc());
      }

      list.add(detail1);
      OrderStatusProcessDetail detail2 = new OrderStatusProcessDetail();
      detail2.setOperationName("审核");
      if (orderItem.getOrderAuditTime() != null) {
         detail2.setFlg(Boolean.TRUE);
         detail2.setOperationTime(orderItem.getOrderAuditTime());
         detail2.setOperatorName(orderItem.getOrderAuditDocName());
         detail2.setOperatorNo(orderItem.getOrderAuditDoc());
      }

      list.add(detail2);
      OrderStatusProcessDetail detail3 = new OrderStatusProcessDetail();
      detail3.setOperationName("执行");
      if (orderItem.getOrderExecuteTime() != null) {
         detail3.setFlg(Boolean.TRUE);
         detail3.setOperationTime(orderItem.getOrderExecuteTime());
         detail3.setOperatorName(orderItem.getOrderExecuteDocName());
         detail3.setOperatorNo(orderItem.getOrderExecuteDoc());
      }

      list.add(detail3);
      Boolean flag = Boolean.FALSE;
      OrderStatusProcessDetail detail6 = new OrderStatusProcessDetail();
      detail6.setOperationName("取消");
      if (orderItem.getOrderCancelTime() != null) {
         detail6.setFlg(Boolean.TRUE);
         detail6.setOperationTime(orderItem.getOrderCancelTime());
         detail6.setOperatorName(orderItem.getOrderCancelDocName());
         detail6.setOperatorNo(orderItem.getOrderCancelDoc());
         list.add(detail6);
         flag = Boolean.TRUE;
      }

      OrderStatusProcessDetail detail7 = new OrderStatusProcessDetail();
      detail7.setOperationName("取消确认");
      if (orderItem.getOrderCancelAuditTime() != null) {
         detail7.setFlg(Boolean.TRUE);
         detail7.setOperationTime(orderItem.getOrderCancelAuditTime());
         detail7.setOperatorName(orderItem.getOrderCancelAuditDocName());
         detail7.setOperatorNo(orderItem.getOrderCancelAuditDoc());
      }

      if (flag) {
         list.add(detail7);
      }

      return list;
   }

   private List getInspectionProcessData(TdPaOrderItem orderItem) {
      String orderNo = orderItem.getOrderNo();
      String orderClassCode = orderItem.getOrderClassCode();
      List<OrderStatusProcessDetail> list = new ArrayList();
      OrderStatusProcessDetail detail1 = new OrderStatusProcessDetail();
      detail1.setOperationName("开嘱");
      if (orderItem.getOrderStartTime() != null) {
         detail1.setFlg(Boolean.TRUE);
         detail1.setOperationTime(orderItem.getOrderStartTime());
         detail1.setOperatorName(orderItem.getOrderStartDocName());
         detail1.setOperatorNo(orderItem.getOrderStartDoc());
      }

      list.add(detail1);
      OrderStatusProcessDetail detail2 = new OrderStatusProcessDetail();
      detail2.setOperationName("审核");
      if (orderItem.getOrderAuditTime() != null) {
         detail2.setFlg(Boolean.TRUE);
         detail2.setOperationTime(orderItem.getOrderAuditTime());
         detail2.setOperatorName(orderItem.getOrderAuditDocName());
         detail2.setOperatorNo(orderItem.getOrderAuditDoc());
      }

      list.add(detail2);
      switch (orderClassCode) {
         case "02":
            List<OrderApplyFormDetailVo> detailList = this.tdPaOrderListMapper.selectOrderClassCode2Detail(orderNo);
            OrderStatusProcessDetail detail3 = new OrderStatusProcessDetail();
            detail3.setOperationName("医技登记");
            if (detailList != null && !detailList.isEmpty()) {
               OrderApplyFormDetailVo formDetailVo = (OrderApplyFormDetailVo)detailList.get(0);
               if (formDetailVo != null && formDetailVo.getExamTime() != null) {
                  detail3.setFlg(Boolean.TRUE);
                  detail3.setOperationTime(formDetailVo.getExamTime());
                  detail3.setOperatorName(formDetailVo.getExamDocName());
                  detail3.setOperatorNo(formDetailVo.getExamDocCd());
               }
            }

            list.add(detail3);
            OrderStatusProcessDetail detail4 = new OrderStatusProcessDetail();
            detail4.setOperationName("报告已出");
            if (detailList != null && !detailList.isEmpty()) {
               OrderApplyFormDetailVo formDetailVo = (OrderApplyFormDetailVo)detailList.get(0);
               if (formDetailVo != null && formDetailVo.getExamRepDate() != null) {
                  detail4.setFlg(Boolean.TRUE);
                  detail4.setOperationTime(formDetailVo.getExamRepDate());
                  detail4.setOperatorName(formDetailVo.getRepDocName());
                  detail4.setOperatorNo(formDetailVo.getRepDocCd());
               }
            }

            list.add(detail4);
            OrderStatusProcessDetail detail5 = new OrderStatusProcessDetail();
            detail5.setOperationName("报告发布");
            if (detailList != null && !detailList.isEmpty()) {
               OrderApplyFormDetailVo formDetailVo = (OrderApplyFormDetailVo)detailList.get(0);
               if (formDetailVo != null && formDetailVo.getCreDate() != null) {
                  detail5.setFlg(Boolean.TRUE);
                  detail5.setOperationTime(formDetailVo.getCreDate());
                  detail5.setOperatorName(formDetailVo.getCrePerName());
                  detail5.setOperatorNo(formDetailVo.getCrePerCode());
               }
            }

            list.add(detail5);
            break;
         case "03":
            List<OrderApplyFormTestVo> testList = this.tdPaOrderListMapper.selectOrderTestDetail(orderNo);
            OrderStatusProcessDetail detail6 = new OrderStatusProcessDetail();
            detail6.setOperationName("条码打印");
            if (testList != null && !testList.isEmpty()) {
               OrderApplyFormTestVo testVo = (OrderApplyFormTestVo)testList.get(0);
               if (testVo != null && testVo.getPrintsj() != null) {
                  detail6.setFlg(Boolean.TRUE);
                  detail6.setOperationTime(testVo.getPrintsj());
                  detail6.setOperatorName(testVo.getPrintOperName());
                  detail6.setOperatorNo(testVo.getPrintOper());
               }
            }

            list.add(detail6);
            OrderStatusProcessDetail detail7 = new OrderStatusProcessDetail();
            detail7.setOperationName("标本采集");
            if (testList != null && !testList.isEmpty()) {
               OrderApplyFormTestVo testVo = (OrderApplyFormTestVo)testList.get(0);
               if (testVo != null && testVo.getSmsj() != null) {
                  detail7.setFlg(Boolean.TRUE);
                  detail7.setOperationTime(testVo.getSmsj());
                  detail7.setOperatorName(testVo.getSmOperName());
                  detail7.setOperatorNo(testVo.getSmOper());
               }
            }

            list.add(detail7);
            OrderStatusProcessDetail detail8 = new OrderStatusProcessDetail();
            detail8.setOperationName("标本送出");
            if (testList != null && !testList.isEmpty()) {
               OrderApplyFormTestVo testVo = (OrderApplyFormTestVo)testList.get(0);
               if (testVo != null && testVo.getScsj() != null) {
                  detail8.setFlg(Boolean.TRUE);
                  detail8.setOperationTime(testVo.getScsj());
                  detail8.setOperatorName(testVo.getScOperName());
                  detail8.setOperatorNo(testVo.getScOper());
               }
            }

            list.add(detail8);
            OrderStatusProcessDetail detail9 = new OrderStatusProcessDetail();
            detail9.setOperationName("标本上机");
            if (testList != null && !testList.isEmpty()) {
               OrderApplyFormTestVo testVo = (OrderApplyFormTestVo)testList.get(0);
               if (testVo != null && testVo.getMachineConfirmTime() != null) {
                  detail9.setFlg(Boolean.TRUE);
                  detail9.setOperationTime(testVo.getMachineConfirmTime());
               }
            }

            list.add(detail9);
            OrderStatusProcessDetail detail10 = new OrderStatusProcessDetail();
            detail10.setOperationName("报告发布");
            if (testList != null && !testList.isEmpty()) {
               OrderApplyFormTestVo testVo = (OrderApplyFormTestVo)testList.get(0);
               if (testVo != null && testVo.getIssTime() != null) {
                  detail10.setFlg(Boolean.TRUE);
                  detail10.setOperationTime(testVo.getIssTime());
                  detail10.setOperatorName(testVo.getIssName());
                  detail10.setOperatorNo(testVo.getIssCd());
               }
            }

            list.add(detail10);
      }

      return list;
   }

   private List assemblyChildProcessData(List logPerformList, List orderPerformDTOS) {
      List<OrderStatusProcessDetail> list = new ArrayList();
      Map<String, List<InpatientTodayOrderPerformDTO>> performListMap = (Map)orderPerformDTOS.stream().collect(Collectors.groupingBy(InpatientTodayOrderPerformDTO::getPerformListStatus));
      Map<Integer, List<TdPaOrderOperationLog>> listStatusMap = (Map)logPerformList.stream().collect(Collectors.groupingBy(TdPaOrderOperationLog::getOperationType));
      OrderStatusProcessDetail detail1 = new OrderStatusProcessDetail();
      detail1.setOperationName("转抄");
      List<TdPaOrderOperationLog> logList = (List)listStatusMap.get(2);
      if (logList != null) {
         List<TdPaOrderOperationLog> operationLogs = (List)logList.stream().sorted(Comparator.comparing(TdPaOrderOperationLog::getOperationTime)).collect(Collectors.toList());
         TdPaOrderOperationLog log = (TdPaOrderOperationLog)operationLogs.get(0);
         detail1.setFlg(Boolean.TRUE);
         detail1.setOperationTime(log.getOperationTime());
         detail1.setOperatorName(log.getOperatorName());
         detail1.setOperatorNo(log.getOperatorNo());
      }

      list.add(detail1);
      OrderStatusProcessDetail detail2 = new OrderStatusProcessDetail();
      detail2.setOperationName("执行");
      List<InpatientTodayOrderPerformDTO> performDTOList = (List)performListMap.get(String.valueOf(1));
      if (performDTOList != null) {
         List<InpatientTodayOrderPerformDTO> operationLogs = (List)performDTOList.stream().sorted(Comparator.comparing(InpatientTodayOrderPerformDTO::getOrderExecuteTime)).collect(Collectors.toList());
         detail2.setFlg(Boolean.TRUE);
         InpatientTodayOrderPerformDTO performDTO = (InpatientTodayOrderPerformDTO)operationLogs.get(0);
         detail2.setOperationTime(performDTO.getOrderExecuteTime());
         detail2.setOperatorName(performDTO.getOrderExecuteDoName());
         detail2.setOperatorNo(performDTO.getOrderExecuteDoCode());
      }

      list.add(detail2);
      Boolean flag = Boolean.FALSE;
      OrderStatusProcessDetail detail3 = new OrderStatusProcessDetail();
      detail3.setOperationName("停止");
      List<TdPaOrderOperationLog> logList4 = (List)listStatusMap.get(4);
      if (logList4 != null) {
         List<TdPaOrderOperationLog> operationLogs = (List)logList4.stream().sorted(Comparator.comparing(TdPaOrderOperationLog::getOperationTime)).collect(Collectors.toList());
         TdPaOrderOperationLog log = (TdPaOrderOperationLog)operationLogs.get(0);
         detail3.setFlg(Boolean.TRUE);
         detail3.setOperationTime(log.getOperationTime());
         detail3.setOperatorName(log.getOperatorName());
         detail3.setOperatorNo(log.getOperatorNo());
         list.add(detail3);
         flag = Boolean.TRUE;
      }

      OrderStatusProcessDetail detail4 = new OrderStatusProcessDetail();
      detail4.setOperationName("停止确认");
      List<TdPaOrderOperationLog> logList5 = (List)listStatusMap.get(5);
      if (logList5 != null) {
         List<TdPaOrderOperationLog> operationLogs = (List)logList5.stream().sorted(Comparator.comparing(TdPaOrderOperationLog::getOperationTime)).collect(Collectors.toList());
         TdPaOrderOperationLog log = (TdPaOrderOperationLog)operationLogs.get(0);
         detail4.setFlg(Boolean.TRUE);
         detail4.setOperationTime(log.getOperationTime());
         detail4.setOperatorName(log.getOperatorName());
         detail4.setOperatorNo(log.getOperatorNo());
      }

      if (flag) {
         list.add(detail4);
      }

      return list;
   }

   private List assemblyProcessData(TdPaOrderItem tdPaOrderItem) {
      List<OrderStatusProcessDetail> list = new ArrayList();
      OrderStatusProcessDetail detail1 = new OrderStatusProcessDetail();
      detail1.setOperationName("开嘱");
      if (tdPaOrderItem.getOrderStartTime() != null) {
         detail1.setFlg(Boolean.TRUE);
         detail1.setOperationTime(tdPaOrderItem.getOrderStartTime());
         detail1.setOperatorName(tdPaOrderItem.getOrderStartDocName());
         detail1.setOperatorNo(tdPaOrderItem.getOrderStartDoc());
      }

      list.add(detail1);
      OrderStatusProcessDetail detail2 = new OrderStatusProcessDetail();
      detail2.setOperationName("审核");
      if (tdPaOrderItem.getOrderAuditTime() != null) {
         detail2.setFlg(Boolean.TRUE);
         detail2.setOperationTime(tdPaOrderItem.getOrderAuditTime());
         detail2.setOperatorName(tdPaOrderItem.getOrderAuditDocName());
         detail2.setOperatorNo(tdPaOrderItem.getOrderAuditDoc());
      }

      list.add(detail2);
      OrderStatusProcessDetail detail3 = new OrderStatusProcessDetail();
      detail3.setOperationName("首次执行");
      if (tdPaOrderItem.getOrderExecuteTime() != null) {
         detail3.setFlg(Boolean.TRUE);
         detail3.setOperationTime(tdPaOrderItem.getOrderExecuteTime());
         detail3.setOperatorName(tdPaOrderItem.getOrderExecuteDocName());
         detail3.setOperatorNo(tdPaOrderItem.getOrderExecuteDoc());
      }

      list.add(detail3);
      OrderStatusProcessDetail detail4 = new OrderStatusProcessDetail();
      detail4.setOperationName("停止");
      if (tdPaOrderItem.getOrderStopTime() != null) {
         detail4.setFlg(Boolean.TRUE);
         detail4.setOperationTime(tdPaOrderItem.getOrderStopTime());
         detail4.setOperatorName(tdPaOrderItem.getOrderStopDocName());
         detail4.setOperatorNo(tdPaOrderItem.getOrderStopDoc());
      }

      list.add(detail4);
      OrderStatusProcessDetail detail5 = new OrderStatusProcessDetail();
      detail5.setOperationName("停止确认");
      if (tdPaOrderItem.getOrderStopAuditTime() != null) {
         detail5.setFlg(Boolean.TRUE);
         detail5.setOperationTime(tdPaOrderItem.getOrderStopAuditTime());
         detail5.setOperatorName(tdPaOrderItem.getOrderStopAuditDocName());
         detail5.setOperatorNo(tdPaOrderItem.getOrderStopAuditDoc());
      }

      list.add(detail5);
      Boolean flag = Boolean.FALSE;
      OrderStatusProcessDetail detail6 = new OrderStatusProcessDetail();
      detail6.setOperationName("取消");
      if (tdPaOrderItem.getOrderCancelTime() != null) {
         detail6.setFlg(Boolean.TRUE);
         detail6.setOperationTime(tdPaOrderItem.getOrderCancelTime());
         detail6.setOperatorName(tdPaOrderItem.getOrderCancelDocName());
         detail6.setOperatorNo(tdPaOrderItem.getOrderCancelDoc());
         list.add(detail6);
         flag = Boolean.TRUE;
      }

      OrderStatusProcessDetail detail7 = new OrderStatusProcessDetail();
      detail7.setOperationName("取消确认");
      if (tdPaOrderItem.getOrderCancelAuditTime() != null) {
         detail7.setFlg(Boolean.TRUE);
         detail7.setOperationTime(tdPaOrderItem.getOrderCancelAuditTime());
         detail7.setOperatorName(tdPaOrderItem.getOrderCancelAuditDocName());
         detail7.setOperatorNo(tdPaOrderItem.getOrderCancelAuditDoc());
      }

      if (flag) {
         list.add(detail7);
      }

      return list;
   }

   private List genTakeDrugListDetail(List takeDrugDTOS, String performNo, Map returnFeeMap) {
      if (takeDrugDTOS != null && takeDrugDTOS.size() > 0) {
         for(OrderTakeDrugDTO dto : takeDrugDTOS) {
            BigDecimal returnedDose = BigDecimal.ZERO;
            if (returnFeeMap != null && returnFeeMap.size() > 0) {
               List<ExpenseDetail> expenseDetails = (List)returnFeeMap.get(performNo);
               if (expenseDetails != null && expenseDetails.size() > 0) {
                  Map<String, List<ExpenseDetail>> listMap = (Map)expenseDetails.stream().collect(Collectors.groupingBy((t) -> t.getChargeCode() + t.getDrugStockNo()));
                  if (listMap != null) {
                     List<ExpenseDetail> tempList = (List)listMap.get(dto.getChargeNo() + dto.getDrugStockNo());
                     if (tempList != null && !tempList.isEmpty()) {
                        for(ExpenseDetail temp : tempList) {
                           returnedDose = returnedDose.add(temp.getDose());
                        }
                     }
                  }
               }
            }

            BigDecimal orderDose = dto.getOrderDose();
            if (returnedDose.add(orderDose).compareTo(BigDecimal.ZERO) == 0) {
               dto.setTakeDrugStatus("5");
            }

            dto.setReturnDose(returnedDose);
            BigDecimal add = orderDose.add(returnedDose);
            BigDecimal total = add.multiply(dto.getOrderPrice()).setScale(4).stripTrailingZeros();
            dto.setTotal(total.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : total);
         }
      }

      return takeDrugDTOS;
   }

   public OrderListInfoVo queryDecoctionAllTotal(OrderListSearchVo orderListSearchVo) throws Exception {
      orderListSearchVo.setPplorderGroupNo((Integer)null);
      orderListSearchVo.setPageSize((Integer)null);
      List<OrderListVo> listAll = this.tdPaOrderListMapper.selectOrderDecoctionListPage(orderListSearchVo);
      Map<String, List<OrderListVo>> listMap = (Map)listAll.stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo()));
      List<List<OrderListVo>> pageDataList = new ArrayList(1);
      List<OrderListVo> herbalListAll = new ArrayList(1);
      int orderNum = 3;
      List<String> orderGroupNoList = new ArrayList(listMap.keySet());

      for(String orderGroupNo : (List)orderGroupNoList.stream().sorted().collect(Collectors.toList())) {
         List<OrderListVo> herbalListGroup = new ArrayList(1);
         List<OrderListVo> orderGroupList = (List)listMap.get(orderGroupNo);
         List<OrderListVo> herbalList = null;
         OrderListVo mainHerbal = null;

         for(int i = 0; i < orderGroupList.size(); ++i) {
            OrderListVo temp = (OrderListVo)orderGroupList.get(i);
            temp.setHerbalList((List)null);
            if (i % orderNum == 0) {
               if (mainHerbal != null) {
                  herbalListAll.add(mainHerbal);
                  herbalListGroup.add(mainHerbal);
               }

               mainHerbal = new OrderListVo();
               BeanUtils.copyProperties(temp, mainHerbal);
               List var21 = new ArrayList(orderNum);
               mainHerbal.setHerbalList(var21);
            }

            mainHerbal.getHerbalList().add(temp);
         }

         int herbalListSize = herbalListGroup.size() * orderNum;
         int groupListSize = orderGroupList.size();
         if (herbalListSize != groupListSize) {
            int subHerbalListSize = mainHerbal.getHerbalList().size();

            for(int i = 0; i < orderNum - subHerbalListSize; ++i) {
               mainHerbal.getHerbalList().add(new OrderListVo());
            }

            herbalListAll.add(mainHerbal);
         }

         OrderListVo orderListVo = (OrderListVo)orderGroupList.get(0);
         orderListVo.setHerbalList((List)null);
         orderListVo.setMasterOrder("2");
         herbalListAll.add(orderListVo);
      }

      Map<String, List<OrderListVo>> herbalMap = (Map)herbalListAll.stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo() + "" + t.getReOrganizationNo()));
      OrderListInfoVo res = new OrderListInfoVo();
      res.setListAll(herbalListAll);
      res.setPageDataList(pageDataList);
      res.setHerbalMap(herbalMap);
      return res;
   }
}
