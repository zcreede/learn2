package com.emr.framework.security.service;

import com.emr.common.constant.Constants;
import com.emr.common.exception.CustomException;
import com.emr.common.exception.user.CaptchaException;
import com.emr.common.exception.user.CaptchaExpireException;
import com.emr.common.exception.user.UserKeyNotMatchException;
import com.emr.common.exception.user.UserPasswordNotMatchException;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.ip.IpUtils;
import com.emr.framework.manager.AsyncManager;
import com.emr.framework.manager.factory.AsyncFactory;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.token.AutoLoginAuthenticationToken;
import com.emr.framework.security.token.CALoginAuthenticationToken;
import com.emr.framework.security.token.OperationRoomAuthenticationToken;
import com.emr.project.other.domain.GrantOutDoctor;
import com.emr.project.other.service.IGrantOutDoctorService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysUserService;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SysLoginService {
   @Autowired
   private TokenService tokenService;
   @Resource
   private AuthenticationManager authenticationManager;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISysUserService userService;
   @Autowired
   private IGrantOutDoctorService grantOutDoctorService;

   public String login(String username, String password, String code, String uuid) throws Exception {
      GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(username);
      if (grantOutDoctor != null) {
         if (grantOutDoctor.getValidFlag().equals("0")) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.overdue")));
            throw new CustomException(MessageUtils.message("user.disable"));
         }

         if (grantOutDoctor.getCreDate().compareTo(new Date()) > 0) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.overdue")));
            throw new CustomException(MessageUtils.message("user.nostart"));
         }

         if (grantOutDoctor.getEndDate().compareTo(new Date()) < 0) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.overdue")));
            throw new CustomException(MessageUtils.message("user.overdue"));
         }
      }

      String loginErrorKey = "login_error:" + username;
      Integer loginErrorNum = (Integer)this.redisCache.getCacheObject(loginErrorKey);
      loginErrorNum = loginErrorNum == null ? 0 : loginErrorNum;
      if (loginErrorNum >= 3) {
         if (StringUtils.isBlank(code) || StringUtils.isBlank(uuid)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException(MessageUtils.message("user.jcaptcha.error"));
         }

         String verifyKey = "captcha_codes:" + uuid;
         String captcha = (String)this.redisCache.getCacheObject(verifyKey);
         this.redisCache.deleteObject(verifyKey);
         if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
         }

         if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
         }
      }

      Authentication authentication = null;

      try {
         authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      } catch (Exception e) {
         loginErrorNum = loginErrorNum + 1;
         this.redisCache.setCacheObject(loginErrorKey, loginErrorNum, Constants.LOGIN_ERROR_EXPIRATION, TimeUnit.MINUTES);
         if (e instanceof BadCredentialsException) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
         }

         AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", e.getMessage()));
         throw new CustomException(e.getMessage());
      }

      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Success", MessageUtils.message("user.login.success")));
      LoginUser loginUser = (LoginUser)authentication.getPrincipal();
      this.recordLoginInfo(loginUser.getUser());
      return this.tokenService.createToken(loginUser);
   }

   public void recordLoginInfo(SysUser user) {
      user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
      user.setLoginDate(DateUtils.getNowDate());
      this.userService.updateUserProfile(user);
   }

   public String caLogin(String sn) throws Exception {
      Authentication authentication = null;

      try {
         authentication = this.authenticationManager.authenticate(new CALoginAuthenticationToken((Object)null, sn));
      } catch (Exception e) {
         if (e instanceof BadCredentialsException) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(sn, "Error", MessageUtils.message("user.key.not.match")));
            throw new UserKeyNotMatchException();
         }

         AsyncManager.me().execute(AsyncFactory.recordLogininfor(sn, "Error", e.getMessage()));
         throw new CustomException(e.getMessage());
      }

      LoginUser loginUser = (LoginUser)authentication.getDetails();
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginUser.getUser().getUserName(), "Success", MessageUtils.message("user.login.success")));
      this.recordLoginInfo(loginUser.getUser());
      return this.tokenService.createToken(loginUser);
   }

   public String orLogin(String emplNumber) throws Exception {
      Authentication authentication = null;

      try {
         authentication = this.authenticationManager.authenticate(new OperationRoomAuthenticationToken((Object)null, emplNumber));
      } catch (Exception e) {
         if (e instanceof BadCredentialsException) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(emplNumber, "Error", MessageUtils.message("user.key.not.match")));
            throw new UserKeyNotMatchException();
         }

         AsyncManager.me().execute(AsyncFactory.recordLogininfor(emplNumber, "Error", e.getMessage()));
         throw new CustomException(e.getMessage());
      }

      LoginUser loginUser = (LoginUser)authentication.getDetails();
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginUser.getUser().getUserName(), "Success", MessageUtils.message("user.login.success")));
      this.recordLoginInfo(loginUser.getUser());
      return this.tokenService.createToken(loginUser);
   }

   public String autoLogin(String username) {
      Authentication authentication = null;

      try {
         authentication = this.authenticationManager.authenticate(new AutoLoginAuthenticationToken((Object)null, username));
      } catch (Exception e) {
         if (e instanceof BadCredentialsException) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.key.not.match")));
            throw new UserKeyNotMatchException();
         }

         AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", e.getMessage()));
         throw new CustomException(e.getMessage());
      }

      LoginUser loginUser = (LoginUser)authentication.getDetails();
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginUser.getUser().getUserName(), "Success", MessageUtils.message("user.login.success")));
      this.recordLoginInfo(loginUser.getUser());
      return this.tokenService.createToken(loginUser);
   }
}
