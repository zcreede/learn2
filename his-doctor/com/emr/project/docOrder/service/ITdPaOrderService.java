package com.emr.project.docOrder.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.req.PrintOrderDataListReq;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSaveResVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderAgentVo;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.pat.domain.Visitinfo;
import java.util.List;
import java.util.Map;

public interface ITdPaOrderService {
   TdPaOrder selectTdPaOrderById(String orderNo);

   TdPaOrder selectTdPaOrderByApplyFormNo(String applyFormNo) throws Exception;

   List selectTdPaOrderList(TdPaOrder tdPaOrder);

   List selectOrderSearchVoList(OrderSearchVo queryParam) throws Exception;

   List selectOrderSearchVoListStopOneDay(OrderSearchVo queryParam) throws Exception;

   List selectSubOrderSearchVoList(List listMain) throws Exception;

   int insertTdPaOrder(TdPaOrder tdPaOrder);

   int updateTdPaOrder(TdPaOrder tdPaOrder);

   List deleteTdPaOrderByIds(List orderNoList, List applyFormList, List operationApplyList, List caConsApplyList) throws Exception;

   int deleteTdPaOrderById(String orderNo);

   List saveTdPaOrder(List orderSaveVoList, OrderSaveResVo orderSaveResVo, List drugCheckList, TdPaOrderAgentVo tdPaOrderAgentVo, Visitinfo visitinfo, String ip, String operatingRoomFlag) throws Exception;

   void commitTdPaOrders(OrderCommitVo orderCommitVo, List orderItemList, Visitinfo visitinfo) throws Exception;

   void cancelCommitTdPaOrders(OrderCommitVo orderCommitVo, List orderItemList, Visitinfo visitinfo, String operatorDesc, List applyFormList, List operationApplyList, List caConsApplyList) throws Exception;

   Map selectOrderDictList(String patientId) throws Exception;

   void cancelDoctorOrder(List orderItemList, Visitinfo visitinfo, OrderCommitVo orderCommitVo) throws Exception;

   List selectByOrderNoList(List orderNoList) throws Exception;

   List selectPastHerbTreeList(String patientId) throws Exception;

   void insertList(List list) throws Exception;

   List selectUnclaimedDrugList(String patientId, List orderNoList) throws Exception;

   List selectUnExecPerformList(String patientId, List orderNoList) throws Exception;

   Map selectDrugBBList(String inpNo) throws Exception;

   AjaxResult checkPrintOrder(List detailList, AjaxResult ajaxResult) throws Exception;

   Map printOrderData(PrintOrderDataListReq req) throws Exception;

   String isStockNum(List orderSaveVoList) throws Exception;

   String verifyExpensedetailByOrderNo(List orderNoList) throws Exception;

   String isDrugStockNum(List list) throws Exception;

   DrugDoseVo getDrugDoseToday(TdPaOrderItem orderItem, TdPaOrderDetail orderDetail) throws Exception;
}
