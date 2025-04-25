package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.TokenService;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.domain.SysMenu;
import com.emr.project.system.service.ISysMenuService;
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
@RequestMapping({"/system/menu"})
public class SysMenuController extends BaseController {
   @Autowired
   private ISysMenuService menuService;
   @Autowired
   private TokenService tokenService;

   @PreAuthorize("@ss.hasPermi('system:menu:list')")
   @GetMapping({"/list"})
   public AjaxResult list(SysMenu menu) {
      LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
      Long userId = loginUser.getUser().getUserId();
      List<SysMenu> menus = this.menuService.selectMenuList(menu, userId);
      return AjaxResult.success((Object)menus);
   }

   @PreAuthorize("@ss.hasPermi('system:menu:query')")
   @GetMapping({"/{menuId}"})
   public AjaxResult getInfo(@PathVariable Long menuId) {
      return AjaxResult.success((Object)this.menuService.selectMenuById(menuId));
   }

   @GetMapping({"/treeselect"})
   public AjaxResult treeselect(SysMenu menu) {
      LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
      Long userId = loginUser.getUser().getUserId();
      List<SysMenu> menus = this.menuService.selectMenuList(menu, userId);
      return AjaxResult.success((Object)this.menuService.buildMenuTreeSelect(menus));
   }

   @GetMapping({"/roleMenuTreeselect/{roleId}"})
   public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
      LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
      List<SysMenu> menus = this.menuService.selectMenuList(loginUser.getUser().getUserId());
      AjaxResult ajax = AjaxResult.success();
      ajax.put("checkedKeys", this.menuService.selectMenuListByRoleId(roleId));
      ajax.put("menus", this.menuService.buildMenuTreeSelect(menus));
      return ajax;
   }

   @PreAuthorize("@ss.hasPermi('system:menu:add')")
   @Log(
      title = "菜单管理",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@Validated @RequestBody SysMenu menu) {
      if ("1".equals(this.menuService.checkMenuNameUnique(menu))) {
         return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
      } else if ("0".equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
         return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
      } else {
         menu.setCreateBy(SecurityUtils.getUsername());
         return this.toAjax(this.menuService.insertMenu(menu));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:menu:edit')")
   @Log(
      title = "菜单管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@Validated @RequestBody SysMenu menu) {
      if ("1".equals(this.menuService.checkMenuNameUnique(menu))) {
         return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
      } else if ("0".equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
         return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
      } else if (menu.getMenuId().equals(menu.getParentId())) {
         return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
      } else {
         menu.setUpdateBy(SecurityUtils.getUsername());
         return this.toAjax(this.menuService.updateMenu(menu));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:menu:remove')")
   @Log(
      title = "菜单管理",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{menuId}"})
   public AjaxResult remove(@PathVariable("menuId") Long menuId) {
      if (this.menuService.hasChildByMenuId(menuId)) {
         return AjaxResult.error("存在子菜单,不允许删除");
      } else {
         return this.menuService.checkMenuExistRole(menuId) ? AjaxResult.error("菜单已分配,不允许删除") : this.toAjax(this.menuService.deleteMenuById(menuId));
      }
   }
}
