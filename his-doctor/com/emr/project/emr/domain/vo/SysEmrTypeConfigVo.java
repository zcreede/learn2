package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.SysEmrTypeConfig;

public class SysEmrTypeConfigVo extends SysEmrTypeConfig {
   private String emrTypeName;
   private String emrClassName;
   private String mrFileTypeName;
   private String dictLabel;
   private String dictValue;

   public String getDictLabel() {
      return this.dictLabel;
   }

   public void setDictLabel(String dictLabel) {
      this.dictLabel = dictLabel;
   }

   public String getDictValue() {
      return this.dictValue;
   }

   public void setDictValue(String dictValue) {
      this.dictValue = dictValue;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getEmrClassName() {
      return this.emrClassName;
   }

   public void setEmrClassName(String emrClassName) {
      this.emrClassName = emrClassName;
   }

   public String getMrFileTypeName() {
      return this.mrFileTypeName;
   }

   public void setMrFileTypeName(String mrFileTypeName) {
      this.mrFileTypeName = mrFileTypeName;
   }
}
