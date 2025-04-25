package com.emr.project.tmpl.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.XmlElementParseConfigVo;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.TmplElemLink;
import com.emr.project.tmpl.domain.TmplElemLinkElem;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.tmpl.domain.vo.TmplElemLinkVo;
import com.emr.project.tmpl.service.ITmplElemLinkElemService;
import com.emr.project.tmpl.service.ITmplElemLinkService;
import com.emr.project.webEditor.util.XmlElementParseUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/tmpl/link"})
public class TmplElemLinkController extends BaseController {
   @Autowired
   private ITmplElemLinkService tmplElemLinkService;
   @Autowired
   private ITmplElemLinkElemService tmplElemLinkElemService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IIndexService indexService;

   @PreAuthorize("@ss.hasPermi('tmpl:link:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmplElemLink tmplElemLink) {
      this.startPage();
      List<TmplElemLink> list = null;

      try {
         list = this.tmplElemLinkService.selectTmplElemLinkList(tmplElemLink);
      } catch (Exception e) {
         this.log.error("查询模板元素联动配置列表出现异常，", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:link:query')")
   @GetMapping({"/info"})
   public AjaxResult getInfo(Long tempId, String contElemName) {
      AjaxResult ajaxResult = AjaxResult.success("查询联动元素配置信息成功");
      boolean flag = true;

      try {
         if (tempId == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag && StringUtils.isBlank(contElemName)) {
            flag = false;
            ajaxResult = AjaxResult.error("元素contElemName不能为空");
         }

         if (flag) {
            TmplElemLinkVo tmplElemLinkVo = this.tmplElemLinkService.selectByTempIdElemId(tempId, contElemName);
            ajaxResult = AjaxResult.success("查询联动元素配置信息成功", tmplElemLinkVo);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询联动元素配置信息出现异常，请联系管理员");
         this.log.error("查询联动元素配置信息出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:link:add')")
   @Log(
      title = "模板元素联动配置主",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmplElemLinkVo tmplElemLinkVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (flag && tmplElemLinkVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplElemLinkVo.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag && StringUtils.isBlank(tmplElemLinkVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isBlank(tmplElemLinkVo.getContElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素ContName不能为空");
         }

         if (flag && StringUtils.isBlank(tmplElemLinkVo.getContElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素ContName不能为空");
         }

         if (flag && tmplElemLinkVo.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("元素ID不能为空");
         }

         if (flag && StringUtils.isBlank(tmplElemLinkVo.getElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素ElemCd不能为空");
         }

         if (flag && StringUtils.isBlank(tmplElemLinkVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素ElemName不能为空");
         }

         if (flag && StringUtils.isBlank(tmplElemLinkVo.getLinkType())) {
            flag = false;
            ajaxResult = AjaxResult.error("联动类型不能为空");
         }

         if (flag && StringUtils.isBlank(tmplElemLinkVo.getContType())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素类型不能为空");
         }

         if (flag && StringUtils.isBlank(tmplElemLinkVo.getTypeFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("控件类型不能为空");
         }

         if (flag && tmplElemLinkVo.getLinkType().equals("2") && StringUtils.isBlank(tmplElemLinkVo.getConditionsContent())) {
            flag = false;
            ajaxResult = AjaxResult.error("联动类型是显示元素时，元素内容不能为空");
         }

         if (flag && tmplElemLinkVo.getLinkType().equals("3") && StringUtils.isBlank(tmplElemLinkVo.getConditionsContent2())) {
            flag = false;
            ajaxResult = AjaxResult.error("联动类型是隐藏元素时，元素内容不能为空");
         }

         if (flag && CollectionUtils.isNotEmpty(tmplElemLinkVo.getLinkElemList())) {
            List<String> contElemNameList = new ArrayList();

            for(TmplElemLinkElem tmplElemLinkElem : tmplElemLinkVo.getLinkElemList()) {
               if (StringUtils.isBlank(tmplElemLinkElem.getContElemName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("联动元素的唯一标识不能为空");
                  break;
               }

               String contElemName = tmplElemLinkElem.getContElemName();
               if (contElemNameList.contains(contElemName)) {
                  flag = false;
                  ajaxResult = AjaxResult.error("联动元素不能有重复的元素");
                  break;
               }

               contElemNameList.add(contElemName);
               if (StringUtils.isBlank(tmplElemLinkElem.getElemCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("联动元素的ElemCd不能为空");
                  break;
               }

               if (StringUtils.isBlank(tmplElemLinkElem.getElemName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("联动元素的ElemName不能为空");
                  break;
               }

               if (tmplElemLinkElem.getElemId() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("联动元素的ElemId不能为空");
                  break;
               }

               if (StringUtils.isBlank(tmplElemLinkElem.getLinkType())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("联动元素的联动类型不能为空");
                  break;
               }

               if (flag && StringUtils.isBlank(tmplElemLinkVo.getContType())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("联动元素的元素类型不能为空");
               }

               if (flag && StringUtils.isBlank(tmplElemLinkVo.getTypeFlag())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("联动元素的控件类型不能为空");
               }
            }
         }

         if (flag) {
            this.tmplElemLinkService.insertTmplElemLink(tmplElemLinkVo);
         }
      } catch (Exception e) {
         this.log.error("新增模板联动元素配置出现异常，", e);
         ajaxResult = AjaxResult.error("新增模板联动元素配置出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:link:add')")
   @Log(
      title = "模板元素联动配置新增元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/addElem"})
   public AjaxResult addElem(@RequestBody TmplElemLinkElem tmplElemLinkElem) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (tmplElemLinkElem.getLinkId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("联动元素的主配置id不能为空");
         }

         TmplElemLink tmplElemLink = flag ? this.tmplElemLinkService.selectTmplElemLinkById(tmplElemLinkElem.getLinkId()) : null;
         if (flag && tmplElemLink == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有联动元素的主配置记录");
         }

         if (StringUtils.isBlank(tmplElemLinkElem.getContElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("联动元素的唯一标识不能为空");
         }

         if (StringUtils.isBlank(tmplElemLinkElem.getElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("联动元素的ElemCd不能为空");
         }

         if (StringUtils.isBlank(tmplElemLinkElem.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("联动元素的ElemName不能为空");
         }

         if (tmplElemLinkElem.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("联动元素的ElemId不能为空");
         }

         if (flag) {
            this.tmplElemLinkElemService.insertTmplElemLinkElem(tmplElemLinkElem);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("模板元素联动配置新增元素出现异常，请联系管理员");
         this.log.error("模板元素联动配置新增元素出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:link:remove')")
   @DeleteMapping({"/delElem/{id}"})
   public AjaxResult deleteElem(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除联动元素成功");

      try {
         TmplElemLinkElem tmplElemLinkElem = this.tmplElemLinkElemService.selectTmplElemLinkElemById(id);
         if (tmplElemLinkElem != null) {
            this.tmplElemLinkElemService.deleteTmplElemLinkElemById(id);
         } else {
            ajaxResult = AjaxResult.error("没有这个联动元素记录");
         }
      } catch (Exception e) {
         this.log.error("删除联动元素出现异常，", e);
         ajaxResult = AjaxResult.error("删除联动元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:link:edit')")
   @Log(
      title = "模板元素联动配置主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmplElemLink tmplElemLink) {
      AjaxResult ajaxResult = AjaxResult.success("修改模板联动元素配置成功");

      try {
         this.tmplElemLinkService.updateTmplElemLink(tmplElemLink);
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("修改模板联动元素配置出现异常");
         this.log.error("修改模板联动元素配置出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:link:remove')")
   @Log(
      title = "模板元素联动配置主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除联动元素配置成功");

      try {
         TmplElemLink tmplElemLink = this.tmplElemLinkService.selectTmplElemLinkById(id);
         if (tmplElemLink != null) {
            this.tmplElemLinkService.deleteTmplElemLinkById(id);
         } else {
            ajaxResult = AjaxResult.error("没有这个联动元素配置记录");
         }
      } catch (Exception e) {
         this.log.error("删除联动元素配置出现异常，", e);
         ajaxResult = AjaxResult.error("删除联动元素配置出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:link:indexLinks,emr:index:add,emr:index:save,emr:index:authUpdate,emrSubfile:index:add,emr:index:sign')")
   @Log(
      title = "模板元素联动配置主",
      businessType = BusinessType.DELETE
   )
   @PostMapping({"/indexLinks"})
   public AjaxResult indexLinks(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询联动元素配置成功");
      boolean flag = true;

      try {
         if (StringUtils.isBlank(indexVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("查询联动元素配置，病历xml不能为空");
         }

         String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
         String xmlStrFull = indexVo.getXmlStr();
         if (!StringUtils.isNotBlank(indexVo.getPatientId())) {
            if (indexVo.getTempId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("查询联动元素配置，模板id不能为空");
            }

            if (flag) {
               Long tempId = indexVo.getTempId();
               String xmlStr = indexVo.getXmlStr();
               List<TmplElemLinkVo> tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByTempId(tempId);
               TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXmlAll(xmlStr, editorType);
               if (tempIndexSaveElemVo != null) {
                  List<ElemAttri> elemAttriList = tempIndexSaveElemVo.getElemAttriList();
                  tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByXmlStr(tmplElemLinkVoList, elemAttriList, indexVo);
                  ajaxResult.put("tmplElemLinkVoList", tmplElemLinkVoList);
               }
            }
         } else {
            List<TmplElemLinkVo> tmplElemLinkVoResList = new ArrayList();
            List<String> contElemNameList = new ArrayList();
            List<TmplElemLinkVo> tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByPatientId(indexVo.getPatientId());
            TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXmlAll(xmlStrFull, editorType);
            if (tempIndexSaveElemVo != null) {
               List<ElemAttri> elemAttriList = tempIndexSaveElemVo.getElemAttriList();
               tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByXmlStr(tmplElemLinkVoList, elemAttriList, indexVo);
               if (CollectionUtils.isNotEmpty(tmplElemLinkVoList)) {
                  for(TmplElemLinkVo tmplElemLinkVo : tmplElemLinkVoList) {
                     if (!contElemNameList.contains(tmplElemLinkVo.getContElemName())) {
                        contElemNameList.add(tmplElemLinkVo.getContElemName());
                        tmplElemLinkVoResList.add(tmplElemLinkVo);
                     }
                  }
               }
            }

            ajaxResult.put("tmplElemLinkVoList", tmplElemLinkVoResList);
         }

         XmlElementParseConfigVo configVo = this.indexService.getXmlElementParseConfigs();
         List<ElemAttriVo> linkDataList = XmlElementParseUtil.getLinkDataListFromXml(xmlStrFull, configVo);
         if (CollectionUtils.isNotEmpty(linkDataList)) {
            ajaxResult.put("linkDataList", linkDataList);
         }
      } catch (Exception e) {
         this.log.error("查询联动元素配置出现异常", e);
         ajaxResult = AjaxResult.error("查询联动元素配置出现异常");
      }

      return ajaxResult;
   }
}
