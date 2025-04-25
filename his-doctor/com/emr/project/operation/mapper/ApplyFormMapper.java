package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.ApplyForm;
import org.apache.ibatis.annotations.Param;

public interface ApplyFormMapper {
   ApplyForm searchApplyForm(@Param("OrderNo") String OrderNo, @Param("orderSortNumber") String orderSortNumber);

   void updateStatusByOrderNoAndOrderSortNumber(@Param("OrderNo") String OrderNo, @Param("orderSortNumber") String orderSortNumber, @Param("applyFormStatus") String applyFormStatus);

   void updateItemStatusByOrderNoAndOrderSortNumber(@Param("applyFormNo") String applyFormNo, @Param("orderGroupNo") String orderGroupNo, @Param("cope_sort_number") String cope_sort_number, @Param("applyFormStatus") String applyFormStatus);

   void updatesqd(@Param("admission_no") String admission_no, @Param("applyFormNo") String applyFormNo, @Param("applyFormStatus") String applyFormStatus);

   int updateByPrimaryKeySelective(ApplyForm record);
}
