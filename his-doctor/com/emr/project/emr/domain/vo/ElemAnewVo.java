package com.emr.project.emr.domain.vo;

import com.emr.project.sys.domain.vo.QuoteElemVo;
import com.emr.project.tmpl.domain.vo.ElemMacroVo;
import java.util.List;

public class ElemAnewVo {
   private List quoteElemVoList;
   private List elemMacroVoList;

   public List getQuoteElemVoList() {
      return this.quoteElemVoList;
   }

   public void setQuoteElemVoList(List quoteElemVoList) {
      this.quoteElemVoList = quoteElemVoList;
   }

   public List getElemMacroVoList() {
      return this.elemMacroVoList;
   }

   public void setElemMacroVoList(List elemMacroVoList) {
      this.elemMacroVoList = elemMacroVoList;
   }
}
