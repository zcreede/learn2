package com.emr.project.tmpa.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderFreq extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构编码"
   )
   private String hospitalCode;
   @Excel(
      name = "频率编码"
   )
   private String freqCd;
   @Excel(
      name = "频率名称"
   )
   private String freqName;
   @Excel(
      name = "显示名称"
   )
   private String freqShowName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "频率值",
      readConverterExp = "一=天执行次数"
   )
   private Integer freqValue;
   @Excel(
      name = "频率类型",
      readConverterExp = "0=每天执行；1隔天执行，天数可以配置；3周几执行"
   )
   private String freqType;
   @Excel(
      name = "间隔天数"
   )
   private Integer freqInterDays;
   @Excel(
      name = "每周次执行时间　1|3|5:表示每周３次，每周一，周三，周五的时候使用该医嘱"
   )
   private String weekDay;
   @Excel(
      name = "用药时间",
      readConverterExp = "多=一天多次用药的，可以指定每次用药时间，如8|14|20|。说明一天3次的用药时间分别为8点、14点、20点。如果当天患者11点入院，首次默认次数就为2，当日只有14点和20点用药"
   )
   private String medicTime;
   @Excel(
      name = "启用标志"
   )
   private String enabled;
   @Excel(
      name = "排序"
   )
   private Long sort;
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

   public void setFreqShowName(String freqShowName) {
      this.freqShowName = freqShowName;
   }

   public String getFreqShowName() {
      return this.freqShowName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setFreqValue(Integer freqValue) {
      this.freqValue = freqValue;
   }

   public Integer getFreqValue() {
      return this.freqValue;
   }

   public void setFreqType(String freqType) {
      this.freqType = freqType;
   }

   public String getFreqType() {
      return this.freqType;
   }

   public void setFreqInterDays(Integer freqInterDays) {
      this.freqInterDays = freqInterDays;
   }

   public Integer getFreqInterDays() {
      return this.freqInterDays;
   }

   public void setWeekDay(String weekDay) {
      this.weekDay = weekDay;
   }

   public String getWeekDay() {
      return this.weekDay;
   }

   public void setMedicTime(String medicTime) {
      this.medicTime = medicTime;
   }

   public String getMedicTime() {
      return this.medicTime;
   }

   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }

   public String getEnabled() {
      return this.enabled;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("freqCd", this.getFreqCd()).append("freqName", this.getFreqName()).append("freqShowName", this.getFreqShowName()).append("inputstrphtc", this.getInputstrphtc()).append("freqValue", this.getFreqValue()).append("freqType", this.getFreqType()).append("freqInterDays", this.getFreqInterDays()).append("weekDay", this.getWeekDay()).append("medicTime", this.getMedicTime()).append("enabled", this.getEnabled()).append("sort", this.getSort()).append("orderType", this.getOrderType()).append("drugType", this.getDrugType()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
