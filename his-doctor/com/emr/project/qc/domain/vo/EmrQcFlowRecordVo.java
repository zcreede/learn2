package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.EmrQcFlowRecord;
import java.util.Date;

public class EmrQcFlowRecordVo extends EmrQcFlowRecord {
   private String patName;
   private Date outTime;

   public String getPatName() {
      return this.patName;
   }

   public void setPatName(String patName) {
      this.patName = patName;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }
}
