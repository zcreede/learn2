package com.emr.project.tmpa.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderSig extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构编码"
   )
   private String hospitalCode;
   private String sigCd;
   @Excel(
      name = "用法名称"
   )
   private String sigName;
   @Excel(
      name = "用法显示名称"
   )
   private String sigShowName;
   @Excel(
      name = "助记码"
   )
   private String inputstrphtc;
   @Excel(
      name = "排序序号"
   )
   private Long sort;
   @Excel(
      name = "是否有子医嘱",
      readConverterExp = "0=没有；1有子医嘱"
   )
   private String hasSub;
   @Excel(
      name = "医嘱类型",
      readConverterExp = "0=全部医嘱；1长期医嘱；2临时医嘱"
   )
   private String orderType;
   @Excel(
      name = "药品类型",
      readConverterExp = "0=全部；1西药；3草药"
   )
   private String drugType;
   @Excel(
      name = "执行终端(1PC端；2移动端）"
   )
   private String execTerm;
   @Excel(
      name = "使用标志"
   )
   private String enabled;
   @Excel(
      name = "标准用法编码"
   )
   private String standardCd;
   @Excel(
      name = "医嘱本上是否显示"
   )
   private String orderBookShow;
   private String ivDripFlag;
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
   private String docCd;

   public String getIvDripFlag() {
      return this.ivDripFlag;
   }

   public void setIvDripFlag(String ivDripFlag) {
      this.ivDripFlag = ivDripFlag;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setSigCd(String sigCd) {
      this.sigCd = sigCd;
   }

   public String getSigCd() {
      return this.sigCd;
   }

   public void setSigName(String sigName) {
      this.sigName = sigName;
   }

   public String getSigName() {
      return this.sigName;
   }

   public void setSigShowName(String sigShowName) {
      this.sigShowName = sigShowName;
   }

   public String getSigShowName() {
      return this.sigShowName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
   }

   public void setHasSub(String hasSub) {
      this.hasSub = hasSub;
   }

   public String getHasSub() {
      return this.hasSub;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setDrugType(String drugType) {
      this.drugType = drugType;
   }

   public String getDrugType() {
      return this.drugType;
   }

   public void setExecTerm(String execTerm) {
      this.execTerm = execTerm;
   }

   public String getExecTerm() {
      return this.execTerm;
   }

   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }

   public String getEnabled() {
      return this.enabled;
   }

   public void setStandardCd(String standardCd) {
      this.standardCd = standardCd;
   }

   public String getStandardCd() {
      return this.standardCd;
   }

   public void setOrderBookShow(String orderBookShow) {
      this.orderBookShow = orderBookShow;
   }

   public String getOrderBookShow() {
      return this.orderBookShow;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("sigCd", this.getSigCd()).append("sigName", this.getSigName()).append("sigShowName", this.getSigShowName()).append("inputstrphtc", this.getInputstrphtc()).append("sort", this.getSort()).append("hasSub", this.getHasSub()).append("orderType", this.getOrderType()).append("drugType", this.getDrugType()).append("execTerm", this.getExecTerm()).append("enabled", this.getEnabled()).append("standardCd", this.getStandardCd()).append("orderBookShow", this.getOrderBookShow()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("docCd", this.getDocCd()).toString();
   }
}
