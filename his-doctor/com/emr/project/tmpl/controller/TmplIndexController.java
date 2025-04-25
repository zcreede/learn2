package com.emr.project.tmpl.controller;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysStaElem;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmplMedicineDept;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysStaElemService;
import com.emr.project.system.service.ITmplMedicineDeptService;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.tmpl.domain.vo.TmplIndexSearchVo;
import com.emr.project.tmpl.domain.vo.TmplIndexVo;
import com.emr.project.tmpl.domain.vo.TreeVo;
import com.emr.project.tmpl.service.ITmplIndexService;
import com.emr.project.webEditor.util.XmlElementParseUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping({"/tmpl/index"})
public class TmplIndexController extends BaseController {
   @Autowired
   private ITmplIndexService tmplIndexService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISysStaElemService sysStaElemService;
   @Autowired
   private ITmplMedicineDeptService tmplMedicineDeptService;

   @PreAuthorize("@ss.hasPermi('tmpl:index:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmplIndex tmplIndex) {
      this.startPage();
      List<TmplIndex> list = this.tmplIndexService.selectTmplIndexList(tmplIndex);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:query,tmpl:index:pageInfo')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmplIndexService.selectTmplIndexById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:add')")
   @PostMapping
   public AjaxResult add(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("创建成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getShowName())) {
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               flag = false;
               ajaxResult = AjaxResult.error("模板名称不能为空");
            } else {
               tmplIndexVo.setShowName(tmplIndexVo.getTempName());
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempSort())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类别不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专业代码不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种代码不能为空");
         }

         if (flag) {
            flag = this.verifyTempMajor(tmplIndexVo);
            if (!flag) {
               ajaxResult = AjaxResult.error("病历模板仅专科科室可以新建！");
            }
         }

         if (flag) {
            List<SysStaElem> elemList = new ArrayList();
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               elemList = this.sysStaElemService.selectTmplTypeRequElemList(tmplIndexVo.getTempType());
            }

