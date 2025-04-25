package com.emr.project.system.service;

import com.emr.framework.security.LoginUser;
import com.emr.project.monitor.domain.SysUserOnline;

public interface ISysUserOnlineService {
   SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user);

   SysUserOnline selectOnlineByUserName(String userName, LoginUser user);

   SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user);

   SysUserOnline loginUserToUserOnline(LoginUser user);
}
