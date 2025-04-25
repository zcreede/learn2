package com.emr.project.report.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class VisitinfoExportVo {
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入科日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date inDeptTime;
   @Excel(
      name = "住院号"
   )
   private String recordNo;
   @Excel(
      name = "患者类型"
   )
   private String patTypeName;
   @Excel(
      name = "姓名"
   )
   private String patientName;
   @Excel(
      name = "年龄"
   )
   private String age;
   @Excel(
      name = "性别"
   )
   private String sex;
   @Excel(
      name = "病床号"
   )
   private String bedNo;
   @Excel(
      name = "诊断"
   )
   private String inhosDiag;
   @Excel(
      name = "住院医师"
   )
   private String resDocName;
   @Excel(
      name = "责任护士"
   )
   private String dutyNurName;
   @Excel(
      name = "护理级别"
   )
   private String ngName;
   @Excel(
      name = "押金总额"
   )
   private Double foreSum;
   @Excel(
      name = "可用余额"
   )
   private Double totalPatAva;
   @Excel(
      name = "入院天数"
   )
   private int inhosDayNum;

   public Date getInDeptTime() {
      return this.inDeptTime;
   }

   public void setInDeptTime(Date inDeptTime) {
      this.inDeptTime = inDeptTime;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getPatTypeName() {
      return this.patTypeName;
   }

   public void setPatTypeName(String patTypeName) {
      this.patTypeName = patTypeName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getInhosDiag() {
      return this.inhosDiag;
   }

   public void setInhosDiag(String inhosDiag) {
      this.inhosDiag = inhosDiag;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getDutyNurName() {
      return this.dutyNurName;
   }

   public void setDutyNurName(String dutyNurName) {
      this.dutyNurName = dutyNurName;
   }

   public String getNgName() {
      return this.ngName;
   }

   public void setNgName(String ngName) {
      this.ngName = ngName;
   }

   public Double getForeSum() {
      return this.foreSum;
   }

   public void setForeSum(Double foreSum) {
      this.foreSum = foreSum;
   }

   public Double getTotalPatAva() {
      return this.totalPatAva;
   }

   public void setTotalPatAva(Double totalPatAva) {
      this.totalPatAva = totalPatAva;
   }

   public int getInhosDayNum() {
      return this.inhosDayNum;
   }

   public void setInhosDayNum(int inhosDayNum) {
      this.inhosDayNum = inhosDayNum;
   }
}
