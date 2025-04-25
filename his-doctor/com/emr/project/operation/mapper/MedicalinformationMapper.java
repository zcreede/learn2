package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface MedicalinformationMapper {
   Medicalinformation getMedicalinfo(String admission_no);

   Medicalinformation getHisMedicalByAdmissionNo(String admission_no);

   Map getMedicalinfoDetail(@Param("admissionNo") String admissionNo);

   Medicalinformation selectMedicalinfo(@Param("admissionNo") String admissionNo, @Param("hospitalizedCount") Integer hospitalizedCount);

   Medicalinformation selectMedicalinfoByPatientId(@Param("patientId") String patientId);

   List selectOutpatient(@Param("inpNo") String inpNo, @Param("idCard") String idCard);

   List selectPatientInfoOther(VisitinfoVo visitinfoVo);

   void updateLCLJInfo(Medicalinformation m);
}
