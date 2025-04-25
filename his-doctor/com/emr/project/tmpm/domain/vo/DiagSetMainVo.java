package com.emr.project.tmpm.domain.vo;

import com.emr.project.tmpm.domain.DiagSetDetail;
import com.emr.project.tmpm.domain.DiagSetMain;
import java.util.List;

public class DiagSetMainVo extends DiagSetMain {
   private List detailList;

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }
}
