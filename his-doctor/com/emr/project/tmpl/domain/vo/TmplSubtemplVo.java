package com.emr.project.tmpl.domain.vo;

import com.emr.project.tmpl.domain.TmplSubtempl;
import java.util.Map;

public class TmplSubtemplVo extends TmplSubtempl {
   private String majorName;
   private String diseaseName;
   private String mrTypeName;
   private String base64;
   private String xmlStr;
   private Boolean editFlag;
   private Map elemsAttriMap;
   private Long tempId;

   public Long getTempId() {
      return this.tempId;
   }

   public void setTempId(Long tempId) {
      this.tempId = tempId;
   }

   public Boolean getEditFlag() {
      return this.editFlag;
   }

   public void setEditFlag(Boolean editFlag) {
      this.editFlag = editFlag;
   }

   public Map getElemsAttriMap() {
      return this.elemsAttriMap;
   }

   public void setElemsAttriMap(Map elemsAttriMap) {
      this.elemsAttriMap = elemsAttriMap;
   }

   public String getBase64() {
      return this.base64;
   }

   public void setBase64(String base64) {
      this.base64 = base64;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public String getMajorName() {
      return this.majorName;
   }

   public void setMajorName(String majorName) {
      this.majorName = majorName;
   }

   public String getDiseaseName() {
      return this.diseaseName;
   }

   public void setDiseaseName(String diseaseName) {
      this.diseaseName = diseaseName;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }
}
