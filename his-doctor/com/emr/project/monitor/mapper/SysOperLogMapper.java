package com.emr.project.monitor.mapper;

import com.emr.project.monitor.domain.SysOperLog;
import java.util.List;

public interface SysOperLogMapper {
   void insertOperlog(SysOperLog operLog);

   List selectOperLogList(SysOperLog operLog);

   int deleteOperLogByIds(Long[] operIds);

   SysOperLog selectOperLogById(Long operId);

   void cleanOperLog();
}
