package com.emr.project.sys.domain.vo;

import com.emr.project.sys.domain.JobRecord;

public class JobRecordVo extends JobRecord {
   public int[] jobStatusArr;

   public int[] getJobStatusArr() {
      return this.jobStatusArr;
   }

   public void setJobStatusArr(int[] jobStatusArr) {
      this.jobStatusArr = jobStatusArr;
   }
}
