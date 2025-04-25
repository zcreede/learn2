package com.emr.project.emr.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.DroolsUtil;
import com.emr.common.utils.StringUtils;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.emr.service.IGenerateRulrFileService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("generateRulrFileService_03_0301")
public class GenerateRulrFileService_3_1Impl implements IGenerateRulrFileService {
   protected final Logger log = LoggerFactory.getLogger(GenerateRulrFileService_3_1Impl.class);
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   public String generateRulrFile(RuleVo ruleVo) throws Exception {
      if (!DroolsUtil.checkRuleVo(ruleVo) && !StringUtils.isEmpty(ruleVo.getValidationExpression()) && !StringUtils.isEmpty(ruleVo.getRelationValidationExpression()) && ruleVo.getExpression2List() != null && !ruleVo.getExpression2List().isEmpty()) {
         JSONArray condition2 = ruleVo.getExpression2List();
         String fileName = ruleVo.getCheckId() + ".drl";
         GenerationRuleVo generationRuleVoCondition = this.getGenerationRuleVo(ruleVo);
         generationRuleVoCondition.setRuleConditions(ruleVo.getValidationExpression());
         generationRuleVoCondition.setRuleOrder(condition2.size() + 2);
         GenerationRuleVo generationRuleVoCondition2 = this.getGenerationRuleVo(ruleVo);
         generationRuleVoCondition2.setRuleConditions(ruleVo.getRelationValidationExpression());
         generationRuleVoCondition2.setRuleOrder(condition2.size() + 1);
         List<GenerationRuleVo> generationRuleVoList = new ArrayList();

         for(int i = 0; i < condition2.size(); ++i) {
            GenerationRuleVo generationRuleVo = this.getGenerationRuleVo(ruleVo);
            JSONObject temp = JSONObject.parseObject(condition2.get(i).toString());
            String checkExpression = temp.getString("checkExpression");
            String fieldProp = temp.getString("fieldProp");
            generationRuleVo.setRuleName(ruleVo.getRuleName() + "_" + i);
            generationRuleVo.setRule(checkExpression);
            generationRuleVo.setErrorColumn(fieldProp);
            generationRuleVo.setRuleOrder(condition2.size() - i);
            generationRuleVoList.add(generationRuleVo);
         }

         this.kieTemplateConfig.generationRulesByConditions2(generationRuleVoCondition, generationRuleVoCondition2, generationRuleVoList, fileName);
         return fileName;
      } else {
         throw new CustomException("params info error");
      }
   }

   private GenerationRuleVo getGenerationRuleVo(RuleVo ruleVo) {
      GenerationRuleVo generationRuleVo = new GenerationRuleVo();
      generationRuleVo.setRuleId(ruleVo.getCheckId().toString());
      generationRuleVo.setRuleName(ruleVo.getRuleName());
      generationRuleVo.setEventType(ruleVo.getCheckVo());
      generationRuleVo.setEventVariable("$" + ruleVo.getCheckVo());
      generationRuleVo.setRuleResuleVo("MrHpCheckResultVo");
      return generationRuleVo;
   }
}
