package com.emr.project.dr.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class DrHandoverPrintVo {
   private List mainVo = new ArrayList();
   private List detailVoList = new ArrayList();

   public List getMainVo() {
      return this.mainVo;
   }

   public void setMainVo(List mainVo) {
      this.mainVo = mainVo;
   }

   public List getDetailVoList() {
      return this.detailVoList;
   }

   public void setDetailVoList(List detailVoList) {
      this.detailVoList = detailVoList;
   }
}
