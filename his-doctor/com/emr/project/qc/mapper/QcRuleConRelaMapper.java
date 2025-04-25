package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRuleConRela;
import com.emr.project.qc.domain.vo.QcRuleConRelaVo;
import java.util.List;

public interface QcRuleConRelaMapper {
   QcRuleConRela selectQcRuleConRelaById(Long id);

   List selectQcRuleConRelaList(QcRuleConRela qcRuleConRela);

   List selectQcRuleConRela(String emrTypeCode);

   int insertQcRuleConRela(QcRuleConRelaVo qcRuleConRelaVo);

   int updateQcRuleConRela(QcRuleConRelaVo qcRuleConRelaVo);

   int deleteQcRuleConRelaById(Long id);

   int deleteQcRuleConRelaByIds(Long[] ids);
}
