package com.emr.project.webEditor.zb.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class NumberQuality extends BaseQuality {
   @JSONField(
      name = "MaxValue"
   )
   private Integer MaxValue;
   @JSONField(
      name = "MinValue"
   )
   private Integer MinValue;
   @JSONField(
      name = "Precision"
   )
   private Integer Precision;
   @JSONField(
      name = "Unit"
   )
   private String Unit;
   @JSONField(
      name = "ContentException"
   )
   private String ContentException;
   @JSONField(
      name = "OutRangeInfo"
   )
   private String OutRangeInfo;

   public Integer getMaxValue() {
      return this.MaxValue;
   }

   public void setMaxValue(Integer maxValue) {
      this.MaxValue = maxValue;
   }

   public Integer getMinValue() {
      return this.MinValue;
   }

   public void setMinValue(Integer minValue) {
      this.MinValue = minValue;
   }

   public Integer getPrecision() {
      return this.Precision;
   }

   public void setPrecision(Integer precision) {
      this.Precision = precision;
   }

   public String getUnit() {
      return this.Unit;
   }

   public void setUnit(String unit) {
      this.Unit = unit;
   }

   public String getContentException() {
      return this.ContentException;
   }

   public void setContentException(String contentException) {
      this.ContentException = contentException;
   }

   public String getOutRangeInfo() {
      return this.OutRangeInfo;
   }

   public void setOutRangeInfo(String outRangeInfo) {
      this.OutRangeInfo = outRangeInfo;
   }
}
