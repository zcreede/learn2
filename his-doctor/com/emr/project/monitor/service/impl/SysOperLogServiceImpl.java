package com.emr.project.monitor.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.project.monitor.domain.SysOperLog;
import com.emr.project.monitor.mapper.SysOperLogMapper;
import com.emr.project.monitor.service.ISysOperLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOperLogServiceImpl implements ISysOperLogService {
   @Autowired
   private SysOperLogMapper operLogMapper;

   public void insertOperlog(SysOperLog operLog) {
      operLog.setOperId(SnowIdUtils.uniqueLong());
      this.operLogMapper.insertOperlog(operLog);
   }

   public List selectOperLogList(SysOperLog operLog) {
      return this.operLogMapper.selectOperLogList(operLog);
   }

   public int deleteOperLogByIds(Long[] operIds) {
      return this.operLogMapper.deleteOperLogByIds(operIds);
   }

   public SysOperLog selectOperLogById(Long operId) {
      return this.operLogMapper.selectOperLogById(operId);
   }

   public void cleanOperLog() {
      this.operLogMapper.cleanOperLog();
   }
}
