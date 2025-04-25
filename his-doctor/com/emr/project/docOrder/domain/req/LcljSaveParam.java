package com.emr.project.docOrder.domain.req;

import java.util.List;

public class LcljSaveParam {
   private String projectCode;
   private String operCode;
   private String sign;
   private String zyh;
   private List cpPatientYzgxb;

   public String getProjectCode() {
      return this.projectCode;
   }

   public void setProjectCode(String projectCode) {
      this.projectCode = projectCode;
   }

   public String getOperCode() {
      return this.operCode;
   }

   public void setOperCode(String operCode) {
      this.operCode = operCode;
   }

   public String getSign() {
      return this.sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public List getCpPatientYzgxb() {
      return this.cpPatientYzgxb;
   }

   public void setCpPatientYzgxb(List cpPatientYzgxb) {
      this.cpPatientYzgxb = cpPatientYzgxb;
   }

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }
}
