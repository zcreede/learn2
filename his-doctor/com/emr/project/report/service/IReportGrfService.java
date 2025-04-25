package com.emr.project.report.service;

import com.emr.project.report.domain.vo.ReportGrfReqParamVo;
import java.util.List;
import java.util.Map;

public interface IReportGrfService {
   List feeSummaryDayData(Map param) throws Exception;

   List feeSummaryDayDataList(ReportGrfReqParamVo paramVo) throws Exception;

   List feeSummaryDayDataListForPdf(ReportGrfReqParamVo paramVo) throws Exception;

   List feeSummaryDay(ReportGrfReqParamVo reportGrfReqParamVo) throws Exception;
}
