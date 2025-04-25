package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.mapper.TdPaApplyFormItemMapper;
import com.emr.project.docOrder.service.ITdPaApplyFormItemService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaApplyFormItemServiceImpl implements ITdPaApplyFormItemService {
   @Autowired
   private TdPaApplyFormItemMapper tdPaApplyFormItemMapper;

   public List selectTdPaApplyFormItemByFormNo(String applyFormNo) throws Exception {
      return this.tdPaApplyFormItemMapper.selectTdPaApplyFormItemByApplyFormNo(applyFormNo);
   }

   public List selectTdPaApplyFormItemList(TdPaApplyFormItem tdPaApplyFormItem) {
      return this.tdPaApplyFormItemMapper.selectTdPaApplyFormItemList(tdPaApplyFormItem);
   }

   public int insertTdPaApplyFormItem(TdPaApplyFormItem tdPaApplyFormItem) {
      return this.tdPaApplyFormItemMapper.insertTdPaApplyFormItem(tdPaApplyFormItem);
   }

   public void insertTdPaApplyFormItemList(List tdPaApplyFormItem) throws Exception {
      this.tdPaApplyFormItemMapper.insertTdPaApplyFormItemList(tdPaApplyFormItem);
   }

   public int updateTdPaApplyFormItem(TdPaApplyFormItem tdPaApplyFormItem) {
      return this.tdPaApplyFormItemMapper.updateTdPaApplyFormItem(tdPaApplyFormItem);
   }

   public int deleteTdPaApplyFormItemByIds(String[] applyFormNos) {
      return this.tdPaApplyFormItemMapper.deleteTdPaApplyFormItemByIds(applyFormNos);
   }

   public void deleteTdPaApplyFormItemByFormNo(String applyFormNo) {
      this.tdPaApplyFormItemMapper.deleteTdPaApplyFormItemByFormNo(applyFormNo);
   }

   public List selectTdPaApplyFormItemVoList(TdPaApplyFormVo paApplyFormVo) throws Exception {
      return this.tdPaApplyFormItemMapper.selectTdPaApplyFormItemVoList(paApplyFormVo);
   }

   public List selectItemByApplyNoList(List applyNo) throws Exception {
      List<TdPaApplyFormItem> list = new ArrayList();
      if (applyNo != null && !applyNo.isEmpty()) {
         list = this.tdPaApplyFormItemMapper.selectItemByApplyNoList(applyNo);
      }

      return list;
   }

   public void updateStatusByApplyFormNos(List applyFormNoList, String applyFormStatus) throws Exception {
      if (applyFormNoList != null && !applyFormNoList.isEmpty() && StringUtils.isNotBlank(applyFormStatus)) {
         this.tdPaApplyFormItemMapper.updateStatusByApplyFormNos(applyFormNoList, applyFormStatus);
      }

   }
}
