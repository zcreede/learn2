package com.emr.framework.config;

import com.emr.common.filter.RepeatableFilter;
import com.emr.common.filter.XssFilter;
import com.emr.common.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
   @Value("${xss.enabled}")
   private String enabled;
   @Value("${xss.excludes}")
   private String excludes;
   @Value("${xss.urlPatterns}")
   private String urlPatterns;

   @Bean
   public FilterRegistrationBean xssFilterRegistration() {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setDispatcherTypes(DispatcherType.REQUEST, new DispatcherType[0]);
      registration.setFilter(new XssFilter());
      registration.addUrlPatterns(StringUtils.split(this.urlPatterns, ","));
      registration.setName("xssFilter");
      registration.setOrder(Integer.MIN_VALUE);
      Map<String, String> initParameters = new HashMap();
      initParameters.put("excludes", this.excludes);
      initParameters.put("enabled", this.enabled);
      registration.setInitParameters(initParameters);
      return registration;
   }

   @Bean
   public FilterRegistrationBean someFilterRegistration() {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(new RepeatableFilter());
      registration.addUrlPatterns(new String[]{"/*"});
      registration.setName("repeatableFilter");
      registration.setOrder(Integer.MAX_VALUE);
      return registration;
   }
}
