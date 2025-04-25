package com.emr.project.sys.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.sys.domain.QuoteElem;
import com.emr.project.sys.domain.vo.QuoteElemSaveVo;
import com.emr.project.sys.domain.vo.QuoteElemTypeNumVo;
import com.emr.project.sys.service.IQuoteElemService;
import java.util.ArrayList;
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
@RequestMapping({"/sys/quote/elem"})
public class QuoteElemController extends BaseController {
   @Autowired
   private IQuoteElemService quoteElemService;

   @PreAuthorize("@ss.hasPermi('sys:quoteElem:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QuoteElem quoteElem) {
      List<QuoteElem> list = null;

      try {
         this.startPage();
         list = this.quoteElemService.selectQuoteElemList(quoteElem);
      } catch (Exception e) {
         this.log.error("查询病历自动引用元素列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('sys:quoteElem:export')")
   @Log(
      title = "病历自动引用元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QuoteElem quoteElem) {
      List<QuoteElem> list = null;

      try {
         list = this.quoteElemService.selectQuoteElemList(quoteElem);
      } catch (Exception e) {
         this.log.error("病历自动引用元素数据出现异常", e);
      }

      ExcelUtil<QuoteElem> util = new ExcelUtil(QuoteElem.class);
      return util.exportExcel(list, "病历自动引用元素数据");
   }

   @PreAuthorize("@ss.hasPermi('sys:quoteElem:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      QuoteElem quoteElem = null;

      try {
         quoteElem = this.quoteElemService.selectQuoteElemById(id);
      } catch (Exception e) {
         this.log.error("获取病历自动引用元素详细信息出现异常：", e);
      }

      return AjaxResult.success((Object)quoteElem);
   }

   @PreAuthorize("@ss.hasPermi('sys:quoteElem:add')")
   @Log(
      title = "病历自动引用元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QuoteElemSaveVo quoteElemSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && quoteElemSaveVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         List<QuoteElem> list = quoteElemSaveVo.getFromElemList();
         if (flag && quoteElemSaveVo.getTempType() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && quoteElemSaveVo.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(quoteElemSaveVo.getElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(quoteElemSaveVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && (list == null || list != null && list.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         List<QuoteElem> addList = new ArrayList(1);
         if (flag) {
            for(QuoteElem quoteElem : list) {
               if (!StringUtils.isBlank(quoteElem.getFromMrTypeCd()) && quoteElem.getFromElemId() != null && !StringUtils.isBlank(quoteElem.getFromElemCd()) && !StringUtils.isBlank(quoteElem.getFromElemName()) && quoteElem.getElemOrder() != null) {
                  quoteElem.setTempType(quoteElemSaveVo.getTempType());
                  quoteElem.setElemId(quoteElemSaveVo.getElemId());
                  quoteElem.setElemCd(quoteElemSaveVo.getElemCd());
                  quoteElem.setElemName(quoteElemSaveVo.getElemName());
                  quoteElem.setBase64Flag(quoteElemSaveVo.getBase64Flag());
                  addList.add(quoteElem);
               }
            }

            if (addList == null || addList != null && addList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("参数不完整，请重新填写");
            }
         }

         if (flag && StringUtils.isNotBlank(quoteElemSaveVo.getBase64Flag()) && quoteElemSaveVo.getBase64Flag().equals("1") && addList.size() > 1) {
            flag = false;
            ajaxResult = AjaxResult.error("使用base64引用时，引用元素只能有一个");
         }

         if (flag) {
            this.quoteElemService.insertQuoteElemList(addList);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("新增病历自动引用元素出现异常");
         this.log.error("新增病历自动引用元素出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('sys:quoteElem:edit')")
   @Log(
      title = "病历自动引用元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QuoteElemSaveVo quoteElemSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (flag && quoteElemSaveVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         List<QuoteElem> list = quoteElemSaveVo.getFromElemList();
         if (flag && quoteElemSaveVo.getTempType() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && quoteElemSaveVo.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(quoteElemSaveVo.getElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(quoteElemSaveVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && (list == null || list != null && list.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         List<QuoteElem> addList = new ArrayList(1);
         if (flag) {
            for(QuoteElem quoteElem : list) {
               if (!StringUtils.isBlank(quoteElem.getFromMrTypeCd()) && quoteElem.getFromElemId() != null && !StringUtils.isBlank(quoteElem.getFromElemCd()) && !StringUtils.isBlank(quoteElem.getFromElemName()) && quoteElem.getElemOrder() != null) {
                  quoteElem.setTempType(quoteElemSaveVo.getTempType());
                  quoteElem.setElemId(quoteElemSaveVo.getElemId());
                  quoteElem.setElemCd(quoteElemSaveVo.getElemCd());
                  quoteElem.setElemName(quoteElemSaveVo.getElemName());
                  quoteElem.setBase64Flag(quoteElemSaveVo.getBase64Flag());
                  addList.add(quoteElem);
               }
            }

            if (addList == null || addList != null && addList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("参数不完整，请重新填写");
            }
         }

         if (flag && StringUtils.isNotBlank(quoteElemSaveVo.getBase64Flag()) && quoteElemSaveVo.getBase64Flag().equals("1") && addList.size() > 1) {
            flag = false;
            ajaxResult = AjaxResult.error("使用base64引用时，引用元素只能有一个");
         }

         if (flag) {
            this.quoteElemService.updateQuoteElemList(quoteElemSaveVo, addList);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("修改病历自动引用元素出现异常：");
         this.log.error("修改病历自动引用元素出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('sys:quoteElem:remove')")
   @Log(
      title = "查询病历自动引用元素中病历类型及引用元素数量列表",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      Integer res = null;

      try {
         res = this.quoteElemService.deleteQuoteElemByIds(ids);
      } catch (Exception e) {
         this.log.error("查询病历自动引用元素中病历类型及引用元素数量列表出现异常：", e);
      }

      return this.toAjax(res);
   }

   @GetMapping({"/typeNumlist"})
   @PreAuthorize("@ss.hasPermi('sys:quoteElem:list')")
   public TableDataInfo typeNumList(QuoteElemTypeNumVo quoteElemTypeNumVo) {
      List<QuoteElemTypeNumVo> list = null;

      try {
         this.startPage();
         list = this.quoteElemService.selectTypeNumVo(quoteElemTypeNumVo);
      } catch (Exception e) {
         this.log.error("查询病历自动引用元素中病历类型及引用元素数量列表出现异常", e);
      }

      return this.getDataTable(list);
   }
}
