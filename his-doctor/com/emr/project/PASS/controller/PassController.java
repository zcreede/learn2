package com.emr.project.PASS.controller;

import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.service.ISysEmrConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/docOrder/pass"})
public class PassController extends BaseController {
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PreAuthorize("@ss.hasAnyPermi('docOrder:pass:passFlag,docOrder:order:add,docOrder:herb:add')")
   @GetMapping({"/passFlag"})
   public AjaxResult getPassFlag() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         String passFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0042");
         ajaxResult.put("passFlag", passFlag);
      } catch (Exception e) {
         this.log.error("查询合理用药启用标识出现异常", e);
         ajaxResult = AjaxResult.error("查询合理用药启用标识出现异常");
      }

      return ajaxResult;
   }
}
