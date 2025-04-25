package com.emr.project.system.service;

import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUserRole;
import java.util.List;
import java.util.Set;

public interface ISysRoleService {
   List selectRoleList(SysRole role);

   List selectRolesByUserId(Long userId);

   Set selectRolePermissionByUserId(Long userId, Long deptId);

   List selectRoleAll();

   List selectRoleListByUserId(Long userId);

   SysRole selectRoleById(Long roleId);

   String checkRoleNameUnique(SysRole role);

   String checkRoleKeyUnique(SysRole role);

   void checkRoleAllowed(SysRole role);

   int countUserRoleByRoleId(Long roleId);

   int insertRole(SysRole role);

   int updateRole(SysRole role);

   int updateRoleStatus(SysRole role);

   int authDataScope(SysRole role);

   int deleteRoleById(Long roleId);

   int deleteRoleByIds(Long[] roleIds);

   int deleteAuthUser(SysUserRole userRole);

   int deleteAuthUsers(Long roleId, Long[] userIds);

   int insertAuthUsers(Long roleId, Long[] userIds);

   List selectRoleListByIds(List roleIdList);
}
