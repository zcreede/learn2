package com.emr.project.dr.service;

import com.emr.project.dr.domain.DrHandoverMain;
import com.emr.project.dr.domain.DrHandoverPatientType;
import com.emr.project.dr.domain.vo.DrHandoverPatientTypeVo;
import java.util.List;

public interface IDrHandoverPatientTypeService {
   DrHandoverPatientType selectDrHandoverPatientTypeById(Long typeCode);

   List selectDrHandoverPatientTypeList(DrHandoverPatientType drHandoverPatientType);

   int insertDrHandoverPatientType(DrHandoverPatientType drHandoverPatientType);

   int updateDrHandoverPatientType(DrHandoverPatientType drHandoverPatientType);

   int deleteDrHandoverPatientTypeByIds(Long[] typeCodes);

   int deleteDrHandoverPatientTypeById(Long typeCode);

   List selectPatientListByTypeCode(DrHandoverMain drHandoverMain, Long typeCode);

   List selectHandoverPatientListBySql(DrHandoverPatientTypeVo drHandoverPatientTypeVo) throws Exception;
}
