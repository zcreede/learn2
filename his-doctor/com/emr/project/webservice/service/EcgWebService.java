package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.req.EcgReq;
import com.emr.project.webservice.domain.resp.EcgRes;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface EcgWebService {
   @WebMethod
   EcgRes receiveEcg(@WebParam(name = "Request") EcgReq req) throws Exception;
}
