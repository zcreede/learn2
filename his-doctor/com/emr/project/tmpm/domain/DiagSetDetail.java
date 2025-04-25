package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiagSetDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "组套编码"
   )
   private String setCd;
   @Excel(
      name = "诊断编码"
   )
   private String diagCd;
   @Excel(
      name = "诊断名称"
   )
   private String diagName;
   @Excel(
      name = "诊断说明"
   )
   private String diagRem;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人项目"
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
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "序号"
   )
   private Integer sortNumber;
   private String diagExCd;
   private String diagClassCd;
   private String diagClassName;
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

   public String getDiagClassCd() {
      return this.diagClassCd;
   }

   public void setDiagClassCd(String diagClassCd) {
      this.diagClassCd = diagClassCd;
   }

   public String getDiagClassName() {
      return this.diagClassName;
   }

   public void setDiagClassName(String diagClassName) {
      this.diagClassName = diagClassName;
   }

   public String getDiagExCd() {
      return this.diagExCd;
   }

   public void setDiagExCd(String diagExCd) {
      this.diagExCd = diagExCd;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setSetCd(String setCd) {
      this.setCd = setCd;
   }

   public String getSetCd() {
      return this.setCd;
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

   public void setDiagRem(String diagRem) {
      this.diagRem = diagRem;
   }

   public String getDiagRem() {
      return this.diagRem;
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

   public void setSortNumber(Integer sortNumber) {
      this.sortNumber = sortNumber;
   }

   public Integer getSortNumber() {
      return this.sortNumber;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("setCd", this.getSetCd()).append("diagCd", this.getDiagCd()).append("diagName", this.getDiagName()).append("diagRem", this.getDiagRem()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("sortNumber", this.getSortNumber()).append("diagExCd", this.getDiagExCd()).append("diagClassCd", this.getDiagClassCd()).append("diagClassName", this.getDiagClassName()).toString();
   }
}
