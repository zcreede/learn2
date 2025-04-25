package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrSignRecord;
import java.util.List;

public class EmrSignRecordVo {
   private List list;
   private Boolean cancleSignFlag;
   private String reason;
   private String emrState;
   private String freeMoveType;

   public String getFreeMoveType() {
      return this.freeMoveType;
   }

   public void setFreeMoveType(String freeMoveType) {
      this.freeMoveType = freeMoveType;
   }

   public String getEmrState() {
      return this.emrState;
   }

   public void setEmrState(String emrState) {
      this.emrState = emrState;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public Boolean getCancleSignFlag() {
      return this.cancleSignFlag;
   }

   public void setCancleSignFlag(Boolean cancleSignFlag) {
      this.cancleSignFlag = cancleSignFlag;
   }

   public String getReason() {
      return this.reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }
}
