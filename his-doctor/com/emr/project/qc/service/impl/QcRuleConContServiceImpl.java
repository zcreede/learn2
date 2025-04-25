package com.emr.project.qc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import com.emr.project.qc.domain.QcAgeConDict;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcGendConDict;
import com.emr.project.qc.domain.QcRuleConCont;
import com.emr.project.qc.domain.vo.QcRuleConContVo;
import com.emr.project.qc.mapper.QcRuleConContMapper;
import com.emr.project.qc.service.IQcAgeConDictService;
import com.emr.project.qc.service.IQcCheckElemService;
import com.emr.project.qc.service.IQcGendConDictService;
import com.emr.project.qc.service.IQcRuleConContService;
import com.emr.project.qc.service.IQcRuleUtilService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.service.ISysStaElemService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcRuleConContServiceImpl implements IQcRuleConContService {
   protected final Logger log = LoggerFactory.getLogger(QcRuleConContServiceImpl.class);
   @Autowired
   private QcRuleConContMapper qcRuleConContMapper;
   @Autowired
   private IQcCheckElemService qcCheckElemService;
   @Autowired
   private IQcRuleUtilService qcRuleUtilService;
   @Autowired
   private IQcAgeConDictService qcAgeConDictService;
   @Autowired
   private IQcGendConDictService qcGendConDictService;
   @Autowired
   private KieTemplateConfig kieTemplateConfig;
   @Autowired
   private ISysStaElemService sysStaElemService;

   public QcRuleConCont selectQcRuleConContById(Long id) {
      return this.qcRuleConContMapper.selectQcRuleConContById(id);
   }

   public List selectQcRuleConContList(QcRuleConContVo qcRuleConContVo) throws Exception {
      List<QcRuleConContVo> list = this.qcRuleConContMapper.selectQcRuleConContList(qcRuleConContVo);
      if (list != null && list.size() > 0) {
         QcCheckElem qcCheckElem1 = new QcCheckElem();
         qcCheckElem1.setCheckIdList((List)list.stream().map((s) -> s.getId()).collect(Collectors.toList()));
         List<QcCheckElem> checkElemList = this.qcCheckElemService.selectQcCheckElemList(qcCheckElem1);
         Map<Long, List<QcCheckElem>> mapList = (Map)checkElemList.stream().collect(Collectors.groupingBy((s) -> s.getCheckId()));

         for(QcRuleConContVo vo : list) {
            List<QcCheckElem> elemList = (List)mapList.get(vo.getId());
            vo.setElemVoList(elemList);
         }
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcRuleConCont(QcRuleConContVo qcRuleConContVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      if (qcRuleConContVo.getElemVoList() != null && !qcRuleConContVo.getElemVoList().isEmpty()) {
         this.qcRuleUtilService.getAddQcCheckElemList(qcRuleConContVo.getElemVoList(), id, CommonConstants.QC.RULE_TYPE_CODE_1);
      }

      qcRuleConContVo.setId(id);
      qcRuleConContVo.setRuleCode(id.toString());
      qcRuleConContVo.setDelFlag("0");
      qcRuleConContVo.setAddMeth("2");
      qcRuleConContVo.setCrePerCode(basEmployee.getEmplNumber());
      qcRuleConContVo.setCrePerName(basEmployee.getEmplName());
      this.getExpression(qcRuleConContVo);
      String checkFileName = this.genRuleFile(qcRuleConContVo);
      qcRuleConContVo.setRuleFile(checkFileName);
      this.qcRuleConContMapper.insertQcRuleConCont(qcRuleConContVo);
   }

   private void getExpression(QcRuleConContVo qcRuleConContVo) throws Exception {
      String[] keyTypeStr = qcRuleConContVo.getKeyType().split(",");
      List<String> dictTypeList = new ArrayList();

      for(int i = 0; i < keyTypeStr.length; ++i) {
         dictTypeList.add(keyTypeStr[i]);
      }

      JSONObject jsonObject = new JSONObject();
      if (qcRuleConContVo.getRuleType().equals("2")) {
         String json1 = "";
         String json2 = "";
         QcGendConDict qcGendConDict = new QcGendConDict();
         qcGendConDict.setDictTypeList(dictTypeList);

         for(QcGendConDict qcGendConDict1 : this.qcGendConDictService.selectQcGendConDictList(qcGendConDict)) {
            if (qcGendConDict1.getGenderCode().equals("1")) {
               String[] str = qcGendConDict1.getItemCon().split("#");

               for(int z = 0; z < str.length; ++z) {
                  json1 = json1 + "elemConText.contains(\"" + str[z] + "\") || ";
               }
            } else {
               String[] str = qcGendConDict1.getItemCon().split("#");

               for(int z = 0; z < str.length; ++z) {
                  json2 = json2 + "elemConText.contains(\"" + str[z] + "\") || ";
               }
            }
         }

         jsonObject.put("1", json1.substring(0, json1.length() - 3));
         jsonObject.put("2", json2.substring(0, json2.length() - 3));
      } else {
         String json1 = "";
         String json2 = "";
         String json3 = "";
         String json4 = "";
         QcAgeConDict qcAgeConDict = new QcAgeConDict();
         qcAgeConDict.setDictTypeList(dictTypeList);

         for(QcAgeConDict qcAgeConDict1 : this.qcAgeConDictService.selectQcAgeConDictList(qcAgeConDict)) {
            if (qcAgeConDict1.getAgeCode().equals("1")) {
               String[] str = qcAgeConDict1.getItemCon().split("#");

               for(int z = 0; z < str.length; ++z) {
                  json1 = json1 + "elemConText.contains(\"" + str[z] + "\") || ";
               }
            } else if (qcAgeConDict1.getAgeCode().equals("2")) {
               String[] str = qcAgeConDict1.getItemCon().split("#");

               for(int z = 0; z < str.length; ++z) {
                  json2 = json2 + "elemConText.contains(\"" + str[z] + "\") || ";
               }
            } else if (qcAgeConDict1.getAgeCode().equals("3")) {
               String[] str = qcAgeConDict1.getItemCon().split("#");

               for(int z = 0; z < str.length; ++z) {
                  json3 = json3 + "elemConText.contains(\"" + str[z] + "\") || ";
               }
            } else if (qcAgeConDict1.getAgeCode().equals("4")) {
               String[] str = qcAgeConDict1.getItemCon().split("#");

               for(int z = 0; z < str.length; ++z) {
                  json4 = json4 + "elemConText.contains(\"" + str[z] + "\") || ";
               }
            }
         }

         jsonObject.put("1", json1.substring(0, json1.length() - 3));
         jsonObject.put("2", json2.substring(0, json2.length() - 3));
         jsonObject.put("3", json3.substring(0, json3.length() - 3));
         jsonObject.put("4", json4.substring(0, json4.length() - 3));
      }

      qcRuleConContVo.setExpres(jsonObject.toJSONString());
   }

   private String genRuleFile(QcRuleConContVo qcRuleConContVo) throws Exception {
      try {
         new ArrayList();
         List e;
         if (qcRuleConContVo.getElemFlag().equals("1")) {
            e = this.sysStaElemService.selectQcElemList();
         } else {
            e = this.qcCheckElemService.selectQcCheckElemById(qcRuleConContVo.getId());
         }

         List<String> elemIdList = (List)e.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
         StringBuffer elemIdStr = new StringBuffer("");

         for(int i = 0; i < elemIdList.size(); ++i) {
            elemIdStr.append("elemId==\"" + (String)elemIdList.get(i) + "\" ||");
         }

         JSONObject jsonObject = JSONObject.parseObject(qcRuleConContVo.getExpres());
         String saveFileName = "";

         for(String key : jsonObject.keySet()) {
            String fileName = qcRuleConContVo.getId() + "_" + qcRuleConContVo.getRuleType() + "_" + key;
            List<GenerationRuleVo> generationRuleVoList = new ArrayList();
            GenerationRuleVo generationRuleVo = new GenerationRuleVo();
            generationRuleVo.setRule("");
            generationRuleVo.setErrorMsg(qcRuleConContVo.getRuleDesc());
            generationRuleVo.setRuleCode(qcRuleConContVo.getRuleName());
            generationRuleVo.setOtherRule("$s: ElemAttriVo(elemId!=null && elemConText!=null && (" + elemIdStr.substring(0, elemIdStr.length() - 2) + ") &&(" + jsonObject.get(key).toString() + ")) from $IndexSaveVo.getQcElemList");
            generationRuleVo.setEventType("IndexSaveVo");
            generationRuleVo.setEventVariable("$IndexSaveVo");
            generationRuleVo.setRuleId(qcRuleConContVo.getId().toString());
            generationRuleVo.setRuleName(fileName + "_" + qcRuleConContVo.getRuleName());
            generationRuleVo.setRuleResuleVo("QcRuleResultVo");
            generationRuleVoList.add(generationRuleVo);
            this.kieTemplateConfig.generationElemRules(generationRuleVoList, fileName + ".drl");
         }

         return saveFileName;
      } catch (Exception e) {
         this.log.error(e.getMessage());
         throw e;
      }
   }

   public void updateQcRuleConCont(QcRuleConContVo qcRuleConContVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = qcRuleConContVo.getId();
      if (qcRuleConContVo.getElemVoList() != null && !qcRuleConContVo.getElemVoList().isEmpty()) {
         this.qcRuleUtilService.getAddQcCheckElemList(qcRuleConContVo.getElemVoList(), id, CommonConstants.QC.RULE_TYPE_CODE_1);
      }

      qcRuleConContVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConContVo.setAltPerName(basEmployee.getEmplName());
      this.getExpression(qcRuleConContVo);
      String checkFileName = this.genRuleFile(qcRuleConContVo);
      qcRuleConContVo.setRuleFile(checkFileName);
      this.qcRuleConContMapper.updateQcRuleConCont(qcRuleConContVo);
   }

   public void updateEditFlag(QcRuleConContVo qcRuleConContVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleConContVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConContVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConContMapper.updateQcRuleConCont(qcRuleConContVo);
   }

   public int deleteQcRuleConContByIds(Long[] ids) {
      return this.qcRuleConContMapper.deleteQcRuleConContByIds(ids);
   }

   public void deleteQcRuleConContById(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      QcRuleConContVo qcRuleConContVo = new QcRuleConContVo();
      qcRuleConContVo.setId(id);
      qcRuleConContVo.setDelFlag("1");
      qcRuleConContVo.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleConContVo.setAltPerName(basEmployee.getEmplName());
      this.qcRuleConContMapper.updateQcRuleConCont(qcRuleConContVo);
   }

   public String getQcContExpressionString(Long id) throws Exception {
      return null;
   }
}
