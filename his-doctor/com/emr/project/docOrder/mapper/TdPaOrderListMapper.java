package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.vo.OrderListSearchVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderListMapper {
   List selectOrderLongListPage(OrderListSearchVo param);

   List selectOrderLongAllListPage(OrderListSearchVo param);

   /** @deprecated */
   @Deprecated
   List selectOrderTempListPage(OrderListSearchVo param);

   List selectOrderDecoctionListPage(OrderListSearchVo param);

   List selectOrderClassCode2Detail(@Param("orderNo") String orderNo);

   List selectOrderTestDetail(@Param("orderNo") String orderNo);

   List selectPatOrderDeptList(@Param("patientId") String patientId);
}
