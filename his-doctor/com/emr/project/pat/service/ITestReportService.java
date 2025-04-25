package com.emr.project.pat.service;

import com.emr.project.pat.domain.TestReport;
import com.emr.project.pat.domain.vo.TestReportVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;

public interface ITestReportService {
   TestReport selectTestReportById(String id);

   List selectTestReportList(TestReport testReport);

   List selectTestReportByPatientId(String patientId);

   List selectAppReportItemList(String[] patientIds) throws Exception;

   List selectTestresultList(TestReportVo testReportVo) throws Exception;

   int insertTestReport(TestReport testReport);

   int updateTestReport(TestReport testReport);

   int deleteTestReportByIds(String[] ids);

   int deleteTestReportById(String id);

   List selectAnalysisList(String[] patientIds);

   List selectTestReportVoListByDate(TestReportVo testReportVo) throws Exception;

   void updateTestReportByAppCd(TestReport testReport) throws Exception;

   List selectHisReportList(SqlVo sqlVo) throws Exception;

   List selectHisReport(String id, List list) throws Exception;

   List selectByAppCd(String appCd) throws Exception;

   List selectByAppCds(List list) throws Exception;

   List selectMzTestReportList(TestReportVo testReportVo) throws Exception;
}
