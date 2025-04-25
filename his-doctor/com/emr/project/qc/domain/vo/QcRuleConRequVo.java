package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRuleConRequ;
import java.util.List;

public class QcRuleConRequVo extends QcRuleConRequ {
   private String defeLevelName;
   private List elemVoList;
   private List elemList;
   private List elemExpressionVoList;

   public List getElemExpressionVoList() {
      return this.elemExpressionVoList;
   }

   public void setElemExpressionVoList(List elemExpressionVoList) {
      this.elemExpressionVoList = elemExpressionVoList;
   }

   public List getElemList() {
      return this.elemList;
   }

   public void setElemList(List elemList) {
      this.elemList = elemList;
   }

   public String getDefeLevelName() {
      return this.defeLevelName;
   }

   public void setDefeLevelName(String defeLevelName) {
      this.defeLevelName = defeLevelName;
   }

   public List getElemVoList() {
      return this.elemVoList;
   }

   public void setElemVoList(List elemVoList) {
      this.elemVoList = elemVoList;
   }
}
