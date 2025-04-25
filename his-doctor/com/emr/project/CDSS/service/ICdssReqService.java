package com.emr.project.CDSS.service;

import com.emr.project.CDSS.domain.vo.xyt.CdssPageResVo;
import com.emr.project.CDSS.domain.vo.xyt.CdssResVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;

public interface ICdssReqService {
   CdssPageResVo getCommonPage_mk(String pageName) throws Exception;

   CdssResVo getCheckResult_mk(String patientId, TdPaApplyFormVo tdPaApplyForm) throws Exception;
}
