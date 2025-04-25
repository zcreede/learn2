package com.emr.project.system.service;

import com.emr.project.system.domain.SysEmployeeRoleDept;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import java.util.List;

public interface ISysEmployeeRoleDeptService {
   List selectByUserId(Long userId);

   List selectDeptsByUserId(Long userId);

   void delByUserId(Long userId) throws Exception;

   List selectDeptNameGroupByUser() throws Exception;

   List selectRoleDeptList(SysEmployeeRoleDept sysEmployeeRoleDept) throws Exception;

   void insert(SysEmployeeRoleDept sysEmployeeRoleDept) throws Exception;

   List selectDeptNameGroupByUserVO(BasEmployeeVo vo);
}
