package com.emr.project.mrhp.service.impl;

import com.emr.project.mrhp.domain.TmCmRules;
import com.emr.project.mrhp.mapper.TmCmRulesMapper;
import com.emr.project.mrhp.service.ITmCmRulesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmCmRulesServiceImpl implements ITmCmRulesService {
   @Autowired
   private TmCmRulesMapper tmCmRulesMapper;

   public List selectTmCmRulesList(TmCmRules tmCmRules) throws Exception {
      return this.tmCmRulesMapper.selectTmCmRulesList(tmCmRules);
   }
}
