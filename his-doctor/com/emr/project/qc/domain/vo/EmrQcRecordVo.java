package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.EmrQcRecord;
import java.util.ArrayList;
import java.util.List;

public class EmrQcRecordVo extends EmrQcRecord {
   private Long recordId;
   List chartData = new ArrayList(1);

   public List getChartData() {
      return this.chartData;
   }

   public void setChartData(List chartData) {
      this.chartData = chartData;
   }

   public Long getRecordId() {
      return this.recordId;
   }

   public void setRecordId(Long recordId) {
      this.recordId = recordId;
   }
}
