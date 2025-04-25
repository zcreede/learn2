package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.req.RuleAnalysisVo;
import com.emr.project.pat.domain.Visitinfo;
import java.util.List;

public interface IRuleAnalysisService {
   RuleAnalysisVo ruleAnalysis(List orderSaveVoList, Visitinfo visitinfo) throws Exception;

   void ruleAnalysisQuery(String msgid) throws Exception;
}
