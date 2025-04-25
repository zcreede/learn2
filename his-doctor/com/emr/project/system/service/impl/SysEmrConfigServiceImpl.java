package com.emr.project.system.service.impl;

import com.emr.common.core.text.Convert;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.security.LoginUser;
import com.emr.project.system.domain.SysEmrConfig;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.SysEmrConfigMapper;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysEmrConfigServiceImpl implements ISysEmrConfigService {
   @Autowired
   private SysEmrConfigMapper sysEmrConfigMapper;
   @Autowired
   private RedisCache redisCache;

   @PostConstruct
   public void init() throws Exception {
      this.loadingSysEmrConfigCache();
   }

   public void loadingSysEmrConfigCache() throws Exception {
      List<SysEmrConfig> emrConfigList = this.sysEmrConfigMapper.selectSysEmrConfigList(new SysEmrConfig());
      List<SysEmrConfig> parentList = (List)emrConfigList.stream().filter((t) -> t.getParentId() == null).collect(Collectors.toList());
      List<SysEmrConfig> childrenAll = (List)emrConfigList.stream().filter((t) -> t.getParentId() != null).collect(Collectors.toList());
      Map<Long, List<SysEmrConfig>> childrenMap = (Map)childrenAll.stream().collect(Collectors.groupingBy((t) -> t.getParentId()));

      for(SysEmrConfig emrConfig : parentList) {
         this.redisCache.setCacheObject(this.getCacheKey(emrConfig.getConfigKey(), emrConfig.getOrgCd()), emrConfig.getConfigValue());
         List<SysEmrConfig> children = (List)childrenMap.get(emrConfig.getId());
         if (children != null && !children.isEmpty()) {
            this.redisCache.setCacheList(this.getCacheKeyChildren(emrConfig.getConfigKey()), children);
         }
      }

   }

   public void clearSysEmrConfigCache() throws Exception {
      Collection<String> keys = this.redisCache.keys("sys_emr_config_bak:%s:%s*");
      this.redisCache.deleteObject(keys);
      Collection<String> childrenKeys = this.redisCache.keys("sys_emr_config_children:*");
      this.redisCache.deleteObject(childrenKeys);
   }

   public void resetSysEmrConfigCache() throws Exception {
      this.clearSysEmrConfigCache();
      this.loadingSysEmrConfigCache();
   }

   private String getCacheKey(String configKey, String orgCd) throws Exception {
      return String.format("sys_emr_config_bak:%s:%s", orgCd, configKey);
   }

   private String getCacheKeyChildren(String configKey) throws Exception {
      return "sys_emr_config_children:" + configKey;
   }

   public SysEmrConfig selectSysEmrConfigById(Long id) throws Exception {
      return this.sysEmrConfigMapper.selectSysEmrConfigById(id);
   }

   public String selectSysEmrConfigByKey(String configKey) throws Exception {
      LoginUser loginUser = SecurityUtils.getLoginUser();
      String orgCode = "0000";
      if (loginUser != null) {
         SysUser user = loginUser.getUser();
         SysOrg hospital = user.getHospital();
         if (hospital != null) {
            orgCode = hospital.getOrgCode();
         }
      }

      String configValue = Convert.toStr(this.redisCache.getCacheObject(this.getCacheKey(configKey, orgCode)));
      if (StringUtils.isEmpty(configValue) && !orgCode.equals("0000")) {
         configValue = Convert.toStr(this.redisCache.getCacheObject(this.getCacheKey(configKey, "0000")));
      }

      if (StringUtils.isEmpty(configValue) || StringUtils.isNotEmpty(configValue) && StringUtils.isBlank(configKey)) {
         SysEmrConfig param = new SysEmrConfig(configKey, orgCode);
         SysEmrConfig emrConfig = this.sysEmrConfigMapper.selectSysEmrConfig(param);
         if (emrConfig == null && !orgCode.equals("0000")) {
            emrConfig = this.sysEmrConfigMapper.selectSysEmrConfig(new SysEmrConfig(configKey, "0000"));
         }

         if (emrConfig != null) {
            configValue = emrConfig.getConfigValue();
         }
      }

      return configValue;
   }

   public SysEmrConfig selectConfigValueByKey(String configKey) throws Exception {
      SysEmrConfig param = new SysEmrConfig(configKey);
      SysEmrConfig emrConfig = this.sysEmrConfigMapper.selectSysEmrConfig(param);
      return emrConfig;
   }

   public List selectListByParentKey(String configKey) throws Exception {
      List<SysEmrConfig> resList = this.redisCache.getCacheList(this.getCacheKeyChildren(configKey));
      if (resList == null || resList != null && resList.isEmpty()) {
         SysEmrConfig param = new SysEmrConfig(configKey);
         SysEmrConfig paramSec = this.sysEmrConfigMapper.selectSysEmrConfig(param);
         param = new SysEmrConfig(paramSec.getId());
         resList = this.sysEmrConfigMapper.selectSysEmrConfigList(param);
      }

      return resList;
   }

   public List selectSysEmrConfigList(SysEmrConfig sysEmrConfig) throws Exception {
      return this.sysEmrConfigMapper.selectSysEmrConfigList(sysEmrConfig);
   }

   public int insertSysEmrConfig(SysEmrConfig sysEmrConfig) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      sysEmrConfig.setId(SnowIdUtils.uniqueLong());
      sysEmrConfig.setCreateBy(user.getUserName());
      sysEmrConfig.setCreateTime(DateUtils.getNowDate());
      int count = this.sysEmrConfigMapper.insertSysEmrConfig(sysEmrConfig);
      this.resetSysEmrConfigCache();
      return count;
   }

   public int updateSysEmrConfig(SysEmrConfig sysEmrConfig) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      sysEmrConfig.setUpdateBy(user.getUserName());
      sysEmrConfig.setUpdateTime(DateUtils.getNowDate());
      int count = this.sysEmrConfigMapper.updateSysEmrConfig(sysEmrConfig);
      this.resetSysEmrConfigCache();
      return count;
   }

   public int deleteSysEmrConfigByIds(Long[] ids) throws Exception {
      int count = this.sysEmrConfigMapper.deleteSysEmrConfigByIds(ids);
      this.resetSysEmrConfigCache();
      return count;
   }

   public int deleteSysEmrConfigById(Long id) throws Exception {
      int count = this.sysEmrConfigMapper.deleteSysEmrConfigById(id);
      this.resetSysEmrConfigCache();
      return count;
   }
}
