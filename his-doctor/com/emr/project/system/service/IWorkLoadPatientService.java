package com.emr.project.system.service;

import com.emr.project.system.domain.WorkLoadPatient;
import com.emr.project.system.domain.req.PatientInfoReq;
import java.util.List;

public interface IWorkLoadPatientService {
   WorkLoadPatient selectWorkLoadPatientById(Long id);

   List selectWorkLoadPatientList(WorkLoadPatient workLoadPatient);

   int insertWorkLoadPatient(WorkLoadPatient workLoadPatient);

   int updateWorkLoadPatient(WorkLoadPatient workLoadPatient);

   int deleteWorkLoadPatientByIds(Long[] ids);

   int deleteWorkLoadPatientById(Long id);

   WorkLoadPatient insertOrUpdateWorkLoadPatient(WorkLoadPatient workLoadPatient) throws Exception;

   List getPatientInfo(PatientInfoReq req) throws Exception;

   List selectDeptList() throws Exception;
}
