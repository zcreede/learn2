package com.emr.project.webEditor.zb.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.project.emr.domain.vo.XmlElementParseConfigVo;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.ElemDate;
import com.emr.project.tmpl.domain.ElemGender;
import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.ElemPhysign;
import com.emr.project.tmpl.domain.ElemSign;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

public class ZBXmlElementParseUtil {
   protected static final Logger log = LoggerFactory.getLogger(ZBXmlElementParseUtil.class);

   public static TempIndexSaveElemVo getSaveElemFromXml(String xmlStr) {
      List<ElemAttri> elemAttriList = new ArrayList(1);
      List<ElemDate> elemDateList = new ArrayList(1);
      List<ElemGender> elemGenderList = new ArrayList(1);
      List<ElemMacro> elemMacroList = new ArrayList(1);
      List<ElemPhysign> elemPhysignList = new ArrayList(1);
      List<ElemSign> elemSignList = new ArrayList(1);
      List<Element> list = getElementListFromXmlStr(xmlStr);
      int size = list.size();
      int groupCount = 100;
      int a = size / groupCount;
      int b = size % groupCount;
      if (b > 0) {
         ++a;
      }

      for(int i = 0; i < a; ++i) {
         int beginIndex = i * groupCount;
         int endIndex = (i + 1) * groupCount;
         if (i + 1 == a) {
            endIndex = size;
         }

         List<Element> tempList = list.subList(beginIndex, endIndex);
         addElemAttri(tempList, elemAttriList, elemDateList, elemGenderList, elemMacroList, elemPhysignList, elemSignList);
      }

      TempIndexSaveElemVo tempIndexSaveElemVo = new TempIndexSaveElemVo(elemAttriList, elemDateList, elemGenderList, elemMacroList, elemPhysignList, elemSignList);
      return tempIndexSaveElemVo;
   }

   public static TempIndexSaveElemVo getSaveElemFromXmlAll(String xmlStr) {
      List<ElemAttri> elemAttriList = new ArrayList(1);
      List<ElemDate> elemDateList = new ArrayList(1);
      List<ElemGender> elemGenderList = new ArrayList(1);
      List<ElemMacro> elemMacroList = new ArrayList(1);
      List<ElemPhysign> elemPhysignList = new ArrayList(1);
      List<ElemSign> elemSignList = new ArrayList(1);
      List<Element> list = getElementListFromXmlStr(xmlStr);
      int size = list.size();
      int groupCount = 100;
      int a = size / groupCount;
      int b = size % groupCount;
      if (b > 0) {
         ++a;
      }

      for(int i = 0; i < a; ++i) {
         int beginIndex = i * groupCount;
         int endIndex = (i + 1) * groupCount;
         if (i + 1 == a) {
            endIndex = size;
         }

         List<Element> tempList = list.subList(beginIndex, endIndex);
         addElemAttriAll(tempList, elemAttriList, elemDateList, elemGenderList, elemMacroList, elemPhysignList, elemSignList);
      }

      TempIndexSaveElemVo tempIndexSaveElemVo = new TempIndexSaveElemVo(elemAttriList, elemDateList, elemGenderList, elemMacroList, elemPhysignList, elemSignList);
      return tempIndexSaveElemVo;
   }

   @Async
   protected static void addElemAttri(List list, List elemAttriList, List elemDateList, List elemGenderList, List elemMacroList, List elemPhysignList, List elemSignList) {
      for(Element elt : list) {
         ElemAttri elemAttri = getElemAttri(elt);
         String isCtrlHidden = elt.attributeValue("IsCtrlHidden");
         String typeFlag = elemAttri.getContType();
         boolean signElemFlag = false;
         if (StringUtils.isNotBlank(typeFlag) && (typeFlag.equals("11") || typeFlag.equals("12"))) {
            signElemFlag = true;
         }

         if (signElemFlag || !StringUtils.isNotBlank(isCtrlHidden) || !isCtrlHidden.toUpperCase().equals("TRUE")) {
            elemAttriList.add(elemAttri);
            ElemMacro elemMacro = getElemMacro(elt, elemAttri);
            ElemDate elemDate = getElemDate(elt, elemAttri);
            if (elemDate != null) {
               elemDateList.add(elemDate);
               if (elemMacro != null) {
                  elemMacro.setDataForm(elemDate.getDateFormat());
               }
            }

            ElemGender elemGender = getElemGender(elt, elemAttri);
            if (elemGender != null) {
               elemGenderList.add(elemGender);
            }

            ElemPhysign elemPhysign = getElemPhysign(elt, elemAttri);
            if (elemPhysign != null) {
               elemPhysignList.add(elemPhysign);
            }

            ElemSign elemSign = getElemSign(elt, elemAttri);
            if (elemSign != null) {
               elemSignList.add(elemSign);
            }

            if (elemMacro != null) {
               elemMacroList.add(elemMacro);
            }
         }
      }

   }

