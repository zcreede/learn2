package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpDia extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String diaId;
   @Excel(
      name = "病案ID"
   )
   private String recordId;
   @Excel(
      name = "诊断类型(XY：西医；ZY：中医）"
   )
   private String diaType;
   @Excel(
      name = "诊断序号"
   )
   private String diaNo;
   @Excel(
      name = "疾病诊断名称"
   )
   private String diaName;
   @Excel(
      name = "疾病诊断编码"
   )
   private String diaCd;
   @Excel(
      name = "疾病诊断附加码"
   )
   private String diaExCd;
   @Excel(
      name = "入院病情情况"
   )
   private String inhosCond;
   @Excel(
      name = "入院病情情况编号"
   )
   private String inhosCondCd;
   @Excel(
      name = "诊断类别",
      readConverterExp = "主=要诊断、其他诊断、主证、主病等"
   )
   private String diaClass;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
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
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "转归情况代码"
   )
   private String outcomeCd;
   @Excel(
      name = "转归情况"
   )
   private String outcome;
   @Excel(
      name = "诊断分类 ：门诊诊断、入院诊断、出院诊断、损伤中毒原因、病理诊断）"
   )
   private String diaItem;
   @Excel(
      name = "诊断说明(病理诊断的病理号保存到该列）"
   )
   private String diaRem;
   @Excel(
      name = "病案号"
   )
   private String recordNo;
   @Excel(
      name = "住院号或门诊号"
   )
   private String inpNo;
   @Excel(
      name = "住院次数"
   )
   private Long visitId;
   private String patientName;

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Long getVisitId() {
      return this.visitId;
   }

   public void setVisitId(Long visitId) {
      this.visitId = visitId;
   }

   public void setDiaId(String diaId) {
      this.diaId = diaId;
   }

   public String getDiaId() {
      return this.diaId;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setDiaType(String diaType) {
      this.diaType = diaType;
   }

   public String getDiaType() {
      return this.diaType;
   }

   public void setDiaNo(String diaNo) {
      this.diaNo = diaNo;
   }

   public String getDiaNo() {
      return this.diaNo;
   }

   public void setDiaName(String diaName) {
      this.diaName = diaName;
   }

   public String getDiaName() {
      return this.diaName;
   }

   public void setDiaCd(String diaCd) {
      this.diaCd = diaCd;
   }

   public String getDiaCd() {
      return this.diaCd;
   }

   public void setDiaExCd(String diaExCd) {
      this.diaExCd = diaExCd;
   }

   public String getDiaExCd() {
      return this.diaExCd;
   }

   public void setInhosCond(String inhosCond) {
      this.inhosCond = inhosCond;
   }

   public String getInhosCond() {
      return this.inhosCond;
   }

   public void setInhosCondCd(String inhosCondCd) {
      this.inhosCondCd = inhosCondCd;
   }

   public String getInhosCondCd() {
      return this.inhosCondCd;
   }

   public void setDiaClass(String diaClass) {
      this.diaClass = diaClass;
   }

   public String getDiaClass() {
      return this.diaClass;
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

   public void setOutcomeCd(String outcomeCd) {
      this.outcomeCd = outcomeCd;
   }

   public String getOutcomeCd() {
      return this.outcomeCd;
   }

   public void setOutcome(String outcome) {
      this.outcome = outcome;
   }

   public String getOutcome() {
      return this.outcome;
   }

   public void setDiaItem(String diaItem) {
      this.diaItem = diaItem;
   }

   public String getDiaItem() {
      return this.diaItem;
   }

   public void setDiaRem(String diaRem) {
      this.diaRem = diaRem;
   }

   public String getDiaRem() {
      return this.diaRem;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("diaId", this.getDiaId()).append("recordId", this.getRecordId()).append("diaType", this.getDiaType()).append("diaNo", this.getDiaNo()).append("diaName", this.getDiaName()).append("diaCd", this.getDiaCd()).append("diaExCd", this.getDiaExCd()).append("inhosCond", this.getInhosCond()).append("inhosCondCd", this.getInhosCondCd()).append("diaClass", this.getDiaClass()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("outcomeCd", this.getOutcomeCd()).append("outcome", this.getOutcome()).append("diaItem", this.getDiaItem()).append("diaRem", this.getDiaRem()).toString();
   }
}
