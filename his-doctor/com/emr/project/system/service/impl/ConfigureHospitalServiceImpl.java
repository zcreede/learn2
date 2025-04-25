package com.emr.project.system.service.impl;

import com.emr.project.system.domain.ConfigureHospital;
import com.emr.project.system.mapper.ConfigureHospitalMapper;
import com.emr.project.system.service.ConfigureHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigureHospitalServiceImpl implements ConfigureHospitalService {
   @Autowired
   private ConfigureHospitalMapper configureHospitalMapper;

   public ConfigureHospital queryByConfigureCode(float configureCode, String hospitalCode) {
      ConfigureHospital param = new ConfigureHospital();
      param.setConfigureCode(configureCode);
      param.setHospitalCode(hospitalCode);
      ConfigureHospital temp = this.configureHospitalMapper.selectByConn(param);
      return temp;
   }
}
