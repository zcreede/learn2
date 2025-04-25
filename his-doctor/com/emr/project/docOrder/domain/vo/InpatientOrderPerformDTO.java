package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InpatientOrderPerformDTO {
   private String bedid;
   private String patientName;
   private String orderUsageWay;
   private String orderUsageWayName;
   private String hospitalCode;
   private String performWardNo;
   private String performDepCode;
   private String caseNo;
   private String performListNo;
   private String orderNo;
   private String orderType;
   private String orderSortNumber;
   private String orderClassCode;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String orderDoctorCode;
   private String orderDoctorNo;
   private String orderDoctorWardNo;
   private String orderDoctorDepCode;
   private String orderDoctorDepName;
   private String orderDoctorName;
   private String orderUsageWayNmae;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStartTime;
   private String documentTypeNo;
   private String performListStatus;
   private String cpNo;
   private String detailperformDepName;
   private String cpName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date handleTime;
   private String handleNurseCode;
   private String handleNurseNo;
   private String handleNurseName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date performTime;
   private String performNurseCode;
   private String performNurseNo;
   private String performNurseName;
   private String outHavaDrugFlag;
   private String orderItemType;
   private String firstDoubleFlag;
   private String takeDrugMode;
   private String depCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date planUsageTime;
   private String babyAdmissionNo;
   private String firstBottleFlag;
   private String performComputerNo;
   private String performComputerIp;
   private String detailPerformDepCode;
   private String detailPerformDepName;
   private String detailPerformWardNo;
   private String pcPdaFlag;
   private Integer performListSortNumber;
   private String orderGroupNo;
   private String chargeNo;
   private String chargeName;
   private String standardCode;
   private String standardName;
   private String standard;
   private String unit;
   private String usageUnit;
   private BigDecimal orderActualUsage;
   private BigDecimal orderDose;
   private BigDecimal orderTotalDose;
   private BigDecimal price;
   private BigDecimal orderTotal;
   private String orderFreqName;
   private String orderFreq;
   private String herbalFlag;
   private BigDecimal herbalDose;
   private String drugForm;
   private String doctorInstructions;
   private String patientSelfDrugFlag;
   private String priceFlag;
   private String pharmacopoeiaNo;
   private String orderGroupSortNumber;
   private String stockNo;
   private String hygienicMaterialFlag;
   private String drugClassCode;
   private String patient_ward_no;
   private String patientDepCode;
   private String baby_name;
   private String isGreen;
   private String orderUsageFlag;
   private BigDecimal money;
   private String order_status;
   private String order_stop_time;
   private String decoctMethod;
   private Boolean checked;
   private List orderUsageFeeList;
   private String standardCd;
   private String performFirstGenFlag;
   private String orderGroupStr;

   public InpatientOrderPerformDTO() {
      this.checked = Boolean.FALSE;
   }

   public String getPerformFirstGenFlag() {
      return this.performFirstGenFlag;
   }

   public void setPerformFirstGenFlag(String performFirstGenFlag) {
      this.performFirstGenFlag = performFirstGenFlag;
   }

   public String getStandardCd() {
      return this.standardCd;
   }

   public void setStandardCd(String standardCd) {
      this.standardCd = standardCd;
   }

   public List getOrderUsageFeeList() {
      return this.orderUsageFeeList;
   }

   public void setOrderUsageFeeList(List orderUsageFeeList) {
      this.orderUsageFeeList = orderUsageFeeList;
   }

   public Boolean getChecked() {
      return this.checked;
   }

   public void setChecked(Boolean checked) {
      this.checked = checked;
   }

   public String getDetailperformDepName() {
      return this.detailperformDepName;
   }

   public void setDetailperformDepName(String detailperformDepName) {
      this.detailperformDepName = detailperformDepName;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getOrderUsageWay() {
      return this.orderUsageWay;
   }

   public void setOrderUsageWay(String orderUsageWay) {
      this.orderUsageWay = orderUsageWay;
   }

   public String getOrderUsageWayName() {
      return this.orderUsageWayName;
   }

   public void setOrderUsageWayName(String orderUsageWayName) {
      this.orderUsageWayName = orderUsageWayName;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getPerformWardNo() {
      return this.performWardNo;
   }

   public void setPerformWardNo(String performWardNo) {
      this.performWardNo = performWardNo;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
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

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getOrderUsageWayNmae() {
      return this.orderUsageWayNmae;
   }

   public void setOrderUsageWayNmae(String orderUsageWayNmae) {
      this.orderUsageWayNmae = orderUsageWayNmae;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
   }

   public String getOrderDoctorNo() {
      return this.orderDoctorNo;
   }

   public void setOrderDoctorNo(String orderDoctorNo) {
      this.orderDoctorNo = orderDoctorNo;
   }

   public String getOrderDoctorWardNo() {
      return this.orderDoctorWardNo;
   }

   public void setOrderDoctorWardNo(String orderDoctorWardNo) {
      this.orderDoctorWardNo = orderDoctorWardNo;
   }

   public String getOrderDoctorDepCode() {
      return this.orderDoctorDepCode;
   }

   public void setOrderDoctorDepCode(String orderDoctorDepCode) {
      this.orderDoctorDepCode = orderDoctorDepCode;
   }

   public String getOrderDoctorDepName() {
      return this.orderDoctorDepName;
   }

   public void setOrderDoctorDepName(String orderDoctorDepName) {
      this.orderDoctorDepName = orderDoctorDepName;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getPerformListStatus() {
      return this.performListStatus;
   }

   public void setPerformListStatus(String performListStatus) {
      this.performListStatus = performListStatus;
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

   public Date getHandleTime() {
      return this.handleTime;
   }

   public void setHandleTime(Date handleTime) {
      this.handleTime = handleTime;
   }

   public String getHandleNurseCode() {
      return this.handleNurseCode;
   }

   public void setHandleNurseCode(String handleNurseCode) {
      this.handleNurseCode = handleNurseCode;
   }

   public String getHandleNurseNo() {
      return this.handleNurseNo;
   }

   public void setHandleNurseNo(String handleNurseNo) {
      this.handleNurseNo = handleNurseNo;
   }

   public String getHandleNurseName() {
      return this.handleNurseName;
   }

   public void setHandleNurseName(String handleNurseName) {
      this.handleNurseName = handleNurseName;
   }

   public Date getPerformTime() {
      return this.performTime;
   }

   public void setPerformTime(Date performTime) {
      this.performTime = performTime;
   }

   public String getPerformNurseCode() {
      return this.performNurseCode;
   }

   public void setPerformNurseCode(String performNurseCode) {
      this.performNurseCode = performNurseCode;
   }

   public String getPerformNurseNo() {
      return this.performNurseNo;
   }

   public void setPerformNurseNo(String performNurseNo) {
      this.performNurseNo = performNurseNo;
   }

   public String getPerformNurseName() {
      return this.performNurseName;
   }

   public void setPerformNurseName(String performNurseName) {
      this.performNurseName = performNurseName;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getOrderItemType() {
      return this.orderItemType;
   }

   public void setOrderItemType(String orderItemType) {
      this.orderItemType = orderItemType;
   }

   public String getFirstDoubleFlag() {
      return this.firstDoubleFlag;
   }

   public void setFirstDoubleFlag(String firstDoubleFlag) {
      this.firstDoubleFlag = firstDoubleFlag;
   }

   public String getTakeDrugMode() {
      return this.takeDrugMode;
   }

   public void setTakeDrugMode(String takeDrugMode) {
      this.takeDrugMode = takeDrugMode;
   }

   public String getDepCode() {
      return this.depCode;
   }

   public void setDepCode(String depCode) {
      this.depCode = depCode;
   }

   public Date getPlanUsageTime() {
      return this.planUsageTime;
   }

   public void setPlanUsageTime(Date planUsageTime) {
      this.planUsageTime = planUsageTime;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public String getFirstBottleFlag() {
      return this.firstBottleFlag;
   }

   public void setFirstBottleFlag(String firstBottleFlag) {
      this.firstBottleFlag = firstBottleFlag;
   }

   public String getPerformComputerNo() {
      return this.performComputerNo;
   }

   public void setPerformComputerNo(String performComputerNo) {
      this.performComputerNo = performComputerNo;
   }

   public String getPerformComputerIp() {
      return this.performComputerIp;
   }

   public void setPerformComputerIp(String performComputerIp) {
      this.performComputerIp = performComputerIp;
   }

   public String getDetailPerformDepCode() {
      return this.detailPerformDepCode;
   }

   public void setDetailPerformDepCode(String detailPerformDepCode) {
      this.detailPerformDepCode = detailPerformDepCode;
   }

   public String getDetailPerformDepName() {
      return this.detailPerformDepName;
   }

   public void setDetailPerformDepName(String detailPerformDepName) {
      this.detailPerformDepName = detailPerformDepName;
   }

   public String getDetailPerformWardNo() {
      return this.detailPerformWardNo;
   }

   public void setDetailPerformWardNo(String detailPerformWardNo) {
      this.detailPerformWardNo = detailPerformWardNo;
   }

   public String getPcPdaFlag() {
      return this.pcPdaFlag;
   }

   public void setPcPdaFlag(String pcPdaFlag) {
      this.pcPdaFlag = pcPdaFlag;
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
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

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode;
   }

   public String getStandardName() {
      return this.standardName;
   }

   public void setStandardName(String standardName) {
      this.standardName = standardName;
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

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public BigDecimal getOrderActualUsage() {
      return this.orderActualUsage;
   }

   public String getOrderFreqName() {
      return this.orderFreqName;
   }

   public void setOrderFreqName(String orderFreqName) {
      this.orderFreqName = orderFreqName;
   }

   public String getOrderFreq() {
      return this.orderFreq;
   }

   public void setOrderFreq(String orderFreq) {
      this.orderFreq = orderFreq;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getPatientSelfDrugFlag() {
      return this.patientSelfDrugFlag;
   }

   public void setPatientSelfDrugFlag(String patientSelfDrugFlag) {
      this.patientSelfDrugFlag = patientSelfDrugFlag;
   }

   public String getPriceFlag() {
      return this.priceFlag;
   }

   public void setPriceFlag(String priceFlag) {
      this.priceFlag = priceFlag;
   }

   public String getPharmacopoeiaNo() {
      return this.pharmacopoeiaNo;
   }

   public void setPharmacopoeiaNo(String pharmacopoeiaNo) {
      this.pharmacopoeiaNo = pharmacopoeiaNo;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public String getHygienicMaterialFlag() {
      return this.hygienicMaterialFlag;
   }

   public void setHygienicMaterialFlag(String hygienicMaterialFlag) {
      this.hygienicMaterialFlag = hygienicMaterialFlag;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public String getPatient_ward_no() {
      return this.patient_ward_no;
   }

   public void setPatient_ward_no(String patient_ward_no) {
      this.patient_ward_no = patient_ward_no;
   }

   public String getPatientDepCode() {
      return this.patientDepCode;
   }

   public void setPatientDepCode(String patientDepCode) {
      this.patientDepCode = patientDepCode;
   }

   public String getBaby_name() {
      return this.baby_name;
   }

   public void setBaby_name(String baby_name) {
      this.baby_name = baby_name;
   }

   public String getIsGreen() {
      return this.isGreen;
   }

   public void setIsGreen(String isGreen) {
      this.isGreen = isGreen;
   }

   public String getOrderUsageFlag() {
      return this.orderUsageFlag;
   }

   public void setOrderUsageFlag(String orderUsageFlag) {
      this.orderUsageFlag = orderUsageFlag;
   }

   public BigDecimal getMoney() {
      return this.money;
   }

   public void setMoney(BigDecimal money) {
      this.money = money;
   }

   public String getOrder_status() {
      return this.order_status;
   }

   public void setOrder_status(String order_status) {
      this.order_status = order_status;
   }

   public String getOrder_stop_time() {
      return this.order_stop_time;
   }

   public void setOrder_stop_time(String order_stop_time) {
      this.order_stop_time = order_stop_time;
   }

   public String getDecoctMethod() {
      return this.decoctMethod;
   }

   public void setDecoctMethod(String decoctMethod) {
      this.decoctMethod = decoctMethod;
   }

   public String getOrderGroupStr() {
      return this.orderGroupStr;
   }

   public void setOrderGroupStr(String orderGroupStr) {
      this.orderGroupStr = orderGroupStr;
   }

   public void setOrderActualUsage(BigDecimal orderActualUsage) {
      this.orderActualUsage = orderActualUsage;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public BigDecimal getOrderTotalDose() {
      return this.orderTotalDose;
   }

   public void setOrderTotalDose(BigDecimal orderTotalDose) {
      this.orderTotalDose = orderTotalDose;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }
}
