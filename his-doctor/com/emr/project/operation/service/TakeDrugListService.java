package com.emr.project.operation.service;

import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugListKey;
import com.emr.project.operation.domain.req.SaveUnTakeDrugReq;
import com.emr.project.system.domain.SysUser;
import java.util.List;

public interface TakeDrugListService {
   TakeDrugList queryByPrimaryKey(TakeDrugListKey takeDrugListKey);

   void unTakeDrugReturn(SaveUnTakeDrugReq req, SysUser user, String ip) throws Exception;

   String searchTakeDrugListByStockNo(TakeDrugList t);

   List selectByPerformListNo(String performListNo);

   void changeStatusTakeDrugLists(List takeDrugLists, int takeDurgStatus) throws Exception;

   void updateStatusTakeDrugLists(List takeDrugList);

   void insertList(List list) throws Exception;

   List selectListByReturnVo(List returnVoList) throws Exception;

   void updateOrderDose(List list) throws Exception;

   List selectOrderTakeDrugLists(List orderNoList) throws Exception;

   String verifyOrderCancelTakeDrugList(List orderNoList) throws Exception;
}
