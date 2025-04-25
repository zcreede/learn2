package com.emr.project.pat.service;

import com.emr.project.pat.domain.TestAlertReport;
import com.emr.project.pat.domain.vo.TestAlertReportVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgResVo;
import java.util.List;

public interface ITestAlertReportService {
   TestAlertReport selectTestAlertReportById(String resultalertid);

   List selectTestAlertReportList(TestAlertReport testAlertReport);

   int insertTestAlertReport(TestAlertReport testAlertReport);

   int updateTestAlertReport(TestAlertReport testAlertReport);

   int deleteTestAlertReportByIds(String[] resultalertids);

   int deleteTestAlertReportById(String resultalertid);

   TestExamAlertMsgResVo selectTestAlertMsg(TestAlertReportVo param) throws Exception;
}
