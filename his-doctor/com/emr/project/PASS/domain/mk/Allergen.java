package com.emr.project.PASS.domain.mk;

public class Allergen {
   private String Index;
   private String AllerCode;
   private String AllerName;
   private String AllerSymptom;

   public String getIndex() {
      return this.Index;
   }

   public void setIndex(String index) {
      this.Index = index;
   }

   public String getAllerCode() {
      return this.AllerCode;
   }

   public void setAllerCode(String allerCode) {
      this.AllerCode = allerCode;
   }

   public String getAllerName() {
      return this.AllerName;
   }

   public void setAllerName(String allerName) {
      this.AllerName = allerName;
   }

   public String getAllerSymptom() {
      return this.AllerSymptom;
   }

   public void setAllerSymptom(String allerSymptom) {
      this.AllerSymptom = allerSymptom;
   }
}
