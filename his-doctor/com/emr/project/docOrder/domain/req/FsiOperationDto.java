package com.emr.project.docOrder.domain.req;

import java.io.Serializable;

public class FsiOperationDto implements Serializable {
   private String setl_list_oprn_id;
   private String oprn_code;
   private String oprn_name;
   private String main_oprn_flag;
   private String oprn_date;
   private String anst_way;
   private String oper_dr_name;
   private String oper_dr_code;
   private String anst_dr_name;
   private String anst_dr_code;

   public String getSetl_list_oprn_id() {
      return this.setl_list_oprn_id;
   }

   public void setSetl_list_oprn_id(String setl_list_oprn_id) {
      this.setl_list_oprn_id = setl_list_oprn_id;
   }

   public String getOprn_code() {
      return this.oprn_code;
   }

   public void setOprn_code(String oprn_code) {
      this.oprn_code = oprn_code;
   }

   public String getOprn_name() {
      return this.oprn_name;
   }

   public void setOprn_name(String oprn_name) {
      this.oprn_name = oprn_name;
   }

   public String getMain_oprn_flag() {
      return this.main_oprn_flag;
   }

   public void setMain_oprn_flag(String main_oprn_flag) {
      this.main_oprn_flag = main_oprn_flag;
   }

   public String getOprn_date() {
      return this.oprn_date;
   }

   public void setOprn_date(String oprn_date) {
      this.oprn_date = oprn_date;
   }

   public String getAnst_way() {
      return this.anst_way;
   }

   public void setAnst_way(String anst_way) {
      this.anst_way = anst_way;
   }

   public String getOper_dr_name() {
      return this.oper_dr_name;
   }

   public void setOper_dr_name(String oper_dr_name) {
      this.oper_dr_name = oper_dr_name;
   }

   public String getOper_dr_code() {
      return this.oper_dr_code;
   }

   public void setOper_dr_code(String oper_dr_code) {
      this.oper_dr_code = oper_dr_code;
   }

   public String getAnst_dr_name() {
      return this.anst_dr_name;
   }

   public void setAnst_dr_name(String anst_dr_name) {
      this.anst_dr_name = anst_dr_name;
   }

   public String getAnst_dr_code() {
      return this.anst_dr_code;
   }

   public void setAnst_dr_code(String anst_dr_code) {
      this.anst_dr_code = anst_dr_code;
   }
}
