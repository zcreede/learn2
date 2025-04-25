package com.emr.project.system.controller;

import com.emr.common.exception.CustomException;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.BasTmplDisease;
import com.emr.project.system.service.IBasTmplDiseaseService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping({"/system/disease"})
public class BasTmplDiseaseController extends BaseController {
   @Autowired
   private IBasTmplDiseaseService basTmplDiseaseService;

   @PreAuthorize("@ss.hasPermi('system:disease:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(BasTmplDisease basTmplDisease) {
      List<BasTmplDisease> list = null;

      try {
         this.startPage();
         list = this.basTmplDiseaseService.selectBasTmplDiseaseList(basTmplDisease);
      } catch (Exception e) {
         this.log.error("查询模板病种列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:disease:export')")
   @Log(
      title = "模板病种",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BasTmplDisease basTmplDisease) {
      List<BasTmplDisease> list = this.basTmplDiseaseService.selectBasTmplDiseaseList(basTmplDisease);
      ExcelUtil<BasTmplDisease> util = new ExcelUtil(BasTmplDisease.class);
      return util.exportExcel(list, "模板病种数据");
   }

   @PreAuthorize("@ss.hasPermi('system:disease:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.basTmplDiseaseService.selectBasTmplDiseaseById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:disease:add')")
   @Log(
      title = "模板病种",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BasTmplDisease basTmplDisease) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (flag && basTmplDisease == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(basTmplDisease.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择专科");
         }

         if (flag && StringUtils.isBlank(basTmplDisease.getName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写病种名称");
         }

         if (flag && StringUtils.isBlank(basTmplDisease.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写病种名称拼音码");
         }

         if (flag && StringUtils.isBlank(basTmplDisease.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写启用状态");
         }

         if (flag) {
            this.basTmplDiseaseService.insertBasTmplDisease(basTmplDisease);
         }
      } catch (CustomException e) {
         this.log.error("新增模板病种出现异常", e);
         ajaxResult = AjaxResult.error(e.getMessage());
      } catch (Exception e) {
         this.log.error("新增模板病种出现异常", e);
         ajaxResult = AjaxResult.error("新增模板病种出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:disease:edit')")
   @Log(
      title = "模板病种",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BasTmplDisease basTmplDisease) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (flag && basTmplDisease == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && basTmplDisease.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(basTmplDisease.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择专科");
         }

         if (flag && StringUtils.isBlank(basTmplDisease.getName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写病种名称");
         }

         if (flag && StringUtils.isBlank(basTmplDisease.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写病种名称拼音码");
         }

         if (flag && StringUtils.isBlank(basTmplDisease.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写启用状态");
         }

         if (flag) {
            this.basTmplDiseaseService.updateBasTmplDisease(basTmplDisease);
         }
      } catch (CustomException e) {
         this.log.error("新增模板病种出现异常", e);
         ajaxResult = AjaxResult.error(e.getMessage());
      } catch (Exception e) {
         this.log.error("新增模板病种出现异常", e);
         ajaxResult = AjaxResult.error("新增模板病种出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:disease:remove')")
   @Log(
      title = "模板病种",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      return this.toAjax(this.basTmplDiseaseService.deleteBasTmplDiseaseById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:disease:changeValidFlag')")
   @Log(
      title = "模板病种",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/changeValidFlag"})
   public AjaxResult changeValidFlag(Long id, String validFlag) {
      AjaxResult ajaxResult = AjaxResult.success("操作成功");
      boolean flag = true;

      try {
         if (flag && id == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(validFlag)) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择专科");
         }

         if (flag) {
            BasTmplDisease basTmplDisease = this.basTmplDiseaseService.selectBasTmplDiseaseById(id);
            if (basTmplDisease == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这个病种，请确认后再修改");
            }
         }

         if (flag) {
            BasTmplDisease param = new BasTmplDisease();
            param.setId(id);
            param.setValidFlag(validFlag);
            this.basTmplDiseaseService.updateBasTmplDiseaseValidFlag(param);
         }
      } catch (Exception e) {
         this.log.error("病种启用状态修改出现异常, ", e);
         ajaxResult = AjaxResult.error("病种启用状态修改出现异常");
      }

      return ajaxResult;
   }

   @ApiOperation("获取病种下拉列表")
   @PreAuthorize("@ss.hasAnyPermi('system:disease:diseaseList,emr:index:add,tmpl:index:add,pat:record:queryList,tmpl:auditRecord:queryList,tmpl:index:edit')")
   @GetMapping({"/diseaseList"})
   public AjaxResult selectDiseaseList(BasTmplDisease basTmplDisease) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         List<BasTmplDisease> basTmplDiseases = this.basTmplDiseaseService.selectDiseaseList(basTmplDisease);
         ajaxResult = AjaxResult.success((Object)basTmplDiseases);
      } catch (Exception e) {
         this.log.error("获取病种下拉列表出现异常", e);
         ajaxResult = AjaxResult.error("获取病种下拉列表出现异常");
      }

      return ajaxResult;
   }
}
