package com.emr.project.CDSS.domain.xyt;

public class Anaphylaxis {
   private String allergyType;
   private String allergenCode;
   private String allergenName;
   private String allergyDegree;

   public String getAllergyType() {
      return this.allergyType;
   }

   public void setAllergyType(String allergyType) {
      this.allergyType = allergyType;
   }

   public String getAllergenCode() {
      return this.allergenCode;
   }

   public void setAllergenCode(String allergenCode) {
      this.allergenCode = allergenCode;
   }

   public String getAllergenName() {
      return this.allergenName;
   }

   public void setAllergenName(String allergenName) {
      this.allergenName = allergenName;
   }

   public String getAllergyDegree() {
      return this.allergyDegree;
   }

   public void setAllergyDegree(String allergyDegree) {
      this.allergyDegree = allergyDegree;
   }
}
