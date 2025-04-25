package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdPaApplyForm;
import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.esSearch.domain.DrugAndClin;
import java.util.Date;
import java.util.List;

public class TdPaApplyFormVo extends TdPaApplyForm {
   private List itemList;
   private List applyItemList;
   private String inputFormat;
   private String printFlag;
   private String printFormat;
   private String applyFormName;
   private String age;
   private String sex;
   private String bedNo;
   private String patType;
   private String resDocName;
   private String proTypeName;
   private String nowAddrTel;
   private String weight;
   private Boolean recFlag;
   private String conDocName;
   List applyFormStatusList;
   private String cdssConfirmFlag;
   private String reportUrl;
   private String performDepSite;
   private String examId;
   private String applyFlag;
   private String applyTimeStart;
   private String applyTimeEnd;
   private String reportDateStart;
   private String reportDateEnd;
   private String bedid;
   private String browserType;
   private String browserTypeName;
   private Date sctmsj;
   private Date printSj;
   private String oper;
   private String printOper;
   private Date smSj;
   private String smOper;
   private Date scSj;
   private String scOper;
   private Long id;
   private String recieveUser;
   private Date recieveDt;
   private String testUser;
   private Date testDt;
   private String reportUser;
   private Date reportDt;
   private String reportPrintUser;
   private Date reportPrintDt;
   private String barcodeNo;
   private String babyName;
   private String babySex;
   private Date babyBirth;
   private String tmplFilePath;
   private String notice;

   public String getTmplFilePath() {
      return this.tmplFilePath;
   }

   public void setTmplFilePath(String tmplFilePath) {
      this.tmplFilePath = tmplFilePath;
   }

   public String getNotice() {
      return this.notice;
   }

   public void setNotice(String notice) {
      this.notice = notice;
   }

   public String getBarcodeNo() {
      return this.barcodeNo;
   }

   public void setBarcodeNo(String barcodeNo) {
      this.barcodeNo = barcodeNo;
   }

   public String getRecieveUser() {
      return this.recieveUser;
   }

   public void setRecieveUser(String recieveUser) {
      this.recieveUser = recieveUser;
   }

   public Date getRecieveDt() {
      return this.recieveDt;
   }

   public void setRecieveDt(Date recieveDt) {
      this.recieveDt = recieveDt;
   }

   public String getTestUser() {
      return this.testUser;
   }

   public void setTestUser(String testUser) {
      this.testUser = testUser;
   }

   public Date getTestDt() {
      return this.testDt;
   }

   public void setTestDt(Date testDt) {
      this.testDt = testDt;
   }

   public String getReportUser() {
      return this.reportUser;
   }

   public void setReportUser(String reportUser) {
      this.reportUser = reportUser;
   }

   public Date getReportDt() {
      return this.reportDt;
   }

   public void setReportDt(Date reportDt) {
      this.reportDt = reportDt;
   }

   public String getReportPrintUser() {
      return this.reportPrintUser;
   }

   public void setReportPrintUser(String reportPrintUser) {
      this.reportPrintUser = reportPrintUser;
   }

   public Date getReportPrintDt() {
      return this.reportPrintDt;
   }

