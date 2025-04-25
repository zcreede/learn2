package com.emr.framework.aspectj;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.service.ISyncDatasourceService;
import java.util.Objects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DataSourceAspect {
   protected Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private ISyncDatasourceService syncDatasourceService;

   @Pointcut("@annotation(com.emr.framework.aspectj.lang.annotation.DataSource)|| @within(com.emr.framework.aspectj.lang.annotation.DataSource)")
   public void dsPointCut() {
   }

   @Around("dsPointCut()")
   public Object around(ProceedingJoinPoint point) throws Throwable {
      DataSource dataSource = this.getDataSource(point);
      if (StringUtils.isNotNull(dataSource)) {
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(dataSource.value().name());
         DruidUtil.switchDB(syncDatasource);
      }

      Object var7;
      try {
         var7 = point.proceed();
      } finally {
         DruidUtil.clearDataSource();
      }

      return var7;
   }

   public DataSource getDataSource(ProceedingJoinPoint point) {
      MethodSignature signature = (MethodSignature)point.getSignature();
      DataSource dataSource = (DataSource)AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
      return Objects.nonNull(dataSource) ? dataSource : (DataSource)AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
   }
}
