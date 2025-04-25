package com.emr.project.docOrder.service;

import java.util.List;

public interface ITdPaOrderPerformDetailService {
   void addList(List list) throws Exception;

   List selectPerformDetailList(List list) throws Exception;
}
