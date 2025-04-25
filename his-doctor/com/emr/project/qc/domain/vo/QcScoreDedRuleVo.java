package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcScoreDedRule;

public class QcScoreDedRuleVo extends QcScoreDedRule {
   private String ruleName;
   private String ruleType;
   private String emrTypeCd;
   private String emrTypeName;

   public String getRuleName() {
      return this.ruleName;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getRuleType() {
      return this.ruleType;
   }

   public void setRuleType(String ruleType) {
      this.ruleType = ruleType;
   }

   public String getEmrTypeCd() {
      return this.emrTypeCd;
   }

   public void setEmrTypeCd(String emrTypeCd) {
      this.emrTypeCd = emrTypeCd;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }
}
