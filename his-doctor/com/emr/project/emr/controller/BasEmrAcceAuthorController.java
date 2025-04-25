package com.emr.project.emr.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.BasEmrAcceAuthor;
import com.emr.project.emr.domain.vo.AcceRoleVo;
import com.emr.project.emr.domain.vo.EmrAcceVo;
import com.emr.project.emr.service.IBasEmrAcceAuthorService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
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
@RequestMapping({"/emr/author"})
public class BasEmrAcceAuthorController extends BaseController {
   @Autowired
   private IBasEmrAcceAuthorService basEmrAcceAuthorService;

   @PreAuthorize("@ss.hasPermi('emr:author:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(BasEmrAcceAuthor basEmrAcceAuthor) {
      this.startPage();
      List<BasEmrAcceAuthor> list = this.basEmrAcceAuthorService.selectBasEmrAcceAuthorList(basEmrAcceAuthor);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:author:export')")
   @Log(
      title = "病历访问等级权限",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BasEmrAcceAuthor basEmrAcceAuthor) {
      List<BasEmrAcceAuthor> list = this.basEmrAcceAuthorService.selectBasEmrAcceAuthorList(basEmrAcceAuthor);
      ExcelUtil<BasEmrAcceAuthor> util = new ExcelUtil(BasEmrAcceAuthor.class);
      return util.exportExcel(list, "病历访问等级权限数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:author:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.basEmrAcceAuthorService.selectBasEmrAcceAuthorById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:author:add')")
   @Log(
      title = "病历访问等级权限",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BasEmrAcceAuthor basEmrAcceAuthor) {
      return this.toAjax(this.basEmrAcceAuthorService.insertBasEmrAcceAuthor(basEmrAcceAuthor));
   }

   @PreAuthorize("@ss.hasPermi('emr:author:edit')")
   @Log(
      title = "病历访问等级权限",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BasEmrAcceAuthor basEmrAcceAuthor) {
      return this.toAjax(this.basEmrAcceAuthorService.updateBasEmrAcceAuthor(basEmrAcceAuthor));
   }

   @PreAuthorize("@ss.hasPermi('emr:author:remove')")
   @Log(
      title = "病历访问等级权限",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.basEmrAcceAuthorService.deleteBasEmrAcceAuthorByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('emr:author:editAcce')")
   @Log(
      title = "病历访问权限设置",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/editAcce"})
   public AjaxResult editAcce(@RequestBody EmrAcceVo emrAcceVo) {
      AjaxResult ajaxResult = AjaxResult.success("病历访问权限设置成功");
      Boolean flag = true;

      try {
         if (flag && emrAcceVo.getAuthorLevel() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrAcceVo.getRoleOrUser() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            if (emrAcceVo.getIds() != null && emrAcceVo.getIds().length > 0) {
               this.basEmrAcceAuthorService.editAcce(emrAcceVo);
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("参数不能为空");
            }
         }
      } catch (Exception e) {
         this.log.error("病历访问权限设置出现异常", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:author:roleList')")
   @GetMapping({"/roleList"})
   public TableDataInfo roleList(AcceRoleVo acceRoleVo) {
      List<AcceRoleVo> list = null;

      try {
         this.startPage();
         list = this.basEmrAcceAuthorService.selectRoleAcceList(acceRoleVo);
      } catch (Exception e) {
         this.log.error("查询病历访问权限出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:author:userList')")
   @GetMapping({"/userList"})
   public TableDataInfo userList(SysUser sysUser) {
      List<SysUser> list = null;

      try {
         this.startPage();
         list = this.basEmrAcceAuthorService.selectUserAcceList(sysUser);
      } catch (Exception e) {
         this.log.error("查询病历访问权限出现异常", e);
      }

      return this.getDataTable(list);
   }
}
