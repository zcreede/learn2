package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrSearchCase;
import com.emr.project.emr.domain.EmrSearchCaseDetail;
import java.util.List;

public class EmrSearchCaseVo extends EmrSearchCase {
   private List detailList;

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }
}
