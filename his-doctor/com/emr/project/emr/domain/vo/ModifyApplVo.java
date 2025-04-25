package com.emr.project.emr.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.emr.domain.ModifyAppl;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class ModifyApplVo extends ModifyAppl {
   private String appTimeBegStr;
   private String appTimeEndStr;
   private List conStateList;
   private List ids;
   private String age;
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
   private String inpNo;
   private String mrState;
   private String patientMainId;
   private List mrStateList;
   private String emrTypeName;
   private String recoDateBegin;
   private String recoDateEnd;
   private String emrType;
   private String emrTypeNameReal;
   private Long mrFileIdReal;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date dateStart;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date dateEnd;

   public Long getMrFileIdReal() {
      return this.mrFileIdReal;
   }

   public void setMrFileIdReal(Long mrFileIdReal) {
      this.mrFileIdReal = mrFileIdReal;
   }

   public String getEmrType() {
      return this.emrType;
   }

   public void setEmrType(String emrType) {
      this.emrType = emrType;
   }

   public String getEmrTypeNameReal() {
      return this.emrTypeNameReal;
   }

   public void setEmrTypeNameReal(String emrTypeNameReal) {
      this.emrTypeNameReal = emrTypeNameReal;
   }

   public Date getDateStart() {
      return this.dateStart;
   }

   public void setDateStart(Date dateStart) {
      this.dateStart = dateStart;
   }

   public Date getDateEnd() {
      return this.dateEnd;
   }

   public void setDateEnd(Date dateEnd) {
      this.dateEnd = dateEnd;
   }

   public String getRecoDateBegin() {
      return this.recoDateBegin;
   }

   public void setRecoDateBegin(String recoDateBegin) {
      this.recoDateBegin = recoDateBegin;
   }

   public String getRecoDateEnd() {
      return this.recoDateEnd;
   }

   public void setRecoDateEnd(String recoDateEnd) {
      this.recoDateEnd = recoDateEnd;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public List getMrStateList() {
      return this.mrStateList;
   }

   public void setMrStateList(List mrStateList) {
      this.mrStateList = mrStateList;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
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

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public List getIds() {
      return this.ids;
   }

   public void setIds(List ids) {
      this.ids = ids;
   }

   public List getConStateList() {
      return this.conStateList;
   }

   public void setConStateList(List conStateList) {
      this.conStateList = conStateList;
   }

   public String getAppTimeBegStr() {
      return this.appTimeBegStr;
   }

   public void setAppTimeBegStr(String appTimeBegStr) {
      this.appTimeBegStr = appTimeBegStr;
   }

   public String getAppTimeEndStr() {
      return this.appTimeEndStr;
   }

   public void setAppTimeEndStr(String appTimeEndStr) {
      this.appTimeEndStr = appTimeEndStr;
   }
}
