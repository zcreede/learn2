package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderAgent;
import java.util.List;

public interface TdPaOrderAgentMapper {
   TdPaOrderAgent selectTdPaOrderAgentById(Long id);

   List selectTdPaOrderAgentList(TdPaOrderAgent tdPaOrderAgent);

   int insertTdPaOrderAgent(TdPaOrderAgent tdPaOrderAgent);

   int updateTdPaOrderAgent(TdPaOrderAgent tdPaOrderAgent);

   int deleteTdPaOrderAgentById(Long id);

   int deleteTdPaOrderAgentByIds(Long[] ids);

   void insertTdPaOrderAgentList(List list);
}
