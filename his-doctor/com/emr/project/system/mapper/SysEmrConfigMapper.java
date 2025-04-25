package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysEmrConfig;
import java.util.List;

public interface SysEmrConfigMapper {
   SysEmrConfig selectSysEmrConfigById(Long id);

   List selectSysEmrConfigList(SysEmrConfig sysEmrConfig);

   SysEmrConfig selectSysEmrConfig(SysEmrConfig sysEmrConfig);

   int insertSysEmrConfig(SysEmrConfig sysEmrConfig);

   int updateSysEmrConfig(SysEmrConfig sysEmrConfig);

   int deleteSysEmrConfigById(Long id);

   int deleteSysEmrConfigByIds(Long[] ids);
}
