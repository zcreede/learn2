package com.emr.project.tmpa.mapper;

import com.emr.project.tmpa.domain.OrderFreq;
import java.util.List;

public interface OrderFreqMapper {
   OrderFreq selectOrderFreqById(String freqCd);

   List selectOrderFreqList(OrderFreq orderFreq);

   List selectUseTimeOrderFreqList(OrderFreq orderFreq);

   int insertOrderFreq(OrderFreq orderFreq);

   int updateOrderFreq(OrderFreq orderFreq);

   int deleteOrderFreqById(String hospitalCode);

   int deleteOrderFreqByIds(String[] hospitalCodes);

   List selectByFreqCdList(List freqCdList);
}
