package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import java.util.List;

public interface ITdPaApplyFormItemService {
   List selectTdPaApplyFormItemByFormNo(String applyFormNo) throws Exception;

   List selectTdPaApplyFormItemList(TdPaApplyFormItem tdPaApplyFormItem);

   int insertTdPaApplyFormItem(TdPaApplyFormItem tdPaApplyFormItem);

   void insertTdPaApplyFormItemList(List tdPaApplyFormItem) throws Exception;

   int updateTdPaApplyFormItem(TdPaApplyFormItem tdPaApplyFormItem);

   int deleteTdPaApplyFormItemByIds(String[] applyFormNos);

   void deleteTdPaApplyFormItemByFormNo(String applyFormNo);

   List selectTdPaApplyFormItemVoList(TdPaApplyFormVo paApplyFormVo) throws Exception;

   List selectItemByApplyNoList(List applyNo) throws Exception;

   void updateStatusByApplyFormNos(List applyFormNoList, String applyFormStatus) throws Exception;
}
