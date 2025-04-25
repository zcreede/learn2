package com.emr.project.dr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.DrHandoverScheme;
import com.emr.project.dr.domain.vo.DrHandoverSchemeVo;
import com.emr.project.dr.service.IDrHandoverSchemeDeptService;
import com.emr.project.dr.service.IDrHandoverSchemeService;
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
@RequestMapping({"/handover/scheme"})
public class DrHandoverSchemeController extends BaseController {
   @Autowired
   private IDrHandoverSchemeService drHandoverSchemeService;
   @Autowired
   private IDrHandoverSchemeDeptService drHandoverSchemeDeptService;

   @PreAuthorize("@ss.hasPermi('handover:scheme:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DrHandoverSchemeVo drHandoverSchemeVo) {
      List<DrHandoverSchemeVo> list = null;

      try {
         this.startPage();
         list = this.drHandoverSchemeService.selectDrHandoverSchemeVoList(drHandoverSchemeVo);
      } catch (Exception e) {
         this.log.error("查询交接班班次配置列表出现异常: ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('dr:scheme:export')")
   @Log(
      title = "交接班班次配置",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DrHandoverScheme drHandoverScheme) {
      List<DrHandoverScheme> list = this.drHandoverSchemeService.selectDrHandoverSchemeList(drHandoverScheme);
      ExcelUtil<DrHandoverScheme> util = new ExcelUtil(DrHandoverScheme.class);
      return util.exportExcel(list, "交接班班次配置数据");
   }

   @PreAuthorize("@ss.hasPermi('dr:scheme:query')")
   @GetMapping({"/{schemeCd}"})
   public AjaxResult getInfo(@PathVariable("schemeCd") Long schemeCd) {
      return AjaxResult.success((Object)this.drHandoverSchemeService.selectDrHandoverSchemeById(schemeCd));
   }

   @PreAuthorize("@ss.hasPermi('handover:scheme:add')")
   @Log(
      title = "交接班班次配置",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrHandoverSchemeVo drHandoverScheme) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(drHandoverScheme.getSchemeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次名称不能为空，请填写后再保存");
         }

         if (flag && drHandoverScheme.getSchemeBeginDayFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("班次开始时间标志不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverScheme.getSchemeBegin())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次开始时间不能为空，请填写后再保存");
         }

         if (flag && drHandoverScheme.getSchemeEndDayFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("班次结束时间标志不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverScheme.getSchemeEnd())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次结束时间不能为空，请填写后再保存");
         }

         if (flag && (drHandoverScheme.getDeptList() == null || drHandoverScheme.getDeptList() != null && drHandoverScheme.getDeptList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次的适用科室不能为空，请填写后再保存");
         }

         if (flag) {
            this.drHandoverSchemeService.insertDrHandoverScheme(drHandoverScheme);
         }
      } catch (Exception e) {
         this.log.error("新增交接班班次配置出现异常，", e);
         ajaxResult = AjaxResult.error("新增交接班班次配置出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:scheme:edit')")
   @Log(
      title = "交接班班次配置",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrHandoverSchemeVo drHandoverScheme) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (flag && drHandoverScheme.getSchemeCd() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("班次编码不能为空");
         }

         DrHandoverScheme scheme = flag ? this.drHandoverSchemeService.selectDrHandoverSchemeById(drHandoverScheme.getSchemeCd()) : null;
         if (flag && scheme == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个班次记录，不能编辑");
         }

         if (flag && StringUtils.isBlank(drHandoverScheme.getSchemeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次名称不能为空，请填写后再保存");
         }

         if (flag && drHandoverScheme.getSchemeBeginDayFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("班次开始时间标志不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverScheme.getSchemeBegin())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次开始时间不能为空，请填写后再保存");
         }

         if (flag && drHandoverScheme.getSchemeEndDayFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("班次结束时间标志不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverScheme.getSchemeEnd())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次结束时间不能为空，请填写后再保存");
         }

         if (flag && (drHandoverScheme.getDeptList() == null || drHandoverScheme.getDeptList() != null && drHandoverScheme.getDeptList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次的适用科室不能为空，请填写后再保存");
         }

         if (flag) {
            this.drHandoverSchemeService.updateDrHandoverScheme(drHandoverScheme);
         }
      } catch (Exception e) {
         this.log.error("修改交接班班次配置出现异常，", e);
         ajaxResult = AjaxResult.error("修改交接班班次配置出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:scheme:remove')")
   @Log(
      title = "交接班班次配置",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{schemeCds}"})
   public AjaxResult remove(@PathVariable Long[] schemeCds) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.drHandoverSchemeService.deleteDrHandoverSchemeByIds(schemeCds);
      } catch (Exception e) {
         this.log.error("删除交接班班次配置出现异常：", e);
         ajaxResult = AjaxResult.error("删除交接班班次配置出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:info')")
   @GetMapping({"/listByDept"})
   public AjaxResult listByDept(String deptCd) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isBlank(deptCd)) {
            ajaxResult = AjaxResult.error("科室编码不能为空");
         } else {
            List<DrHandoverScheme> list = this.drHandoverSchemeService.selectListByCurrDept(deptCd);
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("查询某科室的班次列表出现异常：", e);
         ajaxResult = AjaxResult.error("查询某科室的班次列表出现异常");
      }

      return ajaxResult;
   }
}
