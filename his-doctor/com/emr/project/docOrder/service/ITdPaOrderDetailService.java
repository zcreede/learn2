package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.vo.OrderStopDoReturnVo;
import com.emr.project.docOrder.domain.vo.OrderStopSignVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.pat.domain.Visitinfo;
import java.util.Date;
import java.util.List;

public interface ITdPaOrderDetailService {
   TdPaOrderDetail selectTdPaOrderDetailById(Long id);

   List selectTdPaOrderDetailList(TdPaOrderDetailVo tdPaOrderDetail);

   int insertTdPaOrderDetail(TdPaOrderDetail tdPaOrderDetail);

   int updateTdPaOrderDetail(TdPaOrderDetail tdPaOrderDetail);

   int deleteTdPaOrderDetailByIds(Long[] ids);

   int deleteTdPaOrderDetailById(Long id);

   void deleteTdPaOrderDetailByOrder(String orderNo) throws Exception;

   List selectOrderDetailListByPat(String patientId) throws Exception;

   void insertList(List list) throws Exception;

   List selectByOrderNoList(List orderNoList) throws Exception;

   List selectByOrderNo(String orderNo) throws Exception;

   void toDelTable(List list) throws Exception;

   void deleteTdPaOrderDetailByIds(List orderNoList) throws Exception;

   OrderStopDoReturnVo stopOrder(OrderStopSignVo orderStopSignVo, List orderItemList, String ip) throws Exception;

   OrderStopDoReturnVo stopAllOrder(Visitinfo visitinfo, OrderStopSignVo orderStopSignVo, List orderItemList, String ip) throws Exception;

   List getStopAllOrderList(Visitinfo visitinfo) throws Exception;

   List getStopAllOrderList(String patientId) throws Exception;

   void rearrange(Visitinfo visitinfo, List orderItemList, String ip) throws Exception;

   Integer getMaxReOrganizationNo(String patientId) throws Exception;

   List selectListByTime(Date orderStartTime, Date orderStartTimeEnd) throws Exception;

   List selectDetailEventCode(List orderNoList) throws Exception;

   void updaTeskinTestResults(String orderNo, String orderSortNumber, Date operatorDate, String skinTestResultsLot, String skinTestResults);

   OrderStopDoReturnVo orderCancelDo(OrderStopSignVo orderStopSignVo, List orderItemList, String ip) throws Exception;

   TdPaOrderDetail selectOrderDetail(String orderNo) throws Exception;

   List selectTdPaOrderDetailListByPatientId(TdPaOrderDetailVo tdPaOrderDetail) throws Exception;
}
