package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.req.ClinicalPathwayReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;

public interface IClinicalPathwayService {
   WebServiceResp getPathway(ClinicalPathwayReq req) throws Exception;

   WebServiceResp getIsNeedPathway(ClinicalPathwayReq req) throws Exception;
}
