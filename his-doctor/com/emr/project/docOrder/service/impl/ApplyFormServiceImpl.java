package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.service.ApplyFormService;
import com.emr.project.operation.domain.ApplyForm;
import com.emr.project.operation.mapper.ApplyFormMapper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyFormServiceImpl implements ApplyFormService {
   @Autowired
   private ApplyFormMapper applyFormMapper;

   public void updateApplyFormStatus(String applyFormNo, String applyFormStatus, Date settleAccountDate, String settleAccountCode, String settleAccountNo, String settleAccountName) throws Exception {
      ApplyForm applyFormParam = new ApplyForm(applyFormNo, applyFormStatus, settleAccountDate, settleAccountCode, settleAccountNo, settleAccountName);
      this.applyFormMapper.updateByPrimaryKeySelective(applyFormParam);
   }

   public ApplyForm searchApplyForm(String OrderNo, String orderSortNumber) throws Exception {
      return this.applyFormMapper.searchApplyForm(OrderNo, orderSortNumber);
   }
}
