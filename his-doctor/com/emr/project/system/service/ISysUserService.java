package com.emr.project.system.service;

import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import java.util.List;

public interface ISysUserService {
   List selectUserList(SysUser user);

   List selectUserListByDeptId(SysUser user) throws Exception;

   List selectAllocatedList(SysUser user);

   List selectUnallocatedList(SysUser user);

   SysUser selectUserByUserName(String userName);

   SysUser selectUserById(Long userId);

   String selectUserRoleGroup(String userName);

   String selectUserPostGroup(String userName);

   String checkUserNameUnique(String userName);

   String checkPhoneUnique(SysUser user);

   String checkEmailUnique(SysUser user);

   void checkUserAllowed(SysUser user);

   int insertUser(SysUser user) throws Exception;

   int updateUser(SysUser user) throws Exception;

   void insertUserAuth(Long userId, Long[] roleIds);

   int updateUserStatus(SysUser user);

   int updateUserProfile(SysUser user);

   boolean updateUserAvatar(String userName, String avatar);

   int resetPwd(SysUser user);

   int resetUserPwd(String userName, String password);

   int deleteUserById(Long userId);

   int deleteUserByIds(Long[] userIds);

   String importUser(List userList, Boolean isUpdateSupport, String operName);

   List selectDoctors(String deptId, String status);

   List doctorsAndTechnician(String deptId, String staffName, String status);

   List selectUserLiseByRoleNo();

   List getStaffTypeBmy(SysUser sysUser);

   List selectDoctorsByName(String deptId, String staffName, String status);

   List selectNurses(String deptId, String status);

   BsStaff queryByStaffNo(String staffNo) throws Exception;

   void insertUserPost(BasEmployeeVo basEmployeeVo) throws Exception;

   SysUser selectUserByCertSn(String certSn) throws Exception;

   void addUseCount(String deptCode, String[] staffCodes);

   void reduceUseCount(String deptCode, String[] staffCodes);
}
