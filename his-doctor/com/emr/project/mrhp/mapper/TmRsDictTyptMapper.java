package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.TmRsDictTypt;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmRsDictTyptMapper {
   TmRsDictTypt selectTmRsDictTyptById(Long id);

   List selectTmRsDictTyptList(TmRsDictTypt tmRsDictTypt);

   int insertTmRsDictTypt(TmRsDictTypt tmRsDictTypt);

   int updateTmRsDictTypt(TmRsDictTypt tmRsDictTypt);

   int deleteTmRsDictTyptById(Long id);

   int deleteTmRsDictTyptByIds(Long[] ids);

   List selectListByDefineId(@Param("id") Long id);

   List selectDictTypeByTypeCode(@Param("typeCode") String dictTypeCode);
}
