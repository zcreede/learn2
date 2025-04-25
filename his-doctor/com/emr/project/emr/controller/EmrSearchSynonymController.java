package com.emr.project.emr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrSearchSynonym;
import com.emr.project.emr.service.IEmrSearchSynonymService;
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
@RequestMapping({"/emr/synonym"})
public class EmrSearchSynonymController extends BaseController {
   @Autowired
   private IEmrSearchSynonymService emrSearchSynonymService;

   @PreAuthorize("@ss.hasPermi('emr:synonym:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrSearchSynonym emrSearchSynonym) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<EmrSearchSynonym> list = this.emrSearchSynonymService.selectEmrSearchSynonymList(emrSearchSynonym);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询同义词列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询同义词列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('emr:synonym:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrSearchSynonymService.selectEmrSearchSynonymById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:synonym:add')")
   @Log(
      title = "病历检索同义词",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSearchSynonym emrSearchSynonym) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (emrSearchSynonym == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSearchSynonym.getSynonymS())) {
            flag = false;
            ajaxResult = AjaxResult.error("同义词1不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSearchSynonym.getSynonymT())) {
            flag = false;
            ajaxResult = AjaxResult.error("同义词2不能为空");
         }

         if (flag) {
            this.emrSearchSynonymService.insertEmrSearchSynonym(emrSearchSynonym);
         }
      } catch (Exception e) {
         this.log.error("新增病历检索同义词出现异常", e);
         ajaxResult = AjaxResult.error("新增病历检索同义词出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:synonym:edit')")
   @Log(
      title = "病历检索同义词",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSearchSynonym emrSearchSynonym) {
      return this.toAjax(this.emrSearchSynonymService.updateEmrSearchSynonym(emrSearchSynonym));
   }

   @PreAuthorize("@ss.hasPermi('emr:synonym:remove')")
   @Log(
      title = "病历检索同义词",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         if (id == null) {
            ajaxResult = AjaxResult.error("id不能为空");
         } else {
            this.emrSearchSynonymService.deleteEmrSearchSynonymById(id);
         }
      } catch (Exception e) {
         this.log.error("删除病历检索同义词出现异常", e);
         ajaxResult = AjaxResult.error("删除病历检索同义词出现异常");
      }

      return ajaxResult;
   }
}
