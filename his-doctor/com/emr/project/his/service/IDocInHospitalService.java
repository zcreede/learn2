package com.emr.project.his.service;

import com.emr.project.his.domain.vo.DocInHospitalVo;
import com.emr.project.system.domain.vo.SqlVo;

public interface IDocInHospitalService {
   DocInHospitalVo selectDocInHospitalByInpNo(SqlVo sqlVo) throws Exception;
}
