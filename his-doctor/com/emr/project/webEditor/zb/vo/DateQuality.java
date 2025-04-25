package com.emr.project.webEditor.zb.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class DateQuality extends BaseQuality {
   private String currentTime;
   private String inoutHosTimeRange;
   @JSONField(
      name = "Style"
   )
   private String Style;
   @JSONField(
      name = "StyleFormat"
   )
   private String StyleFormat;
   @JSONField(
      name = "ContentException"
   )
   private String ContentException;
   @JSONField(
      name = "OutRangeInfo"
   )
   private String OutRangeInfo;
   @JSONField(
      name = "StartDateTime"
   )
   private String StartDateTime;
   @JSONField(
      name = "EndDateTime"
   )
   private String EndDateTime;
   @JSONField(
      name = "linkData"
   )
   private String linkData;
   @JSONField(
      name = "doCode"
   )
   private String doCode;
   @JSONField(
      name = "triggerWay"
   )
   private String triggerWay;

   public String getDoCode() {
      return this.doCode;
   }

   public void setDoCode(String doCode) {
      this.doCode = doCode;
   }

   public String getTriggerWay() {
      return this.triggerWay;
   }

   public void setTriggerWay(String triggerWay) {
      this.triggerWay = triggerWay;
   }

   public String getLinkData() {
      return this.linkData;
   }

   public void setLinkData(String linkData) {
      this.linkData = linkData;
   }

   public String getStartDateTime() {
      return this.StartDateTime;
   }

   public void setStartDateTime(String startDateTime) {
      this.StartDateTime = startDateTime;
   }

   public String getEndDateTime() {
      return this.EndDateTime;
   }

   public void setEndDateTime(String endDateTime) {
      this.EndDateTime = endDateTime;
   }

   public String getCurrentTime() {
      return this.currentTime;
   }

   public void setCurrentTime(String currentTime) {
      this.currentTime = currentTime;
   }

   public String getInoutHosTimeRange() {
      return this.inoutHosTimeRange;
   }

   public void setInoutHosTimeRange(String inoutHosTimeRange) {
      this.inoutHosTimeRange = inoutHosTimeRange;
   }

   public String getStyle() {
      return this.Style;
   }

   public void setStyle(String style) {
      this.Style = style;
   }

   public String getStyleFormat() {
      return this.StyleFormat;
   }

   public void setStyleFormat(String styleFormat) {
      this.StyleFormat = styleFormat;
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
