package com.emr.project.qc.domain;

public class EmrQcStatusMes {
   private Boolean flag;
   private String msg;

   public EmrQcStatusMes() {
      this.flag = Boolean.TRUE;
   }

   public Boolean getFlag() {
      return this.flag;
   }

   public void setFlag(Boolean flag) {
      this.flag = flag;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }
}
