package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.req.DipCfPageReq;
import com.emr.project.mrhp.domain.resp.MrHpRecordDipResp;

public interface DipIntService {
   MrHpRecordDipResp selectDipCfPage(DipCfPageReq req, String methord) throws Exception;
}
