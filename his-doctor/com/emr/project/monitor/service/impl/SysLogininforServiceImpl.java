package com.emr.project.monitor.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.project.monitor.domain.SysLogininfor;
import com.emr.project.monitor.mapper.SysLogininforMapper;
import com.emr.project.monitor.service.ISysLogininforService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogininforServiceImpl implements ISysLogininforService {
   @Autowired
   private SysLogininforMapper logininforMapper;

   public void insertLogininfor(SysLogininfor logininfor) {
      logininfor.setInfoId(SnowIdUtils.uniqueLong());
      this.logininforMapper.insertLogininfor(logininfor);
   }

   public List selectLogininforList(SysLogininfor logininfor) {
      return this.logininforMapper.selectLogininforList(logininfor);
   }

   public int deleteLogininforByIds(Long[] infoIds) {
      return this.logininforMapper.deleteLogininforByIds(infoIds);
   }

   public void cleanLogininfor() {
      this.logininforMapper.cleanLogininfor();
   }
}
