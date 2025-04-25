package com.emr.project.docOrder.service;

public interface ITdPaOrderOperationService {
   void insert(String orderNo, String orderSortNumber, String orderGroupNo) throws Exception;
}
