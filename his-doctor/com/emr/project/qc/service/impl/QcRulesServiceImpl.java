package com.emr.project.qc.service.impl;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRules;
import com.emr.project.qc.domain.vo.QcRulesVo;
import com.emr.project.qc.mapper.QcRulesMapper;
import com.emr.project.qc.service.IQcRulesService;
import com.emr.project.qc.util.IndexUtil;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.service.ISysDictTypeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcRulesServiceImpl implements IQcRulesService {
   @Autowired
   private QcRulesMapper qcRulesMapper;
   @Autowired
   private ISysDictTypeService sysDictTypeService;

   public QcRules selectQcRulesById(Long id) {
      return this.qcRulesMapper.selectQcRulesById(id);
   }

   public List selectQcRulesList(QcRules qcRules) {
      return this.qcRulesMapper.selectQcRulesList(qcRules);
   }

   public List selectQcRulesListTop20(QcRules qcRules) {
      return this.qcRulesMapper.selectQcRulesListTop20(qcRules);
   }

   public List getRulesByMrTypeElem(List elemIdList, Index index, SubfileIndex subfileIndex, MrHp mrHp) throws Exception {
      List<SysDictData> dictDataList = this.sysDictTypeService.selectDictDataByType("s004");
      Map<String, Object> mrTypeMap = IndexUtil.getIndexMrTypeAndName(dictDataList, mrHp, index, subfileIndex);
      QcRulesVo qcRulesVo = new QcRulesVo();
      qcRulesVo.setEmrTypeCode(mrTypeMap.get("mrType").toString());
      List<QcCheckElem> qcCheckElemList = new ArrayList(elemIdList.size());

      for(String elemId : elemIdList) {
         QcCheckElem qcCheckElem = new QcCheckElem();
         qcCheckElem.setElemId(elemId);
         qcCheckElemList.add(qcCheckElem);
      }

      qcRulesVo.setQcCheckElemList(qcCheckElemList);
      return this.qcRulesMapper.selectRulesByMrTypeElems(qcRulesVo);
   }

   public List selectScoreRulesList(QcRulesVo qcRulesVo) throws Exception {
      return this.qcRulesMapper.selectScoreRulesList(qcRulesVo);
   }
}
