package com.emr.project.mzInfo.service;

import java.util.List;

public interface IOutpatientInfoService {
   List selectOutPatientInfoByVisitNo(String patientId) throws Exception;

   List selectOutPatientInfoByIdCard(String patientId, String idCard) throws Exception;

   List selectTestReportList(String patientId) throws Exception;

   List selectTestReportResultListByAppCd(String appCd) throws Exception;

   List selectItemList(String patientId) throws Exception;

   List selectExamItemResultList(String id) throws Exception;
}
