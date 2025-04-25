package com.emr.project.emr.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class IndexCheckResultVo {
   private Long checkId;
   private Boolean conditionsRes;
   private String errorColumn;
   private String errorMsg;

   public IndexCheckResultVo() {
   }

   public IndexCheckResultVo(Long checkId, String errorColumn) {
      this.checkId = checkId;
      this.errorColumn = errorColumn;
   }

   public IndexCheckResultVo(String checkId, String errorColumn) {
      this.checkId = Long.parseLong(checkId);
      this.errorColumn = errorColumn;
   }

   public IndexCheckResultVo(Long checkId, String errorColumn, String errorMsg) {
      this.checkId = checkId;
      this.errorColumn = errorColumn;
      this.errorMsg = errorMsg;
   }

   public Long getCheckId() {
      return this.checkId;
   }

   public void setCheckId(Long checkId) {
      this.checkId = checkId;
   }

   public void setCheckId(String checkIdStr) {
      this.checkId = Long.parseLong(checkIdStr);
   }

   public String getErrorColumn() {
      return this.errorColumn;
   }

   public void setErrorColumn(String errorColumn) {
      this.errorColumn = errorColumn;
   }

   public String getErrorMsg() {
      return this.errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

   public Boolean getIsConditionsRes() {
      return this.conditionsRes;
   }

   public void setIsConditionsRes(Boolean conditionsRes) {
      this.conditionsRes = conditionsRes;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("checkId", this.getCheckId()).append("errorColumn", this.getErrorColumn()).append("errorMsg", this.getErrorMsg()).append("conditionsRes", this.getIsConditionsRes()).toString();
   }
}
