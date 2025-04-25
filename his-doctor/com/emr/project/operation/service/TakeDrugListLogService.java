package com.emr.project.operation.service;

import java.util.List;

public interface TakeDrugListLogService {
   List addTakeDrugListLogList(int operateType, List takeDrugLists);

   void addTakeDrugListLogListByVO(int operateType, List takeDrugLists);

   void deleteByIdList(List list) throws Exception;
}
