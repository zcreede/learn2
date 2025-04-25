package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.InpatientOrderPerformFirstBottle;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InpatientOrderPerformFirstBottleMapper {
   int deleteByPrimaryKey(Long id);

   int deleteByids(Long[] ids);

   int insert(InpatientOrderPerformFirstBottle record);

   int insertSelective(InpatientOrderPerformFirstBottle record);

   InpatientOrderPerformFirstBottle selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(InpatientOrderPerformFirstBottle record);

   int updateByPrimaryKey(InpatientOrderPerformFirstBottle record);

   InpatientOrderPerformFirstBottle selectByConn(InpatientOrderPerformFirstBottle record);

   List selectListByConn(InpatientOrderPerformFirstBottle record);

   List selectListByAdminssionNos(@Param("orderPerformList") List orderPerformList);
}
