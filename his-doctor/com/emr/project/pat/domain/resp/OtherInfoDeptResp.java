package com.emr.project.pat.domain.resp;

import com.emr.project.pat.domain.Otherinfo;

public class OtherInfoDeptResp extends Otherinfo {
   private String deptCode;

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }
}
