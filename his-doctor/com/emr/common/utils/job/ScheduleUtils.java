package com.emr.common.utils.job;

import com.emr.common.constant.ScheduleConstants;
import com.emr.common.exception.job.TaskException;
import com.emr.project.monitor.domain.SysJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

public class ScheduleUtils {
   private static Class getQuartzJobClass(SysJob sysJob) {
      boolean isConcurrent = "0".equals(sysJob.getConcurrent());
      return isConcurrent ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;
   }

   public static TriggerKey getTriggerKey(Long jobId, String jobGroup) {
      return TriggerKey.triggerKey("TASK_CLASS_NAME" + jobId, jobGroup);
   }

   public static JobKey getJobKey(Long jobId, String jobGroup) {
      return JobKey.jobKey("TASK_CLASS_NAME" + jobId, jobGroup);
   }

   public static void createScheduleJob(Scheduler scheduler, SysJob job) throws SchedulerException, TaskException {
      Class<? extends Job> jobClass = getQuartzJobClass(job);
      Long jobId = job.getJobId();
      String jobGroup = job.getJobGroup();
      JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(getJobKey(jobId, jobGroup)).build();
      CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
      cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);
      CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobId, jobGroup)).withSchedule(cronScheduleBuilder).build();
      jobDetail.getJobDataMap().put("TASK_PROPERTIES", job);
      if (scheduler.checkExists(getJobKey(jobId, jobGroup))) {
         scheduler.deleteJob(getJobKey(jobId, jobGroup));
      }

      scheduler.scheduleJob(jobDetail, trigger);
      if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())) {
         scheduler.pauseJob(getJobKey(jobId, jobGroup));
      }

   }

   public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob job, CronScheduleBuilder cb) throws TaskException {
      switch (job.getMisfirePolicy()) {
         case "0":
            return cb;
         case "1":
            return cb.withMisfireHandlingInstructionIgnoreMisfires();
         case "2":
            return cb.withMisfireHandlingInstructionFireAndProceed();
         case "3":
            return cb.withMisfireHandlingInstructionDoNothing();
         default:
            throw new TaskException("The task misfire policy '" + job.getMisfirePolicy() + "' cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
      }
   }
}
