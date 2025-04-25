package com.emr.project.system.mapper;

import com.emr.project.system.domain.TmBsModule;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmBsModuleMapper {
   TmBsModule selectTmBsModuleById(Long id);

   List selectTmBsModuleList(TmBsModule tmBsModule);

   int insertTmBsModule(TmBsModule tmBsModule);

   int updateTmBsModule(TmBsModule tmBsModule);

   int deleteTmBsModuleById(Long id);

   int deleteTmBsModuleByIds(Long[] ids);

   List selectModuleListByUser(@Param("staffCode") String staffCode);
}
