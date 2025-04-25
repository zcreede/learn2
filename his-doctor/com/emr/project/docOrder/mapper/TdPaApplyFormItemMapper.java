package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaApplyFormItemMapper {
   List selectTdPaApplyFormItemByFormNo(String applyFormNo);

   List selectTdPaApplyFormItemList(TdPaApplyFormItem tdPaApplyFormItem);

   int insertTdPaApplyFormItem(TdPaApplyFormItem tdPaApplyFormItem);

   void insertTdPaApplyFormItemList(List tdPaApplyFormItem);

   int updateTdPaApplyFormItem(TdPaApplyFormItem tdPaApplyFormItem);

   void deleteTdPaApplyFormItemByFormNo(String applyFormNo);

   int deleteTdPaApplyFormItemByIds(String[] applyFormNos);

   List selectTdPaApplyFormItemVoList(TdPaApplyFormVo paApplyFormVo);

   List selectItemByApplyNoList(List applyNo);

   void updateStatusByApplyFormNos(List applyFormNoList, @Param("applyFormStatus") String applyFormStatus);

   List selectTdPaApplyFormItemByApplyFormNo(@Param("applyFormNo") String applyFormNo);
}
