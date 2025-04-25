package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.EmrQcRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class RunTimeQcCheckedVo extends EmrQcRecord {
   private Long id;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd"
   )
   private Date qcDateBegin;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd"
   )
   private Date qcDateEnd;
   private String patientId;
   private String patientName;
   private String inpNo;
   private String recordNo;
   private String sex;
   private String age;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String deptName;
   private String inhosWmDiaName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private Date inhosTime;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private Date outTime;
   private String pcName;
   private String resDocName;
   private String operFlag;
   private String consFlag;
   private String dieFlag;
   private String bloodTrans;
   private String changeFlag;
   private String infectFlag;
   private String rescueFlag;
   private String dayNumFlag;
   private String costSumFlag;
   private String operIcon;
   private String consIcon;
   private String dieIcon;
   private String bloodIcon;
   private String changeIcon;
   private String infectIcon;
   private String rescueIcon;
   private String dayNumIcon;
   private String costSumIcon;
   private Integer dayNum;
   private Double costSum;
   private String bedNo;
   private String payTypeName;
   private String state;
   private String deptCd;
   private String ngCd;
   private String ngName;
   private String colorValue;
   private String alleInfo;
   private Double totalPatAva;

   public Double getTotalPatAva() {
      return this.totalPatAva;
   }

   public void setTotalPatAva(Double totalPatAva) {
      this.totalPatAva = totalPatAva;
   }

   public String getAlleInfo() {
      return this.alleInfo;
   }

   public void setAlleInfo(String alleInfo) {
      this.alleInfo = alleInfo;
   }

   public String getNgCd() {
      return this.ngCd;
   }

   public void setNgCd(String ngCd) {
      this.ngCd = ngCd;
   }

   public String getNgName() {
      return this.ngName;
   }

   public void setNgName(String ngName) {
      this.ngName = ngName;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getColorValue() {
      return this.colorValue;
   }

   public void setColorValue(String colorValue) {
      this.colorValue = colorValue;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getPayTypeName() {
      return this.payTypeName;
   }

   public void setPayTypeName(String payTypeName) {
      this.payTypeName = payTypeName;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Integer getDayNum() {
      return this.dayNum;
   }

   public void setDayNum(Integer dayNum) {
      this.dayNum = dayNum;
   }

   public Double getCostSum() {
      return this.costSum;
   }

   public void setCostSum(Double costSum) {
      this.costSum = costSum;
   }

   public Date getQcDateBegin() {
      return this.qcDateBegin;
   }

   public void setQcDateBegin(Date qcDateBegin) {
      this.qcDateBegin = qcDateBegin;
   }

   public Date getQcDateEnd() {
      return this.qcDateEnd;
   }

   public void setQcDateEnd(Date qcDateEnd) {
      this.qcDateEnd = qcDateEnd;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
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

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getInhosWmDiaName() {
      return this.inhosWmDiaName;
   }

   public void setInhosWmDiaName(String inhosWmDiaName) {
      this.inhosWmDiaName = inhosWmDiaName;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public String getPcName() {
      return this.pcName;
   }

   public void setPcName(String pcName) {
      this.pcName = pcName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getOperFlag() {
      return this.operFlag;
   }

   public void setOperFlag(String operFlag) {
      this.operFlag = operFlag;
   }

   public String getConsFlag() {
      return this.consFlag;
   }

   public void setConsFlag(String consFlag) {
      this.consFlag = consFlag;
   }

   public String getDieFlag() {
      return this.dieFlag;
   }

   public void setDieFlag(String dieFlag) {
      this.dieFlag = dieFlag;
   }

   public String getBloodTrans() {
      return this.bloodTrans;
   }

   public void setBloodTrans(String bloodTrans) {
      this.bloodTrans = bloodTrans;
   }

   public String getChangeFlag() {
      return this.changeFlag;
   }

   public void setChangeFlag(String changeFlag) {
      this.changeFlag = changeFlag;
   }

   public String getInfectFlag() {
      return this.infectFlag;
   }

   public void setInfectFlag(String infectFlag) {
      this.infectFlag = infectFlag;
   }

   public String getRescueFlag() {
      return this.rescueFlag;
   }

   public void setRescueFlag(String rescueFlag) {
      this.rescueFlag = rescueFlag;
   }

   public String getDayNumFlag() {
      return this.dayNumFlag;
   }

   public void setDayNumFlag(String dayNumFlag) {
      this.dayNumFlag = dayNumFlag;
   }

   public String getCostSumFlag() {
      return this.costSumFlag;
   }

   public void setCostSumFlag(String costSumFlag) {
      this.costSumFlag = costSumFlag;
   }

   public String getOperIcon() {
      return this.operIcon;
   }

   public void setOperIcon(String operIcon) {
      this.operIcon = operIcon;
   }

   public String getConsIcon() {
      return this.consIcon;
   }

   public void setConsIcon(String consIcon) {
      this.consIcon = consIcon;
   }

   public String getDieIcon() {
      return this.dieIcon;
   }

   public void setDieIcon(String dieIcon) {
      this.dieIcon = dieIcon;
   }

   public String getBloodIcon() {
      return this.bloodIcon;
   }

   public void setBloodIcon(String bloodIcon) {
      this.bloodIcon = bloodIcon;
   }

   public String getChangeIcon() {
      return this.changeIcon;
   }

   public void setChangeIcon(String changeIcon) {
      this.changeIcon = changeIcon;
   }

   public String getInfectIcon() {
      return this.infectIcon;
   }

   public void setInfectIcon(String infectIcon) {
      this.infectIcon = infectIcon;
   }

   public String getRescueIcon() {
      return this.rescueIcon;
   }

   public void setRescueIcon(String rescueIcon) {
      this.rescueIcon = rescueIcon;
   }

   public String getDayNumIcon() {
      return this.dayNumIcon;
   }

   public void setDayNumIcon(String dayNumIcon) {
      this.dayNumIcon = dayNumIcon;
   }

   public String getCostSumIcon() {
      return this.costSumIcon;
   }

   public void setCostSumIcon(String costSumIcon) {
      this.costSumIcon = costSumIcon;
   }
}
