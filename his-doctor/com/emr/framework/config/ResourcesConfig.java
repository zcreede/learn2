package com.emr.framework.config;

import com.emr.framework.interceptor.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
   @Autowired
   private RepeatSubmitInterceptor repeatSubmitInterceptor;

   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler(new String[]{"/profile/**"}).addResourceLocations(new String[]{"file:" + EMRConfig.getProfile() + "/"});
      registry.addResourceHandler(new String[]{"/swagger-ui/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/springfox-swagger-ui/"});
   }

   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(this.repeatSubmitInterceptor).addPathPatterns(new String[]{"/**"});
   }

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOriginPattern("*");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
   }
}
