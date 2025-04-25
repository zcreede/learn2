package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import java.util.List;

public class TmPmOrderSetDetailVo extends TmPmOrderSetDetail {
   private List mainIdList;
   private Long rowId;
   private List updateList;

   public List getUpdateList() {
      return this.updateList;
   }

   public void setUpdateList(List updateList) {
      this.updateList = updateList;
   }

   public Long getRowId() {
      return this.rowId;
   }

   public void setRowId(Long rowId) {
      this.rowId = rowId;
   }

   public List getMainIdList() {
      return this.mainIdList;
   }

   public void setMainIdList(List mainIdList) {
      this.mainIdList = mainIdList;
   }
}
