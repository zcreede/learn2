package com.emr.project.tmpm.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.ClinItemDept;
import com.emr.project.tmpm.service.IClinItemDeptService;
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
@RequestMapping({"/clin/dept"})
public class ClinItemDeptController extends BaseController {
   @Autowired
   private IClinItemDeptService clinItemDeptService;

   @PreAuthorize("@ss.hasPermi('clin:dept:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ClinItemDept clinItemDept) {
      this.startPage();
      List<ClinItemDept> list = this.clinItemDeptService.selectClinItemDeptList(clinItemDept);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('clin:dept:export')")
   @Log(
      title = "临床项目与科室对应关系",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ClinItemDept clinItemDept) {
      List<ClinItemDept> list = this.clinItemDeptService.selectClinItemDeptList(clinItemDept);
      ExcelUtil<ClinItemDept> util = new ExcelUtil(ClinItemDept.class);
      return util.exportExcel(list, "临床项目与科室对应关系数据");
   }

   @PreAuthorize("@ss.hasPermi('clin:dept:query')")
   @GetMapping({"/{deptCd}"})
   public AjaxResult getInfo(@PathVariable("deptCd") String deptCd) {
      return AjaxResult.success((Object)this.clinItemDeptService.selectClinItemDeptById(deptCd));
   }

   @PreAuthorize("@ss.hasPermi('clin:dept:add')")
   @Log(
      title = "临床项目与科室对应关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ClinItemDept clinItemDept) {
      return this.toAjax(this.clinItemDeptService.insertClinItemDept(clinItemDept));
   }

   @PreAuthorize("@ss.hasPermi('clin:dept:edit')")
   @Log(
      title = "临床项目与科室对应关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ClinItemDept clinItemDept) {
      return this.toAjax(this.clinItemDeptService.updateClinItemDept(clinItemDept));
   }

   @PreAuthorize("@ss.hasPermi('clin:dept:remove')")
   @Log(
      title = "临床项目与科室对应关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{deptCds}"})
   public AjaxResult remove(@PathVariable String[] deptCds) {
      return this.toAjax(this.clinItemDeptService.deleteClinItemDeptByIds(deptCds));
   }
}
