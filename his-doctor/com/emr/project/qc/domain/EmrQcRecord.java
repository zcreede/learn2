package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcRecord extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "患者性别"
   )
   private String sex;
   @Excel(
      name = "就诊号"
   )
   private String inpNo;
   @Excel(
      name = "患者所在科室编号"
   )
   private String inDeptCd;
   @Excel(
      name = "患者所在科室"
   )
   private String inDeptName;
   @Excel(
      name = "质控环节  01.实时质控;02.科室质控 ;03.抽查质控;04.离线质控05.终末质控;5.质控组抽检;6.病案室病历抽检;7.病案室质控评分;8.专家组归档病历抽检"
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
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "质控日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date qcDate;
   @Excel(
      name = "完成时间"
   )
   private Date finishDate;
   @Excel(
      name = "修改时限"
   )
   private Integer limitHours;
   @Excel(
      name = "完成状态"
   )
   private String state;
   @Excel(
      name = "完成状态名称"
   )
   private String stateName;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
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
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   private Long qcBillNo;
   private String operReason;
   private String operTime;
   private String patientMainId;
   private Integer total;
   private String messageStr;
   private String bedNo;
   private String backoutFileBz;
   private String backoutFilePerCode;
   private String backoutFilePerName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private Date backoutFileDate;
   private String recordNo;

   public String getBackoutFileBz() {
      return this.backoutFileBz;
   }

   public void setBackoutFileBz(String backoutFileBz) {
      this.backoutFileBz = backoutFileBz;
   }

   public String getBackoutFilePerCode() {
      return this.backoutFilePerCode;
   }

   public void setBackoutFilePerCode(String backoutFilePerCode) {
      this.backoutFilePerCode = backoutFilePerCode;
   }

   public String getBackoutFilePerName() {
      return this.backoutFilePerName;
   }

   public void setBackoutFilePerName(String backoutFilePerName) {
      this.backoutFilePerName = backoutFilePerName;
   }

   public Date getBackoutFileDate() {
      return this.backoutFileDate;
   }

   public void setBackoutFileDate(Date backoutFileDate) {
      this.backoutFileDate = backoutFileDate;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getMessageStr() {
      return this.messageStr;
   }

   public void setMessageStr(String messageStr) {
      this.messageStr = messageStr;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public String getOperReason() {
      return this.operReason;
   }

   public void setOperReason(String operReason) {
      this.operReason = operReason;
   }

   public String getOperTime() {
      return this.operTime;
   }

   public void setOperTime(String operTime) {
      this.operTime = operTime;
   }

   public Long getQcBillNo() {
      return this.qcBillNo;
   }

   public void setQcBillNo(Long qcBillNo) {
      this.qcBillNo = qcBillNo;
   }

   public Date getFinishDate() {
      return this.finishDate;
   }

   public void setFinishDate(Date finishDate) {
      this.finishDate = finishDate;
   }

   public Integer getLimitHours() {
      return this.limitHours;
   }

   public void setLimitHours(Integer limitHours) {
      this.limitHours = limitHours;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getStateName() {
      return this.stateName;
   }

   public void setStateName(String stateName) {
      this.stateName = stateName;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getSex() {
      return this.sex;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInDeptCd(String inDeptCd) {
      this.inDeptCd = inDeptCd;
   }

   public String getInDeptCd() {
      return this.inDeptCd;
   }

   public void setInDeptName(String inDeptName) {
      this.inDeptName = inDeptName;
   }

   public String getInDeptName() {
      return this.inDeptName;
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

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("sex", this.getSex()).append("inpNo", this.getInpNo()).append("inDeptCd", this.getInDeptCd()).append("inDeptName", this.getInDeptName()).append("qcSection", this.getQcSection()).append("qcdoctCd", this.getQcdoctCd()).append("qcdoctName", this.getQcdoctName()).append("qcDate", this.getQcDate()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
