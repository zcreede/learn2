package com.emr.project.esSearch.domain.req;

import com.emr.project.esSearch.domain.TmPaFreeze;
import java.util.List;

public class DrugStockUpdateReq {
   private String subtractFlag;
   private List freezeList;
   private List freezeListUpdateNum;
   private List freezeListDel;

   public DrugStockUpdateReq() {
   }

   public DrugStockUpdateReq(String subtractFlag, List freezeList) {
      this.subtractFlag = subtractFlag;
      this.freezeList = freezeList;
   }

   public String getSubtractFlag() {
      return this.subtractFlag;
   }

   public void setSubtractFlag(String subtractFlag) {
      this.subtractFlag = subtractFlag;
   }

   public List getFreezeList() {
      return this.freezeList;
   }

   public void setFreezeList(List freezeList) {
      this.freezeList = freezeList;
   }

   public List getFreezeListUpdateNum() {
      return this.freezeListUpdateNum;
   }

   public void setFreezeListUpdateNum(List freezeListUpdateNum) {
      this.freezeListUpdateNum = freezeListUpdateNum;
   }

   public List getFreezeListDel() {
      return this.freezeListDel;
   }

   public void setFreezeListDel(List freezeListDel) {
      this.freezeListDel = freezeListDel;
   }
}
