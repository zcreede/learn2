package com.emr.project.tmpl.domain.vo;

import com.emr.project.tmpl.domain.TmplIndex;
import java.util.List;

public class TmplIndexSearchVo extends TmplIndex {
   private String patientId;
   private String tmplDept;
   private String tmplOrg;
   private List useSexList;
   private List useAgeList;
   private String commonTempMajor;
   private List tempTypeList;
   private List medicineCodeList;

   public String getTmplOrg() {
      return this.tmplOrg;
   }

   public void setTmplOrg(String tmplOrg) {
      this.tmplOrg = tmplOrg;
   }

   public List getTempTypeList() {
      return this.tempTypeList;
   }

   public void setTempTypeList(List tempTypeList) {
      this.tempTypeList = tempTypeList;
   }

   public String getCommonTempMajor() {
      return this.commonTempMajor;
   }

   public void setCommonTempMajor(String commonTempMajor) {
      this.commonTempMajor = commonTempMajor;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public List getUseSexList() {
      return this.useSexList;
   }

   public void setUseSexList(List useSexList) {
      this.useSexList = useSexList;
   }

   public String getTmplDept() {
      return this.tmplDept;
   }

   public void setTmplDept(String tmplDept) {
      this.tmplDept = tmplDept;
   }

   public List getUseAgeList() {
      return this.useAgeList;
   }

   public void setUseAgeList(List useAgeList) {
      this.useAgeList = useAgeList;
   }

   public List getMedicineCodeList() {
      return this.medicineCodeList;
   }

   public void setMedicineCodeList(List medicineCodeList) {
      this.medicineCodeList = medicineCodeList;
   }
}
