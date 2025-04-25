package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.req.OperPlanListReq;
import com.emr.project.operation.domain.req.OperationToBePlanReq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationPlanMapper {
   List queryPlanList(OperationToBePlanReq req);

   List selectOperPlanList(OperPlanListReq req);

   int getOperTableByShiftDate(@Param("shiftDate") String shiftDate);
}
