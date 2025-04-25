package com.emr.project.operation.service;

import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.operation.domain.req.OperPlanDetailReq;
import com.emr.project.operation.domain.req.OperPlanListReq;
import com.emr.project.operation.domain.req.OperPlanRejectReq;
import com.emr.project.operation.domain.req.OperationToBePlanReq;
import com.emr.project.operation.domain.req.RoomListReq;
import com.emr.project.operation.domain.resp.OperPlanCountResp;
import java.util.List;

public interface OperationPlanService {
   List queryPlanList(OperationToBePlanReq req) throws Exception;

   List selectOperPlanList(OperPlanListReq req) throws Exception;

   int getOperTableByShiftDate(String shiftDate) throws Exception;

   int updateOperPlan(OperPlanRejectReq req);

   TdCaOperationApply selectOperPlanDetail(OperPlanDetailReq req) throws Exception;

   OperPlanCountResp selectOperPlanCount(OperPlanDetailReq req) throws Exception;

   List selectOperRoomInfo(OperPlanDetailReq req) throws Exception;

   List selectRoomList(RoomListReq req) throws Exception;
}
