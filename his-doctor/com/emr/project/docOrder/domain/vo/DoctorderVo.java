package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class DoctorderVo {
   private Long id;
   private String maNo;
   private String maGroupNo;
   private String patientId;
   private String maTypeCd;
   private String maTypeName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date maOpenTime;
   private String maTypeId;
   private String maTypeIdName;
   private String status;
   private String clinProName;
   private String spec;
   private String usage;
   private String rate;
   private String realDosage;
   private String unit;
   private String antiUseAim;
   private String udTimeExp;
   private String skinTestFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date exeTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date stopTime;
   private String sqlStr;
   private String beginTimeStr;
   private String endTimeStr;
   private List maNoList;
   private List maTypeIdList;
   private String belongFlag;
   private List patientIds;

   public List getPatientIds() {
      return this.patientIds;
   }

   public void setPatientIds(List patientIds) {
      this.patientIds = patientIds;
   }

   public String getBelongFlag() {
      return this.belongFlag;
   }

   public void setBelongFlag(String belongFlag) {
      this.belongFlag = belongFlag;
   }

   public List getMaTypeIdList() {
      return this.maTypeIdList;
   }

   public void setMaTypeIdList(List maTypeIdList) {
      this.maTypeIdList = maTypeIdList;
   }

   public List getMaNoList() {
      return this.maNoList;
   }

   public void setMaNoList(List maNoList) {
      this.maNoList = maNoList;
   }

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public String getBeginTimeStr() {
      return this.beginTimeStr;
   }

   public void setBeginTimeStr(String beginTimeStr) {
      this.beginTimeStr = beginTimeStr;
   }

   public String getEndTimeStr() {
      return this.endTimeStr;
   }

   public void setEndTimeStr(String endTimeStr) {
      this.endTimeStr = endTimeStr;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getMaNo() {
      return this.maNo;
   }

   public void setMaNo(String maNo) {
      this.maNo = maNo;
   }

   public String getMaGroupNo() {
      return this.maGroupNo;
   }

   public void setMaGroupNo(String maGroupNo) {
      this.maGroupNo = maGroupNo;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getMaTypeCd() {
      return this.maTypeCd;
   }

   public void setMaTypeCd(String maTypeCd) {
      this.maTypeCd = maTypeCd;
   }

   public String getMaTypeName() {
      return this.maTypeName;
   }

   public void setMaTypeName(String maTypeName) {
      this.maTypeName = maTypeName;
   }

   public Date getMaOpenTime() {
      return this.maOpenTime;
   }

   public void setMaOpenTime(Date maOpenTime) {
      this.maOpenTime = maOpenTime;
   }

   public String getMaTypeId() {
      return this.maTypeId;
   }

   public void setMaTypeId(String maTypeId) {
      this.maTypeId = maTypeId;
   }

   public String getMaTypeIdName() {
      return this.maTypeIdName;
   }

   public void setMaTypeIdName(String maTypeIdName) {
      this.maTypeIdName = maTypeIdName;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getClinProName() {
      return this.clinProName;
   }

   public void setClinProName(String clinProName) {
      this.clinProName = clinProName;
   }

   public String getSpec() {
      return this.spec;
   }

   public void setSpec(String spec) {
      this.spec = spec;
   }

   public String getUsage() {
      return this.usage;
   }

   public void setUsage(String usage) {
      this.usage = usage;
   }

   public String getRate() {
      return this.rate;
   }

   public void setRate(String rate) {
      this.rate = rate;
   }

   public String getRealDosage() {
      return this.realDosage;
   }

   public void setRealDosage(String realDosage) {
      this.realDosage = realDosage;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getAntiUseAim() {
      return this.antiUseAim;
   }

   public void setAntiUseAim(String antiUseAim) {
      this.antiUseAim = antiUseAim;
   }

   public String getUdTimeExp() {
      return this.udTimeExp;
   }

   public void setUdTimeExp(String udTimeExp) {
      this.udTimeExp = udTimeExp;
   }

   public String getSkinTestFlag() {
      return this.skinTestFlag;
   }

   public void setSkinTestFlag(String skinTestFlag) {
      this.skinTestFlag = skinTestFlag;
   }

   public Date getExeTime() {
      return this.exeTime;
   }

   public void setExeTime(Date exeTime) {
      this.exeTime = exeTime;
   }

   public Date getStopTime() {
      return this.stopTime;
   }

   public void setStopTime(Date stopTime) {
      this.stopTime = stopTime;
   }
}
