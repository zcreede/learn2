package com.emr.project.operation.domain.req;

public class OperationPatientReq {
   private Integer type;
   private String searchStr;
   private String deptCode;

   public Integer getType() {
      return this.type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public String getSearchStr() {
      return this.searchStr;
   }

   public void setSearchStr(String searchStr) {
      this.searchStr = searchStr;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }
}
