package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderPerformFirst;
import java.util.List;
import java.util.Map;

public interface ITdPaOrderPerformFirstService {
   TdPaOrderPerformFirst selectTdPaOrderPerformFirstById(Long id);

   List selectTdPaOrderPerformFirstList(TdPaOrderPerformFirst tdPaOrderPerformFirst);

   int insertTdPaOrderPerformFirst(TdPaOrderPerformFirst tdPaOrderPerformFirst);

   int updateTdPaOrderPerformFirst(TdPaOrderPerformFirst tdPaOrderPerformFirst);

   int deleteTdPaOrderPerformFirstByIds(Long[] ids);

   int deleteTdPaOrderPerformFirstById(Long id);

   Map selectByOrderInfo(String orderNo, String orderSortNumber, Integer performListSortNumber, String admissionNo, int hospitalizedCount) throws Exception;
}
