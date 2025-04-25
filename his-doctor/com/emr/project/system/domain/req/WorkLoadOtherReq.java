package com.emr.project.system.domain.req;

import com.emr.project.system.domain.WorkLoadOther;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkLoadOtherReq implements Serializable {
   private Long mainId;
   private List otherList = new ArrayList();

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public List getOtherList() {
      return this.otherList;
   }

   public void setOtherList(List otherList) {
      this.otherList = otherList;
   }
}
