package com.emr.project.tmpm.domain.vo;

import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.tmpm.domain.ClinItemCollection;

public class ClinItemCollectionVo extends ClinItemCollection {
   private DrugAndClin drugAndClin;

   public DrugAndClin getDrugAndClin() {
      return this.drugAndClin;
   }

   public void setDrugAndClin(DrugAndClin drugAndClin) {
      this.drugAndClin = drugAndClin;
   }
}
