package com.emr.project.tmpm.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.DiagSetMain;
import com.emr.project.tmpm.domain.vo.DiagSetMainVo;
import com.emr.project.tmpm.service.IDiagSetMainService;
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
@RequestMapping({"/diagSet/main"})
public class DiagSetMainController extends BaseController {
   @Autowired
   private IDiagSetMainService diagSetMainService;

   @PreAuthorize("@ss.hasPermi('diagSet:main:list')")
   @GetMapping({"/pageList"})
   public TableDataInfo pageList(DiagSetMain diagSetMain) {
      this.startPage();
      List<DiagSetMain> list = this.diagSetMainService.selectDiagSetMainList(diagSetMain);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('diagSet:main:list,pat:diag:write')")
   @GetMapping({"/list"})
   public AjaxResult list(DiagSetMain diagSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         diagSetMain.setEnabled("1");
         List<DiagSetMain> list = this.diagSetMainService.selectDiagSetMainList(diagSetMain);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询诊断套餐列表出现异常", e);
         ajaxResult = AjaxResult.error("查询诊断套餐列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('diagSet:main:list,pat:diag:write')")
   @GetMapping({"/getList"})
   public AjaxResult getList(DiagSetMain diagSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<DiagSetMain> list = this.diagSetMainService.selectDiagSetMainList(diagSetMain);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询诊断套餐列表出现异常", e);
         ajaxResult = AjaxResult.error("查询诊断套餐列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('diagSet:main:export')")
   @Log(
      title = "诊断套餐主",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DiagSetMain diagSetMain) {
      List<DiagSetMain> list = this.diagSetMainService.selectDiagSetMainList(diagSetMain);
      ExcelUtil<DiagSetMain> util = new ExcelUtil(DiagSetMain.class);
      return util.exportExcel(list, "诊断套餐主数据");
   }

   @PreAuthorize("@ss.hasPermi('diagSet:main:query')")
   @GetMapping({"/{setCd}"})
   public AjaxResult getInfo(@PathVariable("setCd") String setCd) {
      return AjaxResult.success((Object)this.diagSetMainService.selectDiagSetMainById(setCd));
   }

   @PreAuthorize("@ss.hasPermi('diagSet:main:add')")
   @Log(
      title = "诊断套餐主",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DiagSetMainVo diagSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("添加成功");
      boolean flag = true;

      try {
         if (diagSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套名称不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享类型不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标志不能为空");
         }

         if (flag) {
            this.diagSetMainService.insertDiagSetMain(diagSetMain);
            ajaxResult.put("diagSetMain", diagSetMain);
         }
      } catch (Exception e) {
         this.log.error("新增诊断套餐出现异常", e);
         ajaxResult = AjaxResult.error("新增诊断套餐出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('diagSet:main:edit')")
   @Log(
      title = "诊断套餐主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DiagSetMain diagSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (diagSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套编码不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套名称不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享类型不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标志不能为空");
         }

         if (flag) {
            this.diagSetMainService.updateDiagSetMain(diagSetMain);
         }
      } catch (Exception e) {
         this.log.error("保存诊断套餐出现异常", e);
         ajaxResult = AjaxResult.error("保存诊断套餐出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('diagSet:main:remove')")
   @Log(
      title = "诊断套餐主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{setCd}"})
   public AjaxResult remove(@PathVariable String setCd) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.diagSetMainService.deleteDiagSetMainById(setCd);
      } catch (Exception e) {
         this.log.error("删除诊断主套餐出现异常", e);
         ajaxResult = AjaxResult.error("删除诊断主套餐出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('diagSet:main:saveAs,pat:diag:write')")
   @Log(
      title = "诊断套餐主",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/saveAs"})
   public AjaxResult saveAs(@RequestBody DiagSetMainVo diagSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("另存成功");
      boolean flag = true;

      try {
         if (diagSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套名称不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享类型不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getDiagClass())) {
            flag = false;
            ajaxResult = AjaxResult.error("诊断分类不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用禁用不能为空");
         }

         if (flag) {
            this.diagSetMainService.saveAsDiagSet(diagSetMain);
            ajaxResult.put("diagSetMain", diagSetMain);
         }
      } catch (Exception e) {
         this.log.error("另存为套餐出现异常", e);
         ajaxResult = AjaxResult.error("另存为套餐出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('diagSet:main:edit')")
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody DiagSetMain diagSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (diagSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套编码不能为空");
         }

         if (flag && StringUtils.isEmpty(diagSetMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.diagSetMainService.updateSetMainFlag(diagSetMain);
         }
      } catch (Exception e) {
         this.log.error("修改启用禁用状态出现异常", e);
         ajaxResult = AjaxResult.error("修改启用禁用状态出现异常");
      }

      return ajaxResult;
   }
}
