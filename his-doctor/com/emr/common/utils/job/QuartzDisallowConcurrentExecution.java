package com.emr.common.utils.job;

import com.emr.project.monitor.domain.SysJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
   protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
      JobInvokeUtil.invokeMethod(sysJob);
   }
}