   public void setReportPrintDt(Date reportPrintDt) {
      this.reportPrintDt = reportPrintDt;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Date getSmSj() {
      return this.smSj;
   }

   public void setSmSj(Date smSj) {
      this.smSj = smSj;
   }

   public String getSmOper() {
      return this.smOper;
   }

   public void setSmOper(String smOper) {
      this.smOper = smOper;
   }

   public Date getScSj() {
      return this.scSj;
   }

   public void setScSj(Date scSj) {
      this.scSj = scSj;
   }

   public String getScOper() {
      return this.scOper;
   }

   public void setScOper(String scOper) {
      this.scOper = scOper;
   }

   public String getOper() {
      return this.oper;
   }

   public void setOper(String oper) {
      this.oper = oper;
   }

   public String getPrintOper() {
      return this.printOper;
   }

   public void setPrintOper(String printOper) {
      this.printOper = printOper;
   }

   public Date getSctmsj() {
      return this.sctmsj;
   }

   public void setSctmsj(Date sctmsj) {
      this.sctmsj = sctmsj;
   }

   public Date getPrintSj() {
      return this.printSj;
   }

   public void setPrintSj(Date printSj) {
      this.printSj = printSj;
   }

   public String getBrowserType() {
      return this.browserType;
   }

   public void setBrowserType(String browserType) {
      this.browserType = browserType;
   }

   public String getBrowserTypeName() {
      return this.browserTypeName;
   }

   public void setBrowserTypeName(String browserTypeName) {
      this.browserTypeName = browserTypeName;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getApplyTimeStart() {
      return this.applyTimeStart;
   }

   public void setApplyTimeStart(String applyTimeStart) {
      this.applyTimeStart = applyTimeStart;
   }

   public String getApplyTimeEnd() {
      return this.applyTimeEnd;
   }

   public void setApplyTimeEnd(String applyTimeEnd) {
      this.applyTimeEnd = applyTimeEnd;
   }

   public String getReportDateStart() {
      return this.reportDateStart;
   }

   public void setReportDateStart(String reportDateStart) {
      this.reportDateStart = reportDateStart;
   }

   public String getReportDateEnd() {
      return this.reportDateEnd;
   }

   public void setReportDateEnd(String reportDateEnd) {
      this.reportDateEnd = reportDateEnd;
   }

   public String getApplyFlag() {
      return this.applyFlag;
   }

   public void setApplyFlag(String applyFlag) {
      this.applyFlag = applyFlag;
   }

   public String getExamId() {
      return this.examId;
   }

   public void setExamId(String examId) {
      this.examId = examId;
   }

   public String getPerformDepSite() {
      return this.performDepSite;
   }

   public void setPerformDepSite(String performDepSite) {
      this.performDepSite = performDepSite;
   }

   public String getReportUrl() {
      return this.reportUrl;
   }

   public void setReportUrl(String reportUrl) {
      this.reportUrl = reportUrl;
   }

   public String getCdssConfirmFlag() {
      return this.cdssConfirmFlag;
   }

   public void setCdssConfirmFlag(String cdssConfirmFlag) {
      this.cdssConfirmFlag = cdssConfirmFlag;
   }

   public List getApplyFormStatusList() {
      return this.applyFormStatusList;
   }

   public void setApplyFormStatusList(List applyFormStatusList) {
      this.applyFormStatusList = applyFormStatusList;
   }

   public String getConDocName() {
      return this.conDocName;
   }

   public void setConDocName(String conDocName) {
      this.conDocName = conDocName;
   }

   public Boolean getRecFlag() {
      return this.recFlag;
   }

   public void setRecFlag(Boolean recFlag) {
      this.recFlag = recFlag;
   }

   public String getWeight() {
      return this.weight;
   }

   public void setWeight(String weight) {
      this.weight = weight;
   }

   public String getProTypeName() {
      return this.proTypeName;
   }

   public void setProTypeName(String proTypeName) {
      this.proTypeName = proTypeName;
   }

   public String getNowAddrTel() {
      return this.nowAddrTel;
   }

   public void setNowAddrTel(String nowAddrTel) {
      this.nowAddrTel = nowAddrTel;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getPatType() {
      return this.patType;
   }

   public void setPatType(String patType) {
      this.patType = patType;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getApplyFormName() {
      return this.applyFormName;
   }

   public void setApplyFormName(String applyFormName) {
      this.applyFormName = applyFormName;
   }

   public String getInputFormat() {
      return this.inputFormat;
   }

   public void setInputFormat(String inputFormat) {
      this.inputFormat = inputFormat;
   }

   public String getPrintFlag() {
      return this.printFlag;
   }

   public void setPrintFlag(String printFlag) {
      this.printFlag = printFlag;
   }

   public String getPrintFormat() {
      return this.printFormat;
   }

   public void setPrintFormat(String printFormat) {
      this.printFormat = printFormat;
   }

   public List getApplyItemList() {
      return this.applyItemList;
   }

   public void setApplyItemList(List applyItemList) {
      this.applyItemList = applyItemList;
   }

   public List getItemList() {
      return this.itemList;
   }

   public void setItemList(List itemList) {
      this.itemList = itemList;
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

   public Date getBabyBirth() {
      return this.babyBirth;
   }

   public void setBabyBirth(Date babyBirth) {
      this.babyBirth = babyBirth;
   }
}
