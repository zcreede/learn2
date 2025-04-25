package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.CumulativeCost;

public interface CumulativeCostMapper {
   int deleteByPrimaryKey(String admissionNo);

   int insert(CumulativeCost record);

   int insertSelective(CumulativeCost record);

   CumulativeCost selectByPrimaryKey(String admissionNo);

   int updateByPrimaryKeySelective(CumulativeCost record);

   int updateByPrimaryKey(CumulativeCost record);
}
