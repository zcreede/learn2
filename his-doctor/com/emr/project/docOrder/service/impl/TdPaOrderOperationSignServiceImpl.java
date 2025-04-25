package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TdPaOrderOperationSign;
import com.emr.project.docOrder.mapper.TdPaOrderOperationSignMapper;
import com.emr.project.docOrder.service.ITdPaOrderOperationSignService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderOperationSignServiceImpl implements ITdPaOrderOperationSignService {
   @Autowired
   private TdPaOrderOperationSignMapper tdPaOrderOperationSignMapper;

   public TdPaOrderOperationSign selectTdPaOrderOperationSignById(Long id) {
      return this.tdPaOrderOperationSignMapper.selectTdPaOrderOperationSignById(id);
   }

   public List selectTdPaOrderOperationSignList(TdPaOrderOperationSign tdPaOrderOperationSign) {
      return this.tdPaOrderOperationSignMapper.selectTdPaOrderOperationSignList(tdPaOrderOperationSign);
   }

   public int insertTdPaOrderOperationSign(TdPaOrderOperationSign tdPaOrderOperationSign) {
      return this.tdPaOrderOperationSignMapper.insertTdPaOrderOperationSign(tdPaOrderOperationSign);
   }

   public int updateTdPaOrderOperationSign(TdPaOrderOperationSign tdPaOrderOperationSign) {
      return this.tdPaOrderOperationSignMapper.updateTdPaOrderOperationSign(tdPaOrderOperationSign);
   }

   public int deleteTdPaOrderOperationSignByIds(Long[] ids) {
      return this.tdPaOrderOperationSignMapper.deleteTdPaOrderOperationSignByIds(ids);
   }

   public int deleteTdPaOrderOperationSignById(Long id) {
      return this.tdPaOrderOperationSignMapper.deleteTdPaOrderOperationSignById(id);
   }
}
