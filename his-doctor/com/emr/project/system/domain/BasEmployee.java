package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BasEmployee extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "人员编号"
   )
   private String emplNumber;
   @Excel(
      name = "职员姓名"
   )
   private String emplName;
   @Excel(
      name = "助记码"
   )
   private String inputstrphtc;
   @Excel(
      name = "性别"
   )
   private String gender;
   @Excel(
      name = "性别名称"
   )
   private String genderName;
   @Excel(
      name = "联系电话"
   )
   private String phone;
   @Excel(
      name = "邮箱"
   )
   private String email;
   @Excel(
      name = "职称编码"
   )
   private String titleCode;
   @Excel(
      name = "职称"
   )
   private String titleName;
   @Excel(
      name = "民族"
   )
   private String nation;
   @Excel(
      name = "籍贯"
   )
   private String nativeplace;
   @Excel(
      name = "家庭住址"
   )
   private String familyAdress;
   @Excel(
      name = "政治面貌"
   )
   private String politiStatus;
   @Excel(
      name = "身份证号"
   )
   private String idNumber;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "出生日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date birthday;
   @Excel(
      name = "婚姻状况"
   )
   private String mariStatus;
   @Excel(
      name = "人员分类编号"
   )
   private String emplTypeCode;
   @Excel(
      name = "人员分类"
   )
   private String emplType;
   @Excel(
      name = "人员状态"
   )
   private String status;
   @Excel(
      name = "岗位"
   )
   private String quarters;
   @Excel(
      name = "岗位ID"
   )
   private String quartersCode;
   @Excel(
      name = "照片"
   )
   private String photo;
   @Excel(
      name = "实习人员标志",
      readConverterExp = "1=：实习人员"
   )
   private String subFlag;
   @Excel(
      name = "学历代码"
   )
   private String eduCd;
   @Excel(
      name = "学历名称"
   )
   private String eduName;
   @Excel(
      name = "来源单位/学校"
   )
   private String sourceOrg;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "实习开始时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date optBeg;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "实习结束时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date optEnd;
   @Excel(
      name = "证书唯一标识"
   )
   private String certId;
   private String delFlag;
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
      name = "创建人代码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
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
      name = "修改人代码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人名称"
   )
   private String altPerName;
   private String roleId;
   private String userId;
   private String deptId;
   private String deptName;
   private String deptCode;

   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getDeptId() {
      return this.deptId;
   }

   public void setDeptId(String deptId) {
      this.deptId = deptId;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setEmplNumber(String emplNumber) {
      this.emplNumber = emplNumber;
   }

   public String getEmplNumber() {
      return this.emplNumber;
   }

   public void setEmplName(String emplName) {
      this.emplName = emplName;
   }

   public String getEmplName() {
      return this.emplName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getGender() {
      return this.gender;
   }

   public void setGenderName(String genderName) {
      this.genderName = genderName;
   }

   public String getGenderName() {
      return this.genderName;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getEmail() {
      return this.email;
   }

   public void setTitleCode(String titleCode) {
      this.titleCode = titleCode;
   }

   public String getTitleCode() {
      return this.titleCode;
   }

   public void setTitleName(String titleName) {
      this.titleName = titleName;
   }

   public String getTitleName() {
      return this.titleName;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getNation() {
      return this.nation;
   }

   public void setNativeplace(String nativeplace) {
      this.nativeplace = nativeplace;
   }

   public String getNativeplace() {
      return this.nativeplace;
   }

   public void setFamilyAdress(String familyAdress) {
      this.familyAdress = familyAdress;
   }

   public String getFamilyAdress() {
      return this.familyAdress;
   }

   public void setPolitiStatus(String politiStatus) {
      this.politiStatus = politiStatus;
   }

   public String getPolitiStatus() {
      return this.politiStatus;
   }

   public void setIdNumber(String idNumber) {
      this.idNumber = idNumber;
   }

   public String getIdNumber() {
      return this.idNumber;
   }

   public void setBirthday(Date birthday) {
      this.birthday = birthday;
   }

   public Date getBirthday() {
      return this.birthday;
   }

   public void setMariStatus(String mariStatus) {
      this.mariStatus = mariStatus;
   }

   public String getMariStatus() {
      return this.mariStatus;
   }

   public void setEmplTypeCode(String emplTypeCode) {
      this.emplTypeCode = emplTypeCode;
   }

   public String getEmplTypeCode() {
      return this.emplTypeCode;
   }

   public void setEmplType(String emplType) {
      this.emplType = emplType;
   }

   public String getEmplType() {
      return this.emplType;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setQuarters(String quarters) {
      this.quarters = quarters;
   }

   public String getQuarters() {
      return this.quarters;
   }

   public void setQuartersCode(String quartersCode) {
      this.quartersCode = quartersCode;
   }

   public String getQuartersCode() {
      return this.quartersCode;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }

   public String getPhoto() {
      return this.photo;
   }

   public void setSubFlag(String subFlag) {
      this.subFlag = subFlag;
   }

   public String getSubFlag() {
      return this.subFlag;
   }

   public void setEduCd(String eduCd) {
      this.eduCd = eduCd;
   }

   public String getEduCd() {
      return this.eduCd;
   }

   public void setEduName(String eduName) {
      this.eduName = eduName;
   }

   public String getEduName() {
      return this.eduName;
   }

   public void setSourceOrg(String sourceOrg) {
      this.sourceOrg = sourceOrg;
   }

   public String getSourceOrg() {
      return this.sourceOrg;
   }

   public void setOptBeg(Date optBeg) {
      this.optBeg = optBeg;
   }

   public Date getOptBeg() {
      return this.optBeg;
   }

   public void setOptEnd(Date optEnd) {
      this.optEnd = optEnd;
   }

   public Date getOptEnd() {
      return this.optEnd;
   }

   public void setCertId(String certId) {
      this.certId = certId;
   }

   public String getCertId() {
      return this.certId;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("emplNumber", this.getEmplNumber()).append("emplName", this.getEmplName()).append("inputstrphtc", this.getInputstrphtc()).append("gender", this.getGender()).append("genderName", this.getGenderName()).append("phone", this.getPhone()).append("email", this.getEmail()).append("titleCode", this.getTitleCode()).append("titleName", this.getTitleName()).append("nation", this.getNation()).append("nativeplace", this.getNativeplace()).append("familyAdress", this.getFamilyAdress()).append("politiStatus", this.getPolitiStatus()).append("idNumber", this.getIdNumber()).append("birthday", this.getBirthday()).append("mariStatus", this.getMariStatus()).append("emplTypeCode", this.getEmplTypeCode()).append("emplType", this.getEmplType()).append("status", this.getStatus()).append("quarters", this.getQuarters()).append("quartersCode", this.getQuartersCode()).append("photo", this.getPhoto()).append("subFlag", this.getSubFlag()).append("eduCd", this.getEduCd()).append("eduName", this.getEduName()).append("sourceOrg", this.getSourceOrg()).append("optBeg", this.getOptBeg()).append("optEnd", this.getOptEnd()).append("certId", this.getCertId()).append("delFlag", this.getDelFlag()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).toString();
   }
}
