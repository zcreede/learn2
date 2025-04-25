package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.req.VteInfoReq;
import com.emr.project.webservice.domain.resp.WebServiceVteResp;

public interface IVteInfoWebService {
   WebServiceVteResp getVteInfoWebApi(VteInfoReq vteInfoReq) throws Exception;
}
