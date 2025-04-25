package com.emr.project.docOrder.domain.vo;

import java.util.Date;

public class OrderDoHandlePrintVo {
   private String case_no;
   private String admission_no;
   private Integer hospitalized_count;
   private String yebz;
   private String type;
   private String order_no;
   private String order_sort_number;
   private Date filing_date;
   private String ward_no;
   private String operator;

   public OrderDoHandlePrintVo() {
   }

   public OrderDoHandlePrintVo(String case_no, String admission_no, Integer hospitalized_count, String yebz, String type, String order_no, String order_sort_number, Date filing_date, String ward_no, String operator) {
      this.case_no = case_no;
      this.admission_no = admission_no;
      this.hospitalized_count = hospitalized_count;
      this.yebz = yebz;
      this.type = type;
      this.order_no = order_no;
      this.order_sort_number = order_sort_number;
      this.filing_date = filing_date;
      this.ward_no = ward_no;
      this.operator = operator;
   }

   public String getCase_no() {
      return this.case_no;
   }

   public void setCase_no(String case_no) {
      this.case_no = case_no;
   }

   public String getAdmission_no() {
      return this.admission_no;
   }

   public void setAdmission_no(String admission_no) {
      this.admission_no = admission_no;
   }

   public Integer getHospitalized_count() {
      return this.hospitalized_count;
   }

   public void setHospitalized_count(Integer hospitalized_count) {
      this.hospitalized_count = hospitalized_count;
   }

   public String getYebz() {
      return this.yebz;
   }

   public void setYebz(String yebz) {
      this.yebz = yebz;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getOrder_no() {
      return this.order_no;
   }

   public void setOrder_no(String order_no) {
      this.order_no = order_no;
   }

   public String getOrder_sort_number() {
      return this.order_sort_number;
   }

   public void setOrder_sort_number(String order_sort_number) {
      this.order_sort_number = order_sort_number;
   }

   public Date getFiling_date() {
      return this.filing_date;
   }

   public void setFiling_date(Date filing_date) {
      this.filing_date = filing_date;
   }

   public String getWard_no() {
      return this.ward_no;
   }

   public void setWard_no(String ward_no) {
      this.ward_no = ward_no;
   }

   public String getOperator() {
      return this.operator;
   }

   public void setOperator(String operator) {
      this.operator = operator;
   }
}
