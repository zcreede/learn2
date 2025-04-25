package com.emr.project.CDSS.controller;

import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.CDSS.domain.vo.xyt.CdssPageResVo;
import com.emr.project.CDSS.service.ICdssReqService;
import com.emr.project.CDSS.xyt.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/cdss/req"})
public class CdssReqController extends BaseController {
   @Autowired
   private ICdssReqService cdssReqService;

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list,tmpm:form:add')")
   @GetMapping({"/index"})
   public AjaxResult getPageIndex() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         CdssPageResVo resVo = this.cdssReqService.getCommonPage_mk(RequestUtil.PAGE_INDEX);
         ajaxResult = AjaxResult.success("查询成功", resVo);
      } catch (Exception e) {
         this.log.error("查询集成知识搜素主页出现异常", e);
         ajaxResult = AjaxResult.error("查询集成知识搜素主页出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list,tmpm:form:add')")
   @GetMapping({"/entryIndex"})
   public AjaxResult getPageEntryIndex() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         CdssPageResVo resVo = this.cdssReqService.getCommonPage_mk(RequestUtil.PAGE_ENTRYINDEX);
         ajaxResult = AjaxResult.success("查询成功", resVo);
      } catch (Exception e) {
         this.log.error("查询集成知识搜素主页-临床指南出现异常", e);
         ajaxResult = AjaxResult.error("查询集成知识搜素主页-临床指南出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list,tmpm:form:add')")
   @GetMapping({"/referenceIndex"})
   public AjaxResult getPageReferenceIndex() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         CdssPageResVo resVo = this.cdssReqService.getCommonPage_mk(RequestUtil.PAGE_REFERENCEINDEX);
         ajaxResult = AjaxResult.success("查询成功", resVo);
      } catch (Exception e) {
         this.log.error("查询集成知识搜素主页-医学文献出现异常", e);
         ajaxResult = AjaxResult.error("查询集成知识搜素主页-医学文献出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list,tmpm:form:add')")
   @GetMapping({"/drugClass"})
   public AjaxResult getPageDrugClass() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         CdssPageResVo resVo = this.cdssReqService.getCommonPage_mk(RequestUtil.PAGE_DRUGCLASS);
         ajaxResult = AjaxResult.success("查询成功", resVo);
      } catch (Exception e) {
         this.log.error("查询集成知识搜素主页-药品信息出现异常", e);
         ajaxResult = AjaxResult.error("查询集成知识搜素主页-药品信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list,tmpm:form:add')")
   @GetMapping({"/examineIndex"})
   public AjaxResult getPageExamineIndex() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         CdssPageResVo resVo = this.cdssReqService.getCommonPage_mk(RequestUtil.PAGE_EXAMINEINDEX);
         ajaxResult = AjaxResult.success("查询成功", resVo);
      } catch (Exception e) {
         this.log.error("查询集成知识搜素主页-检查检验出现异常", e);
         ajaxResult = AjaxResult.error("查询集成知识搜素主页-检查检验出现异常");
      }

      return ajaxResult;
   }
}
