package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EditState;

public class EditStateVo extends EditState {
   private String beginTimeStr;
   private String endTimeStr;

   public String getBeginTimeStr() {
      return this.beginTimeStr;
   }

   public void setBeginTimeStr(String beginTimeStr) {
      this.beginTimeStr = beginTimeStr;
   }

   public String getEndTimeStr() {
      return this.endTimeStr;
   }

   public void setEndTimeStr(String endTimeStr) {
      this.endTimeStr = endTimeStr;
   }
}
