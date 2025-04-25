package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.Visitinfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class OperRoomVisitinfoVo extends Visitinfo {
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operDateTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date planOperTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date applyDate;
   private String operRoom;
   private String operRoomCd;
   private String operTable;
   private String operDiag;
   private String operName;
   private String operatorName;
   private String operAid1;
   private String anesthesia;
   private String anesthetist;
   private String operStatus;
   private Date startDate;
   private Date endDate;
   private String queryType;
   private String sex;
   private String age;
   private String nurseEmrUrl;
   private String operTypeCd;
   private Date shiftDateBegin;
   private Date shiftDateEnd;
   private Date planOperTimeBegin;
   private Date planOperTimeEnd;
   private String execDeptCd;
   private List noStatusList;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String wardNo;
   private String bedNo;
   private String departmentNo;

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getDepartmentNo() {
      return this.departmentNo;
   }

   public void setDepartmentNo(String departmentNo) {
      this.departmentNo = departmentNo;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
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

   public List getNoStatusList() {
      return this.noStatusList;
   }

   public void setNoStatusList(List noStatusList) {
      this.noStatusList = noStatusList;
   }

   public String getExecDeptCd() {
      return this.execDeptCd;
   }

   public void setExecDeptCd(String execDeptCd) {
      this.execDeptCd = execDeptCd;
   }

   public Date getShiftDateBegin() {
      return this.shiftDateBegin;
   }

   public void setShiftDateBegin(Date shiftDateBegin) {
      this.shiftDateBegin = shiftDateBegin;
   }

   public Date getShiftDateEnd() {
      return this.shiftDateEnd;
   }

   public void setShiftDateEnd(Date shiftDateEnd) {
      this.shiftDateEnd = shiftDateEnd;
   }

   public Date getPlanOperTimeBegin() {
      return this.planOperTimeBegin;
   }

   public void setPlanOperTimeBegin(Date planOperTimeBegin) {
      this.planOperTimeBegin = planOperTimeBegin;
   }

   public Date getPlanOperTimeEnd() {
      return this.planOperTimeEnd;
   }

   public void setPlanOperTimeEnd(Date planOperTimeEnd) {
      this.planOperTimeEnd = planOperTimeEnd;
   }

   public String getOperTypeCd() {
      return this.operTypeCd;
   }

   public void setOperTypeCd(String operTypeCd) {
      this.operTypeCd = operTypeCd;
   }

   public String getNurseEmrUrl() {
      return this.nurseEmrUrl;
   }

   public void setNurseEmrUrl(String nurseEmrUrl) {
      this.nurseEmrUrl = nurseEmrUrl;
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

   public String getQueryType() {
      return this.queryType;
   }

   public void setQueryType(String queryType) {
      this.queryType = queryType;
   }

   public Date getOperDateTime() {
      return this.operDateTime;
   }

   public void setOperDateTime(Date operDateTime) {
      this.operDateTime = operDateTime;
   }

   public String getOperRoom() {
      return this.operRoom;
   }

   public void setOperRoom(String operRoom) {
      this.operRoom = operRoom;
   }

   public String getOperRoomCd() {
      return this.operRoomCd;
   }

   public void setOperRoomCd(String operRoomCd) {
      this.operRoomCd = operRoomCd;
   }

   public String getOperTable() {
      return this.operTable;
   }

   public void setOperTable(String operTable) {
      this.operTable = operTable;
   }

   public String getOperDiag() {
      return this.operDiag;
   }

   public void setOperDiag(String operDiag) {
      this.operDiag = operDiag;
   }

   public String getOperName() {
      return this.operName;
   }

   public void setOperName(String operName) {
      this.operName = operName;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperAid1() {
      return this.operAid1;
   }

   public void setOperAid1(String operAid1) {
      this.operAid1 = operAid1;
   }

   public String getAnesthesia() {
      return this.anesthesia;
   }

   public void setAnesthesia(String anesthesia) {
      this.anesthesia = anesthesia;
   }

   public String getAnesthetist() {
      return this.anesthetist;
   }

   public void setAnesthetist(String anesthetist) {
      this.anesthetist = anesthetist;
   }

   public String getOperStatus() {
      return this.operStatus;
   }

   public void setOperStatus(String operStatus) {
      this.operStatus = operStatus;
   }

   public Date getPlanOperTime() {
      return this.planOperTime;
   }

   public void setPlanOperTime(Date planOperTime) {
      this.planOperTime = planOperTime;
   }

   public Date getApplyDate() {
      return this.applyDate;
   }

   public void setApplyDate(Date applyDate) {
      this.applyDate = applyDate;
   }
}
