package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcRuleIterEmr;
import java.util.List;

public class QcRuleIterEmrVo extends QcRuleIterEmr {
   private List iterTypeCodeList;

   public List getIterTypeCodeList() {
      return this.iterTypeCodeList;
   }

   public void setIterTypeCodeList(List iterTypeCodeList) {
      this.iterTypeCodeList = iterTypeCodeList;
   }
}
