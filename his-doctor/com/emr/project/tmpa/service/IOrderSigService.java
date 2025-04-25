package com.emr.project.tmpa.service;

import com.emr.project.tmpa.domain.OrderSig;
import java.util.List;

public interface IOrderSigService {
   OrderSig selectOrderSigById(String sigCd);

   List selectOrderSigList(OrderSig orderSig);

   int insertOrderSig(OrderSig orderSig);

   int updateOrderSig(OrderSig orderSig);

   int deleteOrderSigByIds(String[] sigCds);

   int deleteOrderSigById(String sigCd);

   List selectOrderSigListBySigCd(List sigCdList) throws Exception;

   List selectUseTimeOrderSigList(OrderSig orderSig) throws Exception;

   List selectOrderSigStandList() throws Exception;
}
