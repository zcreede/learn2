package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcRuleConLogic;
import java.util.List;

public interface IQcRuleConLogicService {
   QcRuleConLogic selectQcRuleConLogicById(Long id);

   List selectQcRuleConLogicList(QcRuleConLogic qcRuleConLogic) throws Exception;

   void insertQcRuleConLogic(QcRuleConLogic qcRuleConLogic) throws Exception;

   void updateQcRuleConLogic(QcRuleConLogic qcRuleConLogic) throws Exception;

   int deleteQcRuleConLogicByIds(Long[] ids);

   void deleteQcRuleConLogicById(Long id) throws Exception;

   void updateEditFlag(QcRuleConLogic qcRuleConLogic) throws Exception;
}