   protected static void addElemAttriAll(List list, List elemAttriList, List elemDateList, List elemGenderList, List elemMacroList, List elemPhysignList, List elemSignList) {
      for(Element elt : list) {
         ElemAttri elemAttri = getElemAttri(elt);
         String typeFlag = elemAttri.getContType();
         boolean signElemFlag = false;
         if (StringUtils.isNotBlank(typeFlag) && (typeFlag.equals("11") || typeFlag.equals("12"))) {
            signElemFlag = true;
         }

         elemAttriList.add(elemAttri);
         ElemMacro elemMacro = getElemMacro(elt, elemAttri);
         ElemDate elemDate = getElemDate(elt, elemAttri);
         if (elemDate != null) {
            elemDateList.add(elemDate);
            if (elemMacro != null) {
               elemMacro.setDataForm(elemDate.getDateFormat());
            }
         }

         ElemGender elemGender = getElemGender(elt, elemAttri);
         if (elemGender != null) {
            elemGenderList.add(elemGender);
         }

         ElemPhysign elemPhysign = getElemPhysign(elt, elemAttri);
         if (elemPhysign != null) {
            elemPhysignList.add(elemPhysign);
         }

         ElemSign elemSign = getElemSign(elt, elemAttri);
         if (elemSign != null) {
            elemSignList.add(elemSign);
         }

         if (elemMacro != null) {
            elemMacroList.add(elemMacro);
         }
      }

   }

   private static ElemAttri getElemAttri(Element elt) {
      String contElemName = elt.attributeValue("Name");
      Long elemId = elt.attribute("elemId") != null && StringUtils.isNotBlank(elt.attributeValue("elemId")) ? Long.parseLong(elt.attributeValue("elemId")) : null;
      String elemName = elt.attribute("elemName") != null && StringUtils.isNotBlank(elt.attributeValue("elemName")) ? elt.attributeValue("elemName") : null;
      String elemCd = elt.attribute("elemCd") != null && StringUtils.isNotBlank(elt.attributeValue("elemCd")) ? elt.attributeValue("elemCd") : null;
      String typeFlag = elt.attribute("typeFlag") != null && StringUtils.isNotBlank(elt.attributeValue("typeFlag")) ? elt.attributeValue("typeFlag") : null;
      String contType = elt.attribute("contType") != null && StringUtils.isNotBlank(elt.attributeValue("contType")) ? elt.attributeValue("contType") : null;
      String tmplContElemName = elt.attribute("tmplContElemName") != null && StringUtils.isNotBlank(elt.attributeValue("tmplContElemName")) ? elt.attributeValue("tmplContElemName") : null;
      String linkdata = elt.attribute("linkData") != null && StringUtils.isNotBlank(elt.attributeValue("linkData")) ? elt.attributeValue("linkData") : null;
      String doCode = elt.attribute("doCode") != null && StringUtils.isNotBlank(elt.attributeValue("doCode")) ? elt.attributeValue("doCode") : null;
      String triggerWay = elt.attribute("triggerWay") != null && StringUtils.isNotBlank(elt.attributeValue("triggerWay")) ? elt.attributeValue("triggerWay") : null;
      JSONObject elemAttrisObj = new JSONObject();
      JSONObject elemAttriObj = new JSONObject();
      JSONObject elemAttriElemObj = new JSONObject();
      JSONArray elemAttriArr = new JSONArray();
      JSONArray elemArr = new JSONArray();
      int attrCount = elt.attributeCount();

      for(int i = 0; i < attrCount; ++i) {
         Attribute attr = elt.attribute(i);
         boolean isBooleanAttr = isBooleanAttribute(attr.getName());
         String attrValue = attr.getValue();
         Object attrValueObj = isBooleanAttr ? Boolean.valueOf(attrValue) : attrValue;
         elemAttriElemObj.put(attr.getName(), attrValueObj);
         elemAttriArr.add(attr.getName());
      }

      elemAttriObj.put("elem", elemAttriElemObj);
      elemAttriObj.put("elemAttrArr", elemAttriArr);
      elemArr.add(elemAttriObj);
      elemAttrisObj.put("elems", elemArr);
      ElemAttri elemAttri = new ElemAttri(contElemName, elemId, elemName, elemCd, typeFlag, contType, elemAttrisObj.toJSONString(), tmplContElemName, linkdata, doCode, triggerWay);
      return elemAttri;
   }

