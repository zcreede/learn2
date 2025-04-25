package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.InpatientOrderPerform;
import com.emr.project.docOrder.domain.req.SuspendOrderListReq;
import com.emr.project.docOrder.domain.req.TodayOrderOperationReq;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface InpatientSuspendOrderMapper {
   List selectInpatientSuspendOrder(SuspendOrderListReq suspendOrderListReq);

   int updateByPrimaryKeySelective(InpatientOrderPerform inPerform);

   List selectTodayOrderOperation(TodayOrderOperationReq req);

   List selectTakeDrugDetail(@Param("performListNo") String performListNo, @Param("orderNo") String orderNo, @Param("admissionNo") String admissionNo);

   List selectPerformStatusList(@Param("performListNo") String performListNo, @Param("list") List sortNumberList, @Param("type") String type);

   List selectByPerformListNo(@Param("performListNo") String performListNo, @Param("performListStatus") String performListStatus);

   void updateStatus(@Param("performListNo") String performListNo, @Param("list") List performListSortNumberList, @Param("status") String status, @Param("message") String message);

   List selectOrderDetailByOrderNoList(@Param("list") Set orderNoSet);

   List selectByPerformListByPerformListNo(@Param("performListNo") String performListNo, @Param("list") List performListSortNumberList);

   int selectCountStatus(@Param("orderNo") String orderNo, @Param("performListNo") String performListNo, @Param("statusList") List statusList, @Param("list") List performListSortNumberList);

   List selectReturnDrugBillNoList(@Param("performListNo") String performListNo);

   List selectReturnDrugBillNoListByPerformListNo(@Param("list") Set performListNo);

   List selectTodayOrderOperationList(TodayOrderOperationReq req);
}
