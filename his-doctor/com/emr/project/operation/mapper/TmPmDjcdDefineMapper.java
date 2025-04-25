package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TmPmDjcdDefine;

public interface TmPmDjcdDefineMapper {
   int insert(TmPmDjcdDefine record);

   int insertSelective(TmPmDjcdDefine record);

   TmPmDjcdDefine getDjcdDefineByDjlx(Integer djlx);
}
