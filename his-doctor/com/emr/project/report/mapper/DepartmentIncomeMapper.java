package com.emr.project.report.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentIncomeMapper {
   List selectDepartmentStatisticsDataList(@Param("depCode") String depCode, @Param("departmentType") String departmentType, @Param("dateTimeStart") String dateTimeStart, @Param("dateTimeEnd") String dateTimeEnd, @Param("costType") String costType);

   List selectFeeWorkloadList(@Param("depCode") String depCode, @Param("departmentType") String departmentType, @Param("dateTimeStart") String dateTimeStart, @Param("dateTimeEnd") String dateTimeEnd, @Param("costType") String costType);

   List selectLeaveHospitalDataList(@Param("depCode") String depCode, @Param("departmentType") String departmentType, @Param("dateTimeStart") String dateTimeStart, @Param("dateTimeEnd") String dateTimeEnd);

   List selectInHosPatientDataList(@Param("depCode") String depCode, @Param("departmentType") String departmentType, @Param("dateTimeStart") String dateTimeStart, @Param("dateTimeEnd") String dateTimeEnd);

   List selectDeptLeaveHospitalList(@Param("depCode") String depCode, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("departmentType") String departmentType);

   List selectDeptInHosPatientList(@Param("depCode") String depCode, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("departmentType") String departmentType);

   List selectLeaveHospitalFeeWorkloadList(@Param("depCode") String depCode, @Param("departmentType") String departmentType, @Param("dateTimeStart") String dateTimeStart, @Param("dateTimeEnd") String dateTimeEnd);

   List selectInHosPatientFeeWorkloadList(@Param("depCode") String depCode, @Param("departmentType") String departmentType, @Param("dateTimeStart") String dateTimeStart, @Param("dateTimeEnd") String dateTimeEnd);
}
