package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TdPaOrderAgent;
import com.emr.project.docOrder.mapper.TdPaOrderAgentMapper;
import com.emr.project.docOrder.service.ITdPaOrderAgentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderAgentServiceImpl implements ITdPaOrderAgentService {
   @Autowired
   private TdPaOrderAgentMapper tdPaOrderAgentMapper;

   public TdPaOrderAgent selectTdPaOrderAgentById(Long id) {
      return this.tdPaOrderAgentMapper.selectTdPaOrderAgentById(id);
   }

   public List selectTdPaOrderAgentList(TdPaOrderAgent tdPaOrderAgent) {
      return this.tdPaOrderAgentMapper.selectTdPaOrderAgentList(tdPaOrderAgent);
   }

   public int insertTdPaOrderAgent(TdPaOrderAgent tdPaOrderAgent) throws Exception {
      return this.tdPaOrderAgentMapper.insertTdPaOrderAgent(tdPaOrderAgent);
   }

   public int updateTdPaOrderAgent(TdPaOrderAgent tdPaOrderAgent) {
      return this.tdPaOrderAgentMapper.updateTdPaOrderAgent(tdPaOrderAgent);
   }

   public int deleteTdPaOrderAgentByIds(Long[] ids) {
      return this.tdPaOrderAgentMapper.deleteTdPaOrderAgentByIds(ids);
   }

   public int deleteTdPaOrderAgentById(Long id) {
      return this.tdPaOrderAgentMapper.deleteTdPaOrderAgentById(id);
   }

   public void insertTdPaOrderAgentList(List list) throws Exception {
      this.tdPaOrderAgentMapper.insertTdPaOrderAgentList(list);
   }
}
