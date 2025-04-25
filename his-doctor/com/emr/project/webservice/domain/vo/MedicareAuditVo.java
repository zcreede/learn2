package com.emr.project.webservice.domain.vo;

import java.util.List;

public class MedicareAuditVo {
   private List medicareAuditResultVoList;
   private String errorMsg;

   public List getMedicareAuditResultVoList() {
      return this.medicareAuditResultVoList;
   }

   public void setMedicareAuditResultVoList(List medicareAuditResultVoList) {
      this.medicareAuditResultVoList = medicareAuditResultVoList;
   }

   public String getErrorMsg() {
      return this.errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }
}
