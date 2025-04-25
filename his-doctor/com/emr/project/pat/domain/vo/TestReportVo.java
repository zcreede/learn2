package com.emr.project.pat.domain.vo;

import com.emr.project.docOrder.domain.vo.TdPaApplyFormItemVo;
import com.emr.project.pat.domain.TestReport;
import com.emr.project.pat.domain.TestResult;
import java.util.Date;
import java.util.List;

public class TestReportVo extends TestReport {
   private String maItemName;
   private String patientName;
   private String sex;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String age;
   private String inpNo;
   private String bedNo;
   private Long testCount;
   private String maItemCd;
   private String itemState;
   private String itemStateName;
   private String clinRepDate;
   private String normalSign2;
   private Date startDate;
   private Date endDate;
   private List reasultList;
   private List reportList;
   List formItemList;
   private String type;
   private String docType;
   private String caseNo;
   private String browserType;
   private String browserTypeName;
   private String reportUrl;
   private String documentTypeCd;
   private String hospitalizedCount;
   private String visitType;

   public String getVisitType() {
      return this.visitType;
   }

   public void setVisitType(String visitType) {
      this.visitType = visitType;
   }

   public String getItemStateName() {
      return this.itemStateName;
   }

   public void setItemStateName(String itemStateName) {
      this.itemStateName = itemStateName;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
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

   public String getDocumentTypeCd() {
      return this.documentTypeCd;
   }

   public void setDocumentTypeCd(String documentTypeCd) {
      this.documentTypeCd = documentTypeCd;
   }

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getReportUrl() {
      return this.reportUrl;
   }

   public void setReportUrl(String reportUrl) {
      this.reportUrl = reportUrl;
   }

   public String getDocType() {
      return this.docType;
   }

   public void setDocType(String docType) {
      this.docType = docType;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public List getFormItemList() {
      return this.formItemList;
   }

   public void setFormItemList(List formItemList) {
      this.formItemList = formItemList;
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

   public String getNormalSign2() {
      return this.normalSign2;
   }

   public void setNormalSign2(String normalSign2) {
      this.normalSign2 = normalSign2;
   }

   public String getItemState() {
      return this.itemState;
   }

   public void setItemState(String itemState) {
      this.itemState = itemState;
   }

   public String getClinRepDate() {
      return this.clinRepDate;
   }

   public void setClinRepDate(String clinRepDate) {
      this.clinRepDate = clinRepDate;
   }

   public String getMaItemCd() {
      return this.maItemCd;
   }

   public void setMaItemCd(String maItemCd) {
      this.maItemCd = maItemCd;
   }

   public Long getTestCount() {
      return this.testCount;
   }

   public void setTestCount(Long testCount) {
      this.testCount = testCount;
   }

   public List getReportList() {
      return this.reportList;
   }

   public void setReportList(List reportList) {
      this.reportList = reportList;
   }

   public List getReasultList() {
      return this.reasultList;
   }

   public void setReasultList(List reasultList) {
      this.reasultList = reasultList;
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

   public String getMaItemName() {
      return this.maItemName;
   }

   public void setMaItemName(String maItemName) {
      this.maItemName = maItemName;
   }
}
