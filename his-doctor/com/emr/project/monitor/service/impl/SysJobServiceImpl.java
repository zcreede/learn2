package com.emr.project.monitor.service.impl;

import com.emr.common.constant.ScheduleConstants;
import com.emr.common.exception.job.TaskException;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.job.CronUtils;
import com.emr.common.utils.job.ScheduleUtils;
import com.emr.project.monitor.domain.SysJob;
import com.emr.project.monitor.mapper.SysJobMapper;
import com.emr.project.monitor.service.ISysJobService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysJobServiceImpl implements ISysJobService {
   @Autowired
   private Scheduler scheduler;
   @Autowired
   private SysJobMapper jobMapper;

   @PostConstruct
   public void init() throws SchedulerException, TaskException {
      this.scheduler.clear();

      for(SysJob job : this.jobMapper.selectJobAll()) {
         ScheduleUtils.createScheduleJob(this.scheduler, job);
      }

   }

   public List selectJobList(SysJob job) {
      return this.jobMapper.selectJobList(job);
   }

   public SysJob selectJobById(Long jobId) {
      return this.jobMapper.selectJobById(jobId);
   }

   @Transactional
   public int pauseJob(SysJob job) throws SchedulerException {
      Long jobId = job.getJobId();
      String jobGroup = job.getJobGroup();
      job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
      int rows = this.jobMapper.updateJob(job);
      if (rows > 0) {
         this.scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
      }

      return rows;
   }

   @Transactional
   public int resumeJob(SysJob job) throws SchedulerException {
      Long jobId = job.getJobId();
      String jobGroup = job.getJobGroup();
      job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
      int rows = this.jobMapper.updateJob(job);
      if (rows > 0) {
         this.scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
      }

      return rows;
   }

   @Transactional
   public int deleteJob(SysJob job) throws SchedulerException {
      Long jobId = job.getJobId();
      String jobGroup = job.getJobGroup();
      int rows = this.jobMapper.deleteJobById(jobId);
      if (rows > 0) {
         this.scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
      }

      return rows;
   }

   @Transactional
   public void deleteJobByIds(Long[] jobIds) throws SchedulerException {
      for(Long jobId : jobIds) {
         SysJob job = this.jobMapper.selectJobById(jobId);
         this.deleteJob(job);
      }

   }

   @Transactional
   public int changeStatus(SysJob job) throws SchedulerException {
      int rows = 0;
      String status = job.getStatus();
      if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
         rows = this.resumeJob(job);
      } else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
         rows = this.pauseJob(job);
      }

      return rows;
   }

   @Transactional
   public void run(SysJob job) throws SchedulerException {
      Long jobId = job.getJobId();
      String jobGroup = job.getJobGroup();
      SysJob properties = this.selectJobById(job.getJobId());
      JobDataMap dataMap = new JobDataMap();
      dataMap.put("TASK_PROPERTIES", properties);
      this.scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
   }

   @Transactional
   public int insertJob(SysJob job) throws SchedulerException, TaskException {
      job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
      job.setJobId(SnowIdUtils.uniqueLong());
      int rows = this.jobMapper.insertJob(job);
      if (rows > 0) {
         ScheduleUtils.createScheduleJob(this.scheduler, job);
      }

      return rows;
   }

   @Transactional
   public int updateJob(SysJob job) throws SchedulerException, TaskException {
      SysJob properties = this.selectJobById(job.getJobId());
      int rows = this.jobMapper.updateJob(job);
      if (rows > 0) {
         this.updateSchedulerJob(job, properties.getJobGroup());
      }

      return rows;
   }

   public void updateSchedulerJob(SysJob job, String jobGroup) throws SchedulerException, TaskException {
      Long jobId = job.getJobId();
      JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
      if (this.scheduler.checkExists(jobKey)) {
         this.scheduler.deleteJob(jobKey);
      }

      ScheduleUtils.createScheduleJob(this.scheduler, job);
   }

   public boolean checkCronExpressionIsValid(String cronExpression) {
      return CronUtils.isValid(cronExpression);
   }
}
