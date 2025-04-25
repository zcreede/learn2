package com.emr.project.operation.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.operation.domain.TdCaOperationRoom;
import com.emr.project.operation.service.ITdCaOperationRoomService;
import com.emr.project.system.domain.SysUser;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/operation/operationRoom"})
public class OperationRoomConfigContraller extends BaseController {
   @Autowired
   private ITdCaOperationRoomService tdCaOperationRoomService;

   @PreAuthorize("@ss.hasAnyPermi('operation:operationRoom:list')")
   @GetMapping({"/list"})
   @ApiOperation(
      value = "查询手术房间维护列表",
      notes = "operation:operationRoom:list"
   )
   public TableDataInfo list(String searchValue) {
      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         String orgCode = user.getHospital().getOrgCode();
         this.startPage();
         List<TdCaOperationRoom> list = this.tdCaOperationRoomService.selectTdCaOperationRoomListOfSearch(orgCode, searchValue);
         return this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询手术房间维护列表失败", e);
         return new TableDataInfo(500, "查询手术房间维护列表失败，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('operation:operationRoom:query')")
   @GetMapping({"/{id}"})
   @ApiOperation(
      value = "获取手术房间维护详细信息",
      notes = "operation:operationRoom:query"
   )
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      AjaxResult res = AjaxResult.success("保存退药申请成功");

      try {
         TdCaOperationRoom tdCaOperationRoom = this.tdCaOperationRoomService.selectTdCaOperationRoomById(id);
         res.put("data", tdCaOperationRoom);
      } catch (Exception e) {
         this.log.error("获取手术房间维护列表失败", e);
         res = AjaxResult.error("获取手术房间维护列表失败，请联系管理员");
      }

      return res;
   }

   @PreAuthorize("@ss.hasPermi('operation:operationRoom:save')")
   @PostMapping({"/save"})
   @ApiOperation(
      value = "新增手术房间维护",
      notes = "operation:operationRoom:save"
   )
   public AjaxResult save(@RequestBody TdCaOperationRoom tdCaOperationRoom) {
      AjaxResult res = AjaxResult.success("保存成功");

      try {
         return StringUtils.isBlank(tdCaOperationRoom.getSurgicalRoomName()) ? AjaxResult.error("手术间名称不能为空") : this.toAjax(this.tdCaOperationRoomService.insertTdCaOperationRoom(tdCaOperationRoom));
      } catch (Exception e) {
         this.log.error("新增手术房间维护出现异常，", e);
         return AjaxResult.error("新增手术房间维护出现异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('operation:operationRoom:edit')")
   @PutMapping({"/edit"})
   @ApiOperation(
      value = "修改手术房间维护",
      notes = "operation:operationRoom:edit"
   )
   public AjaxResult edit(@RequestBody TdCaOperationRoom tdCaOperationRoom) {
      AjaxResult res = AjaxResult.success("修改成功");

      try {
         return this.toAjax(this.tdCaOperationRoomService.updateTdCaOperationRoom(tdCaOperationRoom));
      } catch (Exception e) {
         this.log.error("修改手术房间维护出现异常，", e);
         return AjaxResult.error("修改手术房间维护出现异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('operation:operationRoom:remove')")
   @PutMapping({"/remove"})
   @ApiOperation(
      value = "删除手术房间维护",
      notes = "operation:operationRoom:remove"
   )
   public AjaxResult remove(Long id) {
      AjaxResult res = AjaxResult.success("删除成功");

      try {
         return this.toAjax(this.tdCaOperationRoomService.deleteTdCaOperationRoomById(id));
      } catch (Exception e) {
         this.log.error("删除手术房间维护出现异常，", e);
         return AjaxResult.error("删除手术房间维护出现异常，请联系管理员！");
      }
   }
}
