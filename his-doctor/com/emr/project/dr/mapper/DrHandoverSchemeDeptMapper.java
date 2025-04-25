package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.DrHandoverSchemeDept;
import java.util.List;

public interface DrHandoverSchemeDeptMapper {
   DrHandoverSchemeDept selectDrHandoverSchemeDeptById(Long id);

   List selectDrHandoverSchemeDeptList(DrHandoverSchemeDept drHandoverSchemeDept);

   int insertDrHandoverSchemeDept(DrHandoverSchemeDept drHandoverSchemeDept);

   void insertList(List list);

   int updateDrHandoverSchemeDept(DrHandoverSchemeDept drHandoverSchemeDept);

   int deleteDrHandoverSchemeDeptById(Long id);

   int deleteDrHandoverSchemeDeptByIds(Long[] ids);

   void deleteBySchemeCdList(List schemeCdList);

   List selectBySchemeCdList(List schemeCdList);

   List selectByDeptCdList(List deptCdList);
}
