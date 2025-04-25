package com.emr.project.system.service.impl;

import com.emr.project.system.domain.SysUserDept;
import com.emr.project.system.mapper.SysUserDeptMapper;
import com.emr.project.system.service.ISysUserDeptService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserDeptServiceImpl implements ISysUserDeptService {
   @Autowired
   private SysUserDeptMapper sysUserDeptMapper;

   public List selectSysUserDeptById(Long userId) {
      return this.sysUserDeptMapper.selectSysUserDeptById(userId);
   }

   public List selectSysUserDeptList(SysUserDept sysUserDept) {
      return this.sysUserDeptMapper.selectSysUserDeptList(sysUserDept);
   }

   public int insertSysUserDept(SysUserDept sysUserDept) {
      return this.sysUserDeptMapper.insertSysUserDept(sysUserDept);
   }

   public int updateSysUserDept(SysUserDept sysUserDept) {
      return this.sysUserDeptMapper.updateSysUserDept(sysUserDept);
   }

   public int deleteSysUserDeptByIds(Long[] userIds) {
      return this.sysUserDeptMapper.deleteSysUserDeptByIds(userIds);
   }

   public int deleteSysUserDeptById(Long userId) {
      return this.sysUserDeptMapper.deleteSysUserDeptById(userId);
   }

   public List selectDeptListByUserId(Long userId) {
      this.selectSysUserDeptById(userId);
      List<Long> deptList = new ArrayList();
      return deptList;
   }
}
