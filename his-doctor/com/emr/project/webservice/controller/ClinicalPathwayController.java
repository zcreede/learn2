package com.emr.project.webservice.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.req.ClinicalPathwayReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;
import com.emr.project.webservice.service.IClinicalPathwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/clinicalPathway"})
public class ClinicalPathwayController extends BaseController {
   @Autowired
   private IClinicalPathwayService clinicalPathwayService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PostMapping({"/getPathway"})
   @PreAuthorize("@ss.hasPermi('pat:visitinfo:pathway')")
   public AjaxResult getPathway(@RequestBody ClinicalPathwayReq req) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         String drugFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
         if (StringUtils.isEmpty(drugFlag) || drugFlag.equals("0")) {
            return ajaxResult;
         }

         if (StringUtils.isEmpty(req.getAdmissionNo())) {
            ajaxResult = AjaxResult.error("住院号不能为空");
            return ajaxResult;
         }

         WebServiceResp resp = this.clinicalPathwayService.getPathway(req);
         ajaxResult.put("data", resp);
      } catch (Exception e) {
         this.log.error("查询临床路径出现异常！", e);
         ajaxResult = AjaxResult.error("查询临床路径出现异常!");
      }

      return ajaxResult;
   }
}
