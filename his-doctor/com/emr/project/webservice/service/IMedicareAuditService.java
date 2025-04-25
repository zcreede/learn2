package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.req.MedicareAuditEncounterDtosReq;
import com.emr.project.webservice.domain.req.MedicareAuditPatientDtosReq;
import com.emr.project.webservice.domain.vo.MedicareAuditVo;
import java.util.List;

public interface IMedicareAuditService {
   MedicareAuditVo getMedicareAuditWebApi(String admissionNo, List orderNoList) throws Exception;

   MedicareAuditPatientDtosReq selectPatientDtos(String admissionNo) throws Exception;

   MedicareAuditEncounterDtosReq selectEncounterDtos(String admissionNo) throws Exception;

   List selectDiagnoseDtos(String admissionNo) throws Exception;

   List selectOrderDtos(String admissionNo, List orderNoList) throws Exception;
}
