package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.InpatientOrderUsageFee;
import com.emr.project.docOrder.mapper.InpatientOrderUsageFeeMapper;
import com.emr.project.docOrder.service.InpatientOrderUsageFeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InpatientOrderUsageFeeServiceImpl implements InpatientOrderUsageFeeService {
   @Autowired
   private InpatientOrderUsageFeeMapper inpatientOrderUsageFeeMapper;

   public InpatientOrderUsageFee queryByUsageFirstFlagWardNo(String yongfaBh, String firstFlag, String wardNo, String crbz) {
      InpatientOrderUsageFee param = new InpatientOrderUsageFee(yongfaBh, wardNo, crbz, firstFlag);
      return this.inpatientOrderUsageFeeMapper.selectByConn(param);
   }

   public List selectListByConn(InpatientOrderUsageFee record) {
      return this.inpatientOrderUsageFeeMapper.selectListByConn(record);
   }

   public List selectOrderUsageFeeVoAll() throws Exception {
      return this.inpatientOrderUsageFeeMapper.selectOrderUsageFeeVoAll();
   }
}
