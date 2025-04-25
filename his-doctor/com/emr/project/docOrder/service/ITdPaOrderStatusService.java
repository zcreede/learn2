package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.pat.domain.Visitinfo;
import java.util.Date;
import java.util.List;

public interface ITdPaOrderStatusService {
   List selectTdPaOrderStatusById(String orderNo);

   List selectTdPaOrderStatusList(TdPaOrderStatus tdPaOrderStatus);

   int insertTdPaOrderStatus(TdPaOrderStatus tdPaOrderStatus);

   int updateTdPaOrderStatus(TdPaOrderStatus tdPaOrderStatus);

   int deleteTdPaOrderStatusByIds(String[] orderNos);

   int deleteTdPaOrderStatusById(String orderNo);

   void addTdPaOrderStatus(Visitinfo visitinfo, List orderItemList, int operationType, String operationTypeName, Date currDate, String operatorDesc) throws Exception;

   void addTdPaOrderStatus(Visitinfo visitinfo, List orderItemList, int operationType, String operationTypeName, Date currDate, String operatorDesc, String operatorNo, String operatorName) throws Exception;

   void updateTdPaOrderStatus(List orderNoList, int operationType, String operationTypeName, Date currDate, String operatorDesc) throws Exception;

   List selectByOrderNoList(List orderNoList) throws Exception;

   List selectByOrderNoListAndStatusList(List orderNoList, List operationTypeList) throws Exception;

   List selectByOperationTypes(String patientId) throws Exception;

   boolean getOrderStatusIsEffective(TdPaOrderItem inpatientOrderItem, String flag, Date nowDate) throws Exception;

   boolean getOrderStatusIsEffective(String orderNo, String orderSortNumber, String orderGroupNumer, String flag, String performTime) throws Exception;

   void insertSelective(TdPaOrderStatus tdPaOrderStatus) throws Exception;

   void updateItemTime(String orderNo, String orderSortNumber, String orderGroupNo, String staffCode, Date dbDate, int i, boolean isUpdateStatus, String stopUser);

   void updateItemOrderStatus(String orderNo, String orderSortNumber, String orderGroupNo, String orderStatus, String orderClassCode);

   TdPaOrderStatus getTdPaOrderStatus(Visitinfo visitinfo, String orderNo, String orderGroupNo, String orderSortNumber, String orderClassCode, String operationType, String operationName, Date currDate) throws Exception;

   void insertTdPaOrderStatusList(List orderStatusList) throws Exception;

   String selectOrderType(String orderNo, String orderSortNumber, String orderGroupNo);
}
