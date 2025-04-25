package com.emr.project.emr.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.DroolsUtil;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.emr.service.IGenerateRulrFileService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("generateRulrFileService_01_0101")
public class GenerateRulrFileService_1_1Impl implements IGenerateRulrFileService {
   protected final Logger log = LoggerFactory.getLogger(GenerateRulrFileService_1_1Impl.class);
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   public String generateRulrFile(RuleVo ruleVo) throws IOException, IllegalAccessException {
      try {
         if (!DroolsUtil.checkRuleVo(ruleVo) && ruleVo.getErrorColumn() != null && !ruleVo.getErrorColumn().isEmpty()) {
            String fileName = ruleVo.getCheckId() + ".drl";
            List<GenerationRuleVo> generationRuleVoList = new ArrayList();
            Map<String, Object> map = new HashMap();
            map.put("checkId", ruleVo.getCheckId());
            map.put("errorMsg", ruleVo.getErrorMsg());
            int i = 1;
            String[] columnArray = ruleVo.getErrorColumn().split(",");

            for(int z = 0; z < columnArray.length; ++z) {
               String[] ruleStr = columnArray[z].split("\\|");
               map.put("errorColumn", columnArray[z]);
            }

            for(int z = 0; z < columnArray.length; ++z) {
               String[] ruleStr = columnArray[z].split("\\|");
               GenerationRuleVo generationRuleVo = new GenerationRuleVo();
               generationRuleVo.setRule("DroolsUtil.isNull(" + (String)ruleVo.getNotNullList().get(z) + ")");
               map.put("errorColumn", ruleVo.getNotNullList().get(z));
               generationRuleVo.setErrorMsg("\"" + JSONUtils.toJSONString(map).replaceAll("\"", "\\\\\"") + "\"");
               generationRuleVo.setRuleCode(ruleVo.getRuleName() + "_" + i);
               generationRuleVo.setEventType(ruleVo.getCheckVo());
               generationRuleVo.setEventVariable("$" + ruleVo.getCheckVo());
               generationRuleVo.setRuleId(ruleVo.getCheckId().toString());
               generationRuleVo.setRuleName(ruleVo.getRuleName() + "_" + z);
               generationRuleVo.setRuleResuleVo("MrHpCheckResultVo");
               generationRuleVo.setErrorColumn(columnArray[z]);
               generationRuleVo.setRuleOrder(ruleVo.getNotNullList().size() - z);
               generationRuleVoList.add(generationRuleVo);
               ++i;
            }

            this.kieTemplateConfig.generationRules(generationRuleVoList, fileName);
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
