package com.emr.common.utils.job;

import com.emr.common.utils.ExceptionUtil;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.bean.BeanUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.project.monitor.domain.SysJob;
import com.emr.project.monitor.domain.SysJobLog;
import com.emr.project.monitor.mapper.SysJobMapper;
import com.emr.project.monitor.service.ISysJobLogService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractQuartzJob implements Job {
   private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);
   @Value("${job.valid}")
   private Integer jobValid;
   @Value("${job.groups}")
   private String groups;
   @Value("${job.validPdf}")
   private Integer jobValidPdf;
   @Value("${job.pdfJobId}")
   private String pdfJobId;
   @Autowired
   private SysJobMapper jobMapper;
   private static ThreadLocal threadLocal = new ThreadLocal();

   public void execute(JobExecutionContext context) throws JobExecutionException {
      log.debug("===========jobValid================：：" + this.jobValid);
      if (this.jobValid == null || this.jobValid != null && this.jobValid == 1) {
         log.debug("===========jobValid====2222============：：" + this.jobValid);
         SysJob sysJob = new SysJob();
         BeanUtils.copyBeanProp(sysJob, context.getMergedJobDataMap().get("TASK_PROPERTIES"));

         try {
            SysJob sysJobRes = this.jobMapper.selectJobById(sysJob.getJobId());
            if (sysJobRes != null && String.valueOf(0).equals(sysJobRes.getStatus())) {
               List<String> pdfJobIdList = Arrays.asList(this.pdfJobId.split(","));
               if (this.jobValidPdf != null && 1 == this.jobValidPdf) {
                  if (pdfJobIdList.contains(String.valueOf(sysJobRes.getJobId()))) {
                     this.before(context, sysJob);
                     if (sysJob != null) {
                        this.doExecute(context, sysJob);
                     }

                     this.after(context, sysJob, (Exception)null);
                  } else {
                     this.before(context, sysJob);
                     if (sysJob != null) {
                        this.doExecute(context, sysJob);
                     }

                     this.after(context, sysJob, (Exception)null);
                  }
               } else if (!pdfJobIdList.contains(String.valueOf(sysJobRes.getJobId()))) {
                  this.before(context, sysJob);
                  if (sysJob != null) {
                     this.doExecute(context, sysJob);
                  }

                  this.after(context, sysJob, (Exception)null);
               }
            }
         } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            this.after(context, sysJob, e);
         }
      }

   }

   protected void before(JobExecutionContext context, SysJob sysJob) {
      threadLocal.set(new Date());
   }

   protected void after(JobExecutionContext context, SysJob sysJob, Exception e) {
      Date startTime = (Date)threadLocal.get();
      threadLocal.remove();
      SysJobLog sysJobLog = new SysJobLog();
      sysJobLog.setJobLogId(SnowIdUtils.uniqueLong());
      sysJobLog.setJobName(sysJob.getJobName());
      sysJobLog.setJobGroup(sysJob.getJobGroup());
      sysJobLog.setInvokeTarget(sysJob.getInvokeTarget());
      sysJobLog.setStartTime(startTime);
      sysJobLog.setStopTime(new Date());
      long runMs = sysJobLog.getStopTime().getTime() - sysJobLog.getStartTime().getTime();
      sysJobLog.setJobMessage(sysJobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
      if (e != null) {
         sysJobLog.setStatus("1");
         String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
         sysJobLog.setExceptionInfo(errorMsg);
      } else {
         sysJobLog.setStatus("0");
      }

      ((ISysJobLogService)SpringUtils.getBean(ISysJobLogService.class)).addJobLog(sysJobLog);
   }

   protected abstract void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception;
}
