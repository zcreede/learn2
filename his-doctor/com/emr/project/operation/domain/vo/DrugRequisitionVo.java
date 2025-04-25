package com.emr.project.operation.domain.vo;

import java.util.List;

public class DrugRequisitionVo {
   private String admissionNo;
   private String name;
   private Integer hospitalizedCount;
   private String pharmacyCode;
   private String pharmacyName;
   private List list;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getPharmacyCode() {
      return this.pharmacyCode;
   }

   public void setPharmacyCode(String pharmacyCode) {
      this.pharmacyCode = pharmacyCode;
   }

   public String getPharmacyName() {
      return this.pharmacyName;
   }

   public void setPharmacyName(String pharmacyName) {
      this.pharmacyName = pharmacyName;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }
}
