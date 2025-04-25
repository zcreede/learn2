package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.TmplDept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmplDeptMapper {
   TmplDept selectTmplDeptById(Long id);

   List selectTmplDeptList(TmplDept tmplDept);

   int insertTmplDept(TmplDept tmplDept);

   int updateTmplDept(TmplDept tmplDept);

   int deleteTmplDeptById(Long id);

   int deleteTmplDeptByIds(Long[] ids);

   int insertTmplDeptList(List tmplDeptList);

   int deleteTmplDeptByTempId(Long tempId);

   List getTempIdsByDepts(@Param("medicineId") Long medicineId);
}
