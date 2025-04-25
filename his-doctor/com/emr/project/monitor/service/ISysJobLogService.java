package com.emr.project.monitor.service;

import com.emr.project.monitor.domain.SysJobLog;
import java.util.List;

public interface ISysJobLogService {
   List selectJobLogList(SysJobLog jobLog);

   SysJobLog selectJobLogById(Long jobLogId);

   void addJobLog(SysJobLog jobLog);

   int deleteJobLogByIds(Long[] logIds);

   int deleteJobLogById(Long jobId);

   void cleanJobLog();

   SysJobLog selectJobLogByTagStr(String tagStr) throws Exception;
}
