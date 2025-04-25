package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.InpatientOrderPerform;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderPerformHMapper {
   InpatientOrderPerform selectTdPaOrderPerformHById(String performListNo);

   List selectTdPaOrderPerformHList(InpatientOrderPerform tdPaOrderPerformH);

   List selectTdPaOrderPerformHByPerformListNos(@Param("tdPaOrderPerformHList") List tdPaOrderPerformHList);

   int insertTdPaOrderPerformH(InpatientOrderPerform tdPaOrderPerformH);

   int batchInsert(@Param("tdPaOrderPerformHList") List tdPaOrderPerformHList);

   int batchInsertTdPaOrderPerformH(@Param("tdPaOrderPerformHList") List tdPaOrderPerformHList);

   int updateTdPaOrderPerformH(InpatientOrderPerform tdPaOrderPerformH);

   int deleteTdPaOrderPerformHById(String performListNo);

   int deleteTdPaOrderPerformHByIds(String[] performListNos);
}
