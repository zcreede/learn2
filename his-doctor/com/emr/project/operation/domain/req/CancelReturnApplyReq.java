package com.emr.project.operation.domain.req;

import com.emr.project.operation.domain.TakeDrugReturn;
import java.util.ArrayList;
import java.util.List;

public class CancelReturnApplyReq {
   private List params = new ArrayList();

   public List getParams() {
      return this.params;
   }

   public void setParams(List params) {
      this.params = params;
   }
}
