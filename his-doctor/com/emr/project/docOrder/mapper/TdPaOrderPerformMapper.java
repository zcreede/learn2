package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderPerform;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderPerformMapper {
   TdPaOrderPerform selectTdPaOrderPerformById(String performListNo);

   List selectTdPaOrderPerformList(TdPaOrderPerform tdPaOrderPerform);

   int insertTdPaOrderPerform(TdPaOrderPerform tdPaOrderPerform);

   int updateTdPaOrderPerform(TdPaOrderPerform tdPaOrderPerform);

   int deleteTdPaOrderPerformById(String performListNo);

   int deleteTdPaOrderPerformByIds(String[] performListNos);

   void insertList(List list);

   int getPerformStatus(@Param("performListNo") String performListNo, @Param("performListSortNumber") int performListSortNumber);

   List selectPerformListByOrderNo(@Param("orderNo") String orderNo);

   void updatePerformByOrderNoAndStauts(@Param("orderNo") String orderNo, @Param("resOrderStatus") String resOrderStatus);
}
