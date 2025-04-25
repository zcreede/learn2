package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.req.MrHpRecordDipReq;
import com.emr.project.mrhp.domain.resp.MrHpRecordDipResp;

public interface IMrHpDipQcService {
   MrHpRecordDipResp selectDipQcUrl(MrHpRecordDipReq mrHpRecordDipReq) throws Exception;
}
