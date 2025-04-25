package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;

public interface IMrHpDiaService {
   MrHpDia selectMrHpDiaById(String diaId);

   List selectMrHpDiaList(MrHpDia mrHpDia);

   int insertMrHpDia(MrHpDia mrHpDia);

   List selectTdCmDiagListLineByRecordId(String recordId, String diagType);

   int updateMrHpDia(MrHpDia mrHpDia);

   int deleteMrHpDiaByIds(String[] diaIds);

   int deleteMrHpDiaById(String diaId);

   void deleteMrHpDiaByRecordId(String recordId);

   List selectMrHpDiaByRescordId(String rescordId) throws Exception;

   List selectDiagInfoByPatientId(DiagInfo diagInfo) throws Exception;

   void insertMrHpDiaList(MrHpVo mrHpVo);

   List selectHisPbMrDiaList(SqlVo sqlVo) throws Exception;

   List selectMrisDiagInfoByPatientId(DiagInfo diagInfo) throws Exception;

   List selectMrisMrHpDiaList(MrHpDia mrHpDia) throws Exception;

   void insertMrisHpDiaList(TdCmHpLineVo mrHpVo) throws Exception;
}
