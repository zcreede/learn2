package com.emr.project.emr.domain.vo;

import com.emr.project.system.domain.SysUser;

public class AcceUserVo {
   private SysUser sysUser;
   private String authorLevel;

   public SysUser getSysUser() {
      return this.sysUser;
   }

   public void setSysUser(SysUser sysUser) {
      this.sysUser = sysUser;
   }

   public String getAuthorLevel() {
      return this.authorLevel;
   }

   public void setAuthorLevel(String authorLevel) {
      this.authorLevel = authorLevel;
   }
}
