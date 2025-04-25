package com.emr.project.operation.service.impl;

import com.emr.project.operation.mapper.InpatientOrderMapper;
import com.emr.project.operation.service.InpatientOrderService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InpatientOrderServiceImpl implements InpatientOrderService {
   private static Logger log = LoggerFactory.getLogger(InpatientOrderServiceImpl.class);
   @Autowired
   private InpatientOrderMapper inpatientOrderMapper;

   public BigDecimal calculationPerformFeeNoPerform(String admissionNo) throws Exception {
      BigDecimal total = BigDecimal.ZERO;
      BigDecimal noMedicineAmount = this.inpatientOrderMapper.getNoMedicineAmount(admissionNo);
      BigDecimal notRecordedApply = this.inpatientOrderMapper.getNotRecordedApply(admissionNo);
      BigDecimal notSureApply = this.inpatientOrderMapper.getNotSureApply(admissionNo);
      if (noMedicineAmount == null) {
         noMedicineAmount = BigDecimal.ZERO;
      }

      if (notRecordedApply == null) {
         notRecordedApply = BigDecimal.ZERO;
      }

      if (notSureApply == null) {
         notSureApply = BigDecimal.ZERO;
      }

      total = total.add(noMedicineAmount).add(notRecordedApply).add(notSureApply);
      return total;
   }
}
