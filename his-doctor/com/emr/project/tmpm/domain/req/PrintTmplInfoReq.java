package com.emr.project.tmpm.domain.req;

import java.io.Serializable;
import java.util.List;

public class PrintTmplInfoReq implements Serializable {
   private List typeCodeList;

   public List getTypeCodeList() {
      return this.typeCodeList;
   }

   public void setTypeCodeList(List typeCodeList) {
      this.typeCodeList = typeCodeList;
   }
}
