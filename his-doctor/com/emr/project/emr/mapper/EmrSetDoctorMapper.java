package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSetDoctor;
import java.util.List;

public interface EmrSetDoctorMapper {
   EmrSetDoctor selectEmrSetDoctorById(Long id);

   List selectEmrSetDoctorList(EmrSetDoctor emrSetDoctor);

   int insertEmrSetDoctor(EmrSetDoctor emrSetDoctor);

   int updateEmrSetDoctor(EmrSetDoctor emrSetDoctor);

   int deleteEmrSetDoctorById(Long id);

   int deleteEmrSetDoctorByIds(Long[] ids);

   void deleteEmrSetDoctorBySetCd(String setCd, String docCd);

   void deleteEmrSetDoctorBySetCdAndPatientId(String setCd, String docCd, String patientId);

   void deleteEmrSetDoctorBySetCdForSel(String setCd);

   void insertEmrSetDoctorList(List emrSetDoctorList);
}
