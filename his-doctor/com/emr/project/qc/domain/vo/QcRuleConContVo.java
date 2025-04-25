package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRuleConCont;
import java.util.List;

public class QcRuleConContVo extends QcRuleConCont {
   private String defeLevelName;
   private List elemVoList;

   public List getElemVoList() {
      return this.elemVoList;
   }

   public void setElemVoList(List elemVoList) {
      this.elemVoList = elemVoList;
   }

   public String getDefeLevelName() {
      return this.defeLevelName;
   }

   public void setDefeLevelName(String defeLevelName) {
      this.defeLevelName = defeLevelName;
   }
}
