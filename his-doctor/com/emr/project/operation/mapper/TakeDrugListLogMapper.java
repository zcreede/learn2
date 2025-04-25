package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TakeDrugListLog;
import java.util.List;

public interface TakeDrugListLogMapper {
   int deleteByPrimaryKey(Long id);

   int insert(TakeDrugListLog record);

   int insertSelective(TakeDrugListLog record);

   TakeDrugListLog selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(TakeDrugListLog record);

   int updateByPrimaryKey(TakeDrugListLog record);

   void insertBatch(List list);

   void deleteByIdList(List list);
}
