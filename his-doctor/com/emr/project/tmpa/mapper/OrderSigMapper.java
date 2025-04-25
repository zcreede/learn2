package com.emr.project.tmpa.mapper;

import com.emr.project.tmpa.domain.OrderSig;
import java.util.List;

public interface OrderSigMapper {
   OrderSig selectOrderSigById(String sigCd);

   List selectOrderSigList(OrderSig orderSig);

   List selectUseTimeOrderSigList(OrderSig orderSig);

   int insertOrderSig(OrderSig orderSig);

   int updateOrderSig(OrderSig orderSig);

   int deleteOrderSigById(String sigCd);

   int deleteOrderSigByIds(String[] sigCds);

   List selectOrderSigListBySigCd(List sigCdList);

   List selectOrderSigStandList();
}
