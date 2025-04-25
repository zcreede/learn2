package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.TmRsDictTypt;
import java.util.List;

public interface ITmRsDictTyptService {
   TmRsDictTypt selectTmRsDictTyptById(Long id);

   List selectTmRsDictTyptList(TmRsDictTypt tmRsDictTypt);

   int insertTmRsDictTypt(TmRsDictTypt tmRsDictTypt);

   int updateTmRsDictTypt(TmRsDictTypt tmRsDictTypt);

   int deleteTmRsDictTyptByIds(Long[] ids);

   int deleteTmRsDictTyptById(Long id);

   Boolean checkDefineRemove(Long id);

   List selectDictTypeByTypeCode(String dictTypeCode);
}
