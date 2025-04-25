package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderDetailMapper {
   TdPaOrderDetail selectTdPaOrderDetailById(Long id);

   List selectTdPaOrderDetailList(TdPaOrderDetailVo tdPaOrderDetail);

   int insertTdPaOrderDetail(TdPaOrderDetail tdPaOrderDetail);

   int updateTdPaOrderDetail(TdPaOrderDetail tdPaOrderDetail);

   int deleteTdPaOrderDetailById(Long id);

   int deleteTdPaOrderDetailByIds(Long[] ids);

   void deleteTdPaOrderDetailByOrder(String orderNo);

   List selectOrderDetailListByPat(String patientId);

   void insertList(List list);

   List selectByOrderNoList(List orderNoList);

   List selectByOrderNo(String orderNo);

   void toDelTable(List list);

   void deleteTdPaOrderDetailByOrderNos(List orderNoList);

   Integer selectMaxReOrganizationNo(String patientId);

   List selectListByTime(Date orderStartTime, Date orderStartTimeEnd);

   List selectDetailEventCode(List orderNoList);

   void updateTdPaOrderPerformList(List performList);

   void updateTdPaOrderPerformDetailList(List performDetailList);

   void drugUpdateList(List drugUpdateList);

   String queryExecutorDptNo(@Param("orderNo") String orderNo, @Param("orderGroupNo") String orderGroupNo, @Param("orderSortNumber") String orderSortNumber);

   List selectByCpNoList(@Param("list") List list);

   void updaTeskinTestResults(@Param("orderNo") String orderNo, @Param("orderSortNumber") String orderSortNumber, @Param("operatorDate") Date operatorDate, @Param("batchNumber") String batchNumber, @Param("allergyType") String allergyType);

   TdPaOrderDetail selectOrderDetail(@Param("orderNo") String orderNo);

   List selectTdPaOrderDetailListByPatientId(TdPaOrderDetailVo tdPaOrderDetail);
}
