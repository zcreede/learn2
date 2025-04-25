package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.DiagSetMain;
import com.emr.project.tmpm.domain.vo.DiagSetMainVo;
import java.util.List;

public interface IDiagSetMainService {
   DiagSetMain selectDiagSetMainById(String setCd);

   List selectDiagSetMainList(DiagSetMain diagSetMain);

   void insertDiagSetMain(DiagSetMainVo diagSetMain) throws Exception;

   void updateDiagSetMain(DiagSetMain diagSetMain) throws Exception;

   int deleteDiagSetMainByIds(String[] setCds);

   void deleteDiagSetMainById(String setCd) throws Exception;

   void saveAsDiagSet(DiagSetMainVo diagSetMainVo) throws Exception;

   Integer selectSetMainMaxNumber() throws Exception;

   void updateSetMainFlag(DiagSetMain diagSetMain) throws Exception;
}
