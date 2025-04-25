package com.emr.project.system.mapper;

import com.emr.project.system.domain.PatAtt;

public interface PatAttMapper {
   int insert(PatAtt record);

   int insertSelective(PatAtt record);

   int updatePatAtt(PatAtt record);

   PatAtt findByAdmissionNo(String admissionNo);
}
