package com.emr.project.esSearch.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.annotation.ESMetaData;
import org.zxp.esclientrhl.enums.Analyzer;
import org.zxp.esclientrhl.enums.DataType;

@ESMetaData(
   indexName = "emrfile",
   number_of_shards = 5,
   number_of_replicas = 0,
   printLog = true
)
public class EmrFile {
   @ESID
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "医疗机构名称"
   )
   private String orgName;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   @ESMapping(
      datatype = DataType.text_type,
      analyzer = Analyzer.ik_max_word,
      search_analyzer = Analyzer.ik_max_word
   )
   private String patientName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "住院号或门诊号"
   )
   private String inpNo;
   @Excel(
      name = "住院次数"
   )
   private Integer visitId;
   @Excel(
      name = "病案号"
   )
   private String recordNo;
   @Excel(
      name = "患者性别"
   )
   private String sex;
   @Excel(
      name = "患者性别代码"
   )
   private String sexCd;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出生日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date birDate;
   @Excel(
      name = "年龄岁"
   )
   private Long ageY;
   @Excel(
      name = "年龄月"
   )
   private Long ageM;
   @Excel(
      name = "年龄日"
   )
   private Long ageD;
   @Excel(
      name = "年龄时"
   )
   private Long ageH;
   @Excel(
      name = "年龄分"
   )
   private Long ageMi;
   @Excel(
      name = "科室代码"
   )
   private String deptCd;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @Excel(
      name = "入院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date inhosTime;
   @Excel(
      name = "出院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date outTime;
   @Excel(
      name = "患者病情代码"
   )
   private String pcCd;
   @Excel(
      name = "患者病情名称"
   )
   private String pcName;
   @Excel(
      name = "入院途径代码"
   )
   private String inhosWayCd;
   @Excel(
      name = "入院途径名称"
   )
   private String inhosWayName;
   @Excel(
      name = "治疗类别(1.中医(1.1中医，1.2民族医) ；2.中西医；3.西医)"
   )
   private String treatType = "";
   @Excel(
      name = "治疗类别(1.中医(1.1中医，1.2民族医) ；2.中西医；3.西医)"
   )
   private String treatTypeName;
   @Excel(
      name = "护理等级代码"
   )
   private String ngCd;
   @Excel(
      name = "护理等级名称"
   )
   private String ngName;
   @Excel(
      name = "病历内容"
   )
   @ESMapping(
      datatype = DataType.text_type,
      analyzer = Analyzer.ik_max_word,
      search_analyzer = Analyzer.ik_max_word,
      ignore_above = 4000
   )
   private String mrFileContent;
   @Excel(
      name = "病历内容 简略"
   )
   private String mrFileContentSub;
   @Excel(
      name = "病历名称"
   )
   private String mrFileShowName;
   @Excel(
      name = "病历创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date mrFileCreDate;
   private String mrType;
   @ESMapping(
      datatype = DataType.text_type,
      analyzer = Analyzer.ik_max_word,
      search_analyzer = Analyzer.ik_max_word
   )
   private String patientInfoStr;
   @ESMapping(
      datatype = DataType.text_type,
      analyzer = Analyzer.ik_max_word,
      search_analyzer = Analyzer.ik_max_word
   )
   private String diagsStr;
   @ESMapping(
      datatype = DataType.text_type,
      analyzer = Analyzer.ik_max_word,
      search_analyzer = Analyzer.ik_max_word
   )
   private String mainSuitStr;
   @Excel(
      name = "归档时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date fileTime;
   private String patientMainId;
   private Long mainId;
   @ESMapping(
      datatype = DataType.text_type,
      analyzer = Analyzer.ik_max_word,
      search_analyzer = Analyzer.ik_max_word
   )
   private String diagAllStr;

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public String getMainSuitStr() {
      return this.mainSuitStr;
   }

   public void setMainSuitStr(String mainSuitStr) {
      this.mainSuitStr = mainSuitStr;
   }

   public String getPatientInfoStr() {
      return this.patientInfoStr;
   }

   public void setPatientInfoStr(String patientInfoStr) {
      this.patientInfoStr = patientInfoStr;
   }

   public String getDiagsStr() {
      return this.diagsStr;
   }

   public void setDiagsStr(String diagsStr) {
      this.diagsStr = diagsStr;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
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

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Integer getVisitId() {
      return this.visitId;
   }

   public void setVisitId(Integer visitId) {
      this.visitId = visitId;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
   }

   public Date getBirDate() {
      return this.birDate;
   }

   public void setBirDate(Date birDate) {
      this.birDate = birDate;
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

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
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

   public String getPcCd() {
      return this.pcCd;
   }

   public void setPcCd(String pcCd) {
      this.pcCd = pcCd;
   }

   public String getPcName() {
      return this.pcName;
   }

   public void setPcName(String pcName) {
      this.pcName = pcName;
   }

   public String getInhosWayCd() {
      return this.inhosWayCd;
   }

   public void setInhosWayCd(String inhosWayCd) {
      this.inhosWayCd = inhosWayCd;
   }

   public String getInhosWayName() {
      return this.inhosWayName;
   }

   public void setInhosWayName(String inhosWayName) {
      this.inhosWayName = inhosWayName;
   }

   public String getTreatType() {
      return this.treatType;
   }

   public void setTreatType(String treatType) {
      this.treatType = treatType;
   }

   public String getTreatTypeName() {
      return this.treatTypeName;
   }

   public void setTreatTypeName(String treatTypeName) {
      this.treatTypeName = treatTypeName;
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

   public String getMrFileContent() {
      return this.mrFileContent;
   }

   public void setMrFileContent(String mrFileContent) {
      this.mrFileContent = mrFileContent;
   }

   public String getMrFileContentSub() {
      return this.mrFileContentSub;
   }

   public void setMrFileContentSub(String mrFileContentSub) {
      this.mrFileContentSub = mrFileContentSub;
   }

   public String getMrFileShowName() {
      return this.mrFileShowName;
   }

   public void setMrFileShowName(String mrFileShowName) {
      this.mrFileShowName = mrFileShowName;
   }

   public Date getMrFileCreDate() {
      return this.mrFileCreDate;
   }

   public void setMrFileCreDate(Date mrFileCreDate) {
      this.mrFileCreDate = mrFileCreDate;
   }

   public Date getFileTime() {
      return this.fileTime;
   }

   public void setFileTime(Date fileTime) {
      this.fileTime = fileTime;
   }

   public String getDiagAllStr() {
      return this.diagAllStr;
   }

   public void setDiagAllStr(String diagAllStr) {
      this.diagAllStr = diagAllStr;
   }
}
