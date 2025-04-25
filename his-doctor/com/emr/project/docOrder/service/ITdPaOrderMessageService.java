package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderMessage;
import com.emr.project.pat.domain.Visitinfo;
import java.util.Date;
import java.util.List;

public interface ITdPaOrderMessageService {
   TdPaOrderMessage selectTdPaOrderMessageById(Long id);

   List selectTdPaOrderMessageList(TdPaOrderMessage tdPaOrderMessage);

   int insertTdPaOrderMessage(TdPaOrderMessage tdPaOrderMessage);

   int updateTdPaOrderMessage(TdPaOrderMessage tdPaOrderMessage);

   int deleteTdPaOrderMessageByIds(Long[] ids);

   int deleteTdPaOrderMessageById(Long id);

   void insertList(List list) throws Exception;

   void addMsgs(List orderList, Visitinfo visitinfo, int operationType, String operationTypeName) throws Exception;

   void updateMsgStatus(List orderNoList, String msgStatus, String orderStatus) throws Exception;

   List selectOrderReturnMessageList(String msgStatus, Date startTime, Date endTime);

   List getCurrentDeptOrderMsgList();
}
