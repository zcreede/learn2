package com.emr.project.report.domain.dto;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class ChangeDeptTableListDTO {
   @Excel(
      name = "住院号"
   )
   private String caseNo;
   private String admissionNo;
   @Excel(
      name = "入院次数"
   )
   private Integer hospitalizedCount;
   @Excel(
      name = "姓名"
   )
   private String patientName;
   @Excel(
      name = "性别"
   )
   private String patientSex;
   private String wardNoIn;
   private String wardNoOut;
   private String bedidIn;
   private String bedidOut;
   private String doctorNameIn;
   @Excel(
      name = "转入科室"
   )
   private String inDepName;
   @Excel(
      name = "转出科室"
   )
   private String outDepName;
   @Excel(
      name = "转科时间",
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operatorDateOut;

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getBedidIn() {
      return this.bedidIn;
   }

   public void setBedidIn(String bedidIn) {
      this.bedidIn = bedidIn;
   }

   public String getBedidOut() {
      return this.bedidOut;
   }

   public void setBedidOut(String bedidOut) {
      this.bedidOut = bedidOut;
   }

   public String getDoctorNameIn() {
      return this.doctorNameIn;
   }

   public void setDoctorNameIn(String doctorNameIn) {
      this.doctorNameIn = doctorNameIn;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getInDepName() {
      return this.inDepName;
   }

   public void setInDepName(String inDepName) {
      this.inDepName = inDepName;
   }

   public Date getOperatorDateOut() {
      return this.operatorDateOut;
   }

   public void setOperatorDateOut(Date operatorDateOut) {
      this.operatorDateOut = operatorDateOut;
   }

   public String getOutDepName() {
      return this.outDepName;
   }

   public void setOutDepName(String outDepName) {
      this.outDepName = outDepName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientSex() {
      return this.patientSex;
   }

   public void setPatientSex(String patientSex) {
      this.patientSex = patientSex;
   }

   public String getWardNoIn() {
      return this.wardNoIn;
   }

   public void setWardNoIn(String wardNoIn) {
      this.wardNoIn = wardNoIn;
   }

   public String getWardNoOut() {
      return this.wardNoOut;
   }

   public void setWardNoOut(String wardNoOut) {
      this.wardNoOut = wardNoOut;
   }
}
