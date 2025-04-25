package com.emr.project.pdf.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TArMedicalinformationPdf extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   @ApiModelProperty("医疗机构代码")
   private String hospitalCode;
   @Excel(
      name = "患者所在科室"
   )
   @ApiModelProperty("患者所在科室")
   private String deptCode;
   @Excel(
      name = "患者ID"
   )
   @ApiModelProperty("患者ID")
   private String patientId;
   @Excel(
      name = "病案号"
   )
   @ApiModelProperty("病案号")
   private String caseNo;
   @Excel(
      name = "住院号"
   )
   @ApiModelProperty("住院号")
   private String admissionNo;
   @Excel(
      name = "婴儿住院号"
   )
   @ApiModelProperty("婴儿住院号")
   private String babyAdmissionno;
   @Excel(
      name = "姓名"
   )
   @ApiModelProperty("姓名")
   private String patientName;
   @Excel(
      name = "生成类型：1-医嘱单，2-费用总清单"
   )
   @ApiModelProperty("生成类型：1-医嘱单，2-费用总清单")
   private String genType;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm"
   )
   @Excel(
      name = "出院时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @ApiModelProperty("出院时间")
   private Date leaveHospitalDate;
   @Excel(
      name = "医嘱类型：1-长期医嘱、2-临时医嘱、3-汤剂医嘱"
   )
   @ApiModelProperty("医嘱类型：1-长期医嘱、2-临时医嘱、3-汤剂医嘱")
   private String orderType;
   @Excel(
      name = "文件存放路径"
   )
   @ApiModelProperty("文件存放路径")
   private String pdfPath;
   @Excel(
      name = "创建人名称"
   )
   @ApiModelProperty("创建人名称")
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   @ApiModelProperty("创建人编码")
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   @ApiModelProperty("创建日期")
   private Date creDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
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

   public void setBabyAdmissionno(String babyAdmissionno) {
      this.babyAdmissionno = babyAdmissionno;
   }

   public String getBabyAdmissionno() {
      return this.babyAdmissionno;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setGenType(String genType) {
      this.genType = genType;
   }

   public String getGenType() {
      return this.genType;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setPdfPath(String pdfPath) {
      this.pdfPath = pdfPath;
   }

   public String getPdfPath() {
      return this.pdfPath;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("hospitalCode", this.getHospitalCode()).append("deptCode", this.getDeptCode()).append("patientId", this.getPatientId()).append("caseNo", this.getCaseNo()).append("admissionNo", this.getAdmissionNo()).append("babyAdmissionno", this.getBabyAdmissionno()).append("patientName", this.getPatientName()).append("genType", this.getGenType()).append("leaveHospitalDate", this.getLeaveHospitalDate()).append("orderType", this.getOrderType()).append("pdfPath", this.getPdfPath()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
