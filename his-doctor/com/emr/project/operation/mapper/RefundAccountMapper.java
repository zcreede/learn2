package com.emr.project.operation.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefundAccountMapper {
   List getRefundList(@Param("admissionNo") String admissionNo, @Param("chargeName") String chargeName, @Param("threeLevelCode") String threeLevelCode, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("deptCode") String deptCode, @Param("list") List list, @Param("materiallist") List materiallist);

   List getRefundApplyList(@Param("admissionNo") String admissionNo, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("deptCode") String deptCode);
}
