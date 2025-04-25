package com.emr.project.docOrder.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.req.OrderExecutionRecordReq;
import com.emr.project.docOrder.domain.resp.OrderExecutionRecordResp;
import com.emr.project.docOrder.service.IOrderExecutionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/orderExecutionRecord"})
public class OrderExecutionRecordController extends BaseController {
   @Autowired
   private IOrderExecutionRecordService orderExecutionRecordService;

   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('docOrder:orderExecutionRecord:list')")
   @PostMapping({"/list"})
   public AjaxResult hislist(@RequestBody OrderExecutionRecordReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(req.getAdmissionNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(req.getOrderNo())) {
         resultMsg = AjaxResult.error("医嘱编号不能为空！");
         return resultMsg;
      } else {
         try {
            OrderExecutionRecordResp resp = this.orderExecutionRecordService.getOrderExecutionRecord(req);
            resultMsg.put("data", resp);
         } catch (Exception e) {
            resultMsg = AjaxResult.error("查询医嘱执行列表出现异常，请联系管理员!");
            this.log.error("查询医嘱执行列表出现异常", e);
         }

         return resultMsg;
      }
   }
}
