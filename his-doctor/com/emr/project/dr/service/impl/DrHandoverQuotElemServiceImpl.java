package com.emr.project.dr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.dr.domain.DrHandoverQuotElem;
import com.emr.project.dr.mapper.DrHandoverQuotElemMapper;
import com.emr.project.dr.service.IDrHandoverQuotElemService;
import com.emr.project.system.domain.BasEmployee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrHandoverQuotElemServiceImpl implements IDrHandoverQuotElemService {
   @Autowired
   private DrHandoverQuotElemMapper drHandoverQuotElemMapper;

   public DrHandoverQuotElem selectDrHandoverQuotElemById(Long id) {
      return this.drHandoverQuotElemMapper.selectDrHandoverQuotElemById(id);
   }

   public List selectDrHandoverQuotElemList(DrHandoverQuotElem drHandoverQuotElem) {
      return this.drHandoverQuotElemMapper.selectDrHandoverQuotElemList(drHandoverQuotElem);
   }

   public int insertDrHandoverQuotElem(DrHandoverQuotElem drHandoverQuotElem) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverQuotElem.setId(SnowIdUtils.uniqueLong());
      drHandoverQuotElem.setCrePerCode(basEmployee.getEmplNumber());
      drHandoverQuotElem.setCrePerName(basEmployee.getEmplName());
      return this.drHandoverQuotElemMapper.insertDrHandoverQuotElem(drHandoverQuotElem);
   }

   public int updateDrHandoverQuotElem(DrHandoverQuotElem drHandoverQuotElem) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverQuotElem.setAltPerCode(basEmployee.getEmplNumber());
      drHandoverQuotElem.setAltPerName(basEmployee.getEmplName());
      return this.drHandoverQuotElemMapper.updateDrHandoverQuotElem(drHandoverQuotElem);
   }

   public int deleteDrHandoverQuotElemByIds(Long[] ids) {
      return this.drHandoverQuotElemMapper.deleteDrHandoverQuotElemByIds(ids);
   }

   public int deleteDrHandoverQuotElemById(Long id) {
      return this.drHandoverQuotElemMapper.deleteDrHandoverQuotElemById(id);
   }

   public List selectElemListByTypeCode(Long typeCode) throws Exception {
      return this.drHandoverQuotElemMapper.selectElemListByTypeCode(typeCode);
   }
}