   private static ElemDate getElemDate(Element elt, ElemAttri elemAttri) {
      String type = elt.attribute("Type").getValue();
      ElemDate elemDate = null;
      if (type.equals("7")) {
         String nowDate = elt.attribute("currentTime") != null && StringUtils.isNotBlank(elt.attributeValue("currentTime")) ? elt.attributeValue("currentTime") : null;
         String inOutInter = elt.attribute("inoutHosTimeRange") != null && StringUtils.isNotBlank(elt.attributeValue("inoutHosTimeRange")) ? elt.attributeValue("inoutHosTimeRange") : null;
         String dateFormat = elt.attribute("StyleFormat") != null && StringUtils.isNotBlank(elt.attributeValue("StyleFormat")) ? elt.attributeValue("StyleFormat") : null;
         elemDate = new ElemDate(elemAttri.getContElemName(), elemAttri.getElemId(), elemAttri.getElemName(), elemAttri.getElemCd(), elemAttri.getTypeFlag(), elemAttri.getContType(), nowDate, inOutInter, dateFormat);
      }

      return elemDate;
   }

   private static ElemGender getElemGender(Element elt, ElemAttri elemAttri) {
      ElemGender elemGender = null;
      Attribute attribute = elt.attribute("elemSex");
      String elemValue = attribute != null && StringUtils.isNotBlank(attribute.getValue()) ? attribute.getValue() : null;
      if (elemValue != null && (elemValue.equals("1") || elemValue.equals("2"))) {
         elemGender = new ElemGender(elemAttri.getContElemName(), elemAttri.getElemId(), elemAttri.getElemName(), elemAttri.getElemCd(), elemAttri.getTypeFlag(), elemAttri.getContType(), elemValue);
         elemGender.setElemQua(elemAttri.getElemAttri());
      }

      return elemGender;
   }

   private static ElemMacro getElemMacro(Element elt, ElemAttri elemAttri) {
      ElemMacro elemMacro = null;
      Attribute attribute = elt.attribute("isMacroReplace");
      String isMacroReplace = attribute != null && StringUtils.isNotBlank(attribute.getValue()) ? attribute.getValue() : "";
      if (StringUtils.isNotEmpty(isMacroReplace) && !isMacroReplace.equals("FALSE")) {
         Attribute replMethAttr = elt.attribute("macroReplaceType");
         Attribute sourTableAttr = elt.attribute("sourceTable");
         Attribute sourColuAttr = elt.attribute("sourceColu");
         Attribute remindFlagAttr = elt.attribute("remindFlag");
         String replMeth = replMethAttr != null && StringUtils.isNotBlank(replMethAttr.getValue()) ? replMethAttr.getValue() : null;
         String sourTable = sourTableAttr != null && StringUtils.isNotBlank(sourTableAttr.getValue()) ? sourTableAttr.getValue() : null;
         String sourColu = sourColuAttr != null && StringUtils.isNotBlank(sourColuAttr.getValue()) ? sourColuAttr.getValue() : null;
         String remindFlag = remindFlagAttr != null && StringUtils.isNotBlank(remindFlagAttr.getValue()) ? remindFlagAttr.getValue() : null;
         remindFlag = StringUtils.isNotBlank(remindFlag) && remindFlag.equalsIgnoreCase("TRUE") ? "1" : "0";
         elemMacro = new ElemMacro(elemAttri.getContElemName(), elemAttri.getElemId(), elemAttri.getElemName(), elemAttri.getElemCd(), elemAttri.getTypeFlag(), elemAttri.getContType(), replMeth, sourTable, sourColu);
         elemMacro.setReplType(isMacroReplace);
         elemMacro.setChangeAlertFlag(remindFlag);
      }

      return elemMacro;
   }

