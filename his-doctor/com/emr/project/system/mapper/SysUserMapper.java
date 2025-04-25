package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysUser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
   List selectUserList(SysUser sysUser);

   List selectUserListByDeptId(SysUser sysUser);

   List selectAllocatedList(SysUser user);

   List selectUnallocatedList(SysUser user);

   SysUser selectUserByUserName(String userName);

   SysUser selectUserById(Long userId);

   int insertUser(SysUser user);

   int updateUser(SysUser user);

   int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

   int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

   int deleteUserById(Long userId);

   int deleteUserByIds(Long[] userIds);

   int checkUserNameUnique(String userName);

   SysUser checkPhoneUnique(String phonenumber);

   SysUser checkEmailUnique(String email);

   List selectUserLiseByRoleType(@Param("roleType") String roleType, @Param("deptId") String deptId, @Param("status") String status);

   List doctorsAndTechnician(@Param("staffTypes") String[] staffTypes, @Param("deptId") String deptId, @Param("staffName") String staffName, @Param("status") String status);

   List selectUserLiseByRoleNo(@Param("roleNo") String roleNo);

   List getStaffTypeBmy(SysUser sysUser);

   List selectUserLiseByRoleTypeAndName(@Param("roleType") String roleType, @Param("deptId") String deptId, @Param("staffName") String staffName, @Param("status") String status);

   void insertMap(Map map);

   List selectUserAuthorList(SysUser sysUser);

   List selctStaffList();

   void deleteUserByUserName(String userName);

   SysUser selectUserByCertSn(@Param("certSn") String certSn);
}
