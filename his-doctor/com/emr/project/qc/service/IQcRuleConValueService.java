package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcRuleConValue;
import com.emr.project.qc.domain.vo.QcRuleConValueVo;
import java.util.List;

public interface IQcRuleConValueService {
   QcRuleConValue selectQcRuleConValueById(Long id);

   List selectQcRuleConValueList(QcRuleConValueVo qcRuleConValueVo) throws Exception;

   void insertQcRuleConValue(QcRuleConValueVo qcRuleConValueVo) throws Exception;

   void updateQcRuleConValue(QcRuleConValueVo qcRuleConValueVo) throws Exception;

   void deleteQcRuleConValueByIds(Long[] ids) throws Exception;

   void deleteQcRuleConValueById(Long id) throws Exception;

   void updateValidFlag(QcRuleConValueVo qcRuleConValueVo) throws Exception;
}
