package com.emr.project.webEditor.zb.vo;

public class SignQuality extends BaseQuality {
   private String sText;
   private String HelpTip;
   private String PlaceHolder;

   public String getsText() {
      return this.sText;
   }

   public void setsText(String sText) {
      this.sText = sText;
   }

   public String getHelpTip() {
      return this.HelpTip;
   }

   public void setHelpTip(String helpTip) {
      this.HelpTip = helpTip;
   }

   public String getPlaceHolder() {
      return this.PlaceHolder;
   }

   public void setPlaceHolder(String placeHolder) {
      this.PlaceHolder = placeHolder;
   }
}
