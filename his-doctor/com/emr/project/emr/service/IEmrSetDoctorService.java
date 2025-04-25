package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSetDoctor;
import com.emr.project.emr.domain.vo.EmrSetDoctorVo;
import java.util.List;

public interface IEmrSetDoctorService {
   EmrSetDoctor selectEmrSetDoctorById(Long id);

   List selectEmrSetDoctorList(EmrSetDoctor emrSetDoctor);

   int insertEmrSetDoctor(EmrSetDoctor emrSetDoctor);

   int updateEmrSetDoctor(EmrSetDoctor emrSetDoctor);

   int deleteEmrSetDoctorByIds(Long[] ids);

   int deleteEmrSetDoctorById(Long id);

   Boolean saveEmrSetDoctorList(EmrSetDoctorVo emrSetDoctorVo) throws Exception;

   void deleteEmrSetDoctorBySetCd(String setCd) throws Exception;
}
