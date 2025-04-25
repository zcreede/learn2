package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.InpatientOrderPerformDetail;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface InpatientOrderPerformDetailMapper {
   int insert(InpatientOrderPerformDetail record);

   int insertSelective(InpatientOrderPerformDetail record);

   int deleteByPrimaryKey(InpatientOrderPerformDetail key);

   InpatientOrderPerformDetail selectByPrimaryKey(InpatientOrderPerformDetail key);

   int updateByPrimaryKeySelective(InpatientOrderPerformDetail record);

   int updateByPrimaryKey(InpatientOrderPerformDetail record);

   int insertList(List list);

   List selectListByNo(Map param);

   List selectListByPerformListNoList(@Param("list") List performListNoList);

   int deletePatientOrderPerformDetailByPerformListNos(@Param("orderPerformDetailList") List orderPerformDetailList);
}
