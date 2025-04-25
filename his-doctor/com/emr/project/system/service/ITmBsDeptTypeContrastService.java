package com.emr.project.system.service;

import com.emr.project.system.domain.TmBsDeptTypeContrast;
import com.emr.project.system.domain.vo.TmBsDeptTypeContrastVo;
import java.util.List;

public interface ITmBsDeptTypeContrastService {
   List selectTmBsDeptTypeContrastById(String deptCode);

   List selectTmBsDeptTypeContrastList(TmBsDeptTypeContrast tmBsDeptTypeContrast);

   List selectTmBsDeptTypeContrastVoList(TmBsDeptTypeContrastVo tmBsDeptTypeContrastVo);

   int insertTmBsDeptTypeContrast(TmBsDeptTypeContrast tmBsDeptTypeContrast);

   int updateTmBsDeptTypeContrast(TmBsDeptTypeContrast tmBsDeptTypeContrast);

   int deleteTmBsDeptTypeContrastByIds(String[] deptCodes);

   int deleteTmBsDeptTypeContrastById(String deptCode);
}
