package com.emr.project.emr.service.impl;

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

@Service("generateRulrFileService_01_0102")
public class GenerateRulrFileService_1_2Impl implements IGenerateRulrFileService {
   protected final Logger log = LoggerFactory.getLogger(GenerateRulrFileService_1_2Impl.class);
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   public String generateRulrFile(RuleVo ruleVo) throws Exception {
      if (!DroolsUtil.checkRuleVo(ruleVo) && !StringUtils.isEmpty(ruleVo.getNotNullRule()) && !StringUtils.isEmpty(ruleVo.getCheckColumn())) {
         String fileName = ruleVo.getCheckId() + ".drl";
         GenerationRuleVo generationRuleVoCondition = this.getGenerationRuleVo(ruleVo);
         generationRuleVoCondition.setRuleConditions(ruleVo.getNotNullRule());
         generationRuleVoCondition.setRuleOrder(2);
         List<GenerationRuleVo> generationRuleVoList = new ArrayList();
         GenerationRuleVo generationRuleVo = this.getGenerationRuleVo(ruleVo);
         generationRuleVo.setErrorColumn(ruleVo.getErrorColumn());
         generationRuleVo.setRule("DroolsUtil.isNull(" + ruleVo.getCheckColumn() + ")");
         generationRuleVoList.add(generationRuleVo);
         this.kieTemplateConfig.generationRulesByConditions(generationRuleVoCondition, generationRuleVoList, fileName);
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
