package com.emr.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.manager.AsyncManager;
import com.emr.framework.manager.factory.AsyncFactory;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.TokenService;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
   protected final Logger log = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);
   @Autowired
   private TokenService tokenService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
      LoginUser loginUser = this.tokenService.getLoginUser(request);
      if (StringUtils.isNotNull(loginUser)) {
         String userName = loginUser.getUsername();

         try {
            String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (StringUtils.isBlank(emrFlag) || StringUtils.isNotBlank(emrFlag) && !emrFlag.equals("1")) {
               String indexName = "drugandclin_zy_" + userName;
               this.drugAndClinService.dropDrugAndClinIndex(indexName);
            }
         } catch (Exception e) {
            this.log.error("删除用户{}的医嘱开立项目的es索引出现异常,", userName, e);
         }

         this.tokenService.delLoginUser(loginUser.getToken());
         this.tokenService.delLoginUserRedisInfos(loginUser);
         AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, "Logout", "退出成功"));
      }

      ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(200, "退出成功")));
   }
}
