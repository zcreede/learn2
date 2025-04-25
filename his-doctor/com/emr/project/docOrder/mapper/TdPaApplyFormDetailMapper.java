package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaApplyFormDetail;
import java.util.List;

public interface TdPaApplyFormDetailMapper {
   List selectTdPaApplyFormDetailByFormNo(String applyFormNo);

   List selectTdPaApplyFormDetailList(TdPaApplyFormDetail tdPaApplyFormDetail);

   int insertTdPaApplyFormDetail(TdPaApplyFormDetail tdPaApplyFormDetail);

   void insertTdPaApplyFormDetailList(List tdPaApplyFormDetailList);

   int updateTdPaApplyFormDetail(TdPaApplyFormDetail tdPaApplyFormDetail);

   void deleteTdPaApplyFormDetailByFormNo(String applyFormNo);

   int deleteTdPaApplyFormDetailByIds(String[] applyFormNos);
}
