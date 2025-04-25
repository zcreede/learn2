package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.TmRdDefine;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmRdDefineMapper {
   TmRdDefine selectTmRdDefineById(Long id);

   List selectTmRdDefineList(TmRdDefine tmRdDefine);

   int insertTmRdDefine(TmRdDefine tmRdDefine);

   int updateTmRdDefine(TmRdDefine tmRdDefine);

   int deleteTmRdDefineById(Long id);

   int deleteTmRdDefineByIds(Long[] ids);

   List selectManageList(@Param("sql") String sql);
}
