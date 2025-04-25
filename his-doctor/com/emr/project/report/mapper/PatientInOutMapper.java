package com.emr.project.report.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PatientInOutMapper {
   List selectBeHospitalizedTableList(@Param("deptCode") String deptCode, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("caseNo") String caseNo, @Param("name") String name);

   List selectLeaveHospitalizedTableList(@Param("deptCode") String deptCode, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("status") String status, @Param("list") List threeLevelCodeList, @Param("residentNo") String residentNo);

   List selectChangeDeptTableList(@Param("deptCode") String deptCode, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("caseNo") String caseNo, @Param("name") String name, @Param("isChangeIn") String isChangeIn);

   List selectInHosPatientList(@Param("depCode") String depCode, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("threeLevelCodeList") List threeLevelCodeList, @Param("residentNo") String residentNo);

   List selectLeaveHospitalList(@Param("depCode") String depCode, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("threeLevelCodeList") List threeLevelCodeList, @Param("residentNo") String residentNo);
}
