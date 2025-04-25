package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRuleConLogic;
import java.util.List;

public interface QcRuleConLogicMapper {
   QcRuleConLogic selectQcRuleConLogicById(Long id);

   List selectQcRuleConLogicList(QcRuleConLogic qcRuleConLogic);

   int insertQcRuleConLogic(QcRuleConLogic qcRuleConLogic);

   int updateQcRuleConLogic(QcRuleConLogic qcRuleConLogic);

   int deleteQcRuleConLogicById(Long id);

   int deleteQcRuleConLogicByIds(Long[] ids);
}
