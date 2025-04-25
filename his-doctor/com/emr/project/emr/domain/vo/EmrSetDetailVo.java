package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrSetDetail;
import java.util.List;

public class EmrSetDetailVo extends EmrSetDetail {
   private String checkedFlag;
   private String docCd;
   private String setName;
   private String tempFile;
   private String patientId;
   private Boolean isCreateFlag;
   private String mrType;
   private List emrSetDetailList;
   private List childList;
   private String emrTypeName;
   private String emrTypeId;
   private String isMainFile;
   private String tempClass;

   public String getTempClass() {
      return this.tempClass;
   }

   public void setTempClass(String tempClass) {
      this.tempClass = tempClass;
   }

   public String getIsMainFile() {
      return this.isMainFile;
   }

   public void setIsMainFile(String isMainFile) {
      this.isMainFile = isMainFile;
   }

   public List getEmrSetDetailList() {
      return this.emrSetDetailList;
   }

   public void setEmrSetDetailList(List emrSetDetailList) {
      this.emrSetDetailList = emrSetDetailList;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public Boolean getIsCreateFlag() {
      return this.isCreateFlag;
   }

   public void setIsCreateFlag(Boolean isCreateFlag) {
      this.isCreateFlag = isCreateFlag;
   }

   public String getTempFile() {
      return this.tempFile;
   }

   public void setTempFile(String tempFile) {
      this.tempFile = tempFile;
   }

   public String getSetName() {
      return this.setName;
   }

   public void setSetName(String setName) {
      this.setName = setName;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public List getChildList() {
      return this.childList;
   }

   public void setChildList(List childList) {
      this.childList = childList;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getCheckedFlag() {
      return this.checkedFlag;
   }

   public void setCheckedFlag(String checkedFlag) {
      this.checkedFlag = checkedFlag;
   }

   public String getEmrTypeId() {
      return this.emrTypeId;
   }

   public void setEmrTypeId(String emrTypeId) {
      this.emrTypeId = emrTypeId;
   }
}
