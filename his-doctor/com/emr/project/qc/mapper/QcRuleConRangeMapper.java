package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRuleConRange;
import com.emr.project.qc.domain.vo.QcRuleConRangeVo;
import java.util.List;

public interface QcRuleConRangeMapper {
   QcRuleConRange selectQcRuleConRangeById(Long id);

   List selectQcRuleConRangeList(QcRuleConRangeVo qcRuleConRangeVo);

   int insertQcRuleConRange(QcRuleConRange qcRuleConRange);

   int updateQcRuleConRange(QcRuleConRange qcRuleConRange);

   int deleteQcRuleConRangeById(Long id);

   int deleteQcRuleConRangeByIds(Long[] ids);
}
