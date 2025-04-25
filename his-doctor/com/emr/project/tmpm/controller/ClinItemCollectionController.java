package com.emr.project.tmpm.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.ClinItemCollection;
import com.emr.project.tmpm.domain.vo.ClinItemCollectionVo;
import com.emr.project.tmpm.service.IClinItemCollectionService;
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
@RequestMapping({"/clin/collection"})
public class ClinItemCollectionController extends BaseController {
   @Autowired
   private IClinItemCollectionService clinItemCollectionService;

   @PreAuthorize("@ss.hasPermi('clin:collection:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ClinItemCollection clinItemCollection) {
      this.startPage();
      List<ClinItemCollection> list = this.clinItemCollectionService.selectClinItemCollectionList(clinItemCollection);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('clin:collection:export')")
   @Log(
      title = "个人收藏临床项目",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ClinItemCollection clinItemCollection) {
      List<ClinItemCollection> list = this.clinItemCollectionService.selectClinItemCollectionList(clinItemCollection);
      ExcelUtil<ClinItemCollection> util = new ExcelUtil(ClinItemCollection.class);
      return util.exportExcel(list, "个人收藏临床项目数据");
   }

   @PreAuthorize("@ss.hasPermi('clin:collection:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.clinItemCollectionService.selectClinItemCollectionById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('clin:collection:add')")
   @Log(
      title = "个人收藏临床项目",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ClinItemCollection clinItemCollection) {
      AjaxResult ajaxResult = AjaxResult.success("收藏成功");
      boolean flag = true;

      try {
         if (clinItemCollection == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(clinItemCollection.getItemClassCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目类别编码不能为空");
         }

         if (flag && StringUtils.isEmpty(clinItemCollection.getItemClassName())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目类别名称不能为空");
         }

         if (flag && StringUtils.isEmpty(clinItemCollection.getItemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目编码不能为空");
         }

         if (flag && StringUtils.isEmpty(clinItemCollection.getItemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目名称不能为空");
         }

         if (flag) {
            this.clinItemCollectionService.insertClinItemCollection(clinItemCollection);
         }
      } catch (Exception e) {
         this.log.error("新增个人收藏临床项目出现异常", e);
         ajaxResult = AjaxResult.error("新增个人收藏临床项目出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('clin:collection:edit')")
   @Log(
      title = "个人收藏临床项目",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ClinItemCollection clinItemCollection) {
      return this.toAjax(this.clinItemCollectionService.updateClinItemCollection(clinItemCollection));
   }

   @PreAuthorize("@ss.hasAnyPermi('clin:collection:remove,clin:collection:add')")
   @Log(
      title = "个人收藏临床项目",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/delete"})
   public AjaxResult remove(@RequestBody ClinItemCollection clinItemCollection) {
      AjaxResult ajaxResult = AjaxResult.success("取消收藏成功");

      try {
         if (StringUtils.isEmpty(clinItemCollection.getItemCd())) {
            ajaxResult = AjaxResult.error("项目编码不能为空");
         } else {
            this.clinItemCollectionService.deleteClinItemCollectionByItemCd(clinItemCollection);
         }
      } catch (Exception e) {
         this.log.error("取消收藏出现异常", e);
         ajaxResult = AjaxResult.error("取消收藏出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('clin:collection:list,docOrder:order:list')")
   @GetMapping({"/commonDrugList"})
   public AjaxResult commonDrugList(ClinItemCollection clinItemCollection) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         clinItemCollection.setHerbalFlag("0");
         clinItemCollection.setItemClassCd("01");
         List<ClinItemCollectionVo> drugList = this.clinItemCollectionService.selectCommonDrugList(clinItemCollection);
         ajaxResult = AjaxResult.success((Object)drugList);
      } catch (Exception e) {
         this.log.error("查询收藏常用药品出现异常", e);
         ajaxResult = AjaxResult.error("查询收藏常用药品出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('clin:collection:list,docOrder:order:list')")
   @GetMapping({"/unDrugItemList"})
   public AjaxResult unDrugItemList(ClinItemCollection clinItemCollection) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<ClinItemCollection> drugList = this.clinItemCollectionService.selectUnDrugItemList(clinItemCollection);
         ajaxResult = AjaxResult.success((Object)drugList);
      } catch (Exception e) {
         this.log.error("查询收藏常用项目出现异常", e);
         ajaxResult = AjaxResult.error("查询收藏常用项目出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('clin:collection:list,docOrder:order:list')")
   @GetMapping({"/setItemList"})
   public AjaxResult setItemList(ClinItemCollection clinItemCollection) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<ClinItemCollectionVo> drugList = this.clinItemCollectionService.selectSetItemList(clinItemCollection);
         ajaxResult = AjaxResult.success((Object)drugList);
      } catch (Exception e) {
         this.log.error("查询收藏常用组套出现异常", e);
         ajaxResult = AjaxResult.error("查询收藏常用组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('clin:collection:list,docOrder:herb:list')")
   @GetMapping({"/herbList"})
   public AjaxResult herbList(ClinItemCollection clinItemCollection) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<ClinItemCollectionVo> drugList = this.clinItemCollectionService.selectHerbList(clinItemCollection);
         ajaxResult = AjaxResult.success((Object)drugList);
      } catch (Exception e) {
         this.log.error("查询常用中草药出现异常", e);
         ajaxResult = AjaxResult.error("查询常用中草药出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('clin:collection:list,docOrder:herb:list')")
   @GetMapping({"/recipeList"})
   public AjaxResult recipeList(ClinItemCollection clinItemCollection) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<ClinItemCollectionVo> drugList = this.clinItemCollectionService.selectRecipeList(clinItemCollection);
         ajaxResult = AjaxResult.success((Object)drugList);
      } catch (Exception e) {
         this.log.error("查询常用协定处方出现异常", e);
         ajaxResult = AjaxResult.error("查询常用协定处方出现异常");
      }

      return ajaxResult;
   }
}
