package com.emr.project.tmpa.service.impl;

import com.emr.project.tmpa.domain.OrderFreq;
import com.emr.project.tmpa.mapper.OrderFreqMapper;
import com.emr.project.tmpa.service.IOrderFreqService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFreqServiceImpl implements IOrderFreqService {
   @Autowired
   private OrderFreqMapper orderFreqMapper;

   public OrderFreq selectOrderFreqById(String freqCd) {
      return this.orderFreqMapper.selectOrderFreqById(freqCd);
   }

   public List selectOrderFreqList(OrderFreq orderFreq) {
      return this.orderFreqMapper.selectOrderFreqList(orderFreq);
   }

   public int insertOrderFreq(OrderFreq orderFreq) {
      return this.orderFreqMapper.insertOrderFreq(orderFreq);
   }

   public int updateOrderFreq(OrderFreq orderFreq) {
      return this.orderFreqMapper.updateOrderFreq(orderFreq);
   }

   public int deleteOrderFreqByIds(String[] hospitalCodes) {
      return this.orderFreqMapper.deleteOrderFreqByIds(hospitalCodes);
   }

   public int deleteOrderFreqById(String hospitalCode) {
      return this.orderFreqMapper.deleteOrderFreqById(hospitalCode);
   }

   public List selectByFreqCdList(List freqCdList) throws Exception {
      List<OrderFreq> list = new ArrayList(1);
      if (freqCdList != null && !freqCdList.isEmpty()) {
         list = this.orderFreqMapper.selectByFreqCdList(freqCdList);
      }

      return list;
   }

   public List selectUseTimeOrderFreqList(OrderFreq orderFreq) throws Exception {
      return this.orderFreqMapper.selectUseTimeOrderFreqList(orderFreq);
   }
}
