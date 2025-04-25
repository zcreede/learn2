package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TdPaOrderDetailVo extends TdPaOrderDetail {
   private String orderStartDoc;
   private String orderStartDocName;
   private String hospitalCode;
   private String itemOrderUsageWayName;
   private String itemOrderFreqName;
   private String prescribeNo;
   private String prescribeName;
   private BigDecimal stockNum;
   private BigDecimal stockFakeNum;
   private String emrEventCd;
   private String performListNo;
   private String performListSortNumber;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date planUsageTime;
   private String orderFreqName;
   private String orderType;
   private String performListStatus;
   private Integer orderGroupNoRe;
   private List lcljCpNoList;
   private String patientId;

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getEmrEventCd() {
      return this.emrEventCd;
   }

   public void setEmrEventCd(String emrEventCd) {
      this.emrEventCd = emrEventCd;
   }

   public BigDecimal getStockNum() {
      return this.stockNum;
   }

   public void setStockNum(BigDecimal stockNum) {
      this.stockNum = stockNum;
   }

   public BigDecimal getStockFakeNum() {
      return this.stockFakeNum;
   }

   public void setStockFakeNum(BigDecimal stockFakeNum) {
      this.stockFakeNum = stockFakeNum;
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

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getItemOrderUsageWayName() {
      return this.itemOrderUsageWayName;
   }

   public void setItemOrderUsageWayName(String itemOrderUsageWayName) {
      this.itemOrderUsageWayName = itemOrderUsageWayName;
   }

   public String getItemOrderFreqName() {
      return this.itemOrderFreqName;
   }

   public void setItemOrderFreqName(String itemOrderFreqName) {
      this.itemOrderFreqName = itemOrderFreqName;
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

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(String performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public Date getPlanUsageTime() {
      return this.planUsageTime;
   }

   public void setPlanUsageTime(Date planUsageTime) {
      this.planUsageTime = planUsageTime;
   }

   public String getOrderFreqName() {
      return this.orderFreqName;
   }

   public void setOrderFreqName(String orderFreqName) {
      this.orderFreqName = orderFreqName;
   }

   public String getPerformListStatus() {
      return this.performListStatus;
   }

   public void setPerformListStatus(String performListStatus) {
      this.performListStatus = performListStatus;
   }

   public Integer getOrderGroupNoRe() {
      return this.orderGroupNoRe;
   }

   public void setOrderGroupNoRe(Integer orderGroupNoRe) {
      this.orderGroupNoRe = orderGroupNoRe;
   }

   public List getLcljCpNoList() {
      return this.lcljCpNoList;
   }

   public void setLcljCpNoList(List lcljCpNoList) {
      this.lcljCpNoList = lcljCpNoList;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }
}
