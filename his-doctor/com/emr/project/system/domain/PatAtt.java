package com.emr.project.system.domain;

import java.math.BigDecimal;

public class PatAtt {
   private String hospitalCode;
   private String caseNo;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String ulcerFlag;
   private String scaldFlag;
   private String fallingFlag;
   private String tumbleFlag;
   private String ridFlag;
   private String infectFlag;
   private String heatFlag;
   private String keyFlag;
   private String researchFlag;
   private BigDecimal height;
   private BigDecimal weight;
   private String allergyName;
   private String operationFlag;

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo == null ? null : caseNo.trim();
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo == null ? null : admissionNo.trim();
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getUlcerFlag() {
      return this.ulcerFlag;
   }

   public void setUlcerFlag(String ulcerFlag) {
      this.ulcerFlag = ulcerFlag == null ? null : ulcerFlag.trim();
   }

   public String getScaldFlag() {
      return this.scaldFlag;
   }

   public void setScaldFlag(String scaldFlag) {
      this.scaldFlag = scaldFlag == null ? null : scaldFlag.trim();
   }

   public String getFallingFlag() {
      return this.fallingFlag;
   }

   public void setFallingFlag(String fallingFlag) {
      this.fallingFlag = fallingFlag == null ? null : fallingFlag.trim();
   }

   public String getTumbleFlag() {
      return this.tumbleFlag;
   }

   public void setTumbleFlag(String tumbleFlag) {
      this.tumbleFlag = tumbleFlag == null ? null : tumbleFlag.trim();
   }

   public String getRidFlag() {
      return this.ridFlag;
   }

   public void setRidFlag(String ridFlag) {
      this.ridFlag = ridFlag == null ? null : ridFlag.trim();
   }

   public String getInfectFlag() {
      return this.infectFlag;
   }

   public void setInfectFlag(String infectFlag) {
      this.infectFlag = infectFlag == null ? null : infectFlag.trim();
   }

   public String getHeatFlag() {
      return this.heatFlag;
   }

   public void setHeatFlag(String heatFlag) {
      this.heatFlag = heatFlag == null ? null : heatFlag.trim();
   }

   public String getKeyFlag() {
      return this.keyFlag;
   }

   public void setKeyFlag(String keyFlag) {
      this.keyFlag = keyFlag == null ? null : keyFlag.trim();
   }

   public String getResearchFlag() {
      return this.researchFlag;
   }

   public void setResearchFlag(String researchFlag) {
      this.researchFlag = researchFlag == null ? null : researchFlag.trim();
   }

   public BigDecimal getHeight() {
      return this.height;
   }

   public void setHeight(BigDecimal height) {
      this.height = height;
   }

   public BigDecimal getWeight() {
      return this.weight;
   }

   public void setWeight(BigDecimal weight) {
      this.weight = weight;
   }

   public String getAllergyName() {
      return this.allergyName;
   }

   public void setAllergyName(String allergyName) {
      this.allergyName = allergyName == null ? null : allergyName.trim();
   }

   public String getOperationFlag() {
      return this.operationFlag;
   }

   public void setOperationFlag(String operationFlag) {
      this.operationFlag = operationFlag == null ? null : operationFlag.trim();
   }
}
