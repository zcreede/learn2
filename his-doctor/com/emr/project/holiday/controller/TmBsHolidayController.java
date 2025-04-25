package com.emr.project.holiday.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.holiday.domain.TmBsHoliday;
import com.emr.project.holiday.service.ITmBsHolidayService;
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
@RequestMapping({"/qc/holiday"})
public class TmBsHolidayController extends BaseController {
   @Autowired
   private ITmBsHolidayService tmBsHolidayService;

   @PreAuthorize("@ss.hasPermi('holiday:holiday:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmBsHoliday tmBsHoliday) {
      this.startPage();
      List<TmBsHoliday> list = this.tmBsHolidayService.selectTmBsHolidayList(tmBsHoliday);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('holiday:holiday:export')")
   @Log(
      title = "节假日设置",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmBsHoliday tmBsHoliday) {
      List<TmBsHoliday> list = this.tmBsHolidayService.selectTmBsHolidayList(tmBsHoliday);
      ExcelUtil<TmBsHoliday> util = new ExcelUtil(TmBsHoliday.class);
      return util.exportExcel(list, "节假日设置数据");
   }

   @PreAuthorize("@ss.hasPermi('holiday:holiday:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmBsHolidayService.selectTmBsHolidayById(id));
   }

   @PreAuthorize("@ss.hasPermi('holiday:holiday:add')")
   @Log(
      title = "节假日设置",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmBsHoliday tmBsHoliday) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功！");
      if (StringUtils.isEmpty(tmBsHoliday.getHolidayDesc())) {
         ajaxResult = AjaxResult.error("节假日说明不能为空！");
         return ajaxResult;
      } else if (tmBsHoliday.getStartDate() != null && tmBsHoliday.getEndDate() != null) {
         if (StringUtils.isEmpty(tmBsHoliday.getHolidayType())) {
            ajaxResult = AjaxResult.error("类型不能为空！");
            return ajaxResult;
         } else {
            try {
               this.tmBsHolidayService.insertTmBsHoliday(tmBsHoliday);
            } catch (Exception e) {
               this.log.error("新增节假日配置出现异常，请联系管理员！", e);
               ajaxResult = AjaxResult.error("新增节假日配置出现异常，请联系管理员！");
            }

            return ajaxResult;
         }
      } else {
         ajaxResult = AjaxResult.error("时间不能为空！");
         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasPermi('holiday:holiday:edit')")
   @Log(
      title = "节假日设置",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmBsHoliday tmBsHoliday) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功！");
      if (StringUtils.isEmpty(tmBsHoliday.getHolidayDesc())) {
         ajaxResult = AjaxResult.error("节假日说明不能为空！");
         return ajaxResult;
      } else if (tmBsHoliday.getStartDate() != null && tmBsHoliday.getEndDate() != null) {
         if (StringUtils.isEmpty(tmBsHoliday.getHolidayType())) {
            ajaxResult = AjaxResult.error("类型不能为空！");
            return ajaxResult;
         } else if (tmBsHoliday.getId() == null) {
            ajaxResult = AjaxResult.error("主键id不能为空！");
            return ajaxResult;
         } else {
            try {
               this.tmBsHolidayService.updateTmBsHoliday(tmBsHoliday);
            } catch (Exception e) {
               this.log.error("修改节假日配置出现异常，请联系管理员！", e);
               ajaxResult = AjaxResult.error("修改节假日配置出现异常，请联系管理员！");
            }

            return ajaxResult;
         }
      } else {
         ajaxResult = AjaxResult.error("时间不能为空！");
         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasPermi('holiday:holiday:remove')")
   @Log(
      title = "节假日设置",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tmBsHolidayService.deleteTmBsHolidayByIds(ids));
   }
}
