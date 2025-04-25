package com.emr.project.system.mapper;

import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.vo.BasEmployeeSearchVo;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface BasEmployeeMapper {
   BasEmployeeVo selectBasEmployeeById(@Param("id") Long id);

   List selectBasEmployeeList(BasEmployee basEmployee);

   BasEmployee selectBasEmployee(BasEmployee basEmployee);

   int insertBasEmployee(BasEmployeeVo basEmployeeVo);

   int updateBasEmployee(BasEmployeeVo basEmployeeVo);

   void updateDelFlagByIds(Long[] ids);

   int deleteBasEmployeeById(String id);

   int deleteBasEmployeeByIds(String[] ids);

   void insertMap(Map map);

   List searchList(BasEmployeeSearchVo basEmployee);

   List searchListAll(BasEmployeeSearchVo basEmployee);

   List selectAuthorizeList(BasEmployeeVo basEmployeeVo);

   List selectRoleEmployeeList();

   List selectDeptBasEmployeeList(String deptCode);

   void batchUserRoleDept(List list);

   List selectUserLiseByRoleType(BasEmployeeVo basEmployeeVo);

   List selectBasEmployeeAllList();
}
