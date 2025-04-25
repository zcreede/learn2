package com.emr.project.system.service;

import com.emr.project.system.domain.TmBsModule;
import java.util.List;

public interface ITmBsModuleService {
   TmBsModule selectTmBsModuleById(Long id);

   List selectTmBsModuleList(TmBsModule tmBsModule);

   int insertTmBsModule(TmBsModule tmBsModule);

   int updateTmBsModule(TmBsModule tmBsModule);

   int deleteTmBsModuleByIds(Long[] ids);

   int deleteTmBsModuleById(Long id);

   List selectModuleListByUser(String userName) throws Exception;
}
