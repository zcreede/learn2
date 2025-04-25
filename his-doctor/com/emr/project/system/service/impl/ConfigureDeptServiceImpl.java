package com.emr.project.system.service.impl;

import com.emr.project.system.domain.ConfigureDept;
import com.emr.project.system.domain.ConfigureHospital;
import com.emr.project.system.mapper.ConfigureDeptMapper;
import com.emr.project.system.service.ConfigureDeptService;
import com.emr.project.system.service.ConfigureHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigureDeptServiceImpl implements ConfigureDeptService {
   @Autowired
   private ConfigureDeptMapper configureDeptMapper;
   @Autowired
   private ConfigureHospitalService configureHospitalService;

   public ConfigureDept queryByConfigureCode(String configureCode, String hospitalCode, String depCode) {
      ConfigureDept param = new ConfigureDept();
      param.setConfigureCode(configureCode);
      param.setHospitalCode(hospitalCode);
      param.setDepCode(depCode);
      ConfigureDept temp = this.configureDeptMapper.selectByConn(param);
      return temp;
   }

   public String getConfigureValue(String configureCode, String hospitalCode, String depCode) {
      String res = null;
      ConfigureDept configureDept = this.queryByConfigureCode(configureCode, hospitalCode, depCode);
      if (configureDept == null) {
         ConfigureHospital configureHospital = this.configureHospitalService.queryByConfigureCode(Float.parseFloat(configureCode), hospitalCode);
         if (configureHospital != null) {
            res = configureHospital.getConfigureValue();
         }
      } else {
         res = configureDept.getConfigureValue();
      }

      return res;
   }

   public Boolean selectDeptArrearsByDepCode(String patientDepCode) {
      Boolean flag = Boolean.TRUE;
      String arrearsFlag = this.configureDeptMapper.selectArrearsByDeptCode(patientDepCode);
      return "1".equals(arrearsFlag) ? Boolean.FALSE : flag;
   }
}
