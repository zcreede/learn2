package com.emr.project.system.domain;

public class ConfigureHospital {
   private Long id;
   private String hospitalCode;
   private String configureName;
   private float configureCode;
   private String configureValue;
   private String configureDesc;
   private Integer sortNumber;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
   }

   public String getConfigureName() {
      return this.configureName;
   }

   public void setConfigureName(String configureName) {
      this.configureName = configureName == null ? null : configureName.trim();
   }

   public float getConfigureCode() {
      return this.configureCode;
   }

   public void setConfigureCode(float configureCode) {
      this.configureCode = configureCode;
   }

   public String getConfigureValue() {
      return this.configureValue;
   }

   public void setConfigureValue(String configureValue) {
      this.configureValue = configureValue == null ? null : configureValue.trim();
   }

   public String getConfigureDesc() {
      return this.configureDesc;
   }

   public void setConfigureDesc(String configureDesc) {
      this.configureDesc = configureDesc == null ? null : configureDesc.trim();
   }

   public Integer getSortNumber() {
      return this.sortNumber;
   }

   public void setSortNumber(Integer sortNumber) {
      this.sortNumber = sortNumber;
   }
}
