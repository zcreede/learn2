package com.emr.project.task;

import com.emr.common.utils.ExceptionUtil;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.job.JobInvokeUtil;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.sys.domain.JobRecord;
import com.emr.project.sys.domain.JobRecordLog;
import com.emr.project.sys.domain.vo.JobRecordVo;
import com.emr.project.sys.service.IJobRecordLogService;
import com.emr.project.sys.service.IJobRecordService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobRecordTask {
   @Autowired
   private IJobRecordService jobRecordService;
   @Autowired
   private IJobRecordLogService jobRecordLogService;

   public void runJobRecords() {
      JobRecordVo jobRecordVo = new JobRecordVo();
      int[] statusArr = new int[]{0, 2};
      jobRecordVo.setJobStatusArr(statusArr);
      List<JobRecord> jobRecordList = this.jobRecordService.selectJobRecordList(jobRecordVo);
      if (jobRecordList != null && !jobRecordList.isEmpty()) {
         Iterator var4 = jobRecordList.iterator();

         while(true) {
            JobRecord jobRecord;
            JobRecord updateJobParam;
            JobRecordLog addLog;
            Date startDate;
            while(true) {
               if (!var4.hasNext()) {
                  return;
               }

               jobRecord = (JobRecord)var4.next();
               updateJobParam = new JobRecord();
               addLog = new JobRecordLog();
               startDate = new Date();
               boolean var16 = false;

               try {
                  var16 = true;
                  JobInvokeUtil.invokeMethod(jobRecord.getParamsJson());
                  updateJobParam.setId(jobRecord.getId());
                  updateJobParam.setJobStatus(1);
                  addLog.setRecordId(jobRecord.getId());
                  addLog.setStatus(2);
                  var16 = false;
                  break;
               } catch (Exception e) {
                  updateJobParam.setId(jobRecord.getId());
                  updateJobParam.setJobStatus(2);
                  addLog.setRecordId(jobRecord.getId());
                  addLog.setStatus(2);
                  String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
                  addLog.setExceptionInfo(errorMsg);
                  var16 = false;
               } finally {
                  if (var16) {
                     DruidUtil.clearDataSource();
                     long runMs = (new Date()).getTime() - startDate.getTime();
                     addLog.setMessage(jobRecord.getId() + " 总共耗时：" + runMs + "毫秒");
                     this.jobRecordService.updateJobRecord(updateJobParam);
                     this.jobRecordLogService.insertJobRecordLog(addLog);
                  }
               }

               DruidUtil.clearDataSource();
               long runMs = (new Date()).getTime() - startDate.getTime();
               addLog.setMessage(jobRecord.getId() + " 总共耗时：" + runMs + "毫秒");
               this.jobRecordService.updateJobRecord(updateJobParam);
               this.jobRecordLogService.insertJobRecordLog(addLog);
            }

            DruidUtil.clearDataSource();
            long runMs = (new Date()).getTime() - startDate.getTime();
            addLog.setMessage(jobRecord.getId() + " 总共耗时：" + runMs + "毫秒");
            this.jobRecordService.updateJobRecord(updateJobParam);
            this.jobRecordLogService.insertJobRecordLog(addLog);
         }
      }
   }
}
