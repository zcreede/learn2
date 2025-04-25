package com.emr.project.mrhp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.ObjectUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.emr.service.IGenerateRulrFileService;
import com.emr.project.mrhp.domain.MrHpCheckSet;
import com.emr.project.mrhp.domain.vo.CheckExpressionVo;
import com.emr.project.mrhp.domain.vo.CheckFeeVo;
import com.emr.project.mrhp.domain.vo.MrHpCheckSetVo;
import com.emr.project.mrhp.mapper.MrHpCheckSetMapper;
import com.emr.project.mrhp.service.IMrHpCheckSetService;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.service.IQcCheckElemService;
import com.emr.project.system.domain.SysCustomSet;
import com.emr.project.system.domain.SysCustomUnit;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysCustomSetService;
import com.emr.project.system.service.ISysCustomUnitService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysDictTypeService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MrHpCheckSetServiceImpl implements IMrHpCheckSetService {
   @Autowired
   private MrHpCheckSetMapper mrHpCheckSetMapper;
   @Autowired
   private ISysCustomUnitService customUnitService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISysCustomSetService sysCustomSetService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private IQcCheckElemService qcCheckElemService;
   @Value("${spring.drools.path}")
   private String path;
   @Autowired
   private ISysDictTypeService sysDictTypeService;

   public MrHpCheckSetVo selectMrHpCheckSetById(String id) throws Exception {
      MrHpCheckSetVo mrHpCheckSet = this.mrHpCheckSetMapper.selectMrHpCheckSetById(id);
      if (mrHpCheckSet != null) {
         if (StringUtils.isNotEmpty(mrHpCheckSet.getCheckExpression1())) {
            mrHpCheckSet.setCheckExpression1List(this.getCheckExpressionList(mrHpCheckSet.getCheckExpression1()));
         }

         if (StringUtils.isNotEmpty(mrHpCheckSet.getCheckExpression2())) {
            mrHpCheckSet.setCheckExpression2List(this.getCheckExpressionList(mrHpCheckSet.getCheckExpression2()));
         }

         if ("0201".equals(mrHpCheckSet.getCheckTypeNo()) || "02".equals(mrHpCheckSet.getCheckClassNo()) || "0302".equals(mrHpCheckSet.getCheckTypeNo()) || "0303".equals(mrHpCheckSet.getCheckTypeNo()) || "0101".equals(mrHpCheckSet.getCheckTypeNo())) {
            JSONArray jsonArray = JSONArray.parseArray(mrHpCheckSet.getCheckColumn1());
            String str = "";

            for(int i = 0; i < jsonArray.size(); ++i) {
               JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
               str = str + jsonObject.get("fieldCd") + "|";
            }

            mrHpCheckSet.setCheckColumn1(str.substring(0, str.length() - 1));
         }

         if (("0102".equals(mrHpCheckSet.getCheckTypeNo()) || "0302".equals(mrHpCheckSet.getCheckTypeNo())) && StringUtils.isNotEmpty(mrHpCheckSet.getCheckColumn2())) {
            JSONArray jsonArray = JSONArray.parseArray(mrHpCheckSet.getCheckColumn2());
            String str = "";

            for(int i = 0; i < jsonArray.size(); ++i) {
               JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
               str = str + jsonObject.get("fieldCd") + "|";
            }

            mrHpCheckSet.setCheckColumn2(str.substring(0, str.length() - 1));
         }
      }

      return mrHpCheckSet;
   }

   public List getCheckExpressionList(String jsonStr) {
      List<CheckExpressionVo> resultList = new ArrayList();

      try {
         JSONObject jsonObject = JSONObject.parseObject(jsonStr);
         JSONArray jsonArray = jsonObject.getJSONArray("checkExpressionVoList");
         resultList = jsonArray.toJavaList(CheckExpressionVo.class);
      } catch (Exception var5) {
      }

      return resultList;
   }

   public List selectMrHpCheckSetVoList(MrHpCheckSetVo mrHpCheckSetVo) throws Exception {
      return this.mrHpCheckSetMapper.selectMrHpCheckSetVoList(mrHpCheckSetVo);
   }

   public void insertMrHpCheckSet(MrHpCheckSetVo mrHpCheckSetVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Long id = SnowIdUtils.uniqueLong();
      mrHpCheckSetVo.setId(id);
      mrHpCheckSetVo.setCheckNo(id.toString());
      mrHpCheckSetVo.setOrgCd(sysUser.getHospital().getOrgCode());
      mrHpCheckSetVo.setCrePerCode(sysUser.getUserName());
      mrHpCheckSetVo.setCrePerName(sysUser.getNickName());
      this.insertAndUpdate(mrHpCheckSetVo);
      this.mrHpCheckSetMapper.insertMrHpCheckSet(mrHpCheckSetVo);
   }

   public void insertAndUpdate(MrHpCheckSetVo mrHpCheckSetVo) throws Exception {
      if (mrHpCheckSetVo.getCheckClassNo().equals("02")) {
         JSONArray arr = new JSONArray();
         arr.add(this.getColumnJsonObj(mrHpCheckSetVo.getCheckTable1(), mrHpCheckSetVo.getCheckColumn1()));
         this.getColumnJsonObj(mrHpCheckSetVo.getCheckTable1(), mrHpCheckSetVo.getCheckColumn1());
         mrHpCheckSetVo.setCheckColumn1(arr.toJSONString());
      }

      switch (mrHpCheckSetVo.getCheckTypeNo()) {
         case "0302":
            JSONArray arr03021 = new JSONArray();
            arr03021.add(this.getColumnJsonObj(mrHpCheckSetVo.getCheckTable1(), mrHpCheckSetVo.getCheckColumn1()));
            JSONArray arr03022 = new JSONArray();
            arr03022.add(this.getColumnJsonObj(mrHpCheckSetVo.getCheckTable2(), mrHpCheckSetVo.getCheckColumn2()));
            mrHpCheckSetVo.setCheckColumn1(arr03021.toJSONString());
            mrHpCheckSetVo.setCheckColumn2(arr03022.toJSONString());
            break;
         case "0101":
            String[] str = mrHpCheckSetVo.getCheckColumn1().split("\\|");
            JSONArray arr0101 = new JSONArray();

            for(int i = 0; i < str.length; ++i) {
               JSONObject obj = this.getColumnJsonObj(mrHpCheckSetVo.getCheckTable1(), str[i]);
               if (obj != null) {
                  arr0101.add(obj);
               }
            }

            mrHpCheckSetVo.setCheckColumn1(arr0101.toJSONString());
            break;
         case "0102":
            JSONArray arr0102 = new JSONArray();
            arr0102.add(this.getColumnJsonObj(mrHpCheckSetVo.getCheckTable2(), mrHpCheckSetVo.getCheckColumn2()));
            mrHpCheckSetVo.setCheckColumn2(arr0102.toJSONString());
            break;
         case "0303":
            JSONObject obj0303 = new JSONObject();
            String label = this.sysDictDataService.selectDictLabel("s050", mrHpCheckSetVo.getCheckColumn1());
            obj0303.put("fieldCd", mrHpCheckSetVo.getCheckColumn1());
            obj0303.put("fieldName", label);
            obj0303.put("fieldProp", mrHpCheckSetVo.getCheckColumn1());
            JSONArray arr0303 = new JSONArray();
            arr0303.add(obj0303);
            mrHpCheckSetVo.setCheckColumn1(arr0303.toJSONString());
      }

      checkFileNameOld = mrHpCheckSetVo.getCheckFileName();
      if (StringUtils.isNotBlank(checkFileNameOld)) {
         File checkFileOld = new File(this.path + "/" + checkFileNameOld);
         if (checkFileOld.exists()) {
            checkFileOld.delete();
         }
      }

      String checkFileName = this.genRuleFile(mrHpCheckSetVo);
      mrHpCheckSetVo.setCheckFileName(checkFileName);
      List<QcCheckElem> qcCheckElemList = mrHpCheckSetVo.getQcCheckElemList();
      this.qcCheckElemService.deleteQcCheckElemById(mrHpCheckSetVo.getId());
      if (qcCheckElemList != null && !qcCheckElemList.isEmpty()) {
         qcCheckElemList = (List)qcCheckElemList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet(Comparator.comparing(QcCheckElem::getElemId))), ArrayList::new));
         this.qcCheckElemService.insertQcCheckElem(qcCheckElemList);
      }

   }

   public String getColumnJsonStr(String tableName, String fieldCd) {
      String json = "";
      if (StringUtils.isNotEmpty(fieldCd) && StringUtils.isNotEmpty(tableName)) {
         JSONObject obj = new JSONObject();
         List<SysCustomSet> list = this.sysCustomSetService.selectSysCustomSetListBySetName(tableName);
         if (list != null && list.size() > 0) {
            SysCustomUnit sysCustomUnit = this.customUnitService.selectSysCustomUnitBySetIdAndFieldCd(((SysCustomSet)list.get(0)).getId(), fieldCd);
            obj.put("fieldCd", sysCustomUnit.getFieldCd());
            obj.put("fieldName", sysCustomUnit.getFieldName());
            obj.put("fieldProp", StringUtils.toCamelCase(sysCustomUnit.getFieldCd()));
         }

         if (obj != null && obj.size() > 0) {
            json = obj.toJSONString();
         }
      }

      return json;
   }

   public JSONObject getColumnJsonObj(String tableName, String fieldCd) {
      JSONObject obj = null;
      if (StringUtils.isNotEmpty(fieldCd) && StringUtils.isNotEmpty(tableName)) {
         obj = new JSONObject();
         List<SysCustomSet> list = this.sysCustomSetService.selectSysCustomSetListBySetName(tableName);
         if (list != null && list.size() > 0) {
            SysCustomUnit sysCustomUnit = this.customUnitService.selectSysCustomUnitBySetIdAndFieldCd(((SysCustomSet)list.get(0)).getId(), fieldCd);
            obj.put("fieldCd", sysCustomUnit.getFieldCd());
            obj.put("fieldName", sysCustomUnit.getFieldName());
            obj.put("fieldProp", StringUtils.toCamelCase(sysCustomUnit.getFieldCd()));
         }
      }

      return obj;
   }

   public void updateMrHpCheckSet(MrHpCheckSetVo mrHpCheckSetVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      mrHpCheckSetVo.setAltPerCode(sysUser.getUserName());
      mrHpCheckSetVo.setAltPerName(sysUser.getNickName());
      this.insertAndUpdate(mrHpCheckSetVo);
      this.mrHpCheckSetMapper.updateMrHpCheckSet(mrHpCheckSetVo);
   }

   public int deleteMrHpCheckSetByIds(String[] ids) {
      return this.mrHpCheckSetMapper.deleteMrHpCheckSetByIds(ids);
   }

   public int deleteMrHpCheckSetById(String id) {
      return this.mrHpCheckSetMapper.deleteMrHpCheckSetById(id);
   }

   private String getPreObjStr(String tableName) {
      String preObjStr = "";
      switch (tableName.toLowerCase(Locale.ROOT)) {
         case "mr_hp_attach":
            preObjStr = "MrHpAttachVo.";
         default:
            return preObjStr;
      }
   }

   private String getDiaOpeObjStr(String tableName) {
      String preObjStr = "";
      switch (tableName.toLowerCase(Locale.ROOT)) {
         case "mr_hp_dia":
            preObjStr = "dia.";
            break;
         case "mr_hp_ope":
            preObjStr = "ope.";
      }

      return preObjStr;
   }

   private String getCheckExpressionString(List checkExpressionVoList, String tableName) throws Exception {
      String preObj = StringUtils.isNotBlank(tableName) ? this.getPreObjStr(tableName) : "";
      String diaOpeObjStr = StringUtils.isNotBlank(tableName) ? this.getDiaOpeObjStr(tableName) : "";
      JSONObject checkExpressionObj = new JSONObject();
      String checkExpression = "";
      String codeCheckExpression = "";
      String objCheckExpression = "";
      JSONArray checkExpressionVoArray = new JSONArray();
      if (checkExpressionVoList != null && !checkExpressionVoList.isEmpty()) {
         for(CheckExpressionVo checkExpressionVo : checkExpressionVoList) {
            String expression = "";
            checkExpression = checkExpression + (checkExpressionVo.getPreBrackets().equals("1") ? "(" : "");
            checkExpression = checkExpression + checkExpressionVo.getFieldName() + checkExpressionVo.getOperator();
            if (!checkExpressionVo.getOperator().equals("不为空") && !checkExpressionVo.getOperator().equals("为空")) {
               if (!checkExpressionVo.getDataType().equals("varchar") && !checkExpressionVo.getDataType().equals("varchar2") && !checkExpressionVo.getDataType().equals("nvarchar") && !checkExpressionVo.getDataType().equals("char")) {
                  checkExpression = checkExpression + checkExpressionVo.getFieldDictDataValue();
               } else {
                  checkExpression = checkExpression + "\"" + checkExpressionVo.getFieldDictDataValue() + "\"";
               }
            }

            checkExpression = checkExpression + (checkExpressionVo.getAfterBrackets().equals("1") ? ")" : "");
            checkExpression = checkExpression + checkExpressionVo.getLogicOperator();
            codeCheckExpression = codeCheckExpression + (checkExpressionVo.getPreBrackets().equals("1") ? "(" : "");
            String codeOperator = checkExpressionVo.getCodeOperator();
            if (!codeOperator.contains("@column") && !codeOperator.contains("@value")) {
               codeCheckExpression = codeCheckExpression + preObj + checkExpressionVo.getFieldProp() + codeOperator;
               expression = expression + preObj + checkExpressionVo.getFieldProp() + codeOperator;
               if (!checkExpressionVo.getDataType().equals("varchar") && !checkExpressionVo.getDataType().equals("varchar2") && !checkExpressionVo.getDataType().equals("nvarchar") && !checkExpressionVo.getDataType().equals("char")) {
                  codeCheckExpression = codeCheckExpression + checkExpressionVo.getFieldDictDataValue();
                  expression = expression + checkExpressionVo.getFieldDictDataValue();
               } else {
                  codeCheckExpression = codeCheckExpression + "\"" + checkExpressionVo.getFieldDictDataValue() + "\"";
                  expression = expression + "\"" + checkExpressionVo.getFieldDictDataValue() + "\"";
               }
            } else {
               codeCheckExpression = codeCheckExpression + codeOperator.replaceAll("@column", preObj + checkExpressionVo.getFieldProp()).replaceAll("@value", "\"" + checkExpressionVo.getFieldDictDataValue() + "\"");
               expression = expression + codeOperator.replaceAll("@column", preObj + checkExpressionVo.getFieldProp()).replaceAll("@value", "\"" + checkExpressionVo.getFieldDictDataValue() + "\"");
            }

            codeCheckExpression = codeCheckExpression + (checkExpressionVo.getAfterBrackets().equals("1") ? ")" : "");
            if (StringUtils.isNotEmpty(checkExpressionVo.getLogicOperator())) {
               codeCheckExpression = codeCheckExpression + checkExpressionVo.getLogicOperator();
            }

            checkExpressionVo.setCheckExpression(expression);
            JSONObject checkExpressionVoObj = (JSONObject)JSONObject.toJSON(checkExpressionVo);
            checkExpressionVoArray.add(checkExpressionVoObj);
            if (StringUtils.isNotBlank(diaOpeObjStr)) {
               String filedProp = checkExpressionVo.getFieldProp();
               String fieldPropGet = diaOpeObjStr + "get" + filedProp.substring(0, 1).toUpperCase() + filedProp.substring(1) + "()";
               if (!codeOperator.contains("@column") && !codeOperator.contains("@value")) {
                  objCheckExpression = objCheckExpression + fieldPropGet + checkExpressionVo.getOperator();
                  if (!checkExpressionVo.getDataType().equals("varchar") && !checkExpressionVo.getDataType().equals("varchar2") && !checkExpressionVo.getDataType().equals("nvarchar") && !checkExpressionVo.getDataType().equals("char")) {
                     objCheckExpression = objCheckExpression + checkExpressionVo.getFieldDictDataValue();
                  } else {
                     objCheckExpression = objCheckExpression + "\"" + checkExpressionVo.getFieldDictDataValue() + "\"";
                  }
               } else {
                  objCheckExpression = objCheckExpression + codeOperator.replaceAll("@column", fieldPropGet).replace("@value", "\"" + checkExpressionVo.getFieldDictDataValue() + "\"");
               }
            }
         }
      }

      checkExpressionObj.put("checkExpression", checkExpression);
      checkExpressionObj.put("checkExpressionVoList", checkExpressionVoArray);
      checkExpressionObj.put("codeCheckExpression", codeCheckExpression);
      checkExpressionObj.put("objCheckExpression", objCheckExpression);
      return checkExpressionObj.toJSONString();
   }

   private String getCheckExpressionFeeString(List checkFeeVoList) throws Exception {
      JSONObject jsonObject = new JSONObject();
      String checkExpression = "";
      String codeCheckExpression = "";
      JSONArray checkFeeVoArray = new JSONArray();
      Double offset = null;
      if (checkFeeVoList != null && !checkFeeVoList.isEmpty()) {
         for(CheckFeeVo checkFeeVo : checkFeeVoList) {
            if (checkFeeVo.getIsOffset().equals("1")) {
               offset = checkFeeVo.getOffsetValue();
            }

            checkExpression = checkExpression + checkFeeVo.getFeeTypeName() + "|";
            codeCheckExpression = codeCheckExpression + checkFeeVo.getFeeTypeCode() + "|";
            JSONObject temp = ObjectUtils.objectToJSONObject(checkFeeVo);
            checkFeeVoArray.add(temp);
         }

         checkExpression = checkExpression.substring(0, checkExpression.length() - 1);
         codeCheckExpression = codeCheckExpression.substring(0, codeCheckExpression.length() - 1);
      }

      jsonObject.put("checkExpression", checkExpression);
      jsonObject.put("checkExpressionVoList", checkFeeVoArray);
      jsonObject.put("codeCheckExpression", codeCheckExpression);
      if (offset != null) {
         jsonObject.put("offsetCheckExpression", offset);
      }

      return jsonObject.toJSONString();
   }

   public List selectTableField(MrHpCheckSetVo mrHpCheckSetVo) throws Exception {
      Long setId = mrHpCheckSetVo.getSetId();
      List<CheckExpressionVo> resultList = new ArrayList();
      SysCustomUnit unit = new SysCustomUnit();
      unit.setSetId(setId);
      unit.setFieldName(mrHpCheckSetVo.getFieldName());
      List<SysCustomUnit> unitList = this.customUnitService.selectSysCustomUnitList(unit);
      String operator = this.sysEmrConfigService.selectSysEmrConfigByKey("0015");
      Map<String, Object> map = (Map)JSON.parse(operator);

      for(SysCustomUnit sysCustomUnit : unitList) {
         CheckExpressionVo checkExpressionVo = new CheckExpressionVo();
         checkExpressionVo.setId(sysCustomUnit.getId());
         checkExpressionVo.setFieldCd(sysCustomUnit.getFieldCd());
         checkExpressionVo.setFieldName(sysCustomUnit.getFieldName());
         checkExpressionVo.setFieldProp(StringUtils.toCamelCase(sysCustomUnit.getFieldCd()));
         checkExpressionVo.setDataType(sysCustomUnit.getDataType());
         Object obj = map.get(sysCustomUnit.getDataType());
         Map<String, Object> mapObj = new HashMap();
         if (obj != null) {
            mapObj = (Map)JSON.parse(obj.toString());
         }

         if (mapObj != null && mapObj.size() > 0) {
            checkExpressionVo.setCodeOperatorList(JSONArray.parseArray(mapObj.get("codeOperatorList").toString()));
            checkExpressionVo.setOperatorList(JSONArray.parseArray(mapObj.get("operatorList").toString()));
         } else {
            checkExpressionVo.setCodeOperatorList(new JSONArray());
            checkExpressionVo.setOperatorList(new JSONArray());
         }

         Map<String, String> setMap = new HashMap();
         if (StringUtils.isNotEmpty(sysCustomUnit.getRemark())) {
            setMap = (Map)JSON.parse(sysCustomUnit.getRemark());
         }

         if (setMap != null && setMap.size() > 0) {
            checkExpressionVo.setFieldTable((String)setMap.get("fieldTable"));
            checkExpressionVo.setFieldColumn((String)setMap.get("fieldColumn"));
            checkExpressionVo.setFieldDictType((String)setMap.get("fieldDictType"));
            checkExpressionVo.setFieldDictDataProp((String)setMap.get("fieldDictDataProp"));
         } else {
            checkExpressionVo.setFieldTable("");
            checkExpressionVo.setFieldColumn("");
            checkExpressionVo.setFieldDictType("");
            checkExpressionVo.setFieldDictDataProp("");
         }

         resultList.add(checkExpressionVo);
      }

      return resultList;
   }

   public String createExpressionJson(MrHpCheckSetVo mrHpCheckSetVo) throws Exception {
      String json = "";
      if (mrHpCheckSetVo.getCheckTypeNo().equals("0303")) {
         json = this.getCheckExpressionFeeString(mrHpCheckSetVo.getCheckFeeVoList());
      } else {
         json = this.getCheckExpressionString(mrHpCheckSetVo.getCheckExpression1List(), mrHpCheckSetVo.getTableName());
      }

      return json;
   }

   private String genRuleFile(MrHpCheckSetVo mrHpCheckSetVo) throws Exception {
      String serviceName = "generateRulrFileService_" + mrHpCheckSetVo.getCheckClassNo() + "_" + mrHpCheckSetVo.getCheckTypeNo();
      RuleVo ruleVo = new RuleVo();
      ruleVo.setCheckId(mrHpCheckSetVo.getId());
      ruleVo.setRuleName(mrHpCheckSetVo.getCheckName());
      ruleVo.setErrorMsg(mrHpCheckSetVo.getCheckTips());
      switch (mrHpCheckSetVo.getCheckClassNo()) {
         case "01":
            this.getRuleVo01(mrHpCheckSetVo, ruleVo);
            break;
         case "02":
            this.getRuleVo02(mrHpCheckSetVo, ruleVo);
            break;
         case "03":
            this.getRuleVo03(mrHpCheckSetVo, ruleVo);
            break;
         case "04":
            this.getRuleVo04(mrHpCheckSetVo, ruleVo);
            break;
         case "05":
            this.getRuleVo05(mrHpCheckSetVo, ruleVo);
      }

      IGenerateRulrFileService generateRulrFileService = (IGenerateRulrFileService)SpringUtils.getBean(serviceName);
      String ruleFileName = generateRulrFileService.generateRulrFile(ruleVo);
      return ruleFileName;
   }

   private void getQcCheckElemList(MrHpCheckSetVo mrHpCheckSetVo, String errorColumn) {
   }

   private void getRuleVo01(MrHpCheckSetVo mrHpCheckSetVo, RuleVo ruleVo) throws Exception {
      String preObj = mrHpCheckSetVo.getCheckTable1().toLowerCase(Locale.ROOT).equals("mr_hp") ? "" : "MrHpAttachVo.";
      ruleVo.setCheckVo("MrHpVo");
      if (mrHpCheckSetVo.getCheckTypeNo().equals("0101")) {
         JSONArray jsonArray = JSONArray.parseArray(mrHpCheckSetVo.getCheckColumn1());
         ruleVo.setErrorColumn(this.getErrorColumnByCheckExpressionVoList(jsonArray, mrHpCheckSetVo));
         List<String> notNullList = new ArrayList(jsonArray.size());

         for(int i = 0; i < jsonArray.size(); ++i) {
            JSONObject temp = JSONObject.parseObject(jsonArray.get(i).toString());
            notNullList.add(preObj + temp.getString("fieldProp"));
         }

         ruleVo.setNotNullList(notNullList);
      } else if (mrHpCheckSetVo.getCheckTypeNo().equals("0102")) {
         String errorColumn = this.getErrorColumnByJSONString(mrHpCheckSetVo.getCheckColumn2(), mrHpCheckSetVo);
         ruleVo.setErrorColumn(errorColumn);
         String checkExpression1 = mrHpCheckSetVo.getCheckExpression1();
         JSONObject jsonObject = JSON.parseObject(checkExpression1);
         ruleVo.setNotNullRule(jsonObject.getString("codeCheckExpression"));
         String preObj2 = mrHpCheckSetVo.getCheckTable2().toLowerCase(Locale.ROOT).equals("mr_hp") ? "" : "MrHpAttachVo.";
         String checkColumn2 = mrHpCheckSetVo.getCheckColumn2();
         ruleVo.setCheckColumn(preObj2 + errorColumn);
         mrHpCheckSetVo.setCheckHasConditions("1");
      }

   }

   private void getRuleVo02(MrHpCheckSetVo mrHpCheckSetVo, RuleVo ruleVo) throws Exception {
      String preObj = mrHpCheckSetVo.getCheckTable1().toLowerCase(Locale.ROOT).equals("mr_hp") ? "" : "MrHpAttachVo.";
      ruleVo.setCheckVo("MrHpVo");
      String errorColumn = this.getErrorColumnByJSONString(mrHpCheckSetVo.getCheckColumn1(), mrHpCheckSetVo);
      ruleVo.setErrorColumn(errorColumn);
      ruleVo.setCheckColumn(preObj + errorColumn.split("\\|")[0]);
      ruleVo.setIsCheckNull(mrHpCheckSetVo.getCheckNull());
      switch (mrHpCheckSetVo.getCheckTypeNo()) {
         case "0201":
            List<SysDictData> dictDataList = this.sysDictTypeService.selectDictDataByType(mrHpCheckSetVo.getCheckTable2());
            List<String> enumerationList = new ArrayList(dictDataList.size());
            if (mrHpCheckSetVo.getCheckColumn2().equals("dictValue")) {
               enumerationList = (List)dictDataList.stream().map((t) -> t.getDictValue()).collect(Collectors.toList());
            } else if (mrHpCheckSetVo.getCheckColumn2().equals("dictLabel")) {
               enumerationList = (List)dictDataList.stream().map((t) -> t.getDictLabel()).collect(Collectors.toList());
            }

            ruleVo.setEnumerationList(enumerationList);
            break;
         case "0202":
            List<String> enumerationList2 = Arrays.asList(mrHpCheckSetVo.getCheckExpression1().split("\\|"));
            ruleVo.setEnumerationList(enumerationList2);
            break;
         case "0203":
            JSONObject jsonObject = JSON.parseObject(mrHpCheckSetVo.getCheckExpression1());
            String checkExpression = jsonObject.getString("codeCheckExpression");
            ruleVo.setValidationExpression(checkExpression);
            break;
         case "0204":
            ruleVo.setRegular(mrHpCheckSetVo.getCheckExpression1());
      }

   }

   private void getRuleVo03(MrHpCheckSetVo mrHpCheckSetVo, RuleVo ruleVo) throws Exception {
      String preObj = mrHpCheckSetVo.getCheckTable1().toLowerCase(Locale.ROOT).equals("mr_hp") ? "" : "MrHpAttachVo.";
      String preObj2 = "";
      if (StringUtils.isNotBlank(mrHpCheckSetVo.getCheckTable2())) {
         preObj2 = mrHpCheckSetVo.getCheckTable2().toLowerCase(Locale.ROOT).equals("mr_hp") ? "" : "MrHpAttachVo.";
      }

      ruleVo.setCheckVo("MrHpVo");
      String checkRelopeRator = mrHpCheckSetVo.getCheckRelopeRator();
      JSONObject checkRelopeRatorObj = StringUtils.isNotBlank(checkRelopeRator) ? JSONObject.parseObject(checkRelopeRator) : null;
      String codeOperator = checkRelopeRatorObj != null ? checkRelopeRatorObj.getString("codeOperator") : "";
      switch (mrHpCheckSetVo.getCheckTypeNo()) {
         case "0301":
            JSONObject object1 = JSONObject.parseObject(mrHpCheckSetVo.getCheckExpression1());
            JSONObject object2 = JSONObject.parseObject(mrHpCheckSetVo.getCheckExpression2());
            JSONArray jsonArray = (JSONArray)object2.get("checkExpressionVoList");
            String errorColumn = this.getErrorColumnByCheckExpressionVoList(jsonArray, mrHpCheckSetVo);
            ruleVo.setErrorColumn(errorColumn);
            ruleVo.setValidationExpression(object1.getString("codeCheckExpression"));
            ruleVo.setRelationValidationExpression(object2.getString("codeCheckExpression"));
            ruleVo.setExpression2List(jsonArray);
            mrHpCheckSetVo.setCheckHasConditions("1");
            break;
         case "0302":
            String checkColumn1Str = this.getErrorColumnByJSONString(mrHpCheckSetVo.getCheckColumn1(), mrHpCheckSetVo);
            String checkColumn2Str = this.getErrorColumnByJSONString(mrHpCheckSetVo.getCheckColumn2(), mrHpCheckSetVo);
            ruleVo.setCheckColumn(checkColumn1Str.split("\\|")[0]);
            ruleVo.setErrorColumn(checkColumn1Str);
            ruleVo.setRelationColumn(checkColumn2Str.split("\\|")[0]);
            if (!codeOperator.contains("@column") && !codeOperator.contains("@value")) {
               codeOperator = preObj + ruleVo.getCheckColumn() + codeOperator + ruleVo.getRelationColumn();
            } else {
               codeOperator = codeOperator.replaceAll("@column", ruleVo.getCheckColumn()).replaceAll("@value", "" + ruleVo.getRelationColumn() + "");
            }

            ruleVo.setValidationExpression(codeOperator);
            break;
         case "0303":
            String checkColumnStr2 = this.getErrorColumnByJSONString(mrHpCheckSetVo.getCheckColumn1(), mrHpCheckSetVo);
            String feeCode = checkColumnStr2.split("\\|")[0];
            ruleVo.setCheckColumn(feeCode);
            ruleVo.setAmount(feeCode);
            JSONObject object1 = JSONObject.parseObject(mrHpCheckSetVo.getCheckExpression1());
            ruleVo.setLogicCheckRule(codeOperator);
            String feeTypeCodes = object1.getString("codeCheckExpression");
            String[] feeTypeCodeArr = feeTypeCodes.split("\\|");
            List<String> feeList = new ArrayList();

            for(int i = 0; i < feeTypeCodeArr.length; ++i) {
               String feeTypeCode = feeTypeCodeArr[i];
               feeList.add(feeTypeCode);
            }

            ruleVo.setErrorColumn(checkColumnStr2);
            ruleVo.setFeeList(feeList);
            String offset = object1.getString("offsetCheckExpression");
            ruleVo.setOffset(offset);
      }

   }

   private void getRuleVo04(MrHpCheckSetVo mrHpCheckSetVo, RuleVo ruleVo) {
      mrHpCheckSetVo.setCheckHasConditions("1");
      ruleVo.setCheckVo("MrHpVo");
      JSONObject object1 = StringUtils.isBlank(mrHpCheckSetVo.getCheckExpression1()) ? null : JSONObject.parseObject(mrHpCheckSetVo.getCheckExpression1());
      JSONObject object2 = StringUtils.isBlank(mrHpCheckSetVo.getCheckExpression2()) ? null : JSONObject.parseObject(mrHpCheckSetVo.getCheckExpression2());
      String errorColumn = "";
      if (object1 != null) {
         switch (mrHpCheckSetVo.getCheckTypeNo()) {
            case "0401":
               ruleVo.setValidationExpression(object1.getString("codeCheckExpression"));
               break;
            case "0402":
               String validationExpression = StringUtils.isBlank(mrHpCheckSetVo.getCheckRelopeRator()) ? "" : "dia.getDiaItem().equals(\"" + mrHpCheckSetVo.getCheckRelopeRator() + "\") && ";
               validationExpression = validationExpression + (StringUtils.isBlank(mrHpCheckSetVo.getCheckColumn1()) ? "" : "dia.getDiaClass().equals(\"" + mrHpCheckSetVo.getCheckColumn1() + "\") && ");
               validationExpression = validationExpression + object1.getString("objCheckExpression");
               ruleVo.setValidationExpression(validationExpression);
         }

         errorColumn = errorColumn + this.getErrorColumnByCheckExpressionVoList(object1.getJSONArray("checkExpressionVoList"), mrHpCheckSetVo);
      }

      if (object2 != null) {
         String validationExpression1 = org.apache.commons.lang3.StringUtils.isBlank(mrHpCheckSetVo.getCheckTable2()) ? "" : "diaItem.equals(\"" + mrHpCheckSetVo.getCheckTable2() + "\") && ";
         validationExpression1 = validationExpression1 + (StringUtils.isBlank(mrHpCheckSetVo.getCheckColumn2()) ? "" : "diaClass.equals(\"" + mrHpCheckSetVo.getCheckColumn2() + "\") && ");
         validationExpression1 = validationExpression1 + object2.getString("codeCheckExpression");
         ruleVo.setValidationExpression1(validationExpression1);
         errorColumn = errorColumn + this.getErrorColumnByCheckExpressionVoList(object2.getJSONArray("checkExpressionVoList"), mrHpCheckSetVo);
      }

      ruleVo.setErrorColumn(errorColumn);
   }

   private void getRuleVo05(MrHpCheckSetVo mrHpCheckSetVo, RuleVo ruleVo) {
      mrHpCheckSetVo.setCheckHasConditions("1");
      ruleVo.setCheckVo("MrHpVo");
      JSONObject object1 = StringUtils.isBlank(mrHpCheckSetVo.getCheckExpression1()) ? null : JSONObject.parseObject(mrHpCheckSetVo.getCheckExpression1());
      JSONObject object2 = StringUtils.isBlank(mrHpCheckSetVo.getCheckExpression2()) ? null : JSONObject.parseObject(mrHpCheckSetVo.getCheckExpression2());
      String errorColumn = "";
      if (object1 != null) {
         switch (mrHpCheckSetVo.getCheckTypeNo()) {
            case "0501":
               ruleVo.setValidationExpression(object1.getString("codeCheckExpression"));
               break;
            case "0502":
               String checkColumn1 = mrHpCheckSetVo.getCheckColumn1();
               String validationExpression = "";
               if (StringUtils.isNotBlank(checkColumn1)) {
                  validationExpression = checkColumn1.equals("0") ? "opeNo.equals(\"0\")" : "!opeNo.equals(\"0\")";
               }

               validationExpression = validationExpression + object1.getString("objCheckExpression");
               ruleVo.setValidationExpression(validationExpression);
         }

         errorColumn = errorColumn + this.getErrorColumnByCheckExpressionVoList(object1.getJSONArray("checkExpressionVoList"), mrHpCheckSetVo);
      }

      if (object2 != null) {
         String checkColumn2 = mrHpCheckSetVo.getCheckColumn2();
         String validationExpression1 = "";
         if (StringUtils.isNotBlank(checkColumn2)) {
            validationExpression1 = checkColumn2.equals("0") ? "opeNo.equals(\"0\") && " : "!opeNo.equals(\"0\") && ";
         }

         validationExpression1 = validationExpression1 + object2.getString("codeCheckExpression");
         ruleVo.setValidationExpression1(validationExpression1);
         errorColumn = errorColumn + this.getErrorColumnByCheckExpressionVoList(object2.getJSONArray("checkExpressionVoList"), mrHpCheckSetVo);
      }

      ruleVo.setErrorColumn(errorColumn);
   }

   private String getErrorColumnByCheckFeeVoList(JSONArray jsonArray, MrHpCheckSetVo mrHpCheckSetVo) {
      List<String> fieldPropList = new ArrayList(jsonArray.size());

      for(int i = 0; i < jsonArray.size(); ++i) {
         JSONObject temp = JSONObject.parseObject(jsonArray.get(i).toString());
         String fieldProp = temp.getString("feeTypeCode");
         String fieldName = temp.getString("feeTypeName");
         fieldPropList.add(fieldProp + "|" + fieldName);
      }

      fieldPropList.stream().distinct();
      return StringUtils.join(fieldPropList, ",");
   }

   private String getErrorColumnByCheckExpressionVoList(JSONArray jsonArray, MrHpCheckSetVo mrHpCheckSetVo) {
      List<QcCheckElem> qcCheckElemList = new ArrayList(1);
      List<String> fieldPropList = new ArrayList(jsonArray.size());

      for(int i = 0; i < jsonArray.size(); ++i) {
         JSONObject temp = JSONObject.parseObject(jsonArray.get(i).toString());
         String fieldProp = temp.getString("fieldProp");
         String fieldName = temp.getString("fieldName");
         fieldPropList.add(fieldProp);
         QcCheckElem qcCheckElem = new QcCheckElem(mrHpCheckSetVo.getId(), fieldProp, fieldName, CommonConstants.QC.RULE_TYPE_CODE_7);
         qcCheckElemList.add(qcCheckElem);
      }

      fieldPropList.stream().distinct();
      if (mrHpCheckSetVo.getQcCheckElemList() == null) {
         mrHpCheckSetVo.setQcCheckElemList(qcCheckElemList.isEmpty() ? null : qcCheckElemList);
      } else {
         mrHpCheckSetVo.getQcCheckElemList().addAll(qcCheckElemList);
      }

      return StringUtils.join(fieldPropList, ",");
   }

   private String getErrorColumnByJSONString(String checkColumn, MrHpCheckSetVo mrHpCheckSetVo) {
      String resStr = null;
      List<QcCheckElem> qcCheckElemList = new ArrayList(1);
      if (StringUtils.isNotBlank(checkColumn)) {
         JSONArray checkColumn2Arr = JSONArray.parseArray(checkColumn);
         JSONObject checkColumn2Obj = checkColumn2Arr.getJSONObject(0);
         String fieldProp = checkColumn2Obj.getString("fieldProp");
         String fieldName = checkColumn2Obj.getString("fieldName");
         resStr = fieldProp;
         QcCheckElem qcCheckElem = new QcCheckElem(mrHpCheckSetVo.getId(), fieldProp, fieldName, CommonConstants.QC.RULE_TYPE_CODE_7);
         qcCheckElemList.add(qcCheckElem);
      }

      if (mrHpCheckSetVo.getQcCheckElemList() == null) {
         mrHpCheckSetVo.setQcCheckElemList(qcCheckElemList.isEmpty() ? null : qcCheckElemList);
      } else {
         mrHpCheckSetVo.getQcCheckElemList().addAll(qcCheckElemList);
      }

      return resStr;
   }

   public MrHpCheckSetVo selectMrHpCheckSetByCheckName(String checkName, Long id) throws Exception {
      return this.mrHpCheckSetMapper.selectMrHpCheckSetByCheckName(checkName, id);
   }

   public void updateEditFlag(MrHpCheckSet mrHpCheckSet) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      mrHpCheckSet.setAltPerCode(sysUser.getUserName());
      mrHpCheckSet.setAltPerName(sysUser.getNickName());
      this.mrHpCheckSetMapper.updateEditFlag(mrHpCheckSet);
   }

   public List selectMrHpCheckSetList(MrHpCheckSetVo mrHpCheckSetVo) throws Exception {
      return this.mrHpCheckSetMapper.selectMrHpCheckSetList(mrHpCheckSetVo);
   }
}
