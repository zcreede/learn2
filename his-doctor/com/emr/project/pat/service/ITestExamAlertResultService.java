package com.emr.project.pat.service;

import com.emr.project.pat.domain.TestExamAlertResult;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestAlertReportVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgVo;
import java.util.List;

public interface ITestExamAlertResultService {
   TestExamAlertResult selectTestExamAlertResultByReportId(String id);

   List selectTestExamAlertResultList(TestExamAlertResult testExamAlertResult);

   TestExamAlertResult insertTestExamAlertResult(TestExamAlertMsgVo alertMsgVo, TestExamAlertResult result) throws Exception;

   int updateTestExamAlertResult(TestExamAlertResult testExamAlertResult);

   int deleteTestExamAlertResultByIds(Long[] ids);

   int deleteTestExamAlertResultById(Long id);

   List testResultlist(TestAlertReportVo testAlertReportVo, String overtime) throws Exception;

   List examResultlist(ExamItemVo examItemVo, String overtime) throws Exception;

   void updateMrFileId(TestExamAlertResult testExamAlertResult) throws Exception;

   List selectExamaLertList(ExamItemVo examItemVo) throws Exception;

   List selectExamAlertList(ExamItemVo examItemVo) throws Exception;
}
