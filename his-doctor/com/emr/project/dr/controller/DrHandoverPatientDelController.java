package com.emr.project.dr.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.DrHandoverPatientDel;
import com.emr.project.dr.service.IDrHandoverPatientDelService;
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
@RequestMapping({"/handover/del"})
public class DrHandoverPatientDelController extends BaseController {
   @Autowired
   private IDrHandoverPatientDelService drHandoverPatientDelService;

   @PreAuthorize("@ss.hasPermi('dr:del:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DrHandoverPatientDel drHandoverPatientDel) {
      this.startPage();
      List<DrHandoverPatientDel> list = this.drHandoverPatientDelService.selectDrHandoverPatientDelList(drHandoverPatientDel);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('dr:del:export')")
   @Log(
      title = "手动删除的患者信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DrHandoverPatientDel drHandoverPatientDel) {
      List<DrHandoverPatientDel> list = this.drHandoverPatientDelService.selectDrHandoverPatientDelList(drHandoverPatientDel);
      ExcelUtil<DrHandoverPatientDel> util = new ExcelUtil(DrHandoverPatientDel.class);
      return util.exportExcel(list, "手动删除的患者信息数据");
   }

   @PreAuthorize("@ss.hasPermi('dr:del:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.drHandoverPatientDelService.selectDrHandoverPatientDelById(id));
   }

   @PreAuthorize("@ss.hasPermi('dr:del:add')")
   @Log(
      title = "手动删除的患者信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrHandoverPatientDel drHandoverPatientDel) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");

      try {
         ajaxResult = this.toAjax(this.drHandoverPatientDelService.insertDrHandoverPatientDel(drHandoverPatientDel));
      } catch (Exception var4) {
         this.log.error("");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('dr:del:edit')")
   @Log(
      title = "手动删除的患者信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrHandoverPatientDel drHandoverPatientDel) {
      return this.toAjax(this.drHandoverPatientDelService.updateDrHandoverPatientDel(drHandoverPatientDel));
   }

   @PreAuthorize("@ss.hasPermi('dr:del:remove')")
   @Log(
      title = "手动删除的患者信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.drHandoverPatientDelService.deleteDrHandoverPatientDelByIds(ids));
   }
}
