package com.emr.project.report.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class PatientInLeaveInfoVo {
   public String departmentNo;
   @Excel(
      name = "科室"
   )
   public String deptName;
   @Excel(
      name = "住院号"
   )
   public String admissionNo;
   @Excel(
      name = "次数"
   )
   public Integer hospitalizedCount;
   @Excel(
      name = "姓名"
   )
   public String name;
   @Excel(
      name = "性别"
   )
   public String sex;
   @Excel(
      name = "年龄"
   )
   public String age;
   @Excel(
      name = "床位号"
   )
   public String bedId;
   @Excel(
      name = "管床医师"
   )
   public String residentName;
   public String expenseCategoryNo;
   @Excel(
      name = "费别"
   )
   public String expenseCategory;
   @Excel(
      name = "预交款"
   )
   public BigDecimal deposIt;
   @Excel(
      name = "总费用"
   )
   public BigDecimal cumulativeCost;
   @Excel(
      name = "余欠款"
   )
   public BigDecimal arrearsAmount;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入院日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   public Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出院日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   public Date leaveHospitalDate;
   @Excel(
      name = "天数"
   )
   public Integer dayNum;
   @Excel(
      name = "诊断"
   )
   public String admittingDiagnosisName;
   @Excel(
      name = "地址"
   )
   public String presentAddress;
   @Excel(
      name = "联系电话"
   )
   public String contTel;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;

   public String getDepartmentNo() {
      return this.departmentNo;
   }

   public void setDepartmentNo(String departmentNo) {
      this.departmentNo = departmentNo;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getBedId() {
      return this.bedId;
   }

   public void setBedId(String bedId) {
      this.bedId = bedId;
   }

   public String getResidentName() {
      return this.residentName;
   }

   public void setResidentName(String residentName) {
      this.residentName = residentName;
   }

   public String getExpenseCategoryNo() {
      return this.expenseCategoryNo;
   }

   public void setExpenseCategoryNo(String expenseCategoryNo) {
      this.expenseCategoryNo = expenseCategoryNo;
   }

   public String getExpenseCategory() {
      return this.expenseCategory;
   }

   public void setExpenseCategory(String expenseCategory) {
      this.expenseCategory = expenseCategory;
   }

   public BigDecimal getDeposIt() {
      return this.deposIt;
   }

   public void setDeposIt(BigDecimal deposIt) {
      this.deposIt = deposIt;
   }

   public BigDecimal getCumulativeCost() {
      return this.cumulativeCost;
   }

   public void setCumulativeCost(BigDecimal cumulativeCost) {
      this.cumulativeCost = cumulativeCost;
   }

   public BigDecimal getArrearsAmount() {
      return this.arrearsAmount;
   }

   public void setArrearsAmount(BigDecimal arrearsAmount) {
      this.arrearsAmount = arrearsAmount;
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
   }

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public Integer getDayNum() {
      return this.dayNum;
   }

   public void setDayNum(Integer dayNum) {
      this.dayNum = dayNum;
   }

   public String getAdmittingDiagnosisName() {
      return this.admittingDiagnosisName;
   }

   public void setAdmittingDiagnosisName(String admittingDiagnosisName) {
      this.admittingDiagnosisName = admittingDiagnosisName;
   }

   public String getPresentAddress() {
      return this.presentAddress;
   }

   public void setPresentAddress(String presentAddress) {
      this.presentAddress = presentAddress;
   }

   public String getContTel() {
      return this.contTel;
   }

   public void setContTel(String contTel) {
      this.contTel = contTel;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public Long getAgeY() {
      return this.ageY;
   }

   public void setAgeY(Long ageY) {
      this.ageY = ageY;
   }

   public Long getAgeM() {
      return this.ageM;
   }

   public void setAgeM(Long ageM) {
      this.ageM = ageM;
   }

   public Long getAgeD() {
      return this.ageD;
   }

   public void setAgeD(Long ageD) {
      this.ageD = ageD;
   }

   public Long getAgeH() {
      return this.ageH;
   }

   public void setAgeH(Long ageH) {
      this.ageH = ageH;
   }

   public Long getAgeMi() {
      return this.ageMi;
   }

   public void setAgeMi(Long ageMi) {
      this.ageMi = ageMi;
   }
}
