package com.emr.project.dr.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class TdCaConsApplyForExcelVo {
   @Excel(
      name = "患者住院号"
   )
   private String inpNo;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "病床号"
   )
   private String bedNo;
   @Excel(
      name = "申请科室名称"
   )
   private String applyDeptName;
   @Excel(
      name = "发起人"
   )
   private String applyDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "发起时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date applyDate;
   @Excel(
      name = "会诊类型名称"
   )
   private String consTypeName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date consSignDate;
   @Excel(
      name = "会诊记录完成时间",
      width = (double)30.0F
   )
   private String consSignDateS;
   @Excel(
      name = "累计用时"
   )
   private String remainHours;
   @Excel(
      name = "超时状态"
   )
   private String exceedState;
   @Excel(
      name = "会诊科室"
   )
   private String consDeptName;
   @Excel(
      name = "会诊医师"
   )
   private String consDocName;

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getApplyDeptName() {
      return this.applyDeptName;
   }

   public void setApplyDeptName(String applyDeptName) {
      this.applyDeptName = applyDeptName;
   }

   public String getApplyDocName() {
      return this.applyDocName;
   }

   public void setApplyDocName(String applyDocName) {
      this.applyDocName = applyDocName;
   }

   public Date getApplyDate() {
      return this.applyDate;
   }

   public void setApplyDate(Date applyDate) {
      this.applyDate = applyDate;
   }

   public String getConsTypeName() {
      return this.consTypeName;
   }

   public void setConsTypeName(String consTypeName) {
      this.consTypeName = consTypeName;
   }

   public Date getConsSignDate() {
      return this.consSignDate;
   }

   public void setConsSignDate(Date consSignDate) {
      this.consSignDate = consSignDate;
   }

   public String getConsSignDateS() {
      return this.consSignDateS;
   }

   public void setConsSignDateS(String consSignDateS) {
      this.consSignDateS = consSignDateS;
   }

   public String getRemainHours() {
      return this.remainHours;
   }

   public void setRemainHours(String remainHours) {
      this.remainHours = remainHours;
   }

   public String getExceedState() {
      return this.exceedState;
   }

   public void setExceedState(String exceedState) {
      this.exceedState = exceedState;
   }

   public String getConsDeptName() {
      return this.consDeptName;
   }

   public void setConsDeptName(String consDeptName) {
      this.consDeptName = consDeptName;
   }

   public String getConsDocName() {
      return this.consDocName;
   }

   public void setConsDocName(String consDocName) {
      this.consDocName = consDocName;
   }
}
