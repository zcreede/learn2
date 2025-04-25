package com.emr.project.system.service;

import com.emr.project.system.domain.SysConfig;
import java.util.List;

public interface ISysConfigService {
   SysConfig selectConfigById(Long configId);

   String selectConfigByKey(String configKey);

   List selectConfigList(SysConfig config);

   int insertConfig(SysConfig config);

   int updateConfig(SysConfig config);

   void deleteConfigByIds(Long[] configIds);

   void loadingConfigCache();

   void clearConfigCache();

   void resetConfigCache();

   String checkConfigKeyUnique(SysConfig config);
}
