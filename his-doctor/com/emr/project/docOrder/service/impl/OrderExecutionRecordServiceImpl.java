package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.req.OrderExecutionRecordReq;
import com.emr.project.docOrder.domain.resp.OrderExecutionDetail;
import com.emr.project.docOrder.domain.resp.OrderExecutionExpenseDetail;
import com.emr.project.docOrder.domain.resp.OrderExecutionItemDetail;
import com.emr.project.docOrder.domain.resp.OrderExecutionPerformDetail;
import com.emr.project.docOrder.domain.resp.OrderExecutionRecordResp;
import com.emr.project.docOrder.mapper.OrderExecutionRecordMapper;
import com.emr.project.docOrder.service.IOrderExecutionRecordService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderExecutionRecordServiceImpl implements IOrderExecutionRecordService {
   @Autowired
   private OrderExecutionRecordMapper orderExecutionRecordMapper;

   public OrderExecutionRecordResp getOrderExecutionRecord(OrderExecutionRecordReq req) throws Exception {
      OrderExecutionRecordResp resp = new OrderExecutionRecordResp();
      String orderNo = req.getOrderNo();
      String admissionNo = req.getAdmissionNo();
      OrderExecutionItemDetail detail = this.orderExecutionRecordMapper.selectOrderDetail(orderNo, admissionNo);
      resp.setExecutionDetail(detail);
      List<OrderExecutionDetail> detailList = this.orderExecutionRecordMapper.selectOrderDetailList(orderNo);
      resp.setDetailList(detailList);
      List<OrderExecutionPerformDetail> dataList = this.orderExecutionRecordMapper.selectPerformDetailList(admissionNo, orderNo);

      for(OrderExecutionPerformDetail performDetail : dataList) {
         String performListStatus = performDetail.getPerformListStatus();
         if ("0".equals(performListStatus)) {
            performDetail.setPerformListStatusStr("未执行");
         } else if ("1".equals(performListStatus)) {
            performDetail.setPerformListStatusStr("已执行");
         } else if ("2".equals(performListStatus)) {
            performDetail.setPerformListStatusStr("作废");
         } else if ("3".equals(performListStatus)) {
            performDetail.setPerformListStatusStr("库存不足");
         } else if ("4".equals(performListStatus)) {
            performDetail.setPerformListStatusStr("欠费");
         }
      }

      resp.setPerformDetailList(dataList);
      List<OrderExecutionExpenseDetail> expenseDetailList = this.orderExecutionRecordMapper.selectExpenseDetailList(admissionNo, orderNo, (String)null);
      BigDecimal reduce = (BigDecimal)expenseDetailList.stream().map(OrderExecutionExpenseDetail::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
      resp.setExpenseDetailList(expenseDetailList);
      resp.setTotal(reduce);
      return resp;
   }
}
