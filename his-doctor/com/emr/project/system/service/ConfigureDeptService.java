package com.emr.project.system.service;

import com.emr.project.system.domain.ConfigureDept;

public interface ConfigureDeptService {
   ConfigureDept queryByConfigureCode(String configureCode, String hospitalCode, String depCode);

   String getConfigureValue(String configureCode, String hospitalCode, String depCode);

   Boolean selectDeptArrearsByDepCode(String patientDepCode);
}
