package com.emr.framework.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.framework.config.properties.DruidProperties;
import com.emr.project.system.domain.SyncDatasource;
import org.springframework.stereotype.Component;

@Component
public class DruidUtil {
   public static void switchDB(SyncDatasource syncDatasource) {
      DruidDataSource dataSource = (DruidDataSource)DynamicDataSource.dataSourcesMap.get(syncDatasource.getSyncCode());
      if (dataSource == null) {
         dataSource = new DruidDataSource();
         dataSource.setDriverClassName(syncDatasource.getDatabaseDcn());
         dataSource.setUrl(syncDatasource.getDatabaseUrl());
         dataSource.setUsername(syncDatasource.getDatabaseUsername());
         dataSource.setPassword(syncDatasource.getDatabasePassword());
         DruidProperties druidProperties = (DruidProperties)SpringUtils.getBean("druidProperties");
         druidProperties.dataSource(dataSource, syncDatasource.getDatabaseType());
         DynamicDataSource.dataSourcesMap.put(syncDatasource.getSyncCode(), dataSource);
      }

      DynamicDataSource.setDataSource(syncDatasource.getSyncCode());
   }

   public static void clearDataSource() {
      DynamicDataSource.clear();
   }

   public static void releaseDataSource(String syncCode) {
      DynamicDataSource.releaseDataSource(syncCode);
   }
}
