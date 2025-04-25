package com.emr.project.operation.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceYy implements Serializable {
   private static final long serialVersionUID = 1L;
   private String id;
   private String hospitalNo;
   private String itemNo;
   private String itemName;
   private String itemNamePym;
   private String standardCode;
   private String standardName;
   private String itemNoSuffix;
   private String standardNamePym;
   private BigDecimal price;
   private String standard;
   private String specs;
   private String unit;
   private BigDecimal price1;
   private BigDecimal price2;
   private BigDecimal price3;
   private String outFlag;
   private String outUpper;
   private String hosFlag;
   private String hosUpper;
   private String accountUpper;
   private String materialFlag;
   private String depExecNo;
   private String composeFlag;
   private BigDecimal specialRate;
   private String medicalRecord1;
   private String medicalRecord2;
   private BigDecimal waringNumber;
   private String itemConnotation;
   private String itemExcept;
   private String itemOther;
   private String itemCodeYb;

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getHospitalNo() {
      return this.hospitalNo;
   }

   public void setHospitalNo(String hospitalNo) {
      this.hospitalNo = hospitalNo == null ? null : hospitalNo.trim();
   }

   public String getItemNo() {
      return this.itemNo;
   }

   public void setItemNo(String itemNo) {
      this.itemNo = itemNo == null ? null : itemNo.trim();
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName == null ? null : itemName.trim();
   }

   public String getItemNamePym() {
      return this.itemNamePym;
   }

   public void setItemNamePym(String itemNamePym) {
      this.itemNamePym = itemNamePym == null ? null : itemNamePym.trim();
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode == null ? null : standardCode.trim();
   }

   public String getStandardName() {
      return this.standardName;
   }

   public void setStandardName(String standardName) {
      this.standardName = standardName == null ? null : standardName.trim();
   }

   public String getItemNoSuffix() {
      return this.itemNoSuffix;
   }

   public void setItemNoSuffix(String itemNoSuffix) {
      this.itemNoSuffix = itemNoSuffix == null ? null : itemNoSuffix.trim();
   }

   public String getStandardNamePym() {
      return this.standardNamePym;
   }

   public void setStandardNamePym(String standardNamePym) {
      this.standardNamePym = standardNamePym == null ? null : standardNamePym.trim();
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getSpecs() {
      return this.specs;
   }

   public void setSpecs(String specs) {
      this.specs = specs == null ? null : specs.trim();
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit == null ? null : unit.trim();
   }

   public BigDecimal getPrice1() {
      return this.price1;
   }

   public void setPrice1(BigDecimal price1) {
      this.price1 = price1;
   }

   public BigDecimal getPrice2() {
      return this.price2;
   }

   public void setPrice2(BigDecimal price2) {
      this.price2 = price2;
   }

   public BigDecimal getPrice3() {
      return this.price3;
   }

   public void setPrice3(BigDecimal price3) {
      this.price3 = price3;
   }

   public String getOutFlag() {
      return this.outFlag;
   }

   public void setOutFlag(String outFlag) {
      this.outFlag = outFlag == null ? null : outFlag.trim();
   }

   public String getOutUpper() {
      return this.outUpper;
   }

   public void setOutUpper(String outUpper) {
      this.outUpper = outUpper == null ? null : outUpper.trim();
   }

   public String getHosFlag() {
      return this.hosFlag;
   }

   public void setHosFlag(String hosFlag) {
      this.hosFlag = hosFlag == null ? null : hosFlag.trim();
   }

   public String getHosUpper() {
      return this.hosUpper;
   }

   public void setHosUpper(String hosUpper) {
      this.hosUpper = hosUpper == null ? null : hosUpper.trim();
   }

   public String getAccountUpper() {
      return this.accountUpper;
   }

   public void setAccountUpper(String accountUpper) {
      this.accountUpper = accountUpper == null ? null : accountUpper.trim();
   }

   public String getMaterialFlag() {
      return this.materialFlag;
   }

   public void setMaterialFlag(String materialFlag) {
      this.materialFlag = materialFlag == null ? null : materialFlag.trim();
   }

   public String getDepExecNo() {
      return this.depExecNo;
   }

   public void setDepExecNo(String depExecNo) {
      this.depExecNo = depExecNo == null ? null : depExecNo.trim();
   }

   public String getComposeFlag() {
      return this.composeFlag;
   }

   public void setComposeFlag(String composeFlag) {
      this.composeFlag = composeFlag == null ? null : composeFlag.trim();
   }

   public BigDecimal getSpecialRate() {
      return this.specialRate;
   }

   public void setSpecialRate(BigDecimal specialRate) {
      this.specialRate = specialRate;
   }

   public String getMedicalRecord1() {
      return this.medicalRecord1;
   }

   public void setMedicalRecord1(String medicalRecord1) {
      this.medicalRecord1 = medicalRecord1 == null ? null : medicalRecord1.trim();
   }

   public String getMedicalRecord2() {
      return this.medicalRecord2;
   }

   public void setMedicalRecord2(String medicalRecord2) {
      this.medicalRecord2 = medicalRecord2 == null ? null : medicalRecord2.trim();
   }

   public BigDecimal getWaringNumber() {
      return this.waringNumber;
   }

   public void setWaringNumber(BigDecimal waringNumber) {
      this.waringNumber = waringNumber;
   }

   public String getItemConnotation() {
      return this.itemConnotation;
   }

   public void setItemConnotation(String itemConnotation) {
      this.itemConnotation = itemConnotation;
   }

   public String getItemExcept() {
      return this.itemExcept;
   }

   public void setItemExcept(String itemExcept) {
      this.itemExcept = itemExcept;
   }

   public String getItemOther() {
      return this.itemOther;
   }

   public void setItemOther(String itemOther) {
      this.itemOther = itemOther;
   }

   public String getItemCodeYb() {
      return this.itemCodeYb;
   }

   public void setItemCodeYb(String itemCodeYb) {
      this.itemCodeYb = itemCodeYb;
   }
}
