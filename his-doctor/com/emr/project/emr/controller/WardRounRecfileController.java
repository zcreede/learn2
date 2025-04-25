package com.emr.project.emr.controller;

import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.config.EMRConfig;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.WardRounRecfile;
import com.emr.project.emr.service.IWardRounRecfileService;
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
@RequestMapping({"/system/recfile"})
public class WardRounRecfileController extends BaseController {
   @Autowired
   private IWardRounRecfileService wardRounRecfileService;
   @Autowired
   private EMRConfig emrConfig;

   @PreAuthorize("@ss.hasPermi('system:recfile:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(WardRounRecfile wardRounRecfile) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isNotEmpty(wardRounRecfile.getPatientId())) {
            this.startPage();
            List<WardRounRecfile> list = this.wardRounRecfileService.selectWardRounRecfileList(wardRounRecfile);
            tableDataInfo = this.getDataTable(list);
            tableDataInfo.setObject(this.emrConfig.getWardFileType());
            tableDataInfo.setResult(IPAddressUtil.getLocalIp());
         } else {
            tableDataInfo = new TableDataInfo(500, "患者id不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询查房录音记录列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询查房录音记录列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:recfile:list,emr:index:list')")
   @GetMapping({"/queryList"})
   public AjaxResult queryList(WardRounRecfile wardRounRecfile) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isNotEmpty(wardRounRecfile.getPatientId())) {
            List<WardRounRecfile> list = this.wardRounRecfileService.selectWardRounRecfileList(wardRounRecfile);
            ajaxResult = AjaxResult.success((Object)list);
         } else {
            ajaxResult = AjaxResult.error("患者id不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询查房录音记录列表出现异常", e);
         ajaxResult = AjaxResult.error("查询查房录音记录列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:recfile:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.wardRounRecfileService.selectWardRounRecfileById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:recfile:add')")
   @Log(
      title = "查房录音记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody WardRounRecfile wardRounRecfile) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (wardRounRecfile == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getFileName())) {
            flag = false;
            ajaxResult = AjaxResult.error("文件名称不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getPatientName())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者名称不能为空");
         }

         if (flag && wardRounRecfile.getRecTime() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("录音时间不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getFilePath())) {
            flag = false;
            ajaxResult = AjaxResult.error("录音文件地址不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getRecCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("录音人编码不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getRecName())) {
            flag = false;
            ajaxResult = AjaxResult.error("录音人姓名不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getRecTerm())) {
            flag = false;
            ajaxResult = AjaxResult.error("录音终端地址不能为空");
         }

         if (flag) {
            this.wardRounRecfileService.insertWardRounRecfile(wardRounRecfile);
         }
      } catch (Exception e) {
         this.log.error("新增查房录音记录出现异常", e);
         ajaxResult = AjaxResult.error("新增查房录音记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:recfile:edit')")
   @Log(
      title = "查房录音记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody WardRounRecfile wardRounRecfile) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (wardRounRecfile == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && wardRounRecfile.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getFileName())) {
            flag = false;
            ajaxResult = AjaxResult.error("文件名称不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getPatientName())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者名称不能为空");
         }

         if (flag && wardRounRecfile.getRecTime() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("录音时间不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getFilePath())) {
            flag = false;
            ajaxResult = AjaxResult.error("录音文件地址不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getRecCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("录音人编码不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getRecName())) {
            flag = false;
            ajaxResult = AjaxResult.error("录音人姓名不能为空");
         }

         if (flag && StringUtils.isEmpty(wardRounRecfile.getRecTerm())) {
            flag = false;
            ajaxResult = AjaxResult.error("录音终端地址不能为空");
         }

         if (flag) {
            this.wardRounRecfileService.updateWardRounRecfile(wardRounRecfile);
         }
      } catch (Exception e) {
         this.log.error("修改查房录音记录出现异常", e);
         ajaxResult = AjaxResult.error("修改查房录音记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:recfile:remove')")
   @Log(
      title = "查房录音记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         if (id == null) {
            ajaxResult = AjaxResult.error("id不能为空");
         } else {
            this.wardRounRecfileService.deleteWardRounRecfileById(id);
         }
      } catch (Exception e) {
         this.log.error("删除查房录音记录出现异常", e);
         ajaxResult = AjaxResult.error("删除查房录音记录出现异常");
      }

      return ajaxResult;
   }
}
