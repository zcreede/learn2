package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderItemMapper {
   List selectTdPaOrderItemById(String orderNo);

   List selectTdPaOrderItemList(TdPaOrderItemVo tdPaOrderItem);

   List selectOrderHerbList(TdPaOrderItemVo tdPaOrderItem);

   int insertTdPaOrderItem(TdPaOrderItem tdPaOrderItem);

   int updateTdPaOrderItem(TdPaOrderItem tdPaOrderItem);

   int deleteTdPaOrderItemById(String orderNo);

   int deleteTdPaOrderItemByIds(String[] orderNos);

   List selectByOrderNoList(List orderNoList);

   List selectFromStatusByOrderNoList(List orderNoList);

   void updateOrderStatus(TdPaOrderItemVo orderItemVo);

   void updateCancelCommit(TdPaOrderItemVo orderItemVo);

   void updateOrderStatusList(List orderItemVoList);

   void insertList(List list);

   Integer selectPatientMaxGroupNo(@Param("patientId") String patientId);

   void toDelTable(List list);

   void deleteTdPaOrderItemByOrderNos(List orderNoList);

   List selectByOrderFlagCdAndStatus(@Param("patientId") String patientId, @Param("orderStatus") String orderStatus, List orderFlagCdList);

   List selectByStatusList(TdPaOrderItemVo tdPaOrderItemVo);

   List selectByOrderTypeAndStatus(TdPaOrderItemVo param);

   List selectPastOrderListByPatList(List patientIdList);

   List selectPatientOutOrder(TdPaOrderItem param, List statusList);

   void stopUnclaimedDrugList(List takeDrugList);

   void stopUnExecPerformList(List orderPerformList);

   void deleteUnclaimedDrugByPerFormNos(List orderNoList);

   List selectByPatientIdAndDrugCode72hExe(@Param("patientId") String patientId, @Param("drugCode") String drugCode);

   List selectStopAllOrderList(String patientId);

   List selectCpNoDayOrderList(TdPaOrderItemVo tdPaOrderItemVo);

   TdPaOrderItem selectByParam(@Param("orderNo") String orderNo, @Param("orderSortNumber") String orderSortNumber, @Param("orderGroupNo") String orderGroupNo);

   List selectTdPaOrderItemListAll(TdPaOrderItem tdPaOrderItem);

   void updateTdPaOrderItemByTableName(TdPaOrderItemVo tdPaOrderItemVo);

   void stopPerformList(@Param("orderPerformList") List orderPerformList, @Param("userName") String userName, @Param("nickName") String nickName);

   List selectPatientDoingNursingLevel(@Param("patientId") String patientId, @Param("orderFlagCd") String orderFlagCd, @Param("nlChargeNoList") List nlChargeNoList);

   List selectNursingLevelChargeNoList();

   List selectPatOrderForDrugCodes(@Param("patientId") String patientId, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("drugCode") String drugCode);

   List selectOrderItemAll(TdPaOrderItemVo orderItemVo);

   List selectOrderItemByOrderNoList(@Param("list") List orderNoList);

   void updateExecTimeByOrderNoList(@Param("list") List orderNoList, @Param("executionTime") Date executionTime);

   Integer selectCultureCount(@Param("patientId") String patientId);

   Integer selectCultureCountBySubmitDate(@Param("patientId") String patientId, @Param("orderStartTime") Date orderStartTime);
}
