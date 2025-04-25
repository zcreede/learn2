package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.vo.SysEmrTypeConfigVo;
import com.emr.project.emr.mapper.SysEmrTypeConfigMapper;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysEmrTypeConfigServiceImpl implements ISysEmrTypeConfigService {
   @Autowired
   private SysEmrTypeConfigMapper sysEmrTypeConfigMapper;

   public SysEmrTypeConfig selectSysEmrTypeConfigById(Long id) {
      return this.sysEmrTypeConfigMapper.selectSysEmrTypeConfigById(id);
   }

   public List selectSysEmrTypeConfigList(SysEmrTypeConfig sysEmrTypeConfig) {
      List<SysEmrTypeConfigVo> configVos = this.sysEmrTypeConfigMapper.selectSysEmrTypeConfigList(sysEmrTypeConfig);
      if (configVos != null && configVos.size() > 0) {
         for(int i = 0; i < configVos.size(); ++i) {
            SysEmrTypeConfigVo sysEmrTypeConfigVo = (SysEmrTypeConfigVo)configVos.get(i);
            if (StringUtils.isBlank(sysEmrTypeConfigVo.getReviewedLevel())) {
               sysEmrTypeConfigVo.setReviewedLevel("1");
               configVos.set(i, sysEmrTypeConfigVo);
            }
         }
      }

      return configVos;
   }

   public List selectListByNameAndClass(String emrClassCode, String emrTypeName) {
      SysEmrTypeConfigVo param = new SysEmrTypeConfigVo();
      param.setEmrClassCode(emrClassCode);
      param.setEmrTypeName(emrTypeName);
      return this.sysEmrTypeConfigMapper.selectListByNameAndClass(param);
   }

   public int insertSysEmrTypeConfig(SysEmrTypeConfig sysEmrTypeConfig) {
      if (StringUtils.isBlank(sysEmrTypeConfig.getReviewedLevel())) {
         sysEmrTypeConfig.setReviewedLevel("1");
      }

      return this.sysEmrTypeConfigMapper.insertSysEmrTypeConfig(sysEmrTypeConfig);
   }

   public int updateSysEmrTypeConfig(SysEmrTypeConfig sysEmrTypeConfig) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      if (StringUtils.isBlank(sysEmrTypeConfig.getReviewedLevel())) {
         sysEmrTypeConfig.setReviewedLevel("1");
      }

      if (sysEmrTypeConfig.getId() != null) {
         sysEmrTypeConfig.setAltPerCode(user.getUserName());
         sysEmrTypeConfig.setAltPerName(user.getNickName());
         return this.sysEmrTypeConfigMapper.updateSysEmrTypeConfig(sysEmrTypeConfig);
      } else {
         sysEmrTypeConfig.setId(SnowIdUtils.uniqueLong());
         sysEmrTypeConfig.setCrePerCode(user.getUserName());
         sysEmrTypeConfig.setCrePerName(user.getNickName());
         sysEmrTypeConfig.setValidFlag("1");
         return this.sysEmrTypeConfigMapper.insertSysEmrTypeConfig(sysEmrTypeConfig);
      }
   }

   public int deleteSysEmrTypeConfigByIds(Long[] ids) {
      return this.sysEmrTypeConfigMapper.deleteSysEmrTypeConfigByIds(ids);
   }

   public int deleteSysEmrTypeConfigById(Long id) {
      return this.sysEmrTypeConfigMapper.deleteSysEmrTypeConfigById(id);
   }

   public SysEmrTypeConfigVo selectSysEmrTypeConfigByTypeId(String emrTypeCode) {
      return this.sysEmrTypeConfigMapper.selectSysEmrTypeConfigByTypeId(emrTypeCode);
   }
}
