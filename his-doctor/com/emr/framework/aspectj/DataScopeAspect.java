package com.emr.framework.aspectj;

import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.framework.aspectj.lang.annotation.DataScope;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.TokenService;
import com.emr.framework.web.domain.BaseEntity;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataScopeAspect {
   public static final String DATA_SCOPE_ALL = "1";
   public static final String DATA_SCOPE_CUSTOM = "2";
   public static final String DATA_SCOPE_DEPT = "3";
   public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";
   public static final String DATA_SCOPE_SELF = "5";
   public static final String DATA_SCOPE = "dataScope";

   @Pointcut("@annotation(com.emr.framework.aspectj.lang.annotation.DataScope)")
   public void dataScopePointCut() {
   }

   @Before("dataScopePointCut()")
   public void doBefore(JoinPoint point) throws Throwable {
      this.clearDataScope(point);
      this.handleDataScope(point);
   }

   protected void handleDataScope(final JoinPoint joinPoint) {
      DataScope controllerDataScope = this.getAnnotationLog(joinPoint);
      if (controllerDataScope != null) {
         LoginUser loginUser = ((TokenService)SpringUtils.getBean(TokenService.class)).getLoginUser(ServletUtils.getRequest());
         if (StringUtils.isNotNull(loginUser)) {
            SysUser currentUser = loginUser.getUser();
            if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin()) {
               dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(), controllerDataScope.userAlias());
            }
         }

      }
   }

   public static void dataScopeFilter(JoinPoint joinPoint, SysUser user, String deptAlias, String userAlias) {
      StringBuilder sqlString = new StringBuilder();

      for(SysRole role : user.getRoles()) {
         String dataScope = role.getDataScope();
         if ("1".equals(dataScope)) {
            sqlString = new StringBuilder();
            break;
         }

         if ("2".equals(dataScope)) {
            sqlString.append(StringUtils.format(" OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ", deptAlias, role.getRoleId()));
         } else if ("3".equals(dataScope)) {
            sqlString.append(StringUtils.format(" OR {}.dept_id = {} ", deptAlias, user.getDeptId()));
         } else if ("4".equals(dataScope)) {
            sqlString.append(StringUtils.format(" OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set({}, ancestors) > 0 )", deptAlias, user.getDeptId(), user.getDeptId()));
         } else if ("5".equals(dataScope)) {
            if (StringUtils.isNotBlank(userAlias)) {
               sqlString.append(StringUtils.format(" OR {}.user_id = {} ", userAlias, user.getUserId()));
            } else {
               sqlString.append(" OR 1=0 ");
            }
         }
      }

      if (StringUtils.isNotBlank(sqlString.toString())) {
         Object params = joinPoint.getArgs()[0];
         if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity)params;
            baseEntity.getParams().put("dataScope", " AND (" + sqlString.substring(4) + ")");
         }
      }

   }

   private DataScope getAnnotationLog(JoinPoint joinPoint) {
      Signature signature = joinPoint.getSignature();
      MethodSignature methodSignature = (MethodSignature)signature;
      Method method = methodSignature.getMethod();
      return method != null ? (DataScope)method.getAnnotation(DataScope.class) : null;
   }

   private void clearDataScope(final JoinPoint joinPoint) {
      Object params = joinPoint.getArgs()[0];
      if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
         BaseEntity baseEntity = (BaseEntity)params;
         baseEntity.getParams().put("dataScope", "");
      }

   }
}
