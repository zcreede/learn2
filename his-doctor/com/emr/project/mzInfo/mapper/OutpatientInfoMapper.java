package com.emr.project.mzInfo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutpatientInfoMapper {
   List selectOutPatientInfoByVisitNo(@Param("visitNo") String visitNo);

   List selectOutPatientInfoByIdCard(@Param("patientId") String patientId, @Param("idCard") String idCard);

   List selectTestReportList(@Param("patientId") String patientId);

   List selectTestReportResultList(@Param("list") List appCdList);

   List selectReportItemListByAppCd(@Param("appCd") String appCd);

   List selectTestReportDetailByAppCd(@Param("appCd") String appCd);

   List selectItemListByPatientId(@Param("patientId") String patientId);

   List selectExamItemResultList(@Param("id") String id);
}
