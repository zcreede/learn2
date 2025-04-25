package com.emr.project.system.service;

import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.vo.SysDeptVo;
import java.util.List;
import java.util.Map;

public interface ISysDeptService {
   List selectDeptList(SysDept dept);

   List selectDeptPageList(SysDept dept);

   List queryUserDeptLeves(Long userId) throws Exception;

   List filterDeptByModule(List selectHDIndexVoList, List moduleList) throws Exception;

   List buildDeptTree(List depts);

   List buildDeptTreeSelect(List depts);

   List selectDeptListByRoleId(Long roleId);

   SysDept selectDeptById(Long deptId);

   int selectNormalChildrenDeptById(Long deptId);

   boolean hasChildByDeptId(Long deptId);

   boolean checkDeptExistUser(Long deptId);

   String checkDeptNameUnique(SysDept dept);

   String checkDeptCodeUnique(SysDept dept);

   int insertDept(SysDept dept);

   int updateDept(SysDept dept);

   int deleteDeptById(Long deptId);

   List selectDeptByEmployeeId(String employeeId);

   List selectDeptByUserId(Long employeeId);

   List selectSpecialTypeList(String sysFlag) throws Exception;

   List selectTmplDeptOrderList(SysDeptVo sysDeptVo) throws Exception;

   SysDept selectDeptByDeptCode(String deptCode, Long deptId) throws Exception;

   SysDept selectDeptByDeptName(String deptName, Long deptId) throws Exception;

   List selectOrgDeptList(SysDept sysDept) throws Exception;

   List selectDocDeptList(String emplNumber) throws Exception;

   List selectListByDeptCodeList(String hospitalCode, List deptCodeList) throws Exception;

   List orgHosDeptList(String patientId, String deptName);

   Map getDeptMaps();

   List selectdeptListByTypeCode(String typeCode) throws Exception;

   List queryByDepCodeList(List depCode);

   SysDept getOneByCode(String depCode);

   List selectDeptZyList(SysDept dept) throws Exception;
}
