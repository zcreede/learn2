package com.emr.project.monitor.mapper;

import com.emr.project.monitor.domain.SysLogininfor;
import java.util.List;

public interface SysLogininforMapper {
   void insertLogininfor(SysLogininfor logininfor);

   List selectLogininforList(SysLogininfor logininfor);

   int deleteLogininforByIds(Long[] infoIds);

   int cleanLogininfor();
}
