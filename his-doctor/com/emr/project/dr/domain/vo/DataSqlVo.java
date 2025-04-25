package com.emr.project.dr.domain.vo;

public class DataSqlVo {
   private String dataSql;
   private String beginTime;
   private String endTime;
   private String deptCd;

   public String getDataSql() {
      return this.dataSql;
   }

   public void setDataSql(String dataSql) {
      this.dataSql = dataSql;
   }

   public String getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(String beginTime) {
      this.beginTime = beginTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }
}
