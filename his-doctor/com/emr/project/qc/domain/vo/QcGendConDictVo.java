package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcGendConDict;
import java.util.List;

public class QcGendConDictVo extends QcGendConDict {
   private List qcGendConDictList;

   public List getQcGendConDictList() {
      return this.qcGendConDictList;
   }

   public void setQcGendConDictList(List qcGendConDictList) {
      this.qcGendConDictList = qcGendConDictList;
   }
}
