package com.emr.project.qc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.StringUtils;
import com.emr.framework.config.KieTemplateConfig;
import com.emr.project.emr.domain.vo.GenerationRuleVo;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.vo.ElemExpressionVo;
import com.emr.project.qc.service.IQcCheckElemService;
import com.emr.project.qc.service.IQcRuleUtilService;
import com.emr.project.system.service.ISysStaElemService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcRuleUtilServiceImpl implements IQcRuleUtilService {
   @Autowired
   private ISysStaElemService sysStaElemService;
   @Autowired
   private IQcCheckElemService qcCheckElemService;
   @Autowired
   private KieTemplateConfig kieTemplateConfig;

   public void getAddQcCheckElemList(List elemVoList, Long checkId, Integer ruleType) throws Exception {
      List<QcCheckElem> elemCheckList = new ArrayList();
      if (elemVoList != null && elemVoList.size() > 0) {
         for(QcCheckElem qcCheckElem : elemVoList) {
            qcCheckElem.setCheckId(checkId);
            qcCheckElem.setRuleType(ruleType);
            elemCheckList.add(qcCheckElem);
         }
      }

      if (elemCheckList != null && elemCheckList.size() > 0) {
         this.qcCheckElemService.deleteQcCheckElemById(checkId);
         this.qcCheckElemService.insertQcCheckElem(elemCheckList);
      }

   }

   public String getErrorColumnByExpressionVoList(List list) throws Exception {
      List<String> fieldPropList = new ArrayList(list.size());

      for(int i = 0; i < list.size(); ++i) {
         fieldPropList.add(((QcCheckElem)list.get(i)).getElemId().toString() + "|" + ((QcCheckElem)list.get(i)).getElemName());
      }

      fieldPropList.stream().distinct();
      return StringUtils.join(fieldPropList, ",");
   }

   public String createRuleFile(RuleVo ruleVo) throws Exception {
      String fileName = ruleVo.getCheckId() + ".drl";
      List<GenerationRuleVo> generationRuleVoList = new ArrayList();
      GenerationRuleVo generationRuleVo = new GenerationRuleVo();
      generationRuleVo.setOtherRule(ruleVo.getValidationExpression());
      generationRuleVo.setErrorMsg(ruleVo.getErrorMsg());
      generationRuleVo.setRuleCode(ruleVo.getRuleName());
      generationRuleVo.setEventType("IndexSaveVo");
      generationRuleVo.setEventVariable("$IndexSaveVo");
      generationRuleVo.setRuleId(ruleVo.getCheckId().toString());
      generationRuleVo.setRuleName(ruleVo.getCheckId() + "_" + ruleVo.getRuleName());
      generationRuleVo.setRuleResuleVo("QcRuleResultVo");
      generationRuleVo.setErrorColumn(ruleVo.getErrorColumn());
      generationRuleVoList.add(generationRuleVo);
      this.kieTemplateConfig.generationElemRules(generationRuleVoList, fileName);
      return fileName;
   }

   public String getExpressionJsonString(List elemExpressionVoList) throws Exception {
      JSONObject elemExpressionObj = new JSONObject();
      String elemExpression = "";
      String codeElemExpression = "";
      JSONArray elemExpressionVoArray = new JSONArray();
      if (elemExpressionVoList != null && !elemExpressionVoList.isEmpty()) {
         for(ElemExpressionVo elemExpressionVo : elemExpressionVoList) {
            JSONObject elemExpressionVoObj = (JSONObject)JSONObject.toJSON(elemExpressionVo);
            elemExpression = elemExpression + (elemExpressionVo.getPreBrackets().equals("1") ? "(" : "");
            elemExpression = elemExpression + elemExpressionVo.getElemName() + elemExpressionVo.getOperator();
            if (!elemExpressionVo.getOperator().equals("不为空") && !elemExpressionVo.getOperator().equals("为空")) {
               if (!elemExpressionVo.getContType().equals("2")) {
                  elemExpression = elemExpression + "\"" + elemExpressionVo.getOperatorValue() + "\"";
               } else {
                  elemExpression = elemExpression + elemExpressionVo.getOperatorValue();
               }
            }

            elemExpression = elemExpression + (elemExpressionVo.getAfterBrackets().equals("1") ? ")" : "");
            elemExpression = elemExpression + elemExpressionVo.getLogicOperator();
            codeElemExpression = codeElemExpression + (elemExpressionVo.getPreBrackets().equals("1") ? "(" : "");
            String valueStr = elemExpressionVo.getOperatorValue();
            String codeOperator = elemExpressionVo.getCodeOperator();
            codeOperator = codeOperator.replace("@E", "\"" + elemExpressionVo.getId().toString() + "\"").replace("@V", "\"" + valueStr + "\"").replace("@T", "elemConText").replace("@qc", "$qcElemList");
            codeElemExpression = codeElemExpression + codeOperator;
            codeElemExpression = codeElemExpression + (elemExpressionVo.getAfterBrackets().equals("1") ? ")" : "");
            if (StringUtils.isNotEmpty(elemExpressionVo.getLogicOperator())) {
               codeElemExpression = codeElemExpression + elemExpressionVo.getLogicOperator();
            }

            elemExpressionVoArray.add(elemExpressionVoObj);
         }
      }

      elemExpressionObj.put("elemExpression", elemExpression);
      elemExpressionObj.put("elemExpressionVoList", elemExpressionVoArray);
      elemExpressionObj.put("codeElemExpression", codeElemExpression);
      return elemExpressionObj.toJSONString();
   }

   public String getAggregateByObjectList(List list) throws Exception {
      StringBuilder sb = new StringBuilder();

      for(String s : list) {
         if (sb.length() > 0) {
            sb.append(",");
         }

         sb.append("\"" + s + "\"");
      }

      return sb.toString();
   }
}
