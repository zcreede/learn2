package com.emr.project.system.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysElemStrstore;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.SysElemStrstoreMapper;
import com.emr.project.system.service.ISysElemStrstoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysElemStrstoreServiceImpl implements ISysElemStrstoreService {
   @Autowired
   private SysElemStrstoreMapper sysElemStrstoreMapper;

   public SysElemStrstore selectSysElemStrstoreById(Long id) {
      return this.sysElemStrstoreMapper.selectSysElemStrstoreById(id);
   }

   public List selectSysElemStrstoreList(SysElemStrstore sysElemStrstore) {
      return this.sysElemStrstoreMapper.selectSysElemStrstoreList(sysElemStrstore);
   }

   public int insertSysElemStrstore(SysElemStrstore sysElemStrstore) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      sysElemStrstore.setCreateTime(DateUtils.getNowDate());
      sysElemStrstore.setCrePerCode(user.getUserName());
      sysElemStrstore.setAltPerName(user.getNickName());
      sysElemStrstore.setAltDate(DateUtils.getNowDate());
      sysElemStrstore.setAltPerCode(user.getUserName());
      sysElemStrstore.setAltPerName(user.getNickName());
      sysElemStrstore.setIsValid("1");
      sysElemStrstore.setRequFlag("1");
      sysElemStrstore.setId(SnowIdUtils.uniqueLong());
      return this.sysElemStrstoreMapper.insertSysElemStrstore(sysElemStrstore);
   }

   public int updateSysElemStrstore(SysElemStrstore sysElemStrstore) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      sysElemStrstore.setAltPerCode(basEmployee.getEmplNumber());
      sysElemStrstore.setAltPerName(basEmployee.getEmplName());
      return this.sysElemStrstoreMapper.updateSysElemStrstore(sysElemStrstore);
   }

   public int deleteSysElemStrstoreByIds(Long[] ids) {
      return this.sysElemStrstoreMapper.deleteSysElemStrstoreByIds(ids);
   }

   public int deleteSysElemStrstoreById(Long id) {
      return this.sysElemStrstoreMapper.deleteSysElemStrstoreById(id);
   }
}
