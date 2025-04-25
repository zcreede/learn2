package com.emr.project.monitor.service;

import com.emr.common.exception.job.TaskException;
import com.emr.project.monitor.domain.SysJob;
import java.util.List;
import org.quartz.SchedulerException;

public interface ISysJobService {
   List selectJobList(SysJob job);

   SysJob selectJobById(Long jobId);

   int pauseJob(SysJob job) throws SchedulerException;

   int resumeJob(SysJob job) throws SchedulerException;

   int deleteJob(SysJob job) throws SchedulerException;

   void deleteJobByIds(Long[] jobIds) throws SchedulerException;

   int changeStatus(SysJob job) throws SchedulerException;

   void run(SysJob job) throws SchedulerException;

   int insertJob(SysJob job) throws SchedulerException, TaskException;

   int updateJob(SysJob job) throws SchedulerException, TaskException;

   boolean checkCronExpressionIsValid(String cronExpression);
}
