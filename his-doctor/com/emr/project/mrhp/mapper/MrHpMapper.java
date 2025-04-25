package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.domain.vo.MedicalInforVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.domain.vo.PatientInfoDetailVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrHpMapper {
   MrHp selectMrHpById(String recordId);

   MrHpVo selectMrHpVoById(String recordId);

   String selectMrHpSignCodes(String recordId);

   MedicalInforVo selectMrHpAndAttachByPatientId(MrHpVo mrHpVo);

   List selectMrHpList(MrHp mrHp);

   int insertMrHp(MrHpVo mrHpVo);

   int updateMrHp(MrHpVo mrHpVo);

   int updateMrHpState(MrHp mrHp);

   int deleteMrHpById(String recordId);

   int deleteMrHpByIds(String[] recordIds);

   MrHpVo selectMrHpByPatientId(String patientId);

   MrHpVo selectVistVoByPatientId(String patientId);

   List selectPatMrHpList(PatEventVo patEventVo);

   List selectPatMrHpNewList(PatEventVo patEventVo);

   void updateLastFinishTimeList(List list);

   void updateMrHpOutTime(MrHpVo mrHpVo);

   MrHpVo selectPbHisMrHp(SqlVo sqlVo);

   PatientInfoDetailVo selectPatientInfoDetail(@Param("admissionNo") String admissionNo);
}
