package com.emr.project.system.mapper;

import com.emr.project.system.domain.WorkLoadPatient;
import com.emr.project.system.domain.req.PatientInfoReq;
import com.emr.project.system.domain.req.WorkLoadReportReq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkLoadPatientMapper {
   WorkLoadPatient selectWorkLoadPatientById(Long id);

   List selectWorkLoadPatientList(WorkLoadPatient workLoadPatient);

   int insertWorkLoadPatient(WorkLoadPatient workLoadPatient);

   int updateWorkLoadPatient(WorkLoadPatient workLoadPatient);

   int deleteWorkLoadPatientById(Long id);

   int deleteWorkLoadPatientByIds(Long[] ids);

   void insertAllList(@Param("list") List patientList);

   List selectWorkLoadBySql(@Param("sqlScript") String sqlScript);

   List getPatientInfo(PatientInfoReq req);

   List selectDeptList();

   void updateAllList(@Param("list") List updatePatient);

   List workLoadReport(WorkLoadReportReq req);

   List selectOriginalNumber(WorkLoadReportReq req);

   List selectNumNumber(WorkLoadReportReq req);

   List selectBedNumber(WorkLoadReportReq req);

   void deleteWorkByMainId(@Param("mainId") Long id);

   List selectBedOpenList(@Param("list") List deptCodeList);

   List selectWorkLoadByMainId(@Param("mainId") Long mainId);
}
