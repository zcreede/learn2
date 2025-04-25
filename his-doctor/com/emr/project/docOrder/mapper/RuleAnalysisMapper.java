package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.req.FsiEncounterDto;
import com.emr.project.docOrder.domain.req.RuleAnalysisPatientDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RuleAnalysisMapper {
   RuleAnalysisPatientDto selectPatientInfo(@Param("patientId") String patientId);

   FsiEncounterDto selectVisitInfo(@Param("patientId") String patientId);

   List selectDiagInfo(@Param("patientId") String patientId);

   List selectYbInfo(@Param("list") List cpNoList);
}
