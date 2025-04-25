package com.emr.project.operation.domain.vo;

import com.emr.project.operation.domain.TakeDrugList;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TdPaTakeDrugListPageVo extends TakeDrugList {
   private String manufacturer;
   private String standard;
   private List orderTypeList;
   private String groupStr;
   private List orallyFlagList;
   private List orallyCodeList;
   private String orallyFlag;
   private String orderDoctorName;
   private String drugPlace;
   private String highRiskFlag;
   private String drugStockFlag;
   private List specialDrug;
   private String isGroupFirst;
   private String sigStandCode;
   private String drugEatTimeFlag;
   private String drugEatTimeBegin;
   private String drugEatTimeEnd;
   private String name;
   private String admittingDiagnosis;
   private String expenseCategory;
   private String allergicHistory;
   private Boolean checked;
   private List performListNoList;
   private BigDecimal amount;
   private List patientList;
   private String pharmacyName;

   public TdPaTakeDrugListPageVo() {
      this.checked = Boolean.FALSE;
      this.patientList = new ArrayList(1);
   }

   public String getPharmacyName() {
      return this.pharmacyName;
   }

   public void setPharmacyName(String pharmacyName) {
      this.pharmacyName = pharmacyName;
   }

   public List getPatientList() {
      return this.patientList;
   }

   public void setPatientList(List patientList) {
      this.patientList = patientList;
   }

   public BigDecimal getAmount() {
      return this.amount;
   }

   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }

   public List getPerformListNoList() {
      return this.performListNoList;
   }

   public void setPerformListNoList(List performListNoList) {
      this.performListNoList = performListNoList;
   }

   public String getDrugEatTimeEnd() {
      return this.drugEatTimeEnd;
   }

   public void setDrugEatTimeEnd(String drugEatTimeEnd) {
      this.drugEatTimeEnd = drugEatTimeEnd;
   }

   public String getDrugEatTimeBegin() {
      return this.drugEatTimeBegin;
   }

   public void setDrugEatTimeBegin(String drugEatTimeBegin) {
      this.drugEatTimeBegin = drugEatTimeBegin;
   }

   public String getDrugEatTimeFlag() {
      return this.drugEatTimeFlag;
   }

   public void setDrugEatTimeFlag(String drugEatTimeFlag) {
      this.drugEatTimeFlag = drugEatTimeFlag;
   }

   public String getManufacturer() {
      return this.manufacturer;
   }

   public void setManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public List getOrderTypeList() {
      return this.orderTypeList;
   }

   public void setOrderTypeList(List orderTypeList) {
      this.orderTypeList = orderTypeList;
   }

   public String getGroupStr() {
      return this.groupStr;
   }

   public void setGroupStr(String groupStr) {
      this.groupStr = groupStr;
   }

   public List getOrallyFlagList() {
      return this.orallyFlagList;
   }

   public void setOrallyFlagList(List orallyFlagList) {
      this.orallyFlagList = orallyFlagList;
   }

   public List getOrallyCodeList() {
      return this.orallyCodeList;
   }

   public void setOrallyCodeList(List orallyCodeList) {
      this.orallyCodeList = orallyCodeList;
   }

   public String getOrallyFlag() {
      return this.orallyFlag;
   }

   public void setOrallyFlag(String orallyFlag) {
      this.orallyFlag = orallyFlag;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getDrugPlace() {
      return this.drugPlace;
   }

   public void setDrugPlace(String drugPlace) {
      this.drugPlace = drugPlace;
   }

   public String getHighRiskFlag() {
      return this.highRiskFlag;
   }

   public void setHighRiskFlag(String highRiskFlag) {
      this.highRiskFlag = highRiskFlag;
   }

   public String getDrugStockFlag() {
      return this.drugStockFlag;
   }

   public void setDrugStockFlag(String drugStockFlag) {
      this.drugStockFlag = drugStockFlag;
   }

   public List getSpecialDrug() {
      return this.specialDrug;
   }

   public void setSpecialDrug(List specialDrug) {
      this.specialDrug = specialDrug;
   }

   public String getIsGroupFirst() {
      return this.isGroupFirst;
   }

   public void setIsGroupFirst(String isGroupFirst) {
      this.isGroupFirst = isGroupFirst;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAdmittingDiagnosis() {
      return this.admittingDiagnosis;
   }

   public void setAdmittingDiagnosis(String admittingDiagnosis) {
      this.admittingDiagnosis = admittingDiagnosis;
   }

   public String getExpenseCategory() {
      return this.expenseCategory;
   }

   public void setExpenseCategory(String expenseCategory) {
      this.expenseCategory = expenseCategory;
   }

   public String getAllergicHistory() {
      return this.allergicHistory;
   }

   public void setAllergicHistory(String allergicHistory) {
      this.allergicHistory = allergicHistory;
   }

   public Boolean getChecked() {
      return this.checked;
   }

   public void setChecked(Boolean checked) {
      this.checked = checked;
   }

   public String getSigStandCode() {
      return this.sigStandCode;
   }

   public void setSigStandCode(String sigStandCode) {
      this.sigStandCode = sigStandCode;
   }
}
