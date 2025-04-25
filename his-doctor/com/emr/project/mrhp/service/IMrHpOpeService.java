package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpOpe;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;

public interface IMrHpOpeService {
   MrHpOpe selectMrHpOpeById(String opeId);

   List selectMrHpOpeByRecordId(String recordId);

   List selectMrHpOpeList(MrHpOpe mrHpOpe);

   int insertMrHpOpe(MrHpOpe mrHpOpe);

   int updateMrHpOpe(MrHpOpe mrHpOpe);

   int deleteMrHpOpeByIds(String[] opeIds);

   int deleteMrHpOpeById(String opeId);

   void deleteMrHpOpeByRecordId(String recordId);

   List selectMrHpOpeByRescordId(String rescordId) throws Exception;

   List selectOperatRecordByPatientId(String patientId) throws Exception;

   void insertMrHpOpeList(MrHpVo mrHpVo) throws Exception;

   List selectPbHisMrHpOpe(SqlVo sqlVo) throws Exception;

   List selectMrisOperatRecordByPatientId(String patientId) throws Exception;

   List selectMrisMrHpOpeByRescordId(String recordId) throws Exception;

   void insertMrisHpOpeList(TdCmHpLineVo mrHpVo) throws Exception;
}
