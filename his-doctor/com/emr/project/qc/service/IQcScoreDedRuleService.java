package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcScoreDedRule;
import com.emr.project.qc.domain.vo.QcRulesVo;
import java.util.List;

public interface IQcScoreDedRuleService {
   QcScoreDedRule selectQcScoreDedRuleById(Long id);

   List selectQcScoreDedRuleList(QcScoreDedRule qcScoreDedRule);

   List selectQcRules(QcRulesVo qcRulesVo) throws Exception;

   List selectDetailQcRules(Long dedId) throws Exception;

   int insertQcScoreDedRule(QcScoreDedRule qcScoreDedRule);

   int updateQcScoreDedRule(QcScoreDedRule qcScoreDedRule);

   int deleteQcScoreDedRuleByIds(Long[] ids);

   int deleteQcScoreDedRuleById(Long id);

   void deleteByDedId(Long dedId) throws Exception;

   void insertList(Long dedId, List ruleIdList) throws Exception;

   List selectByDedIds(List dedIdList) throws Exception;

   List selectQcRuleDedList() throws Exception;
}
