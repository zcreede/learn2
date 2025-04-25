package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.resp.OrderStatusProcessDetail;
import java.util.ArrayList;
import java.util.List;

public class OderPerformResultVo {
   private List mainStatusList = new ArrayList();
   private List performDetailVo = new ArrayList();

   public List getMainStatusList() {
      return this.mainStatusList;
   }

   public void setMainStatusList(List mainStatusList) {
      this.mainStatusList = mainStatusList;
   }

   public List getPerformDetailVo() {
      return this.performDetailVo;
   }

   public void setPerformDetailVo(List performDetailVo) {
      this.performDetailVo = performDetailVo;
   }
}
