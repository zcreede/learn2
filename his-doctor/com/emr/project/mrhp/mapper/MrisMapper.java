package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import org.apache.ibatis.annotations.Param;

public interface MrisMapper {
   TdCmHpLineVo selectMrHpByPatientId(@Param("patientId") String patientId);

   TdCmHpLineVo selectVistVoByPatientId(@Param("patientId") String patientId);

   TdCmHpLineVo selectMrisFee(String recordId);
}
