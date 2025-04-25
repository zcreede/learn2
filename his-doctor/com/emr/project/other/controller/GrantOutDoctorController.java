package com.emr.project.other.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.other.domain.GrantOutDoctor;
import com.emr.project.other.domain.vo.GrantOutDoctorVo;
import com.emr.project.other.service.IGrantOutDoctorService;
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
@RequestMapping({"/other/doctor"})
public class GrantOutDoctorController extends BaseController {
   @Autowired
   private IGrantOutDoctorService grantOutDoctorService;

   @PreAuthorize("@ss.hasPermi('other:doctor:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(GrantOutDoctorVo grantOutDoctorVo) {
      List<GrantOutDoctor> list = null;

      try {
         String beginTimeStr = grantOutDoctorVo.getCreDateBegStr();
         String endTimeStr = grantOutDoctorVo.getCreDateEndStr();
         if (StringUtils.isNotBlank(beginTimeStr)) {
            beginTimeStr = beginTimeStr + " 00:00:00";
            grantOutDoctorVo.setCreDateBegStr(beginTimeStr);
         }

         if (StringUtils.isNotBlank(endTimeStr)) {
            Date endDate = DateUtils.parseDate(endTimeStr, new String[]{DateUtils.YYYY_MM_DD});
            endDate = DateUtils.addDays(endDate, 1);
            endTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, endDate);
            endTimeStr = endTimeStr + " 00:00:00";
            grantOutDoctorVo.setCreDateEndStr(endTimeStr);
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         grantOutDoctorVo.setDocCode(user.getUserName());
         this.startPage();
         list = this.grantOutDoctorService.selectGrantOutDoctorList(grantOutDoctorVo);
      } catch (Exception e) {
         this.log.error("查询外部人员授权列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('other:doctor:export')")
   @Log(
      title = "外部人员授权",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(GrantOutDoctorVo grantOutDoctorVo) {
      List<GrantOutDoctor> list = this.grantOutDoctorService.selectGrantOutDoctorList(grantOutDoctorVo);
      ExcelUtil<GrantOutDoctor> util = new ExcelUtil(GrantOutDoctor.class);
      return util.exportExcel(list, "外部人员授权数据");
   }

   @PreAuthorize("@ss.hasPermi('other:doctor:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.grantOutDoctorService.selectGrantOutDoctorById(id));
   }

   @PreAuthorize("@ss.hasPermi('other:doctor:add')")
   @Log(
      title = "外部人员授权",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody GrantOutDoctor grantOutDoctor) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      if (grantOutDoctor == null) {
         ajaxResult = AjaxResult.error("参数不能为空");
         return ajaxResult;
      } else if (StringUtils.isBlank(grantOutDoctor.getSubName())) {
         ajaxResult = AjaxResult.error("请填写外来医生性别");
         return ajaxResult;
      } else if (StringUtils.isBlank(grantOutDoctor.getGender())) {
         ajaxResult = AjaxResult.error("请填写外来医生性别");
         return ajaxResult;
      } else if (StringUtils.isBlank(grantOutDoctor.getSourceCd())) {
         ajaxResult = AjaxResult.error("请填写外来医生类型");
         return ajaxResult;
      } else if (grantOutDoctor.getCreDate() == null) {
         ajaxResult = AjaxResult.error("请填写授权时限开始时间");
         return ajaxResult;
      } else if (grantOutDoctor.getEndDate() == null) {
         ajaxResult = AjaxResult.error("请填写授权时限结束时间");
         return ajaxResult;
      } else if (StringUtils.isBlank(grantOutDoctor.getValidFlag())) {
         ajaxResult = AjaxResult.error("请填写账号状态");
         return ajaxResult;
      } else {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         String userName = user.getBasEmployee().getEmplNumber();
         Boolean checkFlag = this.grantOutDoctorService.checkIsOutDoctor(userName);
         if (checkFlag) {
            ajaxResult = AjaxResult.error("外来医师无法授权！");
            return ajaxResult;
         } else {
            try {
               this.grantOutDoctorService.insertGrantOutDoctor(grantOutDoctor);
            } catch (Exception e) {
               this.log.error("新增外部人员授权出现异常，", e);
               ajaxResult = AjaxResult.error("新增外部人员授权出现异常");
            }

            return ajaxResult;
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('other:doctor:edit')")
   @Log(
      title = "外部人员授权",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody GrantOutDoctor grantOutDoctor) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (flag && grantOutDoctor == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && grantOutDoctor.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            GrantOutDoctor grantOutDoctorTemp = this.grantOutDoctorService.selectGrantOutDoctorById(grantOutDoctor.getId());
            if (grantOutDoctorTemp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这个外来医生，不能修改");
            }
         }

         if (flag && StringUtils.isBlank(grantOutDoctor.getSubName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写外来医生性别");
         }

         if (flag && StringUtils.isBlank(grantOutDoctor.getGender())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写外来医生性别");
         }

         if (flag && StringUtils.isBlank(grantOutDoctor.getSourceCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写外来医生类型");
         }

         if (flag && grantOutDoctor.getCreDate() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写授权时限开始时间");
         }

         if (flag && grantOutDoctor.getEndDate() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写授权时限结束时间");
         }

         if (flag && StringUtils.isBlank(grantOutDoctor.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写账号状态");
         }

         this.grantOutDoctorService.updateGrantOutDoctor(grantOutDoctor);
      } catch (Exception e) {
         this.log.error("新增外部人员授权出现异常，", e);
         ajaxResult = AjaxResult.error("新增外部人员授权出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('other:doctor:remove')")
   @Log(
      title = "外部人员授权",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.grantOutDoctorService.deleteGrantOutDoctorById(id);
      } catch (Exception e) {
         this.log.error("删除外部人员授权出现异常,", e);
         ajaxResult = AjaxResult.error("删除外部人员授权出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('other:doctor:changeValidFlag')")
   @Log(
      title = "修改外部人员授权账号状态",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/changeValidFlag"})
   public AjaxResult changeValidFlag(@RequestBody GrantOutDoctor grantOutDoctor) {
      AjaxResult ajaxResult = AjaxResult.success("操作成功");
      boolean flag = true;

      try {
         if (flag && grantOutDoctor == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && grantOutDoctor.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(grantOutDoctor.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            GrantOutDoctor grantOutDoctorTemp = this.grantOutDoctorService.selectGrantOutDoctorById(grantOutDoctor.getId());
            if (grantOutDoctorTemp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这个外来医生，不能修改");
            }
         }

         if (flag) {
            GrantOutDoctor param = new GrantOutDoctor();
            param.setId(grantOutDoctor.getId());
            param.setValidFlag(grantOutDoctor.getValidFlag());
            this.grantOutDoctorService.updateGrantOutDoctor(param);
         }
      } catch (Exception e) {
         this.log.error("修改外部人员授权账号状态出现异常,", e);
         ajaxResult = AjaxResult.error("修改外部人员授权账号状态出现异常");
      }

      return ajaxResult;
   }
}
