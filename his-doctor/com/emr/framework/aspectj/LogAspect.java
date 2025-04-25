package com.emr.framework.aspectj;

import com.alibaba.fastjson.JSON;
import com.emr.common.enums.HttpMethod;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.ip.IpUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessStatus;
import com.emr.framework.manager.AsyncManager;
import com.emr.framework.manager.factory.AsyncFactory;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.TokenService;
import com.emr.project.monitor.domain.SysOperLog;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

@Aspect
@Component
public class LogAspect {
   private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

   @Pointcut("@annotation(com.emr.framework.aspectj.lang.annotation.Log)")
   public void logPointCut() {
   }

   @AfterReturning(
      pointcut = "logPointCut()",
      returning = "jsonResult"
   )
   public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
      this.handleLog(joinPoint, (Exception)null, jsonResult);
   }

   @AfterThrowing(
      value = "logPointCut()",
      throwing = "e"
   )
   public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
      this.handleLog(joinPoint, e, (Object)null);
   }

   protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
      try {
         Log controllerLog = this.getAnnotationLog(joinPoint);
         if (controllerLog == null) {
            return;
         }

         LoginUser loginUser = ((TokenService)SpringUtils.getBean(TokenService.class)).getLoginUser(ServletUtils.getRequest());
         SysOperLog operLog = new SysOperLog();
         operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
         String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
         operLog.setOperIp(ip);
         operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
         operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
         if (loginUser != null) {
            operLog.setOperName(loginUser.getUsername());
         }

         if (e != null) {
            operLog.setStatus(BusinessStatus.FAIL.ordinal());
            operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
         }

         String className = joinPoint.getTarget().getClass().getName();
         String methodName = joinPoint.getSignature().getName();
         operLog.setMethod(className + "." + methodName + "()");
         operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
         this.getControllerMethodDescription(joinPoint, controllerLog, operLog);
         AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
      } catch (Exception exp) {
         log.error("==前置通知异常==");
         log.error("异常信息:{}", exp.getMessage());
         exp.printStackTrace();
      }

   }

   public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog) throws Exception {
      operLog.setBusinessType(log.businessType().ordinal());
      operLog.setTitle(log.title());
      operLog.setOperatorType(log.operatorType().ordinal());
      if (log.isSaveRequestData()) {
         this.setRequestValue(joinPoint, operLog);
      }

   }

   private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog) throws Exception {
      String requestMethod = operLog.getRequestMethod();
      if (!HttpMethod.PUT.name().equals(requestMethod) && !HttpMethod.POST.name().equals(requestMethod)) {
         Map<?, ?> paramsMap = (Map)ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
         operLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
      } else {
         String params = this.argsArrayToString(joinPoint.getArgs());
         operLog.setOperParam(StringUtils.substring(params, 0, 2000));
      }

   }

   private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
      Signature signature = joinPoint.getSignature();
      MethodSignature methodSignature = (MethodSignature)signature;
      Method method = methodSignature.getMethod();
      return method != null ? (Log)method.getAnnotation(Log.class) : null;
   }

   private String argsArrayToString(Object[] paramsArray) {
      String params = "";
      if (paramsArray != null && paramsArray.length > 0) {
         for(int i = 0; i < paramsArray.length; ++i) {
            if (StringUtils.isNotNull(paramsArray[i]) && !this.isFilterObject(paramsArray[i])) {
               Object jsonObj = JSON.toJSON(paramsArray[i]);
               params = params + jsonObj.toString() + " ";
            }
         }
      }

      return params.trim();
   }

   public boolean isFilterObject(final Object o) {
      Class<?> clazz = o.getClass();
      if (clazz.isArray()) {
         return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
      } else {
         if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection)o;
            Iterator iter = collection.iterator();
            if (iter.hasNext()) {
               return iter.next() instanceof MultipartFile;
            }
         } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map)o;
            Iterator iter = map.entrySet().iterator();
            if (iter.hasNext()) {
               Map.Entry entry = (Map.Entry)iter.next();
               return entry.getValue() instanceof MultipartFile;
            }
         }

         return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof BindingResult;
      }
   }
}
