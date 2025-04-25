package com.emr.project.operation.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PatientsReturnDrugMapper {
   List unTakeDrugList(@Param("admissionNo") String admissionNo, @Param("deptCode") String deptCode, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("drugName") String drugName, @Param("deptCode1") String deptCode1);

   List selectUnTakeDrugListByPerformListNo(@Param("performListNo") String performListNo, @Param("list") List performListSortNumberList);
}
