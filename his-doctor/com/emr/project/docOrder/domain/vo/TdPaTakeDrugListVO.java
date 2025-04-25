package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdPaTakeDrugList;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class TdPaTakeDrugListVO extends TdPaTakeDrugList {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String performListNo;
   private Integer performListSortNumber;
   private String admissionNo;
   private String takeDrugWardNo;
   private String patientName;
   private String bedid;
   private String orderNo;
   private String orderSortNumber;
   private String orderGroupSortNumber;
   private Integer orderGroupNo;
   private String orderType;
   private String drugName;
   private String drugNamePym;
   private String orderStandard;
   private String searchStr;
   private String usageUnit;
   private String takeFlag;
   private Date startDate;
   private Date endDate;
   private Double orderTotal;
   private String isGroupFirst;
   private String groupStr;
   private String prescription;
   private String issueOper;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date issueDate;
   private String queryType;
   private String drugCode;
   private String issueOperName;
   private String pharmacyNoName;
   private List admissionNoList;
   private List prescriptionList;
   private List takeDrugList;
   private String personAge;
   private String sex;
   private String expenseCategory;
   private String deposIt;
   private String staffName;
   private String money;

   public String getMoney() {
      return this.money;
   }

   public void setMoney(String money) {
      this.money = money;
   }

   public static long getSerialVersionUID() {
      return 1L;
   }

   public String getPersonAge() {
      return this.personAge;
   }

   public void setPersonAge(String personAge) {
      this.personAge = personAge;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getExpenseCategory() {
      return this.expenseCategory;
   }

   public void setExpenseCategory(String expenseCategory) {
      this.expenseCategory = expenseCategory;
   }

   public String getDeposIt() {
      return this.deposIt;
   }

   public void setDeposIt(String deposIt) {
      this.deposIt = deposIt;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }

   public String getSearchStr() {
      return this.searchStr;
   }

   public void setSearchStr(String searchStr) {
      this.searchStr = searchStr;
   }

   public List getTakeDrugList() {
      return this.takeDrugList;
   }

   public void setTakeDrugList(List takeDrugList) {
      this.takeDrugList = takeDrugList;
   }

   public List getAdmissionNoList() {
      return this.admissionNoList;
   }

   public void setAdmissionNoList(List admissionNoList) {
      this.admissionNoList = admissionNoList;
   }

   public List getPrescriptionList() {
      return this.prescriptionList;
   }

   public void setPrescriptionList(List prescriptionList) {
      this.prescriptionList = prescriptionList;
   }

   public String getPharmacyNoName() {
      return this.pharmacyNoName;
   }

   public void setPharmacyNoName(String pharmacyNoName) {
      this.pharmacyNoName = pharmacyNoName;
   }

   public String getIssueOperName() {
      return this.issueOperName;
   }

   public void setIssueOperName(String issueOperName) {
      this.issueOperName = issueOperName;
   }

   public String getDrugCode() {
      return this.drugCode;
   }

   public void setDrugCode(String drugCode) {
      this.drugCode = drugCode;
   }

   public String getQueryType() {
      return this.queryType;
   }

   public void setQueryType(String queryType) {
      this.queryType = queryType;
   }

   public String getPrescription() {
      return this.prescription;
   }

   public void setPrescription(String prescription) {
      this.prescription = prescription;
   }

   public String getIssueOper() {
      return this.issueOper;
   }

   public void setIssueOper(String issueOper) {
      this.issueOper = issueOper;
   }

   public Date getIssueDate() {
      return this.issueDate;
   }

   public void setIssueDate(Date issueDate) {
      this.issueDate = issueDate;
   }

   public String getGroupStr() {
      return this.groupStr;
   }

   public void setGroupStr(String groupStr) {
      this.groupStr = groupStr;
   }

   public String getIsGroupFirst() {
      return this.isGroupFirst;
   }

   public void setIsGroupFirst(String isGroupFirst) {
      this.isGroupFirst = isGroupFirst;
   }

   public Double getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(Double orderTotal) {
      this.orderTotal = orderTotal;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public String getTakeFlag() {
      return this.takeFlag;
   }

   public void setTakeFlag(String takeFlag) {
      this.takeFlag = takeFlag;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setTakeDrugWardNo(String takeDrugWardNo) {
      this.takeDrugWardNo = takeDrugWardNo;
   }

   public String getTakeDrugWardNo() {
      return this.takeDrugWardNo;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugNamePym(String drugNamePym) {
      this.drugNamePym = drugNamePym;
   }

   public String getDrugNamePym() {
      return this.drugNamePym;
   }

   public void setOrderStandard(String orderStandard) {
      this.orderStandard = orderStandard;
   }

   public String getOrderStandard() {
      return this.orderStandard;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }
}
