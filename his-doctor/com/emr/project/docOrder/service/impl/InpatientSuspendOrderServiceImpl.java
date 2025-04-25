package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.CommonUtils;
import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.borrowing.domain.vo.SysDictDataVO;
import com.emr.project.docOrder.domain.AllergyRecord;
import com.emr.project.docOrder.domain.ApplyFormItem;
import com.emr.project.docOrder.domain.InpatientOrderPerform;
import com.emr.project.docOrder.domain.InpatientOrderPerformDetail;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderOperationLog;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.docOrder.domain.req.ProcessingOrdersReq;
import com.emr.project.docOrder.domain.req.SuppPrintOrderDataListReq;
import com.emr.project.docOrder.domain.req.SuppPrintOrderDataReq;
import com.emr.project.docOrder.domain.req.SuspendOrderListReq;
import com.emr.project.docOrder.domain.req.TodayOrderOperationReq;
import com.emr.project.docOrder.domain.resp.OrderExecutionExpenseDetail;
import com.emr.project.docOrder.domain.vo.AllergyRecordDTO;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnResultVo;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnVo;
import com.emr.project.docOrder.domain.vo.InpatientOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.InpatientTodayOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.OrderDoHandleDrugDoseVo;
import com.emr.project.docOrder.domain.vo.OrderTakeDrugDTO;
import com.emr.project.docOrder.mapper.AllergyRecordMapper;
import com.emr.project.docOrder.mapper.ApplyFormItemMapper;
import com.emr.project.docOrder.mapper.InpatientSuspendOrderMapper;
import com.emr.project.docOrder.mapper.OrderExecutionRecordMapper;
import com.emr.project.docOrder.mapper.TdPaOrderItemMapper;
import com.emr.project.docOrder.mapper.TdPaOrderMapper;
import com.emr.project.docOrder.mapper.TdPaOrderOperationLogMapper;
import com.emr.project.docOrder.service.ApplyFormService;
import com.emr.project.docOrder.service.IInpatientSuspendOrderService;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.docOrder.service.InpatientOrderPerformDetailService;
import com.emr.project.docOrder.service.PharmacyStockService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.operation.domain.ApplyForm;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.domain.CumulativeCost;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.domain.PatFee;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugReturn;
import com.emr.project.operation.domain.Tfsqb;
import com.emr.project.operation.domain.dto.DepartAccountDTO;
import com.emr.project.operation.domain.dto.TakeDrugFeeDTO;
import com.emr.project.operation.domain.resp.UnTakeDrugResp;
import com.emr.project.operation.mapper.ApplyFormMapper;
import com.emr.project.operation.mapper.BaseinfomationMapper;
import com.emr.project.operation.mapper.ExpenseDetailMapper;
import com.emr.project.operation.mapper.MedicalinformationMapper;
import com.emr.project.operation.mapper.PatientsReturnDrugMapper;
import com.emr.project.operation.mapper.TakeDrugReturnMapper;
import com.emr.project.operation.mapper.TfsqbMapper;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.operation.service.ICumulativeCostService;
import com.emr.project.operation.service.IDepartAccountService;
import com.emr.project.operation.service.TakeDrugListService;
import com.emr.project.operation.service.TakeDrugReturnLogService;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.mapper.PatFeeMapper;
import com.emr.project.pat.service.IPatFeeService;
import com.emr.project.system.domain.ConfigureHospital;
import com.emr.project.system.domain.DictStandard;
import com.emr.project.system.domain.PatAtt;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.DictStandardMapper;
import com.emr.project.system.mapper.PatAttMapper;
import com.emr.project.system.mapper.SysDictDataMapper;
import com.emr.project.system.service.ConfigureHospitalService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InpatientSuspendOrderServiceImpl implements IInpatientSuspendOrderService {
   private final Logger log = LoggerFactory.getLogger(InpatientSuspendOrderServiceImpl.class);
   @Autowired
   private InpatientSuspendOrderMapper inpatientSuspendOrderMapper;
   @Autowired
   private ICommonOperationService commonService;
   @Autowired
   private TakeDrugListService takeDrugListService;
   @Autowired
   private IPatFeeService patFeeService;
   @Autowired
   private PharmacyStockService pharmacyStockService;
   @Autowired
   private ConfigureHospitalService configureHospitalService;
   @Autowired
   private ApplyFormService applyFormService;
   @Autowired
   private ApplyFormItemMapper applyFormItemMapper;
   @Autowired
   private InpatientOrderPerformDetailService inpatientOrderPerformDetailService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ITdPaOrderOperationLogService orderOperationLogService;
   @Autowired
   private OrderExecutionRecordMapper orderExecutionRecordMapper;
   @Autowired
   private ExpenseDetailMapper expenseDetailMapper;
   @Autowired
   private IDepartAccountService departAccountService;
   @Autowired
   private ITdPaOrderDetailService orderDetailService;
   @Autowired
   private MedicalinformationMapper medicalinformationMapper;
   @Autowired
   private AllergyRecordMapper allergyRecordMapper;
   @Autowired
   private HisProcService hisProcService;
   @Autowired
   private BaseinfomationMapper baseinfomationMapper;
   @Autowired
   private PatAttMapper patAttMapper;
   @Autowired
   private DictStandardMapper dictStandardMapper;
   @Autowired
   private TdPaOrderOperationLogMapper tdPaOrderOperationLogMapper;
   @Autowired
   private TakeDrugReturnMapper takeDrugReturnMapper;
   @Autowired
   private TakeDrugReturnLogService takeDrugReturnLogService;
   @Autowired
   private ApplyFormMapper applyFormMapper;
   @Autowired
   private SysDictDataMapper sysDictDataMapper;
   @Autowired
   private PatFeeMapper patFeeMapper;
   @Autowired
   private ICumulativeCostService cumulativeCostService;
   @Autowired
   private ExpenseDetailService expenseDetailService;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private TfsqbMapper tfsqbMapper;
   @Autowired
   private TdPaOrderItemMapper tdPaOrderItemMapper;
   @Autowired
   private TdPaOrderMapper tdPaOrderMapper;
   @Autowired
   private PatientsReturnDrugMapper patientsReturnDrugMapper;
   @Autowired
   private IDrugStockService drugStockService;

   public List selectSuspendOrderList(SuspendOrderListReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      req.setDepCode(user.getDept().getDeptCode());
      List<InpatientOrderPerformDTO> performDTOList = this.inpatientSuspendOrderMapper.selectInpatientSuspendOrder(req);
      Map<String, List<InpatientOrderPerformDTO>> inpatientOrderGroupMap = (Map)performDTOList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo() + t.getOrderGroupNo()));

      for(String key : inpatientOrderGroupMap.keySet()) {
         List<InpatientOrderPerformDTO> orderDTOS = (List)inpatientOrderGroupMap.get(key);
         if (orderDTOS != null && orderDTOS.size() > 0 && orderDTOS.size() > 1) {
            for(int i = 0; i < orderDTOS.size(); ++i) {
               if (i == 0) {
                  ((InpatientOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┓");
               } else if (i == orderDTOS.size() - 1) {
                  ((InpatientOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┛");
               } else {
                  ((InpatientOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┃");
               }
            }
         }
      }

      return performDTOList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updatePerformStart(List inpatientOrderPerformList) {
      String performListNo = ((InpatientOrderPerformDTO)inpatientOrderPerformList.get(0)).getPerformListNo();
      int performListSortNumber = ((InpatientOrderPerformDTO)inpatientOrderPerformList.get(0)).getPerformListSortNumber();
      InpatientOrderPerform perform = new InpatientOrderPerform();
      perform.setPerformListNo(performListNo);
      perform.setPerformListSortNumber(performListSortNumber);
      this.updatePerformStatus(perform, "2", (String)null, (String)null);
   }

   public void updatePerformStatus(InpatientOrderPerform inPerform, String status, String operatorCode, String operatorNo) {
      inPerform.setPerformListStatus(status);
      if (inPerform.getPerformTime() == null) {
         Date dateDB = this.commonService.getDbDate();
         inPerform.setPerformTime(dateDB);
      }

      if (inPerform.getPerformNurseCode() == null) {
         inPerform.setPerformNurseCode(operatorCode);
      }

      if (inPerform.getPerformNurseNo() == null) {
         inPerform.setPerformNurseNo(operatorNo);
      }

      this.inpatientSuspendOrderMapper.updateByPrimaryKeySelective(inPerform);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public OrderDoHandleDrugDoseVo updatePerformStartUse(List inop, List printList, List drugAndClinList, SysUser user, String ip) throws Exception {
      OrderDoHandleDrugDoseVo vo = new OrderDoHandleDrugDoseVo();
      String msgSb = "";
      String performListNo = ((InpatientOrderPerformDTO)inop.get(0)).getPerformListNo();
      int performListSortNumber = ((InpatientOrderPerformDTO)inop.get(0)).getPerformListSortNumber();
      String operatorCode = user.getUserName();
      String operatorNo = user.getUserName();
      String operatorName = user.getNickName();
      boolean flagQf = false;
      boolean isHasStock = true;
      int status = 1;
      String orderClassCode = ((InpatientOrderPerformDTO)inop.get(0)).getOrderClassCode();
      boolean arrea = this.commonService.checkPatientArrears(((InpatientOrderPerformDTO)inop.get(0)).getAdmissionNo(), ((InpatientOrderPerformDTO)inop.get(0)).getHospitalizedCount() + "", this.getTotal(performListNo, performListSortNumber + ""));
      String arrearsFlag = user.getDept().getArrearsFlag();
      String staffCode = user.getUserName();
      String staffNo = user.getUserName();
      String staffName = user.getNickName();
      boolean isAccountFlag = false;
      switch (orderClassCode) {
         case "01":
            status = 0;
            if (arrea) {
               flagQf = true;
               List<TakeDrugList> takeDrugList = this.takeDrugListService.selectByPerformListNo(performListNo);
               vo = this.pharmacyStockService.isHasStock(takeDrugList, drugAndClinList, (InpatientOrderPerformDTO)inop.get(0));
               isHasStock = vo.getNoStockFlag().equals("1");
               if (!isHasStock) {
                  msgSb = msgSb + "库存不足";
                  status = 3;
                  this.takeDrugListService.changeStatusTakeDrugLists(takeDrugList, 3);
                  this.takeDrugListService.updateStatusTakeDrugLists(takeDrugList);
               } else {
                  this.takeDrugListService.changeStatusTakeDrugLists(takeDrugList, 0);
                  this.takeDrugListService.updateStatusTakeDrugLists(takeDrugList);
               }
            } else {
               status = 4;
               msgSb = msgSb + "欠费";
            }
            break;
         case "02":
            isAccountFlag = this.isAccountFlagByjcjy(arrearsFlag, arrea, "02");
            flagQf = arrea;
            if (!arrea) {
               status = 4;
               msgSb = msgSb + "欠费";
            }

            if (arrea) {
               String applyFormStatus = "30";
               String oldApplyFormStatus = "0";
               if (isAccountFlag) {
                  this.patFeeService.toFee(inop, "0207", (String)null, (List)null, (DepartAccountDTO)null);
                  applyFormStatus = "31";
                  oldApplyFormStatus = "3";
               }

               ApplyForm applyForm = this.applyFormService.searchApplyForm(((InpatientOrderPerformDTO)inop.get(0)).getOrderNo(), ((InpatientOrderPerformDTO)inop.get(0)).getOrderSortNumber());
               this.applyFormService.updateApplyFormStatus(applyForm.getApplyFormNo(), applyFormStatus, this.commonService.getDbDate(), staffCode, staffNo, staffName);
               List<ApplyFormItem> applyFormItems = this.applyFormItemMapper.selectByApplyFormNo(applyForm.getApplyFormNo());
               if (!applyFormItems.isEmpty()) {
                  for(ApplyFormItem applyFormItem : applyFormItems) {
                     applyFormItem.setApplyFormStatus(applyFormStatus);
                  }

                  this.applyFormItemMapper.updateStatusList(applyFormItems);
               }
            }
            break;
         case "03":
            isAccountFlag = this.isAccountFlagByjcjy(arrearsFlag, arrea, "03");
            flagQf = arrea;
            if (!arrea) {
               status = 4;
               msgSb = msgSb + "欠费";
            }

            if (arrea) {
               String applyFormStatus = "30";
               String oldApplyFormStatus = "0";
               if (isAccountFlag) {
                  this.patFeeService.toFee(inop, "0207", (String)null, (List)null, (DepartAccountDTO)null);
                  applyFormStatus = "31";
                  oldApplyFormStatus = "3";
               }

               ApplyForm applyForm = this.applyFormService.searchApplyForm(((InpatientOrderPerformDTO)inop.get(0)).getOrderNo(), ((InpatientOrderPerformDTO)inop.get(0)).getOrderSortNumber());
               this.applyFormService.updateApplyFormStatus(applyForm.getApplyFormNo(), applyFormStatus, this.commonService.getDbDate(), staffCode, staffNo, staffName);
               List<ApplyFormItem> applyFormItems = this.applyFormItemMapper.selectByApplyFormNo(applyForm.getApplyFormNo());
               if (!applyFormItems.isEmpty()) {
                  for(ApplyFormItem applyFormItem : applyFormItems) {
                     applyFormItem.setApplyFormStatus(applyFormStatus);
                  }

                  this.applyFormItemMapper.updateStatusList(applyFormItems);
               }
            }
            break;
         default:
            if (arrea) {
               flagQf = true;
               if (!((InpatientOrderPerformDTO)inop.get(0)).getOrderType().equals("1") || !((InpatientOrderPerformDTO)inop.get(0)).getOrderItemType().equals("3")) {
                  this.patFeeService.toFee(inop, "0207", (String)null, (List)null, (DepartAccountDTO)null);
               }
            } else {
               status = 4;
               msgSb = msgSb + "欠费";
            }
      }

      for(InpatientOrderPerformDTO inpatientOrderPerformDTO : inop) {
         InpatientOrderPerform perform = new InpatientOrderPerform();
         perform.setPerformListNo(inpatientOrderPerformDTO.getPerformListNo());
         perform.setPerformListSortNumber(inpatientOrderPerformDTO.getPerformListSortNumber());
         this.updatePerformStatus(perform, status + "", operatorCode, operatorNo);
      }

      if (flagQf && isHasStock && status != 0) {
         Set hs = new HashSet();
         Date nowDate = this.commonService.getDbDate();

         for(InpatientOrderPerformDTO t : inop) {
            if (!hs.contains(t.getOrderNo() + t.getOrderSortNumber())) {
               hs.add(t.getOrderNo() + t.getOrderSortNumber());
               if (!"02".equals(t.getOrderClassCode()) && !"03".equals(t.getOrderClassCode())) {
                  if ("05".equals(t.getOrderClassCode())) {
                     TdPaOrderStatus handleStatus = new TdPaOrderStatus(nowDate, t.getPatientDepCode(), t.getPatientDepCode(), t.getCaseNo(), t.getAdmissionNo(), t.getHospitalizedCount(), t.getOrderNo(), t.getOrderSortNumber(), t.getOrderGroupNo(), 3, "执行", (Integer)null, (String)null, 3, t.getOrderClassCode());
                     this.tdPaOrderStatusService.insertSelective(handleStatus);
                     int orderStatus = 3;
                     if ("2".equals(t.getOrderType())) {
                        orderStatus = 8;
                     }

                     this.tdPaOrderStatusService.updateItemTime(t.getOrderNo(), t.getOrderSortNumber(), t.getOrderGroupNo(), user.getUserName(), nowDate, orderStatus, true, (String)null);
                     this.orderOperationLogService.addInpatientOrderOperationLog(performListNo, performListSortNumber + "", "", 3, "执行", (String)null, operatorCode, operatorNo, operatorName);
                  }
               } else {
                  String orderStatusName = null;
                  int ls_status_new;
                  int orderStatus;
                  if ("1".equals(t.getOrderType())) {
                     int ls_status = 4;
                     ls_status_new = 5;
                     orderStatus = 3;
                     orderStatusName = "执行";
                  } else {
                     int ls_status = 3;
                     ls_status_new = 8;
                     orderStatus = 8;
                     orderStatusName = "已执行";
                  }

                  TdPaOrderStatus handleStatus = new TdPaOrderStatus(nowDate, t.getPatientDepCode(), t.getPatientDepCode(), t.getCaseNo(), t.getAdmissionNo(), t.getHospitalizedCount(), t.getOrderNo(), t.getOrderSortNumber(), t.getOrderGroupNo(), orderStatus, orderStatusName, (Integer)null, (String)null, 2, t.getOrderClassCode());
                  this.tdPaOrderStatusService.insertSelective(handleStatus);
                  this.tdPaOrderStatusService.updateItemTime(t.getOrderNo(), t.getOrderSortNumber(), t.getOrderGroupNo(), user.getUserName(), nowDate, ls_status_new, true, t.getOrderDoctorCode());
               }

               if (!user.getDept().getDeptCode().equals(t.getDetailPerformDepCode()) && !"01".equals(t.getOrderClassCode())) {
                  new HashMap();
                  Map<String, Object> param = new HashMap();
                  param.put("case_no", t.getCaseNo());
                  param.put("admission_no", t.getAdmissionNo());
                  param.put("hospitalized_count", t.getHospitalizedCount());
                  if (StringUtils.isNotBlank(t.getBabyAdmissionNo())) {
                     param.put("yebz", "2");
                  } else {
                     param.put("yebz", "1");
                  }

                  param.put("type", "0");
                  param.put("order_no", t.getOrderNo());
                  param.put("order_sort_number", t.getOrderSortNumber());
                  param.put("filing_date", this.commonService.getDbDate());
                  param.put("ward_no", user.getDept().getDeptCode());
                  param.put("operator", "");
                  List<Map<String, Object>> list = this.patFeeService.getPrintDate(param);
                  printList.addAll(list);
               }
            }
         }
      }

      vo.setErrorMsg(msgSb);
      if (vo != null && StringUtils.isNotBlank(vo.getNoStockFlag()) && vo.getNoStockFlag().equals("1") && CollectionUtils.isNotEmpty(vo.getDrugDoseVoList())) {
         List<DrugDoseVo> updateDoseList = vo.getDrugDoseVoList();
         this.drugStockService.updateDrugDoseByOrderDose(user, updateDoseList, "1", ip);
      }

      return vo;
   }

   public List selectTodayOrderOperation(List allList, Integer pageNum, Integer pageSize) {
      List<InpatientTodayOrderPerformDTO> pageList = new ArrayList(1);
      if (allList != null && allList.size() > 0) {
         int begin = 0;
         int end = 0;
         if (pageSize != 0) {
            begin = (pageNum - 1) * pageSize;
            end = pageNum * pageSize;
            if (end > allList.size()) {
               end = allList.size();
            }
         }

         if (end == 0) {
            pageList.addAll(allList);
         } else {
            pageList = allList.subList(begin, end);
         }

         Map<String, List<InpatientTodayOrderPerformDTO>> orderNoMap = (Map)pageList.stream().collect(Collectors.groupingBy(InpatientTodayOrderPerformDTO::getOrderNo));
         Set<String> orderNoSet = orderNoMap.keySet();
         if (orderNoSet.size() > 0) {
            List<TdPaOrderDetail> orderDetailList = this.inpatientSuspendOrderMapper.selectOrderDetailByOrderNoList(orderNoSet);
            if (orderDetailList != null && orderDetailList.size() > 0) {
               Map<String, List<TdPaOrderDetail>> collect = (Map)orderDetailList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo() + t.getOrderSortNumber() + t.getOrderGroupNo() + t.getOrderGroupSortNumber()));

               for(InpatientTodayOrderPerformDTO dto : pageList) {
                  String usageUnit = dto.getUsageUnit();
                  BigDecimal orderActualUsage = dto.getOrderActualUsage();
                  if (StringUtils.isNotEmpty(usageUnit) && orderActualUsage != null) {
                     dto.setOrderDose(orderActualUsage.toString() + usageUnit);
                  }

                  String orderNo = dto.getOrderNo();
                  String orderGroupSortNumber = dto.getOrderGroupSortNumber();
                  String orderSortNumber = dto.getOrderSortNumber();
                  String orderGroupNo = dto.getOrderGroupNo();
                  String key = orderNo + orderSortNumber + orderGroupNo + orderGroupSortNumber;
                  if (collect.containsKey(key)) {
                     List<TdPaOrderDetail> detailList = (List)collect.get(key);
                     if (detailList != null && detailList.size() > 0) {
                        TdPaOrderDetail tdPaOrderDetail = (TdPaOrderDetail)detailList.get(0);
                        dto.setSkinTestResults(tdPaOrderDetail.getSkinTestResults());
                        dto.setSkinTestResultsLot(tdPaOrderDetail.getSkinTestResultsLot());
                        dto.setSkinTestResultsTime(tdPaOrderDetail.getSkinTestResultsTime());
                        dto.setDoctorInstructions(tdPaOrderDetail.getDoctorInstructions());
                        dto.setMasterOrder(tdPaOrderDetail.getMasterOrder());
                        dto.setOrderItemFlag(tdPaOrderDetail.getOrderItemFlag());
                     }
                  }
               }
            }
         }

         Map<String, List<InpatientTodayOrderPerformDTO>> inpatientOrderGroupMap = (Map)pageList.stream().collect(Collectors.groupingBy((t) -> t.getPerformListNo() + t.getOrderNo() + t.getPerformListSortNumber()));

         for(String key : inpatientOrderGroupMap.keySet()) {
            List<InpatientTodayOrderPerformDTO> orderDTOS = (List)inpatientOrderGroupMap.get(key);
            if (orderDTOS != null && orderDTOS.size() > 0 && orderDTOS.size() > 1) {
               for(int i = 0; i < orderDTOS.size(); ++i) {
                  if (i == 0) {
                     ((InpatientTodayOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┓");
                  } else if (i == orderDTOS.size() - 1) {
                     ((InpatientTodayOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┛");
                  } else {
                     ((InpatientTodayOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┃");
                  }
               }
            }
         }
      }

      return pageList;
   }

   public Map selectTodayOrderListDetail(InpatientTodayOrderPerformDTO req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Map<String, Object> listMap = new HashMap();
      String admissionNo = req.getAdmissionNo();
      String orderNo = req.getOrderNo();
      String performListNo = req.getPerformListNo();
      TodayOrderOperationReq todayOrderOperationReq = new TodayOrderOperationReq();
      todayOrderOperationReq.setAdmissionNo(admissionNo);
      todayOrderOperationReq.setPerformListNo(performListNo);
      todayOrderOperationReq.setFlag(1);
      todayOrderOperationReq.setDeptCode(user.getDept().getDeptCode());
      List<InpatientTodayOrderPerformDTO> list = this.inpatientSuspendOrderMapper.selectTodayOrderOperation(todayOrderOperationReq);

      for(InpatientTodayOrderPerformDTO dto : list) {
         String usageUnit = dto.getUsageUnit();
         BigDecimal orderActualUsage = dto.getOrderActualUsage();
         if (StringUtils.isNotEmpty(usageUnit) && orderActualUsage != null) {
            dto.setOrderDose(orderActualUsage.toString() + usageUnit);
         }
      }

      Map<String, List<InpatientTodayOrderPerformDTO>> orderNoMap = (Map)list.stream().collect(Collectors.groupingBy(InpatientTodayOrderPerformDTO::getOrderNo));
      Set<String> orderNoSet = orderNoMap.keySet();
      if (orderNoSet.size() > 0) {
         List<TdPaOrderDetail> orderDetailList = this.inpatientSuspendOrderMapper.selectOrderDetailByOrderNoList(orderNoSet);
         if (orderDetailList != null && orderDetailList.size() > 0) {
            Map<String, List<TdPaOrderDetail>> collect = (Map)orderDetailList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo() + t.getOrderSortNumber() + t.getOrderGroupNo() + t.getOrderGroupSortNumber()));

            for(InpatientTodayOrderPerformDTO dto : list) {
               String dtoOrderNo = dto.getOrderNo();
               String orderGroupSortNumber = dto.getOrderGroupSortNumber();
               String orderSortNumber = dto.getOrderSortNumber();
               String orderGroupNo = dto.getOrderGroupNo();
               String key = dtoOrderNo + orderSortNumber + orderGroupNo + orderGroupSortNumber;
               if (collect.containsKey(key)) {
                  List<TdPaOrderDetail> detailList = (List)collect.get(key);
                  if (detailList != null && detailList.size() > 0) {
                     TdPaOrderDetail tdPaOrderDetail = (TdPaOrderDetail)detailList.get(0);
                     dto.setSkinTestResults(tdPaOrderDetail.getSkinTestResults());
                     dto.setSkinTestResultsLot(tdPaOrderDetail.getSkinTestResultsLot());
                     dto.setSkinTestResultsTime(tdPaOrderDetail.getSkinTestResultsTime());
                     dto.setDoctorInstructions(tdPaOrderDetail.getDoctorInstructions());
                     dto.setMasterOrder(tdPaOrderDetail.getMasterOrder());
                     dto.setOrderItemFlag(tdPaOrderDetail.getOrderItemFlag());
                  }
               }
            }
         }
      }

      Map<String, List<InpatientTodayOrderPerformDTO>> inpatientOrderGroupMap = (Map)list.stream().collect(Collectors.groupingBy((t) -> t.getPerformListNo() + t.getOrderNo() + t.getPerformListSortNumber()));

      for(String key : inpatientOrderGroupMap.keySet()) {
         List<InpatientTodayOrderPerformDTO> orderDTOS = (List)inpatientOrderGroupMap.get(key);
         if (orderDTOS != null && orderDTOS.size() > 0 && orderDTOS.size() > 1) {
            for(int i = 0; i < orderDTOS.size(); ++i) {
               if (i == 0) {
                  ((InpatientTodayOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┓");
               } else if (i == orderDTOS.size() - 1) {
                  ((InpatientTodayOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┛");
               } else {
                  ((InpatientTodayOrderPerformDTO)orderDTOS.get(i)).setOrderGroupStr("┃");
               }
            }
         }
      }

      listMap.put("orderPerformList", list);
      List<OrderExecutionExpenseDetail> expenseDetailList = this.orderExecutionRecordMapper.selectExpenseDetailList(admissionNo, orderNo, performListNo);
      listMap.put("expenseDetailList", expenseDetailList);
      List<OrderTakeDrugDTO> tackDrugList = this.inpatientSuspendOrderMapper.selectTakeDrugDetail(performListNo, orderNo, admissionNo);
      Map<String, List<ExpenseDetail>> returnFeeMap = null;
      Map<String, List<TakeDrugReturn>> takeDrugReturnMap = null;
      if (tackDrugList != null && tackDrugList.size() > 0) {
         Map<String, List<OrderTakeDrugDTO>> typeMap = (Map)tackDrugList.stream().collect(Collectors.groupingBy(OrderTakeDrugDTO::getType));

         for(String type : typeMap.keySet()) {
            if (!type.equals("0") && type.equals("1")) {
               List<String> billsNoList = this.inpatientSuspendOrderMapper.selectReturnDrugBillNoList(performListNo);
               List<ExpenseDetail> returnFeeList = billsNoList.isEmpty() ? null : this.expenseDetailMapper.selectReturnedFeeList(billsNoList);
               returnFeeMap = (Map<String, List<ExpenseDetail>>)(returnFeeList == null ? new HashMap() : (Map)returnFeeList.stream().collect(Collectors.groupingBy(ExpenseDetail::getChargeCode)));
               List<TakeDrugReturn> takeDrugReturnList = this.takeDrugReturnService.queryByBillsNoOldList(billsNoList);
               takeDrugReturnMap = (Map)takeDrugReturnList.stream().collect(Collectors.groupingBy(TakeDrugReturn::getChargeCode));
            }
         }
      }

      for(OrderTakeDrugDTO dto : tackDrugList) {
         BigDecimal returnedDose = new BigDecimal("0");
         BigDecimal applyedDose = new BigDecimal("0");
         if (returnFeeMap != null) {
            List<ExpenseDetail> tempList = (List)returnFeeMap.get(dto.getChargeNo());
            if (tempList != null && !tempList.isEmpty()) {
               for(ExpenseDetail temp : tempList) {
                  returnedDose = returnedDose.add(temp.getDose());
               }
            }
         }

         if (takeDrugReturnMap != null) {
            List<TakeDrugReturn> drugReturnList = (List)takeDrugReturnMap.get(dto.getChargeNo());
            if (drugReturnList != null && !drugReturnList.isEmpty()) {
               for(TakeDrugReturn temp : drugReturnList) {
                  applyedDose = applyedDose.add(temp.getDose());
               }
            }
         }

         BigDecimal orderDose = dto.getOrderDose();
         if (returnedDose.add(orderDose).compareTo(BigDecimal.ZERO) == 0) {
            dto.setTakeDrugStatus("5");
         } else if (applyedDose.compareTo(BigDecimal.ZERO) < 0) {
            dto.setTakeDrugStatus("4");
         }

         dto.setApplyDose(applyedDose);
         dto.setReturnDose(returnedDose);
         BigDecimal add = orderDose.add(returnedDose);
         dto.setTotal(add.multiply(dto.getOrderPrice()));
      }

      listMap.put("tackDrugDetailList", tackDrugList);
      return listMap;
   }

   public List getSuppPrintData(SuppPrintOrderDataListReq req) {
      List<Map<String, List<Map<String, Object>>>> mapList1 = new ArrayList();
      List<SuppPrintOrderDataReq> list = req.getList();
      SuppPrintOrderDataReq dataReq = (SuppPrintOrderDataReq)req.getList().get(0);
      List<String> performListNos = (List)list.stream().map(SuppPrintOrderDataReq::getPerformListNo).distinct().collect(Collectors.toList());
      if (performListNos.size() > 0) {
         Set<String> set = new HashSet();

         for(String key : performListNos) {
            set.add(key);
         }

         List<Map<String, Object>> mapList = new ArrayList();

         for(String prescription : set) {
            Map<String, Object> param = new HashMap();
            param.put("type", "2");
            param.put("order_no", prescription);
            param.put("admission_no", dataReq.getAdmissionNo());
            param.put("hospitalized_count", dataReq.getHospitalizedCount());
            List<Map<String, Object>> objectList = this.patFeeService.getPrintDate(param);
            mapList.addAll(objectList);
         }

         this.departAccountService.groupByExecutorDpt(mapList1, mapList, Boolean.TRUE);
      }

      return mapList1;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateSkinTestResults(AllergyRecordDTO req) throws Exception {
      String orderNo = req.getOrderNo();
      String orderSortNumber = req.getOrderSortNumber();
      this.orderDetailService.updaTeskinTestResults(orderNo, orderSortNumber, req.getOperatorDate(), req.getSkinTestResultsLot(), req.getSkinTestResults());
      Medicalinformation m = this.medicalinformationMapper.getMedicalinfo(req.getAdmissionNo());
      this.saveAllergyRecord(req, m);
      if (!"(-)".equals(req.getSkinTestResults())) {
         this.savePatAtt(req.getChargeName(), m);
      }

      this.saveDictStandard(req.getChargeNo(), req.getChargeName());
   }

   public Boolean selectPerformStatus(String performListNo, List sortNumberList, String type) {
      if (sortNumberList.contains("-1")) {
         sortNumberList = null;
      }

      List<InpatientOrderPerform> list = this.inpatientSuspendOrderMapper.selectPerformStatusList(performListNo, sortNumberList, type);
      return list != null && !list.isEmpty() ? Boolean.FALSE : Boolean.TRUE;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void revokeOrderPerform(ProcessingOrdersReq req) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String performListNo = req.getPerformListNo();
      List<String> performListSortNumberList = req.getPerformListSortNumberList();
      String admissionNo = req.getAdmissionNo();
      String orderClassCode = req.getOrderClassCode();
      String orderNo = req.getOrderNo();
      String orderGroupNo = req.getOrderGroupNo();
      String orderSortNumber = req.getOrderSortNumber();
      if (performListSortNumberList.contains("-1")) {
         performListSortNumberList = null;
      }

      if (!orderClassCode.equals("02") && !orderClassCode.equals("03") && !orderClassCode.equals("07")) {
         switch (orderClassCode) {
            case "01":
               List<ExpenseDetail> expenseDetailList = this.expenseDetailService.selectExpenseDetailListByPerformListNo(performListNo, performListSortNumberList);
               this.revokeOrderClass(expenseDetailList, req, user, performListNo, performListSortNumberList, admissionNo, orderClassCode, orderNo, orderGroupNo, orderSortNumber);
               break;
            case "05":
            case "99":
               List<ExpenseDetail> detailList = this.expenseDetailMapper.selectExpenseDetailByPerformList(performListNo, performListSortNumberList);
               this.revokeOrderClass(detailList, req, user, performListNo, performListSortNumberList, admissionNo, orderClassCode, orderNo, orderGroupNo, orderSortNumber);
               break;
            default:
               this.updateOrderStatusAndPerformStatus(req, user, performListNo, performListSortNumberList, admissionNo, orderClassCode, orderNo, orderGroupNo, orderSortNumber);
         }

      }
   }

   private void revokeOrderClass(List expenseDetailList, ProcessingOrdersReq req, SysUser user, String performListNo, List performListSortNumberList, String admissionNo, String orderClassCode, String orderNo, String orderGroupNo, String orderSortNumber) throws Exception {
      if (expenseDetailList != null && expenseDetailList.size() > 0) {
         Map<String, List<ExpenseDetail>> inpatientOrderGroupMap = (Map)expenseDetailList.stream().collect(Collectors.groupingBy(ExpenseDetail::getPerformListSortNumber));
         List<String> billNoList = (List)expenseDetailList.stream().map(ExpenseDetail::getBillsNo).distinct().collect(Collectors.toList());
         List<Tfsqb> tfsqbList = this.tfsqbMapper.selectTfsqListByBillNoList(billNoList);
         Map<String, List<Tfsqb>> tfBillNoList = new HashMap();
         if (!tfsqbList.isEmpty()) {
            tfBillNoList = (Map)tfsqbList.stream().collect(Collectors.groupingBy(Tfsqb::getBillsNoOld));
         }

         for(String key : inpatientOrderGroupMap.keySet()) {
            List<ExpenseDetail> details = (List)inpatientOrderGroupMap.get(key);
            List<ExpenseDetail> expenseDetails = new ArrayList();
            List<ExpenseDetail> returnExpenseDetails = new ArrayList();

            for(ExpenseDetail expenseDetail : details) {
               String billsNoOld = expenseDetail.getBillsNoOld();
               if (StringUtils.isNotEmpty(billsNoOld)) {
                  returnExpenseDetails.add(expenseDetail);
               } else {
                  expenseDetails.add(expenseDetail);
               }
            }

            Map<String, List<ExpenseDetail>> map = (Map)expenseDetails.stream().collect(Collectors.groupingBy(ExpenseDetail::getBillsNo));

            for(String tfsqd : tfBillNoList.keySet()) {
               List<Tfsqb> tfsqbs = (List)tfBillNoList.get(tfsqd);
               List<ExpenseDetail> detailList = (List)map.get(tfsqd);
               if (detailList != null && detailList.size() > 0) {
                  ExpenseDetail expenseDetail = (ExpenseDetail)detailList.get(0);
                  BigDecimal doseTotal = (BigDecimal)tfsqbs.stream().map(Tfsqb::getDose).reduce(BigDecimal.ZERO, BigDecimal::add);
                  BigDecimal totalTotal = (BigDecimal)tfsqbs.stream().map(Tfsqb::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                  BigDecimal dose = expenseDetail.getDose();
                  if (doseTotal.add(dose).compareTo(BigDecimal.ZERO) > 0) {
                     expenseDetail.setDose(doseTotal.add(dose));
                     expenseDetail.setTotal(totalTotal.add(expenseDetail.getTotal()));
                  } else {
                     map.remove(tfsqd);
                  }
               }
            }

            BigDecimal totalAmount = BigDecimal.ZERO;
            List<PatFee> patFeeList = new ArrayList();
            List<ExpenseDetail> list = new ArrayList();
            if (map != null) {
               for(String keyDetail : map.keySet()) {
                  List<ExpenseDetail> detailList = (List)map.get(keyDetail);
                  if (detailList != null && detailList.size() > 0) {
                     for(int i = 0; i < detailList.size(); ++i) {
                        String newPrescription = this.hisProcService.getDocumentOrBillNo(SecurityUtils.getLoginUser().getUser().getUserName(), 4);
                        ExpenseDetail expenseDetail = (ExpenseDetail)detailList.get(i);
                        String prescription = expenseDetail.getPrescription();
                        PatFee patFee = this.patFeeMapper.selectFeeByPrescription(prescription);
                        if (patFee != null) {
                           patFee.setFilingDate(new Date());
                           patFee.setPrescription(newPrescription);
                           patFeeList.add(patFee);
                        }

                        BigDecimal total = expenseDetail.getTotal();
                        BigDecimal dose = expenseDetail.getDose();
                        expenseDetail.setTotal(total.negate());
                        expenseDetail.setDose(dose.negate());
                        totalAmount = totalAmount.add(expenseDetail.getTotal());
                        expenseDetail.setBillsNoOld(expenseDetail.getBillsNo());
                        expenseDetail.setPrescription(newPrescription);
                        String billNo = newPrescription + CommonUtils.getTenNumberStr(i);
                        expenseDetail.setBillsNo(billNo);
                        expenseDetail.setFilingDate(new Date());
                        expenseDetail.setPerformListNo(performListNo);
                        list.add(expenseDetail);
                     }

                     if (list.size() > 0) {
                        this.expenseDetailMapper.insertBatch(list);
                        this.insertTfsqData(list, patFeeList);
                     }

                     if (patFeeList.size() > 0) {
                        this.patFeeMapper.insertBatch(patFeeList);
                     }

                     if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
                        CumulativeCost cumulativeCost = this.cumulativeCostService.queryByAdmissionNo(admissionNo);
                        BigDecimal cumulativeCostAmount = cumulativeCost.getCumulativeCost();
                        cumulativeCostAmount = cumulativeCostAmount == null ? new BigDecimal("0") : cumulativeCostAmount;
                        cumulativeCostAmount = cumulativeCostAmount.add(totalAmount).compareTo(BigDecimal.ZERO) > 0 ? cumulativeCostAmount.add(totalAmount).setScale(2, 4) : BigDecimal.ZERO;
                        CumulativeCost param = new CumulativeCost(admissionNo, cumulativeCostAmount);
                        this.cumulativeCostService.updateCumulativeCost(param);
                     }
                  }
               }
            }
         }
      }

      this.updateOrderStatusAndPerformStatus(req, user, performListNo, performListSortNumberList, admissionNo, orderClassCode, orderNo, orderGroupNo, orderSortNumber);
   }

   private void insertTfsqData(List list, List patFeeList) throws Exception {
      if (!list.isEmpty()) {
         String admissionNo = ((ExpenseDetail)list.get(0)).getAdmissionNo();
         Baseinfomation baseInfo = this.baseinfomationMapper.findBaseInfo(admissionNo);
         Map<String, List<PatFee>> prescriptionMap = (Map)patFeeList.stream().collect(Collectors.groupingBy(PatFee::getPrescription));
         List<Tfsqb> tfsqbList = new ArrayList();

         for(ExpenseDetail exDetail : list) {
            String billsNo = this.hisProcService.getDocumentOrBillNo(exDetail.getAdmissionNo(), 2);
            String prescription = exDetail.getPrescription();
            List<PatFee> patFees = (List)prescriptionMap.get(prescription);
            Tfsqb tfsqb = new Tfsqb();
            tfsqb.setAdmissionNo(exDetail.getAdmissionNo());
            tfsqb.setHospitalizedCount(exDetail.getHospitalizedCount());
            tfsqb.setBillsNoOld(exDetail.getBillsNoOld());
            tfsqb.setBillsNoNew(exDetail.getBillsNo());
            tfsqb.setSerialNumber(billsNo);
            tfsqb.setSerialNumberXh("1");
            tfsqb.setHospitalCode(exDetail.getHospitalCode());
            tfsqb.setDose(exDetail.getDose());
            tfsqb.setPrice(exDetail.getPrice());
            tfsqb.setMark("1");
            tfsqb.setTotal(exDetail.getTotal());
            tfsqb.setStandard(exDetail.getStandard());
            tfsqb.setUnit(exDetail.getUnit());
            tfsqb.setThreeLevelAccounting(exDetail.getThreeLevelAccounting());
            tfsqb.setThreeLevelName(exDetail.getThreeLevelName());
            tfsqb.setChargeCode(exDetail.getChargeCode());
            tfsqb.setChargeName(exDetail.getChargeName());
            tfsqb.setChargeNo(exDetail.getChargeNo());
            tfsqb.setOrderNo(exDetail.getCopeNo());
            tfsqb.setOrderType(exDetail.getCopeType());
            tfsqb.setOrderSortNumber(exDetail.getCopeSortNumber());
            tfsqb.setItemSortNumber(exDetail.getItemSortNumber());
            tfsqb.setProjectNo(exDetail.getProjectNo());
            tfsqb.setProjectName(exDetail.getProjectName());
            tfsqb.setStandardCode(exDetail.getStandardCode());
            tfsqb.setStandardName(exDetail.getStandardName());
            tfsqb.setPerformListNo(exDetail.getPerformListNo());
            tfsqb.setPatientName(baseInfo.getName());
            tfsqb.setExpenseCategoryNo(baseInfo.getExpenseCategoryNo());
            if (!patFees.isEmpty()) {
               PatFee patFee = (PatFee)patFees.get(0);
               tfsqb.setCaseNo(patFee.getCaseNo());
               tfsqb.setBabyNo(patFee.getBabyNo());
               tfsqb.setWardNo(patFee.getWardNo());
               tfsqb.setWardName(patFee.getWardName());
               tfsqb.setResidentCode(patFee.getVisitingStaffCode());
               tfsqb.setResidentNo(patFee.getVisitingStaffNo());
               tfsqb.setResidentName(patFee.getVisitingStaffName());
               tfsqb.setOperatorCode(patFee.getOperatorCode());
               tfsqb.setOperatorNo(patFee.getOperatorCode());
               tfsqb.setOperatorName(patFee.getOperatorName());
               tfsqb.setOperatorNewCode(patFee.getOperatorCode());
               tfsqb.setOperatorNewNo(patFee.getOperatorCode());
               tfsqb.setOperatorNewName(patFee.getOperatorName());
               tfsqb.setOperatorDate(patFee.getFilingDate());
               tfsqb.setApplyWardNo(patFee.getPhysicianDptNo());
               tfsqb.setApplyWardName(patFee.getPhysicianDptName());
               tfsqb.setApplyNo(patFee.getPhysicianNo());
               tfsqb.setApplyName(patFee.getPhysicianName());
            }

            tfsqbList.add(tfsqb);
         }

         this.tfsqbMapper.insertBatch(tfsqbList);
      }

   }

   private void updateOrderStatusAndPerformStatus(ProcessingOrdersReq req, SysUser user, String performListNo, List performListSortNumberList, String admissionNo, String orderClassCode, String orderNo, String orderGroupNo, String orderSortNumber) throws Exception {
      String orderType = this.tdPaOrderStatusService.selectOrderType(orderNo, orderSortNumber, orderGroupNo);
      String statusName = "处理";
      Integer statusCode = 2;
      if (orderType.equals("1")) {
         statusName = "处理";
         statusCode = 2;
      } else if (orderType.equals("2")) {
         statusName = "已执行";
         statusCode = 8;
      }

      List<String> statusList = new ArrayList();
      statusList.add(String.valueOf(1));
      List<String> performList = new ArrayList(1);
      if (performListSortNumberList == null) {
         performList = null;
      } else {
         for(String performListSortNumber : performListSortNumberList) {
            performList.add(performListNo + performListSortNumber);
         }
      }

      int count = this.inpatientSuspendOrderMapper.selectCountStatus(orderNo, performListNo, statusList, performList);
      if (count == 0) {
         Medicalinformation medicalinfo = this.medicalinformationMapper.getMedicalinfo(admissionNo);
         TdPaOrderStatus handleStatus = new TdPaOrderStatus(new Date(), medicalinfo.getWardNo(), medicalinfo.getWardNo(), medicalinfo.getCaseNo(), admissionNo, req.getHospitalizedCount(), orderNo, orderSortNumber, orderGroupNo, statusCode, statusName, (Integer)null, (String)null, 2, orderClassCode);
         this.tdPaOrderStatusService.insertSelective(handleStatus);
         this.tdPaOrderStatusService.updateItemOrderStatus(orderNo, orderSortNumber, orderGroupNo, statusCode.toString(), "1");
         TdPaOrderOperationLog temp = new TdPaOrderOperationLog();
         temp.setId(SnowIdUtils.uniqueLong());
         temp.setOrderNo(orderNo);
         temp.setOrderSortNumber(orderSortNumber);
         temp.setOrderGroupNo(orderGroupNo);
         temp.setOperationType(statusCode);
         temp.setOperationName(statusName);
         temp.setOperatorDesc((String)null);
         temp.setOperatorCode(user.getUserName());
         temp.setOperatorName(user.getNickName());
         temp.setOperatorNo(user.getUserName());
         temp.setOperationTime(new Date());
         this.tdPaOrderOperationLogMapper.insert(temp);
      }

      this.inpatientSuspendOrderMapper.updateStatus(performListNo, performListSortNumberList, String.valueOf(0), req.getMessage());
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public DrugListAndPerformReturnResultVo voidOrderPerform(ProcessingOrdersReq req, String ip) throws Exception {
      DrugListAndPerformReturnResultVo resultVo = null;
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String performListNo = req.getPerformListNo();
      List<String> performListSortNumberList = req.getPerformListSortNumberList();
      String admissionNo = req.getAdmissionNo();
      String orderClassCode = req.getOrderClassCode();
      String orderNo = req.getOrderNo();
      String orderGroupNo = req.getOrderGroupNo();
      String orderSortNumber = req.getOrderSortNumber();
      if (performListSortNumberList.contains("-1")) {
         performListSortNumberList = null;
      }

      switch (orderClassCode) {
         case "02":
         case "03":
            List<ExpenseDetail> detailList = this.expenseDetailMapper.selectExpenseDetailByPerformList(performListNo, performListSortNumberList);
            this.voidOrderClass(detailList, req, user, performListNo, performListSortNumberList, admissionNo, orderClassCode, orderNo, orderGroupNo, orderSortNumber);
            this.applyFormMapper.updateStatusByOrderNoAndOrderSortNumber(orderNo, orderSortNumber, "2");
            this.applyFormMapper.updateItemStatusByOrderNoAndOrderSortNumber(orderNo, orderGroupNo, orderSortNumber, "2");
            break;
         case "01":
            resultVo = this.drugOrderReturn(performListNo, performListSortNumberList, ip);
            this.updateVoidOrderStatusAndPerformStatus(req, user, performListNo, performListSortNumberList, admissionNo, orderClassCode, orderNo, orderGroupNo, orderSortNumber);
            break;
         default:
            this.updateVoidOrderStatusAndPerformStatus(req, user, performListNo, performListSortNumberList, admissionNo, orderClassCode, orderNo, orderGroupNo, orderSortNumber);
      }

      return resultVo;
   }

   public List selectTodayOrderOperationAllList(TodayOrderOperationReq req) {
      List<InpatientTodayOrderPerformDTO> allList = new ArrayList();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      req.setDeptCode(user.getDept().getDeptCode());
      List<InpatientTodayOrderPerformDTO> dataList = this.inpatientSuspendOrderMapper.selectTodayOrderOperationList(req);
      if (dataList != null && dataList.size() > 0) {
         Map<String, List<InpatientTodayOrderPerformDTO>> performListNoMap = (Map)dataList.stream().collect(Collectors.groupingBy(InpatientTodayOrderPerformDTO::getPerformListNo));

         for(String performListNo : performListNoMap.keySet()) {
            List<InpatientTodayOrderPerformDTO> performChildList = (List)performListNoMap.get(performListNo);
            List<InpatientTodayOrderPerformDTO> newChildList = (List)performChildList.stream().sorted(Comparator.comparing(InpatientTodayOrderPerformDTO::getPerformListStatus)).collect(Collectors.toList());
            InpatientTodayOrderPerformDTO performDTO = (InpatientTodayOrderPerformDTO)newChildList.get(0);
            Integer performListSortNumber = performDTO.getPerformListSortNumber();
            Map<Integer, List<InpatientTodayOrderPerformDTO>> performStatusMap = (Map)performChildList.stream().collect(Collectors.groupingBy(InpatientTodayOrderPerformDTO::getPerformListSortNumber));
            List<InpatientTodayOrderPerformDTO> performDTOList = (List)performStatusMap.get(performListSortNumber);
            if (performDTOList != null && performDTOList.size() > 0) {
               allList.addAll(performDTOList);
            }
         }

         allList = (List)allList.stream().sorted(Comparator.comparing(InpatientTodayOrderPerformDTO::getBedOrder).thenComparing(InpatientTodayOrderPerformDTO::getOrderGroupNo, Comparator.comparingDouble(Integer::parseInt))).collect(Collectors.toList());
      }

      return allList;
   }

   public List selectOrderItemByOrderNoList(List orderNoList) {
      return this.tdPaOrderItemMapper.selectOrderItemByOrderNoList(orderNoList);
   }

   public TdPaOrder selectOrderByOrderNo(String orderNo) {
      return this.tdPaOrderMapper.selectOrderByOrderNo(orderNo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateExecTimeByOrderNoList(List orderNoList, Date executionTime) throws Exception {
      this.tdPaOrderItemMapper.updateExecTimeByOrderNoList(orderNoList, executionTime);
   }

   private void voidOrderClass(List expenseDetailList, ProcessingOrdersReq req, SysUser user, String performListNo, List performListSortNumberList, String admissionNo, String orderClassCode, String orderNo, String orderGroupNo, String orderSortNumber) throws Exception {
      if (expenseDetailList != null && expenseDetailList.size() > 0) {
         Map<String, List<ExpenseDetail>> inpatientOrderGroupMap = (Map)expenseDetailList.stream().collect(Collectors.groupingBy(ExpenseDetail::getPerformListSortNumber));

         for(String key : inpatientOrderGroupMap.keySet()) {
            List<ExpenseDetail> details = (List)inpatientOrderGroupMap.get(key);
            List<ExpenseDetail> expenseDetails = new ArrayList();
            List<ExpenseDetail> returnExpenseDetails = new ArrayList();

            for(ExpenseDetail expenseDetail : details) {
               String billsNoOld = expenseDetail.getBillsNoOld();
               if (StringUtils.isNotEmpty(billsNoOld)) {
                  returnExpenseDetails.add(expenseDetail);
               } else {
                  expenseDetails.add(expenseDetail);
               }
            }

            Map<String, List<ExpenseDetail>> map = (Map)expenseDetails.stream().collect(Collectors.groupingBy(ExpenseDetail::getBillsNo));

            for(ExpenseDetail returnExpense : returnExpenseDetails) {
               String billsNoOld = returnExpense.getBillsNoOld();
               if (map.containsKey(billsNoOld)) {
                  map.remove(billsNoOld);
               }
            }

            BigDecimal totalAmount = BigDecimal.ZERO;
            if (map != null) {
               for(String keyDetail : map.keySet()) {
                  List<ExpenseDetail> detailList = (List)map.get(keyDetail);
                  List<PatFee> patFeeList = new ArrayList();
                  List<ExpenseDetail> list = new ArrayList();
                  if (detailList != null && detailList.size() > 0) {
                     for(int i = 0; i < detailList.size(); ++i) {
                        String newPrescription = this.hisProcService.getDocumentOrBillNo(SecurityUtils.getLoginUser().getUser().getUserName(), 4);
                        ExpenseDetail expenseDetail = (ExpenseDetail)detailList.get(i);
                        String prescription = expenseDetail.getPrescription();
                        PatFee patFee = this.patFeeMapper.selectFeeByPrescription(prescription);
                        if (patFee != null) {
                           patFee.setFilingDate(new Date());
                           patFee.setPrescription(newPrescription);
                           patFeeList.add(patFee);
                        }

                        BigDecimal total = expenseDetail.getTotal();
                        BigDecimal dose = expenseDetail.getDose();
                        expenseDetail.setTotal(total.negate());
                        expenseDetail.setDose(dose.negate());
                        totalAmount = totalAmount.add(expenseDetail.getTotal());
                        expenseDetail.setBillsNoOld(expenseDetail.getBillsNo());
                        expenseDetail.setPrescription(newPrescription);
                        String billNo = newPrescription + CommonUtils.getTenNumberStr(i);
                        expenseDetail.setBillsNo(billNo);
                        expenseDetail.setFilingDate(new Date());
                        expenseDetail.setPerformListNo(performListNo);
                        list.add(expenseDetail);
                     }

                     if (list.size() > 0) {
                        this.expenseDetailMapper.insertBatch(list);
                     }

                     if (patFeeList.size() > 0) {
                        this.patFeeMapper.insertBatch(patFeeList);
                     }

                     if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
                        CumulativeCost cumulativeCost = this.cumulativeCostService.queryByAdmissionNo(admissionNo);
                        BigDecimal cumulativeCostAmount = cumulativeCost.getCumulativeCost();
                        cumulativeCostAmount = cumulativeCostAmount == null ? new BigDecimal("0") : cumulativeCostAmount;
                        cumulativeCostAmount = cumulativeCostAmount.add(totalAmount).compareTo(BigDecimal.ZERO) > 0 ? cumulativeCostAmount.add(totalAmount).setScale(2, 4) : BigDecimal.ZERO;
                        CumulativeCost param = new CumulativeCost(admissionNo, cumulativeCostAmount);
                        this.cumulativeCostService.updateCumulativeCost(param);
                     }
                  }
               }
            }
         }
      }

      this.updateVoidOrderStatusAndPerformStatus(req, user, performListNo, performListSortNumberList, admissionNo, orderClassCode, orderNo, orderGroupNo, orderSortNumber);
   }

   private void updateVoidOrderStatusAndPerformStatus(ProcessingOrdersReq req, SysUser user, String performListNo, List performListSortNumberList, String admissionNo, String orderClassCode, String orderNo, String orderGroupNo, String orderSortNumber) throws Exception {
      String statusName = "提交";
      Integer statusCode = 0;
      List<String> statusList = new ArrayList();
      statusList.add(String.valueOf(1));
      statusList.add(String.valueOf(0));
      List<String> performList = new ArrayList(1);
      if (performListSortNumberList == null) {
         performList = null;
      } else {
         for(String performListSortNumber : performListSortNumberList) {
            performList.add(performListNo + performListSortNumber);
         }
      }

      int count = this.inpatientSuspendOrderMapper.selectCountStatus(orderNo, performListNo, statusList, performList);
      if (count == 0) {
         Medicalinformation medicalinfo = this.medicalinformationMapper.getMedicalinfo(admissionNo);
         TdPaOrderStatus handleStatus = new TdPaOrderStatus(new Date(), medicalinfo.getWardNo(), medicalinfo.getWardNo(), medicalinfo.getCaseNo(), admissionNo, req.getHospitalizedCount(), orderNo, orderSortNumber, orderGroupNo, statusCode, statusName, (Integer)null, (String)null, 2, orderClassCode);
         this.tdPaOrderStatusService.insertSelective(handleStatus);
         this.tdPaOrderStatusService.updateItemOrderStatus(orderNo, orderSortNumber, orderGroupNo, statusCode.toString(), "0");
         TdPaOrderOperationLog temp = new TdPaOrderOperationLog();
         temp.setId(SnowIdUtils.uniqueLong());
         temp.setOrderNo(orderNo);
         temp.setOrderSortNumber(orderSortNumber);
         temp.setOrderGroupNo(orderGroupNo);
         temp.setOperationType(statusCode);
         temp.setOperationName(statusName);
         temp.setOperatorDesc((String)null);
         temp.setOperatorCode(user.getUserName());
         temp.setOperatorName(user.getNickName());
         temp.setOperatorNo(user.getUserName());
         temp.setOperationTime(new Date());
         this.tdPaOrderOperationLogMapper.insert(temp);
      }

      this.inpatientSuspendOrderMapper.updateStatus(performListNo, performListSortNumberList, String.valueOf(2), req.getMessage());
   }

   private DrugListAndPerformReturnResultVo drugOrderReturn(String performListNo, List performListSortNumberList, String ip) throws Exception {
      DrugListAndPerformReturnResultVo resultVo = null;
      List<TakeDrugFeeDTO> drugLists = this.expenseDetailMapper.selectTakeDrugListByPerformListNo(performListNo, performListSortNumberList);
      if (drugLists != null && drugLists.size() > 0) {
         List<TakeDrugReturn> takeDrugReturnList = new ArrayList();
         Map<String, BigDecimal> sourceDoseMap = new HashMap();
         this.genTakeDrugReturn(takeDrugReturnList, drugLists, sourceDoseMap);
         this.takeDrugReturnMapper.insertList(takeDrugReturnList);
         this.takeDrugReturnLogService.addTakeDrugListLogList(0, takeDrugReturnList, 2, sourceDoseMap);
      }

      try {
         List<UnTakeDrugResp> drugUnLists = this.patientsReturnDrugMapper.selectUnTakeDrugListByPerformListNo(performListNo, performListSortNumberList);
         List<DrugListAndPerformReturnVo> returnVoList = new ArrayList(1);

         for(UnTakeDrugResp detail : drugUnLists) {
            DrugListAndPerformReturnVo returnVo = new DrugListAndPerformReturnVo((Long)null, detail.getPerformListNo(), Integer.valueOf(detail.getPerformListSortNumber()), new BigDecimal(detail.getOrderDose()));
            returnVoList.add(returnVo);
         }

         if (returnVoList.size() > 0) {
            resultVo = this.takeDrugReturnService.doReturnDrug(returnVoList, ip);
         }
      } catch (Exception e) {
         this.log.error("领药单作废出现异常：", e);
      }

      return resultVo;
   }

   public List getSysDictDataList() {
      List<SysDictDataVO> list = new ArrayList();

      for(SysDictData data : this.sysDictDataMapper.selectDictDataByType("s093")) {
         SysDictDataVO dataVO = new SysDictDataVO();
         BeanUtils.copyProperties(data, dataVO);
         list.add(dataVO);
      }

      return list;
   }

   private void genTakeDrugReturn(List takeDrugReturnList, List takeDrugFeeDTOList, Map sourceDoseMap) throws Exception {
      Map<String, List<TakeDrugFeeDTO>> takeDrugFeeDTOMap = (Map)takeDrugFeeDTOList.stream().collect(Collectors.groupingBy(TakeDrugFeeDTO::getExecutorDptNo));
      SysUser user = SecurityUtils.getLoginUser().getUser();

      for(String executorDptNo : takeDrugFeeDTOMap.keySet()) {
         List<TakeDrugFeeDTO> tempList = (List)takeDrugFeeDTOMap.get(executorDptNo);
         String documentOrBillNo = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 2);

         for(int i = 0; i < tempList.size(); ++i) {
            TakeDrugFeeDTO temp = (TakeDrugFeeDTO)tempList.get(i);
            TakeDrugReturn takeDrugReturn = new TakeDrugReturn();
            takeDrugReturn.setSerialNumber(documentOrBillNo);
            takeDrugReturn.setSerialNumberXh(i + 1 + "");
            takeDrugReturn.setHospitalCode(temp.getHospitalCode());
            takeDrugReturn.setCaseNo(temp.getCaseNo());
            takeDrugReturn.setPatientId(temp.getPatientId());
            takeDrugReturn.setAdmissionNo(temp.getAdmissionNo());
            takeDrugReturn.setHospitalizedCount(temp.getHospitalizedCount());
            takeDrugReturn.setPatientName(temp.getPatientName());
            takeDrugReturn.setResidentCode(temp.getVisitingStaffCode());
            takeDrugReturn.setResidentNo(temp.getVisitingStaffNo());
            takeDrugReturn.setResidentName(temp.getVisitingStaffName());
            takeDrugReturn.setWardNo(temp.getWardName());
            takeDrugReturn.setWardName(temp.getWardNo());
            takeDrugReturn.setChargeCode(temp.getChargeCode());
            takeDrugReturn.setDrugStockNo(temp.getDrugStockNo());
            takeDrugReturn.setChargeName(temp.getChargeName());
            takeDrugReturn.setStandard(temp.getStandard());
            takeDrugReturn.setUnit(temp.getUnit());
            takeDrugReturn.setPrice(temp.getPrice());
            takeDrugReturn.setDose(temp.getDose().negate());
            takeDrugReturn.setTotal(temp.getPrice().multiply(temp.getDose()).negate());
            takeDrugReturn.setOperatorCode(user.getUserName());
            takeDrugReturn.setOperatorNo(user.getUserName());
            takeDrugReturn.setOperatorName(user.getNickName());
            takeDrugReturn.setOperatorDate(new Date());
            takeDrugReturn.setBillsNoOld(temp.getBillsNo());
            takeDrugReturn.setMark("0");
            takeDrugReturn.setApplyName(user.getDept().getDeptName());
            takeDrugReturn.setApplyNo(user.getDept().getDeptCode());
            takeDrugReturn.setExecutorDptNo(temp.getExecutorDptNo());
            takeDrugReturn.setPhysicianName(temp.getOrderName());
            takeDrugReturn.setTakeDrugWardNo(temp.getTakeDrugWardNo());
            takeDrugReturn.setOrderNo(temp.getCopeNo());
            takeDrugReturn.setOrderSortNumber(temp.getCopeSortNumber());
            takeDrugReturn.setOrderGroupSortNumber(temp.getItemSortNumber());
            if (StringUtils.isNotBlank(temp.getCopeGroup())) {
               takeDrugReturn.setOrderGroupNo(Integer.valueOf(temp.getCopeGroup()));
            }

            takeDrugReturn.setPrescriptionOld(temp.getPrescription());
            takeDrugReturnList.add(takeDrugReturn);
            sourceDoseMap.put(takeDrugReturn.getSerialNumber() + "_" + takeDrugReturn.getSerialNumberXh(), temp.getDose());
         }
      }

   }

   private void savePatAtt(String allergyName, Medicalinformation m) {
      PatAtt patAtt = this.patAttMapper.findByAdmissionNo(m.getAdmissionNo());
      if (patAtt != null) {
         if (StringUtils.isNotBlank(patAtt.getAllergyName())) {
            if (!patAtt.getAllergyName().contains(allergyName)) {
               patAtt.setAllergyName(patAtt.getAllergyName() + " " + allergyName);
            }
         } else {
            patAtt.setAllergyName(allergyName);
         }

         this.patAttMapper.updatePatAtt(patAtt);
      } else {
         patAtt = new PatAtt();
         patAtt.setAdmissionNo(m.getAdmissionNo());
         patAtt.setCaseNo(m.getCaseNo());
         patAtt.setHospitalCode(m.getHospitalCode());
         patAtt.setHospitalizedCount(m.getHospitalizedCount());
         patAtt.setAllergyName(allergyName);
         this.patAttMapper.insert(patAtt);
      }

   }

   private void saveDictStandard(String allergyCode, String allergyName) {
      DictStandard param = new DictStandard();
      param.setDictCode(allergyCode);
      param.setDictName(allergyName);
      List<DictStandard> dictStandardList = this.dictStandardMapper.selectListByConn(param);
      if (dictStandardList.isEmpty()) {
         param.setDictPym(PinYinUtil.getPinYinHeadChar(allergyName));
         param.setTypeCode("CV05.01.038");
         param.setTypeName("过敏源代码表");
         param.setTypeShort("过敏源代码表");
         this.dictStandardMapper.save(param);
      }

   }

   private void saveAllergyRecord(AllergyRecordDTO allergyRecord, Medicalinformation m) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      AllergyRecord record = new AllergyRecord();
      record.setAdmissionNo(m.getAdmissionNo());
      List<AllergyRecord> list = this.allergyRecordMapper.getList(record);
      AllergyRecord allergyRecord2 = null;

      for(AllergyRecord allergyRecord1 : list) {
         if (StringUtils.isNotBlank(allergyRecord1.getAllergyCode()) && allergyRecord1.getAllergyCode().equals(allergyRecord.getChargeNo())) {
            allergyRecord2 = allergyRecord1;
            break;
         }
      }

      if (allergyRecord2 != null) {
         allergyRecord2.setOperatorDate(allergyRecord.getOperatorDate());
         allergyRecord2.setOperatorName(user.getNickName());
         allergyRecord2.setOperatorNo(user.getUserName());
         allergyRecord2.setAllergyType(allergyRecord.getSkinTestResults());
         this.allergyRecordMapper.updateAllergyRecord(allergyRecord2);
      } else {
         Baseinfomation b = this.baseinfomationMapper.findBaseInfo(m.getAdmissionNo());
         allergyRecord2 = new AllergyRecord();
         allergyRecord2.setId(SnowIdUtils.uniqueLong());
         allergyRecord2.setHospitalCode(m.getHospitalCode());
         allergyRecord2.setCaseNo(m.getCaseNo());
         allergyRecord2.setAdmissionNo(m.getAdmissionNo());
         allergyRecord2.setHospitalizedCount(m.getHospitalizedCount());
         allergyRecord2.setPatientName(m.getName());
         allergyRecord2.setPatientAge(b.getPersonAge() == null ? "" : b.getPersonAge() + "岁");
         allergyRecord2.setPatientSex(CommonUtils.getSex(b.getSex()));
         allergyRecord2.setAllergyCode(allergyRecord.getChargeNo());
         allergyRecord2.setAllergyName(allergyRecord.getChargeName());
         allergyRecord2.setOperatorDate(allergyRecord.getOperatorDate());
         allergyRecord2.setOperatorNo(user.getUserName());
         allergyRecord2.setOperatorName(user.getNickName());
         allergyRecord2.setAllergyType(allergyRecord.getSkinTestResults());
         this.allergyRecordMapper.insert(allergyRecord2);
      }

   }

   private boolean isAccountFlagByjcjy(String arrearsFlag, boolean arrea, String orderClassCode) {
      boolean isAccountFlag = false;
      ConfigureHospital configureHospital = this.configureHospitalService.queryByConfigureCode(1238.0F, SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode());
      if (null != configureHospital) {
         switch (configureHospital.getConfigureValue()) {
            case "1":
               if ("0".equals(arrearsFlag)) {
                  if (!arrea) {
                     isAccountFlag = false;
                  } else {
                     isAccountFlag = false;
                  }
               } else {
                  isAccountFlag = false;
               }
               break;
            case "2":
               if (orderClassCode.equals("02")) {
                  isAccountFlag = true;
               } else {
                  isAccountFlag = false;
               }
               break;
            case "3":
               if (orderClassCode.equals("03")) {
                  isAccountFlag = true;
               } else {
                  isAccountFlag = false;
               }
               break;
            default:
               isAccountFlag = true;
         }
      }

      return isAccountFlag;
   }

   public BigDecimal getTotal(String performListNo, String performListSortNumber) {
      Map<String, String> map = new HashMap();
      map.put("performListNo", performListNo);
      map.put("performListSortNumber", performListSortNumber);
      List<InpatientOrderPerformDetail> list = this.inpatientOrderPerformDetailService.selectListByNo(map);
      BigDecimal tot = new BigDecimal("0");
      if (list.size() > 0) {
         for(InpatientOrderPerformDetail detail : list) {
            int patientSelfDrugFlag = detail.getPatientSelfDrugFlag() == null ? 0 : detail.getPatientSelfDrugFlag();
            if (patientSelfDrugFlag != 3 && (detail.getPriceFlag() == null || detail.getPriceFlag() == 1)) {
               tot = tot.add(detail.getOrderTotal());
            }
         }
      }

      return tot;
   }
}
