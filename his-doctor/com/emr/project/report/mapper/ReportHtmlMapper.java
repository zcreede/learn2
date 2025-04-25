package com.emr.project.report.mapper;

import com.emr.project.report.domain.vo.FeeItemVoParam;
import com.emr.project.report.domain.vo.FeeWorkloadVo;
import com.emr.project.report.domain.vo.PatientInLeaveInfoVoParam;
import java.util.List;

public interface ReportHtmlMapper {
   List selectPatientAll(PatientInLeaveInfoVoParam param);

   List selectPatientIn(PatientInLeaveInfoVoParam param);

   List selectPatientLeave(PatientInLeaveInfoVoParam param);

   List queryFeeItemList(FeeItemVoParam param);

   Double queryFeeItemTotal(FeeItemVoParam param);

   List selectFeeWorkloadList(FeeWorkloadVo param);

   List selectInHosPatientFeeWorkloadList(FeeWorkloadVo param);

   List selectLeaveHospitalFeeWorkloadList(FeeWorkloadVo param);
}
