package com.emr.project.webservice.service;

import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.webservice.domain.vo.OperationInfoVo;

public interface OperationService {
   int updateOperationInfo(OperationInfoVo operationInfoVo) throws Exception;

   TdCaOperationApply selectTdCaOperationApplyById(String applyFormNo);
}
