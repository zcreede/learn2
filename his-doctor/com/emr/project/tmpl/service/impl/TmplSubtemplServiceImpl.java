package com.emr.project.tmpl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.TmplSubtempl;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.tmpl.domain.vo.TmplSubtemplVo;
import com.emr.project.tmpl.domain.vo.TreeVo;
import com.emr.project.tmpl.mapper.TmplSubtemplMapper;
import com.emr.project.tmpl.service.IElemAttriService;
import com.emr.project.tmpl.service.ITmplIndexService;
import com.emr.project.tmpl.service.ITmplSubtemplService;
import com.emr.project.webEditor.util.XmlElementParseUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TmplSubtemplServiceImpl implements ITmplSubtemplService {
   @Autowired
   private TmplSubtemplMapper tmplSubtemplMapper;
   @Autowired
   private IElemAttriService elemAttriService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITmplIndexService tmplIndexService;

   public TmplSubtemplVo selectTmplSubtemplById(Long id) {
      return this.tmplSubtemplMapper.selectTmplSubtemplById(id);
   }

   public List selectTmplSubtemplList(TmplSubtempl tmplSubtempl) {
      return this.tmplSubtemplMapper.selectTmplSubtemplList(tmplSubtempl);
   }

   public void insertTmplSubtempl(TmplSubtemplVo tmplSubtemplVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysOrg sysOrg = sysUser.getHospital();
      SysDept sysDept = sysUser.getDept();
      tmplSubtemplVo.setCrePerCode(sysUser.getUserName());
      tmplSubtemplVo.setCrePerName(sysUser.getNickName());
      tmplSubtemplVo.setValidFlag("1");
      tmplSubtemplVo.setId(SnowIdUtils.uniqueLong());
      tmplSubtemplVo.setOrgCd(sysOrg.getOrgCode());
      tmplSubtemplVo.setDeptCd(sysDept.getDeptCode());
      tmplSubtemplVo.setDeptName(sysDept.getDeptName());
      tmplSubtemplVo.setDocCd(sysUser.getUserName());
      tmplSubtemplVo.setDocName(sysUser.getNickName());
      this.tmplSubtemplMapper.insertTmplSubtempl(tmplSubtemplVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertTmplSubtemplCopySave(TmplSubtemplVo tmplSubtemplVo) throws Exception {
      this.insertTmplSubtempl(tmplSubtemplVo);
      this.updateTmplSubtemplSave(tmplSubtemplVo);
   }

   public void updateTmplSubtempl(TmplSubtemplVo tmplSubtemplVo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      tmplSubtemplVo.setAltPerCode(sysUser.getUserName());
      tmplSubtemplVo.setAltPerName(sysUser.getNickName());
      this.tmplSubtemplMapper.updateTmplSubtempl(tmplSubtemplVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateTmplSubtemplSave(TmplSubtemplVo tmplSubtemplVo) throws Exception {
      String xmlStr = tmplSubtemplVo.getXmlStr();
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
      Long id = tmplSubtemplVo.getId();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("base64", tmplSubtemplVo.getBase64());
      jsonObject.put("xmlStr", tmplSubtemplVo.getXmlStr());
      String tempFile = jsonObject.toJSONString();
      tmplSubtemplVo.setTempFile(tempFile);
      String tempName = tmplSubtemplVo.getTempName();
      String tempType = tmplSubtemplVo.getTempType();
      this.updateTmplSubtempl(tmplSubtemplVo);
      this.tmplIndexService.addTmplElem(tempIndexSaveElemVo, id, tempName, tempType);
   }

   public int deleteTmplSubtemplByIds(Long[] ids) {
      return this.tmplSubtemplMapper.deleteTmplSubtemplByIds(ids);
   }

   public int deleteTmplSubtemplById(Long id) {
      return this.tmplSubtemplMapper.deleteTmplSubtemplById(id);
   }

   public List selectTreeList(TmplSubtemplVo tmplSubtemplVo) {
      List<TmplSubtemplVo> tmplSubtemplVoList = this.tmplSubtemplMapper.selectTmplSubtemplVoList(tmplSubtemplVo);
      List<TreeVo> treeList = new ArrayList();
      Map<String, List<TmplSubtemplVo>> longListMap = (Map)tmplSubtemplVoList.stream().collect(Collectors.groupingBy(TmplSubtempl::getDisease));

      for(String key : longListMap.keySet()) {
         List<TmplSubtemplVo> disList = (List)longListMap.get(key);
         List<TreeVo> disTreeList = new ArrayList();
         Map<String, List<TmplSubtemplVo>> tempTypeMap = (Map)disList.stream().collect(Collectors.groupingBy(TmplSubtempl::getTempType));

         for(String type : tempTypeMap.keySet()) {
            List<TreeVo> tempTreeList = new ArrayList();

            for(TmplSubtemplVo subtemplVo : (List)tempTypeMap.get(type)) {
               TreeVo treeVo = new TreeVo();
               treeVo.setId(subtemplVo.getId());
               treeVo.setCode(subtemplVo.getId().toString());
               treeVo.setLabel(subtemplVo.getTempName());
               treeVo.setBtnstate(false);
               tempTreeList.add(treeVo);
            }

            TreeVo disTreeSelect = new TreeVo(type, ((TmplSubtemplVo)((List)tempTypeMap.get(type)).get(0)).getMrTypeName());
            disTreeSelect.setChildrenVo(tempTreeList);
            disTreeList.add(disTreeSelect);
         }

         TreeVo treeSelect = new TreeVo(key, ((TmplSubtemplVo)((List)longListMap.get(key)).get(0)).getDiseaseName());
         treeSelect.setChildrenVo(disTreeList);
         treeList.add(treeSelect);
      }

      return treeList;
   }

   public List selectTreeHelperList(TmplSubtemplVo tmplSubtemplVo) throws Exception {
      List<TreeVo> treeList = new ArrayList();
      TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(tmplSubtemplVo.getTempId());
      if (tmplIndex != null) {
         List<TmplSubtemplVo> tmplSubtemplVoList = this.tmplSubtemplMapper.selectTmplSubtemplVoList(tmplSubtemplVo);
         Map<String, List<TmplSubtemplVo>> longListMap = (Map)tmplSubtemplVoList.stream().collect(Collectors.groupingBy(TmplSubtempl::getDisease));

         for(String key : longListMap.keySet()) {
            List<TmplSubtemplVo> disList = (List)longListMap.get(key);
            List<TreeVo> tempTree = new ArrayList();

            for(TmplSubtemplVo vo : disList) {
               TreeVo treeSelect = new TreeVo(vo.getId(), vo.getElemId(), vo.getTempName());
               treeSelect.setBase64(vo.getTempFile());
               tempTree.add(treeSelect);
            }

            TreeVo treeSelect = new TreeVo(key, ((TmplSubtemplVo)disList.get(0)).getDiseaseName());
            treeSelect.setChildrenVo(tempTree);
            treeList.add(treeSelect);
         }
      }

      return treeList;
   }

   public TmplSubtemplVo selectTmplSubtemplVoById(Long id) {
      TmplSubtemplVo tmplSubtemplVo = this.tmplSubtemplMapper.selectTmplSubtemplById(id);
      ElemAttri elemAttri = new ElemAttri();
      elemAttri.setTempId(id);
      List<ElemAttri> elemAttriList = this.elemAttriService.selectElemAttriList(elemAttri);
      Map<String, Object> map = new HashMap();
      if (elemAttriList != null && elemAttriList.size() > 0) {
         for(ElemAttri param : elemAttriList) {
            map.put(param.getContElemName(), param.getElemAttri());
         }

         tmplSubtemplVo.setElemsAttriMap(map);
      }

      boolean flag = this.addEditFlag(tmplSubtemplVo.getId());
      tmplSubtemplVo.setEditFlag(flag);
      return tmplSubtemplVo;
   }

   public Boolean addEditFlag(Long tempId) {
      Boolean editFlag = true;
      SysUser user = SecurityUtils.getLoginUser().getUser();
      TmplSubtemplVo param = this.tmplSubtemplMapper.selectTmplSubtemplById(tempId);
      if (!param.getCrePerCode().equals(user.getUserName()) && !SysUser.isAdmin(user.getUserId())) {
         editFlag = false;
      }

      return editFlag;
   }
}
