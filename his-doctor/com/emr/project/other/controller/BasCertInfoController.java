package com.emr.project.other.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.service.IBasCertInfoService;
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
@RequestMapping({"/other/info"})
public class BasCertInfoController extends BaseController {
   @Autowired
   private IBasCertInfoService basCertInfoService;

   @PreAuthorize("@ss.hasPermi('other:info:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(BasCertInfo basCertInfo) {
      this.startPage();
      List<BasCertInfo> list = this.basCertInfoService.selectBasCertInfoList(basCertInfo);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('other:info:export')")
   @Log(
      title = "数字证书信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BasCertInfo basCertInfo) {
      List<BasCertInfo> list = this.basCertInfoService.selectBasCertInfoList(basCertInfo);
      ExcelUtil<BasCertInfo> util = new ExcelUtil(BasCertInfo.class);
      return util.exportExcel(list, "数字证书信息数据");
   }

   @PreAuthorize("@ss.hasPermi('other:info:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.basCertInfoService.selectBasCertInfoById(id));
   }

   @PreAuthorize("@ss.hasPermi('other:info:add')")
   @Log(
      title = "数字证书信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BasCertInfo basCertInfo) {
      AjaxResult ajaxResult = AjaxResult.success("新增数字证书信息成功");
      boolean flag = true;

      try {
         if (flag && basCertInfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(basCertInfo.getCertSn())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写证书唯一标识");
         }

         if (flag && StringUtils.isBlank(basCertInfo.getCertSn())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写证书序列号");
         }

         if (flag) {
            this.basCertInfoService.insertBasCertInfo(basCertInfo);
         }
      } catch (Exception e) {
         this.log.error("新增数字证书信息出现异常,", e);
         ajaxResult = AjaxResult.error("新增数字证书信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('other:info:edit')")
   @Log(
      title = "数字证书信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BasCertInfo basCertInfo) {
      AjaxResult ajaxResult = AjaxResult.success("修改数字证书信息成功");
      boolean flag = true;

      try {
         if (flag && basCertInfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && basCertInfo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写证书唯一标识");
         }

         if (flag && StringUtils.isBlank(basCertInfo.getCertSn())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写证书序列号");
         }

         if (flag) {
            this.basCertInfoService.updateBasCertInfo(basCertInfo);
         }
      } catch (Exception e) {
         this.log.error("修改数字证书信息出现异常，", e);
         ajaxResult = AjaxResult.error("修改数字证书信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('other:info:remove')")
   @Log(
      title = "数字证书信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.basCertInfoService.deleteBasCertInfoByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('other:info:queryByUser')")
   @GetMapping({"/query/{employeenumber}"})
   public AjaxResult getInfo(@PathVariable("employeenumber") String employeenumber) {
      return AjaxResult.success((Object)this.basCertInfoService.selectBasCertInfoByEmployeenumber(employeenumber));
   }
}
