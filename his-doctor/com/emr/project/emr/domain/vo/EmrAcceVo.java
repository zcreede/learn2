package com.emr.project.emr.domain.vo;

public class EmrAcceVo {
   private String[] ids;
   private String authorLevel;
   private Integer roleOrUser;

   public String[] getIds() {
      return this.ids;
   }

   public void setIds(String[] ids) {
      this.ids = ids;
   }

   public String getAuthorLevel() {
      return this.authorLevel;
   }

   public void setAuthorLevel(String authorLevel) {
      this.authorLevel = authorLevel;
   }

   public Integer getRoleOrUser() {
      return this.roleOrUser;
   }

   public void setRoleOrUser(Integer roleOrUser) {
      this.roleOrUser = roleOrUser;
   }
}
