package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderOperationLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderOperationLogMapper {
   TdPaOrderOperationLog selectTdPaOrderOperationLogById(Long id);

   List selectTdPaOrderOperationLogList(TdPaOrderOperationLog tdPaOrderOperationLog);

   int insertTdPaOrderOperationLog(TdPaOrderOperationLog tdPaOrderOperationLog);

   int updateTdPaOrderOperationLog(TdPaOrderOperationLog tdPaOrderOperationLog);

   int deleteTdPaOrderOperationLogById(Long id);

   int deleteTdPaOrderOperationLogByIds(Long[] ids);

   void insertList(List list);

   int insert(TdPaOrderOperationLog record);

   List selectLogByOrderNo(@Param("orderNo") String orderNo, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
