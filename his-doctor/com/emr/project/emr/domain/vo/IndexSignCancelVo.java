package com.emr.project.emr.domain.vo;

import java.util.List;

public class IndexSignCancelVo {
   private Long id;
   private Long subFileIndexId;
   private String xmlStr;
   private List signElemList;
   private String freeMoveType;

   public String getFreeMoveType() {
      return this.freeMoveType;
   }

   public void setFreeMoveType(String freeMoveType) {
      this.freeMoveType = freeMoveType;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getSubFileIndexId() {
      return this.subFileIndexId;
   }

   public void setSubFileIndexId(Long subFileIndexId) {
      this.subFileIndexId = subFileIndexId;
   }

   public List getSignElemList() {
      return this.signElemList;
   }

   public void setSignElemList(List signElemList) {
      this.signElemList = signElemList;
   }
}
