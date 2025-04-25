package com.emr.project.emr.domain.vo;

public class IndexHFInfoPageVo {
   private String name;
   private String value;
   private String text;
   private String contElemName;

   public IndexHFInfoPageVo() {
   }

   public IndexHFInfoPageVo(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public String getContElemName() {
      return this.contElemName;
   }

   public void setContElemName(String contElemName) {
      this.contElemName = contElemName;
   }
}
