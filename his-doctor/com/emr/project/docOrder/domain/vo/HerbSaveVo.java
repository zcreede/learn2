package com.emr.project.docOrder.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class HerbSaveVo {
   private String orderNo;
   private String orderType;
   private String orderClassCode;
   private String patientId;
   private String outHavaDrugFlag;
   private String babyAdmissionNo;
   private String itemOrderUsageWay;
   private String itemOrderUsageWayName;
   private String itemOrderFreq;
   private String itemOrderFreqName;
   private BigDecimal herbalDose;
   private String decoctMethod;
   private String plasterFlag;
   private String herbalIntr;
   private String oederDesc;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStartTime;
   private String orderStartDoc;
   private String orderStartDocName;
   private String orderDoctorDepCode;
   private String orderDoctorDepName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStopTime;
   private String orderStopDoc;
   private String orderStopDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderCancelTime;
   private String performDepCode;
   private String performDepName;
   private String orderPerformFlag;
   private String performComputerIp;
   private String prescribeNo;
   private String prescribeName;
   private String orderStatus;
   private List orderDetailList;
   private Integer orderGroupNo;
   private String cipherName;
   private String inputstrphtc;
   private String shareType;
   private List orderNoList;
   @Excel(
      name = "医嘱重整序号"
   )
   private Integer reOrganizationNo;
   private String isSubmit;
   private String operatingRoomFlag;

   public String getIsSubmit() {
      return this.isSubmit;
   }

   public void setIsSubmit(String isSubmit) {
      this.isSubmit = isSubmit;
   }

   public Integer getReOrganizationNo() {
      return this.reOrganizationNo;
   }

   public void setReOrganizationNo(Integer reOrganizationNo) {
      this.reOrganizationNo = reOrganizationNo;
   }

   public List getOrderNoList() {
      return this.orderNoList;
   }

   public void setOrderNoList(List orderNoList) {
      this.orderNoList = orderNoList;
   }

   public String getCipherName() {
      return this.cipherName;
   }

   public void setCipherName(String cipherName) {
      this.cipherName = cipherName;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getShareType() {
      return this.shareType;
   }

   public void setShareType(String shareType) {
      this.shareType = shareType;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }

   public String getPrescribeNo() {
      return this.prescribeNo;
   }

   public void setPrescribeNo(String prescribeNo) {
      this.prescribeNo = prescribeNo;
   }

   public String getPrescribeName() {
      return this.prescribeName;
   }

   public void setPrescribeName(String prescribeName) {
      this.prescribeName = prescribeName;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public List getOrderDetailList() {
      return this.orderDetailList;
   }

   public void setOrderDetailList(List orderDetailList) {
      this.orderDetailList = orderDetailList;
   }

   public String getPerformComputerIp() {
      return this.performComputerIp;
   }

   public void setPerformComputerIp(String performComputerIp) {
      this.performComputerIp = performComputerIp;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getPerformDepName() {
      return this.performDepName;
   }

   public void setPerformDepName(String performDepName) {
      this.performDepName = performDepName;
   }

   public String getOrderPerformFlag() {
      return this.orderPerformFlag;
   }

   public void setOrderPerformFlag(String orderPerformFlag) {
      this.orderPerformFlag = orderPerformFlag;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public String getItemOrderUsageWay() {
      return this.itemOrderUsageWay;
   }

   public void setItemOrderUsageWay(String itemOrderUsageWay) {
      this.itemOrderUsageWay = itemOrderUsageWay;
   }

   public String getItemOrderUsageWayName() {
      return this.itemOrderUsageWayName;
   }

   public void setItemOrderUsageWayName(String itemOrderUsageWayName) {
      this.itemOrderUsageWayName = itemOrderUsageWayName;
   }

   public String getItemOrderFreq() {
      return this.itemOrderFreq;
   }

   public void setItemOrderFreq(String itemOrderFreq) {
      this.itemOrderFreq = itemOrderFreq;
   }

   public String getItemOrderFreqName() {
      return this.itemOrderFreqName;
   }

   public void setItemOrderFreqName(String itemOrderFreqName) {
      this.itemOrderFreqName = itemOrderFreqName;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public String getDecoctMethod() {
      return this.decoctMethod;
   }

   public void setDecoctMethod(String decoctMethod) {
      this.decoctMethod = decoctMethod;
   }

   public String getPlasterFlag() {
      return this.plasterFlag;
   }

   public void setPlasterFlag(String plasterFlag) {
      this.plasterFlag = plasterFlag;
   }

   public String getHerbalIntr() {
      return this.herbalIntr;
   }

   public void setHerbalIntr(String herbalIntr) {
      this.herbalIntr = herbalIntr;
   }

   public String getOederDesc() {
      return this.oederDesc;
   }

   public void setOederDesc(String oederDesc) {
      this.oederDesc = oederDesc;
   }

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
   }

   public String getOrderStartDoc() {
      return this.orderStartDoc;
   }

   public void setOrderStartDoc(String orderStartDoc) {
      this.orderStartDoc = orderStartDoc;
   }

   public String getOrderStartDocName() {
      return this.orderStartDocName;
   }

   public void setOrderStartDocName(String orderStartDocName) {
      this.orderStartDocName = orderStartDocName;
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

   public Date getOrderStopTime() {
      return this.orderStopTime;
   }

   public void setOrderStopTime(Date orderStopTime) {
      this.orderStopTime = orderStopTime;
   }

   public String getOrderStopDoc() {
      return this.orderStopDoc;
   }

   public void setOrderStopDoc(String orderStopDoc) {
      this.orderStopDoc = orderStopDoc;
   }

   public String getOrderStopDocName() {
      return this.orderStopDocName;
   }

   public void setOrderStopDocName(String orderStopDocName) {
      this.orderStopDocName = orderStopDocName;
   }

   public Date getOrderCancelTime() {
      return this.orderCancelTime;
   }

   public void setOrderCancelTime(Date orderCancelTime) {
      this.orderCancelTime = orderCancelTime;
   }

   public String getOperatingRoomFlag() {
      return this.operatingRoomFlag;
   }

   public void setOperatingRoomFlag(String operatingRoomFlag) {
      this.operatingRoomFlag = operatingRoomFlag;
   }
}
