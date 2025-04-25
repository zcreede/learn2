package com.emr.project.qc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRuleConRela;
import com.emr.project.qc.domain.vo.QcRuleConRelaVo;
import com.emr.project.qc.mapper.QcRuleConRelaMapper;
import com.emr.project.qc.service.IQcRuleConRelaService;
import com.emr.project.qc.service.IQcRuleUtilService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcRuleConRelaServiceImpl implements IQcRuleConRelaService {
   protected final Logger log = LoggerFactory.getLogger(QcRuleConRelaServiceImpl.class);
   @Autowired
   private QcRuleConRelaMapper qcRuleConRelaMapper;
   @Autowired
   private IQcRuleUtilService qcRuleUtilService;

   public QcRuleConRela selectQcRuleConRelaById(Long id) {
      return this.qcRuleConRelaMapper.selectQcRuleConRelaById(id);
   }

   public List selectQcRuleConRelaList(QcRuleConRela qcRuleConRela) throws Exception {
      return this.qcRuleConRelaMapper.selectQcRuleConRelaList(qcRuleConRela);
   }

   public List selectQcRuleConRela(String emrTypeCode) throws Exception {
      return this.qcRuleConRelaMapper.selectQcRuleConRela(emrTypeCode);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcRuleConRela(QcRuleConRelaVo qcRuleConRelaVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      List<QcCheckElem> elemList = new ArrayList();
      QcCheckElem qcCheckElem = new QcCheckElem();
      qcCheckElem.setElemName(qcRuleConRelaVo.getEleName1());
      qcCheckElem.setElemId(qcRuleConRelaVo.getEleId1());
      elemList.add(qcCheckElem);
      this.qcRuleUtilService.getAddQcCheckElemList(elemList, id, CommonConstants.QC.RULE_TYPE_CODE_5);
      qcRuleConRelaVo.setId(id);
      qcRuleConRelaVo.setRuleCode(id.toString());
      qcRuleConRelaVo.setDelFlag("0");
      qcRuleConRelaVo.setAddMeth("2");
      qcRuleConRelaVo.setCrePerCode(basEmployee.getEmplNumber());
      qcRuleConRelaVo.setCrePerName(basEmployee.getEmplName());
      this.getExpression(qcRuleConRelaVo);
      String fileName = this.genRuleFile(qcRuleConRelaVo);
      qcRuleConRelaVo.setRuleFile(fileName);
      this.qcRuleConRelaMapper.insertQcRuleConRela(qcRuleConRelaVo);
   }

   private void getExpression(QcRuleConRelaVo qcRuleConRelaVo) {
      JSONObject jsonObject = JSONObject.parseObject(qcRuleConRelaVo.getComChar());
      String comChar = jsonObject.getString("codeOperator");
      String str = "";
      if (comChar.equals("containsOne")) {
         str = "!DroolsUtil.isNull(\"" + qcRuleConRelaVo.getEleId1() + "\") && !DroolsUtil.isNull(elemConText) && elemConText.contains(\"" + qcRuleConRelaVo.getEleId2() + "\")";
      } else if (comChar.equals("containsAll")) {
         str = "!DroolsUtil.isNull(\"" + qcRuleConRelaVo.getEleId1() + "\") && !DroolsUtil.isNull(elemConText) && elemConText.equals(\"" + qcRuleConRelaVo.getEleId2() + "\")";
      } else {
         str = "!DroolsUtil.isNull(\"" + qcRuleConRelaVo.getEleId1() + "\") && " + qcRuleConRelaVo.getEleId1() + qcRuleConRelaVo.getComChar() + qcRuleConRelaVo.getEleId2() + "";
      }

      qcRuleConRelaVo.setExpres(str);
   }

   private String genRuleFile(QcRuleConRelaVo qcRuleConRelaVo) throws Exception {
      try {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(qcRuleConRelaVo.getId());
         ruleVo.setErrorColumn(qcRuleConRelaVo.getEleId1() + "|" + qcRuleConRelaVo.getEleName1());
         ruleVo.setErrorMsg(qcRuleConRelaVo.getRuleDesc());
         String rule = qcRuleConRelaVo.getExpres().replaceAll("@elemContent", qcRuleConRelaVo.getEleId1());
         ruleVo.setValidationExpression("$s: QcCheckElemVo(" + rule + ") from $qcElemList");
         ruleVo.setRuleName(qcRuleConRelaVo.getRuleName());
         String fileName = this.qcRuleUtilService.createRuleFile(ruleVo);
         return fileName;
      } catch (Exception e) {
         this.log.error(e.getMessage());
         throw e;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateQcRuleConRela(QcRuleConRelaVo qcRuleConRelaVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = qcRuleConRelaVo.getId();
      List<QcCheckElem> elemList = new ArrayList();
      QcCheckElem qcCheckElem = new QcCheckElem();
      qcCheckElem.setElemName(qcRuleConRelaVo.getEleName1());
      qcCheckElem.setElemId(qcRuleConRelaVo.getEleId1());
      elemList.add(qcCheckElem);
      this.qcRuleUtilService.getAddQcCheckElemList(elemList, id, CommonConstants.QC.RULE_TYPE_CODE_5);
      qcRuleConRelaVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRelaVo.setAltPerName(basEmployee.getEmplName());
      this.getExpression(qcRuleConRelaVo);
      String fileName = this.genRuleFile(qcRuleConRelaVo);
      qcRuleConRelaVo.setRuleFile(fileName);
      this.qcRuleConRelaMapper.updateQcRuleConRela(qcRuleConRelaVo);
   }

   public int deleteQcRuleConRelaByIds(Long[] ids) {
      return this.qcRuleConRelaMapper.deleteQcRuleConRelaByIds(ids);
   }

   public void deleteQcRuleConRelaById(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      QcRuleConRelaVo qcRuleConRela = new QcRuleConRelaVo();
      qcRuleConRela.setId(id);
      qcRuleConRela.setDelFlag("1");
      qcRuleConRela.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRela.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConRelaMapper.updateQcRuleConRela(qcRuleConRela);
   }

   public void updateEditFlag(QcRuleConRelaVo qcRuleConRelaVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleConRelaVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRelaVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConRelaMapper.updateQcRuleConRela(qcRuleConRelaVo);
   }
}