   private static ElemPhysign getElemPhysign(Element elt, ElemAttri elemAttri) {
      ElemPhysign elemPhysign = null;
      Long elemId = elemAttri.getElemId();
      if (elemId != null && (elemId.equals(CommonConstants.ELEM.PHYSIGN_TEMPERATURE) || elemId.equals(CommonConstants.ELEM.PHYSIGN_PULSE) || elemId.equals(CommonConstants.ELEM.PHYSIGN_RR) || elemId.equals(CommonConstants.ELEM.PHYSIGN_NBPD) || elemId.equals(CommonConstants.ELEM.PHYSIGN_NBPS) || elemId.equals(CommonConstants.ELEM.PHYSIGN_WEIGHT))) {
         elemPhysign = new ElemPhysign(elemAttri.getContElemName(), elemAttri.getElemId(), elemAttri.getElemName(), elemAttri.getElemCd(), elemAttri.getTypeFlag(), elemAttri.getContType());
      }

      return elemPhysign;
   }

   private static ElemSign getElemSign(Element elt, ElemAttri elemAttri) {
      ElemSign elemSign = null;
      String typeFlag = elemAttri.getContType();
      if (StringUtils.isNotBlank(typeFlag) && (typeFlag.equals("11") || typeFlag.equals("12"))) {
         elemSign = new ElemSign(elemAttri.getContElemName(), elemAttri.getElemId(), elemAttri.getElemName(), elemAttri.getElemCd(), elemAttri.getTypeFlag(), elemAttri.getContType(), typeFlag);
      }

      return elemSign;
   }

   public static Boolean isSaveContType(String elementTagName) {
      switch (elementTagName) {
         case "NewCtrl":
            return true;
         case "Region":
            return true;
         case "Section":
            return true;
         default:
            return false;
      }
   }

   public static boolean isBooleanAttribute(String attributeName) {
      switch (attributeName) {
         case "DeleteProtect":
            return true;
         case "EditProtect":
            return true;
         case "IsCtrlHidden":
            return true;
         case "ReverseEdit":
            return true;
         case "Printable":
            return true;
         case "MustFillContent":
            return true;
         case "ViewSecret":
            return true;
         case "IsNotRecordInXML":
            return true;
         case "IsChecked":
            return true;
         case "ShowValue":
            return true;
         case "TitleVisible":
            return true;
         default:
            return false;
      }
   }

   private static List getElementListFromXmlStr(String xmlStr) {
      List<Element> elementList = new ArrayList(1);
      Document doc = null;

      try {
         doc = DocumentHelper.parseText(xmlStr);
         Element rootElt = doc.getRootElement();
         Element headerElt = rootElt.element("Header");
         if (headerElt != null) {
            getChildrenElt(headerElt, elementList);
         }

         Element contentElt = rootElt.element("DocObjContent");
         if (contentElt != null) {
            getChildrenElt(contentElt, elementList);
         }

         Element footerElt = rootElt.element("Footer");
         if (footerElt != null) {
            getChildrenElt(footerElt, elementList);
         }

         if (headerElt == null && contentElt == null && footerElt == null) {
            getChildrenElt(rootElt, elementList);
         }
      } catch (DocumentException e) {
         e.printStackTrace();
      }

      return elementList;
   }

