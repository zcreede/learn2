package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.TmDsPreserveOut;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface TmDsPreserveOutMapper {
   TmDsPreserveOut selectTmDsPreserveOutById(Long id);

   List selectTmDsPreserveOutList(TmDsPreserveOut tmDsPreserveOut);

   int insertTmDsPreserveOut(TmDsPreserveOut tmDsPreserveOut);

   int updateTmDsPreserveOut(TmDsPreserveOut tmDsPreserveOut);

   int deleteTmDsPreserveOutById(Long id);

   int deleteTmDsPreserveOutByIds(Long[] ids);

   List testConnSql(@Param("sqlStr") String sqlStr);

   Map selectSql(@Param("sql") String sqlStatement);
}
