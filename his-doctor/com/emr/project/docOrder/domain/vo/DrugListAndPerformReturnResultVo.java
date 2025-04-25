package com.emr.project.docOrder.domain.vo;

import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugListLog;
import com.emr.project.operation.domain.TakeDrugReturn;
import java.util.List;

public class DrugListAndPerformReturnResultVo {
   private List deleteList;
   private List updateList;
   private List takeDrugListLogList;
   private List drugReturnList;
   private List yfkcHzList;
   private List takeDrugLists;

   public List getDeleteList() {
      return this.deleteList;
   }

   public void setDeleteList(List deleteList) {
      this.deleteList = deleteList;
   }

   public List getUpdateList() {
      return this.updateList;
   }

   public void setUpdateList(List updateList) {
      this.updateList = updateList;
   }

   public List getTakeDrugListLogList() {
      return this.takeDrugListLogList;
   }

   public void setTakeDrugListLogList(List takeDrugListLogList) {
      this.takeDrugListLogList = takeDrugListLogList;
   }

   public List getDrugReturnList() {
      return this.drugReturnList;
   }

   public void setDrugReturnList(List drugReturnList) {
      this.drugReturnList = drugReturnList;
   }

   public List getYfkcHzList() {
      return this.yfkcHzList;
   }

   public void setYfkcHzList(List yfkcHzList) {
      this.yfkcHzList = yfkcHzList;
   }

   public List getTakeDrugLists() {
      return this.takeDrugLists;
   }

   public void setTakeDrugLists(List takeDrugLists) {
      this.takeDrugLists = takeDrugLists;
   }
}
