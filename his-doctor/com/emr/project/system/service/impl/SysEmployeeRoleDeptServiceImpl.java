package com.emr.project.system.service.impl;

import com.emr.project.system.domain.SysEmployeeRoleDept;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import com.emr.project.system.mapper.SysEmployeeRoleDeptMapper;
import com.emr.project.system.service.ISysEmployeeRoleDeptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysEmployeeRoleDeptServiceImpl implements ISysEmployeeRoleDeptService {
   @Autowired
   private SysEmployeeRoleDeptMapper sysEmployeeRoleDeptMapper;

   public List selectByUserId(Long userId) {
      return this.sysEmployeeRoleDeptMapper.selectCountRoleDeptByUserId(userId);
   }

   public List selectDeptsByUserId(Long userId) {
      return this.sysEmployeeRoleDeptMapper.selectDeptsByUserId(userId);
   }

   public void delByUserId(Long userId) throws Exception {
      this.sysEmployeeRoleDeptMapper.deleteByUserId(userId);
   }

   public List selectDeptNameGroupByUser() throws Exception {
      return this.sysEmployeeRoleDeptMapper.selectDeptNameGroupByUser();
   }

   public List selectRoleDeptList(SysEmployeeRoleDept sysEmployeeRoleDept) throws Exception {
      return this.sysEmployeeRoleDeptMapper.selectRoleDeptList(sysEmployeeRoleDept);
   }

   public void insert(SysEmployeeRoleDept sysEmployeeRoleDept) throws Exception {
      this.sysEmployeeRoleDeptMapper.insertSysUserRoleDept(sysEmployeeRoleDept);
   }

   public List selectDeptNameGroupByUserVO(BasEmployeeVo vo) {
      return this.sysEmployeeRoleDeptMapper.selectDeptNameGroupByUserVO(vo);
   }
}
