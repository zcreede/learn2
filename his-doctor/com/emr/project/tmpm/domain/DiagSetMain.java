package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiagSetMain extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String setCd;
   @Excel(
      name = "医疗机构编码 "
   )
   private String hospitalCode;
   @Excel(
      name = "组套名称"
   )
   private String setName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "共享类型",
      readConverterExp = "1=全院共享；2科室,；=3个人"
   )
   private String shareType;
   @Excel(
      name = "共享对象",
      readConverterExp = "全=运共享存000000，科室共享存科室编号，个人使用存医师编号"
   )
   private String shareObject;
   @Excel(
      name = "使用标志((0禁用；1使用）"
   )
   private String enabled;
   @Excel(
      name = "诊断分类",
      readConverterExp = "西=医、中医"
   )
   private String diagClass;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人项目"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建时间",
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
   private String diagTypeCd;
   private String diagTypeName;

   public String getDiagTypeCd() {
      return this.diagTypeCd;
   }

   public void setDiagTypeCd(String diagTypeCd) {
      this.diagTypeCd = diagTypeCd;
   }

   public String getDiagTypeName() {
      return this.diagTypeName;
   }

   public void setDiagTypeName(String diagTypeName) {
      this.diagTypeName = diagTypeName;
   }

   public void setSetCd(String setCd) {
      this.setCd = setCd;
   }

   public String getSetCd() {
      return this.setCd;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setSetName(String setName) {
      this.setName = setName;
   }

   public String getSetName() {
      return this.setName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setShareType(String shareType) {
      this.shareType = shareType;
   }

   public String getShareType() {
      return this.shareType;
   }

   public void setShareObject(String shareObject) {
      this.shareObject = shareObject;
   }

   public String getShareObject() {
      return this.shareObject;
   }

   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }

   public String getEnabled() {
      return this.enabled;
   }

   public void setDiagClass(String diagClass) {
      this.diagClass = diagClass;
   }

   public String getDiagClass() {
      return this.diagClass;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("setCd", this.getSetCd()).append("hospitalCode", this.getHospitalCode()).append("setName", this.getSetName()).append("inputstrphtc", this.getInputstrphtc()).append("shareType", this.getShareType()).append("shareObject", this.getShareObject()).append("enabled", this.getEnabled()).append("diagClass", this.getDiagClass()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("sortNumber", this.getSortNumber()).append("diagTypeCd", this.getDiagTypeCd()).append("diagTypeName", this.getDiagTypeName()).toString();
   }
}
