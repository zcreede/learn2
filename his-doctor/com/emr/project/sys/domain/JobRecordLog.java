package com.emr.project.sys.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class JobRecordLog extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "任务表id"
   )
   private Long recordId;
   @Excel(
      name = "执行报错信息"
   )
   private String exceptionInfo;
   @Excel(
      name = "任务状态 1 执行成功 0 执行失败"
   )
   private Integer status;
   @Excel(
      name = "任务执行信息"
   )
   private String message;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setRecordId(Long recordId) {
      this.recordId = recordId;
   }

   public Long getRecordId() {
      return this.recordId;
   }

   public void setExceptionInfo(String exceptionInfo) {
      this.exceptionInfo = exceptionInfo;
   }

   public String getExceptionInfo() {
      return this.exceptionInfo;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public Integer getStatus() {
      return this.status;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getMessage() {
      return this.message;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("recordId", this.getRecordId()).append("exceptionInfo", this.getExceptionInfo()).append("status", this.getStatus()).append("message", this.getMessage()).append("createTime", this.getCreateTime()).toString();
   }
}
