package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRuleConCont;
import com.emr.project.qc.domain.vo.QcRuleConContVo;
import java.util.List;

public interface QcRuleConContMapper {
   QcRuleConCont selectQcRuleConContById(Long id);

   List selectQcRuleConContList(QcRuleConContVo qcRuleConContVo);

   void insertQcRuleConCont(QcRuleConContVo qcRuleConContVo);

   void updateQcRuleConCont(QcRuleConContVo qcRuleConContVo);

   void deleteQcRuleConContById(Long id);

   int deleteQcRuleConContByIds(Long[] ids);
}
