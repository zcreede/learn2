package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdPaTakeDrugApply;

public class TdPaTakeDrugApplyVo extends TdPaTakeDrugApply {
   private String takeDrugDateStr;

   public String getTakeDrugDateStr() {
      return this.takeDrugDateStr;
   }

   public void setTakeDrugDateStr(String takeDrugDateStr) {
      this.takeDrugDateStr = takeDrugDateStr;
   }
}
