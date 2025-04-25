package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BsStaff extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医院编码"
   )
   private String hospitalNo;
   @Excel(
      name = "职工内码"
   )
   private String staffCode;
   @Excel(
      name = "职工编码"
   )
   private String staffNo;
   @Excel(
      name = "职工姓名"
   )
   private String staffName;
   @Excel(
      name = "助记码"
   )
   private String staffNamePym;
   @Excel(
      name = "身份证号"
   )
   private String idcard;
   @Excel(
      name = "性别"
   )
   private String staffSex;
   @Excel(
      name = "民族"
   )
   private String nation;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "出生日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date birdthDate;
   @Excel(
      name = "政治面貌"
   )
   private String politicalStatus;
   @Excel(
      name = "职业类别"
   )
   private String occuType;
   @Excel(
      name = "职称"
   )
   private String title;
   @Excel(
      name = "聘用方式"
   )
   private String hiresWay;
   @Excel(
      name = "联系电话"
   )
   private String tel;
   @Excel(
      name = "籍贯"
   )
   private String nativePlace;
   @Excel(
      name = "个人简介"
   )
   private String profiles;
   @Excel(
      name = "在岗标志",
      readConverterExp = "0=正常1停用"
   )
   private String status;
   private String delFlag;
   @Excel(
      name = "照片"
   )
   private String pic;
   @Excel(
      name = "照片类型"
   )
   private String picType;
   @Excel(
      name = "人员分类"
   )
   private String staffType;
   @Excel(
      name = "密码",
      readConverterExp = "H=IS初始密码2"
   )
   private String password;
   @Excel(
      name = "HIS密码对应"
   )
   private String passwordHis;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "起止日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date beginDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "终止日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date endDate;
   @Excel(
      name = "电子签章"
   )
   private String caPic;
   @Excel(
      name = "电子签章类型"
   )
   private String caPicType;
   @Excel(
      name = "电子签章序列号"
   )
   private String caSerial;
   @Excel(
      name = "处方权"
   )
   private String prescribeFlag;
   @Excel(
      name = "手术级别"
   )
   private String operativeGrade;
   @Excel(
      name = "号单级别"
   )
   private String orderGrade;
   @Excel(
      name = "慢病权限"
   )
   private String chronicFlag;
   @Excel(
      name = "抗菌药物权限"
   )
   private String antibioticsFlag;
   @Excel(
      name = "bz_1"
   )
   private String bz1;
   @Excel(
      name = "bz_2"
   )
   private String bz2;
   @Excel(
      name = "bz_3"
   )
   private String bz3;
   private List roles;
   private String[] deptCodes;
   private String[] roleNos;
   private String roleNo;
   private List depts;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setHospitalNo(String hospitalNo) {
      this.hospitalNo = hospitalNo;
   }

   public String getHospitalNo() {
      return this.hospitalNo;
   }

   public void setStaffCode(String staffCode) {
      this.staffCode = staffCode;
   }

   public String getStaffCode() {
      return this.staffCode;
   }

   public void setStaffNo(String staffNo) {
      this.staffNo = staffNo;
   }

   public String getStaffNo() {
      return this.staffNo;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffNamePym(String staffNamePym) {
      this.staffNamePym = staffNamePym;
   }

   public String getStaffNamePym() {
      return this.staffNamePym;
   }

   public void setIdcard(String idcard) {
      this.idcard = idcard;
   }

   public String getIdcard() {
      return this.idcard;
   }

   public void setStaffSex(String staffSex) {
      this.staffSex = staffSex;
   }

   public String getStaffSex() {
      return this.staffSex;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getNation() {
      return this.nation;
   }

   public void setBirdthDate(Date birdthDate) {
      this.birdthDate = birdthDate;
   }

   public Date getBirdthDate() {
      return this.birdthDate;
   }

   public void setPoliticalStatus(String politicalStatus) {
      this.politicalStatus = politicalStatus;
   }

   public String getPoliticalStatus() {
      return this.politicalStatus;
   }

   public void setOccuType(String occuType) {
      this.occuType = occuType;
   }

   public String getOccuType() {
      return this.occuType;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitle() {
      return this.title;
   }

   public void setHiresWay(String hiresWay) {
      this.hiresWay = hiresWay;
   }

   public String getHiresWay() {
      return this.hiresWay;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getTel() {
      return this.tel;
   }

   public void setNativePlace(String nativePlace) {
      this.nativePlace = nativePlace;
   }

   public String getNativePlace() {
      return this.nativePlace;
   }

   public void setProfiles(String profiles) {
      this.profiles = profiles;
   }

   public String getProfiles() {
      return this.profiles;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setPic(String pic) {
      this.pic = pic;
   }

   public String getPic() {
      return this.pic;
   }

   public void setPicType(String picType) {
      this.picType = picType;
   }

   public String getPicType() {
      return this.picType;
   }

   public void setStaffType(String staffType) {
      this.staffType = staffType;
   }

   public String getStaffType() {
      return this.staffType;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPasswordHis(String passwordHis) {
      this.passwordHis = passwordHis;
   }

   public String getPasswordHis() {
      return this.passwordHis;
   }

   public void setBeginDate(Date beginDate) {
      this.beginDate = beginDate;
   }

   public Date getBeginDate() {
      return this.beginDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setCaPic(String caPic) {
      this.caPic = caPic;
   }

   public String getCaPic() {
      return this.caPic;
   }

   public void setCaPicType(String caPicType) {
      this.caPicType = caPicType;
   }

   public String getCaPicType() {
      return this.caPicType;
   }

   public void setCaSerial(String caSerial) {
      this.caSerial = caSerial;
   }

   public String getCaSerial() {
      return this.caSerial;
   }

   public void setPrescribeFlag(String prescribeFlag) {
      this.prescribeFlag = prescribeFlag;
   }

   public String getPrescribeFlag() {
      return this.prescribeFlag;
   }

   public void setOperativeGrade(String operativeGrade) {
      this.operativeGrade = operativeGrade;
   }

   public String getOperativeGrade() {
      return this.operativeGrade;
   }

   public void setOrderGrade(String orderGrade) {
      this.orderGrade = orderGrade;
   }

   public String getOrderGrade() {
      return this.orderGrade;
   }

   public void setChronicFlag(String chronicFlag) {
      this.chronicFlag = chronicFlag;
   }

   public String getChronicFlag() {
      return this.chronicFlag;
   }

   public void setAntibioticsFlag(String antibioticsFlag) {
      this.antibioticsFlag = antibioticsFlag;
   }

   public String getAntibioticsFlag() {
      return this.antibioticsFlag;
   }

   public void setBz1(String bz1) {
      this.bz1 = bz1;
   }

   public String getBz1() {
      return this.bz1;
   }

   public void setBz2(String bz2) {
      this.bz2 = bz2;
   }

   public String getBz2() {
      return this.bz2;
   }

   public void setBz3(String bz3) {
      this.bz3 = bz3;
   }

   public String getBz3() {
      return this.bz3;
   }

   public String[] getDeptCodes() {
      return this.deptCodes;
   }

   public void setDeptCodes(String[] deptCodes) {
      this.deptCodes = deptCodes;
   }

   public String[] getRoleNos() {
      return this.roleNos;
   }

   public void setRoleNos(String[] roleNos) {
      this.roleNos = roleNos;
   }

   public String getRoleNo() {
      return this.roleNo;
   }

   public void setRoleNo(String roleNo) {
      this.roleNo = roleNo;
   }

   public List getRoles() {
      return this.roles;
   }

   public void setRoles(List roles) {
      this.roles = roles;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("hospitalNo", this.getHospitalNo()).append("staffCode", this.getStaffCode()).append("staffNo", this.getStaffNo()).append("staffName", this.getStaffName()).append("staffNamePym", this.getStaffNamePym()).append("idcard", this.getIdcard()).append("staffSex", this.getStaffSex()).append("nation", this.getNation()).append("birdthDate", this.getBirdthDate()).append("politicalStatus", this.getPoliticalStatus()).append("occuType", this.getOccuType()).append("title", this.getTitle()).append("hiresWay", this.getHiresWay()).append("tel", this.getTel()).append("nativePlace", this.getNativePlace()).append("profiles", this.getProfiles()).append("status", this.getStatus()).append("delFlag", this.getDelFlag()).append("pic", this.getPic()).append("picType", this.getPicType()).append("staffType", this.getStaffType()).append("password", this.getPassword()).append("passwordHis", this.getPasswordHis()).append("beginDate", this.getBeginDate()).append("endDate", this.getEndDate()).append("caPic", this.getCaPic()).append("caPicType", this.getCaPicType()).append("caSerial", this.getCaSerial()).append("prescribeFlag", this.getPrescribeFlag()).append("operativeGrade", this.getOperativeGrade()).append("orderGrade", this.getOrderGrade()).append("chronicFlag", this.getChronicFlag()).append("antibioticsFlag", this.getAntibioticsFlag()).append("bz1", this.getBz1()).append("bz2", this.getBz2()).append("bz3", this.getBz3()).toString();
   }
}
