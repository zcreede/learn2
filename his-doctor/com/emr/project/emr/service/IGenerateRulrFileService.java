package com.emr.project.emr.service;

import com.emr.project.emr.domain.vo.RuleVo;

public interface IGenerateRulrFileService {
   String generateRulrFile(RuleVo ruleVo) throws Exception;
}
