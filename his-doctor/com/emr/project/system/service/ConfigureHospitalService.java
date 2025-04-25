package com.emr.project.system.service;

import com.emr.project.system.domain.ConfigureHospital;

public interface ConfigureHospitalService {
   ConfigureHospital queryByConfigureCode(float configureCode, String hospitalCode);
}
