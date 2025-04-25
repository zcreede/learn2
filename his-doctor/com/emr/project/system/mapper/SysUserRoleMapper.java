package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysUserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
   int deleteUserRoleByUserId(Long userId);

   int deleteUserRole(Long[] ids);

   int countUserRoleByRoleId(Long roleId);

   int batchUserRole(List userRoleList);

   int deleteUserRoleInfo(SysUserRole userRole);

   int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

   void deleteStaffRoleByStaffCode(String staffCode);
}
