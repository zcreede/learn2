package com.emr.project.qc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRuleConRequ;
import com.emr.project.qc.domain.vo.QcRuleConRequVo;
import com.emr.project.qc.mapper.QcRuleConRequMapper;
import com.emr.project.qc.service.IQcCheckElemService;
import com.emr.project.qc.service.IQcRuleConRequService;
import com.emr.project.qc.service.IQcRuleUtilService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.service.ISysStaElemService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcRuleConRequServiceImpl implements IQcRuleConRequService {
   protected final Logger log = LoggerFactory.getLogger(QcRuleConRequServiceImpl.class);
   @Autowired
   private QcRuleConRequMapper qcRuleConRequMapper;
   @Autowired
   private IQcCheckElemService qcCheckElemService;
   @Autowired
   private IQcRuleUtilService qcRuleUtilService;
   @Autowired
   private KieTemplateConfig kieTemplateConfig;
   @Autowired
   private ISysStaElemService sysStaElemService;

   public QcRuleConRequ selectQcRuleConRequById(Long id) {
      return this.qcRuleConRequMapper.selectQcRuleConRequById(id);
   }

   public List selectQcRuleConRequList(QcRuleConRequVo qcRuleConRequVo) throws Exception {
      List<QcRuleConRequVo> list = this.qcRuleConRequMapper.selectQcRuleConRequList(qcRuleConRequVo);
      if (list != null && list.size() > 0) {
         QcCheckElem qcCheckElem1 = new QcCheckElem();
         qcCheckElem1.setCheckIdList((List)list.stream().map((s) -> s.getId()).collect(Collectors.toList()));
         List<QcCheckElem> checkElemList = this.qcCheckElemService.selectQcCheckElemList(qcCheckElem1);
         Map<Long, List<QcCheckElem>> mapList = (Map)checkElemList.stream().collect(Collectors.groupingBy((s) -> s.getCheckId()));

         for(QcRuleConRequVo vo : list) {
            List<QcCheckElem> elemList = (List)mapList.get(vo.getId());
            vo.setElemVoList(elemList);
         }
      }

      return list;
   }

   public List selectQcRuleConRequ(String emrTypeCode) throws Exception {
      return this.qcRuleConRequMapper.selectQcRuleConRequ(emrTypeCode);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcRuleConRequ(QcRuleConRequVo qcRuleConRequVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      if (qcRuleConRequVo.getRuleType().equals("1")) {
         qcRuleConRequVo.setExpres("elemId!=null&&(@elemIdStr)&&elemConText==null");
      }

      if (qcRuleConRequVo.getElemVoList() != null && !qcRuleConRequVo.getElemVoList().isEmpty()) {
         this.qcRuleUtilService.getAddQcCheckElemList(qcRuleConRequVo.getElemVoList(), id, CommonConstants.QC.RULE_TYPE_CODE_2);
      }

      qcRuleConRequVo.setId(id);
      qcRuleConRequVo.setRuleCode(id.toString());
      qcRuleConRequVo.setDelFlag("0");
      qcRuleConRequVo.setAddMeth("2");
      qcRuleConRequVo.setCrePerCode(basEmployee.getEmplNumber());
      qcRuleConRequVo.setCrePerName(basEmployee.getEmplName());
      if (qcRuleConRequVo.getRuleType().equals("2")) {
         qcRuleConRequVo.setRuleFile(this.genRuleFile02(qcRuleConRequVo));
      } else {
         qcRuleConRequVo.setRuleFile(this.genRuleFile01(qcRuleConRequVo));
      }

      this.qcRuleConRequMapper.insertQcRuleConRequ(qcRuleConRequVo);
   }

   private String genRuleFile01(QcRuleConRequVo qcRuleConRequVo) throws Exception {
      try {
         new ArrayList();
         List e;
         if (qcRuleConRequVo.getElemFlag().equals("1")) {
            e = this.sysStaElemService.selectQcElemList();
         } else {
            e = this.qcCheckElemService.selectQcCheckElemById(qcRuleConRequVo.getId());
         }

         List<String> elemIdList = (List)e.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
         StringBuffer elemIdStr = new StringBuffer("");

         for(int i = 0; i < elemIdList.size(); ++i) {
            elemIdStr.append("elemId==\"" + (String)elemIdList.get(i) + "\" ||");
         }

         String str = elemIdStr.substring(0, elemIdStr.length() - 2);
         String rule = qcRuleConRequVo.getExpres().replaceAll("@elemIdStr", str);
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(qcRuleConRequVo.getId());
         ruleVo.setValidationExpression("$s: ElemAttriVo(" + rule + ") from $IndexSaveVo.getQcElemList");
         ruleVo.setRuleName(qcRuleConRequVo.getRuleName());
         String fileName = this.qcRuleUtilService.createRuleFile(ruleVo);
         return fileName;
      } catch (Exception e) {
         this.log.error(e.getMessage());
         throw e;
      }
   }

   private String genRuleFile02(QcRuleConRequVo qcRuleConRequVo) throws Exception {
      try {
         JSONObject jsonObject = JSONObject.parseObject(qcRuleConRequVo.getExpres());
         String express = jsonObject.getString("codeElemExpression");
         new ArrayList();
         List elemList;
         if (qcRuleConRequVo.getElemFlag().equals("1")) {
            elemList = this.sysStaElemService.selectQcElemList();
         } else {
            elemList = this.qcCheckElemService.selectQcCheckElemById(qcRuleConRequVo.getId());
         }

         List<String> elemIdList = (List)elemList.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
         StringBuffer elemIdStr = new StringBuffer("");

         for(int i = 0; i < elemIdList.size(); ++i) {
            elemIdStr.append("elemId==\"" + (String)elemIdList.get(i) + "\" ||");
         }

         String str = elemIdStr.substring(0, elemIdStr.length() - 2);
         String fileName = qcRuleConRequVo.getId() + ".drl";
         GenerationRuleVo generationRuleConditionVo = new GenerationRuleVo();
         generationRuleConditionVo.setEventType("IndexSaveVo");
         generationRuleConditionVo.setEventVariable("$IndexSaveVo");
         generationRuleConditionVo.setRuleId(qcRuleConRequVo.getId().toString());
         generationRuleConditionVo.setRuleName(qcRuleConRequVo.getId() + "_" + qcRuleConRequVo.getRuleName());
         generationRuleConditionVo.setRuleResuleVo("QcRuleResultVo");
         generationRuleConditionVo.setRuleConditions(express);
         List<GenerationRuleVo> generationRuleVoList = new ArrayList();
         GenerationRuleVo generationRuleVo = new GenerationRuleVo();
         generationRuleVo.setRule("");
         generationRuleVo.setRuleCode(qcRuleConRequVo.getRuleName());
         generationRuleVo.setOtherRule("$s: ElemAttriVo(elemId!=null && elemConText==null&&(" + str + ")) from $IndexSaveVo.getQcElemList");
         generationRuleVo.setEventType("IndexSaveVo");
         generationRuleVo.setEventVariable("$IndexSaveVo");
         generationRuleVo.setRuleId(qcRuleConRequVo.getId().toString());
         generationRuleVo.setRuleName(qcRuleConRequVo.getId() + "_" + qcRuleConRequVo.getRuleName());
         generationRuleVo.setRuleResuleVo("QcRuleResultVo");
         generationRuleVoList.add(generationRuleVo);
         this.kieTemplateConfig.generationElemConditionsRules(generationRuleConditionVo, generationRuleVoList, fileName);
         return fileName;
      } catch (Exception e) {
         this.log.error(e.getMessage());
         throw e;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateQcRuleConRequ(QcRuleConRequVo qcRuleConRequVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = qcRuleConRequVo.getId();
      if (qcRuleConRequVo.getElemVoList() != null && !qcRuleConRequVo.getElemVoList().isEmpty()) {
         this.qcRuleUtilService.getAddQcCheckElemList(qcRuleConRequVo.getElemVoList(), id, CommonConstants.QC.RULE_TYPE_CODE_2);
      }

      qcRuleConRequVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRequVo.setAltPerName(basEmployee.getEmplName());
      if (qcRuleConRequVo.getRuleType().equals("1")) {
         qcRuleConRequVo.setExpres("elemId!=null&&(@elemIdStr)&&elemConText==null");
      }

      if (qcRuleConRequVo.getRuleType().equals("2")) {
         qcRuleConRequVo.setRuleFile(this.genRuleFile02(qcRuleConRequVo));
      } else {
         qcRuleConRequVo.setRuleFile(this.genRuleFile01(qcRuleConRequVo));
      }

      this.qcRuleConRequMapper.updateQcRuleConRequ(qcRuleConRequVo);
   }

   public int deleteQcRuleConRequByIds(Long[] ids) {
      return this.qcRuleConRequMapper.deleteQcRuleConRequByIds(ids);
   }

   public void deleteQcRuleConRequById(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      QcRuleConRequVo qcRuleConRequVo = new QcRuleConRequVo();
      qcRuleConRequVo.setId(id);
      qcRuleConRequVo.setDelFlag("1");
      qcRuleConRequVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRequVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConRequMapper.updateQcRuleConRequ(qcRuleConRequVo);
   }

   public void updateValidFlag(QcRuleConRequVo qcRuleConRequVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleConRequVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConRequVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConRequMapper.updateQcRuleConRequ(qcRuleConRequVo);
   }
}
