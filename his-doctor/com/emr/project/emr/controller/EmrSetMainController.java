package com.emr.project.emr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.emr.domain.EmrSetMain;
import com.emr.project.emr.service.IEmrSetMainService;
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
@RequestMapping({"/emrSet/main"})
public class EmrSetMainController extends BaseController {
   @Autowired
   private IEmrSetMainService emrSetMainService;

   @PreAuthorize("@ss.hasAnyPermi('emrSet:main:list,emr:index:save')")
   @GetMapping({"/list"})
   public AjaxResult list(EmrSetMain emrSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<EmrSetMain> list = this.emrSetMainService.selectEmrSetMainList(emrSetMain);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询病历组套出现异常", e);
         ajaxResult = AjaxResult.error("查询病历组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emrSet:main:query')")
   @GetMapping({"/{setCd}"})
   public AjaxResult getInfo(@PathVariable("setCd") String setCd) {
      return AjaxResult.success((Object)this.emrSetMainService.selectEmrSetMainById(setCd));
   }

   @PreAuthorize("@ss.hasPermi('emrSet:main:add')")
   @Log(
      title = "添加病历组套",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSetMain emrSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (emrSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套名称不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("适用范围不能为空");
         }

         if (flag) {
            this.emrSetMainService.insertEmrSetMain(emrSetMain);
            ajaxResult = AjaxResult.success((Object)emrSetMain);
         }
      } catch (Exception e) {
         this.log.error("添加病历组套出现异常", e);
         ajaxResult = AjaxResult.error("添加病历组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emrSet:main:edit')")
   @Log(
      title = "修改病历组套",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSetMain emrSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (emrSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套编码不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套名称不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("适用范围不能为空");
         }

         if (flag) {
            this.emrSetMainService.updateEmrSetMain(emrSetMain);
            ajaxResult = AjaxResult.success();
         }
      } catch (Exception e) {
         this.log.error("修改病历组套出现异常", e);
         ajaxResult = AjaxResult.error("修改病历组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emrSet:main:remove')")
   @Log(
      title = "删除病历组套",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{setCd}"})
   public AjaxResult remove(@PathVariable String setCd) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.emrSetMainService.deleteEmrSetMainById(setCd);
      } catch (Exception e) {
         this.log.error("删除病历组套出现异常", e);
         ajaxResult = AjaxResult.error("删除病历组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emrSet:main:edit')")
   @Log(
      title = "修改病历组套启用状态",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody EmrSetMain emrSetMain) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (emrSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套编码不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.emrSetMainService.updateEmrSetMain(emrSetMain);
            ajaxResult = AjaxResult.success((Object)emrSetMain);
         }
      } catch (Exception e) {
         this.log.error("修改病历组套启用状态出现异常", e);
         ajaxResult = AjaxResult.error("修改病历组套启用状态出现异常");
      }

      return ajaxResult;
   }
}
