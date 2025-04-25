package com.emr.project.system.mapper;

import com.emr.project.emr.domain.vo.AcceRoleVo;
import com.emr.project.system.domain.SysRole;
import java.util.List;

public interface SysRoleMapper {
   List selectRoleList(SysRole role);

   List selectRolePermissionByUserId(Long userId, Long deptId);

   List selectRoleAll();

   List selectRoleListByUserId(Long userId);

   SysRole selectRoleById(Long roleId);

   List selectRolesByUserName(String userName);

   SysRole checkRoleNameUnique(String roleName);

   SysRole checkRoleKeyUnique(String roleKey);

   int updateRole(SysRole role);

   int insertRole(SysRole role);

   int deleteRoleById(Long roleId);

   int deleteRoleByIds(Long[] roleIds);

   List selectRoleListByIds(List list);

   List selectRoleAcceList(AcceRoleVo acceRoleVo);
}
