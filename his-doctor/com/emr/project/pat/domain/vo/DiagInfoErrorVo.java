package com.emr.project.pat.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;

public class DiagInfoErrorVo extends BaseEntity {
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
   private String medicalInsuranceCode;
   private String medicalInsuranceName;
   private String errMessage;

   public String getIcdCd() {
      return this.icdCd;
   }

   public void setIcdCd(String icdCd) {
      this.icdCd = icdCd;
   }

   public String getIcdName() {
      return this.icdName;
   }

   public void setIcdName(String icdName) {
      this.icdName = icdName;
   }

   public String getIcdCdAdd() {
      return this.icdCdAdd;
   }

   public void setIcdCdAdd(String icdCdAdd) {
      this.icdCdAdd = icdCdAdd;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getVerNum() {
      return this.verNum;
   }

   public void setVerNum(String verNum) {
      this.verNum = verNum;
   }

   public String getDictTypeId() {
      return this.dictTypeId;
   }

   public void setDictTypeId(String dictTypeId) {
      this.dictTypeId = dictTypeId;
   }

   public String getMedType() {
      return this.medType;
   }

   public void setMedType(String medType) {
      this.medType = medType;
   }

   public String getSpecDise() {
      return this.specDise;
   }

   public void setSpecDise(String specDise) {
      this.specDise = specDise;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
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

   public String getErrMessage() {
      return this.errMessage;
   }

   public void setErrMessage(String errMessage) {
      this.errMessage = errMessage;
   }
}
