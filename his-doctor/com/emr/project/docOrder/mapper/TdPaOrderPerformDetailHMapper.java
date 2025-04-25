package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.InpatientOrderPerformDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderPerformDetailHMapper {
   InpatientOrderPerformDetail selectTdPaOrderPerformDetailHById(String performListNo);

   List selectListByPerformListNoList(@Param("list") List performListNoList);

   List selectTdPaOrderPerformDetailHList(InpatientOrderPerformDetail tdPaOrderPerformDetailH);

   int insertTdPaOrderPerformDetailH(InpatientOrderPerformDetail tdPaOrderPerformDetailH);

   int batchInsert(@Param("tdPaOrderPerformDetailHList") List tdPaOrderPerformDetailHList);

   int batchInsertTdPaOrderPerformDetailH(@Param("tdPaOrderPerformDetailHList") List tdPaOrderPerformDetailHList);

   int updateTdPaOrderPerformDetailH(InpatientOrderPerformDetail tdPaOrderPerformDetailH);

   int deleteTdPaOrderPerformDetailHById(String performListNo);

   int deleteTdPaOrderPerformDetailHByIds(String[] performListNos);
}
