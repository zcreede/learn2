package com.emr.project.pat.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.BasDoctGroupMember;
import com.emr.project.pat.domain.vo.BasDoctGroupMemberVo;
import com.emr.project.pat.service.IBasDoctGroupMemberService;
import com.emr.project.system.domain.vo.BasEmployeeVo;
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
@RequestMapping({"/pat/member"})
public class BasDoctGroupMemberController extends BaseController {
   @Autowired
   private IBasDoctGroupMemberService basDoctGroupMemberService;

   @PreAuthorize("@ss.hasAnyPermi('pat:member:list,pat:doctgroup:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(BasDoctGroupMember basDoctGroupMember) {
      List<BasDoctGroupMember> list = null;

      try {
         this.startPage();
         list = this.basDoctGroupMemberService.selectBasDoctGroupMemberList(basDoctGroupMember);
      } catch (Exception e) {
         this.log.error("查询科室医疗组成员列表异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:member:export')")
   @Log(
      title = "医师组成员",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BasDoctGroupMember basDoctGroupMember) {
      List<BasDoctGroupMember> list = this.basDoctGroupMemberService.selectBasDoctGroupMemberList(basDoctGroupMember);
      ExcelUtil<BasDoctGroupMember> util = new ExcelUtil(BasDoctGroupMember.class);
      return util.exportExcel(list, "医师组成员数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:member:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.basDoctGroupMemberService.selectBasDoctGroupMemberById(id));
   }

   @PreAuthorize("@ss.hasPermi('pat:member:add')")
   @Log(
      title = "医师组成员",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BasDoctGroupMember basDoctGroupMember) {
      return this.toAjax(this.basDoctGroupMemberService.insertBasDoctGroupMember(basDoctGroupMember));
   }

   @PreAuthorize("@ss.hasPermi('pat:member:edit')")
   @Log(
      title = "医师组成员",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BasDoctGroupMember basDoctGroupMember) {
      return this.toAjax(this.basDoctGroupMemberService.updateBasDoctGroupMember(basDoctGroupMember));
   }

   @PreAuthorize("@ss.hasPermi('pat:member:remove')")
   @Log(
      title = "医师组成员",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.basDoctGroupMemberService.deleteBasDoctGroupMemberByIds(ids);
      } catch (Exception e) {
         this.log.error("删除医师组成员出现异常", e);
         ajaxResult = AjaxResult.error("删除医师组成员出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:member:addMembers')")
   @Log(
      title = "医师组成员",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/addMembers"})
   public AjaxResult addMembers(@RequestBody BasDoctGroupMemberVo basDoctGroupMemberVo) {
      AjaxResult ajaxResult = AjaxResult.success("添加成功");
      Boolean flag = true;

      try {
         if (flag && basDoctGroupMemberVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && basDoctGroupMemberVo.getGroupId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("医疗组id不能为空");
         }

         if (flag) {
            this.basDoctGroupMemberService.insertMembers(basDoctGroupMemberVo);
         }
      } catch (Exception e) {
         this.log.error("新增科室医疗组成员出现异常,", e);
         ajaxResult = AjaxResult.error("新增科室医疗组成员出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:member:unGroupMemberList,pat:doctgroup:list')")
   @GetMapping({"/unGroupMemberList"})
   public AjaxResult unGroupMemberList(BasDoctGroupMemberVo basDoctGroupMemberVo) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(basDoctGroupMemberVo.getDeptCd())) {
            ajaxResult = AjaxResult.error("部门编码不能为空");
         } else {
            List<BasEmployeeVo> list = this.basDoctGroupMemberService.unGroupMemberList(basDoctGroupMemberVo);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询科室医疗组成员列表异常", e);
         ajaxResult = AjaxResult.error("查询科室医疗组成员列表异常");
      }

      return ajaxResult;
   }
}
