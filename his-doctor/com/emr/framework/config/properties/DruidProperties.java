package com.emr.framework.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidProperties {
   @Value("${spring.datasource.druid.initialSize}")
   private int initialSize;
   @Value("${spring.datasource.druid.minIdle}")
   private int minIdle;
   @Value("${spring.datasource.druid.maxActive}")
   private int maxActive;
   @Value("${spring.datasource.druid.maxWait}")
   private int maxWait;
   @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
   private int timeBetweenEvictionRunsMillis;
   @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
   private int minEvictableIdleTimeMillis;
   @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
   private int maxEvictableIdleTimeMillis;
   @Value("${spring.datasource.druid.validationQuery}")
   private String validationQuery;
   @Value("${spring.datasource.druid.validationQuery1}")
   private String validationQuery1;
   @Value("${spring.datasource.druid.initialSize1}")
   private int initialSize1;
   @Value("${spring.datasource.druid.minIdle1}")
   private int minIdle1;
   @Value("${spring.datasource.druid.maxActive1}")
   private int maxActive1;
   @Value("${spring.datasource.druid.testWhileIdle1}")
   private boolean testWhileIdle1;
   @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis1}")
   private int timeBetweenEvictionRunsMillis1;
   @Value("${spring.datasource.druid.minEvictableIdleTimeMillis1}")
   private int minEvictableIdleTimeMillis1;
   @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis1}")
   private int maxEvictableIdleTimeMillis1;
   @Value("${spring.datasource.druid.validationQuery2}")
   private String validationQuery2;
   @Value("${spring.datasource.druid.validationQuery3}")
   private String validationQuery3;
   @Value("${spring.datasource.druid.validationQuery4}")
   private String validationQuery4;
   @Value("${spring.datasource.druid.validationQuery5}")
   private String validationQuery5;
   @Value("${spring.datasource.druid.testWhileIdle}")
   private boolean testWhileIdle;
   @Value("${spring.datasource.druid.testOnBorrow}")
   private boolean testOnBorrow;
   @Value("${spring.datasource.druid.testOnReturn}")
   private boolean testOnReturn;

   public DruidDataSource dataSource(DruidDataSource datasource) {
      datasource.setInitialSize(this.initialSize);
      datasource.setMaxActive(this.maxActive);
      datasource.setMinIdle(this.minIdle);
      datasource.setMaxWait((long)this.maxWait);
      datasource.setTimeBetweenEvictionRunsMillis((long)this.timeBetweenEvictionRunsMillis);
      datasource.setMinEvictableIdleTimeMillis((long)this.minEvictableIdleTimeMillis);
      datasource.setMaxEvictableIdleTimeMillis((long)this.maxEvictableIdleTimeMillis);
      datasource.setValidationQuery(this.validationQuery);
      datasource.setTestWhileIdle(this.testWhileIdle);
      datasource.setTestOnBorrow(this.testOnBorrow);
      datasource.setTestOnReturn(this.testOnReturn);
      return datasource;
   }

   public DruidDataSource dataSource(DruidDataSource datasource, String datebaseType) {
      datasource.setInitialSize(this.initialSize1);
      datasource.setMaxActive(this.maxActive1);
      datasource.setMinIdle(this.minIdle1);
      datasource.setMaxWait((long)this.maxWait);
      datasource.setTimeBetweenEvictionRunsMillis((long)this.timeBetweenEvictionRunsMillis1);
      datasource.setMinEvictableIdleTimeMillis((long)this.minEvictableIdleTimeMillis1);
      datasource.setMaxEvictableIdleTimeMillis((long)this.maxEvictableIdleTimeMillis1);
      datasource.setTestWhileIdle(this.testWhileIdle1);
      datasource.setTestOnBorrow(this.testOnBorrow);
      datasource.setTestOnReturn(this.testOnReturn);
      switch (datebaseType) {
         case "DB2":
            datasource.setValidationQuery(this.validationQuery3);
            break;
         case "SQLServer":
            datasource.setValidationQuery(this.validationQuery1);
            break;
         case "MySQL":
            datasource.setValidationQuery(this.validationQuery1);
            break;
         case "Ingres":
            datasource.setValidationQuery(this.validationQuery1);
            break;
         case "H2":
            datasource.setValidationQuery(this.validationQuery1);
            break;
         case "SQLite":
            datasource.setValidationQuery(this.validationQuery1);
            break;
         case "HSqlDB":
            datasource.setValidationQuery(this.validationQuery2);
            break;
         case "PostgreSQL":
            datasource.setValidationQuery(this.validationQuery4);
            break;
         case "Informix":
            datasource.setValidationQuery(this.validationQuery5);
            break;
         default:
            datasource.setValidationQuery(this.validationQuery);
      }

      return datasource;
   }

   public String getValidationQuery() {
      return this.validationQuery;
   }

   public void setValidationQuery(String validationQuery) {
      this.validationQuery = validationQuery;
   }

   public String getValidationQuery1() {
      return this.validationQuery1;
   }

   public void setValidationQuery1(String validationQuery1) {
      this.validationQuery1 = validationQuery1;
   }

   public String getValidationQuery2() {
      return this.validationQuery2;
   }

   public void setValidationQuery2(String validationQuery2) {
      this.validationQuery2 = validationQuery2;
   }

   public String getValidationQuery3() {
      return this.validationQuery3;
   }

   public void setValidationQuery3(String validationQuery3) {
      this.validationQuery3 = validationQuery3;
   }

   public String getValidationQuery4() {
      return this.validationQuery4;
   }

   public void setValidationQuery4(String validationQuery4) {
      this.validationQuery4 = validationQuery4;
   }

   public String getValidationQuery5() {
      return this.validationQuery5;
   }

   public void setValidationQuery5(String validationQuery5) {
      this.validationQuery5 = validationQuery5;
   }
}
