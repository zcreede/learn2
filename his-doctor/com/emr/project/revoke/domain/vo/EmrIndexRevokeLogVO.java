package com.emr.project.revoke.domain.vo;

import com.emr.project.revoke.domain.EmrIndexRevokeLog;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class EmrIndexRevokeLogVO extends EmrIndexRevokeLog {
   private String mrState;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date lastFileDate;

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public Date getLastFileDate() {
      return this.lastFileDate;
   }

   public void setLastFileDate(Date lastFileDate) {
      this.lastFileDate = lastFileDate;
   }
}
