package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.InpatientOrderPerform;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.req.ProcessingOrdersReq;
import com.emr.project.docOrder.domain.req.SuppPrintOrderDataListReq;
import com.emr.project.docOrder.domain.req.SuspendOrderListReq;
import com.emr.project.docOrder.domain.req.TodayOrderOperationReq;
import com.emr.project.docOrder.domain.vo.AllergyRecordDTO;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnResultVo;
import com.emr.project.docOrder.domain.vo.InpatientTodayOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.OrderDoHandleDrugDoseVo;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IInpatientSuspendOrderService {
   List selectSuspendOrderList(SuspendOrderListReq req);

   void updatePerformStart(List inpatientOrderPerformList);

   void updatePerformStatus(InpatientOrderPerform inPerform, String status, String operatorCode, String operatorNo);

   OrderDoHandleDrugDoseVo updatePerformStartUse(List inop, List printList, List drugAndClinList, SysUser user, String ip) throws Exception;

   List selectTodayOrderOperation(List allList, Integer pageNum, Integer pageSize);

   Map selectTodayOrderListDetail(InpatientTodayOrderPerformDTO req);

   List getSuppPrintData(SuppPrintOrderDataListReq req);

   void updateSkinTestResults(AllergyRecordDTO req) throws Exception;

   Boolean selectPerformStatus(String performListNo, List sortNumberList, String type);

   List getSysDictDataList();

   void revokeOrderPerform(ProcessingOrdersReq req) throws Exception;

   DrugListAndPerformReturnResultVo voidOrderPerform(ProcessingOrdersReq req, String ip) throws Exception;

   List selectTodayOrderOperationAllList(TodayOrderOperationReq req);

   List selectOrderItemByOrderNoList(List orderNoList);

   TdPaOrder selectOrderByOrderNo(String orderNo);

   void updateExecTimeByOrderNoList(List orderNoList, Date executionTime) throws Exception;
}
