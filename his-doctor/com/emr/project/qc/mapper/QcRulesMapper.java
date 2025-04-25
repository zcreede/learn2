package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRules;
import com.emr.project.qc.domain.vo.QcRulesVo;
import java.util.List;

public interface QcRulesMapper {
   QcRules selectQcRulesById(Long id);

   List selectQcRulesList(QcRules qcRules);

   List selectQcRulesListTop20(QcRules qcRules);

   List selectRulesByMrTypeElems(QcRulesVo qcRulesVo);

   List selectScoreRulesList(QcRulesVo qcRulesVo);
}
