package com.emr.project.other.mapper;

import com.emr.project.other.domain.GrantOutDoctor;
import com.emr.project.other.domain.vo.GrantOutDoctorVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GrantOutDoctorMapper {
   GrantOutDoctor selectGrantOutDoctorById(Long id);

   List selectGrantOutDoctorList(GrantOutDoctorVo grantOutDoctorVo);

   int insertGrantOutDoctor(GrantOutDoctor grantOutDoctor);

   int updateGrantOutDoctor(GrantOutDoctor grantOutDoctor);

   int deleteGrantOutDoctorById(Long id);

   int deleteGrantOutDoctorByIds(Long[] ids);

   Integer selectSubNameMaxNumByDocCode(String docCode);

   Integer checkIsOutDoctor(@Param("userName") String userName);
}
