package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysConfig;
import java.util.List;

public interface SysConfigMapper {
   SysConfig selectConfig(SysConfig config);

   List selectConfigList(SysConfig config);

   SysConfig checkConfigKeyUnique(String configKey);

   int insertConfig(SysConfig config);

   int updateConfig(SysConfig config);

   int deleteConfigById(Long configId);

   int deleteConfigByIds(Long[] configIds);
}
