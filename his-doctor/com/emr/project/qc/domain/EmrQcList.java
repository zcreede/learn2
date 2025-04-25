package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcList extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "质控记录ID"
   )
   private Long mainId;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "病历类型"
   )
   private String mrType;
   @Excel(
      name = "病历类型名称"
   )
   private String mrTypeName;
   @Excel(
      name = "规则名称"
   )
   private String ruleName;
   @Excel(
      name = "缺陷描述"
   )
   private String flawDesc;
   @Excel(
      name = "质控次数"
   )
   private Integer qconTimes;
   @Excel(
      name = "病历元素ID，用于定位元素位置"
   )
   private String emrEleId;
   @Excel(
      name = "病历元素名称，医嘱、体温单、病案首页存数据字段中文名称"
   )
   private String emrEleName;
   @Excel(
      name = "缺陷问题定位行号"
   )
   private String defectRow;
   @Excel(
      name = "病历元素内容"
   )
   private String eleContext;
   @Excel(
      name = "缺陷状态 0 未处理  1 确认不处理 2 已处理 3 已确认修改 4 驳回"
   )
   private String qcState;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "处理时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date treatTime;
   @Excel(
      name = "处理人编码"
   )
   private String treatCd;
   @Excel(
      name = "处理人姓名"
   )
   private String treatName;
   @Excel(
      name = "处理结果",
      readConverterExp = "1=：已修改；2：无需修改"
   )
   private String treatFlag;
   @Excel(
      name = "处理结果原因描述"
   )
   private String treatDesc;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "确认时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date conTime;
   @Excel(
      name = "确认人编码"
   )
   private String conCd;
   @Excel(
      name = "确认人姓名"
   )
   private String conName;
   @Excel(
      name = "处理期限单位",
      readConverterExp = "天=、时"
   )
   private String deadlineUnit;
   @Excel(
      name = "确认结论 1 已修改 2 未修改驳回"
   )
   private String conResult;
   @Excel(
      name = "确认说明"
   )
   private String conDesc;
   @Excel(
      name = "病历修改责任医师编号"
   )
   private String doctCd;
   @Excel(
      name = "病历修改责任医师姓名"
   )
   private String doctName;
   @Excel(
      name = "质控环节  01.实时质控;02.科室质控 ;03.抽查质控;04.离线质控05.终末质控; 5.质控组抽检;6.病案室病历抽检;7.病案室质控评分; 8.专家组归档病历抽检"
   )
   private String qcSection;
   @Excel(
      name = "质控人员编码"
   )
   private String qcdoctCd;
   @Excel(
      name = "质控人员名称"
   )
   private String qcdoctName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "质控日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date qcDate;
   @Excel(
      name = "处理期限"
   )
   private Long treatDeadline;
   @Excel(
      name = "病历文件索引ID，文档存文档ID，医嘱本存医嘱类型",
      readConverterExp = "临=时医嘱、长期医嘱、汤剂医嘱"
   )
   private String mrFileId;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date altDate;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date creDate;
   private String flawDate;
   private Long ruleId;
   private String defeLevel;
   private Long dbEleId;
   private String mrFileName;
   private String recordId;
   @Excel(
      name = "缺失病历缺陷标识 0否 1是"
   )
   private Integer missingFileFlag;

   public String getFlawDate() {
      return this.flawDate;
   }

   public void setFlawDate(String flawDate) {
      this.flawDate = flawDate;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public Long getDbEleId() {
      return this.dbEleId;
   }

   public void setDbEleId(Long dbEleId) {
      this.dbEleId = dbEleId;
   }

   public String getMrFileName() {
      return this.mrFileName;
   }

   public void setMrFileName(String mrFileName) {
      this.mrFileName = mrFileName;
   }

   public Integer getMissingFileFlag() {
      return this.missingFileFlag;
   }

   public void setMissingFileFlag(Integer missingFileFlag) {
      this.missingFileFlag = missingFileFlag;
   }

   public String getDefeLevel() {
      return this.defeLevel;
   }

   public void setDefeLevel(String defeLevel) {
      this.defeLevel = defeLevel;
   }

   public Long getRuleId() {
      return this.ruleId;
   }

   public void setRuleId(Long ruleId) {
      this.ruleId = ruleId;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setFlawDesc(String flawDesc) {
      this.flawDesc = flawDesc;
   }

   public String getFlawDesc() {
      return this.flawDesc;
   }

   public void setQconTimes(Integer qconTimes) {
      this.qconTimes = qconTimes;
   }

   public Integer getQconTimes() {
      return this.qconTimes;
   }

   public void setEmrEleId(String emrEleId) {
      this.emrEleId = emrEleId;
   }

   public String getEmrEleId() {
      return this.emrEleId;
   }

   public void setEmrEleName(String emrEleName) {
      this.emrEleName = emrEleName;
   }

   public String getEmrEleName() {
      return this.emrEleName;
   }

   public void setDefectRow(String defectRow) {
      this.defectRow = defectRow;
   }

   public String getDefectRow() {
      return this.defectRow;
   }

   public void setEleContext(String eleContext) {
      this.eleContext = eleContext;
   }

   public String getEleContext() {
      return this.eleContext;
   }

   public void setQcState(String qcState) {
      this.qcState = qcState;
   }

   public String getQcState() {
      return this.qcState;
   }

   public void setTreatTime(Date treatTime) {
      this.treatTime = treatTime;
   }

   public Date getTreatTime() {
      return this.treatTime;
   }

   public void setTreatCd(String treatCd) {
      this.treatCd = treatCd;
   }

   public String getTreatCd() {
      return this.treatCd;
   }

   public void setTreatName(String treatName) {
      this.treatName = treatName;
   }

   public String getTreatName() {
      return this.treatName;
   }

   public void setTreatFlag(String treatFlag) {
      this.treatFlag = treatFlag;
   }

   public String getTreatFlag() {
      return this.treatFlag;
   }

   public void setTreatDesc(String treatDesc) {
      this.treatDesc = treatDesc;
   }

   public String getTreatDesc() {
      return this.treatDesc;
   }

   public void setConTime(Date conTime) {
      this.conTime = conTime;
   }

   public Date getConTime() {
      return this.conTime;
   }

   public void setConCd(String conCd) {
      this.conCd = conCd;
   }

   public String getConCd() {
      return this.conCd;
   }

   public void setConName(String conName) {
      this.conName = conName;
   }

   public String getConName() {
      return this.conName;
   }

   public void setDeadlineUnit(String deadlineUnit) {
      this.deadlineUnit = deadlineUnit;
   }

   public String getDeadlineUnit() {
      return this.deadlineUnit;
   }

   public void setConResult(String conResult) {
      this.conResult = conResult;
   }

   public String getConResult() {
      return this.conResult;
   }

   public void setConDesc(String conDesc) {
      this.conDesc = conDesc;
   }

   public String getConDesc() {
      return this.conDesc;
   }

   public void setDoctCd(String doctCd) {
      this.doctCd = doctCd;
   }

   public String getDoctCd() {
      return this.doctCd;
   }

   public void setDoctName(String doctName) {
      this.doctName = doctName;
   }

   public String getDoctName() {
      return this.doctName;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }

   public String getQcSection() {
      return this.qcSection;
   }

   public void setQcdoctCd(String qcdoctCd) {
      this.qcdoctCd = qcdoctCd;
   }

   public String getQcdoctCd() {
      return this.qcdoctCd;
   }

   public void setQcdoctName(String qcdoctName) {
      this.qcdoctName = qcdoctName;
   }

   public String getQcdoctName() {
      return this.qcdoctName;
   }

   public void setQcDate(Date qcDate) {
      this.qcDate = qcDate;
   }

   public Date getQcDate() {
      return this.qcDate;
   }

   public void setTreatDeadline(Long treatDeadline) {
      this.treatDeadline = treatDeadline;
   }

   public Long getTreatDeadline() {
      return this.treatDeadline;
   }

   public void setMrFileId(String mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getMrFileId() {
      return this.mrFileId;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("mainId", this.getMainId()).append("patientId", this.getPatientId()).append("mrType", this.getMrType()).append("mrTypeName", this.getMrTypeName()).append("ruleName", this.getRuleName()).append("flawDesc", this.getFlawDesc()).append("qconTimes", this.getQconTimes()).append("emrEleId", this.getEmrEleId()).append("emrEleName", this.getEmrEleName()).append("defectRow", this.getDefectRow()).append("eleContext", this.getEleContext()).append("qcState", this.getQcState()).append("treatTime", this.getTreatTime()).append("treatCd", this.getTreatCd()).append("treatName", this.getTreatName()).append("treatFlag", this.getTreatFlag()).append("treatDesc", this.getTreatDesc()).append("conTime", this.getConTime()).append("conCd", this.getConCd()).append("conName", this.getConName()).append("deadlineUnit", this.getDeadlineUnit()).append("conResult", this.getConResult()).append("conDesc", this.getConDesc()).append("doctCd", this.getDoctCd()).append("doctName", this.getDoctName()).append("qcSection", this.getQcSection()).append("qcdoctCd", this.getQcdoctCd()).append("qcdoctName", this.getQcdoctName()).append("qcDate", this.getQcDate()).append("treatDeadline", this.getTreatDeadline()).append("mrFileId", this.getMrFileId()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("checkId", this.getRuleId()).append("defeLevel", this.getDefeLevel()).append("dbEleId", this.getDbEleId()).append("mrFileName", this.getMrFileName()).toString();
   }
}
