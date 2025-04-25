package com.emr.project.tmpa.service.impl;

import com.emr.project.tmpa.domain.OrderSig;
import com.emr.project.tmpa.mapper.OrderSigMapper;
import com.emr.project.tmpa.service.IOrderSigService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSigServiceImpl implements IOrderSigService {
   @Autowired
   private OrderSigMapper orderSigMapper;

   public OrderSig selectOrderSigById(String sigCd) {
      return this.orderSigMapper.selectOrderSigById(sigCd);
   }

   public List selectOrderSigList(OrderSig orderSig) {
      return this.orderSigMapper.selectOrderSigList(orderSig);
   }

   public int insertOrderSig(OrderSig orderSig) {
      return this.orderSigMapper.insertOrderSig(orderSig);
   }

   public int updateOrderSig(OrderSig orderSig) {
      return this.orderSigMapper.updateOrderSig(orderSig);
   }

   public int deleteOrderSigByIds(String[] sigCds) {
      return this.orderSigMapper.deleteOrderSigByIds(sigCds);
   }

   public int deleteOrderSigById(String sigCd) {
      return this.orderSigMapper.deleteOrderSigById(sigCd);
   }

   public List selectOrderSigListBySigCd(List sigCdList) throws Exception {
      List<OrderSig> list = new ArrayList(1);
      if (sigCdList != null && !sigCdList.isEmpty()) {
         list = this.orderSigMapper.selectOrderSigListBySigCd(sigCdList);
      }

      return list;
   }

   public List selectUseTimeOrderSigList(OrderSig orderSig) throws Exception {
      return this.orderSigMapper.selectUseTimeOrderSigList(orderSig);
   }

   public List selectOrderSigStandList() throws Exception {
      return this.orderSigMapper.selectOrderSigStandList();
   }
}
