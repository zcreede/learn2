package com.emr.project.operation.controller;

import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.operation.domain.TmNaTcwhMx;
import com.emr.project.operation.service.ITmNaTcwhMxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/operation/tcwhMx"})
@Api(
   tags = {"诊疗套餐明细-刘培"}
)
public class TmNaTcwhMxController extends BaseController {
   @Autowired
   private ITmNaTcwhMxService tmNaTcwhMxService;

   @PreAuthorize("@ss.hasPermi('operation:tcwhMx:list')")
   @GetMapping({"/list"})
   @ApiOperation(
      value = "查询诊疗套餐明细列表",
      notes = "operation:tcwhMx:list"
   )
   public TableDataInfo list(TmNaTcwhMx tmNaTcwhMx) {
      try {
         this.startPage();
         List<TmNaTcwhMx> list = this.tmNaTcwhMxService.selectTmNaTcwhMxList(tmNaTcwhMx);
         return this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询诊疗套餐明细列表出现异常，", e);
         return new TableDataInfo(500, "查询诊疗套餐明细列表出现异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('operation:tcwhMx:save')")
   @PostMapping({"/save"})
   @ApiOperation(
      value = "新增诊疗套餐明细",
      notes = "operation:tcwhMx:save"
   )
   public AjaxResult save(@RequestBody List tmNaTcwhMxs) {
      try {
         return this.toAjax(this.tmNaTcwhMxService.save(tmNaTcwhMxs));
      } catch (Exception e) {
         this.log.error("新增诊疗套餐明细出现异常，", e);
         return AjaxResult.error("新增诊疗套餐明细出现异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('operation:tcwhMx:remove')")
   @DeleteMapping({"/{ids}"})
   @ApiOperation(
      value = "删除诊疗套餐明细",
      notes = "operation:tcwhMx:remove"
   )
   public AjaxResult remove(@PathVariable Long[] ids) {
      try {
         return this.toAjax(this.tmNaTcwhMxService.deleteTmNaTcwhMxByIds(ids));
      } catch (Exception e) {
         this.log.error("删除诊疗套餐明细出现异常，", e);
         return AjaxResult.error("删除诊疗套餐明细出现异常，请联系管理员！");
      }
   }
}
