package com.emr.project.dr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DrHandoverPatientDel extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "交接班ID"
   )
   private Long mainId;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "姓名"
   )
   private String patientName;
   @Excel(
      name = "患者标识编码",
      readConverterExp = "多=个标识可以用分隔符，隔开"
   )
   private String patientTypeCode;
   @Excel(
      name = "患者表示名称",
      readConverterExp = "多=个标识可以用分隔符，隔开"
   )
   private String patientTypeName;
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
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;

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

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientTypeCode(String patientTypeCode) {
      this.patientTypeCode = patientTypeCode;
   }

   public String getPatientTypeCode() {
      return this.patientTypeCode;
   }

   public void setPatientTypeName(String patientTypeName) {
      this.patientTypeName = patientTypeName;
   }

   public String getPatientTypeName() {
      return this.patientTypeName;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
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

   public DrHandoverPatientDel(DrHandoverDetail drHandoverDetail) {
      this.id = drHandoverDetail.getId();
      this.patientId = drHandoverDetail.getPatientId();
      this.mainId = drHandoverDetail.getMainId();
      this.patientName = drHandoverDetail.getPatientName();
      this.patientTypeCode = drHandoverDetail.getPatientTypeCode();
      this.patientTypeName = drHandoverDetail.getPatientTypeName();
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("mainId", this.getMainId()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("patientTypeCode", this.getPatientTypeCode()).append("patientTypeName", this.getPatientTypeName()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
