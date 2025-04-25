package com.emr.project.webservice.domain.req;

public class MedicareAuditDiagnoseDtosReq {
   private String dise_id;
   private String inout_dise_type;
   private String maindise_flag;
   private String dias_srt_no;
   private String dise_codg;
   private String dise_name;
   private String dise_date;

   public String getDise_date() {
      return this.dise_date;
   }

   public void setDise_date(String dise_date) {
      this.dise_date = dise_date;
   }

   public String getDise_id() {
      return this.dise_id;
   }

   public void setDise_id(String dise_id) {
      this.dise_id = dise_id;
   }

   public String getInout_dise_type() {
      return this.inout_dise_type;
   }

   public void setInout_dise_type(String inout_dise_type) {
      this.inout_dise_type = inout_dise_type;
   }

   public String getMaindise_flag() {
      return this.maindise_flag;
   }

   public void setMaindise_flag(String maindise_flag) {
      this.maindise_flag = maindise_flag;
   }

   public String getDias_srt_no() {
      return this.dias_srt_no;
   }

   public void setDias_srt_no(String dias_srt_no) {
      this.dias_srt_no = dias_srt_no;
   }

   public String getDise_codg() {
      return this.dise_codg;
   }

   public void setDise_codg(String dise_codg) {
      this.dise_codg = dise_codg;
   }

   public String getDise_name() {
      return this.dise_name;
   }

   public void setDise_name(String dise_name) {
      this.dise_name = dise_name;
   }
}
