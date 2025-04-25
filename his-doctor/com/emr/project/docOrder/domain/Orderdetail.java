package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Orderdetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String id;
   private String orgCd;
   @Excel(
      name = "医疗机构名称"
   )
   private String orgName;
   @Excel(
      name = "医嘱主表ID"
   )
   private String orderId;
   @Excel(
      name = "医嘱组号"
   )
   private Long maNo;
   @Excel(
      name = "医嘱编号"
   )
   private String maCd;
   @Excel(
      name = "医嘱组内序号"
   )
   private Long maGroupNo;
   @Excel(
      name = "临床项目编号"
   )
   private String clinProCd;
   @Excel(
      name = "临床项目名称"
   )
   private String clinProName;
   @Excel(
      name = "计价标志"
   )
   private String valFlag;
   @Excel(
      name = "皮试标志",
      readConverterExp = "0=：否；1：是；2：续"
   )
   private String skinTestFlag;
   @Excel(
      name = "附加费编号"
   )
   private String addFeeCd;
   @Excel(
      name = "医嘱重整序号"
   )
   private Long rmaNo;
   @Excel(
      name = "项目编号"
   )
   private String itemCd;
   @Excel(
      name = "项目名称"
   )
   private String itemName;
   @Excel(
      name = "规格"
   )
   private String spec;
   @Excel(
      name = "单位"
   )
   private String unit;
   @Excel(
      name = "数量"
   )
   private Long amount;
   @Excel(
      name = "总量"
   )
   private Long gross;
   @Excel(
      name = "金额"
   )
   private Long money;
   @Excel(
      name = "频率"
   )
   private String rate;
   @Excel(
      name = "用法"
   )
   private String usage;
   @Excel(
      name = "执行科室编号"
   )
   private String exeDeptCd;
   @Excel(
      name = "执行科室"
   )
   private String exeDeptName;
   @Excel(
      name = "自备药品标志"
   )
   private String ownedDrugFlag;
   @Excel(
      name = "单价"
   )
   private Long unitPrice;
   @Excel(
      name = "药品字典编号"
   )
   private String medDictCd;
   @Excel(
      name = "实际用量"
   )
   private Long realDosage;
   @Excel(
      name = "用量单位"
   )
   private String dosageUnit;
   @Excel(
      name = "药品剂型"
   )
   private String drugForm;
   @Excel(
      name = "医院项目编号"
   )
   private String hosItemCd;
   @Excel(
      name = "皮试结果"
   )
   private String skinTestRes;
   @Excel(
      name = "抗菌药物使用目的"
   )
   private String antiUseAim;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
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
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
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
      name = "频率编码"
   )
   private String rateCd;
   @Excel(
      name = "用法编码"
   )
   private String usageCd;
   @Excel(
      name = "死亡标志",
      readConverterExp = "1=：死亡医嘱"
   )
   private String dieFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "审核时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date audittime;
   @Excel(
      name = "出院带药"
   )
   private String dischargemedication;
   @Excel(
      name = "自备药标志"
   )
   private String proprietarymarker;
   @Excel(
      name = "备用标志"
   )
   private String alternatemark;
   @Excel(
      name = "备用标志"
   )
   private String patientId;

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

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public String getOrderId() {
      return this.orderId;
   }

   public void setMaNo(Long maNo) {
      this.maNo = maNo;
   }

   public Long getMaNo() {
      return this.maNo;
   }

   public void setMaCd(String maCd) {
      this.maCd = maCd;
   }

   public String getMaCd() {
      return this.maCd;
   }

   public void setMaGroupNo(Long maGroupNo) {
      this.maGroupNo = maGroupNo;
   }

   public Long getMaGroupNo() {
      return this.maGroupNo;
   }

   public void setClinProCd(String clinProCd) {
      this.clinProCd = clinProCd;
   }

   public String getClinProCd() {
      return this.clinProCd;
   }

   public void setClinProName(String clinProName) {
      this.clinProName = clinProName;
   }

   public String getClinProName() {
      return this.clinProName;
   }

   public void setValFlag(String valFlag) {
      this.valFlag = valFlag;
   }

   public String getValFlag() {
      return this.valFlag;
   }

   public void setSkinTestFlag(String skinTestFlag) {
      this.skinTestFlag = skinTestFlag;
   }

   public String getSkinTestFlag() {
      return this.skinTestFlag;
   }

   public void setAddFeeCd(String addFeeCd) {
      this.addFeeCd = addFeeCd;
   }

   public String getAddFeeCd() {
      return this.addFeeCd;
   }

   public void setRmaNo(Long rmaNo) {
      this.rmaNo = rmaNo;
   }

   public Long getRmaNo() {
      return this.rmaNo;
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

   public void setSpec(String spec) {
      this.spec = spec;
   }

   public String getSpec() {
      return this.spec;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setAmount(Long amount) {
      this.amount = amount;
   }

   public Long getAmount() {
      return this.amount;
   }

   public void setGross(Long gross) {
      this.gross = gross;
   }

   public Long getGross() {
      return this.gross;
   }

   public void setMoney(Long money) {
      this.money = money;
   }

   public Long getMoney() {
      return this.money;
   }

   public void setRate(String rate) {
      this.rate = rate;
   }

   public String getRate() {
      return this.rate;
   }

   public void setUsage(String usage) {
      this.usage = usage;
   }

   public String getUsage() {
      return this.usage;
   }

   public void setExeDeptCd(String exeDeptCd) {
      this.exeDeptCd = exeDeptCd;
   }

   public String getExeDeptCd() {
      return this.exeDeptCd;
   }

   public void setExeDeptName(String exeDeptName) {
      this.exeDeptName = exeDeptName;
   }

   public String getExeDeptName() {
      return this.exeDeptName;
   }

   public void setOwnedDrugFlag(String ownedDrugFlag) {
      this.ownedDrugFlag = ownedDrugFlag;
   }

   public String getOwnedDrugFlag() {
      return this.ownedDrugFlag;
   }

   public void setUnitPrice(Long unitPrice) {
      this.unitPrice = unitPrice;
   }

   public Long getUnitPrice() {
      return this.unitPrice;
   }

   public void setMedDictCd(String medDictCd) {
      this.medDictCd = medDictCd;
   }

   public String getMedDictCd() {
      return this.medDictCd;
   }

   public void setRealDosage(Long realDosage) {
      this.realDosage = realDosage;
   }

   public Long getRealDosage() {
      return this.realDosage;
   }

   public void setDosageUnit(String dosageUnit) {
      this.dosageUnit = dosageUnit;
   }

   public String getDosageUnit() {
      return this.dosageUnit;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setHosItemCd(String hosItemCd) {
      this.hosItemCd = hosItemCd;
   }

   public String getHosItemCd() {
      return this.hosItemCd;
   }

   public void setSkinTestRes(String skinTestRes) {
      this.skinTestRes = skinTestRes;
   }

   public String getSkinTestRes() {
      return this.skinTestRes;
   }

   public void setAntiUseAim(String antiUseAim) {
      this.antiUseAim = antiUseAim;
   }

   public String getAntiUseAim() {
      return this.antiUseAim;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setRateCd(String rateCd) {
      this.rateCd = rateCd;
   }

   public String getRateCd() {
      return this.rateCd;
   }

   public void setUsageCd(String usageCd) {
      this.usageCd = usageCd;
   }

   public String getUsageCd() {
      return this.usageCd;
   }

   public void setDieFlag(String dieFlag) {
      this.dieFlag = dieFlag;
   }

   public String getDieFlag() {
      return this.dieFlag;
   }

   public void setAudittime(Date audittime) {
      this.audittime = audittime;
   }

   public Date getAudittime() {
      return this.audittime;
   }

   public void setDischargemedication(String dischargemedication) {
      this.dischargemedication = dischargemedication;
   }

   public String getDischargemedication() {
      return this.dischargemedication;
   }

   public void setProprietarymarker(String proprietarymarker) {
      this.proprietarymarker = proprietarymarker;
   }

   public String getProprietarymarker() {
      return this.proprietarymarker;
   }

   public void setAlternatemark(String alternatemark) {
      this.alternatemark = alternatemark;
   }

   public String getAlternatemark() {
      return this.alternatemark;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("orgName", this.getOrgName()).append("orderId", this.getOrderId()).append("maNo", this.getMaNo()).append("maCd", this.getMaCd()).append("maGroupNo", this.getMaGroupNo()).append("clinProCd", this.getClinProCd()).append("clinProName", this.getClinProName()).append("valFlag", this.getValFlag()).append("skinTestFlag", this.getSkinTestFlag()).append("addFeeCd", this.getAddFeeCd()).append("rmaNo", this.getRmaNo()).append("itemCd", this.getItemCd()).append("itemName", this.getItemName()).append("spec", this.getSpec()).append("unit", this.getUnit()).append("amount", this.getAmount()).append("gross", this.getGross()).append("money", this.getMoney()).append("rate", this.getRate()).append("usage", this.getUsage()).append("exeDeptCd", this.getExeDeptCd()).append("exeDeptName", this.getExeDeptName()).append("ownedDrugFlag", this.getOwnedDrugFlag()).append("unitPrice", this.getUnitPrice()).append("medDictCd", this.getMedDictCd()).append("realDosage", this.getRealDosage()).append("dosageUnit", this.getDosageUnit()).append("remark", this.getRemark()).append("drugForm", this.getDrugForm()).append("hosItemCd", this.getHosItemCd()).append("skinTestRes", this.getSkinTestRes()).append("antiUseAim", this.getAntiUseAim()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("rateCd", this.getRateCd()).append("usageCd", this.getUsageCd()).append("dieFlag", this.getDieFlag()).append("audittime", this.getAudittime()).append("dischargemedication", this.getDischargemedication()).append("proprietarymarker", this.getProprietarymarker()).append("alternatemark", this.getAlternatemark()).append("patientId", this.getPatientId()).toString();
   }
}
