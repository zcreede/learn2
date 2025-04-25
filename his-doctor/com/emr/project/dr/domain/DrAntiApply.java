package com.emr.project.dr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DrAntiApply extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "机构编码"
   )
   private String orgCd;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "患者病案号"
   )
   private String recordNo;
   @Excel(
      name = "性别编码"
   )
   private String sexCd;
   @Excel(
      name = "性别名称"
   )
   private String sexName;
   @Excel(
      name = "年龄"
   )
   private String age;
   @Excel(
      name = "临床诊断"
   )
   private String clinDiag;
   @Excel(
      name = "药品字典编码"
   )
   private String drugCode;
   @Excel(
      name = "药品名称"
   )
   private String drugName;
   @Excel(
      name = "药品规格"
   )
   private String drugSpec;
   @Excel(
      name = "药品等级编码"
   )
   private String drugGradeCode;
   @Excel(
      name = "药品等级名称"
   )
   private String drugGradeName;
   @Excel(
      name = "申请数量"
   )
   private Long applyAmount;
   @Excel(
      name = "单位"
   )
   private String unit;
   @Excel(
      name = "抗菌药物使用目的"
   )
   private String purposeAntimicrobialUse;
   @Excel(
      name = "申请说明"
   )
   private String applyComm;
   @Excel(
      name = "申请医师编码"
   )
   private String applyDocCode;
   @Excel(
      name = "申请医师姓名"
   )
   private String applyDocName;
   @Excel(
      name = "申请医师职称"
   )
   private String applyTitleCode;
   @Excel(
      name = "申请单职称名称"
   )
   private String applyTitleName;
   @Excel(
      name = "科室编码"
   )
   private String deptCode;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date applyDate;
   @Excel(
      name = "状态"
   )
   private String state;
   @Excel(
      name = "使用标志",
      readConverterExp = "0=未使用；1使用；使用标志为1时，不允许再删除"
   )
   private String useFlag;
   @Excel(
      name = "医嘱编码，记录使用本次申请的第一条医嘱编码。和use_fllag同时更新"
   )
   private String orderNo;
   @Excel(
      name = "审批医师编码"
   )
   private String apprDocCode;
   @Excel(
      name = "审批医师姓名"
   )
   private String apprDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "审批时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date apprDocDate;
   @Excel(
      name = "审批医师职称"
   )
   private String apprTitleCode;
   @Excel(
      name = "审批医师职称名称"
   )
   private String apprTitleName;
   @Excel(
      name = "审批意见"
   )
   private String apprComm;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "修改编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人姓名"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   private String inpNo;
   private String inputstrphtc;
   private String bedNo;

   public DrAntiApply() {
   }

   public DrAntiApply(String patientId, String drugCode, String state) {
      this.patientId = patientId;
      this.drugCode = drugCode;
      this.state = state;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

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

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setSexName(String sexName) {
      this.sexName = sexName;
   }

   public String getSexName() {
      return this.sexName;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getAge() {
      return this.age;
   }

   public void setClinDiag(String clinDiag) {
      this.clinDiag = clinDiag;
   }

   public String getClinDiag() {
      return this.clinDiag;
   }

   public void setDrugCode(String drugCode) {
      this.drugCode = drugCode;
   }

   public String getDrugCode() {
      return this.drugCode;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugSpec(String drugSpec) {
      this.drugSpec = drugSpec;
   }

   public String getDrugSpec() {
      return this.drugSpec;
   }

   public void setDrugGradeCode(String drugGradeCode) {
      this.drugGradeCode = drugGradeCode;
   }

   public String getDrugGradeCode() {
      return this.drugGradeCode;
   }

   public void setDrugGradeName(String drugGradeName) {
      this.drugGradeName = drugGradeName;
   }

   public String getDrugGradeName() {
      return this.drugGradeName;
   }

   public void setApplyAmount(Long applyAmount) {
      this.applyAmount = applyAmount;
   }

   public Long getApplyAmount() {
      return this.applyAmount;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setPurposeAntimicrobialUse(String purposeAntimicrobialUse) {
      this.purposeAntimicrobialUse = purposeAntimicrobialUse;
   }

   public String getPurposeAntimicrobialUse() {
      return this.purposeAntimicrobialUse;
   }

   public void setApplyComm(String applyComm) {
      this.applyComm = applyComm;
   }

   public String getApplyComm() {
      return this.applyComm;
   }

   public void setApplyDocCode(String applyDocCode) {
      this.applyDocCode = applyDocCode;
   }

   public String getApplyDocCode() {
      return this.applyDocCode;
   }

   public void setApplyDocName(String applyDocName) {
      this.applyDocName = applyDocName;
   }

   public String getApplyDocName() {
      return this.applyDocName;
   }

   public void setApplyTitleCode(String applyTitleCode) {
      this.applyTitleCode = applyTitleCode;
   }

   public String getApplyTitleCode() {
      return this.applyTitleCode;
   }

   public void setApplyTitleName(String applyTitleName) {
      this.applyTitleName = applyTitleName;
   }

   public String getApplyTitleName() {
      return this.applyTitleName;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setApplyDate(Date applyDate) {
      this.applyDate = applyDate;
   }

   public Date getApplyDate() {
      return this.applyDate;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getState() {
      return this.state;
   }

   public void setUseFlag(String useFlag) {
      this.useFlag = useFlag;
   }

   public String getUseFlag() {
      return this.useFlag;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setApprDocCode(String apprDocCode) {
      this.apprDocCode = apprDocCode;
   }

   public String getApprDocCode() {
      return this.apprDocCode;
   }

   public void setApprDocName(String apprDocName) {
      this.apprDocName = apprDocName;
   }

   public String getApprDocName() {
      return this.apprDocName;
   }

   public void setApprDocDate(Date apprDocDate) {
      this.apprDocDate = apprDocDate;
   }

   public Date getApprDocDate() {
      return this.apprDocDate;
   }

   public void setApprTitleCode(String apprTitleCode) {
      this.apprTitleCode = apprTitleCode;
   }

   public String getApprTitleCode() {
      return this.apprTitleCode;
   }

   public void setApprTitleName(String apprTitleName) {
      this.apprTitleName = apprTitleName;
   }

   public String getApprTitleName() {
      return this.apprTitleName;
   }

   public void setApprComm(String apprComm) {
      this.apprComm = apprComm;
   }

   public String getApprComm() {
      return this.apprComm;
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

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("recordNo", this.getRecordNo()).append("sexCd", this.getSexCd()).append("sexName", this.getSexName()).append("age", this.getAge()).append("clinDiag", this.getClinDiag()).append("drugCode", this.getDrugCode()).append("drugName", this.getDrugName()).append("drugSpec", this.getDrugSpec()).append("drugGradeCode", this.getDrugGradeCode()).append("drugGradeName", this.getDrugGradeName()).append("applyAmount", this.getApplyAmount()).append("unit", this.getUnit()).append("purposeAntimicrobialUse", this.getPurposeAntimicrobialUse()).append("applyComm", this.getApplyComm()).append("applyDocCode", this.getApplyDocCode()).append("applyDocName", this.getApplyDocName()).append("applyTitleCode", this.getApplyTitleCode()).append("applyTitleName", this.getApplyTitleName()).append("deptCode", this.getDeptCode()).append("deptName", this.getDeptName()).append("applyDate", this.getApplyDate()).append("state", this.getState()).append("useFlag", this.getUseFlag()).append("orderNo", this.getOrderNo()).append("apprDocCode", this.getApprDocCode()).append("apprDocName", this.getApprDocName()).append("apprDocDate", this.getApprDocDate()).append("apprTitleCode", this.getApprTitleCode()).append("apprTitleName", this.getApprTitleName()).append("apprComm", this.getApprComm()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("inpNo", this.getInpNo()).append("inputstrphtc", this.getInputstrphtc()).append("bedNo", this.getBedNo()).toString();
   }
}
