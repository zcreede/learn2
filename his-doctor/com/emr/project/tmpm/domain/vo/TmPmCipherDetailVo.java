package com.emr.project.tmpm.domain.vo;

import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.tmpm.domain.TmPmCipherDetail;
import java.util.List;

public class TmPmCipherDetailVo extends TmPmCipherDetail {
   private DrugAndClin drugAndClin;
   private List idList;

   public List getIdList() {
      return this.idList;
   }

   public void setIdList(List idList) {
      this.idList = idList;
   }

   public DrugAndClin getDrugAndClin() {
      return this.drugAndClin;
   }

   public void setDrugAndClin(DrugAndClin drugAndClin) {
      this.drugAndClin = drugAndClin;
   }
}
