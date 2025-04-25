package com.emr.project.operation.service;

import java.math.BigDecimal;

public interface InpatientOrderService {
   BigDecimal calculationPerformFeeNoPerform(String admissionNo) throws Exception;
}
