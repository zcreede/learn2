package com.emr.project.emr.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.DroolsUtil;
import com.emr.common.utils.StringUtils;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.emr.service.IGenerateRulrFileService;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("generateRulrFileService_03_0302")
public class GenerateRulrFileService_3_2Impl implements IGenerateRulrFileService {
   protected final Logger log = LoggerFactory.getLogger(GenerateRulrFileService_3_2Impl.class);
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   public String generateRulrFile(RuleVo ruleVo) throws IOException, IllegalAccessException {
      try {
         if (!DroolsUtil.checkRuleVo(ruleVo) && !StringUtils.isEmpty(ruleVo.getValidationExpression())) {
            String fileName = ruleVo.getCheckId() + ".drl";
            Map<String, Object> map = new HashMap();
            map.put("checkId", ruleVo.getCheckId());
            map.put("errorMsg", ruleVo.getErrorMsg());
            map.put("errorColumn", ruleVo.getErrorColumn());
            GenerationRuleVo generationRuleVo = new GenerationRuleVo();
            generationRuleVo.setRule(ruleVo.getValidationExpression());
            generationRuleVo.setErrorMsg("\"" + JSONUtils.toJSONString(map).replaceAll("\"", "\\\\\"") + "\"");
            generationRuleVo.setRuleCode(ruleVo.getRuleName());
            generationRuleVo.setEventType(ruleVo.getCheckVo());
            generationRuleVo.setEventVariable("$" + ruleVo.getCheckVo());
            generationRuleVo.setRuleId(ruleVo.getCheckId().toString());
            generationRuleVo.setRuleName(ruleVo.getRuleName());
            generationRuleVo.setRuleResuleVo("MrHpCheckResultVo");
            generationRuleVo.setErrorColumn(ruleVo.getErrorColumn());
            this.kieTemplateConfig.generationRules(Arrays.asList(generationRuleVo), fileName);
            return fileName;
         } else {
            throw new CustomException("params info error");
         }
      } catch (Exception e) {
         this.log.error(e.getMessage());
         throw e;
      }
   }
}
