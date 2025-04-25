package com.emr.project.emr.service.impl;

import com.emr.common.exception.CustomException;
import com.emr.common.utils.DroolsUtil;
import com.emr.common.utils.StringUtils;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.emr.service.IGenerateRulrFileService;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("generateRulrFileService_04_0402")
public class GenerateRulrFileService_4_2Impl implements IGenerateRulrFileService {
   protected final Logger log = LoggerFactory.getLogger(GenerateRulrFileService_4_2Impl.class);
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   public String generateRulrFile(RuleVo ruleVo) throws Exception {
      if (!DroolsUtil.checkRuleVo(ruleVo) && !StringUtils.isEmpty(ruleVo.getValidationExpression())) {
         String fileName = ruleVo.getCheckId() + ".drl";
         GenerationRuleVo generationRuleVoCondition = this.getGenerationRuleVo(ruleVo);
         generationRuleVoCondition.setRuleConditions(ruleVo.getValidationExpression());
         generationRuleVoCondition.setRuleOrder(2);
         GenerationRuleVo generationRuleVo = this.getGenerationRuleVo(ruleVo);
         generationRuleVo.setOtherRule("$s: MrHpDia(" + ruleVo.getValidationExpression1() + ") from " + generationRuleVo.getEventVariable() + ".mrHpDiaXYList");
         generationRuleVo.setErrorColumn("$s.getDiaId()");
         this.kieTemplateConfig.generationRulesByConditionsDiaOpe2(generationRuleVoCondition, Arrays.asList(generationRuleVo), fileName);
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