   public static List getContElemNameListFromXmlStr(String xmlStr, String base64Id) {
      List<Element> elementList = new ArrayList(1);
      Document doc = null;

      try {
         doc = DocumentHelper.parseText(xmlStr);
         Element rootElt = doc.getRootElement();
         new ArrayList();
         List nodeList = rootElt.content();
         if (CollectionUtils.isNotEmpty(nodeList)) {
            for(Node node : nodeList) {
               new ArrayList();
               List footerElt = ((Element)node).content();
               if (CollectionUtils.isNotEmpty(footerElt)) {
                  Iterator iterator = footerElt.iterator();

                  while(iterator.hasNext()) {
                     Node node1 = (Node)iterator.next();
                     Boolean flag = false;
                     if (isSaveContType(node1.getName())) {
                        List<Attribute> attributeList = ((Element)node1).attributes();
                        if (CollectionUtils.isNotEmpty(attributeList)) {
                           for(Attribute attribute : attributeList) {
                              if (base64Id.equals(attribute.getValue())) {
                                 flag = true;
                              }
                           }
                        }
                     }

                     if (!flag) {
                        iterator.remove();
                     }
                  }
               }
            }
         }

         Element headerElt = rootElt.element("Header");
         if (headerElt != null) {
            getChildrenElt(headerElt, elementList);
         }

         Element contentElt = rootElt.element("DocObjContent");
         if (contentElt != null) {
            getChildrenElt(contentElt, elementList);
         }

         Element footerElt = rootElt.element("Footer");
         if (footerElt != null) {
            getChildrenElt(footerElt, elementList);
         }

         if (headerElt == null && contentElt == null && footerElt == null) {
            getChildrenElt(rootElt, elementList);
         }
      } catch (DocumentException e) {
         e.printStackTrace();
      }

      return elementList;
   }

   public static List getLinkDataNameListFromXmlStr(String xmlStr) {
      List<Element> elementList = new ArrayList(1);
      Document doc = null;

      try {
         doc = DocumentHelper.parseText(xmlStr);
         Element rootElt = doc.getRootElement();
         Element headerElt = rootElt.element("Header");
         if (headerElt != null) {
            getChildrenElt(headerElt, elementList);
         }

         Element contentElt = rootElt.element("DocObjContent");
         if (contentElt != null) {
            getChildrenElt(contentElt, elementList);
         }

         Element footerElt = rootElt.element("Footer");
         if (footerElt != null) {
            getChildrenElt(footerElt, elementList);
         }

         if (headerElt == null && contentElt == null && footerElt == null) {
            getChildrenElt(rootElt, elementList);
         }
      } catch (DocumentException e) {
         e.printStackTrace();
      }

      return elementList;
   }

   @Async
   protected static void getChildrenElt(Element elt, List list) {
      Element eltTemp;
      for(Iterator headerIter = elt.elementIterator(); headerIter.hasNext(); getChildrenElt(eltTemp, list)) {
         eltTemp = (Element)headerIter.next();
         if (isSaveContType(eltTemp.getName())) {
            list.add(eltTemp);
         }
      }

   }

   public static List getElemAttriVoListFromXml(String xmlStr, XmlElementParseConfigVo configVo) {
      List<ElemAttriVo> elemAttriVoList = new ArrayList(1);

      for(Element elt : getElementListFromXmlStr(xmlStr)) {
         String isCtrlHidden = elt.attributeValue("IsCtrlHidden");
         if (!StringUtils.isNotBlank(isCtrlHidden) || !isCtrlHidden.toUpperCase().equals("TRUE")) {
            ElemAttriVo elemAttriVo = getElemAttriVo(elt, configVo);
            elemAttriVoList.add(elemAttriVo);
         }
      }

      return elemAttriVoList;
   }

   public static List getElemAttriVoListFromXmlNew(String xmlStr, XmlElementParseConfigVo configVo, String base64Id) {
      List<ElemAttriVo> elemAttriVoList = new ArrayList(1);

      for(Element elt : getContElemNameListFromXmlStr(xmlStr, base64Id)) {
         ElemAttriVo elemAttriVo = getElemAttriVo(elt, configVo);
         elemAttriVoList.add(elemAttriVo);
      }

      return elemAttriVoList;
   }

