package com.emr.project.webservice.domain.resp;

import java.util.Date;

public class WebServiceVteResult {
   private String evaluationTypeValue;
   private String resultScore;
   private String content;
   private String resultRiskKey;
   private String resultRiskValue;
   private String createUser;
   private Date createTime;
   private String confirmUser;
   private Date confirmTime;

   public String getEvaluationTypeValue() {
      return this.evaluationTypeValue;
   }

   public void setEvaluationTypeValue(String evaluationTypeValue) {
      this.evaluationTypeValue = evaluationTypeValue;
   }

   public String getResultScore() {
      return this.resultScore;
   }

   public void setResultScore(String resultScore) {
      this.resultScore = resultScore;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getResultRiskKey() {
      return this.resultRiskKey;
   }

   public void setResultRiskKey(String resultRiskKey) {
      this.resultRiskKey = resultRiskKey;
   }

   public String getResultRiskValue() {
      return this.resultRiskValue;
   }

   public void setResultRiskValue(String resultRiskValue) {
      this.resultRiskValue = resultRiskValue;
   }

   public String getCreateUser() {
      return this.createUser;
   }

   public void setCreateUser(String createUser) {
      this.createUser = createUser;
   }

   public Date getCreateTime() {
      return this.createTime;
   }

   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }

   public String getConfirmUser() {
      return this.confirmUser;
   }

   public void setConfirmUser(String confirmUser) {
      this.confirmUser = confirmUser;
   }

   public Date getConfirmTime() {
      return this.confirmTime;
   }

   public void setConfirmTime(Date confirmTime) {
      this.confirmTime = confirmTime;
   }
}
