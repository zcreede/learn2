package com.emr.project.pat.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;

public class PatientInfoVo {
   private String admissionNo;
   private String bedid;
   private String name;
   private String sex;
   private String personAge;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date hospitalizedDate;
   private String depName;
   private String deposIt;
   private BigDecimal cumulativeCost;
   private BigDecimal money;
   private BigDecimal amountGuaranteed;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date birdthDate;
   private String expenseCategoryNo;
   private String expenseCategory;
   private String nation;
   private String maritalSt;
   private String occuType;
   private String cardEdBgCd;
   private String postcode;
   private String tel;
   private String idcard;
   private String inpatArrearageMark;
   private String presentAddressSheng;
   private String presentAddressShi;
   private String presentAddressXian;
   private String presentAddressXiang;
   private String presentAddressCun;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date entryDate;
   private String ResidentName;
   private String ResidentNo;
   private String visitingStaffNo;
   private String supervisorNurseNo;
   private String archiaterName;
   private String visitingStaffName;
   private String supervisorNurseName;
   private String bedSurcharge;
   private String levelName;
   private String levelCode;
   private String outpatientDoctor;
   private String hospitalCondition;
   private String hospitalWay;
   private String bloodType;
   private String outpatientDiagnosis;
   private String weight;
   private String hospitalCode;
   private String caseNo;
   private Integer hospitalizedCount;
   private String admittingDiagnosis;
   private String admittingDiagnosisName;
   private String ageMonth;
   private String ageDay;
   private String ageHour;
   private String isOut;

   public String getIsOut() {
      return this.isOut;
   }

   public void setIsOut(String isOut) {
      this.isOut = isOut;
   }

   public String getAdmittingDiagnosisName() {
      return this.admittingDiagnosisName;
   }

