package com.emr.project.operation.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.TakeDrugReturn;
import com.emr.project.operation.domain.TmBsAccountThird;
import com.emr.project.operation.domain.dto.TakeDrugFeeDTO;
import com.emr.project.operation.domain.req.TakeDrugFeeReq;
import com.emr.project.operation.mapper.ExpenseDetailMapper;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.operation.service.TmBsAccountThirdService;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.domain.vo.ExpenseDetailVo;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseDetailServiceImpl implements ExpenseDetailService {
   @Autowired
   private ExpenseDetailMapper expenseDetailMapper;
   @Autowired
   private TmBsAccountThirdService tmBsAccountThirdService;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public void addList(List list) throws Exception {
      this.expenseDetailMapper.insertBatch(list);
   }

   public void addListApp(List list) throws Exception {
      this.expenseDetailMapper.insertBatchApp(list);
   }

   public List queryTakeDrugFeePageList(TakeDrugFeeReq req) throws Exception {
      String admissionNo = req.getAdmissionNo();
      String drugName = req.getDrugName();
      String deptCode = req.getDeptCode();
      String startTime = req.getStartTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + " 00:00:00";
      }

      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + " 23:59:59";
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode1 = user.getDept().getDeptCode();
      TmBsAccountThird param = new TmBsAccountThird();
      param.setFirstCode("1");
      List<TmBsAccountThird> accountThirdList = this.tmBsAccountThirdService.queryTmBsAccountThirdList(param);
      List<String> threeLevelCodeList = accountThirdList != null && !accountThirdList.isEmpty() ? (List)accountThirdList.stream().map(TmBsAccountThird::getCode).collect(Collectors.toList()) : null;
      String config0071 = this.sysEmrConfigService.selectSysEmrConfigByKey("0071");
      if (StringUtils.isNotBlank(config0071) && config0071.equals("1")) {
         param.setFirstCode("3");
         List<TmBsAccountThird> accountThirdListM = this.tmBsAccountThirdService.queryTmBsAccountThirdList(param);
         List<String> threeLevelCodeListM = CollectionUtils.isNotEmpty(accountThirdListM) ? (List)accountThirdListM.stream().map((t) -> t.getCode()).collect(Collectors.toList()) : null;
         if (CollectionUtils.isNotEmpty(threeLevelCodeListM)) {
            threeLevelCodeList = (List<String>)(CollectionUtils.isNotEmpty(threeLevelCodeList) ? threeLevelCodeList : new ArrayList(1));
            threeLevelCodeList.addAll(threeLevelCodeListM);
         }
      }

      List<TakeDrugFeeDTO> list = this.expenseDetailMapper.selectTakeDrugFeePageList(threeLevelCodeList, admissionNo, drugName, deptCode, startTime, endTime, deptCode1);
      List<String> billsNoList = (List)list.stream().map((t) -> t.getBillsNo()).collect(Collectors.toList());
      List<ExpenseDetail> returnFeeList = billsNoList.isEmpty() ? null : this.expenseDetailMapper.selectReturnedFeeList(billsNoList);
      Map<String, List<ExpenseDetail>> returnFeeMap = (Map<String, List<ExpenseDetail>>)(returnFeeList == null ? new HashMap() : (Map)returnFeeList.stream().collect(Collectors.groupingBy(ExpenseDetail::getBillsNoOld)));
      List<TakeDrugFeeDTO> resList = new ArrayList();
      if (list != null && !list.isEmpty()) {
         List<TakeDrugReturn> takeDrugReturnList = this.takeDrugReturnService.queryByBillsNoOldList(billsNoList);
         Map<String, List<TakeDrugReturn>> takeDrugReturnMap = (Map)takeDrugReturnList.stream().collect(Collectors.groupingBy(TakeDrugReturn::getBillsNoOld));
         list.forEach((takeDrugFeeDTO) -> {
            String billsNo = takeDrugFeeDTO.getBillsNo();
            List<ExpenseDetail> tempList = (List)returnFeeMap.get(billsNo);
            BigDecimal returnedDose = new BigDecimal("0");
            BigDecimal applyedDose = new BigDecimal("0");
            if (tempList != null && !tempList.isEmpty()) {
               for(ExpenseDetail temp : tempList) {
                  returnedDose = returnedDose.add(temp.getDose());
               }
            }

            List<TakeDrugReturn> drugReturnList = (List)takeDrugReturnMap.get(billsNo);
            if (drugReturnList != null && !drugReturnList.isEmpty()) {
               for(TakeDrugReturn temp : drugReturnList) {
                  applyedDose = applyedDose.add(temp.getDose());
               }
            }

            takeDrugFeeDTO.setReturnedDose(returnedDose);
            takeDrugFeeDTO.setApplyedDose(applyedDose);
            takeDrugFeeDTO.setApplyCount(takeDrugFeeDTO.getApplyedDose().negate());
            Date filingDate = takeDrugFeeDTO.getIssueDate();
            if (filingDate != null) {
               takeDrugFeeDTO.setOperatorDateTime(DateFormatUtils.format(filingDate, "yyyy-MM-dd HH:mm"));
            }

            BigDecimal ableReturnDose = takeDrugFeeDTO.getDose().add(returnedDose).add(applyedDose).setScale(2, 4);
            if (ableReturnDose.compareTo(new BigDecimal("0")) > 0) {
               takeDrugFeeDTO.setReturnCount(ableReturnDose.stripTrailingZeros());
               resList.add(takeDrugFeeDTO);
            }

         });
      }

      List<TakeDrugFeeDTO> resultList = new ArrayList();
      Map<String, List<TakeDrugFeeDTO>> listMap = (Map)resList.stream().collect(Collectors.groupingBy((s) -> s.getCopeNo() + s.getPerformListNo() + s.getDrugStockNo()));
      String orderNoKey = null;

      for(TakeDrugFeeDTO takeDrugFeeDto : resList) {
         String orderNoKeyTemp = takeDrugFeeDto.getCopeNo() + takeDrugFeeDto.getPerformListNo() + takeDrugFeeDto.getDrugStockNo();
         List<TakeDrugFeeDTO> listTemp = null;
         if (!StringUtils.isBlank(orderNoKey) && orderNoKey.equals(orderNoKeyTemp)) {
            orderNoKey = orderNoKeyTemp;
         } else {
            orderNoKey = orderNoKeyTemp;
            listTemp = (List)listMap.get(orderNoKeyTemp);
            List zList = (List)listTemp.stream().filter((s) -> s.getOrderGroupSortNumber().equals("01")).collect(Collectors.toList());
            List sList = (List)listTemp.stream().filter((s) -> !s.getOrderGroupSortNumber().equals("01")).collect(Collectors.toList());
            TakeDrugFeeDTO zVo = null;
            if (zList == null || zList != null && zList.isEmpty()) {
               if (sList.size() > 1) {
                  zVo = (TakeDrugFeeDTO)sList.get(0);
                  sList = sList.subList(1, sList.size());
               } else {
                  zVo = (TakeDrugFeeDTO)sList.get(0);
                  sList = null;
               }
            } else {
               zVo = (TakeDrugFeeDTO)zList.get(0);
            }

            if (sList != null && !sList.isEmpty()) {
               zVo.setIcon("┓");
               resultList.add(zVo);
               sList = (List)sList.stream().sorted(Comparator.comparing(TakeDrugFeeDTO::getOrderGroupSortNumber)).collect(Collectors.toList());

               for(int i = 0; i < sList.size(); ++i) {
                  TakeDrugFeeDTO sVo = (TakeDrugFeeDTO)sList.get(i);
                  if (i == sList.size() - 1) {
                     sVo.setIcon("┛");
                  } else {
                     sVo.setIcon("┃");
                  }

                  resultList.add(sVo);
               }
            } else {
               resultList.add(zVo);
            }
         }
      }

      return resultList;
   }

   public List queryReturnedFeeList(List billsNoList) throws Exception {
      return this.expenseDetailMapper.selectReturnedFeeList(billsNoList);
   }

   public List queryFeeList(List billsNoList) throws Exception {
      return this.expenseDetailMapper.selectFeeList(billsNoList);
   }

   public List selectExpenseDetailListByPerformListNo(String performListNo, List performListSortNumberList) {
      return this.expenseDetailMapper.selectExpenseDetailListByPerformListNo(performListNo, performListSortNumberList);
   }

   public List selectExpenseDetailByBillsNoList(Set billsNoList) {
      return this.expenseDetailMapper.selectExpenseDetailByBillsNoList(billsNoList);
   }

   public List selectExpenseDetailApplyByBillsNoList(List billsNoList) throws Exception {
      List<ExpenseDetail> list = null;
      if (CollectionUtils.isNotEmpty(billsNoList)) {
         list = this.expenseDetailMapper.selectExpenseDetailApplyByBillsNoList(billsNoList);
      }

      return list;
   }

   public List selectExpenseDetailApplyByPrescriptions(List prescriptions) throws Exception {
      return CollectionUtils.isNotEmpty(prescriptions) ? this.expenseDetailMapper.selectExpenseDetailApplyByPrescriptions(prescriptions) : null;
   }

   public void delExpenseDetailApplyByBillsNo(List billsNoList) throws Exception {
      if (CollectionUtils.isNotEmpty(billsNoList)) {
         this.expenseDetailMapper.delExpenseDetailApplyByBillsNo(billsNoList);
      }

   }

   public List selectPatExpenseDetailApplyList(String admissionNo, String accountingDepartmentNo) throws Exception {
      List<ExpenseDetailVo> list = null;
      if (StringUtils.isNotBlank(admissionNo) && StringUtils.isNotBlank(accountingDepartmentNo)) {
         list = this.expenseDetailMapper.selectPatExpenseDetailApplyList(admissionNo, accountingDepartmentNo);
      }

      return list;
   }

   public String verifyExpensedetailByOrderNo(List orderList) {
      String resStr = null;
      List<ExpenseDetail> list = this.expenseDetailMapper.selectExpensedetailByOrderNo(orderList);
      Map<String, List<ExpenseDetail>> listMap = (Map<String, List<ExpenseDetail>>)(CollectionUtils.isNotEmpty(list) ? (Map)list.stream().collect(Collectors.groupingBy((t) -> t.getCopeNo())) : new HashMap(1));
      List<String> orderGroupNoList = new ArrayList(1);

      for(String orderNo : orderList) {
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

   public int queryOperationFee(String inpNo) throws Exception {
      return this.expenseDetailMapper.queryOperationFee(inpNo);
   }
}
