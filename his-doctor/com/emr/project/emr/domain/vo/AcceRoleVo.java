package com.emr.project.emr.domain.vo;

import com.emr.project.system.domain.SysRole;

public class AcceRoleVo extends SysRole {
   private String authorLevel;

   public String getAuthorLevel() {
      return this.authorLevel;
   }

   public void setAuthorLevel(String authorLevel) {
      this.authorLevel = authorLevel;
   }
}
