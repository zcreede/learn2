package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TdPmDjh;
import java.util.Map;

public interface TdPmDjhMapper {
   int insert(TdPmDjh record);

   int insertSelective(TdPmDjh record);

   Integer insertDjh(TdPmDjh tdPmDjh);

   String getNewId(Map param);
}
