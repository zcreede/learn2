package com.emr.project.sys.mapper;

import com.emr.project.sys.domain.JobRecord;
import com.emr.project.sys.domain.vo.JobRecordVo;
import java.util.List;

public interface JobRecordMapper {
   JobRecord selectJobRecordById(Long id);

   List selectJobRecordList(JobRecord jobRecord);

   List selectJobRecordListByVo(JobRecordVo jobRecordVo);

   int insertJobRecord(JobRecord jobRecord);

   int updateJobRecord(JobRecord jobRecord);

   int deleteJobRecordById(Long id);

   int deleteJobRecordByIds(Long[] ids);
}
