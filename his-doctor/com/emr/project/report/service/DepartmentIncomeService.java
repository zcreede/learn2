package com.emr.project.report.service;

import com.emr.project.report.domain.req.DepartmentStatisticsDataReq;
import com.emr.project.report.domain.req.PatientWorkloadReq;
import com.emr.project.report.domain.resp.FeeWorkloadAllListResp;
import java.util.List;

public interface DepartmentIncomeService {
   List selectDepartmentStatisticsDataList(DepartmentStatisticsDataReq req) throws Exception;

   FeeWorkloadAllListResp selectFeeWorkloadList(DepartmentStatisticsDataReq req) throws Exception;

   List queryDeptWorkload(PatientWorkloadReq req);
}
