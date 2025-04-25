package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TdPaOrderPerformDetail;
import com.emr.project.docOrder.mapper.TdPaOrderPerformDetailMapper;
import com.emr.project.docOrder.service.ITdPaOrderPerformDetailService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderPerformDetailServiceImpl implements ITdPaOrderPerformDetailService {
   @Autowired
   private TdPaOrderPerformDetailMapper tdPaOrderPerformDetailMapper;

   public void addList(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.tdPaOrderPerformDetailMapper.insertList(list);
      }

   }

   public List selectPerformDetailList(List list) throws Exception {
      List<TdPaOrderPerformDetail> resList = null;
      if (CollectionUtils.isNotEmpty(list)) {
         resList = this.tdPaOrderPerformDetailMapper.selectPerformDetailList(list);
      }

      return resList;
   }
}
