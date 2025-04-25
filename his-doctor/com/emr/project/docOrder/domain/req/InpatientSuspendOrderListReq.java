package com.emr.project.docOrder.domain.req;

import com.emr.project.docOrder.domain.vo.InpatientOrderPerformDTO;
import java.util.ArrayList;
import java.util.List;

public class InpatientSuspendOrderListReq {
   private List list = new ArrayList();

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }
}
