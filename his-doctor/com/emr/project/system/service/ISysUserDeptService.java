package com.emr.project.system.service;

import com.emr.project.system.domain.SysUserDept;
import java.util.List;

public interface ISysUserDeptService {
   List selectSysUserDeptById(Long userId);

   List selectSysUserDeptList(SysUserDept sysUserDept);

   int insertSysUserDept(SysUserDept sysUserDept);

   int updateSysUserDept(SysUserDept sysUserDept);

   int deleteSysUserDeptByIds(Long[] userIds);

   int deleteSysUserDeptById(Long userId);

   List selectDeptListByUserId(Long userId);
}
