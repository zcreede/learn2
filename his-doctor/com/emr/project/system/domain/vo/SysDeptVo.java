package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.SysDept;

public class SysDeptVo extends SysDept {
   private String tickstate;
   private String tempId;

   public String getTempId() {
      return this.tempId;
   }

   public void setTempId(String tempId) {
      this.tempId = tempId;
   }

   public String getTickstate() {
      return this.tickstate;
   }

   public void setTickstate(String tickstate) {
      this.tickstate = tickstate;
   }
}
