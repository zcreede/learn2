package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderStatusMapper {
   List selectTdPaOrderStatusById(String orderNo);

   List selectTdPaOrderStatusList(TdPaOrderStatus tdPaOrderStatus);

   int insertTdPaOrderStatus(TdPaOrderStatus tdPaOrderStatus);

   int updateTdPaOrderStatus(TdPaOrderStatus tdPaOrderStatus);

   int deleteTdPaOrderStatusById(String orderNo);

   int deleteTdPaOrderStatusByIds(String[] orderNos);

   List selectByOrderNoList(List orderNoList);

   void insertList(List list);

   void updateTdPaOrderStatusByOrderNoList(List orderNoList, @Param("operationType") String operationType, @Param("operationTypeName") String operationTypeName, @Param("currDate") Date currDate, @Param("operatorDesc") String operatorDesc);

   List selectByOperationTypes(@Param("patientId") String patientId);

   List selectByOrderNoListAndStatusList(List orderNoList, List operationTypeList);

   int insertSelective(TdPaOrderStatus record);

   Integer getOrderStatus(@Param("orderNo") String orderNo, @Param("orderSortNumber") String orderSortNumber);

   void updateItemTime(TdPaOrderItem inpatientOrderItem);

   void updateItemOrderStatus(@Param("orderNo") String orderNo, @Param("orderSortNumber") String orderSortNumber, @Param("orderGroupNo") String orderGroupNo, @Param("orderStatus") String orderStatus);

   void updateItemOrderCheckStatus(@Param("orderNo") String orderNo, @Param("orderSortNumber") String orderSortNumber, @Param("orderGroupNo") String orderGroupNo, @Param("orderStatus") String orderStatus);

   String selectOrderType(@Param("orderNo") String orderNo, @Param("orderSortNumber") String orderSortNumber, @Param("orderGroupNo") String orderGroupNo);
}
