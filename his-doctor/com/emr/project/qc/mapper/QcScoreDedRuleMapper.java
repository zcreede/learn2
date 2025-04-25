package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcScoreDedRule;
import com.emr.project.qc.domain.vo.QcRulesVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QcScoreDedRuleMapper {
   QcScoreDedRule selectQcScoreDedRuleById(Long id);

   List selectQcScoreDedRuleList(QcScoreDedRule qcScoreDedRule);

   List selectQcScoreDedByRuleAndPffaList(QcScoreDedRule qcScoreDedRule);

   int insertQcScoreDedRule(QcScoreDedRule qcScoreDedRule);

   int updateQcScoreDedRule(QcScoreDedRule qcScoreDedRule);

   int deleteQcScoreDedRuleById(Long id);

   int deleteQcScoreDedRuleByIds(Long[] ids);

   void deleteByDedId(Long dedId);

   void insertList(List list);

   List selectQcRules(QcRulesVo qcRulesVo);

   List selectDetailQcRules(@Param("dedId") Long dedId);

   List selectByDedIds(List dedIdList);

   List selectQcRuleDedList();
}
