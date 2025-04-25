package com.emr.project.sys.service;

import com.emr.project.sys.domain.JobRecord;
import com.emr.project.sys.domain.vo.JobRecordVo;
import java.util.List;

public interface IJobRecordService {
   JobRecord selectJobRecordById(Long id);

   List selectJobRecordList(JobRecord jobRecord);

   List selectJobRecordList(JobRecordVo jobRecordVo);

   int insertJobRecord(JobRecord jobRecord);

   int updateJobRecord(JobRecord jobRecord);

   int deleteJobRecordByIds(Long[] ids);

   int deleteJobRecordById(Long id);
}
