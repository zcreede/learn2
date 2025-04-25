package com.emr.project.webservice.controller;

import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.webservice.domain.req.PaperlessCheckReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;
import com.emr.project.webservice.service.PaperlessCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/paperless"})
public class PaperlessCheckController extends BaseController {
   @Autowired
   private PaperlessCheckService paperlessCheckService;

   @PostMapping({"/check"})
   public AjaxResult getPathway(@RequestBody PaperlessCheckReq req) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         WebServiceResp resp = this.paperlessCheckService.getPaperlessCheckFlag(req);
         ajaxResult.put("data", resp);
      } catch (Exception e) {
         this.log.error("查询临床路径出现异常！", e);
         ajaxResult = AjaxResult.error("查询临床路径出现异常!");
      }

      return ajaxResult;
   }
}
