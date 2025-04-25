package com.emr.project.operation.service;

import com.emr.project.operation.domain.dto.DepartAccountDTO;
import com.emr.project.operation.domain.req.CombinationListReq;
import com.emr.project.operation.domain.req.CombinationSaveReq;
import com.emr.project.operation.domain.req.NurseWorkloadReq;
import com.emr.project.operation.domain.req.OperationPatientReq;
import com.emr.project.operation.domain.req.SearchDoctorListReq;
import com.emr.project.operation.domain.req.SuppPrintDataListReq;
import java.util.List;
import java.util.Map;

public interface IDepartAccountService {
   List getDepartAccountList(Map param) throws Exception;

   Map saveDepartAccount(DepartAccountDTO departAccountDTO, List mapList1) throws Exception;

   Map submitDepartAccount(List expenseDetailAppList, List mapList1) throws Exception;

   void delDepartAccount(List expenseDetailAppList) throws Exception;

   List getDoctorList(SearchDoctorListReq req);

   List getDeptList(String searchStr);

   List getNurseList(String searchStr, String status);

   List getCombinationList(CombinationListReq req);

   List getCombinationDetails(String packageNo);

   void saveCombinationPackage(CombinationSaveReq req) throws Exception;

   List getOperationApply(String admissionNo);

   List getOperationPatientList(OperationPatientReq req);

   List getSuppPrintData(SuppPrintDataListReq req);

   void groupByExecutorDpt(List mapList1, List mapList, Boolean flag);

   Boolean selectDeptArrearsByDepCode(String patientDepCode);

   List queryNurseWorkloadAllList(NurseWorkloadReq req) throws Exception;

   List queryNurseWorkloadByPage(NurseWorkloadReq req, List allList) throws Exception;
}
