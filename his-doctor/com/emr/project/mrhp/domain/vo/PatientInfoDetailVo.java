package com.emr.project.mrhp.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class PatientInfoDetailVo {
   private String patientName;
   private String caseNo;
   private String admissionNo;
   private String hospitalizedCount;
   private String inDeptName;
   private String inDeptCd;
   private String inRoomNo;
   @ApiModelProperty("过敏史")
   private String alleDrug;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date outTime;
   @ApiModelProperty("籍贯-省")
   private String apAddrPro;
   @ApiModelProperty("籍贯-市")
   private String apAddrPlagty;
   @Excel(
      name = "现住址-省"
   )
   private String nowAddrPro = "";
   @Excel(
      name = "现住址-市"
   )
   private String nowAddrFlagty = "";
   @Excel(
      name = "现住址-县"
   )
   private String nowAddrCou = "";
   @Excel(
      name = "现住址"
   )
   private String nowAddr = "";
   @ApiModelProperty("现住址-邮编")
   private String nowAddrPost;
   @Excel(
      name = "ABO血型"
   )
   private String aboName = "";
   @Excel(
      name = "ABO血型代码"
   )
   private String aboCd = "";
   @Excel(
      name = "RH血型"
   )
   private String rhName = "";
   @Excel(
      name = "Rh血型代码"
   )
   private String rhCd = "";

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

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

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getInDeptName() {
      return this.inDeptName;
   }

   public void setInDeptName(String inDeptName) {
      this.inDeptName = inDeptName;
   }

   public String getInDeptCd() {
      return this.inDeptCd;
   }

   public void setInDeptCd(String inDeptCd) {
      this.inDeptCd = inDeptCd;
   }

   public String getInRoomNo() {
      return this.inRoomNo;
   }

   public void setInRoomNo(String inRoomNo) {
      this.inRoomNo = inRoomNo;
   }

   public String getAlleDrug() {
      return this.alleDrug;
   }

   public void setAlleDrug(String alleDrug) {
      this.alleDrug = alleDrug;
   }

   public String getApAddrPro() {
      return this.apAddrPro;
   }

   public void setApAddrPro(String apAddrPro) {
      this.apAddrPro = apAddrPro;
   }

   public String getApAddrPlagty() {
      return this.apAddrPlagty;
   }

   public void setApAddrPlagty(String apAddrPlagty) {
      this.apAddrPlagty = apAddrPlagty;
   }

   public String getNowAddrPro() {
      return this.nowAddrPro;
   }

   public void setNowAddrPro(String nowAddrPro) {
      this.nowAddrPro = nowAddrPro;
   }

   public String getNowAddrFlagty() {
      return this.nowAddrFlagty;
   }

   public void setNowAddrFlagty(String nowAddrFlagty) {
      this.nowAddrFlagty = nowAddrFlagty;
   }

   public String getNowAddrCou() {
      return this.nowAddrCou;
   }

   public void setNowAddrCou(String nowAddrCou) {
      this.nowAddrCou = nowAddrCou;
   }

   public String getNowAddr() {
      return this.nowAddr;
   }

   public void setNowAddr(String nowAddr) {
      this.nowAddr = nowAddr;
   }

   public String getNowAddrPost() {
      return this.nowAddrPost;
   }

   public void setNowAddrPost(String nowAddrPost) {
      this.nowAddrPost = nowAddrPost;
   }

   public String getAboName() {
      return this.aboName;
   }

   public void setAboName(String aboName) {
      this.aboName = aboName;
   }

   public String getAboCd() {
      return this.aboCd;
   }

   public void setAboCd(String aboCd) {
      this.aboCd = aboCd;
   }

   public String getRhName() {
      return this.rhName;
   }

   public void setRhName(String rhName) {
      this.rhName = rhName;
   }

   public String getRhCd() {
      return this.rhCd;
   }

   public void setRhCd(String rhCd) {
      this.rhCd = rhCd;
   }
}