            ajaxResult.put("elemList", elemList);
            this.tmplIndexService.insertTmplIndex(tmplIndexVo);
            ajaxResult.put("id", tmplIndexVo.getId());
            String configJson = this.sysEmrConfigService.selectSysEmrConfigByKey("0029");
            ajaxResult.put("margin", configJson);
         }

         return ajaxResult;
      } catch (Exception e) {
         this.log.error("新增电子病历模板出现异常", e);
         return AjaxResult.error("新增电子病历模板出现异常");
      }
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:addStandardTmpl')")
   @PostMapping({"/addStandard"})
   public AjaxResult addStandard(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("创建成功");
      boolean flag = true;
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();

      try {
         if (!"sa".equals(sysUser.getUserName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请使用sa账号新增标准模板");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getShowName())) {
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               flag = false;
               ajaxResult = AjaxResult.error("模板名称不能为空");
            } else {
               tmplIndexVo.setShowName(tmplIndexVo.getTempName());
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempSort())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类别不能为空");
         }

         if (flag) {
            List<SysStaElem> elemList = new ArrayList();
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               elemList = this.sysStaElemService.selectTmplTypeRequElemList(tmplIndexVo.getTempType());
            }

            ajaxResult.put("elemList", elemList);
            this.tmplIndexService.insertTmplStandardIndex(tmplIndexVo);
            ajaxResult.put("id", tmplIndexVo.getId());
            String configJson = this.sysEmrConfigService.selectSysEmrConfigByKey("0029");
            ajaxResult.put("margin", configJson);
         }

         return ajaxResult;
      } catch (Exception e) {
         this.log.error("新增电子病历模板出现异常", e);
         return AjaxResult.error("新增电子病历模板出现异常");
      }
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:edit')")
   @PutMapping
   public AjaxResult edit(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("编辑成功");
      boolean flag = true;

      try {
         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         TmplIndexVo tmplIndex = null;
         if (flag) {
            tmplIndex = this.tmplIndexService.selectTmplIndexVoById(tmplIndexVo.getId());
            if (tmplIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这条模板信息，不能修改");
            }
         }

         if (flag && !tmplIndex.getTempEditState().equals("1") && !tmplIndex.getTempEditState().equals("2") && !tmplIndex.getTempEditState().equals("5")) {
            flag = false;
            ajaxResult = AjaxResult.error("此模板已提交审核或审核通过，不能再修改");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               flag = false;
               ajaxResult = AjaxResult.error("模板类型不能为空");
            } else {
               tmplIndexVo.setShowName(tmplIndexVo.getTempName());
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempSort())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类别不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专业代码不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种代码不能为空");
         }

         if (flag) {
            flag = this.verifyTempMajor(tmplIndexVo);
            if (!flag) {
               ajaxResult = AjaxResult.error("病历模板仅专科科室可以修改！");
            }
         }

         if (flag && !this.tmplIndexService.addEditFlag(tmplIndex)) {
            flag = false;
            ajaxResult = AjaxResult.error("模板属性不能编辑");
         }

         if (flag) {
            this.tmplIndexService.updateTmplIndex(tmplIndexVo);
            ajaxResult = AjaxResult.success((Object)this.tmplIndexService.selectTmplIndexVoById(tmplIndexVo.getId()));
         }

         return ajaxResult;
      } catch (Exception e) {
         this.log.error("修改电子病历模板出现异常", e);
         return AjaxResult.error("编辑电子病历模板出现异常");
      }
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:editStandardTmpl')")
   @PutMapping({"/updateStandardTmpl"})
   public AjaxResult updateStandardTmpl(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("编辑成功");
      boolean flag = true;

      try {
         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         TmplIndexVo tmplIndex = null;
         if (flag) {
            tmplIndex = this.tmplIndexService.selectTmplStandardIndexVoById(tmplIndexVo.getId());
            if (tmplIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这条模板信息，不能修改");
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               flag = false;
               ajaxResult = AjaxResult.error("模板类型不能为空");
            } else {
               tmplIndexVo.setShowName(tmplIndexVo.getTempName());
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempSort())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类别不能为空");
         }

         if (flag && !this.tmplIndexService.addEditFlag(tmplIndex)) {
            flag = false;
            ajaxResult = AjaxResult.error("模板属性不能编辑");
         }

         if (flag) {
            this.tmplIndexService.updateTmplStandardIndex(tmplIndexVo);
            ajaxResult = AjaxResult.success((Object)this.tmplIndexService.selectTmplStandardIndexVoById(tmplIndexVo.getId()));
         }

         return ajaxResult;
      } catch (Exception e) {
         this.log.error("修改电子病历模板出现异常", e);
         return AjaxResult.error("编辑电子病历模板出现异常");
      }
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:remove')")
   @Log(
      title = "电子病历模板",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      boolean flag = true;

      try {
         TmplIndexVo param = this.tmplIndexService.selectTmplIndexVoById(id);
         if (param == null) {
            flag = false;
            ajaxResult = AjaxResult.error("未查询到模板信息");
         }

         if (flag) {
            flag = this.verifyTempMajor(param);
            if (!flag) {
               ajaxResult = AjaxResult.error("病历模板仅专科科室可以删除！");
            }
         }

         if (flag) {
            if ("4".equals(param.getTempEditState())) {
               flag = false;
               ajaxResult = AjaxResult.error("审核通过的模板不可删除");
            }

            if ("3".equals(param.getTempEditState())) {
               flag = false;
               ajaxResult = AjaxResult.error("提交审核的模板不可删除");
            }
         }

         if (flag) {
            this.tmplIndexService.deleteTmplIndexById(id);
         }
      } catch (Exception var6) {
         this.log.error("删除电子病历模板出现异常");
         ajaxResult = AjaxResult.error("删除电子病历模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:deleteStandardTmplById')")
   @Log(
      title = "电子病历模板",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/deleteTmplStandard/{id}"})
   public AjaxResult deleteTmplStandard(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      boolean flag = true;

      try {
         TmplIndexVo param = this.tmplIndexService.selectTmplStandardIndexVoById(id);
         if (param == null) {
            flag = false;
            ajaxResult = AjaxResult.error("未查询到模板信息");
         }

         if (flag && !"sa".equals(sysUser.getUserName())) {
            flag = false;
            ajaxResult = AjaxResult.error("仅sa账号可以删除标准模板！");
         }

         if (flag) {
            this.tmplIndexService.deleteTmplStandardIndexById(id);
         }
      } catch (Exception var6) {
         this.log.error("删除电子病历标准模板出现异常");
         ajaxResult = AjaxResult.error("删除电子病历标准模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:headerTreeList')")
   @GetMapping({"/headerTreeList"})
   public AjaxResult headerTreeList(TmplIndex tmplIndex) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         List<TreeSelect> treeSelectList = this.tmplIndexService.selectHeaderTreeList(tmplIndex);
         ajaxResult = AjaxResult.success((Object)treeSelectList);
      } catch (Exception e) {
         this.log.error("查询页眉页脚下拉树出现异常");
         ajaxResult = AjaxResult.error("查询页眉页脚下拉树出现异常", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:getTempIndexTree,tmpl:index:pageInfo')")
   @GetMapping({"/getTempIndexTree/{treeType}"})
   public AjaxResult getTempIndexTree(@PathVariable("treeType") int treeType, TmplIndexSearchVo tmplIndex) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isNotEmpty(tmplIndex.getTempMajor()) && tmplIndex.getTempMajor().equals("null")) {
            tmplIndex.setTempMajor((String)null);
         }

         if (treeType == 2) {
            List<TreeVo> treeSelectVoList = this.tmplIndexService.getTempIndexTrees(tmplIndex, treeType);
            ajaxResult = AjaxResult.success((Object)treeSelectVoList);
         } else if (treeType == 4) {
            List<TreeVo> treeSelectVoList = this.tmplIndexService.getTempStandardIndexTrees(tmplIndex);
            ajaxResult = AjaxResult.success((Object)treeSelectVoList);
         } else {
            List<TreeVo> treeSelectVoList = this.tmplIndexService.getTempIndexTrees(tmplIndex);
            ajaxResult = AjaxResult.success((Object)treeSelectVoList);
         }
      } catch (Exception e) {
         this.log.error("查询模板文档树出现异常", e);
         ajaxResult = AjaxResult.error("查询模板文档树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:auditRecord:queryList')")
   @GetMapping({"/getTempIndexTreeAudit"})
   public AjaxResult getTempIndexTreeAudit(TmplIndexSearchVo tmplIndex) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         int treeType = 1;
         if (tmplIndex != null && StringUtils.isNotEmpty(tmplIndex.getTempState())) {
            tmplIndex.setTempEditState(tmplIndex.getTempState());
            tmplIndex.setTempState((String)null);
         }

         List<TreeVo> treeSelectVoList = this.tmplIndexService.getTempIndexTrees(tmplIndex, treeType);
         ajaxResult = AjaxResult.success((Object)treeSelectVoList);
      } catch (Exception e) {
         this.log.error("查询模板文档树出现异常", e);
         ajaxResult = AjaxResult.error("查询模板文档树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:add,tmpl:index:tmplIndexTree')")
   @GetMapping({"/tmplIndexTree"})
   public AjaxResult tmplIndexTree(TmplIndexSearchVo tmplIndex) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && tmplIndex == null) {
            flag = false;
            ajaxResult = AjaxResult.error("查询参数不能为空");
         }

         if (flag && StringUtils.isBlank(tmplIndex.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("用户id不能为空");
         }

         VisitinfoVo visitinfoVo = null;
         if (flag) {
            visitinfoVo = this.visitinfoService.selectVisitinfoById(tmplIndex.getPatientId());
            if (visitinfoVo == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此患者的就诊信息");
            }
         }

         if (flag && StringUtils.isNotBlank(tmplIndex.getTempType()) && tmplIndex.getTempType().contains(",")) {
            String[] tempTypeArr = tmplIndex.getTempType().split(",");
            List<String> tempTypeList = Arrays.asList(tempTypeArr);
            tmplIndex.setTempTypeList(tempTypeList);
            tmplIndex.setTempType((String)null);
         }

         if (flag) {
            boolean isAdmin = SecurityUtils.currentUserIsAdmin();
            if (!isAdmin) {
               SysUser user = SecurityUtils.getLoginUser().getUser();
               List<String> useSexList = new ArrayList(2);
               useSexList.add("1");
               if (StringUtils.isNotBlank(visitinfoVo.getSexCd()) && (visitinfoVo.getSexCd().equals("1") || visitinfoVo.getSexCd().equals("2"))) {
                  String useSex = visitinfoVo.getSexCd().equals("1") ? "2" : "3";
                  useSexList.add(useSex);
               }

               tmplIndex.setUseSexList(useSexList);
               List<String> useAgeList = new ArrayList(2);
               useAgeList.add("1");
               Long ageY = visitinfoVo.getAgeY() == null ? 0L : visitinfoVo.getAgeY();
               boolean isChild = AgeUtil.isChild(ageY);
               String useAge = isChild ? "3" : "2";
               useAgeList.add(useAge);
               tmplIndex.setUseAgeList(useAgeList);
               tmplIndex.setTmplDept(user.getDept().getDeptCode());
               tmplIndex.setTmplOrg(user.getHospital().getOrgCode());
               tmplIndex.setCommonTempMajor("1111");
            }

            tmplIndex.setTempState("4");
            List<TreeVo> treeSelectList = this.tmplIndexService.getTempIndexTrees(tmplIndex, 1);
            ajaxResult = AjaxResult.success("查询成功", treeSelectList);
         }
      } catch (Exception e) {
         this.log.error("查询病历模板树出现异常：", e);
         ajaxResult = AjaxResult.error("查询病历模板树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:queryList,tmpl:index:searchList')")
   @GetMapping({"/queryList"})
   public TableDataInfo list(TmplIndexVo tmplIndexVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<TmplIndexVo> list = this.tmplIndexService.selectTmplIndexVoList(tmplIndexVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询病历模板管理页面列表异常", e);
         tableDataInfo = new TableDataInfo(500, "查询病历模板管理页面列表异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:editState')")
   @PutMapping({"/editState"})
   public AjaxResult editState(@RequestBody TmplIndex tmplIndex) {
      AjaxResult ajaxResult = AjaxResult.success("操作成功");

      try {
         if (tmplIndex.getId() != null && StringUtils.isNotEmpty(tmplIndex.getValidFlag())) {
            this.tmplIndexService.updateTmplIndexState(tmplIndex);
         } else {
            ajaxResult = AjaxResult.error("模板id和状态值不能为空");
         }
      } catch (Exception e) {
         this.log.error("修改电子病历模板出现异常", e);
         ajaxResult = AjaxResult.error("修改电子病历模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:queryList,tmpl:index:getTempIndexTree,tmpl:index:pageInfo')")
   @GetMapping({"/tempTypeList"})
   public AjaxResult tempTypeList(TmplIndex tmplIndex) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         List<TreeSelect> list = this.tmplIndexService.selectTempTypeList(tmplIndex);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception var4) {
         this.log.error("查询模板类型下拉列表出现异常");
         ajaxResult = AjaxResult.error("查询模板类型下拉列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:tempStateSave,tmpl:auditRecord:queryList')")
   @PutMapping({"/tempStateSave"})
   public AjaxResult tempStateSave(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempEditState())) {
            flag = false;
            ajaxResult = AjaxResult.error("审核状态参数不能为空");
         }

         if (flag && "2".equals(tmplIndexVo.getTempEditState()) && StringUtils.isEmpty(tmplIndexVo.getConView())) {
            flag = false;
            ajaxResult = AjaxResult.error("驳回意见不能为空");
         }

         if (flag && "4".equals(tmplIndexVo.getTempEditState()) && (StringUtils.isEmpty((Collection)tmplIndexVo.getDeptList()) || tmplIndexVo.getDeptList().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("适用科室不能为空");
         }

         if (flag) {
            this.tmplIndexService.updateTempStateSave(tmplIndexVo);
         }
      } catch (Exception e) {
         this.log.error("提交审核出现异常", e);
         ajaxResult = AjaxResult.error("提交审核出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:addElem')")
   @PostMapping({"/addElem"})
   public AjaxResult addElem(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存电子病历模板元素成功");
      boolean flag = true;

      try {
         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag && tmplIndexVo.getTempName() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && tmplIndexVo.getXmlStr() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容的xml字符串不能为空");
         }

         if (flag && tmplIndexVo.getBase64Str() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容的base64字符串不能为空");
         }

         if (flag && tmplIndexVo.getTempType() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板病历类型不能为空");
         }

         if (flag) {
            TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexNoFileById(tmplIndexVo.getId());
            if (!this.tmplIndexService.addEditFlagNew(tmplIndex, (Boolean)null)) {
               flag = false;
               ajaxResult = AjaxResult.error("不能编辑当前模板");
            }
         }

         if (flag) {
            this.tmplIndexService.insertTmplElem(tmplIndexVo);
         }
      } catch (Exception e) {
         this.log.error("保存电子病历模板元素出现异常", e);
         ajaxResult = AjaxResult.error("保存电子病历模板元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:addStandardElem')")
   @PostMapping({"/addElemStandard"})
   public AjaxResult addElemStandard(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存电子病历模板元素成功");
      boolean flag = true;
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();

      try {
         if (!"sa".equals(sysUser.getUserName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请使用sa账号保存标准模板");
         }

         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag && tmplIndexVo.getTempName() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && tmplIndexVo.getXmlStr() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容的xml字符串不能为空");
         }

         if (flag && tmplIndexVo.getBase64Str() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容的base64字符串不能为空");
         }

         if (flag && tmplIndexVo.getTempType() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板病历类型不能为空");
         }

         if (flag) {
            TmplIndex tmplIndex = this.tmplIndexService.selectTmplStandardIndexNoFileById(tmplIndexVo.getId());
            if (!this.tmplIndexService.addEditFlagNew(tmplIndex, (Boolean)null)) {
               flag = false;
               ajaxResult = AjaxResult.error("不能编辑当前模板");
            }
         }

         if (flag) {
            this.tmplIndexService.insertTmplStandardElem(tmplIndexVo);
         }
      } catch (Exception e) {
         this.log.error("保存电子病历模板元素出现异常", e);
         ajaxResult = AjaxResult.error("保存电子病历模板元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:addCopy,tmpl:index:addCopyAudit')")
   @PostMapping({"/addCopy"})
   public AjaxResult addCopy(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("另存为成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getShowName())) {
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               flag = false;
               ajaxResult = AjaxResult.error("模板显示名称不能为空");
            } else {
               tmplIndexVo.setShowName(tmplIndexVo.getTempName());
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempSort())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类别不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专业代码不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种代码不能为空");
         }

         if (flag) {
            flag = this.verifyTempMajor(tmplIndexVo);
            if (!flag) {
               ajaxResult = AjaxResult.error("病历模板仅专科科室可以编辑新建！");
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getBase64Str())) {
            flag = false;
            ajaxResult = AjaxResult.error("Base64编码不能为空");
         }

         if (flag) {
            this.tmplIndexService.copyTmplIndex(tmplIndexVo);
            ajaxResult.put("id", tmplIndexVo.getId());
         }

         return ajaxResult;
      } catch (Exception e) {
         this.log.error("另存为电子病历模板出现异常", e);
         return AjaxResult.error("另存为电子病历模板出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:CopyToStandardTmpl')")
   @PostMapping({"/copyToStandardTmpl"})
   public AjaxResult copyToStandardTmpl(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("另存为成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      boolean flag = true;

      try {
         if (!"sa".equals(sysUser.getUserName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请使用sa账号新增标准模板");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getShowName())) {
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               flag = false;
               ajaxResult = AjaxResult.error("模板显示名称不能为空");
            } else {
               tmplIndexVo.setShowName(tmplIndexVo.getTempName());
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempSort())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类别不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getBase64Str())) {
            flag = false;
            ajaxResult = AjaxResult.error("Base64编码不能为空");
         }

         if (flag) {
            List<SysStaElem> elemList = new ArrayList();
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               elemList = this.sysStaElemService.selectTmplTypeRequElemList(tmplIndexVo.getTempType());
            }

            ajaxResult.put("elemList", elemList);
            this.tmplIndexService.copyHosTmplToStandardIndex(tmplIndexVo);
            ajaxResult.put("id", tmplIndexVo.getId());
         }

         return ajaxResult;
      } catch (Exception e) {
         this.log.error("另存为电子病历模板出现异常", e);
         return AjaxResult.error("另存为电子病历模板出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:addCopy,tmpl:index:addCopyAudit')")
   @PostMapping({"/copyToHospitalTmpl"})
   public AjaxResult copyToHospitalTmpl(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("另存为成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getShowName())) {
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               flag = false;
               ajaxResult = AjaxResult.error("模板显示名称不能为空");
            } else {
               tmplIndexVo.setShowName(tmplIndexVo.getTempName());
            }
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempSort())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类别不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专业代码不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种代码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getBase64Str())) {
            flag = false;
            ajaxResult = AjaxResult.error("Base64编码不能为空");
         }

         if (flag) {
            List<SysStaElem> elemList = new ArrayList();
            if (!"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType())) {
               elemList = this.sysStaElemService.selectTmplTypeRequElemList(tmplIndexVo.getTempType());
            }

            ajaxResult.put("elemList", elemList);
            this.tmplIndexService.copyStaTmplToHosIndex(tmplIndexVo);
            ajaxResult.put("id", tmplIndexVo.getId());
         }

         return ajaxResult;
      } catch (Exception e) {
         this.log.error("另存为电子病历模板出现异常", e);
         return AjaxResult.error("另存为电子病历模板出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:tempStateSubmit,tmpl:index:tempSubmitAudit')")
   @PutMapping({"/tempStateSubmit"})
   public AjaxResult tempStateSubmit(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("提交审核成功");
      boolean flag = true;
      String str = new String("");

      try {
         if (flag && tmplIndexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         TmplIndex tmplIndex = null;
         if (flag) {
            tmplIndex = this.tmplIndexService.selectTmplIndexById(tmplIndexVo.getId());
            if (tmplIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此模板记录");
            }
         }

         Boolean editFlag = this.tmplIndexService.addEditFlagNew(tmplIndex, (Boolean)null);
         if (flag && !editFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("不能编辑这个模板，不能提交审核");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempEditState())) {
            flag = false;
            ajaxResult = AjaxResult.error("审核状态参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && ("200".equals(tmplIndexVo.getTempType()) || "300".equals(tmplIndexVo.getTempType()))) {
            flag = false;
            ajaxResult = AjaxResult.error("页眉/页脚不需要提交审核，保存后即可使用");
         }

         if (flag && "3".equals(tmplIndexVo.getTempState())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板已在审核中，不能重复申请");
         }

         if (flag && "4".equals(tmplIndexVo.getTempState())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板已通过审核，不能重复提交");
         }

         if (flag && !tmplIndex.getTempMajor().equals("1111") && StringUtils.isEmpty((Collection)tmplIndexVo.getDeptList())) {
            flag = false;
            ajaxResult = AjaxResult.error("适用科室不能为空");
         }

         if (flag && tmplIndexVo.getXmlStr() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容的xml字符串不能为空");
         }

         if (flag && tmplIndexVo.getBase64Str() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容的base64字符串不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板病历类型不能为空");
         }

         String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
         TempIndexSaveElemVo tempIndexSaveElemVo = flag ? XmlElementParseUtil.getSaveElemFromXml(tmplIndexVo.getXmlStr(), editorType) : null;
         str = flag ? this.tmplIndexService.judgeElemStrstore(tmplIndexVo, tempIndexSaveElemVo) : str;
         if (flag && StringUtils.isNotEmpty(str)) {
            flag = false;
            ajaxResult = AjaxResult.error("病历模板未包含必备元素:" + str + "请添加后再试");
         }

         if (flag) {
            tmplIndexVo = this.tmplIndexService.updateTempStateSubmit(tmplIndexVo, tempIndexSaveElemVo);
            ajaxResult = AjaxResult.success((Object)tmplIndexVo);
         }
      } catch (Exception e) {
         this.log.error("提交审核出现异常", e);
         ajaxResult = AjaxResult.error("提交审核出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:tempStateSubmit,tmpl:index:tempSubmitAudit')")
   @PutMapping({"/backoutTempStateSubmit"})
   public AjaxResult backoutTempStateSubmit(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("撤销成功");
      boolean flag = true;

      try {
         if (flag && tmplIndexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         TmplIndex tmplIndex = null;
         if (flag) {
            tmplIndex = this.tmplIndexService.selectTmplIndexById(tmplIndexVo.getId());
            if (tmplIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此模板记录");
            }

            if (!"3".equals(tmplIndex.getTempEditState())) {
               flag = false;
               ajaxResult = AjaxResult.error("只有提交审核中的模板，才能进行撤销操作");
            }
         }

         if (flag) {
            tmplIndexVo = this.tmplIndexService.backoutTempStateSubmit(tmplIndexVo);
            ajaxResult = AjaxResult.success((Object)tmplIndexVo);
         }
      } catch (Exception e) {
         this.log.error("撤销提交审核出现异常", e);
         ajaxResult = AjaxResult.error("撤销提交审核出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:query,tmpl:index:pageInfo,tmpl:index:tempStateSave,tmpl:auditRecord:pageInfo,tmpl:auditRecord:queryList')")
   @GetMapping({"getDetailInfo/{id}"})
   public AjaxResult getDetailInfo(@PathVariable("id") Long id) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         TmplIndexVo tmplIndexVo = this.tmplIndexService.selectTmplIndexVoById(id);
         ajaxResult = AjaxResult.success((Object)tmplIndexVo);
         ajaxResult.put("data", tmplIndexVo);
         ajaxResult.put("currDept", SecurityUtils.getLoginUser().getUser().getDept());
      } catch (Exception e) {
         this.log.error("获取电子病历模板信息出现异常", e);
         ajaxResult = AjaxResult.error("获取电子病历模板信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:query,tmpl:index:pageInfo,tmpl:auditRecord:queryList')")
   @GetMapping({"/getStandardTmplDetailInfo/{id}"})
   public AjaxResult getStandardTmplDetailInfo(@PathVariable("id") Long id) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         TmplIndexVo tmplIndexVo = this.tmplIndexService.selectTmplStandardIndexVoById(id);
         tmplIndexVo.setStandardTmpl("1");
         ajaxResult = AjaxResult.success((Object)tmplIndexVo);
         ajaxResult.put("data", tmplIndexVo);
         ajaxResult.put("currDept", SecurityUtils.getLoginUser().getUser().getDept());
      } catch (Exception e) {
         this.log.error("获取电子病历模板信息出现异常", e);
         ajaxResult = AjaxResult.error("获取电子病历模板信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:addElemAudit')")
   @PostMapping({"/addElemAudit"})
   public AjaxResult addElemAudit(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存电子病历模板元素成功");
      boolean flag = true;

      try {
         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag && tmplIndexVo.getXmlStr() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容的xml字符串不能为空");
         }

         if (flag && tmplIndexVo.getBase64Str() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板内容的base64字符串不能为空");
         }

         if (flag && tmplIndexVo.getTempType() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板病历类型不能为空");
         }

         if (flag) {
            this.tmplIndexService.insertTmplElem(tmplIndexVo);
            ajaxResult.put("id", tmplIndexVo.getId());
         }
      } catch (Exception e) {
         this.log.error("保存电子病历模板元素出现异常", e);
         ajaxResult = AjaxResult.error("保存电子病历模板元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:index:editElemAudit')")
   @PutMapping({"/editElemAudit"})
   public AjaxResult editElemAudit(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("编辑成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isNotNull(tmplIndexVo)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplIndexVo.getTempSort())) {
            flag = false;
            ajaxResult = AjaxResult.error("模板类别不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专业代码不能为空");
         }

         if (flag && !"200".equals(tmplIndexVo.getTempType()) && !"300".equals(tmplIndexVo.getTempType()) && StringUtils.isEmpty(tmplIndexVo.getTempDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种代码不能为空");
         }

         if (flag && StringUtils.isEmpty((Collection)tmplIndexVo.getDeptList())) {
            flag = false;
            ajaxResult = AjaxResult.error("适用科室不能为空");
         }

         if (flag) {
            ajaxResult = AjaxResult.success((Object)this.tmplIndexService.updateTmplIndex(tmplIndexVo));
         }

         return ajaxResult;
      } catch (Exception e) {
         this.log.error("修改电子病历模板出现异常", e);
         return AjaxResult.error("编辑电子病历模板出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:isTmplEdit,tmpl:index:edit')")
   @PutMapping({"/isTmplEdit"})
   public AjaxResult isTmplEdit(@RequestBody TmplIndexVo tmplIndexVo) {
      AjaxResult ajaxResult = AjaxResult.success("可以编辑");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      boolean flag = true;

      try {
         if (tmplIndexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplIndexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         TmplIndexVo param = this.tmplIndexService.selectTmplIndexVoById(tmplIndexVo.getId());
         if (flag && param == null) {
            flag = false;
            ajaxResult = AjaxResult.error("未查询到当前模板信息");
         }

         if (flag) {
            flag = this.verifyTempMajor(param);
            if (!flag) {
               ajaxResult = AjaxResult.error("病历模板仅专科科室可以编辑！您可以另存为该模板对另存为的模板进行编辑");
            }
         }

         if (flag) {
            if ("4".equals(param.getTempEditState()) || "3".equals(param.getTempEditState())) {
               this.tmplIndexService.updateTmplEditState(param);
               param.setTempEditState("1");
            }

            ajaxResult.put("tmplIndex", param);
         }
      } catch (Exception e) {
         this.log.error("模板编辑判断出现异常", e);
         ajaxResult = AjaxResult.error("模板编辑判断出现异常");
      }

      return ajaxResult;
   }

   private Boolean verifyTempMajor(TmplIndexVo tmplIndexVo) throws Exception {
      boolean flag = true;
      if (flag) {
         boolean isAllFlag = this.tmplIndexService.getIsAllFlag();
         if (!isAllFlag) {
            TmplMedicineDept medicineDept = new TmplMedicineDept();
            medicineDept.setMedicineId(tmplIndexVo.getTempMajorId());
            medicineDept.setMedicineCode(tmplIndexVo.getTempMajor());
            List<TmplMedicineDept> medicineDeptList = this.tmplMedicineDeptService.selectTmplMedicineDeptList(medicineDept);
            List<String> deptCodeList = (List<String>)(CollectionUtils.isNotEmpty(medicineDeptList) ? (List)medicineDeptList.stream().map((t) -> t.getDeptCd()).collect(Collectors.toList()) : new ArrayList(1));
            String userDeptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
            if (!deptCodeList.contains(userDeptCode)) {
               flag = false;
            }
         }
      }

      return flag;
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSet:main:list')")
   @GetMapping({"/setEmrTree"})
   public AjaxResult setEmrTree(TmplIndexSearchVo tmplIndex) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (tmplIndex == null) {
            tmplIndex = new TmplIndexSearchVo();
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         tmplIndex.setTmplDept(user.getDept().getDeptCode());
         tmplIndex.setTmplOrg(user.getHospital().getOrgCode());
         tmplIndex.setCommonTempMajor("1111");
         tmplIndex.setTempState("4");
         List<TreeVo> treeSelectList = this.tmplIndexService.getTempIndexTrees(tmplIndex, 1);
         ajaxResult = AjaxResult.success("查询成功", treeSelectList);
      } catch (Exception e) {
         this.log.error("查询病历模板树出现异常：", e);
         ajaxResult = AjaxResult.error("查询病历模板树出现异常");
      }

      return ajaxResult;
   }
}
