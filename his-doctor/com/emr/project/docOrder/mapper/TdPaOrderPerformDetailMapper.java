package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderPerformDetail;
import java.util.List;

public interface TdPaOrderPerformDetailMapper {
   TdPaOrderPerformDetail selectTdPaOrderPerformDetailById(String performListNo);

   List selectTdPaOrderPerformDetailList(TdPaOrderPerformDetail tdPaOrderPerformDetail);

   int insertTdPaOrderPerformDetail(TdPaOrderPerformDetail tdPaOrderPerformDetail);

   int updateTdPaOrderPerformDetail(TdPaOrderPerformDetail tdPaOrderPerformDetail);

   int deleteTdPaOrderPerformDetailById(String performListNo);

   int deleteTdPaOrderPerformDetailByIds(String[] performListNos);

   void insertList(List list);

   List selectPerformDetailList(List list);
}
