package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysEmployeeRoleDept;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import java.util.List;

public interface SysEmployeeRoleDeptMapper {
   void deleteByUserId(Long userId);

   void insertSysUserRoleDept(SysEmployeeRoleDept sysEmployeeRoleDept);

   List selectCountRoleDeptByUserId(Long userId);

   List selectDeptsByUserId(Long userId);

   List selectDeptNameGroupByUser();

   List selectRoleDeptList(SysEmployeeRoleDept sysEmployeeRoleDept);

   void batchUserRoleDept(List list);

   List selectDeptNameGroupByUserVO(BasEmployeeVo vo);
}
