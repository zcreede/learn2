package com.emr.project.system.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.SysShareElem;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SysShareElemVo;
import com.emr.project.system.mapper.SysShareElemMapper;
import com.emr.project.system.service.ISysShareElemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysShareElemServiceImpl implements ISysShareElemService {
   @Autowired
   private SysShareElemMapper sysShareElemMapper;

   public SysShareElem selectSysShareElemById(Long id) {
      return this.sysShareElemMapper.selectSysShareElemById(id);
   }

   public List selectSysShareElemList(SysShareElem sysShareElem) {
      return this.sysShareElemMapper.selectSysShareElemList(sysShareElem);
   }

   public int insertSysShareElem(SysShareElem sysShareElem) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      sysShareElem.setAltDate(DateUtils.getNowDate());
      sysShareElem.setCreateTime(DateUtils.getNowDate());
      sysShareElem.setCreatorid(user.getUserName());
      sysShareElem.setIsValid("1");
      sysShareElem.setId(SnowIdUtils.uniqueLong());
      return this.sysShareElemMapper.insertSysShareElem(sysShareElem);
   }

   public int updateSysShareElem(SysShareElem sysShareElem) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      sysShareElem.setAltDate(DateUtils.getNowDate());
      sysShareElem.setAltPerCode(user.getUserName());
      return this.sysShareElemMapper.updateSysShareElem(sysShareElem);
   }

   public int deleteSysShareElemByIds(Long[] ids) {
      return this.sysShareElemMapper.deleteSysShareElemByIds(ids);
   }

   public int deleteSysShareElemById(Long id) {
      return this.sysShareElemMapper.deleteSysShareElemById(id);
   }

   public List selectSysShareElemByPatientId(SysShareElemVo sysShareElemVo) throws Exception {
      return this.sysShareElemMapper.selectSysShareElemByPatientId(sysShareElemVo);
   }
}
