package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class InpatientTodayOrderPerformDTO {
   private String bedId;
   private Integer bedOrder;
   private String caseNo;
   private String patientName;
   private String admissionNo;
   private String babyAdmissionNo;
   private Integer hospitalizedCount;
   private String performListNo;
   private Integer performListSortNumber;
   private String orderNo;
   private String orderType;
   private String orderSortNumber;
   private String orderClassCode;
   private String orderGroupNo;
   private String performListStatus;
   private String chargeNo;
   private String chargeName;
   private String standard;
   private String unit;
   private String orderDose;
   private BigDecimal orderTotalDose;
   private String orderUsageWay;
   private String sigName;
   private String orderFreq;
   private String freqName;
   private String cpNo;
   private String cpName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date planUsageTime;
   private String orderAuditDoCode;
   private String orderAuditDoName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderAuditTime;
   private String orderExecuteDoCode;
   private String orderExecuteDoName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderExecuteTime;
   private String executorDptNo;
   private String executorDptName;
   private String orderDoctorName;
   private String orderDoctorCode;
   private String orderDoctorDeptCode;
   private String orderDoctorDeptName;
   private String orderItemFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operationTime;
   private String doctorInstructions;
   private String orderGroupStr;
   private String usageUnit;
   private BigDecimal orderActualUsage;
   private BigDecimal orderTotal;
   private BigDecimal price;
   private String masterOrder;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date skinTestResultsTime;
   private String skinTestResults;
   private String skinTestResultsLot;
   private Boolean checked;
   private String orderGroupSortNumber;
   private String reasonMessage;
   private String orderExecuteTimeStr;

   public InpatientTodayOrderPerformDTO() {
      this.checked = Boolean.FALSE;
   }

   public Integer getBedOrder() {
      return this.bedOrder;
   }

   public void setBedOrder(Integer bedOrder) {
      this.bedOrder = bedOrder;
   }

   public String getOrderExecuteTimeStr() {
      return this.orderExecuteTimeStr;
   }

   public void setOrderExecuteTimeStr(String orderExecuteTimeStr) {
      this.orderExecuteTimeStr = orderExecuteTimeStr;
   }

   public String getReasonMessage() {
      return this.reasonMessage;
   }

   public void setReasonMessage(String reasonMessage) {
      this.reasonMessage = reasonMessage;
   }

   public String getMasterOrder() {
      return this.masterOrder;
   }

   public void setMasterOrder(String masterOrder) {
      this.masterOrder = masterOrder;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public Boolean getChecked() {
      return this.checked;
   }

   public void setChecked(Boolean checked) {
      this.checked = checked;
   }

   public Date getSkinTestResultsTime() {
      return this.skinTestResultsTime;
   }

   public void setSkinTestResultsTime(Date skinTestResultsTime) {
      this.skinTestResultsTime = skinTestResultsTime;
   }

   public String getSkinTestResults() {
      return this.skinTestResults;
   }

   public void setSkinTestResults(String skinTestResults) {
      this.skinTestResults = skinTestResults;
   }

   public String getSkinTestResultsLot() {
      return this.skinTestResultsLot;
   }

   public void setSkinTestResultsLot(String skinTestResultsLot) {
      this.skinTestResultsLot = skinTestResultsLot;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public BigDecimal getOrderActualUsage() {
      return this.orderActualUsage;
   }

   public void setOrderActualUsage(BigDecimal orderActualUsage) {
      this.orderActualUsage = orderActualUsage;
   }

   public String getOrderItemFlag() {
      return this.orderItemFlag;
   }

   public void setOrderItemFlag(String orderItemFlag) {
      this.orderItemFlag = orderItemFlag;
   }

   public String getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(String orderDose) {
      this.orderDose = orderDose;
   }

   public BigDecimal getOrderTotalDose() {
      return this.orderTotalDose;
   }

   public void setOrderTotalDose(BigDecimal orderTotalDose) {
      this.orderTotalDose = orderTotalDose;
   }

   public String getBedId() {
      return this.bedId;
   }

   public void setBedId(String bedId) {
      this.bedId = bedId;
   }

   public String getOrderGroupStr() {
      return this.orderGroupStr;
   }

   public void setOrderGroupStr(String orderGroupStr) {
      this.orderGroupStr = orderGroupStr;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getPerformListStatus() {
      return this.performListStatus;
   }

   public void setPerformListStatus(String performListStatus) {
      this.performListStatus = performListStatus;
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getOrderUsageWay() {
      return this.orderUsageWay;
   }

   public void setOrderUsageWay(String orderUsageWay) {
      this.orderUsageWay = orderUsageWay;
   }

   public String getSigName() {
      return this.sigName;
   }

   public void setSigName(String sigName) {
      this.sigName = sigName;
   }

   public String getOrderFreq() {
      return this.orderFreq;
   }

   public void setOrderFreq(String orderFreq) {
      this.orderFreq = orderFreq;
   }

   public String getFreqName() {
      return this.freqName;
   }

   public void setFreqName(String freqName) {
      this.freqName = freqName;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getOrderAuditDoCode() {
      return this.orderAuditDoCode;
   }

   public void setOrderAuditDoCode(String orderAuditDoCode) {
      this.orderAuditDoCode = orderAuditDoCode;
   }

   public String getOrderAuditDoName() {
      return this.orderAuditDoName;
   }

   public void setOrderAuditDoName(String orderAuditDoName) {
      this.orderAuditDoName = orderAuditDoName;
   }

   public String getOrderExecuteDoCode() {
      return this.orderExecuteDoCode;
   }

   public void setOrderExecuteDoCode(String orderExecuteDoCode) {
      this.orderExecuteDoCode = orderExecuteDoCode;
   }

   public String getOrderExecuteDoName() {
      return this.orderExecuteDoName;
   }

   public void setOrderExecuteDoName(String orderExecuteDoName) {
      this.orderExecuteDoName = orderExecuteDoName;
   }

   public String getExecutorDptNo() {
      return this.executorDptNo;
   }

   public void setExecutorDptNo(String executorDptNo) {
      this.executorDptNo = executorDptNo;
   }

   public String getExecutorDptName() {
      return this.executorDptName;
   }

   public void setExecutorDptName(String executorDptName) {
      this.executorDptName = executorDptName;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
   }

   public String getOrderDoctorDeptCode() {
      return this.orderDoctorDeptCode;
   }

   public void setOrderDoctorDeptCode(String orderDoctorDeptCode) {
      this.orderDoctorDeptCode = orderDoctorDeptCode;
   }

   public String getOrderDoctorDeptName() {
      return this.orderDoctorDeptName;
   }

   public void setOrderDoctorDeptName(String orderDoctorDeptName) {
      this.orderDoctorDeptName = orderDoctorDeptName;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public Date getPlanUsageTime() {
      return this.planUsageTime;
   }

   public void setPlanUsageTime(Date planUsageTime) {
      this.planUsageTime = planUsageTime;
   }

   public Date getOrderAuditTime() {
      return this.orderAuditTime;
   }

   public void setOrderAuditTime(Date orderAuditTime) {
      this.orderAuditTime = orderAuditTime;
   }

   public Date getOrderExecuteTime() {
      return this.orderExecuteTime;
   }

   public void setOrderExecuteTime(Date orderExecuteTime) {
      this.orderExecuteTime = orderExecuteTime;
   }

   public Date getOperationTime() {
      return this.operationTime;
   }

   public void setOperationTime(Date operationTime) {
      this.operationTime = operationTime;
   }
}
