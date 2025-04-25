package com.emr.project.system.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysMenu;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.MetaVo;
import com.emr.project.system.domain.vo.RouterVo;
import com.emr.project.system.mapper.SysMenuMapper;
import com.emr.project.system.mapper.SysRoleMapper;
import com.emr.project.system.mapper.SysRoleMenuMapper;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysMenuService;
import com.emr.project.system.service.ISysUserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl implements ISysMenuService {
   public static final String PREMISSION_STRING = "perms[\"{0}\"]";
   @Autowired
   private SysMenuMapper menuMapper;
   @Autowired
   private SysRoleMapper roleMapper;
   @Autowired
   private SysRoleMenuMapper roleMenuMapper;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private IBasEmployeeService basEmployeeService;

   public List selectMenuList(Long userId) {
      return this.selectMenuList(new SysMenu(), userId);
   }

   public List selectMenuList(SysMenu menu, Long userId) {
      List<SysMenu> menuList = null;
      if (SysUser.isAdmin(userId)) {
         menuList = this.menuMapper.selectMenuList(menu);
      } else {
         SysUser sysUser = this.sysUserService.selectUserById(userId);
         BasEmployee basEmployee = this.basEmployeeService.selectByEmplNumber(sysUser.getUserName());
         menu.getParams().put("userId", basEmployee.getId());
         menuList = this.menuMapper.selectMenuListByUserId(menu);
      }

      return menuList;
   }

   public Set selectMenuPermsByUserId(String userId, String deptId) {
      List<String> perms = this.menuMapper.selectMenuPermsByUserId(userId, deptId);
      Set<String> permsSet = new HashSet();

      for(String perm : perms) {
         if (StringUtils.isNotEmpty(perm)) {
            permsSet.addAll(Arrays.asList(perm.trim().split(",")));
         }
      }

      return permsSet;
   }

   public List selectMenuTreeByUserId(Long userId) throws Exception {
      List<SysMenu> menus = null;
      if (SecurityUtils.isAdmin(userId)) {
         menus = this.menuMapper.selectMenuTreeAll();
      } else {
         SysUser sysUser = this.sysUserService.selectUserById(userId);
         BasEmployee basEmployee = this.basEmployeeService.selectBasEmployeeById(sysUser.getEmployeeId());
         menus = this.menuMapper.selectMenuTreeByUserId(basEmployee.getEmplNumber());
      }

      return this.getChildPerms(menus, 0);
   }

   public List selectMenuListByRoleId(Long roleId) {
      SysRole role = this.roleMapper.selectRoleById(roleId);
      return this.menuMapper.selectMenuListByRoleId(roleId, role.isMenuCheckStrictly());
   }

   public List buildMenus(List menus) {
      List<RouterVo> routers = new LinkedList();

      for(SysMenu menu : menus) {
         RouterVo router = new RouterVo();
         router.setHidden("1".equals(menu.getVisible()));
         router.setName(this.getRouteName(menu));
         router.setPath(this.getRouterPath(menu));
         router.setComponent(this.getComponent(menu));
         router.setSkipMode(menu.getSkipMode());
         router.setIsFrame(StringUtils.isNotEmpty(menu.getIsFrame()) ? menu.getIsFrame() : null);
         router.setBrowserType(StringUtils.isNotEmpty(menu.getBrowserType()) ? menu.getBrowserType() : null);
         router.setBrowserTypeName(StringUtils.isNotEmpty(menu.getBrowserTypeName()) ? menu.getBrowserTypeName() : null);
         router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
         List<SysMenu> cMenus = menu.getChildren();
         if (!cMenus.isEmpty() && cMenus.size() > 0 && "M".equals(menu.getMenuType())) {
            router.setAlwaysShow(true);
            router.setRedirect("noRedirect");
            router.setChildren(this.buildMenus(cMenus));
         } else if (this.isMenuFrame(menu)) {
            router.setMeta((MetaVo)null);
            List<RouterVo> childrenList = new ArrayList();
            RouterVo children = new RouterVo();
            children.setPath(menu.getPath());
            children.setComponent(menu.getComponent());
            children.setName(StringUtils.capitalize(menu.getPath()));
            children.setSkipMode(menu.getSkipMode());
            children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
            childrenList.add(children);
            router.setChildren(childrenList);
         } else if (menu.getParentId().intValue() == 0 && this.isInnerLink(menu)) {
            router.setMeta((MetaVo)null);
            router.setPath("/inner");
            List<RouterVo> childrenList = new ArrayList();
            RouterVo children = new RouterVo();
            String routerPath = StringUtils.replaceEach(menu.getPath(), new String[]{"http://", "https://"}, new String[]{"", ""});
            children.setPath(routerPath);
            children.setComponent("InnerLink");
            children.setName(StringUtils.capitalize(routerPath));
            children.setSkipMode(menu.getSkipMode());
            children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
            childrenList.add(children);
            router.setChildren(childrenList);
         }

         routers.add(router);
      }

      return routers;
   }

   public List buildMenuTree(List menus) {
      List<SysMenu> returnList = new ArrayList();
      List<Long> tempList = new ArrayList();

      for(SysMenu dept : menus) {
         tempList.add(dept.getMenuId());
      }

      for(SysMenu menu : menus) {
         if (!tempList.contains(menu.getParentId())) {
            this.recursionFn(menus, menu);
            returnList.add(menu);
         }
      }

      if (returnList.isEmpty()) {
         returnList = menus;
      }

      return returnList;
   }

   public List buildMenuTreeSelect(List menus) {
      List<SysMenu> menuTrees = this.buildMenuTree(menus);
      return (List)menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public SysMenu selectMenuById(Long menuId) {
      return this.menuMapper.selectMenuById(menuId);
   }

   public boolean hasChildByMenuId(Long menuId) {
      int result = this.menuMapper.hasChildByMenuId(menuId);
      return result > 0;
   }

   public boolean checkMenuExistRole(Long menuId) {
      int result = this.roleMenuMapper.checkMenuExistRole(menuId);
      return result > 0;
   }

   public int insertMenu(SysMenu menu) {
      return this.menuMapper.insertMenu(menu);
   }

   public int updateMenu(SysMenu menu) {
      return this.menuMapper.updateMenu(menu);
   }

   public int deleteMenuById(Long menuId) {
      return this.menuMapper.deleteMenuById(menuId);
   }

   public String checkMenuNameUnique(SysMenu menu) {
      Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
      SysMenu info = this.menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
      return StringUtils.isNotNull(info) && info.getMenuId() != menuId ? "1" : "0";
   }

   public String getRouteName(SysMenu menu) {
      String routerName = StringUtils.capitalize(menu.getPath());
      if (this.isMenuFrame(menu)) {
         routerName = "";
      }

      return routerName;
   }

   public String getRouterPath(SysMenu menu) {
      String routerPath = menu.getPath();
      if (menu.getParentId().intValue() != 0 && this.isInnerLink(menu)) {
         routerPath = StringUtils.replaceEach(routerPath, new String[]{"http://", "https://"}, new String[]{"", ""});
      }

      if (0 == menu.getParentId().intValue() && "M".equals(menu.getMenuType()) && "1".equals(menu.getIsFrame())) {
         routerPath = "/" + menu.getPath();
      } else if (this.isMenuFrame(menu)) {
         routerPath = "/";
      }

      return routerPath;
   }

   public String getComponent(SysMenu menu) {
      String component = "Layout";
      if (StringUtils.isNotEmpty(menu.getComponent()) && !this.isMenuFrame(menu)) {
         component = menu.getComponent();
      } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && this.isInnerLink(menu)) {
         component = "InnerLink";
      } else if (StringUtils.isEmpty(menu.getComponent()) && this.isParentView(menu)) {
         component = "ParentView";
      }

      return component;
   }

   public boolean isMenuFrame(SysMenu menu) {
      return menu.getParentId().intValue() == 0 && "C".equals(menu.getMenuType()) && menu.getIsFrame().equals("1");
   }

   public boolean isParentView(SysMenu menu) {
      return menu.getParentId().intValue() != 0 && "M".equals(menu.getMenuType());
   }

   public boolean isInnerLink(SysMenu menu) {
      return menu.getIsFrame().equals("1") && StringUtils.ishttp(menu.getPath());
   }

   public List getChildPerms(List list, int parentId) {
      List<SysMenu> returnList = new ArrayList();

      for(SysMenu t : list) {
         if (t.getParentId() == (long)parentId) {
            this.recursionFn(list, t);
            returnList.add(t);
         }
      }

      return returnList;
   }

   private void recursionFn(List list, SysMenu t) {
      List<SysMenu> childList = this.getChildList(list, t);
      t.setChildren(childList);

      for(SysMenu tChild : childList) {
         if (this.hasChild(list, tChild)) {
            this.recursionFn(list, tChild);
         }
      }

   }

   private List getChildList(List list, SysMenu t) {
      List<SysMenu> tlist = new ArrayList();

      for(SysMenu n : list) {
         if (n.getParentId() == t.getMenuId()) {
            tlist.add(n);
         }
      }

      return tlist;
   }

   private boolean hasChild(List list, SysMenu t) {
      return this.getChildList(list, t).size() > 0;
   }

   public List selectMenuTreeAll() throws Exception {
      return this.getChildPerms(this.menuMapper.selectMenuTreeAll(), 0);
   }
}
