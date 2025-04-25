package com.emr.project.report.service;

import com.emr.project.report.domain.req.BeHospitalizedTableReq;
import com.emr.project.report.domain.req.ChangeDeptTableListReq;
import com.emr.project.report.domain.req.LeaveHospitalizedTableReq;
import com.emr.project.report.domain.req.PatientWorkloadReq;
import java.util.List;
import java.util.Map;

public interface IPatientInOutService {
   List getBeHospitalizedTableList(BeHospitalizedTableReq req);

   Map getLeaveHospitalizedTableList(LeaveHospitalizedTableReq req);

   List getChangeDeptTableList(ChangeDeptTableListReq req);

   List queryPatientWorkload(PatientWorkloadReq req);

   List getLeaveHospitalizedTableListPage(List listAll, LeaveHospitalizedTableReq req);
}
