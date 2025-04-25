package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.TestExamAlertResult;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestAlertReportVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestExamAlertResultMapper {
   TestExamAlertResult selectTestExamAlertResultByReportId(@Param("reportId") String reportId);

   List selectTestExamAlertResultList(TestExamAlertResult testExamAlertResult);

   int insertTestExamAlertResult(TestExamAlertResult testExamAlertResult);

   int updateTestExamAlertResult(TestExamAlertResult testExamAlertResult);

   int deleteTestExamAlertResultById(Long id);

   int deleteTestExamAlertResultByIds(Long[] ids);

   List selectTestAlertResultList(TestAlertReportVo testAlertReportVo);

   List selectExamAlertResultList(ExamItemVo examItemVo);

   void updateMrFileId(TestExamAlertResult testExamAlertResult);

   List selectExamaLertList(ExamItemVo examItemVo);

   List selectExamAlertList(ExamItemVo examItemVo);
}
