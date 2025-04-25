package com.emr.project.operation.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.operation.domain.Tfsqb;
import com.emr.project.operation.domain.req.RefundAccountListReq;
import com.emr.project.operation.domain.req.RefundAccountReq;
import com.emr.project.operation.domain.req.RefundApplyReq;
import com.emr.project.operation.domain.req.RefundBatchToVoidReq;
import java.util.List;

public interface IRefundAccountService {
   List getRefundList(RefundAccountListReq req) throws Exception;

   AjaxResult checkRefundNum(AjaxResult resultMsg, List list);

   List saveRefundAccount(RefundAccountReq req) throws Exception;

   List getRefundApplyList(RefundApplyReq req);

   void updateRefundBatchToVoid(RefundBatchToVoidReq req);

   List printRefundAccount(List billNoList) throws Exception;

   Tfsqb queryByKey(String orgCd, String admissionNo, String serialNnumber, String serialNumberXh) throws Exception;
}
