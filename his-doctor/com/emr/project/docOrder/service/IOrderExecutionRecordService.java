package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.req.OrderExecutionRecordReq;
import com.emr.project.docOrder.domain.resp.OrderExecutionRecordResp;

public interface IOrderExecutionRecordService {
   OrderExecutionRecordResp getOrderExecutionRecord(OrderExecutionRecordReq req) throws Exception;
}
