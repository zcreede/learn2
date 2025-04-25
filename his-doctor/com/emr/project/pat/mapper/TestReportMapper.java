package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.TestReport;
import com.emr.project.pat.domain.vo.TestReportVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface TestReportMapper {
   TestReport selectTestReportById(String id);

   List selectTestReportByPatientId(String patientId);

   List selectTestReportList(TestReport testReport);

   List selectAppReportItemList(String[] patientIds);

   List selectTestresultList(TestReportVo testReportVo);

   int insertTestReport(TestReport testReport);

   int updateTestReport(TestReport testReport);

   int deleteTestReportById(String id);

   int deleteTestReportByIds(String[] ids);

   List selectGroupItemList(String[] ids);

   void insertMap(Map map);

   List selectNormalSignList(String[] patientIds);

   List selectTestReportVoListByDate(TestReportVo testReportVo);

   void updateTestReportByAppCd(TestReport testReport);

   List selectHisReportList(SqlVo sqlVo);

   List selectByAppCd(@Param("appCd") String appCd);

   List selectByAppCds(@Param("list") List list);

   List selectMzTestReportList(TestReportVo testReportVo);
}
