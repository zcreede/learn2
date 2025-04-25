package com.emr.project.docOrder.domain.vo;

import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.system.domain.SysDept;
import java.util.List;

public class OrderListBaseInfoVo {
   private List babyList;
   private int longPageSize;
   private int tempPageSize;
   private int decoctionPageSize;
   private String tempDrugDoseViewFlag;
   private List orderDocDeptList;

   public int getTempPageSize() {
      return this.tempPageSize;
   }

   public void setTempPageSize(int tempPageSize) {
      this.tempPageSize = tempPageSize;
   }

   public int getDecoctionPageSize() {
      return this.decoctionPageSize;
   }

   public void setDecoctionPageSize(int decoctionPageSize) {
      this.decoctionPageSize = decoctionPageSize;
   }

   public int getLongPageSize() {
      return this.longPageSize;
   }

   public void setLongPageSize(int longPageSize) {
      this.longPageSize = longPageSize;
   }

   public List getBabyList() {
      return this.babyList;
   }

   public void setBabyList(List babyList) {
      this.babyList = babyList;
   }

   public String getTempDrugDoseViewFlag() {
      return this.tempDrugDoseViewFlag;
   }

   public void setTempDrugDoseViewFlag(String tempDrugDoseViewFlag) {
      this.tempDrugDoseViewFlag = tempDrugDoseViewFlag;
   }

   public List getOrderDocDeptList() {
      return this.orderDocDeptList;
   }

   public void setOrderDocDeptList(List orderDocDeptList) {
      this.orderDocDeptList = orderDocDeptList;
   }
}
