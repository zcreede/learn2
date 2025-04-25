package com.emr.project.sys.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class JobRecord extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "同步编码: 如果JOB_TYPE=sync JOB_CODE=SYNC_CODE"
   )
   private String jobCode;
   @Excel(
      name = "任务状态 0 待执行 1 执行成功 2 执行失败"
   )
   private Integer jobStatus;
   @Excel(
      name = "SqlVo参数json"
   )
   private String paramsJson;
   @Excel(
      name = "任务类型 sync 同步任务"
   )
   private String jobType;
   @Excel(
      name = "任务执行beanName"
   )
   private String jobBeanName;
   @Excel(
      name = "任务执行方法"
   )
   private String jobBeanMethod;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altTime;
   private int exeOrder;

   public int getExeOrder() {
      return this.exeOrder;
   }

   public void setExeOrder(int exeOrder) {
      this.exeOrder = exeOrder;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setJobCode(String jobCode) {
      this.jobCode = jobCode;
   }

   public String getJobCode() {
      return this.jobCode;
   }

   public void setJobStatus(Integer jobStatus) {
      this.jobStatus = jobStatus;
   }

   public Integer getJobStatus() {
      return this.jobStatus;
   }

   public void setParamsJson(String paramsJson) {
      this.paramsJson = paramsJson;
   }

   public String getParamsJson() {
      return this.paramsJson;
   }

   public void setJobType(String jobType) {
      this.jobType = jobType;
   }

   public String getJobType() {
      return this.jobType;
   }

   public void setJobBeanName(String jobBeanName) {
      this.jobBeanName = jobBeanName;
   }

   public String getJobBeanName() {
      return this.jobBeanName;
   }

   public void setJobBeanMethod(String jobBeanMethod) {
      this.jobBeanMethod = jobBeanMethod;
   }

   public String getJobBeanMethod() {
      return this.jobBeanMethod;
   }

   public void setAltTime(Date altTime) {
      this.altTime = altTime;
   }

   public Date getAltTime() {
      return this.altTime;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("jobCode", this.getJobCode()).append("jobStatus", this.getJobStatus()).append("paramsJson", this.getParamsJson()).append("jobType", this.getJobType()).append("jobBeanName", this.getJobBeanName()).append("jobBeanMethod", this.getJobBeanMethod()).append("createTime", this.getCreateTime()).append("altTime", this.getAltTime()).toString();
   }
}
