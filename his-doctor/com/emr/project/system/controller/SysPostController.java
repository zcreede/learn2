package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysPost;
import com.emr.project.system.service.ISysPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/system/post"})
public class SysPostController extends BaseController {
   @Autowired
   private ISysPostService postService;

   @PreAuthorize("@ss.hasPermi('system:post:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysPost post) {
      this.startPage();
      List<SysPost> list = this.postService.selectPostList(post);
      return this.getDataTable(list);
   }

   @Log(
      title = "岗位管理",
      businessType = BusinessType.EXPORT
   )
   @PreAuthorize("@ss.hasPermi('system:post:export')")
   @GetMapping({"/export"})
   public AjaxResult export(SysPost post) {
      List<SysPost> list = this.postService.selectPostList(post);
      ExcelUtil<SysPost> util = new ExcelUtil(SysPost.class);
      return util.exportExcel(list, "岗位数据");
   }

   @PreAuthorize("@ss.hasPermi('system:post:query')")
   @GetMapping({"/{postId}"})
   public AjaxResult getInfo(@PathVariable Long postId) {
      return AjaxResult.success((Object)this.postService.selectPostById(postId));
   }

   @PreAuthorize("@ss.hasPermi('system:post:add')")
   @Log(
      title = "岗位管理",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@Validated @RequestBody SysPost post) {
      if ("1".equals(this.postService.checkPostNameUnique(post))) {
         return AjaxResult.error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
      } else if ("1".equals(this.postService.checkPostCodeUnique(post))) {
         return AjaxResult.error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
      } else {
         post.setCreateBy(SecurityUtils.getUsername());
         return this.toAjax(this.postService.insertPost(post));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:post:edit')")
   @Log(
      title = "岗位管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@Validated @RequestBody SysPost post) {
      if ("1".equals(this.postService.checkPostNameUnique(post))) {
         return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
      } else if ("1".equals(this.postService.checkPostCodeUnique(post))) {
         return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
      } else {
         post.setUpdateBy(SecurityUtils.getUsername());
         return this.toAjax(this.postService.updatePost(post));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:post:remove')")
   @Log(
      title = "岗位管理",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{postIds}"})
   public AjaxResult remove(@PathVariable Long[] postIds) {
      return this.toAjax(this.postService.deletePostByIds(postIds));
   }

   @GetMapping({"/optionselect"})
   public AjaxResult optionselect() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<SysPost> posts = this.postService.selectPostAll();
         ajaxResult = AjaxResult.success((Object)posts);
      } catch (Exception e) {
         this.log.error("获取岗位选择框列表异常", e);
         ajaxResult = AjaxResult.error("获取岗位选择框列表异常");
      }

      return ajaxResult;
   }
}
