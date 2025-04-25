package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.req.PaperlessCheckReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;

public interface PaperlessCheckService {
   WebServiceResp getPaperlessCheckFlag(PaperlessCheckReq req) throws Exception;
}
