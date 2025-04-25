package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TdPaOrderOperation;
import com.emr.project.docOrder.mapper.TdPaOrderOperationMapper;
import com.emr.project.docOrder.service.ITdPaOrderOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderOperationServiceImpl implements ITdPaOrderOperationService {
   @Autowired
   private TdPaOrderOperationMapper tdPaOrderOperationMapper;

   public void insert(String orderNo, String orderSortNumber, String orderGroupNo) throws Exception {
      TdPaOrderOperation record = new TdPaOrderOperation();
      record.setOrderNo(orderNo);
      record.setOrderSortNumber(orderSortNumber);
      record.setOrderGroupNo(orderGroupNo);
      record.setValid(1);
      this.tdPaOrderOperationMapper.insertTdPaOrderOperation(record);
   }
}
