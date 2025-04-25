package com.emr.project.operation.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.operation.domain.req.OperPlanDetailReq;
import com.emr.project.operation.domain.req.OperPlanListReq;
import com.emr.project.operation.domain.req.OperPlanRejectReq;
import com.emr.project.operation.domain.req.OperationToBePlanReq;
import com.emr.project.operation.domain.req.RoomListReq;
import com.emr.project.operation.domain.resp.OperPlanListResp;
import com.emr.project.operation.service.OperationPlanService;
import com.emr.project.system.domain.SysUser;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/operation/plan"})
public class OperationPlanContraller extends BaseController {
   @Autowired
   private OperationPlanService operationPlanService;

   @PreAuthorize("@ss.hasAnyPermi('operation:plan:list')")
   @GetMapping({"/queryPlanList"})
   @ApiOperation(
      value = "手术安排查询列表",
      notes = "operation:plan:list"
   )
   public AjaxResult queryPlanList(OperationToBePlanReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(req.getStatistType())) {
            resultMsg = AjaxResult.error("统计方式不能为空");
            return resultMsg;
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         String orgCode = user.getHospital().getOrgCode();
         req.setOrgCode(orgCode);
         req.setStatus("06");
         List<TdCaOperationApply> list = this.operationPlanService.queryPlanList(req);
         resultMsg.put("data", list);
      } catch (Exception e) {
         this.log.error("手术安排查询列表失败", e);
         resultMsg = AjaxResult.error("手术安排查询列表失败，请联系管理员！");
      }

      return resultMsg;
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:plan:operPlanList,operation:plan:list')")
   @GetMapping({"/operPlanList"})
   @ApiOperation(
      value = "查询手术安排列表",
      notes = "operation:plan:operPlanList"
   )
   public AjaxResult operPlanList(OperPlanListReq req) {
      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         String orgCode = user.getHospital().getOrgCode();
         req.setOrgCode(orgCode);
         List<OperPlanListResp> list = this.operationPlanService.selectOperPlanList(req);
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询手术安排列表异常", e);
         return AjaxResult.error("查询手术安排列表异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:plan:updateOperPlan')")
   @GetMapping({"/getOperTableByShiftDate"})
   @ApiOperation(
      value = "根据手术安排时间查询手术台次",
      notes = "operation:plan:updateOperPlan"
   )
   public AjaxResult getOperTableByShiftDate(String shiftDate) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;
      if (shiftDate == null) {
         flag = false;
         ajaxResult = AjaxResult.error("请填写手术安排时间");
      }

      try {
         if (flag) {
            int num = this.operationPlanService.getOperTableByShiftDate(shiftDate);
            ajaxResult = AjaxResult.success((Object)num);
         }
      } catch (Exception e) {
         e.printStackTrace();
         ajaxResult = AjaxResult.error("根据手术安排时间查询手术台次出现异常，请联系系统管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:plan:updateOperPlan,operation:plan:list')")
   @PostMapping({"/updateOperPlan"})
   @ApiOperation(
      value = "更新手术安排",
      notes = "operation:plan:updateOperPlan"
   )
   public AjaxResult updateOperPlan(@RequestBody OperPlanRejectReq req) {
      try {
         if (StringUtil.isEmpty(req.getApplyFormNo())) {
            return AjaxResult.error("申请单编号不能为空");
         } else if (StringUtil.isEmpty(req.getType())) {
            return AjaxResult.error("接口类型不能为空");
         } else {
            if ("1".equals(req.getType())) {
               req.setStatus("04");
            } else if ("2".equals(req.getType())) {
               if (!"08".equals(req.getStatus())) {
                  req.setStatus("06");
               }

               if (req.getOperDate() == null) {
                  return AjaxResult.error("手术安排时间不能为空");
               }

               if (StringUtil.isEmpty(req.getOperRoom())) {
                  return AjaxResult.error("手术间不能为空");
               }
            } else if ("3".equals(req.getType())) {
               req.setStatus("02");
            } else if ("4".equals(req.getType())) {
               req.setStatus("08");
               if (req.getInRoomTime() == null) {
                  return AjaxResult.error("入室时间不能为空");
               }
            } else if ("5".equals(req.getType())) {
               req.setStatus("02");
            } else {
               if (!"6".equals(req.getType())) {
                  return AjaxResult.error("接口类型有误");
               }

               req.setStatus("07");
               if (req.getOutRoomTime() == null) {
                  return AjaxResult.error("出室时间不能为空");
               }
            }

            return this.toAjax(this.operationPlanService.updateOperPlan(req));
         }
      } catch (Exception e) {
         this.log.error("更新手术安排异常", e);
         return AjaxResult.error("更新手术安排异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:plan:operPlanDetail,operation:plan:list')")
   @GetMapping({"/operPlanDetail"})
   @ApiOperation(
      value = "查询手术安排详情",
      notes = "operation:plan:operPlanDetail"
   )
   public AjaxResult operPlanDetail(OperPlanDetailReq req) {
      try {
         return AjaxResult.success((Object)this.operationPlanService.selectOperPlanDetail(req));
      } catch (Exception e) {
         this.log.error("查询手术安排详情异常", e);
         return AjaxResult.error("查询手术安排详情异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:plan:operPlanCount,operation:plan:list')")
   @GetMapping({"/operPlanCount"})
   @ApiOperation(
      value = "查询手术安排数量",
      notes = "operation:plan:operPlanCount"
   )
   public AjaxResult operPlanCount(OperPlanDetailReq req) {
      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         req.setDeptCd(user.getDept().getDeptCode());
         return AjaxResult.success((Object)this.operationPlanService.selectOperPlanCount(req));
      } catch (Exception e) {
         this.log.error("查询手术安排数量异常", e);
         return AjaxResult.error("查询手术安排数量异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:plan:operRoomInfo,operation:plan:list')")
   @GetMapping({"/operRoomInfo"})
   @ApiOperation(
      value = "查询手术间信息",
      notes = "operation:plan:operRoomInfo"
   )
   public AjaxResult operRoomInfo(OperPlanDetailReq req) {
      try {
         return AjaxResult.success((Object)this.operationPlanService.selectOperRoomInfo(req));
      } catch (Exception e) {
         this.log.error("查询手术间信息异常", e);
         return AjaxResult.error("查询手术间信息异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:plan:roomList,operation:plan:list')")
   @GetMapping({"/roomList"})
   @ApiOperation(
      value = "查询手术室",
      notes = "operation:plan:roomList"
   )
   public AjaxResult roomList(RoomListReq req) {
      try {
         return AjaxResult.success((Object)this.operationPlanService.selectRoomList(req));
      } catch (Exception e) {
         this.log.error("查询手术室异常", e);
         return AjaxResult.error("查询手术室异常，请联系管理员！");
      }
   }
}
