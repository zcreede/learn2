package com.emr.framework.security.service;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.UUIDUtils;
import com.emr.common.utils.ip.AddressUtils;
import com.emr.common.utils.ip.IpUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.security.BsLoginStaff;
import com.emr.framework.security.LoginUser;
import com.emr.project.system.domain.SysUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenService {
   @Value("${token.header}")
   private String header;
   @Value("${token.secret}")
   private String secret;
   @Value("${token.expireTime}")
   private int expireTime;
   protected static final long MILLIS_SECOND = 1000L;
   protected static final long MILLIS_MINUTE = 60000L;
   private static final Long MILLIS_MINUTE_TEN = 1200000L;
   @Autowired
   private RedisCache redisCache;

   public LoginUser getLoginUser_2(HttpServletRequest request) {
      String token = this.getToken(request);
      if (StringUtils.isNotEmpty(token)) {
         try {
            Claims claims = this.parseToken(token);
            String uuid = (String)claims.get("login_user_key");
            String userKey = this.getTokenKey(uuid);
            LoginUser user = (LoginUser)this.redisCache.getCacheObject(userKey);
            return user;
         } catch (Exception var7) {
         }
      }

      return null;
   }

   public LoginUser getLoginUser(HttpServletRequest request) {
      String token = this.getToken(request);
      if (StringUtils.isNotEmpty(token)) {
         try {
            Claims claims = this.parseToken(token);
            String uuid = (String)claims.get("user_key");
            String userKey = this.getTokenKey(uuid);
            JSONObject loginUserObj = (JSONObject)this.redisCache.getCacheObject(userKey);
            LoginUser user = new LoginUser();
            user.setToken(uuid);
            if (loginUserObj != null) {
               user.setLoginTime(loginUserObj.getLong("loginTime"));
               user.setExpireTime(loginUserObj.getLong("expireTime"));
               user.setIpaddr(loginUserObj.getString("ipaddr"));
               user.setPermissions((Set)loginUserObj.get("permissions"));
               SysUser sysUser = (SysUser)loginUserObj.getObject("user", SysUser.class);
               user.setUser(sysUser);
            }

            return user;
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      return null;
   }

   public BsLoginStaff getLoginUsers(HttpServletRequest request) {
      String token = this.getToken(request);
      if (StringUtils.isNotEmpty(token)) {
         try {
            Claims claims = this.parseToken(token);
            String uuid = (String)claims.get("login_user_key");
            String userKey = this.getTokenKey(uuid);
            BsLoginStaff user = (BsLoginStaff)this.redisCache.getCacheObject(userKey);
            return user;
         } catch (Exception var7) {
         }
      }

      return null;
   }

   public LoginUser getLoginUser(String token) {
      if (StringUtils.isNotEmpty(token)) {
         Claims claims = this.parseToken(token);
         String uuid = (String)claims.get("login_user_key");
         String userKey = this.getTokenKey(uuid);
         LoginUser user = (LoginUser)this.redisCache.getCacheObject(userKey);
         return user;
      } else {
         return null;
      }
   }

   public void setLoginUser(LoginUser loginUser) {
      if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
         this.refreshToken(loginUser);
      }

   }

   public void delLoginUser(String token) {
      if (StringUtils.isNotEmpty(token)) {
         String userKey = this.getTokenKey(token);
         this.redisCache.deleteObject(userKey);
      }

   }

   public void delLoginUserRedisInfos(LoginUser loginUser) {
      String messageKey = "message_key:" + loginUser.getUsername();
      String lisPacsAlertKey = "lis_pacs_alert_key:" + loginUser.getUsername();
      this.redisCache.deleteObject(messageKey);
      this.redisCache.deleteObject(lisPacsAlertKey);
   }

   public String createToken(LoginUser loginUser) {
      String token = UUIDUtils.fastUUID();
      loginUser.setToken(token);
      this.setUserAgent(loginUser);
      this.refreshToken(loginUser);
      Map<String, Object> claims = new HashMap();
      claims.put("login_user_key", token);
      return this.createToken(claims);
   }

   public void verifyToken(BsLoginStaff bsLoginStaff) {
      long expireTime = bsLoginStaff.getExpireTime();
      long currentTime = System.currentTimeMillis();
      if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
         this.refreshToken(bsLoginStaff);
      }

   }

   public void verifyToken(LoginUser loginUser) {
      long expireTime = loginUser.getExpireTime();
      long currentTime = System.currentTimeMillis();
      if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
         this.refreshToken(loginUser);
      }

   }

   public void refreshToken(LoginUser loginUser) {
      loginUser.setLoginTime(System.currentTimeMillis());
      loginUser.setExpireTime(loginUser.getLoginTime() + (long)this.expireTime * 60000L);
      String userKey = this.getTokenKey(loginUser.getToken());
      this.redisCache.setCacheObject(userKey, loginUser, this.expireTime, TimeUnit.MINUTES);
   }

   public void refreshToken(BsLoginStaff bsLoginStaff) {
      bsLoginStaff.setLoginTime(System.currentTimeMillis());
      bsLoginStaff.setExpireTime(bsLoginStaff.getLoginTime() + (long)this.expireTime * 60000L);
      String userKey = this.getTokenKey(bsLoginStaff.getToken());
      this.redisCache.setCacheObject(userKey, bsLoginStaff, this.expireTime, TimeUnit.MINUTES);
   }

   public void setUserAgent(LoginUser loginUser) {
      UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
      String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
      loginUser.setIpaddr(ip);
      loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
      loginUser.setBrowser(userAgent.getBrowser().getName());
      loginUser.setOs(userAgent.getOperatingSystem().getName());
   }

   private String createToken(Map claims) {
      String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, this.secret).compact();
      return token;
   }

   private Claims parseToken(String token) {
      return (Claims)Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
   }

   public String getUsernameFromToken(String token) {
      Claims claims = this.parseToken(token);
      return claims.getSubject();
   }

   public String getToken(HttpServletRequest request) {
      String token = request.getHeader(this.header);
      if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer ")) {
         token = token.replace("Bearer ", "");
      }

      return token;
   }

   private String getTokenKey(String uuid) {
      return "login_tokens:" + uuid;
   }
}
