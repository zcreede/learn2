package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.req.DipServiceReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;

public interface IDipService {
   WebServiceResp updateDIP(DipServiceReq req, String mark) throws Exception;
}
