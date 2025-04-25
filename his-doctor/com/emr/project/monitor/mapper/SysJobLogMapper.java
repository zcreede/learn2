package com.emr.project.monitor.mapper;

import com.emr.project.monitor.domain.SysJobLog;
import java.util.List;

public interface SysJobLogMapper {
   List selectJobLogList(SysJobLog jobLog);

   List selectJobLogAll();

   SysJobLog selectJobLogById(Long jobLogId);

   int insertJobLog(SysJobLog jobLog);

   int deleteJobLogByIds(Long[] logIds);

   int deleteJobLogById(Long jobId);

   void cleanJobLog();

   SysJobLog selectJobLogByTagStr(String tagStr);
}
