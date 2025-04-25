package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TdPaOrderSignDetail;
import com.emr.project.docOrder.mapper.TdPaOrderSignDetailMapper;
import com.emr.project.docOrder.service.ITdPaOrderSignDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderSignDetailServiceImpl implements ITdPaOrderSignDetailService {
   @Autowired
   private TdPaOrderSignDetailMapper tdPaOrderSignDetailMapper;

   public TdPaOrderSignDetail selectTdPaOrderSignDetailById(Long id) {
      return this.tdPaOrderSignDetailMapper.selectTdPaOrderSignDetailById(id);
   }

   public List selectTdPaOrderSignDetailList(TdPaOrderSignDetail tdPaOrderSignDetail) {
      return this.tdPaOrderSignDetailMapper.selectTdPaOrderSignDetailList(tdPaOrderSignDetail);
   }

   public int insertTdPaOrderSignDetail(TdPaOrderSignDetail tdPaOrderSignDetail) {
      return this.tdPaOrderSignDetailMapper.insertTdPaOrderSignDetail(tdPaOrderSignDetail);
   }

   public int updateTdPaOrderSignDetail(TdPaOrderSignDetail tdPaOrderSignDetail) {
      return this.tdPaOrderSignDetailMapper.updateTdPaOrderSignDetail(tdPaOrderSignDetail);
   }

   public int deleteTdPaOrderSignDetailByIds(Long[] ids) {
      return this.tdPaOrderSignDetailMapper.deleteTdPaOrderSignDetailByIds(ids);
   }

   public int deleteTdPaOrderSignDetailById(Long id) {
      return this.tdPaOrderSignDetailMapper.deleteTdPaOrderSignDetailById(id);
   }

   public void insertList(List signDetailList) {
      this.tdPaOrderSignDetailMapper.insertList(signDetailList);
   }
}
