package com.emr.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysRoleMenu {
   private Long roleId;
   private Long menuId;

   public Long getRoleId() {
      return this.roleId;
   }

   public void setRoleId(Long roleId) {
      this.roleId = roleId;
   }

   public Long getMenuId() {
      return this.menuId;
   }

   public void setMenuId(Long menuId) {
      this.menuId = menuId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("roleId", this.getRoleId()).append("menuId", this.getMenuId()).toString();
   }
}
