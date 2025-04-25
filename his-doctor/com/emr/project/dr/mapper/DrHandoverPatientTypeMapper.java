package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.DrHandoverPatientType;
import com.emr.project.dr.domain.vo.DrHandoverPatientTypeVo;
import java.util.List;

public interface DrHandoverPatientTypeMapper {
   DrHandoverPatientType selectDrHandoverPatientTypeById(Long typeCode);

   List selectDrHandoverPatientTypeList(DrHandoverPatientType drHandoverPatientType);

   int insertDrHandoverPatientType(DrHandoverPatientType drHandoverPatientType);

   int updateDrHandoverPatientType(DrHandoverPatientType drHandoverPatientType);

   int deleteDrHandoverPatientTypeById(Long typeCode);

   int deleteDrHandoverPatientTypeByIds(Long[] typeCodes);

   List selectHandoverPatientListBySql(DrHandoverPatientTypeVo drHandoverPatientTypeVo);
}
