package com.emr.project.pat.service.impl;

import com.emr.common.utils.CommonUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.docOrder.domain.InpatientOrderUsageFee;
import com.emr.project.docOrder.domain.OrderDetailDTO;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.vo.InpatientOrderDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderPerformDTO;
import com.emr.project.docOrder.mapper.TdPaOrderDetailMapper;
import com.emr.project.docOrder.mapper.TdPaOrderStatusMapper;
import com.emr.project.docOrder.service.InpatientOrderUsageFeeService;
import com.emr.project.operation.domain.CumulativeCost;
import com.emr.project.operation.domain.DepartAccount;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.domain.PatFee;
import com.emr.project.operation.domain.PriceYy;
import com.emr.project.operation.domain.dto.DepartAccountDTO;
import com.emr.project.operation.domain.dto.NursingOperationFeeDTO;
import com.emr.project.operation.domain.vo.PriceYyJzXmVo;
import com.emr.project.operation.domain.vo.TmBsAccountThirdProjectVo;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.operation.service.ICumulativeCostService;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.operation.service.IPriceYyService;
import com.emr.project.operation.service.TmBsAccountThirdService;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.domain.req.FeeDetailReq;
import com.emr.project.pat.domain.vo.ExpenseDetailVo;
import com.emr.project.pat.domain.vo.PatFeeVo;
import com.emr.project.pat.mapper.PatFeeMapper;
import com.emr.project.pat.service.IPatFeeService;
import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysUserService;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

@Service
public class PatFeeServiceImpl implements IPatFeeService {
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private PatFeeMapper patFeeMapper;
   @Autowired
   private ICommonOperationService commonService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private HisProcService hisProcService;
   @Autowired
   private IPriceYyService priceYyService;
   @Autowired
   private TmBsAccountThirdService tmBsAccountThirdService;
   @Autowired
   private ExpenseDetailService expenseDetailService;
   @Autowired
   private ICumulativeCostService cumulativeCostService;
   @Autowired
   private TdPaOrderStatusMapper orderStatusMapper;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private TdPaOrderDetailMapper orderDetailMapper;
   @Autowired
   private InpatientOrderUsageFeeService inpatientOrderUsageFeeService;

   public PatFeeVo selectAmountList(PatFeeVo patFeeVo) throws Exception {
      return this.patFeeMapper.selectAmount(patFeeVo);
   }

   public List selectFeeList(PatFeeVo patFeeVo) throws Exception {
      List<PatFeeVo> list = this.patFeeMapper.selectFeeList(patFeeVo);
      if (list != null && !list.isEmpty()) {
         Double total = (Double)list.stream().collect(Collectors.summingDouble(PatFeeVo::getFeeAmount));

         for(PatFeeVo fee : list) {
            fee.setFeeRatio(fee.getFeeAmount() / total);
         }
      }

      return list;
   }

   public List selectFeeByChargeCode(ExpenseDetailVo expenseDetailVo) throws Exception {
      List<ExpenseDetail> list = this.patFeeMapper.selectFeeByChargeCode(expenseDetailVo);
      List<ExpenseDetail> listNew = new ArrayList(list.size());
      String preThreeCode = "";
      String preThreeName = "";
      BigDecimal preAmoutTotal = new BigDecimal("0");

      for(int i = 0; i < list.size(); ++i) {
         ExpenseDetail expenseDetail = (ExpenseDetail)list.get(i);
         String threeCode = expenseDetail.getThreeLevelAccounting();
         String threeName = expenseDetail.getThreeLevelName();
         if (i == 0) {
            preThreeCode = threeCode;
            preThreeName = threeName;
            preAmoutTotal = expenseDetail.getTotal();
            listNew.add(expenseDetail);
         } else {
            if ((preThreeCode + preThreeName).equals(threeCode + preThreeName)) {
               preAmoutTotal = preAmoutTotal.add(expenseDetail.getTotal());
               listNew.add(expenseDetail);
            } else {
               ExpenseDetail temp = new ExpenseDetail();
               temp.setThreeLevelAccounting(preThreeCode);
               temp.setThreeLevelName(preThreeName);
               temp.setChargeNo("total");
               temp.setChargeName(preThreeName + "合计：");
               temp.setTotal(preAmoutTotal);
               listNew.add(temp);
               preThreeCode = threeCode;
               preThreeName = threeName;
               preAmoutTotal = expenseDetail.getTotal();
               listNew.add(expenseDetail);
            }

            if (i == list.size() - 1) {
               ExpenseDetail temp = new ExpenseDetail();
               temp.setThreeLevelAccounting(expenseDetail.getThreeLevelAccounting());
               temp.setThreeLevelName(expenseDetail.getThreeLevelName());
               temp.setChargeNo("total");
               temp.setChargeName(expenseDetail.getThreeLevelName() + "合计：");
               temp.setTotal(preAmoutTotal);
               listNew.add(temp);
            }
         }
      }

      return listNew;
   }

   public List selectFeeByThreeLevelAccounting(ExpenseDetailVo expenseDetailVo) throws Exception {
      return this.patFeeMapper.selectFeeByThreeLevelAccounting(expenseDetailVo);
   }

   public List selectFeeByDate(ExpenseDetailVo expenseDetailVo) throws Exception {
      return this.patFeeMapper.selectFeeByDate(expenseDetailVo);
   }

