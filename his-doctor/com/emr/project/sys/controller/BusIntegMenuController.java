package com.emr.project.sys.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.sys.domain.BusIntegMenu;
import com.emr.project.sys.domain.vo.BusIntegMenuSearchVo;
import com.emr.project.sys.domain.vo.BusIntegMenuTreeVo;
import com.emr.project.sys.domain.vo.BusIntegMenuVo;
import com.emr.project.sys.service.IBusIntegMenuService;
import java.util.ArrayList;
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
@RequestMapping({"/sys/busmenu"})
public class BusIntegMenuController extends BaseController {
   @Autowired
   private IBusIntegMenuService busIntegMenuService;

   @PreAuthorize("@ss.hasPermi('system:config:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(BusIntegMenu busIntegMenu) {
      this.startPage();
      List<BusIntegMenu> list = this.busIntegMenuService.selectBusIntegMenuList(busIntegMenu);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('sys:busmenu:export')")
   @Log(
      title = "集成页面菜单定义",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BusIntegMenu busIntegMenu) {
      List<BusIntegMenu> list = this.busIntegMenuService.selectBusIntegMenuList(busIntegMenu);
      ExcelUtil<BusIntegMenu> util = new ExcelUtil(BusIntegMenu.class);
      return util.exportExcel(list, "集成页面菜单定义数据");
   }

   @PreAuthorize("@ss.hasPermi('system:config:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.busIntegMenuService.selectBusIntegMenuById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:config:add')")
   @Log(
      title = "集成页面菜单定义",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BusIntegMenu busIntegMenu) {
      return this.toAjax(this.busIntegMenuService.insertBusIntegMenu(busIntegMenu));
   }

   @PreAuthorize("@ss.hasPermi('system:config:add')")
   @Log(
      title = "集成页面菜单定义",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BusIntegMenu busIntegMenu) {
      return this.toAjax(this.busIntegMenuService.updateBusIntegMenu(busIntegMenu));
   }

   @PreAuthorize("@ss.hasPermi('sys:busmenu:remove')")
   @Log(
      title = "集成页面菜单定义",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.busIntegMenuService.deleteBusIntegMenuByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('system:config:add')")
   @Log(
      title = "集成页面菜单定义",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/delete/{id}"})
   public AjaxResult delete(@PathVariable Long id) {
      return this.toAjax(this.busIntegMenuService.deleteBusIntegMenuById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('sys:busmenu:trees,operation:pat:info')")
   @GetMapping({"/trees"})
   public AjaxResult busmenuTreeslist(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isBlank(patientId)) {
            ajaxResult = AjaxResult.error("参数不能为空");
         } else {
            BusIntegMenuVo busIntegMenuVo = this.busIntegMenuService.busmenuTreeslist(patientId);
            ajaxResult = AjaxResult.success("查询成功", busIntegMenuVo);
         }
      } catch (Exception e) {
         this.log.error("查询就诊菜单出现异常，", e);
         ajaxResult = AjaxResult.error("查询就诊菜单出错");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term,qc:rt:check')")
   @GetMapping({"/treesQc"})
   public AjaxResult busmenuTreeslistQc(String patientId, String qcSection) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcSection)) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (flag) {
            BusIntegMenuSearchVo busIntegMenuSearchVo = new BusIntegMenuSearchVo(patientId);
            busIntegMenuSearchVo.setQcSection(qcSection);
            List<String> indexStateList = new ArrayList(1);
            indexStateList.add("08");
            indexStateList.add("01");
            indexStateList.add("03");
            indexStateList.add("05");
            indexStateList.add("07");
            busIntegMenuSearchVo.setIndexStateList(indexStateList);
            busIntegMenuSearchVo.setIndexNofinishQcRedColorFlag("1");
            List<BusIntegMenuTreeVo> menuTreeList = this.busIntegMenuService.busmenuTreeslist(busIntegMenuSearchVo, "3");
            ajaxResult = AjaxResult.success("查询成功", menuTreeList);
         }
      } catch (Exception e) {
         this.log.error("查询就诊菜单出现异常，", e);
         ajaxResult = AjaxResult.error("查询就诊菜单出错");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('mz:info:treeMz,pat:info:emrAllList')")
   @GetMapping({"/treeMz"})
   public AjaxResult busmenuTreeslistMz(String mzh, String patientId, String visitNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (StringUtils.isBlank(visitNo)) {
            flag = false;
            ajaxResult = AjaxResult.error("住院号不能为空");
         }

         if (flag) {
            BusIntegMenuSearchVo busIntegMenuSearchVo = new BusIntegMenuSearchVo();
            busIntegMenuSearchVo.setPatientId(patientId);
            busIntegMenuSearchVo.setVisitNo(visitNo);
            List<BusIntegMenuTreeVo> menuTreeList = this.busIntegMenuService.busmenuTreeslistMz(busIntegMenuSearchVo, "6", (List)null);
            ajaxResult = AjaxResult.success("查询成功", menuTreeList);
         }
      } catch (Exception e) {
         this.log.error("查询就诊菜单出现异常，", e);
         ajaxResult = AjaxResult.error("查询就诊菜单出错");
      }

      return ajaxResult;
   }
}
