package com.emr.project.emr.domain.vo;

import com.alibaba.fastjson.JSONArray;
import java.util.List;

public class RuleVo {
   private Long checkId;
   private String ruleName;
   private String errorMsg;
   private String errorColumn;
   private String checkVo;
   private String validationTable;
   private String relationTable;
   private List notNullList;
   private String notNullRule;
   private String checkColumn;
   private List enumerationList;
   private String regular;
   private String validationExpression;
   private String isCheckNull;
   private String relationValidationExpression;
   private String relationColumn;
   private String logicCheckRule;
   private String amount;
   private String offset;
   private List feeList;
   private JSONArray expression2List;
   private String validationExpression1;

   public JSONArray getExpression2List() {
      return this.expression2List;
   }

   public void setExpression2List(JSONArray expression2List) {
      this.expression2List = expression2List;
   }

   public String getValidationTable() {
      return this.validationTable;
   }

   public void setValidationTable(String validationTable) {
      this.validationTable = validationTable;
   }

   public String getRelationTable() {
      return this.relationTable;
   }

   public void setRelationTable(String relationTable) {
      this.relationTable = relationTable;
   }

   public String getValidationExpression1() {
      return this.validationExpression1;
   }

   public void setValidationExpression1(String validationExpression1) {
      this.validationExpression1 = validationExpression1;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getErrorMsg() {
      return this.errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

   public String getErrorColumn() {
      return this.errorColumn;
   }

   public void setErrorColumn(String errorColumn) {
      this.errorColumn = errorColumn;
   }

   public String getCheckVo() {
      return this.checkVo;
   }

   public void setCheckVo(String checkVo) {
      this.checkVo = checkVo;
   }

   public List getNotNullList() {
      return this.notNullList;
   }

   public void setNotNullList(List notNullList) {
      this.notNullList = notNullList;
   }

   public String getNotNullRule() {
      return this.notNullRule;
   }

   public void setNotNullRule(String notNullRule) {
      this.notNullRule = notNullRule;
   }

   public String getCheckColumn() {
      return this.checkColumn;
   }

   public void setCheckColumn(String checkColumn) {
      this.checkColumn = checkColumn;
   }

   public List getEnumerationList() {
      return this.enumerationList;
   }

   public void setEnumerationList(List enumerationList) {
      this.enumerationList = enumerationList;
   }

   public String getRegular() {
      return this.regular;
   }

   public void setRegular(String regular) {
      this.regular = regular;
   }

   public String getValidationExpression() {
      return this.validationExpression;
   }

   public void setValidationExpression(String validationExpression) {
      this.validationExpression = validationExpression;
   }

   public String getIsCheckNull() {
      return this.isCheckNull;
   }

   public void setIsCheckNull(String isCheckNull) {
      this.isCheckNull = isCheckNull;
   }

   public String getRelationValidationExpression() {
      return this.relationValidationExpression;
   }

   public void setRelationValidationExpression(String relationValidationExpression) {
      this.relationValidationExpression = relationValidationExpression;
   }

   public String getRelationColumn() {
      return this.relationColumn;
   }

   public void setRelationColumn(String relationColumn) {
      this.relationColumn = relationColumn;
   }

   public String getLogicCheckRule() {
      return this.logicCheckRule;
   }

   public void setLogicCheckRule(String logicCheckRule) {
      this.logicCheckRule = logicCheckRule;
   }

   public String getAmount() {
      return this.amount;
   }

   public void setAmount(String amount) {
      this.amount = amount;
   }

   public String getOffset() {
      return this.offset;
   }

   public void setOffset(String offset) {
      this.offset = offset;
   }

   public List getFeeList() {
      return this.feeList;
   }

   public void setFeeList(List feeList) {
      this.feeList = feeList;
   }

   public Long getCheckId() {
      return this.checkId;
   }

   public void setCheckId(Long checkId) {
      this.checkId = checkId;
   }
}
