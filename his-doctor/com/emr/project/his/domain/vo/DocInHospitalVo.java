package com.emr.project.his.domain.vo;

import com.emr.project.his.domain.DocInHospital;

public class DocInHospitalVo extends DocInHospital {
   private String age;
   private String mzh;

   public String getMzh() {
      return this.mzh;
   }

   public void setMzh(String mzh) {
      this.mzh = mzh;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }
}
