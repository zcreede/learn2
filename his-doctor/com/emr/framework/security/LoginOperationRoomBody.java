package com.emr.framework.security;

public class LoginOperationRoomBody {
   private String username;
   private String password;
   private String inpNo;
   private String deptCd;
   private String jzType;
   private String idcard;

   public String getJzType() {
      return this.jzType;
   }

   public void setJzType(String jzType) {
      this.jzType = jzType;
   }

   public String getIdcard() {
      return this.idcard;
   }

   public void setIdcard(String idcard) {
      this.idcard = idcard;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
