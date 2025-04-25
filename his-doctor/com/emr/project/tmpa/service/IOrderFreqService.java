package com.emr.project.tmpa.service;

import com.emr.project.tmpa.domain.OrderFreq;
import java.util.List;

public interface IOrderFreqService {
   OrderFreq selectOrderFreqById(String freqCd);

   List selectOrderFreqList(OrderFreq orderFreq);

   int insertOrderFreq(OrderFreq orderFreq);

   int updateOrderFreq(OrderFreq orderFreq);

   int deleteOrderFreqByIds(String[] hospitalCodes);

   int deleteOrderFreqById(String hospitalCode);

   List selectByFreqCdList(List freqCdList) throws Exception;

   List selectUseTimeOrderFreqList(OrderFreq orderFreq) throws Exception;
}
