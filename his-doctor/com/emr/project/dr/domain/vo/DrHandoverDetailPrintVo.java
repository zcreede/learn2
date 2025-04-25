package com.emr.project.dr.domain.vo;

public class DrHandoverDetailPrintVo {
   private String patientTypeName;
   private String inpNo;
   private String patientName;
   private String age;
   private String sex;
   private String bedNo;
   private String diaName;
   private Integer index;
   private String condSesc;

   public Integer getIndex() {
      return this.index;
   }

   public void setIndex(Integer index) {
      this.index = index;
   }

   public String getPatientTypeName() {
      return this.patientTypeName;
   }

   public void setPatientTypeName(String patientTypeName) {
      this.patientTypeName = patientTypeName;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getDiaName() {
      return this.diaName;
   }

   public void setDiaName(String diaName) {
      this.diaName = diaName;
   }

   public String getCondSesc() {
      return this.condSesc;
   }

   public void setCondSesc(String condSesc) {
      this.condSesc = condSesc;
   }
}
