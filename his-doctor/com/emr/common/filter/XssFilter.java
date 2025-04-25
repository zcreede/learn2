package com.emr.common.filter;

import com.emr.common.utils.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XssFilter implements Filter {
   public List excludes = new ArrayList();
   public boolean enabled = false;

   public void init(FilterConfig filterConfig) throws ServletException {
      String tempExcludes = filterConfig.getInitParameter("excludes");
      String tempEnabled = filterConfig.getInitParameter("enabled");
      if (StringUtils.isNotEmpty(tempExcludes)) {
         String[] url = tempExcludes.split(",");

         for(int i = 0; url != null && i < url.length; ++i) {
            this.excludes.add(url[i]);
         }
      }

      if (StringUtils.isNotEmpty(tempEnabled)) {
         this.enabled = Boolean.valueOf(tempEnabled);
      }

   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest)request;
      HttpServletResponse resp = (HttpServletResponse)response;
      if (this.handleExcludeURL(req, resp)) {
         chain.doFilter(request, response);
      } else {
         XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest)request);
         chain.doFilter(xssRequest, response);
      }
   }

   private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
      if (!this.enabled) {
         return true;
      } else if (this.excludes != null && !this.excludes.isEmpty()) {
         String url = request.getServletPath();

         for(String pattern : this.excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void destroy() {
   }
}
