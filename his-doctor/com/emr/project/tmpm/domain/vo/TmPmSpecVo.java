package com.emr.project.tmpm.domain.vo;

import com.emr.project.tmpm.domain.TmPmSpec;
import java.util.List;

public class TmPmSpecVo extends TmPmSpec {
   private String admissionNo;
   private List list;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }
}
