package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.EmrQcFlowScoreList;

public class EmrQcFlowScoreListVo extends EmrQcFlowScoreList {
   private String emrTypeName;
   private String ruleElemName;
   private String qcElemName;
   private String emrEleName;

   public String getEmrEleName() {
      return this.emrEleName;
   }

   public void setEmrEleName(String emrEleName) {
      this.emrEleName = emrEleName;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getRuleElemName() {
      return this.ruleElemName;
   }

   public void setRuleElemName(String ruleElemName) {
      this.ruleElemName = ruleElemName;
   }

   public String getQcElemName() {
      return this.qcElemName;
   }

   public void setQcElemName(String qcElemName) {
      this.qcElemName = qcElemName;
   }
}
