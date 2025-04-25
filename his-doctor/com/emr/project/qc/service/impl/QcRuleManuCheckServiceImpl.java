package com.emr.project.qc.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRuleManuCheck;
import com.emr.project.qc.domain.vo.QcRuleManuCheckVo;
import com.emr.project.qc.mapper.QcRuleManuCheckMapper;
import com.emr.project.qc.service.IQcCheckElemService;
import com.emr.project.qc.service.IQcRuleManuCheckService;
import com.emr.project.qc.service.IQcRuleUtilService;
import com.emr.project.qc.util.IndexUtil;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.service.ISysDictTypeService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcRuleManuCheckServiceImpl implements IQcRuleManuCheckService {
   @Autowired
   private QcRuleManuCheckMapper qcRuleManuCheckMapper;
   @Autowired
   private IQcCheckElemService qcCheckElemService;
   @Autowired
   private IQcRuleUtilService qcRuleUtilService;
   @Autowired
   private ISysDictTypeService sysDictTypeService;

   public QcRuleManuCheck selectQcRuleManuCheckById(Long id) {
      return this.qcRuleManuCheckMapper.selectQcRuleManuCheckById(id);
   }

   public List selectQcRuleManuCheckList(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception {
      List<QcRuleManuCheckVo> list = this.qcRuleManuCheckMapper.selectQcRuleManuCheckList(qcRuleManuCheckVo);
      if (list != null && list.size() > 0) {
         QcCheckElem qcCheckElem1 = new QcCheckElem();
         qcCheckElem1.setCheckIdList((List)list.stream().map((s) -> s.getId()).collect(Collectors.toList()));
         List<QcCheckElem> checkElemList = this.qcCheckElemService.selectQcCheckElemList(qcCheckElem1);
         Map<Long, List<QcCheckElem>> mapList = (Map)checkElemList.stream().collect(Collectors.groupingBy((s) -> s.getCheckId()));

         for(QcRuleManuCheckVo qcRuleManuCheck : list) {
            List<QcCheckElem> elemList = (List)mapList.get(qcRuleManuCheck.getId());
            qcRuleManuCheck.setElemVoList(elemList);
         }
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcRuleManuCheck(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      if (qcRuleManuCheckVo.getElemVoList() != null && !qcRuleManuCheckVo.getElemVoList().isEmpty()) {
         this.qcRuleUtilService.getAddQcCheckElemList(qcRuleManuCheckVo.getElemVoList(), id, CommonConstants.QC.RULE_TYPE_CODE_8);
      }

      qcRuleManuCheckVo.setId(id);
      qcRuleManuCheckVo.setRuleCode(id.toString());
      qcRuleManuCheckVo.setDelFlag("0");
      qcRuleManuCheckVo.setCrePerCode(basEmployee.getEmplNumber());
      qcRuleManuCheckVo.setCrePerName(basEmployee.getEmplName());
      this.qcRuleManuCheckMapper.insertQcRuleManuCheck(qcRuleManuCheckVo);
   }

   public void updateQcRuleManuCheck(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = qcRuleManuCheckVo.getId();
      this.qcCheckElemService.deleteQcCheckElemById(id);
      if (qcRuleManuCheckVo.getElemVoList() != null && !qcRuleManuCheckVo.getElemVoList().isEmpty()) {
         this.qcRuleUtilService.getAddQcCheckElemList(qcRuleManuCheckVo.getElemVoList(), id, CommonConstants.QC.RULE_TYPE_CODE_8);
      }

      qcRuleManuCheckVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleManuCheckVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleManuCheckMapper.updateQcRuleManuCheck(qcRuleManuCheckVo);
   }

   public int deleteQcRuleManuCheckByIds(Long[] ids) {
      return this.qcRuleManuCheckMapper.deleteQcRuleManuCheckByIds(ids);
   }

   public void deleteQcRuleManuCheck(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      QcRuleManuCheckVo qcRuleManuCheck = new QcRuleManuCheckVo();
      qcRuleManuCheck.setId(id);
      qcRuleManuCheck.setDelFlag("1");
      qcRuleManuCheck.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleManuCheck.setAltPerName(basEmployee.getEmplName());
      this.qcRuleManuCheckMapper.updateQcRuleManuCheck(qcRuleManuCheck);
   }

   public List getRulesByMrTypeElem(List elemIdList, Index index, SubfileIndex subfileIndex, MrHp mrHp) throws Exception {
      List<SysDictData> dictDataList = this.sysDictTypeService.selectDictDataByType("s004");
      Map<String, Object> mrTypeMap = IndexUtil.getIndexMrTypeAndName(dictDataList, mrHp, index, subfileIndex);
      QcRuleManuCheckVo param = new QcRuleManuCheckVo();
      param.setElemList(elemIdList);
      param.setEmrTypeCode(mrTypeMap.get("mrType").toString());
      return this.qcRuleManuCheckMapper.selectRulesByMrTypeElems(param);
   }

   public List getOrderListRules(QcRuleManuCheckVo param) throws Exception {
      return this.qcRuleManuCheckMapper.selectRulesByMrTypeElems(param);
   }

   public List selectQcRuleTypeList(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception {
      return this.qcRuleManuCheckMapper.selectRulesByTypeElems(qcRuleManuCheckVo);
   }

   public List selectQcRuleTypeForZkList(QcRuleManuCheckVo qcRuleManuCheckVo) throws Exception {
      return this.qcRuleManuCheckMapper.selectQcRuleTypeForZkList(qcRuleManuCheckVo);
   }
}