   public List selectFeeByType(ExpenseDetailVo expenseDetailVo) throws Exception {
      return this.patFeeMapper.selectFeeByType(expenseDetailVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public BigDecimal toFee(List dataList, String feeType, String performListNo, List isAccountFlagSuccessList, DepartAccountDTO departAccountDTO) throws Exception {
      PatFee patFee = new PatFee();
      List<ExpenseDetail> expenseDetailList = new ArrayList();
      BigDecimal totalAmount = new BigDecimal("0");
      Medicalinformation medicalinformation = null;
      switch (feeType) {
         case "0303":
            totalAmount = this.nursingOperationFeeToPatFeeExpenseDetail(dataList, patFee, expenseDetailList, totalAmount);
            break;
         case "0303_2":
            totalAmount = this.nursingOperationFeeToPatFeeExpenseDetailApply(dataList, patFee, expenseDetailList, totalAmount, departAccountDTO);
            break;
         case "0204":
            totalAmount = this.orderToPatFeeExpenseDetail(dataList, patFee, expenseDetailList, totalAmount, performListNo);
            break;
         case "0205":
            totalAmount = this.drugPerformToPatFeeExpenseDetail(dataList, patFee, expenseDetailList, totalAmount);
            break;
         case "0205_2":
            totalAmount = this.drugHerbalPerformToPatFeeExpenseDetail(dataList, patFee, expenseDetailList, totalAmount);
            break;
         case "0206":
            totalAmount = this.crossDepApplyToPatFeeExpenseDetail(dataList, patFee, expenseDetailList, totalAmount);
            break;
         case "0207":
            totalAmount = this.otherPerformToPatFeeExpenseDetail(dataList, patFee, expenseDetailList, totalAmount);
      }

      BigDecimal amount = new BigDecimal("0");
      if (BigDecimal.ZERO.compareTo(totalAmount) == -1 && !feeType.equals("0303_2")) {
         checkObj = this.commonService.checkPatientArrears(patFee.getAdmissionNo(), patFee.getHospitalizedCount() + "", totalAmount);
         if (!"0206".equals(feeType) && !checkObj) {
            if (feeType.equals("0205") || feeType.equals("0205_2") || feeType.equals("0207")) {
               amount = new BigDecimal("-1");
            }
         } else {
            if (isAccountFlagSuccessList != null) {
               isAccountFlagSuccessList.add(true);
            }

            List<PatFee> patFeeList = new ArrayList(Arrays.asList(patFee));
            this.patFeeMapper.insertBatch(patFeeList);
            this.expenseDetailService.addList(expenseDetailList);
            totalAmount = totalAmount.setScale(2, 4);
            CumulativeCost cumulativeCost = this.cumulativeCostService.queryByAdmissionNo(patFee.getAdmissionNo());
            BigDecimal cumulativeCostAmount = cumulativeCost.getCumulativeCost();
            cumulativeCostAmount = cumulativeCostAmount == null ? new BigDecimal("0") : cumulativeCostAmount;
            cumulativeCostAmount = cumulativeCostAmount.add(totalAmount).setScale(2, 4);
            CumulativeCost param = new CumulativeCost(patFee.getAdmissionNo(), cumulativeCostAmount);
            this.cumulativeCostService.updateCumulativeCost(param);
            amount = cumulativeCost.getDeposIt().subtract(cumulativeCostAmount).setScale(2, 4);
            if (feeType.equals("0205") || feeType.equals("0205_2") || feeType.equals("0207")) {
               amount = new BigDecimal("1");
            }
         }
      } else if (feeType.equals("0303_2")) {
         List<PatFee> patFeeList = new ArrayList(Arrays.asList(patFee));
         this.patFeeMapper.insertBatchApp(patFeeList);
         this.expenseDetailService.addListApp(expenseDetailList);
      }

      return amount;
   }

   private BigDecimal orderToPatFeeExpenseDetail(List inpatientOrderDTOList, PatFee patFee, List expenseDetailList, BigDecimal totalAmount, String performListNo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      this.orderToPatFee((InpatientOrderDTO)inpatientOrderDTOList.get(0), patFee);
      String prescription = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 4);
      String billsNoPre = prescription;
      patFee.setPrescription(prescription);
      Date filingDate = this.commonService.getDbDate();
      patFee.setFilingDate(filingDate);
      List<String> cpNoList = (List)inpatientOrderDTOList.stream().map((t) -> t.getChargeNo()).distinct().collect(Collectors.toList());
      List<OrderDetailDTO> orderDetailDTOList = this.orderDetailMapper.selectByCpNoList(cpNoList);
      Map<String, List<OrderDetailDTO>> orderDetailDTOMap = (Map)orderDetailDTOList.stream().collect(Collectors.groupingBy(TdPaOrderDetail::getCpNo));
      Integer billNoNum = 0;

      for(int i = 0; i < inpatientOrderDTOList.size(); ++i) {
         InpatientOrderDTO inpatientOrderDTO = (InpatientOrderDTO)inpatientOrderDTOList.get(i);
         List<OrderDetailDTO> orderDetailDTOListTemp = (List)orderDetailDTOMap.get(inpatientOrderDTO.getChargeNo());
         if (orderDetailDTOListTemp != null && !orderDetailDTOListTemp.isEmpty()) {
            for(int j = 0; j < orderDetailDTOListTemp.size(); ++j) {
               OrderDetailDTO orderDetailDTO = (OrderDetailDTO)orderDetailDTOListTemp.get(j);
               ExpenseDetail expenseDetail = new ExpenseDetail();
               this.orderToExpenseDetail(inpatientOrderDTO, expenseDetail, orderDetailDTO);
               expenseDetail.setPayMark("-1");
               expenseDetail.setPrescription(prescription);
               String billNo = billsNoPre + CommonUtils.getTenNumberStr(billNoNum);
               expenseDetail.setPerformListNo(performListNo);
               expenseDetail.setPerformListSortNumber("1");
               expenseDetail.setBillsNo(billNo);
               expenseDetail.setFilingDate(filingDate);
               expenseDetailList.add(expenseDetail);
               totalAmount = totalAmount.add(expenseDetail.getTotal());
               billNoNum = billNoNum + 1;
            }
         }
      }

      return totalAmount;
   }

   private void orderToExpenseDetail(InpatientOrderDTO inpatientOrderDTO, ExpenseDetail expenseDetail, OrderDetailDTO orderDetailDTO) throws Exception {
      expenseDetail.setHospitalCode(inpatientOrderDTO.getHospitalCode());
      expenseDetail.setAdmissionNo(inpatientOrderDTO.getAdmissionNo());
      expenseDetail.setHospitalizedCount(inpatientOrderDTO.getHospitalizedCount());
      Map<String, String> priceYyParamMap = new HashMap(1);
      priceYyParamMap.put("standardCode", orderDetailDTO.getStandardCode());
      priceYyParamMap.put("chargeNo", orderDetailDTO.getChargeNo());
      PriceYyJzXmVo priceYyJzXmMap = this.priceYyService.getByStandardCode(priceYyParamMap);
      if (priceYyJzXmMap == null) {
         priceYyJzXmMap = new PriceYyJzXmVo();
      }

      String projectNo = priceYyJzXmMap.getAccountUpper();
      String projectName = priceYyJzXmMap.getHosUpperName();
      String threeLevelAccounting = priceYyJzXmMap.getCode();
      String threeLevelName = priceYyJzXmMap.getName();
      expenseDetail.setProjectNo(projectNo);
      expenseDetail.setProjectName(projectName);
      expenseDetail.setThreeLevelAccounting(threeLevelAccounting);
      expenseDetail.setThreeLevelName(threeLevelName);
      expenseDetail.setStandardCode(priceYyJzXmMap.getStandardCode());
      expenseDetail.setStandardName(priceYyJzXmMap.getStandardName());
      expenseDetail.setChargeCode(priceYyJzXmMap.getItemNo());
      expenseDetail.setChargeNo(priceYyJzXmMap.getItemNoSuffix());
      expenseDetail.setChargeName(priceYyJzXmMap.getItemName());
      expenseDetail.setStandard(priceYyJzXmMap.getStandard());
      expenseDetail.setUnit(priceYyJzXmMap.getUnit());
      expenseDetail.setPrice(priceYyJzXmMap.getPrice());
      BigDecimal dose = inpatientOrderDTO.getOrderDose().multiply(orderDetailDTO.getOrderDose()).setScale(4, 2);
      expenseDetail.setDose(dose);
      BigDecimal total = expenseDetail.getPrice().multiply(dose).setScale(4, 2);
      expenseDetail.setTotal(total);
      expenseDetail.setCopeNo(inpatientOrderDTO.getOrderNo());
      expenseDetail.setCopeSortNumber(inpatientOrderDTO.getOrderSortNumber());
      expenseDetail.setItemSortNumber(inpatientOrderDTO.getOrderGroupSortNumber());
      expenseDetail.setCopeGroup(inpatientOrderDTO.getOrderGroupNo());
      expenseDetail.setCopeType(inpatientOrderDTO.getOrderType());
      expenseDetail.setCopeClass(inpatientOrderDTO.getOrderClassCode());
      expenseDetail.setItemCode(inpatientOrderDTO.getCpNo());
      expenseDetail.setItemName(inpatientOrderDTO.getCpName());
      String babyFeeMark = StringUtils.isNotBlank(inpatientOrderDTO.getBabyAdmissionNo()) ? "1" : "0";
      expenseDetail.setBabyFeeMark(babyFeeMark);
      expenseDetail.setSourceProgram("java");
   }

   private void orderToPatFee(InpatientOrderDTO inpatientOrderDTO, PatFee patFee) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Medicalinformation medicalinformation = this.medicalinfoService.queryByAdmissionNo(inpatientOrderDTO.getAdmissionNo());
      patFee.setHospitalCode(inpatientOrderDTO.getHospitalCode());
      patFee.setCaseNo(inpatientOrderDTO.getCaseNo());
      patFee.setAdmissionNo(inpatientOrderDTO.getAdmissionNo());
      patFee.setHospitalizedCount(inpatientOrderDTO.getHospitalizedCount());
      patFee.setBabyNo(inpatientOrderDTO.getBabyAdmissionNo());
      patFee.setWardNo(inpatientOrderDTO.getPatientWardNo());
      patFee.setWardName(inpatientOrderDTO.getPatientWardName());
      patFee.setDepartmentNo(inpatientOrderDTO.getPatientDepCode());
      patFee.setDepartmentName(inpatientOrderDTO.getPatientDepName());
      patFee.setMedicalGroupV(medicalinformation.getMedicalGroup());
      patFee.setVisitingStaffCode(inpatientOrderDTO.getResidentDoctorCode());
      patFee.setVisitingStaffNo(inpatientOrderDTO.getResidentDoctorNo());
      patFee.setVisitingStaffName(inpatientOrderDTO.getResidentDoctorName());
      patFee.setPhysicianCode(inpatientOrderDTO.getOrderDoctorCode());
      patFee.setPhysicianNo(inpatientOrderDTO.getOrderDoctorNo());
      patFee.setPhysicianName(inpatientOrderDTO.getOrderDoctorName());
      patFee.setPhysicianDptNo(inpatientOrderDTO.getOrderDoctorDepCode());
      patFee.setPhysicianDptName(inpatientOrderDTO.getOrderDoctorDepName());
      patFee.setExecutorCode(user.getUserName());
      patFee.setExecutorNo(user.getUserName());
      patFee.setExecutorName(user.getNickName());
      String executorDptNo = this.orderDetailMapper.queryExecutorDptNo(inpatientOrderDTO.getOrderNo(), inpatientOrderDTO.getOrderGroupNo(), inpatientOrderDTO.getOrderSortNumber());
      patFee.setExecutorDptNo(executorDptNo);
      String executorDptName = "";
      if (StringUtils.isNotBlank(executorDptNo)) {
         SysDept dept = this.sysDeptService.getOneByCode(executorDptNo);
         if (dept != null) {
            executorDptName = dept.getDeptName();
         }
      }

      patFee.setExecutorDptName(executorDptName);
      patFee.setAccountingDepartmentNo(user.getDept().getDeptCode());
      patFee.setAccountingDepartmentName(user.getDept().getDeptName());
      patFee.setSourceProgram("java");
      patFee.setOperatorCode(user.getUserName());
      patFee.setOperator(user.getUserName());
      patFee.setOperatorName(user.getNickName());
   }

