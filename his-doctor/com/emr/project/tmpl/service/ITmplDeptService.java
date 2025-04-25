package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.TmplDept;
import java.util.List;

public interface ITmplDeptService {
   TmplDept selectTmplDeptById(Long id);

   List selectTmplDeptList(TmplDept tmplDept);

   int insertTmplDept(TmplDept tmplDept);

   int updateTmplDept(TmplDept tmplDept);

   int deleteTmplDeptByIds(Long[] ids);

   int deleteTmplDeptById(Long id);

   int deleteTmplDeptByTempId(Long tempId);

   int insertTmplDeptList(List tmplDeptList);

   List getTempIdsByDepts(Long medicineId);
}
