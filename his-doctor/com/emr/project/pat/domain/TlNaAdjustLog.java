package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TlNaAdjustLog extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构代码"
   )
   private String hospitalCode;
   private Long id;
   @Excel(
      name = "病区编码"
   )
   private String wardNo;
   @Excel(
      name = "病区名称"
   )
   private String wardName;
   @Excel(
      name = "病案号"
   )
   private String caseNo;
   @Excel(
      name = "住院号"
   )
   private String admissionNo;
   @Excel(
      name = "入院次数"
   )
   private Integer hospitalizedCount;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "原业务编码"
   )
   private String serviceCodeOld;
   @Excel(
      name = "原业务名称"
   )
   private String serviceNameOld;
   @Excel(
      name = "新业务编码"
   )
   private String serviceCodeNew;
   @Excel(
      name = "新业务名称"
   )
   private String serviceNameNew;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "操作时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date operatorDate;
   @Excel(
      name = "操作员编码"
   )
   private String operatorNo;
   @Excel(
      name = "操作员名称"
   )
   private String operatorName;
   @Excel(
      name = "操作类型",
      readConverterExp = "t=m_na_dict_czlx"
   )
   private String operateType;
   @Excel(
      name = "业务类型",
      readConverterExp = "t=m_na_dict_ywlx"
   )
   private String serviceType;
   @Excel(
      name = "0：主业务；1：子业务 如病人入科",
      readConverterExp = "主="
   )
   private String mark;

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName;
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setServiceCodeOld(String serviceCodeOld) {
      this.serviceCodeOld = serviceCodeOld;
   }

   public String getServiceCodeOld() {
      return this.serviceCodeOld;
   }

   public void setServiceNameOld(String serviceNameOld) {
      this.serviceNameOld = serviceNameOld;
   }

   public String getServiceNameOld() {
      return this.serviceNameOld;
   }

   public void setServiceCodeNew(String serviceCodeNew) {
      this.serviceCodeNew = serviceCodeNew;
   }

   public String getServiceCodeNew() {
      return this.serviceCodeNew;
   }

   public void setServiceNameNew(String serviceNameNew) {
      this.serviceNameNew = serviceNameNew;
   }

   public String getServiceNameNew() {
      return this.serviceNameNew;
   }

   public void setOperatorDate(Date operatorDate) {
      this.operatorDate = operatorDate;
   }

   public Date getOperatorDate() {
      return this.operatorDate;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo;
   }

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperateType(String operateType) {
      this.operateType = operateType;
   }

   public String getOperateType() {
      return this.operateType;
   }

   public void setServiceType(String serviceType) {
      this.serviceType = serviceType;
   }

   public String getServiceType() {
      return this.serviceType;
   }

   public void setMark(String mark) {
      this.mark = mark;
   }

   public String getMark() {
      return this.mark;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("id", this.getId()).append("wardNo", this.getWardNo()).append("wardName", this.getWardName()).append("caseNo", this.getCaseNo()).append("admissionNo", this.getAdmissionNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("patientName", this.getPatientName()).append("serviceCodeOld", this.getServiceCodeOld()).append("serviceNameOld", this.getServiceNameOld()).append("serviceCodeNew", this.getServiceCodeNew()).append("serviceNameNew", this.getServiceNameNew()).append("operatorDate", this.getOperatorDate()).append("operatorNo", this.getOperatorNo()).append("operatorName", this.getOperatorName()).append("operateType", this.getOperateType()).append("serviceType", this.getServiceType()).append("mark", this.getMark()).toString();
   }
}
