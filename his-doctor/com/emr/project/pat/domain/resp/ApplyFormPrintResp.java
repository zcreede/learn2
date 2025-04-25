package com.emr.project.pat.domain.resp;

import com.emr.project.docOrder.domain.vo.TdPaApplyFormItemVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import java.math.BigDecimal;
import java.util.List;

public class ApplyFormPrintResp {
   private TdPaApplyFormVo applyForm;
   private List applyItemList;
   private BigDecimal totalPrice;
   private String hospitalName;
   private String urgentName;
   private String infectiousName;

   public TdPaApplyFormVo getApplyForm() {
      return this.applyForm;
   }

   public void setApplyForm(TdPaApplyFormVo applyForm) {
      this.applyForm = applyForm;
   }

   public List getApplyItemList() {
      return this.applyItemList;
   }

   public void setApplyItemList(List applyItemList) {
      this.applyItemList = applyItemList;
   }

   public BigDecimal getTotalPrice() {
      return this.totalPrice;
   }

   public void setTotalPrice(BigDecimal totalPrice) {
      this.totalPrice = totalPrice;
   }

   public String getHospitalName() {
      return this.hospitalName;
   }

   public void setHospitalName(String hospitalName) {
      this.hospitalName = hospitalName;
   }

   public String getUrgentName() {
      return this.urgentName;
   }

   public void setUrgentName(String urgentName) {
      this.urgentName = urgentName;
   }

   public String getInfectiousName() {
      return this.infectiousName;
   }

   public void setInfectiousName(String infectiousName) {
      this.infectiousName = infectiousName;
   }
}
