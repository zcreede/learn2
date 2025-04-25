package com.emr.project.system.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.framework.security.LoginUser;
import com.emr.project.monitor.domain.SysUserOnline;
import com.emr.project.system.service.ISysUserOnlineService;
import org.springframework.stereotype.Service;

@Service
public class SysUserOnlineServiceImpl implements ISysUserOnlineService {
   public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user) {
      return StringUtils.equals(ipaddr, user.getIpaddr()) ? this.loginUserToUserOnline(user) : null;
   }

   public SysUserOnline selectOnlineByUserName(String userName, LoginUser user) {
      return StringUtils.equals(userName, user.getUsername()) ? this.loginUserToUserOnline(user) : null;
   }

   public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user) {
      return StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()) ? this.loginUserToUserOnline(user) : null;
   }

   public SysUserOnline loginUserToUserOnline(LoginUser user) {
      if (!StringUtils.isNull(user) && !StringUtils.isNull(user.getUser())) {
         SysUserOnline sysUserOnline = new SysUserOnline();
         sysUserOnline.setTokenId(user.getToken());
         sysUserOnline.setUserName(user.getUsername());
         sysUserOnline.setIpaddr(user.getIpaddr());
         sysUserOnline.setLoginLocation(user.getLoginLocation());
         sysUserOnline.setBrowser(user.getBrowser());
         sysUserOnline.setOs(user.getOs());
         sysUserOnline.setLoginTime(user.getLoginTime());
         if (StringUtils.isNotNull(user.getUser().getDept())) {
            sysUserOnline.setDeptName(user.getUser().getDept().getDeptName());
         }

         return sysUserOnline;
      } else {
         return null;
      }
   }
}
