package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcAgeConDict;
import java.util.List;

public class QcAgeConDictVo extends QcAgeConDict {
   private List qcAgeConDictList;

   public List getQcAgeConDictList() {
      return this.qcAgeConDictList;
   }

   public void setQcAgeConDictList(List qcAgeConDictList) {
      this.qcAgeConDictList = qcAgeConDictList;
   }
}
