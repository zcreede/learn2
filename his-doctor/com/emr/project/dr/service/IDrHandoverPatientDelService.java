package com.emr.project.dr.service;

import com.emr.project.dr.domain.DrHandoverPatientDel;
import java.util.List;

public interface IDrHandoverPatientDelService {
   DrHandoverPatientDel selectDrHandoverPatientDelById(Long id);

   List selectDrHandoverPatientDelList(DrHandoverPatientDel drHandoverPatientDel);

   int insertDrHandoverPatientDel(DrHandoverPatientDel drHandoverPatientDel) throws Exception;

   int updateDrHandoverPatientDel(DrHandoverPatientDel drHandoverPatientDel);

   int deleteDrHandoverPatientDelByIds(Long[] ids);

   int deleteDrHandoverPatientDelById(Long id);
}
