package com.emr.project.tmpm.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.service.IClinItemMainService;
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
@RequestMapping({"/clin/main"})
public class ClinItemMainController extends BaseController {
   @Autowired
   private IClinItemMainService clinItemMainService;

   @PreAuthorize("@ss.hasPermi('clin:main:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ClinItemMain clinItemMain) {
      this.startPage();
      List<ClinItemMain> list = this.clinItemMainService.selectClinItemMainList(clinItemMain);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('clin:main:export')")
   @Log(
      title = "临床项目主",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ClinItemMain clinItemMain) {
      List<ClinItemMain> list = this.clinItemMainService.selectClinItemMainList(clinItemMain);
      ExcelUtil<ClinItemMain> util = new ExcelUtil(ClinItemMain.class);
      return util.exportExcel(list, "临床项目主数据");
   }

   @PreAuthorize("@ss.hasPermi('clin:main:query')")
   @GetMapping({"/{itemCd}"})
   public AjaxResult getInfo(@PathVariable("itemCd") String itemCd) {
      return AjaxResult.success((Object)this.clinItemMainService.selectClinItemMainById(itemCd));
   }

   @PreAuthorize("@ss.hasPermi('clin:main:add')")
   @Log(
      title = "临床项目主",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ClinItemMain clinItemMain) {
      return this.toAjax(this.clinItemMainService.insertClinItemMain(clinItemMain));
   }

   @PreAuthorize("@ss.hasPermi('clin:main:edit')")
   @Log(
      title = "临床项目主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ClinItemMain clinItemMain) {
      return this.toAjax(this.clinItemMainService.updateClinItemMain(clinItemMain));
   }

   @PreAuthorize("@ss.hasPermi('clin:main:remove')")
   @Log(
      title = "临床项目主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{itemCds}"})
   public AjaxResult remove(@PathVariable String[] itemCds) {
      return this.toAjax(this.clinItemMainService.deleteClinItemMainByIds(itemCds));
   }
}
