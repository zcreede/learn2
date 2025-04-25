package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcRuleConRange;
import com.emr.project.qc.domain.vo.QcRuleConRangeVo;
import java.util.List;

public interface IQcRuleConRangeService {
   QcRuleConRange selectQcRuleConRangeById(Long id);

   List selectQcRuleConRangeList(QcRuleConRangeVo qcRuleConRangeVo) throws Exception;

   void insertQcRuleConRange(QcRuleConRangeVo qcRuleConRangeVo) throws Exception;

   void updateQcRuleConRange(QcRuleConRangeVo qcRuleConRangeVo) throws Exception;

   int deleteQcRuleConRangeByIds(Long[] ids);

   void deleteQcRuleConRangeById(Long id) throws Exception;

   void updateValidFlag(QcRuleConRangeVo qcRuleConRangeVo) throws Exception;
}
