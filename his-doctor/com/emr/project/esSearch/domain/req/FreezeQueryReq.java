package com.emr.project.esSearch.domain.req;

import java.util.List;

public class FreezeQueryReq {
   private String orgCd;
   private String drugCd;
   private String deptCode;
   private List freezeSerialList;
   private List freezeIdList;

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getDrugCd() {
      return this.drugCd;
   }

   public void setDrugCd(String drugCd) {
      this.drugCd = drugCd;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public List getFreezeSerialList() {
      return this.freezeSerialList;
   }

   public void setFreezeSerialList(List freezeSerialList) {
      this.freezeSerialList = freezeSerialList;
   }

   public List getFreezeIdList() {
      return this.freezeIdList;
   }

   public void setFreezeIdList(List freezeIdList) {
      this.freezeIdList = freezeIdList;
   }
}
