package com.emr.project.sys.mapper;

import com.emr.project.sys.domain.JobRecordLog;
import java.util.List;

public interface JobRecordLogMapper {
   JobRecordLog selectJobRecordLogById(Long id);

   List selectJobRecordLogList(JobRecordLog jobRecordLog);

   int insertJobRecordLog(JobRecordLog jobRecordLog);

   int updateJobRecordLog(JobRecordLog jobRecordLog);

   int deleteJobRecordLogById(Long id);

   int deleteJobRecordLogByIds(Long[] ids);
}
