package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class SealupRecord extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "住院号"
   )
   private String inpNo;
   @Excel(
      name = "就诊ID"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "性别"
   )
   private String gender;
   @Excel(
      name = "患者年龄"
   )
   private String age;
   @Excel(
      name = "医师代码"
   )
   private String resDocCode;
   @Excel(
      name = "医师名称"
   )
   private String resDocName;
   @Excel(
      name = "科室ID"
   )
   private String deptId;
   @Excel(
      name = "科室名称"
   )
   private String dpetName;
   @Excel(
      name = "床位号"
   )
   private String bedNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date inhosTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date outTime;
   @Excel(
      name = "状态  1 封存 0 启封"
   )
   private Integer status;
   @Excel(
      name = "院方代表姓名"
   )
   private String hosRepre;
   @Excel(
      name = "院方代表身份证号"
   )
   private String hosRepreIdNumb;
   @Excel(
      name = "院方代表签名"
   )
   private String hosRepreSign;
   @Excel(
      name = "患者或授权人姓名"
   )
   private String patName;
   @Excel(
      name = "患者或授权人身份证号"
   )
   private String patIdNumb;
   @Excel(
      name = "患者签名"
   )
   private String patSign;
   @Excel(
      name = "见证人"
   )
   private String witness;
   @Excel(
      name = "见证人身份证号"
   )
   private String witnessIdNumb;
   @Excel(
      name = "见证人签名"
   )
   private String witnessSign;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "封存日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date sealDate;
   @Excel(
      name = "封存密码"
   )
   private String sealPass;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "启封日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date nosealDate;
   @Excel(
      name = "封存操作人编码"
   )
   private String sealPersonCode;
   @Excel(
      name = "封存操作人"
   )
   private String sealPerson;
   @Excel(
      name = "启封操作人编码"
   )
   private String unsealPersonCode;
   @Excel(
      name = "启封操作人"
   )
   private String unsealPerson;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "有效标志",
      readConverterExp = "1=：有效；0：无效"
   )
   private String isValid;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getGender() {
      return this.gender;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getAge() {
      return this.age;
   }

   public void setResDocCode(String resDocCode) {
      this.resDocCode = resDocCode;
   }

   public String getResDocCode() {
      return this.resDocCode;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setDeptId(String deptId) {
      this.deptId = deptId;
   }

   public String getDeptId() {
      return this.deptId;
   }

   public void setDpetName(String dpetName) {
      this.dpetName = dpetName;
   }

   public String getDpetName() {
      return this.dpetName;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public Integer getStatus() {
      return this.status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public void setHosRepre(String hosRepre) {
      this.hosRepre = hosRepre;
   }

   public String getHosRepre() {
      return this.hosRepre;
   }

   public void setHosRepreIdNumb(String hosRepreIdNumb) {
      this.hosRepreIdNumb = hosRepreIdNumb;
   }

   public String getHosRepreIdNumb() {
      return this.hosRepreIdNumb;
   }

   public void setHosRepreSign(String hosRepreSign) {
      this.hosRepreSign = hosRepreSign;
   }

   public String getHosRepreSign() {
      return this.hosRepreSign;
   }

   public void setPatName(String patName) {
      this.patName = patName;
   }

   public String getPatName() {
      return this.patName;
   }

   public void setPatIdNumb(String patIdNumb) {
      this.patIdNumb = patIdNumb;
   }

   public String getPatIdNumb() {
      return this.patIdNumb;
   }

   public void setPatSign(String patSign) {
      this.patSign = patSign;
   }

   public String getPatSign() {
      return this.patSign;
   }

   public void setWitness(String witness) {
      this.witness = witness;
   }

   public String getWitness() {
      return this.witness;
   }

   public void setWitnessIdNumb(String witnessIdNumb) {
      this.witnessIdNumb = witnessIdNumb;
   }

   public String getWitnessIdNumb() {
      return this.witnessIdNumb;
   }

   public void setWitnessSign(String witnessSign) {
      this.witnessSign = witnessSign;
   }

   public String getWitnessSign() {
      return this.witnessSign;
   }

   public void setSealDate(Date sealDate) {
      this.sealDate = sealDate;
   }

   public Date getSealDate() {
      return this.sealDate;
   }

   public void setSealPass(String sealPass) {
      this.sealPass = sealPass;
   }

   public String getSealPass() {
      return this.sealPass;
   }

   public void setNosealDate(Date nosealDate) {
      this.nosealDate = nosealDate;
   }

   public Date getNosealDate() {
      return this.nosealDate;
   }

   public void setSealPersonCode(String sealPersonCode) {
      this.sealPersonCode = sealPersonCode;
   }

   public String getSealPersonCode() {
      return this.sealPersonCode;
   }

   public void setSealPerson(String sealPerson) {
      this.sealPerson = sealPerson;
   }

   public String getSealPerson() {
      return this.sealPerson;
   }

   public void setUnsealPersonCode(String unsealPersonCode) {
      this.unsealPersonCode = unsealPersonCode;
   }

   public String getUnsealPersonCode() {
      return this.unsealPersonCode;
   }

   public void setUnsealPerson(String unsealPerson) {
      this.unsealPerson = unsealPerson;
   }

   public String getUnsealPerson() {
      return this.unsealPerson;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setIsValid(String isValid) {
      this.isValid = isValid;
   }

   public String getIsValid() {
      return this.isValid;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }
}
