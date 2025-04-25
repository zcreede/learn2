package com.emr.project.pat.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.service.IBabyInfoService;
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
@RequestMapping({"/pat/babyInfo"})
public class BabyInfoController extends BaseController {
   @Autowired
   private IBabyInfoService babyInfoService;

   @PreAuthorize("@ss.hasPermi('pat:babyInfo:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(BabyInfo babyInfo) {
      this.startPage();
      List<BabyInfo> list = this.babyInfoService.selectBabyInfoList(babyInfo);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:babyInfo:export')")
   @Log(
      title = "婴儿信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BabyInfo babyInfo) {
      List<BabyInfo> list = this.babyInfoService.selectBabyInfoList(babyInfo);
      ExcelUtil<BabyInfo> util = new ExcelUtil(BabyInfo.class);
      return util.exportExcel(list, "婴儿信息数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:babyInfo:query')")
   @GetMapping({"/{patientId}"})
   public AjaxResult getInfo(@PathVariable("patientId") String patientId) {
      return AjaxResult.success((Object)this.babyInfoService.selectBabyInfoById(patientId));
   }

   @PreAuthorize("@ss.hasPermi('pat:babyInfo:add')")
   @Log(
      title = "婴儿信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BabyInfo babyInfo) {
      return this.toAjax(this.babyInfoService.insertBabyInfo(babyInfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:babyInfo:edit')")
   @Log(
      title = "婴儿信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BabyInfo babyInfo) {
      return this.toAjax(this.babyInfoService.updateBabyInfo(babyInfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:babyInfo:remove')")
   @Log(
      title = "婴儿信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{patientIds}"})
   public AjaxResult remove(@PathVariable String[] patientIds) {
      return this.toAjax(this.babyInfoService.deleteBabyInfoByIds(patientIds));
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:add,pat:babyInfo:babyTreeList')")
   @GetMapping({"/babyTreeList"})
   public AjaxResult babyTreeList(BabyInfo babyInfo) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isNotEmpty(babyInfo.getPatientId())) {
            List<BabyInfo> list = this.babyInfoService.selectBabyTreeList(babyInfo);
            ajaxResult = AjaxResult.success((Object)list);
         } else {
            ajaxResult = AjaxResult.error("患者就诊id不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询患者婴儿下拉出现异常", e);
         ajaxResult = AjaxResult.error("查询患者婴儿下拉出现异常");
      }

      return ajaxResult;
   }
}
