package com.emr.project.monitor.mapper;

import com.emr.project.monitor.domain.SysJob;
import java.util.List;

public interface SysJobMapper {
   List selectJobList(SysJob job);

   List selectJobAll();

   SysJob selectJobById(Long jobId);

   int deleteJobById(Long jobId);

   int deleteJobByIds(Long[] ids);

   int updateJob(SysJob job);

   int insertJob(SysJob job);
}
