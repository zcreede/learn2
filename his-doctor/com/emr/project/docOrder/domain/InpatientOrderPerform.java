package com.emr.project.docOrder.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InpatientOrderPerform implements Cloneable {
   private String hospitalCode;
   private String performWardNo;
   private String performDepCode;
   private String caseNo;
   public String admissionNo;
   private String performListNo;
   private String orderNo;
   private String orderType;
   private String orderSortNumber;
   private String orderClassCode;
   private Integer hospitalizedCount;
   private String orderDoctorCode;
   private String orderDoctorNo;
   private String orderDoctorWardNo;
   private String orderDoctorDepCode;
   private Date orderStartTime;
   private String documentTypeNo;
   private String performListStatus;
   private String cpNo;
   private String cpName;
   private Date handleTime;
   private String handleNurseCode;
   private String handleNurseNo;
   private Date performTime;
   private String performNurseCode;
   private String performNurseNo;
   private String outHavaDrugFlag;
   private Integer orderItemType;
   private Integer firstDoubleFlag;
   private String takeDrugMode;
   private String depCode;
   private Date planUsageTime;
   private String babyAdmissionNo;
   private Long firstBottleFlag;
   private String performComputerNo;
   private String performComputerIp;
   private Integer performListSortNumber;
   private String detailPerformDepCode;
   private String detailPerformWardNo;
   private String orderGroupNo;
   private String priceFlag;
   private BigDecimal orderDoseItem;
   private String unitItem;
   private BigDecimal priceItem;
   private BigDecimal orderTotalItem;
   private String pcPdaFlag;
   private String reasonMessage;
   private String performOrderStatus;
   private String performOrderChangeTime;
   private Integer infusionCardPrint;
   private Integer bottleLabelPrint;
   private Date performCancelTime;
   private String performCancelAuditDoc;
   private String performCancelAuditDocName;
   private List detailList;

   public Integer getInfusionCardPrint() {
      return this.infusionCardPrint;
   }

   public void setInfusionCardPrint(Integer infusionCardPrint) {
      this.infusionCardPrint = infusionCardPrint;
   }

   public Integer getBottleLabelPrint() {
      return this.bottleLabelPrint;
   }

   public void setBottleLabelPrint(Integer bottleLabelPrint) {
      this.bottleLabelPrint = bottleLabelPrint;
   }

   public Date getPerformCancelTime() {
      return this.performCancelTime;
   }

   public void setPerformCancelTime(Date performCancelTime) {
      this.performCancelTime = performCancelTime;
   }

   public String getPerformCancelAuditDoc() {
      return this.performCancelAuditDoc;
   }

   public void setPerformCancelAuditDoc(String performCancelAuditDoc) {
      this.performCancelAuditDoc = performCancelAuditDoc;
   }

   public String getPerformCancelAuditDocName() {
      return this.performCancelAuditDocName;
   }

   public void setPerformCancelAuditDocName(String performCancelAuditDocName) {
      this.performCancelAuditDocName = performCancelAuditDocName;
   }

   public String getPcPdaFlag() {
      return this.pcPdaFlag;
   }

   public void setPcPdaFlag(String pcPdaFlag) {
      this.pcPdaFlag = pcPdaFlag;
   }

   public String getReasonMessage() {
      return this.reasonMessage;
   }

   public void setReasonMessage(String reasonMessage) {
      this.reasonMessage = reasonMessage;
   }

   public String getPerformOrderStatus() {
      return this.performOrderStatus;
   }

   public void setPerformOrderStatus(String performOrderStatus) {
      this.performOrderStatus = performOrderStatus;
   }

   public String getPerformOrderChangeTime() {
      return this.performOrderChangeTime;
   }

   public void setPerformOrderChangeTime(String performOrderChangeTime) {
      this.performOrderChangeTime = performOrderChangeTime;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
   }

   public String getPerformWardNo() {
      return this.performWardNo;
   }

   public void setPerformWardNo(String performWardNo) {
      this.performWardNo = performWardNo == null ? null : performWardNo.trim();
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode == null ? null : performDepCode.trim();
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo == null ? null : caseNo.trim();
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo == null ? null : performListNo.trim();
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo == null ? null : orderNo.trim();
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType == null ? null : orderType.trim();
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber == null ? null : orderSortNumber.trim();
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode == null ? null : orderClassCode.trim();
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
      this.orderDoctorCode = orderDoctorCode == null ? null : orderDoctorCode.trim();
   }

   public String getOrderDoctorNo() {
      return this.orderDoctorNo;
   }

   public void setOrderDoctorNo(String orderDoctorNo) {
      this.orderDoctorNo = orderDoctorNo == null ? null : orderDoctorNo.trim();
   }

   public String getOrderDoctorWardNo() {
      return this.orderDoctorWardNo;
   }

   public void setOrderDoctorWardNo(String orderDoctorWardNo) {
      this.orderDoctorWardNo = orderDoctorWardNo == null ? null : orderDoctorWardNo.trim();
   }

   public String getOrderDoctorDepCode() {
      return this.orderDoctorDepCode;
   }

   public void setOrderDoctorDepCode(String orderDoctorDepCode) {
      this.orderDoctorDepCode = orderDoctorDepCode == null ? null : orderDoctorDepCode.trim();
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
      this.documentTypeNo = documentTypeNo == null ? null : documentTypeNo.trim();
   }

   public String getPerformListStatus() {
      return this.performListStatus;
   }

   public void setPerformListStatus(String performListStatus) {
      this.performListStatus = performListStatus == null ? null : performListStatus.trim();
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo == null ? null : cpNo.trim();
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName == null ? null : cpName.trim();
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
      this.handleNurseCode = handleNurseCode == null ? null : handleNurseCode.trim();
   }

   public String getHandleNurseNo() {
      return this.handleNurseNo;
   }

   public void setHandleNurseNo(String handleNurseNo) {
      this.handleNurseNo = handleNurseNo == null ? null : handleNurseNo.trim();
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
      this.performNurseCode = performNurseCode == null ? null : performNurseCode.trim();
   }

   public String getPerformNurseNo() {
      return this.performNurseNo;
   }

   public void setPerformNurseNo(String performNurseNo) {
      this.performNurseNo = performNurseNo == null ? null : performNurseNo.trim();
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public Integer getOrderItemType() {
      return this.orderItemType;
   }

   public void setOrderItemType(Integer orderItemType) {
      this.orderItemType = orderItemType;
   }

   public Integer getFirstDoubleFlag() {
      return this.firstDoubleFlag;
   }

   public void setFirstDoubleFlag(Integer firstDoubleFlag) {
      this.firstDoubleFlag = firstDoubleFlag;
   }

   public String getTakeDrugMode() {
      return this.takeDrugMode;
   }

   public void setTakeDrugMode(String takeDrugMode) {
      this.takeDrugMode = takeDrugMode == null ? null : takeDrugMode.trim();
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
      this.babyAdmissionNo = babyAdmissionNo == null ? null : babyAdmissionNo.trim();
   }

   public Long getFirstBottleFlag() {
      return this.firstBottleFlag;
   }

   public void setFirstBottleFlag(Long firstBottleFlag) {
      this.firstBottleFlag = firstBottleFlag;
   }

   public String getPerformComputerNo() {
      return this.performComputerNo;
   }

   public void setPerformComputerNo(String performComputerNo) {
      this.performComputerNo = performComputerNo == null ? null : performComputerNo.trim();
   }

   public String getPerformComputerIp() {
      return this.performComputerIp;
   }

   public void setPerformComputerIp(String performComputerIp) {
      this.performComputerIp = performComputerIp == null ? null : performComputerIp.trim();
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getDetailPerformDepCode() {
      return this.detailPerformDepCode;
   }

   public void setDetailPerformDepCode(String detailPerformDepCode) {
      this.detailPerformDepCode = detailPerformDepCode == null ? null : detailPerformDepCode.trim();
   }

   public String getDetailPerformWardNo() {
      return this.detailPerformWardNo;
   }

   public void setDetailPerformWardNo(String detailPerformWardNo) {
      this.detailPerformWardNo = detailPerformWardNo == null ? null : detailPerformWardNo.trim();
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public BigDecimal getOrderDoseItem() {
      return this.orderDoseItem;
   }

   public void setOrderDoseItem(BigDecimal orderDoseItem) {
      this.orderDoseItem = orderDoseItem;
   }

   public String getUnitItem() {
      return this.unitItem;
   }

   public void setUnitItem(String unitItem) {
      this.unitItem = unitItem;
   }

   public BigDecimal getPriceItem() {
      return this.priceItem;
   }

   public void setPriceItem(BigDecimal priceItem) {
      this.priceItem = priceItem;
   }

   public BigDecimal getOrderTotalItem() {
      return this.orderTotalItem;
   }

   public void setOrderTotalItem(BigDecimal orderTotalItem) {
      this.orderTotalItem = orderTotalItem;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getPriceFlag() {
      return this.priceFlag;
   }

   public void setPriceFlag(String priceFlag) {
      this.priceFlag = priceFlag;
   }

   public Object clone() throws CloneNotSupportedException {
      return super.clone();
   }

   public String toString() {
      return "InpatientOrderPerform [hospitalCode=" + this.hospitalCode + ", performWardNo=" + this.performWardNo + ", performDepCode=" + this.performDepCode + ", caseNo=" + this.caseNo + ", admissionNo=" + this.admissionNo + ", performListNo=" + this.performListNo + ", orderNo=" + this.orderNo + ", orderType=" + this.orderType + ", orderSortNumber=" + this.orderSortNumber + ", orderClassCode=" + this.orderClassCode + ", hospitalizedCount=" + this.hospitalizedCount + ", orderDoctorCode=" + this.orderDoctorCode + ", orderDoctorNo=" + this.orderDoctorNo + ", orderDoctorWardNo=" + this.orderDoctorWardNo + ", orderDoctorDepCode=" + this.orderDoctorDepCode + ", orderStartTime=" + this.orderStartTime + ", documentTypeNo=" + this.documentTypeNo + ", performListStatus=" + this.performListStatus + ", cpNo=" + this.cpNo + ", cpName=" + this.cpName + ", handleTime=" + this.handleTime + ", handleNurseCode=" + this.handleNurseCode + ", handleNurseNo=" + this.handleNurseNo + ", performTime=" + this.performTime + ", performNurseCode=" + this.performNurseCode + ", performNurseNo=" + this.performNurseNo + ", outHavaDrugFlag=" + this.outHavaDrugFlag + ", orderItemType=" + this.orderItemType + ", firstDoubleFlag=" + this.firstDoubleFlag + ", takeDrugMode=" + this.takeDrugMode + ", deptCode=" + this.depCode + ", planUsageTime=" + this.planUsageTime + ", babyAdmissionNo=" + this.babyAdmissionNo + ", firstBottleFlag=" + this.firstBottleFlag + ", performComputerNo=" + this.performComputerNo + ", performComputerIp=" + this.performComputerIp + ", performListSortNumber=" + this.performListSortNumber + ", detailPerformDepCode=" + this.detailPerformDepCode + ", detailPerformWardNo=" + this.detailPerformWardNo + ", detailList=" + this.detailList + "]";
   }
}
