package com.emr.project.sys.domain.vo;

import java.util.List;

public class BusIntegMenuVo {
   private List currMenuTreeList;
   private List hisMenuTreeList;

   public BusIntegMenuVo() {
   }

   public BusIntegMenuVo(List currMenuTreeList, List hisMenuTreeList) {
      this.currMenuTreeList = currMenuTreeList;
      this.hisMenuTreeList = hisMenuTreeList;
   }

   public List getCurrMenuTreeList() {
      return this.currMenuTreeList;
   }

   public void setCurrMenuTreeList(List currMenuTreeList) {
      this.currMenuTreeList = currMenuTreeList;
   }

   public List getHisMenuTreeList() {
      return this.hisMenuTreeList;
   }

   public void setHisMenuTreeList(List hisMenuTreeList) {
      this.hisMenuTreeList = hisMenuTreeList;
   }
}
