package com.emr.project.tmpl.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.TmplSubtempl;
import com.emr.project.tmpl.domain.vo.TmplSubtemplVo;
import com.emr.project.tmpl.domain.vo.TreeVo;
import com.emr.project.tmpl.service.ITmplSubtemplService;
import java.util.List;
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
@RequestMapping({"/tmpl/subtempl"})
public class TmplSubtemplController extends BaseController {
   @Autowired
   private ITmplSubtemplService tmplSubtemplService;

   @PreAuthorize("@ss.hasPermi('tmpl:subtempl:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmplSubtempl tmplSubtempl) {
      this.startPage();
      List<TmplSubtempl> list = this.tmplSubtemplService.selectTmplSubtemplList(tmplSubtempl);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:subtempl:export')")
   @Log(
      title = "电子病历子模板",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmplSubtempl tmplSubtempl) {
      List<TmplSubtempl> list = this.tmplSubtemplService.selectTmplSubtemplList(tmplSubtempl);
      ExcelUtil<TmplSubtempl> util = new ExcelUtil(TmplSubtempl.class);
      return util.exportExcel(list, "电子病历子模板数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:subtempl:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmplSubtemplService.selectTmplSubtemplById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:subtempl:add')")
   @Log(
      title = "电子病历子模板",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmplSubtemplVo tmplSubtemplVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (tmplSubtemplVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplSubtemplVo.getTmepFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专科id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getElemId())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型名称不能为空");
         }

         if (flag && tmplSubtemplVo.getSerialNo() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("序号不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag) {
            this.tmplSubtemplService.insertTmplSubtempl(tmplSubtemplVo);
            ajaxResult.put("id", tmplSubtemplVo.getId());
         }
      } catch (Exception e) {
         this.log.error("新增子模板出现异常", e);
         ajaxResult = AjaxResult.error("新增子模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:subtempl:edit')")
   @Log(
      title = "电子病历子模板",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/edit"})
   public AjaxResult edit(@RequestBody TmplSubtemplVo tmplSubtemplVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (tmplSubtemplVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplSubtemplVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板id不能为空");
         }

         if (flag && tmplSubtemplVo.getTmepFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专科id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getElemId())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型名称不能为空");
         }

         if (flag && tmplSubtemplVo.getSerialNo() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("序号不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag) {
            this.tmplSubtemplService.updateTmplSubtempl(tmplSubtemplVo);
            ajaxResult = AjaxResult.success((Object)this.tmplSubtemplService.selectTmplSubtemplVoById(tmplSubtemplVo.getId()));
         }
      } catch (Exception e) {
         this.log.error("修改子模板出现异常", e);
         ajaxResult = AjaxResult.error("修改子模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:subtempl:save')")
   @Log(
      title = "电子病历子模板",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult save(@RequestBody TmplSubtemplVo tmplSubtemplVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmplSubtemplVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplSubtemplVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getBase64())) {
            flag = false;
            ajaxResult = AjaxResult.error("base64编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("xml字符串不能为空");
         }

         if (flag) {
            this.tmplSubtemplService.updateTmplSubtemplSave(tmplSubtemplVo);
         }
      } catch (Exception e) {
         this.log.error("保存电子病历子模板出现异常", e);
         ajaxResult = AjaxResult.error("保存电子病历子模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:subtempl:copySave')")
   @Log(
      title = "电子病历子模板",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/copySave"})
   public AjaxResult copySave(@RequestBody TmplSubtemplVo tmplSubtemplVo) {
      AjaxResult ajaxResult = AjaxResult.success("另存为成功");
      boolean flag = true;

      try {
         if (tmplSubtemplVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplSubtemplVo.getTmepFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专科id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getElemId())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型名称不能为空");
         }

         if (flag && tmplSubtemplVo.getSerialNo() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("序号不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getBase64())) {
            flag = false;
            ajaxResult = AjaxResult.error("base64编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("xml字符串不能为空");
         }

         if (flag) {
            this.tmplSubtemplService.insertTmplSubtemplCopySave(tmplSubtemplVo);
            ajaxResult.put("id", tmplSubtemplVo.getId());
         }
      } catch (Exception e) {
         this.log.error("电子病历子模板另存出现异常", e);
         ajaxResult = AjaxResult.error("电子病历子模板另存出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:subtempl:copySave,emr:index:add')")
   @Log(
      title = "电子病历子模板",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/copyEmrSave"})
   public AjaxResult copyEmrSave(@RequestBody TmplSubtemplVo tmplSubtemplVo) {
      AjaxResult ajaxResult = AjaxResult.success("另存为成功");
      boolean flag = true;

      try {
         if (tmplSubtemplVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplSubtemplVo.getTmepFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getMajor())) {
            flag = false;
            ajaxResult = AjaxResult.error("专科id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getDisease())) {
            flag = false;
            ajaxResult = AjaxResult.error("病种id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getElemId())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型id不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型名称不能为空");
         }

         if (flag && tmplSubtemplVo.getSerialNo() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("序号不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmplSubtemplVo.getTempText())) {
            flag = false;
            ajaxResult = AjaxResult.error("另存文本不能为空");
         }

         if (flag) {
            this.tmplSubtemplService.insertTmplSubtempl(tmplSubtemplVo);
            ajaxResult.put("id", tmplSubtemplVo.getId());
         }
      } catch (Exception e) {
         this.log.error("电子病历子模板另存出现异常", e);
         ajaxResult = AjaxResult.error("电子病历子模板另存出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:subtempl:remove')")
   @Log(
      title = "电子病历子模板",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.tmplSubtemplService.deleteTmplSubtemplById(id);
      } catch (Exception e) {
         this.log.error("删除子模板出现异常", e);
         ajaxResult = AjaxResult.error("删除子模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:subtempl:selectTree,tmpl:subtempl:list,td:apply:add')")
   @GetMapping({"/selectTree"})
   public AjaxResult selectTree(TmplSubtemplVo tmplSubtemplVo) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (tmplSubtemplVo.getTmepFlag() == null) {
            ajaxResult = AjaxResult.error("子模板类型不能为空");
         } else {
            List<TreeVo> treeSelectList = this.tmplSubtemplService.selectTreeList(tmplSubtemplVo);
            ajaxResult = AjaxResult.success((Object)treeSelectList);
         }
      } catch (Exception e) {
         this.log.error("查询子模板文档树出现异常", e);
         ajaxResult = AjaxResult.error("查询子模板文档树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:subtempl:selectTreeHelper,emr:index:helper')")
   @GetMapping({"/selectTreeHelper"})
   public AjaxResult selectTreeHelper(TmplSubtemplVo tmplSubtemplVo) {
      AjaxResult ajaxResult = new AjaxResult();
      boolean flag = true;

      try {
         if (tmplSubtemplVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmplSubtemplVo.getTmepFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型不能为空");
         }

         if (flag && tmplSubtemplVo.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag) {
            List<TreeVo> treeSelectList = this.tmplSubtemplService.selectTreeHelperList(tmplSubtemplVo);
            ajaxResult = AjaxResult.success((Object)treeSelectList);
         }
      } catch (Exception e) {
         this.log.error("查询病历助手子模板文档树出现异常", e);
         ajaxResult = AjaxResult.error("查询病历助手子模板文档树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:subtempl:pageInfo,tmpl:subtempl:list')")
   @GetMapping({"getDetailInfo/{id}"})
   public AjaxResult getDetailInfo(@PathVariable("id") Long id) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         ajaxResult = AjaxResult.success((Object)this.tmplSubtemplService.selectTmplSubtemplVoById(id));
      } catch (Exception e) {
         this.log.error("获取电子病历子模板信息出现异常", e);
         ajaxResult = AjaxResult.error("获取电子病历子模板信息出现异常");
      }

      return ajaxResult;
   }
}
