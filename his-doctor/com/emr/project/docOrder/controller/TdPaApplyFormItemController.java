package com.emr.project.docOrder.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormItemVo;
import com.emr.project.docOrder.service.ITdPaApplyFormItemService;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.service.IClinItemMainService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping({"/tmpm/item"})
public class TdPaApplyFormItemController extends BaseController {
   @Autowired
   private ITdPaApplyFormItemService tdPaApplyFormItemService;
   @Autowired
   private IClinItemMainService clinItemMainService;

   @PreAuthorize("@ss.hasPermi('tmpm:item:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaApplyFormItem tdPaApplyFormItem) {
      this.startPage();
      List<TdPaApplyFormItem> list = this.tdPaApplyFormItemService.selectTdPaApplyFormItemList(tdPaApplyFormItem);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpm:item:export')")
   @Log(
      title = "检查检验申请单项目",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaApplyFormItem tdPaApplyFormItem) {
      List<TdPaApplyFormItem> list = this.tdPaApplyFormItemService.selectTdPaApplyFormItemList(tdPaApplyFormItem);
      ExcelUtil<TdPaApplyFormItem> util = new ExcelUtil(TdPaApplyFormItem.class);
      return util.exportExcel(list, "检查检验申请单项目数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpm:item:add')")
   @Log(
      title = "检查检验申请单项目",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaApplyFormItem tdPaApplyFormItem) {
      return this.toAjax(this.tdPaApplyFormItemService.insertTdPaApplyFormItem(tdPaApplyFormItem));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:item:edit')")
   @Log(
      title = "检查检验申请单项目",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaApplyFormItem tdPaApplyFormItem) {
      return this.toAjax(this.tdPaApplyFormItemService.updateTdPaApplyFormItem(tdPaApplyFormItem));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:item:remove')")
   @Log(
      title = "检查检验申请单项目",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{applyFormNos}"})
   public AjaxResult remove(@PathVariable String[] applyFormNos) {
      return this.toAjax(this.tdPaApplyFormItemService.deleteTdPaApplyFormItemByIds(applyFormNos));
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpm:form:query,docOrder:check:list')")
   @GetMapping({"/applyItemList"})
   public AjaxResult applyItemList(TdPaApplyFormItem tdPaApplyFormItem) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(tdPaApplyFormItem.getApplyFormNo())) {
            ajaxResult = AjaxResult.error("申请单编号不能为空");
         } else {
            List<TdPaApplyFormItemVo> list = this.tdPaApplyFormItemService.selectTdPaApplyFormItemByFormNo(tdPaApplyFormItem.getApplyFormNo());
            if (CollectionUtils.isNotEmpty(list)) {
               List<String> itemCdList = (List)list.stream().map((t) -> t.getItemCode()).distinct().collect(Collectors.toList());
               List<ClinItemMain> clinItemMainList = this.clinItemMainService.selectClinItemMainByItemCds(itemCdList);
               Map<String, List<ClinItemMain>> clinItemMainMap = (Map)clinItemMainList.stream().collect(Collectors.groupingBy((t) -> t.getItemCd()));
               list.stream().forEach((t) -> {
                  List<ClinItemMain> clinItemListTemp = (List)clinItemMainMap.get(t.getItemCode());
                  ClinItemMain clinItemMain = CollectionUtils.isNotEmpty(clinItemListTemp) ? (ClinItemMain)clinItemListTemp.get(0) : null;
                  if (clinItemMain != null) {
                     t.setIndication(clinItemMain.getIndication());
                     t.setExamNote(clinItemMain.getExamNote());
                     t.setSpecCollectionReq(clinItemMain.getSpecCollectionReq());
                     t.setExamSign(clinItemMain.getExamSign());
                     t.setSupportDiag(clinItemMain.getSupportDiag());
                     t.setExclusionDiag(clinItemMain.getExclusionDiag());
                  }

               });
            }

            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据申请单编号获取项目集合出现异常", e);
         ajaxResult = AjaxResult.error("根据申请单编号获取项目集合出现异常");
      }

      return ajaxResult;
   }
}
