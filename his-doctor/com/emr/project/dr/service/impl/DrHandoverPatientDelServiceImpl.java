package com.emr.project.dr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.project.dr.domain.DrHandoverPatientDel;
import com.emr.project.dr.mapper.DrHandoverPatientDelMapper;
import com.emr.project.dr.service.IDrHandoverPatientDelService;
import com.emr.project.system.domain.BasEmployee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrHandoverPatientDelServiceImpl implements IDrHandoverPatientDelService {
   @Autowired
   private DrHandoverPatientDelMapper drHandoverPatientDelMapper;

   public DrHandoverPatientDel selectDrHandoverPatientDelById(Long id) {
      return this.drHandoverPatientDelMapper.selectDrHandoverPatientDelById(id);
   }

   public List selectDrHandoverPatientDelList(DrHandoverPatientDel drHandoverPatientDel) {
      return this.drHandoverPatientDelMapper.selectDrHandoverPatientDelList(drHandoverPatientDel);
   }

   public int insertDrHandoverPatientDel(DrHandoverPatientDel drHandoverPatientDel) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverPatientDel.setCrePerCode(basEmployee.getEmplNumber());
      drHandoverPatientDel.setCrePerName(basEmployee.getEmplName());
      return this.drHandoverPatientDelMapper.insertDrHandoverPatientDel(drHandoverPatientDel);
   }

   public int updateDrHandoverPatientDel(DrHandoverPatientDel drHandoverPatientDel) {
      return this.drHandoverPatientDelMapper.updateDrHandoverPatientDel(drHandoverPatientDel);
   }

   public int deleteDrHandoverPatientDelByIds(Long[] ids) {
      return this.drHandoverPatientDelMapper.deleteDrHandoverPatientDelByIds(ids);
   }

   public int deleteDrHandoverPatientDelById(Long id) {
      return this.drHandoverPatientDelMapper.deleteDrHandoverPatientDelById(id);
   }
}
