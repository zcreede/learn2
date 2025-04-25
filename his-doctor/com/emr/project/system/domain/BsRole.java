package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BsRole extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String hospitalCode;
   @Excel(
      name = "角色编码"
   )
   private String roleNo;
   @Excel(
      name = "角色名称"
   )
   private String roleName;
   @Excel(
      name = "拼音码"
   )
   private String rolePym;
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
      name = "操作员内码"
   )
   private String operatorCode;
   @Excel(
      name = "操作员编码"
   )
   private String operatorNo;
   @Excel(
      name = "操作员名称"
   )
   private String operatorName;
   @Excel(
      name = "角色归属"
   )
   private String roleType;
   @Excel(
      name = "角色状态",
      readConverterExp = "0=正常1停用"
   )
   private String status;
   private String delFlag;
   @Excel(
      name = "角色所属主系统",
      readConverterExp = "模=块"
   )
   private String mainSystem;
   @Excel(
      name = "预留"
   )
   private String bz;
   private String moduleCode;
   private String moduleName;
   private Long[] menuIds;
   private String[] moduleCodes;

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

   public void setRoleNo(String roleNo) {
      this.roleNo = roleNo;
   }

   public String getRoleNo() {
      return this.roleNo;
   }

   public void setRoleName(String roleName) {
      this.roleName = roleName;
   }

   public String getRoleName() {
      return this.roleName;
   }

   public void setRolePym(String rolePym) {
      this.rolePym = rolePym;
   }

   public String getRolePym() {
      return this.rolePym;
   }

   public void setOperatorDate(Date operatorDate) {
      this.operatorDate = operatorDate;
   }

   public Date getOperatorDate() {
      return this.operatorDate;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode;
   }

   public String getOperatorCode() {
      return this.operatorCode;
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

   public void setRoleType(String roleType) {
      this.roleType = roleType;
   }

   public String getRoleType() {
      return this.roleType;
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

   public void setMainSystem(String mainSystem) {
      this.mainSystem = mainSystem;
   }

   public String getMainSystem() {
      return this.mainSystem;
   }

   public void setBz(String bz) {
      this.bz = bz;
   }

   public String getBz() {
      return this.bz;
   }

   public String getModuleCode() {
      return this.moduleCode;
   }

   public void setModuleCode(String moduleCode) {
      this.moduleCode = moduleCode;
   }

   public Long[] getMenuIds() {
      return this.menuIds;
   }

   public void setMenuIds(Long[] menuIds) {
      this.menuIds = menuIds;
   }

   public String getModuleName() {
      return this.moduleName;
   }

   public void setModuleName(String moduleName) {
      this.moduleName = moduleName;
   }

   public String[] getModuleCodes() {
      return this.moduleCodes;
   }

   public void setModuleCodes(String[] moduleCodes) {
      this.moduleCodes = moduleCodes;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("hospitalCode", this.getHospitalCode()).append("roleNo", this.getRoleNo()).append("roleName", this.getRoleName()).append("rolePym", this.getRolePym()).append("operatorDate", this.getOperatorDate()).append("operatorCode", this.getOperatorCode()).append("operatorNo", this.getOperatorNo()).append("operatorName", this.getOperatorName()).append("roleType", this.getRoleType()).append("status", this.getStatus()).append("delFlag", this.getDelFlag()).append("mainSystem", this.getMainSystem()).append("bz", this.getBz()).toString();
   }
}
