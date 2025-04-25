package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.vo.MedicalinformationVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.PrintAgentVo;
import com.emr.project.docOrder.domain.vo.PrintOrderDataListDetailVo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderMapper {
   TdPaOrder selectTdPaOrderById(String orderNo);

   TdPaOrder selectTdPaOrderByApplyFormNo(String applyFormNo);

   List selectTdPaOrderList(TdPaOrder tdPaOrder);

   List selectTdPaOrderListByAdmissionNos(@Param("admissionNos") List admissionNos);

   List selectOrderSearchVoList(OrderSearchVo queryParam);

   List selectOrderSearchVoListStopOneDay(OrderSearchVo queryParam);

   List selectOrderSearchVoSubList(List mainOrderNoList, @Param("masterOrder") String masterOrder);

   List selectOrderNoList(OrderSearchVo queryParam);

   int insertTdPaOrder(TdPaOrder tdPaOrder);

   int updateTdPaOrder(TdPaOrder tdPaOrder);

   int deleteTdPaOrderById(String orderNo);

   int deleteTdPaOrderByIds(List orderNoList);

   void insertList(List list);

   List selectByOrderNoList(List orderNoList);

   void toDelTable(List list);

   List selectUnclaimedDrugList(@Param("patientId") String patientId, List orderNoList);

   List selectUnExecPerformList(@Param("patientId") String patientId, List orderNoList);

   List selectUnclaimedDrugListByPLNo(@Param("patientId") String patientId, List performListNoList);

   List selectDrugBBList(Map param);

   int checkCountPrintOrder(@Param("list") Set orderNoSet);

   int selectOrderAgentCount(@Param("orderNo") String orderNo, @Param("orderGroupNo") String orderGroupNo, @Param("orderGroupSortNumber") String orderGroupSortNumber, @Param("orderSortNumber") String orderSortNumber);

   MedicalinformationVo selectMedicalInfor(@Param("admissionNo") String admissionNo);

   PrintOrderDataListDetailVo selectOrderDetail(@Param("orderNo") String orderNo, @Param("orderGroupNo") String orderGroupNo, @Param("orderGroupSortNumber") String orderGroupSortNumber, @Param("orderSortNumber") String orderSortNumber);

   PrintAgentVo selectAgentData(@Param("orderNo") String orderNo, @Param("orderGroupNo") String orderGroupNo, @Param("orderGroupSortNumber") String orderGroupSortNumber, @Param("orderSortNumber") String orderSortNumber);

   TdPaOrder selectOrderByOrderNo(@Param("orderNo") String orderNo);
}
