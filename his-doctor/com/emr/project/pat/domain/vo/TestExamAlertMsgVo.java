package com.emr.project.pat.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class TestExamAlertMsgVo {
   private String id;
   private String classCode;
   private String itemCd;
   private String itemName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date publicDate;
   private String patientName;
   private String inpNo;
   private String caseNo;
   private String bedNo;
   private String sex;
   private String age;
   private String resDocCd;
   private String resDocName;
   private String contentStr;
   private String repDeptCd;
   private String repDeptName;
   private String repDocCd;
   private String repDocName;
   private String appDeptCd;
   private String appDeptName;
   private String confirmInfo;
   private String confirmDocCd;
   private String confirmDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date confirmDate;
   private String patientId;
   private String patientMainId;
   private String mainFileId;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getMainFileId() {
      return this.mainFileId;
   }

   public void setMainFileId(String mainFileId) {
      this.mainFileId = mainFileId;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getClassCode() {
      return this.classCode;
   }

   public void setClassCode(String classCode) {
      this.classCode = classCode;
   }

   public String getItemCd() {
      return this.itemCd;
   }

   public void setItemCd(String itemCd) {
      this.itemCd = itemCd;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public Date getPublicDate() {
      return this.publicDate;
   }

   public void setPublicDate(Date publicDate) {
      this.publicDate = publicDate;
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

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
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

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getRepDeptCd() {
      return this.repDeptCd;
   }

   public void setRepDeptCd(String repDeptCd) {
      this.repDeptCd = repDeptCd;
   }

   public String getRepDeptName() {
      return this.repDeptName;
   }

   public void setRepDeptName(String repDeptName) {
      this.repDeptName = repDeptName;
   }

   public String getRepDocCd() {
      return this.repDocCd;
   }

   public void setRepDocCd(String repDocCd) {
      this.repDocCd = repDocCd;
   }

   public String getRepDocName() {
      return this.repDocName;
   }

   public void setRepDocName(String repDocName) {
      this.repDocName = repDocName;
   }

   public String getAppDeptCd() {
      return this.appDeptCd;
   }

   public void setAppDeptCd(String appDeptCd) {
      this.appDeptCd = appDeptCd;
   }

   public String getAppDeptName() {
      return this.appDeptName;
   }

   public void setAppDeptName(String appDeptName) {
      this.appDeptName = appDeptName;
   }

   public String getConfirmInfo() {
      return this.confirmInfo;
   }

   public void setConfirmInfo(String confirmInfo) {
      this.confirmInfo = confirmInfo;
   }

   public String getConfirmDocCd() {
      return this.confirmDocCd;
   }

   public void setConfirmDocCd(String confirmDocCd) {
      this.confirmDocCd = confirmDocCd;
   }

   public String getConfirmDocName() {
      return this.confirmDocName;
   }

   public void setConfirmDocName(String confirmDocName) {
      this.confirmDocName = confirmDocName;
   }

   public Date getConfirmDate() {
      return this.confirmDate;
   }

   public void setConfirmDate(Date confirmDate) {
      this.confirmDate = confirmDate;
   }

   public String getContentStr() {
      return this.contentStr;
   }

   public void setContentStr(String contentStr) {
      this.contentStr = contentStr;
   }
}
