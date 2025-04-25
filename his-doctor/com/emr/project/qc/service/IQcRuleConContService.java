package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcRuleConCont;
import com.emr.project.qc.domain.vo.QcRuleConContVo;
import java.util.List;

public interface IQcRuleConContService {
   QcRuleConCont selectQcRuleConContById(Long id);

   List selectQcRuleConContList(QcRuleConContVo qcRuleConContVo) throws Exception;

   void insertQcRuleConCont(QcRuleConContVo qcRuleConContVo) throws Exception;

   void updateQcRuleConCont(QcRuleConContVo qcRuleConContVo) throws Exception;

   void updateEditFlag(QcRuleConContVo qcRuleConContVo) throws Exception;

   int deleteQcRuleConContByIds(Long[] ids);

   void deleteQcRuleConContById(Long id) throws Exception;

   String getQcContExpressionString(Long id) throws Exception;
}
