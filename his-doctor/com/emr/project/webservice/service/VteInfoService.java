package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.req.VteInfoReq;
import com.emr.project.webservice.domain.resp.WebServiceVteResp;

public interface VteInfoService {
   WebServiceVteResp getVteInfo(VteInfoReq vteInfoReq);

   WebServiceVteResp vteOrderVerify(String patientId) throws Exception;
}
