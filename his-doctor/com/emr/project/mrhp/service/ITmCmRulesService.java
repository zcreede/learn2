package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.TmCmRules;
import java.util.List;

public interface ITmCmRulesService {
   List selectTmCmRulesList(TmCmRules tmCmRules) throws Exception;
}
