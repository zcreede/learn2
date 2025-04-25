package com.emr.project.operation.service.impl;

import com.emr.project.operation.domain.CumulativeCost;
import com.emr.project.operation.mapper.CumulativeCostMapper;
import com.emr.project.operation.service.ICumulativeCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CumulativeCostServiceImpl implements ICumulativeCostService {
   @Autowired
   private CumulativeCostMapper cumulativeCostMapper;

   public CumulativeCost queryByAdmissionNo(String admissionNo) throws Exception {
      return this.cumulativeCostMapper.selectByPrimaryKey(admissionNo);
   }

   public void updateCumulativeCost(CumulativeCost cumulativeCost) throws Exception {
      this.cumulativeCostMapper.updateByPrimaryKeySelective(cumulativeCost);
   }
}
