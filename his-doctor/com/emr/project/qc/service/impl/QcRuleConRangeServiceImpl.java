package com.emr.project.qc.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRuleConRange;
import com.emr.project.qc.domain.vo.QcRuleConRangeVo;
import com.emr.project.qc.mapper.QcRuleConRangeMapper;
import com.emr.project.qc.service.IQcRuleConRangeService;
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
public class QcRuleConRangeServiceImpl implements IQcRuleConRangeService {
   protected final Logger log = LoggerFactory.getLogger(QcRuleConRangeServiceImpl.class);
   @Autowired
   private QcRuleConRangeMapper qcRuleConRangeMapper;
   @Autowired
   private IQcRuleUtilService qcRuleUtilService;
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   public QcRuleConRange selectQcRuleConRangeById(Long id) {
      return this.qcRuleConRangeMapper.selectQcRuleConRangeById(id);
   }

   public List selectQcRuleConRangeList(QcRuleConRangeVo qcRuleConRangeVo) throws Exception {
      return this.qcRuleConRangeMapper.selectQcRuleConRangeList(qcRuleConRangeVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcRuleConRange(QcRuleConRangeVo qcRuleConRangeVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      List<QcCheckElem> elemList = new ArrayList();
      QcCheckElem qcCheckElem = new QcCheckElem();
      qcCheckElem.setElemName(qcRuleConRangeVo.getEleName());
      qcCheckElem.setElemId(qcRuleConRangeVo.getEleId());
      elemList.add(qcCheckElem);
      this.qcRuleUtilService.getAddQcCheckElemList(elemList, id, CommonConstants.QC.RULE_TYPE_CODE_4);
      qcRuleConRangeVo.setId(id);
      qcRuleConRangeVo.setRuleCode(id.toString());
      qcRuleConRangeVo.setDelFlag("0");
      qcRuleConRangeVo.setAddMeth("2");
      qcRuleConRangeVo.setCrePerCode(basEmployee.getEmplNumber());
      qcRuleConRangeVo.setCrePerName(basEmployee.getEmplName());
      this.getExpression(qcRuleConRangeVo);
      String fileName = this.genRuleFile(qcRuleConRangeVo);
      qcRuleConRangeVo.setRuleFile(fileName);
      this.qcRuleConRangeMapper.insertQcRuleConRange(qcRuleConRangeVo);
   }

   private void getExpression(QcRuleConRangeVo qcRuleConRangeVo) {
      if (qcRuleConRangeVo.getRuleType().equals("1")) {
         String low = "";
         switch (qcRuleConRangeVo.getLowChar()) {
            case ">":
               low = "<=";
               break;
            case ">=":
               low = "<";
         }

         upp = "";
         switch (qcRuleConRangeVo.getUppChar()) {
            case "<":
               upp = ">=";
               break;
            case "<=":
               upp = ">";
         }

         str = "elemId!=null&&elemConTextInt!=null&& elemId==\"" + qcRuleConRangeVo.getEleId() + "\" && (elemConTextInt" + low + qcRuleConRangeVo.getLowValue() + " || elemConTextInt " + upp + qcRuleConRangeVo.getUppValue() + ")";
         qcRuleConRangeVo.setExpres(str);
      } else {
         String str = "elemId!=null &&elemConText!=null&& elemId==\"" + qcRuleConRangeVo.getEleId() + "\"";
         switch (qcRuleConRangeVo.getDateRange()) {
            case "1":
               str = str + "&& (DroolsUtil.dateCompareTo(elemConText, \"" + qcRuleConRangeVo.getUppValue() + "\")>=0 || DroolsUtil.dateCompareTo(elemConText, \"" + qcRuleConRangeVo.getLowValue() + "\")<=0 )";
               break;
            case "2":
               str = str + "&& inhosTime!=null && outTime!=null && (DroolsUtil.dateCompareTo(elemConText, inhosTime)<=0 || DroolsUtil.dateCompareTo(elemConText, outTime)>=0 )";
               break;
            case "3":
               str = str + "&& inhosTime!=null && DroolsUtil.dateCompareTo(elemConText, inhosTime)>=0 ";
               break;
            case "4":
               str = str + "&& outTime!=null && DroolsUtil.dateCompareTo(elemConText, outTime)<=0 ";
               break;
            case "5":
               str = str + "&& nowDate!=null && (DroolsUtil.dateCompareTo(elemConText, nowDate)>0 || DroolsUtil.dateCompareTo(elemConText, nowDate)<0) ";
               break;
            case "6":
               str = str + "&& nowDate!=null && DroolsUtil.dateCompareTo(elemConText, nowDate)>=0";
               break;
            case "7":
               str = str + "&& nowDate!=null && DroolsUtil.dateCompareTo(elemConText, nowDate)<=0";
         }

         qcRuleConRangeVo.setExpres(str);
      }

   }

   private String genRuleFile(QcRuleConRangeVo qcRuleConRangeVo) throws Exception {
      try {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(qcRuleConRangeVo.getId());
         ruleVo.setErrorColumn(qcRuleConRangeVo.getEleId() + "|" + qcRuleConRangeVo.getEleName());
         ruleVo.setErrorMsg(qcRuleConRangeVo.getRuleDesc());
         ruleVo.setValidationExpression("$s: ElemAttriVo(" + qcRuleConRangeVo.getExpres() + ") from $IndexSaveVo.getQcElemList");
         ruleVo.setRuleName(qcRuleConRangeVo.getRuleName());
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
   public void updateQcRuleConRange(QcRuleConRangeVo qcRuleConRangeVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = qcRuleConRangeVo.getId();
      List<QcCheckElem> elemList = new ArrayList();
      QcCheckElem qcCheckElem = new QcCheckElem();
      qcCheckElem.setElemName(qcRuleConRangeVo.getEleName());
      qcCheckElem.setElemId(qcRuleConRangeVo.getEleId());
      elemList.add(qcCheckElem);
      this.qcRuleUtilService.getAddQcCheckElemList(elemList, id, CommonConstants.QC.RULE_TYPE_CODE_4);
      qcRuleConRangeVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRangeVo.setAltPerName(basEmployee.getEmplName());
      this.getExpression(qcRuleConRangeVo);
      String fileName = this.genRuleFile(qcRuleConRangeVo);
      qcRuleConRangeVo.setRuleFile(fileName);
      this.qcRuleConRangeMapper.updateQcRuleConRange(qcRuleConRangeVo);
   }

   public int deleteQcRuleConRangeByIds(Long[] ids) {
      return this.qcRuleConRangeMapper.deleteQcRuleConRangeByIds(ids);
   }

   public void deleteQcRuleConRangeById(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      QcRuleConRangeVo qcRuleConRangeVo = new QcRuleConRangeVo();
      qcRuleConRangeVo.setId(id);
      qcRuleConRangeVo.setDelFlag("1");
      qcRuleConRangeVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRangeVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConRangeMapper.updateQcRuleConRange(qcRuleConRangeVo);
   }

   public void updateValidFlag(QcRuleConRangeVo qcRuleConRangeVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleConRangeVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRangeVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConRangeMapper.updateQcRuleConRange(qcRuleConRangeVo);
   }
}
