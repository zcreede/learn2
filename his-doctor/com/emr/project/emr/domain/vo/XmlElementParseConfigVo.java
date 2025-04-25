package com.emr.project.emr.domain.vo;

public class XmlElementParseConfigVo {
   private String editorType;
   private String replaceLineBreaks;
   private String lineBreaksElemIds;
   private String lineBreaksPosition;

   public XmlElementParseConfigVo() {
   }

   public XmlElementParseConfigVo(String editorType, String replaceLineBreaks, String lineBreaksElemIds, String lineBreaksPosition) {
      this.editorType = editorType;
      this.replaceLineBreaks = replaceLineBreaks;
      this.lineBreaksElemIds = lineBreaksElemIds;
      this.lineBreaksPosition = lineBreaksPosition;
   }

   public String getEditorType() {
      return this.editorType;
   }

   public void setEditorType(String editorType) {
      this.editorType = editorType;
   }

   public String getReplaceLineBreaks() {
      return this.replaceLineBreaks;
   }

   public void setReplaceLineBreaks(String replaceLineBreaks) {
      this.replaceLineBreaks = replaceLineBreaks;
   }

   public String getLineBreaksElemIds() {
      return this.lineBreaksElemIds;
   }

   public void setLineBreaksElemIds(String lineBreaksElemIds) {
      this.lineBreaksElemIds = lineBreaksElemIds;
   }

   public String getLineBreaksPosition() {
      return this.lineBreaksPosition;
   }

   public void setLineBreaksPosition(String lineBreaksPosition) {
      this.lineBreaksPosition = lineBreaksPosition;
   }
}
