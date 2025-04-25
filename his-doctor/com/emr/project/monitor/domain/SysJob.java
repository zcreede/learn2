package com.emr.project.monitor.domain;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.job.CronUtils;
import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysJob extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "任务序号",
      cellType = Excel.ColumnType.NUMERIC
   )
   private Long jobId;
   @Excel(
      name = "任务名称"
   )
   private String jobName;
   @Excel(
      name = "任务组名"
   )
   private String jobGroup;
   @Excel(
      name = "调用目标字符串"
   )
   private String invokeTarget;
   @Excel(
      name = "执行表达式 "
   )
   private String cronExpression;
   @Excel(
      name = "计划策略 ",
      readConverterExp = "0=默认,1=立即触发执行,2=触发一次执行,3=不触发立即执行"
   )
   private String misfirePolicy = "0";
   @Excel(
      name = "并发执行",
      readConverterExp = "0=允许,1=禁止"
   )
   private String concurrent;
   @Excel(
      name = "任务状态",
      readConverterExp = "0=正常,1=暂停"
   )
   private String status;

   public Long getJobId() {
      return this.jobId;
   }

   public void setJobId(Long jobId) {
      this.jobId = jobId;
   }

   public @NotBlank(
   message = "任务名称不能为空"
) @Size(
   min = 0,
   max = 64,
   message = "任务名称不能超过64个字符"
) String getJobName() {
      return this.jobName;
   }

   public void setJobName(String jobName) {
      this.jobName = jobName;
   }

   public String getJobGroup() {
      return this.jobGroup;
   }

   public void setJobGroup(String jobGroup) {
      this.jobGroup = jobGroup;
   }

   public @NotBlank(
   message = "调用目标字符串不能为空"
) @Size(
   min = 0,
   max = 500,
   message = "调用目标字符串长度不能超过500个字符"
) String getInvokeTarget() {
      return this.invokeTarget;
   }

   public void setInvokeTarget(String invokeTarget) {
      this.invokeTarget = invokeTarget;
   }

   public @NotBlank(
   message = "Cron执行表达式不能为空"
) @Size(
   min = 0,
   max = 255,
   message = "Cron执行表达式不能超过255个字符"
) String getCronExpression() {
      return this.cronExpression;
   }

   public void setCronExpression(String cronExpression) {
      this.cronExpression = cronExpression;
   }

   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   public Date getNextValidTime() {
      return StringUtils.isNotEmpty(this.cronExpression) ? CronUtils.getNextExecution(this.cronExpression) : null;
   }

   public String getMisfirePolicy() {
      return this.misfirePolicy;
   }

   public void setMisfirePolicy(String misfirePolicy) {
      this.misfirePolicy = misfirePolicy;
   }

   public String getConcurrent() {
      return this.concurrent;
   }

   public void setConcurrent(String concurrent) {
      this.concurrent = concurrent;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("jobId", this.getJobId()).append("jobName", this.getJobName()).append("jobGroup", this.getJobGroup()).append("cronExpression", this.getCronExpression()).append("nextValidTime", this.getNextValidTime()).append("misfirePolicy", this.getMisfirePolicy()).append("concurrent", this.getConcurrent()).append("status", this.getStatus()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
