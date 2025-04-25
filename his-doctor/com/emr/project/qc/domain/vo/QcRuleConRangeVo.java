package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcRuleConRange;
import java.util.List;

public class QcRuleConRangeVo extends QcRuleConRange {
   private String defeLevelName;
   private List elemList;

   public String getDefeLevelName() {
      return this.defeLevelName;
   }

   public void setDefeLevelName(String defeLevelName) {
      this.defeLevelName = defeLevelName;
   }

   public List getElemList() {
      return this.elemList;
   }

   public void setElemList(List elemList) {
      this.elemList = elemList;
   }
}
