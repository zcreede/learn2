package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.vo.SysDeptVo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface SysDeptMapper {
   List selectDeptList(SysDept dept);

   List selectDeptZyList(SysDept dept);

   List selectOrgList();

   List selectDeptPageList(SysDept dept);

   List selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

   SysDept selectDeptById(Long deptId);

   SysDept selectDeptByDeptCode(String deptCode, Long deptId);

   SysDept selectDeptByDeptName(String deptName, Long deptId);

   List selectChildrenDeptById(Long deptId);

   int selectNormalChildrenDeptById(Long deptId);

   int hasChildByDeptId(Long deptId);

   int checkDeptExistUser(Long deptId);

   SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

   SysDept checkDeptCodeUnique(@Param("deptCode") String deptCode);

   int insertDept(SysDept dept);

   int updateDept(SysDept dept);

   void updateDeptStatusNormal(Long[] deptIds);

   int updateDeptChildren(@Param("depts") List depts);

   int deleteDeptById(Long deptId);

   List selectByDeptIds(List list);

   List selectByDeptIdsAndTypes(@Param("list") List list);

   List selectAll();

   List selectDeptByEmployeeId(String employeeId);

   List selectDeptByUserId(Long userId);

   void insertMap(Map map);

   List selectSpecialTypeList(String sysFlag);

   List selectDocDeptList(String emplNumber);

   List selectTmplDeptOrderList(SysDeptVo sysDeptVo);

   List selectListByDeptCodeList(@Param("hospitalCode") String hospitalCode, List deptCodeList);

   void deleteDept();

   void deleteDeptByDeptCode(String deptCode);

   List selectAllDeptList();

   List selectdeptListByTypeCode(@Param("typeCode") String typeCode);

   List selectListByDepCodeList(@Param("list") List list);

   SysDept getOneByCode(@Param("depCode") String depCode);

   List selectSysDeptSiteByCodeList(@Param("list") Set deptCodeSet);
}
