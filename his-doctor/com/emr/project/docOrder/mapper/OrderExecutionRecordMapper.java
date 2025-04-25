package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.resp.OrderExecutionItemDetail;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface OrderExecutionRecordMapper {
   OrderExecutionItemDetail selectOrderDetail(@Param("orderNo") String orderNo, @Param("admissionNo") String admissionNo);

   List selectOrderDetailList(@Param("orderNo") String orderNo);

   List selectPerformDetailList(@Param("admissionNo") String admissionNo, @Param("orderNo") String orderNo);

   List selectExpenseDetailList(@Param("admissionNo") String admissionNo, @Param("orderNo") String orderNo, @Param("performListNo") String performListNo);

   List selectExpenseDetailListByPerformListNo(@Param("list") Set performListNo, @Param("orderNo") String orderNo);

   List selectTakeDrugListByPerformListNo(@Param("list") Set performListNo, @Param("orderNo") String orderNo);

   List selectReturnDetail(@Param("list") List billsNoList, @Param("orderNo") String orderNo);
}
