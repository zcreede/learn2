package com.emr.project.qc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import com.emr.project.qc.domain.QcRuleConLogic;
import com.emr.project.qc.domain.vo.ElemExpressionVo;
import com.emr.project.qc.mapper.QcRuleConLogicMapper;
import com.emr.project.qc.service.IQcRuleConLogicService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcRuleConLogicServiceImpl implements IQcRuleConLogicService {
   protected final Logger log = LoggerFactory.getLogger(QcRuleConLogicServiceImpl.class);
   @Autowired
   private QcRuleConLogicMapper qcRuleConLogicMapper;
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   public QcRuleConLogic selectQcRuleConLogicById(Long id) {
      return this.qcRuleConLogicMapper.selectQcRuleConLogicById(id);
   }

   public List selectQcRuleConLogicList(QcRuleConLogic qcRuleConLogic) throws Exception {
      return this.qcRuleConLogicMapper.selectQcRuleConLogicList(qcRuleConLogic);
   }

   public void insertQcRuleConLogic(QcRuleConLogic qcRuleConLogic) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      qcRuleConLogic.setId(id);
      qcRuleConLogic.setDelFlag("0");
      qcRuleConLogic.setRuleCode(id.toString());
      qcRuleConLogic.setAddMeth("2");
      qcRuleConLogic.setCrePerCode(basEmployee.getEmplNumber());
      qcRuleConLogic.setCrePerName(basEmployee.getEmplName());
      String fileName = this.genRuleFile(qcRuleConLogic);
      qcRuleConLogic.setRuleFile(fileName);
      this.qcRuleConLogicMapper.insertQcRuleConLogic(qcRuleConLogic);
   }

   private String genRuleFile(QcRuleConLogic qcRuleConLogic) throws Exception {
      try {
         String fileName = qcRuleConLogic.getId() + ".drl";
         JSONObject jsonObject1 = JSONObject.parseObject(qcRuleConLogic.getExpres1());
         GenerationRuleVo generationRuleVoCondition1 = this.setRuleVo(qcRuleConLogic, jsonObject1, 999);
         JSONObject jsonObject2 = JSONObject.parseObject(qcRuleConLogic.getExpres2());
         GenerationRuleVo generationRuleVoCondition2 = this.setRuleVo(qcRuleConLogic, jsonObject2, 998);
         JSONArray jsonArray = jsonObject2.getJSONArray("elemExpressionVoList");
         List<ElemExpressionVo> elemExpressionVoList = jsonArray.toJavaList(ElemExpressionVo.class);
         List<GenerationRuleVo> generationRuleVoList = new ArrayList();

         for(int i = 0; i < elemExpressionVoList.size(); ++i) {
            GenerationRuleVo generationRuleVo = new GenerationRuleVo();
            generationRuleVo.setRule("");
            generationRuleVo.setRuleCode(qcRuleConLogic.getRuleName());
            String expree = ((ElemExpressionVo)elemExpressionVoList.get(i)).getOppCodeOperator().replaceAll("@V", "\"" + ((ElemExpressionVo)elemExpressionVoList.get(i)).getOperatorValue() + "\"").replaceAll("@T", "elemConText");
            generationRuleVo.setOtherRule("$s: ElemAttriVo(elemId!=null&&elemId==\"" + ((ElemExpressionVo)elemExpressionVoList.get(i)).getId() + "\"&& " + expree + ") from $IndexSaveVo.getQcElemList");
            generationRuleVo.setEventType("IndexSaveVo");
            generationRuleVo.setEventVariable("$IndexSaveVo");
            generationRuleVo.setRuleId(qcRuleConLogic.getId().toString());
            generationRuleVo.setRuleName(qcRuleConLogic.getId() + "_" + qcRuleConLogic.getRuleName() + "_" + i);
            generationRuleVo.setRuleResuleVo("QcRuleResultVo");
            generationRuleVo.setRuleOrder(elemExpressionVoList.size() - i);
            generationRuleVoList.add(generationRuleVo);
         }

         this.kieTemplateConfig.generationElemConditionsRulesLogic(generationRuleVoCondition1, generationRuleVoCondition2, generationRuleVoList, fileName);
         return fileName;
      } catch (Exception e) {
         this.log.error(e.getMessage());
         throw e;
      }
   }

   private GenerationRuleVo setRuleVo(QcRuleConLogic qcRuleConLogic, JSONObject jsonObject, Integer order) {
      GenerationRuleVo generationRuleVoCondition = new GenerationRuleVo();
      String ruleConditions = jsonObject.getString("codeElemExpression");
      generationRuleVoCondition.setRuleConditions(ruleConditions);
      generationRuleVoCondition.setEventVariable("$IndexSaveVo");
      generationRuleVoCondition.setEventType("IndexSaveVo");
      generationRuleVoCondition.setRuleResuleVo("QcRuleResultVo");
      generationRuleVoCondition.setRuleOrder(order);
      generationRuleVoCondition.setRuleId(qcRuleConLogic.getId().toString());
      generationRuleVoCondition.setRuleName(qcRuleConLogic.getId() + "_" + qcRuleConLogic.getRuleName());
      return generationRuleVoCondition;
   }

   public void updateQcRuleConLogic(QcRuleConLogic qcRuleConLogic) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleConLogic.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConLogic.setAltPerName(basEmployee.getEmplName());
      String fileName = this.genRuleFile(qcRuleConLogic);
      qcRuleConLogic.setRuleFile(fileName);
      this.qcRuleConLogicMapper.updateQcRuleConLogic(qcRuleConLogic);
   }

   public int deleteQcRuleConLogicByIds(Long[] ids) {
      return this.qcRuleConLogicMapper.deleteQcRuleConLogicByIds(ids);
   }

   public void deleteQcRuleConLogicById(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      QcRuleConLogic qcRuleConLogic = new QcRuleConLogic();
      qcRuleConLogic.setId(id);
      qcRuleConLogic.setDelFlag("1");
      qcRuleConLogic.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConLogic.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConLogicMapper.updateQcRuleConLogic(qcRuleConLogic);
   }

   public void updateEditFlag(QcRuleConLogic qcRuleConLogic) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleConLogic.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConLogic.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConLogicMapper.updateQcRuleConLogic(qcRuleConLogic);
   }
}
