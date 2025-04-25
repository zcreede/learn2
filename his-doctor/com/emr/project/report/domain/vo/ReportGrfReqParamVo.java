package com.emr.project.report.domain.vo;

import java.util.List;
import java.util.Map;

public class ReportGrfReqParamVo {
   private String v_query_type;
   private String v_dept_code;
   private String v_admission_no;
   private String v_begin;
   private String v_end;
   private List v_data;
   private String var_bz;

   public List getV_data() {
      return this.v_data;
   }

   public void setV_data(List v_data) {
      this.v_data = v_data;
   }

   public String getV_query_type() {
      return this.v_query_type;
   }

   public void setV_query_type(String v_query_type) {
      this.v_query_type = v_query_type;
   }

   public String getV_dept_code() {
      return this.v_dept_code;
   }

   public void setV_dept_code(String v_dept_code) {
      this.v_dept_code = v_dept_code;
   }

   public String getV_admission_no() {
      return this.v_admission_no;
   }

   public void setV_admission_no(String v_admission_no) {
      this.v_admission_no = v_admission_no;
   }

   public String getV_begin() {
      return this.v_begin;
   }

   public void setV_begin(String v_begin) {
      this.v_begin = v_begin;
   }

   public String getV_end() {
      return this.v_end;
   }

   public void setV_end(String v_end) {
      this.v_end = v_end;
   }

   public String getVar_bz() {
      return this.var_bz;
   }

   public void setVar_bz(String var_bz) {
      this.var_bz = var_bz;
   }
}
