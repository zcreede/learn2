package com.emr.project.webservice.service;

import com.emr.project.mrhp.domain.req.MrHpDrawMainReq;
import com.emr.project.webservice.domain.resp.WebServiceGeneralResp;

public interface GeneralWebService {
   WebServiceGeneralResp getVteInfoWebApi(MrHpDrawMainReq mrHpDrawMainReq) throws Exception;
}
