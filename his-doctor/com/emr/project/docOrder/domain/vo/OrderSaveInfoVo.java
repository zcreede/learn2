package com.emr.project.docOrder.domain.vo;

import java.util.List;

public class OrderSaveInfoVo {
   private List orderSaveVoList;
   private TdPaOrderAgentVo tdPaOrderAgentVo;
   private String operatingRoomFlag;

   public List getOrderSaveVoList() {
      return this.orderSaveVoList;
   }

   public void setOrderSaveVoList(List orderSaveVoList) {
      this.orderSaveVoList = orderSaveVoList;
   }

   public TdPaOrderAgentVo getTdPaOrderAgentVo() {
      return this.tdPaOrderAgentVo;
   }

   public void setTdPaOrderAgentVo(TdPaOrderAgentVo tdPaOrderAgentVo) {
      this.tdPaOrderAgentVo = tdPaOrderAgentVo;
   }

   public String getOperatingRoomFlag() {
      return this.operatingRoomFlag;
   }

   public void setOperatingRoomFlag(String operatingRoomFlag) {
      this.operatingRoomFlag = operatingRoomFlag;
   }
}
