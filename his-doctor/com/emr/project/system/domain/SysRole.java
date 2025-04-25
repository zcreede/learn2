package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysRole extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "角色序号",
      cellType = Excel.ColumnType.NUMERIC
   )
   private Long roleId;
   @Excel(
      name = "角色名称"
   )
   private String roleName;
   @Excel(
      name = "角色权限"
   )
   private String roleKey;
   @Excel(
      name = "角色排序"
   )
   private String roleSort;
   @Excel(
      name = "数据范围",
      readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限,5=仅本人数据权限"
   )
   private String dataScope;
   private boolean menuCheckStrictly;
   private boolean deptCheckStrictly;
   @Excel(
      name = "角色状态",
      readConverterExp = "0=正常,1=停用"
   )
   private String status;
   private String delFlag;
   private boolean flag = false;
   private Long[] menuIds;
   private String[] deptIds;

   public SysRole() {
   }

   public SysRole(Long roleId) {
      this.roleId = roleId;
   }

   public Long getRoleId() {
      return this.roleId;
   }

   public void setRoleId(Long roleId) {
      this.roleId = roleId;
   }

   public boolean isAdmin() {
      return isAdmin(this.roleId);
   }

   public static boolean isAdmin(Long roleId) {
      return roleId != null && 1L == roleId;
   }

   public @NotBlank(
   message = "角色名称不能为空"
) @Size(
   min = 0,
   max = 30,
   message = "角色名称长度不能超过30个字符"
) String getRoleName() {
      return this.roleName;
   }

   public void setRoleName(String roleName) {
      this.roleName = roleName;
   }

   public @NotBlank(
   message = "权限字符不能为空"
) @Size(
   min = 0,
   max = 100,
   message = "权限字符长度不能超过100个字符"
) String getRoleKey() {
      return this.roleKey;
   }

   public void setRoleKey(String roleKey) {
      this.roleKey = roleKey;
   }

   public @NotBlank(
   message = "显示顺序不能为空"
) String getRoleSort() {
      return this.roleSort;
   }

   public void setRoleSort(String roleSort) {
      this.roleSort = roleSort;
   }

   public String getDataScope() {
      return this.dataScope;
   }

   public void setDataScope(String dataScope) {
      this.dataScope = dataScope;
   }

   public boolean isMenuCheckStrictly() {
      return this.menuCheckStrictly;
   }

   public void setMenuCheckStrictly(boolean menuCheckStrictly) {
      this.menuCheckStrictly = menuCheckStrictly;
   }

   public boolean isDeptCheckStrictly() {
      return this.deptCheckStrictly;
   }

   public void setDeptCheckStrictly(boolean deptCheckStrictly) {
      this.deptCheckStrictly = deptCheckStrictly;
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

   public boolean isFlag() {
      return this.flag;
   }

   public void setFlag(boolean flag) {
      this.flag = flag;
   }

   public Long[] getMenuIds() {
      return this.menuIds;
   }

   public void setMenuIds(Long[] menuIds) {
      this.menuIds = menuIds;
   }

   public String[] getDeptIds() {
      return this.deptIds;
   }

   public void setDeptIds(String[] deptIds) {
      this.deptIds = deptIds;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("roleId", this.getRoleId()).append("roleName", this.getRoleName()).append("roleKey", this.getRoleKey()).append("roleSort", this.getRoleSort()).append("dataScope", this.getDataScope()).append("menuCheckStrictly", this.isMenuCheckStrictly()).append("deptCheckStrictly", this.isDeptCheckStrictly()).append("status", this.getStatus()).append("delFlag", this.getDelFlag()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
