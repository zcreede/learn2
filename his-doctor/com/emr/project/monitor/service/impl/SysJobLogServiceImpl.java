package com.emr.project.monitor.service.impl;

import com.emr.project.monitor.domain.SysJobLog;
import com.emr.project.monitor.mapper.SysJobLogMapper;
import com.emr.project.monitor.service.ISysJobLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysJobLogServiceImpl implements ISysJobLogService {
   @Autowired
   private SysJobLogMapper jobLogMapper;

   public List selectJobLogList(SysJobLog jobLog) {
      return this.jobLogMapper.selectJobLogList(jobLog);
   }

   public SysJobLog selectJobLogById(Long jobLogId) {
      return this.jobLogMapper.selectJobLogById(jobLogId);
   }

   public void addJobLog(SysJobLog jobLog) {
      this.jobLogMapper.insertJobLog(jobLog);
   }

   public int deleteJobLogByIds(Long[] logIds) {
      return this.jobLogMapper.deleteJobLogByIds(logIds);
   }

   public int deleteJobLogById(Long jobId) {
      return this.jobLogMapper.deleteJobLogById(jobId);
   }

   public void cleanJobLog() {
      this.jobLogMapper.cleanJobLog();
   }

   public SysJobLog selectJobLogByTagStr(String tagStr) throws Exception {
      return this.jobLogMapper.selectJobLogByTagStr(tagStr);
   }
}
