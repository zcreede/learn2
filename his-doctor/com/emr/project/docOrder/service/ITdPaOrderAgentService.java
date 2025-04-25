package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderAgent;
import java.util.List;

public interface ITdPaOrderAgentService {
   TdPaOrderAgent selectTdPaOrderAgentById(Long id);

   List selectTdPaOrderAgentList(TdPaOrderAgent tdPaOrderAgent);

   int insertTdPaOrderAgent(TdPaOrderAgent tdPaOrderAgent) throws Exception;

   int updateTdPaOrderAgent(TdPaOrderAgent tdPaOrderAgent);

   int deleteTdPaOrderAgentByIds(Long[] ids);

   int deleteTdPaOrderAgentById(Long id);

   void insertTdPaOrderAgentList(List list) throws Exception;
}
