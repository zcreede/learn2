package com.emr.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.framework.config.properties.DruidProperties;
import com.emr.framework.datasource.DynamicDataSource;
import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DruidConfig {
   @Bean
   @ConfigurationProperties("spring.datasource.druid.master")
   public DataSource masterDataSource(DruidProperties druidProperties) {
      DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
      return druidProperties.dataSource(dataSource);
   }

   @Bean
   @Primary
   public DynamicDataSource dataSource(DataSource masterDataSource) {
      DynamicDataSource dynamicDataSource = new DynamicDataSource();
      DynamicDataSource.dataSourcesMap.put("masterDataSource", masterDataSource);
      dynamicDataSource.setTargetDataSources(DynamicDataSource.dataSourcesMap);
      dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
      return dynamicDataSource;
   }

   public void setDataSource(Map targetDataSources, String sourceName, String beanName) {
      try {
         DataSource dataSource = (DataSource)SpringUtils.getBean(beanName);
         targetDataSources.put(sourceName, dataSource);
      } catch (Exception var5) {
      }

   }

   @Bean
   @ConditionalOnProperty(
      name = {"spring.datasource.druid.statViewServlet.enabled"},
      havingValue = "true"
   )
   public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
      DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
      String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
      String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
      String filePath = "support/http/resources/js/common.js";
      Filter filter = new Filter() {
         public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
         }

         public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            chain.doFilter(request, response);
            response.resetBuffer();
            String text = Utils.readFromResource("support/http/resources/js/common.js");
            text = text.replaceAll("<a.*?banner\"></a><br/>", "");
            text = text.replaceAll("powered.*?shrek.wang</a>", "");
            response.getWriter().write(text);
         }

         public void destroy() {
         }
      };
      FilterRegistrationBean registrationBean = new FilterRegistrationBean();
      registrationBean.setFilter(filter);
      registrationBean.addUrlPatterns(new String[]{commonJsPattern});
      return registrationBean;
   }
}
