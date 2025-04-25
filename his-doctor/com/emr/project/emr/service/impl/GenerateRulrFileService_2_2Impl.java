package com.emr.project.emr.service.impl;

import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.emr.service.IGenerateRulrFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("generateRulrFileService_02_0202")
public class GenerateRulrFileService_2_2Impl implements IGenerateRulrFileService {
   @Autowired
   private IGenerateRulrFileService generateRulrFileService_02_0201;

   public String generateRulrFile(RuleVo ruleVo) throws Exception {
      return this.generateRulrFileService_02_0201.generateRulrFile(ruleVo);
   }
}
