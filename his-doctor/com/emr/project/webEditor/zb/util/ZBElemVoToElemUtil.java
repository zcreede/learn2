package com.emr.project.webEditor.zb.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.StringUtils;
import com.emr.project.system.domain.vo.SetViewVo;
import com.emr.project.system.domain.vo.SysStaElemVo;
import com.emr.project.webEditor.zb.vo.BaseQuality;
import com.emr.project.webEditor.zb.vo.CheckQuality;
import com.emr.project.webEditor.zb.vo.DateQuality;
import com.emr.project.webEditor.zb.vo.DropDownQuality;
import com.emr.project.webEditor.zb.vo.NumberQuality;
import com.emr.project.webEditor.zb.vo.RegionQuality;
import com.emr.project.webEditor.zb.vo.TextQuality;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ZBElemVoToElemUtil {
   private static String type = "";
   private static String typeName = "";

   public static String getSysStaElemQuaJson(SysStaElemVo sysStaElemVo, List dropDownDataList) {
      String quaJson = "";
      if ("3".equals(sysStaElemVo.getTypeFlag())) {
         type = "100";
         typeName = "REGION";
         quaJson = getRegionQuality(sysStaElemVo);
      } else {
         switch (sysStaElemVo.getContType()) {
            case "1":
               type = "1";
               typeName = "TextBox";
               quaJson = getTextElemQuality(sysStaElemVo);
               break;
            case "2":
               type = "2";
               typeName = "NumberBox";
               quaJson = getNumberElemQuality(sysStaElemVo);
               break;
            case "3":
               type = "3";
               typeName = "ListBox";
               quaJson = getDropDownElemQuality(sysStaElemVo, dropDownDataList);
               break;
            case "4":
               type = "4";
               typeName = "MultiListBox";
               quaJson = getDropDownElemQuality(sysStaElemVo, dropDownDataList);
               break;
            case "5":
               type = "5";
               typeName = "Combox";
               quaJson = getDropDownElemQuality(sysStaElemVo, dropDownDataList);
               break;
            case "6":
               type = "6";
               typeName = "MultiCombox";
               quaJson = getDropDownElemQuality(sysStaElemVo, dropDownDataList);
               break;
            case "9":
            case "10":
               quaJson = getDropDownElemQuality(sysStaElemVo, dropDownDataList);
               break;
            case "7":
               type = "7";
               typeName = "DateTimeBox";
               quaJson = getDateElemQuality(sysStaElemVo);
               break;
            case "8":
               type = "8";
               typeName = "CheckBox";
               quaJson = getCheckElemQuality(sysStaElemVo);
               break;
            case "11":
            case "12":
               type = "0";
               typeName = "SECTION";
               quaJson = getSignQuality(sysStaElemVo);
               break;
            default:
               quaJson = getBaseElemQuality(sysStaElemVo);
         }
      }

      return quaJson;
   }

   public static String getQualityJsonString(SysStaElemVo sysStaElemVo, List objectList) {
      Map<String, Object> elemsMap = new HashMap();
      List<String> elemList = new ArrayList();
      List<String> keyList = new ArrayList();

      for(Object obj : objectList) {
         BeanUtils.copyProperties(sysStaElemVo, obj);
         String textJson = JSON.toJSONString(obj, (ValueFilter)(object, name, value) -> value == null ? "" : value, new SerializerFeature[0]);
         HashMap map = (HashMap)JSON.parseObject(textJson, HashMap.class);

         for(Object key : map.keySet()) {
            keyList.add(key.toString());
         }

         String keyJson = JSONArray.toJSONString(keyList);
         String elemJson = "{\"elem\":" + textJson + ",\"" + "elemAttrArr" + "\":" + keyJson + "}";
         elemList.add(elemJson);
      }

      elemsMap.put("elems", elemList);
      String elemsJson = JSON.toJSONString(elemsMap);
      return elemsJson;
   }

   public static String getTextElemQuality(SysStaElemVo sysStaElemVo) {
      TextQuality textQuality = new TextQuality();
      List<TextQuality> textQualityList = new ArrayList();
      textQuality.setType(type);
      textQuality.setTypeName(typeName);
      textQualityList.add(textQuality);
      String jsonString = getQualityJsonString(sysStaElemVo, textQualityList);
      return jsonString;
   }

   public static String getNumberElemQuality(SysStaElemVo sysStaElemVo) {
      NumberQuality numberQuality = new NumberQuality();
      List<NumberQuality> numberQualityList = new ArrayList();
      numberQuality.setType(type);
      numberQuality.setTypeName(typeName);
      numberQualityList.add(numberQuality);
      sysStaElemVo.setWindowName((String)null);
      String jsonString = getQualityJsonString(sysStaElemVo, numberQualityList);
      return jsonString;
   }

   public static String getDateElemQuality(SysStaElemVo sysStaElemVo) {
      DateQuality dateQuality = new DateQuality();
      List<DateQuality> dateQualityList = new ArrayList();
      if ("TRUE".equals(sysStaElemVo.getInoutHosTimeRange())) {
         dateQuality.setStartDateTime("inhosTime");
         dateQuality.setEndDateTime("outTime");
      }

      dateQuality.setType(type);
      dateQuality.setTypeName(typeName);
      dateQualityList.add(dateQuality);
      sysStaElemVo.setWindowName((String)null);
      String jsonString = getQualityJsonString(sysStaElemVo, dateQualityList);
      return jsonString;
   }

   public static String getBaseElemQuality(SysStaElemVo sysStaElemVo) {
      BaseQuality baseQuality = new BaseQuality();
      List<BaseQuality> baseQualityList = new ArrayList();
      if ("2".equals(sysStaElemVo.getTypeFlag())) {
         type = "0";
         typeName = "SECTION";
      }

      if ("2".equals(sysStaElemVo.getTypeFlag()) || "A".equals(sysStaElemVo.getTypeFlag())) {
         sysStaElemVo.setMustFillContent((Boolean)null);
         sysStaElemVo.setViewSecret((Boolean)null);
         sysStaElemVo.setPrintable(CommonConstants.BOOL_TRUE);
         sysStaElemVo.setIsNullPrint((String)null);
         sysStaElemVo.setBackgroundColor((String)null);
      }

      if ("A".equals(sysStaElemVo.getTypeFlag())) {
         sysStaElemVo.setPlaceHolder("");
         sysStaElemVo.setHelpTip("");
         sysStaElemVo.setIsCtrlHidden((Boolean)null);
         sysStaElemVo.setEditProtect((Boolean)null);
         sysStaElemVo.setDeleteProtect((Boolean)null);
         sysStaElemVo.setReverseEdit((Boolean)null);
         sysStaElemVo.setWindowName((String)null);
         if (StringUtils.isNotEmpty(sysStaElemVo.getFormulaRemark())) {
            Map<String, String> map = (Map)JSON.parse(sysStaElemVo.getFormulaRemark());
            type = (String)map.get("type");
            typeName = "";
            sysStaElemVo.setMedicalformulaCon((String)map.get("medicalformulaCon"));
         }
      }

      baseQuality.setType(type);
      baseQuality.setTypeName(typeName);
      baseQualityList.add(baseQuality);
      String jsonString = getQualityJsonString(sysStaElemVo, baseQualityList);
      return jsonString;
   }

   public static String getDropDownElemQuality(SysStaElemVo sysStaElemVo, List dropDownDataList) {
      DropDownQuality dropDownQuality = new DropDownQuality();
      List<DropDownQuality> dropDownQualityList = new ArrayList();
      if ("4".equals(sysStaElemVo.getContType()) || "6".equals(sysStaElemVo.getContType())) {
         dropDownQuality.setMutexString(sysStaElemVo.getListDataArrStr());
      }

      List<String> CodeArray = new ArrayList();
      List<String> ValueArray = new ArrayList();
      if ("2".equals(sysStaElemVo.getListDataSource()) && sysStaElemVo.getSetName() != null && dropDownDataList != null) {
         for(SetViewVo data : dropDownDataList) {
            ValueArray.add(data.getCode());
            CodeArray.add(data.getName());
         }
      }

      if ("1".equals(sysStaElemVo.getListDataSource()) && StringUtils.isNotEmpty(sysStaElemVo.getListDataArrStr())) {
         String[] groups = sysStaElemVo.getListDataArrStr().split("\\|");

         for(int i = 0; i < groups.length; ++i) {
            String[] values = groups[i].split("#");

            for(int j = 0; j < values.length; ++j) {
               ValueArray.add(values[j]);
               CodeArray.add(values[j]);
            }
         }
      }

      dropDownQuality.setCodeArray(CodeArray);
      dropDownQuality.setValueArray(ValueArray);
      dropDownQuality.setType(type);
      dropDownQuality.setTypeName(typeName);
      dropDownQualityList.add(dropDownQuality);
      sysStaElemVo.setWindowName((String)null);
      String jsonString = getQualityJsonString(sysStaElemVo, dropDownQualityList);
      return jsonString;
   }

   public static String getCheckElemQuality(SysStaElemVo sysStaElemVo) {
      CheckQuality checkQuality = new CheckQuality();
      List<CheckQuality> checkQualityList = new ArrayList();
      checkQuality.setType(type);
      checkQuality.setTypeName(typeName);
      checkQualityList.add(checkQuality);
      sysStaElemVo.setWindowName((String)null);
      String jsonString = getQualityJsonString(sysStaElemVo, checkQualityList);
      return jsonString;
   }

   public static String getRegionQuality(SysStaElemVo sysStaElemVo) {
      RegionQuality regionQuality = new RegionQuality();
      List<RegionQuality> regionQualityList = new ArrayList();
      sysStaElemVo.setPlaceHolder("");
      sysStaElemVo.setHelpTip("");
      sysStaElemVo.setMustFillContent((Boolean)null);
      sysStaElemVo.setViewSecret((Boolean)null);
      sysStaElemVo.setPrintable(CommonConstants.BOOL_TRUE);
      sysStaElemVo.setIsNullPrint((String)null);
      sysStaElemVo.setBackgroundColor((String)null);
      regionQuality.setType(type);
      regionQuality.setTypeName(typeName);
      regionQualityList.add(regionQuality);
      String jsonString = getQualityJsonString(sysStaElemVo, regionQualityList);
      return jsonString;
   }

   public static String getSignQuality(SysStaElemVo sysStaElemVo) {
      BaseQuality baseQuality = new BaseQuality();
      List<BaseQuality> baseQualityList = new ArrayList();
      sysStaElemVo.setHelpTip(sysStaElemVo.getElemName());
      sysStaElemVo.setPlaceHolder(sysStaElemVo.getElemName());
      sysStaElemVo.setTag("");
      sysStaElemVo.setIsCtrlHidden(CommonConstants.BOOL_FALSE);
      sysStaElemVo.setReverseEdit(CommonConstants.BOOL_FALSE);
      sysStaElemVo.setMustFillContent((Boolean)null);
      sysStaElemVo.setIsNullPrint((String)null);
      sysStaElemVo.setViewSecret((Boolean)null);
      sysStaElemVo.setWindowName((String)null);
      sysStaElemVo.setBackgroundColor("");
      baseQuality.setType(type);
      baseQuality.setTypeName(typeName);
      baseQualityList.add(baseQuality);
      String jsonString = getQualityJsonString(sysStaElemVo, baseQualityList);
      return jsonString;
   }
}
