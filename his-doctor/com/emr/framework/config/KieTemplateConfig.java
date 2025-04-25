package com.emr.framework.config;

import com.emr.common.utils.StringUtils;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.drools.template.ObjectDataCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieTemplateConfig {
   protected final Logger log = LoggerFactory.getLogger(KieTemplateConfig.class);
   @Value("${spring.drools.path}")
   private String path;

   public void generationRules(List generationRuleVoList, String fileName) throws IOException, IllegalAccessException {
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-header.drt"));
      sbf.append(heard);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-content.drt"));
         sbf.append(rule);
      }

      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
   }

   public void generationRulesByConditions(GenerationRuleVo generationRuleVoCondition, List generationRuleVoList, String fileName) throws Exception {
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-header.drt"));
      sbf.append(heard);
      Map<String, Object> map1 = objectToMap(generationRuleVoCondition);
      String conditionStr = objectDataCompiler.compile(Arrays.asList(map1), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-conditions.drt"));
      sbf.append(conditionStr);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-content.drt"));
         sbf.append(rule);
      }

      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
   }

   public void generationRulesByConditions2(GenerationRuleVo generationRuleVoCondition, GenerationRuleVo generationRuleVoCondition2, List generationRuleVoList, String fileName) throws Exception {
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-header.drt"));
      sbf.append(heard);
      Map<String, Object> map1 = objectToMap(generationRuleVoCondition);
      String conditionStr = objectDataCompiler.compile(Arrays.asList(map1), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-conditions.drt"));
      sbf.append(conditionStr);
      Map<String, Object> map2 = objectToMap(generationRuleVoCondition2);
      String conditionStr2 = objectDataCompiler.compile(Arrays.asList(map2), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-conditions2.drt"));
      sbf.append(conditionStr2);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-content.drt"));
         sbf.append(rule);
      }

      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
   }

   public void generationRulesByConditionsDiaOpe(GenerationRuleVo generationRuleVoCondition, List generationRuleVoList, String fileName) throws Exception {
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-header.drt"));
      sbf.append(heard);
      Map<String, Object> map1 = objectToMap(generationRuleVoCondition);
      String conditionStr = objectDataCompiler.compile(Arrays.asList(map1), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-conditions.drt"));
      sbf.append(conditionStr);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-content-diaOpe.drt"));
         sbf.append(rule);
      }

      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
   }

   public void generationRulesByConditionsDiaOpe2(GenerationRuleVo generationRuleVoCondition, List generationRuleVoList, String fileName) throws Exception {
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-header.drt"));
      sbf.append(heard);
      Map<String, Object> map1 = objectToMap(generationRuleVoCondition);
      String conditionStr = objectDataCompiler.compile(Arrays.asList(map1), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-conditions-diaOpe.drt"));
      sbf.append(conditionStr);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-content-diaOpe2.drt"));
         sbf.append(rule);
      }

      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
   }

   public void generationRulesByConditionsOpe(GenerationRuleVo generationRuleVoCondition, List generationRuleVoList, String fileName) throws Exception {
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-header.drt"));
      sbf.append(heard);
      Map<String, Object> map1 = objectToMap(generationRuleVoCondition);
      String conditionStr = objectDataCompiler.compile(Arrays.asList(map1), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-conditions-Ope.drt"));
      sbf.append(conditionStr);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/mrhpRule/rule-content-diaOpe2.drt"));
         sbf.append(rule);
      }

      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
   }

   public void generationElemRules(List generationRuleVoList, String fileName) throws IOException, IllegalAccessException {
      this.log.info("生成文件1111111");
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-header.drt"));
      sbf.append(heard);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-content.drt"));
         sbf.append(rule);
      }

      this.log.info("生成文件222222222");
      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      this.log.info("生成文件333333333");
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
      this.log.info("生成文件444444444");
   }

   public void generationElemConditionsRules(GenerationRuleVo generationRuleVoCondition, List generationRuleVoList, String fileName) throws Exception {
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-header.drt"));
      sbf.append(heard);
      Map<String, Object> map1 = objectToMap(generationRuleVoCondition);
      String conditionStr = objectDataCompiler.compile(Arrays.asList(map1), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-conditions.drt"));
      sbf.append(conditionStr);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-content.drt"));
         sbf.append(rule);
      }

      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
   }

   public void generationElemConditionsRulesLogic(GenerationRuleVo generationRuleVoCondition, GenerationRuleVo generationRuleVoCondition2, List generationRuleVoList, String fileName) throws Exception {
      ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
      StringBuffer sbf = new StringBuffer();
      String heard = objectDataCompiler.compile(new ArrayList(), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-header.drt"));
      sbf.append(heard);
      Map<String, Object> map1 = objectToMap(generationRuleVoCondition);
      String conditionStr = objectDataCompiler.compile(Arrays.asList(map1), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-conditions.drt"));
      sbf.append(conditionStr);
      Map<String, Object> map2 = objectToMap(generationRuleVoCondition2);
      String conditionStr2 = objectDataCompiler.compile(Arrays.asList(map2), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-conditions2.drt"));
      sbf.append(conditionStr2);

      for(GenerationRuleVo generationRuleVo : generationRuleVoList) {
         Map<String, Object> map = objectToMap(generationRuleVo);
         String rule = objectDataCompiler.compile(Arrays.asList(map), Thread.currentThread().getContextClassLoader().getResourceAsStream("drools/template/elemRule/rule-elem-content.drt"));
         sbf.append(rule);
      }

      File dir = new File(this.path);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      File file = new File(this.path + "/" + fileName);
      if (file.exists()) {
         file.delete();
      }

      file.createNewFile();
      FileUtils.writeStringToFile(file, sbf.toString(), "UTF-8", false);
   }

   public static Map objectToMap(Object obj) throws IllegalAccessException {
      Map<String, Object> map = new HashMap();
      Class<?> clazz = obj.getClass();

      for(Field field : clazz.getDeclaredFields()) {
         field.setAccessible(true);
         String fieldName = field.getName();
         Object value = StringUtils.nvl(field.get(obj), "");
         map.put(fieldName, value);
      }

      return map;
   }
}
