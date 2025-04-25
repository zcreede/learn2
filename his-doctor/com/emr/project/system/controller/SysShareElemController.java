package com.emr.project.system.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysShareElem;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysShareElemService;
import java.util.List;
import javax.annotation.Resource;
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
@RequestMapping({"/system/share"})
public class SysShareElemController extends BaseController {
   @Autowired
   private ISysShareElemService sysShareElemService;
   @Resource
   private ISysDictDataService sysDictDataService;

   @PreAuthorize("@ss.hasPermi('system:share:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysShareElem sysShareElem) {
      List<SysShareElem> list = null;

      try {
         this.startPage();
         list = this.sysShareElemService.selectSysShareElemList(sysShareElem);
      } catch (Exception e) {
         this.log.error("查询病历共享引用元素列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:share:export')")
   @Log(
      title = "病历共享引用元素，用于病历书写时，复制粘贴引用",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysShareElem sysShareElem) {
      List<SysShareElem> list = this.sysShareElemService.selectSysShareElemList(sysShareElem);
      ExcelUtil<SysShareElem> util = new ExcelUtil(SysShareElem.class);
      return util.exportExcel(list, "病历共享引用元素，用于病历书写时，复制粘贴引用数据");
   }

   @PreAuthorize("@ss.hasPermi('system:share:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysShareElemService.selectSysShareElemById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:share:add')")
   @Log(
      title = "病历共享引用元素，用于病历书写时，复制粘贴引用",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysShareElem sysShareElem) {
      return this.toAjax(this.sysShareElemService.insertSysShareElem(sysShareElem));
   }

   @PreAuthorize("@ss.hasPermi('system:share:edit')")
   @Log(
      title = "病历共享引用元素，用于病历书写时，复制粘贴引用",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysShareElem sysShareElem) {
      return this.toAjax(this.sysShareElemService.updateSysShareElem(sysShareElem));
   }

   @PreAuthorize("@ss.hasPermi('system:share:remove')")
   @Log(
      title = "病历共享引用元素，用于病历书写时，复制粘贴引用",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sysShareElemService.deleteSysShareElemByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('system:share:addShareElem')")
   @Log(
      title = "病历共享引用元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/addShareElem"})
   public AjaxResult addShareElem(@RequestBody SysShareElem sysShareElem) {
      AjaxResult ajaxResult = AjaxResult.success("病历共享引用元素添加成功");
      Boolean flag = true;

      try {
         if (flag && StringUtils.isEmpty(sysShareElem.getTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型不能为空");
         }

         if (flag && sysShareElem.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id不能为空");
         }

         if (flag && sysShareElem.getElemName() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("元素名称不能为空");
         }

         if (flag && sysShareElem.getElemCd() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("元素编码不能为空");
         }

         if (flag) {
            SysShareElem param = new SysShareElem();
            param.setElemId(sysShareElem.getElemId());
            param.setTempType(sysShareElem.getTempType());
            List<SysShareElem> list = this.sysShareElemService.selectSysShareElemList(param);
            if (flag && list != null && list.size() > 0) {
               flag = false;
               ajaxResult = AjaxResult.error("病历共享引用元素已添加，不能重复添加");
            } else {
               this.sysShareElemService.insertSysShareElem(sysShareElem);
            }
         }
      } catch (Exception e) {
         this.log.error("添加病历共享引用元素出现异常：", e);
         ajaxResult = AjaxResult.error("添加病历共享引用元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:share:elemList')")
   @GetMapping({"/elemList/{tempType}"})
   public TableDataInfo elemList(@PathVariable("tempType") String tempType) {
      List<SysShareElem> list = null;

      try {
         this.startPage();
         SysShareElem sysShareElem = new SysShareElem();
         sysShareElem.setTempType(tempType);
         list = this.sysShareElemService.selectSysShareElemList(sysShareElem);
      } catch (Exception e) {
         this.log.error("查询病历共享元素列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:share:tempList')")
   @GetMapping({"/tempList"})
   public TableDataInfo tempList(SysDictData sysDictData) {
      List<SysDictData> list = null;

      try {
         sysDictData.setDictType("s004");
         this.startPage();
         list = this.sysDictDataService.selectDictDataList(sysDictData);
      } catch (Exception e) {
         this.log.error("查询病历类型列表列表出现异常", e);
      }

      return this.getDataTable(list);
   }
}
