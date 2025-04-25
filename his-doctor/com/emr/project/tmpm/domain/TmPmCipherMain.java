package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmPmCipherMain extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构编码 "
   )
   private String hospitalCode;
   private String cipherCd;
   @Excel(
      name = "协定处方名称"
   )
   private String cipherName;
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
      name = "用法编码"
   )
   private String usageWayCd;
   @Excel(
      name = "用法名称"
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
      name = "草药引子"
   )
   private String herbalIntr;
   @Excel(
      name = "煎药方式",
      readConverterExp = "1=自煎；2代煎-人工煎药；3代煎-煎药机煎药"
   )
   private String decoctMethod;
   @Excel(
      name = "医嘱说明"
   )
   private String oederDesc;
   @Excel(
      name = "是否膏药"
   )
   private String plasterFlag;
   @Excel(
      name = "草药剂数"
   )
   private BigDecimal herbalDose;
   @Excel(
      name = "使用标志((0禁用；1使用）"
   )
   private String enabled;
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
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date altDate;

   public TmPmCipherMain() {
   }

   public TmPmCipherMain(HerbSaveVo herbSaveVo) {
      this.cipherName = herbSaveVo.getCipherName();
      this.inputstrphtc = herbSaveVo.getInputstrphtc();
      this.shareType = herbSaveVo.getShareType();
      this.usageWayCd = herbSaveVo.getItemOrderUsageWay();
      this.usageWayName = herbSaveVo.getItemOrderUsageWayName();
      this.freqCd = herbSaveVo.getItemOrderFreq();
      this.freqName = herbSaveVo.getItemOrderFreqName();
      this.herbalIntr = herbSaveVo.getHerbalIntr();
      this.decoctMethod = herbSaveVo.getDecoctMethod();
      this.oederDesc = herbSaveVo.getOederDesc();
      this.plasterFlag = herbSaveVo.getPlasterFlag();
      this.herbalDose = herbSaveVo.getHerbalDose();
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setCipherCd(String cipherCd) {
      this.cipherCd = cipherCd;
   }

   public String getCipherCd() {
      return this.cipherCd;
   }

   public void setCipherName(String cipherName) {
      this.cipherName = cipherName;
   }

   public String getCipherName() {
      return this.cipherName;
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

   public void setUsageWayCd(String usageWayCd) {
      this.usageWayCd = usageWayCd;
   }

   public String getUsageWayCd() {
      return this.usageWayCd;
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

   public void setHerbalIntr(String herbalIntr) {
      this.herbalIntr = herbalIntr;
   }

   public String getHerbalIntr() {
      return this.herbalIntr;
   }

   public void setDecoctMethod(String decoctMethod) {
      this.decoctMethod = decoctMethod;
   }

   public String getDecoctMethod() {
      return this.decoctMethod;
   }

   public void setOederDesc(String oederDesc) {
      this.oederDesc = oederDesc;
   }

   public String getOederDesc() {
      return this.oederDesc;
   }

   public void setPlasterFlag(String plasterFlag) {
      this.plasterFlag = plasterFlag;
   }

   public String getPlasterFlag() {
      return this.plasterFlag;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }

   public String getEnabled() {
      return this.enabled;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("cipherCd", this.getCipherCd()).append("cipherName", this.getCipherName()).append("inputstrphtc", this.getInputstrphtc()).append("shareType", this.getShareType()).append("shareObject", this.getShareObject()).append("usageWayCd", this.getUsageWayCd()).append("usageWayName", this.getUsageWayName()).append("freqCd", this.getFreqCd()).append("freqName", this.getFreqName()).append("herbalIntr", this.getHerbalIntr()).append("decoctMethod", this.getDecoctMethod()).append("oederDesc", this.getOederDesc()).append("plasterFlag", this.getPlasterFlag()).append("herbalDose", this.getHerbalDose()).append("enabled", this.getEnabled()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