   public void setAdmittingDiagnosisName(String admittingDiagnosisName) {
      this.admittingDiagnosisName = admittingDiagnosisName;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
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

   public String getPersonAge() {
      return this.personAge;
   }

   public void setPersonAge(String personAge) {
      this.personAge = personAge;
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
   }

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public String getDepName() {
      return this.depName;
   }

   public void setDepName(String depName) {
      this.depName = depName;
   }

   public String getDeposIt() {
      return this.deposIt;
   }

   public void setDeposIt(String deposIt) {
      this.deposIt = deposIt;
   }

   public BigDecimal getCumulativeCost() {
      return this.cumulativeCost;
   }

   public void setCumulativeCost(BigDecimal cumulativeCost) {
      this.cumulativeCost = cumulativeCost;
   }

   public BigDecimal getMoney() {
      return this.money;
   }

   public void setMoney(BigDecimal money) {
      this.money = money;
   }

   public BigDecimal getAmountGuaranteed() {
      return this.amountGuaranteed;
   }

   public void setAmountGuaranteed(BigDecimal amountGuaranteed) {
      this.amountGuaranteed = amountGuaranteed;
   }

   public Date getBirdthDate() {
      return this.birdthDate;
   }

   public void setBirdthDate(Date birdthDate) {
      this.birdthDate = birdthDate;
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

   public String getNation() {
      return this.nation;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getMaritalSt() {
      return this.maritalSt;
   }

   public void setMaritalSt(String maritalSt) {
      this.maritalSt = maritalSt;
   }

   public String getOccuType() {
      return this.occuType;
   }

   public void setOccuType(String occuType) {
      this.occuType = occuType;
   }

   public String getCardEdBgCd() {
      return this.cardEdBgCd;
   }

   public void setCardEdBgCd(String cardEdBgCd) {
      this.cardEdBgCd = cardEdBgCd;
   }

   public String getPostcode() {
      return this.postcode;
   }

   public void setPostcode(String postcode) {
      this.postcode = postcode;
   }

   public String getTel() {
      return this.tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getIdcard() {
      return this.idcard;
   }

   public void setIdcard(String idcard) {
      this.idcard = idcard;
   }

   public String getInpatArrearageMark() {
      return this.inpatArrearageMark;
   }

   public void setInpatArrearageMark(String inpatArrearageMark) {
      this.inpatArrearageMark = inpatArrearageMark;
   }

   public String getPresentAddressSheng() {
      return this.presentAddressSheng;
   }

   public void setPresentAddressSheng(String presentAddressSheng) {
      this.presentAddressSheng = presentAddressSheng;
   }

   public String getPresentAddressShi() {
      return this.presentAddressShi;
   }

   public void setPresentAddressShi(String presentAddressShi) {
      this.presentAddressShi = presentAddressShi;
   }

   public String getPresentAddressXian() {
      return this.presentAddressXian;
   }

   public void setPresentAddressXian(String presentAddressXian) {
      this.presentAddressXian = presentAddressXian;
   }

   public String getPresentAddressXiang() {
      return this.presentAddressXiang;
   }

   public void setPresentAddressXiang(String presentAddressXiang) {
      this.presentAddressXiang = presentAddressXiang;
   }

   public String getPresentAddressCun() {
      return this.presentAddressCun;
   }

   public void setPresentAddressCun(String presentAddressCun) {
      this.presentAddressCun = presentAddressCun;
   }

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public String getResidentName() {
      return this.ResidentName;
   }

   public void setResidentName(String residentName) {
      this.ResidentName = residentName;
   }

   public String getResidentNo() {
      return this.ResidentNo;
   }

   public void setResidentNo(String residentNo) {
      this.ResidentNo = residentNo;
   }

   public String getVisitingStaffNo() {
      return this.visitingStaffNo;
   }

   public void setVisitingStaffNo(String visitingStaffNo) {
      this.visitingStaffNo = visitingStaffNo;
   }

   public String getSupervisorNurseNo() {
      return this.supervisorNurseNo;
   }

   public void setSupervisorNurseNo(String supervisorNurseNo) {
      this.supervisorNurseNo = supervisorNurseNo;
   }

   public String getArchiaterName() {
      return this.archiaterName;
   }

   public void setArchiaterName(String archiaterName) {
      this.archiaterName = archiaterName;
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName;
   }

   public String getSupervisorNurseName() {
      return this.supervisorNurseName;
   }

   public void setSupervisorNurseName(String supervisorNurseName) {
      this.supervisorNurseName = supervisorNurseName;
   }

   public String getBedSurcharge() {
      return this.bedSurcharge;
   }

   public void setBedSurcharge(String bedSurcharge) {
      this.bedSurcharge = bedSurcharge;
   }

   public String getLevelName() {
      return this.levelName;
   }

   public void setLevelName(String levelName) {
      this.levelName = levelName;
   }

   public String getLevelCode() {
      return this.levelCode;
   }

   public void setLevelCode(String levelCode) {
      this.levelCode = levelCode;
   }

   public String getOutpatientDoctor() {
      return this.outpatientDoctor;
   }

   public void setOutpatientDoctor(String outpatientDoctor) {
      this.outpatientDoctor = outpatientDoctor;
   }

   public String getHospitalCondition() {
      return this.hospitalCondition;
   }

   public void setHospitalCondition(String hospitalCondition) {
      this.hospitalCondition = hospitalCondition;
   }

   public String getHospitalWay() {
      return this.hospitalWay;
   }

   public void setHospitalWay(String hospitalWay) {
      this.hospitalWay = hospitalWay;
   }

   public String getBloodType() {
      return this.bloodType;
   }

   public void setBloodType(String bloodType) {
      this.bloodType = bloodType;
   }

   public String getOutpatientDiagnosis() {
      return this.outpatientDiagnosis;
   }

   public void setOutpatientDiagnosis(String outpatientDiagnosis) {
      this.outpatientDiagnosis = outpatientDiagnosis;
   }

   public String getWeight() {
      return this.weight;
   }

   public void setWeight(String weight) {
      this.weight = weight;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getAdmittingDiagnosis() {
      return this.admittingDiagnosis;
   }

   public void setAdmittingDiagnosis(String admittingDiagnosis) {
      this.admittingDiagnosis = admittingDiagnosis;
   }

   public String getAgeMonth() {
      return this.ageMonth;
   }

   public void setAgeMonth(String ageMonth) {
      this.ageMonth = ageMonth;
   }

   public String getAgeDay() {
      return this.ageDay;
   }

   public void setAgeDay(String ageDay) {
      this.ageDay = ageDay;
   }

   public String getAgeHour() {
      return this.ageHour;
   }

   public void setAgeHour(String ageHour) {
      this.ageHour = ageHour;
   }
}
