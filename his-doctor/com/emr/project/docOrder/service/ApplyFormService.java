package com.emr.project.docOrder.service;

import com.emr.project.operation.domain.ApplyForm;
import java.util.Date;

public interface ApplyFormService {
   void updateApplyFormStatus(String applyFormNo, String applyFormStatus, Date settleAccountDate, String settleAccountCode, String settleAccountNo, String settleAccountName) throws Exception;

   ApplyForm searchApplyForm(String OrderNo, String orderSortNumber) throws Exception;
}
