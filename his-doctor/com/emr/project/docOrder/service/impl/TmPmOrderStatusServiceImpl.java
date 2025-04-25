package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TmPmOrderStatus;
import com.emr.project.docOrder.mapper.TmPmOrderStatusMapper;
import com.emr.project.docOrder.service.ITmPmOrderStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmPmOrderStatusServiceImpl implements ITmPmOrderStatusService {
   @Autowired
   private TmPmOrderStatusMapper tmPmOrderStatusMapper;

   public TmPmOrderStatus selectTmPmOrderStatusById(String codeNo) {
      return this.tmPmOrderStatusMapper.selectTmPmOrderStatusById(codeNo);
   }

   public List selectTmPmOrderStatusList(TmPmOrderStatus tmPmOrderStatus) {
      return this.tmPmOrderStatusMapper.selectTmPmOrderStatusList(tmPmOrderStatus);
   }

   public int insertTmPmOrderStatus(TmPmOrderStatus tmPmOrderStatus) {
      return this.tmPmOrderStatusMapper.insertTmPmOrderStatus(tmPmOrderStatus);
   }

   public int updateTmPmOrderStatus(TmPmOrderStatus tmPmOrderStatus) {
      return this.tmPmOrderStatusMapper.updateTmPmOrderStatus(tmPmOrderStatus);
   }

   public int deleteTmPmOrderStatusByIds(String[] codeNos) {
      return this.tmPmOrderStatusMapper.deleteTmPmOrderStatusByIds(codeNos);
   }

   public int deleteTmPmOrderStatusById(String codeNo) {
      return this.tmPmOrderStatusMapper.deleteTmPmOrderStatusById(codeNo);
   }
}
