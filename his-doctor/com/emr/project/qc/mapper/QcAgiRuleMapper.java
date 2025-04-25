package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.domain.vo.QcAgiRuleVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QcAgiRuleMapper {
   QcAgiRule selectQcAgiRuleById(Long id);

   QcAgiRule selectQcAgiRuleByCode(@Param("ruleCode") String ruleCode);

   QcAgiRule selectYxQcAgiRuleByCode(@Param("ruleCode") String ruleCode);

   List selectQcAgiRuleListByCode(List ruleCodeList);

   List selectQcAgiRuleListByIds(List list);

   List selectList(QcAgiRule qcAgiRule);

   List selectQcAgiRuleList(QcAgiRuleVo qcAgiRuleVo);

   int insertQcAgiRule(QcAgiRule qcAgiRule);

   int updateQcAgiRule(QcAgiRule qcAgiRule);

   int deleteQcAgiRuleById(Long id);

   int deleteQcAgiRuleByIds(Long[] ids);

   List selectQcAgiRuleEventVoList(String patientId, String emrTypeCode);

   List selectOperBeforeList(String eventCd);
}
