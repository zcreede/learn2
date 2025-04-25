package com.emr.project.pat.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.DrgMrGroup;
import com.emr.project.pat.domain.vo.DrgMrGroupVo;
import com.emr.project.pat.service.IDrgMrGroupService;
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
@RequestMapping({"/pat/group"})
public class DrgMrGroupController extends BaseController {
   @Autowired
   private IDrgMrGroupService drgMrGroupService;

   @PreAuthorize("@ss.hasPermi('pat:group:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DrgMrGroup drgMrGroup) {
      this.startPage();
      List<DrgMrGroup> list = this.drgMrGroupService.selectDrgMrGroupList(drgMrGroup);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:group:export')")
   @Log(
      title = "病案DRG分组",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DrgMrGroup drgMrGroup) {
      List<DrgMrGroup> list = this.drgMrGroupService.selectDrgMrGroupList(drgMrGroup);
      ExcelUtil<DrgMrGroup> util = new ExcelUtil(DrgMrGroup.class);
      return util.exportExcel(list, "病案DRG分组数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:group:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.drgMrGroupService.selectDrgMrGroupById(id));
   }

   @PreAuthorize("@ss.hasPermi('pat:group:add')")
   @Log(
      title = "病案DRG分组",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrgMrGroup drgMrGroup) {
      return this.toAjax(this.drgMrGroupService.insertDrgMrGroup(drgMrGroup));
   }

   @PreAuthorize("@ss.hasPermi('pat:group:edit')")
   @Log(
      title = "病案DRG分组",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrgMrGroup drgMrGroup) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");

      try {
         this.drgMrGroupService.updateDrgMrGroup(drgMrGroup);
      } catch (Exception e) {
         this.log.error("修改病案DRG分组出现异常", e);
         ajaxResult = AjaxResult.error("修改病案DRG分组出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:group:remove')")
   @Log(
      title = "病案DRG分组",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.drgMrGroupService.deleteDrgMrGroupByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/selectInfo"})
   public AjaxResult selectInfo(DrgMrGroupVo drgMrGroupVo) {
      new DrgMrGroupVo();
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(drgMrGroupVo.getPatientId())) {
            ajaxResult = AjaxResult.error("患者就诊id参数不能为空");
         } else {
            DrgMrGroupVo drgMrGroup = this.drgMrGroupService.selectInfo(drgMrGroupVo);
            ajaxResult = AjaxResult.success((Object)drgMrGroup);
         }
      } catch (Exception e) {
         this.log.error("获取病案DRG分组详细信息出现异常", e);
         ajaxResult = AjaxResult.error("获取病案DRG分组详细信息出现异常");
      }

      return ajaxResult;
   }
}
