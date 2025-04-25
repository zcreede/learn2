package com.emr.project.tmpl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.XmlElementParseConfigVo;
import com.emr.project.emr.mapper.IndexMapper;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.TmplElemLink;
import com.emr.project.tmpl.domain.TmplElemLinkElem;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import com.emr.project.tmpl.domain.vo.TmplElemLinkElemVo;
import com.emr.project.tmpl.domain.vo.TmplElemLinkVo;
import com.emr.project.tmpl.mapper.TmplElemLinkMapper;
import com.emr.project.tmpl.service.ITmplElemLinkElemService;
import com.emr.project.tmpl.service.ITmplElemLinkService;
import com.emr.project.webEditor.util.XmlElementParseUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TmplElemLinkServiceImpl implements ITmplElemLinkService {
   @Autowired
   private TmplElemLinkMapper tmplElemLinkMapper;
   @Autowired
   private ITmplElemLinkElemService tmplElemLinkElemService;
   @Autowired
   private IndexMapper indexMapper;
   @Autowired
   private IEmrBinaryService emrBinaryService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public TmplElemLink selectTmplElemLinkById(Long id) throws Exception {
      return this.tmplElemLinkMapper.selectTmplElemLinkById(id);
   }

   public List selectTmplElemLinkVoByTempId(Long tempId) throws Exception {
      List<TmplElemLinkVo> res = new ArrayList(1);
      TmplElemLink param = new TmplElemLink();
      param.setTempId(tempId);
      List<TmplElemLinkVo> list = this.tmplElemLinkMapper.selectTmplElemLinkVoList(param);
      if (CollectionUtils.isNotEmpty(list)) {
         List<Long> linkIdList = (List)list.stream().map((t) -> t.getId()).collect(Collectors.toList());
         List<TmplElemLinkElemVo> linkElemList = this.tmplElemLinkElemService.selectByLinkIdList(linkIdList);
         Map<Long, List<TmplElemLinkElemVo>> linkElemMap = (Map<Long, List<TmplElemLinkElemVo>>)(CollectionUtils.isNotEmpty(linkElemList) ? (Map)linkElemList.stream().collect(Collectors.groupingBy((t) -> t.getLinkId())) : new HashMap(1));

         for(TmplElemLinkVo link : list) {
            List<TmplElemLinkElemVo> elemList = (List)linkElemMap.get(link.getId());
            List<TmplElemLinkElemVo> elemListType1 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("1")).collect(Collectors.toList()) : null;
            List<TmplElemLinkElemVo> elemListType2 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("2")).collect(Collectors.toList()) : null;
            List<TmplElemLinkElemVo> elemListType3 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("3")).collect(Collectors.toList()) : null;
            List<TmplElemLinkElemVo> linkElemListTemp = new ArrayList(1);
            if (CollectionUtils.isNotEmpty(elemListType2)) {
               String conditionsContent = link.getConditionsContent();
               elemListType2.stream().forEach((t) -> t.setConditionsContent(conditionsContent));
            }

            if (CollectionUtils.isNotEmpty(elemListType3)) {
               String conditionsContent2 = link.getConditionsContent2();
               elemListType3.stream().forEach((t) -> t.setConditionsContent2(conditionsContent2));
            }

            link.setLinkElemType1List(elemListType1);
            link.setLinkElemType2List(elemListType2);
            link.setLinkElemType3List(elemListType3);
            if (CollectionUtils.isNotEmpty(elemListType1)) {
               linkElemListTemp.addAll(elemListType1);
            }

            if (CollectionUtils.isNotEmpty(elemListType2)) {
               linkElemListTemp.addAll(elemListType2);
            }

            if (CollectionUtils.isNotEmpty(elemListType3)) {
               linkElemListTemp.addAll(elemListType3);
            }

            if (CollectionUtils.isNotEmpty(linkElemListTemp)) {
               link.setLinkElemVoList(linkElemListTemp);
            }

            res.add(link);
         }
      }

      return res;
   }

   public List selectTmplElemLinkVoByPatientId(String patientId) throws Exception {
      List<TmplElemLinkVo> res = new ArrayList(1);
      List<Long> tempIdList = new ArrayList();
      IndexVo indexVo = new IndexVo();
      indexVo.setPatientId(patientId);
      List<IndexVo> indexVoList = this.indexMapper.selectAllIndexList(indexVo);
      if (CollectionUtils.isNotEmpty(indexVoList)) {
         tempIdList = (List)indexVoList.stream().filter((t) -> t.getTempId() != null).map((t) -> t.getTempId()).distinct().collect(Collectors.toList());
      }

      TmplElemLink param = new TmplElemLink();
      param.setTempIdList(tempIdList);
      List<TmplElemLinkVo> list = this.tmplElemLinkMapper.selectTmplElemLinkVoList(param);
      List<TmplElemLinkVo> allList = new ArrayList();
      List<String> contElemNameList = new ArrayList();

      for(TmplElemLinkVo tmplElemLinkVo : list) {
         if (!contElemNameList.contains(tmplElemLinkVo.getContElemName())) {
            contElemNameList.add(tmplElemLinkVo.getContElemName());
            allList.add(tmplElemLinkVo);
         }
      }

      if (CollectionUtils.isNotEmpty(allList)) {
         List<Long> linkIdList = (List)allList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         List<TmplElemLinkElemVo> linkElemList = this.tmplElemLinkElemService.selectByLinkIdList(linkIdList);
         Map<Long, List<TmplElemLinkElemVo>> linkElemMap = (Map<Long, List<TmplElemLinkElemVo>>)(CollectionUtils.isNotEmpty(linkElemList) ? (Map)linkElemList.stream().collect(Collectors.groupingBy((t) -> t.getLinkId())) : new HashMap(1));

         for(TmplElemLinkVo link : allList) {
            List<TmplElemLinkElemVo> elemList = (List)linkElemMap.get(link.getId());
            List<TmplElemLinkElemVo> elemListType1 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("1")).collect(Collectors.toList()) : null;
            List<TmplElemLinkElemVo> elemListType2 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("2")).collect(Collectors.toList()) : null;
            List<TmplElemLinkElemVo> elemListType3 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("3")).collect(Collectors.toList()) : null;
            List<TmplElemLinkElemVo> linkElemListTemp = new ArrayList(1);
            if (CollectionUtils.isNotEmpty(elemListType2)) {
               String conditionsContent = link.getConditionsContent();
               elemListType2.stream().forEach((t) -> t.setConditionsContent(conditionsContent));
            }

            if (CollectionUtils.isNotEmpty(elemListType3)) {
               String conditionsContent2 = link.getConditionsContent2();
               elemListType3.stream().forEach((t) -> t.setConditionsContent2(conditionsContent2));
            }

            link.setLinkElemType1List(elemListType1);
            link.setLinkElemType2List(elemListType2);
            link.setLinkElemType3List(elemListType3);
            if (CollectionUtils.isNotEmpty(elemListType1)) {
               linkElemListTemp.addAll(elemListType1);
            }

            if (CollectionUtils.isNotEmpty(elemListType2)) {
               linkElemListTemp.addAll(elemListType2);
            }

            if (CollectionUtils.isNotEmpty(elemListType3)) {
               linkElemListTemp.addAll(elemListType3);
            }

            if (CollectionUtils.isNotEmpty(linkElemListTemp)) {
               link.setLinkElemVoList(linkElemListTemp);
            }

            res.add(link);
         }
      }

      return res;
   }

   public List selectTmplElemLinkVoByXmlStr(List tmplElemLinkVoList, List elemAttris, IndexVo indexVo) throws Exception {
      if (indexVo != null && CollectionUtils.isNotEmpty(indexVo.getBase64StrList()) && CollectionUtils.isNotEmpty(indexVo.getMrFileIdList())) {
         for(String mrFileId : (List)indexVo.getMrFileIdList().stream().distinct().collect(Collectors.toList())) {
            IndexVo indexReq = new IndexVo();
            indexReq.setId(Long.valueOf(mrFileId));
            List<IndexVo> indexVoList = this.indexMapper.selectAllIndexList(indexReq);
            if (CollectionUtils.isNotEmpty(indexVoList)) {
               Long tempId = ((IndexVo)indexVoList.get(0)).getTempId();
               List<TmplElemLinkVo> tmplElemLinkVoResList = this.selectTmplElemLinkVoByTempId(tempId);
               if (CollectionUtils.isNotEmpty(tmplElemLinkVoResList)) {
                  new ArrayList();
                  List linkElemType2List = (List)tmplElemLinkVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getContElemName())).distinct().map((t) -> t.getContElemName()).collect(Collectors.toList());

                  for(TmplElemLinkVo tmplElemLinkVo : tmplElemLinkVoResList) {
                     if (!linkElemType2List.contains(tmplElemLinkVo.getContElemName())) {
                        tmplElemLinkVoList.add(tmplElemLinkVo);
                     }
                  }
               }
            }
         }
      }

      List<String> linkContentNameList = new ArrayList(1);
      Map<String, String> linkContentNameMap = new HashMap(1);
      if (CollectionUtils.isNotEmpty(tmplElemLinkVoList)) {
         for(TmplElemLinkVo tmplElemLinkVo : tmplElemLinkVoList) {
            linkContentNameList.add(tmplElemLinkVo.getContElemName());
            List<TmplElemLinkElemVo> linkElemList = new ArrayList(1);
            List<TmplElemLinkElemVo> linkElemType1List = tmplElemLinkVo.getLinkElemType1List();
            if (CollectionUtils.isNotEmpty(linkElemType1List)) {
               linkElemList.addAll(linkElemType1List);
               linkElemType1List.forEach((t) -> linkContentNameList.add(t.getContElemName()));
            }

            List<TmplElemLinkElemVo> linkElemType2List = tmplElemLinkVo.getLinkElemType2List();
            if (CollectionUtils.isNotEmpty(linkElemType2List)) {
               linkElemList.addAll(linkElemType2List);
               linkElemType2List.forEach((t) -> linkContentNameList.add(t.getContElemName()));
            }

            List<TmplElemLinkElemVo> linkElemType3List = tmplElemLinkVo.getLinkElemType3List();
            if (CollectionUtils.isNotEmpty(linkElemType3List)) {
               linkElemList.addAll(linkElemType3List);
               linkElemType3List.forEach((t) -> linkContentNameList.add(t.getContElemName()));
            }

            if (CollectionUtils.isNotEmpty(linkElemList)) {
               tmplElemLinkVo.setLinkElemVoList(linkElemList);
            }
         }

         for(ElemAttri elemAttri : elemAttris) {
            String elemAttriStr = elemAttri.getElemAttri();
            JSONObject elemAttriObj = JSON.parseObject(elemAttriStr);
            JSONArray elemArr = elemAttriObj.getJSONArray("elems");
            JSONObject elemAttriObj2 = elemArr.getJSONObject(0);
            JSONObject elemAttriElemObj = elemAttriObj2.getJSONObject("elem");
            String tmplContElemName = elemAttriElemObj.getString("tmplContElemName");
            if (CollectionUtils.isNotEmpty(linkContentNameList) && StringUtils.isNotBlank(tmplContElemName) && linkContentNameList.contains(tmplContElemName)) {
               linkContentNameMap.put(tmplContElemName, elemAttri.getContElemName());
            }
         }

         if (linkContentNameMap.size() > 0) {
            for(TmplElemLinkVo tmplElemLinkVo : tmplElemLinkVoList) {
               String xmlContElemName = (String)linkContentNameMap.get(tmplElemLinkVo.getContElemName());
               if (StringUtils.isNotBlank(xmlContElemName)) {
                  tmplElemLinkVo.setTmplContElemName(tmplElemLinkVo.getContElemName());
                  tmplElemLinkVo.setContElemName(xmlContElemName);
               }

               List<TmplElemLinkElemVo> linkElemList = new ArrayList(1);
               List<TmplElemLinkElemVo> linkElemType1List = tmplElemLinkVo.getLinkElemType1List();
               this.setXmlContElemName(linkElemType1List, linkContentNameMap);
               if (CollectionUtils.isNotEmpty(linkElemType1List)) {
                  linkElemList.addAll(linkElemType1List);
               }

               List<TmplElemLinkElemVo> linkElemType2List = tmplElemLinkVo.getLinkElemType2List();
               this.setXmlContElemName(linkElemType2List, linkContentNameMap);
               if (CollectionUtils.isNotEmpty(linkElemType2List)) {
                  linkElemList.addAll(linkElemType2List);
               }

               List<TmplElemLinkElemVo> linkElemType3List = tmplElemLinkVo.getLinkElemType3List();
               this.setXmlContElemName(linkElemType3List, linkContentNameMap);
               if (CollectionUtils.isNotEmpty(linkElemType3List)) {
                  linkElemList.addAll(linkElemType3List);
               }

               if (CollectionUtils.isNotEmpty(linkElemList)) {
                  tmplElemLinkVo.setLinkElemVoList(linkElemList);
               }
            }
         }
      }

      return tmplElemLinkVoList;
   }

   private void setXmlContElemName(List linkElemTypeList, Map linkContentNameMap) {
      if (CollectionUtils.isNotEmpty(linkElemTypeList)) {
         for(TmplElemLinkElemVo tmplElemLinkElemVo : linkElemTypeList) {
            String xmlContElemName = (String)linkContentNameMap.get(tmplElemLinkElemVo.getContElemName());
            if (StringUtils.isNotBlank(xmlContElemName)) {
               tmplElemLinkElemVo.setContElemName(xmlContElemName);
            }
         }
      }

   }

   public TmplElemLinkVo selectByTempIdElemId(Long tempId, String contName) throws Exception {
      TmplElemLink param = new TmplElemLink();
      param.setTempId(tempId);
      param.setContElemName(contName);
      List<TmplElemLink> list = this.tmplElemLinkMapper.selectTmplElemLinkList(param);
      TmplElemLinkVo res = null;
      if (CollectionUtils.isNotEmpty(list)) {
         res = new TmplElemLinkVo();
         TmplElemLink tmplElemLink = (TmplElemLink)list.get(0);
         BeanUtils.copyProperties(tmplElemLink, res);
         TmplElemLinkElem param2 = new TmplElemLinkElem();
         param2.setLinkId(tmplElemLink.getId());
         List<TmplElemLinkElemVo> elemList = this.tmplElemLinkElemService.selectTmplElemLinkElemList(param2);
         List<TmplElemLinkElemVo> elemListType1 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("1")).collect(Collectors.toList()) : null;
         List<TmplElemLinkElemVo> elemListType2 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("2")).collect(Collectors.toList()) : null;
         List<TmplElemLinkElemVo> elemListType3 = CollectionUtils.isNotEmpty(elemList) ? (List)elemList.stream().filter((t) -> t.getLinkType().equals("3")).collect(Collectors.toList()) : null;
         if (CollectionUtils.isNotEmpty(elemListType2)) {
            String conditionsContent = res.getConditionsContent();
            elemListType2.stream().forEach((t) -> t.setConditionsContent(conditionsContent));
         }

         if (CollectionUtils.isNotEmpty(elemListType3)) {
            String conditionsContent2 = res.getConditionsContent2();
            elemListType3.stream().forEach((t) -> t.setConditionsContent2(conditionsContent2));
         }

         res.setLinkElemType1List(elemListType1);
         res.setLinkElemType2List(elemListType2);
         res.setLinkElemType3List(elemListType3);
      }

      return res;
   }

   public List selectTmplElemLinkList(TmplElemLink tmplElemLink) throws Exception {
      return this.tmplElemLinkMapper.selectTmplElemLinkList(tmplElemLink);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertTmplElemLink(TmplElemLinkVo tmplElemLinkVo) throws Exception {
      List<TmplElemLinkElem> linkElemList = tmplElemLinkVo.getLinkElemList();
      if (tmplElemLinkVo.getId() != null) {
         tmplElemLinkVo.setAltPerCode(SecurityUtils.getLoginUser().getUser().getUserName());
         tmplElemLinkVo.setAltPerName(SecurityUtils.getLoginUser().getUser().getNickName());
         this.tmplElemLinkMapper.updateTmplElemLink(tmplElemLinkVo);
         if (CollectionUtils.isNotEmpty(linkElemList)) {
            this.tmplElemLinkElemService.deleteByLinkIdAndType(tmplElemLinkVo.getId(), tmplElemLinkVo.getLinkType());
            linkElemList.stream().forEach((t) -> {
               t.setLinkId(tmplElemLinkVo.getId());
               if (t.getId() == null) {
                  t.setId(SnowIdUtils.uniqueLong());
                  t.setCrePerCode(SecurityUtils.getLoginUser().getUser().getUserName());
                  t.setCrePerName(SecurityUtils.getLoginUser().getUser().getNickName());
               }

            });
            this.tmplElemLinkElemService.insertList(linkElemList);
         }
      } else {
         tmplElemLinkVo.setId(SnowIdUtils.uniqueLong());
         tmplElemLinkVo.setCrePerCode(SecurityUtils.getLoginUser().getUser().getUserName());
         tmplElemLinkVo.setCrePerName(SecurityUtils.getLoginUser().getUser().getNickName());
         this.tmplElemLinkMapper.insertTmplElemLink(tmplElemLinkVo);
         if (CollectionUtils.isNotEmpty(linkElemList)) {
            linkElemList.stream().forEach((t) -> {
               t.setId(SnowIdUtils.uniqueLong());
               t.setLinkId(tmplElemLinkVo.getId());
               t.setCrePerCode(SecurityUtils.getLoginUser().getUser().getUserName());
               t.setCrePerName(SecurityUtils.getLoginUser().getUser().getNickName());
            });
            this.tmplElemLinkElemService.insertList(linkElemList);
         }
      }

   }

   public int updateTmplElemLink(TmplElemLink tmplElemLink) throws Exception {
      return this.tmplElemLinkMapper.updateTmplElemLink(tmplElemLink);
   }

   public int deleteTmplElemLinkByIds(Long[] ids) throws Exception {
      return this.tmplElemLinkMapper.deleteTmplElemLinkByIds(ids);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int deleteTmplElemLinkById(Long id) throws Exception {
      this.tmplElemLinkElemService.deleteByLinkId(id);
      return this.tmplElemLinkMapper.deleteTmplElemLinkById(id);
   }

   public void copyToNewTmplIndex(Long sourceTmplId, Long targetTmplId, String targetTmplName) throws Exception {
      TmplElemLink param = new TmplElemLink();
      param.setTempId(sourceTmplId);
      List<TmplElemLink> list = sourceTmplId != null ? this.tmplElemLinkMapper.selectTmplElemLinkList(param) : null;
      if (CollectionUtils.isNotEmpty(list)) {
         List<Long> linkIdList = (List)list.stream().map((t) -> t.getId()).collect(Collectors.toList());
         List<TmplElemLinkElemVo> linkElemList = this.tmplElemLinkElemService.selectByLinkIdList(linkIdList);
         Map<Long, List<TmplElemLinkElem>> linkElemMap = (Map<Long, List<TmplElemLinkElem>>)(CollectionUtils.isNotEmpty(linkElemList) ? (Map)linkElemList.stream().collect(Collectors.groupingBy((t) -> t.getLinkId())) : new HashMap(1));
         List<TmplElemLink> targetList = new ArrayList(list.size());
         List<TmplElemLinkElem> targetElemList = new ArrayList(linkElemList.size());

         for(TmplElemLink link : list) {
            List<TmplElemLinkElem> linkElemListTemp = (List)linkElemMap.get(link.getId());
            link.setId(SnowIdUtils.uniqueLong());
            link.setTempId(targetTmplId);
            link.setTempName(targetTmplName);
            if (CollectionUtils.isNotEmpty(linkElemListTemp)) {
               linkElemListTemp.stream().forEach((t) -> {
                  t.setId(SnowIdUtils.uniqueLong());
                  t.setLinkId(link.getId());
                  t.setCrePerCode(SecurityUtils.getLoginUser().getUser().getUserName());
                  t.setCrePerName(SecurityUtils.getLoginUser().getUser().getNickName());
               });
               targetElemList.addAll(linkElemListTemp);
            }

            link.setCrePerCode(SecurityUtils.getLoginUser().getUser().getUserName());
            link.setCrePerName(SecurityUtils.getLoginUser().getUser().getNickName());
            targetList.add(link);
         }

         this.tmplElemLinkMapper.insertList(targetList);
         if (CollectionUtils.isNotEmpty(targetElemList)) {
            this.tmplElemLinkElemService.insertList(targetElemList);
         }
      }

   }

   public List getTmplElemLinkElemList(List base64IdList, List mrFileIdList) throws Exception {
      List<TmplElemLinkVo> tmplElemLinkList = new ArrayList();
      if (CollectionUtils.isNotEmpty(mrFileIdList)) {
         mrFileIdList = (List)mrFileIdList.stream().distinct().collect(Collectors.toList());

         for(int i = 0; i < base64IdList.size(); ++i) {
            for(String mrFileIdStr : mrFileIdList) {
               Long mrFileId = Long.valueOf(mrFileIdStr);
               String mrFileXmlStr = this.emrBinaryService.selectIndexXmlStrById(mrFileId);
               IndexVo indexVo = new IndexVo();
               indexVo.setId(mrFileId);
               List<IndexVo> indexVoList = this.indexMapper.selectAllIndexList(indexVo);
               XmlElementParseConfigVo configVo = this.getXmlElementParseConfigs();
               List<ElemAttriVo> elemAttriVoList = XmlElementParseUtil.getElemAttriVoListFromXmlNew(mrFileXmlStr, configVo, (String)base64IdList.get(i));
               if (CollectionUtils.isNotEmpty(elemAttriVoList) && CollectionUtils.isNotEmpty(indexVoList)) {
                  Long tempId = ((IndexVo)indexVoList.get(0)).getTempId();
                  List<TmplElemLinkVo> tmplElemLinkVoList = this.selectTmplElemLinkVoByTempId(tempId);
                  List<String> tmplElemLinkNameList = (List)tmplElemLinkVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getContElemName())).distinct().map((t) -> t.getContElemName()).collect(Collectors.toList());
                  if (CollectionUtils.isNotEmpty(tmplElemLinkVoList)) {
                     for(ElemAttriVo elemAttriVo : elemAttriVoList) {
                        TmplElemLinkVo tmplElemLinkVo = new TmplElemLinkVo();
                        if (tmplElemLinkNameList.contains(elemAttriVo.getTmplContElemName())) {
                           List<TmplElemLinkVo> tmplElemLinkList1 = (List)tmplElemLinkVoList.stream().filter((t) -> elemAttriVo.getTmplContElemName().equals(t.getContElemName())).collect(Collectors.toList());
                           if (CollectionUtils.isNotEmpty(tmplElemLinkList1)) {
                              BeanUtils.copyProperties(tmplElemLinkList1.get(0), tmplElemLinkVo);
                           }

                           tmplElemLinkList.add(tmplElemLinkVo);
                        }
                     }
                  }
               }
            }
         }
      }

      List<TmplElemLinkVo> tmplElemLinkListRes = (List)tmplElemLinkList.stream().filter((t) -> StringUtils.isNotBlank(t.getContElemName())).distinct().collect(Collectors.toList());
      return tmplElemLinkListRes;
   }

   public XmlElementParseConfigVo getXmlElementParseConfigs() throws Exception {
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      String replaceLineBreaks = this.sysEmrConfigService.selectSysEmrConfigByKey("0060");
      String lineBreaksElemIds = this.sysEmrConfigService.selectSysEmrConfigByKey("006001");
      String lineBreaksPosition = this.sysEmrConfigService.selectSysEmrConfigByKey("006002");
      XmlElementParseConfigVo configVo = new XmlElementParseConfigVo(editorType, replaceLineBreaks, lineBreaksElemIds, lineBreaksPosition);
      return configVo;
   }
}
