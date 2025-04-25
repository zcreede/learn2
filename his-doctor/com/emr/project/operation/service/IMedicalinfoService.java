package com.emr.project.operation.service;

import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import java.util.List;
import java.util.Map;

public interface IMedicalinfoService {
   Medicalinformation queryByAdmissionNo(String admissionNo);

   Map getMedicalinfoDetail(String admissionNo);

   Medicalinformation getMedicalinfo(String admission_no);

   Medicalinformation getHisMedicalByAdmissionNo(String admission_no);

   Medicalinformation selectMedicalinfo(String admissionNo, Integer hospitalizedCount);

   Medicalinformation selectMedicalinfoByPatientId(String admissionNo);

   List selectOutpatient(String inpNo, String idCard);

   List selectPatientInfoOther(VisitinfoVo visitinfoVo);

   void updateLCLJInfo(Medicalinformation m) throws Exception;
}
