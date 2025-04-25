package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmPmOrderSetDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构编码 "
   )
   private String hospitalCode;
   private Long id;
   @Excel(
      name = "组套编码"
   )
   private String setCd;
   @Excel(
      name = "项目类别编码",
      readConverterExp = "药=疗、处置、检查、检验等"
   )
   private String itemClassCd;
   @Excel(
      name = "组号"
   )
   private Integer groupNo;
   @Excel(
      name = "组内序号"
   )
   private String groupSort;
   @Excel(
      name = "是否主医嘱",
      readConverterExp = "1=主医嘱；2子医嘱"
   )
   private String masterOrder;
   @Excel(
      name = "项目编码"
   )
   private String itemCd;
   @Excel(
      name = "临床项目名称"
   )
   private String itemName;
   @Excel(
      name = "规格"
   )
   private String standard;
   @Excel(
      name = "检查部位代码"
   )
   private String examPartCd;
   @Excel(
      name = "检查部位名称"
   )
   private String examPartName;
   @Excel(
      name = "标本代码"
   )
   private String specCd;
   @Excel(
      name = "标本名称"
   )
   private String specName;
   @Excel(
      name = "实际用量"
   )
   private BigDecimal actualUseage;
   @Excel(
      name = "用量单位 "
   )
   private String usageUnit;
   @Excel(
      name = "用法"
   )
   private String itemOrderUsageWay;
   @Excel(
      name = "用药途径名称"
   )
   private String usageWayName;
   @Excel(
      name = "频率编码"
   )
   private String freqCd;
   @Excel(
      name = "频率名称"
   )
   private String freqName;
   @Excel(
      name = "用药天数(用于解决口服药领药问题)"
   )
   private BigDecimal usageDays;
   @Excel(
      name = "药品剂型"
   )
   private String drugForm;
   @Excel(
      name = "药品类型"
   )
   private String drugClassCode;
   @Excel(
      name = "抗菌药物使用目的(1：治疗性用药；2：预防性用药)"
   )
   private String purposeAntimicrobialUse;
   @Excel(
      name = "滴速"
   )
   private String drippingSpeed;
   @Excel(
      name = "科室编码"
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
   private String itemOrderUsageWayName;
   private String itemFlag;
   private String doctorInstructions;
   private String stockNo;
   private String documentTypeNo;
   private BigDecimal price;
   private String purposeAntimicrobialUseName;

   public String getPurposeAntimicrobialUseName() {
      return this.purposeAntimicrobialUseName;
   }

   public void setPurposeAntimicrobialUseName(String purposeAntimicrobialUseName) {
      this.purposeAntimicrobialUseName = purposeAntimicrobialUseName;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public String getItemFlag() {
      return this.itemFlag;
   }

   public void setItemFlag(String itemFlag) {
      this.itemFlag = itemFlag;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getItemOrderUsageWayName() {
      return this.itemOrderUsageWayName;
   }

   public void setItemOrderUsageWayName(String itemOrderUsageWayName) {
      this.itemOrderUsageWayName = itemOrderUsageWayName;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
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

   public void setItemClassCd(String itemClassCd) {
      this.itemClassCd = itemClassCd;
   }

   public String getItemClassCd() {
      return this.itemClassCd;
   }

   public void setGroupNo(Integer groupNo) {
      this.groupNo = groupNo;
   }

   public Integer getGroupNo() {
      return this.groupNo;
   }

   public String getGroupSort() {
      return this.groupSort;
   }

   public void setGroupSort(String groupSort) {
      this.groupSort = groupSort;
   }

   public void setMasterOrder(String masterOrder) {
      this.masterOrder = masterOrder;
   }

   public String getMasterOrder() {
      return this.masterOrder;
   }

   public void setItemCd(String itemCd) {
      this.itemCd = itemCd;
   }

   public String getItemCd() {
      return this.itemCd;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setExamPartCd(String examPartCd) {
      this.examPartCd = examPartCd;
   }

   public String getExamPartCd() {
      return this.examPartCd;
   }

   public void setExamPartName(String examPartName) {
      this.examPartName = examPartName;
   }

   public String getExamPartName() {
      return this.examPartName;
   }

   public void setSpecCd(String specCd) {
      this.specCd = specCd;
   }

   public String getSpecCd() {
      return this.specCd;
   }

   public void setSpecName(String specName) {
      this.specName = specName;
   }

   public String getSpecName() {
      return this.specName;
   }

   public void setActualUseage(BigDecimal actualUseage) {
      this.actualUseage = actualUseage;
   }

   public BigDecimal getActualUseage() {
      return this.actualUseage;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setItemOrderUsageWay(String itemOrderUsageWay) {
      this.itemOrderUsageWay = itemOrderUsageWay;
   }

   public String getItemOrderUsageWay() {
      return this.itemOrderUsageWay;
   }

   public void setUsageWayName(String usageWayName) {
      this.usageWayName = usageWayName;
   }

   public String getUsageWayName() {
      return this.usageWayName;
   }

   public void setFreqCd(String freqCd) {
      this.freqCd = freqCd;
   }

   public String getFreqCd() {
      return this.freqCd;
   }

   public void setFreqName(String freqName) {
      this.freqName = freqName;
   }

   public String getFreqName() {
      return this.freqName;
   }

   public void setUsageDays(BigDecimal usageDays) {
      this.usageDays = usageDays;
   }

   public BigDecimal getUsageDays() {
      return this.usageDays;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setPurposeAntimicrobialUse(String purposeAntimicrobialUse) {
      this.purposeAntimicrobialUse = purposeAntimicrobialUse;
   }

   public String getPurposeAntimicrobialUse() {
      return this.purposeAntimicrobialUse;
   }

   public void setDrippingSpeed(String drippingSpeed) {
      this.drippingSpeed = drippingSpeed;
   }

   public String getDrippingSpeed() {
      return this.drippingSpeed;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("id", this.getId()).append("setCd", this.getSetCd()).append("itemClassCd", this.getItemClassCd()).append("groupNo", this.getGroupNo()).append("groupSort", this.getGroupSort()).append("masterOrder", this.getMasterOrder()).append("itemCd", this.getItemCd()).append("itemName", this.getItemName()).append("standard", this.getStandard()).append("examPartCd", this.getExamPartCd()).append("examPartName", this.getExamPartName()).append("specCd", this.getSpecCd()).append("specName", this.getSpecName()).append("actualUseage", this.getActualUseage()).append("usageUnit", this.getUsageUnit()).append("itemOrderUsageWay", this.getItemOrderUsageWay()).append("usageWayName", this.getUsageWayName()).append("freqCd", this.getFreqCd()).append("freqName", this.getFreqName()).append("usageDays", this.getUsageDays()).append("drugForm", this.getDrugForm()).append("drugClassCode", this.getDrugClassCode()).append("purposeAntimicrobialUse", this.getPurposeAntimicrobialUse()).append("drippingSpeed", this.getDrippingSpeed()).append("execDeptCd", this.getExecDeptCd()).append("execDeptName", this.getExecDeptName()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("itemOrderUsageWayName", this.getItemOrderUsageWayName()).append("itemFlag", this.getItemFlag()).append("doctorInstructions", this.getDoctorInstructions()).append("stockNo", this.getStockNo()).append("documentTypeNo", this.getDocumentTypeNo()).toString();
   }
}
