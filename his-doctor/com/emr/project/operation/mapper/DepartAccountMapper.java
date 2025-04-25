package com.emr.project.operation.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface DepartAccountMapper {
   List getDepartAccountList(Map param);

   List getDoctorList(@Param("searchStr") String searchStr, @Param("hospitalNo") String hospitalCode, @Param("type") String type, @Param("deptCode") String deptCode, @Param("status") String status);

   List getDeptList(@Param("searchStr") String searchStr, @Param("hospitalNo") String hospitalCode);

   List getNurseList(@Param("searchStr") String searchStr, @Param("hospitalNo") String hospitalCode, @Param("deptCode") String deptCode, @Param("status") String status);

   List getCombinationList(@Param("shareClass") String shareClass, @Param("packagePym") String packagePym, @Param("hospitalCode") String hospitalCode, @Param("userName") String userName, @Param("deptCode") String deptCode);

   List getCombinationDetails(@Param("packageNo") String packageNo);

   List getOperationApply(@Param("admissionNo") String admissionNo);

   Double checkRefundNum(@Param("prescription") String prescription);

   List getOperationPatientList(@Param("type") Integer type, @Param("deptCode") String deptCode, @Param("searchStr") String searchStr, @Param("start") String start, @Param("end") String end);

   String selectArrearsByDeptCode(@Param("patientDepCode") String patientDepCode);

   List selectPrintRefundAccount(@Param("list") List billNoList, @Param("deptCode") String deptCode);

   List queryNurseWorkloadAllList(@Param("admissionNo") String admissionNo, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("nurseNo") String nurseNo, @Param("list") List threeLevelCodeList, @Param("depCode") String depCode, @Param("returnFlag") String returnFlag, @Param("selChargeNo") String selChargeNo);
}
