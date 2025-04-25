package com.emr.project.tmpl.domain.vo;

import com.emr.project.system.domain.SysDept;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.TmplDept;
import com.emr.project.tmpl.domain.TmplIndex;
import java.util.List;
import java.util.Map;

public class TmplIndexVo extends TmplIndex {
   private String tempTypeName;
   private String tempMajorName;
   private String tempDiseaseName;
   private String tempDept;
   private List deptList;
   private Map map;
   private String base64Str;
   private String xmlStr;
   private Long tempMajorId;
   private Boolean editFlag;
   private List elemAttriList;
   private Map elemsAttriMap;
   private List tmplDeptList;
   private Boolean isMedicalRecordTemplate;
   private String standardTmpl;

   public String getStandardTmpl() {
      return this.standardTmpl;
   }

   public void setStandardTmpl(String standardTmpl) {
      this.standardTmpl = standardTmpl;
   }

   public Boolean getIsMedicalRecordTemplate() {
      return this.isMedicalRecordTemplate;
   }

   public void setIsMedicalRecordTemplate(Boolean medicalRecordTemplate) {
      this.isMedicalRecordTemplate = medicalRecordTemplate;
   }

   public List getTmplDeptList() {
      return this.tmplDeptList;
   }

   public void setTmplDeptList(List tmplDeptList) {
      this.tmplDeptList = tmplDeptList;
   }

   public Map getMap() {
      return this.map;
   }

   public void setMap(Map map) {
      this.map = map;
   }

   public Long getTempMajorId() {
      return this.tempMajorId;
   }

   public void setTempMajorId(Long tempMajorId) {
      this.tempMajorId = tempMajorId;
   }

   public Map getElemsAttriMap() {
      return this.elemsAttriMap;
   }

   public void setElemsAttriMap(Map elemsAttriMap) {
      this.elemsAttriMap = elemsAttriMap;
   }

   public Boolean getEditFlag() {
      return this.editFlag;
   }

   public void setEditFlag(Boolean editFlag) {
      this.editFlag = editFlag;
   }

   public List getElemAttriList() {
      return this.elemAttriList;
   }

   public void setElemAttriList(List elemAttriList) {
      this.elemAttriList = elemAttriList;
   }

   public List getDeptList() {
      return this.deptList;
   }

   public void setDeptList(List deptList) {
      this.deptList = deptList;
   }

   public String getTempDept() {
      return this.tempDept;
   }

   public void setTempDept(String tempDept) {
      this.tempDept = tempDept;
   }

   public String getTempTypeName() {
      return this.tempTypeName;
   }

   public void setTempTypeName(String tempTypeName) {
      this.tempTypeName = tempTypeName;
   }

   public String getTempMajorName() {
      return this.tempMajorName;
   }

   public void setTempMajorName(String tempMajorName) {
      this.tempMajorName = tempMajorName;
   }

   public String getTempDiseaseName() {
      return this.tempDiseaseName;
   }

   public void setTempDiseaseName(String tempDiseaseName) {
      this.tempDiseaseName = tempDiseaseName;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public String getBase64Str() {
      return this.base64Str;
   }

   public void setBase64Str(String base64Str) {
      this.base64Str = base64Str;
   }
}
