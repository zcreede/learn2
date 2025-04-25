package com.emr.project.pat.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.pat.service.IAlleInfoService;
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
@RequestMapping({"/pat/alleinfo"})
public class AlleInfoController extends BaseController {
   @Autowired
   private IAlleInfoService alleInfoService;

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/list"})
   public TableDataInfo list(AlleInfo alleInfo) {
      new ArrayList();
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isEmpty(alleInfo.getPatientMainId())) {
            tableDataInfo = new TableDataInfo(500, "患者主索引id不能为空");
         } else {
            this.startPage();
            List list = this.alleInfoService.selectAlleInfoList(alleInfo);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("查询国民史信息列表异常", e);
         tableDataInfo = new TableDataInfo(500, "查询国民史信息列表异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('pat:alleinfo:add')")
   @Log(
      title = "过敏史信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody AlleInfo alleInfo) {
      return this.toAjax(this.alleInfoService.insertAlleInfo(alleInfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:alleinfo:edit')")
   @Log(
      title = "过敏史信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody AlleInfo alleInfo) {
      return this.toAjax(this.alleInfoService.updateAlleInfo(alleInfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:alleinfo:remove')")
   @Log(
      title = "过敏史信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable String[] ids) {
      return this.toAjax(this.alleInfoService.deleteAlleInfoByIds(ids));
   }
}
