package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.TmRdDefine;
import java.util.List;

public interface ITmRdDefineService {
   TmRdDefine selectTmRdDefineById(Long id);

   List selectTmRdDefineList(TmRdDefine tmRdDefine) throws Exception;

   int insertTmRdDefine(TmRdDefine tmRdDefine);

   int updateTmRdDefine(TmRdDefine tmRdDefine);

   int deleteTmRdDefineByIds(Long[] ids);

   int deleteTmRdDefineById(Long id);
}
