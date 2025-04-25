package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.DrHandoverPatientDel;
import java.util.List;

public interface DrHandoverPatientDelMapper {
   DrHandoverPatientDel selectDrHandoverPatientDelById(Long id);

   List selectDrHandoverPatientDelList(DrHandoverPatientDel drHandoverPatientDel);

   int insertDrHandoverPatientDel(DrHandoverPatientDel drHandoverPatientDel);

   int updateDrHandoverPatientDel(DrHandoverPatientDel drHandoverPatientDel);

   int deleteDrHandoverPatientDelById(Long id);

   int deleteDrHandoverPatientDelByIds(Long[] ids);
}
