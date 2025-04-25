package com.emr.project.system.service;

import com.emr.project.system.domain.SysMenu;
import java.util.List;
import java.util.Set;

public interface ISysMenuService {
   List selectMenuList(Long userId);

   List selectMenuList(SysMenu menu, Long userId);

   Set selectMenuPermsByUserId(String userId, String deptId);

   List selectMenuTreeByUserId(Long userId) throws Exception;

   List selectMenuListByRoleId(Long roleId);

   List buildMenus(List menus);

   List buildMenuTree(List menus);

   List buildMenuTreeSelect(List menus);

   SysMenu selectMenuById(Long menuId);

   boolean hasChildByMenuId(Long menuId);

   boolean checkMenuExistRole(Long menuId);

   int insertMenu(SysMenu menu);

   int updateMenu(SysMenu menu);

   int deleteMenuById(Long menuId);

   String checkMenuNameUnique(SysMenu menu);

   List selectMenuTreeAll() throws Exception;
}
