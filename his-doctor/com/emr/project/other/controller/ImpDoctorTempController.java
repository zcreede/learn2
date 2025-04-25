package com.emr.project.other.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.domain.vo.ImpDoctorTempVo;
import com.emr.project.other.service.IImpDoctorTempService;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;
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
@RequestMapping({"/other/temp"})
public class ImpDoctorTempController extends BaseController {
   @Autowired
   private IImpDoctorTempService impDoctorTempService;

   @PreAuthorize("@ss.hasPermi('other:temp:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ImpDoctorTempVo impDoctorTempVo) {
      List<ImpDoctorTemp> list = null;

      try {
         this.startPage();
         SysUser user = SecurityUtils.getLoginUser().getUser();
         impDoctorTempVo.setDeptCd(user.getDept().getDeptCode());
         list = this.impDoctorTempService.selectImpDoctorTempList(impDoctorTempVo);
      } catch (Exception e) {
         this.log.error("查询患者临时授权列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('other:temp:export')")
   @Log(
      title = "患者临时授权",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ImpDoctorTempVo impDoctorTempVo) {
      List<ImpDoctorTemp> list = this.impDoctorTempService.selectImpDoctorTempList(impDoctorTempVo);
      ExcelUtil<ImpDoctorTemp> util = new ExcelUtil(ImpDoctorTemp.class);
      return util.exportExcel(list, "患者临时授权数据");
   }

   @PreAuthorize("@ss.hasPermi('other:temp:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.impDoctorTempService.selectImpDoctorTempById(id));
   }

   @PreAuthorize("@ss.hasPermi('other:temp:add')")
   @Log(
      title = "患者临时授权",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ImpDoctorTemp impDoctorTemp) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && impDoctorTemp == null) {
            ajaxResult = AjaxResult.error("参数不能为空");
            flag = false;
         }

         if (flag && impDoctorTemp.getPatientId() == null) {
            ajaxResult = AjaxResult.error("请选择患者");
            flag = false;
         }

         if (flag && StringUtils.isBlank(impDoctorTemp.getBedNo())) {
            ajaxResult = AjaxResult.error("请选择患者");
            flag = false;
         }

         if (flag && StringUtils.isBlank(impDoctorTemp.getImpRange())) {
            ajaxResult = AjaxResult.error("请选择授权范围");
            flag = false;
         }

         if (flag && (StringUtils.isBlank(impDoctorTemp.getImpDocCd()) || StringUtils.isBlank(impDoctorTemp.getImpDocName())) && (StringUtils.isBlank(impDoctorTemp.getImpDeptCd()) || StringUtils.isBlank(impDoctorTemp.getImpDeptName()))) {
            ajaxResult = AjaxResult.error("请填写授权医师或科室");
            flag = false;
         }

         if (flag && impDoctorTemp.getImpEndTime() == null) {
            ajaxResult = AjaxResult.error("请填写授权结束时间");
            flag = false;
         }

         if (flag && StringUtils.isBlank(impDoctorTemp.getImpAim())) {
            ajaxResult = AjaxResult.error("请填写授权目的");
            flag = false;
         }

         if (flag) {
            this.impDoctorTempService.insertImpDoctorTemp(impDoctorTemp);
         }
      } catch (Exception e) {
         this.log.error("新增患者临时授权出现异常, ", e);
         ajaxResult = AjaxResult.error("新增患者临时授权出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('other:temp:edit')")
   @Log(
      title = "患者临时授权",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ImpDoctorTemp impDoctorTemp) {
      return this.toAjax(this.impDoctorTempService.updateImpDoctorTemp(impDoctorTemp));
   }

   @PreAuthorize("@ss.hasPermi('other:temp:remove')")
   @Log(
      title = "患者临时授权",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.impDoctorTempService.deleteImpDoctorTempByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('other:temp:changeEndDate')")
   @Log(
      title = "患者临时授权",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/changeEndDate"})
   public AjaxResult changeValidFlag(@RequestBody ImpDoctorTemp impDoctorTemp) {
      AjaxResult ajaxResult = AjaxResult.success("操作成功");
      boolean flag = true;

      try {
         if (flag && impDoctorTemp == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && impDoctorTemp.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            ImpDoctorTemp temp = this.impDoctorTempService.selectImpDoctorTempById(impDoctorTemp.getId());
            if (temp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这条临时授权");
            }

            if (temp.getImpEndTime().compareTo(new Date()) < 0) {
               flag = false;
               ajaxResult = AjaxResult.error("这条临时授权已禁用，不能重复禁用");
            }
         }

         if (flag) {
            impDoctorTemp.setImpEndTime(new Date());
            this.impDoctorTempService.updateImpDoctorTemp(impDoctorTemp);
         }
      } catch (Exception e) {
         this.log.error("禁用患者临时授权出现异常，", e);
         ajaxResult = AjaxResult.error("禁用患者临时授权出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('other:temp:query,td:apply:edit')")
   @GetMapping({"/impDocPat"})
   public AjaxResult getInfo(ImpDoctorTemp impDoctorTemp) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(impDoctorTemp.getPatientId())) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            Boolean flag = this.impDoctorTempService.selectImpDocPatBool(impDoctorTemp.getPatientId());
            ajaxResult.put("flag", flag);
         }
      } catch (Exception e) {
         this.log.error("判断临时授权出现异常", e);
         ajaxResult = AjaxResult.error("判断临时授权出现异常");
      }

      return ajaxResult;
   }
}
