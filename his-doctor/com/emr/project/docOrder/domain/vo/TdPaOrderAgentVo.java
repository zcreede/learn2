package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdPaOrderAgent;

public class TdPaOrderAgentVo extends TdPaOrderAgent {
   private String idCard;
   private String sexCd;
   private String sex;
   private Long age;
   private String weight;

   public String getIdCard() {
      return this.idCard;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public Long getAge() {
      return this.age;
   }

   public void setAge(Long age) {
      this.age = age;
   }

   public String getWeight() {
      return this.weight;
   }

   public void setWeight(String weight) {
      this.weight = weight;
   }
}
