package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugListKey;
import java.util.List;

public interface TakeDrugListMapper {
   TakeDrugList selectByPrimaryKey(TakeDrugListKey key);

   void moveToDeleteTable(List list);

   void deleteList(List list);

   void updateOrderDose(List list);

   String searchTakeDrugListByStockNo(TakeDrugList t);

   List selectByPerformListNo(String performListNo);

   void updateStatusTakeDrugLists(List takeDrugList);

   void insertList(List list);

   List selectListByReturnVo(List returnVoList);

   List selectOrderTakeDrugLists(List orderNoList);
}
