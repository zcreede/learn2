package com.emr.project.pat.domain.vo;

import com.emr.project.docOrder.domain.vo.TdPaApplyFormItemVo;
import com.emr.project.pat.domain.ExamItem;
import java.util.Date;
import java.util.List;

public class ExamItemVo extends ExamItem {
   private String patientName;
   private String maItemName;
   private String sex;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String age;
   private String inpNo;
   private String bedNo;
   private String deptName;
   private String asptAim;
   private String itemState;
   private String itemStateName;
   private Date startDate;
   private Date endDate;
   private String resType;
   private String applyFormNo;
   private String reportUrl;
   private String caseNo;
   private Integer hospitalizedCount;
   private String idcard;
   private Date hospitalizedDate;
   private Date leaveHospitalDate;
   private List examIdList;
   private String visitingStaffNo;
   private String visitingStaffName;
   private String examRepDateBegin;
   private String examRepDateEnd;
   private String overtimeFlag;
   private Long msgId;
   private int overtimeConfigure;
   private String mainFileId;
   private String patientMainId;
   private Integer browserType;
   private String browserTypeName;
   List formItemList;
   private String docType;
   private String identify;
   private String mrFileFlag;
   private String babyNo;
   private String babyName;
   private String babySex;
   private String visitType;

   public String getVisitType() {
      return this.visitType;
   }

   public void setVisitType(String visitType) {
      this.visitType = visitType;
   }

   public String getIdentify() {
      return this.identify;
   }

   public void setIdentify(String identify) {
      this.identify = identify;
   }

   public String getMrFileFlag() {
      return this.mrFileFlag;
   }

   public void setMrFileFlag(String mrFileFlag) {
      this.mrFileFlag = mrFileFlag;
   }

   public String getItemStateName() {
      return this.itemStateName;
   }

   public void setItemStateName(String itemStateName) {
      this.itemStateName = itemStateName;
   }

   public String getDocType() {
      return this.docType;
   }

   public void setDocType(String docType) {
      this.docType = docType;
   }

   public List getFormItemList() {
      return this.formItemList;
   }

   public void setFormItemList(List formItemList) {
      this.formItemList = formItemList;
   }

   public Integer getBrowserType() {
      return this.browserType;
   }

   public void setBrowserType(Integer browserType) {
      this.browserType = browserType;
   }

   public String getBrowserTypeName() {
      return this.browserTypeName;
   }

   public void setBrowserTypeName(String browserTypeName) {
      this.browserTypeName = browserTypeName;
   }

   public String getMainFileId() {
      return this.mainFileId;
   }

   public void setMainFileId(String mainFileId) {
      this.mainFileId = mainFileId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public int getOvertimeConfigure() {
      return this.overtimeConfigure;
   }

   public void setOvertimeConfigure(int overtimeConfigure) {
      this.overtimeConfigure = overtimeConfigure;
   }

   public Long getMsgId() {
      return this.msgId;
   }

   public void setMsgId(Long msgId) {
      this.msgId = msgId;
   }

   public String getOvertimeFlag() {
      return this.overtimeFlag;
   }

   public void setOvertimeFlag(String overtimeFlag) {
      this.overtimeFlag = overtimeFlag;
   }

   public String getExamRepDateBegin() {
      return this.examRepDateBegin;
   }

   public void setExamRepDateBegin(String examRepDateBegin) {
      this.examRepDateBegin = examRepDateBegin;
   }

   public String getExamRepDateEnd() {
      return this.examRepDateEnd;
   }

   public void setExamRepDateEnd(String examRepDateEnd) {
      this.examRepDateEnd = examRepDateEnd;
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

   public String getIdcard() {
      return this.idcard;
   }

   public void setIdcard(String idcard) {
      this.idcard = idcard;
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

   public String getReportUrl() {
      return this.reportUrl;
   }

   public void setReportUrl(String reportUrl) {
      this.reportUrl = reportUrl;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getResType() {
      return this.resType;
   }

   public void setResType(String resType) {
      this.resType = resType;
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

   public String getAsptAim() {
      return this.asptAim;
   }

   public void setAsptAim(String asptAim) {
      this.asptAim = asptAim;
   }

   public String getItemState() {
      return this.itemState;
   }

   public void setItemState(String itemState) {
      this.itemState = itemState;
   }

   public String getMaItemName() {
      return this.maItemName;
   }

   public void setMaItemName(String maItemName) {
      this.maItemName = maItemName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
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

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public List getExamIdList() {
      return this.examIdList;
   }

   public void setExamIdList(List examIdList) {
      this.examIdList = examIdList;
   }

   public String getVisitingStaffNo() {
      return this.visitingStaffNo;
   }

   public void setVisitingStaffNo(String visitingStaffNo) {
      this.visitingStaffNo = visitingStaffNo;
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName;
   }

   public String getBabyNo() {
      return this.babyNo;
   }

   public void setBabyNo(String babyNo) {
      this.babyNo = babyNo;
   }

   public String getBabyName() {
      return this.babyName;
   }

   public void setBabyName(String babyName) {
      this.babyName = babyName;
   }

   public String getBabySex() {
      return this.babySex;
   }

   public void setBabySex(String babySex) {
      this.babySex = babySex;
   }
}