   public static List getLinkDataListFromXml(String xmlStr, XmlElementParseConfigVo configVo) {
      List<ElemAttriVo> elemAttriVoList = new ArrayList(1);

      for(Element elt : getLinkDataNameListFromXmlStr(xmlStr)) {
         ElemAttriVo elemAttriVo = getElemAttriVo(elt, configVo);
         elemAttriVoList.add(elemAttriVo);
      }

      elemAttriVoList = (List)elemAttriVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getLinkData())).collect(Collectors.toList());
      return elemAttriVoList;
   }

   public static List getElemMacroListFromXml(String xmlStr) {
      List<ElemMacro> elemMacroList = new ArrayList();

      for(Element elt : getElementListFromXmlStr(xmlStr)) {
         ElemAttri elemAttri = getElemAttri(elt);
         ElemMacro elemMacro = getElemMacro(elt, elemAttri);
         elemMacroList.add(elemMacro);
      }

      return elemMacroList;
   }

   private static ElemAttriVo getElemAttriVo(Element elt, XmlElementParseConfigVo configVo) {
      ElemAttri elemAttri = getElemAttri(elt);
      ElemAttriVo elemAttriVo = new ElemAttriVo(elemAttri);
      String elemConText = "";
      Element textElt = getChildrenElt(elt, "Content_Text");
      elemConText = getText(elt, textElt, configVo);
      if (elt.getParent() != null) {
         String arrayCode = elt.attribute("Code") != null && StringUtils.isNotBlank(elt.attributeValue("Code")) ? elt.attributeValue("Code") : "";
         String parentContElemName = elt.getParent().attribute("Id") != null ? elt.getParent().attribute("Id").getValue() : null;
         String parentElemIdStr = elt.getParent().attribute("elemId") != null ? elt.getParent().attribute("elemId").getValue() : null;
         Long parentElemId = StringUtils.isNotEmpty(parentElemIdStr) && StringUtils.isNotBlank(parentElemIdStr) ? Long.valueOf(parentElemIdStr) : null;
         String parentElemName = elt.getParent().attribute("elemName") != null ? elt.getParent().attribute("elemName").getValue() : null;
         String parentElemCd = elt.getParent().attribute("elemCd") != null ? elt.getParent().attribute("elemCd").getValue() : null;
         String linkData = elt.getParent().attribute("linkData") != null ? elt.getParent().attribute("linkData").getValue() : null;
         elemAttriVo.setElemContTextCode(arrayCode);
         elemAttriVo.setElemConText(elemConText);
         elemAttriVo.setParentContElemName(parentContElemName);
         elemAttriVo.setParentElemId(parentElemId);
         elemAttriVo.setParentElemName(parentElemName);
         elemAttriVo.setParentElemCd(parentElemCd);
         elemAttriVo.setLinkContElemName(linkData);
      }

      return elemAttriVo;
   }

   private static String getRegionSectionText(Element elt) {
      StringBuffer str = new StringBuffer("");
      Iterator headerIter = elt.elementIterator();

      while(headerIter.hasNext()) {
         Element eltTemp = (Element)headerIter.next();
         elt = elt.attribute("PlaceHolder") != null ? (eltTemp.attribute("PlaceHolder") != null ? eltTemp : elt) : eltTemp;
         String text = getChildrenEltStr(elt, eltTemp);
         str.append(text != null && !text.equals("null") ? text : "");
      }

      return str.toString();
   }

   private static String getSectionText(Element elt, Element textElt) {
      String elemConText = "";
      elemConText = textElt != null ? textElt.getText() : null;
      String PlaceHolder = elt.attribute("PlaceHolder") != null && StringUtils.isNotBlank(elt.attributeValue("PlaceHolder")) ? elt.attributeValue("PlaceHolder") : null;
      boolean must = elt.attribute("MustFillContent") == null || !StringUtils.isNotBlank(elt.attributeValue("MustFillContent")) || !elt.attributeValue("MustFillContent").equals("false");
      PlaceHolder = must && StringUtils.isNotBlank(PlaceHolder) && StringUtils.isNotBlank(elemConText) ? "*" + PlaceHolder + "*" : PlaceHolder;
      if (StringUtils.isNotBlank(elemConText) && StringUtils.isNotBlank(PlaceHolder) && elemConText.equals(PlaceHolder)) {
         elemConText = null;
      }

      return elemConText;
   }

   private static String getText(Element elt, Element textElt, XmlElementParseConfigVo configVo) {
      String elemConText = "";
      elemConText = textElt != null && StringUtils.isNotEmpty(textElt.getText()) ? textElt.getText() : null;
      String Unit = elt.attribute("Unit") != null && StringUtils.isNotBlank(elt.attributeValue("Unit")) ? elt.attributeValue("Unit") : null;
      if (StringUtils.isNotEmpty(Unit) && StringUtils.isNotEmpty(elemConText)) {
         elemConText = elemConText.replace(Unit, "");
      }

      if (StringUtils.isNotBlank(configVo.getReplaceLineBreaks()) && configVo.getReplaceLineBreaks().equals("1") && StringUtils.isNotBlank(elemConText)) {
         String lineBreaksElemIds = configVo.getLineBreaksElemIds();
         List<String> lineBreaksElemIdList = (List<String>)(StringUtils.isNotBlank(lineBreaksElemIds) ? Arrays.asList(lineBreaksElemIds.split(",")) : new ArrayList(1));
         String lineBreaksPosition = configVo.getLineBreaksPosition();
         String currElemId = elt.attribute("elemId") != null && StringUtils.isNotBlank(elt.attributeValue("elemId")) ? elt.attributeValue("elemId") : null;
         if (StringUtils.isNotBlank(currElemId) && lineBreaksElemIdList.contains(currElemId)) {
            switch (lineBreaksPosition) {
               case "begin":
                  if (elemConText.substring(0, 1).equals("\n")) {
                     elemConText = elemConText.substring(1);
                  }
                  break;
               case "end":
                  if (elemConText.substring(elemConText.length() - 1).equals("\n")) {
                     elemConText = elemConText.substring(0, elemConText.length() - 1);
                  }
                  break;
               case "all":
                  elemConText = elemConText.replaceAll("\n", "");
            }
         }
      }

      return elemConText;
   }

   private static String getChildrenEltStr(Element newCtrl, Element elt) {
      String str = "";

      String elemConText;
      for(Iterator headerIter = elt.elementIterator(); headerIter.hasNext(); str = str + elemConText) {
         Element eltTemp = (Element)headerIter.next();
         newCtrl = newCtrl.attribute("PlaceHolder") != null ? newCtrl : eltTemp;
         elemConText = "";
         if (eltTemp.getName().equals("Content_Text")) {
            elemConText = getSectionText(newCtrl, eltTemp);
         } else {
            elemConText = getChildrenEltStr(newCtrl, eltTemp);
         }
      }

      return str;
   }

   private static Element getChildrenElt(Element elt, String tagName) {
      Element element = null;

      Element eltTemp;
      for(Iterator headerIter = elt.elementIterator(); headerIter.hasNext(); element = getChildrenElt(eltTemp, tagName)) {
         eltTemp = (Element)headerIter.next();
         if (eltTemp.getName().equals(tagName)) {
            return eltTemp;
         }
      }

      return element;
   }

   public static List getHeaderElementListFromXmlStr(String xmlStr) {
      List<ElemAttri> list = new ArrayList(1);
      Document doc = null;

      try {
         List<Element> elementList = new ArrayList(1);
         doc = DocumentHelper.parseText(xmlStr);
         Element rootElt = doc.getRootElement();
         getElementListFromRootElt(rootElt, "Header", elementList);

         for(Element element : elementList) {
            ElemAttri elemAttri = getElemAttri(element);
            ElemMacro elemMacro = getElemMacro(element, elemAttri);
            if (elemMacro != null) {
               list.add(elemAttri);
            }
         }
      } catch (DocumentException e) {
         e.printStackTrace();
      }

      return list;
   }

   private static void getElementListFromRootElt(Element rootElt, String elemName, List elementList) {
      Element footerElt = rootElt.element(elemName);
      if (footerElt != null) {
         getChildrenElt(footerElt, elementList);
      }

   }
}
