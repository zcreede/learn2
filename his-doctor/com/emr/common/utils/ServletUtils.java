package com.emr.common.utils;

import com.emr.common.core.text.Convert;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtils {
   public static String getParameter(String name) {
      return getRequest().getParameter(name);
   }

   public static String getParameter(String name, String defaultValue) {
      return Convert.toStr(getRequest().getParameter(name), defaultValue);
   }

   public static Integer getParameterToInt(String name) {
      return Convert.toInt(getRequest().getParameter(name));
   }

   public static Integer getParameterToInt(String name, Integer defaultValue) {
      return Convert.toInt(getRequest().getParameter(name), defaultValue);
   }

   public static HttpServletRequest getRequest() {
      return getRequestAttributes().getRequest();
   }

   public static HttpServletResponse getResponse() {
      return getRequestAttributes().getResponse();
   }

   public static HttpSession getSession() {
      return getRequest().getSession();
   }

   public static ServletRequestAttributes getRequestAttributes() {
      RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
      return (ServletRequestAttributes)attributes;
   }

   public static String renderString(HttpServletResponse response, String string) {
      try {
         response.setStatus(200);
         response.setContentType("application/json");
         response.setCharacterEncoding("utf-8");
         response.getWriter().print(string);
      } catch (IOException e) {
         e.printStackTrace();
      }

      return null;
   }

   public static boolean isAjaxRequest(HttpServletRequest request) {
      String accept = request.getHeader("accept");
      if (accept != null && accept.indexOf("application/json") != -1) {
         return true;
      } else {
         String xRequestedWith = request.getHeader("X-Requested-With");
         if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
         } else {
            String uri = request.getRequestURI();
            if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
               return true;
            } else {
               String ajax = request.getParameter("__ajax");
               return StringUtils.inStringIgnoreCase(ajax, "json", "xml");
            }
         }
      }
   }
}
