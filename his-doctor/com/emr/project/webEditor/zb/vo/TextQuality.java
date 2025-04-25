package com.emr.project.webEditor.zb.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class TextQuality extends BaseQuality {
   @JSONField(
      name = "TextBoxRes"
   )
   private String TextBoxRes;
   @JSONField(
      name = "TextBoxMinLen"
   )
   private Integer TextBoxMinLen;
   @JSONField(
      name = "TextBoxMaxLen"
   )
   private Integer TextBoxMaxLen;
   private int sort;

   public int getSort() {
      return this.sort;
   }

   public void setSort(int sort) {
      this.sort = sort;
   }

   public String getTextBoxRes() {
      return this.TextBoxRes;
   }

   public void setTextBoxRes(String textBoxRes) {
      this.TextBoxRes = textBoxRes;
   }

   public Integer getTextBoxMinLen() {
      return this.TextBoxMinLen;
   }

   public void setTextBoxMinLen(Integer textBoxMinLen) {
      this.TextBoxMinLen = textBoxMinLen;
   }

   public Integer getTextBoxMaxLen() {
      return this.TextBoxMaxLen;
   }

   public void setTextBoxMaxLen(Integer textBoxMaxLen) {
      this.TextBoxMaxLen = textBoxMaxLen;
   }
}
