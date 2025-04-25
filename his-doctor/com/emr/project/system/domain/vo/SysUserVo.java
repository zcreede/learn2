package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.SysUser;

public class SysUserVo extends SysUser {
   private String authorLevel;
   private String maxLevel;

   public String getMaxLevel() {
      return this.maxLevel;
   }

   public void setMaxLevel(String maxLevel) {
   }

   public String getAuthorLevel() {
      return this.authorLevel;
   }

   public void setAuthorLevel(String authorLevel) {
      this.authorLevel = authorLevel;
   }
}
