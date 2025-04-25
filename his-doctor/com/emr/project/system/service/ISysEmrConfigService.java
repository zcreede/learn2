package com.emr.project.system.service;

import com.emr.project.system.domain.SysEmrConfig;
import java.util.List;

public interface ISysEmrConfigService {
   SysEmrConfig selectSysEmrConfigById(Long id) throws Exception;

   String selectSysEmrConfigByKey(String configKey) throws Exception;

   SysEmrConfig selectConfigValueByKey(String configKey) throws Exception;

   List selectListByParentKey(String configKey) throws Exception;

   List selectSysEmrConfigList(SysEmrConfig sysEmrConfig) throws Exception;

   int insertSysEmrConfig(SysEmrConfig sysEmrConfig) throws Exception;

   int updateSysEmrConfig(SysEmrConfig sysEmrConfig) throws Exception;

   int deleteSysEmrConfigByIds(Long[] ids) throws Exception;

   int deleteSysEmrConfigById(Long id) throws Exception;

   void loadingSysEmrConfigCache() throws Exception;

   void clearSysEmrConfigCache() throws Exception;

   void resetSysEmrConfigCache() throws Exception;
}
