package com.emr.project.webservice.mapper;

import com.emr.project.webservice.domain.req.MedicareAuditEncounterDtosReq;
import com.emr.project.webservice.domain.req.MedicareAuditPatientDtosReq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MedicareAuditMapper {
   MedicareAuditPatientDtosReq selectPatientDtos(@Param("admissionNo") String admissionNo);

   MedicareAuditEncounterDtosReq selectEncounterDtos(@Param("admissionNo") String admissionNo) throws Exception;

   List selectDiagnoseDtos(@Param("admissionNo") String admissionNo);

   List selectOrderDtos(@Param("admissionNo") String admissionNo, @Param("orderNoList") List orderNoList);
}
