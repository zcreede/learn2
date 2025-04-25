package com.emr.project.mrhp.domain.vo;

import com.emr.project.mrhp.domain.MrHpCheckSet;
import com.emr.project.qc.domain.QcCheckElem;
import java.util.List;

public class MrHpCheckSetVo extends MrHpCheckSet {
   private String checkActMrTypeName;
   private String checkEventNoName;
   private Long setId;
   private CheckExpressionVo checkExpressionVo;
   private List checkExpression1List;
   private List checkExpression2List;
   private List checkFeeVoList;
   private List checkEventNoList;
   private String tableName;
   private String fieldName;
   private List qcCheckElemList;

   public List getQcCheckElemList() {
      return this.qcCheckElemList;
   }

   public void setQcCheckElemList(List qcCheckElemList) {
      this.qcCheckElemList = qcCheckElemList;
   }

   public String getFieldName() {
      return this.fieldName;
   }

   public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
   }

   public String getTableName() {
      return this.tableName;
   }

   public void setTableName(String tableName) {
      this.tableName = tableName;
   }

   public List getCheckEventNoList() {
      return this.checkEventNoList;
   }

   public void setCheckEventNoList(List checkEventNoList) {
      this.checkEventNoList = checkEventNoList;
   }

   public List getCheckFeeVoList() {
      return this.checkFeeVoList;
   }

   public void setCheckFeeVoList(List checkFeeVoList) {
      this.checkFeeVoList = checkFeeVoList;
   }

   public List getCheckExpression1List() {
      return this.checkExpression1List;
   }

   public void setCheckExpression1List(List checkExpression1List) {
      this.checkExpression1List = checkExpression1List;
   }

   public List getCheckExpression2List() {
      return this.checkExpression2List;
   }

   public void setCheckExpression2List(List checkExpression2List) {
      this.checkExpression2List = checkExpression2List;
   }

   public Long getSetId() {
      return this.setId;
   }

   public void setSetId(Long setId) {
      this.setId = setId;
   }

   public CheckExpressionVo getCheckExpressionVo() {
      return this.checkExpressionVo;
   }

   public void setCheckExpressionVo(CheckExpressionVo checkExpressionVo) {
      this.checkExpressionVo = checkExpressionVo;
   }

   public String getCheckActMrTypeName() {
      return this.checkActMrTypeName;
   }

   public void setCheckActMrTypeName(String checkActMrTypeName) {
      this.checkActMrTypeName = checkActMrTypeName;
   }

   public String getCheckEventNoName() {
      return this.checkEventNoName;
   }

   public void setCheckEventNoName(String checkEventNoName) {
      this.checkEventNoName = checkEventNoName;
   }
}
