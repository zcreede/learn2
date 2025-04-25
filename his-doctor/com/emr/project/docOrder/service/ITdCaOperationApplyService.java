package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.domain.vo.OpeAuthorityVo;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderStopSignVo;
import com.emr.project.docOrder.domain.vo.TdCaOperationApplyVo;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.pat.domain.vo.OperRoomVisitinfoVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import java.util.List;
import java.util.Map;

public interface ITdCaOperationApplyService {
   TdCaOperationApply selectTdCaOperationApplyById(String applyFormNo);

   List selectTdCaOperationApplyByIds(List admissionNos);

   List selectTdCaOperationApplyList(TdCaOperationApplyVo tdCaOperationApply) throws Exception;

   List selectOperUnAuditList(TdCaOperationApplyVo tdCaOperationApply) throws Exception;

   List selectOperAuditList(TdCaOperationApplyVo tdCaOperationApply) throws Exception;

   List selectOperPlanList(TdCaOperationApplyVo tdCaOperationApply) throws Exception;

   void insertTdCaOperationApply(TdCaOperationApply tdCaOperationApply) throws Exception;

   void updateTdCaOperationApply(TdCaOperationApply tdCaOperationApply) throws Exception;

   int deleteTdCaOperationApplyByIds(String[] applyFormNos);

   int deleteTdCaOperationApplyById(String applyFormNo);

   void saveTdCaOperationApply(TdCaOperationApplyVo tdCaOperationApply, VisitinfoPersonalVo personalVo) throws Exception;

   void submitTdCaOperationApply(TdCaOperationApplyVo tdCaOperationApply, List orderItemList, OrderCommitVo orderCommitVo) throws Exception;

   void cancelSubmitOpeApply(TdCaOperationApply tdCaOperationApply, OrderStopSignVo orderStopSignVo) throws Exception;

   void deleteTdCaOperationApply(TdCaOperationApply tdCaOperationApply, OrderStopSignVo orderStopSignVo) throws Exception;

   List selectOperBeforeList(TdCaOperationApply tdCaOperationApply, String eventCd) throws Exception;

   List selectOpeIndexByEmrType(TdCaOperationApplyVo tdCaOperationApply) throws Exception;

   void updateApplyAudit(TdCaOperationApply tdCaOperationApply) throws Exception;

   List selectOperFlowChart(String applyFormNo) throws Exception;

   Map selectHisCheckResult(String patientId) throws Exception;

   void addOperEmr(TdCaOperationApplyVo tdCaOperationApplyVo) throws Exception;

   List selectListByOrderNos(List orderNoList) throws Exception;

   void updateStatusByIds(List applyFormNoList, String status) throws Exception;

   Map selectOperDateTime() throws Exception;

   EmrTaskInfoVo setEmrTaskInfo(IndexVo indexVo, VisitinfoPersonalVo personalVo) throws Exception;

   List selectOperPlanStatusList(OperRoomVisitinfoVo operRoomVisitinfoVo) throws Exception;

   List selectOperRoomDocWorkload(TdCaOperationApplyVo tdCaOperationApplyVo) throws Exception;

   OpeAuthorityVo selectDocOpeAuth(String docCd, String operLevel) throws Exception;
}
