package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcRuleConRela;
import com.emr.project.qc.domain.vo.QcRuleConRelaVo;
import java.util.List;

public interface IQcRuleConRelaService {
   QcRuleConRela selectQcRuleConRelaById(Long id);

   List selectQcRuleConRelaList(QcRuleConRela qcRuleConRela) throws Exception;

   List selectQcRuleConRela(String emrTypeCode) throws Exception;

   void insertQcRuleConRela(QcRuleConRelaVo qcRuleConRelaVo) throws Exception;

   void updateQcRuleConRela(QcRuleConRelaVo qcRuleConRelaVo) throws Exception;

   int deleteQcRuleConRelaByIds(Long[] ids);

   void deleteQcRuleConRelaById(Long id) throws Exception;

   void updateEditFlag(QcRuleConRelaVo qcRuleConRelaVo) throws Exception;
}
