package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderOperationLog;
import com.emr.project.pat.domain.Visitinfo;
import java.util.Date;
import java.util.List;

public interface ITdPaOrderOperationLogService {
   TdPaOrderOperationLog selectTdPaOrderOperationLogById(Long id);

   List selectTdPaOrderOperationLogList(TdPaOrderOperationLog tdPaOrderOperationLog);

   int insertTdPaOrderOperationLog(TdPaOrderOperationLog tdPaOrderOperationLog);

   int updateTdPaOrderOperationLog(TdPaOrderOperationLog tdPaOrderOperationLog);

   int deleteTdPaOrderOperationLogByIds(Long[] ids);

   int deleteTdPaOrderOperationLogById(Long id);

   void addTdPaOrderOperationLog(Visitinfo visitinfo, List orderItemList, int operationType, String operationTypeName, Date currDate, String operatorDesc) throws Exception;

   void addTdPaOrderOperationLog(Visitinfo visitinfo, List orderItemList, int operationType, String operationTypeName, Date currDate, String operatorDesc, String operatorNo, String operatorName) throws Exception;

   void addInpatientOrderOperationLog(String orderNo, String orderSortNumber, String orderGroupNo, Integer operationType, String operationName, String operatorDesc, String operatorCode, String operatorNo, String operatorName);

   TdPaOrderOperationLog getInpatientOrderOperationLog(String orderNo, String orderSortNumber, String orderGroupNo, Integer operationType, String operationName, String operatorDesc, String operatorCode, String operatorNo, String operatorName);

   void insertTdPaOrderOperationLogList(List orderOperationLogList) throws Exception;

   List selectLogByOrderNo(String orderNo, Date date);
}
