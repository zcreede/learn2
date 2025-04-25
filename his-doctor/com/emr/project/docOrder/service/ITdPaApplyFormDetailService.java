package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaApplyFormDetail;
import java.util.List;

public interface ITdPaApplyFormDetailService {
   List selectTdPaApplyFormDetailByFormNo(String applyFormNo);

   List selectTdPaApplyFormDetailList(TdPaApplyFormDetail tdPaApplyFormDetail);

   int insertTdPaApplyFormDetail(TdPaApplyFormDetail tdPaApplyFormDetail);

   void insertTdPaApplyFormDetailList(List tdPaApplyFormDetail) throws Exception;

   int updateTdPaApplyFormDetail(TdPaApplyFormDetail tdPaApplyFormDetail);

   int deleteTdPaApplyFormDetailByIds(String[] applyFormNos);

   void deleteTdPaApplyFormDetailByFormNo(String applyFormNo);
}
