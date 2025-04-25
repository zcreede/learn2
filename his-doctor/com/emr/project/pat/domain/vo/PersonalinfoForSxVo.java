package com.emr.project.pat.domain.vo;

public class PersonalinfoForSxVo {
   private String patientId;
   private String patientName;
   private String inputstrphtc;
   private String sex;
   private String sexCd;
   private String cardTypeCd;
   private String cardType;
   private String idCard;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
   }

   public String getCardTypeCd() {
      return this.cardTypeCd;
   }

   public void setCardTypeCd(String cardTypeCd) {
      this.cardTypeCd = cardTypeCd;
   }

   public String getCardType() {
      return this.cardType;
   }

   public void setCardType(String cardType) {
      this.cardType = cardType;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }
}
