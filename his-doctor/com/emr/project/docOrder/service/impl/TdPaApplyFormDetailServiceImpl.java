package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.TdPaApplyFormDetail;
import com.emr.project.docOrder.mapper.TdPaApplyFormDetailMapper;
import com.emr.project.docOrder.service.ITdPaApplyFormDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaApplyFormDetailServiceImpl implements ITdPaApplyFormDetailService {
   @Autowired
   private TdPaApplyFormDetailMapper tdPaApplyFormDetailMapper;

   public List selectTdPaApplyFormDetailByFormNo(String applyFormNo) {
      return this.tdPaApplyFormDetailMapper.selectTdPaApplyFormDetailByFormNo(applyFormNo);
   }

   public List selectTdPaApplyFormDetailList(TdPaApplyFormDetail tdPaApplyFormDetail) {
      return this.tdPaApplyFormDetailMapper.selectTdPaApplyFormDetailList(tdPaApplyFormDetail);
   }

   public int insertTdPaApplyFormDetail(TdPaApplyFormDetail tdPaApplyFormDetail) {
      return this.tdPaApplyFormDetailMapper.insertTdPaApplyFormDetail(tdPaApplyFormDetail);
   }

   public void insertTdPaApplyFormDetailList(List tdPaApplyFormDetailList) throws Exception {
      this.tdPaApplyFormDetailMapper.insertTdPaApplyFormDetailList(tdPaApplyFormDetailList);
   }

   public int updateTdPaApplyFormDetail(TdPaApplyFormDetail tdPaApplyFormDetail) {
      return this.tdPaApplyFormDetailMapper.updateTdPaApplyFormDetail(tdPaApplyFormDetail);
   }

   public int deleteTdPaApplyFormDetailByIds(String[] applyFormNos) {
      return this.tdPaApplyFormDetailMapper.deleteTdPaApplyFormDetailByIds(applyFormNos);
   }

   public void deleteTdPaApplyFormDetailByFormNo(String applyFormNo) {
      this.tdPaApplyFormDetailMapper.deleteTdPaApplyFormDetailByFormNo(applyFormNo);
   }
}
