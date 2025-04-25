package com.emr.project.emr.domain.vo;

public class InsertIndexLogVo {
   private Long id;
   private String patientId;
   private String indexName;
   private String typeName;
   private String typeCode;
   private String emrTypeCode;

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getIndexName() {
      return this.indexName;
   }

   public void setIndexName(String indexName) {
      this.indexName = indexName;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public String getTypeCode() {
      return this.typeCode;
   }

   public void setTypeCode(String typeCode) {
      this.typeCode = typeCode;
   }
}
