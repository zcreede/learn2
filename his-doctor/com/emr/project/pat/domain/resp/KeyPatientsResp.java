package com.emr.project.pat.domain.resp;

public class KeyPatientsResp {
   private String deptCode;
   private String deptName;
   private int totalCount = 0;
   private int operCount = 0;
   private int bloodCount = 0;
   private int dyCount = 0;
   private int illCount = 0;
   private int rescueCount = 0;
   private int dieCount = 0;
   private int consCount = 0;
   private int crisisCount = 0;
   private int antiCount = 0;

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public int getTotalCount() {
      return this.totalCount;
   }

   public void setTotalCount(int totalCount) {
      this.totalCount = totalCount;
   }

   public int getOperCount() {
      return this.operCount;
   }

   public void setOperCount(int operCount) {
      this.operCount = operCount;
   }

   public int getBloodCount() {
      return this.bloodCount;
   }

   public void setBloodCount(int bloodCount) {
      this.bloodCount = bloodCount;
   }

   public int getDyCount() {
      return this.dyCount;
   }

   public void setDyCount(int dyCount) {
      this.dyCount = dyCount;
   }

   public int getIllCount() {
      return this.illCount;
   }

   public void setIllCount(int illCount) {
      this.illCount = illCount;
   }

   public int getRescueCount() {
      return this.rescueCount;
   }

   public void setRescueCount(int rescueCount) {
      this.rescueCount = rescueCount;
   }

   public int getDieCount() {
      return this.dieCount;
   }

   public void setDieCount(int dieCount) {
      this.dieCount = dieCount;
   }

   public int getConsCount() {
      return this.consCount;
   }

   public void setConsCount(int consCount) {
      this.consCount = consCount;
   }

   public int getCrisisCount() {
      return this.crisisCount;
   }

   public void setCrisisCount(int crisisCount) {
      this.crisisCount = crisisCount;
   }

   public int getAntiCount() {
      return this.antiCount;
   }

   public void setAntiCount(int antiCount) {
      this.antiCount = antiCount;
   }
}
