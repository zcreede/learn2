package com.emr.project.docOrder.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.req.OderPerformDetailReq;
import com.emr.project.docOrder.domain.vo.OderPerformResultVo;
import com.emr.project.docOrder.domain.vo.OrderListBaseInfoVo;
import com.emr.project.docOrder.domain.vo.OrderListInfoVo;
import com.emr.project.docOrder.domain.vo.OrderListPrintVo;
import com.emr.project.docOrder.domain.vo.OrderListSearchVo;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public interface ITdPaOrderListService {
   OrderListBaseInfoVo queryOrderListBaseInfo(String patientId) throws Exception;

   OrderListInfoVo operationLongOrderList(OrderListSearchVo orderListSearchVo, Map groupOrderMap, List list, int pageSize, String blankStr) throws Exception;

   List queryOrderTempListPage(int pageNum, String patientId) throws Exception;

   OrderListInfoVo queryDecoctionTotal(OrderListSearchVo orderListSearchVo, int pageSize, String blankStr) throws Exception;

   OrderListInfoVo operationDecoctionOrderList(OrderListSearchVo orderListSearchVo, Map groupOrderMap, List list, int pageSize, String blankStr) throws Exception;

   OrderListInfoVo getPageTotal(OrderListSearchVo orderListSearchVo, int pageSize, String blankStr, String allFlag) throws Exception;

   List queryPrintData(OrderListPrintVo orderListPrintVo, int pageSize, String blankStr, String allFlag) throws Exception;

   AjaxResult getExcelOrderTotal(OrderListSearchVo orderListSearchVo, HttpServletResponse httpResponse, String allFlag) throws Exception;

   AjaxResult getDecoctionExcelTotal(OrderListSearchVo orderListSearchVo, HttpServletResponse httpResponse) throws Exception;

   OderPerformResultVo selectOderPerform(OderPerformDetailReq req) throws Exception;

   List queryOrderProcess(TdPaOrderItem orderItem);

   List selectPatOrderDeptList(String patientId) throws Exception;
}
