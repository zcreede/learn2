package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrHpDiaMapper {
   MrHpDia selectMrHpDiaById(String diaId);

   List selectMrHpDiaList(MrHpDia mrHpDia);

   int insertMrHpDia(MrHpDia mrHpDia);

   List selectTdCmDiagListLineByRecordId(@Param("recordId") String recordId, @Param("diagType") String diagType);

   int updateMrHpDia(MrHpDia mrHpDia);

   int deleteMrHpDiaById(String diaId);

   void deleteMrHpDiaByRecordId(String recordId);

   int deleteMrHpDiaByIds(String[] diaIds);

   List selectMrHpDiaByRescordId(String rescordId);

   List selectDiagInfoByPatientId(DiagInfo diagInfo);

   int insertMrHpDiaList(List mrHpDiaList);

   List selectHisPbMrDiaList(SqlVo sqlVo);

   List selectMrisDiagInfoByPatientId(DiagInfo diagInfo);

   List selectMrisMrHpDiaList(MrHpDia mrHpDia);
}
