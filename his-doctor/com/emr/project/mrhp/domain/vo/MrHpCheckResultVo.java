package com.emr.project.mrhp.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpCheckResultVo {
   private Long checkId;
   private Boolean conditionsRes;
   private Boolean conditionsRes2;
   private String errorColumn;
   private String errorMsg;

   public MrHpCheckResultVo() {
   }

   public MrHpCheckResultVo(Long checkId, String errorColumn) {
      this.checkId = checkId;
      this.errorColumn = errorColumn;
   }

   public MrHpCheckResultVo(String checkId, String errorColumn) {
      this.checkId = Long.parseLong(checkId);
      this.errorColumn = errorColumn;
   }

   public MrHpCheckResultVo(Long checkId, String errorColumn, String errorMsg) {
      this.checkId = checkId;
      this.errorColumn = errorColumn;
      this.errorMsg = errorMsg;
   }

   public Boolean getConditionsRes2() {
      return this.conditionsRes2;
   }

   public void setConditionsRes2(Boolean conditionsRes2) {
      this.conditionsRes2 = conditionsRes2;
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
