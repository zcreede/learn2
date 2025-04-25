package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.domain.vo.TdCaOperationApplyVo;
import com.emr.project.operation.domain.req.OperPlanDetailReq;
import com.emr.project.operation.domain.resp.OperPlanCountResp;
import com.emr.project.pat.domain.vo.OperRoomVisitinfoVo;
import com.emr.project.webservice.domain.vo.OperationInfoVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface TdCaOperationApplyMapper {
   TdCaOperationApply selectTdCaOperationApplyById(String applyFormNo);

   List selectTdCaOperationApplyByIds(@Param("admissionNos") List admissionNos);

   List selectTdCaOperationApplyList(TdCaOperationApplyVo tdCaOperationApply);

   int insertTdCaOperationApply(TdCaOperationApply tdCaOperationApply);

   int updateTdCaOperationApply(TdCaOperationApply tdCaOperationApply);

   int updateOperationApplyManage(TdCaOperationApply tdCaOperationApply);

   int revokeTdCaOperationApply(TdCaOperationApply tdCaOperationApply);

   int revokeInRoomOperationApply(TdCaOperationApply tdCaOperationApply);

   int updateApplyNoByApplyNo(@Param("oldApplyFormNo") String oldApplyFormNo, @Param("newApplyFormNo") String ApplyFormNo);

   int deleteTdCaOperationApplyById(String applyFormNo);

   int deleteTdCaOperationApplyByIds(String[] applyFormNos);

   List selectOperAuditList(TdCaOperationApply tdCaOperationApply);

   List selectListByOrderNos(List orderNoList);

   void updateStatusByIds(List applyFormNoList, @Param("status") String status);

   Integer selectOperPatNum(String deptCode);

   List selectOperPlanStatusList(OperRoomVisitinfoVo operRoomVisitinfoVo);

   List selectOperRoomDocWorkload(TdCaOperationApplyVo tdCaOperationApplyVo);

   int updateTdCaOperationApplyByVo(OperationInfoVo operationInfoVo);

   List selectDocOpeAuth(@Param("staffCode") String staffCode);

   void selectOperResultList(Map param);

   OperPlanCountResp selectOperPlanCount(OperPlanDetailReq req);

   List selectOperRoomInfo(OperPlanDetailReq req);
}
