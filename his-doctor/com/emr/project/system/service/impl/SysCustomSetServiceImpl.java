package com.emr.project.system.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.security.LoginUser;
import com.emr.project.system.domain.SysCustomSet;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.SysCustomSetMapper;
import com.emr.project.system.service.ISysCustomSetService;
import com.emr.project.system.service.ISysCustomUnitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysCustomSetServiceImpl implements ISysCustomSetService {
   @Autowired
   private SysCustomSetMapper sysCustomSetMapper;
   @Autowired
   private ISysCustomUnitService customUnitService;

   public SysCustomSet selectSysCustomSetById(Long id) {
      return this.sysCustomSetMapper.selectSysCustomSetById(id);
   }

   public List selectSysCustomSetList(SysCustomSet sysCustomSet) {
      return this.sysCustomSetMapper.selectSysCustomSetList(sysCustomSet);
   }

   public List selectSysCustomSetListBySetName(String setName) {
      return this.sysCustomSetMapper.selectSysCustomSetListBySetName(setName);
   }

   public List selectSysCustomSetByTypeName(SysCustomSet sysCustomSet) {
      return this.sysCustomSetMapper.selectSysCustomSetByTypeName(sysCustomSet);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertSysCustomSet(SysCustomSet sysCustomSet) throws Exception {
      Long id = SnowIdUtils.uniqueLong();
      sysCustomSet.setId(id);
      sysCustomSet.setCrePerCode(SecurityUtils.getUsername());
      sysCustomSet.setCrePerName(SecurityUtils.getLoginUser().getUser().getNickName());
      this.sysCustomSetMapper.insertSysCustomSet(sysCustomSet);
      this.customUnitService.addSysCustomUnit(sysCustomSet);
   }

   public int updateSysCustomSet(SysCustomSet sysCustomSet) {
      LoginUser loginUser = SecurityUtils.getLoginUser();
      SysUser user = loginUser.getUser();
      sysCustomSet.setAltPerCode(user.getUserName());
      sysCustomSet.setAltPerName(user.getNickName());
      return this.sysCustomSetMapper.updateSysCustomSet(sysCustomSet);
   }

   public int deleteSysCustomSetByIds(Long[] ids) {
      return this.sysCustomSetMapper.deleteSysCustomSetByIds(ids);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int deleteSysCustomSetById(Long id) throws Exception {
      this.customUnitService.deleteSysCustomUnitBySetId(id);
      return this.sysCustomSetMapper.deleteSysCustomSetById(id);
   }
}
