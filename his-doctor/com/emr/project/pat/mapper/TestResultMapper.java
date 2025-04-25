package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.TestResult;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestResultVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;

public interface TestResultMapper {
   TestResult selectTestResultById(String id);

   List selectTestResultList(TestResult testResult);

   int insertTestResult(TestResult testResult);

   int updateTestResult(TestResult testResult);

   int deleteTestResultById(String id);

   int deleteTestResultByIds(String[] ids);

   List analysisResultList(String[] patientIds, String clinItemCd);

   void insertMap(Map map);

   List selectHisCheckResult(String patientId);

   List selectOrderCheckResult(TestResultVo testResultVo);

   List selectHisResultList(SqlVo sqlVo);

   List selectTestAertList(ExamItemVo examItemVo);
}
