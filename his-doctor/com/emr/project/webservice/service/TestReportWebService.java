package com.emr.project.webservice.service;

import com.emr.project.pat.domain.vo.TestReportKbVo;
import javax.jws.WebService;

@WebService
public interface TestReportWebService {
   TestReportKbVo resultKnowledgeBase(String rptItemId) throws Exception;
}
