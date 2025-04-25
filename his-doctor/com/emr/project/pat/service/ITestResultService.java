package com.emr.project.pat.service;

import com.emr.project.pat.domain.TestResult;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestResultVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;

public interface ITestResultService {
   TestResult selectTestResultById(String id);

   List selectTestResultList(TestResult testResult);

   int insertTestResult(TestResult testResult);

   int updateTestResult(TestResult testResult);

   int deleteTestResultByIds(String[] ids);

   int deleteTestResultById(String id);

   List analysisResultList(String[] patientIds, String testAim);

   List selectResultDateList(String[] patientIds, String testAim);

   List selectHisCheckResult(String patientId) throws Exception;

   List selectOrderCheckResult(TestResultVo testResult) throws Exception;

   List selectHisResultList(SqlVo sqlVo) throws Exception;

   List selectTestAertList(ExamItemVo examItemVo) throws Exception;
}
