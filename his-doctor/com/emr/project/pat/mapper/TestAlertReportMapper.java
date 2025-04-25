package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.TestAlertReport;
import com.emr.project.pat.domain.vo.TestAlertReportVo;
import java.util.List;

public interface TestAlertReportMapper {
   TestAlertReport selectTestAlertReportById(String resultalertid);

   List selectTestAlertReportList(TestAlertReport testAlertReport);

   int insertTestAlertReport(TestAlertReport testAlertReport);

   int updateTestAlertReport(TestAlertReport testAlertReport);

   int deleteTestAlertReportById(String resultalertid);

   int deleteTestAlertReportByIds(String[] resultalertids);

   List selectTestAlertMsg(TestAlertReportVo param);
}
