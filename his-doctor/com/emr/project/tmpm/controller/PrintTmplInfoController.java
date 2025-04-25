package com.emr.project.tmpm.controller;

import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.domain.req.PrintTmplInfoReq;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/print/info"})
@Api(
   tags = {"打印模板数据"}
)
public class PrintTmplInfoController extends BaseController {
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   @ApiOperation("查询多个打印模板数据")
   @PostMapping({"/byCodeList"})
   public AjaxResult byTypeList(@RequestBody PrintTmplInfoReq req) {
      AjaxResult result = new AjaxResult();
      if (req == null) {
         return AjaxResult.error("参数不能为空");
      } else {
         try {
            if (CollectionUtils.isNotEmpty(req.getTypeCodeList())) {
               List<PrintTmplInfo> list = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(req.getTypeCodeList());
               result = AjaxResult.success((Object)list);
            }
         } catch (Exception e) {
            this.log.error("查询多个打印模板数据出现异常", e);
            result = AjaxResult.error("查询多个打印模板数据出现异常，请联系管理员");
         }

         return result;
      }
   }
}
