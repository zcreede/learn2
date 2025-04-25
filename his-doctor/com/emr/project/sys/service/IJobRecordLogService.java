package com.emr.project.sys.service;

import com.emr.project.sys.domain.JobRecordLog;
import java.util.List;

public interface IJobRecordLogService {
   JobRecordLog selectJobRecordLogById(Long id);

   List selectJobRecordLogList(JobRecordLog jobRecordLog);

   int insertJobRecordLog(JobRecordLog jobRecordLog);

   int updateJobRecordLog(JobRecordLog jobRecordLog);

   int deleteJobRecordLogByIds(Long[] ids);

   int deleteJobRecordLogById(Long id);
}
