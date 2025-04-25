package com.emr.project.emr.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EditState;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrLog;
import com.emr.project.emr.domain.vo.EditStateVo;
import com.emr.project.emr.service.IEditStateService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.emr.service.ISysEmrLogService;
import com.emr.project.system.domain.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/emr/state"})
public class EditStateController extends BaseController {
   @Autowired
   private IEditStateService editStateService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ISysEmrLogService sysEmrLogService;
   @Autowired
   private ICommonService commonService;

   @ApiOperation("查询病历编辑状态列表-分页")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "inpNo",
   value = "住院号",
   dataType = "Integer"
), @ApiImplicitParam(
   name = "mrFileName",
   value = "文档名称",
   dataType = "String"
), @ApiImplicitParam(
   name = "editPersonCd",
   value = "锁定操作员工工号",
   dataType = "String"
), @ApiImplicitParam(
   name = "beginTimeStr",
   value = "开始锁定日期",
   dataType = "String"
), @ApiImplicitParam(
   name = "endTimeStr",
   value = "结束锁定日期",
   dataType = "String"
), @ApiImplicitParam(
   name = "pageNum",
   value = "当前记录起始索引",
   dataType = "Integer"
), @ApiImplicitParam(
   name = "pageSize",
   value = "每页显示记录数",
   dataType = "Integer"
), @ApiImplicitParam(
   name = "orderByColumn",
   value = "排序列",
   dataType = "String"
), @ApiImplicitParam(
   name = "isAsc",
   value = "asc 或者 desc",
   dataType = "String"
)})
   @PreAuthorize("@ss.hasPermi('emr:state:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EditStateVo editStateVo) {
      List<EditState> list = null;

      try {
         this.startPage();
         String beginTimeStr = editStateVo.getBeginTimeStr();
         String endTimeStr = editStateVo.getEndTimeStr();
         if (StringUtils.isNotBlank(beginTimeStr)) {
            beginTimeStr = beginTimeStr + " 00:00:00";
            editStateVo.setBeginTimeStr(beginTimeStr);
         }

         if (StringUtils.isNotBlank(endTimeStr)) {
            Date endDate = DateUtils.parseDate(endTimeStr, new String[]{DateUtils.YYYY_MM_DD});
            endDate = DateUtils.addDays(endDate, 1);
            endTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, endDate);
            endTimeStr = endTimeStr + " 00:00:00";
            editStateVo.setEndTimeStr(endTimeStr);
         }

         list = this.editStateService.selectDeitEditStateList(editStateVo);
      } catch (Exception e) {
         this.log.error("查询病历编辑状态列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:state:export')")
   @Log(
      title = "病历编辑状态",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EditState editState) {
      List<EditState> list = this.editStateService.selectEditStateList(editState);
      ExcelUtil<EditState> util = new ExcelUtil(EditState.class);
      return util.exportExcel(list, "病历编辑状态数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:state:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.editStateService.selectEditStateById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:state:add')")
   @Log(
      title = "病历编辑状态",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EditState editState) {
      return this.toAjax(this.editStateService.insertEditState(editState));
   }

   @PreAuthorize("@ss.hasPermi('emr:state:edit')")
   @Log(
      title = "病历编辑状态",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EditState editState) {
      AjaxResult ajaxResult = AjaxResult.success("解锁成功");
      boolean flag = true;

      try {
         if (flag && editState == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && editState.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            EditState editStateTemp = this.editStateService.selectEditStateById(editState.getId());
            if (editStateTemp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病历文件");
            } else if (!editStateTemp.getDeitState().equals("0")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历已完成编辑或被锁定,不能编辑");
            }
         }

         if (flag) {
            EditState editStateTemp = new EditState();
            editStateTemp.setId(editState.getId());
            editStateTemp.setDeitState("0");
            this.editStateService.updateEditState(editState);
         }
      } catch (Exception e) {
         this.log.error("编辑病历出现异常,", e);
         ajaxResult = AjaxResult.error("编辑病历出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:state:changeDeitState')")
   @Log(
      title = "病历解锁",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/changeDeitState"})
   public AjaxResult changeDeitState(Long id, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("解锁成功");
      boolean flag = true;

      try {
         if (flag && id == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         Index index = null;
         SubfileIndex subfileIndex = null;
         EditState editStateTemp = null;
         if (flag) {
            editStateTemp = this.editStateService.selectEditStateById(id);
            if (editStateTemp != null) {
               index = this.indexService.selectIndexById(editStateTemp.getMrFileId());
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(editStateTemp.getMrFileId());
               if (index == null && subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("没有此病历文件");
               } else if (!editStateTemp.getDeitState().equals("1")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历没有被锁定,不能解锁");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到编辑记录");
            }
         }

         if (flag) {
            if (subfileIndex != null) {
               index = this.indexService.selectIndexById(subfileIndex.getMainId());
            }

            String mrType = subfileIndex == null ? index.getMrType() : subfileIndex.getMrType();
            String fileName = subfileIndex == null ? index.getMrFileShowName() : subfileIndex.getMrFileShowName();
            EditState edit = new EditState();
            edit.setId(id);
            edit.setDeitState("0");
            this.editStateService.updateEditState(edit);
            List<SysEmrLog> sysEmrLogList = new ArrayList();
            SysEmrLog sysEmrLog = new SysEmrLog();
            sysEmrLog.setOptType("1");
            sysEmrLog.setOptTypeName("异常解锁");
            sysEmrLog.setMrFileId(editStateTemp.getMrFileId());
            sysEmrLog.setMrTypeCd(mrType);
            sysEmrLog.setMrFileName(fileName);
            sysEmrLog.setPatientId(index.getPatientId());
            sysEmrLog.setIp(IPAddressUtil.getIPAddress(httpServletRequest));
            sysEmrLogList.add(sysEmrLog);
            this.sysEmrLogService.insertSysEmrLog(sysEmrLogList);
         }
      } catch (Exception e) {
         this.log.error("解锁病历出现异常,", e);
         ajaxResult = AjaxResult.error("解锁病历出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:state:changeDeitState,emr:index:save')")
   @Log(
      title = "病历解锁",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/changeDeitStateEmr"})
   public AjaxResult changeDeitStateRmr(Long id, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("解锁成功");
      boolean flag = true;

      try {
         if (flag && id == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         Index index = null;
         SubfileIndex subfileIndex = null;
         EditState editStateTemp = null;
         if (flag) {
            editStateTemp = this.editStateService.selectEditStateByEmrId(id);
            if (editStateTemp != null) {
               index = this.indexService.selectIndexById(editStateTemp.getMrFileId());
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(editStateTemp.getMrFileId());
               if (index == null && subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("没有此病历文件");
               } else if (!editStateTemp.getDeitState().equals("1")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历没有被锁定,不能解锁");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到编辑记录");
            }
         }

         if (flag) {
            if (subfileIndex != null) {
               index = this.indexService.selectIndexById(subfileIndex.getMainId());
            }

            String mrType = subfileIndex == null ? index.getMrType() : subfileIndex.getMrType();
            String fileName = subfileIndex == null ? index.getMrFileShowName() : subfileIndex.getMrFileShowName();
            EditState edit = new EditState();
            edit.setId(editStateTemp.getId());
            edit.setDeitState("0");
            this.editStateService.updateEditState(edit);
            List<SysEmrLog> sysEmrLogList = new ArrayList();
            SysEmrLog sysEmrLog = new SysEmrLog();
            sysEmrLog.setOptType("1");
            sysEmrLog.setOptTypeName("异常解锁");
            sysEmrLog.setMrFileId(editStateTemp.getMrFileId());
            sysEmrLog.setMrTypeCd(mrType);
            sysEmrLog.setMrFileName(fileName);
            sysEmrLog.setPatientId(index.getPatientId());
            sysEmrLog.setIp(IPAddressUtil.getIPAddress(httpServletRequest));
            sysEmrLogList.add(sysEmrLog);
            this.sysEmrLogService.insertSysEmrLog(sysEmrLogList);
         }
      } catch (Exception e) {
         this.log.error("解锁病历出现异常,", e);
         ajaxResult = AjaxResult.error("解锁病历出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:state:remove')")
   @Log(
      title = "病历编辑状态",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.editStateService.deleteEditStateByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:tempsave,emr:index:save')")
   @GetMapping({"/updateEditTime"})
   public AjaxResult updateEditTime(Long mrFileId, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("更新编辑记录时间成功");
      boolean flag = true;
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (flag && mrFileId == null) {
         flag = false;
         ajaxResult = AjaxResult.error("参数不能为空");
      }

      try {
         if (flag) {
            EditState editState = new EditState();
            editState.setMrFileId(mrFileId);
            editState.setEditPersonCd(sysUser.getUserName());
            String ip = IPAddressUtil.getIPAddress(request);
            editState.setIp(ip);
            editState.setDeitState("1");
            List<EditState> editStateList = this.editStateService.selectEditStateList(editState);
            if (CollectionUtils.isNotEmpty(editStateList)) {
               EditState editStateUpdate = new EditState();
               editStateUpdate.setId(((EditState)editStateList.get(0)).getId());
               Date dbDate = this.commonService.getDbSysdate();
               editStateUpdate.setUpdateTime(dbDate);
               this.editStateService.updateEditState(editStateUpdate);
            } else {
               EditState currEditState = this.editStateService.selectEditStateByEmrId(mrFileId);
               if (currEditState != null) {
                  ajaxResult = AjaxResult.error("【" + currEditState.getEditPersonName() + "】正在编辑该病历，重新打开病历后再试！");
                  ajaxResult.put("otherIpUnlockFlag", true);
               } else {
                  EditState editStateLast = this.editStateService.selectEditStateLastByEmrId(mrFileId);
                  if (editStateLast != null && !editStateLast.getIp().equals(ip) || !editStateLast.getEditPersonCd().equals(sysUser.getUserName())) {
                     ajaxResult = AjaxResult.error("【" + sysUser.getNickName() + "】已经结束编辑该病历，重新打开病历后再试！");
                     ajaxResult.put("otherIpUnlockFlag", true);
                  }
               }
            }
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("更新编辑记录时间出现异常");
         this.log.error("更新编辑记录时间出现异常：", e);
      }

      return ajaxResult;
   }
}
