package com.emr.project.emr.controller;

import com.drools.core.KieTemplate;
import com.emr.framework.config.BeanConfig;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.emr.service.IGenerateRulrFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/emr/droolsTestDemo"})
public class DroolsTestDemo {
   protected final Logger log = LoggerFactory.getLogger(DroolsTestDemo.class);
   @Autowired
   private KieTemplate kieTemplate;
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   @GetMapping
   public String getPersonDemo() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_1_2");
      if (service != null) {
         return service.generateRulrFile((RuleVo)null);
      } else {
         System.out.println("generateRulrFileService_1_2");
         return null;
      }
   }
}
