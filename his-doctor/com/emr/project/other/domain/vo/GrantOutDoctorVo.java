package com.emr.project.other.domain.vo;

import com.emr.project.other.domain.GrantOutDoctor;

public class GrantOutDoctorVo extends GrantOutDoctor {
   private String creDateBegStr;
   private String creDateEndStr;
   private String subStatus;

   public String getSubStatus() {
      return this.subStatus;
   }

   public void setSubStatus(String subStatus) {
      this.subStatus = subStatus;
   }

   public String getCreDateBegStr() {
      return this.creDateBegStr;
   }

   public void setCreDateBegStr(String creDateBegStr) {
      this.creDateBegStr = creDateBegStr;
   }

   public String getCreDateEndStr() {
      return this.creDateEndStr;
   }

   public void setCreDateEndStr(String creDateEndStr) {
      this.creDateEndStr = creDateEndStr;
   }
}
