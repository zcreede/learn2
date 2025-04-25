package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaApplyForm;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.resp.ApplyFormPrintResp;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import java.util.Map;

public interface ITdPaApplyFormService {
   TdPaApplyFormVo selectTdPaApplyFormById(String applyFormNo) throws Exception;

   List selectTdPaApplyFormList(TdPaApplyForm tdPaApplyForm) throws Exception;

   void insertTdPaApplyForm(TdPaApplyForm tdPaApplyForm) throws Exception;

   Map getCheckApplyFormList(List itemList) throws Exception;

   Map getCheckOutApplyFormList(List itemList) throws Exception;

   int updateTdPaApplyForm(TdPaApplyForm tdPaApplyForm);

   void submitTdPaApplyForm(OrderCommitVo orderCommitVo, List orderItemList, Visitinfo visitinfo) throws Exception;

   int deleteTdPaApplyFormByIds(String[] applyFormNos);

   int deleteTdPaApplyFormById(String applyFormNo);

   List saveApplyFormList(TdPaApplyFormVo tdPaApplyForm, OrderCommitVo orderCommitVo) throws Exception;

   String getPurposeExamination(List list);

   TdPaApplyFormVo selectSelectPatDia(String patientId) throws Exception;

   Map getPrintInfoList(List tdPaApplyFormList) throws Exception;

   TdPaApplyFormVo selectNewestCheck(String patientId) throws Exception;

   TdPaApplyForm genTdPaApplyForm(TdPaOrder order, List orderItemList, List orderDetailList, OrderSaveVo orderSaveVo, Visitinfo visitinfo, List tdPaApplyFormList, List tdPaApplyFormItemList, List tdPaApplyFormDetailList, Map applyFormItemSaveVoMap);

   void insertTdPaApplyFormList(List tdPaApplyFormList) throws Exception;

   List selectCheckFlowChart(String applyFormNo) throws Exception;

   List selectListByOrderNos(List orderNoList) throws Exception;

   void updateStatusByApplyFormNos(List applyFormNoList, String applyFormStatus) throws Exception;

   List selectTdPaApplyFormList(TdPaApplyFormVo tdPaApplyFormVo) throws Exception;

   String selectApplyForm(String orderNo, String orderClassCode);

   List testExamList(TdPaApplyFormVo tdPaApplyFormVo) throws Exception;

   List testTmInfo(String applyFormNo) throws Exception;

   ApplyFormPrintResp queryApplyFormPrintResp(String applyFormNo, SysUser user) throws Exception;
}
