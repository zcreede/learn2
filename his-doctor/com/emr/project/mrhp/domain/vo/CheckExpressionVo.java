package com.emr.project.mrhp.domain.vo;

import com.alibaba.fastjson.JSONArray;
import com.emr.project.system.domain.SysCustomUnit;

public class CheckExpressionVo extends SysCustomUnit {
   private String fieldProp;
   private JSONArray operatorList;
   private JSONArray codeOperatorList;
   private String fieldTable;
   private String fieldColumn;
   private String fieldDictType;
   private String fieldDictDataProp;
   private String operator;
   private String codeOperator;
   private String fieldDictDataValue;
   private String logicOperator;
   private String preBrackets;
   private String afterBrackets;
   private String dictType;
   private String checkExpression;

   public String getCheckExpression() {
      return this.checkExpression;
   }

   public void setCheckExpression(String checkExpression) {
      this.checkExpression = checkExpression;
   }

   public String getDictType() {
      return this.dictType;
   }

   public void setDictType(String dictType) {
      this.dictType = dictType;
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

   public String getFieldDictDataValue() {
      return this.fieldDictDataValue;
   }

   public void setFieldDictDataValue(String fieldDictDataValue) {
      this.fieldDictDataValue = fieldDictDataValue;
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

   public String getFieldProp() {
      return this.fieldProp;
   }

   public void setFieldProp(String fieldProp) {
      this.fieldProp = fieldProp;
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

   public String getFieldTable() {
      return this.fieldTable;
   }

   public void setFieldTable(String fieldTable) {
      this.fieldTable = fieldTable;
   }

   public String getFieldColumn() {
      return this.fieldColumn;
   }

   public void setFieldColumn(String fieldColumn) {
      this.fieldColumn = fieldColumn;
   }

   public String getFieldDictType() {
      return this.fieldDictType;
   }

   public void setFieldDictType(String fieldDictType) {
      this.fieldDictType = fieldDictType;
   }

   public String getFieldDictDataProp() {
      return this.fieldDictDataProp;
   }

   public void setFieldDictDataProp(String fieldDictDataProp) {
      this.fieldDictDataProp = fieldDictDataProp;
   }
}
