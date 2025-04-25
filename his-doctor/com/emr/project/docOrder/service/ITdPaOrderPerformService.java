package com.emr.project.docOrder.service;

import java.util.List;

public interface ITdPaOrderPerformService {
   void addList(List list) throws Exception;

   int getPerformStatus(String performListNo, int performListSortNumber) throws Exception;

   void updatePerformByOrderNoAndStauts(String orderNo, String resOrderStatus) throws Exception;
}
