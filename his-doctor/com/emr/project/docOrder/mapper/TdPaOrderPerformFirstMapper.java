package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderPerformFirst;
import java.util.List;

public interface TdPaOrderPerformFirstMapper {
   TdPaOrderPerformFirst selectTdPaOrderPerformFirstById(Long id);

   List selectTdPaOrderPerformFirstList(TdPaOrderPerformFirst tdPaOrderPerformFirst);

   int insertTdPaOrderPerformFirst(TdPaOrderPerformFirst tdPaOrderPerformFirst);

   int updateTdPaOrderPerformFirst(TdPaOrderPerformFirst tdPaOrderPerformFirst);

   int deleteTdPaOrderPerformFirstById(Long id);

   int deleteTdPaOrderPerformFirstByIds(Long[] ids);
}