   private BigDecimal drugHerbalPerformToPatFeeExpenseDetail(List inpatientOrderPerformDTOList, PatFee patFee, List expenseDetailList, BigDecimal totalAmount) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      InpatientOrderPerformDTO performDTO = (InpatientOrderPerformDTO)inpatientOrderPerformDTOList.get(0);
      Medicalinformation medicalinformation = this.medicalinfoService.queryByAdmissionNo(performDTO.getAdmissionNo());
      if (String.valueOf(performDTO.getHerbalFlag()).equals("1") && StringUtils.isNotBlank(performDTO.getDecoctMethod())) {
         List<SysDictData> decoctMethodList = this.sysDictDataService.selectDictDataByType("s074");
         List<SysDictData> decoctMethodListRes = CollectionUtils.isNotEmpty(decoctMethodList) ? (List)decoctMethodList.stream().filter((t) -> t.getDictValue().equals(performDTO.getDecoctMethod()) && StringUtils.isNotBlank(t.getRemark())).collect(Collectors.toList()) : null;
         if (CollectionUtils.isNotEmpty(decoctMethodListRes)) {
            SysDictData decoctMethodDict = (SysDictData)decoctMethodListRes.get(0);
            this.drugHerbalPerformToPatFee(performDTO, patFee, medicalinformation);
            String prescription = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 4);
            patFee.setPrescription(prescription);
            Date filingDate = this.commonService.getDbDate();
            patFee.setFilingDate(filingDate);
            ExpenseDetail expenseDetail = new ExpenseDetail();
            this.drugHerbalPerformToExpenseDetail(performDTO, expenseDetail, decoctMethodDict);
            expenseDetail.setPayMark("-1");
            expenseDetail.setPrescription(prescription);
            String billNo = prescription + CommonUtils.getTenNumberStr(0);
            expenseDetail.setBillsNo(billNo);
            expenseDetail.setFilingDate(filingDate);
            expenseDetailList.add(expenseDetail);
            totalAmount = totalAmount.add(expenseDetail.getTotal());
         }
      }

      return totalAmount;
   }

   private void drugHerbalPerformToPatFee(InpatientOrderPerformDTO inpatientOrderPerformDTO, PatFee patFee, Medicalinformation medicalinformation) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<SysDept> deptList = this.sysDeptService.queryByDepCodeList(new ArrayList(Arrays.asList(inpatientOrderPerformDTO.getPatientDepCode(), inpatientOrderPerformDTO.getOrderDoctorDepCode())));
      Map<String, SysDept> deptMap = (Map)deptList.stream().collect(Collectors.toMap(SysDept::getDeptCode, Function.identity()));
      BsStaff orderDoctor = this.sysUserService.queryByStaffNo(inpatientOrderPerformDTO.getOrderDoctorNo());
      patFee.setHospitalCode(inpatientOrderPerformDTO.getHospitalCode());
      patFee.setCaseNo(inpatientOrderPerformDTO.getCaseNo());
      patFee.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
      patFee.setHospitalizedCount(inpatientOrderPerformDTO.getHospitalizedCount());
      patFee.setBabyNo(inpatientOrderPerformDTO.getBabyAdmissionNo());
      patFee.setWardNo(((SysDept)deptMap.get(inpatientOrderPerformDTO.getPatientDepCode())).getDeptCode());
      patFee.setWardName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getPatientDepCode())).getDeptName());
      patFee.setDepartmentNo(inpatientOrderPerformDTO.getPatientDepCode());
      patFee.setDepartmentName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getPatientDepCode())).getDeptName());
      patFee.setMedicalGroupV(medicalinformation.getMedicalGroup());
      patFee.setVisitingStaffCode(medicalinformation.getResidentCode());
      patFee.setVisitingStaffNo(medicalinformation.getResidentNo());
      patFee.setVisitingStaffName(medicalinformation.getResidentName());
      patFee.setPhysicianCode(inpatientOrderPerformDTO.getOrderDoctorCode());
      patFee.setPhysicianNo(inpatientOrderPerformDTO.getOrderDoctorNo());
      patFee.setPhysicianName(orderDoctor.getStaffName());
      patFee.setPhysicianDptNo(inpatientOrderPerformDTO.getOrderDoctorDepCode());
      patFee.setPhysicianDptName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getOrderDoctorDepCode())).getDeptName());
      patFee.setExecutorCode(user.getUserName());
      patFee.setExecutorNo(user.getUserName());
      patFee.setExecutorName(user.getNickName());
      patFee.setExecutorDptNo(inpatientOrderPerformDTO.getDetailPerformDepCode());
      patFee.setExecutorDptName(inpatientOrderPerformDTO.getDetailperformDepName());
      patFee.setAccountingDepartmentNo(user.getDept().getDeptCode());
      patFee.setAccountingDepartmentName(user.getDept().getDeptName());
      patFee.setSourceProgram("java");
      patFee.setOperatorCode(user.getUserName());
      patFee.setOperator(user.getUserName());
      patFee.setOperatorName(user.getNickName());
   }

   private void drugHerbalPerformToExpenseDetail(InpatientOrderPerformDTO inpatientOrderPerformDTO, ExpenseDetail expenseDetail, SysDictData decoctMethodDict) throws Exception {
      expenseDetail.setHospitalCode(inpatientOrderPerformDTO.getHospitalCode());
      expenseDetail.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
      expenseDetail.setHospitalizedCount(inpatientOrderPerformDTO.getHospitalizedCount());
      Map<String, String> priceYyParamMap = new HashMap(1);
      priceYyParamMap.put("item_no", decoctMethodDict.getRemark());
      PriceYyJzXmVo priceYyJzXmMap = this.priceYyService.getByCode(priceYyParamMap);
      String projectNo = priceYyJzXmMap.getAccountUpper();
      String projectName = priceYyJzXmMap.getHosUpperName();
      String threeLevelAccounting = priceYyJzXmMap.getCode();
      String threeLevelName = priceYyJzXmMap.getName();
      expenseDetail.setProjectNo(projectNo);
      expenseDetail.setProjectName(projectName);
      expenseDetail.setThreeLevelAccounting(threeLevelAccounting);
      expenseDetail.setThreeLevelName(threeLevelName);
      expenseDetail.setStandardCode(priceYyJzXmMap.getStandardCode());
      expenseDetail.setStandardName(priceYyJzXmMap.getStandardName());
      expenseDetail.setChargeNo(priceYyJzXmMap.getItemNoSuffix());
      expenseDetail.setChargeCode(priceYyJzXmMap.getItemNo());
      expenseDetail.setChargeName(priceYyJzXmMap.getItemName());
      expenseDetail.setStandard(priceYyJzXmMap.getStandard());
      expenseDetail.setUnit(priceYyJzXmMap.getUnit());
      expenseDetail.setPrice(priceYyJzXmMap.getPrice());
      expenseDetail.setDose(inpatientOrderPerformDTO.getHerbalDose());
      BigDecimal total = priceYyJzXmMap.getPrice().multiply(expenseDetail.getDose()).setScale(4, 2);
      expenseDetail.setTotal(total);
      expenseDetail.setCopeNo(inpatientOrderPerformDTO.getOrderNo());
      expenseDetail.setCopeSortNumber(inpatientOrderPerformDTO.getOrderSortNumber());
      expenseDetail.setItemSortNumber(inpatientOrderPerformDTO.getOrderGroupSortNumber());
      expenseDetail.setCopeGroup(inpatientOrderPerformDTO.getOrderGroupNo());
      expenseDetail.setCopeType(inpatientOrderPerformDTO.getOrderType());
      expenseDetail.setCopeClass(inpatientOrderPerformDTO.getOrderClassCode());
      expenseDetail.setItemCode(inpatientOrderPerformDTO.getCpNo());
      expenseDetail.setItemName(inpatientOrderPerformDTO.getCpName());
      expenseDetail.setPerformListNo(inpatientOrderPerformDTO.getPerformListNo());
      expenseDetail.setPerformListSortNumber(inpatientOrderPerformDTO.getPerformListSortNumber() + "");
      String babyFeeMark = StringUtils.isNotBlank(inpatientOrderPerformDTO.getBabyAdmissionNo()) ? "1" : "0";
      expenseDetail.setBabyFeeMark(babyFeeMark);
      expenseDetail.setSourceProgram("java");
   }

   private BigDecimal nursingOperationFeeToPatFeeExpenseDetail(List dataList, PatFee patFee, List expenseDetailList, BigDecimal totalAmount) throws Exception {
      Date filingDate = this.commonService.getDbDate();
      String prescription = ((ExpenseDetail)dataList.get(0)).getPrescription();
      PatFee patFeeNew = this.patFeeMapper.selectFeeAppByPrescription(prescription);
      BeanUtils.copyProperties(patFeeNew, patFee);
      String prescriptionNew = this.hisProcService.getDocumentOrBillNo(SecurityUtils.getLoginUser().getUser().getUserName(), 4);
      patFee.setFilingDate(filingDate);
      patFee.setPrescription(prescriptionNew);
      String billsNoPre = prescriptionNew;

      for(int i = 0; i < dataList.size(); ++i) {
         ExpenseDetail detail = (ExpenseDetail)dataList.get(i);
         String billNo = billsNoPre + CommonUtils.getTenNumberStr(i);
         detail.setPrescription(prescriptionNew);
         detail.setBillsNo(billNo);
         detail.setFilingDate(filingDate);
         expenseDetailList.add(detail);
         totalAmount = totalAmount.add(detail.getTotal());
      }

      return totalAmount;
   }

   private BigDecimal nursingOperationFeeToPatFeeExpenseDetailApply(List nursingOperationFeeDTOList, PatFee patFee, List expenseDetailList, BigDecimal totalAmount, DepartAccountDTO departAccountDTO) throws Exception {
      this.nursingOperationFeeToPatFee((NursingOperationFeeDTO)nursingOperationFeeDTOList.get(0), patFee, departAccountDTO);
      String prescription = this.hisProcService.getDocumentOrBillNo(SecurityUtils.getLoginUser().getUser().getUserName(), 4);
      String billsNoPre = prescription;
      ((NursingOperationFeeDTO)nursingOperationFeeDTOList.get(0)).setPrescription(prescription);
      patFee.setPrescription(prescription);
      Date filingDate = this.commonService.getDbDate();
      patFee.setFilingDate(filingDate);
      List<String> chargeCodeList = (List)nursingOperationFeeDTOList.stream().map(NursingOperationFeeDTO::getXmbm).collect(Collectors.toList());
      List<PriceYy> priceYyList = this.priceYyService.queryByItemCodeList(chargeCodeList);
      Map<String, PriceYy> priceYyMap = (Map)priceYyList.stream().collect(Collectors.toMap(PriceYy::getItemNo, Function.identity()));

      for(int i = 0; i < nursingOperationFeeDTOList.size(); ++i) {
         NursingOperationFeeDTO nursingOperationFeeDTO = (NursingOperationFeeDTO)nursingOperationFeeDTOList.get(i);
         ExpenseDetail expenseDetail = new ExpenseDetail();
         this.nursingOperationFeeToExpenseDetail(nursingOperationFeeDTO, expenseDetail, priceYyMap, departAccountDTO);
         expenseDetail.setPayMark("-1");
         expenseDetail.setPrescription(prescription);
         String billNo = billsNoPre + CommonUtils.getTenNumberStr(i);
         expenseDetail.setBillsNo(billNo);
         expenseDetail.setFilingDate(filingDate);
         expenseDetailList.add(expenseDetail);
         totalAmount = totalAmount.add(expenseDetail.getTotal());
      }

      return totalAmount;
   }

   private void nursingOperationFeeToExpenseDetail(NursingOperationFeeDTO nursingOperationFeeDTO, ExpenseDetail expenseDetail, Map priceYyMap, DepartAccountDTO departAccountDTO) throws Exception {
      PriceYy priceYy = (PriceYy)priceYyMap.get(nursingOperationFeeDTO.getXmbm());
      TmBsAccountThirdProjectVo thirdProjectVo = this.tmBsAccountThirdService.selectThirdAndProjectByCode(priceYy.getHosUpper());
      String projectNo = "";
      String projectName = "";
      String threeLevelAccounting = "";
      String threeLevelName = "";
      if (thirdProjectVo != null) {
         projectNo = thirdProjectVo.getProjectNo();
         projectName = thirdProjectVo.getProjectName();
         threeLevelAccounting = thirdProjectVo.getCode();
         threeLevelName = thirdProjectVo.getName();
      }

      expenseDetail.setHospitalCode(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode());
      expenseDetail.setAdmissionNo(departAccountDTO.getZyh());
      expenseDetail.setHospitalizedCount(departAccountDTO.getZycs());
      expenseDetail.setProjectNo(projectNo);
      expenseDetail.setProjectName(projectName);
      expenseDetail.setThreeLevelAccounting(threeLevelAccounting);
      expenseDetail.setThreeLevelName(threeLevelName);
      expenseDetail.setStandardCode(nursingOperationFeeDTO.getXmbm());
      expenseDetail.setStandardName(nursingOperationFeeDTO.getXmmc());
      expenseDetail.setChargeCode(nursingOperationFeeDTO.getXmbm());
      expenseDetail.setChargeNo(nursingOperationFeeDTO.getXmbm());
      expenseDetail.setChargeName(nursingOperationFeeDTO.getXmmc());
      expenseDetail.setStandard(nursingOperationFeeDTO.getGg());
      expenseDetail.setUnit(nursingOperationFeeDTO.getDw());
      expenseDetail.setPrice(priceYy.getPrice());
      expenseDetail.setDose(nursingOperationFeeDTO.getSl());
      BigDecimal total = priceYy.getPrice().multiply(nursingOperationFeeDTO.getSl()).setScale(4, 2);
      expenseDetail.setTotal(total);
      if (StringUtils.isNotEmpty(departAccountDTO.getOrderNo())) {
         expenseDetail.setCopeNo(departAccountDTO.getOrderNo());
         expenseDetail.setCopeSortNumber(departAccountDTO.getOrderSortNumber());
         expenseDetail.setItemSortNumber(departAccountDTO.getOrderGroupSortNumber());
         expenseDetail.setCopeGroup(departAccountDTO.getOrderGroupNo());
         expenseDetail.setCopeType(departAccountDTO.getOrderType());
         expenseDetail.setCopeClass(departAccountDTO.getOrderClassCode());
      }

      expenseDetail.setItemCode(nursingOperationFeeDTO.getItemCode());
      expenseDetail.setItemName(nursingOperationFeeDTO.getItemName());
      String babyFeeMark = StringUtils.isNotBlank(nursingOperationFeeDTO.getFzyh()) ? "1" : "0";
      expenseDetail.setBabyFeeMark(babyFeeMark);
      expenseDetail.setSourceProgram("诊疗费录入");
   }

   private void nursingOperationFeeToPatFee(NursingOperationFeeDTO nursingOperationFeeDTO, PatFee patFee, DepartAccountDTO departAccountDTO) throws Exception {
      Medicalinformation medicalinformation = this.medicalinfoService.queryByAdmissionNo(departAccountDTO.getZyh());
      patFee.setHospitalCode(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode());
      patFee.setCaseNo(departAccountDTO.getBah());
      patFee.setAdmissionNo(departAccountDTO.getZyh());
      patFee.setHospitalizedCount(departAccountDTO.getZycs());
      patFee.setBabyNo(nursingOperationFeeDTO.getFzyh());
      patFee.setWardNo(departAccountDTO.getPatientDepCode());
      patFee.setWardName(departAccountDTO.getPatientDepName());
      patFee.setDepartmentNo(departAccountDTO.getPatientDepCode());
      patFee.setDepartmentName(departAccountDTO.getPatientDepName());
      patFee.setMedicalGroupV(medicalinformation.getMedicalGroup());
      patFee.setVisitingStaffCode(departAccountDTO.getZyysbm());
      patFee.setVisitingStaffNo(departAccountDTO.getZyysbm());
      patFee.setVisitingStaffName(departAccountDTO.getZyysmc());
      patFee.setPhysicianCode(nursingOperationFeeDTO.getKdysbm());
      patFee.setPhysicianNo(nursingOperationFeeDTO.getKdysbm());
      patFee.setPhysicianName(nursingOperationFeeDTO.getKdys());
      patFee.setPhysicianDptNo(nursingOperationFeeDTO.getKdksbm());
      patFee.setPhysicianDptName(nursingOperationFeeDTO.getKdks());
      patFee.setExecutorCode(nursingOperationFeeDTO.getZxrbm());
      patFee.setExecutorNo(nursingOperationFeeDTO.getZxrbm());
      patFee.setExecutorName(nursingOperationFeeDTO.getZxr());
      patFee.setExecutorDptNo(nursingOperationFeeDTO.getZxksbm());
      patFee.setExecutorDptName(nursingOperationFeeDTO.getZxks());
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      patFee.setAccountingDepartmentNo(sysUser.getDept().getDeptCode());
      patFee.setAccountingDepartmentName(sysUser.getDept().getDeptName());
      patFee.setSourceProgram("诊疗费录入");
      patFee.setOperatorCode(sysUser.getUserName());
      patFee.setOperator(sysUser.getUserName());
      patFee.setOperatorName(sysUser.getNickName());
   }

   private BigDecimal otherPerformToPatFeeExpenseDetail(List inpatientOrderPerformDTOList, PatFee patFee, List expenseDetailList, BigDecimal totalAmount) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String prescription = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 4);
      String billsNoPre = prescription;
      Integer billNoNum = 0;

      for(int i = 0; i < inpatientOrderPerformDTOList.size(); ++i) {
         this.performToPatFee((InpatientOrderPerformDTO)inpatientOrderPerformDTOList.get(i), patFee);
         patFee.setPrescription(prescription);
         Date filingDate = this.commonService.getDbDate();
         patFee.setFilingDate(filingDate);
         InpatientOrderPerformDTO inpatientOrderPerformDTO = (InpatientOrderPerformDTO)inpatientOrderPerformDTOList.get(i);
         Integer subjectFlag = null;
         if (Integer.parseInt(inpatientOrderPerformDTO.getOrderItemType()) == 2 && "05".equals(inpatientOrderPerformDTO.getOrderClassCode())) {
            Integer orderStatus = this.orderStatusMapper.getOrderStatus(inpatientOrderPerformDTO.getOrderNo(), inpatientOrderPerformDTO.getOrderSortNumber());
            if (orderStatus == 2) {
               subjectFlag = 0;
            } else {
               subjectFlag = 1;
            }
         }

         for(PriceYyJzXmVo objectMap : this.priceYyService.getByCpNo(inpatientOrderPerformDTO.getChargeNo(), subjectFlag)) {
            ExpenseDetail expenseDetail = new ExpenseDetail();
            this.performToExpenseDetail(inpatientOrderPerformDTO, expenseDetail, objectMap);
            expenseDetail.setPayMark("-1");
            expenseDetail.setPrescription(prescription);
            String billNo = billsNoPre + CommonUtils.getTenNumberStr(billNoNum);
            expenseDetail.setBillsNo(billNo);
            expenseDetail.setFilingDate(filingDate);
            expenseDetailList.add(expenseDetail);
            BigDecimal total = BigDecimal.ZERO;
            if (expenseDetail.getTotal() != null) {
               total = expenseDetail.getTotal();
            }

            totalAmount = totalAmount.add(total);
            billNoNum = billNoNum + 1;
         }
      }

      return totalAmount;
   }

   private void performToExpenseDetail(InpatientOrderPerformDTO inpatientOrderPerformDTO, ExpenseDetail expenseDetail, PriceYyJzXmVo priceYyJzXmMap) throws Exception {
      expenseDetail.setHospitalCode(inpatientOrderPerformDTO.getHospitalCode());
      expenseDetail.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
      expenseDetail.setHospitalizedCount(inpatientOrderPerformDTO.getHospitalizedCount());
      String projectNo = priceYyJzXmMap.getAccountUpper();
      String projectName = priceYyJzXmMap.getHosUpperName();
      String threeLevelAccounting = priceYyJzXmMap.getCode();
      String threeLevelName = priceYyJzXmMap.getName();
      expenseDetail.setThreeLevelAccounting(threeLevelAccounting);
      expenseDetail.setThreeLevelName(threeLevelName);
      expenseDetail.setStandardCode(priceYyJzXmMap.getStandardCode());
      expenseDetail.setStandardName(priceYyJzXmMap.getStandardName());
      expenseDetail.setChargeCode(priceYyJzXmMap.getItemNo());
      expenseDetail.setChargeNo(priceYyJzXmMap.getItemNoSuffix());
      expenseDetail.setChargeName(priceYyJzXmMap.getItemName());
      expenseDetail.setPrice(priceYyJzXmMap.getPrice());
      expenseDetail.setStandard(priceYyJzXmMap.getStandard());
      expenseDetail.setUnit(priceYyJzXmMap.getUnit());
      BigDecimal dose = inpatientOrderPerformDTO.getOrderDose().multiply(priceYyJzXmMap.getOrderDose());
      BigDecimal total = expenseDetail.getPrice().multiply(dose).setScale(4, 2);
      expenseDetail.setTotal(total);
      expenseDetail.setProjectNo(projectNo);
      expenseDetail.setProjectName(projectName);
      expenseDetail.setDose(dose);
      expenseDetail.setCopeNo(inpatientOrderPerformDTO.getOrderNo());
      expenseDetail.setCopeSortNumber(inpatientOrderPerformDTO.getOrderSortNumber());
      expenseDetail.setItemSortNumber(inpatientOrderPerformDTO.getOrderGroupSortNumber());
      expenseDetail.setCopeGroup(inpatientOrderPerformDTO.getOrderGroupNo());
      expenseDetail.setCopeType(inpatientOrderPerformDTO.getOrderType());
      expenseDetail.setCopeClass(inpatientOrderPerformDTO.getOrderClassCode());
      expenseDetail.setItemCode(inpatientOrderPerformDTO.getCpNo());
      expenseDetail.setItemName(inpatientOrderPerformDTO.getCpName());
      expenseDetail.setPerformListNo(inpatientOrderPerformDTO.getPerformListNo());
      expenseDetail.setPerformListSortNumber(inpatientOrderPerformDTO.getPerformListSortNumber() + "");
      String babyFeeMark = StringUtils.isNotBlank(inpatientOrderPerformDTO.getBabyAdmissionNo()) ? "1" : "0";
      expenseDetail.setBabyFeeMark(babyFeeMark);
      expenseDetail.setSourceProgram("java");
   }

   private void performToPatFee(InpatientOrderPerformDTO inpatientOrderPerformDTO, PatFee patFee) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Medicalinformation medicalinformation = this.medicalinfoService.queryByAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
      List<SysDept> deptList = this.sysDeptService.queryByDepCodeList(Arrays.asList(inpatientOrderPerformDTO.getPatientDepCode(), inpatientOrderPerformDTO.getOrderDoctorDepCode()));
      Map<String, SysDept> deptMap = (Map)deptList.stream().collect(Collectors.toMap(SysDept::getDeptCode, Function.identity()));
      BsStaff orderDoctor = this.sysUserService.queryByStaffNo(inpatientOrderPerformDTO.getOrderDoctorNo());
      patFee.setHospitalCode(inpatientOrderPerformDTO.getHospitalCode());
      patFee.setCaseNo(inpatientOrderPerformDTO.getCaseNo());
      patFee.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
      patFee.setHospitalizedCount(inpatientOrderPerformDTO.getHospitalizedCount());
      patFee.setBabyNo(inpatientOrderPerformDTO.getBabyAdmissionNo());
      patFee.setWardNo(((SysDept)deptMap.get(inpatientOrderPerformDTO.getPatientDepCode())).getDeptCode());
      patFee.setWardName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getPatientDepCode())).getDeptName());
      patFee.setDepartmentNo(inpatientOrderPerformDTO.getPatientDepCode());
      patFee.setDepartmentName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getPatientDepCode())).getDeptName());
      patFee.setMedicalGroupV(medicalinformation.getMedicalGroup());
      patFee.setVisitingStaffCode(medicalinformation.getResidentCode());
      patFee.setVisitingStaffNo(medicalinformation.getResidentNo());
      patFee.setVisitingStaffName(medicalinformation.getResidentName());
      patFee.setPhysicianCode(inpatientOrderPerformDTO.getOrderDoctorCode());
      patFee.setPhysicianNo(inpatientOrderPerformDTO.getOrderDoctorNo());
      patFee.setPhysicianName(orderDoctor.getStaffName());
      patFee.setPhysicianDptNo(inpatientOrderPerformDTO.getOrderDoctorDepCode());
      patFee.setPhysicianDptName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getOrderDoctorDepCode())).getDeptName());
      patFee.setExecutorCode(user.getUserName());
      patFee.setExecutorNo(user.getUserName());
      patFee.setExecutorName(user.getNickName());
      String executorDptNo = this.orderDetailMapper.queryExecutorDptNo(inpatientOrderPerformDTO.getOrderNo(), inpatientOrderPerformDTO.getOrderGroupNo(), inpatientOrderPerformDTO.getOrderSortNumber());
      patFee.setExecutorDptNo(executorDptNo);
      String executorDptName = "";
      if (StringUtils.isNotBlank(executorDptNo)) {
         SysDept dept = this.sysDeptService.getOneByCode(executorDptNo);
         if (dept != null) {
            executorDptName = dept.getDeptName();
         }
      }

      patFee.setExecutorDptName(executorDptName);
      patFee.setAccountingDepartmentNo(user.getDept().getDeptCode());
      patFee.setAccountingDepartmentName(user.getDept().getDeptName());
      patFee.setSourceProgram("java");
      patFee.setOperatorCode(user.getUserName());
      patFee.setOperator(user.getUserName());
      patFee.setOperatorName(user.getNickName());
   }

   public List getPrintDate(Map param) {
      String type = (String)param.get("type");
      List<Map<String, Object>> list = Lists.newArrayList();
      List var4;
      if ("0".equals(type)) {
         var4 = this.patFeeMapper.getTempFyxxYz(param);
      } else if ("2".equals(type)) {
         var4 = this.patFeeMapper.selectListPerformNo(param);
      } else {
         var4 = this.patFeeMapper.getTempFyxxZlf(param);
      }

      return var4;
   }

   public List selectOperRoomFeeDetailList(FeeDetailReq req) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      req.setAccountingDepartmentNo(sysUser.getDept().getDeptCode());
      List<ExpenseDetailVo> listNew = new ArrayList(1);
      BigDecimal amount = BigDecimal.ZERO;
      if (StringUtils.isNotBlank(req.getQueryType()) && req.getQueryType().equals("1")) {
         List<ExpenseDetailVo> list = this.patFeeMapper.selectOperRoomFeeDetailList(req);
         if (StringUtils.isNotBlank(req.getGroupType()) && req.getGroupType().equals("1")) {
            amount = this.getGroupDepart(list, listNew);
         } else if (StringUtils.isNotBlank(req.getGroupType()) && req.getGroupType().equals("2")) {
            amount = this.getGroupDoc(list, listNew);
         } else {
            listNew = list;
            if (CollectionUtils.isNotEmpty(list)) {
               for(int i = 0; i < list.size(); ++i) {
                  ExpenseDetailVo vo = (ExpenseDetailVo)list.get(i);
                  amount = amount.add(vo.getTotal());
               }
            }
         }
      } else if (StringUtils.isNotBlank(req.getQueryType()) && req.getQueryType().equals("2")) {
         List<ExpenseDetailVo> list = this.patFeeMapper.selectOperRoomFeeDetailGroupProject(req);
         if (StringUtils.isBlank(req.getGroupType())) {
            amount = this.getGroupProject(list, listNew);
         } else if (StringUtils.isNotBlank(req.getGroupType()) && req.getGroupType().equals("1")) {
            amount = this.getGroupDepart(list, listNew);
         } else if (StringUtils.isNotBlank(req.getGroupType()) && req.getGroupType().equals("2")) {
            amount = this.getGroupDoc(list, listNew);
         }
      }

      req.setAmount(amount);
      return listNew;
   }

   private BigDecimal getGroupProject(List list, List listNew) {
      BigDecimal amount = BigDecimal.ZERO;
      if (CollectionUtils.isNotEmpty(list)) {
         String projectCurr = null;
         BigDecimal total = BigDecimal.ZERO;

         for(int i = 0; i < list.size(); ++i) {
            ExpenseDetailVo vo = (ExpenseDetailVo)list.get(i);
            if (StringUtils.isNull(projectCurr)) {
               projectCurr = vo.getThirdName();
            } else if (StringUtils.isNotBlank(projectCurr) && !projectCurr.equals(vo.getThirdName())) {
               ExpenseDetailVo voNew = new ExpenseDetailVo();
               voNew.setChargeName(projectCurr + "    小计");
               voNew.setTotal(total);
               listNew.add(voNew);
               projectCurr = vo.getThirdName();
               total = BigDecimal.ZERO;
            }

            total = total.add(vo.getTotal());
            listNew.add(vo);
            if (i == list.size() - 1) {
               ExpenseDetailVo voNew = new ExpenseDetailVo();
               voNew.setChargeName(projectCurr + "    小计");
               voNew.setTotal(total);
               listNew.add(voNew);
            }

            amount = amount.add(vo.getTotal());
         }
      }

      return amount;
   }

   private BigDecimal getGroupDepart(List list, List listNew) {
      BigDecimal amount = BigDecimal.ZERO;
      if (CollectionUtils.isNotEmpty(list)) {
         String departCurr = null;
         BigDecimal total = BigDecimal.ZERO;

         for(int i = 0; i < list.size(); ++i) {
            ExpenseDetailVo vo = (ExpenseDetailVo)list.get(i);
            if (StringUtils.isNull(departCurr)) {
               departCurr = vo.getDepartmentName();
            } else if (StringUtils.isNotBlank(departCurr) && !departCurr.equals(vo.getDepartmentName())) {
               ExpenseDetailVo voNew = new ExpenseDetailVo();
               voNew.setChargeName(departCurr + "小计");
               voNew.setTotal(total);
               listNew.add(voNew);
               departCurr = vo.getDepartmentName();
               total = BigDecimal.ZERO;
            }

            total = total.add(vo.getTotal());
            listNew.add(vo);
            if (i == list.size() - 1) {
               ExpenseDetailVo voNew = new ExpenseDetailVo();
               voNew.setChargeName(departCurr + "小计");
               voNew.setTotal(total);
               listNew.add(voNew);
            }

            amount = amount.add(vo.getTotal());
         }
      }

      return amount;
   }

   private BigDecimal getGroupDoc(List list, List listNew) {
      BigDecimal amount = BigDecimal.ZERO;
      if (CollectionUtils.isNotEmpty(list)) {
         String docCurr = null;
         BigDecimal total = BigDecimal.ZERO;

         for(int i = 0; i < list.size(); ++i) {
            ExpenseDetailVo vo = (ExpenseDetailVo)list.get(i);
            if (StringUtils.isNull(docCurr)) {
               docCurr = vo.getPhysicianName();
            } else if (StringUtils.isNotBlank(docCurr) && !docCurr.equals(vo.getPhysicianName())) {
               ExpenseDetailVo voNew = new ExpenseDetailVo();
               voNew.setChargeName(docCurr + "    小计");
               voNew.setTotal(total);
               listNew.add(voNew);
               docCurr = vo.getPhysicianName();
               total = BigDecimal.ZERO;
            }

            total = total.add(vo.getTotal());
            listNew.add(vo);
            if (i == list.size() - 1) {
               ExpenseDetailVo voNew = new ExpenseDetailVo();
               voNew.setChargeName(docCurr + "    小计");
               voNew.setTotal(total);
               listNew.add(voNew);
            }

            amount = amount.add(vo.getTotal());
         }
      }

      return amount;
   }

   public PatFee selectFeeAppByPrescription(String prescription) throws Exception {
      return StringUtils.isNotBlank(prescription) ? this.patFeeMapper.selectFeeAppByPrescription(prescription) : null;
   }

   public void delFeeAppByPrescription(List prescriptions) throws Exception {
      if (CollectionUtils.isNotEmpty(prescriptions)) {
         this.patFeeMapper.delFeeAppByPrescription(prescriptions);
      }

   }

   private BigDecimal crossDepApplyToPatFeeExpenseDetail(List crossDepApplyList, PatFee patFee, List expenseDetailList, BigDecimal totalAmount) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      this.crossDepApplyToPatFee((DepartAccount)crossDepApplyList.get(0), patFee);
      String prescription = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 4);
      String billsNoPre = prescription;
      patFee.setPrescription(prescription);
      Date filingDate = this.commonService.getDbDate();
      patFee.setFilingDate(filingDate);
      List<String> chargeCodeList = (List)crossDepApplyList.stream().map((t) -> t.getChargeCode()).collect(Collectors.toList());
      List<PriceYy> priceYyList = this.priceYyService.queryByItemCodeList(chargeCodeList);
      Map<String, PriceYy> priceYyMap = (Map)priceYyList.stream().collect(Collectors.toMap(PriceYy::getItemNo, Function.identity()));

      for(int i = 0; i < crossDepApplyList.size(); ++i) {
         DepartAccount crossDepApply = (DepartAccount)crossDepApplyList.get(i);
         ExpenseDetail expenseDetail = new ExpenseDetail();
         this.crossDepApplyToExpenseDetail(crossDepApply, expenseDetail, priceYyMap);
         expenseDetail.setPayMark("-1");
         expenseDetail.setPrescription(prescription);
         String billNo = billsNoPre + CommonUtils.getTenNumberStr(i);
         expenseDetail.setBillsNo(billNo);
         expenseDetail.setFilingDate(filingDate);
         expenseDetailList.add(expenseDetail);
         totalAmount = totalAmount.add(expenseDetail.getTotal());
         crossDepApply.setOperatorNewCode(user.getUserName());
         crossDepApply.setOperatorNewNo(user.getUserName());
         crossDepApply.setOperatorNewName(user.getNickName());
         crossDepApply.setOperatorNewDate(filingDate);
         crossDepApply.setPrescription(billNo);
         crossDepApply.setMark("1");
      }

      return totalAmount;
   }

   private void crossDepApplyToExpenseDetail(DepartAccount crossDepApply, ExpenseDetail expenseDetail, Map priceYyMap) throws Exception {
      PriceYy priceYy = (PriceYy)priceYyMap.get(crossDepApply.getChargeCode());
      TmBsAccountThirdProjectVo thirdProjectVo = this.tmBsAccountThirdService.selectThirdAndProjectByCode(priceYy.getHosUpper());
      String projectNo = "";
      String projectName = "";
      String threeLevelAccounting = "";
      String threeLevelName = "";
      if (thirdProjectVo != null) {
         projectNo = thirdProjectVo.getProjectNo();
         projectName = thirdProjectVo.getProjectName();
         threeLevelAccounting = thirdProjectVo.getCode();
         threeLevelName = thirdProjectVo.getName();
      }

      expenseDetail.setHospitalCode(crossDepApply.getHospitalCode());
      expenseDetail.setAdmissionNo(crossDepApply.getAdmissionNo());
      expenseDetail.setHospitalizedCount(crossDepApply.getHospitalizedCount());
      expenseDetail.setProjectNo(projectNo);
      expenseDetail.setProjectName(projectName);
      expenseDetail.setThreeLevelAccounting(threeLevelAccounting);
      expenseDetail.setThreeLevelName(threeLevelName);
      expenseDetail.setStandardCode(priceYy.getStandardCode());
      expenseDetail.setStandardName(priceYy.getStandardName());
      expenseDetail.setChargeCode(crossDepApply.getChargeCode());
      expenseDetail.setChargeNo(crossDepApply.getChargeNo());
      expenseDetail.setChargeName(crossDepApply.getChargeName());
      expenseDetail.setStandard(crossDepApply.getStandard());
      expenseDetail.setUnit(crossDepApply.getUnit());
      expenseDetail.setPrice(priceYy.getPrice());
      expenseDetail.setDose(crossDepApply.getDose());
      BigDecimal total = priceYy.getPrice().multiply(crossDepApply.getDose()).setScale(4, 2);
      expenseDetail.setTotal(total);
      String babyFeeMark = "0";
      expenseDetail.setBabyFeeMark(babyFeeMark);
      expenseDetail.setSourceProgram("java");
   }

   private void crossDepApplyToPatFee(DepartAccount crossDepApply, PatFee patFee) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Medicalinformation medicalinformation = this.medicalinfoService.queryByAdmissionNo(crossDepApply.getAdmissionNo());
      SysDept dept = this.sysDeptService.getOneByCode(medicalinformation.getDepartmentNo());
      patFee.setHospitalCode(crossDepApply.getHospitalCode());
      patFee.setCaseNo(crossDepApply.getCaseNo());
      patFee.setAdmissionNo(crossDepApply.getAdmissionNo());
      patFee.setHospitalizedCount(crossDepApply.getHospitalizedCount());
      patFee.setWardNo(crossDepApply.getWardNo());
      patFee.setWardName(crossDepApply.getWardName());
      patFee.setDepartmentNo(medicalinformation.getDepartmentNo());
      patFee.setDepartmentName(dept.getDeptName());
      patFee.setMedicalGroupV(medicalinformation.getMedicalGroup());
      patFee.setVisitingStaffCode(crossDepApply.getVisitingStaffCode());
      patFee.setVisitingStaffNo(crossDepApply.getVisitingStaffNo());
      patFee.setVisitingStaffName(crossDepApply.getVisitingStaffName());
      patFee.setPhysicianCode(crossDepApply.getPhysicianNo());
      patFee.setPhysicianNo(crossDepApply.getPhysicianNo());
      patFee.setPhysicianName(crossDepApply.getPhysicianName());
      patFee.setPhysicianDptNo(crossDepApply.getPhysicianDptNo());
      patFee.setPhysicianDptName(crossDepApply.getPhysicianDptName());
      patFee.setExecutorCode(crossDepApply.getExecutorCode());
      patFee.setExecutorNo(crossDepApply.getExecutorNo());
      patFee.setExecutorName(crossDepApply.getExecutorName());
      patFee.setExecutorDptNo(crossDepApply.getExecutorDptNo());
      patFee.setExecutorDptName(crossDepApply.getExecutorDptName());
      patFee.setAccountingDepartmentNo(user.getDept().getDeptCode());
      patFee.setAccountingDepartmentName(user.getDept().getDeptName());
      patFee.setSourceProgram("java");
      patFee.setOperatorCode(user.getUserName());
      patFee.setOperator(user.getUserName());
      patFee.setOperatorName(user.getNickName());
      patFee.setBabyNo("");
   }

   private BigDecimal drugPerformToPatFeeExpenseDetail(List inpatientOrderPerformDTOList, PatFee patFee, List expenseDetailList, BigDecimal totalAmount) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      InpatientOrderPerformDTO inpatientOrderPerformDTO = (InpatientOrderPerformDTO)inpatientOrderPerformDTOList.get(0);
      Medicalinformation medicalinformation = this.medicalinfoService.queryByAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
      String crbz = this.commonService.getCrbz(medicalinformation);
      String orderUsageWayNmae = inpatientOrderPerformDTO.getOrderUsageWayNmae() == null ? "0" : inpatientOrderPerformDTO.getOrderUsageWayNmae().trim();
      if (StringUtils.isBlank(orderUsageWayNmae)) {
         return new BigDecimal(-1);
      } else {
         InpatientOrderUsageFee inpatientOrderUsageFee = this.inpatientOrderUsageFeeService.queryByUsageFirstFlagWardNo(orderUsageWayNmae, inpatientOrderPerformDTO.getFirstBottleFlag() + "", user.getDept().getDeptCode(), crbz);
         if (inpatientOrderUsageFee == null) {
            return new BigDecimal(-1);
         } else {
            this.drugPerformToPatFee(inpatientOrderPerformDTO, patFee, medicalinformation);
            String prescription = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 4);
            patFee.setPrescription(prescription);
            Date filingDate = this.commonService.getDbDate();
            patFee.setFilingDate(filingDate);
            ExpenseDetail expenseDetail = new ExpenseDetail();
            inpatientOrderPerformDTO.getOrderUsageWay();
            this.drugPerformToExpenseDetail(inpatientOrderPerformDTO, expenseDetail, medicalinformation, inpatientOrderUsageFee);
            expenseDetail.setPayMark("-1");
            expenseDetail.setPrescription(prescription);
            String billNo = prescription + CommonUtils.getTenNumberStr(0);
            expenseDetail.setBillsNo(billNo);
            expenseDetail.setFilingDate(filingDate);
            expenseDetailList.add(expenseDetail);
            totalAmount = totalAmount.add(expenseDetail.getTotal());
            return totalAmount;
         }
      }
   }

   private void drugPerformToExpenseDetail(InpatientOrderPerformDTO inpatientOrderPerformDTO, ExpenseDetail expenseDetail, Medicalinformation medicalinformation, InpatientOrderUsageFee inpatientOrderUsageFee) throws Exception {
      expenseDetail.setHospitalCode(inpatientOrderPerformDTO.getHospitalCode());
      expenseDetail.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
      expenseDetail.setHospitalizedCount(inpatientOrderPerformDTO.getHospitalizedCount());
      Map<String, String> priceYyParamMap = new HashMap(1);
      priceYyParamMap.put("item_no", inpatientOrderUsageFee.getItemNo());
      PriceYyJzXmVo priceYyJzXmMap = this.priceYyService.getByCode(priceYyParamMap);
      String projectNo = priceYyJzXmMap.getAccountUpper();
      String projectName = priceYyJzXmMap.getHosUpperName();
      String threeLevelAccounting = priceYyJzXmMap.getCode();
      String threeLevelName = priceYyJzXmMap.getName();
      expenseDetail.setProjectNo(projectNo);
      expenseDetail.setProjectName(projectName);
      expenseDetail.setThreeLevelAccounting(threeLevelAccounting);
      expenseDetail.setThreeLevelName(threeLevelName);
      expenseDetail.setStandardCode(priceYyJzXmMap.getStandardCode());
      expenseDetail.setStandardName(priceYyJzXmMap.getStandardName());
      expenseDetail.setChargeNo(priceYyJzXmMap.getItemNoSuffix());
      expenseDetail.setChargeCode(priceYyJzXmMap.getItemNo());
      expenseDetail.setChargeName(priceYyJzXmMap.getItemName());
      expenseDetail.setStandard(priceYyJzXmMap.getStandard());
      expenseDetail.setUnit(priceYyJzXmMap.getUnit());
      expenseDetail.setPrice(priceYyJzXmMap.getPrice());
      expenseDetail.setDose(new BigDecimal("1"));
      BigDecimal total = priceYyJzXmMap.getPrice().multiply(expenseDetail.getDose()).setScale(4, 2);
      expenseDetail.setTotal(total);
      expenseDetail.setCopeNo(inpatientOrderPerformDTO.getOrderNo());
      expenseDetail.setCopeSortNumber(inpatientOrderPerformDTO.getOrderSortNumber());
      expenseDetail.setItemSortNumber(inpatientOrderPerformDTO.getOrderGroupSortNumber());
      expenseDetail.setCopeGroup(inpatientOrderPerformDTO.getOrderGroupNo());
      expenseDetail.setCopeType(inpatientOrderPerformDTO.getOrderType());
      expenseDetail.setCopeClass(inpatientOrderPerformDTO.getOrderClassCode());
      expenseDetail.setItemCode(inpatientOrderPerformDTO.getCpNo());
      expenseDetail.setItemName(inpatientOrderPerformDTO.getCpName());
      expenseDetail.setPerformListNo(inpatientOrderPerformDTO.getPerformListNo());
      expenseDetail.setPerformListSortNumber(inpatientOrderPerformDTO.getPerformListSortNumber() + "");
      String babyFeeMark = StringUtils.isNotBlank(inpatientOrderPerformDTO.getBabyAdmissionNo()) ? "1" : "0";
      expenseDetail.setBabyFeeMark(babyFeeMark);
      expenseDetail.setSourceProgram("java");
   }

   private void drugPerformToPatFee(InpatientOrderPerformDTO inpatientOrderPerformDTO, PatFee patFee, Medicalinformation medicalinformation) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<SysDept> deptList = this.sysDeptService.queryByDepCodeList(new ArrayList(Arrays.asList(inpatientOrderPerformDTO.getPatientDepCode(), inpatientOrderPerformDTO.getOrderDoctorDepCode())));
      Map<String, SysDept> deptMap = (Map)deptList.stream().collect(Collectors.toMap(SysDept::getDeptCode, Function.identity()));
      BsStaff orderDoctor = this.sysUserService.queryByStaffNo(inpatientOrderPerformDTO.getOrderDoctorNo());
      patFee.setHospitalCode(inpatientOrderPerformDTO.getHospitalCode());
      patFee.setCaseNo(inpatientOrderPerformDTO.getCaseNo());
      patFee.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
      patFee.setHospitalizedCount(inpatientOrderPerformDTO.getHospitalizedCount());
      patFee.setBabyNo(inpatientOrderPerformDTO.getBabyAdmissionNo());
      patFee.setWardNo(inpatientOrderPerformDTO.getPatientDepCode());
      patFee.setWardName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getPatientDepCode())).getDeptName());
      patFee.setDepartmentNo(inpatientOrderPerformDTO.getPatientDepCode());
      patFee.setDepartmentName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getPatientDepCode())).getDeptName());
      patFee.setMedicalGroupV(medicalinformation.getMedicalGroup());
      patFee.setVisitingStaffCode(medicalinformation.getResidentCode());
      patFee.setVisitingStaffNo(medicalinformation.getResidentNo());
      patFee.setVisitingStaffName(medicalinformation.getResidentName());
      patFee.setPhysicianCode(inpatientOrderPerformDTO.getOrderDoctorCode());
      patFee.setPhysicianNo(inpatientOrderPerformDTO.getOrderDoctorNo());
      patFee.setPhysicianName(orderDoctor.getStaffName());
      patFee.setPhysicianDptNo(inpatientOrderPerformDTO.getOrderDoctorDepCode());
      patFee.setPhysicianDptName(((SysDept)deptMap.get(inpatientOrderPerformDTO.getOrderDoctorDepCode())).getDeptName());
      patFee.setExecutorCode(user.getUserName());
      patFee.setExecutorNo(user.getUserName());
      patFee.setExecutorName(user.getNickName());
      patFee.setExecutorDptNo(user.getDept().getDeptCode());
      patFee.setExecutorDptName(user.getDept().getDeptName());
      patFee.setAccountingDepartmentNo(user.getDept().getDeptCode());
      patFee.setAccountingDepartmentName(user.getDept().getDeptName());
      patFee.setSourceProgram("java");
      patFee.setOperatorCode(user.getUserName());
      patFee.setOperator(user.getUserName());
      patFee.setOperatorName(user.getNickName());
   }

   @DataSource(DataSourceType.hisDrugStock)
   public List searchdrugs2Resp(String keyword) {
      return this.patFeeMapper.searchdrugs2Resp(keyword);
   }

   public List searchPriceYy(String keyword) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Map<String, String> param = new HashMap();
      param.put("dep_code", sysUser.getDept().getDeptCode());
      param.put("xmmc", keyword);
      return this.patFeeMapper.searchPriceYy(param);
   }

   public List exportFeeDetailList(FeeDetailReq req) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      req.setAccountingDepartmentNo(sysUser.getDept().getDeptCode());
      List<ExpenseDetailVo> listNew = new ArrayList(1);
      if (StringUtils.isNotBlank(req.getQueryType()) && req.getQueryType().equals("1")) {
         listNew = this.patFeeMapper.selectOperRoomFeeDetailList(req);
      } else if (StringUtils.isNotBlank(req.getQueryType()) && req.getQueryType().equals("2")) {
         listNew = this.patFeeMapper.selectOperRoomFeeDetailGroupProject(req);
      }

      return listNew;
   }
}
