package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.req.ProcessingOrdersReq;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnResultVo;
import com.emr.project.docOrder.service.IInpatientSuspendOrderService;
import com.emr.project.docOrder.service.IOrderPerFormVoidRevokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderPerFormVoidRevokeServiceImpl implements IOrderPerFormVoidRevokeService {
   @Autowired
   private IInpatientSuspendOrderService inpatientSuspendOrderService;

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public DrugListAndPerformReturnResultVo revokeAndVoidOrderPerform(ProcessingOrdersReq req, String ip) throws Exception {
      this.inpatientSuspendOrderService.revokeOrderPerform(req);
      DrugListAndPerformReturnResultVo resultVo = this.inpatientSuspendOrderService.voidOrderPerform(req, ip);
      return resultVo;
   }
}
