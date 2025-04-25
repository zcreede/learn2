package com.emr.project.operation.service;

import com.emr.project.operation.domain.req.TakeDrugFeeReq;
import java.util.List;
import java.util.Set;

public interface ExpenseDetailService {
   void addList(List list) throws Exception;

   void addListApp(List list) throws Exception;

   List queryTakeDrugFeePageList(TakeDrugFeeReq req) throws Exception;

   List queryReturnedFeeList(List billsNoList) throws Exception;

   List queryFeeList(List billsNoList) throws Exception;

   List selectExpenseDetailListByPerformListNo(String performListNo, List performListSortNumberList);

   List selectExpenseDetailByBillsNoList(Set billsNoList);

   List selectExpenseDetailApplyByBillsNoList(List billsNoList) throws Exception;

   List selectExpenseDetailApplyByPrescriptions(List prescriptions) throws Exception;

   void delExpenseDetailApplyByBillsNo(List billsNoList) throws Exception;

   List selectPatExpenseDetailApplyList(String admissionNo, String accountingDepartmentNo) throws Exception;

   String verifyExpensedetailByOrderNo(List orderList);

   int queryOperationFee(String inpNo) throws Exception;
}
