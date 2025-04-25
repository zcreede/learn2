package com.emr.project.system.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.framework.aspectj.lang.annotation.DataScope;
import com.emr.project.emr.domain.BasEmrAcceAuthor;
import com.emr.project.emr.service.IBasEmrAcceAuthorService;
import com.emr.project.system.domain.SysEmployeeRoleDept;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysRoleMenu;
import com.emr.project.system.domain.SysUserRole;
import com.emr.project.system.mapper.SysRoleMapper;
import com.emr.project.system.mapper.SysRoleMenuMapper;
import com.emr.project.system.mapper.SysUserRoleMapper;
import com.emr.project.system.service.ISysRoleService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysRoleServiceImpl implements ISysRoleService {
   @Autowired
   private SysRoleMapper roleMapper;
   @Autowired
   private SysRoleMenuMapper roleMenuMapper;
   @Autowired
   private SysUserRoleMapper userRoleMapper;
   @Resource
   private IBasEmrAcceAuthorService basEmrAcceAuthorService;

   @DataScope(
      deptAlias = "d"
   )
   public List selectRoleList(SysRole role) {
      return this.roleMapper.selectRoleList(role);
   }

   public List selectRolesByUserId(Long userId) {
      List<SysRole> userRoles = this.roleMapper.selectRolePermissionByUserId(userId, (Long)null);
      List<SysRole> roles = this.selectRoleAll();

      for(SysRole role : roles) {
         for(SysRole userRole : userRoles) {
            if (role.getRoleId() == userRole.getRoleId()) {
               role.setFlag(true);
               break;
            }
         }
      }

      return roles;
   }

   public Set selectRolePermissionByUserId(Long userId, Long deptId) {
      List<SysRole> perms = this.roleMapper.selectRolePermissionByUserId(userId, deptId);
      Set<String> permsSet = new HashSet();

      for(SysRole perm : perms) {
         if (StringUtils.isNotNull(perm)) {
            permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
         }
      }

      return permsSet;
   }

   public List selectRoleAll() {
      return ((SysRoleServiceImpl)SpringUtils.getAopProxy(this)).selectRoleList(new SysRole());
   }

   public List selectRoleListByUserId(Long userId) {
      return this.roleMapper.selectRoleListByUserId(userId);
   }

   public SysRole selectRoleById(Long roleId) {
      return this.roleMapper.selectRoleById(roleId);
   }

   public String checkRoleNameUnique(SysRole role) {
      Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
      SysRole info = this.roleMapper.checkRoleNameUnique(role.getRoleName());
      return StringUtils.isNotNull(info) && info.getRoleId() != roleId ? "1" : "0";
   }

   public String checkRoleKeyUnique(SysRole role) {
      Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
      SysRole info = this.roleMapper.checkRoleKeyUnique(role.getRoleKey());
      return StringUtils.isNotNull(info) && info.getRoleId() != roleId ? "1" : "0";
   }

   public void checkRoleAllowed(SysRole role) {
      if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin()) {
         throw new CustomException("不允许操作超级管理员角色");
      }
   }

   public int countUserRoleByRoleId(Long roleId) {
      return this.userRoleMapper.countUserRoleByRoleId(roleId);
   }

   @Transactional
   public int insertRole(SysRole role) {
      this.roleMapper.insertRole(role);
      BasEmrAcceAuthor basEmrAcceAuthor = new BasEmrAcceAuthor();
      basEmrAcceAuthor.setObjectId(role.getRoleId().toString());
      basEmrAcceAuthor.setObjectType(CommonConstants.BAS_EMR_ACCE_AUTHOR.OBJECT_TYPE_1);
      basEmrAcceAuthor.setAuthorLevel("1");
      basEmrAcceAuthor.setDelFlag("0");
      this.basEmrAcceAuthorService.insertBasEmrAcceAuthor(basEmrAcceAuthor);
      return this.insertRoleMenu(role);
   }

   @Transactional
   public int updateRole(SysRole role) {
      this.roleMapper.updateRole(role);
      this.roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
      return this.insertRoleMenu(role);
   }

   public int updateRoleStatus(SysRole role) {
      return this.roleMapper.updateRole(role);
   }

   @Transactional
   public int authDataScope(SysRole role) {
      this.roleMapper.updateRole(role);
      return this.insertRoleDept(role);
   }

   public int insertRoleMenu(SysRole role) {
      int rows = 1;
      List<SysRoleMenu> list = new ArrayList();

      for(Long menuId : role.getMenuIds()) {
         SysRoleMenu rm = new SysRoleMenu();
         rm.setRoleId(role.getRoleId());
         rm.setMenuId(menuId);
         list.add(rm);
      }

      if (list.size() > 0) {
         rows = this.roleMenuMapper.batchRoleMenu(list);
      }

      return rows;
   }

   public int insertRoleDept(SysRole role) {
      int rows = 1;
      List<SysEmployeeRoleDept> list = new ArrayList();

      for(String deptId : role.getDeptIds()) {
         SysEmployeeRoleDept rd = new SysEmployeeRoleDept();
         rd.setRoleId(role.getRoleKey());
         rd.setDeptId(deptId);
         list.add(rd);
      }

      if (list.size() > 0) {
      }

      return rows;
   }

   @Transactional
   public int deleteRoleById(Long roleId) {
      this.roleMenuMapper.deleteRoleMenuByRoleId(roleId);
      return this.roleMapper.deleteRoleById(roleId);
   }

   @Transactional
   public int deleteRoleByIds(Long[] roleIds) {
      for(Long roleId : roleIds) {
         this.checkRoleAllowed(new SysRole(roleId));
         this.selectRoleById(roleId);
      }

      this.roleMenuMapper.deleteRoleMenu(roleIds);
      return this.roleMapper.deleteRoleByIds(roleIds);
   }

   public int deleteAuthUser(SysUserRole userRole) {
      return this.userRoleMapper.deleteUserRoleInfo(userRole);
   }

   public int deleteAuthUsers(Long roleId, Long[] userIds) {
      return this.userRoleMapper.deleteUserRoleInfos(roleId, userIds);
   }

   public int insertAuthUsers(Long roleId, Long[] userIds) {
      List<SysUserRole> list = new ArrayList();

      for(Long var10000 : userIds) {
         SysUserRole ur = new SysUserRole();
         list.add(ur);
      }

      return this.userRoleMapper.batchUserRole(list);
   }

   public List selectRoleListByIds(List roleIdList) {
      return this.roleMapper.selectRoleListByIds(roleIdList);
   }
}
