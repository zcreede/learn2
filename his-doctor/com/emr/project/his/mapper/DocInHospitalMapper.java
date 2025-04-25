package com.emr.project.his.mapper;

import com.emr.project.his.domain.vo.DocInHospitalVo;
import com.emr.project.system.domain.vo.SqlVo;

public interface DocInHospitalMapper {
   DocInHospitalVo selectDocInHospitalByInpNo(SqlVo sqlVo);
}
