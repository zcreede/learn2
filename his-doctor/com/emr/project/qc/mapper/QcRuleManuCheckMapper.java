package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRuleManuCheck;
import com.emr.project.qc.domain.vo.QcRuleManuCheckVo;
import java.util.List;

public interface QcRuleManuCheckMapper {
   QcRuleManuCheck selectQcRuleManuCheckById(Long id);

   List selectQcRuleManuCheckList(QcRuleManuCheckVo qcRuleManuCheckVo);

   void insertQcRuleManuCheck(QcRuleManuCheckVo qcRuleManuCheckVo);

   void updateQcRuleManuCheck(QcRuleManuCheckVo qcRuleManuCheckVo);

   int deleteQcRuleManuCheckById(Long id);

   int deleteQcRuleManuCheckByIds(Long[] ids);

   List selectRulesByMrTypeElems(QcRuleManuCheckVo qcRuleManuCheckVo);

   List selectRulesByTypeElems(QcRuleManuCheckVo qcRuleManuCheckVo);

   List selectQcRuleTypeForZkList(QcRuleManuCheckVo qcRuleManuCheckVo);
}
