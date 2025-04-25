package com.emr.project.monitor.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.security.LoginUser;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.monitor.domain.SysUserOnline;
import com.emr.project.system.service.ISysUserOnlineService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/monitor/online"})
public class SysUserOnlineController extends BaseController {
   @Autowired
   private ISysUserOnlineService userOnlineService;
   @Autowired
   private RedisCache redisCache;

   @PreAuthorize("@ss.hasPermi('monitor:online:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(String ipaddr, String userName) {
      Collection<String> keys = this.redisCache.keys("login_tokens:*");
      List<SysUserOnline> userOnlineList = new ArrayList();

      for(String key : keys) {
         LoginUser user = (LoginUser)this.redisCache.getCacheObject(key);
         if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName)) {
            if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername())) {
               userOnlineList.add(this.userOnlineService.selectOnlineByInfo(ipaddr, userName, user));
            }
         } else if (StringUtils.isNotEmpty(ipaddr)) {
            if (StringUtils.equals(ipaddr, user.getIpaddr())) {
               userOnlineList.add(this.userOnlineService.selectOnlineByIpaddr(ipaddr, user));
            }
         } else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser())) {
            if (StringUtils.equals(userName, user.getUsername())) {
               userOnlineList.add(this.userOnlineService.selectOnlineByUserName(userName, user));
            }
         } else {
            userOnlineList.add(this.userOnlineService.loginUserToUserOnline(user));
         }
      }

      Collections.reverse(userOnlineList);
      userOnlineList.removeAll(Collections.singleton((Object)null));
      return this.getDataTable(userOnlineList);
   }

   @PreAuthorize("@ss.hasPermi('monitor:online:forceLogout')")
   @Log(
      title = "在线用户",
      businessType = BusinessType.FORCE
   )
   @DeleteMapping({"/{tokenId}"})
   public AjaxResult forceLogout(@PathVariable String tokenId) {
      this.redisCache.deleteObject("login_tokens:" + tokenId);
      return AjaxResult.success();
   }
}
