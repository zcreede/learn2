package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcRuleConRequ;
import com.emr.project.qc.domain.vo.QcRuleConRequVo;
import java.util.List;

public interface IQcRuleConRequService {
   QcRuleConRequ selectQcRuleConRequById(Long id);

   List selectQcRuleConRequList(QcRuleConRequVo qcRuleConRequVo) throws Exception;

   List selectQcRuleConRequ(String emrTypeCode) throws Exception;

   void insertQcRuleConRequ(QcRuleConRequVo qcRuleConRequVo) throws Exception;

   void updateQcRuleConRequ(QcRuleConRequVo qcRuleConRequVo) throws Exception;

   int deleteQcRuleConRequByIds(Long[] ids);

   void deleteQcRuleConRequById(Long id) throws Exception;

   void updateValidFlag(QcRuleConRequVo qcRuleConRequVo) throws Exception;
}
