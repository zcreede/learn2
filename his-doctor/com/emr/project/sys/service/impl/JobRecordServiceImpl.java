package com.emr.project.sys.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.project.sys.domain.JobRecord;
import com.emr.project.sys.domain.vo.JobRecordVo;
import com.emr.project.sys.mapper.JobRecordMapper;
import com.emr.project.sys.service.IJobRecordService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobRecordServiceImpl implements IJobRecordService {
   @Autowired
   private JobRecordMapper jobRecordMapper;

   public JobRecord selectJobRecordById(Long id) {
      return this.jobRecordMapper.selectJobRecordById(id);
   }

   public List selectJobRecordList(JobRecord jobRecord) {
      return this.jobRecordMapper.selectJobRecordList(jobRecord);
   }

   public List selectJobRecordList(JobRecordVo jobRecordVo) {
      return this.jobRecordMapper.selectJobRecordListByVo(jobRecordVo);
   }

   public int insertJobRecord(JobRecord jobRecord) {
      jobRecord.setId(SnowIdUtils.uniqueLong());
      return this.jobRecordMapper.insertJobRecord(jobRecord);
   }

   public int updateJobRecord(JobRecord jobRecord) {
      return this.jobRecordMapper.updateJobRecord(jobRecord);
   }

   public int deleteJobRecordByIds(Long[] ids) {
      return this.jobRecordMapper.deleteJobRecordByIds(ids);
   }

   public int deleteJobRecordById(Long id) {
      return this.jobRecordMapper.deleteJobRecordById(id);
   }
}
