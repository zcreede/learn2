package com.emr.project.system.service;

import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.vo.BasEmployeeSearchVo;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import java.util.List;

public interface IBasEmployeeService {
   BasEmployeeVo selectBasEmployeeById(Long id) throws Exception;

   List selectBasEmployeeList(BasEmployee basEmployee) throws Exception;

   List searchList(BasEmployeeSearchVo basEmployee) throws Exception;

   List searchListAll(BasEmployeeSearchVo basEmployee) throws Exception;

   int insertBasEmployee(BasEmployeeVo basEmployeeVo) throws Exception;

   int updateBasEmployee(BasEmployeeVo basEmployeeVo) throws Exception;

   void deleteBasEmployeeByIds(Long[] ids) throws Exception;

   void deleteBasEmployeeById(Long id) throws Exception;

   BasEmployee selectByEmplNumber(String emplNumber);

   List selectAuthorizeList(BasEmployeeVo basEmployeeVo) throws Exception;

   void batchUserRoleDept(List list) throws Exception;

   List selectDoctors(BasEmployeeVo basEmployeeVo) throws Exception;

   List selectDeptBasEmployeeList(String deptCode) throws Exception;

   List selectBasEmployeeAllList() throws Exception;
}
