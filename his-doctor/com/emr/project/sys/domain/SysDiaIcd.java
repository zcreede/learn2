package com.emr.project.sys.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysDiaIcd extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long icdId;
   @Excel(
      name = "ICD编码"
   )
   private String icdCd;
   @Excel(
      name = "ICD名称"
   )
   private String icdName;
   @Excel(
      name = "诊断附加码"
   )
   private String icdCdAdd;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "版本号"
   )
   private String verNum;
   @Excel(
      name = "疾病类目ID",
      readConverterExp = "疾病类目ID（D:ICD10疾病编码;M:肿瘤形态学编码;Y:损伤中毒的外部原因;B:中医疾病编码;Z:中医病征编码)"
   )
   private String dictTypeId;
   @Excel(
      name = "中西医分类",
      readConverterExp = "X=Y：西医；ZY：中医"
   )
   private String medType;
   @Excel(
      name = "特定病种标志",
      readConverterExp = "1=：特定病种"
   )
   private String specDise;
   private String sex;
   private String firstDiagFlag;
   private String deathDiagFlag;
   private String medicalInsuranceCode;
   private String medicalInsuranceName;
   private Integer startAge;
   private Integer endAge;

   public Integer getStartAge() {
      return this.startAge;
   }

   public void setStartAge(Integer startAge) {
      this.startAge = startAge;
   }

   public Integer getEndAge() {
      return this.endAge;
   }

   public void setEndAge(Integer endAge) {
      this.endAge = endAge;
   }

   public String getMedicalInsuranceCode() {
      return this.medicalInsuranceCode;
   }

   public void setMedicalInsuranceCode(String medicalInsuranceCode) {
      this.medicalInsuranceCode = medicalInsuranceCode;
   }

   public String getMedicalInsuranceName() {
      return this.medicalInsuranceName;
   }

   public void setMedicalInsuranceName(String medicalInsuranceName) {
      this.medicalInsuranceName = medicalInsuranceName;
   }

   public void setIcdId(Long icdId) {
      this.icdId = icdId;
   }

   public Long getIcdId() {
      return this.icdId;
   }

   public void setIcdCd(String icdCd) {
      this.icdCd = icdCd;
   }

   public String getIcdCd() {
      return this.icdCd;
   }

   public void setIcdName(String icdName) {
      this.icdName = icdName;
   }

   public String getIcdName() {
      return this.icdName;
   }

   public void setIcdCdAdd(String icdCdAdd) {
      this.icdCdAdd = icdCdAdd;
   }

   public String getIcdCdAdd() {
      return this.icdCdAdd;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setVerNum(String verNum) {
      this.verNum = verNum;
   }

   public String getVerNum() {
      return this.verNum;
   }

   public void setDictTypeId(String dictTypeId) {
      this.dictTypeId = dictTypeId;
   }

   public String getDictTypeId() {
      return this.dictTypeId;
   }

   public void setMedType(String medType) {
      this.medType = medType;
   }

   public String getMedType() {
      return this.medType;
   }

   public void setSpecDise(String specDise) {
      this.specDise = specDise;
   }

   public String getSpecDise() {
      return this.specDise;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getFirstDiagFlag() {
      return this.firstDiagFlag;
   }

   public void setFirstDiagFlag(String firstDiagFlag) {
      this.firstDiagFlag = firstDiagFlag;
   }

   public String getDeathDiagFlag() {
      return this.deathDiagFlag;
   }

   public void setDeathDiagFlag(String deathDiagFlag) {
      this.deathDiagFlag = deathDiagFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("icdId", this.getIcdId()).append("icdCd", this.getIcdCd()).append("icdName", this.getIcdName()).append("icdCdAdd", this.getIcdCdAdd()).append("inputstrphtc", this.getInputstrphtc()).append("verNum", this.getVerNum()).append("dictTypeId", this.getDictTypeId()).append("medType", this.getMedType()).append("specDise", this.getSpecDise()).append("remark", this.getRemark()).append("sex", this.getSex()).append("firstDiagFlag", this.getFirstDiagFlag()).append("deathDiagFlag", this.getDeathDiagFlag()).toString();
   }
}
