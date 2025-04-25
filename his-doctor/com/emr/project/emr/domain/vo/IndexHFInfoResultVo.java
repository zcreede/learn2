package com.emr.project.emr.domain.vo;

import java.util.List;

public class IndexHFInfoResultVo {
   private int page;
   private String regionName;
   List indexHFInfoPageVoList;
   private List propName;
   private List propValue;
   private List text;

   public List getIndexHFInfoPageVoList() {
      return this.indexHFInfoPageVoList;
   }

   public void setIndexHFInfoPageVoList(List indexHFInfoPageVoList) {
      this.indexHFInfoPageVoList = indexHFInfoPageVoList;
   }

   public String getRegionName() {
      return this.regionName;
   }

   public void setRegionName(String regionName) {
      this.regionName = regionName;
   }

   public int getPage() {
      return this.page;
   }

   public void setPage(int page) {
      this.page = page;
   }

   public List getPropName() {
      return this.propName;
   }

   public void setPropName(List propName) {
      this.propName = propName;
   }

   public List getPropValue() {
      return this.propValue;
   }

   public void setPropValue(List propValue) {
      this.propValue = propValue;
   }

   public List getText() {
      return this.text;
   }

   public void setText(List text) {
      this.text = text;
   }
}
