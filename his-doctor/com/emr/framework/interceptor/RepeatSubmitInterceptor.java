package com.emr.framework.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.ServletUtils;
import com.emr.framework.interceptor.annotation.RepeatSubmit;
import com.emr.framework.web.domain.AjaxResult;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public abstract class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      if (handler instanceof HandlerMethod) {
         HandlerMethod handlerMethod = (HandlerMethod)handler;
         Method method = handlerMethod.getMethod();
         RepeatSubmit annotation = (RepeatSubmit)method.getAnnotation(RepeatSubmit.class);
         if (annotation != null && this.isRepeatSubmit(request)) {
            AjaxResult ajaxResult = AjaxResult.error("不允许重复提交，请稍后再试");
            ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
            return false;
         } else {
            return true;
         }
      } else {
         return super.preHandle(request, response, handler);
      }
   }

   public abstract boolean isRepeatSubmit(HttpServletRequest request);
}
