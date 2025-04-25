package com.emr.project.system.domain.resp;

public class DsPreserveOutResp {
   private Long dataSourceId;
   private String databaseDesc;

   public Long getDataSourceId() {
      return this.dataSourceId;
   }

   public void setDataSourceId(Long dataSourceId) {
      this.dataSourceId = dataSourceId;
   }

   public String getDatabaseDesc() {
      return this.databaseDesc;
   }

   public void setDatabaseDesc(String databaseDesc) {
      this.databaseDesc = databaseDesc;
   }
}
