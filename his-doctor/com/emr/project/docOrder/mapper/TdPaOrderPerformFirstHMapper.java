package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.InpatientOrderPerformFirstBottle;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderPerformFirstHMapper {
   InpatientOrderPerformFirstBottle selectTdPaOrderPerformFirstHById(Long id);

   List selectTdPaOrderPerformFirstHByIds(Long[] ids);

   List selectTdPaOrderPerformFirstHList(InpatientOrderPerformFirstBottle tdPaOrderPerformFirstH);

   int insertTdPaOrderPerformFirstH(InpatientOrderPerformFirstBottle tdPaOrderPerformFirstH);

   int batchInsert(@Param("tdPaOrderPerformFirstHList") List tdPaOrderPerformFirstHList);

   int batchInsertTdPaOrderPerformFirstH(@Param("tdPaOrderPerformFirstHList") List tdPaOrderPerformFirstHList);

   int updateTdPaOrderPerformFirstH(InpatientOrderPerformFirstBottle tdPaOrderPerformFirstH);

   int deleteTdPaOrderPerformFirstHById(Long id);

   int deleteTdPaOrderPerformFirstHByIds(Long[] ids);
}
