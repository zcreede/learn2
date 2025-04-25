package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.AlleInfo;
import java.util.List;

public class AlleInfoVo extends AlleInfo {
   List sonList;
   String caseNo;

   public List getSonList() {
      return this.sonList;
   }

   public void setSonList(List sonList) {
      this.sonList = sonList;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }
}
