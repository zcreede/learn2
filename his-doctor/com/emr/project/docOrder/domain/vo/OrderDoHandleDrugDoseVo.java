package com.emr.project.docOrder.domain.vo;

import com.emr.project.his.domain.vo.DrugDoseVo;
import java.util.List;

public class OrderDoHandleDrugDoseVo {
   private String noStockFlag;
   private List yfkcHzList;
   private List drugDoseVoList;
   private String errorMsg;

   public String getErrorMsg() {
      return this.errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

   public OrderDoHandleDrugDoseVo() {
   }

   public OrderDoHandleDrugDoseVo(String noStockFlag) {
      this.noStockFlag = noStockFlag;
   }

   public String getNoStockFlag() {
      return this.noStockFlag;
   }

   public void setNoStockFlag(String noStockFlag) {
      this.noStockFlag = noStockFlag;
   }

   public List getYfkcHzList() {
      return this.yfkcHzList;
   }

   public void setYfkcHzList(List yfkcHzList) {
      this.yfkcHzList = yfkcHzList;
   }

   public List getDrugDoseVoList() {
      return this.drugDoseVoList;
   }

   public void setDrugDoseVoList(List drugDoseVoList) {
      this.drugDoseVoList = drugDoseVoList;
   }
}
