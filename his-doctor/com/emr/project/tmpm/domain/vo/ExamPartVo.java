package com.emr.project.tmpm.domain.vo;

import com.emr.project.tmpm.domain.ExamPart;
import java.util.List;

public class ExamPartVo extends ExamPart {
   private String admissionNo;
   private List list;
   private Integer partmSort;
   private String itemCd;

   public String getItemCd() {
      return this.itemCd;
   }

   public void setItemCd(String itemCd) {
      this.itemCd = itemCd;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public Integer getPartmSort() {
      return this.partmSort;
   }

   public void setPartmSort(Integer partmSort) {
      this.partmSort = partmSort;
   }
}
