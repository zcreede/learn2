package com.emr.project.dr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.DrHandoverPatientType;
import com.emr.project.dr.service.IDrHandoverPatientTypeService;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.service.ISysDictDataService;
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
@RequestMapping({"/handover/type"})
public class DrHandoverPatientTypeController extends BaseController {
   @Autowired
   private IDrHandoverPatientTypeService drHandoverPatientTypeService;
   @Autowired
   private ISysDictDataService dictDataService;

   @PreAuthorize("@ss.hasPermi('handover:type:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DrHandoverPatientType drHandoverPatientType) {
      List<DrHandoverPatientType> list = null;

      try {
         this.startPage();
         list = this.drHandoverPatientTypeService.selectDrHandoverPatientTypeList(drHandoverPatientType);
      } catch (Exception e) {
         this.log.error("查询交接班患者标识列表出现异常，", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('dr:type:export')")
   @Log(
      title = "交接班患者标识",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DrHandoverPatientType drHandoverPatientType) {
      List<DrHandoverPatientType> list = this.drHandoverPatientTypeService.selectDrHandoverPatientTypeList(drHandoverPatientType);
      ExcelUtil<DrHandoverPatientType> util = new ExcelUtil(DrHandoverPatientType.class);
      return util.exportExcel(list, "交接班患者标识数据");
   }

   @PreAuthorize("@ss.hasPermi('dr:type:query')")
   @GetMapping({"/{typeCode}"})
   public AjaxResult getInfo(@PathVariable("typeCode") Long typeCode) {
      return AjaxResult.success((Object)this.drHandoverPatientTypeService.selectDrHandoverPatientTypeById(typeCode));
   }

   @PreAuthorize("@ss.hasPermi('handover:type:add')")
   @Log(
      title = "交接班患者标识",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrHandoverPatientType drHandoverPatientType) {
      AjaxResult ajaxResult = AjaxResult.success("新增交接班患者标识成功");
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(drHandoverPatientType.getTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识名称不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverPatientType.getDataSql())) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识sql语句不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverPatientType.getNumColumn())) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识对应人数字段不能为空，请填写后再保存");
         }

         if (flag && drHandoverPatientType.getSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识排序不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverPatientType.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识状态不能为空，请填写后再保存");
         }

         if (flag) {
            this.drHandoverPatientTypeService.insertDrHandoverPatientType(drHandoverPatientType);
         }
      } catch (Exception e) {
         this.log.error("新增交接班患者标识出现异常,", e);
         ajaxResult = AjaxResult.error("新增交接班患者标识出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:type:edit')")
   @Log(
      title = "交接班患者标识",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrHandoverPatientType drHandoverPatientType) {
      AjaxResult ajaxResult = AjaxResult.success("修改交接班患者标识成功");
      Boolean flag = true;

      try {
         if (flag && drHandoverPatientType.getTypeCode() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识编号不能为空");
         }

         DrHandoverPatientType temp = flag ? this.drHandoverPatientTypeService.selectDrHandoverPatientTypeById(drHandoverPatientType.getTypeCode()) : null;
         if (flag && temp == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有交接班患者标识记录，不能修改");
         }

         if (flag && StringUtils.isBlank(drHandoverPatientType.getTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识名称不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverPatientType.getDataSql())) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识sql语句不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverPatientType.getNumColumn())) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识对应人数字段不能为空，请填写后再保存");
         }

         if (flag && drHandoverPatientType.getSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识排序不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(drHandoverPatientType.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班患者标识状态不能为空，请填写后再保存");
         }

         if (flag) {
            this.drHandoverPatientTypeService.updateDrHandoverPatientType(drHandoverPatientType);
         }
      } catch (Exception e) {
         this.log.error("新增交接班患者标识出现异常,", e);
         ajaxResult = AjaxResult.error("新增交接班患者标识出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:type:remove')")
   @Log(
      title = "交接班患者标识",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{typeCodes}"})
   public AjaxResult remove(@PathVariable Long[] typeCodes) {
      AjaxResult ajaxResult = AjaxResult.success("删除交接班患者标识成功");

      try {
         this.drHandoverPatientTypeService.deleteDrHandoverPatientTypeByIds(typeCodes);
      } catch (Exception e) {
         this.log.error("删除交接班患者标识出现异常，", e);
         ajaxResult = AjaxResult.error("删除交接班患者标识出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:type:add')")
   @GetMapping({"/numColumn"})
   public AjaxResult numColumn() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<SysDictData> list = this.dictDataService.selectDictDataByType("s059");
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询对应人数字段出现异常，", e);
         ajaxResult = AjaxResult.error("查询对应人数字段出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:type:changeEnabled')")
   @PostMapping({"/changeEnabled"})
   public AjaxResult changeEnabled(@RequestBody DrHandoverPatientType drHandoverPatientType) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      Boolean flag = true;

      try {
         if (flag && drHandoverPatientType.getTypeCode() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者标识编码不能为空");
         }

         DrHandoverPatientType temp = flag ? this.drHandoverPatientTypeService.selectDrHandoverPatientTypeById(drHandoverPatientType.getTypeCode()) : null;
         if (flag && temp == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者标识记录");
         }

         if (flag && StringUtils.isBlank(drHandoverPatientType.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者标识状态不能为空");
         }

         if (flag) {
            this.drHandoverPatientTypeService.updateDrHandoverPatientType(drHandoverPatientType);
         }
      } catch (Exception e) {
         this.log.error("修改患者标识状态出现异常，", e);
         ajaxResult = AjaxResult.error("修改患者标识状态出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:type:add')")
   @GetMapping({"/dataSql"})
   public AjaxResult checkUpDataSql(String dataSql) {
      return AjaxResult.success("检查成功");
   }
}
