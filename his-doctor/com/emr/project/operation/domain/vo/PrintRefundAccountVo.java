package com.emr.project.operation.domain.vo;

import java.math.BigDecimal;

public class PrintRefundAccountVo {
   private String orgName;
   private String caseNo;
   private String deptName;
   private String patientName;
   private String admissionNo;
   private String bedId;
   private String serialNumber;
   private String chargeName;
   private BigDecimal total;
   private String staffName;

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
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

   public String getBedId() {
      return this.bedId;
   }

   public void setBedId(String bedId) {
      this.bedId = bedId;
   }

   public String getSerialNumber() {
      return this.serialNumber;
   }

   public void setSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }
}
