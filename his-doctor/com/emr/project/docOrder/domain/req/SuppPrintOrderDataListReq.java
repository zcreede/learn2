package com.emr.project.docOrder.domain.req;

import java.util.ArrayList;
import java.util.List;

public class SuppPrintOrderDataListReq {
   private List list = new ArrayList();

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }
}
