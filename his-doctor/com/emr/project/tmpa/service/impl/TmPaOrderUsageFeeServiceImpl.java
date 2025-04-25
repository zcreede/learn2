package com.emr.project.tmpa.service.impl;

import com.emr.project.tmpa.domain.TmPaOrderUsageFee;
import com.emr.project.tmpa.mapper.TmPaOrderUsageFeeMapper;
import com.emr.project.tmpa.service.ITmPaOrderUsageFeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmPaOrderUsageFeeServiceImpl implements ITmPaOrderUsageFeeService {
   @Autowired
   private TmPaOrderUsageFeeMapper tmPaOrderUsageFeeMapper;

   public TmPaOrderUsageFee selectTmPaOrderUsageFeeById(Long no) {
      return this.tmPaOrderUsageFeeMapper.selectTmPaOrderUsageFeeById(no);
   }

   public List selectTmPaOrderUsageFeeList(TmPaOrderUsageFee tmPaOrderUsageFee) {
      return this.tmPaOrderUsageFeeMapper.selectTmPaOrderUsageFeeList(tmPaOrderUsageFee);
   }

   public int insertTmPaOrderUsageFee(TmPaOrderUsageFee tmPaOrderUsageFee) {
      return this.tmPaOrderUsageFeeMapper.insertTmPaOrderUsageFee(tmPaOrderUsageFee);
   }

   public int updateTmPaOrderUsageFee(TmPaOrderUsageFee tmPaOrderUsageFee) {
      return this.tmPaOrderUsageFeeMapper.updateTmPaOrderUsageFee(tmPaOrderUsageFee);
   }

   public int deleteTmPaOrderUsageFeeByIds(Long[] nos) {
      return this.tmPaOrderUsageFeeMapper.deleteTmPaOrderUsageFeeByIds(nos);
   }

   public int deleteTmPaOrderUsageFeeById(Long no) {
      return this.tmPaOrderUsageFeeMapper.deleteTmPaOrderUsageFeeById(no);
   }
}
