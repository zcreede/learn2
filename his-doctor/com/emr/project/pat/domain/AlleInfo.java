package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AlleInfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "患者主索引ID"
   )
   private String patientMainId;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "类型  1 药物过敏   2 食物过敏    3 其他类型"
   )
   private String alleType;
   @Excel(
      name = "过敏物"
   )
   private String alleName;
   @Excel(
      name = "症状描述"
   )
   private String symDesc;
   @Excel(
      name = "严重程度"
   )
   private String cgiSi;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "发生日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date occurDate;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   private String alleCode;
   private String inputstrphtc;
   private String admissionNo;
   private Integer hospitalizedCount;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getAlleCode() {
      return this.alleCode;
   }

   public void setAlleCode(String alleCode) {
      this.alleCode = alleCode;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setAlleType(String alleType) {
      this.alleType = alleType;
   }

   public String getAlleType() {
      return this.alleType;
   }

   public void setAlleName(String alleName) {
      this.alleName = alleName;
   }

   public String getAlleName() {
      return this.alleName;
   }

   public void setSymDesc(String symDesc) {
      this.symDesc = symDesc;
   }

   public String getSymDesc() {
      return this.symDesc;
   }

   public void setCgiSi(String cgiSi) {
      this.cgiSi = cgiSi;
   }

   public String getCgiSi() {
      return this.cgiSi;
   }

   public void setOccurDate(Date occurDate) {
      this.occurDate = occurDate;
   }

   public Date getOccurDate() {
      return this.occurDate;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("patientMainId", this.getPatientMainId()).append("patientId", this.getPatientId()).append("alleType", this.getAlleType()).append("alleName", this.getAlleName()).append("symDesc", this.getSymDesc()).append("cgiSi", this.getCgiSi()).append("occurDate", this.getOccurDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
