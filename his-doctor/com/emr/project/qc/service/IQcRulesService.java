package com.emr.project.qc.service;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.qc.domain.QcRules;
import com.emr.project.qc.domain.vo.QcRulesVo;
import java.util.List;

public interface IQcRulesService {
   QcRules selectQcRulesById(Long id);

   List selectQcRulesList(QcRules qcRules);

   List selectQcRulesListTop20(QcRules qcRules);

   List getRulesByMrTypeElem(List elemIdList, Index index, SubfileIndex subfileIndex, MrHp mrHp) throws Exception;

   List selectScoreRulesList(QcRulesVo qcRulesVo) throws Exception;
}
