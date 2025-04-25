package com.emr.project.mrhp.service;

import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import java.util.List;

public interface IMrisService {
   TdCmHpLineVo selectMrHpPatientDetail(String patientId) throws Exception;

   void saveMrHp(TdCmHpLineVo mrHpVo) throws Exception;

   void saveSignAndMrHp(TdCmHpLineVo mrHpVo, EmrSignData emrSignData) throws Exception;

   void mrHpSaveSign(TdCmHpLineVo mrHpVo, EmrSignData emrSignData) throws Exception;

   List getQcErrorList(TdCmHpLineVo mrHpVo) throws Exception;

   List mrHpCheckOut(TdCmHpLineVo mrHpVo) throws Exception;
}
