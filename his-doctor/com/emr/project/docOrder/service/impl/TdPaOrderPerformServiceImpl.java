package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TdPaOrderPerform;
import com.emr.project.docOrder.mapper.TdPaOrderPerformMapper;
import com.emr.project.docOrder.service.ITdPaOrderPerformService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderPerformServiceImpl implements ITdPaOrderPerformService {
   @Autowired
   private TdPaOrderPerformMapper tdPaOrderPerformMapper;

   public void addList(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.tdPaOrderPerformMapper.insertList(list);
      }

   }

   public int getPerformStatus(String performListNo, int performListSortNumber) throws Exception {
      return this.tdPaOrderPerformMapper.getPerformStatus(performListNo, performListSortNumber);
   }

   public void updatePerformByOrderNoAndStauts(String orderNo, String resOrderStatus) throws Exception {
      this.tdPaOrderPerformMapper.updatePerformByOrderNoAndStauts(orderNo, resOrderStatus);
   }
}
