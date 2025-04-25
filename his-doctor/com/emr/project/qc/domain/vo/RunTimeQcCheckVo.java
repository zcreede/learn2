package com.emr.project.qc.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class RunTimeQcCheckVo {
   private Long qcBillNo;
   private Long id;
   private int inOrOutFlag;
   private String beginDateTime;
   private String endDateTime;
   private String resDocCd;
   private String deptCd;
   private Integer dayNum;
   private Double costSum;
   private Long preId;
   private String patientId;
   private String patientName;
   private String inpNo;
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
   private String pcCdFlag;
   private String operIcon;
   private String consIcon;
   private String dieIcon;
   private String bloodIcon;
   private String changeIcon;
   private String infectIcon;
   private String rescueIcon;
   private String dayNumIcon;
   private String costSumIcon;
   private String bedNo;
   private String payTypeName;
   private String state;
   private String stateName;
   private Integer checkNum;
   private int checkNumFlag;
   private String pageFlag;
   private String editFlag;
   private String editMsg;
   private String switchFlag;
   private String switchMsg;
   private String recordNo;
   private String bloodFlag;
   @ApiModelProperty("有危急值患者：1是")
   private String alertFlag;
   @ApiModelProperty("危急值颜色")
   private String alertIcon;

   public String getBloodFlag() {
      return this.bloodFlag;
   }

   public void setBloodFlag(String bloodFlag) {
      this.bloodFlag = bloodFlag;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public int getCheckNumFlag() {
      return this.checkNumFlag;
   }

   public void setCheckNumFlag(int checkNumFlag) {
      this.checkNumFlag = checkNumFlag;
   }

   public Long getQcBillNo() {
      return this.qcBillNo;
   }

   public void setQcBillNo(Long qcBillNo) {
      this.qcBillNo = qcBillNo;
   }

   public Integer getCheckNum() {
      return this.checkNum;
   }

   public void setCheckNum(Integer checkNum) {
      this.checkNum = checkNum;
   }

   public String getSwitchFlag() {
      return this.switchFlag;
   }

   public void setSwitchFlag(String switchFlag) {
      this.switchFlag = switchFlag;
   }

   public String getSwitchMsg() {
      return this.switchMsg;
   }

   public void setSwitchMsg(String switchMsg) {
      this.switchMsg = switchMsg;
   }

   public String getEditMsg() {
      return this.editMsg;
   }

   public void setEditMsg(String editMsg) {
      this.editMsg = editMsg;
   }

   public String getEditFlag() {
      return this.editFlag;
   }

   public void setEditFlag(String editFlag) {
      this.editFlag = editFlag;
   }

   public String getAlertIcon() {
      return this.alertIcon;
   }

   public void setAlertIcon(String alertIcon) {
      this.alertIcon = alertIcon;
   }

   public String getPageFlag() {
      return this.pageFlag;
   }

   public void setPageFlag(String pageFlag) {
      this.pageFlag = pageFlag;
   }

   public String getStateName() {
      return this.stateName;
   }

   public void setStateName(String stateName) {
      this.stateName = stateName;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public Long getPreId() {
      return this.preId;
   }

   public void setPreId(Long preId) {
      this.preId = preId;
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

   public String getAlertFlag() {
      return this.alertFlag;
   }

   public void setAlertFlag(String alertFlag) {
      this.alertFlag = alertFlag;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPcCdFlag() {
      return this.pcCdFlag;
   }

   public void setPcCdFlag(String pcCdFlag) {
      this.pcCdFlag = pcCdFlag;
   }

   public int getInOrOutFlag() {
      return this.inOrOutFlag;
   }

   public void setInOrOutFlag(int inOrOutFlag) {
      this.inOrOutFlag = inOrOutFlag;
   }

   public String getBeginDateTime() {
      return this.beginDateTime;
   }

   public void setBeginDateTime(String beginDateTime) {
      this.beginDateTime = beginDateTime;
   }

   public String getEndDateTime() {
      return this.endDateTime;
   }

   public void setEndDateTime(String endDateTime) {
      this.endDateTime = endDateTime;
   }

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
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
