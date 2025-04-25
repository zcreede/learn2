package com.emr.project.sys.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.project.sys.domain.JobRecordLog;
import com.emr.project.sys.mapper.JobRecordLogMapper;
import com.emr.project.sys.service.IJobRecordLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobRecordLogServiceImpl implements IJobRecordLogService {
   @Autowired
   private JobRecordLogMapper jobRecordLogMapper;

   public JobRecordLog selectJobRecordLogById(Long id) {
      return this.jobRecordLogMapper.selectJobRecordLogById(id);
   }

   public List selectJobRecordLogList(JobRecordLog jobRecordLog) {
      return this.jobRecordLogMapper.selectJobRecordLogList(jobRecordLog);
   }

   public int insertJobRecordLog(JobRecordLog jobRecordLog) {
      jobRecordLog.setId(SnowIdUtils.uniqueLong());
      return this.jobRecordLogMapper.insertJobRecordLog(jobRecordLog);
   }

   public int updateJobRecordLog(JobRecordLog jobRecordLog) {
      return this.jobRecordLogMapper.updateJobRecordLog(jobRecordLog);
   }

   public int deleteJobRecordLogByIds(Long[] ids) {
      return this.jobRecordLogMapper.deleteJobRecordLogByIds(ids);
   }

   public int deleteJobRecordLogById(Long id) {
      return this.jobRecordLogMapper.deleteJobRecordLogById(id);
   }
}
