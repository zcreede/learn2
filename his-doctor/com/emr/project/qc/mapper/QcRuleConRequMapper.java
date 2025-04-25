package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRuleConRequ;
import com.emr.project.qc.domain.vo.QcRuleConRequVo;
import java.util.List;

public interface QcRuleConRequMapper {
   QcRuleConRequ selectQcRuleConRequById(Long id);

   List selectQcRuleConRequList(QcRuleConRequVo qcRuleConRequVo);

   List selectQcRuleConRequ(String emrTypeCode);

   void insertQcRuleConRequ(QcRuleConRequVo qcRuleConRequVo);

   void updateQcRuleConRequ(QcRuleConRequVo qcRuleConRequVo);

   int deleteQcRuleConRequById(Long id);

   int deleteQcRuleConRequByIds(Long[] ids);
}
