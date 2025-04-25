package com.emr.project.webEditor.util;

import com.emr.project.emr.domain.vo.XmlElementParseConfigVo;
import com.emr.project.sys.domain.vo.QuoteElemVo;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.TmplQuoteElem;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.webEditor.zb.util.ZBXmlElementParseUtil;
import java.util.ArrayList;
import java.util.List;

public class XmlElementParseUtil {
   public static TempIndexSaveElemVo getSaveElemFromXml(String xmlStr, String editorType) {
      switch (editorType) {
         case "ZB":
            return ZBXmlElementParseUtil.getSaveElemFromXml(xmlStr);
         default:
            return null;
      }
   }

   public static TempIndexSaveElemVo getSaveElemFromXmlAll(String xmlStr, String editorType) {
      switch (editorType) {
         case "ZB":
            return ZBXmlElementParseUtil.getSaveElemFromXmlAll(xmlStr);
         default:
            return null;
      }
   }

   public static Boolean isSaveContType(String elementTagName, String editorType) {
      switch (editorType) {
         case "ZB":
            return ZBXmlElementParseUtil.isSaveContType(elementTagName);
         default:
            return null;
      }
   }

   public static List getQuoteElem(List elemAttriList, List quoteElemIdList) {
      List<TmplQuoteElem> tmplQuoteElemList = new ArrayList(1);

      for(ElemAttri elemAttri : elemAttriList) {
         Long elemId = elemAttri.getElemId();
         if (elemId != null && quoteElemIdList.contains(elemId)) {
            TmplQuoteElem tmplQuoteElem = new TmplQuoteElem(elemAttri.getContElemName(), elemAttri.getElemId(), elemAttri.getElemName(), elemAttri.getElemCd(), elemAttri.getTypeFlag(), elemAttri.getContType());
            tmplQuoteElemList.add(tmplQuoteElem);
         }
      }

      return tmplQuoteElemList;
   }

   public static List getSysQuoteElem(List elemAttriList, List quoteElemIdList) {
      List<QuoteElemVo> tmplQuoteElemList = new ArrayList(1);

      for(ElemAttri elemAttri : elemAttriList) {
         Long elemId = elemAttri.getElemId();
         if (elemId != null && quoteElemIdList.contains(elemId)) {
            QuoteElemVo tmplQuoteElem = new QuoteElemVo(elemAttri.getContElemName(), String.valueOf(elemAttri.getElemId()), elemAttri.getElemName(), elemAttri.getElemCd());
            tmplQuoteElemList.add(tmplQuoteElem);
         }
      }

      return tmplQuoteElemList;
   }

   public static List getElemAttriVoListFromXml(String xmlStr, XmlElementParseConfigVo configVo) {
      switch (configVo.getEditorType()) {
         case "ZB":
            return ZBXmlElementParseUtil.getElemAttriVoListFromXml(xmlStr, configVo);
         default:
            return null;
      }
   }

   public static List getElemAttriVoListFromXmlNew(String xmlStr, XmlElementParseConfigVo configVo, String base64Id) {
      switch (configVo.getEditorType()) {
         case "ZB":
            return ZBXmlElementParseUtil.getElemAttriVoListFromXmlNew(xmlStr, configVo, base64Id);
         default:
            return null;
      }
   }

   public static List getLinkDataListFromXml(String xmlStr, XmlElementParseConfigVo configVo) {
      switch (configVo.getEditorType()) {
         case "ZB":
            return ZBXmlElementParseUtil.getLinkDataListFromXml(xmlStr, configVo);
         default:
            return null;
      }
   }

   public static List getHeaderElementListFromXmlStr(String xmlStr, String editorType) {
      switch (editorType) {
         case "ZB":
            return ZBXmlElementParseUtil.getHeaderElementListFromXmlStr(xmlStr);
         default:
            return null;
      }
   }

   public static List getElemMacroListFromXml(String xmlStr, String editorType) {
      switch (editorType) {
         case "ZB":
            return ZBXmlElementParseUtil.getElemMacroListFromXml(xmlStr);
         default:
            return null;
      }
   }
}
