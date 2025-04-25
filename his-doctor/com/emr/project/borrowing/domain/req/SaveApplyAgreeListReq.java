package com.emr.project.borrowing.domain.req;

import java.util.ArrayList;
import java.util.List;

public class SaveApplyAgreeListReq {
   private List list = new ArrayList();

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }
}
