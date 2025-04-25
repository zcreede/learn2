package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcAgiRule extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "规则编号"
   )
   private String ruleCode;
   @Excel(
      name = "规则名称"
   )
   private String ruleName;
   @Excel(
      name = "病历类型编码"
   )
   private String emrTypeCode;
   @Excel(
      name = "病历类型名称"
   )
   private String emrTypeName;
   @Excel(
      name = "规则算法类型：1 程序固化算法 2 根据配置算法执行"
   )
   private String ruleComType;
   @Excel(
      name = "时效事件编号"
   )
   private String agiEvnt;
   @Excel(
      name = "添加方式  1 ：系统预置  2：人工添加"
   )
   private String addMeth;
   @Excel(
      name = "触发逻辑说明"
   )
   private String trigLogicDesc;
   @Excel(
      name = "触发时间"
   )
   private Integer trigTime;
   @Excel(
      name = "触发时间说明"
   )
   private String trigTimeDesc;
   @Excel(
      name = "触发时间 时限单位 小时 H，天 D"
   )
   private String trigTimeUnit;
   @Excel(
      name = "完成时限"
   )
   private Integer finishLimitTime;
   @Excel(
      name = "时限单位 小时，天"
   )
   private String limitTimeUnit;
   @Excel(
      name = "事前质控启用标志"
   )
   private String proValidFlag;
   @Excel(
      name = "事后监控启用标志"
   )
   private String posValidFlag;
   @Excel(
      name = "触发任务类型"
   )
   private String taskType;
   @Excel(
      name = "任务责任对象 1 管床医师  2 事件前责任医师 3  事件的责任医师   4 患者主治医师  5 患者主任医师"
   )
   private String taksDoct;
   @Excel(
      name = "缺陷严重程度 "
   )
   private String defeLevel;
   @Excel(
      name = "完整性校验启用标志"
   )
   private String interVeriValid;
   @Excel(
      name = "超时校验启用标志"
   )
   private String limitTimeVeriValid;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
   private String delFlag;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
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
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   private String taskFinishFlag;
   private String ruleTip;

   public QcAgiRule() {
   }

   public QcAgiRule(String interVeriValid, String validFlag, String delFlag) {
      this.interVeriValid = interVeriValid;
      this.validFlag = validFlag;
      this.delFlag = delFlag;
   }

   public String getTrigTimeDesc() {
      return this.trigTimeDesc;
   }

   public void setTrigTimeDesc(String trigTimeDesc) {
      this.trigTimeDesc = trigTimeDesc;
   }

   public String getTrigTimeUnit() {
      return this.trigTimeUnit;
   }

   public void setTrigTimeUnit(String trigTimeUnit) {
      this.trigTimeUnit = trigTimeUnit;
   }

   public String getTaskFinishFlag() {
      return this.taskFinishFlag;
   }

   public void setTaskFinishFlag(String taskFinishFlag) {
      this.taskFinishFlag = taskFinishFlag;
   }

   public String getRuleTip() {
      return this.ruleTip;
   }

   public void setRuleTip(String ruleTip) {
      this.ruleTip = ruleTip;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setRuleCode(String ruleCode) {
      this.ruleCode = ruleCode;
   }

   public String getRuleCode() {
      return this.ruleCode;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setRuleComType(String ruleComType) {
      this.ruleComType = ruleComType;
   }

   public String getRuleComType() {
      return this.ruleComType;
   }

   public void setAgiEvnt(String agiEvnt) {
      this.agiEvnt = agiEvnt;
   }

   public String getAgiEvnt() {
      return this.agiEvnt;
   }

   public void setAddMeth(String addMeth) {
      this.addMeth = addMeth;
   }

   public String getAddMeth() {
      return this.addMeth;
   }

   public void setTrigLogicDesc(String trigLogicDesc) {
      this.trigLogicDesc = trigLogicDesc;
   }

   public String getTrigLogicDesc() {
      return this.trigLogicDesc;
   }

   public void setTrigTime(Integer trigTime) {
      this.trigTime = trigTime;
   }

   public Integer getTrigTime() {
      return this.trigTime;
   }

   public void setFinishLimitTime(Integer finishLimitTime) {
      this.finishLimitTime = finishLimitTime;
   }

   public Integer getFinishLimitTime() {
      return this.finishLimitTime;
   }

   public void setLimitTimeUnit(String limitTimeUnit) {
      this.limitTimeUnit = limitTimeUnit;
   }

   public String getLimitTimeUnit() {
      return this.limitTimeUnit;
   }

   public void setProValidFlag(String proValidFlag) {
      this.proValidFlag = proValidFlag;
   }

   public String getProValidFlag() {
      return this.proValidFlag;
   }

   public void setPosValidFlag(String posValidFlag) {
      this.posValidFlag = posValidFlag;
   }

   public String getPosValidFlag() {
      return this.posValidFlag;
   }

   public void setTaskType(String taskType) {
      this.taskType = taskType;
   }

   public String getTaskType() {
      return this.taskType;
   }

   public void setTaksDoct(String taksDoct) {
      this.taksDoct = taksDoct;
   }

   public String getTaksDoct() {
      return this.taksDoct;
   }

   public void setDefeLevel(String defeLevel) {
      this.defeLevel = defeLevel;
   }

   public String getDefeLevel() {
      return this.defeLevel;
   }

   public void setInterVeriValid(String interVeriValid) {
      this.interVeriValid = interVeriValid;
   }

   public String getInterVeriValid() {
      return this.interVeriValid;
   }

   public void setLimitTimeVeriValid(String limitTimeVeriValid) {
      this.limitTimeVeriValid = limitTimeVeriValid;
   }

   public String getLimitTimeVeriValid() {
      return this.limitTimeVeriValid;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("ruleCode", this.getRuleCode()).append("ruleName", this.getRuleName()).append("emrTypeCode", this.getEmrTypeCode()).append("emrTypeName", this.getEmrTypeName()).append("ruleComType", this.getRuleComType()).append("agiEvnt", this.getAgiEvnt()).append("addMeth", this.getAddMeth()).append("trigLogicDesc", this.getTrigLogicDesc()).append("trigTime", this.getTrigTime()).append("finishLimitTime", this.getFinishLimitTime()).append("limitTimeUnit", this.getLimitTimeUnit()).append("proValidFlag", this.getProValidFlag()).append("posValidFlag", this.getPosValidFlag()).append("taskType", this.getTaskType()).append("taksDoct", this.getTaksDoct()).append("defeLevel", this.getDefeLevel()).append("interVeriValid", this.getInterVeriValid()).append("limitTimeVeriValid", this.getLimitTimeVeriValid()).append("validFlag", this.getValidFlag()).append("delFlag", this.getDelFlag()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("taskFinishFlag", this.getTaskFinishFlag()).append("ruleTip", this.getRuleTip()).toString();
   }
}
