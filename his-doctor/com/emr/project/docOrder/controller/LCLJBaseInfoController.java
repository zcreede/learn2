package com.emr.project.docOrder.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.req.StageListReq;
import com.emr.project.docOrder.domain.vo.StageDataList;
import com.emr.project.docOrder.domain.vo.VaryTreeData;
import com.emr.project.docOrder.service.ILCLJBaseInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/lclj/base"})
public class LCLJBaseInfoController {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private ILCLJBaseInfoService lcljBaseInfoService;

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/getVaryTree"})
   public AjaxResult getVaryTree() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<VaryTreeData> list = this.lcljBaseInfoService.getVaryTree();
         ajaxResult.put("data", list);
      } catch (Exception e) {
         this.log.error("查询变异原因字典出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/getsStageList"})
   public AjaxResult getsStageList(StageListReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (StringUtils.isEmpty(req.getStageGsljchr())) {
         return AjaxResult.error("路径编码不能为空");
      } else if (StringUtils.isEmpty(req.getStageGszljchr())) {
         return AjaxResult.error("子路径编码不能为空");
      } else {
         try {
            StageDataList data = this.lcljBaseInfoService.getStageList(req);
            ajaxResult.put("data", data);
            return ajaxResult;
         } catch (Exception e) {
            this.log.error("获取患者路径阶段出现异常，", e);
            return AjaxResult.error("获取患者路径阶段出现异常，请联系管理员！");
         }
      }
   }
}
