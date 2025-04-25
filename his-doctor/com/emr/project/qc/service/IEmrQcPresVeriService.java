package com.emr.project.qc.service;

import com.emr.project.qc.domain.EmrQcPresVeri;
import java.util.List;

public interface IEmrQcPresVeriService {
   EmrQcPresVeri selectEmrQcPresVeriById(Long id);

   List selectEmrQcPresVeriList(EmrQcPresVeri emrQcPresVeri);

   int insertEmrQcPresVeri(EmrQcPresVeri emrQcPresVeri);

   int updateEmrQcPresVeri(EmrQcPresVeri emrQcPresVeri);

   int deleteEmrQcPresVeriByIds(Long[] ids);

   int deleteEmrQcPresVeriById(Long id);

   void addListBySubmitQc(String patientId) throws Exception;
}
