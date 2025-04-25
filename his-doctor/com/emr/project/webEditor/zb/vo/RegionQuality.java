package com.emr.project.webEditor.zb.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class RegionQuality extends BaseQuality {
   @JSONField(
      name = "Title"
   )
   private String Title;
   @JSONField(
      name = "TitleVisible"
   )
   private Boolean TitleVisible;
   @JSONField(
      name = "FrontIndent"
   )
   private Double FrontIndent;
   @JSONField(
      name = "BackIndent"
   )
   private Double BackIndent;
   @JSONField(
      name = "Visible"
   )
   private String Visible;

   public String getTitle() {
      return this.Title;
   }

   public void setTitle(String title) {
      this.Title = title;
   }

   public Boolean getTitleVisible() {
      return this.TitleVisible;
   }

   public void setTitleVisible(Boolean titleVisible) {
      this.TitleVisible = titleVisible;
   }

   public Double getFrontIndent() {
      return this.FrontIndent;
   }

   public void setFrontIndent(Double frontIndent) {
      this.FrontIndent = frontIndent;
   }

   public Double getBackIndent() {
      return this.BackIndent;
   }

   public void setBackIndent(Double backIndent) {
      this.BackIndent = backIndent;
   }

   public String getVisible() {
      return this.Visible;
   }

   public void setVisible(String visible) {
      this.Visible = visible;
   }
}
