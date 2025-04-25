package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.DiagSetMain;
import com.emr.project.tmpm.domain.vo.DiagSetMainVo;
import java.util.List;

public interface DiagSetMainMapper {
   DiagSetMain selectDiagSetMainById(String setCd);

   List selectDiagSetMainList(DiagSetMain diagSetMain);

   void insertDiagSetMain(DiagSetMainVo diagSetMain);

   int updateDiagSetMain(DiagSetMain diagSetMain);

   void deleteDiagSetMainById(String setCd);

   int deleteDiagSetMainByIds(String[] setCds);

   Integer selectSetMainMaxNumber();
}
