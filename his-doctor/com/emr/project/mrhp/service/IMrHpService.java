package com.emr.project.mrhp.service;

import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import com.emr.project.mrhp.domain.vo.MrHpPrintVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.domain.vo.PatientInfoDetailVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import java.util.List;
import java.util.Map;

public interface IMrHpService {
   MrHp selectMrHpById(String recordId);

   MrHpVo selectMrHpVoById(String recordId);

   MrHpVo selectMrHpByPatientId(String patientId);

   List selectMrHpList(MrHp mrHp);

   void saveMrHp(MrHpVo mrHpVo) throws Exception;

   int deleteMrHpByIds(String[] recordIds);

   int deleteMrHpById(String recordId);

   MrHpVo selectMrHpPatientDetail(MrHpVo mrHpVo) throws Exception;

   String verificationRequired(MrHpVo mrHpVo, MrHpAttachVo mrHpAttachVo) throws Exception;

   MrHpAttachVo selectMrHpAttachList(MrHpVo mrHpVo);

   void saveSignAndMrHp(MrHpVo mrHpVo, EmrSignData emrSignData) throws Exception;

   void mrHpSaveSign(MrHpVo mrHpVo, EmrSignData emrSignData) throws Exception;

   void mrHpCancelSign(MrHp mrHp) throws Exception;

   MrHpVo selectMrHpPrintView(MrHpVo mrHpVo) throws Exception;

   MrHpVo selectMrHpPrintViewHis(MrHpVo mrHpVo) throws Exception;

   Map selectMrHpDictMap() throws Exception;

   Map selectMrHpSignPicList(String recordId) throws Exception;

   List getQcErrorList(MrHpVo mrHpVo) throws Exception;

   MrHpPrintVo getPrintPersonInfoDetail(MrHpVo mrHpVo) throws Exception;

   List mrHpCheckOut(MrHpVo mrHpVo) throws Exception;

   List selectPatMrHpList(PatEventVo patEventVo) throws Exception;

   List selectPatMrHpNewList(PatEventVo patEventVo) throws Exception;

   void updateLastFinishTimeList(List list) throws Exception;

   void updateOutInfoFromOrder(OrderSaveVo orderSaveVo) throws Exception;

   void syncMrHpVoToHis(MrHpVo mrHpVo, List rc013List) throws Exception;

   PatientInfoDetailVo selectPatientInfoDetail(String admissionNo);

   String getLis(String patientId);
}
