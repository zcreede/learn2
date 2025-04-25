package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TdPaOrderPerformFirst;
import com.emr.project.docOrder.mapper.TdPaOrderPerformFirstMapper;
import com.emr.project.docOrder.service.ITdPaOrderPerformFirstService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderPerformFirstServiceImpl implements ITdPaOrderPerformFirstService {
   @Autowired
   private TdPaOrderPerformFirstMapper tdPaOrderPerformFirstMapper;

   public TdPaOrderPerformFirst selectTdPaOrderPerformFirstById(Long id) {
      return this.tdPaOrderPerformFirstMapper.selectTdPaOrderPerformFirstById(id);
   }

   public List selectTdPaOrderPerformFirstList(TdPaOrderPerformFirst tdPaOrderPerformFirst) {
      return this.tdPaOrderPerformFirstMapper.selectTdPaOrderPerformFirstList(tdPaOrderPerformFirst);
   }

   public int insertTdPaOrderPerformFirst(TdPaOrderPerformFirst tdPaOrderPerformFirst) {
      return this.tdPaOrderPerformFirstMapper.insertTdPaOrderPerformFirst(tdPaOrderPerformFirst);
   }

   public int updateTdPaOrderPerformFirst(TdPaOrderPerformFirst tdPaOrderPerformFirst) {
      return this.tdPaOrderPerformFirstMapper.updateTdPaOrderPerformFirst(tdPaOrderPerformFirst);
   }

   public int deleteTdPaOrderPerformFirstByIds(Long[] ids) {
      return this.tdPaOrderPerformFirstMapper.deleteTdPaOrderPerformFirstByIds(ids);
   }

   public int deleteTdPaOrderPerformFirstById(Long id) {
      return this.tdPaOrderPerformFirstMapper.deleteTdPaOrderPerformFirstById(id);
   }

   public Map selectByOrderInfo(String orderNo, String orderSortNumber, Integer performListSortNumber, String admissionNo, int hospitalizedCount) throws Exception {
      Map<Integer, TdPaOrderPerformFirst> resMap = null;
      TdPaOrderPerformFirst param = new TdPaOrderPerformFirst();
      param.setOrderNo(orderNo);
      param.setOrderSortNumber(orderSortNumber);
      param.setPerformListSortNumber(performListSortNumber != null ? performListSortNumber.toString() : null);
      param.setAdmissionNo(admissionNo);
      param.setHospitalizedCount(hospitalizedCount);
      List<TdPaOrderPerformFirst> performFirstList = this.tdPaOrderPerformFirstMapper.selectTdPaOrderPerformFirstList(param);
      if (CollectionUtils.isNotEmpty(performFirstList)) {
         resMap = new HashMap(1);
         if (performListSortNumber != null && performListSortNumber > 0) {
            List<TdPaOrderPerformFirst> performFirstListTemp = (List)performFirstList.stream().filter((t) -> Integer.valueOf(t.getPerformListSortNumber()) == performListSortNumber).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(performFirstListTemp)) {
               resMap.put(performListSortNumber, performFirstListTemp.get(0));
            }
         } else {
            for(TdPaOrderPerformFirst performFirst : performFirstList) {
               resMap.put(Integer.valueOf(performFirst.getPerformListSortNumber()), performFirst);
            }
         }
      }

      return resMap;
   }
}
