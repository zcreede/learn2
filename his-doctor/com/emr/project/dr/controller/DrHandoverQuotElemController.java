package com.emr.project.dr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.DrHandoverQuotElem;
import com.emr.project.dr.service.IDrHandoverQuotElemService;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysElemStrstore;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysElemStrstoreService;
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
@RequestMapping({"/handover/elem"})
public class DrHandoverQuotElemController extends BaseController {
   @Autowired
   private IDrHandoverQuotElemService drHandoverQuotElemService;
   @Autowired
   private ISysDictDataService dictDataService;
   @Autowired
   private ISysElemStrstoreService sysElemStrstoreService;

   @PreAuthorize("@ss.hasPermi('handover:elem:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DrHandoverQuotElem drHandoverQuotElem) {
      this.startPage();
      List<DrHandoverQuotElem> list = this.drHandoverQuotElemService.selectDrHandoverQuotElemList(drHandoverQuotElem);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('dr:elem:export')")
   @Log(
      title = "患者病情摘要配置",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DrHandoverQuotElem drHandoverQuotElem) {
      List<DrHandoverQuotElem> list = this.drHandoverQuotElemService.selectDrHandoverQuotElemList(drHandoverQuotElem);
      ExcelUtil<DrHandoverQuotElem> util = new ExcelUtil(DrHandoverQuotElem.class);
      return util.exportExcel(list, "患者病情摘要配置数据");
   }

   @PreAuthorize("@ss.hasPermi('dr:elem:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.drHandoverQuotElemService.selectDrHandoverQuotElemById(id));
   }

   @PreAuthorize("@ss.hasPermi('handover:elem:add')")
   @Log(
      title = "患者病情摘要配置",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrHandoverQuotElem drHandoverQuotElem) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromMrTypeCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用病历配置类型编码不能为空，请选择后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromMrTypeCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用病历配置类型编码不能为空，请选择后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromMrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用病历配置类型不能为空，请选择后再保存");
         }

         if (flag && drHandoverQuotElem.getFromElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("引用元素ID不能为空，请选择后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用元素编码不能为空，请选择后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用元素名称不能为空，请选择后再保存");
         }

         if (flag && drHandoverQuotElem.getElemOrder() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("排序不能为空，请选择后再保存");
         }

         if (flag) {
            this.drHandoverQuotElemService.insertDrHandoverQuotElem(drHandoverQuotElem);
         }
      } catch (Exception e) {
         this.log.error("新增患者病情摘要配置出现异常,", e);
         ajaxResult = AjaxResult.error("新增患者病情摘要配置出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:elem:edit')")
   @Log(
      title = "患者病情摘要配置",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrHandoverQuotElem drHandoverQuotElem) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      Boolean flag = true;

      try {
         if (flag && drHandoverQuotElem.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病情摘要配置id不能为空");
         }

         DrHandoverQuotElem quotElem = flag ? this.drHandoverQuotElemService.selectDrHandoverQuotElemById(drHandoverQuotElem.getId()) : null;
         if (flag && quotElem == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者病情摘要配置记录");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromMrTypeCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用病历配置类型编码不能为空，请选择后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromMrTypeCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用病历配置类型编码不能为空，请选择后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromMrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用病历配置类型不能为空，请选择后再保存");
         }

         if (flag && drHandoverQuotElem.getFromElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("引用元素ID不能为空，请选择后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用元素编码不能为空，请选择后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverQuotElem.getFromElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("引用元素名称不能为空，请选择后再保存");
         }

         if (flag && drHandoverQuotElem.getElemOrder() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("排序不能为空，请选择后再保存");
         }

         if (flag) {
            this.drHandoverQuotElemService.updateDrHandoverQuotElem(drHandoverQuotElem);
         }
      } catch (Exception e) {
         this.log.error("修改患者病情摘要配置出现异常,", e);
         ajaxResult = AjaxResult.error("修改患者病情摘要配置出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:elem:remove')")
   @Log(
      title = "患者病情摘要配置",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.drHandoverQuotElemService.deleteDrHandoverQuotElemByIds(ids);
      } catch (Exception e) {
         this.log.error("删除患者病情摘要配置出现异常,", e);
         ajaxResult = AjaxResult.error("删除患者病情摘要配置出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('handover:type:add,handover:type:edit')")
   @GetMapping({"/mrTypes"})
   public AjaxResult mrTypes(String dictLabel) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<SysDictData> list = this.dictDataService.selectDictDataByType("s004", dictLabel);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询引用病历类型出现异常，", e);
         ajaxResult = AjaxResult.error("查询引用病历类型出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('handover:type:add,handover:type:edit')")
   @GetMapping({"/elems"})
   public AjaxResult elems(DrHandoverQuotElem drHandoverQuotElem) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         SysElemStrstore param = new SysElemStrstore();
         param.setElemName(drHandoverQuotElem.getFromElemName());
         param.setIsValid("1");
         param.setTempType(drHandoverQuotElem.getFromMrTypeCd());
         List<SysElemStrstore> list = this.sysElemStrstoreService.selectSysElemStrstoreList(param);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询元素出现异常，", e);
         ajaxResult = AjaxResult.error("查询元素出现异常");
      }

      return ajaxResult;
   }
}
