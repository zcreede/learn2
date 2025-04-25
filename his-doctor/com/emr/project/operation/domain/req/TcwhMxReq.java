package com.emr.project.operation.domain.req;

import com.emr.project.operation.domain.TmNaTcwhMx;
import java.util.List;

public class TcwhMxReq extends TmNaTcwhMx {
   private List tcwhIds;

   public List getTcwhIds() {
      return this.tcwhIds;
   }

   public void setTcwhIds(List tcwhIds) {
      this.tcwhIds = tcwhIds;
   }
}
