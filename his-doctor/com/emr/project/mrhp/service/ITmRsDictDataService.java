package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.TmRsDictData;
import java.util.List;

public interface ITmRsDictDataService {
   TmRsDictData selectTmRsDictDataById(Long id);

   List selectTmRsDictDataList(TmRsDictData tmRsDictData);

   int insertTmRsDictData(TmRsDictData tmRsDictData);

   int updateTmRsDictData(TmRsDictData tmRsDictData);

   int deleteTmRsDictDataByIds(Long[] ids);

   int deleteTmRsDictDataById(Long id);
}
