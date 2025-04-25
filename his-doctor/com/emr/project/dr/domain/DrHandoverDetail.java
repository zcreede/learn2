package com.emr.project.dr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DrHandoverDetail extends BaseEntity {
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
      name = "住院号"
   )
   private String inpNo;
   @Excel(
      name = "姓名"
   )
   private String patientName;
   @Excel(
      name = "患者性别代码"
   )
   private String sexCd;
   @Excel(
      name = "患者性别"
   )
   private String sex;
   @Excel(
      name = "患者年龄"
   )
   private String age;
   @Excel(
      name = "床位号"
   )
   private String bedNo;
   @Excel(
      name = "临床诊断"
   )
   private String diaName;
   @Excel(
      name = "病情描述及措施(交接班事项)"
   )
   private String condSesc;
   private String delFlag;
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
   @Excel(
      name = "排序",
      readConverterExp = "存=患者标识中序号最小的"
   )
   private Long sort;
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
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
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

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getSex() {
      return this.sex;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getAge() {
      return this.age;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setDiaName(String diaName) {
      this.diaName = diaName;
   }

   public String getDiaName() {
      return this.diaName;
   }

   public void setCondSesc(String condSesc) {
      this.condSesc = condSesc;
   }

   public String getCondSesc() {
      return this.condSesc;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
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

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("mainId", this.getMainId()).append("patientId", this.getPatientId()).append("inpNo", this.getInpNo()).append("patientName", this.getPatientName()).append("sexCd", this.getSexCd()).append("sex", this.getSex()).append("age", this.getAge()).append("bedNo", this.getBedNo()).append("diaName", this.getDiaName()).append("condSesc", this.getCondSesc()).append("delFlag", this.getDelFlag()).append("patientTypeCode", this.getPatientTypeCode()).append("patientTypeName", this.getPatientTypeName()).append("sort", this.getSort()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
