package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderPerformFirst extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "住院号"
   )
   private String admissionNo;
   @Excel(
      name = "入院次数"
   )
   private Integer hospitalizedCount;
   @Excel(
      name = "医嘱编号"
   )
   private String orderNo;
   @Excel(
      name = "医嘱序号"
   )
   private String orderSortNumber;
   @Excel(
      name = "执行单序号"
   )
   private String performListSortNumber;
   @Excel(
      name = "病区代码"
   )
   private String wardNo;
   @Excel(
      name = "1:第一瓶;2:加一瓶；3：普通；4：特殊；5：:避光器第一瓶；6：避光器加一瓶"
   )
   private String firstFlag;
   @Excel(
      name = "0:否；1：是"
   )
   private String jjbz;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setPerformListSortNumber(String performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setFirstFlag(String firstFlag) {
      this.firstFlag = firstFlag;
   }

   public String getFirstFlag() {
      return this.firstFlag;
   }

   public void setJjbz(String jjbz) {
      this.jjbz = jjbz;
   }

   public String getJjbz() {
      return this.jjbz;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("admissionNo", this.getAdmissionNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("orderNo", this.getOrderNo()).append("orderSortNumber", this.getOrderSortNumber()).append("performListSortNumber", this.getPerformListSortNumber()).append("wardNo", this.getWardNo()).append("firstFlag", this.getFirstFlag()).append("jjbz", this.getJjbz()).toString();
   }
}
