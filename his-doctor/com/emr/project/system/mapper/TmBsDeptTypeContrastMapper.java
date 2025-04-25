package com.emr.project.system.mapper;

import com.emr.project.system.domain.TmBsDeptTypeContrast;
import com.emr.project.system.domain.vo.TmBsDeptTypeContrastVo;
import java.util.List;

public interface TmBsDeptTypeContrastMapper {
   List selectTmBsDeptTypeContrastById(String deptCode);

   List selectTmBsDeptTypeContrastList(TmBsDeptTypeContrast tmBsDeptTypeContrast);

   List selectTmBsDeptTypeContrastVoList(TmBsDeptTypeContrastVo tmBsDeptTypeContrastVo);

   int insertTmBsDeptTypeContrast(TmBsDeptTypeContrast tmBsDeptTypeContrast);

   int updateTmBsDeptTypeContrast(TmBsDeptTypeContrast tmBsDeptTypeContrast);

   int deleteTmBsDeptTypeContrastById(String deptCode);

   int deleteTmBsDeptTypeContrastByIds(String[] deptCodes);
}
