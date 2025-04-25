package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmPmCipherDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "协定处方编码"
   )
   private String cipherCd;
   @Excel(
      name = "协定处方序号"
   )
   private String cipherSort;
   @Excel(
      name = "药品编码"
   )
   private String drugCd;
   @Excel(
      name = "药品名称"
   )
   private String drugName;
   @Excel(
      name = "规格"
   )
   private String standard;
   @Excel(
      name = "单位"
   )
   private String unit;
   @Excel(
      name = "实际用量"
   )
   private BigDecimal actualUsage;
   @Excel(
      name = "总量"
   )
   private BigDecimal totalDose;
   @Excel(
      name = "医师说明"
   )
   private String docNote;
   @Excel(
      name = "用量单位"
   )
   private String usageUnit;
   @Excel(
      name = "执行科室编码"
   )
   private String execDeptCd;
   @Excel(
      name = "执行科室名称"
   )
   private String execDeptName;
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
   private String doseCoef;
   private BigDecimal orderTotal;
   private BigDecimal price;

   public TmPmCipherDetail() {
   }

   public TmPmCipherDetail(TdPaOrderDetail tdPaOrderDetail) {
      this.drugCd = tdPaOrderDetail.getChargeNo();
      this.drugName = tdPaOrderDetail.getChargeName();
      this.standard = tdPaOrderDetail.getStandard();
      this.unit = tdPaOrderDetail.getUnit();
      this.actualUsage = tdPaOrderDetail.getOrderActualUsage();
      this.totalDose = tdPaOrderDetail.getOrderTotalDose();
      this.usageUnit = tdPaOrderDetail.getUsageUnit();
      this.doseCoef = tdPaOrderDetail.getDoseCoef();
      this.orderTotal = tdPaOrderDetail.getOrderTotal();
      this.price = tdPaOrderDetail.getPrice();
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getDoseCoef() {
      return this.doseCoef;
   }

   public void setDoseCoef(String doseCoef) {
      this.doseCoef = doseCoef;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setCipherCd(String cipherCd) {
      this.cipherCd = cipherCd;
   }

   public String getCipherCd() {
      return this.cipherCd;
   }

   public void setCipherSort(String cipherSort) {
      this.cipherSort = cipherSort;
   }

   public String getCipherSort() {
      return this.cipherSort;
   }

   public void setDrugCd(String drugCd) {
      this.drugCd = drugCd;
   }

   public String getDrugCd() {
      return this.drugCd;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setActualUsage(BigDecimal actualUsage) {
      this.actualUsage = actualUsage;
   }

   public BigDecimal getActualUsage() {
      return this.actualUsage;
   }

   public void setTotalDose(BigDecimal totalDose) {
      this.totalDose = totalDose;
   }

   public BigDecimal getTotalDose() {
      return this.totalDose;
   }

   public void setDocNote(String docNote) {
      this.docNote = docNote;
   }

   public String getDocNote() {
      return this.docNote;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setExecDeptCd(String execDeptCd) {
      this.execDeptCd = execDeptCd;
   }

   public String getExecDeptCd() {
      return this.execDeptCd;
   }

   public void setExecDeptName(String execDeptName) {
      this.execDeptName = execDeptName;
   }

   public String getExecDeptName() {
      return this.execDeptName;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("cipherCd", this.getCipherCd()).append("cipherSort", this.getCipherSort()).append("drugCd", this.getDrugCd()).append("drugName", this.getDrugName()).append("standard", this.getStandard()).append("unit", this.getUnit()).append("actualUsage", this.getActualUsage()).append("totalDose", this.getTotalDose()).append("docNote", this.getDocNote()).append("usageUnit", this.getUsageUnit()).append("execDeptCd", this.getExecDeptCd()).append("execDeptName", this.getExecDeptName()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("price", this.getPrice()).append("doseCoef", this.getDoseCoef()).append("orderTotal", this.getOrderTotal()).toString();
   }
}
