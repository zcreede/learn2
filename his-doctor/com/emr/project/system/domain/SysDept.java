package com.emr.project.system.domain;

import com.emr.framework.web.domain.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysDept extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long deptId;
   private Long parentId;
   private String ancestors;
   private String deptName;
   private String orderNum;
   private String leader;
   private String phone;
   private String email;
   private String status;
   private String delFlag;
   private String parentName;
   private String searchCode;
   private String deptCode;
   private String deptType;
   private String sysFlag;
   private String orgCode;
   private String takeDrugModel;
   private String arrearsFlag;
   private String accountsMode;
   private String site;
   private String mrClass;
   private String chsCode;
   private String chsName;
   private String defaultDrugstore;
   private String deptNamePym1;
   private String deptNamePym2;
   private List children = new ArrayList();

   public String getDeptNamePym1() {
      return this.deptNamePym1;
   }

   public void setDeptNamePym1(String deptNamePym1) {
      this.deptNamePym1 = deptNamePym1;
   }

   public String getDeptNamePym2() {
      return this.deptNamePym2;
   }

   public void setDeptNamePym2(String deptNamePym2) {
      this.deptNamePym2 = deptNamePym2;
   }

   public String getDefaultDrugstore() {
      return this.defaultDrugstore;
   }

   public void setDefaultDrugstore(String defaultDrugstore) {
      this.defaultDrugstore = defaultDrugstore;
   }

   public String getChsCode() {
      return this.chsCode;
   }

   public void setChsCode(String chsCode) {
      this.chsCode = chsCode;
   }

   public String getChsName() {
      return this.chsName;
   }

   public void setChsName(String chsName) {
      this.chsName = chsName;
   }

   public String getMrClass() {
      return this.mrClass;
   }

   public void setMrClass(String mrClass) {
      this.mrClass = mrClass;
   }

   public String getSite() {
      return this.site;
   }

   public void setSite(String site) {
      this.site = site;
   }

   public String getAccountsMode() {
      return this.accountsMode;
   }

   public void setAccountsMode(String accountsMode) {
      this.accountsMode = accountsMode;
   }

   public String getArrearsFlag() {
      return this.arrearsFlag;
   }

   public void setArrearsFlag(String arrearsFlag) {
      this.arrearsFlag = arrearsFlag;
   }

   public String getTakeDrugModel() {
      return this.takeDrugModel;
   }

   public void setTakeDrugModel(String takeDrugModel) {
      this.takeDrugModel = takeDrugModel;
   }

   public String getOrgCode() {
      return this.orgCode;
   }

   public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
   }

   public String getSysFlag() {
      return this.sysFlag;
   }

   public void setSysFlag(String sysFlag) {
      this.sysFlag = sysFlag;
   }

   public String getDeptType() {
      return this.deptType;
   }

   public void setDeptType(String deptType) {
      this.deptType = deptType;
   }

   public String getSearchCode() {
      return this.searchCode;
   }

   public void setSearchCode(String searchCode) {
      this.searchCode = searchCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public Long getDeptId() {
      return this.deptId;
   }

   public void setDeptId(Long deptId) {
      this.deptId = deptId;
   }

   public Long getParentId() {
      return this.parentId;
   }

   public void setParentId(Long parentId) {
      this.parentId = parentId;
   }

   public String getAncestors() {
      return this.ancestors;
   }

   public void setAncestors(String ancestors) {
      this.ancestors = ancestors;
   }

   public @NotBlank(
   message = "部门名称不能为空"
) @Size(
   min = 0,
   max = 30,
   message = "部门名称长度不能超过30个字符"
) String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public @NotBlank(
   message = "显示顺序不能为空"
) String getOrderNum() {
      return this.orderNum;
   }

   public void setOrderNum(String orderNum) {
      this.orderNum = orderNum;
   }

   public String getLeader() {
      return this.leader;
   }

   public void setLeader(String leader) {
      this.leader = leader;
   }

   public @Size(
   min = 0,
   max = 11,
   message = "联系电话长度不能超过11个字符"
) String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public @Email(
   message = "邮箱格式不正确"
) @Size(
   min = 0,
   max = 50,
   message = "邮箱长度不能超过50个字符"
) String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getParentName() {
      return this.parentName;
   }

   public void setParentName(String parentName) {
      this.parentName = parentName;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("deptId", this.getDeptId()).append("parentId", this.getParentId()).append("ancestors", this.getAncestors()).append("deptName", this.getDeptName()).append("orderNum", this.getOrderNum()).append("leader", this.getLeader()).append("phone", this.getPhone()).append("email", this.getEmail()).append("status", this.getStatus()).append("delFlag", this.getDelFlag()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("searchCode", this.getSearchCode()).append("deptCode", this.getDeptCode()).append("sysFlag", this.getSysFlag()).toString();
   }
}
