package com.emr.project.operation.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.operation.domain.TmNaTcwh;
import com.emr.project.operation.domain.req.TcwhListReq;
import com.emr.project.operation.service.ITmNaTcwhService;
import com.emr.project.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/operation/tcwh"})
@Api(
   tags = {"诊疗套餐主-刘培"}
)
public class TmNaTcwhController extends BaseController {
   @Autowired
   private ITmNaTcwhService tmNaTcwhService;

   @PreAuthorize("@ss.hasPermi('operation:tcwh:list')")
   @GetMapping({"/list"})
   @ApiOperation(
      value = "查询诊疗套餐主列表",
      notes = "operation:tcwh:list"
   )
   public TableDataInfo list(TcwhListReq req) {
      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         req.setHospitalCode(user.getHospital().getOrgCode());
         req.setOperatorNo(user.getUserName());
         req.setWardNo(user.getDept().getDeptCode());
         this.startPage();
         List<TmNaTcwh> list = this.tmNaTcwhService.selectTmNaTcwhList(req);
         return this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询诊疗套餐主列表出现异常，", e);
         return new TableDataInfo(500, "查询诊疗套餐主列表出现异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('operation:tcwh:save')")
   @PostMapping({"/save"})
   @ApiOperation(
      value = "新增诊疗套餐主",
      notes = "operation:tcwh:save"
   )
   public AjaxResult save(@RequestBody List tmNaTcwhs) {
      try {
         return this.toAjax(this.tmNaTcwhService.save(tmNaTcwhs));
      } catch (Exception e) {
         this.log.error("新增诊疗套餐主出现异常，", e);
         return AjaxResult.error("新增诊疗套餐主出现异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('operation:tcwh:remove')")
   @DeleteMapping({"/{ids}"})
   @ApiOperation(
      value = "删除诊疗套餐主",
      notes = "operation:tcwh:remove"
   )
   public AjaxResult remove(@PathVariable Long[] ids) {
      try {
         List<TmNaTcwh> tcwhList = this.tmNaTcwhService.selectListFromMx();

         for(Long id : ids) {
            List<TmNaTcwh> filterList = (List)tcwhList.stream().filter((t) -> t.getId().equals(id)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(filterList)) {
               return AjaxResult.error("套餐：" + ((TmNaTcwh)filterList.get(0)).getPackageName() + "已有明细记录，不能删除");
            }
         }

         return this.toAjax(this.tmNaTcwhService.deleteTmNaTcwhByIds(ids));
      } catch (Exception e) {
         this.log.error("删除诊疗套餐主出现异常，", e);
         return AjaxResult.error("删除诊疗套餐主出现异常，请联系管理员！");
      }
   }
}
