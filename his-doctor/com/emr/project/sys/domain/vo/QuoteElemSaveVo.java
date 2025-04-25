package com.emr.project.sys.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.sys.domain.QuoteElem;
import java.util.List;

public class QuoteElemSaveVo {
   private String tempType;
   @Excel(
      name = "元素ID   元素管理表中的ID"
   )
   private String elemId;
   @Excel(
      name = "元素名称  元素管理表中的名称"
   )
   private String elemName;
   @Excel(
      name = "元素编码  元素管理表中的编码"
   )
   private String elemCd;
   List fromElemList;
   private String base64Flag;

   public String getBase64Flag() {
      return this.base64Flag;
   }

   public void setBase64Flag(String base64Flag) {
      this.base64Flag = base64Flag;
   }

   public String getTempType() {
      return this.tempType;
   }

   public void setTempType(String tempType) {
      this.tempType = tempType;
   }

   public String getElemId() {
      return this.elemId;
   }

   public void setElemId(String elemId) {
      this.elemId = elemId;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public List getFromElemList() {
      return this.fromElemList;
   }

   public void setFromElemList(List fromElemList) {
      this.fromElemList = fromElemList;
   }
}
