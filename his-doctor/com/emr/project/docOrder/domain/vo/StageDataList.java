package com.emr.project.docOrder.domain.vo;

import java.io.Serializable;
import java.util.List;

public class StageDataList implements Serializable {
   private String stageCode;
   private String stageName;
   private String projectType;
   private String stageGsljchr;
   private String stageGszljchr;
   private String type;
   private List children;

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public String getStageCode() {
      return this.stageCode;
   }

   public void setStageCode(String stageCode) {
      this.stageCode = stageCode;
   }

   public String getStageName() {
      return this.stageName;
   }

   public void setStageName(String stageName) {
      this.stageName = stageName;
   }

   public String getProjectType() {
      return this.projectType;
   }

   public void setProjectType(String projectType) {
      this.projectType = projectType;
   }

   public String getStageGsljchr() {
      return this.stageGsljchr;
   }

   public void setStageGsljchr(String stageGsljchr) {
      this.stageGsljchr = stageGsljchr;
   }

   public String getStageGszljchr() {
      return this.stageGszljchr;
   }

   public void setStageGszljchr(String stageGszljchr) {
      this.stageGszljchr = stageGszljchr;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }
}
