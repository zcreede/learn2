package com.emr.framework.manager.factory;

import com.emr.common.utils.LogUtils;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.ip.AddressUtils;
import com.emr.common.utils.ip.IpUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.project.monitor.domain.SysLogininfor;
import com.emr.project.monitor.domain.SysOperLog;
import com.emr.project.monitor.service.ISysLogininforService;
import com.emr.project.monitor.service.ISysOperLogService;
import eu.bitwalker.useragentutils.UserAgent;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncFactory {
   private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

   public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args) {
      final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
      final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
      return new TimerTask() {
         public void run() {
            String address = AddressUtils.getRealAddressByIP(ip);
            StringBuilder s = new StringBuilder();
            s.append(LogUtils.getBlock(ip));
            s.append(address);
            s.append(LogUtils.getBlock(username));
            s.append(LogUtils.getBlock(status));
            s.append(LogUtils.getBlock(message));
            AsyncFactory.sys_user_logger.info(s.toString(), args);
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();
            SysLogininfor logininfor = new SysLogininfor();
            logininfor.setUserName(username);
            logininfor.setIpaddr(ip);
            logininfor.setLoginLocation(address);
            logininfor.setBrowser(browser);
            logininfor.setOs(os);
            logininfor.setMsg(message);
            if (!"Success".equals(status) && !"Logout".equals(status)) {
               if ("Error".equals(status)) {
                  logininfor.setStatus("1");
               }
            } else {
               logininfor.setStatus("0");
            }

            ((ISysLogininforService)SpringUtils.getBean(ISysLogininforService.class)).insertLogininfor(logininfor);
         }
      };
   }

   public static TimerTask recordOper(final SysOperLog operLog) {
      return new TimerTask() {
         public void run() {
            operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
            ((ISysOperLogService)SpringUtils.getBean(ISysOperLogService.class)).insertOperlog(operLog);
         }
      };
   }
}
