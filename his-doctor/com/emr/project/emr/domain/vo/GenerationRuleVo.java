package com.emr.project.emr.domain.vo;

import java.io.Serializable;

public class GenerationRuleVo implements Serializable {
   private String rule;
   private String ruleId;
   private String ruleName;
   private String ruleCode;
   private String errorMsg;
   private String errorColumn;
   private String eventType;
   private String eventVariable;
   private String otherRule;
   private String ruleConditions;
   private int ruleOrder;
   private String ruleResuleVo;
   private String preCondition;

   public String getPreCondition() {
      return this.preCondition;
   }

   public void setPreCondition(String preCondition) {
      this.preCondition = preCondition;
   }

   public String getRuleResuleVo() {
      return this.ruleResuleVo;
   }

   public void setRuleResuleVo(String ruleResuleVo) {
      this.ruleResuleVo = ruleResuleVo;
   }

   public int getRuleOrder() {
      return this.ruleOrder;
   }

   public void setRuleOrder(int ruleOrder) {
      this.ruleOrder = ruleOrder;
   }

   public String getOtherRule() {
      return this.otherRule;
   }

   public void setOtherRule(String otherRule) {
      this.otherRule = otherRule;
   }

   public String getRule() {
      return this.rule;
   }

   public void setRule(String rule) {
      this.rule = rule;
   }

   public String getRuleCode() {
      return this.ruleCode;
   }

   public void setRuleCode(String ruleCode) {
      this.ruleCode = ruleCode;
   }

   public String getErrorMsg() {
      return this.errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

   public String getEventType() {
      return this.eventType;
   }

   public void setEventType(String eventType) {
      this.eventType = eventType;
   }

   public String getEventVariable() {
      return this.eventVariable;
   }

   public void setEventVariable(String eventVariable) {
      this.eventVariable = eventVariable;
   }

   public String getRuleId() {
      return this.ruleId;
   }

   public void setRuleId(String ruleId) {
      this.ruleId = ruleId;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getErrorColumn() {
      return this.errorColumn;
   }

   public void setErrorColumn(String errorColumn) {
      this.errorColumn = errorColumn;
   }

   public String getRuleConditions() {
      return this.ruleConditions;
   }

   public void setRuleConditions(String ruleConditions) {
      this.ruleConditions = ruleConditions;
   }
}
