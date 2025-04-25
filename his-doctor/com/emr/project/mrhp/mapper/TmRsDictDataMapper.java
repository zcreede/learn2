package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.TmRsDictData;
import java.util.List;

public interface TmRsDictDataMapper {
   TmRsDictData selectTmRsDictDataById(Long id);

   List selectTmRsDictDataList(TmRsDictData tmRsDictData);

   int insertTmRsDictData(TmRsDictData tmRsDictData);

   int updateTmRsDictData(TmRsDictData tmRsDictData);

   int deleteTmRsDictDataById(Long id);

   int deleteTmRsDictDataByIds(Long[] ids);

   void insertAllList(List list);
}
