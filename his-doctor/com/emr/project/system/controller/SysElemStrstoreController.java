package com.emr.project.system.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysElemStrstore;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysElemStrstoreService;
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
@RequestMapping({"/system/strstore"})
public class SysElemStrstoreController extends BaseController {
   @Autowired
   private ISysElemStrstoreService sysElemStrstoreService;
   @Resource
   private ISysDictDataService sysDictDataService;

   @PreAuthorize("@ss.hasPermi('system:strstore:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysElemStrstore sysElemStrstore) {
      List<SysElemStrstore> list = null;

      try {
         this.startPage();
         list = this.sysElemStrstoreService.selectSysElemStrstoreList(sysElemStrstore);
      } catch (Exception e) {
         this.log.error("查询病历结构化存储元素列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:strstore:export')")
   @Log(
      title = "病历结构化存储元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysElemStrstore sysElemStrstore) {
      List<SysElemStrstore> list = this.sysElemStrstoreService.selectSysElemStrstoreList(sysElemStrstore);
      ExcelUtil<SysElemStrstore> util = new ExcelUtil(SysElemStrstore.class);
      return util.exportExcel(list, "病历结构化存储元素数据");
   }

   @PreAuthorize("@ss.hasPermi('system:strstore:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysElemStrstoreService.selectSysElemStrstoreById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:strstore:add')")
   @Log(
      title = "病历结构化存储元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysElemStrstore sysElemStrstore) {
      return this.toAjax(this.sysElemStrstoreService.insertSysElemStrstore(sysElemStrstore));
   }

   @PreAuthorize("@ss.hasPermi('system:strstore:edit')")
   @Log(
      title = "病历结构化存储元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysElemStrstore sysElemStrstore) {
      return this.toAjax(this.sysElemStrstoreService.updateSysElemStrstore(sysElemStrstore));
   }

   @PreAuthorize("@ss.hasPermi('system:strstore:remove')")
   @Log(
      title = "病历结构化存储元素",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sysElemStrstoreService.deleteSysElemStrstoreByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('system:strstore:addStrstoreElem')")
   @Log(
      title = "病历结构化存储元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/addStrstoreElem"})
   public AjaxResult addStrstoreElem(@RequestBody SysElemStrstore sysElemStrstore) {
      AjaxResult ajaxResult = AjaxResult.success("病历共享引用元素添加成功");
      Boolean flag = true;

      try {
         if (flag && sysElemStrstore.getTempType() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && sysElemStrstore.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && sysElemStrstore.getElemName() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && sysElemStrstore.getElemCd() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            SysElemStrstore param = new SysElemStrstore();
            param.setElemId(sysElemStrstore.getElemId());
            param.setTempType(sysElemStrstore.getTempType());
            param.setIsValid("1");
            List<SysElemStrstore> list = this.sysElemStrstoreService.selectSysElemStrstoreList(param);
            if (flag && list != null && list.size() > 0) {
               flag = false;
               ajaxResult = AjaxResult.error("病历共享引用元素已添加，不能重复添加");
            } else {
               this.sysElemStrstoreService.insertSysElemStrstore(sysElemStrstore);
            }
         }
      } catch (Exception e) {
         this.log.error("添加病历结构化存储元素出现异常：", e);
         ajaxResult = AjaxResult.error("添加病历结构化存储元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:strstore:elemStrstoreList')")
   @GetMapping({"/elemStrstoreList/{tempType}"})
   public TableDataInfo elemStrstoreList(@PathVariable("tempType") String tempType) {
      List<SysElemStrstore> list = null;

      try {
         this.startPage();
         SysElemStrstore sysElemStrstore = new SysElemStrstore();
         sysElemStrstore.setTempType(tempType);
         list = this.sysElemStrstoreService.selectSysElemStrstoreList(sysElemStrstore);
      } catch (Exception e) {
         this.log.error("查询病历结构化存储元素列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:strstore:tempList')")
   @GetMapping({"/tempList"})
   public TableDataInfo tempList(SysDictData sysDictData) {
      List<SysDictData> list = null;
      sysDictData.setDictType("s004");

      try {
         this.startPage();
         list = this.sysDictDataService.selectDictDataList(sysDictData);
      } catch (Exception e) {
         this.log.error("查询病历类型列表列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('system:strstore:tempTypeList,tmpl:subtempl:list')")
   @GetMapping({"/tempTypeList/{tempType}"})
   public AjaxResult tempTypeList(@PathVariable("tempType") String tempType) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         SysElemStrstore sysElemStrstore = new SysElemStrstore();
         sysElemStrstore.setTempType(tempType);
         List<SysElemStrstore> list = this.sysElemStrstoreService.selectSysElemStrstoreList(sysElemStrstore);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询病历结构化存储元素列表出现异常", e);
         ajaxResult = AjaxResult.error("查询病历结构化存储元素列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:strstore:edit')")
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody SysElemStrstore sysElemStrstore) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (sysElemStrstore == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && sysElemStrstore.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数id不能为空");
         }

         if (flag && StringUtils.isEmpty(sysElemStrstore.getRequFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数状态不能为空");
         }

         if (flag) {
            this.sysElemStrstoreService.updateSysElemStrstore(sysElemStrstore);
         }
      } catch (Exception e) {
         this.log.error("必有状态修改出现异常", e);
         ajaxResult = AjaxResult.error("必有状态修改出现异常");
      }

      return ajaxResult;
   }
}
