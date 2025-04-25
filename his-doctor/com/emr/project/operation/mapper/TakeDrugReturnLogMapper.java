package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TakeDrugReturnLog;
import java.util.List;

public interface TakeDrugReturnLogMapper {
   int deleteByPrimaryKey(Long id);

   int insert(TakeDrugReturnLog record);

   int insertSelective(TakeDrugReturnLog record);

   TakeDrugReturnLog selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(TakeDrugReturnLog record);

   int updateByPrimaryKey(TakeDrugReturnLog record);

   void insertBatch(List list);
}
