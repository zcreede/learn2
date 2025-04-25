package com.emr.project.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.framework.web.domain.TreeSelectElem;
import com.emr.project.qc.domain.vo.ElemExpressionVo;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysDictType;
import com.emr.project.system.domain.SysStaElem;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SetViewVo;
import com.emr.project.system.domain.vo.SysStaElemVo;
import com.emr.project.system.mapper.SysStaElemMapper;
import com.emr.project.system.service.ISysCustomSetService;
import com.emr.project.system.service.ISysDictTypeService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysStaElemService;
import com.emr.project.webEditor.util.ElemVoToElemUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysStaElemServiceImpl implements ISysStaElemService {
   @Autowired
   private SysStaElemMapper sysStaElemMapper;
   @Autowired
   private ISysDictTypeService dictTypeService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISysCustomSetService sysCustomSetService;

   public SysStaElem selectSysStaElemById(Long id) {
      return this.sysStaElemMapper.selectSysStaElemById(id);
   }

   public List selectSysStaElemList(SysStaElem sysStaElem) {
      return this.sysStaElemMapper.selectSysStaElemList(sysStaElem);
   }

   public List selectExpreElemList(SysStaElem sysStaElem) throws Exception {
      List<ElemExpressionVo> list = this.sysStaElemMapper.selectExpreElemList(sysStaElem);
      String operator = this.sysEmrConfigService.selectSysEmrConfigByKey("0017");
      Map<String, Object> map = (Map)JSON.parse(operator);

      for(ElemExpressionVo elemExpressionVo : list) {
         Object obj = map.get(elemExpressionVo.getContType());
         Map<String, Object> mapObj = new HashMap();
         if (obj != null) {
            mapObj = (Map)JSON.parse(obj.toString());
         }

         if (mapObj != null && mapObj.size() > 0) {
            elemExpressionVo.setCodeOperatorList(JSONArray.parseArray(mapObj.get("codeOperatorList").toString()));
            elemExpressionVo.setOperatorList(JSONArray.parseArray(mapObj.get("operatorList").toString()));
            elemExpressionVo.setOppCodeOperatorList(JSONArray.parseArray(mapObj.get("oppCodeOperatorList").toString()));
         } else {
            elemExpressionVo.setCodeOperatorList(new JSONArray());
            elemExpressionVo.setOperatorList(new JSONArray());
            elemExpressionVo.setOppCodeOperatorList(new JSONArray());
         }
      }

      return list;
   }

   public List selectQcElemList() {
      return this.sysStaElemMapper.selectQcElemList();
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertSysStaElem(SysStaElemVo sysStaElemVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysStaElem sysStaElem = this.getSysStaElem(sysStaElemVo);
      sysStaElem.setCrePerCode(sysUser.getUserName());
      sysStaElem.setCrePerName(sysUser.getNickName());
      this.sysStaElemMapper.insertSysStaElem(sysStaElem);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateSysStaElem(SysStaElemVo sysStaElemVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysStaElem sysStaElem = this.getSysStaElem(sysStaElemVo);
      sysStaElem.setAltPerCode(sysUser.getUserName());
      sysStaElem.setAltPerName(sysUser.getNickName());
      sysStaElem.setPlcHdrColor(sysStaElemVo.getPlcHdrColor());
      this.sysStaElemMapper.updateSysStaElem(sysStaElem);
   }

   public int changeValidFlagElem(SysStaElem sysStaElem) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      sysStaElem.setAltPerCode(sysUser.getUserName());
      sysStaElem.setAltPerName(sysUser.getNickName());
      return this.sysStaElemMapper.changeValidFlagElem(sysStaElem);
   }

   public int deleteSysStaElemByIds(Long[] ids) {
      return this.sysStaElemMapper.deleteSysStaElemByIds(ids);
   }

   public int deleteSysStaElemById(Long id) {
      return this.sysStaElemMapper.deleteSysStaElemById(id);
   }

   public List queryElemLibTree() throws Exception {
      List<TreeSelect> treeSelectList = new ArrayList(1);
      SysDictType sysDictType = this.dictTypeService.selectDictTypeByType("s001");
      if (sysDictType != null) {
         List<SysDictType> sysDictTypeList = new ArrayList(1);
         sysDictTypeList.add(sysDictType);
         List<SysDictData> sysDictDataList = this.dictTypeService.selectDictDataByType("s001");
         sysDictType.setChildren(sysDictDataList);
         treeSelectList = (List)sysDictTypeList.stream().map(TreeSelect::new).collect(Collectors.toList());
      }

      return treeSelectList;
   }

   public List getChilTreeList(String fatherId, List list) {
      List<TreeSelectElem> treeChilList = new ArrayList();

      for(SysStaElem sysStaElem : list) {
         if (fatherId.equals(sysStaElem.getClasId())) {
            TreeSelectElem tree = new TreeSelectElem(sysStaElem.getId().toString(), sysStaElem.getElemCd(), sysStaElem.getElemName());
            treeChilList.add(tree);
         }
      }

      return treeChilList;
   }

   public List selectElementTree(SysStaElem sysStaElem) throws Exception {
      List<TreeSelectElem> treeList = new ArrayList();
      List<SysStaElem> list = this.sysStaElemMapper.selectSysStaElemTreeList(sysStaElem);
      Map<String, List<SysStaElem>> mapList = (Map)list.stream().collect(Collectors.groupingBy(SysStaElem::getClasId));
      new ArrayList();

      for(String key : mapList.keySet()) {
         List treeChilList = this.getChilTreeList(key, (List)mapList.get(key));
         TreeSelectElem tree = new TreeSelectElem(((SysStaElem)((List)mapList.get(key)).get(0)).getId().toString(), key, ((SysStaElem)((List)mapList.get(key)).get(0)).getClasName(), treeChilList);
         treeList.add(tree);
      }

      return treeList;
   }

   public List selectElemLibraryList(SysStaElem sysStaElem) throws Exception {
      List<SysStaElemVo> sysStaElemVos = this.sysStaElemMapper.selectElemLibraryList(sysStaElem);
      return sysStaElemVos;
   }

   public SysStaElem generateUniqueCode(String str) throws Exception {
      SysStaElem sysStaElem = new SysStaElem();
      sysStaElem.setId(SnowIdUtils.uniqueLong());
      String code = "000000000";
      SysStaElem staElem = this.sysStaElemMapper.selectElemLibraryFirst();
      if (staElem != null) {
         try {
            Long codeLong = Long.parseLong(staElem.getElemCd().substring(2)) + 1L;
            DecimalFormat df = new DecimalFormat(code);
            code = str + df.format(codeLong);
         } catch (Exception var7) {
         }
      }

      sysStaElem.setElemCd(code);
      return sysStaElem;
   }

   public SysStaElemVo selectElemInfo(String id) throws Exception {
      return this.sysStaElemMapper.selectElemInfo(id);
   }

   public List selectElemSignList(SysStaElem sysStaElem) throws Exception {
      return this.sysStaElemMapper.selectElemSignList(sysStaElem);
   }

   public SysStaElem getSysStaElem(SysStaElemVo sysStaElemVo) throws Exception {
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      String quaJson = "";
      SysStaElem sysStaElem = new SysStaElem();
      List<?> dropDownDataList = this.getDropDownDataList(sysStaElemVo);
      if ("3".equals(sysStaElemVo.getTypeFlag())) {
         quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
      } else {
         switch (sysStaElemVo.getContType()) {
            case "1":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "2":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "3":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "4":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "5":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "6":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "9":
            case "10":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "7":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "8":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            case "11":
            case "12":
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
               break;
            default:
               quaJson = ElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList, editorType);
         }
      }

      sysStaElemVo.setId(sysStaElemVo.getElemId());
      sysStaElemVo.setElemQua(quaJson);
      BeanUtils.copyProperties(sysStaElemVo, sysStaElem);
      return sysStaElem;
   }

   public List getDropDownDataList(SysStaElemVo sysStaElemVo) {
      List<SetViewVo> dictList = null;
      if ("2".equals(sysStaElemVo.getListDataSource()) && sysStaElemVo.getSetName() != null) {
         try {
            dictList = this.sysStaElemMapper.selectSetViewList(sysStaElemVo.getSetName());
         } catch (Exception var4) {
            System.out.println("内部数据集表名可能不存在");
         }
      }

      return dictList;
   }

   public Map getSetViewVoList(String setName) throws Exception {
      Map<String, List<String>> map = new HashMap();
      List<String> CodeArray = new ArrayList();
      List<String> ValueArray = new ArrayList();

      for(SetViewVo setViewVo : this.sysStaElemMapper.selectSetViewList(setName)) {
         ValueArray.add(setViewVo.getCode());
         CodeArray.add(setViewVo.getName());
      }

      map.put("CodeArray", CodeArray);
      map.put("ValueArray", ValueArray);
      return map;
   }

   public List selectElemQueryList(SysStaElem sysStaElem, String type) throws Exception {
      List<ElemExpressionVo> list = this.sysStaElemMapper.selectElemQueryList(sysStaElem);
      String operator = this.sysEmrConfigService.selectSysEmrConfigByKey(type);
      Map<String, Object> map = (Map)JSON.parse(operator);

      for(ElemExpressionVo elemExpressionVo : list) {
         Object obj = map.get(elemExpressionVo.getContType());
         Map<String, Object> mapObj = new HashMap();
         if (obj != null) {
            mapObj = (Map)JSON.parse(obj.toString());
         }

         if (mapObj != null && mapObj.size() > 0) {
            elemExpressionVo.setCodeOperatorList(JSONArray.parseArray(mapObj.get("codeOperatorList").toString()));
            elemExpressionVo.setOperatorList(JSONArray.parseArray(mapObj.get("operatorList").toString()));
         } else {
            elemExpressionVo.setCodeOperatorList(new JSONArray());
            elemExpressionVo.setOperatorList(new JSONArray());
         }
      }

      return list;
   }

   public List selectTmplTypeRequElemList(String tempType) throws Exception {
      return this.sysStaElemMapper.selectTmplTypeRequElemList(tempType);
   }

   public void saveElemVo(SysStaElemVo sysStaElemVo) throws Exception {
      SysStaElem sysStaElem = this.sysStaElemMapper.selectSysStaElemById(sysStaElemVo.getElemId());
      if (sysStaElem == null) {
         this.insertSysStaElem(sysStaElemVo);
      } else {
         this.updateSysStaElem(sysStaElemVo);
      }

   }

   public void updateElem(SysStaElemVo sysStaElemVo) throws Exception {
      List<SysStaElem> list = this.sysStaElemMapper.updateElem(sysStaElemVo);
      if (list != null && !list.isEmpty()) {
         for(SysStaElem elemAttri : list) {
            String str = elemAttri.getElemQua();
            str = str.replace(sysStaElemVo.getOldStr(), sysStaElemVo.getNewStr());
            elemAttri.setElemQua(str);
            this.sysStaElemMapper.updateSysStaElem(elemAttri);
         }
      }

   }

   public List queryTertiaryList(String serarchValue) throws Exception {
      return this.sysStaElemMapper.queryTertiaryList(serarchValue);
   }
}
