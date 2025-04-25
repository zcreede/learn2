package com.emr.project.tmpl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.project.emr.domain.vo.SysEmrTypeConfigVo;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
import com.emr.project.sys.domain.QuoteElem;
import com.emr.project.sys.service.IQuoteElemService;
import com.emr.project.system.domain.BasDictMedicine;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.BasTmplDisease;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysElemStrstore;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmplMedicineDept;
import com.emr.project.system.mapper.SysDeptMapper;
import com.emr.project.system.service.IBasDictMedicineService;
import com.emr.project.system.service.IBasTmplDiseaseService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysDictTypeService;
import com.emr.project.system.service.ISysElemStrstoreService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysUserService;
import com.emr.project.system.service.ITmplMedicineDeptService;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.ElemDate;
import com.emr.project.tmpl.domain.ElemGender;
import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.ElemPhysign;
import com.emr.project.tmpl.domain.ElemSign;
import com.emr.project.tmpl.domain.TmplAuditRecord;
import com.emr.project.tmpl.domain.TmplDept;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.TmplQuoteElem;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.tmpl.domain.vo.TmplIndexSearchVo;
import com.emr.project.tmpl.domain.vo.TmplIndexVo;
import com.emr.project.tmpl.domain.vo.TreeVo;
import com.emr.project.tmpl.mapper.TmplIndexMapper;
import com.emr.project.tmpl.service.IElemAttriService;
import com.emr.project.tmpl.service.IElemDateService;
import com.emr.project.tmpl.service.IElemGenderService;
import com.emr.project.tmpl.service.IElemMacroService;
import com.emr.project.tmpl.service.IElemPhysignService;
import com.emr.project.tmpl.service.IElemSignService;
import com.emr.project.tmpl.service.ITmplAuditRecordService;
import com.emr.project.tmpl.service.ITmplDeptService;
import com.emr.project.tmpl.service.ITmplElemLinkService;
import com.emr.project.tmpl.service.ITmplIndexService;
import com.emr.project.tmpl.service.ITmplQuoteElemService;
import com.emr.project.webEditor.util.XmlElementParseUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TmplIndexServiceImpl implements ITmplIndexService {
   protected final Logger log = LoggerFactory.getLogger(TmplIndexServiceImpl.class);
   @Autowired
   private TmplIndexMapper tmplIndexMapper;
   @Autowired
   private IBasDictMedicineService basDictMedicineService;
   @Autowired
   private ISysDictTypeService sysDictTypeService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private IBasTmplDiseaseService diseaseService;
   @Autowired
   private ISysEmrTypeConfigService sysEmrTypeConfigService;
   @Autowired
   private ITmplDeptService tmplDeptService;
   @Autowired
   private ITmplAuditRecordService tmplAuditRecordService;
   @Resource
   private IElemAttriService elemAttriService;
   @Resource
   private IElemDateService elemDateService;
   @Resource
   private IElemGenderService elemGenderService;
   @Resource
   private IElemMacroService elemMacroService;
   @Resource
   private IElemPhysignService elemPhysignService;
   @Resource
   private IElemSignService elemSignService;
   @Resource
   private ISysElemStrstoreService sysElemStrstoreService;
   @Resource
   private IQuoteElemService quoteElemService;
   @Resource
   private SysDeptMapper sysDeptMapper;
   @Resource
   private ITmplQuoteElemService tmplQuoteElemService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITmplMedicineDeptService tmplMedicineDeptService;
   @Autowired
   private ITmplElemLinkService tmplElemLinkService;
   @Autowired
   private ISysUserService sysUserService;

   public TmplIndex selectTmplIndexById(Long id) {
      return this.tmplIndexMapper.selectTmplIndexById(id);
   }

   public TmplIndex selectTmplIndexNoFileById(Long id) {
      return this.tmplIndexMapper.selectTmplIndexNoFileById(id);
   }

   public TmplIndex selectTmplStandardIndexNoFileById(Long id) {
      return this.tmplIndexMapper.selectTmplStandardIndexNoFileById(id);
   }

   public List selectTmplIndexList(TmplIndex tmplIndex) {
      return this.tmplIndexMapper.selectTmplIndexList(tmplIndex);
   }

   public List selectTmplIndexVoList(TmplIndexVo tmplIndexVo) throws Exception {
      return this.tmplIndexMapper.selectTmplIndexVoList(tmplIndexVo);
   }

   public List selectHeaderTreeList(TmplIndex tmplIndex) throws Exception {
      List<TmplIndex> list = this.tmplIndexMapper.selectHeaderTreeList(tmplIndex);
      Map<String, List<TmplIndex>> mapList = (Map)list.stream().collect(Collectors.groupingBy(TmplIndex::getTempType));
      List<TreeSelect> treeList = new ArrayList();
      String tmplType = "页眉";

      for(String key : mapList.keySet()) {
         if ("300".equals(key)) {
            tmplType = "页脚";
         }

         List<TreeSelect> chiltreeList = new ArrayList();

         for(TmplIndex tmpl : (List)mapList.get(key)) {
            TreeSelect chiltree = new TreeSelect(tmpl.getId(), tmpl.getId().toString(), tmpl.getTempName());
            chiltreeList.add(chiltree);
         }

         TreeSelect tree = new TreeSelect(Long.parseLong(key), key, tmplType, chiltreeList);
         treeList.add(tree);
      }

      return treeList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertTmplIndex(TmplIndexVo tmplIndexVo) throws Exception {
      if (!"300".equals(tmplIndexVo.getTempType()) && !"200".equals(tmplIndexVo.getTempType())) {
         SysEmrTypeConfigVo sysEmrTypeConfig = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndexVo.getTempType());
         if (sysEmrTypeConfig != null) {
            tmplIndexVo.setTempClass(sysEmrTypeConfig.getEmrClassCode());
         }
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      if (tmplIndexVo.getIsMedicalRecordTemplate() != null && tmplIndexVo.getIsMedicalRecordTemplate()) {
         tmplIndexVo.setTempEditState("3");
      } else {
         tmplIndexVo.setTempEditState("1");
      }

      tmplIndexVo.setTempState("1");
      tmplIndexVo.setValidFlag("1");
      tmplIndexVo.setId(SnowIdUtils.uniqueLong());
      tmplIndexVo.setCrePerCode(user.getUserName());
      tmplIndexVo.setCrePerName(user.getNickName());
      tmplIndexVo.setInputstrphtc(PinYinUtil.getPinYinHeadChar(tmplIndexVo.getTempName()));
      int row = this.tmplIndexMapper.insertTmplIndex(tmplIndexVo);
      TmplMedicineDept param = new TmplMedicineDept();
      param.setMedicineCode(tmplIndexVo.getTempMajor());
      List<TmplMedicineDept> tmplMedicineDeptList = this.tmplMedicineDeptService.selectTmplMedicineDeptList(param);
      if (CollectionUtils.isNotEmpty(tmplMedicineDeptList)) {
         List<String> deptCode = (List)tmplMedicineDeptList.stream().map((t) -> t.getDeptCd()).collect(Collectors.toList());
         this.insertTmplDeptList(deptCode, tmplIndexVo.getId());
      }

      return row;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertTmplIndexFromStandard(TmplIndexVo tmplIndexVo) throws Exception {
      if (!"300".equals(tmplIndexVo.getTempType()) && !"200".equals(tmplIndexVo.getTempType())) {
         SysEmrTypeConfigVo sysEmrTypeConfig = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndexVo.getTempType());
         if (sysEmrTypeConfig != null) {
            tmplIndexVo.setTempClass(sysEmrTypeConfig.getEmrClassCode());
         }
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      if (tmplIndexVo.getIsMedicalRecordTemplate() != null && tmplIndexVo.getIsMedicalRecordTemplate()) {
         tmplIndexVo.setTempEditState("3");
      } else {
         tmplIndexVo.setTempEditState("1");
      }

      tmplIndexVo.setTempState("1");
      tmplIndexVo.setValidFlag("1");
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("base64", tmplIndexVo.getBase64Str());
      jsonObject.put("xmlStr", tmplIndexVo.getXmlStr());
      String tempFile = jsonObject.toJSONString();
      tmplIndexVo.setTempEditFile(tempFile);
      tmplIndexVo.setId(SnowIdUtils.uniqueLong());
      tmplIndexVo.setCrePerCode(user.getUserName());
      tmplIndexVo.setCrePerName(user.getNickName());
      tmplIndexVo.setInputstrphtc(PinYinUtil.getPinYinHeadChar(tmplIndexVo.getTempName()));
      int row = this.tmplIndexMapper.insertTmplIndex(tmplIndexVo);
      TmplMedicineDept param = new TmplMedicineDept();
      param.setMedicineCode(tmplIndexVo.getTempMajor());
      List<TmplMedicineDept> tmplMedicineDeptList = this.tmplMedicineDeptService.selectTmplMedicineDeptList(param);
      if (CollectionUtils.isNotEmpty(tmplMedicineDeptList)) {
         List<String> deptCode = (List)tmplMedicineDeptList.stream().map((t) -> t.getDeptCd()).collect(Collectors.toList());
         this.insertTmplDeptList(deptCode, tmplIndexVo.getId());
      }

      return row;
   }

   public int insertTmplStandardIndex(TmplIndexVo tmplIndexVo) throws Exception {
      if (!"300".equals(tmplIndexVo.getTempType()) && !"200".equals(tmplIndexVo.getTempType())) {
         SysEmrTypeConfigVo sysEmrTypeConfig = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndexVo.getTempType());
         if (sysEmrTypeConfig != null) {
            tmplIndexVo.setTempClass(sysEmrTypeConfig.getEmrClassCode());
         }
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      if (tmplIndexVo.getIsMedicalRecordTemplate() != null && tmplIndexVo.getIsMedicalRecordTemplate()) {
         tmplIndexVo.setTempEditState("3");
      } else {
         tmplIndexVo.setTempEditState("1");
      }

      tmplIndexVo.setTempState("1");
      tmplIndexVo.setValidFlag("1");
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("base64", tmplIndexVo.getBase64Str());
      jsonObject.put("xmlStr", tmplIndexVo.getXmlStr());
      String tempFile = jsonObject.toJSONString();
      if (tmplIndexVo != null && StringUtils.isNotBlank(tmplIndexVo.getBase64Str()) && StringUtils.isNotBlank(tmplIndexVo.getXmlStr())) {
         tmplIndexVo.setTempEditFile(tempFile);
      }

      tmplIndexVo.setId(SnowIdUtils.uniqueLong());
      tmplIndexVo.setCrePerCode(user.getUserName());
      tmplIndexVo.setCrePerName(user.getNickName());
      tmplIndexVo.setInputstrphtc(PinYinUtil.getPinYinHeadChar(tmplIndexVo.getTempName()));
      int row = this.tmplIndexMapper.insertTmplStandardIndex(tmplIndexVo);
      return row;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int updateTmplIndex(TmplIndexVo tmplIndexVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmplIndexVo.setAltPerCode(user.getUserName());
      tmplIndexVo.setAltPerName(user.getNickName());
      int row = this.tmplIndexMapper.updateTmplIndex(tmplIndexVo);
      this.tmplDeptService.deleteTmplDeptByTempId(tmplIndexVo.getId());
      TmplMedicineDept param = new TmplMedicineDept();
      param.setMedicineCode(tmplIndexVo.getTempMajor());
      List<TmplMedicineDept> tmplMedicineDeptList = this.tmplMedicineDeptService.selectTmplMedicineDeptList(param);
      if (CollectionUtils.isNotEmpty(tmplMedicineDeptList)) {
         List<String> deptCode = (List)tmplMedicineDeptList.stream().map((t) -> t.getDeptCd()).collect(Collectors.toList());
         this.insertTmplDeptList(deptCode, tmplIndexVo.getId());
      }

      return row;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int updateTmplStandardIndex(TmplIndexVo tmplIndexVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmplIndexVo.setAltPerCode(user.getUserName());
      tmplIndexVo.setAltPerName(user.getNickName());
      int row = this.tmplIndexMapper.updateTmplStandardIndex(tmplIndexVo);
      return row;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateTmplEditState(TmplIndexVo tmplIndexVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      TmplIndexVo tmpl = new TmplIndexVo();
      tmpl.setId(tmplIndexVo.getId());
      tmpl.setTempEditState("1");
      tmpl.setAltPerCode(basEmployee.getEmplNumber());
      tmpl.setAltPerName(basEmployee.getEmplName());
      this.tmplIndexMapper.updateTempStateSave(tmpl);
      TmplAuditRecord tmplAuditRecord = new TmplAuditRecord();
      tmplAuditRecord.setId(SnowIdUtils.uniqueLong());
      tmplAuditRecord.setTmplId(tmplIndexVo.getId());
      tmplAuditRecord.setAppDeptCd(tmplIndexVo.getAppDeptCd());
      tmplAuditRecord.setAppDeptName(tmplIndexVo.getAppDeptName());
      tmplAuditRecord.setAppDocCd(tmplIndexVo.getAppDocCd());
      tmplAuditRecord.setAppDocName(tmplIndexVo.getAppDocName());
      tmplAuditRecord.setAppTime(tmplIndexVo.getAppTime());
      tmplAuditRecord.setConDeptCd(tmplIndexVo.getConDeptCd());
      tmplAuditRecord.setConDeptName(tmplIndexVo.getConDeptName());
      tmplAuditRecord.setConDocCd(tmplIndexVo.getConDocCd());
      tmplAuditRecord.setConDocName(tmplIndexVo.getConDocName());
      tmplAuditRecord.setAuditResult(Long.parseLong("5"));
      tmplAuditRecord.setConDate(DateUtils.getNowDate());
      tmplAuditRecord.setConView(tmplIndexVo.getConView());
      tmplAuditRecord.setCrePerCode(tmplIndexVo.getCrePerCode());
      tmplAuditRecord.setCrePerName(tmplIndexVo.getCrePerName());
      this.tmplAuditRecordService.insertTmplAuditRecord(tmplAuditRecord);
   }

   public int updateTmplIndexState(TmplIndex tmplIndex) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      tmplIndex.setAltPerCode(basEmployee.getEmplNumber());
      tmplIndex.setAltPerName(basEmployee.getEmplName());
      return this.tmplIndexMapper.updateTmplIndexState(tmplIndex);
   }

   public int deleteTmplIndexByIds(Long[] ids) {
      return this.tmplIndexMapper.deleteTmplIndexByIds(ids);
   }

   public int deleteTmplIndexById(Long id) throws Exception {
      return this.tmplIndexMapper.deleteTmplIndexById(id);
   }

   public int deleteTmplStandardIndexById(Long id) throws Exception {
      return this.tmplIndexMapper.deleteTmplStandardIndexById(id);
   }

   public List getTempIndexTree(TmplIndex tmplIndex, int treeType) throws Exception {
      List<TmplIndex> tmplIndexAllList = this.tmplIndexMapper.selectTmplIndexList(tmplIndex);
      List<TmplIndex> tmplIndexHFList = (List)tmplIndexAllList.stream().filter((t) -> t.getTempType().equals("200") || t.getTempType().equals("300")).collect(Collectors.toList());
      List<TmplIndex> tmplIndexList = (List)tmplIndexAllList.stream().filter((t) -> !t.getTempType().equals("200") && !t.getTempType().equals("300")).collect(Collectors.toList());
      BasDictMedicine medicinesParam = new BasDictMedicine();
      List<BasDictMedicine> medicinesAllList = this.basDictMedicineService.selectBasDictMedicineList(medicinesParam);
      List<String> tempMajorList = (List)tmplIndexList.stream().map((t) -> t.getTempMajor()).distinct().collect(Collectors.toList());
      List<BasDictMedicine> medicinesList = (List)medicinesAllList.stream().filter((t) -> tempMajorList.contains(t.getCode())).collect(Collectors.toList());
      BasTmplDisease diseaseParam = new BasTmplDisease();
      diseaseParam.setValidFlag("1");
      List<String> tempDiseaseList = (List)tmplIndexList.stream().map((t) -> t.getTempDisease()).distinct().collect(Collectors.toList());
      List<BasTmplDisease> diseaseAllList = this.diseaseService.selectDiseaseList(diseaseParam);
      List<BasTmplDisease> diseaseList = (List)diseaseAllList.stream().filter((t) -> tempDiseaseList.contains(t.getCode())).collect(Collectors.toList());
      List<SysDictData> tempTypeList = this.sysDictTypeService.selectDictDataByType("s004");
      Map<String, List<TmplIndex>> tmplIndexListMedicinesMap = (Map)tmplIndexList.stream().collect(Collectors.groupingBy((t) -> t.getTempMajor()));
      List<TreeSelect> tempIndexTree = new ArrayList(medicinesList.size());
      if (treeType == 1 || treeType == 3) {
         for(BasDictMedicine medicines : medicinesList) {
            TreeSelect tree1 = new TreeSelect(medicines.getId(), medicines.getCode(), medicines.getName());
            List<TmplIndex> tmplIndexMedicinesList = (List)tmplIndexListMedicinesMap.get(medicines.getCode());
            Map<String, List<TmplIndex>> medicinesDiseaseMap = (Map)tmplIndexMedicinesList.stream().collect(Collectors.groupingBy((t) -> t.getTempDisease()));
            List<String> tempDiseaseStrList = (List)tmplIndexMedicinesList.stream().map((t) -> t.getTempDisease()).distinct().collect(Collectors.toList());
            List<BasTmplDisease> diseaseListTemp = (List)diseaseList.stream().filter((t) -> tempDiseaseStrList.contains(t.getCode())).collect(Collectors.toList());
            List<TreeSelect> tree2List = new ArrayList(diseaseListTemp.size());

            for(BasTmplDisease disease : diseaseListTemp) {
               TreeSelect tree2 = new TreeSelect(disease.getId(), disease.getCode(), disease.getName());
               List<TmplIndex> tmplIndexDiseaseListTemp = (List)medicinesDiseaseMap.get(disease.getCode());
               List<String> tempTypeStrList = (List)tmplIndexDiseaseListTemp.stream().map((t) -> t.getTempType()).distinct().collect(Collectors.toList());
               List<SysDictData> tempTypeListTemp = (List)tempTypeList.stream().filter((t) -> tempTypeStrList.contains(t.getDictValue())).collect(Collectors.toList());
               Map<String, List<TmplIndex>> tmplIndexListClassMap = (Map)tmplIndexDiseaseListTemp.stream().collect(Collectors.groupingBy((t) -> t.getTempType()));
               List<TreeSelect> tree3List = new ArrayList(tempTypeListTemp.size());

               for(SysDictData tempClass : tempTypeListTemp) {
                  TreeSelect tree3 = new TreeSelect(tempClass.getDictCode(), tempClass.getDictValue(), tempClass.getDictLabel());
                  List<TmplIndex> indexList = (List)tmplIndexListClassMap.get(tempClass.getDictValue());
                  List<TreeSelect> tree4List = new ArrayList(indexList.size());
                  indexList.forEach((t) -> tree4List.add(new TreeSelect(t.getId(), t.getId() + "", t.getTempName())));
                  tree3.setChildren(tree4List);
                  tree3List.add(tree3);
               }

               tree2.setChildren(tree3List);
               tree2List.add(tree2);
            }

            tree1.setChildren(tree2List);
            tempIndexTree.add(tree1);
         }
      }

      if (treeType == 2 || treeType == 3) {
         Map<String, List<TmplIndex>> tmplIndexHFListMap = (Map)tmplIndexHFList.stream().collect(Collectors.groupingBy((t) -> t.getTempType()));

         for(String tempType : tmplIndexHFListMap.keySet()) {
            String tempTypeName = "其他";
            if (tempType.equals("300")) {
               tempTypeName = "页脚";
            }

            if (tempType.equals("200")) {
               tempTypeName = "页眉";
            }

            TreeSelect treeSelect = new TreeSelect((Long)null, tempType, tempTypeName);
            List<TmplIndex> tmplIndexHFListTemp = (List)tmplIndexHFListMap.get(tempType);
            if (tmplIndexHFListTemp != null) {
               List<TreeSelect> tree2List = new ArrayList(tmplIndexHFListTemp.size());
               treeSelect.setChildren(tree2List);
            }

            tempIndexTree.add(treeSelect);
         }
      }

      return tempIndexTree;
   }

   public List selectTempTypeList(TmplIndex tmplIndex) throws Exception {
      List<TreeSelect> treeList = new ArrayList();
      TreeSelect footer = new TreeSelect("300", "页脚");
      TreeSelect header = new TreeSelect("200", "页眉");
      treeList.add(header);
      treeList.add(footer);

      for(SysDictData sysDictData : this.sysDictDataService.selectDictDataByType("s004")) {
         TreeSelect tree = new TreeSelect(sysDictData.getDictValue(), sysDictData.getDictLabel());
         treeList.add(tree);
      }

      return treeList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateTempStateSave(TmplIndexVo tmplIndexVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = user.getDept();
      tmplIndexVo.setAltPerCode(user.getUserName());
      tmplIndexVo.setAltPerName(user.getNickName());
      tmplIndexVo.setConDeptCd(sysDept.getDeptCode());
      tmplIndexVo.setConDeptName(sysDept.getDeptName());
      tmplIndexVo.setConDocCd(user.getUserName());
      tmplIndexVo.setConDocName(user.getNickName());
      if ("4".equals(tmplIndexVo.getTempEditState())) {
         tmplIndexVo.setTempState(tmplIndexVo.getTempEditState());
      }

      TmplIndex tmplIndex = this.tmplIndexMapper.selectTmplIndexById(tmplIndexVo.getId());
      if (tmplIndex != null) {
         tmplIndexVo.setTempFile(tmplIndex.getTempEditFile());
         TmplAuditRecord tmplAuditRecord = new TmplAuditRecord();
         tmplAuditRecord.setId(SnowIdUtils.uniqueLong());
         tmplAuditRecord.setTmplId(tmplIndex.getId());
         tmplAuditRecord.setAppDeptCd(tmplIndex.getAppDeptCd());
         tmplAuditRecord.setAppDeptName(tmplIndex.getAppDeptName());
         tmplAuditRecord.setAppDocCd(tmplIndex.getAppDocCd());
         tmplAuditRecord.setAppDocName(tmplIndex.getAppDocName());
         tmplAuditRecord.setAppTime(tmplIndex.getAppTime());
         tmplAuditRecord.setConDeptCd(sysDept.getDeptCode());
         tmplAuditRecord.setConDeptName(sysDept.getDeptName());
         tmplAuditRecord.setConDocCd(user.getUserName());
         tmplAuditRecord.setConDocName(user.getNickName());
         tmplAuditRecord.setAuditResult(Long.parseLong(tmplIndexVo.getTempEditState()));
         tmplAuditRecord.setConDate(tmplIndex.getConDate() != null ? tmplIndex.getConDate() : new Date());
         tmplAuditRecord.setConView(tmplIndexVo.getConView());
         tmplAuditRecord.setCrePerCode(tmplIndex.getCrePerCode());
         tmplAuditRecord.setCrePerName(tmplIndex.getCrePerName());
         tmplAuditRecord.setCreDate(tmplIndex.getCreDate());
         this.tmplAuditRecordService.insertTmplAuditRecord(tmplAuditRecord);
      }

      this.tmplIndexMapper.updateTempStateSave(tmplIndexVo);
   }

   public void insertTmplDeptList(List deptCodeList, Long tempId) throws Exception {
      SysDept allDept = null;
      if (deptCodeList.contains("000000")) {
         allDept = new SysDept();
         allDept.setDeptCode("000000");
         allDept.setDeptName("全部科室");
      }

      deptCodeList = (List)deptCodeList.stream().filter((t) -> !t.equals("000000")).collect(Collectors.toList());
      List<SysDept> deptList = (List<SysDept>)(CollectionUtils.isNotEmpty(deptCodeList) ? this.sysDeptMapper.selectByDeptIds(deptCodeList) : new ArrayList(1));
      if (allDept != null) {
         deptList.add(allDept);
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TmplDept> tmplDeptList = new ArrayList();

      for(SysDept sysDept : deptList) {
         TmplDept tmplDept = new TmplDept();
         if (sysDept.getDeptCode().equals("000000")) {
            tmplDept.setOrgCd(SecurityUtils.getLoginUser().getUser().getDept().getOrgCode());
         } else {
            tmplDept.setOrgCd(sysDept.getOrgCode());
         }

         tmplDept.setId(SnowIdUtils.uniqueLong());
         tmplDept.setTempId(tempId);
         tmplDept.setDeptCd(sysDept.getDeptCode());
         tmplDept.setDeptName(sysDept.getDeptName());
         tmplDept.setValidFlag("1");
         tmplDept.setCrePerCode(user.getUserName());
         tmplDept.setCrePerName(user.getNickName());
         tmplDeptList.add(tmplDept);
      }

      this.tmplDeptService.insertTmplDeptList(tmplDeptList);
   }

   public List getTempIndexTrees(TmplIndexSearchVo tmplIndex, int treeType) throws Exception {
      Boolean btnstate = false;
      List<TmplIndex> tmplIndexAllList = this.tmplIndexMapper.searchTmplIndexList(tmplIndex);
      List<TmplIndex> tmplIndexHFList = (List)tmplIndexAllList.stream().filter((t) -> t.getTempType().equals("200") || t.getTempType().equals("300")).collect(Collectors.toList());
      List<TmplIndex> tmplIndexList = (List)tmplIndexAllList.stream().filter((t) -> !t.getTempType().equals("200") && !t.getTempType().equals("300")).collect(Collectors.toList());
      BasDictMedicine medicinesParam = new BasDictMedicine();
      List<BasDictMedicine> medicinesAllList = this.basDictMedicineService.selectBasDictMedicineList(medicinesParam);
      List<String> tempMajorList = (List)tmplIndexList.stream().map((t) -> t.getTempMajor()).distinct().collect(Collectors.toList());
      List<BasDictMedicine> medicinesListTemp = (List)medicinesAllList.stream().filter((t) -> tempMajorList.contains(t.getCode())).collect(Collectors.toList());
      List<BasDictMedicine> medicinesLisSta = new ArrayList(medicinesListTemp.size());
      List<BasDictMedicine> medicinesList1 = (List)medicinesListTemp.stream().filter((t) -> t.getCode().equals("1111")).collect(Collectors.toList());
      List<BasDictMedicine> medicinesList2 = (List)medicinesListTemp.stream().filter((t) -> !t.getCode().equals("1111")).collect(Collectors.toList());
      medicinesLisSta.addAll(medicinesList2);
      medicinesLisSta.addAll(medicinesList1);
      String deptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      List<BasDictMedicine> basDictMedicineListDq = (List)medicinesLisSta.stream().filter((t) -> StringUtils.isNotBlank(t.getSoleDeptCode()) ? t.getSoleDeptCode().indexOf(deptCode) != -1 : deptCode.equals(t.getSoleDeptCode())).collect(Collectors.toList());
      List<BasDictMedicine> basDictMedicineListFdq = (List)medicinesLisSta.stream().filter((t) -> {
         boolean var10000;
         label23: {
            if (StringUtils.isNotBlank(t.getSoleDeptCode())) {
               if (t.getSoleDeptCode().indexOf(deptCode) == -1) {
                  break label23;
               }
            } else if (!deptCode.equals(t.getSoleDeptCode())) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }).collect(Collectors.toList());
      List<BasDictMedicine> medicinesList = new ArrayList(medicinesListTemp.size());
      medicinesList.addAll(basDictMedicineListDq);
      medicinesList.addAll(basDictMedicineListFdq);
      BasTmplDisease diseaseParam = new BasTmplDisease();
      diseaseParam.setValidFlag("1");
      List<String> tempDiseaseList = (List)tmplIndexList.stream().map((t) -> t.getTempDisease()).distinct().collect(Collectors.toList());
      List<BasTmplDisease> diseaseAllList = this.diseaseService.selectDiseaseList(diseaseParam);
      List<BasTmplDisease> diseaseList = (List)diseaseAllList.stream().filter((t) -> tempDiseaseList.contains(t.getCode())).collect(Collectors.toList());
      List<SysDictData> tempTypeList = this.sysDictTypeService.selectDictDataByType("s004");
      Map<String, List<TmplIndex>> tmplIndexListMedicinesMap = (Map)tmplIndexList.stream().collect(Collectors.groupingBy((t) -> t.getTempMajor()));
      List<TreeVo> tempIndexTree = new ArrayList(medicinesList.size());
      if (treeType == 1 || treeType == 3) {
         for(BasDictMedicine medicines : medicinesList) {
            TreeVo tree1 = new TreeVo(medicines.getId(), medicines.getCode(), medicines.getName());
            List<TmplIndex> tmplIndexMedicinesList = (List)tmplIndexListMedicinesMap.get(medicines.getCode());
            Map<String, List<TmplIndex>> medicinesDiseaseMap = (Map)tmplIndexMedicinesList.stream().collect(Collectors.groupingBy((t) -> t.getTempDisease()));
            List<String> tempDiseaseStrList = (List)tmplIndexMedicinesList.stream().map((t) -> t.getTempDisease()).distinct().collect(Collectors.toList());
            List<BasTmplDisease> diseaseListTemp = (List)diseaseList.stream().filter((t) -> tempDiseaseStrList.contains(t.getCode())).collect(Collectors.toList());
            List<TreeVo> tree2List = new ArrayList(diseaseListTemp.size());

            for(BasTmplDisease disease : diseaseListTemp) {
               TreeVo tree2 = new TreeVo(disease.getId(), disease.getCode(), disease.getName());
               List<TmplIndex> tmplIndexDiseaseListTemp = (List)medicinesDiseaseMap.get(disease.getCode());
               List<String> tempTypeStrList = (List)tmplIndexDiseaseListTemp.stream().map((t) -> t.getTempType()).distinct().collect(Collectors.toList());
               List<SysDictData> tempTypeListTemp = (List)tempTypeList.stream().filter((t) -> tempTypeStrList.contains(t.getDictValue())).collect(Collectors.toList());
               Map<String, List<TmplIndex>> tmplIndexListClassMap = (Map)tmplIndexDiseaseListTemp.stream().collect(Collectors.groupingBy((t) -> t.getTempType()));
               List<TreeVo> tree3List = new ArrayList(tempTypeListTemp.size());

               for(SysDictData tempClass : tempTypeListTemp) {
                  TreeVo tree3 = new TreeVo(tempClass.getDictCode(), tempClass.getDictValue(), tempClass.getDictLabel());
                  List<TmplIndex> indexList = (List)tmplIndexListClassMap.get(tempClass.getDictValue());
                  List<TreeVo> tree4List = new ArrayList(indexList.size());

                  for(TmplIndex param : indexList) {
                     TreeVo treeVo = new TreeVo();
                     treeVo.setId(param.getId());
                     treeVo.setCode(param.getId().toString());
                     treeVo.setLabel(param.getTempName());
                     treeVo.setBtnstate(btnstate);
                     treeVo.setSort(param.getTempNo());
                     treeVo.setCreDate(param.getCreDate());
                     treeVo.setEditFlag(this.addEditFlag_old(param));
                     treeVo.setTempType(param.getTempType());
                     treeVo.setTempState(param.getTempState());
                     treeVo.setTempEditState(param.getTempEditState());
                     tree4List.add(treeVo);
                  }

                  Collections.sort(tree4List, Comparator.comparing(TreeVo::getCreDate));
                  Collections.sort(tree4List, Comparator.comparing(TreeVo::getSort));
                  tree3.setChildrenVo(tree4List);
                  tree3List.add(tree3);
               }

               tree2.setChildrenVo(tree3List);
               tree2List.add(tree2);
            }

            tree1.setChildrenVo(tree2List);
            tempIndexTree.add(tree1);
         }
      }

      if (treeType == 2 || treeType == 3) {
         Map<String, List<TmplIndex>> tmplIndexHFListMap = (Map)tmplIndexHFList.stream().collect(Collectors.groupingBy((t) -> t.getTempType()));

         for(String tempType : tmplIndexHFListMap.keySet()) {
            String tempTypeName = "其他";
            if (tempType.equals("300")) {
               tempTypeName = "页脚";
            }

            if (tempType.equals("200")) {
               tempTypeName = "页眉";
            }

            TreeVo treeSelect = new TreeVo((Long)null, tempType, tempTypeName);
            List<TmplIndex> tmplIndexHFListTemp = (List)tmplIndexHFListMap.get(tempType);
            if (tmplIndexHFListTemp != null) {
               List<TreeVo> tree2List = new ArrayList(tmplIndexHFListTemp.size());

               for(TmplIndex param : tmplIndexHFListTemp) {
                  TreeVo treeVo = new TreeVo();
                  treeVo.setId(param.getId());
                  treeVo.setCode(param.getId().toString());
                  treeVo.setLabel(param.getTempName());
                  treeVo.setBtnstate(btnstate);
                  treeVo.setSort(param.getTempNo());
                  treeVo.setCreDate(param.getCreDate());
                  treeVo.setEditFlag(this.addEditFlag_old(param));
                  treeVo.setTempType(param.getTempType());
                  tree2List.add(treeVo);
               }

               treeSelect.setChildrenVo(tree2List);
            }

            tempIndexTree.add(treeSelect);
         }
      }

      return tempIndexTree;
   }

   public List getTempStandardIndexTrees(TmplIndexSearchVo tmplIndex, int treeType) throws Exception {
      Boolean btnstate = false;
      List<TmplIndex> tmplIndexAllList = this.tmplIndexMapper.searchTmplStandardIndexList(tmplIndex);
      List<TmplIndex> tmplIndexHFList = (List)tmplIndexAllList.stream().filter((t) -> t.getTempType().equals("200") || t.getTempType().equals("300")).collect(Collectors.toList());
      List<TmplIndex> tmplIndexList = (List)tmplIndexAllList.stream().filter((t) -> !t.getTempType().equals("200") && !t.getTempType().equals("300")).collect(Collectors.toList());
      BasDictMedicine medicinesParam = new BasDictMedicine();
      List<BasDictMedicine> medicinesAllList = this.basDictMedicineService.selectBasDictMedicineList(medicinesParam);
      List<String> tempTypeResList = (List)tmplIndexList.stream().map((t) -> t.getTempType()).distinct().collect(Collectors.toList());
      List<BasDictMedicine> medicinesListTemp = (List)medicinesAllList.stream().filter((t) -> tempTypeResList.contains(t.getCode())).collect(Collectors.toList());
      List<BasDictMedicine> medicinesLisSta = new ArrayList(medicinesListTemp.size());
      List<BasDictMedicine> medicinesList1 = (List)medicinesListTemp.stream().filter((t) -> t.getCode().equals("1111")).collect(Collectors.toList());
      List<BasDictMedicine> medicinesList2 = (List)medicinesListTemp.stream().filter((t) -> !t.getCode().equals("1111")).collect(Collectors.toList());
      medicinesLisSta.addAll(medicinesList2);
      medicinesLisSta.addAll(medicinesList1);
      BasTmplDisease diseaseParam = new BasTmplDisease();
      diseaseParam.setValidFlag("1");
      List<String> tempDiseaseList = (List)tmplIndexList.stream().map((t) -> t.getTempDisease()).distinct().collect(Collectors.toList());
      List<BasTmplDisease> diseaseAllList = this.diseaseService.selectDiseaseList(diseaseParam);
      List<BasTmplDisease> diseaseList = (List)diseaseAllList.stream().filter((t) -> tempDiseaseList.contains(t.getCode())).collect(Collectors.toList());
      List<SysDictData> tempTypeList = this.sysDictTypeService.selectDictDataByType("s004");
      Map<String, List<TmplIndex>> tmplIndexListMedicinesMap = (Map)tmplIndexList.stream().collect(Collectors.groupingBy((t) -> t.getTempMajor()));
      List<TreeVo> tempIndexTree = new ArrayList(medicinesLisSta.size());
      if (treeType == 1 || treeType == 3) {
         for(BasDictMedicine medicines : medicinesLisSta) {
            TreeVo tree1 = new TreeVo(medicines.getId(), medicines.getCode(), medicines.getName());
            List<TmplIndex> tmplIndexMedicinesList = (List)tmplIndexListMedicinesMap.get(medicines.getCode());
            Map<String, List<TmplIndex>> medicinesDiseaseMap = (Map)tmplIndexMedicinesList.stream().collect(Collectors.groupingBy((t) -> t.getTempDisease()));
            List<String> tempDiseaseStrList = (List)tmplIndexMedicinesList.stream().map((t) -> t.getTempDisease()).distinct().collect(Collectors.toList());
            List<BasTmplDisease> diseaseListTemp = (List)diseaseList.stream().filter((t) -> tempDiseaseStrList.contains(t.getCode())).collect(Collectors.toList());
            List<TreeVo> tree2List = new ArrayList(diseaseListTemp.size());

            for(BasTmplDisease disease : diseaseListTemp) {
               TreeVo tree2 = new TreeVo(disease.getId(), disease.getCode(), disease.getName());
               List<TmplIndex> tmplIndexDiseaseListTemp = (List)medicinesDiseaseMap.get(disease.getCode());
               List<String> tempTypeStrList = (List)tmplIndexDiseaseListTemp.stream().map((t) -> t.getTempType()).distinct().collect(Collectors.toList());
               List<SysDictData> tempTypeListTemp = (List)tempTypeList.stream().filter((t) -> tempTypeStrList.contains(t.getDictValue())).collect(Collectors.toList());
               Map<String, List<TmplIndex>> tmplIndexListClassMap = (Map)tmplIndexDiseaseListTemp.stream().collect(Collectors.groupingBy((t) -> t.getTempType()));
               List<TreeVo> tree3List = new ArrayList(tempTypeListTemp.size());

               for(SysDictData tempClass : tempTypeListTemp) {
                  TreeVo tree3 = new TreeVo(tempClass.getDictCode(), tempClass.getDictValue(), tempClass.getDictLabel());
                  List<TmplIndex> indexList = (List)tmplIndexListClassMap.get(tempClass.getDictValue());
                  List<TreeVo> tree4List = new ArrayList(indexList.size());

                  for(TmplIndex param : indexList) {
                     TreeVo treeVo = new TreeVo();
                     treeVo.setId(param.getId());
                     treeVo.setCode(param.getId().toString());
                     treeVo.setLabel(param.getTempName());
                     treeVo.setBtnstate(btnstate);
                     treeVo.setSort(param.getTempNo());
                     treeVo.setCreDate(param.getCreDate());
                     treeVo.setEditFlag(this.addEditFlag_old(param));
                     treeVo.setTempType(param.getTempType());
                     treeVo.setTempState(param.getTempState());
                     treeVo.setTempEditState(param.getTempEditState());
                     tree4List.add(treeVo);
                  }

                  Collections.sort(tree4List, Comparator.comparing(TreeVo::getCreDate));
                  Collections.sort(tree4List, Comparator.comparing(TreeVo::getSort));
                  tree3.setChildrenVo(tree4List);
                  tree3List.add(tree3);
               }

               tree2.setChildrenVo(tree3List);
               tree2List.add(tree2);
            }

            tree1.setChildrenVo(tree2List);
            tempIndexTree.add(tree1);
         }
      }

      if (treeType == 2 || treeType == 3) {
         Map<String, List<TmplIndex>> tmplIndexHFListMap = (Map)tmplIndexHFList.stream().collect(Collectors.groupingBy((t) -> t.getTempType()));

         for(String tempType : tmplIndexHFListMap.keySet()) {
            String tempTypeName = "其他";
            if (tempType.equals("300")) {
               tempTypeName = "页脚";
            }

            if (tempType.equals("200")) {
               tempTypeName = "页眉";
            }

            TreeVo treeSelect = new TreeVo((Long)null, tempType, tempTypeName);
            List<TmplIndex> tmplIndexHFListTemp = (List)tmplIndexHFListMap.get(tempType);
            if (tmplIndexHFListTemp != null) {
               List<TreeVo> tree2List = new ArrayList(tmplIndexHFListTemp.size());

               for(TmplIndex param : tmplIndexHFListTemp) {
                  TreeVo treeVo = new TreeVo();
                  treeVo.setId(param.getId());
                  treeVo.setCode(param.getId().toString());
                  treeVo.setLabel(param.getTempName());
                  treeVo.setBtnstate(btnstate);
                  treeVo.setSort(param.getTempNo());
                  treeVo.setCreDate(param.getCreDate());
                  treeVo.setEditFlag(this.addEditFlag_old(param));
                  treeVo.setTempType(param.getTempType());
                  tree2List.add(treeVo);
               }

               treeSelect.setChildrenVo(tree2List);
            }

            tempIndexTree.add(treeSelect);
         }
      }

      return tempIndexTree;
   }

   public boolean getIsAllFlag() throws Exception {
      String deptCodes = this.sysEmrConfigService.selectSysEmrConfigByKey("0054");
      List<String> deptCodeList = StringUtils.isNotBlank(deptCodes) ? new ArrayList(Arrays.asList(deptCodes.split(","))) : new ArrayList(1);
      String deptCodeCurr = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      Boolean isAllFlag = deptCodeList.contains(deptCodeCurr);
      return isAllFlag;
   }

   public List getTempIndexTrees(TmplIndexSearchVo tmplIndex) throws Exception {
      Boolean btnstate = false;
      Boolean isAllFlag = false;
      if (SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
         isAllFlag = true;
      } else {
         isAllFlag = this.getIsAllFlag();
      }

      List<TreeVo> tempIndexTree = new ArrayList(1);
      List<TmplIndex> tmplIndexList = null;
      List<TmplIndex> tmplIndexHFList = null;
      List<BasDictMedicine> basDictMedicineList = null;
      List<Long> medicineIdList = new ArrayList(1);
      if (!isAllFlag) {
         tmplIndex.setTmplDept(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         tmplIndex.setTmplOrg(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode());
         tmplIndex.setCommonTempMajor("1111");
         tmplIndexList = this.tmplIndexMapper.searchTmplIndexList(tmplIndex);
         List<String> medicineCodeList = (List)tmplIndexList.stream().map((t) -> t.getTempMajor()).distinct().collect(Collectors.toList());
         basDictMedicineList = this.basDictMedicineService.selectListByCodes(medicineCodeList);
         if (CollectionUtils.isNotEmpty(basDictMedicineList)) {
            medicineIdList = (List)basDictMedicineList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         }
      } else {
         List<TmplIndex> tmplIndexAllList = this.tmplIndexMapper.searchTmplIndexList(tmplIndex);
         tmplIndexHFList = (List)tmplIndexAllList.stream().filter((t) -> t.getTempType().equals("200") || t.getTempType().equals("300")).collect(Collectors.toList());
         tmplIndexList = (List)tmplIndexAllList.stream().filter((t) -> !t.getTempType().equals("200") && !t.getTempType().equals("300")).collect(Collectors.toList());
         List<String> medicineCodeList = (List)tmplIndexList.stream().map((t) -> t.getTempMajor()).distinct().collect(Collectors.toList());
         basDictMedicineList = this.basDictMedicineService.selectListByCodes(medicineCodeList);
         medicineIdList = (List)basDictMedicineList.stream().map((t) -> t.getId()).collect(Collectors.toList());
      }

      if (CollectionUtils.isNotEmpty(tmplIndexList)) {
         List<BasTmplDisease> diseaseList = this.diseaseService.selectListByMedicineIds(medicineIdList);
         List<SysDictData> tempTypeList = this.sysDictTypeService.selectDictDataByType("s004");
         Map<String, List<TmplIndex>> tmplIndexListMedicinesMap = (Map)tmplIndexList.stream().collect(Collectors.groupingBy((t) -> t.getTempMajor()));
         tempIndexTree = this.getTempIndexTrees(basDictMedicineList, tmplIndexListMedicinesMap, diseaseList, tempTypeList, btnstate, isAllFlag);
      }

      return tempIndexTree;
   }

   private List getTempIndexTrees(List medicinesList, Map tmplIndexListMedicinesMap, List diseaseList, List tempTypeList, Boolean btnstate, Boolean isAllFlag) throws Exception {
      List<TreeVo> tempIndexTree = new ArrayList(medicinesList.size());
      List<BasDictMedicine> medicinesListTmp = new ArrayList(medicinesList.size());
      List<BasDictMedicine> medicinesListTmpl = (List)medicinesList.stream().filter((t) -> t.getCode().equals("1111")).collect(Collectors.toList());
      List<BasDictMedicine> medicinesListTmp2 = (List)medicinesList.stream().filter((t) -> !t.getCode().equals("1111")).collect(Collectors.toList());
      medicinesListTmp.addAll(medicinesListTmpl);
      medicinesListTmp.addAll(medicinesListTmp2);

      for(BasDictMedicine medicines : medicinesListTmp) {
         TreeVo tree1 = new TreeVo(medicines.getId(), medicines.getCode(), medicines.getName());
         List<TmplIndex> tmplIndexMedicinesList = (List)tmplIndexListMedicinesMap.get(medicines.getCode());
         Map<String, List<TmplIndex>> medicinesDiseaseMap = (Map)tmplIndexMedicinesList.stream().collect(Collectors.groupingBy((t) -> t.getTempDisease()));
         List<String> tempDiseaseStrList = (List)tmplIndexMedicinesList.stream().map((t) -> t.getTempDisease()).distinct().collect(Collectors.toList());
         List<BasTmplDisease> diseaseListTemp = (List)diseaseList.stream().filter((t) -> tempDiseaseStrList.contains(t.getCode())).collect(Collectors.toList());
         List<TreeVo> tree2List = new ArrayList(diseaseListTemp.size());

         for(BasTmplDisease disease : diseaseListTemp) {
            TreeVo tree2 = new TreeVo(disease.getId(), disease.getCode(), disease.getName());
            List<TmplIndex> tmplIndexDiseaseListTemp = (List)medicinesDiseaseMap.get(disease.getCode());
            List<String> tempTypeStrList = (List)tmplIndexDiseaseListTemp.stream().map((t) -> t.getTempType()).distinct().collect(Collectors.toList());
            List<SysDictData> tempTypeListTemp = (List)tempTypeList.stream().filter((t) -> tempTypeStrList.contains(t.getDictValue())).collect(Collectors.toList());
            Map<String, List<TmplIndex>> tmplIndexListClassMap = (Map)tmplIndexDiseaseListTemp.stream().collect(Collectors.groupingBy((t) -> t.getTempType()));
            List<TreeVo> tree3List = new ArrayList(tempTypeListTemp.size());

            for(SysDictData tempClass : tempTypeListTemp) {
               TreeVo tree3 = new TreeVo(tempClass.getDictCode(), tempClass.getDictValue(), tempClass.getDictLabel());
               List<TmplIndex> indexList = (List)tmplIndexListClassMap.get(tempClass.getDictValue());
               List<TreeVo> tree4List = new ArrayList(indexList.size());

               for(TmplIndex param : indexList) {
                  TreeVo treeVo = new TreeVo();
                  treeVo.setId(param.getId());
                  treeVo.setCode(param.getId().toString());
                  treeVo.setLabel(param.getTempName());
                  treeVo.setBtnstate(btnstate);
                  treeVo.setSort(param.getTempNo());
                  treeVo.setCreDate(param.getCreDate());
                  Boolean editFlag = this.addEditFlagOnly(param, isAllFlag);
                  treeVo.setEditFlag(editFlag);
                  treeVo.setTempType(param.getTempType());
                  treeVo.setTempState(param.getTempState());
                  treeVo.setTempEditState(param.getTempEditState());
                  tree4List.add(treeVo);
               }

               Collections.sort(tree4List, Comparator.comparing(TreeVo::getCreDate));
               Collections.sort(tree4List, Comparator.comparing(TreeVo::getSort));
               tree3.setChildrenVo(tree4List);
               tree3List.add(tree3);
            }

            tree2.setChildrenVo(tree3List);
            tree2List.add(tree2);
         }

         tree1.setChildrenVo(tree2List);
         tempIndexTree.add(tree1);
      }

      return tempIndexTree;
   }

   public List getTempStandardIndexTrees(TmplIndexSearchVo tmplIndex) throws Exception {
      List<TreeVo> tempIndexTree = new ArrayList(1);
      List<TmplIndex> tmplIndexList = null;
      List<TmplIndex> tmplIndexHFList = null;
      List<SysDictData> tempTypeReqList = null;
      new ArrayList(1);
      List<TmplIndex> tmplIndexAllList = this.tmplIndexMapper.searchTmplStandardIndexList(tmplIndex);
      tmplIndexHFList = (List)tmplIndexAllList.stream().filter((t) -> t.getTempType().equals("200") || t.getTempType().equals("300")).collect(Collectors.toList());
      tmplIndexList = (List)tmplIndexAllList.stream().filter((t) -> !t.getTempType().equals("200") && !t.getTempType().equals("300")).collect(Collectors.toList());
      List<String> tempTypeResList = (List)tmplIndexList.stream().map((t) -> t.getTempType()).distinct().collect(Collectors.toList());
      SysDictData dictData = new SysDictData();
      dictData.setDictType("s004");
      dictData.setDataValList(tempTypeResList);
      tempTypeReqList = this.sysDictTypeService.selectDictDataByTypeAndVal(dictData);
      List tmplTypeValList = (List)tempTypeReqList.stream().map((t) -> t.getDictLabel()).collect(Collectors.toList());
      if (CollectionUtils.isNotEmpty(tmplIndexList)) {
         List<SysDictData> tempTypeList = this.sysDictTypeService.selectDictDataByType("s004");
         Map<String, List<TmplIndex>> tmplIndexListMedicinesMap = (Map)tmplIndexList.stream().collect(Collectors.groupingBy((t) -> t.getTempType()));
         tempIndexTree = this.getTempStandardIndexTrees(tempTypeReqList, tmplIndexListMedicinesMap, tmplTypeValList, tempTypeList);
      }

      return tempIndexTree;
   }

   private List getTempStandardIndexTrees(List tempTypeReqList, Map tmplIndexListMedicinesMap, List tmplTypeValList, List tempTypeList) throws Exception {
      List<TreeVo> tempIndexTree = new ArrayList(tempTypeReqList.size());
      List<SysDictData> medicinesListTmp = new ArrayList(tempTypeReqList.size());
      List<SysDictData> medicinesListTmpl = (List)tempTypeReqList.stream().filter((t) -> t.getDictLabel().equals("1111")).collect(Collectors.toList());
      List<SysDictData> medicinesListTmp2 = (List)tempTypeReqList.stream().filter((t) -> !t.getDictLabel().equals("1111")).collect(Collectors.toList());
      medicinesListTmp.addAll(medicinesListTmpl);
      medicinesListTmp.addAll(medicinesListTmp2);

      for(SysDictData medicines : medicinesListTmp) {
         TreeVo tree1 = new TreeVo(medicines.getDictCode(), medicines.getDictValue(), medicines.getDictLabel());
         tree1.setEditFlag(Boolean.FALSE);
         List<TmplIndex> tmplIndexMedicinesList = (List)tmplIndexListMedicinesMap.get(medicines.getDictValue());
         List<String> tempDiseaseStrList = (List)tmplIndexMedicinesList.stream().map((t) -> t.getTempType()).distinct().collect(Collectors.toList());
         List<TmplIndex> diseaseListTemp = (List)tmplIndexMedicinesList.stream().filter((t) -> tempDiseaseStrList.contains(t.getTempType())).collect(Collectors.toList());
         List<TreeVo> tree2List = new ArrayList(diseaseListTemp.size());

         for(TmplIndex param : diseaseListTemp) {
            TreeVo treeVo = new TreeVo();
            treeVo.setId(param.getId());
            treeVo.setCode(param.getId().toString());
            treeVo.setLabel(param.getTempName());
            treeVo.setSort(param.getTempNo());
            treeVo.setCreDate(param.getCreDate());
            treeVo.setTempType(param.getTempType());
            treeVo.setTempState(param.getTempState());
            treeVo.setTempEditState(param.getTempEditState());
            treeVo.setStandardTmpl("1");
            treeVo.setEditFlag(Boolean.TRUE);
            tree2List.add(treeVo);
         }

         tree1.setChildrenVo(tree2List);
         tempIndexTree.add(tree1);
      }

      return tempIndexTree;
   }

   public void insertTmplElem(TmplIndexVo tmplIndexVo) throws Exception {
      String xmlStr = tmplIndexVo.getXmlStr();
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Long id = tmplIndexVo.getId();
      String tempName = tmplIndexVo.getTempName();
      String tempType = tmplIndexVo.getTempType();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("base64", tmplIndexVo.getBase64Str());
      jsonObject.put("xmlStr", tmplIndexVo.getXmlStr());
      String tempFile = jsonObject.toJSONString();
      tmplIndexVo.setTempEditFile(tempFile);
      tmplIndexVo.setAltPerCode(user.getUserName());
      tmplIndexVo.setAltPerName(user.getNickName());
      this.tmplIndexMapper.updateTmplIndex(tmplIndexVo);
      this.addTmplElem(tempIndexSaveElemVo, id, tempName, tempType);
   }

   public void insertTmplStandardElem(TmplIndexVo tmplIndexVo) throws Exception {
      String xmlStr = tmplIndexVo.getXmlStr();
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Long id = tmplIndexVo.getId();
      String tempName = tmplIndexVo.getTempName();
      String tempType = tmplIndexVo.getTempType();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("base64", tmplIndexVo.getBase64Str());
      jsonObject.put("xmlStr", tmplIndexVo.getXmlStr());
      String tempFile = jsonObject.toJSONString();
      tmplIndexVo.setTempEditFile(tempFile);
      tmplIndexVo.setAltPerCode(user.getUserName());
      tmplIndexVo.setAltPerName(user.getNickName());
      this.tmplIndexMapper.updateTmplStandardIndex(tmplIndexVo);
      this.addTmplElemStandard(tempIndexSaveElemVo, id, tempName, tempType);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void copyTmplIndex(TmplIndexVo tmplIndexVo) throws Exception {
      Long sourceTmplId = tmplIndexVo.getId();
      this.insertTmplIndex(tmplIndexVo);
      this.insertTmplElem(tmplIndexVo);
      Long targetTmplId = tmplIndexVo.getId();
      this.tmplElemLinkService.copyToNewTmplIndex(sourceTmplId, targetTmplId, tmplIndexVo.getTempName());
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void copyStaTmplToHosIndex(TmplIndexVo tmplIndexVo) throws Exception {
      Long sourceTmplId = tmplIndexVo.getId();
      this.insertTmplIndexFromStandard(tmplIndexVo);
      this.insertTmplElem(tmplIndexVo);
      Long targetTmplId = tmplIndexVo.getId();
      this.tmplElemLinkService.copyToNewTmplIndex(sourceTmplId, targetTmplId, tmplIndexVo.getTempName());
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void copyHosTmplToStandardIndex(TmplIndexVo tmplIndexVo) throws Exception {
      Long sourceTmplId = tmplIndexVo.getId();
      this.insertTmplStandardIndex(tmplIndexVo);
      this.insertTmplElem(tmplIndexVo);
      Long targetTmplId = tmplIndexVo.getId();
      this.tmplElemLinkService.copyToNewTmplIndex(sourceTmplId, targetTmplId, tmplIndexVo.getTempName());
   }

   public String judgeElemStrstore(TmplIndexVo tmplIndexVo, TempIndexSaveElemVo tempIndexSaveElemVo) throws Exception {
      String str = "";
      StringBuffer sb = this.getElemStrstore(tmplIndexVo, tempIndexSaveElemVo);
      if (StringUtils.isNotEmpty((CharSequence)sb)) {
         str = sb.substring(0, sb.length() - 1);
      }

      return str;
   }

   public TmplIndexVo updateTempStateSubmit(TmplIndexVo tmplIndexVo, TempIndexSaveElemVo tempIndexSaveElemVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmplIndexVo.setAppDocCd(user.getUserName());
      tmplIndexVo.setAppDocName(user.getNickName());
      tmplIndexVo.setAppDeptCd(user.getDept().getDeptCode());
      tmplIndexVo.setAppDeptName(user.getDept().getDeptName());
      tmplIndexVo.setAppTime(DateUtils.getNowDate());
      tmplIndexVo.setAltPerCode(user.getUserName());
      tmplIndexVo.setAltPerName(user.getNickName());
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("base64", tmplIndexVo.getBase64Str());
      jsonObject.put("xmlStr", tmplIndexVo.getXmlStr());
      String tempFile = jsonObject.toJSONString();
      tmplIndexVo.setTempEditFile(tempFile);
      List<SysUser> sysUserList = this.sysUserService.selectUserLiseByRoleNo();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = user.getDept();
      if (sysUserList != null && sysUserList.size() > 0) {
         List<String> sysUsers = (List)sysUserList.stream().filter((t) -> StringUtils.isNotBlank(t.getUserName())).map((t) -> t.getUserName()).collect(Collectors.toList());
         if (sysUsers.contains(sysUser.getUserName())) {
            tmplIndexVo.setTempEditState("4");
            tmplIndexVo.setTempState("4");
            tmplIndexVo.setTempFile(tempFile);
         } else {
            tmplIndexVo.setTempEditState("3");
         }
      } else {
         tmplIndexVo.setTempEditState("3");
      }

      this.addTmplElem(tempIndexSaveElemVo, tmplIndexVo.getId(), tmplIndexVo.getTempName(), tmplIndexVo.getTempType());
      this.tmplIndexMapper.updateTmplIndex(tmplIndexVo);
      if (sysUserList != null && sysUserList.size() > 0) {
         List<String> sysUsers = (List)sysUserList.stream().filter((t) -> StringUtils.isNotBlank(t.getUserName())).map((t) -> t.getUserName()).collect(Collectors.toList());
         if (sysUsers.contains(sysUser.getUserName())) {
            TmplIndex tmplIndex = this.tmplIndexMapper.selectTmplIndexById(tmplIndexVo.getId());
            if (tmplIndex != null) {
               tmplIndexVo.setTempFile(tmplIndex.getTempEditFile());
               TmplAuditRecord tmplAuditRecord = new TmplAuditRecord();
               tmplAuditRecord.setId(SnowIdUtils.uniqueLong());
               tmplAuditRecord.setTmplId(tmplIndex.getId());
               tmplAuditRecord.setAppDeptCd(tmplIndex.getAppDeptCd());
               tmplAuditRecord.setAppDeptName(tmplIndex.getAppDeptName());
               tmplAuditRecord.setAppDocCd(tmplIndex.getAppDocCd());
               tmplAuditRecord.setAppDocName(tmplIndex.getAppDocName());
               tmplAuditRecord.setAppTime(tmplIndex.getAppTime());
               tmplAuditRecord.setConDeptCd(sysDept.getDeptCode());
               tmplAuditRecord.setConDeptName(sysDept.getDeptName());
               tmplAuditRecord.setConDocCd(user.getUserName());
               tmplAuditRecord.setConDocName(user.getNickName());
               tmplAuditRecord.setAuditResult(Long.parseLong(tmplIndexVo.getTempEditState()));
               tmplAuditRecord.setConDate(tmplIndex.getConDate() != null ? tmplIndex.getConDate() : new Date());
               tmplAuditRecord.setConView(tmplIndexVo.getConView());
               tmplAuditRecord.setCrePerCode(tmplIndex.getCrePerCode());
               tmplAuditRecord.setCrePerName(tmplIndex.getCrePerName());
               tmplAuditRecord.setCreDate(tmplIndex.getCreDate());
               this.tmplAuditRecordService.insertTmplAuditRecord(tmplAuditRecord);
            }
         }
      }

      return tmplIndexVo;
   }

   public TmplIndexVo backoutTempStateSubmit(TmplIndexVo tmplIndexVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmplIndexVo.setAppDocCd(user.getUserName());
      tmplIndexVo.setAppDocName(user.getNickName());
      if (StringUtils.isNotNull(user.getDeptId())) {
         tmplIndexVo.setAppDeptCd(user.getDeptId().toString());
      }

      if (StringUtils.isNotNull(user.getDept())) {
         tmplIndexVo.setAppDeptName(user.getDept().getDeptName());
      }

      tmplIndexVo.setAppTime(DateUtils.getNowDate());
      tmplIndexVo.setAltPerCode(user.getUserName());
      tmplIndexVo.setAltPerName(user.getNickName());
      tmplIndexVo.setTempEditState("1");
      this.tmplIndexMapper.updateTmplIndex(tmplIndexVo);
      return tmplIndexVo;
   }

   public TmplIndexVo selectTmplIndexVoById(Long id) throws Exception {
      TmplIndexVo tmplIndexVo = this.tmplIndexMapper.selectTmplById(id);
      Boolean editFlag = this.addEditFlagNew(tmplIndexVo, (Boolean)null);
      tmplIndexVo.setEditFlag(editFlag);
      tmplIndexVo.setDeptList(this.getDeptList(id));
      ElemAttri elemAttri = new ElemAttri();
      elemAttri.setTempId(id);
      List<ElemAttri> elemAttriList = this.elemAttriService.selectElemAttriList(elemAttri);
      Map<String, Object> map = new HashMap();
      if (elemAttriList != null && elemAttriList.size() > 0) {
         for(ElemAttri param : elemAttriList) {
            if (param.getContElemName() != null) {
               map.put(param.getContElemName(), param.getElemAttri());
            }
         }

         tmplIndexVo.setElemsAttriMap(map);
      }

      return tmplIndexVo;
   }

   public TmplIndexVo selectTmplStandardIndexVoById(Long id) throws Exception {
      TmplIndexVo tmplIndexVo = this.tmplIndexMapper.selectTmplStandardById(id);
      Boolean editFlag = this.addEditFlagNew(tmplIndexVo, (Boolean)null);
      tmplIndexVo.setEditFlag(editFlag);
      ElemAttri elemAttri = new ElemAttri();
      elemAttri.setTempId(id);
      List<ElemAttri> elemAttriList = this.elemAttriService.selectElemAttriStandardList(elemAttri);
      Map<String, Object> map = new HashMap();
      if (elemAttriList != null && elemAttriList.size() > 0) {
         for(ElemAttri param : elemAttriList) {
            if (param.getContElemName() != null) {
               map.put(param.getContElemName(), param.getElemAttri());
            }
         }

         tmplIndexVo.setElemsAttriMap(map);
      }

      return tmplIndexVo;
   }

   public Boolean addEditFlag(TmplIndexVo tmplIndexVo) throws Exception {
      Boolean editFlag = this.addEditFlagNew(tmplIndexVo, (Boolean)null);
      tmplIndexVo.setEditFlag(editFlag);
      return editFlag;
   }

   public Boolean addEditFlag_old(TmplIndex tmplIndex) {
      Boolean editFlag = false;
      SysUser user = SecurityUtils.getLoginUser().getUser();
      if (SysUser.isAdmin(user.getUserId())) {
         editFlag = true;
      } else if (tmplIndex.getCrePerCode().equals(user.getUserName())) {
         if (!"200".equals(tmplIndex.getTempType()) && !"300".equals(tmplIndex.getTempType())) {
            if (tmplIndex.getTempEditState().equals("2") || tmplIndex.getTempEditState().equals("1")) {
               editFlag = true;
            }
         } else {
            editFlag = true;
         }
      }

      return editFlag;
   }

   public Boolean addEditFlagNew(TmplIndex tmplIndex, Boolean isAllFlag) throws Exception {
      Boolean editFlag = false;
      if (!tmplIndex.getTempEditState().equals("3") && !tmplIndex.getTempEditState().equals("4")) {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         if (SysUser.isAdmin(user.getUserName())) {
            editFlag = true;
         } else {
            isAllFlag = isAllFlag == null ? this.getIsAllFlag() : isAllFlag;
            if (isAllFlag) {
               if (!"200".equals(tmplIndex.getTempType()) && !"300".equals(tmplIndex.getTempType())) {
                  if (tmplIndex.getTempMajor().equals("1111")) {
                     editFlag = true;
                  }
               } else {
                  editFlag = true;
               }
            } else if (!"1111".equals(tmplIndex.getTempMajor())) {
               editFlag = true;
            }
         }

         return editFlag;
      } else {
         return editFlag;
      }
   }

   private boolean addEditFlagOnly(TmplIndex tmplIndex, Boolean isAllFlag) throws Exception {
      Boolean editFlag = false;
      SysUser user = SecurityUtils.getLoginUser().getUser();
      if (SysUser.isAdmin(user.getUserName())) {
         editFlag = true;
      } else {
         isAllFlag = isAllFlag == null ? this.getIsAllFlag() : isAllFlag;
         if (isAllFlag) {
            if (!"200".equals(tmplIndex.getTempType()) && !"300".equals(tmplIndex.getTempType())) {
               if (tmplIndex.getTempMajor().equals("1111")) {
                  editFlag = true;
               }
            } else {
               editFlag = true;
            }
         } else if (!"1111".equals(tmplIndex.getTempMajor())) {
            editFlag = true;
         }
      }

      return editFlag;
   }

   public TmplIndex selectTmplIndexByEmrId(Long emrId) {
      return this.tmplIndexMapper.selectTmplIndexByEmrId(emrId);
   }

   public List getDeptList(Long id) {
      TmplDept tmplDept = new TmplDept();
      tmplDept.setTempId(id);
      List<TmplDept> tmplDeptList = this.tmplDeptService.selectTmplDeptList(tmplDept);
      List<SysDept> sysDeptList = this.sysDeptMapper.selectDeptList(new SysDept());
      List<SysDept> sysOrgList = this.sysDeptMapper.selectOrgList();
      Map<String, List<SysDept>> sysDeptMap = (Map)sysDeptList.stream().collect(Collectors.groupingBy((t) -> t.getDeptCode()));
      Map<String, List<SysDept>> sysOrgMap = (Map)sysOrgList.stream().collect(Collectors.groupingBy((t) -> t.getDeptCode()));
      List<SysDept> deptList = new ArrayList(tmplDeptList.size());
      if (tmplDeptList != null && tmplDeptList.size() > 0) {
         for(TmplDept param : tmplDeptList) {
            if (param.getDeptCd().equals("000000")) {
               SysDept dept = new SysDept();
               dept.setDeptId(10000L);
               dept.setOrgCode("000000");
               dept.setDeptCode("000000");
               dept.setDeptName("全部科室");
               deptList.add(dept);
            }

            List<SysDept> tempList = (List)sysDeptMap.get(param.getDeptCd());
            List<SysDept> orgList = (List)sysOrgMap.get(param.getDeptCd());
            SysDept sysDept = tempList != null && !tempList.isEmpty() ? (SysDept)tempList.get(0) : (orgList != null && !orgList.isEmpty() ? (SysDept)orgList.get(0) : null);
            if (sysDept != null) {
               deptList.add(sysDept);
            }
         }
      }

      return deptList;
   }

   public StringBuffer getElemStrstore(TmplIndexVo tmplIndexVo, TempIndexSaveElemVo tempIndexSaveElemVo) throws Exception {
      StringBuffer sb = new StringBuffer("");
      SysElemStrstore sysElemStrstore = new SysElemStrstore();
      sysElemStrstore.setTempType(tmplIndexVo.getTempType());
      sysElemStrstore.setRequFlag("1");
      List<SysElemStrstore> list = this.sysElemStrstoreService.selectSysElemStrstoreList(sysElemStrstore);
      Map<Long, List<SysElemStrstore>> sysElemStrstoreMap = (Map)list.stream().collect(Collectors.groupingBy((t) -> t.getElemId()));
      if (list != null && list.size() > 0) {
         List<Long> idList = (List)list.stream().map(SysElemStrstore::getElemId).collect(Collectors.toList());
         List<ElemAttri> elemAttriList = tempIndexSaveElemVo.getElemAttriList();
         if (elemAttriList != null && elemAttriList.size() > 0) {
            List<Long> attriList = (List)elemAttriList.stream().map(ElemAttri::getElemId).collect(Collectors.toList());
            if (!attriList.containsAll(idList)) {
               idList.forEach((t) -> {
                  if (!attriList.contains(t)) {
                     SysElemStrstore temp = (SysElemStrstore)((List)sysElemStrstoreMap.get(t)).get(0);
                     sb.append("[" + temp.getElemName() + "]、");
                  }

               });
            }
         } else {
            for(SysElemStrstore param : list) {
               sb.append("[" + param.getElemName() + "]、");
            }
         }
      }

      return sb;
   }

   public void addTmplElem(TempIndexSaveElemVo tempIndexSaveElemVo, Long id, String tempName, String tempType) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<ElemAttri> elemAttriList = tempIndexSaveElemVo.getElemAttriList();
      List<ElemDate> elemDateList = tempIndexSaveElemVo.getElemDateList();
      List<ElemGender> elemGenderList = tempIndexSaveElemVo.getElemGenderList();
      List<ElemMacro> elemMacroList = tempIndexSaveElemVo.getElemMacroList();
      List<ElemPhysign> elemPhysignList = tempIndexSaveElemVo.getElemPhysignList();
      List<ElemSign> elemSignList = tempIndexSaveElemVo.getElemSignList();
      List<TmplQuoteElem> tmplQuoteElemList = this.getTmplQuoteElem(tempType, elemAttriList);
      this.elemDateService.deleteElemDateByTempId(id);
      this.elemAttriService.deleteElemAttriByTempId(id);
      this.elemSignService.deleteElemSignByTempId(id);
      this.elemPhysignService.deleteElemPhysignByTempId(id);
      this.elemMacroService.deleteElemMacroByTempId(id);
      this.elemGenderService.deleteElemGenderByTempId(id);
      this.tmplQuoteElemService.deleteTmplQuoteElemByTempId(id);
      if (elemAttriList != null && elemAttriList.size() > 0) {
         for(ElemAttri param : elemAttriList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setCrePerName(user.getNickName());
         }

         this.elemAttriService.insertElemAttriList(elemAttriList);
      }

      if (elemDateList != null && elemDateList.size() > 0) {
         for(ElemDate param : elemDateList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
         }

         this.elemDateService.insertElemDateList(elemDateList);
      }

      if (elemGenderList != null && elemGenderList.size() > 0) {
         for(ElemGender param : elemGenderList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
            param.setCrePerCode(user.getUserName());
         }

         this.elemGenderService.insertElemGenderList(elemGenderList);
      }

      if (elemMacroList != null && elemMacroList.size() > 0) {
         for(ElemMacro param : elemMacroList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
         }

         this.elemMacroService.insertElemMacroList(elemMacroList);
      }

      if (elemPhysignList != null && elemPhysignList.size() > 0) {
         for(ElemPhysign param : elemPhysignList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
         }

         this.elemPhysignService.insertElemPhysignList(elemPhysignList);
      }

      if (elemSignList != null && elemSignList.size() > 0) {
         for(ElemSign param : elemSignList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerCode(user.getUserName());
            param.setCrePerName(user.getNickName());
         }

         this.elemSignService.insertElemSignList(elemSignList);
      }

      if (tmplQuoteElemList != null && tmplQuoteElemList.size() > 0) {
         for(TmplQuoteElem param : tmplQuoteElemList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
         }

         this.tmplQuoteElemService.insertTmplQuoteElemList(tmplQuoteElemList);
      }

   }

   public void addTmplElemStandard(TempIndexSaveElemVo tempIndexSaveElemVo, Long id, String tempName, String tempType) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<ElemAttri> elemAttriList = tempIndexSaveElemVo.getElemAttriList();
      List<ElemDate> elemDateList = tempIndexSaveElemVo.getElemDateList();
      List<ElemGender> elemGenderList = tempIndexSaveElemVo.getElemGenderList();
      List<ElemMacro> elemMacroList = tempIndexSaveElemVo.getElemMacroList();
      List<ElemPhysign> elemPhysignList = tempIndexSaveElemVo.getElemPhysignList();
      List<ElemSign> elemSignList = tempIndexSaveElemVo.getElemSignList();
      List<TmplQuoteElem> tmplQuoteElemList = this.getTmplQuoteElemStandard(tempType, elemAttriList);
      this.elemDateService.deleteElemDateStandardByTempId(id);
      this.elemAttriService.deleteElemAttriStandardByTempId(id);
      this.elemSignService.deleteElemSignStandardByTempId(id);
      this.elemPhysignService.deleteElemPhysignStandardByTempId(id);
      this.elemMacroService.deleteElemMacroStandardByTempId(id);
      this.elemGenderService.deleteElemGenderStandardByTempId(id);
      this.tmplQuoteElemService.deleteTmplQuoteElemByTempId(id);
      if (elemAttriList != null && elemAttriList.size() > 0) {
         for(ElemAttri param : elemAttriList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setCrePerName(user.getNickName());
         }

         this.elemAttriService.insertElemAttriStandardList(elemAttriList);
      }

      if (elemDateList != null && elemDateList.size() > 0) {
         for(ElemDate param : elemDateList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
         }

         this.elemDateService.insertElemDateStandardList(elemDateList);
      }

      if (elemGenderList != null && elemGenderList.size() > 0) {
         for(ElemGender param : elemGenderList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
            param.setCrePerCode(user.getUserName());
         }

         this.elemGenderService.insertElemGenderStandardList(elemGenderList);
      }

      if (elemMacroList != null && elemMacroList.size() > 0) {
         for(ElemMacro param : elemMacroList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
         }

         this.elemMacroService.insertElemMacroStandardList(elemMacroList);
      }

      if (elemPhysignList != null && elemPhysignList.size() > 0) {
         for(ElemPhysign param : elemPhysignList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
         }

         this.elemPhysignService.insertElemPhysignStandardList(elemPhysignList);
      }

      if (elemSignList != null && elemSignList.size() > 0) {
         for(ElemSign param : elemSignList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerCode(user.getUserName());
            param.setCrePerName(user.getNickName());
         }

         this.elemSignService.insertElemSignStandardList(elemSignList);
      }

      if (tmplQuoteElemList != null && tmplQuoteElemList.size() > 0) {
         for(TmplQuoteElem param : tmplQuoteElemList) {
            param.setId(SnowIdUtils.uniqueLong());
            param.setTempId(id);
            param.setTempName(tempName);
            param.setTempType(tempType);
            param.setCrePerName(user.getNickName());
         }

         this.tmplQuoteElemService.insertTmplQuoteElemStandardList(tmplQuoteElemList);
      }

   }

   public List selectListByIds(List list) throws Exception {
      return this.tmplIndexMapper.selectListByIds(list);
   }

   public List getTmplQuoteElem(String tempType, List elemAttriList) throws Exception {
      List<TmplQuoteElem> tmplQuoteElems = new ArrayList();
      QuoteElem quoteElem = new QuoteElem();
      quoteElem.setTempType(tempType);
      List<QuoteElem> quoteElemList = this.quoteElemService.selectQuoteElemList(quoteElem);
      if (quoteElemList != null && quoteElemList.size() > 0) {
         List<Long> ids = (List)quoteElemList.stream().map((q) -> Long.parseLong(q.getElemId())).collect(Collectors.toList());
         if (elemAttriList != null && elemAttriList.size() > 0) {
            List<TmplQuoteElem> tmplQuoteElemList = XmlElementParseUtil.getQuoteElem(elemAttriList, ids);
            if (tmplQuoteElemList != null && !tmplQuoteElemList.isEmpty()) {
               tmplQuoteElems = tmplQuoteElemList;
            }
         }
      }

      return tmplQuoteElems;
   }

   public List getTmplQuoteElemStandard(String tempType, List elemAttriList) throws Exception {
      List<TmplQuoteElem> tmplQuoteElems = new ArrayList();
      QuoteElem quoteElem = new QuoteElem();
      quoteElem.setTempType(tempType);
      List<QuoteElem> quoteElemList = this.quoteElemService.selectQuoteElemList(quoteElem);
      if (quoteElemList != null && quoteElemList.size() > 0) {
         List<Long> ids = (List)quoteElemList.stream().map((q) -> Long.parseLong(q.getElemId())).collect(Collectors.toList());
         if (elemAttriList != null && elemAttriList.size() > 0) {
            List<TmplQuoteElem> tmplQuoteElemList = XmlElementParseUtil.getQuoteElem(elemAttriList, ids);
            if (tmplQuoteElemList != null && !tmplQuoteElemList.isEmpty()) {
               tmplQuoteElems = tmplQuoteElemList;
            }
         }
      }

      return tmplQuoteElems;
   }
}
