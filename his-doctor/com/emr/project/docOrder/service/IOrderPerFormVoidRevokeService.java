package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.req.ProcessingOrdersReq;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnResultVo;

public interface IOrderPerFormVoidRevokeService {
   DrugListAndPerformReturnResultVo revokeAndVoidOrderPerform(ProcessingOrdersReq req, String ip) throws Exception;
}
