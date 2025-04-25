package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcScoreDedRule;
import com.emr.project.qc.domain.vo.QcRulesVo;
import com.emr.project.qc.mapper.QcScoreDedRuleMapper;
import com.emr.project.qc.service.IQcCheckElemService;
import com.emr.project.qc.service.IQcScoreDedRuleService;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcScoreDedRuleServiceImpl implements IQcScoreDedRuleService {
   @Autowired
   private QcScoreDedRuleMapper qcScoreDedRuleMapper;
   @Autowired
   private IQcCheckElemService qcCheckElemService;

   public QcScoreDedRule selectQcScoreDedRuleById(Long id) {
      return this.qcScoreDedRuleMapper.selectQcScoreDedRuleById(id);
   }

   public List selectQcScoreDedRuleList(QcScoreDedRule qcScoreDedRule) {
      return this.qcScoreDedRuleMapper.selectQcScoreDedRuleList(qcScoreDedRule);
   }

   public List selectQcRules(QcRulesVo qcRulesVo) throws Exception {
      List<QcRulesVo> list = this.qcScoreDedRuleMapper.selectQcRules(qcRulesVo);
      this.setQcCheckElem(list);
      return list;
   }

   public List selectDetailQcRules(Long dedId) throws Exception {
      List<QcRulesVo> list = this.qcScoreDedRuleMapper.selectDetailQcRules(dedId);
      this.setQcCheckElem(list);
      return list;
   }

   private void setQcCheckElem(List list) throws Exception {
      List<QcRulesVo> listNew = (List)list.stream().filter((t) -> StringUtils.isBlank(t.getElemFlag()) || StringUtils.isNotBlank(t.getElemFlag()) && !t.getElemFlag().equals("1")).collect(Collectors.toList());
      List<Long> checkIdList = (List)listNew.stream().map((t) -> t.getId()).collect(Collectors.toList());
      if (checkIdList != null && !checkIdList.isEmpty()) {
         List<QcCheckElem> qcCheckElemList = this.qcCheckElemService.selectQcCheckElemByIds(checkIdList);
         Map<Long, List<QcCheckElem>> qcCheckElemListMap = (Map)qcCheckElemList.stream().collect(Collectors.groupingBy((t) -> t.getCheckId()));

         for(QcRulesVo rulesVo : list) {
            Long checkId = rulesVo.getId();
            List<QcCheckElem> tempList = (List)qcCheckElemListMap.get(checkId);
            rulesVo.setQcCheckElemList(tempList);
         }
      }

   }

   public int insertQcScoreDedRule(QcScoreDedRule qcScoreDedRule) {
      return this.qcScoreDedRuleMapper.insertQcScoreDedRule(qcScoreDedRule);
   }

   public int updateQcScoreDedRule(QcScoreDedRule qcScoreDedRule) {
      return this.qcScoreDedRuleMapper.updateQcScoreDedRule(qcScoreDedRule);
   }

   public int deleteQcScoreDedRuleByIds(Long[] ids) {
      return this.qcScoreDedRuleMapper.deleteQcScoreDedRuleByIds(ids);
   }

   public int deleteQcScoreDedRuleById(Long id) {
      return this.qcScoreDedRuleMapper.deleteQcScoreDedRuleById(id);
   }

   public void deleteByDedId(Long dedId) throws Exception {
      this.qcScoreDedRuleMapper.deleteByDedId(dedId);
   }

   public void insertList(Long dedId, List ruleIdList) throws Exception {
      List<QcScoreDedRule> list = new ArrayList(ruleIdList.size());

      for(Long ruleId : ruleIdList) {
         QcScoreDedRule qcScoreDedRule = new QcScoreDedRule();
         qcScoreDedRule.setId(SnowIdUtils.uniqueLong());
         qcScoreDedRule.setDedId(dedId);
         qcScoreDedRule.setRuleId(ruleId);
         qcScoreDedRule.setValidFlag("1");
         SysUser user = SecurityUtils.getLoginUser().getUser();
         qcScoreDedRule.setCrePerCode(user.getUserName());
         qcScoreDedRule.setCrePerName(user.getNickName());
         list.add(qcScoreDedRule);
      }

      this.qcScoreDedRuleMapper.insertList(list);
   }

   public List selectByDedIds(List dedIdList) throws Exception {
      List<QcScoreDedRule> list = null;
      if (dedIdList != null && !dedIdList.isEmpty()) {
         list = this.qcScoreDedRuleMapper.selectByDedIds(dedIdList);
      }

      return list;
   }

   public List selectQcRuleDedList() throws Exception {
      return this.qcScoreDedRuleMapper.selectQcRuleDedList();
   }
}
