package com.emr.project.dr.service;

import com.emr.project.dr.domain.DrHandoverSchemeDept;
import java.util.List;

public interface IDrHandoverSchemeDeptService {
   DrHandoverSchemeDept selectDrHandoverSchemeDeptById(Long id);

   List selectDrHandoverSchemeDeptList(DrHandoverSchemeDept drHandoverSchemeDept);

   int insertDrHandoverSchemeDept(DrHandoverSchemeDept drHandoverSchemeDept);

   int updateDrHandoverSchemeDept(DrHandoverSchemeDept drHandoverSchemeDept);

   int deleteDrHandoverSchemeDeptByIds(Long[] ids);

   int deleteDrHandoverSchemeDeptById(Long id);

   void deleteBySchemeCdList(List schemeCdList) throws Exception;

   List selectBySchemeCdList(List schemeCdList) throws Exception;

   void insertList(List list) throws Exception;

   List selectByDeptCdList(List deptCdList) throws Exception;
}
