package com.emr.project.system.domain;

public class DictStandard {
   private Long id;
   private String typeName;
   private String typeShort;
   private String typeCode;
   private String dictCode;
   private String dictName;
   private String dictPym;

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

   public String getTypeShort() {
      return this.typeShort;
   }

   public void setTypeShort(String typeShort) {
      this.typeShort = typeShort;
   }

   public String getDictCode() {
      return this.dictCode;
   }

   public String getTypeCode() {
      return this.typeCode;
   }

   public void setTypeCode(String typeCode) {
      this.typeCode = typeCode;
   }

   public void setDictCode(String dictCode) {
      this.dictCode = dictCode;
   }

   public String getDictName() {
      return this.dictName;
   }

   public void setDictName(String dictName) {
      this.dictName = dictName;
   }

   public String getDictPym() {
      return this.dictPym;
   }

   public void setDictPym(String dictPym) {
      this.dictPym = dictPym;
   }
}
