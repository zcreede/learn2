package com.emr;

import com.drools.core.KieTemplate;
import com.emr.common.utils.DateUtils;
import com.emr.framework.config.BeanConfig;
import com.emr.project.emr.domain.vo.IndexSaveVo;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.emr.service.IGenerateRulrFileService;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import com.emr.project.mrhp.domain.vo.MrHpCheckResultVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.qc.domain.vo.QcRuleResultVo;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
   classes = {EMRApplication.class},
   webEnvironment = WebEnvironment.RANDOM_PORT
)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DroolsApplicationTest {
   @Autowired
   private KieTemplate kieTemplate;
   private KieSession kSession;
   private static List kieTemplates = new ArrayList();

   @Before
   public void initKieSession() {
      this.kSession = this.kieTemplate.getKieSession(new String[]{"ai-rules.drl"});
   }

   @After
   public void runDispose() {
      this.kSession.dispose();
   }

   @Test
   public void testA() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_01_0101");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(10101L);
         ruleVo.setRuleName("testA");
         ruleVo.setErrorMsg("testA");
         ruleVo.setErrorColumn("recordNo,patientId");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setValidationTable("MrHpDia");
         List<String> notNullList = new ArrayList();
         notNullList.add("recordNo");
         notNullList.add("patientId");
         ruleVo.setNotNullList(notNullList);
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testB() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_01_0102");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(10201L);
         ruleVo.setRuleName("testB");
         ruleVo.setErrorMsg("testB");
         ruleVo.setErrorColumn("patientId");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setNotNullRule("recordNo != null && recordNo !=\"\"");
         ruleVo.setCheckColumn("patientId");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testC() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_02_0201");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(20201L);
         ruleVo.setRuleName("testC");
         ruleVo.setErrorMsg("testC");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setCheckColumn("patientId");
         List<String> enumerationList = new ArrayList();
         enumerationList.add("1");
         enumerationList.add("2");
         enumerationList.add("3");
         enumerationList.add("4");
         ruleVo.setEnumerationList(enumerationList);
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testD() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_02_0202");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(20202L);
         ruleVo.setRuleName("testD");
         ruleVo.setErrorMsg("testD");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setCheckColumn("patientId");
         List<String> enumerationList = new ArrayList();
         enumerationList.add("1");
         enumerationList.add("2");
         enumerationList.add("3");
         enumerationList.add("4");
         ruleVo.setEnumerationList(enumerationList);
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testE() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_02_0203");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(20203L);
         ruleVo.setRuleName("testE");
         ruleVo.setErrorMsg("testE");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setCheckColumn("ageY");
         ruleVo.setIsCheckNull("1");
         ruleVo.setValidationExpression("ageY>10");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testF() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_02_0204");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(20204L);
         ruleVo.setRuleName("testF");
         ruleVo.setErrorMsg("testF");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setCheckColumn("patientId");
         ruleVo.setIsCheckNull("1");
         ruleVo.setRegular("^[1-9]\\\\d{5}$");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testG() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_03_0301");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(30301L);
         ruleVo.setRuleName("testG");
         ruleVo.setErrorMsg("testG");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setValidationExpression("ageY>1");
         ruleVo.setRelationValidationExpression("ageM>1");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testH() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_03_0302");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(30302L);
         ruleVo.setRuleName("testH");
         ruleVo.setErrorMsg("testH");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setRelationColumn("inhosTime");
         ruleVo.setCheckColumn("birDate");
         ruleVo.setLogicCheckRule("1");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setValidationExpression("inHosTime > birDate");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testI() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_03_0303");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(30303L);
         ruleVo.setRuleName("testI");
         ruleVo.setErrorMsg("testI");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setAmount("amount");
         List<String> feeList = new ArrayList();
         feeList.add("fee1");
         feeList.add("fee2");
         feeList.add("fee3");
         ruleVo.setFeeList(feeList);
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testJ() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_04_0401");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(40401L);
         ruleVo.setRuleName("testJ");
         ruleVo.setErrorMsg("testJ");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setValidationExpression("ageY>12");
         ruleVo.setValidationExpression1("diaType == \"1\" && diaNo==\"2\"");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testK() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_04_0402");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setCheckId(40402L);
         ruleVo.setRuleName("testK");
         ruleVo.setErrorMsg("testK");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setValidationExpression("diaItem == \"1\" && StringUtils.startsWith(diaCd,\"C\")");
         ruleVo.setValidationExpression1("diaItem == \"2\" && (diaCd == null ||diaCd == \"\" )");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testL() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_05_0501");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setRuleName("testL");
         ruleVo.setErrorMsg("testL");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setValidationExpression("ageY>12");
         ruleVo.setValidationExpression1("anestMethCode == \"1\"");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testM() throws Exception {
      IGenerateRulrFileService service = (IGenerateRulrFileService)BeanConfig.getBean("generateRulrFileService_05_0502");
      if (service != null) {
         RuleVo ruleVo = new RuleVo();
         ruleVo.setRuleName("testM");
         ruleVo.setErrorMsg("testM");
         ruleVo.setErrorColumn("recordNo");
         ruleVo.setCheckVo("MrHpVo");
         ruleVo.setValidationExpression("oprLevelCode == \"1\" && StringUtils.startsWith(oprLevel,\"C\")");
         ruleVo.setValidationExpression1("oprLevelCode == \"2\" && (oprLevel == null ||oprLevel == \"\" )");
         String fileName = service.generateRulrFile(ruleVo);
         kieTemplates.add(fileName);
      }

   }

   @Test
   public void testN() throws Exception {
      this.kSession = this.kieTemplate.getKieSession(new String[]{"926432142290845696.drl"});
      MrHpVo bob = new MrHpVo();
      bob.setMarStaCd("1");
      bob.setContTel("10601384668");
      bob.setWorkPost("1234567");
      bob.setLeaveWayCd("5");
      bob.setInhosWeight(5L);
      bob.setAgeY(13L);
      bob.setSex("2");
      bob.setBirDate(DateUtils.parseDate("1941-11-20"));
      bob.setInhosTime(DateUtils.parseDate("2018-06-16"));
      bob.setRecordNo("1");
      bob.setPatientId("5");
      List<MrHpDia> mrHpDiaXYList = new ArrayList();
      MrHpDia mrHpDia = new MrHpDia();
      mrHpDia.setDiaType("1");
      mrHpDia.setDiaNo("2");
      mrHpDia.setDiaItem("1");
      mrHpDia.setDiaId("0089581");
      mrHpDia.setDiaCd("C1");
      mrHpDiaXYList.add(mrHpDia);
      MrHpDia mrHpDia1 = new MrHpDia();
      mrHpDia1.setDiaType("3");
      mrHpDia1.setDiaNo("4");
      mrHpDia1.setDiaId("0089582");
      mrHpDia1.setDiaItem("2");
      mrHpDiaXYList.add(mrHpDia1);
      MrHpDia mrHpDia2 = new MrHpDia();
      mrHpDia2.setDiaType("1");
      mrHpDia2.setDiaNo("3");
      mrHpDia2.setDiaId("0089583");
      mrHpDia2.setDiaItem("2");
      mrHpDiaXYList.add(mrHpDia2);
      bob.setMrHpDiaXYList(mrHpDiaXYList);
      List<MrHpFee> mrHpFeeList = new ArrayList();
      MrHpFee fee1 = new MrHpFee();
      MrHpFee fee2 = new MrHpFee();
      MrHpFee fee3 = new MrHpFee();
      MrHpFee amount = new MrHpFee();
      amount.setFeeCd("amount");
      amount.setAmount(22.22);
      fee1.setFeeCd("fee1");
      fee1.setAmount(10.22);
      fee2.setFeeCd("fee2");
      fee2.setAmount(10.22);
      fee3.setFeeCd("fee3");
      fee3.setAmount(3.22);
      mrHpFeeList.add(amount);
      mrHpFeeList.add(fee1);
      mrHpFeeList.add(fee2);
      mrHpFeeList.add(fee3);
      bob.setMrHpFeeList(mrHpFeeList);
      MrHpAttachVo mrHpAttachVo = new MrHpAttachVo();
      mrHpAttachVo.setPatientId("15");
      bob.setMrHpAttachVo(mrHpAttachVo);
      this.kSession.insert(bob);
      this.kSession.insert(mrHpDiaXYList);
      int rules = this.kSession.fireAllRules();
      System.out.println("rules:: " + rules);

      for(String erroMsg : bob.getErrorMsg()) {
         System.out.println(erroMsg);
      }

      for(MrHpCheckResultVo checkResultVo : bob.getResultVoList()) {
         System.out.println(checkResultVo.toString());
      }

   }

   @Test
   public void testEMR() throws Exception {
      this.kSession = this.kieTemplate.getKieSession(new String[]{"979318195305316352.drl"});
      IndexSaveVo indexSaveVo = new IndexSaveVo();
      List<ElemAttriVo> qcElemList = new ArrayList();
      ElemAttriVo elemAttriVo = new ElemAttriVo();
      elemAttriVo.setElemId(100205L);
      elemAttriVo.setContElemName("1111111");
      elemAttriVo.setElemConText("adsasd113dsa");
      ElemAttriVo elemAttriVo1 = new ElemAttriVo();
      elemAttriVo1.setElemId(100237L);
      elemAttriVo1.setContElemName("2222222");
      elemAttriVo1.setElemName("2222222");
      qcElemList.add(elemAttriVo);
      qcElemList.add(elemAttriVo1);
      indexSaveVo.setQcElemList(qcElemList);
      this.kSession.insert(indexSaveVo);
      int rules = this.kSession.fireAllRules();
      System.out.println("rules:: " + rules);
      System.out.println("rules:: " + indexSaveVo.getResultVoList().size());

      for(QcRuleResultVo qcRuleResultVo : indexSaveVo.getResultVoList()) {
         System.out.println(qcRuleResultVo.toString());
      }

   }
}
