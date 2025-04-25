package com.emr.project.emr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.emr.domain.EmrSearchCase;
import com.emr.project.emr.domain.vo.EmrSearchCaseVo;
import com.emr.project.emr.service.IEmrSearchCaseService;
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
@RequestMapping({"/emr/case"})
public class EmrSearchCaseController extends BaseController {
   @Autowired
   private IEmrSearchCaseService emrSearchCaseService;

   @PreAuthorize("@ss.hasPermi('emr:case:list')")
   @GetMapping({"/list"})
   public AjaxResult list(EmrSearchCase emrSearchCase) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<EmrSearchCase> list = this.emrSearchCaseService.selectEmrSearchCaseList(emrSearchCase);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询病历检索方案列表出现异常", e);
         ajaxResult = AjaxResult.error("查询病历检索方案列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:case:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrSearchCaseService.selectEmrSearchCaseById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:case:add')")
   @Log(
      title = "病历检索方案",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSearchCaseVo emrSearchCase) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (emrSearchCase == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSearchCase.getCaseName())) {
            flag = false;
            ajaxResult = AjaxResult.error("方案名称不能为空");
         }

         if (flag && (emrSearchCase == null || emrSearchCase.getDetailList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("方案详情不能为空");
         }

         if (flag) {
            this.emrSearchCaseService.insertEmrSearchCase(emrSearchCase);
         }
      } catch (Exception e) {
         this.log.error("新增病历检索方案出现异常", e);
         ajaxResult = AjaxResult.error("新增病历检索方案出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:case:edit')")
   @Log(
      title = "病历检索方案",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSearchCaseVo emrSearchCaseVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (emrSearchCaseVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrSearchCaseVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSearchCaseVo.getCaseName())) {
            flag = false;
            ajaxResult = AjaxResult.error("方案名称不能为空");
         }

         if (flag && (emrSearchCaseVo == null || emrSearchCaseVo.getDetailList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("方案详情不能为空");
         }

         if (flag) {
            this.emrSearchCaseService.updateEmrSearchCase(emrSearchCaseVo);
         }
      } catch (Exception e) {
         this.log.error("修改病历检索方案出现异常", e);
         ajaxResult = AjaxResult.error("修改病历检索方案出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:case:remove')")
   @Log(
      title = "病历检索方案",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.emrSearchCaseService.deleteEmrSearchCaseById(id);
      } catch (Exception e) {
         this.log.error("删除病历检索方案出现异常", e);
         ajaxResult = AjaxResult.error("删除病历检索方案出现异常");
      }

      return ajaxResult;
   }
}
