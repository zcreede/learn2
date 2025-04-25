package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiagInfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String id;
   @Excel(
      name = "经《医疗机构执业许可证》登记的，并按照特定编码体系填写的代码"
   )
   private String orgCd;
   @Excel(
      name = "患者在住院诊疗所在的医疗机构名称"
   )
   private String orgName;
   @Excel(
      name = "患者主索引ID"
   )
   private String patientMainId;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "诊断类型代码",
      readConverterExp = "0=1.门诊诊断；02.入院诊断；03.出院诊断"
   )
   private String diagTypeCd;
   @Excel(
      name = "诊断类型名称"
   )
   private String diagTypeName;
   @Excel(
      name = "诊断分类",
      readConverterExp = "Z=Y中医；XY西医"
   )
   private String diagClass;
   @Excel(
      name = "诊断标识代码"
   )
   private String diagClassCd;
   @Excel(
      name = "诊断标识名称 主要诊断、其他诊断、主证、主病等"
   )
   private String diagClassName;
   @Excel(
      name = "诊断序号"
   )
   private String diagNo;
   @Excel(
      name = "疾病诊断编码",
      readConverterExp = "I=CD-10国际疾病分类标准编码表"
   )
   private String diagCd;
   @Excel(
      name = "疾病诊断名称"
   )
   private String diagName;
   @Excel(
      name = "诊断部位"
   )
   private String diagSite;
   @Excel(
      name = "是否疑似诊断"
   )
   private String diagSusp;
   @Excel(
      name = "诊断医师编码"
   )
   private String diaDocCd;
   @Excel(
      name = "诊断医师名称"
   )
   private String diaDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "诊断日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date diaDate;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date creDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date altDate;
   @Excel(
      name = "诊断科室编码"
   )
   private String diagDeptCd;
   @Excel(
      name = "诊断科室名称"
   )
   private String diagDeptName;
   @Excel(
      name = "入院病情编码"
   )
   private String inhosCondCd;
   @Excel(
      name = "治疗效果编码"
   )
   private String outcomeCd;
   @Excel(
      name = "治疗效果名称"
   )
   private String outcomeName;
   @Excel(
      name = "诊断附加编码"
   )
   private String diagExCd;
   private Boolean editFlag;
   @Excel(
      name = "入院病情名称"
   )
   private String inhosCondName;
   private String firstDiagFlag;
   private String deathDiagFlag;

   public String getFirstDiagFlag() {
      return this.firstDiagFlag;
   }

   public void setFirstDiagFlag(String firstDiagFlag) {
      this.firstDiagFlag = firstDiagFlag;
   }

   public String getDeathDiagFlag() {
      return this.deathDiagFlag;
   }

   public void setDeathDiagFlag(String deathDiagFlag) {
      this.deathDiagFlag = deathDiagFlag;
   }

   public String getInhosCondName() {
      return this.inhosCondName;
   }

   public void setInhosCondName(String inhosCondName) {
      this.inhosCondName = inhosCondName;
   }

   public Boolean getEditFlag() {
      return this.editFlag;
   }

   public void setEditFlag(Boolean editFlag) {
      this.editFlag = editFlag;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setDiagTypeCd(String diagTypeCd) {
      this.diagTypeCd = diagTypeCd;
   }

   public String getDiagTypeCd() {
      return this.diagTypeCd;
   }

   public void setDiagTypeName(String diagTypeName) {
      this.diagTypeName = diagTypeName;
   }

   public String getDiagTypeName() {
      return this.diagTypeName;
   }

   public void setDiagClass(String diagClass) {
      this.diagClass = diagClass;
   }

   public String getDiagClass() {
      return this.diagClass;
   }

   public void setDiagClassCd(String diagClassCd) {
      this.diagClassCd = diagClassCd;
   }

   public String getDiagClassCd() {
      return this.diagClassCd;
   }

   public void setDiagClassName(String diagClassName) {
      this.diagClassName = diagClassName;
   }

   public String getDiagClassName() {
      return this.diagClassName;
   }

   public void setDiagNo(String diagNo) {
      this.diagNo = diagNo;
   }

   public String getDiagNo() {
      return this.diagNo;
   }

   public void setDiagCd(String diagCd) {
      this.diagCd = diagCd;
   }

   public String getDiagCd() {
      return this.diagCd;
   }

   public void setDiagName(String diagName) {
      this.diagName = diagName;
   }

   public String getDiagName() {
      return this.diagName;
   }

   public void setDiagSite(String diagSite) {
      this.diagSite = diagSite;
   }

   public String getDiagSite() {
      return this.diagSite;
   }

   public void setDiagSusp(String diagSusp) {
      this.diagSusp = diagSusp;
   }

   public String getDiagSusp() {
      return this.diagSusp;
   }

   public void setDiaDocCd(String diaDocCd) {
      this.diaDocCd = diaDocCd;
   }

   public String getDiaDocCd() {
      return this.diaDocCd;
   }

   public void setDiaDocName(String diaDocName) {
      this.diaDocName = diaDocName;
   }

   public String getDiaDocName() {
      return this.diaDocName;
   }

   public void setDiaDate(Date diaDate) {
      this.diaDate = diaDate;
   }

   public Date getDiaDate() {
      return this.diaDate;
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

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setDiagDeptCd(String diagDeptCd) {
      this.diagDeptCd = diagDeptCd;
   }

   public String getDiagDeptCd() {
      return this.diagDeptCd;
   }

   public void setDiagDeptName(String diagDeptName) {
      this.diagDeptName = diagDeptName;
   }

   public String getDiagDeptName() {
      return this.diagDeptName;
   }

   public void setInhosCondCd(String inhosCondCd) {
      this.inhosCondCd = inhosCondCd;
   }

   public String getInhosCondCd() {
      return this.inhosCondCd;
   }

   public void setOutcomeCd(String outcomeCd) {
      this.outcomeCd = outcomeCd;
   }

   public String getOutcomeCd() {
      return this.outcomeCd;
   }

   public void setOutcomeName(String outcomeName) {
      this.outcomeName = outcomeName;
   }

   public String getOutcomeName() {
      return this.outcomeName;
   }

   public void setDiagExCd(String diagExCd) {
      this.diagExCd = diagExCd;
   }

   public String getDiagExCd() {
      return this.diagExCd;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("orgName", this.getOrgName()).append("patientMainId", this.getPatientMainId()).append("patientId", this.getPatientId()).append("diagTypeCd", this.getDiagTypeCd()).append("diagTypeName", this.getDiagTypeName()).append("diagClass", this.getDiagClass()).append("diagClassCd", this.getDiagClassCd()).append("diagClassName", this.getDiagClassName()).append("diagNo", this.getDiagNo()).append("diagCd", this.getDiagCd()).append("diagName", this.getDiagName()).append("diagSite", this.getDiagSite()).append("diagSusp", this.getDiagSusp()).append("diaDocCd", this.getDiaDocCd()).append("diaDocName", this.getDiaDocName()).append("diaDate", this.getDiaDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("diagDeptCd", this.getDiagDeptCd()).append("diagDeptName", this.getDiagDeptName()).append("inhosCondCd", this.getInhosCondCd()).append("outcomeCd", this.getOutcomeCd()).append("outcomeName", this.getOutcomeName()).append("diagExCd", this.getDiagExCd()).append("inhosCondName", this.getInhosCondName()).toString();
   }
}
