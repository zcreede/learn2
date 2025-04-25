package com.emr.framework.security.service;

import com.emr.common.enums.UserStatus;
import com.emr.common.exception.BaseException;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.security.LoginUser;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmBsModule;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysOrgService;
import com.emr.project.system.service.ISysUserService;
import com.emr.project.system.service.ITmBsModuleService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
   @Autowired
   private ISysUserService userService;
   @Autowired
   private SysPermissionService permissionService;
   @Autowired
   private ISysDeptService deptService;
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private ISysOrgService sysOrgService;
   @Autowired
   private ITmBsModuleService moduleService;

   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      SysUser user = this.userService.selectUserByUserName(username);
      if (StringUtils.isNull(user)) {
         log.info("登录用户：{} 不存在.", username);
         throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
      } else {
         if (!SecurityUtils.isAdmin(user.getUserId())) {
            if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
               log.info("登录用户：{} 已被删除.", username);
               throw new BaseException("对不起，您的账号：" + username + " 已被删除");
            }

            if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
               log.info("登录用户：{} 已被停用.", username);
               throw new BaseException("对不起，您的账号：" + username + " 已停用");
            }

            BasEmployee basEmployee = null;

            try {
               BasEmployee var8 = this.basEmployeeService.selectBasEmployeeById(user.getEmployeeId());
               if (var8 == null) {
                  log.info("登录用户：{} 未绑定人员信息", username);
                  throw new BaseException("登录用户：" + username + " 未绑定人员信息");
               }

               user.setBasEmployee(var8);
               List<SysDept> deptList = this.deptService.selectDeptByEmployeeId(((BasEmployee)var8).getEmplNumber());
               List<TmBsModule> moduleList = this.moduleService.selectModuleListByUser(((BasEmployee)var8).getEmplNumber());
               user.setDepts(deptList);
               user.setModuleList(moduleList);
               if (CollectionUtils.isNotEmpty(deptList) && CollectionUtils.isNotEmpty(moduleList) && deptList.size() == 1 && moduleList.size() == 1) {
                  user.setDept((SysDept)deptList.get(0));
                  user.setDeptId(((SysDept)deptList.get(0)).getDeptId());
                  user.setDeptCode(((SysDept)deptList.get(0)).getDeptCode());
                  SysOrg sysOrg = this.sysOrgService.getSysOrgByDept((SysDept)deptList.get(0));
                  user.setHospital(sysOrg);
                  user.setHospitalId(sysOrg.getId());
                  user.setModule((TmBsModule)moduleList.get(0));
               }
            } catch (Exception e) {
               log.info("登录用户：{} 查询绑定人员信息出现异常", username);
               log.info("登录用户：{} 查询绑定人员信息出现异常", e);
               throw new BaseException("登录用户：" + username + " 查询绑定人员信息出现异常");
            }
         } else {
            if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
               log.info("登录用户：{} 已被删除.", username);
               throw new BaseException("对不起，您的账号：" + username + " 已被删除");
            }

            if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
               log.info("登录用户：{} 已被停用.", username);
               throw new BaseException("对不起，您的账号：" + username + " 已停用");
            }
         }

         return this.createLoginUser(user);
      }
   }

   public UserDetails createLoginUser(SysUser user) {
      return new LoginUser(user, this.permissionService.getMenuPermission(user));
   }
}
