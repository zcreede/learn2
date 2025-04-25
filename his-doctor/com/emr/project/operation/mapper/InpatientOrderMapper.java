package com.emr.project.operation.mapper;

import java.math.BigDecimal;

public interface InpatientOrderMapper {
   BigDecimal getNoMedicineAmount(String admissionNo);

   BigDecimal getNotRecordedApply(String admissionNo);

   BigDecimal getNotSureApply(String admissionNo);
}
