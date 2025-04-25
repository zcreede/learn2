package com.emr.project.emr.domain.vo;

import java.util.List;

public class IndexHFInfoVo {
   private String patientId;
   private Long id;
   private String xmlStr;
   private List subFileIndexList;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public List getSubFileIndexList() {
      return this.subFileIndexList;
   }

   public void setSubFileIndexList(List subFileIndexList) {
      this.subFileIndexList = subFileIndexList;
   }
}
