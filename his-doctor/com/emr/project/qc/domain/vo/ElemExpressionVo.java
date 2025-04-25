package com.emr.project.qc.domain.vo;

import com.alibaba.fastjson.JSONArray;
import com.emr.project.system.domain.SysStaElem;

public class ElemExpressionVo extends SysStaElem {
   private String operator;
   private String codeOperator;
   private String oppCodeOperator;
   private String operatorValue;
   private String logicOperator;
   private String preBrackets;
   private String afterBrackets;
   private JSONArray operatorList;
   private JSONArray codeOperatorList;
   private JSONArray oppCodeOperatorList;

   public JSONArray getOppCodeOperatorList() {
      return this.oppCodeOperatorList;
   }

   public void setOppCodeOperatorList(JSONArray oppCodeOperatorList) {
      this.oppCodeOperatorList = oppCodeOperatorList;
   }

   public String getOppCodeOperator() {
      return this.oppCodeOperator;
   }

   public void setOppCodeOperator(String oppCodeOperator) {
      this.oppCodeOperator = oppCodeOperator;
   }

   public String getOperatorValue() {
      return this.operatorValue;
   }

   public void setOperatorValue(String operatorValue) {
      this.operatorValue = operatorValue;
   }

   public JSONArray getOperatorList() {
      return this.operatorList;
   }

   public void setOperatorList(JSONArray operatorList) {
      this.operatorList = operatorList;
   }

   public JSONArray getCodeOperatorList() {
      return this.codeOperatorList;
   }

   public void setCodeOperatorList(JSONArray codeOperatorList) {
      this.codeOperatorList = codeOperatorList;
   }

   public String getOperator() {
      return this.operator;
   }

   public void setOperator(String operator) {
      this.operator = operator;
   }

   public String getCodeOperator() {
      return this.codeOperator;
   }

   public void setCodeOperator(String codeOperator) {
      this.codeOperator = codeOperator;
   }

   public String getLogicOperator() {
      return this.logicOperator;
   }

   public void setLogicOperator(String logicOperator) {
      this.logicOperator = logicOperator;
   }

   public String getPreBrackets() {
      return this.preBrackets;
   }

   public void setPreBrackets(String preBrackets) {
      this.preBrackets = preBrackets;
   }

   public String getAfterBrackets() {
      return this.afterBrackets;
   }

   public void setAfterBrackets(String afterBrackets) {
      this.afterBrackets = afterBrackets;
   }
}
