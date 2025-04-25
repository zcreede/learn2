package com.emr.project.monitor.service;

import com.emr.project.monitor.domain.SysLogininfor;
import java.util.List;

public interface ISysLogininforService {
   void insertLogininfor(SysLogininfor logininfor);

   List selectLogininforList(SysLogininfor logininfor);

   int deleteLogininforByIds(Long[] infoIds);

   void cleanLogininfor();
}
