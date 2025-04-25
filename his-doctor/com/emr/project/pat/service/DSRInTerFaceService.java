package com.emr.project.pat.service;

import com.emr.project.pat.domain.resp.DsrReturnResp;

public interface DSRInTerFaceService {
   DsrReturnResp dsrReport(String bah) throws Exception;
}
