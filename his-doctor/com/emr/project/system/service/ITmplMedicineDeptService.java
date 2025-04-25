package com.emr.project.system.service;

import com.emr.project.system.domain.TmplMedicineDept;
import java.util.List;

public interface ITmplMedicineDeptService {
   TmplMedicineDept selectTmplMedicineDeptById(Long id);

   List selectTmplMedicineDeptList(TmplMedicineDept tmplMedicineDept);

   int insertTmplMedicineDept(TmplMedicineDept tmplMedicineDept);

   void insertList(List list) throws Exception;

   int updateTmplMedicineDept(TmplMedicineDept tmplMedicineDept);

   int deleteTmplMedicineDeptByIds(Long[] ids);

   int deleteTmplMedicineDeptById(Long id);

   void deleteTmplMedicineDeptByMedicineId(Long id) throws Exception;
}
