package com.emr.project.system.mapper;

import java.util.List;

public interface SysRoleMenuMapper {
   int checkMenuExistRole(Long menuId);

   int deleteRoleMenuByRoleId(Long roleId);

   int deleteRoleMenu(Long[] ids);

   int batchRoleMenu(List roleMenuList);
}
