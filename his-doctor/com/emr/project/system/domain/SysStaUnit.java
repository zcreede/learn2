package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysStaUnit extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "数据元标识符"
   )
   private String unitSign;
   @Excel(
      name = "数据元名称"
   )
   private String unitName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "五笔码"
   )
   private String inputstrelse;
   @Excel(
      name = "定义"
   )
   private String define;
   @Excel(
      name = "数据类型"
   )
   private String dataType;
   @Excel(
      name = "表示格式"
   )
   private String showForm;
   @Excel(
      name = "数据元允许值"
   )
   private String allVal;
   @Excel(
      name = "值域分类ID"
   )
   private String valTypeId;
   @Excel(
      name = "分类ID"
   )
   private String clasId;
   @Excel(
      name = "分类名称"
   )
   private String clasName;
   @Excel(
      name = "版本号"
   )
   private String verNum;
   @Excel(
      name = "注册机构"
   )
   private String regAuth;
   @Excel(
      name = "相关环境"
   )
   private String relEnvi;
   @Excel(
      name = "分类模式"
   )
   private String clasMode;
   @Excel(
      name = "主管机构"
   )
   private String resOrg;
   @Excel(
      name = "注册状态"
   )
   private String regState;
   @Excel(
      name = "提交机构"
   )
   private String subOrg;
   @Excel(
      name = "发布状态"
   )
   private String issState;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "发布时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date issTime;
   @Excel(
      name = "发布人编码"
   )
   private String issCd;
   @Excel(
      name = "发布人姓名"
   )
   private String issName;
   @Excel(
      name = "1：标准数据元；2：自定义数据元"
   )
   private String unitSource;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
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

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setUnitSign(String unitSign) {
      this.unitSign = unitSign;
   }

   public String getUnitSign() {
      return this.unitSign;
   }

   public void setUnitName(String unitName) {
      this.unitName = unitName;
   }

   public String getUnitName() {
      return this.unitName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrelse(String inputstrelse) {
      this.inputstrelse = inputstrelse;
   }

   public String getInputstrelse() {
      return this.inputstrelse;
   }

   public void setDefine(String define) {
      this.define = define;
   }

   public String getDefine() {
      return this.define;
   }

   public void setDataType(String dataType) {
      this.dataType = dataType;
   }

   public String getDataType() {
      return this.dataType;
   }

   public void setShowForm(String showForm) {
      this.showForm = showForm;
   }

   public String getShowForm() {
      return this.showForm;
   }

   public void setAllVal(String allVal) {
      this.allVal = allVal;
   }

   public String getAllVal() {
      return this.allVal;
   }

   public void setValTypeId(String valTypeId) {
      this.valTypeId = valTypeId;
   }

   public String getValTypeId() {
      return this.valTypeId;
   }

   public void setClasId(String clasId) {
      this.clasId = clasId;
   }

   public String getClasId() {
      return this.clasId;
   }

   public void setClasName(String clasName) {
      this.clasName = clasName;
   }

   public String getClasName() {
      return this.clasName;
   }

   public void setVerNum(String verNum) {
      this.verNum = verNum;
   }

   public String getVerNum() {
      return this.verNum;
   }

   public void setRegAuth(String regAuth) {
      this.regAuth = regAuth;
   }

   public String getRegAuth() {
      return this.regAuth;
   }

   public void setRelEnvi(String relEnvi) {
      this.relEnvi = relEnvi;
   }

   public String getRelEnvi() {
      return this.relEnvi;
   }

   public void setClasMode(String clasMode) {
      this.clasMode = clasMode;
   }

   public String getClasMode() {
      return this.clasMode;
   }

   public void setResOrg(String resOrg) {
      this.resOrg = resOrg;
   }

   public String getResOrg() {
      return this.resOrg;
   }

   public void setRegState(String regState) {
      this.regState = regState;
   }

   public String getRegState() {
      return this.regState;
   }

   public void setSubOrg(String subOrg) {
      this.subOrg = subOrg;
   }

   public String getSubOrg() {
      return this.subOrg;
   }

   public void setIssState(String issState) {
      this.issState = issState;
   }

   public String getIssState() {
      return this.issState;
   }

   public void setIssTime(Date issTime) {
      this.issTime = issTime;
   }

   public Date getIssTime() {
      return this.issTime;
   }

   public void setIssCd(String issCd) {
      this.issCd = issCd;
   }

   public String getIssCd() {
      return this.issCd;
   }

   public void setIssName(String issName) {
      this.issName = issName;
   }

   public String getIssName() {
      return this.issName;
   }

   public void setUnitSource(String unitSource) {
      this.unitSource = unitSource;
   }

   public String getUnitSource() {
      return this.unitSource;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("unitSign", this.getUnitSign()).append("unitName", this.getUnitName()).append("inputstrphtc", this.getInputstrphtc()).append("inputstrelse", this.getInputstrelse()).append("define", this.getDefine()).append("dataType", this.getDataType()).append("showForm", this.getShowForm()).append("allVal", this.getAllVal()).append("valTypeId", this.getValTypeId()).append("clasId", this.getClasId()).append("clasName", this.getClasName()).append("verNum", this.getVerNum()).append("regAuth", this.getRegAuth()).append("relEnvi", this.getRelEnvi()).append("clasMode", this.getClasMode()).append("resOrg", this.getResOrg()).append("regState", this.getRegState()).append("subOrg", this.getSubOrg()).append("issState", this.getIssState()).append("issTime", this.getIssTime()).append("issCd", this.getIssCd()).append("issName", this.getIssName()).append("unitSource", this.getUnitSource()).append("validFlag", this.getValidFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
