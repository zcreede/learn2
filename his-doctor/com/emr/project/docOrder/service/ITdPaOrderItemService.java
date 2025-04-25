package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.DrugAndClinPatientVo;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.emr.project.docOrder.domain.vo.ItemIsOpenResVo;
import com.emr.project.docOrder.domain.vo.ItemListIsOpenResVo;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.TdCaOperationApplyVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITdPaOrderItemService {
   TdPaOrderItem selectTdPaOrderItemById(String orderNo);

   List selectTdPaOrderItemList(TdPaOrderItemVo tdPaOrderItem) throws Exception;

   List selectOrderHerbList(TdPaOrderItemVo tdPaOrderItem) throws Exception;

   int insertTdPaOrderItem(TdPaOrderItem tdPaOrderItem);

   int updateTdPaOrderItem(TdPaOrderItem tdPaOrderItem);

   int deleteTdPaOrderItemByIds(String[] orderNos);

   int deleteTdPaOrderItemById(String orderNo);

   List selectByOrderNoList(List orderNoList) throws Exception;

   List selectFromStatusByOrderNoList(List orderNoList);

   void updateOrderStatus(TdPaOrderItemVo param) throws Exception;

   void updateCancelCommit(TdPaOrderItemVo orderItemVo);

   void updateOrderStatusList(List orderItemVoList) throws Exception;

   ItemIsOpenResVo selectItemIsOpen(DrugAndClinPatientVo drugAndClinPatientVo) throws Exception;

   ItemIsOpenResVo selectHrefItemIsOpen(String patientId) throws Exception;

   ItemListIsOpenResVo selectItemIsOpenList(String patientId, List orderSearchVoList, Date orderStartTime) throws Exception;

   List selectItemStatusFlow(String orderNo) throws Exception;

   void insertList(List list) throws Exception;

   Integer selectPatientMaxGroupNo(String patientId) throws Exception;

   List insertOrderAndCheckList(VisitinfoPersonalVo personalVo, List applyList, List applyItemList, List applyDetailtList, List clinList, Map applyFormDetailMap, OrderCommitVo orderCommitVo) throws Exception;

   void insertOrderFromTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo) throws Exception;

   void insertOrderFromTdCaOperationApply(TdCaOperationApplyVo tdCaOperationApply, OrderCommitVo orderCommitVo) throws Exception;

   void toDelTable(List list) throws Exception;

   void deleteTdPaOrderItemByIds(List orderNoList) throws Exception;

   TdPaOrderItemVo selectOrderInfoByOrderNo(String orderNo) throws Exception;

   List saveHerbOrder(HerbSaveVo herbSaveVo) throws Exception;

   void saveSubmitHerbOrder(OrderCommitVo orderCommitVo, List orderItemList, Visitinfo visitinfo) throws Exception;

   List updateHerbOrder(HerbSaveVo herbSaveVo) throws Exception;

   List selectByOrderFlagCdAndStatus(String patientId, String orderStatus, List orderFlagCdList) throws Exception;

   List selectByStatusList(TdPaOrderItemVo tdPaOrderItemVo) throws Exception;

   List selectByOrderTypeAndStatus(TdPaOrderItemVo param);

   List selectPastOrderListByPatList(List patientIdList) throws Exception;

   String herbSignText(String signType, String patientId, List orderNoList, Map orderStopMap, Date currDate) throws Exception;

   String orderSignText(String signType, String patientId, List orderNoList, Map orderStopMap, Date currDate) throws Exception;

   void stopUnclaimedDrugList(List takeDrugList);

   void stopUnExecPerformList(List orderPerformList);

   List selectStopAllOrderList(String patientId) throws Exception;

   List isStockNum(List orderPerformList, String detailPerformDepCode) throws Exception;

   List selectCpNoDayOrderList(TdPaOrderItemVo tdPaOrderItemVo) throws Exception;

   List selectTdPaOrderItemListAll(TdPaOrderItem tdPaOrderItem) throws Exception;

   void updateTdPaOrderItemByTableName(TdPaOrderItemVo tdPaOrderItemVo) throws Exception;

   void stopPerformList(List orderPerformList, String userName, String nickName) throws Exception;

   List selectPatientDoingNursingLevel(String patientId) throws Exception;

   List selectNursingLevelChargeNoList() throws Exception;

   List selectPatOrderForDrugCodes(String patientId, String beginTime, String endTime, String drugCode) throws Exception;

   List selectPatientOutOrder(TdPaOrderItem param) throws Exception;

   List selectPatientOutOrder(TdPaOrderItem param, List statusList) throws Exception;

   List selectOrderItemAll(TdPaOrderItemVo orderItemVo) throws Exception;

   Integer selectCultureCount(String patientId);

   Integer selectCultureCountBySubmitDate(String patientId, Date orderStartTime);
}
