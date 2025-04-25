package com.emr.project.webservice.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;

public class EmrProjectMessage extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String projectCode;
   @Excel(
      name = "项目名称"
   )
   private String projectName;
   @Excel(
      name = "项目对应key"
   )
   private String projectKey;

   public void setProjectCode(String projectCode) {
      this.projectCode = projectCode;
   }

   public String getProjectCode() {
      return this.projectCode;
   }

   public void setProjectName(String projectName) {
      this.projectName = projectName;
   }

   public String getProjectName() {
      return this.projectName;
   }

   public void setProjectKey(String projectKey) {
      this.projectKey = projectKey;
   }

   public String getProjectKey() {
      return this.projectKey;
   }
}
