package com.emr.project.report.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ReportGrfMapper {
   void feeSummaryDayData(Map param);

   List feeSummaryDayDataList(@Param("admissionNo") String admissionNo, @Param("tableName") String tableName);

   String selectHospitalMarkByAdmissionNo(@Param("admissionNo") String admissionNo);

   List feeSummaryDayDataListForPdf(@Param("admissionNo") String admissionNo);

   List selectBillingDetailByAdmissionNoList(@Param("admissionNo") String admissionNo);

   List selectFeeSummaryDay(String admissionNo);

   List selectThirdList();
}
