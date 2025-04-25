package com.emr.project.docOrder.domain.vo;

import com.emr.project.operation.domain.ApplyForm;
import java.math.BigDecimal;
import java.util.Date;

public class ApplyFormDTO extends ApplyForm {
   private String itemSortNumber;
   private String itemCode;
   private Integer orderGroupNo;
   private String itemName;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private String barcodeNo;
   private String bedid;
   private String name;
   private String flbh;
   private String flmc;
   private String dzbm;
   private String dzmc;
   private String depName;
   private String staffName;
   private String sex;
   private Integer tmStatus;
   private Integer printStatus;
   private Date scSj;
   private String scOper;
   private String scOperName;
   private Date smSj;
   private String smOper;
   private String smOperName;
   private Date zfSj;
   private Date printSj;
   private Integer printCs;
   private String hospitalCode;
   private String personAge;
   private String expenseCategoryNo;
   private String patientId;
   private String patientDepName;
   private String scJsr;
   private String tmTp;
   private String tmTpBase64;
   private String ys;
   private String purposeExaminationAll;
   private BigDecimal amount;
   private String yznr;

   public String getYznr() {
      return this.yznr;
   }

   public void setYznr(String yznr) {
      this.yznr = yznr;
   }

   public String getPurposeExaminationAll() {
      return this.purposeExaminationAll;
   }

   public void setPurposeExaminationAll(String purposeExaminationAll) {
      this.purposeExaminationAll = purposeExaminationAll;
   }

   public BigDecimal getAmount() {
      return this.amount;
   }

   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }

   public Integer getPrintCs() {
      return this.printCs;
   }

   public void setPrintCs(Integer printCs) {
      this.printCs = printCs;
   }

   public String getYs() {
      return this.ys;
   }

   public void setYs(String ys) {
      this.ys = ys;
   }

   public String getTmTpBase64() {
      return this.tmTpBase64;
   }

   public void setTmTpBase64(String tmTpBase64) {
      this.tmTpBase64 = tmTpBase64;
   }

   public String getTmTp() {
      return this.tmTp;
   }

   public void setTmTp(String tmTp) {
      this.tmTp = tmTp;
   }

   public String getScJsr() {
      return this.scJsr;
   }

   public void setScJsr(String scJsr) {
      this.scJsr = scJsr;
   }

   public String getScOperName() {
      return this.scOperName;
   }

   public void setScOperName(String scOperName) {
      this.scOperName = scOperName;
   }

   public String getSmOperName() {
      return this.smOperName;
   }

   public void setSmOperName(String smOperName) {
      this.smOperName = smOperName;
   }

   public String getScOper() {
      return this.scOper;
   }

   public void setScOper(String scOper) {
      this.scOper = scOper;
   }

   public String getSmOper() {
      return this.smOper;
   }

   public void setSmOper(String smOper) {
      this.smOper = smOper;
   }

   public String getPatientDepName() {
      return this.patientDepName;
   }

   public void setPatientDepName(String patientDepName) {
      this.patientDepName = patientDepName;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getExpenseCategoryNo() {
      return this.expenseCategoryNo;
   }

   public void setExpenseCategoryNo(String expenseCategoryNo) {
      this.expenseCategoryNo = expenseCategoryNo;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getPersonAge() {
      return this.personAge;
   }

   public void setPersonAge(String personAge) {
      this.personAge = personAge;
   }

   public Integer getTmStatus() {
      return this.tmStatus;
   }

   public void setTmStatus(Integer tmStatus) {
      this.tmStatus = tmStatus;
   }

   public Integer getPrintStatus() {
      return this.printStatus;
   }

   public void setPrintStatus(Integer printStatus) {
      this.printStatus = printStatus;
   }

   public Date getScSj() {
      return this.scSj;
   }

   public void setScSj(Date scSj) {
      this.scSj = scSj;
   }

   public Date getSmSj() {
      return this.smSj;
   }

   public void setSmSj(Date smSj) {
      this.smSj = smSj;
   }

   public Date getZfSj() {
      return this.zfSj;
   }

   public void setZfSj(Date zfSj) {
      this.zfSj = zfSj;
   }

   public Date getPrintSj() {
      return this.printSj;
   }

   public void setPrintSj(Date printSj) {
      this.printSj = printSj;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getItemSortNumber() {
      return this.itemSortNumber;
   }

   public void setItemSortNumber(String itemSortNumber) {
      this.itemSortNumber = itemSortNumber;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public String getBarcodeNo() {
      return this.barcodeNo;
   }

   public void setBarcodeNo(String barcodeNo) {
      this.barcodeNo = barcodeNo;
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

   public String getFlbh() {
      return this.flbh;
   }

   public void setFlbh(String flbh) {
      this.flbh = flbh;
   }

   public String getFlmc() {
      return this.flmc;
   }

   public void setFlmc(String flmc) {
      this.flmc = flmc;
   }

   public String getDzbm() {
      return this.dzbm;
   }

   public void setDzbm(String dzbm) {
      this.dzbm = dzbm;
   }

   public String getDzmc() {
      return this.dzmc;
   }

   public void setDzmc(String dzmc) {
      this.dzmc = dzmc;
   }

   public String getDepName() {
      return this.depName;
   }

   public void setDepName(String depName) {
      this.depName = depName;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }
}
