package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysUserDept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserDeptMapper {
   List selectSysUserDeptById(Long userId);

   List selectSysUserDeptList(SysUserDept sysUserDept);

   int insertSysUserDept(SysUserDept sysUserDept);

   int updateSysUserDept(SysUserDept sysUserDept);

   int deleteSysUserDeptById(Long userId);

   int deleteSysUserDeptByIds(Long[] userIds);

   void batchUserDept(List list);

   void deleteUserDeptByStaffCode(String staffCode);

   void addUseCount(@Param("deptCode") String deptCode, @Param("staffCodes") String[] staffCodes);

   void reduceUseCount(@Param("deptCode") String deptCode, @Param("staffCodes") String[] staffCodes);
}
