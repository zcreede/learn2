package com.emr.project.webEditor.zb.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class CheckQuality extends BaseQuality {
   @JSONField(
      name = "CheckOption"
   )
   private String CheckOption;
   @JSONField(
      name = "IsChecked"
   )
   private Boolean IsChecked;
   @JSONField(
      name = "CheckPos"
   )
   private String CheckPos;

   public String getCheckOption() {
      return this.CheckOption;
   }

   public void setCheckOption(String checkOption) {
      this.CheckOption = checkOption;
   }

   public Boolean getIsChecked() {
      return this.IsChecked;
   }

   public void setIsChecked(Boolean isChecked) {
      this.IsChecked = isChecked;
   }

   public String getCheckPos() {
      return this.CheckPos;
   }

   public void setCheckPos(String checkPos) {
      this.CheckPos = checkPos;
   }
}
