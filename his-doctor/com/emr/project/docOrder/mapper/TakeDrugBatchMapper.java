package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TakeDrugBatch;

public interface TakeDrugBatchMapper {
   int deleteByPrimaryKey(Long id);

   int insert(TakeDrugBatch record);

   int insertSelective(TakeDrugBatch record);

   TakeDrugBatch selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(TakeDrugBatch record);

   int updateByPrimaryKey(TakeDrugBatch record);

   TakeDrugBatch selectLastBatch(TakeDrugBatch record);
}
