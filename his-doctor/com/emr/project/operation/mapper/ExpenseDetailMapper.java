package com.emr.project.operation.mapper;

import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface ExpenseDetailMapper {
   int insertBatch(List record) throws Exception;

   int insertBatchApp(List record) throws Exception;

   List selectTakeDrugFeePageList(@Param("list") List threeLevelCodeList, @Param("admissionNo") String admissionNo, @Param("drugName") String drugName, @Param("deptCode") String deptCode, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("deptCode1") String deptCode1);

   List selectReturnedFeeList(List list);

   List selectFeeList(List list);

   List selectPrescriptionList(@Param("list") List performListNos);

   List selectExpenseDetailByPerformListNo(@Param("performListNo") String performListNo);

   List selectTakeDrugListByPerformListNo(@Param("performListNo") String performListNo, @Param("list") List performListSortNumberList);

   List selectExpenseDetailListByPerformListNo(@Param("performListNo") String performListNo, @Param("list") List performListSortNumberList);

   List selectExpenseDetailByPerformList(@Param("performListNo") String performListNo, @Param("list") List performListSortNumberList);

   List selectExpensedetailByOrderNo(@Param("list") List orderNoList);

   List selectExpenseDetailByBillsNoList(@Param("list") Set billsNoList);

   List selectExpenseDetailApplyByBillsNoList(@Param("list") List billsNoList);

   List selectExpenseDetailApplyByPrescriptions(@Param("list") List prescriptions);

   void delExpenseDetailApplyByBillsNo(@Param("list") List billsNoList);

   List selectPatExpenseDetailApplyList(@Param("admissionNo") String admissionNo, @Param("accountingDepartmentNo") String accountingDepartmentNo);

   int queryOperationFee(@Param("inpNo") String inpNo);
}
