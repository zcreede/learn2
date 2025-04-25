package com.emr.project.qc.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRuleConValue;
import com.emr.project.qc.domain.vo.QcRuleConValueVo;
import com.emr.project.qc.mapper.QcRuleConValueMapper;
import com.emr.project.qc.service.IQcRuleConValueService;
import com.emr.project.qc.service.IQcRuleUtilService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.vo.SetViewVo;
import com.emr.project.system.mapper.SysStaElemMapper;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcRuleConValueServiceImpl implements IQcRuleConValueService {
   protected final Logger log = LoggerFactory.getLogger(QcRuleConValueServiceImpl.class);
   @Autowired
   private QcRuleConValueMapper qcRuleConValueMapper;
   @Autowired
   private SysStaElemMapper sysStaElemMapper;
   @Autowired
   private IQcRuleUtilService qcRuleUtilService;

   public QcRuleConValue selectQcRuleConValueById(Long id) {
      return this.qcRuleConValueMapper.selectQcRuleConValueById(id);
   }

   public List selectQcRuleConValueList(QcRuleConValueVo qcRuleConValueVo) throws Exception {
      return this.qcRuleConValueMapper.selectQcRuleConValueList(qcRuleConValueVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcRuleConValue(QcRuleConValueVo qcRuleConValueVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      if (qcRuleConValueVo.getRuleType().equals("1")) {
         List<SetViewVo> dictList = this.sysStaElemMapper.selectSetViewList(qcRuleConValueVo.getValuDictName());
         String valuDictCon = "";

         for(SetViewVo setViewVo : dictList) {
            if (qcRuleConValueVo.getValuDictType().equals("name")) {
               valuDictCon = valuDictCon + setViewVo.getName() + "#";
            } else {
               valuDictCon = valuDictCon + setViewVo.getCode() + "#";
            }
         }

         qcRuleConValueVo.setValuDictCon(valuDictCon.substring(0, valuDictCon.length() - 1));
      }

      List<QcCheckElem> elemList = new ArrayList();
      QcCheckElem qcCheckElem = new QcCheckElem();
      qcCheckElem.setElemName(qcRuleConValueVo.getEleName());
      qcCheckElem.setElemId(qcRuleConValueVo.getEleId());
      elemList.add(qcCheckElem);
      this.qcRuleUtilService.getAddQcCheckElemList(elemList, id, CommonConstants.QC.RULE_TYPE_CODE_3);
      qcRuleConValueVo.setId(id);
      qcRuleConValueVo.setRuleCode(id.toString());
      qcRuleConValueVo.setDelFlag("0");
      qcRuleConValueVo.setAddMeth("2");
      qcRuleConValueVo.setCrePerCode(basEmployee.getEmplNumber());
      qcRuleConValueVo.setCrePerName(basEmployee.getEmplName());
      this.getExpression(qcRuleConValueVo);
      String checkFileName = this.genRuleFile(qcRuleConValueVo);
      qcRuleConValueVo.setRuleFile(checkFileName);
      this.qcRuleConValueMapper.insertQcRuleConValue(qcRuleConValueVo);
   }

   private void getExpression(QcRuleConValueVo qcRuleConValueVo) throws Exception {
      try {
         new ArrayList();
         StringBuffer expressValue = new StringBuffer("");
         String[] expressStr = qcRuleConValueVo.getValuDictCon().split("#");

         for(int i = 0; i < expressStr.length; ++i) {
            expressValue.append("elemConText!=\"" + expressStr[i] + "\" &&");
         }

         String express = "elemId!=null && elemConText!=null&& elemId==\"" + qcRuleConValueVo.getEleId() + "\" && " + expressValue.substring(0, expressValue.length() - 2) + "";
         qcRuleConValueVo.setExpres(express);
      } catch (Exception e) {
         this.log.error(e.getMessage());
         throw e;
      }
   }

   private String genRuleFile(QcRuleConValueVo qcRuleConValueVo) throws Exception {
      try {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(qcRuleConValueVo.getId());
         ruleVo.setErrorColumn(qcRuleConValueVo.getEleId() + "|" + qcRuleConValueVo.getEleName());
         ruleVo.setErrorMsg(qcRuleConValueVo.getRuleDesc());
         ruleVo.setValidationExpression("$s: ElemAttriVo(" + qcRuleConValueVo.getExpres() + ") from $IndexSaveVo.getQcElemList");
         ruleVo.setRuleName(qcRuleConValueVo.getRuleName());
         String fileName = this.qcRuleUtilService.createRuleFile(ruleVo);
         return fileName;
      } catch (Exception e) {
         this.log.error(e.getMessage());
         throw e;
      }
   }

   public void updateQcRuleConValue(QcRuleConValueVo qcRuleConValueVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      if (qcRuleConValueVo.getRuleType().equals("1")) {
         List<SetViewVo> dictList = this.sysStaElemMapper.selectSetViewList(qcRuleConValueVo.getValuDictName());
         String valuDictCon = "";

         for(SetViewVo setViewVo : dictList) {
            if (qcRuleConValueVo.getValuDictType().equals("name")) {
               valuDictCon = valuDictCon + setViewVo.getName() + "#";
            } else {
               valuDictCon = valuDictCon + setViewVo.getCode() + "#";
            }
         }

         qcRuleConValueVo.setValuDictCon(valuDictCon.substring(0, valuDictCon.length() - 1));
      }

      List<QcCheckElem> elemList = new ArrayList();
      QcCheckElem qcCheckElem = new QcCheckElem();
      qcCheckElem.setElemName(qcRuleConValueVo.getEleName());
      qcCheckElem.setElemId(qcRuleConValueVo.getEleId());
      elemList.add(qcCheckElem);
      this.qcRuleUtilService.getAddQcCheckElemList(elemList, qcRuleConValueVo.getId(), CommonConstants.QC.RULE_TYPE_CODE_3);
      qcRuleConValueVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConValueVo.setAltPerName(basEmployee.getEmplName());
      this.getExpression(qcRuleConValueVo);
      String checkFileName = this.genRuleFile(qcRuleConValueVo);
      qcRuleConValueVo.setRuleFile(checkFileName);
      this.qcRuleConValueMapper.updateQcRuleConValue(qcRuleConValueVo);
   }

   public void deleteQcRuleConValueByIds(Long[] ids) {
      this.qcRuleConValueMapper.deleteQcRuleConValueByIds(ids);
   }

   public void deleteQcRuleConValueById(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      QcRuleConValueVo qcRuleConValueVo = new QcRuleConValueVo();
      qcRuleConValueVo.setId(id);
      qcRuleConValueVo.setDelFlag("1");
      qcRuleConValueVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConValueVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConValueMapper.updateQcRuleConValue(qcRuleConValueVo);
   }

   public void updateValidFlag(QcRuleConValueVo qcRuleConValueVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleConValueVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConValueVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConValueMapper.updateQcRuleConValue(qcRuleConValueVo);
   }
}
