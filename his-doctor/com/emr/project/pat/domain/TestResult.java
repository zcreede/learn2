package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestResult extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String id;
   @Excel(
      name = "报告ID"
   )
   private String reportId;
   @Excel(
      name = "报告项目ID"
   )
   private String testItemId;
   @Excel(
      name = "检验项目代码"
   )
   private String testItemCd;
   @Excel(
      name = "检验项目名称"
   )
   private String testItemName;
   @Excel(
      name = "细菌标志"
   )
   private String bactFlag;
   @Excel(
      name = "检验报告结果"
   )
   private String testResExp;
   @Excel(
      name = "大文本结果"
   )
   private String tesTxt;
   @Excel(
      name = "结果异常标志"
   )
   private String normalSign2;
   @Excel(
      name = "结果值单位"
   )
   private String valueUnit2;
   @Excel(
      name = "正常值范围"
   )
   private String valueRange2;
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
      name = "危急值结果标志"
   )
   private String alterResFlag;
   @Excel(
      name = "危急重值上限"
   )
   private String ucl;
   @Excel(
      name = "危急重值下限"
   )
   private String lcl;
   @Excel(
      name = "打印序号"
   )
   private String printOrder;
   private String delFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "打印序号",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date resultDateTime;

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return this.id;
   }

   public void setReportId(String reportId) {
      this.reportId = reportId;
   }

   public String getReportId() {
      return this.reportId;
   }

   public void setTestItemId(String testItemId) {
      this.testItemId = testItemId;
   }

   public String getTestItemId() {
      return this.testItemId;
   }

   public void setTestItemCd(String testItemCd) {
      this.testItemCd = testItemCd;
   }

   public String getTestItemCd() {
      return this.testItemCd;
   }

   public void setTestItemName(String testItemName) {
      this.testItemName = testItemName;
   }

   public String getTestItemName() {
      return this.testItemName;
   }

   public void setBactFlag(String bactFlag) {
      this.bactFlag = bactFlag;
   }

   public String getBactFlag() {
      return this.bactFlag;
   }

   public void setTestResExp(String testResExp) {
      this.testResExp = testResExp;
   }

   public String getTestResExp() {
      return this.testResExp;
   }

   public void setTesTxt(String tesTxt) {
      this.tesTxt = tesTxt;
   }

   public String getTesTxt() {
      return this.tesTxt;
   }

   public void setNormalSign2(String normalSign2) {
      this.normalSign2 = normalSign2;
   }

   public String getNormalSign2() {
      return this.normalSign2;
   }

   public void setValueUnit2(String valueUnit2) {
      this.valueUnit2 = valueUnit2;
   }

   public String getValueUnit2() {
      return this.valueUnit2;
   }

   public void setValueRange2(String valueRange2) {
      this.valueRange2 = valueRange2;
   }

   public String getValueRange2() {
      return this.valueRange2;
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

   public void setAlterResFlag(String alterResFlag) {
      this.alterResFlag = alterResFlag;
   }

   public String getAlterResFlag() {
      return this.alterResFlag;
   }

   public void setUcl(String ucl) {
      this.ucl = ucl;
   }

   public String getUcl() {
      return this.ucl;
   }

   public void setLcl(String lcl) {
      this.lcl = lcl;
   }

   public String getLcl() {
      return this.lcl;
   }

   public void setPrintOrder(String printOrder) {
      this.printOrder = printOrder;
   }

   public String getPrintOrder() {
      return this.printOrder;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setResultDateTime(Date resultDateTime) {
      this.resultDateTime = resultDateTime;
   }

   public Date getResultDateTime() {
      return this.resultDateTime;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("reportId", this.getReportId()).append("testItemId", this.getTestItemId()).append("testItemCd", this.getTestItemCd()).append("testItemName", this.getTestItemName()).append("bactFlag", this.getBactFlag()).append("testResExp", this.getTestResExp()).append("tesTxt", this.getTesTxt()).append("normalSign2", this.getNormalSign2()).append("valueUnit2", this.getValueUnit2()).append("valueRange2", this.getValueRange2()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("alterResFlag", this.getAlterResFlag()).append("ucl", this.getUcl()).append("lcl", this.getLcl()).append("printOrder", this.getPrintOrder()).append("delFlag", this.getDelFlag()).append("resultDateTime", this.getResultDateTime()).toString();
   }
}
