package com.emr.project.dr.service.impl;

import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.dr.domain.DrHandoverDetail;
import com.emr.project.dr.domain.DrHandoverMain;
import com.emr.project.dr.domain.DrHandoverPatientType;
import com.emr.project.dr.domain.vo.DrHandoverPatientTypeVo;
import com.emr.project.dr.mapper.DrHandoverPatientTypeMapper;
import com.emr.project.dr.service.IDrHandoverPatientTypeService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrHandoverPatientTypeServiceImpl implements IDrHandoverPatientTypeService {
   @Autowired
   private DrHandoverPatientTypeMapper drHandoverPatientTypeMapper;

   public DrHandoverPatientType selectDrHandoverPatientTypeById(Long typeCode) {
      return this.drHandoverPatientTypeMapper.selectDrHandoverPatientTypeById(typeCode);
   }

   public List selectDrHandoverPatientTypeList(DrHandoverPatientType drHandoverPatientType) {
      return this.drHandoverPatientTypeMapper.selectDrHandoverPatientTypeList(drHandoverPatientType);
   }

   public int insertDrHandoverPatientType(DrHandoverPatientType drHandoverPatientType) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverPatientType.setInputstrphtc(PinYinUtil.getPinYinHeadChar(drHandoverPatientType.getTypeName()));
      drHandoverPatientType.setTypeCode(SnowIdUtils.uniqueLong());
      drHandoverPatientType.setCrePerCode(basEmployee.getEmplNumber());
      drHandoverPatientType.setCrePerName(basEmployee.getEmplName());
      return this.drHandoverPatientTypeMapper.insertDrHandoverPatientType(drHandoverPatientType);
   }

   public int updateDrHandoverPatientType(DrHandoverPatientType drHandoverPatientType) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverPatientType.setInputstrphtc(PinYinUtil.getPinYinHeadChar(drHandoverPatientType.getTypeName()));
      drHandoverPatientType.setAltPerCode(basEmployee.getEmplNumber());
      drHandoverPatientType.setAltPerName(basEmployee.getEmplName());
      return this.drHandoverPatientTypeMapper.updateDrHandoverPatientType(drHandoverPatientType);
   }

   public int deleteDrHandoverPatientTypeByIds(Long[] typeCodes) {
      return this.drHandoverPatientTypeMapper.deleteDrHandoverPatientTypeByIds(typeCodes);
   }

   public int deleteDrHandoverPatientTypeById(Long typeCode) {
      return this.drHandoverPatientTypeMapper.deleteDrHandoverPatientTypeById(typeCode);
   }

   public List selectPatientListByTypeCode(DrHandoverMain drHandoverMain, Long typeCode) {
      List<DrHandoverDetail> list = new ArrayList(1);
      DrHandoverPatientType drHandoverPatientType = this.selectDrHandoverPatientTypeById(typeCode);
      if (drHandoverPatientType != null) {
         drHandoverPatientType.getDataSql();
      }

      return list;
   }

   public List selectHandoverPatientListBySql(DrHandoverPatientTypeVo drHandoverPatientTypeVo) throws Exception {
      return this.drHandoverPatientTypeMapper.selectHandoverPatientListBySql(drHandoverPatientTypeVo);
   }
}
