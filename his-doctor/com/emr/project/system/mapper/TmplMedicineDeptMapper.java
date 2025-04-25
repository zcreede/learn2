package com.emr.project.system.mapper;

import com.emr.project.system.domain.TmplMedicineDept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmplMedicineDeptMapper {
   TmplMedicineDept selectTmplMedicineDeptById(Long id);

   List selectTmplMedicineDeptList(TmplMedicineDept tmplMedicineDept);

   int insertTmplMedicineDept(TmplMedicineDept tmplMedicineDept);

   int updateTmplMedicineDept(TmplMedicineDept tmplMedicineDept);

   int deleteTmplMedicineDeptById(Long id);

   int deleteTmplMedicineDeptByIds(Long[] ids);

   void insertList(List list);

   void deleteTmplMedicineDeptByMedicineId(@Param("medicineId") Long medicineId);
}
