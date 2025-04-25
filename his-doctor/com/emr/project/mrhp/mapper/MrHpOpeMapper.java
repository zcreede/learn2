package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpOpe;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrHpOpeMapper {
   MrHpOpe selectMrHpOpeById(String opeId);

   List selectMrHpOpeByRecordId(String recordId);

   List selectMrHpOpeList(MrHpOpe mrHpOpe);

   int insertMrHpOpe(MrHpOpe mrHpOpe);

   int updateMrHpOpe(MrHpOpe mrHpOpe);

   int deleteMrHpOpeById(String opeId);

   void deleteMrHpOpeByRecordId(String recordId);

   int deleteMrHpOpeByIds(String[] opeIds);

   List selectMrHpOpeByRescordId(String rescordId);

   List selectOperatRecordByPatientId(String patientId);

   int insertMrHpOpeList(List mrHpOpeList);

   List selectPbHisMrHpOpe(SqlVo sqlVo);

   List selectMrisOperatRecordByPatientId(@Param("patientId") String patientId);

   List selectMrisMrHpOpeByRescordId(@Param("recordId") String recordId);
}
