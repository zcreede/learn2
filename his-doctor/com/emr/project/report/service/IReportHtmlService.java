package com.emr.project.report.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.report.domain.vo.FeeItemVoParam;
import com.emr.project.report.domain.vo.FeeWorkloadVo;
import com.emr.project.report.domain.vo.PatientInLeaveInfoVoParam;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface IReportHtmlService {
   List queryPatient(PatientInLeaveInfoVoParam param) throws Exception;

   List queryFeeItemList(FeeItemVoParam param) throws Exception;

   Double queryFeeItemTotal(FeeItemVoParam param) throws Exception;

   AjaxResult selectFeeWorkloadList(FeeWorkloadVo param) throws Exception;

   AjaxResult feeWorkloadListExcel(FeeWorkloadVo param, HttpServletResponse response) throws Exception;
}
