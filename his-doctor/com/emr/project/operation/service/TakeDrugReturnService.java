package com.emr.project.operation.service;

import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnResultVo;
import java.util.List;

public interface TakeDrugReturnService {
   List queryBySerialNumberList(List list);

   void cancelTakeDrugReturnList(List takeDrugReturns) throws Exception;

   List queryByBillsNoOldList(List list);

   void saveTakeDrugReturnList(List list) throws Exception;

   void addList(List list) throws Exception;

   DrugListAndPerformReturnResultVo doReturnDrug(List returnVoList, String ip) throws Exception;

   void doReturnDrugCancel(DrugListAndPerformReturnResultVo resultVo) throws Exception;

   List selectByBillsNoOldList(List billsNoList) throws Exception;
}
