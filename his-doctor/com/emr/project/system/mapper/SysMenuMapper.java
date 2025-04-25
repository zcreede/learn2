package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysMenu;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
   List selectMenuList(SysMenu menu);

   List selectMenuListByUserId(SysMenu menu);

   List selectMenuPermsByUserId(String userId, String deptId);

   List selectMenuTreeAll();

   List selectMenuTreeByUserId(String userId);

   List selectMenuListByRoleId(@Param("roleId") Long roleId, @Param("menuCheckStrictly") boolean menuCheckStrictly);

   SysMenu selectMenuById(Long menuId);

   int hasChildByMenuId(Long menuId);

   int insertMenu(SysMenu menu);

   int updateMenu(SysMenu menu);

   int deleteMenuById(Long menuId);

   SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}
