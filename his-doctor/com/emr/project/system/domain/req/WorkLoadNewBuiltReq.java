package com.emr.project.system.domain.req;

import java.io.Serializable;

public class WorkLoadNewBuiltReq implements Serializable {
   private String dateTime;
   private Integer originalNum;

   public String getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(String dateTime) {
      this.dateTime = dateTime;
   }

   public Integer getOriginalNum() {
      return this.originalNum;
   }

   public void setOriginalNum(Integer originalNum) {
      this.originalNum = originalNum;
   }
}
