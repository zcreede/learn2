package com.emr.project.operation.domain.req;

import com.emr.project.operation.domain.dto.TakeDrugFeeDTO;
import java.util.ArrayList;
import java.util.List;

public class SaveTakeDrugFeeReq {
   private List list = new ArrayList();

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }
}
