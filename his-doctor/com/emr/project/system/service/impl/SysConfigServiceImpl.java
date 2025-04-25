package com.emr.project.system.service.impl;

import com.emr.common.core.text.Convert;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.project.system.domain.SysConfig;
import com.emr.project.system.mapper.SysConfigMapper;
import com.emr.project.system.service.ISysConfigService;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl implements ISysConfigService {
   @Autowired
   private SysConfigMapper configMapper;
   @Autowired
   private RedisCache redisCache;

   @PostConstruct
   public void init() {
      this.loadingConfigCache();
   }

   public SysConfig selectConfigById(Long configId) {
      SysConfig config = new SysConfig();
      config.setConfigId(configId);
      return this.configMapper.selectConfig(config);
   }

   public String selectConfigByKey(String configKey) {
      String configValue = Convert.toStr(this.redisCache.getCacheObject(this.getCacheKey(configKey)));
      if (StringUtils.isNotEmpty(configValue)) {
         return configValue;
      } else {
         SysConfig config = new SysConfig();
         config.setConfigKey(configKey);
         SysConfig retConfig = this.configMapper.selectConfig(config);
         if (StringUtils.isNotNull(retConfig)) {
            this.redisCache.setCacheObject(this.getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
         } else {
            return "";
         }
      }
   }

   public List selectConfigList(SysConfig config) {
      return this.configMapper.selectConfigList(config);
   }

   public int insertConfig(SysConfig config) {
      int row = this.configMapper.insertConfig(config);
      if (row > 0) {
         this.redisCache.setCacheObject(this.getCacheKey(config.getConfigKey()), config.getConfigValue());
      }

      return row;
   }

   public int updateConfig(SysConfig config) {
      int row = this.configMapper.updateConfig(config);
      if (row > 0) {
         this.redisCache.setCacheObject(this.getCacheKey(config.getConfigKey()), config.getConfigValue());
      }

      return row;
   }

   public void deleteConfigByIds(Long[] configIds) {
      for(Long configId : configIds) {
         SysConfig config = this.selectConfigById(configId);
         if (StringUtils.equals("Y", config.getConfigType())) {
            throw new CustomException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
         }

         this.configMapper.deleteConfigById(configId);
         this.redisCache.deleteObject(this.getCacheKey(config.getConfigKey()));
      }

   }

   public void loadingConfigCache() {
      for(SysConfig config : this.configMapper.selectConfigList(new SysConfig())) {
         this.redisCache.setCacheObject(this.getCacheKey(config.getConfigKey()), config.getConfigValue());
      }

   }

   public void clearConfigCache() {
      Collection<String> keys = this.redisCache.keys("sys_config:*");
      this.redisCache.deleteObject(keys);
   }

   public void resetConfigCache() {
      this.clearConfigCache();
      this.loadingConfigCache();
   }

   public String checkConfigKeyUnique(SysConfig config) {
      Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
      SysConfig info = this.configMapper.checkConfigKeyUnique(config.getConfigKey());
      return StringUtils.isNotNull(info) && info.getConfigId() != configId ? "1" : "0";
   }

   private String getCacheKey(String configKey) {
      return "sys_config:" + configKey;
   }
}
