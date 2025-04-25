package com.emr.framework.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.emr.common.utils.spring.SpringUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
   protected static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
   private static final ThreadLocal dataSourceKey = ThreadLocal.withInitial(() -> "masterDataSource");
   public static Map dataSourcesMap = new ConcurrentHashMap(10);

   protected Object determineCurrentLookupKey() {
      return dataSourceKey.get();
   }

   public static void setDataSource(String dataSource) {
      dataSourceKey.set(dataSource);
      DynamicDataSource dynamicDataSource = (DynamicDataSource)SpringUtils.getBean("dataSource");
      dynamicDataSource.afterPropertiesSet();
   }

   public static String getDataSource() {
      return (String)dataSourceKey.get();
   }

   public static void clear() {
      String dataSourceKey = (String)DynamicDataSource.dataSourceKey.get();
      if (!dataSourceKey.equals("masterDataSource")) {
         DynamicDataSource.dataSourceKey.remove();
      } else {
         log.warn("多数据线程错误，删除主数据源记录，masterDataSource");
      }

   }

   public static void releaseDataSource(String syncCode) {
      DruidDataSource dataSource = (DruidDataSource)dataSourcesMap.get(syncCode);
      if (dataSource != null && !syncCode.equals("masterDataSource")) {
         dataSource.close();
      }

   }
}
