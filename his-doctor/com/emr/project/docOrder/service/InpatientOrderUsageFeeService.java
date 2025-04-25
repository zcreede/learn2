package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.InpatientOrderUsageFee;
import java.util.List;

public interface InpatientOrderUsageFeeService {
   InpatientOrderUsageFee queryByUsageFirstFlagWardNo(String yongfaBh, String firstFlag, String wardNo, String crbz);

   List selectListByConn(InpatientOrderUsageFee record);

   List selectOrderUsageFeeVoAll() throws Exception;
}
