package com.emr.common.utils.job;

import com.emr.project.monitor.domain.SysJob;
import org.quartz.JobExecutionContext;

public class QuartzJobExecution extends AbstractQuartzJob {
   protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
      JobInvokeUtil.invokeMethod(sysJob);
   }
}
