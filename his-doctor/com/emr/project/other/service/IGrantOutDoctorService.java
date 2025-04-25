package com.emr.project.other.service;

import com.emr.project.other.domain.GrantOutDoctor;
import com.emr.project.other.domain.vo.GrantOutDoctorVo;
import java.util.List;

public interface IGrantOutDoctorService {
   GrantOutDoctor selectGrantOutDoctorById(Long id);

   List selectGrantOutDoctorList(GrantOutDoctorVo grantOutDoctorVo);

   int insertGrantOutDoctor(GrantOutDoctor grantOutDoctor) throws Exception;

   int updateGrantOutDoctor(GrantOutDoctor grantOutDoctor);

   int deleteGrantOutDoctorByIds(Long[] ids);

   int deleteGrantOutDoctorById(Long id) throws Exception;

   GrantOutDoctor selectGrantOutDoctorBySubNo(String subNo);

   Boolean checkIsOutDoctor(String userName);
}
