package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.file.FileUtils;
import com.emr.framework.config.EMRConfig;
import com.emr.project.emr.domain.WardRounRecfile;
import com.emr.project.emr.mapper.WardRounRecfileMapper;
import com.emr.project.emr.service.IWardRounRecfileService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardRounRecfileServiceImpl implements IWardRounRecfileService {
   @Autowired
   private WardRounRecfileMapper wardRounRecfileMapper;

   public WardRounRecfile selectWardRounRecfileById(Long id) {
      return this.wardRounRecfileMapper.selectWardRounRecfileById(id);
   }

   public List selectWardRounRecfileList(WardRounRecfile wardRounRecfile) throws Exception {
      return this.wardRounRecfileMapper.selectWardRounRecfileList(wardRounRecfile);
   }

   public int insertWardRounRecfile(WardRounRecfile wardRounRecfile) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      wardRounRecfile.setId(SnowIdUtils.uniqueLong());
      wardRounRecfile.setCrePerCode(user.getUserName());
      wardRounRecfile.setCrePerName(user.getNickName());
      return this.wardRounRecfileMapper.insertWardRounRecfile(wardRounRecfile);
   }

   public int updateWardRounRecfile(WardRounRecfile wardRounRecfile) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      wardRounRecfile.setAltPerCode(user.getUserName());
      wardRounRecfile.setAltPerName(user.getNickName());
      return this.wardRounRecfileMapper.updateWardRounRecfile(wardRounRecfile);
   }

   public int deleteWardRounRecfileByIds(Long[] ids) throws Exception {
      return this.wardRounRecfileMapper.deleteWardRounRecfileByIds(ids);
   }

   public int deleteWardRounRecfileById(Long id) throws Exception {
      WardRounRecfile wardRounRecfile = this.selectWardRounRecfileById(id);
      String fileName = wardRounRecfile.getFilePath().replace("/profile", "");
      FileUtils.deleteFile(EMRConfig.getProfile() + fileName);
      return this.wardRounRecfileMapper.deleteWardRounRecfileById(id);
   }
}
