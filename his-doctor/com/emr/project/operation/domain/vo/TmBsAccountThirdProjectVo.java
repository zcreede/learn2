package com.emr.project.operation.domain.vo;

import com.emr.project.operation.domain.TmBsAccountThird;

public class TmBsAccountThirdProjectVo extends TmBsAccountThird {
   private String projectNo;
   private String projectName;

   public String getProjectNo() {
      return this.projectNo;
   }

   public void setProjectNo(String projectNo) {
      this.projectNo = projectNo;
   }

   public String getProjectName() {
      return this.projectName;
   }

   public void setProjectName(String projectName) {
      this.projectName = projectName;
   }
}
