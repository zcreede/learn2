package com.emr.project.qc.service;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.qc.domain.QcRuleManuCheck;
import com.emr.project.qc.domain.vo.QcRuleManuCheckVo;
import java.util.List;

public interface IQcRuleManuCheckService {
   QcRuleManuCheck selectQcRuleManuCheckById(Long id);

   List selectQcRuleManuCheckList(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception;

   void insertQcRuleManuCheck(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception;

   void updateQcRuleManuCheck(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception;

   int deleteQcRuleManuCheckByIds(Long[] ids);

   void deleteQcRuleManuCheck(Long id) throws Exception;

   List getRulesByMrTypeElem(List elemIdList, Index index, SubfileIndex subfileIndex, MrHp mrHp) throws Exception;

   List getOrderListRules(QcRuleManuCheckVo param) throws Exception;

   List selectQcRuleTypeList(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception;

   List selectQcRuleTypeForZkList(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception;
}
