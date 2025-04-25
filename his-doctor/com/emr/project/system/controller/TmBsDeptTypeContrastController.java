package com.emr.project.system.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.TmBsDeptTypeContrast;
import com.emr.project.system.domain.vo.TmBsDeptTypeContrastVo;
import com.emr.project.system.service.ITmBsDeptTypeContrastService;
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
@RequestMapping({"/system/contrast"})
public class TmBsDeptTypeContrastController extends BaseController {
   @Autowired
   private ITmBsDeptTypeContrastService tmBsDeptTypeContrastService;

   @PreAuthorize("@ss.hasPermi('system:contrast:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmBsDeptTypeContrast tmBsDeptTypeContrast) {
      this.startPage();
      List<TmBsDeptTypeContrast> list = this.tmBsDeptTypeContrastService.selectTmBsDeptTypeContrastList(tmBsDeptTypeContrast);
      return this.getDataTable(list);
   }

   @GetMapping({"/getInHosDeptList"})
   public TableDataInfo list(TmBsDeptTypeContrastVo tmBsDeptTypeContrastVo) {
      this.startPage();
      List<TmBsDeptTypeContrastVo> list = this.tmBsDeptTypeContrastService.selectTmBsDeptTypeContrastVoList(tmBsDeptTypeContrastVo);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:contrast:export')")
   @Log(
      title = "科室类别对照",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmBsDeptTypeContrast tmBsDeptTypeContrast) {
      List<TmBsDeptTypeContrast> list = this.tmBsDeptTypeContrastService.selectTmBsDeptTypeContrastList(tmBsDeptTypeContrast);
      ExcelUtil<TmBsDeptTypeContrast> util = new ExcelUtil(TmBsDeptTypeContrast.class);
      return util.exportExcel(list, "科室类别对照数据");
   }

   @PreAuthorize("@ss.hasPermi('system:contrast:query')")
   @GetMapping({"/{deptCode}"})
   public AjaxResult getInfo(@PathVariable("deptCode") String deptCode) {
      return AjaxResult.success((Object)this.tmBsDeptTypeContrastService.selectTmBsDeptTypeContrastById(deptCode));
   }

   @PreAuthorize("@ss.hasPermi('system:contrast:add')")
   @Log(
      title = "科室类别对照",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmBsDeptTypeContrast tmBsDeptTypeContrast) {
      return this.toAjax(this.tmBsDeptTypeContrastService.insertTmBsDeptTypeContrast(tmBsDeptTypeContrast));
   }

   @PreAuthorize("@ss.hasPermi('system:contrast:edit')")
   @Log(
      title = "科室类别对照",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmBsDeptTypeContrast tmBsDeptTypeContrast) {
      return this.toAjax(this.tmBsDeptTypeContrastService.updateTmBsDeptTypeContrast(tmBsDeptTypeContrast));
   }

   @PreAuthorize("@ss.hasPermi('system:contrast:remove')")
   @Log(
      title = "科室类别对照",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{deptCodes}"})
   public AjaxResult remove(@PathVariable String[] deptCodes) {
      return this.toAjax(this.tmBsDeptTypeContrastService.deleteTmBsDeptTypeContrastByIds(deptCodes));
   }
}
